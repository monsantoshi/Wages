package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgEmpDataStatus implements Serializable {
	private WgEmpDataStatusPK pk;
	private Date statusDate;
	private Long hisSeq;
	private Long codeSeq;
	private Long codeSeqAct;
	private Long hisSeqAct;
	private String preName;
	private String firstName;
	private String lastName;
	private String account;
	private Long salary;
	private String payFlag;
	private String empStatus;
	private Date idate;
	private Date pdate;
	private String gworkCode;
	private String levelCode;
	private Date levelDate;
	private String adminCode;
	private Date adminDate;
	private String retireStatus;
	private PnOrganization refPnOrganization;
	private DbPreSuff refDbPreSuff;

	public WgEmpDataStatus() {

	}

	public WgEmpDataStatus(WgEmpDataStatusPK pk, Date statusDate, Long hisSeq,
			Long codeSeq, Long codeSeqAct, Long hisSeqAct, String preName,
			String firstName, String lastName, String account, Long salary,
			String payFlag, String empStatus, Date idate, Date pdate,
			String gworkCode, String levelCode, Date levelDate,
			String adminCode, Date adminDate, String retireStatus,
			PnOrganization refPnOrganization, DbPreSuff refDbPreSuff) {
		this.pk = pk;
		this.statusDate = statusDate;
		this.hisSeq = hisSeq;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.hisSeqAct = hisSeqAct;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.salary = salary;
		this.payFlag = payFlag;
		this.empStatus = empStatus;
		this.idate = idate;
		this.pdate = pdate;
		this.gworkCode = gworkCode;
		this.levelCode = levelCode;
		this.levelDate = levelDate;
		this.adminCode = adminCode;
		this.adminDate = adminDate;
		this.retireStatus = retireStatus;
		this.refPnOrganization = refPnOrganization;
		this.refDbPreSuff = refDbPreSuff;
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

	public Date getAdminDate() {
		return adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
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

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public Long getHisSeq() {
		return hisSeq;
	}

	public void setHisSeq(Long hisSeq) {
		this.hisSeq = hisSeq;
	}

	public Long getHisSeqAct() {
		return hisSeqAct;
	}

	public void setHisSeqAct(Long hisSeqAct) {
		this.hisSeqAct = hisSeqAct;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
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

	public Date getLevelDate() {
		return levelDate;
	}

	public void setLevelDate(Date levelDate) {
		this.levelDate = levelDate;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public WgEmpDataStatusPK getPk() {
		return pk;
	}

	public void setPk(WgEmpDataStatusPK pk) {
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

	public String getRetireStatus() {
		return retireStatus;
	}

	public void setRetireStatus(String retireStatus) {
		this.retireStatus = retireStatus;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

}
