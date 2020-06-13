package com.ss.tp.model;

import java.io.Serializable;

public class PnAdminPK implements Serializable {
	private String ouCode;
	private String adminCode;

	public PnAdminPK() {
	}

	public PnAdminPK(String code, String code2) {
		super();
		// TODO Auto-generated constructor stub
		adminCode = code;
		ouCode = code2;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
