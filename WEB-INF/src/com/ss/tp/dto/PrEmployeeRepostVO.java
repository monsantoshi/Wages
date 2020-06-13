package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PrEmployeeRepostVO implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;
	private Double codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private Double costChild;
	private Double childStudy;
	private Double childNoStudy;
	private Double costLife;
	private String gundanFlag;
	private Double debtLife;
	private Double debtLoan;
	private Double donate;
	private Double other;
	private Double incomeTax;
	private Double oldSalary;
	private Double newSalary;
	private Double adjOldsal;
	private Double adjNewsal;
	private Double gundanAmt;
	private String flagPr;
	private Double deductAmt;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private Double seqRec;
	private String flagMotherSpouse;
	private String flagFatherSpouse;
	private String flagMother;
	private String flagFather;
	private Double ltf;
	private Double rmf;
	private Double pensionFund;
	private Double teacherFund;
	private Double overageSpouse;
	private Double overage;
	private Double compensateLabour;
	private String confirmFlag;

	private Double newCodeSeq;
	private Double debtLifeSpouse;

	public Double getNewCodeSeq() {
		return newCodeSeq;
	}

	public void setNewCodeSeq(Double newCodeSeq) {
		this.newCodeSeq = newCodeSeq;
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

	public Double getLtf() {
		return ltf;
	}

	public void setLtf(Double ltf) {
		this.ltf = ltf;
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

	public Double getPensionFund() {
		return pensionFund;
	}

	public void setPensionFund(Double pensionFund) {
		this.pensionFund = pensionFund;
	}

	public Double getRmf() {
		return rmf;
	}

	public void setRmf(Double rmf) {
		this.rmf = rmf;
	}

	public Double getSeqRec() {
		return seqRec;
	}

	public void setSeqRec(Double seqRec) {
		this.seqRec = seqRec;
	}

	public Double getTeacherFund() {
		return teacherFund;
	}

	public void setTeacherFund(Double teacherFund) {
		this.teacherFund = teacherFund;
	}

	public PrEmployeeRepostVO() {
	}

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

	public Double getChildNoStudy() {
		return childNoStudy;
	}

	public void setChildNoStudy(Double childNoStudy) {
		this.childNoStudy = childNoStudy;
	}

	public Double getChildStudy() {
		return childStudy;
	}

	public void setChildStudy(Double childStudy) {
		this.childStudy = childStudy;
	}

	public Double getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(Double codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public Double getCostChild() {
		return costChild;
	}

	public void setCostChild(Double costChild) {
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

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
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

	public PrEmployeeRepostVO(Double newsal, Double oldsal, String id,
			Double study, Double study2, Double work, Double labour,
			String flag, Double child, Double life, String by, Date date,
			Double life2, Double loan, Double amt, Double donate, String code,
			String father, String spouse, String mother, String spouse2,
			String pr, Double amt2, String flag2, Double tax, Double ltf,
			String status, Double salary, Double salary2, Double other,
			String code2, Double overage, Double spouse3, String status2,
			Double fund, Double period, Double rmf, Double rec, String id2,
			Double fund2, String by2, Date date2, Double year,Double life3) {
		super();
		// TODO Auto-generated constructor stub
		adjNewsal = newsal;
		adjOldsal = oldsal;
		bankId = id;
		childNoStudy = study;
		childStudy = study2;
		codeSeqWork = work;
		compensateLabour = labour;
		confirmFlag = flag;
		costChild = child;
		costLife = life;
		creBy = by;
		creDate = date;
		debtLife = life2;
		debtLoan = loan;
		deductAmt = amt;
		this.donate = donate;
		empCode = code;
		flagFather = father;
		flagFatherSpouse = spouse;
		flagMother = mother;
		flagMotherSpouse = spouse2;
		flagPr = pr;
		gundanAmt = amt2;
		gundanFlag = flag2;
		incomeTax = tax;
		this.ltf = ltf;
		marriedStatus = status;
		newSalary = salary;
		oldSalary = salary2;
		this.other = other;
		ouCode = code2;
		this.overage = overage;
		overageSpouse = spouse3;
		payStatus = status2;
		pensionFund = fund;
		this.period = period;
		this.rmf = rmf;
		seqRec = rec;
		taxId = id2;
		teacherFund = fund2;
		updBy = by2;
		updDate = date2;
		this.year = year;
		debtLifeSpouse = life3;
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

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public Double getDebtLifeSpouse() {
		return debtLifeSpouse;
	}

	public void setDebtLifeSpouse(Double debtLifeSpouse) {
		this.debtLifeSpouse = debtLifeSpouse;
	}
	

}
