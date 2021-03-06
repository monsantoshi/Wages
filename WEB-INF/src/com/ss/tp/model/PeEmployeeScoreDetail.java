package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PeEmployeeScoreDetail implements Serializable {
	private PeEmployeeScoreDetailPK pk;
	private Double evaScore;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private PeEmployeeScore refPnEmployeeScore;
	private PeEvaluationFormTitle refPeEvaluationFormTitle;

	public PeEmployeeScoreDetail() {
	}

	public PeEmployeeScoreDetail(PeEmployeeScoreDetailPK pk, Double evaScore,
			String creBy, Date creDate, String updBy, Date updDate,
			PeEmployeeScore refPnEmployeeScore,
			PeEvaluationFormTitle refPeEvaluationFormTitle) {
		this.pk = pk;
		this.evaScore = evaScore;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.refPnEmployeeScore = refPnEmployeeScore;
		this.refPeEvaluationFormTitle = refPeEvaluationFormTitle;
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

	public Double getEvaScore() {
		return evaScore;
	}

	public void setEvaScore(Double evaScore) {
		this.evaScore = evaScore;
	}

	public PeEmployeeScoreDetailPK getPk() {
		return pk;
	}

	public void setPk(PeEmployeeScoreDetailPK pk) {
		this.pk = pk;
	}

	public PeEvaluationFormTitle getRefPeEvaluationFormTitle() {
		return refPeEvaluationFormTitle;
	}

	public void setRefPeEvaluationFormTitle(
			PeEvaluationFormTitle refPeEvaluationFormTitle) {
		this.refPeEvaluationFormTitle = refPeEvaluationFormTitle;
	}

	public PeEmployeeScore getRefPnEmployeeScore() {
		return refPnEmployeeScore;
	}

	public void setRefPnEmployeeScore(PeEmployeeScore refPnEmployeeScore) {
		this.refPnEmployeeScore = refPnEmployeeScore;
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
