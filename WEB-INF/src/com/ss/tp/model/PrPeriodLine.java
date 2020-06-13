package com.ss.tp.model;

import java.io.Serializable;

public class PrPeriodLine implements Serializable {
	private PrPeriodLinePK pk;
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

	public PrPeriodLine() {

	}

	public PrPeriodLine(String name, PrPeriodLinePK pk) {
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

	public PrPeriodLinePK getPk() {
		return pk;
	}

	public void setPk(PrPeriodLinePK pk) {
		this.pk = pk;
	}

}
