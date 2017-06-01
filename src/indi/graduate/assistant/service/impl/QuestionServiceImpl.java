package indi.graduate.assistant.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.DictionaryBeanMapper;
import indi.graduate.assistant.dao.QuestionBeanMapper;
import indi.graduate.assistant.dto.AddQuestionDto;
import indi.graduate.assistant.dto.QuestionListDto;
import indi.graduate.assistant.dto.QuestionSerchDto;
import indi.graduate.assistant.model.DictionaryBean;
import indi.graduate.assistant.model.QuestionBeanWithBLOBs;
import indi.graduate.assistant.service.QuestionService;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionBeanMapper questionBeanMapper;
	@Autowired
	DictionaryBeanMapper dictionaryBeanMapper;

	@SuppressWarnings("serial")
	Map<Integer, String> questionDifficultyMap = new HashMap<Integer, String>() {
		{
			put(1, "容易");
			put(2, "一般");
			put(3, "难");
		}
	};
	@SuppressWarnings("serial")
	Map<Integer, String> questionFaceWayMap = new HashMap<Integer, String>() {
		{
			put(0, "无");
			put(1, "现场面试");
			put(10, "电话面试");
			put(11, "现场面试/电话面试");
			put(100, "视频面试");
			put(101, "现场面试/视频面试");
			put(110, "电话面试/视频面试");
			put(111, "现场面试/电话面试/视频面试");
		}
	};

	/** 问题列表 **/
	public Map<String, Object> questionManagementList(QuestionSerchDto questionSerchDto) {
		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (questionSerchDto.getPageNo() - 1) * 10);
		mm.put("pageSize", questionSerchDto.getPageSize());
		if (questionSerchDto.getQuestionType() != 0)
			mm.put("questionType", questionSerchDto.getQuestionType());
		List<QuestionBeanWithBLOBs> result = null;
		List<QuestionListDto> resultList = new ArrayList<>();
		int rowCount = 0;
		try {
			result = questionBeanMapper.selectQuestionList(mm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		try {
			rowCount = questionBeanMapper.selectQuestionNum(mm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		for (QuestionBeanWithBLOBs q : result) {
			QuestionListDto questionListDto = new QuestionListDto();
			questionListDto.setQuestionAnswers(q.getQuestionAnswers());
			questionListDto.setQuestionDifficulty(q.getQuestionDifficulty());
			questionListDto.setQuestionDifficultyStr(questionDifficultyMap.get(q.getQuestionDifficulty()));
			questionListDto.setQuestionFaceWay(q.getQuestionFaceWay());
			questionListDto.setQuestionFaceWayStr(questionFaceWayMap.get(q.getQuestionFaceWay()));
			questionListDto.setQuestionId(q.getQuestionId());
			questionListDto.setQuestionQuestion(q.getQuestionQuestion());
			questionListDto.setQuestionTopic(q.getQuestionTopic());
			questionListDto.setQuestionType(q.getQuestionType());
			resultList.add(questionListDto);
			try {
				questionListDto.setQuestionType1(dictionaryBeanMapper.selectByPrimaryKey(q.getQuestionType()).getDictionaryName());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		Map<String, Object> resultMap = null;
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", resultList);
			resultMap.put("total", rowCount);
		}
		return resultMap;
	}

	/** 添加问题 **/
	public Map<String, Object> addQuestionManagement(AddQuestionDto addQuestionDto) {
		Map<String, Object> map = new HashMap<>();
		QuestionBeanWithBLOBs questionBean = new QuestionBeanWithBLOBs();
		questionBean.setQuestionTopic(addQuestionDto.getQuestionTopic());
		questionBean.setQuestionAnswers(addQuestionDto.getQuestionAnswers());
		questionBean.setQuestionDifficulty(addQuestionDto.getQuestionDifficulty());
		questionBean.setQuestionFaceWay(addQuestionDto.getFaceWay1() + addQuestionDto.getFaceWay2() + addQuestionDto.getFaceWay3());
		questionBean.setQuestionType(addQuestionDto.getQuestionType());
		questionBean.setQuestionQuestion(addQuestionDto.getQuestionQuestion());
		if (addQuestionDto.getFaceWay1() + addQuestionDto.getFaceWay2() + addQuestionDto.getFaceWay3() == 0) {
			map.put("code", "2");
			map.put("codeDes", "请添加适用场景!");
			return map;
		}
		if (addQuestionDto.getQuestionType() == 0) {
			map.put("code", "2");
			map.put("codeDes", "请添加所属类别!");
			return map;
		}
		try {
			if (dictionaryBeanMapper.selectByPrimaryKey(addQuestionDto.getQuestionType()) != null) {
				questionBeanMapper.insert(questionBean);
				map.put("code", "1");
				map.put("codeDes", "添加成功!");
			} else {
				map.put("code", "2");
				map.put("codeDes", "请先添加题目类别!");
			}
		} catch (Exception e) {
			map.put("code", 2);
			map.put("codeDes", "添加失败!");
			e.printStackTrace();
			return map;
		}
		return map;
	}

	/** 修改问题 **/
	public Map<String, Object> editQuestionManagement(AddQuestionDto addQuestionDto) {
		Map<String, Object> map = new HashMap<>();
		QuestionBeanWithBLOBs questionBean = new QuestionBeanWithBLOBs();
		questionBean.setQuestionId(Integer.valueOf(addQuestionDto.getQuestionId()));
		questionBean.setQuestionTopic(addQuestionDto.getQuestionTopic());
		questionBean.setQuestionAnswers(addQuestionDto.getQuestionAnswers());
		questionBean.setQuestionDifficulty(addQuestionDto.getQuestionDifficulty());
		questionBean.setQuestionFaceWay(addQuestionDto.getFaceWay1() + addQuestionDto.getFaceWay2() + addQuestionDto.getFaceWay3());
		questionBean.setQuestionType(addQuestionDto.getQuestionType());
		questionBean.setQuestionQuestion(addQuestionDto.getQuestionQuestion());
		try {
			if (dictionaryBeanMapper.selectByPrimaryKey(addQuestionDto.getQuestionType()) != null) {
				questionBeanMapper.updateByPrimaryKeyWithBLOBs(questionBean);
				map.put("code", "1");
				map.put("codeDes", "修改成功!");
			} else {
				map.put("code", "2");
				map.put("codeDes", "请先添加题目类别!");
			}
		} catch (Exception e) {
			map.put("code", "2");
			map.put("codeDes", "修改失败!");
			e.printStackTrace();
			return map;
		}
		return map;
	}

	/** 删除问题 **/
	public Map<String, Object> delquestionManagement(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			questionBeanMapper.deleteByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "删除 失败!");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "删除成功!");
		return map;
	}

	/** 判断输入是否为空 **/
	public Map<String, Object> isEmpty(AddQuestionDto addQuestionDto) {
		if (null == addQuestionDto.getQuestionTopic().trim() || addQuestionDto.getQuestionTopic().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "题目主干不能为空！");
			return map;
		}
		if (null == addQuestionDto.getQuestionQuestion().trim() || addQuestionDto.getQuestionQuestion().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "完整题目不能为空！");
			return map;
		}
		if (null == addQuestionDto.getQuestionAnswers().trim() || addQuestionDto.getQuestionAnswers().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "参考答案不能为空！");
			return map;
		}
		return null;
	}

	/* 问题分类列表 */
	public Map<String, Object> questionTypeManagementList(int pageNo, int pageSize, int level) {
		// 自动读取方法
		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (pageNo - 1) * 10);
		mm.put("pageSize", pageSize);
		mm.put("level", level);
		List<DictionaryBean> result = null;
		Map<String, Object> resultMap = new HashMap<>();
		int rowCount = 0;
		try {
			result = dictionaryBeanMapper.rootTypeShow(mm);
			rowCount = dictionaryBeanMapper.countAccounts(mm);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "2");
			resultMap.put("codeDes", "数据库错误！");
			return resultMap;
		}
		List<Integer> temp = null;
		if (level == 1) {
			try {
				temp = dictionaryBeanMapper.getRootId();
			} catch (Exception e) {
			}
			for (int i = 0; i < result.size(); i++) {

				DictionaryBean dictionaryBean2 = dictionaryBeanMapper.selectByPrimaryKey(result.get(i).getDictionaryPnode());
				result.get(i).setDictionaryPnodeName(dictionaryBean2.getDictionaryName());
				for (int j = 0; j < temp.size(); j++) {
					if (temp.get(j).equals(result.get(i).getDictionaryPnode())) {
						result.get(i).setDictionaryPnode(j + 1);
					}
				}
			}
		}
		for (int k = 0; k < result.size(); k++) {
			if (result.get(k).getDictionaryType().equals("1")) {
				result.get(k).setDictionaryTypeStr("记录详情");
			} else {
				result.get(k).setDictionaryTypeStr("勾选");
			}
		}
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", rowCount);
		}
		resultMap.put("code", 1);
		return resultMap;
	}

	/** 新增问题分类 **/
	public Map<String, Object> addQuestionTypeManagement(DictionaryBean dictionaryBean, int level) {
		Map<String, Object> map = new HashMap<>();
		// dictionaryBean.setDictionaryLevel(0);
		if (level == 0) {
			dictionaryBean.setDictionaryPnode(0);
			dictionaryBean.setDictionaryLevel(0);
		} else {
			int i = dictionaryBean.getDictionaryPnode();
			List<Integer> temp;
			try {
				temp = dictionaryBeanMapper.getRootId();
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "数据库错误！");
				return map;
			}

			dictionaryBean.setDictionaryPnode(temp.get(i - 1));
			// 给字典type赋值
			DictionaryBean dictionaryBeanType;
			dictionaryBeanType = dictionaryBeanMapper.selectByPrimaryKey(dictionaryBean.getDictionaryPnode());
			dictionaryBean.setDictionaryType(dictionaryBeanType.getDictionaryType());
			dictionaryBean.setDictionaryLevel(1);
		}
		try {
			dictionaryBeanMapper.insertSelective(dictionaryBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		map.put("code", "1");
		return map;
	}

	/** 修改问题分类读取 **/
	public Map<String, Object> editTypeQuery(int dictionaryId) {
		Map<String, Object> map = new HashMap<>();
		DictionaryBean dictionaryBean;
		try {
			dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(dictionaryId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		List<Integer> temp = dictionaryBeanMapper.getRootId();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).equals(dictionaryBean.getDictionaryPnode())) {
				dictionaryBean.setDictionaryPnode(i + 1);
			}
		}
		map.put("code", "1");
		map.put("dictionaryBean", dictionaryBean);
		return map;
	}

	/** 问题分类修改 **/
	public Map<String, Object> editQuestionType(DictionaryBean dictionaryBean, int level) {
		Map<String, Object> map = new HashMap<>();
		// 后台数据验证
		if (null == dictionaryBean.getDictionaryId()) {
			map.put("code", "2");
			map.put("codeDes", "分类Id为空！");
			return map;
		} else if (null == dictionaryBean.getDictionaryName() || dictionaryBean.getDictionaryName().equals("")) {
			map.put("code", "2");
			map.put("codeDes", "分类名为空！");
			return map;
		} else if (level == 1) {
			if (dictionaryBean.getDictionaryPnode() == 0) {
				map.put("code", "2");
				map.put("codeDes", "主类为空！");
				return map;
			}
			// 获取root分类并绑定
			List<Integer> temp = dictionaryBeanMapper.getRootId();
			dictionaryBean.setDictionaryPnode(temp.get(dictionaryBean.getDictionaryPnode() - 1));
			DictionaryBean dictionaryBeanType;
			try {
				dictionaryBeanType = dictionaryBeanMapper.selectByPrimaryKey(dictionaryBean.getDictionaryPnode());
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "数据库错误！");
				return map;
			}
			dictionaryBean.setDictionaryType(dictionaryBeanType.getDictionaryType());
		} else {
			dictionaryBean.setDictionaryPnode(0);
		}
		try {
			dictionaryBeanMapper.updateByPrimaryKey(dictionaryBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		map.put("code", "1");
		return map;
	}

	/** 删除问题分类 **/

	public Map<String, Object> delquestionTypeManagement(String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		DictionaryBean dictionaryBean;
		try {
			dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "删除 失败!");
			return map;
		}
		// 若此类是主类删除该主类前提示删除其下的子类
		if (dictionaryBean.getDictionaryLevel() == 0) {
			List<DictionaryBean> list = new ArrayList<>();
			try {
				list = dictionaryBeanMapper.selectByLevel(1);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "删除 失败!");
				return map;
			}
			for (DictionaryBean dictionaryBean2 : list) {
				if (dictionaryBean2.getDictionaryPnode() == dictionaryBean.getDictionaryId()) {
					map.put("code", "2");
					map.put("codeDes", "该类下有子类，请先删除子类!");
					return map;
				}
			}
			// 若此类事分类，则检查其下题目
		} else if (dictionaryBean.getDictionaryLevel() == 1) {
			Map<String, Integer> mm = new HashMap<>();
			mm.put("questionType", dictionaryBean.getDictionaryId());
			List<QuestionBeanWithBLOBs> questionBeanWithBLOBs = questionBeanMapper.selectQuestionType(mm);
			if (questionBeanWithBLOBs.size() > 0) {
				map.put("code", "2");
				map.put("codeDes", "该类下还有题目，请先删除题目!");
				return map;
			}
		}
		// 删除方法
		try {
			dictionaryBeanMapper.deleteByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "删除 失败!");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "删除成功!");
		return map;
	}

}
