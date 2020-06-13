package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpayUserSFlagPK implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String userId;
	public VFeeWpayUserSFlagPK() {
	
	}
	public VFeeWpayUserSFlagPK(String ouCode, Double year, Double period,
			String userId) {
		
		this.ouCode = ouCode;
		this.year = year;
		this.period = period;
		this.userId = userId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
