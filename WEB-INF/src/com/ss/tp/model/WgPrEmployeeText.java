package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgPrEmployeeText implements Serializable {

	private long keySeq;
	private String ouCode;
	private Integer year;
	private Integer period;
	private String empCode;
	private Integer codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private Integer costChild;
	private Integer childStudy;
	private Integer childNoStudy;
	private Double costLife;
	private Double debtLife;
	private Double debtLoan;
	private Double donate;
	private Double other;
	private Double incomeTax;
	private Double oldSalary;
	private Double newSalary;
	private Double adjOldsal;
	private Double adjNewsal;
	private String flagFather;
	private String flagMother;
	private String flagFatherSpouse;
	private String flagMotherSpouse;
	private Double rmf;
	private Double ltf;
	private Double pensionFund;
	private Double deductAmt;
	private Double teacherFund;
	private Double compensateLabour;
	private Double overage;
	private Double overageSpouse;
	private String gundanFlag;
	private Double gundanAmt;
	private String flagPr;
	private String flagStatus;
	private String confirmFlag;
	private Integer seqData;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	public Double getAdjNewsal() {
		return adjNewsal;
	}

	public void setAdjNewsal(Double adjNewsal) {
		this.adjNewsal = adjNewsal;
	}

	public Double getAdjOldsal() {
		return adjOldsal;
	}

	public void setAdjOldsal(Double adjOldsal) {
		this.adjOldsal = adjOldsal;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Integer getChildNoStudy() {
		return childNoStudy;
	}

	public void setChildNoStudy(Integer childNoStudy) {
		this.childNoStudy = childNoStudy;
	}

	public Integer getChildStudy() {
		return childStudy;
	}

	public void setChildStudy(Integer childStudy) {
		this.childStudy = childStudy;
	}

	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Integer codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public Double getCompensateLabour() {
		return compensateLabour;
	}

	public void setCompensateLabour(Double compensateLabour) {
		this.compensateLabour = compensateLabour;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public Integer getCostChild() {
		return costChild;
	}

	public void setCostChild(Integer costChild) {
		this.costChild = costChild;
	}

	public Double getCostLife() {
		return costLife;
	}

	public void setCostLife(Double costLife) {
		this.costLife = costLife;
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

	public Double getDebtLife() {
		return debtLife;
	}

	public void setDebtLife(Double debtLife) {
		this.debtLife = debtLife;
	}

	public Double getDebtLoan() {
		return debtLoan;
	}

	public void setDebtLoan(Double debtLoan) {
		this.debtLoan = debtLoan;
	}

	public Double getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(Double deductAmt) {
		this.deductAmt = deductAmt;
	}

	public Double getDonate() {
		return donate;
	}

	public void setDonate(Double donate) {
		this.donate = donate;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFlagFather() {
		return flagFather;
	}

	public void setFlagFather(String flagFather) {
		this.flagFather = flagFather;
	}

	public String getFlagFatherSpouse() {
		return flagFatherSpouse;
	}

	public void setFlagFatherSpouse(String flagFatherSpouse) {
		this.flagFatherSpouse = flagFatherSpouse;
	}

	public String getFlagMother() {
		return flagMother;
	}

	public void setFlagMother(String flagMother) {
		this.flagMother = flagMother;
	}

	public String getFlagMotherSpouse() {
		return flagMotherSpouse;
	}

	public void setFlagMotherSpouse(String flagMotherSpouse) {
		this.flagMotherSpouse = flagMotherSpouse;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public Double getGundanAmt() {
		return gundanAmt;
	}

	public void setGundanAmt(Double gundanAmt) {
		this.gundanAmt = gundanAmt;
	}

	public String getGundanFlag() {
		return gundanFlag;
	}

	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}

	public Double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(long keySeq) {
		this.keySeq = keySeq;
	}

	public Double getLtf() {
		return ltf;
	}

	public void setLtf(Double ltf) {
		this.ltf = ltf;
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	public Double getNewSalary() {
		return newSalary;
	}

	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
	}

	public Double getOldSalary() {
		return oldSalary;
	}

	public void setOldSalary(Double oldSalary) {
		this.oldSalary = oldSalary;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getOverage() {
		return overage;
	}

	public void setOverage(Double overage) {
		this.overage = overage;
	}

	public Double getOverageSpouse() {
		return overageSpouse;
	}

	public void setOverageSpouse(Double overageSpouse) {
		this.overageSpouse = overageSpouse;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Double getPensionFund() {
		return pensionFund;
	}

	public void setPensionFund(Double pensionFund) {
		this.pensionFund = pensionFund;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Double getRmf() {
		return rmf;
	}

	public void setRmf(Double rmf) {
		this.rmf = rmf;
	}

	public Integer getSeqData() {
		return seqData;
	}

	public void setSeqData(Integer seqData) {
		this.seqData = seqData;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public Double getTeacherFund() {
		return teacherFund;
	}

	public void setTeacherFund(Double teacherFund) {
		this.teacherFund = teacherFund;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
