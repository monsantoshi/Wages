package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PeWgEmpScoreDetailVO implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;
	private String formCode;
	private Long titleSeq;
	private Double evaScore;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String formDesc;
	private String titleCode;
	private String titleDesc;
	private Double titleScore;

	public Double getTitleScore() {
		return titleScore;
	}

	public void setTitleScore(Double titleScore) {
		this.titleScore = titleScore;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	public String getFormDesc() {
		return formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Double getEvaScore() {
		return evaScore;
	}

	public void setEvaScore(Double evaScore) {
		this.evaScore = evaScore;
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

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}
