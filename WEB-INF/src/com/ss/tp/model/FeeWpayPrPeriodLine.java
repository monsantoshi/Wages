package com.ss.tp.model;

import java.io.Serializable;

public class FeeWpayPrPeriodLine implements Serializable {
	private FeeWpayPrPeriodLinePK pk;
	private String periodName;
	private String mainClose;
	private String tranClose;
	private String prClose;

	public String getPrClose() {
		return prClose;
	}

	public void setPrClose(String prClose) {
		this.prClose = prClose;
	}

	public String getTranClose() {
		return tranClose;
	}

	public void setTranClose(String tranClose) {
		this.tranClose = tranClose;
	}

	public String getMainClose() {
		return mainClose;
	}

	public void setMainClose(String mainClose) {
		this.mainClose = mainClose;
	}

	public FeeWpayPrPeriodLine() {

	}

	public FeeWpayPrPeriodLine(String name, FeeWpayPrPeriodLinePK pk) {
		super();
		// TODO Auto-generated constructor stub
		periodName = name;
		this.pk = pk;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public FeeWpayPrPeriodLinePK getPk() {
		return pk;
	}

	public void setPk(FeeWpayPrPeriodLinePK pk) {
		this.pk = pk;
	}

}
