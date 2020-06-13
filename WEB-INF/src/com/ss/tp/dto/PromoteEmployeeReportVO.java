package com.ss.tp.dto;

import java.io.Serializable;

public class PromoteEmployeeReportVO implements Serializable {
	private String empCode;
	private String name;
	private String positionShort;
	private String orgCode;
	private String orgDesc;

	public PromoteEmployeeReportVO() {

	}

	public PromoteEmployeeReportVO(String empCode, String name,
			String positionShort, String orgCode, String orgDesc) {
		this.empCode = empCode;
		this.name = name;
		this.positionShort = positionShort;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;

	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPositionShort() {
		return positionShort;
	}

	public void setPositionShort(String positionShort) {
		this.positionShort = positionShort;
	}

}
