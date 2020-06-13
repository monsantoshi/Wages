package com.ss.tp.model;

import java.io.Serializable;

public class FeeWpayPrPeriodLinePK implements Serializable {
	private String ouCode;
	private double year;
	private double period;
	private String creBy;

	public FeeWpayPrPeriodLinePK() {

	}

	public FeeWpayPrPeriodLinePK(String code, double period, double year,String creBy) {

		// TODO Auto-generated constructor stub
		this.ouCode = code;
		this.period = period;
		this.year = year;
		this.creBy = creBy;
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

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

}
