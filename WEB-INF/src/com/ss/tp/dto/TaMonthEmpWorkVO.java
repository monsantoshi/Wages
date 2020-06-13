/*
 * Created on 25 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author airsenaL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaMonthEmpWorkVO implements Serializable {
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

	/* start PnOrganization */
	private String divCode;
	private String divDesc;
	private String divShort;
	private String secCode;
	private String secDesc;
	private String orgWorkCode;// name same TaMonthEmpWork that is workCode
	private String orgWorkDesc;// name same TaMonthEmpWork that is workDesc
	private String areaCode;
	private String areaDesc;
	/* end PnOrganization */

	private String fullName;

	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return Returns the evaType.
	 */
	public String getEvaType() {
		return evaType;
	}

	/**
	 * @param evaType
	 *            The evaType to set.
	 */
	public void setEvaType(String evaType) {
		this.evaType = evaType;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the inactive.
	 */
	public String getInactive() {
		return inactive;
	}

	/**
	 * @param inactive
	 *            The inactive to set.
	 */
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return Returns the pnTransfer.
	 */
	public String getPnTransfer() {
		return pnTransfer;
	}

	/**
	 * @param pnTransfer
	 *            The pnTransfer to set.
	 */
	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
	}

	/**
	 * @return Returns the preName.
	 */
	public String getPreName() {
		return preName;
	}

	/**
	 * @param preName
	 *            The preName to set.
	 */
	public void setPreName(String preName) {
		this.preName = preName;
	}

	/**
	 * @return Returns the totalDays.
	 */
	public Double getTotalDays() {
		return totalDays;
	}

	/**
	 * @param totalDays
	 *            The totalDays to set.
	 */
	public void setTotalDays(Double totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @return Returns the totalTime.
	 */
	public Integer getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime
	 *            The totalTime to set.
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

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
	 * @return Returns the workDesc.
	 */
	public String getWorkDesc() {
		return workDesc;
	}

	/**
	 * @param workDesc
	 *            The workDesc to set.
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
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

	/**
	 * @return Returns the creBy.
	 */
	public String getCreBy() {
		return creBy;
	}

	/**
	 * @param creBy
	 *            The creBy to set.
	 */
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	/**
	 * @return Returns the creDate.
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            The creDate to set.
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return Returns the updBy.
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy
	 *            The updBy to set.
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return Returns the updDate.
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            The updDate to set.
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String toString() {
		return "<" + ">";
	}

	/**
	 * @return Returns the prefixName.
	 */
	public String getPrefixName() {
		return prefixName;
	}

	/**
	 * @param prefixName
	 *            The prefixName to set.
	 */
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	/**
	 * @return Returns the divCode.
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode
	 *            The divCode to set.
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return Returns the divDesc.
	 */
	public String getDivDesc() {
		return divDesc;
	}

	/**
	 * @param divDesc
	 *            The divDesc to set.
	 */
	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	/**
	 * @return Returns the secCode.
	 */
	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode
	 *            The secCode to set.
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	/**
	 * @return Returns the secDesc.
	 */
	public String getSecDesc() {
		return secDesc;
	}

	/**
	 * @param secDesc
	 *            The secDesc to set.
	 */
	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	/**
	 * @return Returns the orgWorkCode.
	 */
	public String getOrgWorkCode() {
		return orgWorkCode;
	}

	/**
	 * @param orgWorkCode
	 *            The orgWorkCode to set.
	 */
	public void setOrgWorkCode(String orgWorkCode) {
		this.orgWorkCode = orgWorkCode;
	}

	/**
	 * @return Returns the orgWorkDesc.
	 */
	public String getOrgWorkDesc() {
		return orgWorkDesc;
	}

	/**
	 * @param orgWorkDesc
	 *            The orgWorkDesc to set.
	 */
	public void setOrgWorkDesc(String orgWorkDesc) {
		this.orgWorkDesc = orgWorkDesc;
	}

	/**
	 * @return Returns the areaCode.
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            The areaCode to set.
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return Returns the areaDesc.
	 */
	public String getAreaDesc() {
		return areaDesc;
	}

	/**
	 * @param areaDesc
	 *            The areaDesc to set.
	 */
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getDivShort() {
		return divShort;
	}

	public void setDivShort(String divShort) {
		this.divShort = divShort;
	}

}
