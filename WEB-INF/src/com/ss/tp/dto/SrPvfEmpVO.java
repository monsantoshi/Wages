package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class SrPvfEmpVO implements Serializable {
	private String keySeq;
	private String ouCode;
	private String year;
	private String period;
	private String empCode;
	private String codeSeqWork;

	private String seqData;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;


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
	private String workCode;
	private String workDesc;
	private String zipCode;
	private String position;
	private Date iDate;
	private Date dDate;
	private String oldC;
	private String newC;
	private String oldJ;
	private String newJ;
	private String oldP;
	private String newP;
	
	private String master;
	

	public SrPvfEmpVO() {
	}




	public SrPvfEmpVO(String keySeq, String ouCode, String year, String period,
			String empCode, String codeSeqWork, String seqData, String creBy,
			Date creDate, String updBy, Date updDate, String divCode,
			String divDesc, String secCode, String secDesc, String fullName,
			String prefix, String name, String lastName, String areaCode,
			String areaDesc, String workCode, String workDesc, String zipCode,
			String position, Date iDate, Date dDate, String oldC, String newC,
			String oldJ, String newJ, String oldP, String newP , String master) {
		super();
		this.keySeq = keySeq;
		this.ouCode = ouCode;
		this.year = year;
		this.period = period;
		this.empCode = empCode;
		this.codeSeqWork = codeSeqWork;
		this.seqData = seqData;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.divCode = divCode;
		this.divDesc = divDesc;
		this.secCode = secCode;
		this.secDesc = secDesc;
		this.fullName = fullName;
		this.prefix = prefix;
		this.name = name;
		this.lastName = lastName;
		this.areaCode = areaCode;
		this.areaDesc = areaDesc;
		this.workCode = workCode;
		this.workDesc = workDesc;
		this.zipCode = zipCode;
		this.position = position;
		this.iDate = iDate;
		this.dDate = dDate;
		this.oldC = oldC;
		this.newC = newC;
		this.oldJ = oldJ;
		this.newJ = newJ;
		this.oldP = oldP;
		this.newP = newP;
		this.master = master;
	}




	public String getMaster() {
		return master;
	}




	public void setMaster(String master) {
		this.master = master;
	}




	public String getKeySeq() {
		return keySeq;
	}




	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}




	public String getOuCode() {
		return ouCode;
	}




	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}




	public String getYear() {
		return year;
	}




	public void setYear(String year) {
		this.year = year;
	}




	public String getPeriod() {
		return period;
	}




	public void setPeriod(String period) {
		this.period = period;
	}




	public String getEmpCode() {
		return empCode;
	}




	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}




	public String getCodeSeqWork() {
		return codeSeqWork;
	}




	public void setCodeSeqWork(String codeSeqWork) {
		this.codeSeqWork = codeSeqWork;
	}




	public String getSeqData() {
		return seqData;
	}




	public void setSeqData(String seqData) {
		this.seqData = seqData;
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




	public String getDivCode() {
		return divCode;
	}




	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}




	public String getDivDesc() {
		return divDesc;
	}




	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
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




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getPrefix() {
		return prefix;
	}




	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
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




	public String getWorkCode() {
		return workCode;
	}




	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}




	public String getWorkDesc() {
		return workDesc;
	}




	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}




	public String getZipCode() {
		return zipCode;
	}




	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}




	public String getPosition() {
		return position;
	}




	public void setPosition(String position) {
		this.position = position;
	}




	public Date getiDate() {
		return iDate;
	}




	public void setiDate(Date iDate) {
		this.iDate = iDate;
	}




	public Date getdDate() {
		return dDate;
	}




	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}




	public String getOldC() {
		return oldC;
	}




	public void setOldC(String oldC) {
		this.oldC = oldC;
	}




	public String getNewC() {
		return newC;
	}




	public void setNewC(String newC) {
		this.newC = newC;
	}




	public String getOldJ() {
		return oldJ;
	}




	public void setOldJ(String oldJ) {
		this.oldJ = oldJ;
	}




	public String getNewJ() {
		return newJ;
	}




	public void setNewJ(String newJ) {
		this.newJ = newJ;
	}




	public String getOldP() {
		return oldP;
	}




	public void setOldP(String oldP) {
		this.oldP = oldP;
	}




	public String getNewP() {
		return newP;
	}




	public void setNewP(String newP) {
		this.newP = newP;
	}
	

	
	

}
