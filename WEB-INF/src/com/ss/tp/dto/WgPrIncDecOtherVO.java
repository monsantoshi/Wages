package com.ss.tp.dto;

import java.util.Date;

public class WgPrIncDecOtherVO {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String groupCode;

	private String incDecCode;
	private Integer yearWork;
	private Integer periodWork;
	private Integer codeSeq;
	private String incDecName;

	private Double totalAmt;

	private String taxStatus;
	private String empCode;
	private String empName;
	private String orgDesc;
	private String flagPr;

	private Integer seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public String getEmpCode() {
		return empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public String getIncDecCode() {
		return incDecCode;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public String getOuCode() {
		return ouCode;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public WgPrIncDecOtherVO() {
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

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public Double getPeriodPr() {
		return periodPr;
	}

	public void setPeriodPr(Double periodPr) {
		this.periodPr = periodPr;
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

	public WgPrIncDecOtherVO(Integer codeSeq, String flag, String by,
			Date date, String code, String name, String pr, String code2,
			String code3, String name2, Long seq2, String desc, String code4,
			Double pr2, Integer work, Integer data, String status, Double amt,
			String by2, Date date2, Double pr3, Integer work2) {
		super();
		// TODO Auto-generated constructor stub
		this.codeSeq = codeSeq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.empName = name;
		this.flagPr = pr;
		this.groupCode = code2;
		this.incDecCode = code3;
		this.incDecName = name2;
		this.keySeq = seq2;
		this.orgDesc = desc;
		this.ouCode = code4;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.taxStatus = status;
		this.totalAmt = amt;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
	}

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
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

	public Integer getYearWork() {
		return yearWork;
	}

	public void setYearWork(Integer yearWork) {
		this.yearWork = yearWork;
	}

}
