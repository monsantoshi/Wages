package com.ss.tp.dto;

import java.io.Serializable;

import java.util.Date;


public class ApEmployeeVO implements Serializable {
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
	private String noGroup;
	private String account;
	private String newPosition;
	private String newSec;
	private String newWork;
	private String newDiv;
	private String newNoGroup;

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
	private Double totAmt;
	private String approveFlag;
	private String bankFlag;
	private Date bankDate;
	private Date transferDate;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public ApEmployeeVO() {

	}

	public ApEmployeeVO(Long keySeq, String ouCode, Double yearPn,
			Double monthPn, String volumeSet, String empCode, String name,
			String oldPosition, String oldLevel, String oldSec, String oldWork,
			String oldDiv, String noGroup, String account, String newPosition,
			String newSec, String newWork, String newDiv, String newNoGroup,
			String oldPositionShort, Long codeSeq, String oldDuty,
			String newOldDuty, String newPositionCode, String newLevelCode,
			String newGworkCode, String newDuty, String newOrgCode,
			Long newCodeSeq, String newOrgDesc, String orgCode, String orgDesc,
			Double seqData,Double totAmt,String approveFlag,String bankFlag,Date bankDate,Date transferDate, String creBy, String updBy, Date creDate,
			Date updDate) {

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
		this.noGroup = noGroup;
		this.account = account;
		this.newPosition = newPosition;
		this.newSec = newSec;
		this.newWork = newWork;
		this.newDiv = newDiv;
		this.newNoGroup = newNoGroup;
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
		this.totAmt = totAmt;
		this.approveFlag = approveFlag;
		this.bankFlag = bankFlag;
		this.bankDate = bankDate;
		this.transferDate = transferDate;
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

	public String getNoGroup() {
		return noGroup;
	}

	public void setNoGroup(String noGroup) {
		this.noGroup = noGroup;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNewPosition() {
		return newPosition;
	}

	public void setNewPosition(String newPosition) {
		this.newPosition = newPosition;
	}

	public String getNewSec() {
		return newSec;
	}

	public void setNewSec(String newSec) {
		this.newSec = newSec;
	}

	public String getNewWork() {
		return newWork;
	}

	public void setNewWork(String newWork) {
		this.newWork = newWork;
	}

	public String getNewDiv() {
		return newDiv;
	}

	public void setNewDiv(String newDiv) {
		this.newDiv = newDiv;
	}

	public String getNewNoGroup() {
		return newNoGroup;
	}

	public void setNewNoGroup(String newNoGroup) {
		this.newNoGroup = newNoGroup;
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

	public Double getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(Double totAmt) {
		this.totAmt = totAmt;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}

	public Date getBankDate() {
		return bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
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
