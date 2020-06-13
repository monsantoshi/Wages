package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WePnPromoteInst implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPn;
	private Double monthPn;
	private String volumeSet;
	private String empCode;
	private Long codeSeq;
	private String newOldDuty;
	private String newPositionCode;
	private String newLevelCode;
	private String newGworkCode;
	private String newDuty;
	private String newOrgCode;
	private Long newCodeSeq;

	private Double seqData;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WePnPromoteInst() {

	}

	public WePnPromoteInst(Long keySeq, String ouCode, Double yearPn,
			Double monthPn, String volumeSet, String empCode, Long codeSeq,
			String newOldDuty, String newPositionCode, String newLevelCode,
			String newGworkCode, String newDuty, String newOrgCode,
			Long newCodeSeq, Double seqData, String creBy, String updBy,
			Date creDate, Date updDate) {

		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.yearPn = yearPn;
		this.monthPn = monthPn;
		this.volumeSet = volumeSet;
		this.empCode = empCode;
		this.codeSeq = codeSeq;
		this.newOldDuty = newOldDuty;
		this.newPositionCode = newPositionCode;
		this.newLevelCode = newLevelCode;
		this.newGworkCode = newGworkCode;
		this.newDuty = newDuty;
		this.newOrgCode = newOrgCode;
		this.newCodeSeq = newCodeSeq;
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

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
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
