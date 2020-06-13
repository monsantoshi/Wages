package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgEmployeeVO implements Serializable {
	private Double year;
	private Double period;
	private String empCode;
	private String preSuffCode;
	private String preName;
	private String firstName;
	private String lastName;
	private String name;
	private String positionShort;
	private String orgCode;
	private String orgDesc;
	private Double codeSeqAct;
	private String empStatus;
	private String payStatus;
	private String marriedStatus;
	private Date pDate;
	
	private Double codeSeqWork;
	private String flagWork;
	private String otType;
	private String refNo;
	private String flagStatus;

	

	public FeeWgEmployeeVO() {

	}



	public FeeWgEmployeeVO(Double year, Double period, String empCode,
			String preSuffCode, String preName, String firstName,
			String lastName, String name, String positionShort, String orgCode,
			String orgDesc, Double codeSeqAct, String empStatus,
			String payStatus, String marriedStatus, Date pDate,
			Double codeSeqWork, String flagWork, String otType, String refNo,
			String flagStatus) {
		//super();
		this.year = year;
		this.period = period;
		this.empCode = empCode;
		this.preSuffCode = preSuffCode;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
		this.positionShort = positionShort;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.codeSeqAct = codeSeqAct;
		this.empStatus = empStatus;
		this.payStatus = payStatus;
		this.marriedStatus = marriedStatus;
		this.pDate = pDate;
		this.codeSeqWork = codeSeqWork;
		this.flagWork = flagWork;
		this.otType = otType;
		this.refNo = refNo;
		this.flagStatus = flagStatus;
	}



	public Double getYear() {
		return year;
	}



	public void setYear(Double year) {
		this.year = year;
	}



	public Double getPeriod() {
		return period;
	}



	public void setPeriod(Double period) {
		this.period = period;
	}



	public String getEmpCode() {
		return empCode;
	}



	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}



	public String getPreSuffCode() {
		return preSuffCode;
	}



	public void setPreSuffCode(String preSuffCode) {
		this.preSuffCode = preSuffCode;
	}



	public String getPreName() {
		return preName;
	}



	public void setPreName(String preName) {
		this.preName = preName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPositionShort() {
		return positionShort;
	}



	public void setPositionShort(String positionShort) {
		this.positionShort = positionShort;
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



	public Double getCodeSeqAct() {
		return codeSeqAct;
	}



	public void setCodeSeqAct(Double codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}



	public String getEmpStatus() {
		return empStatus;
	}



	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}



	public String getPayStatus() {
		return payStatus;
	}



	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}



	public String getMarriedStatus() {
		return marriedStatus;
	}



	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}



	public Date getpDate() {
		return pDate;
	}



	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}



	public Double getCodeSeqWork() {
		return codeSeqWork;
	}



	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}



	public String getFlagWork() {
		return flagWork;
	}



	public void setFlagWork(String flagWork) {
		this.flagWork = flagWork;
	}



	public String getOtType() {
		return otType;
	}



	public void setOtType(String otType) {
		this.otType = otType;
	}



	public String getRefNo() {
		return refNo;
	}



	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}



	public String getFlagStatus() {
		return flagStatus;
	}



	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}



	
	

}
