package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PeEvaluationFormTitle implements Serializable {
	private PeEvaluationFormTitlePK pk;
	private String titleCode;
	private String formDesc;
	private Double titleScore;
	private Double formScore;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String formType;
	private String formDefault;
	private PeEvaluationTitle refPeEvaluationTitle;

	public PeEvaluationFormTitle() {
	}

	public PeEvaluationFormTitle(PeEvaluationFormTitlePK pk, String titleCode,
			String formDesc, Double titleScore, Double formScore, String creBy,
			Date creDate, String updBy, Date updDate, String formType,
			String formDefault, PeEvaluationTitle refPeEvaluationTitle) {
		this.pk = pk;
		this.titleCode = titleCode;
		this.formDesc = formDesc;
		this.titleScore = titleScore;
		this.formScore = formScore;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.formType = formType;
		this.formDefault = formDefault;
		this.refPeEvaluationTitle = refPeEvaluationTitle;
	}

	public String getFormDefault() {
		return formDefault;
	}

	public void setFormDefault(String formDefault) {
		this.formDefault = formDefault;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
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

	public String getFormDesc() {
		return formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public Double getFormScore() {
		return formScore;
	}

	public void setFormScore(Double formScore) {
		this.formScore = formScore;
	}

	public PeEvaluationFormTitlePK getPk() {
		return pk;
	}

	public void setPk(PeEvaluationFormTitlePK pk) {
		this.pk = pk;
	}

	public PeEvaluationTitle getRefPeEvaluationTitle() {
		return refPeEvaluationTitle;
	}

	public void setRefPeEvaluationTitle(PeEvaluationTitle refPeEvaluationTitle) {
		this.refPeEvaluationTitle = refPeEvaluationTitle;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public Double getTitleScore() {
		return titleScore;
	}

	public void setTitleScore(Double titleScore) {
		this.titleScore = titleScore;
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
