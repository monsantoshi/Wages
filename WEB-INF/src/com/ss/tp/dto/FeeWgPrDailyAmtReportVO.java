package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPrDailyAmtReportVO implements Serializable {

	private Integer keySeq;
	private String ouCode;
	private String orgCode;
	private String orgDesc;
	private String divCode;
	private Long divSeq;
	private String divDesc;
	private String divShort;
	private String areaCode;
	private Long areaSeq;
	private String areaDesc;
	
	private String secCode;
	private Long secSeq;
	private String secDesc;
	private String workCode;
	private Long workSeq;
	private String workDesc;
	
	private Double yearPr;
	private Double periodPr;
	private String memberNo;
	private String empCode;
	private String fullName;
	private String flagPr;
	
	private Integer codeSeq;
	private String incDecCode;
	private String incDecName;
	private String subCode;
	
	private Double sumN;
	private Double sumA;
	private Double sumR;
	private Double sumB;
	private Double sumS;
	private Double totAmt;
	private Date stDate;
	private Date endDate;
	private String yearPeriod;

	
	public Integer getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(Integer keySeq) {
		this.keySeq = keySeq;
	}
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
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
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public Long getDivSeq() {
		return divSeq;
	}
	public void setDivSeq(Long divSeq) {
		this.divSeq = divSeq;
	}
	public String getDivDesc() {
		return divDesc;
	}
	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}
	public String getDivShort() {
		return divShort;
	}
	public void setDivShort(String divShort) {
		this.divShort = divShort;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Long getAreaSeq() {
		return areaSeq;
	}
	public void setAreaSeq(Long areaSeq) {
		this.areaSeq = areaSeq;
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public Long getSecSeq() {
		return secSeq;
	}
	public void setSecSeq(Long secSeq) {
		this.secSeq = secSeq;
	}
	public String getSecDesc() {
		return secDesc;
	}
	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public Long getWorkSeq() {
		return workSeq;
	}
	public void setWorkSeq(Long workSeq) {
		this.workSeq = workSeq;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
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
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getIncDecCode() {
		return incDecCode;
	}
	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getIncDecName() {
		return incDecName;
	}
	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}
	public String getFlagPr() {
		return flagPr;
	}
	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}
	public Double getSumN() {
		return sumN;
	}
	public void setSumN(Double sumN) {
		this.sumN = sumN;
	}
	public Double getSumA() {
		return sumA;
	}
	public void setSumA(Double sumA) {
		this.sumA = sumA;
	}
	public Double getSumR() {
		return sumR;
	}
	public void setSumR(Double sumR) {
		this.sumR = sumR;
	}
	public Double getSumB() {
		return sumB;
	}
	public void setSumB(Double sumB) {
		this.sumB = sumB;
	}
	public Double getSumS() {
		return sumS;
	}
	public void setSumS(Double sumS) {
		this.sumS = sumS;
	}
	public String getYearPeriod() {
		return yearPeriod;
	}
	public void setYearPeriod(String yearPeriod) {
		this.yearPeriod = yearPeriod;
	}
	public Double getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(Double totAmt) {
		this.totAmt = totAmt;
	}
	public Date getStDate() {
		return stDate;
	}
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
