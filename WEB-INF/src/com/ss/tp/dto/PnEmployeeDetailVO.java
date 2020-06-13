package com.ss.tp.dto;

import java.io.Serializable;

public class PnEmployeeDetailVO implements Serializable {
	private String ouCode;
	private String empCode;
	private String prefixName;
	private String firstName;
	private String lastName;
	private String fullName;
	private String position;
	private String account;
	private String levelCode;
	private String adminDesc;
	private String pDate;
	private String divDesc;
	private String secDesc;
	private String orgCode;
	private String orgDesc;
	private String orgActDesc;
	private String codeSeq;
	private String codeSeqAct;
	private String engName;
	private String engLastname;
	private String engFullname;

	public PnEmployeeDetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAdminDesc() {
		return adminDesc;
	}

	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
	}

	public String getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(String codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}

	public String getDivDesc() {
		return divDesc;
	}

	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPDate() {
		return pDate;
	}

	public void setPDate(String date) {
		pDate = date;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getFullName() {
		return this.prefixName + " " + this.firstName + " " + this.lastName;
	}

	public String getOrgActDesc() {
		return orgActDesc;
	}

	public void setOrgActDesc(String orgActDesc) {
		this.orgActDesc = orgActDesc;
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

	public String getEngFullname() {
		return this.engName + " " + this.engLastname;
	}

}
