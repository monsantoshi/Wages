package com.ss.tp.dto;

import java.io.Serializable;


public class ApPeriodLineVO implements Serializable {
	private String ouCode;
	private Double year;
	private Double month;
	private String volumeSet;
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
	public Double getMonth() {
		return month;
	}
	public void setMonth(Double month) {
		this.month = month;
	}
	public String getVolumeSet() {
		return volumeSet;
	}
	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
	}



}
