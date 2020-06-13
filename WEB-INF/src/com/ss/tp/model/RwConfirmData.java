package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class RwConfirmData implements Serializable {
	private RwConfirmDataPK pk;
	private Date creDate;

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public RwConfirmDataPK getPk() {
		return pk;
	}

	public void setPk(RwConfirmDataPK pk) {
		this.pk = pk;
	}

	public RwConfirmData() {
	}

}
