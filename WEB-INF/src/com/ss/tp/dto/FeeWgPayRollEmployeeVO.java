package com.ss.tp.dto;

import java.io.Serializable;

public class FeeWgPayRollEmployeeVO implements Serializable {
	private String empCode;
	private String name;
	private String positionShort;
	private String orgCode;
	private String orgDesc;
	private Double codeSeqWork;
	private String flagWork;
	private String otType;
	private String refNo;
	private String flagStatus;

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getOtType() {
		return otType;
	}

	public void setOtType(String otType) {
		this.otType = otType;
	}

	public FeeWgPayRollEmployeeVO() {

	}

	public FeeWgPayRollEmployeeVO(String empCode, String name, String positionShort,
			String orgCode, String orgDesc, Double codeSeqWork,
			String flagWork, String otType, String refNo) {
		this.empCode = empCode;
		this.name = name;
		this.positionShort = positionShort;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.codeSeqWork = codeSeqWork;
		this.flagWork = flagWork;
		this.otType = otType;
		this.refNo = refNo;
	}

	public Double getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Double codeSeqWork) {
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
