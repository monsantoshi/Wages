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
public class WgPrWorkDay implements Serializable {
	private WgPrWorkDayPK wgPrWorkDayPK;
	private Integer codeSeqWork;
	private Double workDay;
	private Double workAmt;
	private Double wageAmt;
	private String confirmFlag;
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

	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Integer codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public Double getWageAmt() {
		return wageAmt;
	}

	public void setWageAmt(Double wageAmt) {
		this.wageAmt = wageAmt;
	}

	public WgPrWorkDayPK getWgPrWorkDayPK() {
		return wgPrWorkDayPK;
	}

	public void setWgPrWorkDayPK(WgPrWorkDayPK wgPrWorkDayPK) {
		this.wgPrWorkDayPK = wgPrWorkDayPK;
	}

	public Double getWorkAmt() {
		return workAmt;
	}

	public void setWorkAmt(Double workAmt) {
		this.workAmt = workAmt;
	}

	public Double getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Double workDay) {
		this.workDay = workDay;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
}
