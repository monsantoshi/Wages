package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class VFeeWpayPrBreakPayVO implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;
	private String empName;
	private Double deptSeq;
	private String divSeq;
	private String divCode;
	private String divName;
	private Double areaSeq;
	private String areaCode;
	private String areaDesc;
	private Double secSeq;
	private String secCode;
	private String secDesc;
	private String orgDesc;
	private String bankId;
	private String bankCode;
	private Double breakAmt;
	private String newBankId;
	private String inactive;
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public Double getYear() {
		return year;
	}
	public void setYear(Double year) {
		this.year = year;
	}
	public Double getPeriod() {
		return period;
	}
	public void setPeriod(Double period) {
		this.period = period;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Double getDeptSeq() {
		return deptSeq;
	}
	public void setDeptSeq(Double deptSeq) {
		this.deptSeq = deptSeq;
	}
	public String getDivSeq() {
		return divSeq;
	}
	public void setDivSeq(String divSeq) {
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
	public String getInactive() {
		return inactive;
	}
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}
	public VFeeWpayPrBreakPayVO(String ouCode, Double year, Double period,
			String empCode, String empName, Double deptSeq, String divSeq,
			String divCode, String divName, Double areaSeq, String areaCode,
			String areaDesc, Double secSeq, String secCode, String secDesc,
			String orgDesc, String bankId, Double breakAmt, String newBankId,
			String inactive,String bankCode) {
		
		this.ouCode = ouCode;
		this.year = year;
		this.period = period;
		this.empCode = empCode;
		this.empName = empName;
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
		this.bankId = bankId;
		this.breakAmt = breakAmt;
		this.newBankId = newBankId;
		this.inactive = inactive;
		this.bankCode = bankCode;
	}
	public VFeeWpayPrBreakPayVO() {
	
	}
	
	
	
}
