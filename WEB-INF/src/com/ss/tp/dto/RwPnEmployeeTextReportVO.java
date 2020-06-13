package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class RwPnEmployeeTextReportVO implements Serializable {

	private String ouCode;
	private Double year;
	private Double period;
	private String incDecCode;
	private String incDecName;
	private String empCode;
	private String empName;
	private String orgDesc;
	private Double totalAmt;
	private String flagPr;
	private Date stDate;
	private Date endDate;
	private String yearPeriod;

	public String getYearPeriod() {
		return yearPeriod;
	}

	public void setYearPeriod(String yearPeriod) {
		this.yearPeriod = yearPeriod;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public String getIncDecCode() {
		return incDecCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

}
