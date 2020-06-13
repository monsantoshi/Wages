package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class PeWgControlVO implements Serializable {
	private String ouCode;
	private Long evaYear;
	private Long evaTime;
	private String userId;
	// private Long codeSeq;
	private String flagClose;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	// public Long getCodeSeq() {
	// return codeSeq;
	// }
	// public void setCodeSeq(Long codeSeq) {
	// this.codeSeq = codeSeq;
	// }

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

	public Long getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Long evaTime) {
		this.evaTime = evaTime;
	}

	public Long getEvaYear() {
		return evaYear;
	}

	public void setEvaYear(Long evaYear) {
		this.evaYear = evaYear;
	}

	public String getFlagClose() {
		return flagClose;
	}

	public void setFlagClose(String flagClose) {
		this.flagClose = flagClose;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
