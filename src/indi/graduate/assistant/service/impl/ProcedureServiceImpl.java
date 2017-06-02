package indi.graduate.assistant.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.DictionaryBeanMapper;
import indi.graduate.assistant.dao.InterviewBeanMapper;
import indi.graduate.assistant.dao.InterviewCategoryBeanMapper;
import indi.graduate.assistant.dao.InterviewDetailBeanMapper;
import indi.graduate.assistant.dao.IntervieweeBeanMapper;
import indi.graduate.assistant.dao.QuestionBeanMapper;
import indi.graduate.assistant.dao.UserBeanMapper;
import indi.graduate.assistant.dto.DynamicCombox;
import indi.graduate.assistant.dto.InterviewReportDto;
import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.dto.IsStartUpInterviewDto;
import indi.graduate.assistant.dto.QuestionDto;
import indi.graduate.assistant.dto.ScheduleDto;
import indi.graduate.assistant.model.DictionaryBean;
import indi.graduate.assistant.model.InterviewBean;
import indi.graduate.assistant.model.InterviewCategoryBean;
import indi.graduate.assistant.model.InterviewDetailBean;
import indi.graduate.assistant.model.IntervieweeBean;
import indi.graduate.assistant.model.QuestionBeanWithBLOBs;
import indi.graduate.assistant.model.UserBean;
import indi.graduate.assistant.service.ProcedureService;
import indi.graduate.assistant.util.RegexCheck;

@Service("ProcedureService")
public class ProcedureServiceImpl implements ProcedureService {
	@Autowired
	private InterviewBeanMapper interviewBeanMapper;
	@Autowired
	private IntervieweeBeanMapper intervieweeBeanMapper;
	@Autowired
	private QuestionBeanMapper questionBeanMapper;
	@Autowired
	private InterviewDetailBeanMapper interviewDetailBeanMapper;
	@Autowired
	private DictionaryBeanMapper dictionaryBeanMapper;
	@Autowired
	private InterviewCategoryBeanMapper interviewCategoryBeanMapper;
	@Autowired
	private UserBeanMapper userBeanMapper;

