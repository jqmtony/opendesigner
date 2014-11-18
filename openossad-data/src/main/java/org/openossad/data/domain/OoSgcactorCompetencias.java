package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * OoSgcactorCompetencias generated by hbm2java
 */
public class OoSgcactorCompetencias implements java.io.Serializable {

	private Integer idx;
	private String aceducacion;
	private String acformacion;
	private String achabilidades;
	private String acexperiencia;
	private Date acfecha;

	public OoSgcactorCompetencias() {
	}

	public OoSgcactorCompetencias(Date acfecha) {
		this.acfecha = acfecha;
	}

	public OoSgcactorCompetencias(String aceducacion, String acformacion,
			String achabilidades, String acexperiencia, Date acfecha) {
		this.aceducacion = aceducacion;
		this.acformacion = acformacion;
		this.achabilidades = achabilidades;
		this.acexperiencia = acexperiencia;
		this.acfecha = acfecha;
	}

	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getAceducacion() {
		return this.aceducacion;
	}

	public void setAceducacion(String aceducacion) {
		this.aceducacion = aceducacion;
	}

	public String getAcformacion() {
		return this.acformacion;
	}

	public void setAcformacion(String acformacion) {
		this.acformacion = acformacion;
	}

	public String getAchabilidades() {
		return this.achabilidades;
	}

	public void setAchabilidades(String achabilidades) {
		this.achabilidades = achabilidades;
	}

	public String getAcexperiencia() {
		return this.acexperiencia;
	}

	public void setAcexperiencia(String acexperiencia) {
		this.acexperiencia = acexperiencia;
	}

	public Date getAcfecha() {
		return this.acfecha;
	}

	public void setAcfecha(Date acfecha) {
		this.acfecha = acfecha;
	}

}