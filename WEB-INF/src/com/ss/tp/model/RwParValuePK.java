package com.ss.tp.model;

import java.io.Serializable;

public class RwParValuePK implements Serializable {
	private Double paramKey;
	private Double paramIndex;

	public RwParValuePK() {
	}

	public RwParValuePK(Double index, Double key) {
		paramIndex = index;
		paramKey = key;
	}

	public Double getParamIndex() {
		return paramIndex;
	}

	public void setParamIndex(Double paramIndex) {
		this.paramIndex = paramIndex;
	}

	public Double getParamKey() {
		return paramKey;
	}

	public void setParamKey(Double paramKey) {
		this.paramKey = paramKey;
	}

}
