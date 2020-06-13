package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VFeeWpayRwVinai2 implements Serializable {
	private VFeeWpayRwVinai2PK pk;
	private String empName;
	private String orgDesc;
	private String year1;
	private Double decDay;
	private String flagPr;
	private String remark;
	
	public VFeeWpayRwVinai2() {
	}

	public VFeeWpayRwVinai2(VFeeWpayRwVinai2PK pk, String empName,
			String orgDesc, String year1, Double decDay, String flagPr,
			String remark) {
	
		this.pk = pk;
		this.empName = empName;
		this.orgDesc = orgDesc;
		this.year1 = year1;
		this.decDay = decDay;
		this.flagPr = flagPr;
		this.remark = remark;
	}

	public VFeeWpayRwVinai2PK getPk() {
		return pk;
	}

	public void setPk(VFeeWpayRwVinai2PK pk) {
		this.pk = pk;
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
