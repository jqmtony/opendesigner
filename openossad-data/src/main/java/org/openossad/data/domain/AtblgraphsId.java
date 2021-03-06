package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * AtblgraphsId generated by hbm2java
 */
public class AtblgraphsId implements java.io.Serializable {

	private Integer aid;
	private Date garchiveDate;
	private String gid;
	private String gname;
	private String gref;
	private int glevel;
	private int gorientation;
	private String gtemplate;
	private Boolean gqgrCaptions;
	private Boolean gvisPublic;
	private String projectId;
	private String gversionNumber;
	private String gnews;
	private int gindice;
	private String gauthor;
	private Date gdesignDate;
	private Date gupdateDate;
	private String gverifNames;
	private String gapprobNames;
	private String glinkDoc;
	private String gobject;
	private String gdomain;
	private String gabrList;
	private String gdifList;
	private String gkeywords;
	private String gfield1;
	private String gfield2;
	private String gfield3;
	private Boolean gisModel;
	private Boolean gisOk;
	private Boolean ghtmlGen;
	private Boolean gdocGen;
	private Date gdifDate;
	private String icone;
	private String gtype;
	private Boolean gonlyLink;

	public AtblgraphsId() {
	}

	public AtblgraphsId(Date garchiveDate, String gname, String gref,
			int glevel, int gorientation, String gtemplate, String projectId,
			String gversionNumber, String gnews, int gindice, String gauthor,
			Date gdesignDate, Date gupdateDate, String gverifNames,
			String gapprobNames, String glinkDoc, String gobject,
			String gdomain, String gabrList, String gdifList, String gkeywords,
			String gfield1, String gfield2, String gfield3, Date gdifDate,
			String icone, String gtype) {
		this.garchiveDate = garchiveDate;
		this.gname = gname;
		this.gref = gref;
		this.glevel = glevel;
		this.gorientation = gorientation;
		this.gtemplate = gtemplate;
		this.projectId = projectId;
		this.gversionNumber = gversionNumber;
		this.gnews = gnews;
		this.gindice = gindice;
		this.gauthor = gauthor;
		this.gdesignDate = gdesignDate;
		this.gupdateDate = gupdateDate;
		this.gverifNames = gverifNames;
		this.gapprobNames = gapprobNames;
		this.glinkDoc = glinkDoc;
		this.gobject = gobject;
		this.gdomain = gdomain;
		this.gabrList = gabrList;
		this.gdifList = gdifList;
		this.gkeywords = gkeywords;
		this.gfield1 = gfield1;
		this.gfield2 = gfield2;
		this.gfield3 = gfield3;
		this.gdifDate = gdifDate;
		this.icone = icone;
		this.gtype = gtype;
	}

