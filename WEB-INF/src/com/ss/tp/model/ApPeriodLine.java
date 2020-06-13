package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;


public class ApPeriodLine implements Serializable {
	private ApPeriodLinePK pk;
	private String approveClose;
	private String transferClose;
	private String bankClose;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String approveBy;
	private Date approveDate;
	private String transferBy;
	private Date transferDate;
	private String bankTransferBy;
	private Date bankTransferDate;
	
	public ApPeriodLine() {

	}
	
	public ApPeriodLine(ApPeriodLinePK pk) {
		super();
		this.pk = pk;
	}



	public ApPeriodLine(ApPeriodLinePK pk, String appClose,
			String tranClose, String bClose, String cBy, Date cDate,
			String uBy, Date uDate, String appBy, Date appDate,
			String tranBy, Date tranDate, String bankBy,
			Date bankDate) {
		super();
		this.pk = pk;
		approveClose = appClose;
		transferClose = tranClose;
		bankClose = bClose;
		creBy = cBy;
		creDate = cDate;
		updBy = uBy;
		updDate = uDate;
		approveBy = appBy;
		approveDate = appDate;
		transferBy = tranBy;
		transferDate = tranDate;
		bankTransferBy = bankBy;
		bankTransferDate = bankDate;
	}

	public ApPeriodLinePK getPk() {
		return pk;
	}
	
	
	
	public void setPk(ApPeriodLinePK pk) {
		this.pk = pk;
	}

	public String getApproveClose() {
		return approveClose;
	}
	public void setApproveClose(String approveClose) {
		this.approveClose = approveClose;
	}
	public String getTransferClose() {
		return transferClose;
	}
	public void setTransferClose(String transferClose) {
		this.transferClose = transferClose;
	}
	public String getBankClose() {
		return bankClose;
	}
	public void setBankClose(String bankClose) {
		this.bankClose = bankClose;
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
	public String getApproveBy() {
		return approveBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public String getTransferBy() {
		return transferBy;
	}
	public void setTransferBy(String transferBy) {
		this.transferBy = transferBy;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public String getBankTransferBy() {
		return bankTransferBy;
	}
	public void setBankTransferBy(String bankTransferBy) {
		this.bankTransferBy = bankTransferBy;
	}
	public Date getBankTransferDate() {
		return bankTransferDate;
	}
	public void setBankTransferDate(Date bankTransferDate) {
		this.bankTransferDate = bankTransferDate;
	}

	

	
}
