package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VFeeWpayUserSFlag implements Serializable {
	private VFeeWpayUserSFlagPK pk;
	
	private Double sumFlag;

	public VFeeWpayUserSFlag() {
		
	}

	public VFeeWpayUserSFlag(VFeeWpayUserSFlagPK pk, Double sumFlag) {
		super();
		this.pk = pk;
		this.sumFlag = sumFlag;
	}

	public VFeeWpayUserSFlagPK getPk() {
		return pk;
	}

	public void setPk(VFeeWpayUserSFlagPK pk) {
		this.pk = pk;
	}

	public Double getSumFlag() {
		return sumFlag;
	}

	public void setSumFlag(Double sumFlag) {
		this.sumFlag = sumFlag;
	}

	
	
	

	
}
