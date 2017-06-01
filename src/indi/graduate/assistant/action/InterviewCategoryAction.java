package indi.graduate.assistant.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.service.InterviewCategoryService;

/** 问题类型管理 **/
@Controller
@RequestMapping(value = "/interview/proceduresec")
public class InterviewCategoryAction {
	@Autowired
	InterviewCategoryService interviewCategoryService;
	
		/** 显示列表 **/
	@RequestMapping(value = "/interviewCategory.htm")
	public String interviewCategory(ModelMap modelMap, InterviewSearchDto interviewSearchDto){
		modelMap.putAll(interviewCategoryService.interviewCategory(interviewSearchDto));
		return "/interview/procedure/interviewCategory";
	}

	/**修改问题类别**/
	@RequestMapping(value = "/editInterviewCategory.json")
	@ResponseBody
	public Map<String, Object> editInterviewCategory(int interviewCategoryId,String interviewCategoryType,int[] interviewCategoryQuestionType){
		return interviewCategoryService.editInterviewCategory(interviewCategoryId,interviewCategoryType,interviewCategoryQuestionType);
	}
	
	/**删除问题类别**/
	@RequestMapping(value = "/delInterviewCategory.json")
	@ResponseBody
	public Map<String, Object> delInterviewCategory(int id){
		return interviewCategoryService.delInterviewCategory(id);
	}
}
