package com.ss.tp.model;

import java.io.Serializable;

public class TestEmp implements Serializable {
	private String empCode;
	private String empName;
	private String deptCode;

	public TestEmp() {

	}

	public TestEmp(String empCode, String empName, String deptCode) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.deptCode = deptCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}