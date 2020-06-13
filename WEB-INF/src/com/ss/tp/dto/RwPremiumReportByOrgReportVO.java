package com.ss.tp.dto;

import java.io.Serializable;

public class RwPremiumReportByOrgReportVO implements Serializable {
	private String ouCode;
	private Double year;
	private Double period;
	private String empCode;
	private String empName;
	private String orgDesc;
	private Double morDay;
	private Double aftDay;
	private Double evnDay;
	private Double morHour;
	private Double aftHour;
	private Double evnHour;
	private String flagPr;

	public Double getAftDay() {
		return aftDay;
	}

	public void setAftDay(Double aftDay) {
		this.aftDay = aftDay;
	}

	public Double getAftHour() {
		return aftHour;
	}

	public void setAftHour(Double aftHour) {
		this.aftHour = aftHour;
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

	public Double getEvnDay() {
		return evnDay;
	}

	public void setEvnDay(Double evnDay) {
		this.evnDay = evnDay;
	}

	public Double getEvnHour() {
		return evnHour;
	}

	public void setEvnHour(Double evnHour) {
		this.evnHour = evnHour;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public Double getMorDay() {
		return morDay;
	}

	public void setMorDay(Double morDay) {
		this.morDay = morDay;
	}

	public Double getMorHour() {
		return morHour;
	}

	public void setMorHour(Double morHour) {
		this.morHour = morHour;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

}
