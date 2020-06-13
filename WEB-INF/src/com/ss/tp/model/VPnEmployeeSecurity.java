package com.ss.tp.model;

import java.io.Serializable;

public class VPnEmployeeSecurity implements Serializable {
	private VPnEmployeeSecurityPK pk;

	public VPnEmployeeSecurity() {

	}

	public VPnEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VPnEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VPnEmployeeSecurity(VPnEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
