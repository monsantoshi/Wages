package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class VFeeWpayRwVinai2VO implements Serializable {
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;
	private String empName;
	private String orgDesc;
    private String year1;
	private Double decDay;
	private String flagPr;
	private String remark;
	
	
	public VFeeWpayRwVinai2VO() {
		
	}


	public VFeeWpayRwVinai2VO(String ouCode, Double yearPr, Double periodPr,
			String empCode, String empName, String orgDesc, String year1,
			Double decDay, String flagPr, String remark) {
		
		this.ouCode = ouCode;
		this.yearPr = yearPr;
		this.periodPr = periodPr;
		this.empCode = empCode;
		this.empName = empName;
		this.orgDesc = orgDesc;
		this.year1 = year1;
		this.decDay = decDay;
		this.flagPr = flagPr;
		this.remark = remark;
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


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getOrgDesc() {
		return orgDesc;
	}


	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}


	public String getYear1() {
		return year1;
	}


	public void setYear1(String year1) {
		this.year1 = year1;
	}


	public Double getDecDay() {
		return decDay;
	}


	public void setDecDay(Double decDay) {
		this.decDay = decDay;
	}


	public String getFlagPr() {
		return flagPr;
	}


	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}



}
