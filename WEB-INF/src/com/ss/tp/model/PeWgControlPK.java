package com.ss.tp.model;

import java.io.Serializable;

public class PeWgControlPK implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String userId;

	// private Long codeSeq;

	public PeWgControlPK() {
	}

	public PeWgControlPK(String ouCode, Long evaYear, Long evaTime,
			String userId) {
		this.ouCode = ouCode;
		this.evaYear = evaYear;
		this.evaTime = evaTime;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
}
