package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpayPrBreakPayPK implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;

	public VFeeWpayPrBreakPayPK() {
	}

	public VFeeWpayPrBreakPayPK(String ouCode, Double year, Double period,
			String empCode) {
		this.ouCode = ouCode;
		this.year = year;
		this.period = period;
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

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}



}
