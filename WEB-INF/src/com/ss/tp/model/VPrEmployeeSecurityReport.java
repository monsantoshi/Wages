package com.ss.tp.model;

import java.io.Serializable;

public class VPrEmployeeSecurityReport implements Serializable {
	private VPrEmployeeSecurityReportPK pk;

	public VPrEmployeeSecurityReport() {

	}

	public VPrEmployeeSecurityReportPK getPk() {
		return pk;
	}

	public void setPk(VPrEmployeeSecurityReportPK pk) {
		this.pk = pk;
	}

	public VPrEmployeeSecurityReport(VPrEmployeeSecurityReportPK pk) {
		this.pk = pk;
	}

}
