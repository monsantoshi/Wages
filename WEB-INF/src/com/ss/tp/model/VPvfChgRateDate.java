package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VPvfChgRateDate implements Serializable {
	private Long keySeq;
	
	
	private Date rDate;
	

	public VPvfChgRateDate() {

	}


	public VPvfChgRateDate(Long keySeq, Date rDate) {
		//super();
		this.keySeq = keySeq;
		this.rDate = rDate;
	}


	public Long getKeySeq() {
		return keySeq;
	}


	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}


	public Date getrDate() {
		return rDate;
	}


	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}



}
