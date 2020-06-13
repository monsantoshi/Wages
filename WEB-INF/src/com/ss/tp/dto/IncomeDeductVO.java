package com.ss.tp.dto;

import java.io.Serializable;

public class IncomeDeductVO implements Serializable {
	private String incDecName;
	private Double countOt;

	public IncomeDeductVO() {

	}

	public IncomeDeductVO(String incDecName, Double countOt) {
		this.incDecName = incDecName;
		this.countOt = countOt;
	}

	public Double getCountOt() {
		return countOt;
	}

	public void setCountOt(Double countOt) {
		this.countOt = countOt;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}
}
