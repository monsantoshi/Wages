package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpPrEmpSecReportPK implements Serializable {
	private String userId;
	private String ouCode;
	private Double empCode;
	private Double year;
	private Double period;

	public VFeeWpPrEmpSecReportPK(Double code, String code2,
			Double period, String id, Double year) {
		super();
		// TODO Auto-generated constructor stub
		empCode = code;
		ouCode = code2;
		this.period = period;
		userId = id;
		this.year = year;
	}

	public VFeeWpPrEmpSecReportPK() {
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public Double getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Double empCode) {
		this.empCode = empCode;
	}

}
