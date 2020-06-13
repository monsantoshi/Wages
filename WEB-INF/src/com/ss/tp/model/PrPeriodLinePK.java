package com.ss.tp.model;

import java.io.Serializable;

public class PrPeriodLinePK implements Serializable {
	private String ouCode;
	private double year;
	private double period;

	public PrPeriodLinePK() {

	}

	public PrPeriodLinePK(String code, double period, double year) {

		// TODO Auto-generated constructor stub
		this.ouCode = code;
		this.period = period;
		this.year = year;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public double getYear() {
		return year;
	}

	public void setYear(double year) {
		this.year = year;
	}

}
