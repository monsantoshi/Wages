package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class SuUserOrganization implements Serializable {
	private SuUserOrganizationPK pk;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	public SuUserOrganization() {

	}

	public SuUserOrganization(String by, Date date, SuUserOrganizationPK pk,
			String by2, Date date2) {
		creBy = by;
		creDate = date;
		this.pk = pk;
		updBy = by2;
		updDate = date2;
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

	public SuUserOrganizationPK getPk() {
		return pk;
	}

	public void setPk(SuUserOrganizationPK pk) {
		this.pk = pk;
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
