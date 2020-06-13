package com.ss.tp.model;

import java.io.Serializable;

public class PeEvaluationFormTitlePK implements Serializable {
	private String ouCode;
	private String formCode;
	private Long titleSeq;

	public PeEvaluationFormTitlePK() {
	}

	public PeEvaluationFormTitlePK(String ouCode, String formCode, Long titleSeq) {
		this.ouCode = ouCode;
		this.formCode = formCode;
		this.titleSeq = titleSeq;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getTitleSeq() {
		return titleSeq;
	}

	public void setTitleSeq(Long titleSeq) {
		this.titleSeq = titleSeq;
	}

}
