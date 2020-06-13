package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnEmpDataStatusDAO;
import com.ss.tp.dto.PnEmpDataStatusVO;
import com.ss.tp.model.PnEmpDataStatus;

public class PnEmpDataStatusDAOImpl extends HibernateDaoSupport implements
		PnEmpDataStatusDAO, Serializable {
	public List findByCriteria(String ouCode, String empCode, Date statusDate) {
		List empList = new ArrayList();
		/*
		 * empList = this.getSession().createQuery("from PnEmpDataStatus d " +
		 * "where d.pk.ouCode ='"+ouCode+"' " +
		 * "and d.pk.empCode ='"+empCode+"' " +
		 * "and d.statusDate ="+statusDate+" "+ "order by d.pk.empCode").list();
		 */
		String hql = "from PnEmpDataStatus d " + "where d.pk.ouCode ='"
				+ ouCode + "' " + "and d.pk.empCode ='" + empCode + "' "
				+ "and d.statusDate =? " + "order by d.pk.empCode";
		Session s = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query q = s.createQuery(hql);
		q.setDate(0, statusDate);
		empList = q.list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PnEmpDataStatus emp = (PnEmpDataStatus) iter.next();
			PnEmpDataStatusVO ret = new PnEmpDataStatusVO();
			try {
				BeanUtils.copyProperties(ret, emp.getPk());
				BeanUtils.copyProperties(ret, emp);
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}
}
