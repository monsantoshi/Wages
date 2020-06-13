/*
 * Created on 14 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPeriodLine implements Serializable {
	private WgPeriodLinePK wgPeriodLinePK;
	private String periodName;
	private Date startDate;
	private Date endDate;
	private Date payDate;
	private String tranClose;
	private String prClose;
	private String mainClose;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMainClose() {
		return mainClose;
	}

	public void setMainClose(String mainClose) {
		this.mainClose = mainClose;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getPrClose() {
		return prClose;
	}

	public void setPrClose(String prClose) {
		this.prClose = prClose;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTranClose() {
		return tranClose;
	}

	public void setTranClose(String tranClose) {
		this.tranClose = tranClose;
	}

	public WgPeriodLinePK getWgPeriodLinePK() {
		return wgPeriodLinePK;
	}

	public void setWgPeriodLinePK(WgPeriodLinePK wgPeriodLinePK) {
		this.wgPeriodLinePK = wgPeriodLinePK;
	}
}
