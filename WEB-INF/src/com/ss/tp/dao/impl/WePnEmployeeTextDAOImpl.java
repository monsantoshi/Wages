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
import com.ss.tp.dao.WePnEmployeeTextDAO;

import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnEmployeeTextVO;
//import com.ss.tp.dto.RwPremiumEmployeeVO;
//import com.ss.tp.dto.RwPremiumReportByOrgVO;
import com.ss.tp.model.WePnEmployeeText;
import com.ss.tp.dto.WePnEmployeeTextEmployeeVO;

import org.hibernate.Session;

public class WePnEmployeeTextDAOImpl extends HibernateDaoSupport implements
		WePnEmployeeTextDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public WePnEmployeeTextDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		WePnEmployeeText rw = new WePnEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wepnemployeetextvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WePnEmployeeText loadWePnEmployeeText(WePnEmployeeTextVO rpVo) {
		WePnEmployeeText rwp = new WePnEmployeeText();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (WePnEmployeeText) this.getHibernateTemplate().load(
					WePnEmployeeText.class, rpVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		WePnEmployeeText rw = new WePnEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wepnemployeetextvo);
			rw = this.loadWePnEmployeeText(wepnemployeetextvo);
			// BeanUtils.copyProperties(rw ,wepnemployeetextvo);
			// rw.setYearWork(wepnemployeetextvo.getYearWork());
			// rw.setFlagPr(wepnemployeetextvo.getFlagPr());

			rw.setEmpCode(wepnemployeetextvo.getEmpCode());
			rw.setPreName(wepnemployeetextvo.getPreName());
			rw.setEngName(wepnemployeetextvo.getEngName());
			rw.setEngLastname(wepnemployeetextvo.getEngLastname());
			// rw.setPeriodWork(wepnemployeetextvo.getPeriodWork());
			// rw.setMorDay(wepnemployeetextvo.getMorDay());
			// rw.setMorHour(wepnemployeetextvo.getMorHour());
			// rw.setAftDay(wepnemployeetextvo.getAftDay());
			// rw.setAftHour(wepnemployeetextvo.getAftHour());
			// rw.setEvnDay(wepnemployeetextvo.getEvnDay());
			// rw.setEvnHour(wepnemployeetextvo.getEvnHour());
			// rw.setSeqData(wepnemployeetextvo.getSeqData());
			rw.setUpdBy(wepnemployeetextvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		WePnEmployeeText rw = new WePnEmployeeText();
		try {
			BeanUtils.copyProperties(rw, wepnemployeetextvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(WePnEmployeeTextVO wepnemployeetextvo) {
		this.rwList.add(wepnemployeetextvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateWePnEmployeeTexts() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				WePnEmployeeTextVO wepnemployeetextvo = (WePnEmployeeTextVO) this.rwList
						.get(i);
				if ((wepnemployeetextvo.getKeySeq() != new Long(0))
						&& wepnemployeetextvo.getKeySeq() != null
						&& !wepnemployeetextvo.getKeySeq().equals("")) {
					this.updateWePnEmployeeText(wepnemployeetextvo);
				} else {
					this.insertWePnEmployeeText(wepnemployeetextvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertWePnEmployeeTexts(List wepnemployeetextvolist)
			throws Exception {
		WePnEmployeeText rw = new WePnEmployeeText();
		try {
			for (int i = 0; i < wepnemployeetextvolist.size(); i++) {
				WePnEmployeeTextVO wepnemployeetextvo = (WePnEmployeeTextVO) wepnemployeetextvolist
						.get(i);

				BeanUtils.copyProperties(rw, wepnemployeetextvo);
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

		hql.append(" select distinct rwPre.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rwPre.codeSeq,pnEmp.levelCode,rwPre,preName,rwPre.engName,rwPre.engLastname ");
		hql.append(" from WePnEmployeeText rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = pnOrg.pk.codeSeq ");
		hql.append(" order by rwPre.codeSeq,pnEmp.levelCode desc, rwPre.empCode ");

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
			String positionShort = (String) r[4];
			Long codeSeq = (Long) r[5];
			String levelCode = (String) r[6];
			String preName = (String) r[7];
			String engName = (String) r[8];
			String engLastname = (String) r[9];

			int intLevelCode = Integer.parseInt(levelCode.trim());

			WePnEmployeeTextEmployeeVO ret = new WePnEmployeeTextEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOldPositionShort(positionShort + " " + intLevelCode);
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
		hql.append(" from WePnEmployeeText rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnEmp.levelCode,");
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.areaDesc,pnOrg.secDesc)|| ' ' || pnOrg.workDesc ");
		hql.append(" from WePnEmployeeText rwPre, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rwPre.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeqAct = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		WeEmployeeVO ret = new WeEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String positionShort = (String) r[4];
			String levelCode = (String) r[5];
			String orgDesc = (String) r[6];

			int intLevelCode = Integer.parseInt(levelCode.trim());
			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOldPositionShort(positionShort + " " + intLevelCode);
			ret.setOrgDesc(orgDesc);
			// ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from WePnEmployeeText rw ");
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
				WePnEmployeeTextVO rw = new WePnEmployeeTextVO();

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
		sql.append(" from WePnEmployeeText p, VPnEmployeeSecurity e ");
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
		hql.append(" rwInc.codeSeqAct, ");
		hql.append(" pp.positionShort, ");
		hql.append(" pnEmp.levelCode, ");
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.secDesc,pnOrg.areaDesc)|| ' ' || pnOrg.workDesc, ");
		hql.append(" rwInc.preName, ");
		hql.append(" rwInc.engName, ");
		hql.append(" rwInc.engLastname, ");
		hql.append(" nvl(pnEmp.adminCode,chr(250)) ");
		hql.append(" from WePnEmployeeText rwInc , PnEmployee pnEmp ,PnPosition pp, PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeqAct = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwInc.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and pp.pk.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and pp.pk.gworkCode = pnEmp.gworkCode ");
		hql.append(" and pp.pk.positionCode = pnEmp.positionCode ");
		hql.append(" and rwInc.empCode = pnEmp.pk.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeqAct = pnOrg.pk.codeSeq ");
		hql.append(" order by pnOrg.divSeq,nvl(pnOrg.areaSeq,0),nvl(pnOrg.secSeq,0),nvl(pnEmp.adminCode,chr(250)),to_number(pnEmp.levelCode) desc, rwInc.empCode ");

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
			String positionShort = (String) r[6];
			String levelCode = (String) r[7];
			String orgDesc = (String) r[8];
			String preName = (String) r[9];
			String engName = (String) r[10];
			String engLastname = (String) r[11];

			// String intLevelCode =
			// Integer.toString(Integer.parseInt(levelCode));
			int intLevelCode = Integer.parseInt(levelCode.trim());
			// if (intLevelCode == 0){
			// intLevelCode = null;
			// }

			WePnEmployeeTextEmployeeVO ret = new WePnEmployeeTextEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setCodeSeq(codeSeq);
			ret.setOldPositionShort(positionShort + " " + intLevelCode);
			ret.setOrgDesc(orgDesc);
			ret.setPreName(preName);
			ret.setEngName(engName);
			ret.setEngLastname(engLastname);

			retList.add(ret);
		}

		return retList;
	}

}
