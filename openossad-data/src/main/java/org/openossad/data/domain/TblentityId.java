package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * TblentityId generated by hbm2java
 */
public class TblentityId implements java.io.Serializable {

	private String gid;
	private String gname;
	private String gref;
	private String projectId;
	private String gauthor;
	private Date gdesignDate;
	private Date gupdateDate;
	private String gobject;
	private String gdomain;
	private String gabrList;
	private String gkeywords;
	private String gfield1;
	private String gfield2;
	private String gfield3;
	private Boolean ghtmlGen;
	private Boolean gdocGen;
	private Date gdifDate;
	private String gtype;
	private String gactors;
	private String icone;

	public TblentityId() {
	}

	public TblentityId(String gname, String gref, String projectId,
			String gauthor, Date gdesignDate, Date gupdateDate, String gobject,
			String gdomain, String gabrList, String gkeywords, String gfield1,
			String gfield2, String gfield3, Date gdifDate, String gtype,
			String gactors, String icone) {
		this.gname = gname;
		this.gref = gref;
		this.projectId = projectId;
		this.gauthor = gauthor;
		this.gdesignDate = gdesignDate;
		this.gupdateDate = gupdateDate;
		this.gobject = gobject;
		this.gdomain = gdomain;
		this.gabrList = gabrList;
		this.gkeywords = gkeywords;
		this.gfield1 = gfield1;
		this.gfield2 = gfield2;
		this.gfield3 = gfield3;
		this.gdifDate = gdifDate;
		this.gtype = gtype;
		this.gactors = gactors;
		this.icone = icone;
	}

