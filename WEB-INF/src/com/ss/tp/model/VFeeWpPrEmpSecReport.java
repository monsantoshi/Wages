package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWpPrEmpSecReport implements Serializable {
	private VFeeWpPrEmpSecReportPK pk;

	public VFeeWpPrEmpSecReport() {

	}

	public VFeeWpPrEmpSecReportPK getPk() {
		return pk;
	}

	public void setPk(VFeeWpPrEmpSecReportPK pk) {
		this.pk = pk;
	}

	public VFeeWpPrEmpSecReport(VFeeWpPrEmpSecReportPK pk) {
		this.pk = pk;
	}

}
