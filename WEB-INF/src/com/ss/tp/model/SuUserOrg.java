package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class SuUserOrg implements Serializable {
	private SuUserOrgPK pk;
	private String updBy;
	private Date updDate;

	public SuUserOrg() {
	}

	public SuUserOrg(SuUserOrgPK pk, String updBy, Date updDate) {
		this.pk = pk;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	public SuUserOrgPK getPk() {
		return pk;
	}

	public void setPk(SuUserOrgPK pk) {
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
