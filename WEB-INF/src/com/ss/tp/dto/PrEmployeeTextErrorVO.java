package com.ss.tp.dto;

import java.io.Serializable;

public class PrEmployeeTextErrorVO implements Serializable {
	private String empCode;
	private Double countOt;

	public PrEmployeeTextErrorVO() {

	}

	public PrEmployeeTextErrorVO(String empCode, Double countOt) {
		this.empCode = empCode;
		this.countOt = countOt;
	}

	public Double getCountOt() {
		return countOt;
	}

	public void setCountOt(Double countOt) {
		this.countOt = countOt;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	
}
