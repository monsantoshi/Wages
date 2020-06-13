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
public class FeeContractDetails implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double codeSeq;
	private String yearCon;
	private String contractNo;
	private String empCode;
	private Integer dutySeq;
	private String dutyCodeD;
	private Double salary;  
	private Double extraIncome;
	private Double warfee;
	private Double otRate;
	private Date startDate;
	private Date endDate;
	private String inactiveD;
	private String noted;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	
	
	
	public FeeContractDetails() {
	
	}




	public FeeContractDetails(Long keySeq, String ouCode, Double codeSeq,
			String yearCon, String contractNo, String empCode, Integer dutySeq,
			String dutyCodeD, Double salary, Double extraIncome, Double warfee,
			Double otRate, Date startDate, Date endDate, String inactiveD,
			String noted, String creBy, Date creDate, String updBy, Date updDate) {
		//super();
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.codeSeq = codeSeq;
		this.yearCon = yearCon;
		this.contractNo = contractNo;
		this.empCode = empCode;
		this.dutySeq = dutySeq;
		this.dutyCodeD = dutyCodeD;
		this.salary = salary;
		this.extraIncome = extraIncome;
		this.warfee = warfee;
		this.otRate = otRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.inactiveD = inactiveD;
		this.noted = noted;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
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




	public Double getCodeSeq() {
		return codeSeq;
	}




	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}




	public String getYearCon() {
		return yearCon;
	}




	public void setYearCon(String yearCon) {
		this.yearCon = yearCon;
	}




	public String getContractNo() {
		return contractNo;
	}




	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}




	public String getEmpCode() {
		return empCode;
	}




	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}




	public Integer getDutySeq() {
		return dutySeq;
	}




	public void setDutySeq(Integer dutySeq) {
		this.dutySeq = dutySeq;
	}




	public String getDutyCodeD() {
		return dutyCodeD;
	}




	public void setDutyCodeD(String dutyCodeD) {
		this.dutyCodeD = dutyCodeD;
	}




	public Double getSalary() {
		return salary;
	}




	public void setSalary(Double salary) {
		this.salary = salary;
	}




	public Double getExtraIncome() {
		return extraIncome;
	}




	public void setExtraIncome(Double extraIncome) {
		this.extraIncome = extraIncome;
	}




	public Double getWarfee() {
		return warfee;
	}




	public void setWarfee(Double warfee) {
		this.warfee = warfee;
	}




	public Double getOtRate() {
		return otRate;
	}




	public void setOtRate(Double otRate) {
		this.otRate = otRate;
	}




	public Date getStartDate() {
		return startDate;
	}




	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}




	public Date getEndDate() {
		return endDate;
	}




	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public String getInactiveD() {
		return inactiveD;
	}




	public void setInactiveD(String inactiveD) {
		this.inactiveD = inactiveD;
	}




	public String getNoted() {
		return noted;
	}




	public void setNoted(String noted) {
		this.noted = noted;
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

	

	
}
