package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpayRwVinai3PK implements Serializable {
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	public VFeeWpayRwVinai3PK() {
	}

	public VFeeWpayRwVinai3PK(String ouCode, Double yearPr, Double periodPr,
			String empCode) {
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
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

	public Double getPeriodPr() {
		return periodPr;
	}

	public void setPeriodPr(Double periodPr) {
		this.periodPr = periodPr;
	}

	public Double getYearPr() {
		return yearPr;
	}

	public void setYearPr(Double yearPr) {
		this.yearPr = yearPr;
	}

}
