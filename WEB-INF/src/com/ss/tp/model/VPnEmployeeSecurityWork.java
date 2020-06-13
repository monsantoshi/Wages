package com.ss.tp.model;

import java.io.Serializable;

public class VPnEmployeeSecurityWork implements Serializable {
	private VPnEmployeeSecurityWorkPK pk;

	public VPnEmployeeSecurityWork() {

	}

	public VPnEmployeeSecurityWorkPK getPk() {
		return pk;
	}

	public void setPk(VPnEmployeeSecurityWorkPK pk) {
		this.pk = pk;
	}

	public VPnEmployeeSecurityWork(VPnEmployeeSecurityWorkPK pk) {
		this.pk = pk;
	}

}
