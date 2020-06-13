package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WgEmpDataStatusVO implements Serializable {
	private String ouCode;
	private Long year;
	private Long month;
	private String empCode;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
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

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
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

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

}
