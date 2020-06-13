/*
 * Created on 25 �.�. 2549
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
public class TaStatusWork implements Serializable {
	private TaStatusWorkPK taStatusWorkPk;
	private String workDesc;
	private String evaType;
	private String inactive;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String workType;
	private SuOrganize suOrganize;

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	/**
	 * @return Returns the suOrganize.
	 */
	public SuOrganize getSuOrganize() {
		return suOrganize;
	}

	/**
	 * @param suOrganize
	 *            The suOrganize to set.
	 */
	public void setSuOrganize(SuOrganize suOrganize) {
		this.suOrganize = suOrganize;
	}

	/**
	 * @return Returns the creBy.
	 */
	public String getCreBy() {
		return creBy;
	}

	/**
	 * @param creBy
	 *            The creBy to set.
	 */
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	/**
	 * @return Returns the creDate.
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            The creDate to set.
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return Returns the evaType.
	 */
	public String getEvaType() {
		return evaType;
	}

	/**
	 * @param evaType
	 *            The evaType to set.
	 */
	public void setEvaType(String evaType) {
		this.evaType = evaType;
	}

	/**
	 * @return Returns the inactive.
	 */
	public String getInactive() {
		return inactive;
	}

	/**
	 * @param inactive
	 *            The inactive to set.
	 */
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	/**
	 * @return Returns the taStatusWorkPk.
	 */
	public TaStatusWorkPK getTaStatusWorkPk() {
		return taStatusWorkPk;
	}

	/**
	 * @param taStatusWorkPk
	 *            The taStatusWorkPk to set.
	 */
	public void setTaStatusWorkPk(TaStatusWorkPK taStatusWorkPk) {
		this.taStatusWorkPk = taStatusWorkPk;
	}

	/**
	 * @return Returns the updBy.
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy
	 *            The updBy to set.
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return Returns the updDate.
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            The updDate to set.
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/**
	 * @return Returns the workDesc.
	 */
	public String getWorkDesc() {
		return workDesc;
	}

	/**
	 * @param workDesc
	 *            The workDesc to set.
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
}
