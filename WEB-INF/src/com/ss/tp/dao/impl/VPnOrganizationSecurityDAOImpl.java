package com.ss.tp.dao.impl;

import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.VPnOrganizationSecurityDAO;
import com.ss.tp.dto.VPnOrganizationVO;
import com.ss.tp.model.VPnOrganizationSecurity;
import com.ss.tp.model.VPnOrganizationSecurityPK;

public class VPnOrganizationSecurityDAOImpl extends HibernateDaoSupport
		implements VPnOrganizationSecurityDAO {
	public VPnOrganizationVO findByKey(String ouCode, String userId,
			String codeSeq) {
		VPnOrganizationSecurityPK pk = new VPnOrganizationSecurityPK();
		pk.setOuCode(ouCode);
		pk.setUserId(userId);
		pk.setCodeSeq(Double.valueOf(codeSeq));
		VPnOrganizationSecurity ec = (VPnOrganizationSecurity) getSession()
				.load(VPnOrganizationSecurity.class, pk);
		VPnOrganizationVO ret = new VPnOrganizationVO();
		try {
			BeanUtils.copyProperties(ret, ec.getPk());
			BeanUtils.copyProperties(ret, ec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List findByKeyPK(String ouCode, String userId, String orgCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from VPnOrganizationSecurity vpn ");
		hql.append(" where vpn.pk.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and vpn.pk.userId = '");
		hql.append(userId);
		hql.append("' ");
		hql.append(" and vpn.orgCode = '");
		hql.append(orgCode);
		hql.append("' ");

		System.out.println("HQL findByKeyPK ==> " + hql.toString());

		List retList = this.getSession().createQuery(hql.toString()).list();

		return retList;
	}
	public List findByCodeSeqPK(String ouCode, String userId, Double codeSeq) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from VPnOrganizationSecurity vpn ");
		hql.append(" where vpn.pk.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and vpn.pk.userId = '");
		hql.append(userId);
		hql.append("' ");
		hql.append(" and vpn.pk.codeSeq = '");
		hql.append(codeSeq);
		hql.append("' ");

		System.out.println("HQL findByCodeSeqPK ==> " + hql.toString());

		List retList = this.getSession().createQuery(hql.toString()).list();

		return retList;
	}
	
}
