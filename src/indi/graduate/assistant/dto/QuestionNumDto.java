package indi.graduate.assistant.dto;

public class QuestionNumDto {

	 /**
     *序号
     */
    private Integer dictionaryId;
    /**
     * 中文名
     */
    private String dictionaryName;

    /**
     * 答题数
     */
    private int num;
    
    /**
     * 总数
     * @return
     */
    private int typeSum;

	public int getTypeSum() {
		return typeSum;
	}

	public void setTypeSum(int typeSum) {
		this.typeSum = typeSum;
	}

	public Integer getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
    
    
}
