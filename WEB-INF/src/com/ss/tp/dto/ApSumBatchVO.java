package com.ss.tp.dto;

import java.io.Serializable;

import java.util.Date;


public class ApSumBatchVO implements Serializable {

	
	private String year;
	private String month;
	private String volume;
	private String self;
	private String oth;
	private String total;

	public ApSumBatchVO() {

	}

	public ApSumBatchVO( String year, String month,
			String volume, String self, String oth,String total) {
		
		this.year = year;
		this.month = month;
	    this.volume = volume;
	    this.self = self;
	    this.oth = oth;
	    this.total = total;
		
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getOth() {
		return oth;
	}

	public void setOth(String oth) {
		this.oth = oth;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


	
	
}
