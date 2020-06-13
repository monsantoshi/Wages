package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayPrBreakPay implements Serializable {
	private FeeWpayPrBreakPayPK key;

	private String bankId;
	private String newBankId;
	private String bankCode;
	
	private Double breakAmt;
	private String inactive;
	
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private FeeWpayPrEmployee refFeeWpayPrEmployee;
	
	
	public FeeWpayPrBreakPay() {
		
	}


	public FeeWpayPrBreakPay(FeeWpayPrBreakPayPK key, String bankId,
			String newBankId, Double breakAmt, String inactive, String creBy,
			Date creDate, String updBy, Date updDate,String bankCode,
			FeeWpayPrEmployee prEmp) {
		super();
		this.key = key;
		this.bankId = bankId;
		this.bankCode = bankCode;
		this.newBankId = newBankId;
		this.breakAmt = breakAmt;
		this.inactive = inactive;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		refFeeWpayPrEmployee = prEmp;
	}


	public FeeWpayPrBreakPayPK getKey() {
		return key;
	}


	public void setKey(FeeWpayPrBreakPayPK key) {
		this.key = key;
	}


	public String getBankId() {
		return bankId;
	}


	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public String getNewBankId() {
		return newBankId;
	}


	public void setNewBankId(String newBankId) {
		this.newBankId = newBankId;
	}


	public Double getBreakAmt() {
		return breakAmt;
	}


	public void setBreakAmt(Double breakAmt) {
		this.breakAmt = breakAmt;
	}


	public String getInactive() {
		return inactive;
	}


	public void setInactive(String inactive) {
		this.inactive = inactive;
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


	public FeeWpayPrEmployee getRefFeeWpayPrEmployee() {
		return refFeeWpayPrEmployee;
	}


	public void setRefFeeWpayPrEmployee(FeeWpayPrEmployee refFeeWpayPrEmployee) {
		this.refFeeWpayPrEmployee = refFeeWpayPrEmployee;
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
}
