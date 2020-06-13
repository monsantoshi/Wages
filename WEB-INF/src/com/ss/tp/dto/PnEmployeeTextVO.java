package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PnEmployeeTextVO implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;
	private String engName;
	private String engLastname;
	private Long codeSeq;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String ename;
	private String flagStatus;
	private Double formScore;
	private String confirm;

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
	}

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public Double getFormScore() {
		return formScore;
	}

	public void setFormScore(Double formScore) {
		this.formScore = formScore;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}
