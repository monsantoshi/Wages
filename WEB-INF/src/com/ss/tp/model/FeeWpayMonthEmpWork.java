/*
 * Created on 24 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayMonthEmpWork implements Serializable {
	private FeeWpayMonthEmpWorkPK feeWpayMonthEmpWorkPK;
	private String workCode;
	private Double totalDays;
	private Integer totalTime;
	private String pnTransfer;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String note;
	private FeeWpayStatusWork feeWpayStatusWork;
	private WgEmployee wgEmployee;

	public WgEmployee getWgEmployee() {
		return wgEmployee;
	}

	public void setWgEmployee(WgEmployee wgEmployee) {
		this.wgEmployee = wgEmployee;
	}

	/**
	 * @return Returns the taStatusWork.
	
	/**
	 * @return Returns the creBy.
	 */
	public String getCreBy() {
		return creBy;
	}

	/**
	 * @param creBy
	 *            The creBy to set.
	 */
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	/**
	 * @return Returns the creDate.
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            The creDate to set.
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return Returns the pnTransfer.
	 */
	public String getPnTransfer() {
		return pnTransfer;
	}

	/**
	 * @param pnTransfer
	 *            The pnTransfer to set.
	 */
	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
	}



	public FeeWpayMonthEmpWorkPK getFeeWpayMonthEmpWorkPK() {
		return feeWpayMonthEmpWorkPK;
	}

	public void setFeeWpayMonthEmpWorkPK(FeeWpayMonthEmpWorkPK feeWpayMonthEmpWorkPK) {
		this.feeWpayMonthEmpWorkPK = feeWpayMonthEmpWorkPK;
	}

	public FeeWpayStatusWork getFeeWpayStatusWork() {
		return feeWpayStatusWork;
	}

	public void setFeeWpayStatusWork(FeeWpayStatusWork feeWpayStatusWork) {
		this.feeWpayStatusWork = feeWpayStatusWork;
	}

	/**
	 * @return Returns the totalDays.
	 */
	public Double getTotalDays() {
		return totalDays;
	}

	/**
	 * @param totalDays
	 *            The totalDays to set.
	 */
	public void setTotalDays(Double totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @return Returns the totalTime.
	 */
	public Integer getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime
	 *            The totalTime to set.
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return Returns the updBy.
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy
	 *            The updBy to set.
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return Returns the updDate.
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            The updDate to set.
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/**
	 * @return Returns the workCode.
	 */
	public String getWorkCode() {
		return workCode;
	}

	/**
	 * @param workCode
	 *            The workCode to set.
	 */
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
