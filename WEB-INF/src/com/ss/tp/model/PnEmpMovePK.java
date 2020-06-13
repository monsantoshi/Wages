package com.ss.tp.model;

import java.io.Serializable;

public class PnEmpMovePK implements Serializable {
	private String ouCode;
	private long moveNo;

	public PnEmpMovePK() {
	}

	public PnEmpMovePK(String ouCode, long moveNo) {
		this.ouCode = ouCode;
		this.moveNo = moveNo;
	}

	public long getMoveNo() {
		return moveNo;
	}

	public void setMoveNo(long moveNo) {
		this.moveNo = moveNo;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
