package com.ss.tp.model;

import java.io.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;


public class GlInterfaceErp implements Serializable {
	private Long keySeq;
	private Date accountingDate;
	private Date dateCreated;
	private String userJeSourceName;
	private String segment1;
	private String segment2;
	private String segment3;
	private String segment4;
	private String segment5;
	private String segment6;
	private Double enteredDr;
	private Double enteredCr;	
	private String reference4;
	
	
	//String DATE_FORMAT = "dd/MM/yyyy";
   // SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);


	public GlInterfaceErp() {

	}




	public GlInterfaceErp(Long keySeq, Date accountingDate, Date dateCreated,
			String segment1, String segment2, String segment3, String segment4,
			String segment5, String segment6, Double enteredDr,
			Double enteredCr, String reference4,String userJe) {
		
		this.keySeq = keySeq;
		this.accountingDate = accountingDate;
		this.dateCreated = dateCreated;
		this.segment1 = segment1;
		this.segment2 = segment2;
		this.segment3 = segment3;
		this.segment4 = segment4;
		this.segment5 = segment5;
		this.segment6 = segment6;
		this.enteredDr = enteredDr;
		this.enteredCr = enteredCr;
		this.reference4 = reference4;
		this.userJeSourceName = userJe;
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




	public Date getDateCreated() {
		return dateCreated;
	}




	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}




	public String getSegment1() {
		return segment1;
	}




	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}




	public String getSegment2() {
		return segment2;
	}




	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}




	public String getSegment3() {
		return segment3;
	}




	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}




	public String getSegment4() {
		return segment4;
	}




	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}




	public String getSegment5() {
		return segment5;
	}




	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}




	public String getSegment6() {
		return segment6;
	}




	public void setSegment6(String segment6) {
		this.segment6 = segment6;
	}




	public Double getEnteredDr() {
		return enteredDr;
	}




	public void setEnteredDr(Double enteredDr) {
		this.enteredDr = enteredDr;
	}




	public Double getEnteredCr() {
		return enteredCr;
	}




	public void setEnteredCr(Double enteredCr) {
		this.enteredCr = enteredCr;
	}




	public String getReference4() {
		return reference4;
	}




	public void setReference4(String reference4) {
		this.reference4 = reference4;
	}




	public String getUserJeSourceName() {
		return userJeSourceName;
	}




	public void setUserJeSourceName(String userJeSourceName) {
		this.userJeSourceName = userJeSourceName;
	}

	

}
