package com.ss.tp.model;

import java.io.Serializable;

public class VPrEmployeeSecurity implements Serializable {
	private VPrEmployeeSecurityPK pk;

	public VPrEmployeeSecurity() {

	}

	public VPrEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VPrEmployeeSecurity(VPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
