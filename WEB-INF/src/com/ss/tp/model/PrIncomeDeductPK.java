package com.ss.tp.model;

import java.io.Serializable;

public class PrIncomeDeductPK implements Serializable {
	private String ouCode;
	private String groupCode;
	private String incDecCode;

	public PrIncomeDeductPK() {

	}

	public PrIncomeDeductPK(String code, String code2, String code3) {

		// TODO Auto-generated constructor stub
		this.groupCode = code;
		this.incDecCode = code2;
		this.ouCode = code3;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getIncDecCode() {
		return incDecCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
