/*
 * Created on 9 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.SuMenuDAO;
import com.ss.tp.service.SuMenuService;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuMenuServiceImpl implements SuMenuService, Serializable {
	private SuMenuDAO suMenuDAO;

	/**
	 * @return Returns the suMenuDAO.
	 */
	public SuMenuDAO getSuMenuDAO() {
		return suMenuDAO;
	}

	/**
	 * @param suMenuDAO
	 *            The suMenuDAO to set.
	 */
	public void setSuMenuDAO(SuMenuDAO suMenuDAO) {
		this.suMenuDAO = suMenuDAO;
	}

	public List findSuMenu(String UserGroup) {
		return this.suMenuDAO.findSuMenu(UserGroup);
	}
}
