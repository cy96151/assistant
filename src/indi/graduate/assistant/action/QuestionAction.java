package indi.graduate.assistant.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.AddQuestionDto;
import indi.graduate.assistant.dto.DynamicCombox;
import indi.graduate.assistant.dto.QuestionSerchDto;
import indi.graduate.assistant.model.DictionaryBean;
import indi.graduate.assistant.service.ProcedureService;
import indi.graduate.assistant.service.QuestionService;

/** 问题操作 **/
@Controller
@RequestMapping(value = "/interview/question")
public class QuestionAction {
	@Autowired
	QuestionService questionService;
	@Autowired
	ProcedureService procedureService;

	/** 问题页面 **/
	@RequestMapping(value = "/questionManagement.htm")
	public void questionManagement(ModelMap modelMap, QuestionSerchDto questionSerchDto) {
		modelMap.put("questionType", questionSerchDto.getQuestionType());
		modelMap.putAll(procedureService.interviewCategory());
		modelMap.putAll(questionService.questionManagementList(questionSerchDto));
	}
	
	@RequestMapping(value ={"questionManagement-edit.htm"})
	public void questionManagement_edit(ModelMap map, String questionId){
		map.putAll(procedureService.interviewCategory());
		map.put("questionId", questionId);
	}
	
	@RequestMapping(value ={"questionManagement-show.htm"})
	public void questionManagement_show(ModelMap map, String questionId, int no1, int no2){
		map.put("questionId", questionId);
		map.put("no1", no1);
		map.put("no2", no2);
	}

	/** 问题列表 **/
	@RequestMapping(value = "/questionManagementList.json")
	@ResponseBody
	public Map<String, Object> questionManagementList(QuestionSerchDto questionSerchDto) {
		return questionService.questionManagementList(questionSerchDto);
	}

	/** 新增/修改问题 **/
	@RequestMapping(value = "/addQuestionManagement.json")
	@ResponseBody
	public Map<String, Object> addQuestionManagement(AddQuestionDto addQuestionDto) {
		if (questionService.isEmpty(addQuestionDto) != null) {
			return questionService.isEmpty(addQuestionDto);
		}
		if (addQuestionDto.getQuestionId().equals("") || addQuestionDto.getQuestionId() == null) {
			return questionService.addQuestionManagement(addQuestionDto);
		} else {
			return questionService.editQuestionManagement(addQuestionDto);
		}
	}

	/** 删除问题 **/
	@RequestMapping(value = "/delquestionManagement.json")
	@ResponseBody
	public Map<String, Object> delquestionManagement(String id) {
		return questionService.delquestionManagement(id);
	}

	/** 问题类型列表 **/
	@RequestMapping(value = {"/questionType.htm","/questionCategory.htm"})
	public void questionTypeManagementList(ModelMap modelMap, int pageNo, int pageSize, int level) {
		modelMap.putAll(questionService.questionTypeManagementList(pageNo, pageSize, level));
	}

	@RequestMapping(value = "/questionType-edit.htm")
	public void questionType_edit(ModelMap modelMap, String dictionaryId) {
		modelMap.put("dictionaryId", dictionaryId);
	}

	@RequestMapping(value = "/questionCategory-edit.htm")
	public void questionCategory_edit(ModelMap modelMap, String dictionaryId) {
		modelMap.putAll(questionCategory());
		modelMap.put("dictionaryId", dictionaryId);
	}

	/** 新增问题root类 **/
	@RequestMapping(value = "/addQuestionRootType.json")
	@ResponseBody
	public Map<String, Object> addQuestionRootType(DictionaryBean dictionaryBean, int level) {
		return questionService.addQuestionTypeManagement(dictionaryBean, level);
	}

	/** 修改问题分类时读取资料 **/

	@RequestMapping("/editQuestionTypeQuery.json")
	@ResponseBody
	public Map<String, Object> editQuery(HttpServletRequest request, String dictionaryId) {
		int dictionaryIdTemp = Integer.parseInt(dictionaryId);
		Map<String, Object> map = new HashMap<>();
		map = questionService.editTypeQuery(dictionaryIdTemp);
		return map;
	}

	/** 修改问题分类 **/
	@RequestMapping("/editQuestionRootType.json")
	@ResponseBody
	public Map<String, Object> editQuestionRootType(HttpServletRequest request, DictionaryBean dictionaryBean, int level) {
		Map<String, Object> map = new HashMap<>();
		map = questionService.editQuestionType(dictionaryBean, level);
		return map;
	}

	/** 删除问题分类 **/
	@RequestMapping(value = "/delQuestionType.json")
	@ResponseBody
	public Map<String, Object> delQuestionType(String dictionaryId) {
		return questionService.delquestionTypeManagement(dictionaryId);
	}

	/** 读取分类 **/
	@RequestMapping(value = "/questionCategory.json")
	@ResponseBody
	public Map<String, Object> questionCategory() {
		Map<String, Object> map = new HashMap<>();
		try {
			map = questionService.questionTypeManagementList(1, 100, 0);

		} catch (Exception e) {
			map.put("code", "2");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		@SuppressWarnings("unchecked")
		List<DictionaryBean> dictionaryBeans = (List<DictionaryBean>) map.get("rows");
		List<DynamicCombox> list = new ArrayList<>();
		for (int i = 0; i < dictionaryBeans.size(); i++) {
			DynamicCombox temp = new DynamicCombox();
			temp.setLabel(Integer.toString(i + 1));
			temp.setValue(dictionaryBeans.get(i).getDictionaryName());
			list.add(temp);
		}
		Map<String, Object> map2 = new HashMap<>();
		map2.put("ddd", list);
		return map2;
	}

}
