package com.ss.tp.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.SuUserOrganizationDAO;
import com.ss.tp.dto.PnOrganizationVO;
import com.ss.tp.model.PnOrganization;

public class SuUserOrganizationDAOImpl extends HibernateDaoSupport implements
		SuUserOrganizationDAO {

	public PnOrganizationVO findOrganizationByCriteria(String userId,
			String ouCode, String orgCode) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where ");
		sql.append(" a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append("	and v.orgCode = '" + orgCode + "' ");
		sql.append(" ) ");
		sql.append(" order by a.orgCode ");
		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(sql.toString());
		// System.out.println("*******************");

		PnOrganizationVO orgVo = new PnOrganizationVO();
		if (ls != null && ls.size() > 0) {

			try {
				PnOrganization org = (PnOrganization) ls.get(0);
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return orgVo;
	}

	public PnOrganizationVO findPrOrganizationByCriteria(String userId,
			String ouCode, String orgCode, String year, String period)
			throws Exception {
		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" from PnOrganization a ");
			sql.append(" where ");
			sql.append(" a.pk.ouCode = '" + ouCode + "' ");
			sql.append(" and a.orgCode = '" + orgCode.trim() + "' ");
			sql.append(" and a.pk.codeSeq in ( ");
			sql.append("	select v.pk.codeSeq ");
			sql.append("	from VPnOrganizationSecurity v ");
			sql.append("	where v.pk.userId = '" + userId + "' ");
			sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
			sql.append(" ) ");
			sql.append(" order by a.orgCode ");

			// sql.append(" select distinct p.codeSeqWork ");
			// sql.append(" from VPrEmployeeSecurity v, PrEmployee p ");
			// sql.append(" where ");
			// sql.append(" v.pk.userId = '"+userId+"' ");
			// sql.append(" and v.pk.ouCode = '"+ouCode+"' ");
			// sql.append(" and v.pk.year = " + year);
			// sql.append(" and v.pk.period = " + period);
			// sql.append(" and v.pk.ouCode = p.key.ouCode ");
			// sql.append(" and v.pk.empCode = p.key.empCode ");
			// sql.append(" and v.pk.year = p.key.year ");
			// sql.append(" and v.pk.period = p.key.period ");
			// sql.append(" ) ");

			// System.out.println("*******************");
			List ls = this.getHibernateTemplate().find(sql.toString());
			// System.out.println("*******************");

			PnOrganizationVO orgVo = new PnOrganizationVO();
			if (ls != null && ls.size() > 0) {

				try {
					PnOrganization org = (PnOrganization) ls.get(0);
					BeanUtils.copyProperties(orgVo, org);
					BeanUtils.copyProperties(orgVo, org.getPk());
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}

			return orgVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findOrganizationByUserIdAndOuCode(String userId, String ouCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where ");
		sql.append(" a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");
		sql.append(" order by a.orgCode ");
		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(sql.toString());
		// System.out.println("*******************");
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PnOrganizationVO orgVo = new PnOrganizationVO();
			try {
				PnOrganization org = (PnOrganization) ls.get(i);
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
				ls.set(i, orgVo);

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		System.out.println("finished ...  : " + ls.size());

		return ls;
	}

	public List findOrganizationByUserIdAndOuCodeToOrgCode(String userId,
			String ouCode, String orgCode) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCode != null && !orgCode.equals(""))
			criteria.append("and a.orgCode >= '" + orgCode + "' ");

		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where ");
		sql.append(" a.pk.ouCode = '" + ouCode + "' ");
		sql.append(criteria);
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");
		sql.append(" order by a.orgCode ");

		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PnOrganizationVO orgVo = new PnOrganizationVO();
			try {
				PnOrganization org = (PnOrganization) ls.get(i);
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
				ls.set(i, orgVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.tp.dao.SuUserOrganizationDAO#findSecByDiv(java.lang.String)
	 */
	public List findSecByDiv(String ouCode, String codeSeq, String userId)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where a.levelCode='4' ");
		if (codeSeq != null && !codeSeq.equals("")) {
			sql.append(" and a.areaCode = '" + codeSeq + "' ");
		}
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");
		sql.append(" and a.pk.ouCode='" + ouCode + "' ");
		sql.append(" order by a.secCode ");

		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PnOrganizationVO orgVo = new PnOrganizationVO();
			try {
				PnOrganization org = (PnOrganization) ls.get(i);
				System.out.println("****" + org.getSecCode());
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
				ls.set(i, orgVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return ls;
	}

	public List findWorkCodeByAreaAndSecCode(String ouCode, String areaCode,
			String secCode, String userId) throws Exception {
		List rlstList = new ArrayList();
		StringBuffer sql = new StringBuffer(0);
		try {
			PnOrganizationVO vo = null;
			PnOrganization org = null;
			sql.append(" from PnOrganization org ");
			sql.append(" where org.levelCode = '5' ");
			if (areaCode != null && !areaCode.equals("")) {
				sql.append(" and org.areaCode = '" + areaCode + "'");
			}
			if (secCode != null && !secCode.equals("")) {
				sql.append(" and org.secCode = '" + secCode + "'");
			}
			sql.append(" and org.pk.codeSeq in ( ");
			sql.append("	select distinct v.pk.codeSeq ");
			sql.append("	from VPnOrganizationSecurity v ");
			sql.append("	where ");
			sql.append("		 v.pk.userId = '" + userId + "' ");
			sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
			sql.append(" ) ");
			sql.append(" and org.pk.ouCode = '" + ouCode + "'");
			sql.append(" order by org.workCode ");

			System.out.println(sql);
			List tmpList = this.getHibernateTemplate().find(sql.toString());
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				org = (PnOrganization) itt.next();
				vo = new PnOrganizationVO();
				BeanUtils.copyProperties(vo, org);
				BeanUtils.copyProperties(vo, org.getPk());
				rlstList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlstList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ss.tp.dao.SuUserOrganizationDAO#findOrganizationByUserIdAndOuCodeLevel31
	 * (java.lang.String, java.lang.String)
	 */
	public List findOrganizationByUserIdAndOuCodeLevel31(String userId,
			String ouCode) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where ");
		sql.append(" a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.levelCode = '31' ");
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");
		sql.append(" order by a.areaCode ");
		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(sql.toString());
		// System.out.println("*******************");
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PnOrganizationVO orgVo = new PnOrganizationVO();
			try {
				PnOrganization org = (PnOrganization) ls.get(i);
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
				ls.set(i, orgVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}
	public List findOrganizationByUserIdAndOuCodeLevel3(String userId,
			String ouCode) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PnOrganization a ");
		sql.append(" where ");
		sql.append(" a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.levelCode = '3' ");
		sql.append(" and a.pk.codeSeq in ( ");
		sql.append("	select distinct v.pk.codeSeq ");
		sql.append("	from VPnOrganizationSecurity v ");
		sql.append("	where ");
		sql.append("		 v.pk.userId = '" + userId + "' ");
		sql.append("	and v.pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");
		sql.append(" order by a.divCode ");
		
		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(sql.toString());
		// System.out.println("*******************");
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PnOrganizationVO orgVo = new PnOrganizationVO();
			try {
				PnOrganization org = (PnOrganization) ls.get(i);
				BeanUtils.copyProperties(orgVo, org);
				BeanUtils.copyProperties(orgVo, org.getPk());
				ls.set(i, orgVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}
}
