/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FeeContractPK implements Serializable {
	private String ouCode;
	private Double codeSeq;
	private String yearCon;
	private String contractNo;
	
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public Double getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getYearCon() {
		return yearCon;
	}
	public void setYearCon(String yearCon) {
		this.yearCon = yearCon;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public FeeContractPK() {
	
	}
	public FeeContractPK(String ouCode, Double codeSeq, String yearCon,
			String contractNo) {
		//super();
		this.ouCode = ouCode;
		this.codeSeq = codeSeq;
		this.yearCon = yearCon;
		this.contractNo = contractNo;
	}

	
}
