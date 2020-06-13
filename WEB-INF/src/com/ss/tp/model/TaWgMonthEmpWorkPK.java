/*
 * Created on 24 ¡.¤. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;

public class TaWgMonthEmpWorkPK implements Serializable {
	private String ouCode;
	private Integer workYear;
	private Integer workMonth;
	private String empCode;
	private Integer workSeq;

	/**
	 * @return Returns the empCode.
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            The empCode to set.
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return Returns the ouCode.
	 */
	public String getOuCode() {
		return ouCode;
	}

	/**
	 * @param ouCode
	 *            The ouCode to set.
	 */
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	/**
	 * @return Returns the workMonth.
	 */
	public Integer getWorkMonth() {
		return workMonth;
	}

	/**
	 * @param workMonth
	 *            The workMonth to set.
	 */
	public void setWorkMonth(Integer workMonth) {
		this.workMonth = workMonth;
	}

	/**
	 * @return Returns the workSeq.
	 */
	public Integer getWorkSeq() {
		return workSeq;
	}

	/**
	 * @param workSeq
	 *            The workSeq to set.
	 */
	public void setWorkSeq(Integer workSeq) {
		this.workSeq = workSeq;
	}

	/**
	 * @return Returns the workYear.
	 */
	public Integer getWorkYear() {
		return workYear;
	}

	/**
	 * @param workYear
	 *            The workYear to set.
	 */
	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}
}
