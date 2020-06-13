package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class RwModSalVO implements Serializable {
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

	/* start attribute for report */
	private String divCode;
	private String secCode;
	private String divDesc;
	private String secDesc;
	private String preFix;
	private String firstName;
	private String lastName;
	private String areaCode;
	private String areaDesc;

	/* end attribute for report */

	public RwModSalVO() {

	}

	public RwModSalVO(String step, Long seq, String flag, String by, Date date,
			String code, Date back, Date edun, Date job, Double year,
			String pr, Long seq2, String level, Double sal, Double sal2,
			String code2, Double pr2, Double work, Double data, Date back2,
			Date edun2, Date job2, Date new1, Double amt, String by2,
			Date date2, Double pr3, Double work2,String edu) {

		// TODO Auto-generated constructor stub
		this.backStep = step;
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.endDateBack = back;
		this.endDateEdun = edun;
		this.endDateJob = job;
		this.fiscalYear = year;
		this.flagPr = pr;
		this.keySeq = seq2;
		this.multipleLevel = level;
		this.newSal = sal;
		this.oldSal = sal2;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.startDateBack = back2;
		this.startDateEdun = edun2;
		this.startDateJob = job2;
		this.startDateNew = new1;
		this.totalAmt = amt;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
		this.eduWut = edu;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPreFix() {
		return preFix;
	}

	public void setPreFix(String preFix) {
		this.preFix = preFix;
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
