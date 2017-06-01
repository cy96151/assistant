package indi.graduate.assistant.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.DictionaryBeanMapper;
import indi.graduate.assistant.dao.InterviewCategoryBeanMapper;
import indi.graduate.assistant.dao.QuestionBeanMapper;
import indi.graduate.assistant.dto.DynamicCombox;
import indi.graduate.assistant.dto.InterviewCategoryDto;
import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.dto.QuestionNumDto;
import indi.graduate.assistant.model.DictionaryBean;
import indi.graduate.assistant.model.InterviewCategoryBean;
import indi.graduate.assistant.service.InterviewCategoryService;

@Service("InterviewCategoryService")
public class InterviewCategoryServiceImpl implements InterviewCategoryService {
	@Autowired
	InterviewCategoryBeanMapper interviewCategoryBeanMapper;
	@Autowired
	DictionaryBeanMapper dictionaryBeanMapper;
	@Autowired
	QuestionBeanMapper questionBeanMapper;

	/** 显示面试类型列表 **/
	public Map<String, Object> interviewCategory(InterviewSearchDto interviewSearchDto) {
		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (interviewSearchDto.getPageNo() - 1) * 10);
		mm.put("pageSize", interviewSearchDto.getPageSize());
		List<InterviewCategoryBean> result = null;
		List<InterviewCategoryDto> list = new ArrayList<>();
		int rowCount = 0;
		try {
			result = interviewCategoryBeanMapper.selectIntervieweeList(mm);
			rowCount = interviewCategoryBeanMapper.countAccounts(mm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String, Object> resultMap = null;
		if (result != null && result.size() > 0) {
			for (InterviewCategoryBean bean : result) {
				InterviewCategoryDto interviewCategoryDto = new InterviewCategoryDto();
				String[] s = bean.getInterviewCategoryQuestionType().split(",");
				DictionaryBean dictionaryBean = new DictionaryBean();
				interviewCategoryDto.setInterviewCategoryId(bean.getInterviewCategoryId());
				interviewCategoryDto.setInterviewCategoryQuestionType(bean.getInterviewCategoryQuestionType());
				interviewCategoryDto.setInterviewCategoryType(bean.getInterviewCategoryType());
				String interviewCategoryQuestionType1 = "";
				for (int i = 0; i < s.length; i++) {
					try {
						dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(Integer.valueOf(s[i]));
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
					if( dictionaryBean!=null){
						interviewCategoryQuestionType1 += dictionaryBean.getDictionaryName() + "，";
					}
				}
				interviewCategoryDto.setInterviewCategoryQuestionType1(interviewCategoryQuestionType1.substring(0,interviewCategoryQuestionType1.length()-1));
				list.add(interviewCategoryDto);
			}
			if (list.size() > 0 && list != null) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("rows", list);
				resultMap.put("total", rowCount);
			}
		}
		return resultMap;
	}

	/**修改面试类型**/
	public Map<String, Object> editInterviewCategory(int interviewCategoryId,String interviewCategoryType,int[] interviewCategoryQuestionType){
		Map<String, Object> map=new HashMap<>();
		if(null==interviewCategoryType.trim()||interviewCategoryType.trim().equals("")){
			map.put("code", "2");
			map.put("codeDes", "面试类型不能为空!");
			return map;
		}
		if(null==interviewCategoryQuestionType||interviewCategoryQuestionType.length<=0){
			map.put("code", "2");
			map.put("codeDes", "面试题目类型不能为空!");
			return map;
		}
		String s="";
		for(int i:interviewCategoryQuestionType){
			s=s+i+",";
		}
		InterviewCategoryBean interviewCategoryBean = new InterviewCategoryBean();
		interviewCategoryBean.setInterviewCategoryId(interviewCategoryId);
		interviewCategoryBean.setInterviewCategoryType(interviewCategoryType.trim());
		interviewCategoryBean.setInterviewCategoryQuestionType(s);
		try{
			interviewCategoryBeanMapper.update(interviewCategoryBean);
			map.put("code", "1");
			map.put("codeDes", "修改成功!");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "修改失败!");
			return map;
		}
		return map;
	}
	
	/**删除问题类别**/
	public Map<String,Object> delInterviewCategory(int id){
		Map<String, Object> map = new HashMap<>();
		try {
			interviewCategoryBeanMapper.deleteByPrimaryKey(Integer.valueOf(id));
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

	/**获取面试类型**/
	public Map<String, Object> getInterviewCategory(){
		Map<String, Object> map=new HashMap<>();
		List<InterviewCategoryBean> list1=interviewCategoryBeanMapper.select();
		List<DynamicCombox> list=new ArrayList<>();
		for(InterviewCategoryBean dic:list1){
			DynamicCombox temp=new DynamicCombox();
			temp.setLabel(String.valueOf(dic.getInterviewCategoryId()));
			temp.setValue(dic.getInterviewCategoryType());
			list.add(temp);
		}
		map.put("ddd", list	);
		return map;
	}
	
	/**获取面试问题类型**/
	public Map<String, Object> getQuestionCategory(int interviewType,int interviewId){
		InterviewCategoryBean interviewCategoryBean = null;
		try{
		interviewCategoryBean=interviewCategoryBeanMapper.selectById(interviewType);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		String[] dictionaryId = interviewCategoryBean.getInterviewCategoryQuestionType().split(",");
		List<QuestionNumDto> list = new ArrayList<>();
		for (int i = 0; i < dictionaryId.length; i++) {
			DictionaryBean dictionaryBean = null;
			QuestionNumDto questionNumDto = new QuestionNumDto();
			Map<String, Object> mm = new HashMap<>();
			mm.put("interviewId", interviewId);
			mm.put("dictionaryId", dictionaryId[i]);
			mm.put("questionType", dictionaryId[i]);
			try{
				dictionaryBean = dictionaryBeanMapper.selectByPrimaryKey(Integer.valueOf(dictionaryId[i]));
				questionNumDto.setDictionaryId(dictionaryBean.getDictionaryId());
				questionNumDto.setDictionaryName(dictionaryBean.getDictionaryName());
				questionNumDto.setNum(interviewCategoryBeanMapper.selectNum(mm));
				questionNumDto.setTypeSum(questionBeanMapper.selectQuestionNum(mm));
				list.add(questionNumDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Map<String, Object> resultMap = null;
		if (list != null && list.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", list);
			resultMap.put("total", list.size());
		}
		return resultMap;
	}
}
