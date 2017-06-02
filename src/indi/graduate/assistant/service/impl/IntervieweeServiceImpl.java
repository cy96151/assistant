package indi.graduate.assistant.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.InterviewBeanMapper;
import indi.graduate.assistant.dao.IntervieweeBeanMapper;
import indi.graduate.assistant.dto.AddIntervieweeListDto;
import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.dto.IntervieweeDto;
import indi.graduate.assistant.dto.IsStartUpInterviewDto;
import indi.graduate.assistant.model.InterviewBean;
import indi.graduate.assistant.model.IntervieweeBean;
import indi.graduate.assistant.service.IntervieweeService;
import indi.graduate.assistant.util.RegexCheck;

@Service("IntervieweeService")
public class IntervieweeServiceImpl implements IntervieweeService {
	@Autowired
	IntervieweeBeanMapper intervieweeBeanMapper;
	@Autowired
	InterviewBeanMapper interviewBeanMapper;

	/** 获取面试人员列表 **/
	public Map<String, Object> getinterviewee(InterviewSearchDto interviewSearchDto) {
		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (interviewSearchDto.getPageNo() - 1) * interviewSearchDto.getPageSize());
		mm.put("pageSize", interviewSearchDto.getPageSize());
		if (interviewSearchDto.getSearchName() != null && !interviewSearchDto.getSearchName().trim().equals("")) {
			if (RegexCheck.regexCheck(interviewSearchDto.getSearchName().trim(), RegexCheck.phoneCheck))
				mm.put("intervieweePhone", interviewSearchDto.getSearchName().trim());
			else
				mm.put("intervieweeName", interviewSearchDto.getSearchName().trim());
		}
		if (interviewSearchDto.getIntervieweeStatus() == 1 || interviewSearchDto.getIntervieweeStatus() == 2 || interviewSearchDto.getIntervieweeStatus() == 3 || interviewSearchDto.getIntervieweeStatus() == 4)
			mm.put("intervieweeStatus", interviewSearchDto.getIntervieweeStatus());
		List<IntervieweeDto> result = null;
		int rowCount = 0;
		try {
			result = intervieweeBeanMapper.selectIntervieweeList(mm);
			rowCount = intervieweeBeanMapper.countAccounts(mm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			resultMap.put("rows", result);
		}
		resultMap.put("total", rowCount);
		return resultMap;
	}

