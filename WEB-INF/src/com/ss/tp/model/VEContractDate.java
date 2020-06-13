package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class VEContractDate implements Serializable {
	private Long keySeq;
	
	
	private Date eDate;
	private String userId;
	

	public VEContractDate() {

	}


	public VEContractDate(Long keySeq, Date eDate,String userId) {
		//super();
		this.keySeq = keySeq;
		this.eDate = eDate;
		this.userId = userId;
	}


	public Long getKeySeq() {
		return keySeq;
	}


	public void setKeySeq(Long keySeq) {
		this.keySeq = keySeq;
	}


	public Date geteDate() {
		return eDate;
	}


	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}


	public Date getEDate() {
		return eDate;
	}


	public void setEDate(Date date) {
		eDate = date;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}



}
