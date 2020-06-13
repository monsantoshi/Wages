package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PnEmpMove implements Serializable {
	private PnEmpMovePK pk;
	private String ouCode;
	private String empCode;
	private String moveStatus;
	private Date dateSubmit;
	private Double year;
	private String rentCost;
	private String moveCost;
	private String manReplace;
	private String eduReplace;
	private String lastInst;
	private String eduFirst;
	private String eduLast;
	private Date dateLastmove;
	private String moveWait;
	private String kindManreplace;
	private String creBy;
	private String updBy;
	private Date creDate;
	private Date updDate;
	private PnEmployee emp;
	private Set pnEmpSetPlace;

	public PnEmpMove(PnEmpMovePK pk, String empCode, String status,
			Date dateSubmit, Double year, String cost, String cost2,
			String edu, String inst, String efirst, String elast, Date date,
			String wait, String man, String cby, String uby, Date cdate,
			Date udate, Set pnEmpSetPlace, PnEmployee emp) {
		// TODO Auto-generated constructor stub
		this.pk = pk;
		this.empCode = empCode;
		this.moveStatus = status;
		this.dateSubmit = dateSubmit;
		this.year = year;
		this.rentCost = cost;
		this.moveCost = cost2;
		this.eduReplace = edu;
		this.lastInst = inst;
		this.eduFirst = efirst;
		this.eduLast = elast;
		this.dateLastmove = date;
		this.moveWait = wait;
		this.kindManreplace = man;
		this.creBy = cby;
		this.updBy = uby;
		this.creDate = cdate;
		this.updDate = udate;
		this.pnEmpSetPlace = pnEmpSetPlace;
		this.emp = emp;
	}

	public PnEmpMove() {
	}

	public PnEmpMovePK getPk() {
		return pk;
	}

	public void setPk(PnEmpMovePK pk) {
		this.pk = pk;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getMoveStatus() {
		return moveStatus;
	}

	public void setMoveStatus(String moveStatus) {
		this.moveStatus = moveStatus;
	}

	public Date getDateSubmit() {
		return dateSubmit;
	}

	public void setDateSubmit(Date dateSubmit) {
		this.dateSubmit = dateSubmit;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public String getRentCost() {
		return rentCost;
	}

	public void setRentCost(String rentCost) {
		this.rentCost = rentCost;
	}

	public String getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(String moveCost) {
		this.moveCost = moveCost;
	}

	public String getEdureplace() {
		return eduReplace;
	}

	public void setEdureplace(String eduReplace) {
		this.eduReplace = eduReplace;
	}

	public String getManreplace() {
		return manReplace;
	}

	public void setManreplace(String manReplace) {
		this.manReplace = manReplace;
	}

	public String getLastInst() {
		return lastInst;
	}

	public void setLastInst(String lastInst) {
		this.lastInst = lastInst;
	}

	public String getEduFirst() {
		return eduFirst;
	}

	public void setEduFirst(String eduFirst) {
		this.eduFirst = eduFirst;
	}

	public String getEduLast() {
		return eduLast;
	}

	public void setEduLast(String eduLast) {
		this.eduLast = eduLast;
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

	public Date getDateLastmove() {
		return dateLastmove;
	}

	public void setDateLastmove(Date dateLastmove) {
		this.dateLastmove = dateLastmove;
	}

	public String getMoveWait() {
		return moveWait;
	}

	public void setMoveWait(String moveWait) {
		this.moveWait = moveWait;
	}

	public String getKindManreplace() {
		return kindManreplace;
	}

	public void setKindManreplace(String kindManreplace) {
		this.kindManreplace = kindManreplace;
	}

	public PnEmployee getEmp() {
		return emp;
	}

	public void setEmp(PnEmployee emp) {
		this.emp = emp;
	}

	public Set getPnEmpSetPlace() {
		return pnEmpSetPlace;
	}

	public void setPnEmpSetPlace(Set pnEmpSetPlace) {
		this.pnEmpSetPlace = pnEmpSetPlace;
	}

}
