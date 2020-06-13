package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PeEvaluationFormTitleVO implements Serializable {
	private String ouCode;
	private String formCode;
	private Long titleSeq;
	private String titleCode;
	private String formDesc;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String titleName;
	private Double titleScore;

	public Double getTitleScore() {
		return titleScore;
	}

	public void setTitleScore(Double titleScore) {
		this.titleScore = titleScore;
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

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormDesc() {
		return formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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
