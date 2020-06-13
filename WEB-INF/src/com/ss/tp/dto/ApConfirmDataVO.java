package com.ss.tp.dto;

import java.io.Serializable;


public class ApConfirmDataVO implements Serializable {
	private String ouCode;
	private Long yearPn;
	private Long monthPn;
	private String volumeSetFrom;
	private String volumeSetTo;
	private String userId;
	private String flag;

	public ApConfirmDataVO() {

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

	

	public Long getYearPn() {
		return yearPn;
	}

	public void setYearPn(Long yearPn) {
		this.yearPn = yearPn;
	}

	public Long getMonthPn() {
		return monthPn;
	}

	public void setMonthPn(Long monthPn) {
		this.monthPn = monthPn;
	}



	public String getVolumeSetFrom() {
		return volumeSetFrom;
	}

	public void setVolumeSetFrom(String volumeSetFrom) {
		this.volumeSetFrom = volumeSetFrom;
	}

	public String getVolumeSetTo() {
		return volumeSetTo;
	}

	public void setVolumeSetTo(String volumeSetTo) {
		this.volumeSetTo = volumeSetTo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