	/** 获取面试人列表 **/
	public Map<String, Object> startIntervieweeList(InterviewSearchDto interviewSearchDto) {

		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (interviewSearchDto.getPageNo() - 1) * interviewSearchDto.getPageSize());
		mm.put("pageSize", interviewSearchDto.getPageSize());
		if (interviewSearchDto.getSearchName() != null && !interviewSearchDto.getSearchName().trim().equals("")) {
			if (RegexCheck.regexCheck(interviewSearchDto.getSearchName(), RegexCheck.phoneCheck))
				mm.put("intervieweePhone", interviewSearchDto.getSearchName().trim());
			else
				mm.put("intervieweeName", interviewSearchDto.getSearchName().trim());
		}
		if (interviewSearchDto.getIntervieweeStatus() == 1 || interviewSearchDto.getIntervieweeStatus() == 2 || interviewSearchDto.getIntervieweeStatus() == 3 || interviewSearchDto.getIntervieweeStatus() == 4)
			mm.put("intervieweeStatus", interviewSearchDto.getIntervieweeStatus());
		List<IsStartUpInterviewDto> result = new ArrayList<>();
		int rowCount = 0;
		try {
			result = interviewBeanMapper.selectInterviewList(mm);
			rowCount = interviewBeanMapper.countAccounts(mm);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		if (result != null) {
			for (IsStartUpInterviewDto isStartUpInterviewDto : result) {
				transformation(isStartUpInterviewDto);
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", rowCount);
		}

		return resultMap;
	}

	/** 启动面试过程 **/
	public IsStartUpInterviewDto startprocedure(int interviewId, int interviewWay, int interviewType) {

		IsStartUpInterviewDto isStartUpInterviewDto = new IsStartUpInterviewDto();
		IntervieweeBean intervieweeBean = new IntervieweeBean();
		InterviewBean interviewBean = new InterviewBean();

		/** 更新面试途径 **/
		interviewBean.setInterviewId(interviewId);
		interviewBean.setInterviewWay(interviewWay);
		interviewBean.setInterviewType(interviewType);
		interviewBean.setInterviewTime(new Date());
		try {
			interviewBeanMapper.updateByPrimaryKeySelective(interviewBean);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		/** 获取面试信息 **/
		try {
			isStartUpInterviewDto = interviewBeanMapper.selectIsStartUpInterview(interviewId);
			transformation(isStartUpInterviewDto);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		/** 更改面试状态 **/
		intervieweeBean.setIntervieweeId(isStartUpInterviewDto.getIntervieweeId());
		intervieweeBean.setIntervieweeStatus(2);
		try {
			intervieweeBeanMapper.updateByPrimaryKeySelective(intervieweeBean);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

		return isStartUpInterviewDto;

	}

	/** 获取试题 **/
	public Map<String, Object> getQuestion(int questionFaceWay, int questionType, int questionId, int interviewId) {

		QuestionBeanWithBLOBs question = new QuestionBeanWithBLOBs();
		Map<String, Integer> mm = new HashMap<>();
		Map<String, Object> questionMap = new HashMap<String, Object>();
		mm.put("questionType", questionType);
		questionMap.put("interviewId", interviewId);
		List<QuestionBeanWithBLOBs> list = new ArrayList<>();
		try {
			list = questionBeanMapper.selectQuestionType(mm);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		for (QuestionBeanWithBLOBs q : list) {
			String faceWay = q.getQuestionFaceWay().toString();
			if (faceWay.length() >= questionFaceWay) {
				questionMap.put("questionId", q.getQuestionId());
				InterviewDetailBean interviewDetailBean = interviewDetailBeanMapper.selectUnique(questionMap);
				if (null == interviewDetailBean) {
					if (q.getQuestionId() != null && q.getQuestionId() > questionId && faceWay.charAt(faceWay.length() - questionFaceWay) == '1') {
						question = q;
						break;
					} else {
						continue;
					}
				}

			}
		}

		Map<String, Object> map = new HashMap<>();
		if (question.getQuestionId() != null) {
			map.put("question", question);
		}
		return map;
	}

	/** 下一题 **/
	public Map<String, Object> nextQuestion(QuestionDto questionDto) {

		/** 存入答题情况 **/
		DictionaryBean dictionaryBean;
		QuestionBeanWithBLOBs questionBean;
		try {
			questionBean = questionBeanMapper.selectByPrimaryKey(questionDto.getQuestionId());
			dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(questionBean.getQuestionType());
			Map<String, Object> map = new HashMap<>();
			if (dictionaryBean.getDictionaryType().equals("1") && questionDto.getIdRemark().trim().equals("")) {
				map.put("code", 2);
				map.put("codeDes", "请填写备注情况!");
				return map;
			}
			if (dictionaryBean.getDictionaryType().equals("2") && questionDto.getIdUnderstanding() == 0) {
				map.put("code", 2);
				map.put("codeDes", "请填写掌握情况!");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		InterviewDetailBean interviewDetailBean = new InterviewDetailBean();
		interviewDetailBean.setIdRemark(questionDto.getIdRemark());
		interviewDetailBean.setIdUnderstanding(questionDto.getIdUnderstanding());
		interviewDetailBean.setInterviewId(questionDto.getInterviewId());
		interviewDetailBean.setQuestionId(questionDto.getQuestionId());
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("interviewId", questionDto.getInterviewId());
			map.put("questionId", questionDto.getQuestionId());
			if (interviewDetailBeanMapper.selectUnique(map) == null) {
				interviewDetailBeanMapper.insertSelective(interviewDetailBean);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (questionDto.getOldQuestionType() != 0 && questionDto.getOldQuestionType() == questionDto.getQuestionType()) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", 3);
			map.put("codeDes", "所有题目已答完!");
			return map;
		}
		if (questionDto.getOldQuestionType() != 0) {
			questionDto.setQuestionId(0);
		}
		return getQuestion(questionDto.getQuestionFaceWay(), questionDto.getQuestionType(), questionDto.getQuestionId(), questionDto.getInterviewId());
	}

	/** 面试详情 **/
	public Map<String, Object> getSchedule(String interviewId) {

		List<ScheduleDto> result = new ArrayList<>();
		try {
			result = interviewDetailBeanMapper.schedule(interviewId);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

		}
		Map<String, Object> resultMap = null;
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", result.size());
		}
		return resultMap;

	}

	/** 面试题目数量 **/
	public Map<String, Object> getScheduleNum(String interviewId) {

		int num = 0;
		try {
			num = interviewDetailBeanMapper.scheduleNum(interviewId);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("num", num);
		return resultMap;

	}

	public void transformation(IsStartUpInterviewDto isStartUpInterviewDto) {
		if (isStartUpInterviewDto.getInterviewWay() != null) {
			switch (isStartUpInterviewDto.getInterviewWay()) {
			case 1:
				isStartUpInterviewDto.setInterviewWay1("现场面试");
				break;
			case 2:
				isStartUpInterviewDto.setInterviewWay1("电话面试");
				break;
			case 3:
				isStartUpInterviewDto.setInterviewWay1("视频面试");
				break;
			default:
				break;
			}

		}
		if (isStartUpInterviewDto.getInterviewType() != null) {
			InterviewCategoryBean interviewCategoryBean = null;
			try {
				interviewCategoryBean = interviewCategoryBeanMapper.selectById(isStartUpInterviewDto.getInterviewType());
			} catch (Exception e) {
				e.printStackTrace();
			}
			isStartUpInterviewDto.setInterviewType1(interviewCategoryBean.getInterviewCategoryType());
		}
		if (isStartUpInterviewDto.getInterviewSkillLevel() != null) {
			switch (isStartUpInterviewDto.getInterviewSkillLevel()) {
			case 1:
				isStartUpInterviewDto.setInterviewSkillLevel1("初级");
				break;
			case 2:
				isStartUpInterviewDto.setInterviewSkillLevel1("中级");
				break;
			case 3:
				isStartUpInterviewDto.setInterviewSkillLevel1("高级");
				break;
			default:
				break;
			}
		}
		if (isStartUpInterviewDto.getIntervieweeSex() != null) {
			switch (isStartUpInterviewDto.getIntervieweeSex()) {
			case 1:
				isStartUpInterviewDto.setIntervieweeSex1("男");
				break;
			case 2:
				isStartUpInterviewDto.setIntervieweeSex1("女");
				break;
			default:
				break;
			}
		}
		if (isStartUpInterviewDto.getIntervieweeStatus() != null) {
			switch (isStartUpInterviewDto.getIntervieweeStatus()) {
			case 1:
				isStartUpInterviewDto.setIntervieweeStatus1("无");
				break;
			case 2:
				isStartUpInterviewDto.setIntervieweeStatus1("正在面试");
				break;
			case 3:
				isStartUpInterviewDto.setIntervieweeStatus1("面试完成");
				break;
			case 4:
				isStartUpInterviewDto.setIntervieweeStatus1("待面试");
				break;
			default:
				break;
			}
		}

	}

	/** 生成面试报告 **/
	public Map<String, Object> createReport(int interviewId) {

		Map<String, Object> modelMap = new HashMap<>();
		// 获取面试详情记录
		List<InterviewDetailBean> interviewDetailBeanList = new ArrayList<>();
		try {
			interviewDetailBeanList = interviewDetailBeanMapper.getReport(interviewId);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("code", "2");
			modelMap.put("codeDes", "生成报告失败！");
			return modelMap;
		}
		// 遍历list
		List<QuestionBeanWithBLOBs> questionBeanWithBLOBslist = new ArrayList<>();
		QuestionBeanWithBLOBs questionBeanWithBLOBs;
		for (InterviewDetailBean interviewDetailBeanTemp : interviewDetailBeanList) {
			try {
				questionBeanWithBLOBs = questionBeanMapper.selectByPrimaryKey(interviewDetailBeanTemp.getQuestionId());
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("code", "2");
				modelMap.put("codeDes", "数据库错误！");
				return modelMap;
			}
			questionBeanWithBLOBslist.add(questionBeanWithBLOBs);
		}
		List<InterviewReportDto> interviewReportDtpsList = new ArrayList<>();
		List<Integer> listInt;
		try {
			listInt = dictionaryBeanMapper.getNoRootId();
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("code", "2");
			modelMap.put("codeDes", "数据库错误!");
			return modelMap;
		}
		for (int i = 0; i < questionBeanWithBLOBslist.size(); i++) {

			InterviewReportDto interviewReportDtoTemp = new InterviewReportDto();
			// 遍历questionType，查找相应的问题分类名，并填入interviewReport
			String idUnderstant = "";
			for (Integer integer : listInt) {
				if (questionBeanWithBLOBslist.get(i).getQuestionType().equals(integer)) {
					DictionaryBean dictionaryBean;
					try {
						dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(integer);
					} catch (Exception e) {
						e.printStackTrace();
						modelMap.put("code", "2");
						modelMap.put("codeDes", "数据库错误!");
						return modelMap;
					}
					interviewReportDtoTemp.setQuestionType(dictionaryBean.getDictionaryName());
				}
			}
			interviewReportDtoTemp.setIdRemark(interviewDetailBeanList.get(i).getIdRemark());
			switch (interviewDetailBeanList.get(i).getIdUnderstanding()) {
			case 1:
				idUnderstant = "不了解";
				break;
			case 2:
				idUnderstant = "掌握程度一般";
				break;
			case 3:
				idUnderstant = "掌握程度全面";
				break;

			default:
				idUnderstant = "";
				break;
			}
			if (idUnderstant.equals("") || null == idUnderstant) {
				interviewReportDtoTemp.setIdUnderstandingAndEva(interviewReportDtoTemp.getIdRemark());
			} else if (interviewReportDtoTemp.getIdRemark() == null || interviewReportDtoTemp.getIdRemark().equals("")) {
				interviewReportDtoTemp.setIdUnderstandingAndEva(idUnderstant);
			} else {
				interviewReportDtoTemp.setIdUnderstandingAndEva(idUnderstant + "(" + interviewReportDtoTemp.getIdRemark() + ")");
			}
			interviewReportDtoTemp.setIdUnderstanding(idUnderstant);
			interviewReportDtoTemp.setQuestionTopic(questionBeanWithBLOBslist.get(i).getQuestionTopic());
			interviewReportDtoTemp.setQuestionQuestion(questionBeanWithBLOBslist.get(i).getQuestionQuestion());
			interviewReportDtoTemp.setQuestionAnswers(questionBeanWithBLOBslist.get(i).getQuestionAnswers());

			interviewReportDtoTemp.setInterviewReportId(i);
			interviewReportDtpsList.add(interviewReportDtoTemp);
		}
		InterviewBean interviewBean;
		try {
			interviewBean = interviewBeanMapper.selectByPrimaryKey(interviewId);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("code", "2");
			modelMap.put("codeDes", "数据库错误!");
			return modelMap;
		}
		// 设定该次面试信息
		UserBean userBean;
		try {
			userBean = userBeanMapper.selectByPrimaryKey(interviewBean.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("code", "2");
			modelMap.put("codeDes", "数据库错误!");
			return modelMap;
		}
		interviewBean.setUser(userBean.getUserName());
		interviewBean.setInterviewTimeStr(DateFormat.getDateInstance().format(interviewBean.getInterviewTime()));
		modelMap.put("interviewBean", interviewBean);
		modelMap.put("interviewEvaluate", interviewBean.getInterviewEvaluate());

		// 获取面试人 信息
		IntervieweeBean intervieweeInfo = getIntervieweeInfo(interviewId);
		if (intervieweeInfo.getIntervieweeSex().equals(1)) {
			intervieweeInfo.setIntervieweeSexStr("男");
		} else {
			intervieweeInfo.setIntervieweeSexStr("女");
		}
		intervieweeInfo.setGraduationTime(DateFormat.getDateInstance().format(intervieweeInfo.getIntervieweeGraduationTime()));
		modelMap.put("intervieweeInfo", intervieweeInfo);
		modelMap.put("interviewreportDtpsList", interviewReportDtpsList);
		modelMap.put("code", "1");
		modelMap.put("interviewId", interviewId);
		return modelMap;
	}

	/* 返回问题类型分类 */
	public Map<Integer, Integer> questionClassify(List<Integer> list) {

		Map<Integer, Integer> map = new HashMap<>();
		QuestionBeanWithBLOBs questionBeanWithBLOBs;

		try {
			for (int i = 0; i < list.size(); i++) {
				questionBeanWithBLOBs = questionBeanMapper.selectByPrimaryKey(list.get(i));
				int questionType = questionBeanWithBLOBs.getQuestionType();
				map.put(list.get(i), questionType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/* 根据面试id返回面试人信息 */
	public IntervieweeBean getIntervieweeInfo(int interviewId) {

		// 根据面试id查询面试人id

		InterviewBean interviewBean = new InterviewBean();
		try {
			interviewBean = interviewBeanMapper.selectByPrimaryKey(interviewId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		int intervieweeId = interviewBean.getIntervieweeId();
		IntervieweeBean intervieweeBean = new IntervieweeBean();
		intervieweeBean = intervieweeBeanMapper.selectByPrimaryKey(intervieweeId);

		return intervieweeBean;
	}

	/* 更改面试状态 */
	public boolean modifyInterview(int interviewId, String businRemark) {
		/* 设置本次面试结束 */

		InterviewBean interviewBean = new InterviewBean();
		try {
			interviewBean = interviewBeanMapper.selectByPrimaryKey(interviewId);
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
		interviewBean.setInterviewEnd(2);
		interviewBean.setInterviewEvaluate(businRemark);
		try {
			interviewBeanMapper.updateEvaluateByPrimaryKey(interviewBean);
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
		/* 设定面试人状态变化 */

		int intervieweeId = interviewBean.getIntervieweeId();
		IntervieweeBean intervieweeBean = intervieweeBeanMapper.selectByPrimaryKey(intervieweeId);
		intervieweeBean.setIntervieweeStatus(1);
		Integer it = intervieweeBean.getIntervieweeNum() + 1;
		intervieweeBean.setIntervieweeLastTime(new Date());
		intervieweeBean.setIntervieweeNum(it);
		try {
			intervieweeBeanMapper.updateStatussByPrimaryKey(intervieweeBean);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

		}
		return true;
	}

	// get question by id
	public QuestionBeanWithBLOBs getQuestionById(int questionId) {

		QuestionBeanWithBLOBs questionBeanWithBLOBs = null;
		try {
			questionBeanWithBLOBs = questionBeanMapper.selectByPrimaryKey(questionId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return questionBeanWithBLOBs;
	}

	/** 获取面试题目类型 **/
	public Map<String, Object> interviewCategory() {
		Map<String, Object> map = new HashMap<>();
		List<DictionaryBean> dictionaryBeans = dictionaryBeanMapper.selectByLevel(1);
		List<DynamicCombox> list = new ArrayList<>();
		for (DictionaryBean dic : dictionaryBeans) {
			DynamicCombox temp = new DynamicCombox();
			temp.setLabel(String.valueOf(dic.getDictionaryId()));
			temp.setValue(dic.getDictionaryName());
			list.add(temp);
		}
		map.put("ddd", list);
		return map;
	}

	/** 添加面试类型设置 **/
	public Map<String, Object> addinterviewCategory(String interviewCategoryType, int[] interviewCategoryQuestionType) {
		Map<String, Object> map = new HashMap<>();
		if (null == interviewCategoryType.trim() || interviewCategoryType.trim().equals("")) {
			map.put("code", "2");
			map.put("codeDes", "面试类型不能为空!");
			return map;
		}
		if (null == interviewCategoryQuestionType || interviewCategoryQuestionType.length <= 0) {
			map.put("code", "2");
			map.put("codeDes", "面试题目类型不能为空!");
			return map;
		}
		String s = "";
		for (int i : interviewCategoryQuestionType) {
			s = s + i + ",";
		}
		InterviewCategoryBean interviewCategoryBean = new InterviewCategoryBean();
		interviewCategoryBean.setInterviewCategoryType(interviewCategoryType.trim());
		interviewCategoryBean.setInterviewCategoryQuestionType(s);
		try {
			interviewCategoryBeanMapper.insert(interviewCategoryBean);
			map.put("code", "1");
			map.put("codeDes", "新增成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "新增失败!");
			return map;
		}
		return map;
	}

	/** 隐藏掌握情况 **/
	public Map<String, Object> hideUnderstanding(int dictionaryId, int oldQuestionType, int interviewId) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mm = new HashMap<>();
		if (oldQuestionType == 0) {
			mm.put("dictionaryId", dictionaryId);
			mm.put("questionType", dictionaryId);
		} else {
			mm.put("questionType", oldQuestionType);
			mm.put("dictionaryId", oldQuestionType);
		}
		mm.put("interviewId", interviewId);
		DictionaryBean dictionaryBean;
		try {
			dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(dictionaryId);
			map.put("dictionaryType", dictionaryBean.getDictionaryType());
			map.put("dictionaryName", dictionaryBean.getDictionaryName());
			map.put("dictionaryId", dictionaryBean.getDictionaryId());
			map.put("num", interviewCategoryBeanMapper.selectNum(mm));
			map.put("typeSum", questionBeanMapper.selectQuestionNum(mm));
			map.put("questionType", mm.get("questionType"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("dictionaryType", 0);
			return null;
		}
		return map;

	}
}
