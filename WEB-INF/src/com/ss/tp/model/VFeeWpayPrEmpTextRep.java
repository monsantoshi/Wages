/*
 * Created on 13 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VFeeWpayPrEmpTextRep implements Serializable {
	private Integer keySeq;
	private String ouCode;
	private Integer year;
	private Integer period;
	private String empCode;
	private Integer codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	
	private String bankId;
	private String bankBranch;
	private String bankCode;

	private Integer costChild;

	private String gundanFlag;
	


	private String flagPr;

	private String flagStatus;
	private Integer seqData;

	;
	private String confirmFlag;

	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	public Integer getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(Integer keySeq) {
		this.keySeq = keySeq;
	}
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}
	public void setCodeSeqWork(Integer codeSeqWork) {
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
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public Integer getCostChild() {
		return costChild;
	}
	public void setCostChild(Integer costChild) {
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
	public Integer getSeqData() {
		return seqData;
	}
	public void setSeqData(Integer seqData) {
		this.seqData = seqData;
	}
	public String getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
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
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}



}
