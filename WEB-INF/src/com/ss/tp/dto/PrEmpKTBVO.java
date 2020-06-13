package com.ss.tp.dto;

import java.io.Serializable;

public class PrEmpKTBVO implements Serializable {
	private String ouCode;
	private String empCode;
        private String empName;
	private String memberNo;
	private String amount;
	private String totAmt;
	private String errRemark;

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}

	public String getErrRemark() {
		return errRemark;
	}

	public void setErrRemark(String errRemark) {
		this.errRemark = errRemark;
	}

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }
}
