package com.ss.tp.dto;

public class DefaultYearSectionVO {
	private String year;
	private String section;
	private String period;
	private String month;
	private String volumeSet;
	private boolean confirm;

	public DefaultYearSectionVO() {
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

	public DefaultYearSectionVO(boolean confirm, String section, String year,
			String period, String month,String volumeSet) {
		this.confirm = confirm;
		this.section = section;
		this.year = year;
		this.period = period;
		this.month = month;
		this.volumeSet = volumeSet;

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

	public String getVolumeSet() {
		return volumeSet;
	}

	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
	}

}
