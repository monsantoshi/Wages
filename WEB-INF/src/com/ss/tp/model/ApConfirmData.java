package com.ss.tp.model;

import java.io.Serializable;

import java.util.Date;


public class ApConfirmData implements Serializable {
	private ApConfirmDataPK pk;
	private Date creDate;

	public ApConfirmData(ApConfirmDataPK pk) {
		super();
		this.pk = pk;
	}



	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public ApConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(ApConfirmDataPK pk) {
		this.pk = pk;
	}

	public ApConfirmData() {
	}

}
