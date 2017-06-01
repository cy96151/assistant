package indi.graduate.assistant.service;

import java.util.Map;

import indi.graduate.assistant.dto.AddQuestionDto;
import indi.graduate.assistant.dto.QuestionSerchDto;
import indi.graduate.assistant.model.DictionaryBean;

public interface QuestionService {
	/** 获取问题列表 **/
	public Map<String, Object> questionManagementList(QuestionSerchDto questionSerchDto) ;
	/**添加问题**/
	public Map<String, Object> addQuestionManagement(AddQuestionDto addQuestionDto);
	/**修改问题**/
	public Map<String, Object> editQuestionManagement(AddQuestionDto addQuestionDto);
	/**删除问题**/
	public Map<String, Object> delquestionManagement(String id);
	/**判断输入是否为空**/
	public Map<String, Object> isEmpty(AddQuestionDto addQuestionDto);
	
	/** 获取问题分类列表 **/
	public Map<String, Object> questionTypeManagementList(int pageNo, int pageSize,int level) ;
	/** 新增问题分类 **/
	public Map<String, Object> addQuestionTypeManagement(DictionaryBean dictionaryBean,int level);
	/** 单个问题分类读取 **/
	public Map<String, Object> 	editTypeQuery(int dictionaryId);
	/** 问题分类修改 **/
	public Map<String, Object> 	editQuestionType(DictionaryBean dictionaryBean,int level);
	/**删除问题分类**/
	public Map<String, Object> delquestionTypeManagement(String dictionaryId);
}
