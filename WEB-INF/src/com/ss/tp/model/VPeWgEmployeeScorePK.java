package com.ss.tp.model;

import java.io.Serializable;

public class VPeWgEmployeeScorePK implements Serializable {
	private String ouCode;
	private Integer evaYear;
	private String empCode;

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
	 * @return Returns the evaYear.
	 */
	public Integer getEvaYear() {
		return evaYear;
	}

	/**
	 * @param evaYear
	 *            The evaYear to set.
	 */
	public void setEvaYear(Integer evaYear) {
		this.evaYear = evaYear;
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
}
