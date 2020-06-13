package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwDanger implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	// ============Create By Kiet=============
	private String areaCode;
	private String areaDesc;
	private String divCode;
	private String divDesc;
	private String secCode;
	private String secDesc;
	private String ename;
	private String deptDesc; // nvl(div_code, sec_code) || '' || div_desc ||
	// sec_desc
	private String flagDesc;
	private String orgName;
	private PnEmployee refPnEmployee;
	private PnOrganization refPnOrganization;
	// =======================================

	private Double fullDay;
	private Double halfDay;
	private Double totalAmt;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public RwDanger() {

	}

	public RwDanger(Long seq, String flag, String by, Date date, String code,
			String pr, Double day, Double day2, Long seq2, String code2,
			Double pr2, Double work, Double data, Double amt, String by2,
			Date date2, Double pr3, Double work2) {
		// TODO Auto-generated constructor stub
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.flagPr = pr;
		this.fullDay = day;
		this.halfDay = day2;
		this.keySeq = seq2;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.totalAmt = amt;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

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

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public Double getFullDay() {
		return fullDay;
	}

	public void setFullDay(Double fullDay) {
		this.fullDay = fullDay;
	}

	public Double getHalfDay() {
		return halfDay;
	}

	public void setHalfDay(Double halfDay) {
		this.halfDay = halfDay;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Double getPeriodPr() {
		return periodPr;
	}

	public void setPeriodPr(Double periodPr) {
		this.periodPr = periodPr;
	}

	public Double getPeriodWork() {
		return periodWork;
	}

	public void setPeriodWork(Double periodWork) {
		this.periodWork = periodWork;
	}

	public Double getSeqData() {
		return seqData;
	}

	public void setSeqData(Double seqData) {
		this.seqData = seqData;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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

	public Double getYearPr() {
		return yearPr;
	}

	public void setYearPr(Double yearPr) {
		this.yearPr = yearPr;
	}

	public Double getYearWork() {
		return yearWork;
	}

	public void setYearWork(Double yearWork) {
		this.yearWork = yearWork;
	}

	/**
	 * @return Returns the deptDesc.
	 */
	public String getDeptDesc() {
		return deptDesc;
	}

	/**
	 * @param deptDesc
	 *            The deptDesc to set.
	 */
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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
	 * @return Returns the ename.
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * @param ename
	 *            The ename to set.
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * @return Returns the flagDesc.
	 */
	public String getFlagDesc() {
		return flagDesc;
	}

	/**
	 * @param flagDesc
	 *            The flagDesc to set.
	 */
	public void setFlagDesc(String flagDesc) {
		this.flagDesc = flagDesc;
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
	 * @return Returns the refPnEmployee.
	 */
	public PnEmployee getRefPnEmployee() {
		return refPnEmployee;
	}

	/**
	 * @param refPnEmployee
	 *            The refPnEmployee to set.
	 */
	public void setRefPnEmployee(PnEmployee refPnEmployee) {
		this.refPnEmployee = refPnEmployee;
	}

	/**
	 * @return Returns the orgName.
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            The orgName to set.
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return Returns the refPnOrganize.
	 */
	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}

	/**
	 * @param refPnOrganize
	 *            The refPnOrganize to set.
	 */
	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
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
}
