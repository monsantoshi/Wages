package com.ss.tp.model;

import java.io.Serializable;

public class PeWgEmpScorePK implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;

	public PeWgEmpScorePK() {
	}

	public PeWgEmpScorePK(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		this.ouCode = ouCode;
		this.evaYear = evaYear;
		this.evaTime = evaTime;
		this.empCode = empCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
