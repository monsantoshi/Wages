package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PnEmployeeText implements Serializable {
	private PnEmployeeTextPK pk;
	private PnEmployee emp;
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
	private Date statusDate;
	private Set pnEmployeeTextDetail;

	public PnEmployeeText() {

	}

	public PnEmployeeText(PnEmployeeTextPK pk, PnEmployee emp, String preName,
			String firstName, String lastName, String gworkCode,
			String positionCode, String empStatus, Long codeSeq,
			Long codeSeqAct, String levelCode, Date pdate, String account,
			String adminCode, String engName, String engLastname,
			String flagStatus, String creBy, Date creDate, String updBy,
			Date updDate, Date statusDate, Set pnEmployeeTextDetail) {
		this.pk = pk;
		this.emp = emp;
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
		this.statusDate = statusDate;
		this.pnEmployeeTextDetail = pnEmployeeTextDetail;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public PnEmployeeTextPK getPk() {
		return pk;
	}

	public void setPk(PnEmployeeTextPK pk) {
		this.pk = pk;
	}

	public PnEmployee getEmp() {
		return emp;
	}

	public void setEmp(PnEmployee emp) {
		this.emp = emp;
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

	public Set getPnEmployeeTextDetail() {
		return pnEmployeeTextDetail;
	}

	public void setPnEmployeeTextDetail(Set pnEmployeeTextDetail) {
		this.pnEmployeeTextDetail = pnEmployeeTextDetail;
	}

}
