package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PnEmpSetPlace implements Serializable {
	private PnEmpSetPlacePK pk;
	private String empCode;
	private String moveSeq;
	private Date dateSubmit;
	private String reason;
	private String moveOk;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private PnEmpMove refPnEmpMove;
	private Set pnOrganizationDtl;

	public PnEmpSetPlace(PnEmpSetPlacePK pk, String empCode, String seq,
			Date dateSubmit, String reason, String ok, String cby, String uby,
			Date cdate, Date udate, PnEmpMove refPnEmpMove,
			Set pnOrganizationDtl) {
		// TODO Auto-generated constructor stub
		this.pk = pk;
		this.empCode = empCode;
		this.moveSeq = seq;
		this.dateSubmit = dateSubmit;
		this.reason = reason;
		this.moveOk = ok;
		this.creBy = cby;
		this.updBy = uby;
		this.creDate = cdate;
		this.updDate = udate;
		this.refPnEmpMove = refPnEmpMove;
		this.pnOrganizationDtl = pnOrganizationDtl;

	}

	public PnEmpSetPlace() {
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public PnEmpSetPlacePK getPk() {
		return pk;
	}

	public void setPk(PnEmpSetPlacePK pk) {
		this.pk = pk;
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

	public Set getPnOrganizationDtl() {
		return pnOrganizationDtl;
	}

	public void setPnOrganizationDtl(Set pnOrganizationDtl) {
		this.pnOrganizationDtl = pnOrganizationDtl;
	}

	public PnEmpMove getRefPnEmpMove() {
		return refPnEmpMove;
	}

	public void setRefPnEmpMove(PnEmpMove refPnEmpMove) {
		this.refPnEmpMove = refPnEmpMove;
	}

}
