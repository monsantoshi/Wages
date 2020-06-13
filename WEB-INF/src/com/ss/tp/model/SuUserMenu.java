package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ta
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuUserMenu implements Serializable {
	private SuUserMenuPK pk;
	private String insFlag;
	private String qryFlag;
	private String updFlag;
	private String delFlag;
	private String repFlag;
	private String updBy;
	private Date updDate;
	private String useFlag;
	private String menuFlag;

	/**
	 * 
	 */
	public SuUserMenu() {
		super();
	}

	/**
	 * @param pk
	 * @param insFlag
	 * @param qryFlag
	 * @param updFlag
	 * @param delFlag
	 * @param repFlag
	 * @param updBy
	 * @param updDate
	 * @param useFlag
	 * @param menuFlag
	 */
	public SuUserMenu(SuUserMenuPK pk, String insFlag, String qryFlag,
			String updFlag, String delFlag, String repFlag, String updBy,
			Date updDate, String useFlag, String menuFlag) {
		this.pk = pk;
		this.insFlag = insFlag;
		this.qryFlag = qryFlag;
		this.updFlag = updFlag;
		this.delFlag = delFlag;
		this.repFlag = repFlag;
		this.updBy = updBy;
		this.updDate = updDate;
		this.useFlag = useFlag;
		this.menuFlag = menuFlag;
	}

	/**
	 * @return Returns the delFlag.
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag
	 *            The delFlag to set.
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return Returns the insFlag.
	 */
	public String getInsFlag() {
		return insFlag;
	}

	/**
	 * @param insFlag
	 *            The insFlag to set.
	 */
	public void setInsFlag(String insFlag) {
		this.insFlag = insFlag;
	}

	/**
	 * @return Returns the menuFlag.
	 */
	public String getMenuFlag() {
		return menuFlag;
	}

	/**
	 * @param menuFlag
	 *            The menuFlag to set.
	 */
	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

	/**
	 * @return Returns the pk.
	 */
	public SuUserMenuPK getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            The pk to set.
	 */
	public void setPk(SuUserMenuPK pk) {
		this.pk = pk;
	}

	/**
	 * @return Returns the qryFlag.
	 */
	public String getQryFlag() {
		return qryFlag;
	}

	/**
	 * @param qryFlag
	 *            The qryFlag to set.
	 */
	public void setQryFlag(String qryFlag) {
		this.qryFlag = qryFlag;
	}

	/**
	 * @return Returns the repFlag.
	 */
	public String getRepFlag() {
		return repFlag;
	}

	/**
	 * @param repFlag
	 *            The repFlag to set.
	 */
	public void setRepFlag(String repFlag) {
		this.repFlag = repFlag;
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
	 * @return Returns the updFlag.
	 */
	public String getUpdFlag() {
		return updFlag;
	}

	/**
	 * @param updFlag
	 *            The updFlag to set.
	 */
	public void setUpdFlag(String updFlag) {
		this.updFlag = updFlag;
	}

	/**
	 * @return Returns the useFlag.
	 */
	public String getUseFlag() {
		return useFlag;
	}

	/**
	 * @param useFlag
	 *            The useFlag to set.
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
}
