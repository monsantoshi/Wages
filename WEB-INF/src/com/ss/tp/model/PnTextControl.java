package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PnTextControl implements Serializable {
	private PnTextControlPK pk;
	private String flagClose;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	public PnTextControl() {
	}

	public PnTextControl(PnTextControlPK pk, String flagClose, String creBy,
			Date creDate, String updBy, Date updDate) {
		this.pk = pk;
		this.flagClose = flagClose;
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

	public String getFlagClose() {
		return flagClose;
	}

	public void setFlagClose(String flagClose) {
		this.flagClose = flagClose;
	}

	public PnTextControlPK getPk() {
		return pk;
	}

	public void setPk(PnTextControlPK pk) {
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
