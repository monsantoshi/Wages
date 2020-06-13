package com.ss.tp.dto;

import java.io.Serializable;

import java.util.Date;


public class ApToGlVO implements Serializable {
	private Long keySeq;
	private Date accountingDate;
    private Date createDate;
    private String account;
    private String divResponse;
    private Double amtDebit;
    private Double amtCredit;
    private String divDesc;
    
    

	public ApToGlVO() {

	}


	public ApToGlVO(Long keySeq, Date accountingDate, Date createDate,
			String account, String divResponse, Double amtDebit,
			Double amtCredit, String divDesc) {
		
		this.keySeq = keySeq;
		this.accountingDate = accountingDate;
		this.createDate = createDate;
		this.account = account;
		this.divResponse = divResponse;
		this.amtDebit = amtDebit;
		this.amtCredit = amtCredit;
		this.divDesc = divDesc;
	}


	public Long getKeySeq() {
		return keySeq;
	}


	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}


	public Date getAccountingDate() {
		return accountingDate;
	}


	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getDivResponse() {
		return divResponse;
	}


	public void setDivResponse(String divResponse) {
		this.divResponse = divResponse;
	}


	public Double getAmtDebit() {
		return amtDebit;
	}


	public void setAmtDebit(Double amtDebit) {
		this.amtDebit = amtDebit;
	}


	public Double getAmtCredit() {
		return amtCredit;
	}


	public void setAmtCredit(Double amtCredit) {
		this.amtCredit = amtCredit;
	}


	public String getDivDesc() {
		return divDesc;
	}


	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	

}
