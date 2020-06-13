package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.RwParValueDAO;
import com.ss.tp.dto.RwParValueVO;
import com.ss.tp.model.RwParValue;

public class RwParValueDAOImpl extends HibernateDaoSupport implements
		RwParValueDAO, Serializable {
	public List findByCriteria(Double keyValue) {

		Criteria kv = this.getSession().createCriteria(RwParValue.class);
		kv.add(Restrictions.eq("pk.paramKey", keyValue));
		kv.addOrder(Order.asc("pk.paramIndex"));

		List valList = kv.list();
		List ret = new ArrayList();
		for (Iterator iter = valList.iterator(); iter.hasNext();) {
			RwParValue rpv = (RwParValue) iter.next();
			RwParValueVO rpvVO = new RwParValueVO();
			try {
				BeanUtils.copyProperties(rpvVO, rpv.getPk());
				BeanUtils.copyProperties(rpvVO, rpv);
				ret.add(rpvVO);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}
}
