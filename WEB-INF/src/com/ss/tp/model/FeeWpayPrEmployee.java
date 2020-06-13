package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayPrEmployee implements Serializable {
    private FeeWpayPrEmployeePK key;
	private Double codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private Double costChild;
	private String gundanFlag;
	
	private String flagPr;

	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private Double seqRec;
	
	private String confirmFlag;

	private Double newCodeSeq;
	private String newPayStatus;
	
	private String bankBranch;
	private String bankCode;
	
	



	public String getNewPayStatus() {
		return newPayStatus;
	}

	public void setNewPayStatus(String newPayStatus) {
		this.newPayStatus = newPayStatus;
	}

	public Double getNewCodeSeq() {
		return newCodeSeq;
	}

	public void setNewCodeSeq(Double newCodeSeq) {
		this.newCodeSeq = newCodeSeq;
	}

	public FeeWpayPrEmployee( String id,  Double work, String flag,
			Double child,  String by, Date date, String pr,
			String flag2, FeeWpayPrEmployeePK key,
			String status, 
			 String status2,
			Double rec, String id2, 
			String by2, Date date2,
			String bankBranch,
			String bankCode) {
		super();
		// TODO Auto-generated constructor stub
	
		bankId = id;
		
		codeSeqWork = work;
	
		confirmFlag = flag;
		costChild = child;
	
		creBy = by;
		creDate = date;
	
		
		flagPr = pr;
	
		gundanFlag = flag2;
	
		this.key = key;
		
		marriedStatus = status;
	

		payStatus = status2;

		seqRec = rec;
		taxId = id2;
	
		updBy = by2;
		updDate = date2;
	
		this.bankBranch = bankBranch;
		this.bankCode = bankCode;
	
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public FeeWpayPrEmployee() {
	}

	
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}



	public Double getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public Double getCostChild() {
		return costChild;
	}

	public void setCostChild(Double costChild) {
		this.costChild = costChild;
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


	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	
	public String getGundanFlag() {
		return gundanFlag;
	}

	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}


	
	public FeeWpayPrEmployeePK getKey() {
		return key;
	}

	public void setKey(FeeWpayPrEmployeePK key) {
		 this.key = key;
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
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


	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	

	public Double getSeqRec() {
		return seqRec;
	}

	public void setSeqRec(Double seqRec) {
		this.seqRec = seqRec;
	}

	

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	

}
