package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWgPrEmployeeSecurity implements Serializable {
	private VFeeWgPrEmployeeSecurityPK pk;

	public VFeeWgPrEmployeeSecurity() {

	}

	public VFeeWgPrEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VFeeWgPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VFeeWgPrEmployeeSecurity(VFeeWgPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
