/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FeeContractVO implements Serializable {
	private String ouCode;
	private String yearCon;
	private Double codeSeq;
	private String codeSeqAct;
	private String contractNo;
	private Date conDate;
	private String instructNo;
	private Date instructDate;
	private Date promoteDate;
	private Date scontactDate;
	private Date econtactDate;
	private Integer dutySeq;
	private String dutyCode;
	private String note;
	private String inactive;
	private String dutyName;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	
	private String orgCode;
	private String orgDesc;
	
	private String contractDateStr;
	private String instructDateStr;
	private String promoteDateStr;
	private String scontactDateStr;
	private String econtactDateStr;
	public FeeContractVO() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public FeeContractVO(String ouCode, String yearCon, Double codeSeq,
			String codeSeqAct, String contractNo, Date conDate,
			String instructNo, Date instructDate, Date promoteDate,
			Date scontactDate, Date econtactDate, Integer dutySeq,
			String dutyCode, String note, String inactive, String dutyName,
			String creBy, Date creDate, String updBy, Date updDate,
			String orgCode, String orgDesc, String contractDateStr,
			String instructDateStr, String promoteDateStr,
			String scontactDateStr, String econtactDateStr) {
		//super();
		this.ouCode = ouCode;
		this.yearCon = yearCon;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.contractNo = contractNo;
		this.conDate = conDate;
		this.instructNo = instructNo;
		this.instructDate = instructDate;
		this.promoteDate = promoteDate;
		this.scontactDate = scontactDate;
		this.econtactDate = econtactDate;
		this.dutySeq = dutySeq;
		this.dutyCode = dutyCode;
		this.note = note;
		this.inactive = inactive;
		this.dutyName = dutyName;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.orgCode = orgCode;
		this.orgDesc = orgDesc;
		this.contractDateStr = contractDateStr;
		this.instructDateStr = instructDateStr;
		this.promoteDateStr = promoteDateStr;
		this.scontactDateStr = scontactDateStr;
		this.econtactDateStr = econtactDateStr;
	}
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public String getYearCon() {
		return yearCon;
	}
	public void setYearCon(String yearCon) {
		this.yearCon = yearCon;
	}
	public Double getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getCodeSeqAct() {
		return codeSeqAct;
	}
	public void setCodeSeqAct(String codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
	public Date getScontactDate() {
		return scontactDate;
	}
	public void setScontactDate(Date scontactDate) {
		this.scontactDate = scontactDate;
	}
	public Date getEcontactDate() {
		return econtactDate;
	}
	public void setEcontactDate(Date econtactDate) {
		this.econtactDate = econtactDate;
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
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
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
	public String getContractDateStr() {
		return contractDateStr;
	}
	public void setContractDateStr(String contractDateStr) {
		this.contractDateStr = contractDateStr;
	}
	public String getInstructDateStr() {
		return instructDateStr;
	}
	public void setInstructDateStr(String instructDateStr) {
		this.instructDateStr = instructDateStr;
	}
	public String getPromoteDateStr() {
		return promoteDateStr;
	}
	public void setPromoteDateStr(String promoteDateStr) {
		this.promoteDateStr = promoteDateStr;
	}
	public String getScontactDateStr() {
		return scontactDateStr;
	}
	public void setScontactDateStr(String scontactDateStr) {
		this.scontactDateStr = scontactDateStr;
	}
	public String getEcontactDateStr() {
		return econtactDateStr;
	}
	public void setEcontactDateStr(String econtactDateStr) {
		this.econtactDateStr = econtactDateStr;
	}
	
	
}
