package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.RwHealthDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwHealthVO;
import com.ss.tp.dto.RwHealthEmployeeVO;
import com.ss.tp.model.RwHealth;

public class RwHealthDAOImpl extends HibernateDaoSupport implements
		RwHealthDAO, Serializable {
	List rwList = new ArrayList();

	public RwHealthDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwHealth(RwHealthVO rwhealthvo) throws Exception {
		RwHealth rw = new RwHealth();
		try {
			BeanUtils.copyProperties(rw, rwhealthvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwHealth loadRwHealth(RwHealthVO rwVo) {
		RwHealth rwp = new RwHealth();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (RwHealth) this.getHibernateTemplate().load(RwHealth.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwHealth(RwHealthVO rwhealthvo) throws Exception {
		RwHealth rw = new RwHealth();
		try {
			BeanUtils.copyProperties(rw, rwhealthvo);
			rw = this.loadRwHealth(rwhealthvo);
			rw.setFlagPr(rwhealthvo.getFlagPr());
			rw.setEmpCode(rwhealthvo.getEmpCode());
			rw.setYearWork(rwhealthvo.getYearWork());
			rw.setPeriodWork(rwhealthvo.getPeriodWork());
			rw.setTotalHour(rwhealthvo.getTotalHour());
			rw.setSeqData(rwhealthvo.getSeqData());
			rw.setUpdBy(rwhealthvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwHealth(RwHealthVO rwhealthvo) throws Exception {
		RwHealth rw = new RwHealth();
		try {
			BeanUtils.copyProperties(rw, rwhealthvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwHealthVO rwhealthvo) {
		this.rwList.add(rwhealthvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwHealths() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwHealthVO rwhealthvo = (RwHealthVO) this.rwList.get(i);
				if ((rwhealthvo.getKeySeq() != new Long(0))
						&& rwhealthvo.getKeySeq() != null
						&& !rwhealthvo.getKeySeq().equals("")) {
					this.updateRwHealth(rwhealthvo);
				} else {
					this.insertRwHealth(rwhealthvo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwHealths(List rwhealthvolist) throws Exception {
		RwHealth rw = new RwHealth();
		try {
			for (int i = 0; i < rwhealthvolist.size(); i++) {
				RwHealthVO rwhealthvo = (RwHealthVO) rwhealthvolist.get(i);

				BeanUtils.copyProperties(rw, rwhealthvo);
				rw.setCreDate(new Date());
				rw.setUpdDate(new Date());
				this.getHibernateTemplate().save(rw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

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
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		/*
		 * hql.append( " select distinct rw.empCode,
		 * pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName,
		 * pnPos.positionShort, rw.codeSeq " ); hql.append( " from RwHealth rw ,
		 * PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg " );
		 * hql.append(" where rw.ouCode = '"); hql.append(evaOuCode);
		 * hql.append("' "); hql.append(" and pnEmp.pk.empCode in ( ");
		 * hql.append(" select pk.empCode "); hql.append(" from
		 * VPrEmployeeSecurity "); hql.append(" where pk.userId = '" + userId +
		 * "' "); hql.append(" and pk.ouCode = '" + evaOuCode + "' ");
		 * hql.append(" and pk.year = " + evaYear ); hql.append(" and pk.period
		 * = " + evaPeriod ); hql.append(" ) "); hql.append(criteria);
		 * hql.append(" and rw.ouCode = pnEmp.pk.ouCode "); hql.append(" and
		 * rw.empCode = pnEmp.pk.empCode "); hql.append(" and pnEmp.pk.ouCode =
		 * pnPos.pk.ouCode "); hql.append(" and pnEmp.gworkCode =
		 * pnPos.pk.gworkCode "); hql.append(" and pnEmp.positionCode =
		 * pnPos.pk.positionCode "); hql.append(" and rw.ouCode =
		 * pnOrg.pk.ouCode "); hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq
		 * "); hql.append(" order by rw.codeSeq, rw.empCode ");
		 */

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rw.codeSeq,pnEmp.adminCode,pnEmp.levelCode   ");
		hql.append(" from RwHealth rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rw.codeSeq,pnEmp.adminCode,pnEmp.levelCode desc, rw.empCode ");

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

			PayRollEmployeeVO ret = new PayRollEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);

			retList.add(ret);
		}
		return retList;
	}

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

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
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		/*
		 * hql.append(" select count(distinct rw.empCode) "); hql.append( " from
		 * RwHealth rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization
		 * pnOrg " ); hql.append(" where rw.ouCode = '"); hql.append(evaOuCode);
		 * hql.append("' "); hql.append(" and pnEmp.pk.empCode in ( ");
		 * hql.append(" select pk.empCode "); hql.append(" from
		 * VPrEmployeeSecurity "); hql.append(" where pk.userId = '" + userId +
		 * "' "); hql.append(" and pk.ouCode = '" + evaOuCode + "' ");
		 * hql.append(" and pk.year = " + evaYear ); hql.append(" and pk.period
		 * = " + evaPeriod ); hql.append(" ) "); hql.append(criteria);
		 * hql.append(" and rw.ouCode = pnEmp.pk.ouCode "); hql.append(" and
		 * rw.empCode = pnEmp.pk.empCode "); hql.append(" and pnEmp.pk.ouCode =
		 * pnPos.pk.ouCode "); hql.append(" and pnEmp.gworkCode =
		 * pnPos.pk.gworkCode "); hql.append(" and pnEmp.positionCode =
		 * pnPos.pk.positionCode "); hql.append(" and rw.ouCode =
		 * pnOrg.pk.ouCode "); hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq
		 * ");
		 */

		hql.append(" select count(distinct rw.empCode) ");
		hql.append(" from RwHealth rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v  ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from RwHealth rw, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PrEmployee prEmp, ");
		hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(year);
		hql.append(" and prEmp.key.period = ");
		hql.append(period);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		PayRollEmployeeVO ret = new PayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String positionShort = (String) r[4];
			String orgCode = (String) r[5];
			String orgDesc = (String) r[6];
			Double codeSeqWork = (Double) r[7];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from RwHealth rw ");
		hql.append(" where rw.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rw.yearPr = ");
		hql.append(yearPr);
		hql.append(" and rw.periodPr = ");
		hql.append(periodPr);
		hql.append(" and rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		hql.append(" 	and pk.year = " + yearPr);
		hql.append(" 	and pk.period = " + periodPr);
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
				RwHealthVO rw = new RwHealthVO();

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

	/**
	 * method ????? Query ??????? Table RW_HEALTH ?????????�???????????
	 * 
	 * @param ouCode
	 * @param year
	 * @param period
	 * @param type
	 * @return
	 */
	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId) {
		List rlst = new ArrayList();
		try {
			String sql = " select hel.ouCode, hel.yearPr, hel.periodPr, org.areaCode, org.secCode, org.areaDesc, org.secDesc, "
					+ " hel.totalHour, hel.flagPr, emp.pk.empCode, emp.refDbPreSuff.prefixName, emp.firstName, emp.lastName,org.orgCode,org.orgDesc, "
					+ " hel.yearWork,hel.periodWork "
					//+ " from RwHealth hel, PnOrganization org, VPrEmployeeSecurityReport vemp, PnEmployee emp, VPnOrganizationSecurity v "
					+ " from RwHealth hel, PnOrganization org,  PnEmployee emp, VPnOrganizationSecurity v "
					+ " where hel.ouCode = org.pk.ouCode "
					+ " and hel.yearPr = "
					+ year
					+ " and hel.periodPr = "
					+ period
					+ " and hel.codeSeq = org.pk.codeSeq "
					+ " and hel.empCode = emp.pk.empCode "
					//+ " and hel.creBy = '" + userId + "'" 
					//" and vemp.pk.empCode = emp.pk.empCode "
					+ " and hel.ouCode = '"
					+ ouCode
					+ "'"
					//+ " and vemp.pk.ouCode = '"
					//+ ouCode
					//+ "'"
					//+ " and vemp.pk.userId = '"
					//+ userId
					//+ "'"
					//+ " and vemp.pk.period = hel.periodPr "
					//+ " and vemp.pk.empCode = hel.empCode "
					//+ " and vemp.pk.year = hel.yearPr "
					+ " and v.pk.userId = '"
					+ userId
					+ "'"
					//+ " and vemp.pk.userId = v.pk.userId "
					+ " and hel.ouCode = v.pk.ouCode "
					+ " and hel.codeSeq = v.pk.codeSeq ";
			if (!"".equals(type)) {
				sql += " and hel.flagPr = '" + type + "'";
			}
			sql += " order by nvl(org.secCode, org.divCode),org.orgCode,emp.adminCode,emp.levelCode desc, emp.pk.empCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			RwHealthVO hel = null;
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				hel = new RwHealthVO();
				hel.setOuCode(obj[0] != null ? obj[0].toString() : null);
				hel.setYearPr(obj[1] != null ? new Double(obj[1].toString())
						: null);
				hel.setPeriodPr(obj[2] != null ? new Double(obj[2].toString())
						: null);
				hel.setAreaCode(obj[3] != null ? obj[3].toString() : null);
				hel.setSecCode(obj[4] != null ? obj[4].toString() : null);
				hel.setAreaDesc(obj[5] != null ? obj[5].toString() : null);
				hel.setSecDesc(obj[6] != null ? obj[6].toString() : null);
				hel.setTotalHour(obj[7] != null ? new Double(obj[7].toString())
						: null);
				hel.setFlagPr(obj[8] != null ? obj[8].toString() : null);
				hel.setEmpCode(obj[9] != null ? obj[9].toString() : null);
				hel.setPreFix(obj[10] != null ? obj[10].toString() : null);
				hel.setFirstName(obj[11] != null ? obj[11].toString() : null);
				hel.setLastName(obj[12] != null ? obj[12].toString() : null);
				hel.setOrgCode(obj[13] != null ? obj[13].toString() : null);
				hel.setOrgDesc(obj[14] != null ? obj[14].toString() : null);
				hel.setYearWork(obj[15] != null ? new Double(obj[15].toString())
						: null);
				hel.setPeriodWork(obj[16] != null ? new Double(obj[16]
						.toString()) : null);
				rlst.add(hel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwHealth p, VPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'Y' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		sql.append(" and p.yearPr = e.pk.year ");
		sql.append(" and p.periodPr = e.pk.period ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			// System.out.println("i : " + i.intValue() + " j : " + j.intValue()
			// );
			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return true;
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwHealth p, VPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		sql.append(" and p.yearPr = e.pk.year ");
		sql.append(" and p.periodPr = e.pk.period ");

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
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo)

	{
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwInc.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwInc.periodPr = ");
			criteria.append(evaPeriod);
		}

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
		hql.append(" rwInc.flagPr, ");
		hql.append(" rwInc.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" rwInc.yearWork, ");
		hql.append(" rwInc.periodWork, ");
		hql.append(" rwInc.totalHour, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq ");
		hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from RwHealth rwInc , PnEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.pk.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc, rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String flagPr = (String) r[1];
			String empCode = (String) r[2];
			String prefixName = (String) r[3];
			String firstName = (String) r[4];
			String lastName = (String) r[5];
			Double yearWork = (Double) r[6];
			Double periodWork = (Double) r[7];
			Double totalHour = (Double) r[8];
			Double seqData = (Double) r[9];
			Long codeSeq = (Long) r[10];

			RwHealthEmployeeVO ret = new RwHealthEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalHour(totalHour);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);

			retList.add(ret);
		}

		return retList;
	}
}