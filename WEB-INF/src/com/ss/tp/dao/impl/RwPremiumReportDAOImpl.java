package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.RwPremiumReportDAO;

import com.ss.tp.dto.PayRollEmployeeReportVO;
import com.ss.tp.dto.RwPremiumReportVO;
import com.ss.tp.dto.RwPremiumReportByOrgReportVO;
import com.ss.tp.model.RwPremiumReport;

public class RwPremiumReportDAOImpl extends HibernateDaoSupport implements
		RwPremiumReportDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RwPremiumReportDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		RwPremiumReport rw = new RwPremiumReport();
		try {
			BeanUtils.copyProperties(rw, rwpremiumreportvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwPremiumReport loadRwPremium(RwPremiumReportVO rpVo) {
		RwPremiumReport rwp = new RwPremiumReport();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (RwPremiumReport) this.getHibernateTemplate().load(
					RwPremiumReport.class, rpVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		RwPremiumReport rw = new RwPremiumReport();
		try {
			rw = this.loadRwPremium(rwpremiumreportvo);
			// BeanUtils.copyProperties(rw ,rwpremiumvo);
			rw.setYearWork(rwpremiumreportvo.getYearWork());
			rw.setFlagPr(rwpremiumreportvo.getFlagPr());
			rw.setPeriodWork(rwpremiumreportvo.getPeriodWork());
			rw.setMorDay(rwpremiumreportvo.getMorDay());
			rw.setMorHour(rwpremiumreportvo.getMorHour());
			rw.setAftDay(rwpremiumreportvo.getAftDay());
			rw.setAftHour(rwpremiumreportvo.getAftHour());
			rw.setEvnDay(rwpremiumreportvo.getEvnDay());
			rw.setEvnHour(rwpremiumreportvo.getEvnHour());
			rw.setSeqData(rwpremiumreportvo.getSeqData());
			rw.setUpdBy(rwpremiumreportvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		RwPremiumReport rw = new RwPremiumReport();
		try {
			BeanUtils.copyProperties(rw, rwpremiumreportvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwPremiumReportVO rwpremiumreportvo) {
		this.rwList.add(rwpremiumreportvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwPremiums() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwPremiumReportVO rwpremiumreportvo = (RwPremiumReportVO) this.rwList
						.get(i);
				if ((rwpremiumreportvo.getKeySeq() != new Long(0))
						&& rwpremiumreportvo.getKeySeq() != null
						&& !rwpremiumreportvo.getKeySeq().equals("")) {
					this.updateRwPremium(rwpremiumreportvo);
				} else {
					this.insertRwPremium(rwpremiumreportvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwPremiums(List rwpremiumvolist) throws Exception {
		RwPremiumReport rw = new RwPremiumReport();
		try {
			for (int i = 0; i < rwpremiumvolist.size(); i++) {
				RwPremiumReportVO rwpremiumreportvo = (RwPremiumReportVO) rwpremiumvolist
						.get(i);

				BeanUtils.copyProperties(rw, rwpremiumreportvo);
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
			criteria.append(" and rwPre.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodPr = ");
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
			criteria.append(" and rwPre.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwPre.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rwPre.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rwPre.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rwPre.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rwPre.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc ");
		hql.append(" from RwPremium rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwPre.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc, rwPre.empCode ");

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

			PayRollEmployeeReportVO ret = new PayRollEmployeeReportVO();
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
			criteria.append(" and rwPre.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodPr = ");
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
			criteria.append(" and rwPre.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwPre.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rwPre.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rwPre.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rwPre.empCode) ");
		hql.append(" from RwPremium rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public PayRollEmployeeReportVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from RwPremium rwPre, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PrEmployee prEmp, ");
		hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rwPre.empCode = '");
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
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		PayRollEmployeeReportVO ret = new PayRollEmployeeReportVO();

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

		hql.append(" from RwPremium rw ");
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
				RwPremiumReportVO rw = new RwPremiumReportVO();

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

	public List rwPremiumReportByOrgReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwPre.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
		hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from RwPremium rwPre , PnEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.pk.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPrEmployeeSecurityReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc,rwPre.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			Double morDay = (Double) r[3];
			Double aftDay = (Double) r[4];
			Double evnDay = (Double) r[5];
			Double morHour = (Double) r[6];
			Double aftHour = (Double) r[7];
			Double evnHour = (Double) r[8];
			String flagPr = (String) r[9];
			Double year = (Double) r[10];
			Double period = (Double) r[11];

			RwPremiumReportByOrgReportVO ret = new RwPremiumReportByOrgReportVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setMorDay(morDay);
			ret.setAftDay(aftDay);
			ret.setEvnDay(evnDay);
			ret.setMorHour(morHour);
			ret.setAftHour(aftHour);
			ret.setEvnHour(evnHour);
			ret.setFlagPr(flagPr);
			ret.setYear(year);
			ret.setPeriod(period);

			retList.add(ret);
		}
		return retList;
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwPremium p, VPrEmployeeSecurityReport e ");
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
		sql.append(" from RwPremium p, VPrEmployeeSecurityReport e ");
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

}