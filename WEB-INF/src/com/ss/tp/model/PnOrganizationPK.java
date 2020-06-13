package com.ss.tp.model;

import java.io.Serializable;

public class PnOrganizationPK implements Serializable {
	private String ouCode;
	private Long codeSeq;

	public PnOrganizationPK() {

	}

	public PnOrganizationPK(String ouCode, Long codeSeq) {
		this.ouCode = ouCode;
		this.codeSeq = codeSeq;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
