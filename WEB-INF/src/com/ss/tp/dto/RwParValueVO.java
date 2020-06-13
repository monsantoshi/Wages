package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

public class RwParValueVO implements Serializable {

	private Double paramKey;
	private Double paramIndex;
	private Double paramValN;
	private String paramValV;
	private Date paramValD;

	public Date getParamValD() {
		return paramValD;
	}

	public void setParamValD(Date paramValD) {
		this.paramValD = paramValD;
	}

	public Double getParamValN() {
		return paramValN;
	}

	public void setParamValN(Double paramValN) {
		this.paramValN = paramValN;
	}

	public String getParamValV() {
		return paramValV;
	}

	public void setParamValV(String paramValV) {
		this.paramValV = paramValV;
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
