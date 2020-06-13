package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class TaWgControl implements Serializable {
	private TaWgControlPk taWgControlPk;
	private String flagClose;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private PnOrganization pnOrgzation;

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

	public String getFlagClose() {
		return flagClose;
	}

	public void setFlagClose(String flagClose) {
		this.flagClose = flagClose;
	}

	public PnOrganization getPnOrgzation() {
		return pnOrgzation;
	}

	public void setPnOrgzation(PnOrganization pnOrgzation) {
		this.pnOrgzation = pnOrgzation;
	}

	public TaWgControlPk getTaWgControlPk() {
		return taWgControlPk;
	}

	public void setTaWgControlPk(TaWgControlPk taWgControlPk) {
		this.taWgControlPk = taWgControlPk;
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
