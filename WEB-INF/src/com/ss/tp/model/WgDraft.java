package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgDraft implements Serializable {
	private WgDraftPk pk;
	private Long codeSeq;
	private Long hisSeq;
	private String preName;
	private String firstName;
	private String lastName;
	private String gworkCode;
	private String dutyCode;
	private Double wage;
	private Double wageDraft;
	private Double score;
	private Double perDraft;
	private String remark;
	private String draftStatus;
	private String punishStatus;
	private String noDraft;
	private Date idate;
	private String instructNo;
	private String updateFlag;
	private String confirmFlag;
	private Date statusDate;
	private Double lastPerDraft1;
	private Double lastPerDraft2;
	private Double lastPerDraft3;
	private Double lastPerDraft4;
	private Double lastPerDraft5;
	private Double illDay;
	private Double busDay;
	private Double illDocDay;
	private Double lateDay;
	private Double bornDay;
	private Double bornBusDay;
	private Double budthDay;
	private Double absenceDay;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private PnOrganization refPnOrganization;
	private DbPreSuff refDbPreSuff;

	public WgDraft() {
	}

	public WgDraft(WgDraftPk pk, Long codeSeq, Long hisSeq, String preName,
			String firstName, String lastName, String gworkCode,
			String dutyCode, Double wage, Double wageDraft, Double score,
			Double perDraft, String remark, String draftStatus,
			String punishStatus, String noDraft, Date idate, String instructNo,
			String updateFlag, String confirmFlag, Date statusDate,
			Double lastPerDraft1, Double lastPerDraft2, Double lastPerDraft3,
			Double lastPerDraft4, Double lastPerDraft5, Double illDay,
			Double busDay, Double illDocDay, Double lateDay, Double bornDay,
			Double bornBusDay, Double budthDay, Double absenceDay,
			String creBy, Date creDate, String updBy, Date updDate,
			PnOrganization refPnOrganization, DbPreSuff refDbPreSuff) {
		this.pk = pk;
		this.codeSeq = codeSeq;
		this.hisSeq = hisSeq;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gworkCode = gworkCode;
		this.dutyCode = dutyCode;
		this.wage = wage;
		this.wageDraft = wageDraft;
		this.score = score;
		this.perDraft = perDraft;
		this.remark = remark;
		this.draftStatus = draftStatus;
		this.punishStatus = punishStatus;
		this.noDraft = noDraft;
		this.idate = idate;
		this.instructNo = instructNo;
		this.updateFlag = updateFlag;
		this.confirmFlag = confirmFlag;
		this.statusDate = statusDate;
		this.lastPerDraft1 = lastPerDraft1;
		this.lastPerDraft2 = lastPerDraft2;
		this.lastPerDraft3 = lastPerDraft3;
		this.lastPerDraft4 = lastPerDraft4;
		this.lastPerDraft5 = lastPerDraft5;
		this.illDay = illDay;
		this.busDay = busDay;
		this.illDocDay = illDocDay;
		this.lateDay = lateDay;
		this.bornDay = bornDay;
		this.bornBusDay = bornBusDay;
		this.budthDay = budthDay;
		this.absenceDay = absenceDay;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.refPnOrganization = refPnOrganization;
		this.refDbPreSuff = refDbPreSuff;
	}

	public Double getAbsenceDay() {
		return absenceDay;
	}

	public void setAbsenceDay(Double absenceDay) {
		this.absenceDay = absenceDay;
	}

	public Double getBornBusDay() {
		return bornBusDay;
	}

	public void setBornBusDay(Double bornBusDay) {
		this.bornBusDay = bornBusDay;
	}

	public Double getBornDay() {
		return bornDay;
	}

	public void setBornDay(Double bornDay) {
		this.bornDay = bornDay;
	}

	public Double getBudthDay() {
		return budthDay;
	}

	public void setBudthDay(Double budthDay) {
		this.budthDay = budthDay;
	}

	public Double getBusDay() {
		return busDay;
	}

	public void setBusDay(Double busDay) {
		this.busDay = busDay;
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

	public String getDraftStatus() {
		return draftStatus;
	}

	public void setDraftStatus(String draftStatus) {
		this.draftStatus = draftStatus;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public Long getHisSeq() {
		return hisSeq;
	}

	public void setHisSeq(Long hisSeq) {
		this.hisSeq = hisSeq;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public Double getIllDay() {
		return illDay;
	}

	public void setIllDay(Double illDay) {
		this.illDay = illDay;
	}

	public Double getIllDocDay() {
		return illDocDay;
	}

	public void setIllDocDay(Double illDocDay) {
		this.illDocDay = illDocDay;
	}

	public String getInstructNo() {
		return instructNo;
	}

	public void setInstructNo(String instructNo) {
		this.instructNo = instructNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getLastPerDraft1() {
		return lastPerDraft1;
	}

	public void setLastPerDraft1(Double lastPerDraft1) {
		this.lastPerDraft1 = lastPerDraft1;
	}

	public Double getLastPerDraft2() {
		return lastPerDraft2;
	}

	public void setLastPerDraft2(Double lastPerDraft2) {
		this.lastPerDraft2 = lastPerDraft2;
	}

	public Double getLastPerDraft3() {
		return lastPerDraft3;
	}

	public void setLastPerDraft3(Double lastPerDraft3) {
		this.lastPerDraft3 = lastPerDraft3;
	}

	public Double getLastPerDraft4() {
		return lastPerDraft4;
	}

	public void setLastPerDraft4(Double lastPerDraft4) {
		this.lastPerDraft4 = lastPerDraft4;
	}

	public Double getLastPerDraft5() {
		return lastPerDraft5;
	}

	public void setLastPerDraft5(Double lastPerDraft5) {
		this.lastPerDraft5 = lastPerDraft5;
	}

	public Double getLateDay() {
		return lateDay;
	}

	public void setLateDay(Double lateDay) {
		this.lateDay = lateDay;
	}

	public String getNoDraft() {
		return noDraft;
	}

	public void setNoDraft(String noDraft) {
		this.noDraft = noDraft;
	}

	public Double getPerDraft() {
		return perDraft;
	}

	public void setPerDraft(Double perDraft) {
		this.perDraft = perDraft;
	}

	public WgDraftPk getPk() {
		return pk;
	}

	public void setPk(WgDraftPk pk) {
		this.pk = pk;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPunishStatus() {
		return punishStatus;
	}

	public void setPunishStatus(String punishStatus) {
		this.punishStatus = punishStatus;
	}

	public DbPreSuff getRefDbPreSuff() {
		return refDbPreSuff;
	}

	public void setRefDbPreSuff(DbPreSuff refDbPreSuff) {
		this.refDbPreSuff = refDbPreSuff;
	}

	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}

	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
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

	public Double getWage() {
		return wage;
	}

	public void setWage(Double wage) {
		this.wage = wage;
	}

	public Double getWageDraft() {
		return wageDraft;
	}

	public void setWageDraft(Double wageDraft) {
		this.wageDraft = wageDraft;
	}

}
