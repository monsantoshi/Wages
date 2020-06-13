/*
 * Created on 13 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VFeeWpayGlTransferVO implements Serializable {
	private Integer keySeq;
	
	private Double seq;
	private Double yearPr;
	private Double monthPr;
	private Date accountingDate;
	private String accountCode;
	private String accountName;

	
	private Double debit;
	private Double credit;
	public Integer getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(Integer keySeq) {
		this.keySeq = keySeq;
	}
	public Double getSeq() {
		return seq;
	}
	public void setSeq(Double seq) {
		this.seq = seq;
	}
	public Double getYearPr() {
		return yearPr;
	}
	public void setYearPr(Double yearPr) {
		this.yearPr = yearPr;
	}
	public Double getMonthPr() {
		return monthPr;
	}
	public void setMonthPr(Double monthPr) {
		this.monthPr = monthPr;
	}
	public Date getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}

	
	

}
