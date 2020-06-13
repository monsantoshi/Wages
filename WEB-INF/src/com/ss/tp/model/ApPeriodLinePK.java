package com.ss.tp.model;

import java.io.Serializable;


public class ApPeriodLinePK implements Serializable {
	private String ouCode;
	private double year;
	private double month;
	private String volumeSet;

	public ApPeriodLinePK() {

	}

	public ApPeriodLinePK(String code, double month, double year,String volumeSet) {

		// TODO Auto-generated constructor stub
		this.ouCode = code;
		this.month = month;
		this.year = year;
		this.volumeSet = volumeSet;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public double getYear() {
		return year;
	}

	public void setYear(double year) {
		this.year = year;
	}

	public double getMonth() {
		return month;
	}

	public void setMonth(double month) {
		this.month = month;
	}

	public String getVolumeSet() {
		return volumeSet;
	}

	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
	}

	

}
