package org.openossad.data.domain;

// Generated 03-oct-2010 22:27:17 by Hibernate Tools 3.2.4.GA

/**
 * OoSgcpermisosChunk generated by hbm2java
 */
public class OoSgcpermisosChunk implements java.io.Serializable {

	private String idx;
	private String chunk;

	public OoSgcpermisosChunk() {
	}

	public OoSgcpermisosChunk(String idx, String chunk) {
		this.idx = idx;
		this.chunk = chunk;
	}

	public String getIdx() {
		return this.idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getChunk() {
		return this.chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

}
