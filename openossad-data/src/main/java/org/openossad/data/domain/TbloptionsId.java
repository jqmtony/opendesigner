package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

/**
 * TbloptionsId generated by hbm2java
 */
public class TbloptionsId implements java.io.Serializable {

	private Integer optionId;
	private String optionName;
	private String optionData;

	public TbloptionsId() {
	}

	public TbloptionsId(String optionName, String optionData) {
		this.optionName = optionName;
		this.optionData = optionData;
	}

	public TbloptionsId(Integer optionId, String optionName, String optionData) {
		this.optionId = optionId;
		this.optionName = optionName;
		this.optionData = optionData;
	}

	public Integer getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionData() {
		return this.optionData;
	}

	public void setOptionData(String optionData) {
		this.optionData = optionData;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbloptionsId))
			return false;
		TbloptionsId castOther = (TbloptionsId) other;

		return ((this.getOptionId() == castOther.getOptionId()) || (this
				.getOptionId() != null
				&& castOther.getOptionId() != null && this.getOptionId()
				.equals(castOther.getOptionId())))
				&& ((this.getOptionName() == castOther.getOptionName()) || (this
						.getOptionName() != null
						&& castOther.getOptionName() != null && this
						.getOptionName().equals(castOther.getOptionName())))
				&& ((this.getOptionData() == castOther.getOptionData()) || (this
						.getOptionData() != null
						&& castOther.getOptionData() != null && this
						.getOptionData().equals(castOther.getOptionData())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOptionId() == null ? 0 : this.getOptionId().hashCode());
		result = 37
				* result
				+ (getOptionName() == null ? 0 : this.getOptionName()
						.hashCode());
		result = 37
				* result
				+ (getOptionData() == null ? 0 : this.getOptionData()
						.hashCode());
		return result;
	}

}
