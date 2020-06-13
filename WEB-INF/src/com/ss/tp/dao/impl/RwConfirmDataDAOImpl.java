package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.RwConfirmDataDAO;
import com.ss.tp.dto.*;
import com.ss.tp.model.RwConfirmData;
import com.ss.tp.model.RwConfirmDataPK;

public class RwConfirmDataDAOImpl extends HibernateDaoSupport implements
		RwConfirmDataDAO, Serializable {
	public RwConfirmDataDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwConfirmData(RwConfirmDataVO vo) throws Exception {
		RwConfirmData rw = new RwConfirmData();
		RwConfirmDataPK pk = new RwConfirmDataPK();
		try {
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from RwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '1' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm master : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
	}

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from RwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '2' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm rw : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
	}

}