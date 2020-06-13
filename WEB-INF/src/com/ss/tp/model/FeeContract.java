/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FeeContract implements Serializable {
	private FeeContractPK pk;
	private Date conDate;
	private String instructNo;
	private Date instructDate;
	private Date promoteDate;
	private Date sContactDate;
	private Date eContactDate;
	private Integer dutySeq;
	private String dutyCode;
	private String note;
	private String inactive;

	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private PnOrganization refPnOrganization;
	private FeeWgDuty refWgDuty;
	private Set feeContractDetl;
	public FeeContract() {
	
		// TODO Auto-generated constructor stub
	}
	public FeeContract(FeeContractPK pk, Date conDate, String instructNo,
			Date instructDate, Date promoteDate, Date sContactDate,
			Date eContactDate, Integer dutySeq, String dutyCode, String note,
			String inactive, String creBy, Date creDate, String updBy,
			Date updDate, PnOrganization refPnOrganization, 
			FeeWgDuty refWgDuty,
			Set feeContractDetl) {
		//super();
		this.pk = pk;
		this.conDate = conDate;
		this.instructNo = instructNo;
		this.instructDate = instructDate;
		this.promoteDate = promoteDate;
		this.sContactDate = sContactDate;
		this.eContactDate = eContactDate;
		this.dutySeq = dutySeq;
		this.dutyCode = dutyCode;
		this.note = note;
		this.inactive = inactive;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.refPnOrganization = refPnOrganization;
		this.refWgDuty = refWgDuty;
		this.feeContractDetl = feeContractDetl;
	}
	public FeeContractPK getPk() {
		return pk;
	}
	public void setPk(FeeContractPK pk) {
		this.pk = pk;
	}
	public Date getConDate() {
		return conDate;
	}
	public void setConDate(Date conDate) {
		this.conDate = conDate;
	}
	public String getInstructNo() {
		return instructNo;
	}
	public void setInstructNo(String instructNo) {
		this.instructNo = instructNo;
	}
	public Date getInstructDate() {
		return instructDate;
	}
	public void setInstructDate(Date instructDate) {
		this.instructDate = instructDate;
	}
	public Date getPromoteDate() {
		return promoteDate;
	}
	public void setPromoteDate(Date promoteDate) {
		this.promoteDate = promoteDate;
	}
	public Date getsContactDate() {
		return sContactDate;
	}
	public void setsContactDate(Date sContactDate) {
		this.sContactDate = sContactDate;
	}
	public Date geteContactDate() {
		return eContactDate;
	}
	public void seteContactDate(Date eContactDate) {
		this.eContactDate = eContactDate;
	}
	public Integer getDutySeq() {
		return dutySeq;
	}
	public void setDutySeq(Integer dutySeq) {
		this.dutySeq = dutySeq;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInactive() {
		return inactive;
	}
	public void setInactive(String inactive) {
		this.inactive = inactive;
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
	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}
	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
	}
	public FeeWgDuty getRefWgDuty() {
		return refWgDuty;
	}
	public void setRefWgDuty(FeeWgDuty refWgDuty) {
		this.refWgDuty = refWgDuty;
	}
	public Set getFeeContractDetl() {
		return feeContractDetl;
	}
	public void setFeeContractDetl(Set feeContractDetl) {
		this.feeContractDetl = feeContractDetl;
	}
	
	
	
	
}
