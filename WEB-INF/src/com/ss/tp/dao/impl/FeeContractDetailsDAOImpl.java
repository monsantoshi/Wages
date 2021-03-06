package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;



import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.object.StoredProcedure;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.FeeContractDetailsDAO;

import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeContractDetailsVO;
import com.ss.tp.dto.RwIncDecOtherVO;
//import com.ss.tp.dto.RwPremiumEmployeeVO;
//import com.ss.tp.dto.RwPremiumReportByOrgVO;
import com.ss.tp.dto.RwPremiumEmployeeVO;
import com.ss.tp.dto.RwPremiumReportByOrgVO;
import com.ss.tp.model.FeeWpayRwIncDecOther;
import com.ss.tp.model.FeeContractDetails;

public class FeeContractDetailsDAOImpl extends HibernateDaoSupport implements
		FeeContractDetailsDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FeeContractDetailsDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		FeeContractDetails rw = new FeeContractDetails();
		try {
			BeanUtils.copyProperties(rw, feeContractDetailsvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public FeeContractDetails loadFeeContractDetails(FeeContractDetailsVO rpVo) {
		FeeContractDetails rwp = new FeeContractDetails();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (FeeContractDetails) this.getHibernateTemplate().load(FeeContractDetails.class,
					rpVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		FeeContractDetails rw = new FeeContractDetails();
		try {
			BeanUtils.copyProperties(rw, feeContractDetailsvo);
			rw = this.loadFeeContractDetails(feeContractDetailsvo);
			// BeanUtils.copyProperties(rw ,rwpremiumvo);
			//rw.setContractNo(feeContractDetailsvo.getContractNo());
			//rw.setCodeSeq(feeContractDetailsvo.getCodeSeq());
			//rw.setEmpCode(feeContractDetailsvo.getEmpCode());
			//rw.setYearCon(feeContractDetailsvo.getYearCon());
			rw.setInactiveD(feeContractDetailsvo.getInactive());
		    rw.setWarfee(feeContractDetailsvo.getWarfee());
		    rw.setOtRate(feeContractDetailsvo.getOtRate());
		    rw.setSalary(feeContractDetailsvo.getSalary());
		    rw.setExtraIncome(feeContractDetailsvo.getExtraIncome());
		    rw.setStartDate(feeContractDetailsvo.getStartDate());
		    rw.setEndDate(feeContractDetailsvo.getEndDate());
		    rw.setNoted(feeContractDetailsvo.getNote());
			rw.setUpdBy(feeContractDetailsvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		FeeContractDetails rw = new FeeContractDetails();
		try {
			BeanUtils.copyProperties(rw, feeContractDetailsvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(FeeContractDetailsVO feeContractDetailsvo) {
		this.rwList.add(feeContractDetailsvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateFeeContractDetails() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				FeeContractDetailsVO feeContractDetailsvo = (FeeContractDetailsVO) this.rwList.get(i);
				if ((feeContractDetailsvo.getKeySeq() != new Long(0))
						&& feeContractDetailsvo.getKeySeq() != null
						&& !feeContractDetailsvo.getKeySeq().equals("")) {
					this.updateFeeContractDetails(feeContractDetailsvo);
				} else {
					this.insertFeeContractDetails(feeContractDetailsvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertFeeContractDetailss(List feeContractDetailsvolist) throws Exception {
		FeeContractDetails rw = new FeeContractDetails();
		try {
			for (int i = 0; i < feeContractDetailsvolist.size(); i++) {
				FeeContractDetailsVO feeContractDetailsvo = (FeeContractDetailsVO) feeContractDetailsvolist.get(i);

				BeanUtils.copyProperties(rw, feeContractDetailsvo);
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

		hql.append(" select distinct rwPre.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, rwPre.codeSeq ");
		hql.append(" from FeeContractDetails rwPre , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePK.empCode ");
		//hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		//hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		//hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");
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
			//String positionShort = (String) r[4];

			FeeWpayPayRollEmployeeVO ret = new FeeWpayPayRollEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setPositionShort(positionShort);

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
		hql.append(" from FeeContractDetails rwPre , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		//hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from FeeContractDetails rwPre, ");
		hql.append(" WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
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
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		FeeWpayPayRollEmployeeVO ret = new FeeWpayPayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			//String positionShort = (String) r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			Double codeSeqWork = (Double) r[6];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from FeeContractDetails rw ");
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
		hql.append(" 	from VFeeWpPrEmployeeSecutiry ");
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
				FeeContractDetailsVO rw = new FeeContractDetailsVO();

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

	public List feeContractDetailsReportByOrg(String userId, String evaOuCode,
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
		hql.append(" from FeeContractDetails rwPre , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.pk.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmployeeSecutiryReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,rwPre.empCode ");

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

			RwPremiumReportByOrgVO ret = new RwPremiumReportByOrgVO();
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
		sql.append(" from FeeContractDetails p, VFeeWpPrEmployeeSecutiry e ");
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
		sql.append(" from FeeContractDetails p, VFeeWpPrEmployeeSecutiry e ");
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

	public void generate(String userId, String ouCode, Long year, Long period) {

		Long periodBefore = new Long(period.longValue() - 2);
		Long yearBefore = year;
		if (periodBefore.toString().equals("0")) {
			periodBefore = new Long(24);
			yearBefore = new Long(Integer.parseInt(year.toString()) - 1);
		}

		StringBuffer sql = new StringBuffer(0);
		sql.append(" delete Fee_Wpay_Rw_Premium  rwPre");
		sql.append(" where rwPre.ou_Code = '" + ouCode + "' ");
		sql.append(" and rwPre.year_Pr = " + year);
		sql.append(" and rwPre.period_Pr = " + period);
		sql.append(" and rwPre.code_Seq in ( ");
		sql.append(" 	select code_Seq ");
		sql.append(" 	from Su_User_Organization ");
		sql.append(" 	where ou_Code = '" + ouCode + "' ");
		sql.append(" 	and user_Id = '" + userId + "' ");
		sql.append(" ) ");
		sql.append("and emp_code in (select a. emp_code from  Fee_Wpay_Rw_Premium a, su_user_organization s ");
		sql.append("where a.ou_code = '" + ouCode + "' ");
		sql.append("and a.year_pr = " + yearBefore + " ");
		sql.append("and a.period_pr = " + periodBefore + " ");
		sql.append("and s.user_id = '" + userId + "' ");
		sql.append("and a.code_seq = s.code_seq) ");

		System.out.println("****************** ::: " + sql.toString());
		this.jdbcTemplate.execute(sql.toString());

		StringBuffer hql = new StringBuffer(0);
		hql.append("insert into fee_wpay_rw_premium(ou_code, year_pr, period_pr, emp_code, year_work, period_work");
		hql.append(", code_seq,mor_day,aft_day,evn_day,mor_hour,aft_hour,evn_hour, flag_pr, seq_data, confirm_flag ");
		hql.append(", cre_by, cre_date, upd_by, upd_date) ");
		hql.append("select ou_code, " + year + ", " + period
				+ ", emp_code,  decode(period_work,12,year_work+1,year_work) ");
		hql.append(", decode(period_work,12,1,period_work +1) ");
		hql.append(", code_seq,mor_day,aft_day,evn_day,mor_hour,aft_hour,evn_hour, flag_pr, seq_data, 'N' ");
		hql.append(", '" + userId + "', sysdate, '" + userId + "', sysdate ");
		hql.append("from fee_wpay_rw_premium ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and year_pr = " + yearBefore + " ");
		hql.append("and period_pr = " + periodBefore + " ");
		hql.append("and code_seq in (select code_seq  ");
		hql.append("from su_user_organization  ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + hql.toString());
		this.jdbcTemplate.execute(hql.toString());
	}

	public List findByCriteriaList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq, int count, int countRecord)

	{
		StringBuffer criteria = new StringBuffer();

		if (evaCodeSeq != null && !evaCodeSeq.equals("")) {
			criteria.append(" and pnOrg.orgCode = ");
			criteria.append(evaCodeSeq);
		}


		if (evaContractNo != null && !evaContractNo.equals("")) {
			criteria.append(" and rwInc.contractNo = '");
			criteria.append(evaContractNo);
			criteria.append("' ");
		}


		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwInc.yearCon = '");
			criteria.append(evaYear);
			criteria.append("' ");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwInc.keySeq, ");
		hql.append(" rwInc.inactive, ");
		hql.append(" rwInc.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" rwInc.yearCon, ");
		//hql.append(" rwInc.inactive, ");
		hql.append(" rwInc.startDate, ");
		hql.append(" rwInc.endDate, ");
		hql.append(" rwInc.salary, ");
		hql.append(" rwInc.extraIncome, ");
		hql.append(" rwInc.warfee, ");
		hql.append(" rwInc.otRate, ");
		hql.append(" rwInc.note, ");
		hql.append(" rwInc.codeSeq ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeContractDetails rwInc , FeeWgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String inactive = (String) r[1];
			String empCode = (String) r[2];
			String prefixName = (String) r[3];
			String firstName = (String) r[4];
			String lastName = (String) r[5];
			String yearCon = (String) r[6];
			Date startDate = (Date) r[7];
			Date endDate = (Date) r[8];
			Double salary = (Double) r[9];
			Double extraIncome = (Double) r[10];
			Double warfee = (Double) r[11];
			Double otRate = (Double) r[12];
			String note = (String) r[13];
			//Double seqData = (Double) r[14];
			Double codeSeq = (Double) r[14];

			FeeContractDetailsVO ret = new FeeContractDetailsVO();
			ret.setKeySeq(keySeq);
			ret.setInactive(inactive);
			ret.setInactiveD(inactive);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setYearCon(yearCon);
			ret.setStartDate(startDate);
			ret.setEndDate(endDate);
			ret.setSalary(salary);
			ret.setExtraIncome(extraIncome);
			ret.setWarfee(warfee);
			ret.setOtRate(otRate);
			ret.setNote(note);
			ret.setCodeSeq(codeSeq);
			ret.setCodeSeqD(codeSeq);
			

			retList.add(ret);
		}

		return retList;
	}

	public Integer countDataList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq) 
	
	{
		StringBuffer criteria = new StringBuffer();

		if (evaCodeSeq != null && !evaCodeSeq.equals("")) {
			criteria.append(" and pnOrg.orgCode = ");
			criteria.append(evaCodeSeq);
		}


		if (evaContractNo != null && !evaContractNo.equals("")) {
			criteria.append(" and rwInc.contractNo = '");
			criteria.append(evaContractNo);
			criteria.append("' ");
		}


		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwInc.yearCon = '");
			criteria.append(evaYear);
			criteria.append("' ");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(rwInc.keySeq) ");
		hql.append(" from FeeContractDetails rwInc , FeeWgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");


		System.out.println("HQL findByCriteria ==> " + hql.toString());
		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			String evaFlagFrom, String evaFlagTo) {
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

		hql.append(" select count(rwInc.keySeq) ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeContractDetails rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());
		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	//@Override
	public void addListApprove(FeeContractDetailsVO rwpremiumvo) {
		this.rwList.add(rwpremiumvo);
		
	}

	//@Override
	public void insertAndUpdateApTablesApprove() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				FeeContractDetailsVO feeContractDetailsvo = (FeeContractDetailsVO) this.rwList.get(i);
				if ((feeContractDetailsvo.getKeySeq() != new Long(0))
						&& feeContractDetailsvo.getKeySeq() != null
						&& !feeContractDetailsvo.getKeySeq().equals("")) {
					this.updateApTableApprove(feeContractDetailsvo);
				} else {
					this.insertFeeContractDetails(feeContractDetailsvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			String evaFlagFrom, String evaFlagTo, int count, int countRecord) {
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
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.secDesc,pnOrg.areaDesc), ");
		hql.append(" rwInc.yearWork, ");
		hql.append(" rwInc.periodWork, ");
		hql.append(" rwInc.morDay, ");
		hql.append(" rwInc.aftDay, ");
		hql.append(" rwInc.evnDay, ");
		hql.append(" rwInc.morHour, ");
		hql.append(" rwInc.aftHour, ");
		hql.append(" rwInc.evnHour, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq, ");
		hql.append(" rwInc.approveFlag ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeContractDetails rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		
		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();


		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String flagPr = (String) r[1];
			String empCode = (String) r[2];
			String prefixName = (String) r[3];
			String firstName = (String) r[4];
			String lastName = (String) r[5];
			String orgDesc = (String) r[6];
			Double yearWork = (Double) r[7];
			Double periodWork = (Double) r[8];
			Double morDay = (Double) r[9];
			Double aftDay = (Double) r[10];
			Double evnDay = (Double) r[11];
			Double morHour = (Double) r[12];
			Double aftHour = (Double) r[13];
			Double evnHour = (Double) r[14];
			Double seqData = (Double) r[15];
			Long codeSeq = (Long) r[16];
			String approveFlag = (String) r[17];

			RwPremiumEmployeeVO ret = new RwPremiumEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOrgDesc(orgDesc);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setMorDay(morDay);
			ret.setAftDay(aftDay);
			ret.setEvnDay(evnDay);
			ret.setMorHour(morHour);
			ret.setAftHour(aftHour);
			ret.setEvnHour(evnHour);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);
			ret.setApproveFlag(approveFlag);

			retList.add(ret);
		}

		return retList;
	}


	//@Override
	public void updateApTableApprove(FeeContractDetailsVO feeContractDetailsvo)
			throws Exception {
		FeeContractDetails rw = new FeeContractDetails();
		try {
			rw = this.loadFeeContractDetails(feeContractDetailsvo);
			//rw.setApproveFlag(feeContractDetailsvo.getApproveFlag());
			//rw.setApproveBy(feeContractDetailsvo.getApproveBy());
			//rw.setApproveDate(new Date());
		//
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}