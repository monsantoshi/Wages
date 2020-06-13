package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.*;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.RwVinaiDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinaiReportVO;

import com.ss.tp.dto.RwVinaiVO;
import com.ss.tp.model.RwVinai;

public class RwVinaiDAOImpl extends HibernateDaoSupport implements RwVinaiDAO,
		Serializable {
	List rwList = new ArrayList();

	public RwVinaiDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		RwVinai rw = new RwVinai();
		try {
			BeanUtils.copyProperties(rw, rwvinaivo);
			if (rwvinaivo.getAbsDay1() == null)
				rw.setAbsDay1(null);
			if (rwvinaivo.getAbsMonth1() == null)
				rw.setAbsMonth1(null);
			if (rwvinaivo.getAbsYear1() == null)
				rw.setAbsYear1(null);
			if (rwvinaivo.getAbsDay2() == null)
				rw.setAbsDay2(null);
			if (rwvinaivo.getAbsMonth2() == null)
				rw.setAbsMonth2(null);
			if (rwvinaivo.getAbsYear2() == null)
				rw.setAbsYear2(null);
			if (rwvinaivo.getCutSalYear() == null)
				rw.setCutSalYear(null);
			if (rwvinaivo.getCutSalMonth() == null)
				rw.setCutSalMonth(null);
			if (rwvinaivo.getCutSalPercent() == null)
				rw.setCutSalPercent(null);
			if (rwvinaivo.getDecSal() == null)
				rw.setDecSal(null);
			if (rwvinaivo.getDecSalMonth() == null)
				rw.setDecSalMonth(null);
			if (rwvinaivo.getNewSalary() == null)
				rw.setNewSalary(null);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwVinai loadRwVinai(RwVinaiVO rwVo) {
		RwVinai rwp = new RwVinai();
		try {
			rwp = (RwVinai) this.getHibernateTemplate().load(RwVinai.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		RwVinai rw = new RwVinai();
		try {
			BeanUtils.copyProperties(rw, rwvinaivo);
			rw = this.loadRwVinai(rwvinaivo);
			rw.setFlagPr(rwvinaivo.getFlagPr());
			rw.setYearWork(rwvinaivo.getYearWork());
			rw.setPeriodWork(rwvinaivo.getPeriodWork());
			rw.setDecSal(rwvinaivo.getDecSal());
			rw.setNewSalary(rwvinaivo.getNewSalary());
			rw.setDecSalMonth(rwvinaivo.getDecSalMonth());
			rw.setCutSalYear(rwvinaivo.getCutSalYear());
			rw.setCutSalMonth(rwvinaivo.getCutSalMonth());
			rw.setCutSalPercent(rwvinaivo.getCutSalPercent());
			rw.setAbsYear1(rwvinaivo.getAbsYear1());
			rw.setAbsMonth1(rwvinaivo.getAbsMonth1());
			rw.setAbsDay1(rwvinaivo.getAbsDay1());
			rw.setAbsYear2(rwvinaivo.getAbsYear2());
			rw.setAbsMonth2(rwvinaivo.getAbsMonth2());
			rw.setAbsDay2(rwvinaivo.getAbsDay2());
			rw.setStartDateQut(rwvinaivo.getStartDateQut());
			rw.setEndDateQut(rwvinaivo.getEndDateQut());
			rw.setUpdBy(rwvinaivo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		RwVinai rw = new RwVinai();
		try {
			BeanUtils.copyProperties(rw, rwvinaivo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwVinaiVO rwvinaivo) {
		this.rwList.add(rwvinaivo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwVinais() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwVinaiVO rwvinaivo = (RwVinaiVO) this.rwList.get(i);
				if ((rwvinaivo.getKeySeq() != new Long(0))
						&& rwvinaivo.getKeySeq() != null
						&& !rwvinaivo.getKeySeq().equals("")) {
					this.updateRwVinai(rwvinaivo);
				} else {
					this.insertRwVinai(rwvinaivo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwVinais(List rwvinaivolist) throws Exception {
		RwVinai rw = new RwVinai();
		try {
			for (int i = 0; i < rwvinaivolist.size(); i++) {
				RwVinaiVO rwvinaivo = (RwVinaiVO) rwvinaivolist.get(i);

				BeanUtils.copyProperties(rw, rwvinaivo);
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

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rw.codeSeq,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from RwVinai rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

		hql.append(" select count(distinct rw.empCode) ");
		hql.append(" from RwVinai rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" from RwVinai rw, ");
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

		hql.append(" from RwVinai rw ");
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
				RwVinaiVO rw = new RwVinaiVO();
				RwVinai rwm = new RwVinai();
				try {
					BeanUtils.copyProperties(rw, empList.get(i));
					rwm = (RwVinai) empList.get(i);

					rw.setDecSalMonth(rwm.getDecSalMonth());
					rw.setCutSalYear(rwm.getCutSalYear());
					rw.setCutSalMonth(rwm.getCutSalMonth());
					rw.setCutSalPercent(rwm.getCutSalPercent());
					rw.setAbsYear1(rwm.getAbsYear1());
					rw.setAbsMonth1(rwm.getAbsMonth1());
					rw.setAbsDay1(rwm.getAbsDay1());

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
		sql.append(" from RwVinai p, VPrEmployeeSecurity e ");
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
		sql.append(" from RwVinai p, VPrEmployeeSecurity e ");
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

	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwV.flagPr = '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwV.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,");
		hql.append(" (prEmp.newSalary - rwV.newSalary),rwV.cutSalMonth||'/'||rwV.cutSalYear,rwV.cutSalPercent,rwV.absMonth1||'/'||rwV.absYear1,rwV.absDay1, ");
		hql.append(" rwV.absMonth2||'/'||rwV.absYear2,rwV.absDay2,rwV.startDateQut,rwV.endDateQut, ");
		hql.append(" round(rwV.endDateQut - rwV.startDateQut),rwV2.periodWork||'/'||rwV2.yearWork,rwV2.decDay,rwV.decSal,rwV.decSalMonth,rwV.cutSalMonth ");
		hql.append(" from RwVinai rwV , PnEmployee pnEmp , PnOrganization pnOrg, RwVinai2 rwV2 ,PrEmployee prEmp, VPnOrganizationSecurity v ");
		hql.append(" where rwV.ouCode = '");
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
		hql.append(" and rwV.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwV.empCode = pnEmp.pk.empCode ");
		hql.append(" and rwV.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwV.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwV.ouCode = prEmp.key.ouCode ");
		hql.append(" and rwV.yearPr = prEmp.key.year ");
		hql.append(" and rwV.periodPr = prEmp.key.period ");
		hql.append(" and rwV.empCode = prEmp.key.empCode ");
		hql.append(" and rwV.ouCode = rwV2.ouCode ");
		hql.append(" and rwV.yearPr = rwV2.yearPr ");
		hql.append(" and rwV.periodPr = rwV2.periodPr");
		hql.append(" and rwV.empCode = rwV2.empCode ");
		hql.append(" and rwV.flagPr = rwV2.flagPr ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwV.ouCode = v.pk.ouCode ");
		hql.append(" and rwV.codeSeq = v.pk.codeSeq ");
		hql.append(" order by nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc, rwV.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			Double newSalary = (Double) r[3];
			String cutSal = (String) r[4];
			Double cutSalPercent = (Double) r[5];
			String absYear1 = (String) r[6];
			Double absDay1 = (Double) r[7];
			String absYear2 = (String) r[8];
			Double absDay2 = (Double) r[9];
			Date startDateQut = (Date) r[10];
			Date endDateQut = (Date) r[11];
			Double totalDay = (Double) r[12];
			String periodWork = (String) r[13];
			Double decDay = (Double) r[14];
			Double decSal = (Double) r[15];
			Double decSalMonth = (Double) r[16];
			Double cutSalMonth = (Double) r[17];

			RwVinaiReportVO ret = new RwVinaiReportVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setDecSal(newSalary);
			ret.setCutSal(cutSal);
			ret.setCutSalPercent(cutSalPercent);
			ret.setAbsYear1(absYear1);
			ret.setAbsYear2(absYear2);
			ret.setAbsDay1(absDay1);
			ret.setAbsDay2(absDay2);
			ret.setStartDateQut(startDateQut);
			ret.setEndDateQut(endDateQut);
			ret.setTotalDay(totalDay);
			ret.setPeriodWork(periodWork);
			ret.setDecDay(decDay);
			ret.setDecSal(decSal);
			ret.setDecSalMonth(decSalMonth);
			ret.setCutSalMonth(cutSalMonth);

			retList.add(ret);
		}
		return retList;
	}
}