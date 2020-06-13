package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgIncomeDeduct implements Serializable {

	private WgIncomeDeductPK wgIncomeDeductPK;
	private String incDecName;
	private Integer decSeq;
	private String taxStatus;
	private String ProvidentStatus;
	private String tax5Status;
	private String accNo;
	private String inactive;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String incNodel;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
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

	public Integer getDecSeq() {
		return decSeq;
	}

	public void setDecSeq(Integer decSeq) {
		this.decSeq = decSeq;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}

	public String getIncNodel() {
		return incNodel;
	}

	public void setIncNodel(String incNodel) {
		this.incNodel = incNodel;
	}

	public String getProvidentStatus() {
		return ProvidentStatus;
	}

	public void setProvidentStatus(String providentStatus) {
		ProvidentStatus = providentStatus;
	}

	public String getTax5Status() {
		return tax5Status;
	}

	public void setTax5Status(String tax5Status) {
		this.tax5Status = tax5Status;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
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

	public WgIncomeDeductPK getWgIncomeDeductPK() {
		return wgIncomeDeductPK;
	}

	public void setWgIncomeDeductPK(WgIncomeDeductPK wgIncomeDeductPK) {
		this.wgIncomeDeductPK = wgIncomeDeductPK;
	}

}
