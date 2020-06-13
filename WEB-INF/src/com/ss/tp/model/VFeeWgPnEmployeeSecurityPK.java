package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWgPnEmployeeSecurityPK implements Serializable {
	private String userId;
	private String ouCode;
	private Double empCode;
	//private Double codeSeq;
	private Double codeSeq;

	public VFeeWgPnEmployeeSecurityPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VFeeWgPnEmployeeSecurityPK(String userId, String ouCode, Double empCode,
			 Double codeSeq) {
		super();
		this.userId = userId;
		this.ouCode = ouCode;
		this.empCode = empCode;
		//this.codeSeq = codeSeq;
		this.codeSeq = codeSeq;
	}

	public Double getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
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
