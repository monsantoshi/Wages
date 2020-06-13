package com.ss.tp.model;

import java.io.Serializable;

public class VFeeWgPrEmpSecReport implements Serializable {
	private VFeeWgPrEmpSecReportPK pk;

	public VFeeWgPrEmpSecReport() {

	}

	public VFeeWgPrEmpSecReportPK getPk() {
		return pk;
	}

	public void setPk(VFeeWgPrEmpSecReportPK pk) {
		this.pk = pk;
	}

	public VFeeWgPrEmpSecReport(VFeeWgPrEmpSecReportPK pk) {
		this.pk = pk;
	}

}
