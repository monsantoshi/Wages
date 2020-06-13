/*
 * Created on 29 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VTaMonthEmpWorkPK implements Serializable {
	private String ouCode;
	private Integer workYear;
	private String empCode;
	private String workCode;

	/**
	 * @return Returns the workCode.
	 */
	public String getWorkCode() {
		return workCode;
	}

	/**
	 * @param workCode
	 *            The workCode to set.
	 */
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

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
