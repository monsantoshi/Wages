package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class RwIncDecEmployeeVO implements Serializable {
	private Long keySeq;
	private String flagPr;
	private String empCode;
	private String name;
	private Double yearWork;
	private Double periodWork;
	private Double totalAmt;
	private Double seqData;
	private Long codeSeq;
	private Date startDate;
	private Date endDate;
	private String approveFlag;
	private String orgDesc;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public RwIncDecEmployeeVO() {

	}

	public RwIncDecEmployeeVO(Long keySeq, String flagPr, String empCode,
			String name, Double yearWork, Double periodWork, Double totalAmt,
			Double seqData,String appF,String org) {
		this.keySeq = keySeq;
		this.flagPr = flagPr;
		this.empCode = empCode;
		this.name = name;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.totalAmt = totalAmt;
		this.seqData = seqData;
		this.approveFlag = appF;
		this.orgDesc = org;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getYearWork() {
		return yearWork;
	}

	public void setYearWork(Double yearWork) {
		this.yearWork = yearWork;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
}
