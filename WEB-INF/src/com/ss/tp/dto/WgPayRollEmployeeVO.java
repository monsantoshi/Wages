package com.ss.tp.dto;

import java.io.Serializable;

public class WgPayRollEmployeeVO implements Serializable {
	private String empCode;
	private String name;
	private String positionShort;
	private String orgCode;
	private String orgDesc;
	private Double codeSeqWork;
	private String flagWork;
	private String dutyCode;
	private String dutyDesc;
	private String StatusText;

	public WgPayRollEmployeeVO() {

	}

	public WgPayRollEmployeeVO(String empCode, String name,
			String positionShort, String orgCode, String orgDesc,
			Double codeSeqWork, String flagWork, String dutyCode,
			String dutyDesc, String statusText) {
		super();
		this.empCode = empCode;
		this.name = name;
		this.positionShort = positionShort;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.codeSeqWork = codeSeqWork;
		this.flagWork = flagWork;
		this.dutyCode = dutyCode;
		this.dutyDesc = dutyDesc;
		StatusText = statusText;
	}

	public Double getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getDutyDesc() {
		return dutyDesc;
	}

	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFlagWork() {
		return flagWork;
	}

	public void setFlagWork(String flagWork) {
		this.flagWork = flagWork;
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

	public String getStatusText() {
		return StatusText;
	}

	public void setStatusText(String statusText) {
		StatusText = statusText;
	}

}
