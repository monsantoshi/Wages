package com.ss.tp.dto;

import java.io.Serializable;

public class VPrEmployeeVO implements Serializable {
	private String userId;
	private String ouCode;
	private Double codeSeq;

	public VPrEmployeeVO() {

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

	public VPrEmployeeVO(Double seq, String code, String id) {
		codeSeq = seq;
		ouCode = code;
		userId = id;
	}
}
