package com.ss.tp.model;

import java.io.Serializable;

public class PnPosition implements Serializable {
	private PnPositionPK pk;
	private String positionShort;

	public PnPosition() {
	}

	public PnPosition(PnPositionPK pk, String positionShort) {
		this.pk = pk;
		this.positionShort = positionShort;
	}

	public PnPositionPK getPk() {
		return pk;
	}

	public void setPk(PnPositionPK pk) {
		this.pk = pk;
	}

	public String getPositionShort() {
		return positionShort;
	}

	public void setPositionShort(String positionShort) {
		this.positionShort = positionShort;
	}
}
