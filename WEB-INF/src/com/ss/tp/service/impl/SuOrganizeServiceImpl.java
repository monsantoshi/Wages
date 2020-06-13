/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;

import com.ss.tp.dao.SuOrganizeDAO;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuOrganizeServiceImpl implements SuOrganizeService, Serializable {
	private SuOrganizeDAO suOrganizeDAO;

	public SuOrganizeDAO getSuOrganizeDAO() {
		return suOrganizeDAO;
	}

	public void setSuOrganizeDAO(SuOrganizeDAO suOrganizeDAO) {
		this.suOrganizeDAO = suOrganizeDAO;
	}

	public String findOrganizeName(String ouCode) {
		return this.suOrganizeDAO.findOrganizeName(ouCode);
	}
}
