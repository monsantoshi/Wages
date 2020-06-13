package com.ss.tp.model;

import java.io.Serializable;

public class PeEvaluationTitlePK implements Serializable {
	private String ouCode;
	private String titleCode;

	public PeEvaluationTitlePK() {
	}

	public PeEvaluationTitlePK(String ouCode, String titleCode) {
		this.ouCode = ouCode;
		this.titleCode = titleCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

}
