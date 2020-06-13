package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class SuUser implements Serializable {
	private String userId;
	private String userPassword;
	private String userName;
	private String department;
	private String inactive;
	private String userGroup;
	private String userLin;
	private String updBy;
	private Date updDate;
	private String webFlag;
	private String masterUser;
	private Double telNo;
	private String zipCode;

	public SuUser() {

	}

	public SuUser(String userId, String userPassword, String userName,
			String department, String inactive, String userGroup,
			String userLin, String updBy, Date updDate, String webFlag,
			String masterUser, Double telNo, String zipCode) {
		
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.department = department;
		this.inactive = inactive;
		this.userGroup = userGroup;
		this.userLin = userLin;
		this.updBy = updBy;
		this.updDate = updDate;
		this.webFlag = webFlag;
		this.masterUser = masterUser;
		this.telNo = telNo;
		this.zipCode = zipCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getWebFlag() {
		return webFlag;
	}

	public void setWebFlag(String webFlag) {
		this.webFlag = webFlag;
	}

	public String getMasterUser() {
		return masterUser;
	}

	public void setMasterUser(String masterUser) {
		this.masterUser = masterUser;
	}

	public Double getTelNo() {
		return telNo;
	}

	public void setTelNo(Double telNo) {
		this.telNo = telNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	

}
