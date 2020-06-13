package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class DateUtil implements Serializable {
	private Date startDate;
	private Date endDate;

	public DateUtil() {

	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
