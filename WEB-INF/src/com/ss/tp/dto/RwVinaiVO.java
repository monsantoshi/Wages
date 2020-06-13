package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class RwVinaiVO implements Serializable {
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
	private String approveFlag;
	private String approveBy;
	private Date approveDate;
	private String approveClose;
    private String remark;
	public RwVinaiVO() {

	}
	public RwVinaiVO(Long keySeq, String ouCode, Double yearPr,
			Double periodPr, String empCode, Double yearWork,
			Double periodWork, Long codeSeq, Double decSal, Double newSalary,
			Double decSalMonth, Double cutSalYear, Double cutSalMonth,
			Double cutSalPercent, Double absYear1, Double absMonth1,
			Double absDay1, Double absYear2, Double absMonth2, Double absDay2,
			Date startDateQut, Date endDateQut, Double totalAmt, String flagPr,
			Double seqData, String confirmFlag, String creBy, String updBy,
			Date creDate, Date updDate, String approveFlag, String approveBy,
			Date approveDate, String approveClose, String remark) {
		
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
		this.empCode = empCode;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.codeSeq = codeSeq;
		this.decSal = decSal;
		this.newSalary = newSalary;
		this.decSalMonth = decSalMonth;
		this.cutSalYear = cutSalYear;
		this.cutSalMonth = cutSalMonth;
		this.cutSalPercent = cutSalPercent;
		this.absYear1 = absYear1;
		this.absMonth1 = absMonth1;
		this.absDay1 = absDay1;
		this.absYear2 = absYear2;
		this.absMonth2 = absMonth2;
		this.absDay2 = absDay2;
		this.startDateQut = startDateQut;
		this.endDateQut = endDateQut;
		this.totalAmt = totalAmt;
		this.flagPr = flagPr;
		this.seqData = seqData;
		this.confirmFlag = confirmFlag;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
		this.approveFlag = approveFlag;
		this.approveBy = approveBy;
		this.approveDate = approveDate;
		this.approveClose = approveClose;
		this.remark = remark;
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
	public Double getDecSal() {
		return decSal;
	}
	public void setDecSal(Double decSal) {
		this.decSal = decSal;
	}
	public Double getNewSalary() {
		return newSalary;
	}
	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
	}
	public Double getDecSalMonth() {
		return decSalMonth;
	}
	public void setDecSalMonth(Double decSalMonth) {
		this.decSalMonth = decSalMonth;
	}
	public Double getCutSalYear() {
		return cutSalYear;
	}
	public void setCutSalYear(Double cutSalYear) {
		this.cutSalYear = cutSalYear;
	}
	public Double getCutSalMonth() {
		return cutSalMonth;
	}
	public void setCutSalMonth(Double cutSalMonth) {
		this.cutSalMonth = cutSalMonth;
	}
	public Double getCutSalPercent() {
		return cutSalPercent;
	}
	public void setCutSalPercent(Double cutSalPercent) {
		this.cutSalPercent = cutSalPercent;
	}
	public Double getAbsYear1() {
		return absYear1;
	}
	public void setAbsYear1(Double absYear1) {
		this.absYear1 = absYear1;
	}
	public Double getAbsMonth1() {
		return absMonth1;
	}
	public void setAbsMonth1(Double absMonth1) {
		this.absMonth1 = absMonth1;
	}
	public Double getAbsDay1() {
		return absDay1;
	}
	public void setAbsDay1(Double absDay1) {
		this.absDay1 = absDay1;
	}
	public Double getAbsYear2() {
		return absYear2;
	}
	public void setAbsYear2(Double absYear2) {
		this.absYear2 = absYear2;
	}
	public Double getAbsMonth2() {
		return absMonth2;
	}
	public void setAbsMonth2(Double absMonth2) {
		this.absMonth2 = absMonth2;
	}
	public Double getAbsDay2() {
		return absDay2;
	}
	public void setAbsDay2(Double absDay2) {
		this.absDay2 = absDay2;
	}
	public Date getStartDateQut() {
		return startDateQut;
	}
	public void setStartDateQut(Date startDateQut) {
		this.startDateQut = startDateQut;
	}
	public Date getEndDateQut() {
		return endDateQut;
	}
	public void setEndDateQut(Date endDateQut) {
		this.endDateQut = endDateQut;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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
	public String getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
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
	public String getApproveClose() {
		return approveClose;
	}
	public void setApproveClose(String approveClose) {
		this.approveClose = approveClose;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
