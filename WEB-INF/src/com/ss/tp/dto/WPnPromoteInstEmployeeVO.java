package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class WPnPromoteInstEmployeeVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private String section;
	private String year;
	private String month;
	private String empCode;
	private String name;
	private String oDuty;
	private String nPosition;
	private String nLevel;
	private String nDuty;
	private String nPlaceMoveOrg;
	private String nPlaceMoveCode;
	private String note;
	private String codeSeq;

	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public WPnPromoteInstEmployeeVO() {

	}

	public WPnPromoteInstEmployeeVO(String sec, String code, String name,
			String yea, String mon, String od, String np, String nl, String nd,
			String npo, String npc, String no, String by, Date cdate,
			String by2, Date udate, String code2) {

		// TODO Auto-generated constructor stub
		this.section = sec;
		this.empCode = code;
		this.name = name;
		this.year = year;
		this.month = mon;
		this.oDuty = od;
		this.nPosition = np;
		this.nLevel = nl;
		this.nDuty = nd;
		this.nPlaceMoveOrg = npo;
		this.nPlaceMoveCode = npc;
		this.note = no;
		this.creBy = by;
		this.updBy = by2;
		this.creDate = cdate;
		this.updDate = udate;
		this.codeSeq = code2;
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public String getODuty() {
		return oDuty;
	}

	public void setODuty(String oduty) {
		oDuty = oduty;
	}

	public String getNPosition() {
		return nPosition;
	}

	public void setNPosition(String nposition) {
		nPosition = nposition;
	}

	public String getNLevel() {
		return nLevel;
	}

	public void setNLevel(String nlevel) {
		nLevel = nlevel;
	}

	public String getNDuty() {
		return nDuty;
	}

	public void setNDuty(String nduty) {
		nDuty = nduty;
	}

	public String getNPlaceMoveOrg() {
		return nPlaceMoveOrg;
	}

	public void setNPlaceMoveOrg(String nplaceMoveOrg) {
		nPlaceMoveOrg = nplaceMoveOrg;
	}

	public String getNPlaceMoveCode() {
		return nPlaceMoveCode;
	}

	public void setNPlaceMoveCode(String nplaceMoveCode) {
		nPlaceMoveCode = nplaceMoveCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}

}
