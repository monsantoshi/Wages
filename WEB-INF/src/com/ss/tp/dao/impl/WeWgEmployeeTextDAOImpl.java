package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.hibernate.Session;

import com.ss.tp.dao.WeWgEmployeeTextDAO;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WeWgEmployeeTextEmployeeVO;
import com.ss.tp.dto.WeWgEmployeeTextVO;
import com.ss.tp.model.WeWgEmployeeText;

public class WeWgEmployeeTextDAOImpl extends HibernateDaoSupport implements
		WeWgEmployeeTextDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public WeWgEmployeeTextDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertWeWgEmployeeText(WeWgEmployeeTextVO wewgemployeetextvo)
			throws Exception {
		WeWgEmployeeText rw = new WeWgEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wewgemployeetextvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WeWgEmployeeText loadWeWgEmployeeText(WeWgEmployeeTextVO rpVo) {
		WeWgEmployeeText rwp = new WeWgEmployeeText();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (WeWgEmployeeText) this.getHibernateTemplate().load(
					WeWgEmployeeText.class, rpVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateWeWgEmployeeText(WeWgEmployeeTextVO wewgemployeetextvo)
			throws Exception {
		WeWgEmployeeText rw = new WeWgEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wewgemployeetextvo);
			rw = this.loadWeWgEmployeeText(wewgemployeetextvo);
			// BeanUtils.copyProperties(rw ,wewgemployeetextvo);
			// rw.setYearWork(wewgemployeetextvo.getYearWork());
			// rw.setFlagPr(wewgemployeetextvo.getFlagPr());

			rw.setEmpCode(wewgemployeetextvo.getEmpCode());
			rw.setPreName(wewgemployeetextvo.getPreName());
			rw.setEngName(wewgemployeetextvo.getEngName());
			rw.setEngLastname(wewgemployeetextvo.getEngLastname());
			// rw.setPeriodWork(wewgemployeetextvo.getPeriodWork());
			// rw.setMorDay(wewgemployeetextvo.getMorDay());
			// rw.setMorHour(wewgemployeetextvo.getMorHour());
			// rw.setAftDay(wewgemployeetextvo.getAftDay());
			// rw.setAftHour(wewgemployeetextvo.getAftHour());
			// rw.setEvnDay(wewgemployeetextvo.getEvnDay());
			// rw.setEvnHour(wewgemployeetextvo.getEvnHour());
			// rw.setSeqData(wewgemployeetextvo.getSeqData());
			rw.setUpdBy(wewgemployeetextvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWeWgEmployeeText(WeWgEmployeeTextVO wewgemployeetextvo)
			throws Exception {
		WeWgEmployeeText rw = new WeWgEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wewgemployeetextvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(WeWgEmployeeTextVO wewgemployeetextvo) {
		this.rwList.add(wewgemployeetextvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateWeWgEmployeeTexts() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				WeWgEmployeeTextVO wewgemployeetextvo = (WeWgEmployeeTextVO) this.rwList
						.get(i);
				if ((wewgemployeetextvo.getKeySeq() != new Long(0))
						&& wewgemployeetextvo.getKeySeq() != null
						&& !wewgemployeetextvo.getKeySeq().equals("")) {
					this.updateWeWgEmployeeText(wewgemployeetextvo);
				} else {
					this.insertWeWgEmployeeText(wewgemployeetextvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertWeWgEmployeeTexts(List wewgemployeetextvolist)
			throws Exception {
		WeWgEmployeeText rw = new WeWgEmployeeText();
		try {
			for (int i = 0; i < wewgemployeetextvolist.size(); i++) {
				WeWgEmployeeTextVO wewgemployeetextvo = (WeWgEmployeeTextVO) wewgemployeetextvolist
						.get(i);

				BeanUtils.copyProperties(rw, wewgemployeetextvo);
				rw.setCreDate(new Date());
				rw.setUpdDate(new Date());
				this.getHibernateTemplate().save(rw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord) {

		StringBuffer criteria = new StringBuffer();

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rwPre.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwPre.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rwPre.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, rwPre.codeSeq,rwPre.preName,rwPre.engName,rwPre.engLastname ");
		hql.append(" from WeWgEmployeeText rwPre , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePKk.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = pnOrg.pk.codeSeq ");
		hql.append(" order by rwPre.codeSeq, rwPre.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			Long codeSeq = (Long) r[4];
			String preName = (String) r[5];
			String engName = (String) r[6];
			String engLastname = (String) r[7];

			WeWgEmployeeTextEmployeeVO ret = new WeWgEmployeeTextEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPreName(preName);
			ret.setCodeSeq(codeSeq);
			ret.setEngName(engName);
			ret.setEngLastname(engLastname);
			retList.add(ret);
		}
		return retList;
	}

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo) {

		StringBuffer criteria = new StringBuffer();
		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rwPre.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwPre.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rwPre.empCode) ");
		hql.append(" from WeWgEmployeeText rwPre , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.areaDesc,pnOrg.secDesc)|| ' ' || pnOrg.workDesc ");
		hql.append(" from WeWgEmployeeText rwPre, ");
		hql.append(" WgEmployee pnEmp, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rwPre.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		WeEmployeeVO ret = new WeEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String orgDesc = (String) r[4];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOrgDesc(orgDesc);
			// ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from WeWgEmployeeText rw ");
		hql.append(" where rw.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPnEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");

		// hql.append(" and rw.codeSeq in ( ");
		// hql.append(" select vpn.pk.codeSeq ");
		// hql.append(" from VPnOrganizationSecurity vpn ");
		// hql.append(" where vpn.pk.userId = '");
		// hql.append(userId);
		// hql.append("' ");
		// hql.append(" and rw.ouCode = vpn.pk.ouCode ");
		// hql.append(" and rw.codeSeq = vpn.pk.codeSeq ) ");

		System.out.println("HQL findByEmpCodeList ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		if (empList != null && empList.size() > 0) {
			for (int i = 0; i < empList.size(); i++) {
				WeWgEmployeeTextVO rw = new WeWgEmployeeTextVO();

				try {
					BeanUtils.copyProperties(rw, empList.get(i));
					retList.add(i, rw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return retList;
	}

	public boolean canDelete(String ouCode, String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from WeWgEmployeeText p, VPnEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.pnTransfer = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return false;
			else
				return true;
		} else
			return true;
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord)

	{
		StringBuffer criteria = new StringBuffer();

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rwInc.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwInc.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwInc.keySeq, ");
		hql.append(" rwInc.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" rwInc.codeSeq, ");
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.secDesc,pnOrg.areaDesc)|| ' ' || pnOrg.workDesc, ");
		hql.append(" rwInc.preName, ");
		hql.append(" rwInc.engName, ");
		hql.append(" rwInc.engLastname ");
		hql.append(" from WeWgEmployeeText rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by pnOrg.divSeq,nvl(pnOrg.areaSeq,0),nvl(pnOrg.secSeq,0), rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		// List empList = this.getSession().createQuery(hql.toString()).list();

		// List retList = new ArrayList();

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String empCode = (String) r[1];
			String prefixName = (String) r[2];
			String firstName = (String) r[3];
			String lastName = (String) r[4];
			Long codeSeq = (Long) r[5];
			String orgDesc = (String) r[6];
			String preName = (String) r[7];
			String engName = (String) r[8];
			String engLastname = (String) r[9];

			WeWgEmployeeTextEmployeeVO ret = new WeWgEmployeeTextEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setCodeSeq(codeSeq);
			ret.setOrgDesc(orgDesc);
			ret.setPreName(preName);
			ret.setEngName(engName);
			ret.setEngLastname(engLastname);

			retList.add(ret);
		}

		return retList;
	}

}