	public TblentityId(String gid, String gname, String gref, String projectId,
			String gauthor, Date gdesignDate, Date gupdateDate, String gobject,
			String gdomain, String gabrList, String gkeywords, String gfield1,
			String gfield2, String gfield3, Boolean ghtmlGen, Boolean gdocGen,
			Date gdifDate, String gtype, String gactors, String icone) {
		this.gid = gid;
		this.gname = gname;
		this.gref = gref;
		this.projectId = projectId;
		this.gauthor = gauthor;
		this.gdesignDate = gdesignDate;
		this.gupdateDate = gupdateDate;
		this.gobject = gobject;
		this.gdomain = gdomain;
		this.gabrList = gabrList;
		this.gkeywords = gkeywords;
		this.gfield1 = gfield1;
		this.gfield2 = gfield2;
		this.gfield3 = gfield3;
		this.ghtmlGen = ghtmlGen;
		this.gdocGen = gdocGen;
		this.gdifDate = gdifDate;
		this.gtype = gtype;
		this.gactors = gactors;
		this.icone = icone;
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGref() {
		return this.gref;
	}

	public void setGref(String gref) {
		this.gref = gref;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGauthor() {
		return this.gauthor;
	}

	public void setGauthor(String gauthor) {
		this.gauthor = gauthor;
	}

	public Date getGdesignDate() {
		return this.gdesignDate;
	}

	public void setGdesignDate(Date gdesignDate) {
		this.gdesignDate = gdesignDate;
	}

	public Date getGupdateDate() {
		return this.gupdateDate;
	}

	public void setGupdateDate(Date gupdateDate) {
		this.gupdateDate = gupdateDate;
	}

	public String getGobject() {
		return this.gobject;
	}

	public void setGobject(String gobject) {
		this.gobject = gobject;
	}

	public String getGdomain() {
		return this.gdomain;
	}

	public void setGdomain(String gdomain) {
		this.gdomain = gdomain;
	}

	public String getGabrList() {
		return this.gabrList;
	}

	public void setGabrList(String gabrList) {
		this.gabrList = gabrList;
	}

	public String getGkeywords() {
		return this.gkeywords;
	}

	public void setGkeywords(String gkeywords) {
		this.gkeywords = gkeywords;
	}

	public String getGfield1() {
		return this.gfield1;
	}

	public void setGfield1(String gfield1) {
		this.gfield1 = gfield1;
	}

	public String getGfield2() {
		return this.gfield2;
	}

	public void setGfield2(String gfield2) {
		this.gfield2 = gfield2;
	}

	public String getGfield3() {
		return this.gfield3;
	}

	public void setGfield3(String gfield3) {
		this.gfield3 = gfield3;
	}

	public Boolean getGhtmlGen() {
		return this.ghtmlGen;
	}

	public void setGhtmlGen(Boolean ghtmlGen) {
		this.ghtmlGen = ghtmlGen;
	}

	public Boolean getGdocGen() {
		return this.gdocGen;
	}

	public void setGdocGen(Boolean gdocGen) {
		this.gdocGen = gdocGen;
	}

	public Date getGdifDate() {
		return this.gdifDate;
	}

	public void setGdifDate(Date gdifDate) {
		this.gdifDate = gdifDate;
	}

	public String getGtype() {
		return this.gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public String getGactors() {
		return this.gactors;
	}

	public void setGactors(String gactors) {
		this.gactors = gactors;
	}

	public String getIcone() {
		return this.icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblentityId))
			return false;
		TblentityId castOther = (TblentityId) other;

		return ((this.getGid() == castOther.getGid()) || (this.getGid() != null
				&& castOther.getGid() != null && this.getGid().equals(
				castOther.getGid())))
				&& ((this.getGname() == castOther.getGname()) || (this
						.getGname() != null
						&& castOther.getGname() != null && this.getGname()
						.equals(castOther.getGname())))
				&& ((this.getGref() == castOther.getGref()) || (this.getGref() != null
						&& castOther.getGref() != null && this.getGref()
						.equals(castOther.getGref())))
				&& ((this.getProjectId() == castOther.getProjectId()) || (this
						.getProjectId() != null
						&& castOther.getProjectId() != null && this
						.getProjectId().equals(castOther.getProjectId())))
				&& ((this.getGauthor() == castOther.getGauthor()) || (this
						.getGauthor() != null
						&& castOther.getGauthor() != null && this.getGauthor()
						.equals(castOther.getGauthor())))
				&& ((this.getGdesignDate() == castOther.getGdesignDate()) || (this
						.getGdesignDate() != null
						&& castOther.getGdesignDate() != null && this
						.getGdesignDate().equals(castOther.getGdesignDate())))
				&& ((this.getGupdateDate() == castOther.getGupdateDate()) || (this
						.getGupdateDate() != null
						&& castOther.getGupdateDate() != null && this
						.getGupdateDate().equals(castOther.getGupdateDate())))
				&& ((this.getGobject() == castOther.getGobject()) || (this
						.getGobject() != null
						&& castOther.getGobject() != null && this.getGobject()
						.equals(castOther.getGobject())))
				&& ((this.getGdomain() == castOther.getGdomain()) || (this
						.getGdomain() != null
						&& castOther.getGdomain() != null && this.getGdomain()
						.equals(castOther.getGdomain())))
				&& ((this.getGabrList() == castOther.getGabrList()) || (this
						.getGabrList() != null
						&& castOther.getGabrList() != null && this
						.getGabrList().equals(castOther.getGabrList())))
				&& ((this.getGkeywords() == castOther.getGkeywords()) || (this
						.getGkeywords() != null
						&& castOther.getGkeywords() != null && this
						.getGkeywords().equals(castOther.getGkeywords())))
				&& ((this.getGfield1() == castOther.getGfield1()) || (this
						.getGfield1() != null
						&& castOther.getGfield1() != null && this.getGfield1()
						.equals(castOther.getGfield1())))
				&& ((this.getGfield2() == castOther.getGfield2()) || (this
						.getGfield2() != null
						&& castOther.getGfield2() != null && this.getGfield2()
						.equals(castOther.getGfield2())))
				&& ((this.getGfield3() == castOther.getGfield3()) || (this
						.getGfield3() != null
						&& castOther.getGfield3() != null && this.getGfield3()
						.equals(castOther.getGfield3())))
				&& ((this.getGhtmlGen() == castOther.getGhtmlGen()) || (this
						.getGhtmlGen() != null
						&& castOther.getGhtmlGen() != null && this
						.getGhtmlGen().equals(castOther.getGhtmlGen())))
				&& ((this.getGdocGen() == castOther.getGdocGen()) || (this
						.getGdocGen() != null
						&& castOther.getGdocGen() != null && this.getGdocGen()
						.equals(castOther.getGdocGen())))
				&& ((this.getGdifDate() == castOther.getGdifDate()) || (this
						.getGdifDate() != null
						&& castOther.getGdifDate() != null && this
						.getGdifDate().equals(castOther.getGdifDate())))
				&& ((this.getGtype() == castOther.getGtype()) || (this
						.getGtype() != null
						&& castOther.getGtype() != null && this.getGtype()
						.equals(castOther.getGtype())))
				&& ((this.getGactors() == castOther.getGactors()) || (this
						.getGactors() != null
						&& castOther.getGactors() != null && this.getGactors()
						.equals(castOther.getGactors())))
				&& ((this.getIcone() == castOther.getIcone()) || (this
						.getIcone() != null
						&& castOther.getIcone() != null && this.getIcone()
						.equals(castOther.getIcone())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGid() == null ? 0 : this.getGid().hashCode());
		result = 37 * result
				+ (getGname() == null ? 0 : this.getGname().hashCode());
		result = 37 * result
				+ (getGref() == null ? 0 : this.getGref().hashCode());
		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		result = 37 * result
				+ (getGauthor() == null ? 0 : this.getGauthor().hashCode());
		result = 37
				* result
				+ (getGdesignDate() == null ? 0 : this.getGdesignDate()
						.hashCode());
		result = 37
				* result
				+ (getGupdateDate() == null ? 0 : this.getGupdateDate()
						.hashCode());
		result = 37 * result
				+ (getGobject() == null ? 0 : this.getGobject().hashCode());
		result = 37 * result
				+ (getGdomain() == null ? 0 : this.getGdomain().hashCode());
		result = 37 * result
				+ (getGabrList() == null ? 0 : this.getGabrList().hashCode());
		result = 37 * result
				+ (getGkeywords() == null ? 0 : this.getGkeywords().hashCode());
		result = 37 * result
				+ (getGfield1() == null ? 0 : this.getGfield1().hashCode());
		result = 37 * result
				+ (getGfield2() == null ? 0 : this.getGfield2().hashCode());
		result = 37 * result
				+ (getGfield3() == null ? 0 : this.getGfield3().hashCode());
		result = 37 * result
				+ (getGhtmlGen() == null ? 0 : this.getGhtmlGen().hashCode());
		result = 37 * result
				+ (getGdocGen() == null ? 0 : this.getGdocGen().hashCode());
		result = 37 * result
				+ (getGdifDate() == null ? 0 : this.getGdifDate().hashCode());
		result = 37 * result
				+ (getGtype() == null ? 0 : this.getGtype().hashCode());
		result = 37 * result
				+ (getGactors() == null ? 0 : this.getGactors().hashCode());
		result = 37 * result
				+ (getIcone() == null ? 0 : this.getIcone().hashCode());
		return result;
	}

}