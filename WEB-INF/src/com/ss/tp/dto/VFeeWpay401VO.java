/*
 * Created on 13 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VFeeWpay401VO implements Serializable {
	private Integer keySeq;
	private String ouCodeSum;
	
	private Double yearSum;
	private Double periodSum;
	private String levelGroupSum;
	private String groupCodeSum;
	private String accNoSum;
	private String accNameSum;
	
	private Double empAmountSum;
	private Double amountSum;
	private String incDecCodeSum;
	private String incDecSubCodeSum;
	private String incDecNameSum;
	
	
	
	public VFeeWpay401VO() {
		
	}

	public VFeeWpay401VO(Integer keySeq, String ouCodeSum, Double yearSum,
			Double periodSum, String levelGroupSum, String groupCodeSum,
			String accNoSum, String accNameSum, Double empAmountSum,
			Double amountSum, String incDecCodeSum, String incDecSubCodeSum,
			String incDecNameSum) {
		//super();
		this.keySeq = keySeq;
		this.ouCodeSum = ouCodeSum;
		this.yearSum = yearSum;
		this.periodSum = periodSum;
		this.levelGroupSum = levelGroupSum;
		this.groupCodeSum = groupCodeSum;
		this.accNoSum = accNoSum;
		this.accNameSum = accNameSum;
		this.empAmountSum = empAmountSum;
		this.amountSum = amountSum;
		this.incDecCodeSum = incDecCodeSum;
		this.incDecSubCodeSum = incDecSubCodeSum;
		this.incDecNameSum = incDecNameSum;
	}
	
	
	public Integer getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(Integer keySeq) {
		this.keySeq = keySeq;
	}
	public String getOuCodeSum() {
		return ouCodeSum;
	}
	public void setOuCodeSum(String ouCodeSum) {
		this.ouCodeSum = ouCodeSum;
	}
	public Double getYearSum() {
		return yearSum;
	}
	public void setYearSum(Double yearSum) {
		this.yearSum = yearSum;
	}
	public Double getPeriodSum() {
		return periodSum;
	}
	public void setPeriodSum(Double periodSum) {
		this.periodSum = periodSum;
	}
	public String getLevelGroupSum() {
		return levelGroupSum;
	}
	public void setLevelGroupSum(String levelGroupSum) {
		this.levelGroupSum = levelGroupSum;
	}
	public String getGroupCodeSum() {
		return groupCodeSum;
	}
	public void setGroupCodeSum(String groupCodeSum) {
		this.groupCodeSum = groupCodeSum;
	}
	public String getAccNoSum() {
		return accNoSum;
	}
	public void setAccNoSum(String accNoSum) {
		this.accNoSum = accNoSum;
	}
	public String getAccNameSum() {
		return accNameSum;
	}
	public void setAccNameSum(String accNameSum) {
		this.accNameSum = accNameSum;
	}
	public Double getEmpAmountSum() {
		return empAmountSum;
	}
	public void setEmpAmountSum(Double empAmountSum) {
		this.empAmountSum = empAmountSum;
	}
	public Double getAmountSum() {
		return amountSum;
	}
	public void setAmountSum(Double amountSum) {
		this.amountSum = amountSum;
	}
	public String getIncDecCodeSum() {
		return incDecCodeSum;
	}
	public void setIncDecCodeSum(String incDecCodeSum) {
		this.incDecCodeSum = incDecCodeSum;
	}
	public String getIncDecSubCodeSum() {
		return incDecSubCodeSum;
	}
	public void setIncDecSubCodeSum(String incDecSubCodeSum) {
		this.incDecSubCodeSum = incDecSubCodeSum;
	}
	public String getIncDecNameSum() {
		return incDecNameSum;
	}
	public void setIncDecNameSum(String incDecNameSum) {
		this.incDecNameSum = incDecNameSum;
	}

	

}
