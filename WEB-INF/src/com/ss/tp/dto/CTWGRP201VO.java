package com.ss.tp.dto;

import java.io.Serializable;

public class CTWGRP201VO implements Serializable {
	private String divCode;
	private String areaCode;
	private String divName;
	private String areaName;

	private String secCode;
	private String secName;

	private Long cntEmp;
	private Long empA;
	private Long empI;
	private Long empD;

	private String incDecCode;
	private String incDecName;

	private Long cntRec;
	private Double amtRec;
	private Long cntN;
	private Double amtN;
	private Long cntA;
	private Double amtA;
	private Long cntR;
	private Double amtR;
	private Long cntB;
	private Double amtB;
	private Long cntS;
	private Double amtS;

	private Long costLife;
	private Long gundan;
	private Long costChild;
	private Long sumCostChild;

	public Long getCostChild() {
		return costChild;
	}

	public void setCostChild(Long costChild) {
		this.costChild = costChild;
	}

	public Long getCostLife() {
		return costLife;
	}

	public void setCostLife(Long costLife) {
		this.costLife = costLife;
	}

	public Long getGundan() {
		return gundan;
	}

	public void setGundan(Long gundan) {
		this.gundan = gundan;
	}

	public Long getSumCostChild() {
		return sumCostChild;
	}

	public void setSumCostChild(Long sumCostChild) {
		this.sumCostChild = sumCostChild;
	}

	public Double getAmtA() {
		return amtA;
	}

	public void setAmtA(Double amtA) {
		this.amtA = amtA;
	}

	public Double getAmtB() {
		return amtB;
	}

	public void setAmtB(Double amtB) {
		this.amtB = amtB;
	}

	public Double getAmtN() {
		return amtN;
	}

	public void setAmtN(Double amtN) {
		this.amtN = amtN;
	}

	public Double getAmtR() {
		return amtR;
	}

	public void setAmtR(Double amtR) {
		this.amtR = amtR;
	}

	public Double getAmtRec() {
		return amtRec;
	}

	public void setAmtRec(Double amtRec) {
		this.amtRec = amtRec;
	}

	public Double getAmtS() {
		return amtS;
	}

	public void setAmtS(Double amtS) {
		this.amtS = amtS;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getCntA() {
		return cntA;
	}

	public void setCntA(Long cntA) {
		this.cntA = cntA;
	}

	public Long getCntB() {
		return cntB;
	}

	public void setCntB(Long cntB) {
		this.cntB = cntB;
	}

	public Long getCntEmp() {
		return cntEmp;
	}

	public void setCntEmp(Long cntEmp) {
		this.cntEmp = cntEmp;
	}

	public Long getCntN() {
		return cntN;
	}

	public void setCntN(Long cntN) {
		this.cntN = cntN;
	}

	public Long getCntR() {
		return cntR;
	}

	public void setCntR(Long cntR) {
		this.cntR = cntR;
	}

	public Long getCntRec() {
		return cntRec;
	}

	public void setCntRec(Long cntRec) {
		this.cntRec = cntRec;
	}

	public Long getCntS() {
		return cntS;
	}

	public void setCntS(Long cntS) {
		this.cntS = cntS;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public Long getEmpA() {
		return empA;
	}

	public void setEmpA(Long empA) {
		this.empA = empA;
	}

	public Long getEmpD() {
		return empD;
	}

	public void setEmpD(Long empD) {
		this.empD = empD;
	}

	public Long getEmpI() {
		return empI;
	}

	public void setEmpI(Long empI) {
		this.empI = empI;
	}

	public String getIncDecCode() {
		return incDecCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getSecCode() {
		/*
		 * if (secCode != null &&!secCode.equals("")){ return secCode; }else{
		 * return "1"; }
		 */
		return secCode;

	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecName() {

		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

}
