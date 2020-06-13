package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayPrEmployeeTextVO implements Serializable {
	private String keySeq;
	private String ouCode;
	private String year;
	private String period;
	private String empCode;
	private String codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private String bankCode;
	private String costChild;

	private String gundanFlag;
	
	private String flagPr;
	
	private String flagStatus;
	private String seqData;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	private String confirmFlag;

	private String divCode;
	private String divDesc;
	private String secCode;
	private String secDesc;
	private String fullName;
	private String prefix;
	private String name;
	private String lastName;
	private String areaCode;
	private String areaDesc;

	private String bankBranch;

	

	public FeeWpayPrEmployeeTextVO() {
	}

	public FeeWpayPrEmployeeTextVO(String id,
			String work, 
			String flag, String child, String by, Date date,
			String code,
			String pr, String status, String flag2,
			String seq, String status2,  String code2, String status3, String period,
			String data, String id2,  String by2,
			Date date2, String year,
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
		this.bankCode = bankCode;
	
		
	}

	public String getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(String codeSeqWork) {
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

	public String getCostChild() {
		return costChild;
	}

	public void setCostChild(String costChild) {
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

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public String getSeqData() {
		return seqData;
	}

	public void setSeqData(String seqData) {
		this.seqData = seqData;
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

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivDesc() {
		return divDesc;
	}

	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}




}
