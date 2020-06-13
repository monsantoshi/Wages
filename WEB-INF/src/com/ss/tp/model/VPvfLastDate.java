package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VPvfLastDate implements Serializable {
	private Long keySeq;
	
	
	private Date lDate;
	

	public VPvfLastDate() {

	}


	public VPvfLastDate(Long keySeq, Date lDate) {
		//super();
		this.keySeq = keySeq;
		this.lDate = lDate;
	}


	public Long getKeySeq() {
		return keySeq;
	}


	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}


	public Date getlDate() {
		return lDate;
	}


	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}



}
