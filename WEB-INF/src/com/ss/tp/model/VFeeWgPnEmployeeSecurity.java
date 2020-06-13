package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWgPnEmployeeSecurity implements Serializable {
	private VFeeWgPnEmployeeSecurityPK pk;

	public VFeeWgPnEmployeeSecurity() {

	}

	public VFeeWgPnEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VFeeWgPnEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VFeeWgPnEmployeeSecurity(VFeeWgPnEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
