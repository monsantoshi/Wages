package com.ss.tp.model;

import java.io.Serializable;

public class SuOrganize implements Serializable {
	private String ouCode;
	private String ouName;
	private String inactive;

	public SuOrganize() {
	}

	public SuOrganize(String ouCode, String ouName, String inactive) {
		this.ouCode = ouCode;
		this.ouName = ouName;
		this.inactive = inactive;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getOuName() {
		return ouName;
	}

	public void setOuName(String ouName) {
		this.ouName = ouName;
	}

}
