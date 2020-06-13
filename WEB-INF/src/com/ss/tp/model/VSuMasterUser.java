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
public class VSuMasterUser implements Serializable {
	
	private VSuMasterUserPK pk;
	private String zipCode;
	private Integer flag1;
	private Integer flag2;
	private Integer flag3;
	private Integer flag4;
	public VSuMasterUser() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public VSuMasterUser(VSuMasterUserPK pk, String zipCode, Integer flag1,
			Integer flag2, Integer flag3, Integer flag4) {
		
		this.pk = pk;
		this.zipCode = zipCode;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.flag3 = flag3;
		this.flag4 = flag4;
	}
	public VSuMasterUserPK getPk() {
		return pk;
	}
	public void setPk(VSuMasterUserPK pk) {
		this.pk = pk;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Integer getFlag1() {
		return flag1;
	}
	public void setFlag1(Integer flag1) {
		this.flag1 = flag1;
	}
	public Integer getFlag2() {
		return flag2;
	}
	public void setFlag2(Integer flag2) {
		this.flag2 = flag2;
	}
	public Integer getFlag3() {
		return flag3;
	}
	public void setFlag3(Integer flag3) {
		this.flag3 = flag3;
	}
	public Integer getFlag4() {
		return flag4;
	}
	public void setFlag4(Integer flag4) {
		this.flag4 = flag4;
	}
	
	
	
	
	
	
	
	

}
