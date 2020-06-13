package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WePnEmployeeTextEmployeeVO implements Serializable {
	private Long keySeq;
	private String ouCode;

	private String empCode;
	private String name;
	private String oldPositionShort;
	private String preName;
	private String engName;
	private String engLastname;

	private Long codeSeq;
	private String orgDesc;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WePnEmployeeTextEmployeeVO() {
	}

	public WePnEmployeeTextEmployeeVO(Long seq, String pp, String od,
			String by, Date date, String code, String name, String preName,
			String engName, String engLastname, Long seq2, String code2,
			String by2, Date date2) {

		this.codeSeq = seq;
		this.oldPositionShort = pp;
		this.orgDesc = od;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.name = name;
		this.preName = preName;
		this.engName = engName;
		this.engLastname = engLastname;

		this.keySeq = seq2;

		this.ouCode = code2;

		this.updBy = by2;
		this.updDate = date2;

	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngLastname(String engLastname) {
		this.engLastname = engLastname;
	}

	public String getEngLastname() {
		return engLastname;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setOldPositionShort(String oldPositionShort) {
		this.oldPositionShort = oldPositionShort;
	}

	public String getOldPositionShort() {
		return oldPositionShort;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPreName() {
		return preName;
	}
}
