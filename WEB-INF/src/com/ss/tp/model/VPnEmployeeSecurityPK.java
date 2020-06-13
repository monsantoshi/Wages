package com.ss.tp.model;

import java.io.Serializable;

public class VPnEmployeeSecurityPK implements Serializable {
	private String userId;
	private String ouCode;
	private Double empCode;
	private Double codeSeq;
	private Double codeSeqAct;

	public VPnEmployeeSecurityPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VPnEmployeeSecurityPK(String userId, String ouCode, Double empCode,
			Double codeSeq, Double codeSeqAct) {
		super();
		this.userId = userId;
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
	}

	public Double getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Double getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(Double codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}

	public Double getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Double empCode) {
		this.empCode = empCode;
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

}
