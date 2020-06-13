package com.ss.tp.model;

import java.io.Serializable;

public class PnEmpDataStatusPK implements Serializable {
	private String ouCode;
	private Long year;
	private Long month;
	private String empCode;

	public PnEmpDataStatusPK() {
	}

	public PnEmpDataStatusPK(String ouCode, Long year, Long month,
			String empCode) {
		this.ouCode = ouCode;
		this.year = year;
		this.month = month;
		this.empCode = empCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

}
