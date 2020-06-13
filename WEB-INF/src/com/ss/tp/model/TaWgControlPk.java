package com.ss.tp.model;

import java.io.Serializable;

public class TaWgControlPk implements Serializable {
	private String ouCode;
	private Integer taPeriod;
	private Integer taYear;
	private String userId;

	// private Integer codeSeq;

	public Integer getTaPeriod() {
		return taPeriod;
	}

	public void setTaPeriod(Integer taPeriod) {
		this.taPeriod = taPeriod;
	}

	// public Integer getCodeSeq() {
	// return codeSeq;
	// }
	// public void setCodeSeq(Integer codeSeq) {
	// this.codeSeq = codeSeq;
	// }
	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Integer getTaYear() {
		return taYear;
	}

	public void setTaYear(Integer taYear) {
		this.taYear = taYear;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
