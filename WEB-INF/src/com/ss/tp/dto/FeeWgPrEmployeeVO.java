package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPrEmployeeVO implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;
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
;
	private String confirmFlag;

	private Double newCodeSeq;
	
	private String bankBranch;
	

	
	public FeeWgPrEmployeeVO( String id, Double work,String flag,
			Double child, String by, Date date, String code,  String pr,
			String flag2, String status,
			String code2,
			String status2, 
			Double period,  Double rec, String id2, Double fund2,
			String by2, Date date2, Double year,String bankBranch) {
		super();
		// TODO Auto-generated constructor stub
	
		bankId = id;
	
		codeSeqWork = work;
	
		confirmFlag = flag;
		costChild = child;
	
		creBy = by;
		creDate = date;
	
		empCode = code;
	
		flagPr = pr;
	
		gundanFlag = flag2;
	
		marriedStatus = status;
	
		ouCode = code2;
		
		payStatus = status2;
		
		this.period = period;
		
		seqRec = rec;
		taxId = id2;
	
		updBy = by2;
		updDate = date2;
		this.year = year;
		
		this.bankBranch = bankBranch;
		
	}

	public FeeWgPrEmployeeVO() {
	}


	public String getOuCode() {
		return ouCode;
	}



	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}



	public Double getYear() {
		return year;
	}



	public void setYear(Double year) {
		this.year = year;
	}



	public Double getPeriod() {
		return period;
	}



	public void setPeriod(Double period) {
		this.period = period;
	}



	public String getEmpCode() {
		return empCode;
	}



	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}



	public Double getCodeSeqWork() {
		return codeSeqWork;
	}



	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}



	public String getTaxId() {
		return taxId;
	}



	public void setTaxId(String taxId) {
		this.taxId = taxId;
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



	public String getBankId() {
		return bankId;
	}



	public void setBankId(String bankId) {
		this.bankId = bankId;
	}



	public Double getCostChild() {
		return costChild;
	}



	public void setCostChild(Double costChild) {
		this.costChild = costChild;
	}



	public String getGundanFlag() {
		return gundanFlag;
	}



	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}



	public String getFlagPr() {
		return flagPr;
	}



	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
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



	public Double getSeqRec() {
		return seqRec;
	}



	public void setSeqRec(Double seqRec) {
		this.seqRec = seqRec;
	}



	public String getConfirmFlag() {
		return confirmFlag;
	}



	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}



	public Double getNewCodeSeq() {
		return newCodeSeq;
	}



	public void setNewCodeSeq(Double newCodeSeq) {
		this.newCodeSeq = newCodeSeq;
	}



	public String getBankBranch() {
		return bankBranch;
	}



	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}


}
