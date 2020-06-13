package com.ss.tag;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateBean implements Serializable {
	private Date date;
	private String[] monthName = { "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
			"พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน",
			"ธันววาคม" };

	private int day = -1;
	private int month = -1;
	private int year = -1;
	private int yearCurrent = -1;

	public DateBean() {
		this.date = new Date();
	}

	public DateBean(Date date) {
		this.date = date;
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			date.getDay();
			this.setDay(cal.get(Calendar.DAY_OF_MONTH));
			this.setMonth(cal.get(Calendar.MONTH));
			this.setYear(cal.get(Calendar.YEAR));
		}
	}

	public DateBean(int year, int month, int day) {
		this(new GregorianCalendar(year, month, day).getTime());
	}

	public int getAmountDay(int m, int y) {
		return (m == 1 ? (y % 4 == 0 ? y > 1581 ? ((y % 100 == 0) && (y % 400 != 0)) ? 0
				: 1
				: 1
				: 0)
				: ((m == 3 || m == 5 || m == 8 || m == 10) ? 2 : 3)) + 28;
	}

	/**
	 * @return Returns the day.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            The day to set.
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return Returns the month.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            The month to set.
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return Returns the year.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            The year to set.
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return Returns the monthName.
	 */
	public String[] getMonthName() {
		return monthName;
	}

	/**
	 * @param monthName
	 *            The monthName to set.
	 */
	public void setMonthName(String[] monthName) {
		this.monthName = monthName;
	}

	/**
	 * @return
	 */
	public int getYearCurrent() {
		if (yearCurrent == -1)
			yearCurrent = new Date().getYear() + 1900;
		return yearCurrent;
	}

	public void setYearCerrent(int i) {
		yearCurrent = i;
	}

	public Date getValue() {
		if (this.day == -1 || this.day == 0)
			return null;
		else {
			Calendar cal = new GregorianCalendar(this.year, this.month,
					this.day);
			return cal.getTime();
		}
	}
}
