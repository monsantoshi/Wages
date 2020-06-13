package com.ss.tp.model;

import java.io.Serializable;

public class PeWgEmpScoreDetailPK implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;
	private String formCode;
	private Long titleSeq;

	public PeWgEmpScoreDetailPK() {
	}

	public PeWgEmpScoreDetailPK(String ouCode, Long evaYear, Long evaTime,
			String empCode, String formCode, Long titleSeq) {
		this.ouCode = ouCode;
		this.evaYear = evaYear;
		this.evaTime = evaTime;
		this.empCode = empCode;
		this.formCode = formCode;
		this.titleSeq = titleSeq;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getTitleSeq() {
		return titleSeq;
	}

	public void setTitleSeq(Long titleSeq) {
		this.titleSeq = titleSeq;
	}

}
