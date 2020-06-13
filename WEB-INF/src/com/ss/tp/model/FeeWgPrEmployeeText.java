package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPrEmployeeText implements Serializable {
	private long keySeq;
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

	private String flagStatus;
	private Double seqData;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	private String confirmFlag;
	
	private String bankBranch;

	



	public FeeWgPrEmployeeText() {
	}

	public FeeWgPrEmployeeText( String id,
			 Double work, 
			String flag, Double child,  String by, Date date,
			String code,
			String pr, String status, String flag2,
			long seq,String status2, String code2, String status3,  Double period,
			 Double data, String id2, String by2,
			Date date2, Double year, 
			String bankBranch) {
	
		bankId = id;
		
		codeSeqWork = work;
	
		confirmFlag = flag;
		costChild = child;
	
		creBy = by;
		creDate = date;
	
		empCode = code;

		flagPr = pr;
		flagStatus = status;
	
		gundanFlag = flag2;
	
		keySeq = seq;
	
		marriedStatus = status2;

		ouCode = code2;
	
		payStatus = status3;

		this.period = period;
		
		seqData = data;
		taxId = id2;
	
		updBy = by2;
		updDate = date2;
		this.year = year;
		
		this.bankBranch = bankBranch;

		
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

	
	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
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




	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	


	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}


	public String getGundanFlag() {
		return gundanFlag;
	}

	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}

	public long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(long keySeq) {
		this.keySeq = keySeq;
	}

	

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}


	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
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

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}


}
