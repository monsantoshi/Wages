package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayPrIncomeDeductVO implements Serializable {
	private String ouCode;
	private String groupCode;
	private String incDecCode;
	private String incDecName;
	private Double decSeq;
	private String taxStatus;
	private String providentStatus;
	private String tax5Status;
	private String accNo;
	private String inactive;
	private String incModel;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public FeeWpayPrIncomeDeductVO() {

	}

	public FeeWpayPrIncomeDeductVO(String code, String code2, String code3, String no,
			String by, Date date, Double seq, String inactive, String name,
			String model, String status, String status2, String status3,
			String by2, Date date2) {

		// TODO Auto-generated constructor stub
		this.groupCode = code;
		this.incDecCode = code2;
		this.ouCode = code3;
		this.accNo = no;
		this.creBy = by;
		this.creDate = date;
		this.decSeq = seq;
		this.inactive = inactive;
		this.incDecName = name;
		this.incModel = model;
		this.providentStatus = status;
		this.tax5Status = status2;
		this.taxStatus = status3;
		this.updBy = by2;
		this.updDate = date2;
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

	public String getIncModel() {
		return incModel;
	}

	public void setIncModel(String incModel) {
		this.incModel = incModel;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getIncDecCode() {
		return incDecCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
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
