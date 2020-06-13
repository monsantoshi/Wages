package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WePnEmployeeText implements Serializable {
	private Long keySeq;
	private String ouCode;
	private String empCode;
	private Date statusDate;
	private Long codeSeq;
	private Long codeSeqAct;
	private String preName;
	private String engName;
	private String engLastname;
	private String empLevel;
	private String orgEmp;
	private String positionShort;

	private String pnTransfer;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	// private PnEmployee pnEmployee;

	public WePnEmployeeText() {

	}

	// public PnEmployee getPnEmployee() {
	// return pnEmployee;
	// }

	// public void setPnEmployee(PnEmployee pnEmployee) {
	// this.pnEmployee = pnEmployee;
	// }

	public Long getKeySeq() {
		return keySeq;
	}

	public WePnEmployeeText(Long keySeq, String ouCode, String empCode,
			Date statusDate, Long codeSeq, Long codeSeqAct, String preName,
			String engName, String engLastname, String empLevel, String orgEmp,
			String positionShort, String pnTransfer, String creBy,
			Date creDate, String updBy, Date updDate) {
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.statusDate = statusDate;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.preName = preName;
		this.engName = engName;
		this.engLastname = engLastname;
		this.empLevel = empLevel;
		this.orgEmp = orgEmp;
		this.positionShort = positionShort;
		this.pnTransfer = pnTransfer;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
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

	public String getPnTransfer() {
		return pnTransfer;
	}

	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
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

	public String getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}

	public String getOrgEmp() {
		return orgEmp;
	}

	public void setOrgEmp(String orgEmp) {
		this.orgEmp = orgEmp;
	}

	public String getPositionShort() {
		return positionShort;
	}

	public void setPositionShort(String positionShort) {
		this.positionShort = positionShort;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPreName() {
		return preName;
	}
}
