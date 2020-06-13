/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaWgMonthEmpWorkVO implements Serializable {
	private String ouCode;
	private Integer workYear;
	private Integer workMonth;
	private String empCode;
	private Integer workSeq;
	private String workCode;
	private Double totalDays;
	private Integer totalTime;
	private String pnTransfer;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	/* start taStatusWork */
	private String workDesc;
	private String evaType;
	private String inactive;
	/* end taStatusWork */

	/* start pnEmployee */
	private String preName;
	private String firstName;
	private String lastName;
	/* end pnEmployee */

	/* start dbPreSuff */
	private String prefixName;

	/* end dbPreSuff */

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEvaType() {
		return evaType;
	}

	public void setEvaType(String evaType) {
		this.evaType = evaType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getPnTransfer() {
		return pnTransfer;
	}

	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public Double getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Double totalDays) {
		this.totalDays = totalDays;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getWorkDesc() {

		return workDesc;
	}

	// return workDesc;

	public void setWorkDesc(String workDesc) {

		this.workDesc = workDesc;
	}

	public Integer getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(Integer workMonth) {
		this.workMonth = workMonth;
	}

	public Integer getWorkSeq() {
		return workSeq;
	}

	public void setWorkSeq(Integer workSeq) {
		this.workSeq = workSeq;
	}

	public Integer getWorkYear() {
		return workYear;
	}

	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}
}
