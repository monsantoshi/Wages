package com.ss.tp.model;

import java.io.Serializable;

public class PnPositionPK implements Serializable {
	private String ouCode;
	private String gworkCode;
	private String positionCode;

	public PnPositionPK() {
	}

	public PnPositionPK(String ouCode, String gworkCode, String positionCode) {
		this.ouCode = ouCode;
		this.gworkCode = gworkCode;
		this.positionCode = positionCode;
	}

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
