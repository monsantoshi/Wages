package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PnEmployeeTextDetail implements Serializable {
	private PnEmployeeTextDetailPK pk;

	private String preName;
	private String firstName;
	private String lastName;
	private String gworkCode;
	private String positionCode;
	private String empStatus;
	private Long codeSeq;
	private Long codeSeqAct;
	private String levelCode;
	private Date pdate;
	private String account;
	private String adminCode;
	private String engName;
	private String engLastname;
	private String flagStatus;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private PnEmployeeText refPnEmployeeText;
	private PeEvaluationFormTitle refPeEvaluationFormTitle;

	public PnEmployeeTextDetail() {

	}

	public PnEmployeeTextDetail(PnEmployeeTextDetailPK pk, String preName,
			String firstName, String lastName, String gworkCode,
			String positionCode, String empStatus, Long codeSeq,
			Long codeSeqAct, String levelCode, Date pdate, String account,
			String adminCode, String engName, String engLastname,
			String flagStatus, String creBy, Date creDate, String updBy,
			Date updDate, PnEmployeeText refPnEmployeeText,
			PeEvaluationFormTitle refPeEvaluationFormTitle) {
		this.pk = pk;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gworkCode = gworkCode;
		this.positionCode = positionCode;
		this.empStatus = empStatus;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.levelCode = levelCode;
		this.pdate = pdate;
		this.account = account;
		this.adminCode = adminCode;
		this.engName = engName;
		this.engLastname = engLastname;
		this.flagStatus = flagStatus;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.refPnEmployeeText = refPnEmployeeText;
		this.refPeEvaluationFormTitle = refPeEvaluationFormTitle;
	}

	public PeEvaluationFormTitle getRefPeEvaluationFormTitle() {
		return refPeEvaluationFormTitle;
	}

	public void setRefPeEvaluationFormTitle(
			PeEvaluationFormTitle refPeEvaluationFormTitle) {
		this.refPeEvaluationFormTitle = refPeEvaluationFormTitle;
	}

	public PnEmployeeTextDetailPK getPk() {
		return pk;
	}

	public void setPk(PnEmployeeTextDetailPK pk) {
		this.pk = pk;
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

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
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

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
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

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
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

	public PnEmployeeText getRefPnEmployeeText() {
		return refPnEmployeeText;
	}

	public void setRefPnEmployeeText(PnEmployeeText refPnEmployeeText) {
		this.refPnEmployeeText = refPnEmployeeText;
	}

}
