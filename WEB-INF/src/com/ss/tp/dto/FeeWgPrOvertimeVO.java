package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPrOvertimeVO implements Serializable {

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

	// Report Part
	private String divCode;
	private String secCode;
	private String divDesc;
	private String secDesc;
	private String firstName;
	private String lastName;
	private String prefixName;

	private String orgCode;
	private String orgDesc;
	private String yearPeriod;
	private String startDateTemp;
	private String endDateTemp;

	public FeeWgPrOvertimeVO() {
	}

	public FeeWgPrOvertimeVO(Long keySeq, String ouCode, Double yearPr,
			Double periodPr, String empCode, String otType, String refNo,
			String flagWork, String flagPr, String creBy, String updBy,
			String confirmFlag, Double yearWork, Double periodWork,
			Long codeSeq, Double fiscalYear, Double totDay1, Double totDay15,
			Double totDay3, Double amtDay1, Double amtDay15, Double amtDay3,
			Double seqData, Date startDate, Date endDate, Date creDate,
			Date updDate, String divCode, String secCode, String divDesc,
			String secDesc, String firstName, String lastName,
			String prefixName, String orgCode, String orgDesc,
			String yearPeriod, String startDateTemp, String endDateTemp) {
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
		this.empCode = empCode;
		this.otType = otType;
		this.refNo = refNo;
		this.flagWork = flagWork;
		this.flagPr = flagPr;
		this.creBy = creBy;
		this.updBy = updBy;
		this.confirmFlag = confirmFlag;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.codeSeq = codeSeq;
		this.fiscalYear = fiscalYear;
		this.totDay1 = totDay1;
		this.totDay15 = totDay15;
		this.totDay3 = totDay3;
		this.amtDay1 = amtDay1;
		this.amtDay15 = amtDay15;
		this.amtDay3 = amtDay3;
		this.seqData = seqData;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creDate = creDate;
		this.updDate = updDate;
		this.divCode = divCode;
		this.secCode = secCode;
		this.divDesc = divDesc;
		this.secDesc = secDesc;
		this.firstName = firstName;
		this.lastName = lastName;
		this.prefixName = prefixName;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.yearPeriod = yearPeriod;
		this.startDateTemp = startDateTemp;
		this.endDateTemp = endDateTemp;
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

	public String getEndDateTemp() {
		return endDateTemp;
	}

	public void setEndDateTemp(String endDateTemp) {
		this.endDateTemp = endDateTemp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
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

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
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

	public String getStartDateTemp() {
		return startDateTemp;
	}

	public void setStartDateTemp(String startDateTemp) {
		this.startDateTemp = startDateTemp;
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

	public String getYearPeriod() {
		return yearPeriod;
	}

	public void setYearPeriod(String yearPeriod) {
		this.yearPeriod = yearPeriod;
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

}
