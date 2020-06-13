package com.ss.tp.model;

import java.io.Serializable;

public class PnEmployeePK implements Serializable {
	private String ouCode;
	private String empCode;

	public PnEmployeePK() {
	}

	public PnEmployeePK(String ouCode, String empCode) {
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
