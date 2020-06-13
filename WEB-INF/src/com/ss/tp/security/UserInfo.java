/*
 * Created on 22 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.security;

import java.io.Serializable;
import java.util.Map;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class UserInfo implements Serializable {
	private String userId;
	private String userPassword;
	private String userName;
	private String department;
	private String inactive;
	private String userGroup;
	private String userLin;
	private String ouCode;
	private Map secure;
	private Map bag;

	public Map getBag() {
		return bag;
	}

	public void setBag(Map bag) {
		this.bag = bag;
	}

	public Map getSecure() {
		return secure;
	}

	public void setSecure(Map secure) {
		this.secure = secure;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getUserLin() {
		return userLin;
	}

	public void setUserLin(String userLin) {
		this.userLin = userLin;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

}
