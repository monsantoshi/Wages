/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgPeriodLineDAO;
import com.ss.tp.dto.WgPeriodLineVO;
import com.ss.tp.model.WgPeriodLine;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPeriodLineDAOImpl extends HibernateDaoSupport implements
		WgPeriodLineDAO, Serializable {

	public WgPeriodLine findPeriodLine(String ouCode, String year, String period)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);

		sql.append(" FROM WgPeriodLine l ");
		sql.append(" WHERE l.wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.wgPeriodLinePK.year = " + year);
		sql.append(" AND l.wgPeriodLinePK.period	= " + period);

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			return (WgPeriodLine) ls.get(0);
		} else
			return null;
	}

	public List findYearInPeriodLine(String ouCode) throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT distinct l.wgPeriodLinePK.ouCode,l.wgPeriodLinePK.year");
		sql.append(" FROM WgPeriodLine l ");
		sql.append(" WHERE l.wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		sql.append(" ORDER BY  l.wgPeriodLinePK.year");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();
		System.out.println(yearList);
		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Integer year = (Integer) r[1];

			WgPeriodLineVO ret = new WgPeriodLineVO();
			ret.setOuCode(ouCode);
			ret.setYear(year);
			retList.add(ret);
		}
		return retList;
	}

	public List findPeriodInPeriodLine(String ouCode, Integer year)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT l.wgPeriodLinePK.ouCode,l.wgPeriodLinePK.year,l.wgPeriodLinePK.period,l.periodName");
		sql.append(" FROM WgPeriodLine l ");
		sql.append(" WHERE l.wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.wgPeriodLinePK.year = " + year);
		sql.append(" ORDER BY  l.wgPeriodLinePK.period");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Integer period = (Integer) r[2];
			String periodName = (String) r[3];

			WgPeriodLineVO ret = new WgPeriodLineVO();
			ret.setOuCode(ouCode);
			ret.setYear(year);
			ret.setPeriod(period);
			ret.setPeriodName(periodName);

			retList.add(ret);
		}
		return retList;
	}

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" FROM WgPeriodLine l ");
		sql.append(" WHERE l.wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.wgPeriodLinePK.year = " + year);
		sql.append(" AND l.wgPeriodLinePK.period = " + period);
		sql.append(" AND (l.tranClose = 'Y' ");
		sql.append(" OR l.mainClose = 'Y') ");

		List ls = this.getSession().createQuery(sql.toString()).list();

		if (ls != null && ls.size() > 0) {
			return true;
		} else
			return false;
	}

	public String findMaxPeriodOfYear(String ouCode, String year) {

		StringBuffer sql = new StringBuffer(0);

		sql.append(" SELECT max(l.wgPeriodLinePK.period) ");
		sql.append(" FROM WgPeriodLine l ");
		sql.append(" WHERE l.wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.wgPeriodLinePK.year = " + year);

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			String period = "";
			Integer r = (Integer) ls.get(0);
			period = r.toString();
			return period;
		} else
			return null;
	}
}
