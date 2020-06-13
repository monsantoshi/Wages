package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPnEmployeeVO implements Serializable {
	private String keySeq;
	private Double year;
	private Double period;
	private String flagPn;
	
	private String ouCode;
	private String empCode;
	private String prefixName;
	private String preName;
	private String firstName;
	private String lastName;
	//private String fullName;
	private String marritedStatus;
	private String payStatus;
	
	private String empStatus;
	
	private String pDate;
	private String divShort;
	private String divDesc;
	private String secDesc;
	private String areaDesc;
	private String orgCode;
	private String orgDesc;
	private String orgActDesc;
	private Double codeSeq;
	private Double codeSeqAct;
	private String engName;
	private String engLastname;
	//private String engFullname;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private String fullName;
	private String engFullname;

	public FeeWgPnEmployeeVO() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public FeeWgPnEmployeeVO(String keySeq, Double year, Double period,
			String flagPn, String ouCode, String empCode, String prefixName,
			String preName, String firstName, String lastName,
			String marritedStatus, String payStatus, String empStatus,
			String pDate, String divShort, String divDesc, String secDesc,
			String areaDesc, String orgCode, String orgDesc, String orgActDesc,
			Double codeSeq, Double codeSeqAct, String engName,
			String engLastname, String creBy, String updBy, Date creDate,
			Date updDate, String fullName, String engFullname) {
		//super();
		this.keySeq = keySeq;
		this.year = year;
		this.period = period;
		this.flagPn = flagPn;
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.prefixName = prefixName;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marritedStatus = marritedStatus;
		this.payStatus = payStatus;
		this.empStatus = empStatus;
		this.pDate = pDate;
		this.divShort = divShort;
		this.divDesc = divDesc;
		this.secDesc = secDesc;
		this.areaDesc = areaDesc;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.orgActDesc = orgActDesc;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.engName = engName;
		this.engLastname = engLastname;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
		this.fullName = fullName;
		this.engFullname = engFullname;
	}

	public String getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
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

	public String getFlagPn() {
		return flagPn;
	}

	public void setFlagPn(String flagPn) {
		this.flagPn = flagPn;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
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

	public String getMarritedStatus() {
		return marritedStatus;
	}

	public void setMarritedStatus(String marritedStatus) {
		this.marritedStatus = marritedStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String getDivShort() {
		return divShort;
	}

	public void setDivShort(String divShort) {
		this.divShort = divShort;
	}

	public String getDivDesc() {
		return divDesc;
	}

	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
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

	public String getOrgActDesc() {
		return orgActDesc;
	}

	public void setOrgActDesc(String orgActDesc) {
		this.orgActDesc = orgActDesc;
	}

	public Double getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Double getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(Double codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getEngLastname() {
		return engLastname;
	}

	public void setEngLastname(String engLastname) {
		this.engLastname = engLastname;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEngFullname() {
		return engFullname;
	}

	public void setEngFullname(String engFullname) {
		this.engFullname = engFullname;
	}

	
	
}
