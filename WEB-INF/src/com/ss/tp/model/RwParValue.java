package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwParValue implements Serializable {

	private RwParValuePK pk;
	private Double paramValN;
	private String paramValV;
	private Date paramValD;

	public RwParValue() {

	}

	public RwParValue(Date valD, Double valN, String valV, RwParValuePK pk) {
		super();
		// TODO Auto-generated constructor stub
		paramValD = valD;
		paramValN = valN;
		paramValV = valV;
		this.pk = pk;
	}

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

	public RwParValuePK getPk() {
		return pk;
	}

	public void setPk(RwParValuePK pk) {
		this.pk = pk;
	}

}
