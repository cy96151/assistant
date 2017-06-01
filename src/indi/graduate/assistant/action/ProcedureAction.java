package indi.graduate.assistant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.EvaluateDto;
import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.dto.IsStartUpInterviewDto;
import indi.graduate.assistant.dto.QuestionDto;
import indi.graduate.assistant.service.InterviewCategoryService;
import indi.graduate.assistant.service.ProcedureService;

/** 面试过程 **/
@Controller
@RequestMapping(value = "/interview/procedure")
public class ProcedureAction {
	@Autowired
	ProcedureService procedureService;
	@Autowired
	InterviewCategoryService interviewCategoryService;

	/** 面试人列表 **/
	@RequestMapping(value = "/startIntervieweeList.htm")
	public void startIntervieweeList(ModelMap modelMap, InterviewSearchDto interviewSearchDto) {
		if (interviewSearchDto.getPageSize() == 0 || interviewSearchDto.getPageNo() == 0) {
			interviewSearchDto.setPageNo(1);
			interviewSearchDto.setPageSize(10);
		}
		modelMap.put("searchName", interviewSearchDto.getSearchName());
		modelMap.put("intervieweeStatus", interviewSearchDto.getIntervieweeStatus());
		modelMap.putAll(procedureService.startIntervieweeList(interviewSearchDto));
	}

	@RequestMapping(value = "/startIntervieweeList-start.htm")
	public void startInterview(ModelMap modelMap, String interviewId) {
		modelMap.put("interviewId", interviewId);
		modelMap.putAll(interviewCategoryService.getInterviewCategory());
	}

	/** 启动面试过程 **/
	@RequestMapping(value = "/procedure.htm")
	public void procedure(int interviewId, int interviewWay, int interviewType, int questionId, int type, ModelMap model) {
		IsStartUpInterviewDto isStartUpInterviewDto = procedureService.startprocedure(interviewId, interviewWay, interviewType);
		model.putAll(interviewCategoryService.getQuestionCategory(interviewType, interviewId));
		model.put("isStartUpInterviewDto", isStartUpInterviewDto);
		model.put("questionId", questionId);
		model.put("type", type);
	}

	@RequestMapping(value = "/procedureForm.htm")
	public void procedureForm(ModelMap model, QuestionDto questionDto) {
		model.put("interviewId", questionDto.getInterviewId());
		model.put("interviewWay", questionDto.getQuestionFaceWay());
		if (!questionDto.getIsNewQuestionType()) {
			model.putAll(procedureService.getQuestion(questionDto.getQuestionFaceWay(), questionDto.getQuestionType(), questionDto.getQuestionId(), questionDto.getInterviewId()));
		} else {
			model.putAll(procedureService.nextQuestion(questionDto));
		}
		model.putAll(procedureService.hideUnderstanding(questionDto.getQuestionType(), questionDto.getOldQuestionType(), questionDto.getInterviewId()));

	}

	@RequestMapping("/interviewReport.htm")
	public void interviewReport(HttpServletRequest request, String interviewId, int interviewWay, int interviewType, int questionId, int type, ModelMap modelMap) {
		int interviewIdInt = Integer.parseInt(interviewId);
		// 通过面试id查找面试详情表
		Map<String, Object> map = procedureService.createReport(interviewIdInt);
		for (String key : map.keySet()) {
			if (key == "2" || key.equals("2")) {
				modelMap.put("codeDes", "生成报告失败！");
			}
			modelMap.put(key, map.get(key));
		}
		modelMap.put("interviewId", interviewId);
		modelMap.put("interviewWay", interviewWay);
		modelMap.put("interviewType", interviewType);
		modelMap.put("questionId", questionId);
		modelMap.put("type", type);
	}
	
	@RequestMapping("/interviewReportSecWindow.htm")
	public void clickDetail(ModelMap modelMap, String interviewId) {
		int interviewIdInt = Integer.parseInt(interviewId);
		// 通过面试id查找面试详情表
		Map<String, Object> map = procedureService.createReport(interviewIdInt);
		modelMap.putAll(map);
	}

	@RequestMapping("/interviewReportSec.htm")
	public void interviewReportSec(HttpServletRequest request, String interviewId, ModelMap modelMap) {
		int interviewIdInt = Integer.parseInt(interviewId);
		// 通过面试id查找面试详情表
		Map<String, Object> map = procedureService.createReport(interviewIdInt);
		for (String key : map.keySet()) {
			if (key == "2" || key.equals("2")) {
				modelMap.put("codeDes", "生成报告失败！");
			}
			modelMap.put(key, map.get(key));
		}
	}

	@RequestMapping("/getNextInterview.json")
	@ResponseBody
	public Map<String, String> getNextInterview(HttpServletRequest request, HttpServletResponse response, EvaluateDto evaluateDto) {
		// 更改面试状态
		int interviewIdInt = Integer.parseInt(evaluateDto.getInterviewId());
		Map<String, String> map = new HashMap<>();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		map.put("basePath", basePath);
		boolean flag = procedureService.modifyInterview(interviewIdInt, evaluateDto.getBusinRemark());
		if (!flag) {
			map.put("code", "2");
			map.put("codeDes", "状态修改失败,无法跳转！");
			return map;
		}
		map.put("code", "1");
		return map;
	}

	/** 获取面试题目类型 **/
	@RequestMapping("interviewCategory-edit.htm")
	public void interviewCategory(ModelMap map, String interviewCategoryId) {
		map.putAll(procedureService.interviewCategory());
		map.put("interviewCategoryId", interviewCategoryId);
	}

	/** 添加面试题目类型 **/
	@RequestMapping("addinterviewCategory.json")
	@ResponseBody
	public Map<String, Object> addinterviewCategory(String interviewCategoryType, int[] interviewCategoryQuestionType) {
		return procedureService.addinterviewCategory(interviewCategoryType, interviewCategoryQuestionType);
	}
}