	/** 新增面试人信息 **/
	public Map<String, Object> addInterviewee(AddIntervieweeListDto addIntervieweeList) {
		Map<String, Object> map = new HashMap<String, Object>();
		InterviewBean interviewBean = new InterviewBean();
		IntervieweeBean intervieweeBean = new IntervieweeBean();
		intervieweeBean.setIntervieweeName(addIntervieweeList.getIntervieweeName().trim());
		if (StringUtils.isNotBlank(addIntervieweeList.getIntervieweeSex())) {
			intervieweeBean.setIntervieweeSex(Integer.valueOf(addIntervieweeList.getIntervieweeSex()));
		}
		if (addIntervieweeList.getUserId() != null && !addIntervieweeList.getUserId().equals("")) {
			intervieweeBean.setIntervieweeStatus(4);
		} else {
			intervieweeBean.setIntervieweeStatus(1);
		}
		intervieweeBean.setIntervieweePhone(addIntervieweeList.getIntervieweePhone());
		intervieweeBean.setIntervieweeSchool(addIntervieweeList.getIntervieweeSchool().trim());
		intervieweeBean.setIntervieweeGraduationTime(Date.valueOf(addIntervieweeList.getIntervieweeGraduationTime()));
		intervieweeBean.setIntervieweePecialties(addIntervieweeList.getIntervieweePecialties().trim());
		intervieweeBean.setIntervieweePost(addIntervieweeList.getIntervieweePost().trim());
		intervieweeBean.setIntervieweeProject(addIntervieweeList.getIntervieweeProject().trim());
		try {
			intervieweeBeanMapper.insertSelective(intervieweeBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "新增失败!");
			return map;
		}
		if (addIntervieweeList.getUserId() != null && !addIntervieweeList.getUserId().equals("")) {
			interviewBean.setIntervieweeId(intervieweeBeanMapper.selectLastId());
			interviewBean.setUserId(addIntervieweeList.getUserId());
			if (StringUtils.isNotBlank(addIntervieweeList.getInterviewWay())) {
				interviewBean.setInterviewWay(Integer.valueOf(addIntervieweeList.getInterviewWay()));
			}
			interviewBean.setInterviewEnd(1);
			try {
				interviewBeanMapper.insertSelective(interviewBean);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "新增失败!");
				return map;
			}
		}
		map.put("code", "1");
		map.put("codeDes", "新增成功!");
		return map;
	}

	/** 修改面试人信息 **/
	public Map<String, Object> editInterviewee(AddIntervieweeListDto addIntervieweeList) {
		Map<String, Object> map = new HashMap<String, Object>();
		InterviewBean interviewBean = new InterviewBean();
		IntervieweeBean intervieweeBean = new IntervieweeBean();
		intervieweeBean.setIntervieweeId(Integer.valueOf(addIntervieweeList.getIntervieweeId()));
		intervieweeBean.setIntervieweeName(addIntervieweeList.getIntervieweeName().trim());
		if (StringUtils.isNotBlank(addIntervieweeList.getIntervieweeSex())) {
			intervieweeBean.setIntervieweeSex(Integer.valueOf(addIntervieweeList.getIntervieweeSex()));
		}
		intervieweeBean.setIntervieweePhone(addIntervieweeList.getIntervieweePhone());
		intervieweeBean.setIntervieweeSchool(addIntervieweeList.getIntervieweeSchool().trim());
		intervieweeBean.setIntervieweeGraduationTime(Date.valueOf(addIntervieweeList.getIntervieweeGraduationTime()));
		intervieweeBean.setIntervieweePecialties(addIntervieweeList.getIntervieweePecialties().trim());
		intervieweeBean.setIntervieweePost(addIntervieweeList.getIntervieweePost().trim());
		intervieweeBean.setIntervieweeProject(addIntervieweeList.getIntervieweeProject().trim());
		if (addIntervieweeList.getUserId() != null && !addIntervieweeList.getUserId().equals("")) {
			intervieweeBean.setIntervieweeStatus(4);
		} else {
			intervieweeBean.setIntervieweeStatus(1);
		}
		try {
			intervieweeBeanMapper.updateByPrimaryKeySelective(intervieweeBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "修改失败!");
			return map;
		}
		if (addIntervieweeList.getUserId() != null && !addIntervieweeList.getUserId().equals("")) {
			interviewBean.setIntervieweeId(Integer.valueOf(addIntervieweeList.getIntervieweeId()));
			interviewBean.setUserId(addIntervieweeList.getUserId());
			if (StringUtils.isNotBlank(addIntervieweeList.getInterviewWay())) {
				interviewBean.setInterviewWay(Integer.valueOf(addIntervieweeList.getInterviewWay()));
			} else {
				interviewBean.setInterviewWay(null);
			}
			interviewBean.setInterviewEnd(1);
			if (addIntervieweeList.getInterviewId() != null && !addIntervieweeList.getInterviewId().equals("")) {
				interviewBean.setInterviewId(Integer.valueOf(addIntervieweeList.getInterviewId()));
				try {
					interviewBeanMapper.updateByPrimaryKeySelective(interviewBean);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("code", "2");
					map.put("codeDes", "修改失败!");
					return map;
				}
			} else {
				try {
					interviewBeanMapper.insertSelective(interviewBean);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("code", "2");
					map.put("codeDes", "修改失败!");
					return map;
				}
			}
		}
		map.put("code", "1");
		map.put("codeDes", "修改成功!");
		return map;
	}

	/** 删除面试人信息 **/
	public Map<String, Object> delIntervieweeList(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			intervieweeBeanMapper.deleteByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "删除失败!");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "删除成功!");
		return map;
	}

	/** 归档 **/
	public Map<String, Object> stopStatus(String id) {
		IntervieweeBean intervieweeBean = null;
		Map<String, Object> map = new HashMap<>();
		try {
			intervieweeBean = intervieweeBeanMapper.selectByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "无法归档!");
			return map;
		}
		if (intervieweeBean.getIntervieweeStatus() == 2) {
			map.put("code", "2");
			map.put("codeDes", "正在面试人员无法归档!");
			return map;
		}
		intervieweeBean.setIntervieweeStatus(3);
		try {
			intervieweeBeanMapper.updateByPrimaryKeySelective(intervieweeBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "无法归档!");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "归档成功!");
		return map;
	}

	/** 添加面试官 **/
	public Map<String, Object> addInterviewer(AddIntervieweeListDto addIntervieweeList) {
		InterviewBean interviewBean = new InterviewBean();
		IntervieweeBean intervieweeBean = new IntervieweeBean();
		Map<String, Object> map = new HashMap<>();
		interviewBean.setIntervieweeId(Integer.valueOf(addIntervieweeList.getIntervieweeId()));
		interviewBean.setUserId(addIntervieweeList.getUserId());
		interviewBean.setInterviewEnd(1);
		intervieweeBean.setIntervieweeId(Integer.valueOf(addIntervieweeList.getIntervieweeId()));
		intervieweeBean.setIntervieweeStatus(4);
		if (addIntervieweeList.getInterviewId().equals("0") || addIntervieweeList.getInterviewId().equals("") || addIntervieweeList.getInterviewId() == null) {
			try {
				interviewBeanMapper.insertSelective(interviewBean);
				intervieweeBeanMapper.updateByPrimaryKeySelective(intervieweeBean);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "添加失败!");
				return map;
			}
		} else {
			try {
				interviewBean.setInterviewId(Integer.valueOf(addIntervieweeList.getInterviewId()));
				interviewBeanMapper.updateByPrimaryKeySelective(interviewBean);
				intervieweeBeanMapper.updateByPrimaryKeySelective(intervieweeBean);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "2");
				map.put("codeDes", "添加失败!");
				return map;
			}
		}
		map.put("code", "1");
		map.put("codeDes", "添加成功!");
		return map;
	}

	/** 判断是否输入为空 **/
	public Map<String, Object> isEmpty(AddIntervieweeListDto addIntervieweeList) {

		if (addIntervieweeList.getIntervieweeName() == null || addIntervieweeList.getIntervieweeName().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "姓名不能为空！");
			return map;
		}
		if (!RegexCheck.regexCheck(addIntervieweeList.getIntervieweeName(), RegexCheck.userNameCheck)) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "请输入中文姓名！");
			return map;
		}
		if (addIntervieweeList.getIntervieweePhone() == null || addIntervieweeList.getIntervieweePhone().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "手机号不能为空！");
			return map;
		}
		if (addIntervieweeList.getIntervieweeSchool() == null || addIntervieweeList.getIntervieweeSchool().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "毕业学校不能为空！");
			return map;
		}
		if (!RegexCheck.regexCheck(addIntervieweeList.getIntervieweeSchool(), RegexCheck.schoolCheck)) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "请输入中文的毕业院校名称！");
			return map;
		}
		if (addIntervieweeList.getIntervieweeGraduationTime() == null || addIntervieweeList.getIntervieweeGraduationTime().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "毕业时间不能为空！");
			return map;
		}
		if (!RegexCheck.regexCheck(addIntervieweeList.getIntervieweeGraduationTime(), RegexCheck.dateCheck)) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "毕业时间输入有误！");
			return map;
		}
		if (addIntervieweeList.getIntervieweePost() == null || addIntervieweeList.getIntervieweePost().trim().equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "2");
			map.put("codeDes", "岗位不能为空！");
			return map;
		}
		return null;
	}

	/** 获取面试人面试列表 **/
	public Map<String, Object> intervieeDetailShow(int intervieweeId) {
		Map<String, Object> resultMap = null;
		List<IsStartUpInterviewDto> result = null;
		try {
			result = interviewBeanMapper.intervieeDetailList(intervieweeId);
		} catch (Exception e) {
			return null;
		}
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", result.size());
		}
		return resultMap;
	}
}
