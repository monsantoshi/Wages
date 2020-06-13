package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WeEmployeeTextVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private String empCode;
	private String name;
	private String positionCode;
	private String levelCode;

	private String oldPositionShort;
	private Long codeSeq;

	private String orgCode;
	private String orgDesc;

	private String preName;

	private String engName;
	private String engLastname;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WeEmployeeTextVO() {

	}

	public WeEmployeeTextVO(Long keySeq, String ouCode, String empCode,
			String name, String positionCode, String levelCode,
			String oldPositionShort, Long codeSeq, String orgCode,
			String orgDesc, String preName, String engName, String engLastname,
			String creBy, String updBy, Date creDate, Date updDate) {
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.empCode = empCode;
		this.name = name;
		this.positionCode = positionCode;
		this.levelCode = levelCode;
		this.oldPositionShort = oldPositionShort;
		this.codeSeq = codeSeq;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.preName = preName;
		this.engName = engName;
		this.engLastname = engLastname;
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

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPreName() {
		return preName;
	}
}
