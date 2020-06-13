/*
 * Created on 18 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPrWorkDayVO implements Serializable {
	private String ouCode;
	private Integer year;
	private Integer period;
	private String empCode;
	private Integer codeSeqWork;
	private Double workDay;
	private Double workAmt;
	private Double wageAmt;
	private String confirmFlag;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	private String firstName;
	private String lastName;
	private String prefixName;
	private Double newSalary;

	// ========= Create By Kiet ===========
	private String areaCode;
	private String areaDesc;
	private String secCode;
	private String secDesc;

	// =================================

	/**
	 * 
	 */
	public WgPrWorkDayVO() {
		// TODO Auto-generated constructor stub
	}

	public Double getNewSalary() {
		return newSalary;
	}

	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
	}

	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Integer codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
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

	public Double getWageAmt() {
		return wageAmt;
	}

	public void setWageAmt(Double wageAmt) {
		this.wageAmt = wageAmt;
	}

	public Double getWorkAmt() {
		return workAmt;
	}

	public void setWorkAmt(Double workAmt) {
		this.workAmt = workAmt;
	}

	public Double getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Double workDay) {
		this.workDay = workDay;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return Returns the areaCode.
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            The areaCode to set.
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return Returns the areaDesc.
	 */
	public String getAreaDesc() {
		return areaDesc;
	}

	/**
	 * @param areaDesc
	 *            The areaDesc to set.
	 */
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	/**
	 * @return Returns the secCode.
	 */
	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode
	 *            The secCode to set.
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	/**
	 * @return Returns the secDesc.
	 */
	public String getSecDesc() {
		return secDesc;
	}

	/**
	 * @param secDesc
	 *            The secDesc to set.
	 */
	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
}
