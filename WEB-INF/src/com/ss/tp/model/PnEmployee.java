package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PnEmployee implements Serializable {
	private PnEmployeePK pk;
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
	private PnOrganization refPnOrganization;
	private PnOrganization refPnOrganizationWork;
	private DbPreSuff refDbPreSuff;

	public PnEmployee(String account, String code, Long seq, Long act,
			String name, String code2, Date pdate, String name2, String code3,
			PnEmployeePK pk, String code4, String name3, String eName,
			String eLName, DbPreSuff suff, PnOrganization organization,
			PnOrganization orgWork) {
		super();
		// TODO Auto-generated constructor stub
		this.account = account;
		adminCode = code;
		codeSeq = seq;
		codeSeqAct = act;
		firstName = name;
		gworkCode = code2;
		this.pdate = pdate;
		lastName = name2;
		levelCode = code3;
		this.pk = pk;
		positionCode = code4;
		preName = name3;
		engName = eName;
		engLastname = eLName;
		refDbPreSuff = suff;
		refPnOrganization = organization;
		refPnOrganizationWork = orgWork;
	}

	public PnEmployee() {
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

	public PnEmployeePK getPk() {
		return pk;
	}

	public void setPk(PnEmployeePK pk) {
		this.pk = pk;
	}

	public String getPreName() {
		return preName;
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
