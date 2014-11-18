package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * TblindvalId generated by hbm2java
 */
public class TblindvalId implements java.io.Serializable {

	private Date ivdate;
	private String ivid;
	private float ivvalue;
	private String ivcomment;

	public TblindvalId() {
	}

	public TblindvalId(Date ivdate, float ivvalue, String ivcomment) {
		this.ivdate = ivdate;
		this.ivvalue = ivvalue;
		this.ivcomment = ivcomment;
	}

	public TblindvalId(Date ivdate, String ivid, float ivvalue, String ivcomment) {
		this.ivdate = ivdate;
		this.ivid = ivid;
		this.ivvalue = ivvalue;
		this.ivcomment = ivcomment;
	}

	public Date getIvdate() {
		return this.ivdate;
	}

	public void setIvdate(Date ivdate) {
		this.ivdate = ivdate;
	}

	public String getIvid() {
		return this.ivid;
	}

	public void setIvid(String ivid) {
		this.ivid = ivid;
	}

	public float getIvvalue() {
		return this.ivvalue;
	}

	public void setIvvalue(float ivvalue) {
		this.ivvalue = ivvalue;
	}

	public String getIvcomment() {
		return this.ivcomment;
	}

	public void setIvcomment(String ivcomment) {
		this.ivcomment = ivcomment;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblindvalId))
			return false;
		TblindvalId castOther = (TblindvalId) other;

		return ((this.getIvdate() == castOther.getIvdate()) || (this
				.getIvdate() != null
				&& castOther.getIvdate() != null && this.getIvdate().equals(
				castOther.getIvdate())))
				&& ((this.getIvid() == castOther.getIvid()) || (this.getIvid() != null
						&& castOther.getIvid() != null && this.getIvid()
						.equals(castOther.getIvid())))
				&& (this.getIvvalue() == castOther.getIvvalue())
				&& ((this.getIvcomment() == castOther.getIvcomment()) || (this
						.getIvcomment() != null
						&& castOther.getIvcomment() != null && this
						.getIvcomment().equals(castOther.getIvcomment())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIvdate() == null ? 0 : this.getIvdate().hashCode());
		result = 37 * result
				+ (getIvid() == null ? 0 : this.getIvid().hashCode());
		result = 37 * result + (int) this.getIvvalue();
		result = 37 * result
				+ (getIvcomment() == null ? 0 : this.getIvcomment().hashCode());
		return result;
	}

}