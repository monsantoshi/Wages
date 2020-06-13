package com.ss.tp.dto;

import java.io.Serializable;

public class FeeWpayRwConfirmDataVO implements Serializable {
	private String ouCode;
	private Long year;
	private Long period;
	private String userId;
	private String flag;

	public FeeWpayRwConfirmDataVO() {

	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

}
