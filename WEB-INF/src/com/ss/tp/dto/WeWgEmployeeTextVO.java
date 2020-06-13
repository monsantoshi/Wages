package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WeWgEmployeeTextVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private String empCode;
	private String name;
	private Date statusDate;
	private Long codeSeq;
	private Long codeSeqAct;
	private String oldPositionShort;
	private String preName;
	private String engName;
	private String engLastname;
	private String empLevel;
	private String positionShort;
	private String pnTransfer;
	private String orgDesc;

	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	/*
	 * private String divCode; private String divDesc; private String divShort;
	 * private String secCode; private String secDesc; private String
	 * orgWorkCode;// name same TaMonthEmpWork that is workCode private String
	 * orgWorkDesc;// name same TaMonthEmpWork that is workDesc private String
	 * areaCode; private String areaDesc;
	 * 
	 * private String fullName;
	 */

	public WeWgEmployeeTextVO() {

	}

	public WeWgEmployeeTextVO(Long keySeq, String ouCode, String empCode,
			String name, Date statusDate, Long codeSeq, Long codeSeqAct,
			String oldPositionShort, String preName, String engName,
			String engLastname, String empLevel, String positionShort,
			String pnTransfer, String orgDesc, String creBy, Date creDate,
			String updBy, Date updDate) {
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.name = name;
		this.statusDate = statusDate;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.oldPositionShort = oldPositionShort;
		this.preName = preName;
		this.engName = engName;
		this.engLastname = engLastname;
		this.empLevel = empLevel;
		this.positionShort = positionShort;
		this.pnTransfer = pnTransfer;
		this.orgDesc = orgDesc;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	public String getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}

	public String getPositionShort() {
		return positionShort;
	}

	public void setPositionShort(String positionShort) {
		this.positionShort = positionShort;
	}

	public String getOldPositionShort() {
		return oldPositionShort;
	}

	public void setOldPositionShort(String oldPositionShort) {
		this.oldPositionShort = oldPositionShort;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Long getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(Long codeSeqAct) {
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

	public String getPnTransfer() {
		return pnTransfer;
	}

	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/*
	 * public String getDivCode() { return divCode; }
	 * 
	 * public void setDivCode(String divCode) { this.divCode = divCode; }
	 * 
	 * public String getDivDesc() { return divDesc; }
	 * 
	 * public void setDivDesc(String divDesc) { this.divDesc = divDesc; }
	 * 
	 * public String getDivShort() { return divShort; }
	 * 
	 * public void setDivShort(String divShort) { this.divShort = divShort; }
	 * 
	 * public String getSecCode() { return secCode; }
	 * 
	 * public void setSecCode(String secCode) { this.secCode = secCode; }
	 * 
	 * public String getSecDesc() { return secDesc; }
	 * 
	 * public void setSecDesc(String secDesc) { this.secDesc = secDesc; }
	 * 
	 * public String getOrgWorkCode() { return orgWorkCode; }
	 * 
	 * public void setOrgWorkCode(String orgWorkCode) { this.orgWorkCode =
	 * orgWorkCode; }
	 * 
	 * public String getOrgWorkDesc() { return orgWorkDesc; }
	 * 
	 * public void setOrgWorkDesc(String orgWorkDesc) { this.orgWorkDesc =
	 * orgWorkDesc; }
	 * 
	 * public String getAreaCode() { return areaCode; }
	 * 
	 * public void setAreaCode(String areaCode) { this.areaCode = areaCode; }
	 * 
	 * public String getAreaDesc() { return areaDesc; }
	 * 
	 * public void setAreaDesc(String areaDesc) { this.areaDesc = areaDesc; }
	 * 
	 * public String getFullName() { return fullName; }
	 * 
	 * public void setFullName(String fullName) { this.fullName = fullName; }
	 */

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPreName() {
		return preName;
	}
}
