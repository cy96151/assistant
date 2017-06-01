package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.model.QuestionBeanWithBLOBs;

public interface QuestionBeanMapper {
	List<Integer> selectQuestionCount();
    int deleteByPrimaryKey(Integer questionId);
    int insert(QuestionBeanWithBLOBs record);
    int insertSelective(QuestionBeanWithBLOBs record);
    QuestionBeanWithBLOBs selectByPrimaryKey(Integer questionId);
    int updateByPrimaryKeyWithBLOBs(QuestionBeanWithBLOBs record);
    //获取不同类型试题
   List<QuestionBeanWithBLOBs> selectQuestionType(Map<String, Integer> mm);
    /**搜索列表获取试题**/
    List<QuestionBeanWithBLOBs> selectQuestionList(Map<String, Object> mm);
    int selectQuestionNum(Map<String, Object> mm);
}