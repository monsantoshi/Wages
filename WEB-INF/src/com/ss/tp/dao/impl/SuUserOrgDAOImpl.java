package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.SuUserOrgDAO;
import com.ss.tp.dto.SuUserOrgVO;
import com.ss.tp.model.SuUserOrg;

public class SuUserOrgDAOImpl extends HibernateDaoSupport implements
		SuUserOrgDAO, Serializable {
	public List findByCriteria(String userId) {
		List ouList = new ArrayList();
		ouList = this
				.getSession()
				.createQuery(
						"from SuUserOrg e where e.pk.userId ='"
								+ userId
								+ "' "
								+ "and e.pk.userId in ( select a.userId from SuUser a where a.userId ='"
								+ userId
								+ "' and a.inactive = 'N' and a.webFlag = 'Y' )"
								+ "order by e.pk.ouCode").list();
		List retList = new ArrayList();
		for (Iterator iter = ouList.iterator(); iter.hasNext();) {

			SuUserOrg user = (SuUserOrg) iter.next();
			SuUserOrgVO ret = new SuUserOrgVO();
			try {
				BeanUtils.copyProperties(ret, user.getPk());
				BeanUtils.copyProperties(ret, user);
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}
}
