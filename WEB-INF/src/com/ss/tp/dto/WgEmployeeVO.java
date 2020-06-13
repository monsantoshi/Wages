package com.ss.tp.dto;

import java.io.Serializable;

public class WgEmployeeVO implements Serializable {
	private String ouCode;
	private String empCode;
	private String preName;
	private String firstName;
	private String lastName;
	//private String positionCode;
	private Long codeSeq;
	private Long codeSeqAct;

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Long getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(Long codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}


}
