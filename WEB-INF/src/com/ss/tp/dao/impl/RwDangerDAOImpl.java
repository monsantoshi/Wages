package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.RwDangerDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwDangerVO;
import com.ss.tp.model.RwDanger;
import com.ss.tp.model.SuOrganize;

public class RwDangerDAOImpl extends HibernateDaoSupport implements
		RwDangerDAO, Serializable {
	List rwList = new ArrayList();

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	public RwDangerDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwDanger(RwDangerVO rwdangervo) throws Exception {
		RwDanger rw = new RwDanger();
		try {
			BeanUtils.copyProperties(rw, rwdangervo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwDanger loadRwDanger(RwDangerVO rwVo) {
		RwDanger rwp = new RwDanger();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (RwDanger) this.getHibernateTemplate().load(RwDanger.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwDanger(RwDangerVO rwdangervo) throws Exception {
		RwDanger rw = new RwDanger();
		try {
			BeanUtils.copyProperties(rw, rwdangervo);
			rw = this.loadRwDanger(rwdangervo);
			rw.setFlagPr(rwdangervo.getFlagPr());
			rw.setYearWork(rwdangervo.getYearWork());
			rw.setPeriodWork(rwdangervo.getPeriodWork());
			rw.setFullDay(rwdangervo.getFullDay());
			rw.setHalfDay(rwdangervo.getHalfDay());
			rw.setSeqData(rwdangervo.getSeqData());
			rw.setUpdBy(rwdangervo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwDanger(RwDangerVO rwdangervo) throws Exception {
		RwDanger rw = new RwDanger();
		try {
			BeanUtils.copyProperties(rw, rwdangervo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwDangerVO rwdangervo) {
		this.rwList.add(rwdangervo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwDangers() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwDangerVO rwdangervo = (RwDangerVO) this.rwList.get(i);
				if ((rwdangervo.getKeySeq() != new Long(0))
						&& rwdangervo.getKeySeq() != null
						&& !rwdangervo.getKeySeq().equals("")) {
					this.updateRwDanger(rwdangervo);
				} else {
					this.insertRwDanger(rwdangervo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwDangers(List rwdangervolist) throws Exception {
		RwDanger rw = new RwDanger();
		try {
			for (int i = 0; i < rwdangervolist.size(); i++) {
				System.out.println("Class : "
						+ rwdangervolist.get(i).getClass().getName());
				// String rwdanger = (String) rwdangervolist.get(i);
				// System.out.println("VALUE : "+rwdanger);
				RwDangerVO rwdangervo = (RwDangerVO) rwdangervolist.get(i);
				if (rwdangervo.getKeySeq() != null
						&& !rwdangervo.getKeySeq().equals("")) {
					this.updateRwDanger(rwdangervo);

				} else {
					BeanUtils.copyProperties(rw, rwdangervo);
					rw.setCreDate(new Date());
					rw.setUpdDate(new Date());
					this.getHibernateTemplate().save(rw);
				}

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
		 * pnPos.positionShort, rw.codeSeq " ); hql.append( " from RwDanger rw ,
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

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rw.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from RwDanger rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" order by rw.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc, rw.empCode ");

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
		 * RwDanger rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization
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
		hql.append(" from RwDanger rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" from RwDanger rw, ");
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

		hql.append(" from RwDanger rw ");
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
				RwDangerVO rw = new RwDangerVO();

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

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwDanger p, VPrEmployeeSecurity e ");
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
		sql.append(" from RwDanger p, VPrEmployeeSecurity e ");
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

	// ================Create By Kiet==================

	public String findOrgName(String ouCode) {
		// --SuOrganize
		StringBuffer sql = new StringBuffer(0);
		List list = new ArrayList();
		List listDetail = new ArrayList();
		String orgName = "";
		SuOrganize suOrg;

		sql.append(" SELECT org.ouName ");
		sql.append(" FROM SuOrganize org ");
		sql.append(" WHERE org.ouCode = '" + ouCode + "'");
		try {
			Session s = this.getSession();
			Query q = s.createQuery(sql.toString());
			listDetail = q.list();
			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				orgName = String.valueOf(itt.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgName;
	}

	public List findReportByCriteria(String ouCode, String userId, Long yearPr,
			Long periodPr, String flagPr) {
		RwDanger rwDanger;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		String flagDesc = "";
		String deptDesc = "";
		StringBuffer sql = new StringBuffer(0);

		// --RwDanger
		sql.append(" SELECT ");
		sql.append(" 	d.ouCode, d.yearPr, d.periodPr, d.empCode, d.fullDay, d.halfDay, d.flagPr, "
				+ "	d.refPnOrganization.areaCode, d.refPnOrganization.areaDesc, "
				+ "	d.refPnOrganization.divCode, d.refPnOrganization.secCode,  "
				+ "	d.refPnOrganization.divDesc, d.refPnOrganization.secDesc,  "
				+ " 	d.refPnEmployee.refDbPreSuff.prefixName || ' ' || d.refPnEmployee.firstName || ' ' || d.refPnEmployee.lastName,"
				+ "    d.yearWork,d.periodWork ");
		sql.append(" FROM ");
		sql.append(" 	RwDanger d,VPnOrganizationSecurity v");
		sql.append(" WHERE ");

		// ============================ Security
		// ===================================
		sql.append(" d.empCode in ( ");
		sql.append(" 	select pk.empCode ");
		sql.append(" 	from VPrEmployeeSecurityReport ");
		sql.append(" 	where pk.userId = '" + userId + "' ");
		sql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		sql.append(" 	and pk.year = " + yearPr);
		sql.append(" 	and pk.period = " + periodPr);
		sql.append(" ) ");
		// =========================================================================

		sql.append(" AND d.ouCode = '" + ouCode + "' ");
		sql.append(" and v.pk.userId = '" + userId + "' ");
		sql.append(" and d.ouCode = v.pk.ouCode ");
		sql.append(" and d.codeSeq = v.pk.codeSeq ");

		if (yearPr != null && yearPr.intValue() != 0) {
			sql.append(" AND d.yearPr = " + yearPr);
		}

		if (periodPr != null && periodPr.intValue() != 0) {
			sql.append(" AND d.periodPr = " + periodPr);
		}

		if (!flagPr.equals("")) {
			sql.append(" AND d.flagPr like '" + flagPr + "' ");
		}

		sql.append(" ORDER BY ");
		sql.append("	d.ouCode, d.refPnOrganization.areaCode, d.refPnOrganization.divCode, d.refPnOrganization.secCode,nvl(d.refPnEmployee.adminCode,chr(250)),d.refPnEmployee.levelCode desc, d.empCode ");

		try {
			Session ss = this.getSession();
			Query qq = ss.createQuery(sql.toString());
			listDetail = qq.list();
			Map obj = null;

			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				Object ob[] = (Object[]) itt.next();

				rwDanger = new RwDanger();

				rwDanger.setOuCode((String) ob[0]);
				rwDanger.setYearPr((Double) ob[1]);
				rwDanger.setPeriodPr((Double) ob[2]);
				rwDanger.setEmpCode((String) ob[3]);
				rwDanger.setFullDay((Double) ob[4]);
				rwDanger.setHalfDay((Double) ob[5]);

				if (ChgNullToEmpty((String) ob[6], "").equalsIgnoreCase("N")) {
					flagDesc = "";
				} else if (ChgNullToEmpty((String) ob[6], "").equalsIgnoreCase(
						"A")) {
					flagDesc = "»ÃÑº»ÃØ§ÃÒÂ¡ÒÃËÑ¡";
				} else if (ChgNullToEmpty((String) ob[6], "").equalsIgnoreCase(
						"R")) {
					flagDesc = "àÃÕÂ¡¤×¹";
				} else if (ChgNullToEmpty((String) ob[6], "").equalsIgnoreCase(
						"B")) {
					flagDesc = "ï¿½ï¿½ï¿½Ô¡ï¿½ï¿½Ñºï¿½ï¿½Ø§ï¿½ï¿½Â¡ï¿½ï¿½ï¿½Ñº";
				} else if (ChgNullToEmpty((String) ob[6], "").equalsIgnoreCase(
						"S")) {
					flagDesc = "ï¿½ï¿½ï¿½Ô¡ï¿½ï¿½Ñºï¿½ï¿½Ø§ï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ï¿½Â¡ï¿½×¹";
				} else {
					flagDesc = "";
				}

				rwDanger.setFlagDesc(flagDesc);
				// NVL(secCode, areaCode)
				deptDesc = ChgNullToEmpty((String) ob[10],
						ChgNullToEmpty((String) ob[7], ""));
				deptDesc = deptDesc + " " + ChgNullToEmpty((String) ob[8], "");
				deptDesc = deptDesc + " " + ChgNullToEmpty((String) ob[12], "");
				rwDanger.setDeptDesc(deptDesc);
				rwDanger.setEname(ChgNullToEmpty((String) ob[13], ""));
				rwDanger.setYearWork((Double) ob[14]);
				rwDanger.setPeriodWork((Double) ob[15]);
				list.add(rwDanger);
			}
			System.out.println("\n size List is ==" + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	// ================================================

}