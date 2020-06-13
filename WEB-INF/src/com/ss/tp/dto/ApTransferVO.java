package com.ss.tp.dto;

import java.io.Serializable;

import java.util.Date;


public class ApTransferVO implements Serializable {

	private String seq;
	private String year;
	private String month;
	private String account;
	private String debit;
	private String credit;
	private String cont;

	public ApTransferVO() {

	}

	public ApTransferVO( String seq, String year, String month,
			String account, String debit, String credit,String cont) {
		this.seq = seq;
		this.year = year;
		this.month = month;
		this.account = account;
		this.debit = debit;
		this.credit = credit;
		this.cont = cont;
		
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	
	
}
