package indi.graduate.assistant.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.AddIntervieweeListDto;
import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.service.IntervieweeService;

/** 面试人 **/
@Controller
@RequestMapping(value = "/interview/interviewee")
public class IntervieweeAction {
	@Autowired
	IntervieweeService intervieweeService;

	/** 面试人列表 **/
	@RequestMapping(value = "/intervieweeList.htm")
	public void intervieweeList(ModelMap modelMap, InterviewSearchDto interviewSearchDto) {
		if (interviewSearchDto.getPageSize() == 0 || interviewSearchDto.getPageNo() == 0) {
			interviewSearchDto.setPageNo(1);
			interviewSearchDto.setPageSize(10);
		}
		modelMap.put("searchName", interviewSearchDto.getSearchName());
		modelMap.put("intervieweeStatus", interviewSearchDto.getIntervieweeStatus());
		modelMap.putAll(intervieweeService.getinterviewee(interviewSearchDto));
	}

	/** 面试人信息 **/
	@RequestMapping(value = "/interviewee-edit.htm")
	public void interviewee(ModelMap modelMap, String intervieweeId) {
		modelMap.put("intervieweeId", intervieweeId);
	}

	/** 添加/修改面试人信息 **/
	@RequestMapping(value = "/addIntervieweeList.json")
	@ResponseBody
	public Map<String, Object> addIntervieweeList(HttpServletRequest req, HttpServletResponse rep, AddIntervieweeListDto addIntervieweeList) {
		if (intervieweeService.isEmpty(addIntervieweeList) != null)
			return intervieweeService.isEmpty(addIntervieweeList);
		if (StringUtils.isBlank(addIntervieweeList.getIntervieweeId())) {
			return intervieweeService.addInterviewee(addIntervieweeList);
		} else {
			return intervieweeService.editInterviewee(addIntervieweeList);
		}
	}

	/** 删除面试人信息 **/
	@RequestMapping(value = "/delIntervieweeList.json")
	@ResponseBody
	public Map<String, Object> delIntervieweeList(String id) {
		return intervieweeService.delIntervieweeList(id);

	}

	/** 归档信息 **/
	@RequestMapping(value = "/stopStatus.json")
	@ResponseBody
	public Map<String, Object> stopStatus(String id) {
		return intervieweeService.stopStatus(id);
	}

	/** 添加面试官 **/
	@RequestMapping(value = "/addInterviewer.json")
	@ResponseBody
	public Map<String, Object> addInterviewer(AddIntervieweeListDto addIntervieweeList) {

		return intervieweeService.addInterviewer(addIntervieweeList);
	}

	/** 获取面试详情 **/
	@RequestMapping(value = "/intervieeDetailShow.htm")
	public void intervieweeDetailShow(ModelMap modelMap, int intervieweeId) {
		modelMap.putAll(intervieweeService.intervieeDetailShow(intervieweeId));
	}
}
