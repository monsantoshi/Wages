package com.ss.tp.dto;

import java.io.Serializable;

public class PnPromoteVO implements Serializable {
	private String section;
	private String year;
	private String month;

	public PnPromoteVO() {

	}

	public PnPromoteVO(String section, String year, String month) {
		this.section = section;
		this.year = year;
		this.month = month;

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
