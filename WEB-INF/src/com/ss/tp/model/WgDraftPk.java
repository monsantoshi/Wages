package com.ss.tp.model;

import java.io.Serializable;

public class WgDraftPk implements Serializable {
	private String ouCode;
	private Integer year;
	private String empCode;

	public WgDraftPk() {
	}

	public WgDraftPk(String ouCode, Integer year, String empCode) {
		this.ouCode = ouCode;
		this.year = year;
		this.empCode = empCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
