package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgPrIncDecOther implements Serializable {

	private Long keySeq;
	private String ouCode;
	private Integer yearPr;
	private Integer periodPr;
	private String empCode;
	private String groupCode;
	private String incDecCode;
	private Integer yearWork;
	private Integer periodWork;
	private Integer codeSeq;
	private Double totalAmt;
	private String flagPr;
	private Integer seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
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

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
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

	public Integer getPeriodPr() {
		return periodPr;
	}

	public void setPeriodPr(Integer periodPr) {
		this.periodPr = periodPr;
	}

	public Integer getPeriodWork() {
		return periodWork;
	}

	public void setPeriodWork(Integer periodWork) {
		this.periodWork = periodWork;
	}

	public Integer getSeqData() {
		return seqData;
	}

	public void setSeqData(Integer seqData) {
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

	public Integer getYearPr() {
		return yearPr;
	}

	public void setYearPr(Integer yearPr) {
		this.yearPr = yearPr;
	}

	public Integer getYearWork() {
		return yearWork;
	}

	public void setYearWork(Integer yearWork) {
		this.yearWork = yearWork;
	}
}
