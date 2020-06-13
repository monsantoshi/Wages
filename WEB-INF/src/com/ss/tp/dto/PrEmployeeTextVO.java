package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PrEmployeeTextVO implements Serializable {
	private String keySeq;
	private String ouCode;
	private String year;
	private String period;
	private String empCode;
	private String codeSeqWork;
	private String taxId;
	private String marriedStatus;
	private String payStatus;
	private String bankId;
	private String costChild;
	private String childStudy;
	private String childNoStudy;
	private String costLife;
	private String gundanFlag;
	private String debtLife;
	private String debtLoan;
	private String donate;
	private String other;
	private String incomeTax;
	private String oldSalary;
	private String newSalary;
	private String adjOldsal;
	private String adjNewsal;
	private String gundanAmt;
	private String flagPr;
	private String deductAmt;
	private String flagStatus;
	private String seqData;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String flagFather;
	private String flagMother;
	private String flagFatherSpouse;
	private String flagMotherSpouse;
	private String rmf;
	private String ltf;
	private String pensionFund;
	private String teacherFund;
	private String overage;
	private String overageSpouse;
	private String compensateLabour;
	private String confirmFlag;

	private String divCode;
	private String divDesc;
	private String secCode;
	private String secDesc;
	private String fullName;
	private String prefix;
	private String name;
	private String lastName;
	private String areaCode;
	private String areaDesc;
	private String oldKlongChev;
	private String newKlongChev;
	private String bankBranch;
	private String bankCode;
    private String bankName;
	private String healthFather;
	private String handicappedDec;
	private String oldKc2000;
	private String newKc2000;
	private String debtLifeSpouse;

	public PrEmployeeTextVO() {
	}

	public PrEmployeeTextVO(String newsal, String oldsal, String id,
			String study, String study2, String work, String labour,
			String flag, String child, String life, String by, Date date,
			String life2, String loan, String amt, String donate, String code,
			String father, String spouse, String mother, String spouse2,
			String pr, String status, String amt2, String flag2, String tax,
			String seq, String ltf, String status2, String salary,
			String salary2, String other, String code2, String overage,
			String spouse3, String status3, String fund, String period,
			String rmf, String data, String id2, String fund2, String by2,
			Date date2, String year, String oldChev, String newChev,
			String bankBranch, String bankCode,String bankName, String healthFather,
			String handicappedDec, String oldKc2000, String newKc2000, String life3) {
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
		flagStatus = status;
		gundanAmt = amt2;
		gundanFlag = flag2;
		incomeTax = tax;
		keySeq = seq;
		this.ltf = ltf;
		marriedStatus = status2;
		newSalary = salary;
		oldSalary = salary2;
		this.other = other;
		ouCode = code2;
		this.overage = overage;
		overageSpouse = spouse3;
		payStatus = status3;
		pensionFund = fund;
		this.period = period;
		this.rmf = rmf;
		seqData = data;
		taxId = id2;
		teacherFund = fund2;
		updBy = by2;
		updDate = date2;
		this.year = year;
		oldKlongChev = oldChev;
		newKlongChev = newChev;
		this.bankBranch = bankBranch;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.healthFather = healthFather;
		this.handicappedDec = handicappedDec;
		this.oldKc2000 = oldKc2000;
		this.newKc2000 = newKc2000;
		this.debtLifeSpouse = life3;
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
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            The prefix to set.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return Returns the divCode.
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode
	 *            The divCode to set.
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return Returns the divDesc.
	 */
	public String getDivDesc() {
		return divDesc;
	}

	/**
	 * @param divDesc
	 *            The divDesc to set.
	 */
	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
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

	public String getAdjNewsal() {
		return adjNewsal;
	}

	public void setAdjNewsal(String adjNewsal) {
		this.adjNewsal = adjNewsal;
	}

	public String getAdjOldsal() {
		return adjOldsal;
	}

	public void setAdjOldsal(String adjOldsal) {
		this.adjOldsal = adjOldsal;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getChildNoStudy() {
		return childNoStudy;
	}

	public void setChildNoStudy(String childNoStudy) {
		this.childNoStudy = childNoStudy;
	}

	public String getChildStudy() {
		return childStudy;
	}

	public void setChildStudy(String childStudy) {
		this.childStudy = childStudy;
	}

	public String getCodeSeqWork() {
		return codeSeqWork;
	}

	public void setCodeSeqWork(String codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}

	public String getCompensateLabour() {
		return compensateLabour;
	}

	public void setCompensateLabour(String compensateLabour) {
		this.compensateLabour = compensateLabour;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getCostChild() {
		return costChild;
	}

	public void setCostChild(String costChild) {
		this.costChild = costChild;
	}

	public String getCostLife() {
		return costLife;
	}

	public void setCostLife(String costLife) {
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

	public String getDebtLife() {
		return debtLife;
	}

	public void setDebtLife(String debtLife) {
		this.debtLife = debtLife;
	}

	public String getDebtLoan() {
		return debtLoan;
	}

	public void setDebtLoan(String debtLoan) {
		this.debtLoan = debtLoan;
	}

	public String getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(String deductAmt) {
		this.deductAmt = deductAmt;
	}

	public String getDonate() {
		return donate;
	}

	public void setDonate(String donate) {
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

	public String getGundanAmt() {
		return gundanAmt;
	}

	public void setGundanAmt(String gundanAmt) {
		this.gundanAmt = gundanAmt;
	}

	public String getGundanFlag() {
		return gundanFlag;
	}

	public void setGundanFlag(String gundanFlag) {
		this.gundanFlag = gundanFlag;
	}

	public String getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}

	public String getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}

	public String getLtf() {
		return ltf;
	}

	public void setLtf(String ltf) {
		this.ltf = ltf;
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	public String getNewSalary() {
		return newSalary;
	}

	public void setNewSalary(String newSalary) {
		this.newSalary = newSalary;
	}

	public String getOldSalary() {
		return oldSalary;
	}

	public void setOldSalary(String oldSalary) {
		this.oldSalary = oldSalary;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getOverage() {
		return overage;
	}

	public void setOverage(String overage) {
		this.overage = overage;
	}

	public String getOverageSpouse() {
		return overageSpouse;
	}

	public void setOverageSpouse(String overageSpouse) {
		this.overageSpouse = overageSpouse;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPensionFund() {
		return pensionFund;
	}

	public void setPensionFund(String pensionFund) {
		this.pensionFund = pensionFund;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRmf() {
		return rmf;
	}

	public void setRmf(String rmf) {
		this.rmf = rmf;
	}

	public String getSeqData() {
		return seqData;
	}

	public void setSeqData(String seqData) {
		this.seqData = seqData;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getTeacherFund() {
		return teacherFund;
	}

	public void setTeacherFund(String teacherFund) {
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOldKlongChev() {
		return oldKlongChev;
	}

	public void setOldKlongChev(String oldKlongChev) {
		this.oldKlongChev = oldKlongChev;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getNewKlongChev() {
		return newKlongChev;
	}

	public void setNewKlongChev(String newKlongChev) {
		this.newKlongChev = newKlongChev;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getHealthFather() {
		return healthFather;
	}

	public void setHealthFather(String healthFather) {
		this.healthFather = healthFather;
	}

	public String getHandicappedDec() {
		return handicappedDec;
	}

	public void setHandicappedDec(String handicappedDec) {
		this.handicappedDec = handicappedDec;
	}

	public String getOldKc2000() {
		return oldKc2000;
	}

	public void setOldKc2000(String oldKc2000) {
		this.oldKc2000 = oldKc2000;
	}

	public String getNewKc2000() {
		return newKc2000;
	}

	public void setNewKc2000(String newKc2000) {
		this.newKc2000 = newKc2000;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDebtLifeSpouse() {
		return debtLifeSpouse;
	}

	public void setDebtLifeSpouse(String debtLifeSpouse) {
		this.debtLifeSpouse = debtLifeSpouse;
	}

}
