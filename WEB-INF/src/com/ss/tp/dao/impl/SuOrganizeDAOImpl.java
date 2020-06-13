/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.SuOrganizeDAO;
import com.ss.tp.model.SuOrganize;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuOrganizeDAOImpl extends HibernateDaoSupport implements
		SuOrganizeDAO, Serializable {
	public String findOrganizeName(String ouCode) {

		SuOrganize emp = new SuOrganize();
		try {
			emp = (SuOrganize) getSession().load(SuOrganize.class, ouCode);
		} catch (Exception e) {
		}
		return emp.getOuName().toString().toString().toString().toString();
	}
}
