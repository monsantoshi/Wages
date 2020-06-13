package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpPrEmployeeSecurity implements Serializable {
	private VFeeWpPrEmployeeSecurityPK pk;

	public VFeeWpPrEmployeeSecurity() {

	}

	public VFeeWpPrEmployeeSecurityPK getPk() {
		return pk;
	}

	public void setPk(VFeeWpPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

	public VFeeWpPrEmployeeSecurity(VFeeWpPrEmployeeSecurityPK pk) {
		this.pk = pk;
	}

}
