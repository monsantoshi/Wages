package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PeEmployeeScoreVO implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String empCode;
	private Double evaTotal;
	private Double scoreAdjust;
	private Double scoreNet;
	private Double formScore;
	private String evaEmp1;
	private String evaEmp2;
	private String evaEmp3;
	private String pnTransfer;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String ename;
	private Long CodeSeq;
	private String confirm;

	public Double getFormScore() {
		return formScore;
	}

	public void setFormScore(Double formScore) {
		this.formScore = formScore;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Long getCodeSeq() {
		return CodeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		CodeSeq = codeSeq;
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

	public String getEvaEmp1() {
		return evaEmp1;
	}

	public void setEvaEmp1(String evaEmp1) {
		this.evaEmp1 = evaEmp1;
	}

	public String getEvaEmp2() {
		return evaEmp2;
	}

	public void setEvaEmp2(String evaEmp2) {
		this.evaEmp2 = evaEmp2;
	}

	public String getEvaEmp3() {
		return evaEmp3;
	}

	public void setEvaEmp3(String evaEmp3) {
		this.evaEmp3 = evaEmp3;
	}

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public Double getEvaTotal() {
		return evaTotal;
	}

	public void setEvaTotal(Double evaTotal) {
		this.evaTotal = evaTotal;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
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

	public Double getScoreAdjust() {
		return scoreAdjust;
	}

	public void setScoreAdjust(Double scoreAdjust) {
		this.scoreAdjust = scoreAdjust;
	}

	public Double getScoreNet() {
		return scoreNet;
	}

	public void setScoreNet(Double scoreNet) {
		this.scoreNet = scoreNet;
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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

}
