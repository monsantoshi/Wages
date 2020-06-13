package com.ss.tp.model;

import java.io.Serializable;

public class VWgPrEmployeeSecurity implements Serializable {
	private VPrEmployeeSecurityPK pk;

	public VWgPrEmployeeSecurity() {

	}

	public VPrEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VWgPrEmployeeSecurity(VPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
