package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWpayRwConfirmData implements Serializable {
	private FeeWpayRwConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public FeeWpayRwConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(FeeWpayRwConfirmDataPK pk) {
		this.pk = pk;
	}

	public FeeWpayRwConfirmData() {
	}

}
