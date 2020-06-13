package com.ss.tp.model;

import java.io.Serializable;

public class DbPreSuff implements Serializable {
	private String preSuffCode;
	private String prefixName;
	private String suffixName;
	private String inactive;

	public DbPreSuff() {
	}

	public DbPreSuff(String preSuffCode, String prefixName, String suffixName,
			String inactive) {
		this.preSuffCode = preSuffCode;
		this.prefixName = prefixName;
		this.suffixName = suffixName;
		this.inactive = inactive;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getPreSuffCode() {
		return preSuffCode;
	}

	public void setPreSuffCode(String preSuffCode) {
		this.preSuffCode = preSuffCode;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

}
