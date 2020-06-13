package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VFeeWgRwVinai implements Serializable {
	private VFeeWgRwVinaiPK pk;
	private String empName;
	private String orgDesc;
	private Double decSal;
	private Double newSalary;
	private String cutSal;
	private Double cutSalPercent;
	private String absYear1;
	private Double absDay1;
	private String absYear2;
	private Double absDay2;
	private Date startDateQut;
	private Date endDateQut;
	private Double totalDay;
	private String periodWork;
	private Double decDay;
	private String flagPr;
	private Double decSalMonth;
	private Double cutSalMonth;
	private String approveFlag;

	public VFeeWgRwVinai() {
	}

	public VFeeWgRwVinai(VFeeWgRwVinaiPK pk, String empName, String orgDesc,
			Double decSal, Double newSalary, String cutSal,
			Double cutSalPercent, String absYear1, Double absDay1,
			String absYear2, Double absDay2, Date startDateQut,
			Date endDateQut, Double totalDay, String periodWork, Double decDay,
			String flagPr,String appF) {
		this.pk = pk;
		this.empName = empName;
		this.orgDesc = orgDesc;
		this.decSal = decSal;
		this.setNewSalary(newSalary);
		this.cutSal = cutSal;
		this.cutSalPercent = cutSalPercent;
		this.absYear1 = absYear1;
		this.absDay1 = absDay1;
		this.absYear2 = absYear2;
		this.absDay2 = absDay2;
		this.startDateQut = startDateQut;
		this.endDateQut = endDateQut;
		this.totalDay = totalDay;
		this.periodWork = periodWork;
		this.decDay = decDay;
		this.flagPr = flagPr;
		this.approveFlag = appF;
	}

	public Double getAbsDay1() {
		return absDay1;
	}

	public void setAbsDay1(Double absDay1) {
		this.absDay1 = absDay1;
	}

	public Double getAbsDay2() {
		return absDay2;
	}

	public void setAbsDay2(Double absDay2) {
		this.absDay2 = absDay2;
	}

	public String getAbsYear1() {
		return absYear1;
	}

	public void setAbsYear1(String absYear1) {
		this.absYear1 = absYear1;
	}

	public String getAbsYear2() {
		return absYear2;
	}

	public void setAbsYear2(String absYear2) {
		this.absYear2 = absYear2;
	}

	public String getCutSal() {
		return cutSal;
	}

	public void setCutSal(String cutSal) {
		this.cutSal = cutSal;
	}

	public Double getDecDay() {
		return decDay;
	}

	public void setDecDay(Double decDay) {
		this.decDay = decDay;
	}

	public Double getDecSal() {
		return decSal;
	}

	public void setDecSal(Double decSal) {
		this.decSal = decSal;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEndDateQut() {
		return endDateQut;
	}

	public void setEndDateQut(Date endDateQut) {
		this.endDateQut = endDateQut;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getPeriodWork() {
		return periodWork;
	}

	public void setPeriodWork(String periodWork) {
		this.periodWork = periodWork;
	}

	public VFeeWgRwVinaiPK getPk() {
		return pk;
	}

	public void setPk(VFeeWgRwVinaiPK pk) {
		this.pk = pk;
	}

	public Date getStartDateQut() {
		return startDateQut;
	}

	public void setStartDateQut(Date startDateQut) {
		this.startDateQut = startDateQut;
	}

	public Double getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Double totalDay) {
		this.totalDay = totalDay;
	}

	public Double getDecSalMonth() {
		return decSalMonth;
	}

	public void setDecSalMonth(Double decSalMonth) {
		this.decSalMonth = decSalMonth;
	}

	public void setCutSalPercent(Double cutSalPercent) {
		this.cutSalPercent = cutSalPercent;
	}

	public Double getCutSalPercent() {
		return cutSalPercent;
	}

	public Double getCutSalMonth() {
		return cutSalMonth;
	}

	public void setCutSalMonth(Double cutSalMonth) {
		this.cutSalMonth = cutSalMonth;
	}

	public void setNewSalary(Double newSalary) {
		this.newSalary = newSalary;
	}

	public Double getNewSalary() {
		return newSalary;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

}
