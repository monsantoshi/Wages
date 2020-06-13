package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgContactVO implements Serializable {
	private String ouCode;
	private Double year;
	
	private String conCode;
	private Double codeSeqWork;
	private Date sDate;
	private Date eDate;
	private String orgDesc;
	
	
	
	private String flagStatus;
	
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	public FeeWgContactVO(String ouCode, Double year, String conCode,
			Double codeSeqWork, Date sDate, Date eDate, String flagStatus,
			String creBy, Date creDate, String updBy, Date updDate,String orgDesc) {
		super();
		this.ouCode = ouCode;
		this.year = year;
		this.conCode = conCode;
		this.codeSeqWork = codeSeqWork;
		this.sDate = sDate;
		this.eDate = eDate;
		this.flagStatus = flagStatus;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.orgDesc = orgDesc;
	}
	public FeeWgContactVO() {
		
	}
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public Double getYear() {
		return year;
	}
	public void setYear(Double year) {
		this.year = year;
	}
	public String getConCode() {
		return conCode;
	}
	public void setConCode(String conCode) {
		this.conCode = conCode;
	}
	public Double getCodeSeqWork() {
		return codeSeqWork;
	}
	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
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
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	

	

}
