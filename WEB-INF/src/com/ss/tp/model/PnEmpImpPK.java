package com.ss.tp.model;

import java.io.Serializable;

public class PnEmpImpPK implements Serializable {
	private String ouCode;
	private String empCode;

	public PnEmpImpPK() {
	}

	public PnEmpImpPK(String ouCode, String empCode) {
		this.ouCode = ouCode;
		this.empCode = empCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
