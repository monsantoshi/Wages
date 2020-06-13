package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VPvfMDate implements Serializable {
	private Long keySeq;
	
	
	private Date mDate;
	

	public VPvfMDate() {

	}


	public VPvfMDate(Long keySeq, Date mDate) {
		//super();
		this.keySeq = keySeq;
		this.mDate = mDate;
	}


	public Long getKeySeq() {
		return keySeq;
	}


	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}


	public Date getmDate() {
		return mDate;
	}


	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}



}
