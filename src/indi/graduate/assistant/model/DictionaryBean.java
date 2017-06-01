package indi.graduate.assistant.model;

public class DictionaryBean {
    /**
     *序号
     */
    private Integer dictionaryId;
    private String dictionaryPnodeName;
    /**
     * 中文名
     */
    private String dictionaryName;
    private String dictionaryType;
    private String dictionaryTypeStr;
    /**
     * 父节点
     */
    private Integer dictionaryPnode;

    /**
     *根节点
     */
    private Integer dictionaryLevel;

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

	public int getDictionaryPnode() {
		return dictionaryPnode;
	}

	public void setDictionaryPnode(int dictionaryPnode) {
		this.dictionaryPnode = dictionaryPnode;
	}

	public int getDictionaryLevel() {
		return dictionaryLevel;
	}

	public void setDictionaryLevel(int dictionaryLevel) {
		this.dictionaryLevel = dictionaryLevel;
	}

	public String getDictionaryPnodeName() {
		return dictionaryPnodeName;
	}

	public void setDictionaryPnodeName(String dictionaryPnodeName) {
		this.dictionaryPnodeName = dictionaryPnodeName;
	}

	public String getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public String getDictionaryTypeStr() {
		return dictionaryTypeStr;
	}

	public void setDictionaryTypeStr(String dictionaryTypeStr) {
		this.dictionaryTypeStr = dictionaryTypeStr;
	}




  
}