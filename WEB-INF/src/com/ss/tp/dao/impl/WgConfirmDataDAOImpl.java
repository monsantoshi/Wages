package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgConfirmDataDAO;
import com.ss.tp.dto.WgConfirmDataVO;
import com.ss.tp.model.WgConfirmData;
import com.ss.tp.model.WgConfirmDataPK;

public class WgConfirmDataDAOImpl extends HibernateDaoSupport implements
		WgConfirmDataDAO, Serializable {
	public WgConfirmDataDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertWgConfirmData(WgConfirmDataVO vo) throws Exception {
		WgConfirmData rw = new WgConfirmData();
		WgConfirmDataPK pk = new WgConfirmDataPK();
		try {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from WgConfirmData ");
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
			return false;
		}
	}

	public boolean isConfirmWgData(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from WgConfirmData ");
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
			return false;
		}
	}

}