package com.ss.tp.model;

import java.io.Serializable;

public class SuUserOrgPK implements Serializable {
	private String userId;
	private String ouCode;

	public SuUserOrgPK() {
	}

	public SuUserOrgPK(String userId, String ouCode) {
		this.userId = userId;
		this.ouCode = ouCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
