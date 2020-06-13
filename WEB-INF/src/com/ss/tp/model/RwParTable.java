package com.ss.tp.model;

import java.io.Serializable;

public class RwParTable implements Serializable {

	private Double paramKey;
	private String paramDesc;
	private String paramLang;

	public RwParTable() {

	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Double getParamKey() {
		return paramKey;
	}

	public void setParamKey(Double paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamLang() {
		return paramLang;
	}

	public void setParamLang(String paramLang) {
		this.paramLang = paramLang;
	}

	public RwParTable(String desc, Double key, String lang) {
		paramDesc = desc;
		paramKey = key;
		paramLang = lang;
	}

}
