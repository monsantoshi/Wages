package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.ss.tp.dao.PrPeriodLineDAO;
import com.ss.tp.dao.FeeWpayPrPeriodLineDAO;
//import com.ss.tp.dto.PrPeriodLineVO;
import com.ss.tp.dto.FeeWpayPrPeriodLineVO;
//import com.ss.tp.model.PrPeriodLine;
import com.ss.tp.model.FeeWpayPrPeriodLine;


public class FeeWpayPrPeriodLineDAOImpl extends HibernateDaoSupport implements
		FeeWpayPrPeriodLineDAO, Serializable {

	private Log logger = LogFactory.getLog(this.getClass());

	public FeeWpayPrPeriodLine findPeriodLine(String ouCode, String year, String period,String userId)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		// sql.append(" SELECT l.periodName,l.pk.period");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.pk.year = " + year);
		sql.append(" AND l.pk.creBy = '" + userId + "' ");
		sql.append(" AND l.pk.period	= " + period);

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			return (FeeWpayPrPeriodLine) ls.get(0);
		} else
			return null;
	}

	public List findYearInPeriodLine(String ouCode) throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT distinct l.pk.ouCode,l.pk.year");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ORDER BY  l.pk.year");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();
		System.out.println(yearList);
		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double year = (Double) r[1];

			FeeWpayPrPeriodLineVO ret = new FeeWpayPrPeriodLineVO();
			ret.setOuCode(ouCode);
			ret.setYear(year);
			retList.add(ret);
		}
		return retList;
	}

	public boolean canDeleteData(String ouCode, String year, String period,String userId)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT count(*) ");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.pk.year = " + year);
		sql.append(" AND l.pk.period = " + period);
		sql.append(" AND l.pk.creBy = '" + userId + "' ");
		sql.append(" AND l.prClose = 'Y' ");

		List ls = this.getSession().createQuery(sql.toString()).list();

		// System.out.println("list of can delete data : " + ls.size());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean isCloseTranClose(String ouCode, String year, String period,String userId)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT count(*) ");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.pk.year = " + year);
		sql.append(" AND l.pk.period = " + period);
		sql.append(" AND l.pk.creBy = '" + userId + "' ");
		sql.append(" AND l.tranClose = 'Y' ");

		List ls = this.getSession().createQuery(sql.toString()).list();

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}
	public boolean isCloseMasterClose(String ouCode, String year, String period,String userId)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT count(*) ");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.pk.year = " + year);
		sql.append(" AND l.pk.period = " + period);
		sql.append(" AND l.pk.creBy = '" + userId + "' ");
		sql.append(" AND l.mainClose = 'Y' ");

		List ls = this.getSession().createQuery(sql.toString()).list();

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public List findPeriodInPeriodLine(String ouCode, Double year,String userId)
			throws Exception {

		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT l.pk.ouCode,l.pk.year,l.pk.period,l.periodName");
		sql.append(" FROM FeeWpayPrPeriodLine l ");
		sql.append(" WHERE l.pk.ouCode = '" + ouCode + "' ");
		sql.append(" AND l.pk.year = " + year);
		sql.append(" AND l.pk.creBy = '" + userId + "' ");
		sql.append(" ORDER BY  l.pk.period");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double period = (Double) r[2];
			String periodName = (String) r[3];

			FeeWpayPrPeriodLineVO ret = new FeeWpayPrPeriodLineVO();
			ret.setOuCode(ouCode);
			ret.setYear(year);
			ret.setPeriod(period);
			ret.setPeriodName(periodName);

			retList.add(ret);
		}
		return retList;
	}

}
