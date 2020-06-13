package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class FeeWgRwConfirmData implements Serializable {
	private FeeWgRwConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public FeeWgRwConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(FeeWgRwConfirmDataPK pk) {
		this.pk = pk;
	}

	public FeeWgRwConfirmData() {
	}

}