	public AtblgraphsId(Integer aid, Date garchiveDate, String gid,
			String gname, String gref, int glevel, int gorientation,
			String gtemplate, Boolean gqgrCaptions, Boolean gvisPublic,
			String projectId, String gversionNumber, String gnews, int gindice,
			String gauthor, Date gdesignDate, Date gupdateDate,
			String gverifNames, String gapprobNames, String glinkDoc,
			String gobject, String gdomain, String gabrList, String gdifList,
			String gkeywords, String gfield1, String gfield2, String gfield3,
			Boolean gisModel, Boolean gisOk, Boolean ghtmlGen, Boolean gdocGen,
			Date gdifDate, String icone, String gtype, Boolean gonlyLink) {
		this.aid = aid;
		this.garchiveDate = garchiveDate;
		this.gid = gid;
		this.gname = gname;
		this.gref = gref;
		this.glevel = glevel;
		this.gorientation = gorientation;
		this.gtemplate = gtemplate;
		this.gqgrCaptions = gqgrCaptions;
		this.gvisPublic = gvisPublic;
		this.projectId = projectId;
		this.gversionNumber = gversionNumber;
		this.gnews = gnews;
		this.gindice = gindice;
		this.gauthor = gauthor;
		this.gdesignDate = gdesignDate;
		this.gupdateDate = gupdateDate;
		this.gverifNames = gverifNames;
		this.gapprobNames = gapprobNames;
		this.glinkDoc = glinkDoc;
		this.gobject = gobject;
		this.gdomain = gdomain;
		this.gabrList = gabrList;
		this.gdifList = gdifList;
		this.gkeywords = gkeywords;
		this.gfield1 = gfield1;
		this.gfield2 = gfield2;
		this.gfield3 = gfield3;
		this.gisModel = gisModel;
		this.gisOk = gisOk;
		this.ghtmlGen = ghtmlGen;
		this.gdocGen = gdocGen;
		this.gdifDate = gdifDate;
		this.icone = icone;
		this.gtype = gtype;
		this.gonlyLink = gonlyLink;
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Date getGarchiveDate() {
		return this.garchiveDate;
	}

	public void setGarchiveDate(Date garchiveDate) {
		this.garchiveDate = garchiveDate;
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

	public int getGlevel() {
		return this.glevel;
	}

	public void setGlevel(int glevel) {
		this.glevel = glevel;
	}

	public int getGorientation() {
		return this.gorientation;
	}

	public void setGorientation(int gorientation) {
		this.gorientation = gorientation;
	}

	public String getGtemplate() {
		return this.gtemplate;
	}

	public void setGtemplate(String gtemplate) {
		this.gtemplate = gtemplate;
	}

	public Boolean getGqgrCaptions() {
		return this.gqgrCaptions;
	}

	public void setGqgrCaptions(Boolean gqgrCaptions) {
		this.gqgrCaptions = gqgrCaptions;
	}

	public Boolean getGvisPublic() {
		return this.gvisPublic;
	}

	public void setGvisPublic(Boolean gvisPublic) {
		this.gvisPublic = gvisPublic;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGversionNumber() {
		return this.gversionNumber;
	}

	public void setGversionNumber(String gversionNumber) {
		this.gversionNumber = gversionNumber;
	}

	public String getGnews() {
		return this.gnews;
	}

	public void setGnews(String gnews) {
		this.gnews = gnews;
	}

	public int getGindice() {
		return this.gindice;
	}

	public void setGindice(int gindice) {
		this.gindice = gindice;
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

	public String getGverifNames() {
		return this.gverifNames;
	}

	public void setGverifNames(String gverifNames) {
		this.gverifNames = gverifNames;
	}

	public String getGapprobNames() {
		return this.gapprobNames;
	}

	public void setGapprobNames(String gapprobNames) {
		this.gapprobNames = gapprobNames;
	}

	public String getGlinkDoc() {
		return this.glinkDoc;
	}

	public void setGlinkDoc(String glinkDoc) {
		this.glinkDoc = glinkDoc;
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

	public String getGdifList() {
		return this.gdifList;
	}

	public void setGdifList(String gdifList) {
		this.gdifList = gdifList;
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

	public Boolean getGisModel() {
		return this.gisModel;
	}

	public void setGisModel(Boolean gisModel) {
		this.gisModel = gisModel;
	}

	public Boolean getGisOk() {
		return this.gisOk;
	}

	public void setGisOk(Boolean gisOk) {
		this.gisOk = gisOk;
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

	public String getIcone() {
		return this.icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getGtype() {
		return this.gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public Boolean getGonlyLink() {
		return this.gonlyLink;
	}

	public void setGonlyLink(Boolean gonlyLink) {
		this.gonlyLink = gonlyLink;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AtblgraphsId))
			return false;
		AtblgraphsId castOther = (AtblgraphsId) other;

		return ((this.getAid() == castOther.getAid()) || (this.getAid() != null
				&& castOther.getAid() != null && this.getAid().equals(
				castOther.getAid())))
				&& ((this.getGarchiveDate() == castOther.getGarchiveDate()) || (this
						.getGarchiveDate() != null
						&& castOther.getGarchiveDate() != null && this
						.getGarchiveDate().equals(castOther.getGarchiveDate())))
				&& ((this.getGid() == castOther.getGid()) || (this.getGid() != null
						&& castOther.getGid() != null && this.getGid().equals(
						castOther.getGid())))
				&& ((this.getGname() == castOther.getGname()) || (this
						.getGname() != null
						&& castOther.getGname() != null && this.getGname()
						.equals(castOther.getGname())))
				&& ((this.getGref() == castOther.getGref()) || (this.getGref() != null
						&& castOther.getGref() != null && this.getGref()
						.equals(castOther.getGref())))
				&& (this.getGlevel() == castOther.getGlevel())
				&& (this.getGorientation() == castOther.getGorientation())
				&& ((this.getGtemplate() == castOther.getGtemplate()) || (this
						.getGtemplate() != null
						&& castOther.getGtemplate() != null && this
						.getGtemplate().equals(castOther.getGtemplate())))
				&& ((this.getGqgrCaptions() == castOther.getGqgrCaptions()) || (this
						.getGqgrCaptions() != null
						&& castOther.getGqgrCaptions() != null && this
						.getGqgrCaptions().equals(castOther.getGqgrCaptions())))
				&& ((this.getGvisPublic() == castOther.getGvisPublic()) || (this
						.getGvisPublic() != null
						&& castOther.getGvisPublic() != null && this
						.getGvisPublic().equals(castOther.getGvisPublic())))
				&& ((this.getProjectId() == castOther.getProjectId()) || (this
						.getProjectId() != null
						&& castOther.getProjectId() != null && this
						.getProjectId().equals(castOther.getProjectId())))
				&& ((this.getGversionNumber() == castOther.getGversionNumber()) || (this
						.getGversionNumber() != null
						&& castOther.getGversionNumber() != null && this
						.getGversionNumber().equals(
								castOther.getGversionNumber())))
				&& ((this.getGnews() == castOther.getGnews()) || (this
						.getGnews() != null
						&& castOther.getGnews() != null && this.getGnews()
						.equals(castOther.getGnews())))
				&& (this.getGindice() == castOther.getGindice())
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
				&& ((this.getGverifNames() == castOther.getGverifNames()) || (this
						.getGverifNames() != null
						&& castOther.getGverifNames() != null && this
						.getGverifNames().equals(castOther.getGverifNames())))
				&& ((this.getGapprobNames() == castOther.getGapprobNames()) || (this
						.getGapprobNames() != null
						&& castOther.getGapprobNames() != null && this
						.getGapprobNames().equals(castOther.getGapprobNames())))
				&& ((this.getGlinkDoc() == castOther.getGlinkDoc()) || (this
						.getGlinkDoc() != null
						&& castOther.getGlinkDoc() != null && this
						.getGlinkDoc().equals(castOther.getGlinkDoc())))
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
				&& ((this.getGdifList() == castOther.getGdifList()) || (this
						.getGdifList() != null
						&& castOther.getGdifList() != null && this
						.getGdifList().equals(castOther.getGdifList())))
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
				&& ((this.getGisModel() == castOther.getGisModel()) || (this
						.getGisModel() != null
						&& castOther.getGisModel() != null && this
						.getGisModel().equals(castOther.getGisModel())))
				&& ((this.getGisOk() == castOther.getGisOk()) || (this
						.getGisOk() != null
						&& castOther.getGisOk() != null && this.getGisOk()
						.equals(castOther.getGisOk())))
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
				&& ((this.getIcone() == castOther.getIcone()) || (this
						.getIcone() != null
						&& castOther.getIcone() != null && this.getIcone()
						.equals(castOther.getIcone())))
				&& ((this.getGtype() == castOther.getGtype()) || (this
						.getGtype() != null
						&& castOther.getGtype() != null && this.getGtype()
						.equals(castOther.getGtype())))
				&& ((this.getGonlyLink() == castOther.getGonlyLink()) || (this
						.getGonlyLink() != null
						&& castOther.getGonlyLink() != null && this
						.getGonlyLink().equals(castOther.getGonlyLink())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAid() == null ? 0 : this.getAid().hashCode());
		result = 37
				* result
				+ (getGarchiveDate() == null ? 0 : this.getGarchiveDate()
						.hashCode());
		result = 37 * result
				+ (getGid() == null ? 0 : this.getGid().hashCode());
		result = 37 * result
				+ (getGname() == null ? 0 : this.getGname().hashCode());
		result = 37 * result
				+ (getGref() == null ? 0 : this.getGref().hashCode());
		result = 37 * result + this.getGlevel();
		result = 37 * result + this.getGorientation();
		result = 37 * result
				+ (getGtemplate() == null ? 0 : this.getGtemplate().hashCode());
		result = 37
				* result
				+ (getGqgrCaptions() == null ? 0 : this.getGqgrCaptions()
						.hashCode());
		result = 37
				* result
				+ (getGvisPublic() == null ? 0 : this.getGvisPublic()
						.hashCode());
		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		result = 37
				* result
				+ (getGversionNumber() == null ? 0 : this.getGversionNumber()
						.hashCode());
		result = 37 * result
				+ (getGnews() == null ? 0 : this.getGnews().hashCode());
		result = 37 * result + this.getGindice();
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
		result = 37
				* result
				+ (getGverifNames() == null ? 0 : this.getGverifNames()
						.hashCode());
		result = 37
				* result
				+ (getGapprobNames() == null ? 0 : this.getGapprobNames()
						.hashCode());
		result = 37 * result
				+ (getGlinkDoc() == null ? 0 : this.getGlinkDoc().hashCode());
		result = 37 * result
				+ (getGobject() == null ? 0 : this.getGobject().hashCode());
		result = 37 * result
				+ (getGdomain() == null ? 0 : this.getGdomain().hashCode());
		result = 37 * result
				+ (getGabrList() == null ? 0 : this.getGabrList().hashCode());
		result = 37 * result
				+ (getGdifList() == null ? 0 : this.getGdifList().hashCode());
		result = 37 * result
				+ (getGkeywords() == null ? 0 : this.getGkeywords().hashCode());
		result = 37 * result
				+ (getGfield1() == null ? 0 : this.getGfield1().hashCode());
		result = 37 * result
				+ (getGfield2() == null ? 0 : this.getGfield2().hashCode());
		result = 37 * result
				+ (getGfield3() == null ? 0 : this.getGfield3().hashCode());
		result = 37 * result
				+ (getGisModel() == null ? 0 : this.getGisModel().hashCode());
		result = 37 * result
				+ (getGisOk() == null ? 0 : this.getGisOk().hashCode());
		result = 37 * result
				+ (getGhtmlGen() == null ? 0 : this.getGhtmlGen().hashCode());
		result = 37 * result
				+ (getGdocGen() == null ? 0 : this.getGdocGen().hashCode());
		result = 37 * result
				+ (getGdifDate() == null ? 0 : this.getGdifDate().hashCode());
		result = 37 * result
				+ (getIcone() == null ? 0 : this.getIcone().hashCode());
		result = 37 * result
				+ (getGtype() == null ? 0 : this.getGtype().hashCode());
		result = 37 * result
				+ (getGonlyLink() == null ? 0 : this.getGonlyLink().hashCode());
		return result;
	}

}
