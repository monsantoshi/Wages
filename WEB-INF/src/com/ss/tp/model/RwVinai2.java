package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwVinai2 implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	private Double decDay;
	private Double totalAmt;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private String remark;

	public RwVinai2() {

	}

	public RwVinai2(Long seq, String flag, String by, Date date, Double day,
			String code, String pr, Long seq2, String code2, Double pr2,
			Double work, Double data, Double amt, String by2, Date date2,
			Double pr3, Double work2, String remark) {

		// TODO Auto-generated constructor stub
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.decDay = day;
		this.empCode = code;
		this.flagPr = pr;
		this.keySeq = seq2;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.totalAmt = amt;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
		this.remark = remark;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
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

	public Double getDecDay() {
		return decDay;
	}

	public void setDecDay(Double decDay) {
		this.decDay = decDay;
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

	public Double getPeriodPr() {
		return periodPr;
	}

	public void setPeriodPr(Double periodPr) {
		this.periodPr = periodPr;
	}

	public Double getPeriodWork() {
		return periodWork;
	}

	public void setPeriodWork(Double periodWork) {
		this.periodWork = periodWork;
	}

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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

	public Double getYearPr() {
		return yearPr;
	}

	public void setYearPr(Double yearPr) {
		this.yearPr = yearPr;
	}

	public Double getYearWork() {
		return yearWork;
	}

	public void setYearWork(Double yearWork) {
		this.yearWork = yearWork;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
