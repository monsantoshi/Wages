package com.ss.tp.model;

import java.io.Serializable;

public class PnEmployeeTextPK implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;

	public PnEmployeeTextPK() {
	}

	public PnEmployeeTextPK(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		this.ouCode = ouCode;
		this.evaYear = evaYear;
		this.evaTime = evaTime;
		this.empCode = empCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
	}

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

}
