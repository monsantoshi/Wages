package com.ss.tp.model;

import java.io.Serializable;

public class PnSalaryPK implements Serializable {
	private String ouCode;
	private Long seq;

	public PnSalaryPK() {
	}

	public PnSalaryPK(String code, Long seq) {
		// TODO Auto-generated constructor stub
		this.ouCode = code;
		this.seq = seq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}
