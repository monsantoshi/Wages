package com.ss.tp.dto;

public class DefaultYearSectionPnTextVO {
	private String year;
	private String section;
	private String period;
	private String month;
	private boolean confirm;

	public DefaultYearSectionPnTextVO() {
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public DefaultYearSectionPnTextVO(boolean confirm, String section,
			String year, String period, String month) {
		this.confirm = confirm;
		this.section = section;
		this.year = year;
		this.period = period;
		this.month = month;

	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
