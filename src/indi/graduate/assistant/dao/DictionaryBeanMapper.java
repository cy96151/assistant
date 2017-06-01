package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.model.DictionaryBean;

public interface DictionaryBeanMapper {

	/**插入**/
    int insertSelective(DictionaryBean record);

    /**查找**/
    DictionaryBean selectByPrimaryKey(Integer dictionaryId);
    /**自动载入grid数据方法**/
 	List<DictionaryBean> rootTypeShow(Map<String, Object> mm) ;
 	List<Integer> 	getRootId();
 	List<Integer> 	getNoRootId();
 	int countAccounts(Map<String, Object> mm);
	/** 删除分类 **/
 	void deleteByPrimaryKey(int dictionaryId);
 	/** 修改用户分类 **/
 	int updateByPrimaryKey(DictionaryBean dictionaryBean);
 	
 	/**查找问题子类**/
 	List<DictionaryBean> selectByLevel(Integer level);
 	

}