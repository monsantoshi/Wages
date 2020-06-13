/*
 * Created on 13 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VSuMasterUserPK implements Serializable {
	
	private Double year;
	private Double period;
	
	private String userId;
	private String masterUser;
	public VSuMasterUserPK() {
	//	super();
		// TODO Auto-generated constructor stub
	}
	public VSuMasterUserPK(Double year, Double period, String userId,
			String masterUser) {
		//super();
		this.year = year;
		this.period = period;
		this.userId = userId;
		this.masterUser = masterUser;
	}
	public Double getYear() {
		return year;
	}
	public void setYear(Double year) {
		this.year = year;
	}
	public Double getPeriod() {
		return period;
	}
	public void setPeriod(Double period) {
		this.period = period;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMasterUser() {
		return masterUser;
	}
	public void setMasterUser(String masterUser) {
		this.masterUser = masterUser;
	}
	

	
	

}
