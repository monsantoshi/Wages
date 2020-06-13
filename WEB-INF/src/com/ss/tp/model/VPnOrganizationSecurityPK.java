package com.ss.tp.model;

import java.io.Serializable;

public class VPnOrganizationSecurityPK implements Serializable {
	private String userId;
	private String ouCode;
	private Double codeSeq;

	public VPnOrganizationSecurityPK() {

	}

	public Double getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public VPnOrganizationSecurityPK(Double seq, String code, String id) {
		codeSeq = seq;
		ouCode = code;
		userId = id;
	}

}
