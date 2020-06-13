package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwModSal implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	private Date startDateNew;
	private Double fiscalYear;
	private Date startDateEdun;
	private Date endDateEdun;
	private Double newSal;
	private Double oldSal;
	private Date startDateBack;
	private Date endDateBack;
	private Date startDateJob;
	private Date endDateJob;
	private String multipleLevel;
	private String backStep;
	private Double totalAmt;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private String eduWut;

	public RwModSal() {

	}

	public RwModSal(Long keySeq, String ouCode, Double yearPr, Double periodPr,
			String empCode, Double yearWork, Double periodWork, Long codeSeq,
			Date startDateNew, Double fiscalYear, Date startDateEdun,
			Date endDateEdun, Double newSal, Double oldSal, Date startDateBack,
			Date endDateBack, Date startDateJob, Date endDateJob,
			String multipleLevel, String backStep, Double totalAmt,
			String flagPr, Double seqData, String confirmFlag, String creBy,
			String updBy, Date creDate, Date updDate,String eduWut) {

		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
		this.empCode = empCode;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.codeSeq = codeSeq;
		this.startDateNew = startDateNew;
		this.fiscalYear = fiscalYear;
		this.startDateEdun = startDateEdun;
		this.endDateEdun = endDateEdun;
		this.newSal = newSal;
		this.oldSal = oldSal;
		this.startDateBack = startDateBack;
		this.endDateBack = endDateBack;
		this.startDateJob = startDateJob;
		this.endDateJob = endDateJob;
		this.multipleLevel = multipleLevel;
		this.backStep = backStep;
		this.totalAmt = totalAmt;
		this.flagPr = flagPr;
		this.seqData = seqData;
		this.confirmFlag = confirmFlag;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
	    this.eduWut = eduWut;
	}

	public String getBackStep() {
		return backStep;
	}

	public void setBackStep(String backStep) {
		this.backStep = backStep;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Date getEndDateBack() {
		return endDateBack;
	}

	public void setEndDateBack(Date endDateBack) {
		this.endDateBack = endDateBack;
	}

	public Date getEndDateEdun() {
		return endDateEdun;
	}

	public void setEndDateEdun(Date endDateEdun) {
		this.endDateEdun = endDateEdun;
	}

	public Date getEndDateJob() {
		return endDateJob;
	}

	public void setEndDateJob(Date endDateJob) {
		this.endDateJob = endDateJob;
	}

	public Double getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(Double fiscalYear) {
		this.fiscalYear = fiscalYear;
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

	public String getMultipleLevel() {
		return multipleLevel;
	}

	public void setMultipleLevel(String multipleLevel) {
		this.multipleLevel = multipleLevel;
	}

	public Double getNewSal() {
		return newSal;
	}

	public void setNewSal(Double newSal) {
		this.newSal = newSal;
	}

	public Double getOldSal() {
		return oldSal;
	}

	public void setOldSal(Double oldSal) {
		this.oldSal = oldSal;
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

	public Date getStartDateBack() {
		return startDateBack;
	}

	public void setStartDateBack(Date startDateBack) {
		this.startDateBack = startDateBack;
	}

	public Date getStartDateEdun() {
		return startDateEdun;
	}

	public void setStartDateEdun(Date startDateEdun) {
		this.startDateEdun = startDateEdun;
	}

	public Date getStartDateJob() {
		return startDateJob;
	}

	public void setStartDateJob(Date startDateJob) {
		this.startDateJob = startDateJob;
	}

	public Date getStartDateNew() {
		return startDateNew;
	}

	public void setStartDateNew(Date startDateNew) {
		this.startDateNew = startDateNew;
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

	public String getEduWut() {
		return eduWut;
	}

	public void setEduWut(String eduWut) {
		this.eduWut = eduWut;
	}

}
