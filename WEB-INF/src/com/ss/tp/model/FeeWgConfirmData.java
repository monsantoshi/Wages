package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWgConfirmData implements Serializable {
	private FeeWgConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public FeeWgConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(FeeWgConfirmDataPK pk) {
		this.pk = pk;
	}

	public FeeWgConfirmData() {
	}

}
