package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayConfirmData implements Serializable {
	private FeeWpayConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public FeeWpayConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(FeeWpayConfirmDataPK pk) {
		this.pk = pk;
	}

	public FeeWpayConfirmData() {
	}

}
