package com.ss.tp.model;

import java.io.Serializable;

public class PnEmpSetPlacePK implements Serializable {
	private String ouCode;
	private String moveStatus;
	private String empCode;
	private String year;

	public PnEmpSetPlacePK() {
	}

	public PnEmpSetPlacePK(String ouCode, String moveStatus, String empCode,
			String year) {
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.moveStatus = moveStatus;
		this.year = year;
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

	public String getMoveStatus() {
		return moveStatus;
	}

	public void setMoveStatus(String moveStatus) {
		this.moveStatus = moveStatus;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
