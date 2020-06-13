package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class WgConfirmData implements Serializable {
	private WgConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public WgConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(WgConfirmDataPK pk) {
		this.pk = pk;
	}

	public WgConfirmData() {
	}

}
