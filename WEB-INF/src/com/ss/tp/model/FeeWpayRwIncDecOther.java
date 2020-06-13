package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayRwIncDecOther implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;
	private String groupCode;
	private String incDecCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	private Double totalAmt;
	private Date startDate;
	private Date endDate;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String approveBy;
	private Date approveDate;
	private String approveFlag;
	private String approveClose;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private String loanId;
	public FeeWpayRwIncDecOther() {

	}
	public FeeWpayRwIncDecOther(Long keySeq, String ouCode, Double yearPr,
			Double periodPr, String empCode, String groupCode,
			String incDecCode, Double yearWork, Double periodWork,
			Long codeSeq, Double totalAmt, Date startDate, Date endDate,
			String flagPr, Double seqData, String confirmFlag,
			String approveBy, Date approveDate, String approveFlag,
			String approveClose, String creBy, String updBy, Date creDate,
			Date updDate, String loanId) {
		//super();
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
		this.empCode = empCode;
		this.groupCode = groupCode;
		this.incDecCode = incDecCode;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.codeSeq = codeSeq;
		this.totalAmt = totalAmt;
		this.startDate = startDate;
		this.endDate = endDate;
		this.flagPr = flagPr;
		this.seqData = seqData;
		this.confirmFlag = confirmFlag;
		this.approveBy = approveBy;
		this.approveDate = approveDate;
		this.approveFlag = approveFlag;
		this.approveClose = approveClose;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
		this.loanId = loanId;
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
	public Double getYearPr() {
		return yearPr;
	}
	public void setYearPr(Double yearPr) {
		this.yearPr = yearPr;
	}
	public Double getPeriodPr() {
		return periodPr;
	}
	public void setPeriodPr(Double periodPr) {
		this.periodPr = periodPr;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getIncDecCode() {
		return incDecCode;
	}
	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}
	public Double getYearWork() {
		return yearWork;
	}
	public void setYearWork(Double yearWork) {
		this.yearWork = yearWork;
	}
	public Double getPeriodWork() {
		return periodWork;
	}
	public void setPeriodWork(Double periodWork) {
		this.periodWork = periodWork;
	}
	public Long getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFlagPr() {
		return flagPr;
	}
	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}
	public Double getSeqData() {
		return seqData;
	}
	public void setSeqData(Double seqData) {
		this.seqData = seqData;
	}
	public String getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	public String getApproveBy() {
		return approveBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public String getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}
	public String getApproveClose() {
		return approveClose;
	}
	public void setApproveClose(String approveClose) {
		this.approveClose = approveClose;
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
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}



}
