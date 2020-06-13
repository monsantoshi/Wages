/*
 * Created on 9 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuMenu implements Serializable {
	private String menuId;
	private String menuName;
	private String mainMenu;
	private String linkName;

	/**
	 * @return Returns the linkName.
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 *            The linkName to set.
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return Returns the mainMenu.
	 */
	public String getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu
	 *            The mainMenu to set.
	 */
	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
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
	 * @return Returns the menuName.
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            The menuName to set.
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
