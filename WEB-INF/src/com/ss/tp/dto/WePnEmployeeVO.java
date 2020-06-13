package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WePnEmployeeVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPn;
	private Double monthPn;
	private String volumeSet;
	private String empCode;
	private String name;
	private String oldPosition;
	private String oldLevel;
	private String oldSec;
	private String oldWork;
	private String oldDiv;
	private String oldPositionShort;
	private Long codeSeq;
	private String orgDesc;
	private String oldEngName;
	private String oldEngLastname;
	private String engName;
	private String engLastname;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WePnEmployeeVO() {

	}

	public WePnEmployeeVO(Long keySeq, String ouCode, Double yearPn,
			Double monthPn, String volumeSet, String empCode, String name,
			String oldPosition, String oldLevel, String oldSec, String oldWork,
			String oldDiv, String oldPositionShort, Long codeSeq,
			String orgDesc, String oldEngName, String oldEngLastname,
			String engName, String engLastname, String creBy, String updBy,
			Date creDate, Date updDate) {
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPn = yearPn;
		this.monthPn = monthPn;
		this.volumeSet = volumeSet;
		this.empCode = empCode;
		this.name = name;
		this.oldPosition = oldPosition;
		this.oldLevel = oldLevel;
		this.oldSec = oldSec;
		this.oldWork = oldWork;
		this.oldDiv = oldDiv;
		this.oldPositionShort = oldPositionShort;
		this.codeSeq = codeSeq;
		this.orgDesc = orgDesc;
		this.oldEngName = oldEngName;
		this.oldEngLastname = oldEngLastname;
		this.engName = engName;
		this.engLastname = engLastname;
		this.creBy = creBy;
		this.updBy = updBy;
		this.creDate = creDate;
		this.updDate = updDate;
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

	public Double getYearPn() {
		return yearPn;
	}

	public void setYearPn(Double yearPn) {
		this.yearPn = yearPn;
	}

	public Double getMonthPn() {
		return monthPn;
	}

	public void setMonthPn(Double monthPn) {
		this.monthPn = monthPn;
	}

	public String getVolumeSet() {
		return volumeSet;
	}

	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
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

	public String getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(String oldPosition) {
		this.oldPosition = oldPosition;
	}

	public String getOldLevel() {
		return oldLevel;
	}

	public void setOldLevel(String oldLevel) {
		this.oldLevel = oldLevel;
	}

	public String getOldSec() {
		return oldSec;
	}

	public void setOldSec(String oldSec) {
		this.oldSec = oldSec;
	}

	public String getOldWork() {
		return oldWork;
	}

	public void setOldWork(String oldWork) {
		this.oldWork = oldWork;
	}

	public String getOldDiv() {
		return oldDiv;
	}

	public void setOldDiv(String oldDiv) {
		this.oldDiv = oldDiv;
	}

	public String getOldPositionShort() {
		return oldPositionShort;
	}

	public void setOldPositionShort(String oldPositionShort) {
		this.oldPositionShort = oldPositionShort;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOldEngName() {
		return oldEngName;
	}

	public void setOldEngName(String oldEngName) {
		this.oldEngName = oldEngName;
	}

	public String getOldEngLastname() {
		return oldEngLastname;
	}

	public void setOldEngLastname(String oldEngLastname) {
		this.oldEngLastname = oldEngLastname;
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

}