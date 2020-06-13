package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PeWgEmpScore implements Serializable {
	private PeWgEmpScorePK pk;
	private Double evaTotal;
	private Double scoreAdjust;
	private Double scoreNet;
	private String evaEmp1;
	private String evaEmp2;
	private String evaEmp3;
	private WgEmployee emp;
	private PnEmployee qevaEmp1;
	private PnEmployee qevaEmp2;
	private PnEmployee qevaEmp3;
	private String pnTransfer;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private Date statusDate;
	private Set peWgEmpScoreyDtl;

	public PeWgEmpScore() {

	}

	public PeWgEmpScore(PeWgEmpScorePK pk, Double evaTotal, Double scoreAdjust,
			Double scoreNet, String evaEmp1, String evaEmp2, String evaEmp3,
			WgEmployee emp, PnEmployee qevaEmp1, PnEmployee qevaEmp2,
			PnEmployee qevaEmp3, String pnTransfer, String creBy, Date creDate,
			String updBy, Date updDate, Date statusDate, Set peWgEmpScoreyDtl) {
		this.pk = pk;
		this.evaTotal = evaTotal;
		this.scoreAdjust = scoreAdjust;
		this.scoreNet = scoreNet;
		this.evaEmp1 = evaEmp1;
		this.evaEmp2 = evaEmp2;
		this.evaEmp3 = evaEmp3;
		this.emp = emp;
		this.qevaEmp1 = qevaEmp1;
		this.qevaEmp2 = qevaEmp2;
		this.qevaEmp3 = qevaEmp3;
		this.pnTransfer = pnTransfer;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.statusDate = statusDate;
		this.peWgEmpScoreyDtl = peWgEmpScoreyDtl;
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

	public WgEmployee getEmp() {
		return emp;
	}

	public void setEmp(WgEmployee emp) {
		this.emp = emp;
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

	public Double getEvaTotal() {
		return evaTotal;
	}

	public void setEvaTotal(Double evaTotal) {
		this.evaTotal = evaTotal;
	}

	public PeWgEmpScorePK getPk() {
		return pk;
	}

	public void setPk(PeWgEmpScorePK pk) {
		this.pk = pk;
	}

	public String getPnTransfer() {
		return pnTransfer;
	}

	public void setPnTransfer(String pnTransfer) {
		this.pnTransfer = pnTransfer;
	}

	public PnEmployee getQevaEmp1() {
		return qevaEmp1;
	}

	public void setQevaEmp1(PnEmployee qevaEmp1) {
		this.qevaEmp1 = qevaEmp1;
	}

	public PnEmployee getQevaEmp2() {
		return qevaEmp2;
	}

	public void setQevaEmp2(PnEmployee qevaEmp2) {
		this.qevaEmp2 = qevaEmp2;
	}

	public PnEmployee getQevaEmp3() {
		return qevaEmp3;
	}

	public void setQevaEmp3(PnEmployee qevaEmp3) {
		this.qevaEmp3 = qevaEmp3;
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

	public Set getPeWgEmpScoreyDtl() {
		return peWgEmpScoreyDtl;
	}

	public void setPeWgEmpScoreyDtl(Set peWgEmpScoreyDtl) {
		this.peWgEmpScoreyDtl = peWgEmpScoreyDtl;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

}
