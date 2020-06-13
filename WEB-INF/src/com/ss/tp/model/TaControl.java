/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaControl implements Serializable {
	private TaControlPk taControlPk;
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

	public TaControlPk getTaControlPk() {
		return taControlPk;
	}

	public void setTaControlPk(TaControlPk taControlPk) {
		this.taControlPk = taControlPk;
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
