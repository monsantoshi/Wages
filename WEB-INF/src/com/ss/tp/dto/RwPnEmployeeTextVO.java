package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

import com.ss.tp.model.DbPreSuff;

import com.ss.tp.model.PnOrganization;

public class RwPnEmployeeTextVO implements Serializable {
	private String ouCode;
	private String empCode;
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
	private String flagStatus;
	private String engName;
	private String engLastname;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private PnOrganization refPnOrganization;
	private PnOrganization refPnOrganizationWork;
	private DbPreSuff refDbPreSuff;

	public RwPnEmployeeTextVO(String ou, String emp, String account,
			String code, Long seq, Long act, String name, String code2,
			Date pdate, String name2, String code3, String code4, String name3,
			String fStatus, String eName, String eLName, String by, Date date,
			String by2, Date date2, DbPreSuff suff,
			PnOrganization organization, PnOrganization orgWork) {
		// super();
		// TODO Auto-generated constructor stub
		ouCode = ou;
		empCode = emp;
		this.account = account;
		adminCode = code;
		codeSeq = seq;
		codeSeqAct = act;
		firstName = name;
		gworkCode = code2;
		this.pdate = pdate;
		lastName = name2;
		levelCode = code3;
		positionCode = code4;
		preName = name3;
		this.flagStatus = fStatus;
		this.engName = eName;
		this.engLastname = eLName;
		this.creBy = by;
		this.creDate = date;
		this.updBy = by2;
		this.updDate = date2;
		refDbPreSuff = suff;
		refPnOrganization = organization;
		refPnOrganizationWork = orgWork;
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

	public RwPnEmployeeTextVO() {
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

	public String getPreName() {
		return preName;
	}

	public String getFlagStatus() {
		return flagStatus;
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

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public DbPreSuff getRefDbPreSuff() {
		return refDbPreSuff;
	}

	public void setRefDbPreSuff(DbPreSuff refDbPreSuff) {
		this.refDbPreSuff = refDbPreSuff;
	}

	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}

	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public void setPdate(Date idate) {
		this.pdate = idate;
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

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public PnOrganization getRefPnOrganizationWork() {
		return refPnOrganizationWork;
	}

	public void setRefPnOrganizationWork(PnOrganization refPnOrganizationWork) {
		this.refPnOrganizationWork = refPnOrganizationWork;
	}
}
