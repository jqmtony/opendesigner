package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * TbldemandeId generated by hbm2java
 */
public class TbldemandeId implements java.io.Serializable {

	private Integer did;
	private int ddemandeur;
	private int ddestinataire;
	private int dtypeElement;
	private String didelement;
	private Date ddateDemande;
	private String dlibDemande;
	private String dlibDecision;
	private Date ddateDecision;
	private int ddecision;
	private int ddecisionLue;
	private Date ddateLecture;
	private String dname;

	public TbldemandeId() {
	}

	public TbldemandeId(int ddemandeur, int ddestinataire, int dtypeElement,
			String didelement, Date ddateDemande, String dlibDemande,
			String dlibDecision, Date ddateDecision, int ddecision,
			int ddecisionLue, Date ddateLecture, String dname) {
		this.ddemandeur = ddemandeur;
		this.ddestinataire = ddestinataire;
		this.dtypeElement = dtypeElement;
		this.didelement = didelement;
		this.ddateDemande = ddateDemande;
		this.dlibDemande = dlibDemande;
		this.dlibDecision = dlibDecision;
		this.ddateDecision = ddateDecision;
		this.ddecision = ddecision;
		this.ddecisionLue = ddecisionLue;
		this.ddateLecture = ddateLecture;
		this.dname = dname;
	}

	public TbldemandeId(Integer did, int ddemandeur, int ddestinataire,
			int dtypeElement, String didelement, Date ddateDemande,
			String dlibDemande, String dlibDecision, Date ddateDecision,
			int ddecision, int ddecisionLue, Date ddateLecture, String dname) {
		this.did = did;
		this.ddemandeur = ddemandeur;
		this.ddestinataire = ddestinataire;
		this.dtypeElement = dtypeElement;
		this.didelement = didelement;
		this.ddateDemande = ddateDemande;
		this.dlibDemande = dlibDemande;
		this.dlibDecision = dlibDecision;
		this.ddateDecision = ddateDecision;
		this.ddecision = ddecision;
		this.ddecisionLue = ddecisionLue;
		this.ddateLecture = ddateLecture;
		this.dname = dname;
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public int getDdemandeur() {
		return this.ddemandeur;
	}

	public void setDdemandeur(int ddemandeur) {
		this.ddemandeur = ddemandeur;
	}

	public int getDdestinataire() {
		return this.ddestinataire;
	}

	public void setDdestinataire(int ddestinataire) {
		this.ddestinataire = ddestinataire;
	}

	public int getDtypeElement() {
		return this.dtypeElement;
	}

	public void setDtypeElement(int dtypeElement) {
		this.dtypeElement = dtypeElement;
	}

	public String getDidelement() {
		return this.didelement;
	}

	public void setDidelement(String didelement) {
		this.didelement = didelement;
	}

	public Date getDdateDemande() {
		return this.ddateDemande;
	}

	public void setDdateDemande(Date ddateDemande) {
		this.ddateDemande = ddateDemande;
	}

	public String getDlibDemande() {
		return this.dlibDemande;
	}

	public void setDlibDemande(String dlibDemande) {
		this.dlibDemande = dlibDemande;
	}

	public String getDlibDecision() {
		return this.dlibDecision;
	}

	public void setDlibDecision(String dlibDecision) {
		this.dlibDecision = dlibDecision;
	}

	public Date getDdateDecision() {
		return this.ddateDecision;
	}

	public void setDdateDecision(Date ddateDecision) {
		this.ddateDecision = ddateDecision;
	}

	public int getDdecision() {
		return this.ddecision;
	}

	public void setDdecision(int ddecision) {
		this.ddecision = ddecision;
	}

	public int getDdecisionLue() {
		return this.ddecisionLue;
	}

	public void setDdecisionLue(int ddecisionLue) {
		this.ddecisionLue = ddecisionLue;
	}

	public Date getDdateLecture() {
		return this.ddateLecture;
	}

	public void setDdateLecture(Date ddateLecture) {
		this.ddateLecture = ddateLecture;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbldemandeId))
			return false;
		TbldemandeId castOther = (TbldemandeId) other;

		return ((this.getDid() == castOther.getDid()) || (this.getDid() != null
				&& castOther.getDid() != null && this.getDid().equals(
				castOther.getDid())))
				&& (this.getDdemandeur() == castOther.getDdemandeur())
				&& (this.getDdestinataire() == castOther.getDdestinataire())
				&& (this.getDtypeElement() == castOther.getDtypeElement())
				&& ((this.getDidelement() == castOther.getDidelement()) || (this
						.getDidelement() != null
						&& castOther.getDidelement() != null && this
						.getDidelement().equals(castOther.getDidelement())))
				&& ((this.getDdateDemande() == castOther.getDdateDemande()) || (this
						.getDdateDemande() != null
						&& castOther.getDdateDemande() != null && this
						.getDdateDemande().equals(castOther.getDdateDemande())))
				&& ((this.getDlibDemande() == castOther.getDlibDemande()) || (this
						.getDlibDemande() != null
						&& castOther.getDlibDemande() != null && this
						.getDlibDemande().equals(castOther.getDlibDemande())))
				&& ((this.getDlibDecision() == castOther.getDlibDecision()) || (this
						.getDlibDecision() != null
						&& castOther.getDlibDecision() != null && this
						.getDlibDecision().equals(castOther.getDlibDecision())))
				&& ((this.getDdateDecision() == castOther.getDdateDecision()) || (this
						.getDdateDecision() != null
						&& castOther.getDdateDecision() != null && this
						.getDdateDecision()
						.equals(castOther.getDdateDecision())))
				&& (this.getDdecision() == castOther.getDdecision())
				&& (this.getDdecisionLue() == castOther.getDdecisionLue())
				&& ((this.getDdateLecture() == castOther.getDdateLecture()) || (this
						.getDdateLecture() != null
						&& castOther.getDdateLecture() != null && this
						.getDdateLecture().equals(castOther.getDdateLecture())))
				&& ((this.getDname() == castOther.getDname()) || (this
						.getDname() != null
						&& castOther.getDname() != null && this.getDname()
						.equals(castOther.getDname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDid() == null ? 0 : this.getDid().hashCode());
		result = 37 * result + this.getDdemandeur();
		result = 37 * result + this.getDdestinataire();
		result = 37 * result + this.getDtypeElement();
		result = 37
				* result
				+ (getDidelement() == null ? 0 : this.getDidelement()
						.hashCode());
		result = 37
				* result
				+ (getDdateDemande() == null ? 0 : this.getDdateDemande()
						.hashCode());
		result = 37
				* result
				+ (getDlibDemande() == null ? 0 : this.getDlibDemande()
						.hashCode());
		result = 37
				* result
				+ (getDlibDecision() == null ? 0 : this.getDlibDecision()
						.hashCode());
		result = 37
				* result
				+ (getDdateDecision() == null ? 0 : this.getDdateDecision()
						.hashCode());
		result = 37 * result + this.getDdecision();
		result = 37 * result + this.getDdecisionLue();
		result = 37
				* result
				+ (getDdateLecture() == null ? 0 : this.getDdateLecture()
						.hashCode());
		result = 37 * result
				+ (getDname() == null ? 0 : this.getDname().hashCode());
		return result;
	}

}