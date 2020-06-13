package com.ss.tp.model;

import java.io.Serializable;

public class PrEmployeePK implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;

	public PrEmployeePK() {
	}

	public PrEmployeePK(String code, String code2, Double period, Double year) {
		empCode = code;
		ouCode = code2;
		this.period = period;
		this.year = year;
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

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

}
