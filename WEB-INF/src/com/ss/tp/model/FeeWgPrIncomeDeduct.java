package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWgPrIncomeDeduct implements Serializable {
	private FeeWgPrIncomeDeductPK pk;
	private String incDecName;
	private Double decSeq;
	private String taxStatus;
	private String providentStatus;
	private String tax5Status;
	private String accNo;
	private String inactive;
	private String incNodel;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private Double maxAmt;
	private String flagWeb;

	public String getFlagWeb() {
		return flagWeb;
	}

	public void setFlagWeb(String flagWeb) {
		this.flagWeb = flagWeb;
	}

	public Double getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(Double maxAmt) {
		this.maxAmt = maxAmt;
	}

	public FeeWgPrIncomeDeduct() {

	}

	public FeeWgPrIncomeDeduct(String no, String by, Date date, Double seq,
			String inactive, String name, String nodel, FeeWgPrIncomeDeductPK pk,
			String status, String status2, String status3, String by2,
			Date date2, String flagWeb) {

		// TODO Auto-generated constructor stub
		this.accNo = no;
		this.creBy = by;
		this.creDate = date;
		this.decSeq = seq;
		this.inactive = inactive;
		this.incDecName = name;
		this.incNodel = nodel;
		this.pk = pk;
		this.providentStatus = status;
		this.tax5Status = status2;
		this.taxStatus = status3;
		this.updBy = by2;
		this.updDate = date2;
		this.flagWeb = flagWeb;
	}

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

	public Double getDecSeq() {
		return decSeq;
	}

	public void setDecSeq(Double decSeq) {
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

	public FeeWgPrIncomeDeductPK getPk() {
		return pk;
	}

	public void setPk(FeeWgPrIncomeDeductPK pk) {
		this.pk = pk;
	}

	public String getProvidentStatus() {
		return providentStatus;
	}

	public void setProvidentStatus(String providentStatus) {
		this.providentStatus = providentStatus;
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

}
