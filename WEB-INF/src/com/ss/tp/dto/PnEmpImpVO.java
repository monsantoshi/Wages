package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PnEmpImpVO implements Serializable {
	private String ouCode;
	private String empCode;
	private String empName;
	private String mPosition;
	private String mDivdesc;
	private String mSecdesc;
	private String mHeader;
	private Date iDate;

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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMPosition() {
		return mPosition;
	}

	public void setMPosition(String position) {
		mPosition = position;
	}

	public String getMDivdesc() {
		return mDivdesc;
	}

	public void setMDivdesc(String divdesc) {
		mDivdesc = divdesc;
	}

	public String getMSecdesc() {
		return mSecdesc;
	}

	public void setMSecdesc(String secdesc) {
		mSecdesc = secdesc;
	}

	public String getMHeader() {
		return mHeader;
	}

	public void setMHeader(String header) {
		mHeader = header;
	}

	public Date getIDate() {
		return iDate;
	}

	public void setIDate(Date date) {
		iDate = date;
	}

}
