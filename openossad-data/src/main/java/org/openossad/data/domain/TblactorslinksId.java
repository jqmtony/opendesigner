package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

/**
 * TblactorslinksId generated by hbm2java
 */
public class TblactorslinksId implements java.io.Serializable {

	private String gidAm;
	private String userName;

	public TblactorslinksId() {
	}

	public TblactorslinksId(String gidAm, String userName) {
		this.gidAm = gidAm;
		this.userName = userName;
	}

	public String getGidAm() {
		return this.gidAm;
	}

	public void setGidAm(String gidAm) {
		this.gidAm = gidAm;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblactorslinksId))
			return false;
		TblactorslinksId castOther = (TblactorslinksId) other;

		return ((this.getGidAm() == castOther.getGidAm()) || (this.getGidAm() != null
				&& castOther.getGidAm() != null && this.getGidAm().equals(
				castOther.getGidAm())))
				&& ((this.getUserName() == castOther.getUserName()) || (this
						.getUserName() != null
						&& castOther.getUserName() != null && this
						.getUserName().equals(castOther.getUserName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGidAm() == null ? 0 : this.getGidAm().hashCode());
		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		return result;
	}

}
