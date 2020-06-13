package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

public class PnSalary implements Serializable {
	private PnSalaryPK pk;
	private Long salary;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;

	public PnSalary() {
	}

	public PnSalary(String by, Date date, PnSalaryPK pk, Long salary,
			String by2, Date date2) {
		// TODO Auto-generated constructor stub
		this.creBy = by;
		this.creDate = date;
		this.pk = pk;
		this.salary = salary;
		this.updBy = by2;
		this.updDate = date2;
	}

	public PnSalaryPK getPk() {
		return pk;
	}

	public void setPk(PnSalaryPK pk) {
		this.pk = pk;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
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

}
