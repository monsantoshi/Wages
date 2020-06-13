package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayRwOvertime implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;
	private String otType;
	private String refNo;
	private String flagWork;
	private String flagPr;
	private String creBy;
	private String updBy;
	private String confirmFlag;
	private String approveBy;
	private Date approveDate;
	private String approveFlag;
	private String approveClose;
	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;
	private Double fiscalYear;
	private Double totDay1;
	private Double totDay15;
	private Double totDay3;
	private Double amtDay1;
	private Double amtDay15;
	private Double amtDay3;
	private Double seqData;

	private Date startDate;
	private Date endDate;
	private Date creDate;
	private Date updDate;
	private Double workHour;

	public FeeWpayRwOvertime() {

	}

	public FeeWpayRwOvertime(Double day1, Double day15, Double day3, Long seq,
			String flag, String by, Date date, String code, Date date2,
			Double year, String pr, String work, Long seq2, String type,
			String code2, Double pr2, Double work2, String no, Double data,
			Date date3, Double day12, Double day152, Double day32, String by2,
			Date date4, Double pr3, Double work3,Double workH, String appBy,Date appDate,String appFlag,String appClose) {

		// TODO Auto-generated constructor stub
		this.amtDay1 = day1;
		this.amtDay15 = day15;
		this.amtDay3 = day3;
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.endDate = date2;
		this.fiscalYear = year;
		this.flagPr = pr;
		this.flagWork = work;
		this.keySeq = seq2;
		this.otType = type;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work2;
		this.refNo = no;
		this.seqData = data;
		this.startDate = date3;
		this.totDay1 = day12;
		this.totDay15 = day152;
		this.totDay3 = day32;
		this.updBy = by2;
		this.updDate = date4;
		this.yearPr = pr3;
		this.yearWork = work3;
		this.workHour = workH;
		this.approveBy= appBy;
		this.approveDate=appDate;
		this.approveFlag=appFlag;
		this.approveClose =appClose;
	}

	public Double getAmtDay1() {
		return amtDay1;
	}

	public void setAmtDay1(Double amtDay1) {
		this.amtDay1 = amtDay1;
	}

	public Double getAmtDay15() {
		return amtDay15;
	}

	public void setAmtDay15(Double amtDay15) {
		this.amtDay15 = amtDay15;
	}

	public Double getAmtDay3() {
		return amtDay3;
	}

	public void setAmtDay3(Double amtDay3) {
		this.amtDay3 = amtDay3;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getFlagWork() {
		return flagWork;
	}

	public void setFlagWork(String flagWork) {
		this.flagWork = flagWork;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public String getOtType() {
		return otType;
	}

	public void setOtType(String otType) {
		this.otType = otType;
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

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getTotDay1() {
		return totDay1;
	}

	public void setTotDay1(Double totDay1) {
		this.totDay1 = totDay1;
	}

	public Double getTotDay15() {
		return totDay15;
	}

	public void setTotDay15(Double totDay15) {
		this.totDay15 = totDay15;
	}

	public Double getTotDay3() {
		return totDay3;
	}

	public void setTotDay3(Double totDay3) {
		this.totDay3 = totDay3;
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
     
	public Double getWorkHour(){
		return workHour;
	}
	public void setWorkHour(Double workHour) {
		this.workHour = workHour;
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
	
}
