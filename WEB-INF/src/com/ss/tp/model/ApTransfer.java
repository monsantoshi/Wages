package com.ss.tp.model;

import java.io.Serializable;

import java.util.Date;


public class ApTransfer implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPn;
	private String volumeSet;
	private Double seqData;
    private Long refNo;
	private String empCode;
	private Long codeSeq;
    private Double totAmt;

	

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public ApTransfer() {

	}

	public ApTransfer(Long keySeq, String ouCode, Double yearPn,
			String volumeSet, Double seqData, Long refNo, String empCode, Long codeSeq,
			Double totAmt,
			String creBy, String updBy,
			Date creDate, Date updDate) {

		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPn = yearPn;
		
		this.volumeSet = volumeSet;
		this.seqData = seqData;
		this.refNo = refNo;
		this.empCode = empCode;
		this.codeSeq = codeSeq;
	
		this.totAmt = totAmt;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getYearPn() {
		return yearPn;
	}

	public void setYearPn(Double yearPn) {
		this.yearPn = yearPn;
	}



	public String getVolumeSet() {
		return volumeSet;
	}

	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
	}

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
	}

	public Long getRefNo() {
		return refNo;
	}

	public void setRefNo(Long refNo) {
		this.refNo = refNo;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}
	

	public Double getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(Double totAmt) {
		this.totAmt = totAmt;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	
}
