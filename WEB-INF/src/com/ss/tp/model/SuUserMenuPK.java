package com.ss.tp.model;

import java.io.Serializable;

/**
 * @author Ta
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuUserMenuPK implements Serializable {
	private String userGroup;
	private String menuId;

	public SuUserMenuPK() {
	}

	public SuUserMenuPK(String userGroup, String menuId) {
		super();
		this.userGroup = userGroup;
		this.menuId = menuId;
	}

	/**
	 * @return Returns the menuId.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            The menuId to set.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return Returns the userGroup.
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            The userGroup to set.
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
}
