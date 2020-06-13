package com.ss.tp.model;

import java.io.Serializable;

public class VPeWgEmployeeScore implements Serializable {
	private VPeWgEmployeeScorePK vpeWgEmployeeScorePK;
	private String empName;
	private Integer codeSeq;
	private String orgCode;
	private String orgDesc;
	private String levelCode;
	private String areaCode;
	private String areaDesc;
	private String divCode;
	private String divDesc;
	private String secCode;
	private String secDesc;
	private String workCode;
	private String workDesc;
	private Double score1;
	private Double score2;
	private Double scoreAvg;
	private Double scoreAdjust;
	private Double scoreNet;

	/**
	 * @return Returns the codeSeq.
	 */
	public Integer getCodeSeq() {
		return codeSeq;
	}

	/**
	 * @param codeSeq
	 *            The codeSeq to set.
	 */
	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}

	/**
	 * @return Returns the orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode
	 *            The orgCode to set.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return Returns the orgDesc.
	 */
	public String getOrgDesc() {
		return orgDesc;
	}

	/**
	 * @param orgDesc
	 *            The orgDesc to set.
	 */
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	/**
	 * @return Returns the scoreAdjust.
	 */
	public Double getScoreAdjust() {
		return scoreAdjust;
	}

	/**
	 * @param scoreAdjust
	 *            The scoreAdjust to set.
	 */
	public void setScoreAdjust(Double scoreAdjust) {
		this.scoreAdjust = scoreAdjust;
	}

	/**
	 * @return Returns the scoreAvg.
	 */
	public Double getScoreAvg() {
		return scoreAvg;
	}

	/**
	 * @param scoreAvg
	 *            The scoreAvg to set.
	 */
	public void setScoreAvg(Double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	/**
	 * @return Returns the scoreNet.
	 */
	public Double getScoreNet() {
		return scoreNet;
	}

	/**
	 * @param scoreNet
	 *            The scoreNet to set.
	 */
	public void setScoreNet(Double scoreNet) {
		this.scoreNet = scoreNet;
	}

	/**
	 * @return Returns the score1.
	 */
	public Double getScore1() {
		return score1;
	}

	/**
	 * @param score1
	 *            The score1 to set.
	 */
	public void setScore1(Double score1) {
		this.score1 = score1;
	}

	/**
	 * @return Returns the score2.
	 */
	public Double getScore2() {
		return score2;
	}

	/**
	 * @param score2
	 *            The score2 to set.
	 */
	public void setScore2(Double score2) {
		this.score2 = score2;
	}

	/**
	 * @return Returns the empName.
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            The empName to set.
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return Returns the vpeEmployeeScorePK.
	 */
	public VPeWgEmployeeScorePK getVpeWgEmployeeScorePK() {
		return vpeWgEmployeeScorePK;
	}

	/**
	 * @param vpeEmployeeScorePK
	 *            The vpeEmployeeScorePK to set.
	 */
	public void setVpeWgEmployeeScorePK(
			VPeWgEmployeeScorePK vpeWgEmployeeScorePK) {
		this.vpeWgEmployeeScorePK = vpeWgEmployeeScorePK;
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
	 * @return Returns the levelCode.
	 */
	public String getLevelCode() {
		return levelCode;
	}

	/**
	 * @param levelCode
	 *            The levelCode to set.
	 */
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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
