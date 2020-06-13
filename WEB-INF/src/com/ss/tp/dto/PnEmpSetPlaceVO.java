package com.ss.tp.dto;

//import com.ss.tp.model.PnEmpMove;
//import com.ss.tp.model.PnEmpSetPlacePK;

import java.io.Serializable;
import java.util.Date;

//import java.util.Set;

public class PnEmpSetPlaceVO implements Serializable {

	private Long moveNo;
	private String empCode;
	private Double year;
	private String orgCode;
	private String note;
	private String ouCode;
	private String moveSeq;
	private Date dateSubmit;
	private String reason;
	private String moveOk;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public Long getMoveNo() {
		return moveNo;
	}

	public void setMoveNo(Long moveNo) {
		this.moveNo = moveNo;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMoveSeq() {
		return moveSeq;
	}

	public void setMoveSeq(String moveSeq) {
		this.moveSeq = moveSeq;
	}

	public Date getDateSubmit() {
		return dateSubmit;
	}

	public void setDateSubmit(Date dateSubmit) {
		this.dateSubmit = dateSubmit;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMoveOk() {
		return moveOk;
	}

	public void setMoveOk(String moveOk) {
		this.moveOk = moveOk;
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

	public void setCreBy(Date creDate) {
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

	public void setUpdBy(Date updDate) {
		this.updDate = updDate;
	}

}
