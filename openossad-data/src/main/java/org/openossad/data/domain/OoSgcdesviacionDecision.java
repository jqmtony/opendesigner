package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

/**
 * OoSgcdesviacionDecision generated by hbm2java
 */
public class OoSgcdesviacionDecision implements java.io.Serializable {

	private Integer idx;
	private String ddname;
	private String dddescription;

	public OoSgcdesviacionDecision() {
	}

	public OoSgcdesviacionDecision(String ddname, String dddescription) {
		this.ddname = ddname;
		this.dddescription = dddescription;
	}

	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getDdname() {
		return this.ddname;
	}

	public void setDdname(String ddname) {
		this.ddname = ddname;
	}

	public String getDddescription() {
		return this.dddescription;
	}

	public void setDddescription(String dddescription) {
		this.dddescription = dddescription;
	}

}