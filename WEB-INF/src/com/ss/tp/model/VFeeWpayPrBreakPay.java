package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VFeeWpayPrBreakPay implements Serializable {
	private VFeeWpayPrBreakPayPK pk;
	private Double subOrg;
	private String subName;
	
	private Double deptSeq;
	private Double divSeq;
	
	private String divCode;
	private String divName;
	private Double areaSeq;
	private String areaCode;
	private String areaDesc;
	
	private Double secSeq;
	private String secCode;
	private String secDesc;
	private String orgDesc;
	
	private String empName;
	
	private String bankId;
	private String bankCode;
	
	private Double breakAmt;
	private String newBankId;
	
	private Double salary;
	private String inactive;
	
	
	

	public VFeeWpayPrBreakPay() {
	
	}




	public VFeeWpayPrBreakPay(VFeeWpayPrBreakPayPK pk, Double subOrg,
			String subName, Double deptSeq, Double divSeq, String divCode,
			String divName, Double areaSeq, String areaCode, String areaDesc,
			Double secSeq, String secCode, String secDesc, String orgDesc,
			String empName, String bankId,String bankCode, Double breakAmt, String newBankId,
			Double salary, String inactive) {
		
		this.pk = pk;
		this.subOrg = subOrg;
		this.subName = subName;
		this.deptSeq = deptSeq;
		this.divSeq = divSeq;
		this.divCode = divCode;
		this.divName = divName;
		this.areaSeq = areaSeq;
		this.areaCode = areaCode;
		this.areaDesc = areaDesc;
		this.secSeq = secSeq;
		this.secCode = secCode;
		this.secDesc = secDesc;
		this.orgDesc = orgDesc;
		this.empName = empName;
		this.bankId = bankId;
		this.bankCode = bankCode;
		this.breakAmt = breakAmt;
		this.newBankId = newBankId;
		this.salary = salary;
		this.inactive = inactive;
	}




	public VFeeWpayPrBreakPayPK getPk() {
		return pk;
	}




	public void setPk(VFeeWpayPrBreakPayPK pk) {
		this.pk = pk;
	}




	public Double getSubOrg() {
		return subOrg;
	}




	public void setSubOrg(Double subOrg) {
		this.subOrg = subOrg;
	}




	public String getSubName() {
		return subName;
	}




	public void setSubName(String subName) {
		this.subName = subName;
	}




	public Double getDeptSeq() {
		return deptSeq;
	}




	public void setDeptSeq(Double deptSeq) {
		this.deptSeq = deptSeq;
	}




	public Double getDivSeq() {
		return divSeq;
	}




	public void setDivSeq(Double divSeq) {
		this.divSeq = divSeq;
	}




	public String getDivCode() {
		return divCode;
	}




	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}




	public String getDivName() {
		return divName;
	}




	public void setDivName(String divName) {
		this.divName = divName;
	}




	public Double getAreaSeq() {
		return areaSeq;
	}




	public void setAreaSeq(Double areaSeq) {
		this.areaSeq = areaSeq;
	}




	public String getAreaCode() {
		return areaCode;
	}




	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}




	public String getAreaDesc() {
		return areaDesc;
	}




	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}




	public Double getSecSeq() {
		return secSeq;
	}




	public void setSecSeq(Double secSeq) {
		this.secSeq = secSeq;
	}




	public String getSecCode() {
		return secCode;
	}




	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}




	public String getSecDesc() {
		return secDesc;
	}




	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}




	public String getOrgDesc() {
		return orgDesc;
	}




	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}




	public String getEmpName() {
		return empName;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public String getBankId() {
		return bankId;
	}




	public void setBankId(String bankId) {
		this.bankId = bankId;
	}




	public String getBankCode() {
		return bankCode;
	}




	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}




	public Double getBreakAmt() {
		return breakAmt;
	}




	public void setBreakAmt(Double breakAmt) {
		this.breakAmt = breakAmt;
	}




	public String getNewBankId() {
		return newBankId;
	}




	public void setNewBankId(String newBankId) {
		this.newBankId = newBankId;
	}




	public Double getSalary() {
		return salary;
	}




	public void setSalary(Double salary) {
		this.salary = salary;
	}




	public String getInactive() {
		return inactive;
	}




	public void setInactive(String inactive) {
		this.inactive = inactive;
	}




	
}
