package com.ss.tp.dto;

import java.io.Serializable;

public class WgPrIncDecEmployeeVO implements Serializable {
	private Long keySeq;
	private String flagPr;
	private String empCode;
	private String name;
	private Integer yearWork;
	private Integer periodWork;
	private Double totalAmt;
	private Integer seqData;
	private Integer codeSeq;

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}

	public WgPrIncDecEmployeeVO() {

	}

	public WgPrIncDecEmployeeVO(Long keySeq, String flagPr, String empCode,
			String name, Integer yearWork, Integer periodWork, Double totalAmt,
			Integer seqData) {
		this.keySeq = keySeq;
		this.flagPr = flagPr;
		this.empCode = empCode;
		this.name = name;
		this.yearWork = yearWork;
		this.periodWork = periodWork;
		this.totalAmt = totalAmt;
		this.seqData = seqData;
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

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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
