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
public class FeeContractDetailsVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double codeSeq;
	private Double codeSeqD;
	private String codeSeqAct;
	private String yearCon;
	private String contractNo;
	private String empCode;
	private String name;
	private Integer dutySeq;
	private String dutyCode;
	private Double salary;  
	private Double extraIncome;
	private Double warfee;
	private Double otRate;
	private Date startDate;
	private Date endDate;
	private String inactive;
	private String inactiveD;
	private String note;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	
	
	
	public FeeContractDetailsVO() {
	
	}




	public FeeContractDetailsVO(Long keySeq, String ouCode, Double codeSeq,
			Double codeSeqD, String codeSeqAct, String yearCon,
			String contractNo, String empCode, String name, Integer dutySeq,
			String dutyCode, Double salary, Double extraIncome, Double warfee,
			Double otRate, Date startDate, Date endDate, String inactive,
			String inactiveD, String note, String creBy, Date creDate,
			String updBy, Date updDate) {
		//super();
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.codeSeq = codeSeq;
		this.codeSeqD = codeSeqD;
		this.codeSeqAct = codeSeqAct;
		this.yearCon = yearCon;
		this.contractNo = contractNo;
		this.empCode = empCode;
		this.name = name;
		this.dutySeq = dutySeq;
		this.dutyCode = dutyCode;
		this.salary = salary;
		this.extraIncome = extraIncome;
		this.warfee = warfee;
		this.otRate = otRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.inactive = inactive;
		this.inactiveD = inactiveD;
		this.note = note;
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




	public Double getCodeSeqD() {
		return codeSeqD;
	}




	public void setCodeSeqD(Double codeSeqD) {
		this.codeSeqD = codeSeqD;
	}




	public String getCodeSeqAct() {
		return codeSeqAct;
	}




	public void setCodeSeqAct(String codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
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




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
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




	public String getInactive() {
		return inactive;
	}




	public void setInactive(String inactive) {
		this.inactive = inactive;
	}




	public String getInactiveD() {
		return inactiveD;
	}




	public void setInactiveD(String inactiveD) {
		this.inactiveD = inactiveD;
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
