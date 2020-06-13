package com.ss.tp.dto;

import java.io.Serializable;

public class FwIncdecEmployeeVO implements Serializable {
	private String empCode;
	private String name;
	private String positionShort;
	private String orgCode;
	private String orgDesc;
	private Integer codeSeqWork;
	private String flagWork;
	private String dutyName;

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public FwIncdecEmployeeVO() {

	}

	public FwIncdecEmployeeVO(String empCode, String name,
			String positionShort, String orgCode, String orgDesc,
			Integer codeSeqWork, String flagWork, String dutyName) {
		this.empCode = empCode;
		this.name = name;
		this.positionShort = positionShort;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.codeSeqWork = codeSeqWork;
		this.flagWork = flagWork;
		this.dutyName = dutyName;
	}

	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Integer codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String isFlagWork() {
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

	public String getFlagWork() {
		return flagWork;
	}

}
