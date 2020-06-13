package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WePnPromoteInstVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPn;
	private Double monthPn;
	private String volumeSet;
	private String empCode;
	private String name;
	private String oldPosition;
	private String oldLevel;
	private String oldPositionShort;
	private Long codeSeq;
	private String oldDuty;
	private String newOldDuty;
	private String newPositionCode;
	private String newLevelCode;
	private String newGworkCode;
	private String newDuty;
	private String newOrgCode;
	private Long newCodeSeq;
	private String newOrgDesc;
	private String orgCode;
	private String orgDesc;

	private Double seqData;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WePnPromoteInstVO() {

	}

	public WePnPromoteInstVO(Long keySeq, String ouCode, Double yearPn,
			Double monthPn, String volumeSet, String empCode, String name,
			String oldPosition, String oldLevel, String oldPositionShort,
			Long codeSeq, String oldDuty, String newOldDuty,
			String newPositionCode, String newLevelCode, String newGworkCode,
			String newDuty, String newOrgCode, Long newCodeSeq,
			String newOrgDesc, String orgCode, String orgDesc, Double seqData,
			String creBy, String updBy, Date creDate, Date updDate) {

		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPn = yearPn;
		this.monthPn = monthPn;
		this.volumeSet = volumeSet;
		this.empCode = empCode;
		this.name = name;
		this.oldPosition = oldPosition;
		this.oldLevel = oldLevel;
		this.oldPositionShort = oldPositionShort;
		this.codeSeq = codeSeq;
		this.oldDuty = oldDuty;
		this.newOldDuty = newOldDuty;
		this.newPositionCode = newPositionCode;
		this.newLevelCode = newLevelCode;
		this.newGworkCode = newGworkCode;
		this.newDuty = newDuty;
		this.newOrgCode = newOrgCode;
		this.newCodeSeq = newCodeSeq;
		this.newOrgDesc = newOrgDesc;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.seqData = seqData;
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

	public String getOldDuty() {
		return oldDuty;
	}

	public void setOldDuty(String oldDuty) {
		this.oldDuty = oldDuty;
	}

	public String getNewOldDuty() {
		return newOldDuty;
	}

	public void setNewOldDuty(String newOldDuty) {
		this.newOldDuty = newOldDuty;
	}

	public String getNewPositionCode() {
		return newPositionCode;
	}

	public void setNewPositionCode(String newPositionCode) {
		this.newPositionCode = newPositionCode;
	}

	public String getNewLevelCode() {
		return newLevelCode;
	}

	public void setNewLevelCode(String newLevelCode) {
		this.newLevelCode = newLevelCode;
	}

	public String getNewGworkCode() {
		return newGworkCode;
	}

	public void setNewGworkCode(String newGworkCode) {
		this.newGworkCode = newGworkCode;
	}

	public String getNewDuty() {
		return newDuty;
	}

	public void setNewDuty(String newDuty) {
		this.newDuty = newDuty;
	}

	public String getNewOrgCode() {
		return newOrgCode;
	}

	public void setNewOrgCode(String newOrgCode) {
		this.newOrgCode = newOrgCode;
	}

	public Long getNewCodeSeq() {
		return newCodeSeq;
	}

	public void setNewCodeSeq(Long newCodeSeq) {
		this.newCodeSeq = newCodeSeq;
	}

	public String getNewOrgDesc() {
		return newOrgDesc;
	}

	public void setNewOrgDesc(String newOrgDesc) {
		this.newOrgDesc = newOrgDesc;
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

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
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
