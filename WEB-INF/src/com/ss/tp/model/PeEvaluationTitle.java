package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PeEvaluationTitle implements Serializable {
	private PeEvaluationTitlePK pk;
	private String titleDesc;
	private String inactive;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	public PeEvaluationTitle() {
	}

	public PeEvaluationTitle(PeEvaluationTitlePK pk, String titleDesc,
			String inactive, String creBy, Date creDate, String updBy,
			Date updDate) {
		this.pk = pk;
		this.titleDesc = titleDesc;
		this.inactive = inactive;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
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

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public PeEvaluationTitlePK getPk() {
		return pk;
	}

	public void setPk(PeEvaluationTitlePK pk) {
		this.pk = pk;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
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
