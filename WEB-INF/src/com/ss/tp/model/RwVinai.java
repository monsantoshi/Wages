package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwVinai implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	private Double decSal;
	private Double newSalary;
	private Double decSalMonth;
	private Double cutSalYear;
	private Double cutSalMonth;
	private Double cutSalPercent;
	private Double absYear1;
	private Double absMonth1;
	private Double absDay1;
	private Double absYear2;
	private Double absMonth2;
	private Double absDay2;
	private Date startDateQut;
	private Date endDateQut;
	private Double totalAmt;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public RwVinai() {

	}

	public RwVinai(Double day1, Double day2, Double month1, Double month2,
			Double year1, Double year2, Long seq, String flag, String by,
			Date date, Double sal, Double month, Double percent, Double sal2,
			Double month3, String code, Date qut, String pr, Long seq2,
			Double salary, String code2, Double pr2, Double work, Double data,
			Date qut2, Double amt, String by2, Date date2, Double pr3,
			Double work2) {

		// TODO Auto-generated constructor stub
		this.absDay1 = day1;
		this.absDay2 = day2;
		this.absMonth1 = month1;
		this.absMonth2 = month2;
		this.absYear1 = year1;
		this.absYear2 = year2;
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.setCutSalYear(sal);
		this.cutSalMonth = month;
		this.setCutSalPercent(percent);
		this.decSal = sal2;
		this.decSalMonth = month3;
		this.empCode = code;
		this.endDateQut = qut;
		this.flagPr = pr;
		this.keySeq = seq2;
		this.newSalary = salary;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.startDateQut = qut2;
		this.totalAmt = amt;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
	}

	public Double getAbsDay1() {
		return absDay1;
	}

	public void setAbsDay1(Double absDay1) {
		this.absDay1 = absDay1;
	}

	public Double getAbsDay2() {
		return absDay2;
	}

	public void setAbsDay2(Double absDay2) {
		this.absDay2 = absDay2;
	}

	public Double getAbsMonth1() {
		return absMonth1;
	}

	public void setAbsMonth1(Double absMonth1) {
		this.absMonth1 = absMonth1;
	}

	public Double getAbsMonth2() {
		return absMonth2;
	}

	public void setAbsMonth2(Double absMonth2) {
		this.absMonth2 = absMonth2;
	}

	public Double getAbsYear1() {
		return absYear1;
	}

	public void setAbsYear1(Double absYear1) {
		this.absYear1 = absYear1;
	}

	public Double getAbsYear2() {
		return absYear2;
	}

	public void setAbsYear2(Double absYear2) {
		this.absYear2 = absYear2;
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

	public Double getCutSalMonth() {
		return cutSalMonth;
	}

	public void setCutSalMonth(Double cutSalMonth) {
		this.cutSalMonth = cutSalMonth;
	}

	/*
	 * public Double getCutSalPercent() { return cutSalPercent; } public void
	 * setCutSalPercent(Double cutSalPercent) { this.cutSalPercent =
	 * cutSalPercent; }
	 */
	public Double getDecSal() {
		return decSal;
	}

	public void setDecSal(Double decSal) {
		this.decSal = decSal;
	}

	public Double getDecSalMonth() {
		return decSalMonth;
	}

	public void setDecSalMonth(Double decSalMonth) {
		this.decSalMonth = decSalMonth;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Date getEndDateQut() {
		return endDateQut;
	}

	public void setEndDateQut(Date endDateQut) {
		this.endDateQut = endDateQut;
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

	public Double getNewSalary() {
		return newSalary;
	}

	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
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

	public Date getStartDateQut() {
		return startDateQut;
	}

	public void setStartDateQut(Date startDateQut) {
		this.startDateQut = startDateQut;
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

	public void setCutSalPercent(Double cutSalPercent) {
		this.cutSalPercent = cutSalPercent;
	}

	public Double getCutSalPercent() {
		return cutSalPercent;
	}

	public void setCutSalYear(Double cutSalYear) {
		this.cutSalYear = cutSalYear;
	}

	public Double getCutSalYear() {
		return cutSalYear;
	}

}
