/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaControlPk implements Serializable {
	private String ouCode;
	private Integer taPeriod;
	private Integer taYear;
	private String userId;

	// private Integer codeSeq;

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public Integer getTaYear() {
		return taYear;
	}

	public void setTaYear(Integer taYear) {
		this.taYear = taYear;
	}

	public Integer getTaPeriod() {
		return taPeriod;
	}

	public void setTaPeriod(Integer taPeriod) {
		this.taPeriod = taPeriod;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
