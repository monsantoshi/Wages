package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class FeeWgRwPremiumReportVO implements Serializable {
	private Long keySeq;
	private String ouCode;
	private Double yearPr;
	private Double periodPr;
	private String empCode;

	private Double yearWork;
	private Double periodWork;
	private Long codeSeq;

	private Double morDay;
	private Double aftDay;
	private Double evnDay;
	private Double morHour;
	private Double aftHour;
	private Double evnHour;
	private Double amtMor;
	private Double amtAft;
	private Double amtEvn;

	private String flagPr;
	private Double seqData;
	private String confirmFlag;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;

	public FeeWgRwPremiumReportVO() {

	}

	public FeeWgRwPremiumReportVO(Double day, Double hour, Double aft, Double evn,
			Double mor, Long seq, String flag, String by, Date date,
			String code, Double day2, Double hour2, String pr, Long seq2,
			Double day3, Double hour3, String code2, Double pr2, Double work,
			Double data, String by2, Date date2, Double pr3, Double work2) {

		// TODO Auto-generated constructor stub
		this.aftDay = day;
		this.aftHour = hour;
		this.amtAft = aft;
		this.amtEvn = evn;
		this.amtMor = mor;
		this.codeSeq = seq;
		this.confirmFlag = flag;
		this.creBy = by;
		this.creDate = date;
		this.empCode = code;
		this.evnDay = day2;
		this.evnHour = hour2;
		this.flagPr = pr;
		this.keySeq = seq2;
		this.morDay = day3;
		this.morHour = hour3;
		this.ouCode = code2;
		this.periodPr = pr2;
		this.periodWork = work;
		this.seqData = data;
		this.updBy = by2;
		this.updDate = date2;
		this.yearPr = pr3;
		this.yearWork = work2;
	}

	public Double getAftDay() {
		return aftDay;
	}

	public void setAftDay(Double aftDay) {
		this.aftDay = aftDay;
	}

	public Double getAftHour() {
		return aftHour;
	}

	public void setAftHour(Double aftHour) {
		this.aftHour = aftHour;
	}

	public Double getAmtAft() {
		return amtAft;
	}

	public void setAmtAft(Double amtAft) {
		this.amtAft = amtAft;
	}

	public Double getAmtEvn() {
		return amtEvn;
	}

	public void setAmtEvn(Double amtEvn) {
		this.amtEvn = amtEvn;
	}

	public Double getAmtMor() {
		return amtMor;
	}

	public void setAmtMor(Double amtMor) {
		this.amtMor = amtMor;
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

	public Double getEvnDay() {
		return evnDay;
	}

	public void setEvnDay(Double evnDay) {
		this.evnDay = evnDay;
	}

	public Double getEvnHour() {
		return evnHour;
	}

	public void setEvnHour(Double evnHour) {
		this.evnHour = evnHour;
	}

	public String getFlagPr() {
		return flagPr;
	}

	public void setFlagPr(String flagPr) {
		this.flagPr = flagPr;
	}

	public Long getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}

	public Double getMorDay() {
		return morDay;
	}

	public void setMorDay(Double morDay) {
		this.morDay = morDay;
	}

	public Double getMorHour() {
		return morHour;
	}

	public void setMorHour(Double morHour) {
		this.morHour = morHour;
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
}
