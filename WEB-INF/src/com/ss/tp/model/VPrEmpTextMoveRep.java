/*
 * Created on 13 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPrEmpTextMoveRep implements Serializable {
	private Integer keySeq;
	private String ouCode;
	private Integer year;
	private Integer period;
	private String empCode;
	private Integer codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private String bankBranch;
	private String bankCode;
	private Integer costChild;
	private Integer childStudy;
	private Integer childNoStudy;
	private Double costLife;
	private String gundanFlag;
	private Double debtLife;
	private Double debtLoan;
	private Double donate;
	private Double other;

	private Double incomeTax;
	private Double oldSalary;
	private Double newSalary;;
	private Double adjOldsal;
	private Double adjNewsal;
	private Double gundanAmt;

	private String flagPr;
	private Integer deductAmt;
	private String flagStatus;
	private Integer seqData;

	private String flagFather;
	private String flagMother;
	private String flagFatherSpouse;
	private String flagMotherSpouse;
	private Double healthFather;
	private Double handicappedDec;
	private Double rmf;
	private Double ltf;
	private Double pensionFund;
	private Double teacherFund;
	private Double overage;
	private Double overageSpouse;
	private Double compensateLabour;
	private String confirmFlag;

	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	/**
	 * @return Returns the adjNewsal.
	 */
	public Double getAdjNewsal() {
		return adjNewsal;
	}

	/**
	 * @param adjNewsal
	 *            The adjNewsal to set.
	 */
	public void setAdjNewsal(Double adjNewsal) {
		this.adjNewsal = adjNewsal;
	}

	/**
	 * @return Returns the adjOldsal.
	 */
	public Double getAdjOldsal() {
		return adjOldsal;
	}

	/**
	 * @param adjOldsal
	 *            The adjOldsal to set.
	 */
	public void setAdjOldsal(Double adjOldsal) {
		this.adjOldsal = adjOldsal;
	}

	/**
	 * @return Returns the bankId.
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            The bankId to set.
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	/**
	 * @return Returns the childNoStudy.
	 */
	public Integer getChildNoStudy() {
		return childNoStudy;
	}

	/**
	 * @param childNoStudy
	 *            The childNoStudy to set.
	 */
	public void setChildNoStudy(Integer childNoStudy) {
		this.childNoStudy = childNoStudy;
	}

	/**
	 * @return Returns the childStudy.
	 */
	public Integer getChildStudy() {
		return childStudy;
	}

	/**
	 * @param childStudy
	 *            The childStudy to set.
	 */
	public void setChildStudy(Integer childStudy) {
		this.childStudy = childStudy;
	}

	/**
	 * @return Returns the codeSeqWork.
	 */
	public Integer getCodeSeqWork() {
		return codeSeqWork;
	}

	/**
	 * @param codeSeqWork
	 *            The codeSeqWork to set.
	 */
	public void setCodeSeqWork(Integer codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	/**
	 * @return Returns the compensateLabour.
	 */
	public Double getCompensateLabour() {
		return compensateLabour;
	}

	/**
	 * @param compensateLabour
	 *            The compensateLabour to set.
	 */
	public void setCompensateLabour(Double compensateLabour) {
		this.compensateLabour = compensateLabour;
	}

	/**
	 * @return Returns the confirmFlag.
	 */
	public String getConfirmFlag() {
		return confirmFlag;
	}

	/**
	 * @param confirmFlag
	 *            The confirmFlag to set.
	 */
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 * @return Returns the costChild.
	 */
	public Integer getCostChild() {
		return costChild;
	}

	/**
	 * @param costChild
	 *            The costChild to set.
	 */
	public void setCostChild(Integer costChild) {
		this.costChild = costChild;
	}

	/**
	 * @return Returns the costLife.
	 */
	public Double getCostLife() {
		return costLife;
	}

	/**
	 * @param costLife
	 *            The costLife to set.
	 */
	public void setCostLife(Double costLife) {
		this.costLife = costLife;
	}

	/**
	 * @return Returns the creBy.
	 */
	public String getCreBy() {
		return creBy;
	}

	/**
	 * @param creBy
	 *            The creBy to set.
	 */
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	/**
	 * @return Returns the creDate.
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            The creDate to set.
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return Returns the debtLife.
	 */
	public Double getDebtLife() {
		return debtLife;
	}

	/**
	 * @param debtLife
	 *            The debtLife to set.
	 */
	public void setDebtLife(Double debtLife) {
		this.debtLife = debtLife;
	}

	/**
	 * @return Returns the debtLoan.
	 */
	public Double getDebtLoan() {
		return debtLoan;
	}

	/**
	 * @param debtLoan
	 *            The debtLoan to set.
	 */
	public void setDebtLoan(Double debtLoan) {
		this.debtLoan = debtLoan;
	}

	/**
	 * @return Returns the deductAmt.
	 */
	public Integer getDeductAmt() {
		return deductAmt;
	}

	/**
	 * @param deductAmt
	 *            The deductAmt to set.
	 */
	public void setDeductAmt(Integer deductAmt) {
		this.deductAmt = deductAmt;
	}

	/**
	 * @return Returns the donate.
	 */
	public Double getDonate() {
		return donate;
	}

	/**
	 * @param donate
	 *            The donate to set.
	 */
	public void setDonate(Double donate) {
		this.donate = donate;
	}

	/**
	 * @return Returns the empCode.
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            The empCode to set.
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return Returns the flagFather.
	 */
	public String getFlagFather() {
		return flagFather;
	}

	/**
	 * @param flagFather
	 *            The flagFather to set.
	 */
	public void setFlagFather(String flagFather) {
		this.flagFather = flagFather;
	}

	/**
	 * @return Returns the flagFatherSpouse.
	 */
	public String getFlagFatherSpouse() {
		return flagFatherSpouse;
	}

	/**
	 * @param flagFatherSpouse
	 *            The flagFatherSpouse to set.
	 */
	public void setFlagFatherSpouse(String flagFatherSpouse) {
		this.flagFatherSpouse = flagFatherSpouse;
	}

	/**
	 * @return Returns the flagMother.
	 */
	public String getFlagMother() {
		return flagMother;
	}

	/**
	 * @param flagMother
	 *            The flagMother to set.
	 */
	public void setFlagMother(String flagMother) {
		this.flagMother = flagMother;
	}

	/**
	 * @return Returns the flagMotherSpouse.
	 */
	public String getFlagMotherSpouse() {
		return flagMotherSpouse;
	}

	/**
	 * @param flagMotherSpouse
	 *            The flagMotherSpouse to set.
	 */
	public void setFlagMotherSpouse(String flagMotherSpouse) {
		this.flagMotherSpouse = flagMotherSpouse;
	}

	/**
	 * @return Returns the flagPr.
	 */
	public String getFlagPr() {
		return flagPr;
	}

	/**
	 * @param flagPr
	 *            The flagPr to set.
	 */
	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	/**
	 * @return Returns the flagStatus.
	 */
	public String getFlagStatus() {
		return flagStatus;
	}

	/**
	 * @param flagStatus
	 *            The flagStatus to set.
	 */
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	/**
	 * @return Returns the gundanAmt.
	 */
	public Double getGundanAmt() {
		return gundanAmt;
	}

	/**
	 * @param gundanAmt
	 *            The gundanAmt to set.
	 */
	public void setGundanAmt(Double gundanAmt) {
		this.gundanAmt = gundanAmt;
	}

	/**
	 * @return Returns the gundanFlag.
	 */
	public String getGundanFlag() {
		return gundanFlag;
	}

	/**
	 * @param gundanFlag
	 *            The gundanFlag to set.
	 */
	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}

	/**
	 * @return Returns the incomeTax.
	 */
	public Double getIncomeTax() {
		return incomeTax;
	}

	/**
	 * @param incomeTax
	 *            The incomeTax to set.
	 */
	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * @return Returns the keySeq.
	 */
	public Integer getKeySeq() {
		return keySeq;
	}

	/**
	 * @param keySeq
	 *            The keySeq to set.
	 */
	public void setKeySeq(Integer keySeq) {
		this.keySeq = keySeq;
	}

	/**
	 * @return Returns the ltf.
	 */
	public Double getLtf() {
		return ltf;
	}

	/**
	 * @param ltf
	 *            The ltf to set.
	 */
	public void setLtf(Double ltf) {
		this.ltf = ltf;
	}

	/**
	 * @return Returns the marriedStatus.
	 */
	public String getMarriedStatus() {
		return marriedStatus;
	}

	/**
	 * @param marriedStatus
	 *            The marriedStatus to set.
	 */
	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	/**
	 * @return Returns the newSalary.
	 */
	public Double getNewSalary() {
		return newSalary;
	}

	/**
	 * @param newSalary
	 *            The newSalary to set.
	 */
	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
	}

	/**
	 * @return Returns the oldSalary.
	 */
	public Double getOldSalary() {
		return oldSalary;
	}

	/**
	 * @param oldSalary
	 *            The oldSalary to set.
	 */
	public void setOldSalary(Double oldSalary) {
		this.oldSalary = oldSalary;
	}

	/**
	 * @return Returns the other.
	 */
	public Double getOther() {
		return other;
	}

	/**
	 * @param other
	 *            The other to set.
	 */
	public void setOther(Double other) {
		this.other = other;
	}

	/**
	 * @return Returns the ouCode.
	 */
	public String getOuCode() {
		return ouCode;
	}

	/**
	 * @param ouCode
	 *            The ouCode to set.
	 */
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	/**
	 * @return Returns the overage.
	 */
	public Double getOverage() {
		return overage;
	}

	/**
	 * @param overage
	 *            The overage to set.
	 */
	public void setOverage(Double overage) {
		this.overage = overage;
	}

	/**
	 * @return Returns the overageSpouse.
	 */
	public Double getOverageSpouse() {
		return overageSpouse;
	}

	/**
	 * @param overageSpouse
	 *            The overageSpouse to set.
	 */
	public void setOverageSpouse(Double overageSpouse) {
		this.overageSpouse = overageSpouse;
	}

	/**
	 * @return Returns the payStatus.
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus
	 *            The payStatus to set.
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return Returns the pensionFund.
	 */
	public Double getPensionFund() {
		return pensionFund;
	}

	/**
	 * @param pensionFund
	 *            The pensionFund to set.
	 */
	public void setPensionFund(Double pensionFund) {
		this.pensionFund = pensionFund;
	}

	/**
	 * @return Returns the period.
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            The period to set.
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}

	/**
	 * @return Returns the rmf.
	 */
	public Double getRmf() {
		return rmf;
	}

	/**
	 * @param rmf
	 *            The rmf to set.
	 */
	public void setRmf(Double rmf) {
		this.rmf = rmf;
	}

	/**
	 * @return Returns the seqData.
	 */
	public Integer getSeqData() {
		return seqData;
	}

	/**
	 * @param seqData
	 *            The seqData to set.
	 */
	public void setSeqData(Integer seqData) {
		this.seqData = seqData;
	}

	/**
	 * @return Returns the taxId.
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 *            The taxId to set.
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return Returns the teacherFund.
	 */
	public Double getTeacherFund() {
		return teacherFund;
	}

	/**
	 * @param teacherFund
	 *            The teacherFund to set.
	 */
	public void setTeacherFund(Double teacherFund) {
		this.teacherFund = teacherFund;
	}

	/**
	 * @return Returns the updBy.
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy
	 *            The updBy to set.
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return Returns the updDate.
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            The updDate to set.
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/**
	 * @return Returns the year.
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            The year to set.
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getHealthFather() {
		return healthFather;
	}

	public void setHealthFather(Double healthFather) {
		this.healthFather = healthFather;
	}

	public Double getHandicappedDec() {
		return handicappedDec;
	}

	public void setHandicappedDec(Double handicappedDec) {
		this.handicappedDec = handicappedDec;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
