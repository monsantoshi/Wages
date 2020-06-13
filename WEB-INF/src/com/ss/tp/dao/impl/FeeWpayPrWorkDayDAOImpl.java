package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;


import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ss.tp.dao.FeeWpayPrWorkDayDAO;

import com.ss.tp.dto.FeeWpayMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPrWorkDayVO;
import com.ss.tp.dto.PrWorkDayEmployeeVO;
import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.dto.TaReportVO;
import com.ss.tp.dto.TaWgMonthEmpWorkVO;

import com.ss.tp.model.FeeWpayMonthEmpWork;
import com.ss.tp.model.FeeWpayMonthEmpWorkPK;
import com.ss.tp.model.FeeWpayRwConfirmData;
import com.ss.tp.model.FeeWpayRwConfirmDataPK;
import com.ss.tp.model.PnOrganization;
import com.ss.tp.model.TaWgControl;
import com.ss.tp.model.TaWgControlPk;
import com.ss.tp.model.TaWgMonthEmpWork;
import com.ss.tp.model.TaWgMonthEmpWorkPK;
import com.ss.tp.model.TaWgStatusWork;
//import com.ss.tp.dto.TaWgMonthEmpWorkVO;
import com.ss.tp.model.FeeWpayPrWorkDay;
import com.ss.tp.model.FeeWpayStatusWork;


//import com.ss.tp.model.TaWgMonthEmpWork;
import com.ss.tp.model.WgEmployee;
import com.ss.tp.model.WgEmployeePK;

public class FeeWpayPrWorkDayDAOImpl extends HibernateDaoSupport implements
		FeeWpayPrWorkDayDAO, Serializable {
	List rwList = new ArrayList();
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public FeeWpayPrWorkDayDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertPrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception {
		FeeWpayPrWorkDay rw = new FeeWpayPrWorkDay();
		try {
			BeanUtils.copyProperties(rw, prworkdayvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public FeeWpayPrWorkDay loadPrWorkDay(FeeWpayPrWorkDayVO rwVo) {
		FeeWpayPrWorkDay rwp = new FeeWpayPrWorkDay();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (FeeWpayPrWorkDay) this.getHibernateTemplate().load(FeeWpayPrWorkDay.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updatePrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception {
		FeeWpayPrWorkDay rw = new FeeWpayPrWorkDay();
		try {
			BeanUtils.copyProperties(rw, prworkdayvo);
			rw = this.loadPrWorkDay(prworkdayvo);
			rw.setFlagPr(prworkdayvo.getFlagPr());
			rw.setEmpCode(prworkdayvo.getEmpCode());
			rw.setYearWork(prworkdayvo.getYearWork());
			rw.setPeriodWork(prworkdayvo.getPeriodWork());
			rw.setTotalHour(prworkdayvo.getTotalHour());
			rw.setSeqData(prworkdayvo.getSeqData());
			rw.setUpdBy(prworkdayvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception {
		FeeWpayPrWorkDay rw = new FeeWpayPrWorkDay();
		try {
			BeanUtils.copyProperties(rw, prworkdayvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(FeeWpayPrWorkDayVO prworkdayvo) {
		this.rwList.add(prworkdayvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdatePrWorkDays() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				FeeWpayPrWorkDayVO prworkdayvo = (FeeWpayPrWorkDayVO) this.rwList.get(i);
				if ((prworkdayvo.getKeySeq() != new Long(0))
						&& prworkdayvo.getKeySeq() != null
						&& !prworkdayvo.getKeySeq().equals("")) {
					this.updatePrWorkDay(prworkdayvo);
				} else {
					this.insertPrWorkDay(prworkdayvo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertPrWorkDays(List prworkdayvolist) throws Exception {
		FeeWpayPrWorkDay rw = new FeeWpayPrWorkDay();
		try {
			for (int i = 0; i < prworkdayvolist.size(); i++) {
				FeeWpayPrWorkDayVO prworkdayvo = (FeeWpayPrWorkDayVO) prworkdayvolist.get(i);

				BeanUtils.copyProperties(rw, prworkdayvo);
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

	
		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName,  rw.codeSeq,   ");
		hql.append(" from FeeWpayPrWorkDay rw , WgEmployee pnEmp ,  PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rw.codeSeq, rw.empCode ");

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
		hql.append(" from FeeWpayPrWorkDay rw , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v  ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		//hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from FeeWpayPrWorkDay rw, ");
		hql.append(" WgEmployee pnEmp, ");
		hql.append(" PrEmployee prEmp, ");
		//hql.append(" PnPosition pnPos, ");
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
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

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

		hql.append(" from FeeWpayPrWorkDay rw ");
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
		hql.append(" 	from VFeeWpPrEmployeeSecurity ");
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
				FeeWpayPrWorkDayVO rw = new FeeWpayPrWorkDayVO();

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


	public List findPrWorkDayReport(String ouCode, Integer year, Integer period,
			String type, String userId) {
		List rlst = new ArrayList();
		try {
			String sql = " select hel.ouCode, hel.yearPr, hel.periodPr, org.areaCode, org.secCode, org.areaDesc, org.secDesc, "
					+ " hel.totalHour, hel.flagPr, emp.pk.empCode, emp.refDbPreSuff.prefixName, emp.firstName, emp.lastName,org.orgCode,org.orgDesc, "
					+ " hel.yearWork,hel.periodWork "
					//+ " from FeeWpayPrWorkDay hel, PnOrganization org, VPrEmployeeSecurityReport vemp, PnEmployee emp, VPnOrganizationSecurity v "
					+ " from FeeWpayPrWorkDay hel, PnOrganization org,  WgEmployee emp, VPnOrganizationSecurity v "
					+ " where hel.ouCode = org.pk.ouCode "
					+ " and hel.yearPr = "
					+ year
					+ " and hel.periodPr = "
					+ period
					+ " and hel.codeSeq = org.pk.codeSeq "
					+ " and hel.empCode = emp.wgEmployeePK.empCode "
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
			sql += " order by nvl(org.secCode, org.divCode),org.orgCode, emp.wgEmployeePK.empCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			FeeWpayPrWorkDayVO hel = null;
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				hel = new FeeWpayPrWorkDayVO();
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
		sql.append(" from FeeWpayPrWorkDay p, VFeeWpPrEmployeeSecurity e ");
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
		sql.append(" from FeeWpayPrWorkDay p, VFeeWpPrEmployeeSecurity e ");
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
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayPrWorkDay rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");

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

			PrWorkDayEmployeeVO ret = new PrWorkDayEmployeeVO();
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
	
	public void generate(String userId, String ouCode, Long year, Long period) {

		Long periodBefore = new Long(period.longValue() - 2);
		Long yearBefore = year;
		if (periodBefore.toString().equals("0")) {
			periodBefore = new Long(24);
			yearBefore = new Long(Integer.parseInt(year.toString()) - 1);
		}

		StringBuffer sql = new StringBuffer(0);
		sql.append(" delete Fee_Wpay_Pr_Work_day inc");
		sql.append(" where inc.ou_Code = '" + ouCode + "' ");
		sql.append(" and inc.year_Pr = " + year);
		sql.append(" and inc.period_Pr = " + period);
		//sql.append(" and inc.group_Code = '1' ");
		//sql.append(" and inc.inc_Dec_Code = '01' ");
		sql.append(" and inc.code_Seq in ( ");
		sql.append(" 	select code_Seq ");
		sql.append(" 	from Su_User_Organization ");
		sql.append(" 	where ou_Code = '" + ouCode + "' ");
		sql.append(" 	and user_Id = '" + userId + "' ");
		sql.append(" ) ");
		sql.append("and emp_code in (select a. emp_code from  fee_wpay_pr_work_day a, su_user_organization s ");
		sql.append("where a.ou_code = '" + ouCode + "' ");
		sql.append("and a.year_pr = " + yearBefore + " ");
		sql.append("and a.period_pr = " + periodBefore + " ");
		//sql.append("and a.group_code = '1' ");
		//sql.append("and a.inc_dec_code = '08'");
		sql.append("and s.user_id = '" + userId + "' ");
		sql.append("and a.code_seq = s.code_seq) ");

		System.out.println("****************** ::: " + sql.toString());
		this.jdbcTemplate.execute(sql.toString());

		StringBuffer hql = new StringBuffer(0);
		hql.append("insert into fee_wpay_pr_work_day(ou_code, year_pr, period_pr, emp_code, year_work, period_work");
		hql.append(", code_seq, total_hour, flag_pr, seq_data, confirm_flag ");
		hql.append(", cre_by, cre_date, upd_by, upd_date) ");
		hql.append("select ou_code, "
				+ year
				+ ", "
				+ period
				+ ", emp_code, decode(period_work,12,year_work+1,year_work) ");
		hql.append(", decode(period_work,12,1,period_work +1) ");
		hql.append(", code_seq, total_hour, flag_pr, seq_data, 'N' ");
		hql.append(", '" + userId + "', sysdate, '" + userId + "', sysdate ");
		hql.append("from fee_wpay_pr_work_day ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and year_pr = " + yearBefore + " ");
		hql.append("and period_pr = " + periodBefore + " ");
		hql.append("and cre_by = " + userId + " ");
		//hql.append("and group_code = '1' ");
		//hql.append("and inc_dec_code = '08' ");
		hql.append("and emp_code in (select pr.emp_code  ");
		hql.append("from V_Fee_Wp_Pr_Employee_Security  pr  ");
		hql.append("where pr.ou_code = '" + ouCode + "' ");
		sql.append(" and pr.year = " + year);
		sql.append(" and pr.period  = " + period);
		hql.append(" and pr.user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + hql.toString());
		this.jdbcTemplate.execute(hql.toString());
		
		
		StringBuffer uql = new StringBuffer(0);
		uql.append("update fee_wpay_pr_work_day rw ");
		uql.append("set rw.code_seq = (select pr.code_seq_work from fee_wpay_pr_employee pr where pr.ou_code = '" + ouCode + "' ");
		uql.append(" and pr.year = " + year);
		uql.append(" and pr.period = " + period);
		uql.append(" and pr.emp_code = rw.emp_code ) ");
		uql.append(" where rw.ou_code = '" + ouCode + "' ");
		sql.append(" and rw.year_Pr = " + year);
		sql.append(" and rw.period_Pr = " + period);
		hql.append("and rw.cre_by = " + userId + " ");
		//uql.append(" and group_code = '1' ");
		//uql.append(" and inc_dec_code = '08' ");
		//uql.append(" and rw.code_seq in (select code_seq  ");
		//uql.append(" from su_user_organization  ");
		//uql.append(" where ou_code = '" + ouCode + "' ");
		//uql.append(" and user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + uql.toString());
		this.jdbcTemplate.execute(uql.toString());
		
		
	}

	
	
	
	
	
	
	
	public String findEmpName(String ouCode, String empCode) {
		WgEmployeePK pk = new WgEmployeePK();
		pk.setOuCode(ouCode);
		pk.setEmpCode(empCode);
		WgEmployee emp = (WgEmployee) this.getHibernateTemplate().load(
				WgEmployee.class, pk);
		String empName = "";
		try {
			empName = emp.getRefDbPreSuff().getPrefixName() + ""
					+ emp.getFirstName() + " " + emp.getLastName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empName;
	}

	public FeeWpayMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode) {
		FeeWpayMonthEmpWorkVO tamVo = new FeeWpayMonthEmpWorkVO();
		FeeWpayMonthEmpWork tam = null;
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			tmpLst = sss
					.createCriteria(FeeWpayMonthEmpWork.class)
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.workYear", workYear))
					.add(Restrictions.ge("feeWpayMonthEmpWorkPK.workMonth",
							workMonth))
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.empCode", empCode))
					.list();
			Iterator itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (FeeWpayMonthEmpWork) itt.next();
				BeanUtils.copyProperties(tamVo, tam.getWgEmployee());
				BeanUtils.copyProperties(tamVo, tam.getFeeWpayMonthEmpWorkPK());
				BeanUtils.copyProperties(tamVo, tam.getWgEmployee()
						.getRefDbPreSuff());
				BeanUtils.copyProperties(tamVo, tam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tamVo;
	}

	

	public List findWorkMonth(String ouCode) {
		FeeWpayMonthEmpWorkVO tamVo = null;
		FeeWpayStatusWork tas = null;
		List rlst = new ArrayList();
		try {
			String sql = " from FeeWpayStatusWork tas "
					+ " where tas.feeWpayStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " 
					+ " order by tas.feeWpayStatusWorkPk.workCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			int i = 1;
			for (Iterator itt = tmpList.iterator(); itt.hasNext(); i++) {
				tamVo = new FeeWpayMonthEmpWorkVO();
				tas = (FeeWpayStatusWork) itt.next();
				BeanUtils.copyProperties(tamVo, tas.getFeeWpayStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				tamVo.setWorkSeq(new Integer(i));
				rlst.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	
	public List findStatusWork(String ouCode,Integer workMonth) {
		FeeWpayMonthEmpWorkVO tamVo = null;
		FeeWpayStatusWork tas = null;
		List rlst = new ArrayList();
		try {
			String sql = " from FeeWpayStatusWork tas "
					+ " where tas.feeWpayStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " 
					+ " and tas.feeWpayStatusWorkPk.workCode >= "
					+ workMonth
					+ " order by tas.feeWpayStatusWorkPk.workCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			int i = 1;
			for (Iterator itt = tmpList.iterator(); itt.hasNext(); i++) {
				tamVo = new FeeWpayMonthEmpWorkVO();
				tas = (FeeWpayStatusWork) itt.next();
				BeanUtils.copyProperties(tamVo, tas.getFeeWpayStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				tamVo.setWorkSeq(new Integer(i));
				rlst.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	public void addPrWorkDay(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq,List arrNote) {
		int size = arrDay.size();
		Object objDay = null;
		Object objTime = null;
		FeeWpayMonthEmpWork tam = null;
		FeeWpayMonthEmpWorkPK tamPk = null;
		for (int i = 0; i < size; i++) {
			objDay = arrDay.get(i);
			if (!"".equals(objDay) && !"".equals(objTime)) {
				tam = new FeeWpayMonthEmpWork();
				tamPk = new FeeWpayMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(year);
				//tamPk.setWorkMonth(month);
				tamPk.setWorkMonth(new Integer(arrWorkCode.get(i).toString()));
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setFeeWpayMonthEmpWorkPK(tamPk);
				tam.setCreBy(creby);
				tam.setCreDate(new Date());
				tam.setTotalDays(new Double(arrDay.get(i).toString()));
				tam.setWorkCode(arrWorkCode.get(i).toString());
				tam.setNote(arrNote.get(i).toString());
				this.getHibernateTemplate().save(tam);
			}
		}
	}
	
	public List listMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId, Integer maxRowPerPage,
			Integer pageNo) {
		List lstEmp = new ArrayList();
		FeeWpayMonthEmpWorkVO tamVo;
		WgEmployee pnEmp;
		String sql = "";
		try {
			/* start create sql */
			sql = " select distinct tam.wgEmployee, tam.wgEmployee.refPnOrganizationAct.orgCode "
					+ " from FeeWpayMonthEmpWork tam, VWpEmployeeActSecurity vorg "
					+ " where tam.feeWpayMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth >= "
					+ workMonth
					+ " and vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeq = tam.wgEmployee.refPnOrganizationAct.pk.codeSeq "
					+ " order by tam.wgEmployee.refPnOrganizationAct.orgCode, tam.wgEmployee.wgEmployeePK.empCode ";
			/* end create sql */
			Session sss = this.getSession();
			Query qry = sss.createQuery(sql);
			qry.setMaxResults(maxRowPerPage.intValue());
			qry.setFirstResult(pageNo.intValue() * maxRowPerPage.intValue());
			List tmpLst = qry.list();
			Object arrObj[] = null;
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				// pnEmp = (PnEmployee)itt.next();
				arrObj = (Object[]) itt.next();
				pnEmp = (WgEmployee) arrObj[0];
				tamVo = new FeeWpayMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, pnEmp);
				BeanUtils.copyProperties(tamVo, pnEmp.getWgEmployeePK());
				BeanUtils.copyProperties(tamVo, pnEmp.getRefDbPreSuff());
				lstEmp.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstEmp;
	}


	public int getCountTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId) {
		int rlst = 0;
		try {
			String sql = "";
			/* start create sql */
			sql = " select count(distinct tam.wgEmployee.wgEmployeePK.empCode) "
					+ " from FeeWpayMonthEmpWork tam, VWpEmployeeActSecurity vorg "
					+ " where tam.feeWpayMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth >= "
					+ workMonth
					+ " and vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeq = tam.wgEmployee.refPnOrganizationAct.pk.codeSeq "
					+ " order by tam.wgEmployee.refPnOrganizationAct.orgCode, tam.wgEmployee.wgEmployeePK.empCode ";
			/* end create sql */
			Session sss = this.getSession();
			Query qry = sss.createQuery(sql);
			rlst = Integer.parseInt(qry.list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	
	public int getCheckCloseThisMonth(String ouCode, Integer workYear,
			Integer workMonth) {
		int rlst = 0;
		try {
			String sql = " from FeeWpayRwConfirmData con "
					+ " where con.pk.ouCode = '" + ouCode + "'"
					+ " and con.pk.year = " + workYear
					+ " and con.pk.period = " + workMonth;
			List tmpLst = this.getHibernateTemplate().find(sql);
			Iterator itt = tmpLst.iterator();
			if (itt.hasNext()) {
				rlst = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst; 
	}
	public List findListEmpIsNotMonth(String ouCode, Integer month,
			Integer year, String userId, String areaCode, String secCode,
			String workCode) {
		List rlstList = new ArrayList();
		try {
			String sql = " select emp.wgEmployeePK.empCode from WgEmployee emp "
					+ " where emp.wgEmployeePK.ouCode = '" + ouCode + "'";
			if (!"".equals(areaCode)) {
				sql += " and emp.refPnOrganization.areaCode = '" + areaCode
						+ "'";
			}
			if (!"".equals(secCode)) {
				sql += " and emp.refPnOrganization.secCode = '" + secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and emp.refPnOrganization.workCode = '" + workCode
						+ "'";
			}
			sql += " and emp.wgEmployeePK.empCode in ( "
					+ " select vemp.pk.empCode "
					+ " from VWpEmployeeActSecurity vemp "
					+ " where vemp.pk.userId = '" + userId + "'"
					+ " and vemp.pk.ouCode = '" + ouCode + "'" + " )"
					+ " and emp.empStatus = 'B' "
					+ " and emp.wgEmployeePK.empCode not in ( "
					+ " select tam.wgEmployee.wgEmployeePK.empCode"
					+ " from FeeWpayMonthEmpWork tam "
					+ " where tam.feeWpayMonthEmpWorkPK.workYear = " + year
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth >= " + month
					+ " and tam.feeWpayMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " ) " + " order by emp.wgEmployeePK.empCode ";
			rlstList = this.getHibernateTemplate().find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlstList;
	}


	public String deleteTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, List empCode, String userId) {
		String rlst = "";
		FeeWpayMonthEmpWork tam = null;
		List tmpLst = null;
		String sql = "";
		Object tmpObj = null;
		List tmpEmpList = new ArrayList();
		int size = empCode.size();
		Iterator tmpItt = null;
		for (int i = 0; i < size; i++) {
		
			sql = " from FeeWpayMonthEmpWork as tam "
					+ " where tam.feeWpayMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.feeWpayMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth >= " + workMonth
					+ " and tam.feeWpayMonthEmpWorkPK.empCode = '"
					+ empCode.get(i).toString() + "'";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tam = (FeeWpayMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
		return rlst;
	}


	public List findByKey(Integer workYear, Integer workMonth, String empCode,
			String ouCode) {
		List rlst = new ArrayList();
		List lstEmp = new ArrayList();
		FeeWpayMonthEmpWorkVO tamVo = null;
		FeeWpayMonthEmpWork tam;
		FeeWpayMonthEmpWorkPK tamPk = new FeeWpayMonthEmpWorkPK();
		FeeWpayStatusWork tas = new FeeWpayStatusWork();
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			String sql = " from FeeWpayStatusWork tas "
					+ " where tas.feeWpayStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " 
					+ " and tas.feeWpayStatusWorkPk.workCode >= " 
					+ workMonth
					+ " order by tas.feeWpayStatusWorkPk.workCode";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tas = (FeeWpayStatusWork) itt.next();
				tamVo = new FeeWpayMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, tas.getFeeWpayStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				lstEmp.add(tamVo);
			}
			if (lstEmp.size() > 0) {
				tmpLst = sss
						.createCriteria(FeeWpayMonthEmpWork.class)
						.add(Restrictions.eq("feeWpayMonthEmpWorkPK.workYear",
								workYear))
						.add(Restrictions.ge("feeWpayMonthEmpWorkPK.workMonth",
								workMonth))
						.add(Restrictions.eq("feeWpayMonthEmpWorkPK.empCode",
								empCode)).list();
				if (tmpLst.size() == 1) {
					Iterator tmpItt = tmpLst.iterator();
					tam = (FeeWpayMonthEmpWork) tmpItt.next();
					int i = 1;
					for (Iterator itt = lstEmp.iterator(); itt.hasNext(); i++) {
						tamVo = (FeeWpayMonthEmpWorkVO) itt.next();
						if ((tamVo.getWorkCode()).equals(tam.getWorkCode())) {
							BeanUtils.copyProperties(tamVo,
									tam.getFeeWpayMonthEmpWorkPK());
							BeanUtils.copyProperties(tamVo, tam);
						} else {
							tamVo.setWorkSeq(new Integer(i));
							tamVo.setTotalDays(new Double(0));
							tamVo.setTotalTime(new Integer(0));
						}
						rlst.add(tamVo);
					}
				} else {
					int i = 1;
					for (Iterator itt1 = lstEmp.iterator(); itt1.hasNext(); i++) {
						tamVo = (FeeWpayMonthEmpWorkVO) itt1.next();
						for (Iterator itt2 = tmpLst.iterator(); itt2.hasNext();) {
							tam = (FeeWpayMonthEmpWork) itt2.next();
							if (tamVo.getWorkCode().equals(tam.getWorkCode())) {
								BeanUtils.copyProperties(tamVo,
										tam.getFeeWpayMonthEmpWorkPK());
								BeanUtils.copyProperties(tamVo, tam);
								break;
							}
						}
						if (tamVo.getTotalDays() == null) {
							tamVo.setWorkSeq(new Integer(i));
							tamVo.setTotalDays(new Double(0));
							tamVo.setTotalTime(new Integer(0));
						}
						rlst.add(tamVo);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}


	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq,List arrNote) {
		FeeWpayMonthEmpWork tam = null;
		FeeWpayMonthEmpWorkVO tamVo = null;
		FeeWpayMonthEmpWorkPK tamPk = null;
		int size = arrDay.size();
		String check = "";
		for (int i = 0; i < size; i++) {
			tamVo = new FeeWpayMonthEmpWorkVO();
			tamVo.setOuCode(ouCode);
			tamVo.setWorkYear(workYear);
			tamVo.setWorkMonth(new Integer(arrWorkCode.get(i).toString()));
			tamVo.setEmpCode(empCode);
			tamVo.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
			tamVo.setUpdBy(userId);
			tamVo.setTotalDays(!"".equals(arrDay.get(i).toString()) ? new Double(
					arrDay.get(i).toString()) : new Double("0"));
			tamVo.setWorkCode(arrWorkCode.get(i).toString());
			tamVo.setNote(arrNote.get(i).toString());
			check = this.checkBeforeUpdate(tamVo);
			if ("save".equals(check)) {
				tam = new FeeWpayMonthEmpWork();
				tamPk = new FeeWpayMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(workYear);
				tamPk.setWorkMonth(new Integer(arrWorkCode.get(i).toString()));
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setFeeWpayMonthEmpWorkPK(tamPk);
				tam.setTotalDays(new Double(arrDay.get(i).toString()));
				tam.setWorkCode(arrWorkCode.get(i).toString());
				tam.setNote(arrNote.get(i).toString());
				tam.setCreBy(userId);
				tam.setCreDate(new Date());
				this.getHibernateTemplate().save(tam);
			} else if ("update".equals(check)) {
				tam = this.loadTaMonthEmpWork(tamVo);
				tam.setTotalDays(tamVo.getTotalDays());
				tam.setTotalTime(tamVo.getTotalTime());
				tam.setWorkCode(tamVo.getWorkCode());
				tam.setNote(tamVo.getNote());
				tam.setUpdBy(tamVo.getUpdBy());
				tam.setUpdDate(new Date());
				this.getHibernateTemplate().update(tam);
			} else if ("delete".equals(check)) {
				tam = this.loadTaMonthEmpWork(tamVo);
				this.getHibernateTemplate().delete(tam);
			}
		}
	}

	public String checkBeforeUpdate(FeeWpayMonthEmpWorkVO tamVo) {
		String check = "";
		FeeWpayMonthEmpWork tam = null;
		try {
			Session sss = this.getSession();
			List tmpLst = sss
					.createCriteria(FeeWpayMonthEmpWork.class)
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.ouCode",
							tamVo.getOuCode()))
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.workYear",
							tamVo.getWorkYear()))
					.add(Restrictions.ge("feeWpayMonthEmpWorkPK.workMonth",
							tamVo.getWorkMonth()))
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.empCode",
							tamVo.getEmpCode()))
					.add(Restrictions.eq("feeWpayMonthEmpWorkPK.workSeq",
							tamVo.getWorkSeq())).list();
			Iterator itt = tmpLst.iterator();

			if (tamVo.getTotalDays().doubleValue() == 0) {
				check = itt.hasNext() ? "delete" : "noProcess";
			} else if (itt.hasNext()) {
				check = "update";
			} else if (!itt.hasNext()) {
				check = "save";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}


	public FeeWpayMonthEmpWork loadTaMonthEmpWork(FeeWpayMonthEmpWorkVO tamVo) {
		FeeWpayMonthEmpWork tam = null;
		FeeWpayMonthEmpWorkPK tamPk = new FeeWpayMonthEmpWorkPK();
		try {
			BeanUtils.copyProperties(tamPk, tamVo);
			tam = (FeeWpayMonthEmpWork) this.getHibernateTemplate().load(
					FeeWpayMonthEmpWork.class, tamPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tam;
	}


	public void deleteEmpDtl(String ouCode, Integer workYear,
			Integer workMonth, String empCode, List arrWorkSeq) {
		int size = arrWorkSeq.size();
		List tmpLst = null;
		Session sss = this.getSession();
		FeeWpayMonthEmpWork tam = null;
		Iterator itt = null;
		String sql = "";
		for (int i = 0; i < size; i++) {
			sql = " from FeeWpayMonthEmpWork tam "
					+ " where tam.feeWpayMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.feeWpayMonthEmpWorkPK.workYear = " + workYear
					//+ " and tam.feeWpayMonthEmpWorkPK.workMonth = " + arrWorkSeq.get(i)
					+ " and tam.feeWpayMonthEmpWorkPK.empCode = '" + empCode + "'"
					+ " and tam.feeWpayMonthEmpWorkPK.workSeq = "
					+ arrWorkSeq.get(i);
			tmpLst = sss.createQuery(sql).list();
			itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (FeeWpayMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
	}

	public boolean CheckDataForInsert(String ouCode, Integer workYear,
			Integer workMonth, String userId) {
		List tacontrolList = new ArrayList();
		tacontrolList = this
				.getSession()
				.createQuery(
						" select distinct p.pk.ouCode "
								+ " from FeeWpayRwConfirmData p "
								+ " where p.pk.ouCode ='" + ouCode
								+ "' " + " and p.pk.taYear ="
								+ workYear + " "
								+ " and p.pk.taPeriod =" + workMonth
								+ " " + " and p.pk.userId = '"
								+ userId + "' "
				// " and p.taWgControlPk.codeSeq ="+codeSeq+" "
				).list();
		if (tacontrolList == null || tacontrolList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckDataForUpdate(String ouCode, Integer workYear,
			Integer workMonth, Integer codeSeq) {
		List tacontrolList = new ArrayList();
		tacontrolList = this
				.getSession()
				.createQuery(
						" select distinct p.pk.ouCode "
								+ " from FeeWpayRwConfirmData p "
								+ " where p.pk.ouCode ='" + ouCode
								+ "' " + " and p.pk.year ="
								+ workYear + " "
								+ " and p.pk.period =" + workMonth
								+ " " + " and p.pk.codeSeq ="
								+ codeSeq + " " + " and p.flag ='1' ")
				.list();
		if (tacontrolList == null || tacontrolList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
			String ouCode, String userId) {
		String rlst = "";
		FeeWpayRwConfirmData con = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK conPk = new FeeWpayRwConfirmDataPK();
		boolean insertData = this.CheckDataForInsert(ouCode, workYear,
				workMonth, userId);
		if (insertData) {
			conPk.setOuCode(ouCode);
			conPk.setPeriod(new Long(workMonth.toString()));;
			conPk.setYear(new Long(workYear.toString()));
			conPk.setUserId(userId);
			conPk.setFlag("2");
			// conPk.setCodeSeq(new Integer(intTmp));
			con.setPk(conPk);
			//con.setCreBy(userId);
			//con.setUpdBy(userId);
			con.setCreDate(new Date());
			//con.setUpdDate(new Date());
			this.getHibernateTemplate().save(con);
		} else {
			conPk.setOuCode(ouCode);
			conPk.setPeriod(new Long(workMonth.toString()));;
			conPk.setYear(new Long(workYear.toString()));
			conPk.setUserId(userId);
			conPk.setFlag("2");
			// conPk.setCodeSeq(new Integer(intTmp));
			con = (FeeWpayRwConfirmData) this.getHibernateTemplate().load(
					FeeWpayRwConfirmData.class, conPk);
			//con.setFlagClose("Y");
			//con.setUpdBy(userId);
			//con.setUpdDate(new Date());
			this.getHibernateTemplate().update(con);
		}
		rlst = "ï¿½ï¿½ï¿½ï¿½ï¿½Å¢Í§ï¿½ï¿½Í¹ï¿½ï¿½Ð»Õ¹ï¿½ï¿½Ù¡ï¿½Ô´ï¿½ï¿½ï¿½Âºï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		return rlst;
	}

	
	public List findListDivReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		FeeWpayMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {

			sql = "  select distinct nvl(tam.wgEmployee.refPnOrganizationAct.areaCode,tam.wgEmployee.refPnOrganizationAct.divCode) as areaCode ,"
					+ " nvl(tam.wgEmployee.refPnOrganizationAct.areaDesc,tam.wgEmployee.refPnOrganizationAct.divDesc) as areaDesc,"
					+ " tam.wgEmployee.refPnOrganizationAct.divCode,"
					+ " tam.wgEmployee.refPnOrganizationAct.divDesc "
					+ " from FeeWpayMonthEmpWork tam "
					+ " where tam.feeWpayMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.wgEmployee.codeSeqAct in ( "
					+ " select vorg.pk.codeSeq "
					+ " from VWpEmployeeActSecurity vorg "
					+ " where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ "		and vorg.pk.userId = '" + userId + "'" + " ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.wgEmployee.refPnOrganizationAct.areaCode,tam.wgEmployee.refPnOrganizationAct.divCode))";

			Session ss = this.getSession();
			Query qq = ss.createQuery(sql);
			listEmp = qq.list();
			String arerCode = "";
			String areaDesc = "";
			String divCode = "";
			String divDesc = "";
			for (int i = 0; i < listEmp.size(); i++) {
				Object[] obj = (Object[]) listEmp.get(i);
				arerCode = (String) obj[0];
				areaDesc = (String) obj[1];
				divCode = (String) obj[2];
				divDesc = (String) obj[3];
				tamVo = new FeeWpayMonthEmpWorkVO();
				tamVo.setAreaCode(arerCode);
				tamVo.setAreaDesc(areaDesc);
				tamVo.setDivCode(divCode);
				tamVo.setDivDesc(divDesc);
				list.add(tamVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public List findListReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		FeeWpayMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {

			sql = " select nvl(tam.wgEmployee.refPnOrganizationAct.areaCode,tam.wgEmployee.refPnOrganizationAct.divCode) as areaCode ,"
					+ " nvl(tam.wgEmployee.refPnOrganizationAct.areaDesc,tam.wgEmployee.refPnOrganizationAct.divDesc) as areaDesc, "
					+ " nvl(tam.wgEmployee.refPnOrganizationAct.secCode,nvl(tam.wgEmployee.refPnOrganizationAct.areaCode,tam.wgEmployee.refPnOrganizationAct.divCode)) as secCode,"
					+ " nvl(tam.wgEmployee.refPnOrganizationAct.secDesc,nvl(tam.wgEmployee.refPnOrganizationAct.areaDesc,tam.wgEmployee.refPnOrganizationAct.divDesc)) as secDesc,"
					+ " tam.wgEmployee.refPnOrganizationAct.workCode as orgWorkCode,"
					+ " tam.wgEmployee.refPnOrganizationAct.workDesc as orgWorkDesc,"
					+ " tam.wgEmployee.wgEmployeePK.empCode,"
					+ " tam.wgEmployee.refDbPreSuff.prefixName,"
					+ " tam.wgEmployee.firstName,"
					+ " tam.wgEmployee.lastName,"
					+ " tam.workCode,"
					+ " tam.feeWpayStatusWork.workDesc,"
					+ " tam.totalDays,"
					+ " tam.wgEmployee.refPnOrganizationAct.divCode,"
					+ " tam.wgEmployee.refPnOrganizationAct.divDesc, "
					+ " tam.note "
					+ " from FeeWpayMonthEmpWork tam "
					+ " where tam.feeWpayMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.feeWpayMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.wgEmployee.codeSeqAct in ( "
					+ " select vorg.pk.codeSeq "
					+ " from VWpEmployeeActSecurity vorg "
					+ " where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ "  and vorg.pk.userId = '" + userId + "'" + " ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganizationAct.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.wgEmployee.refPnOrganizationAct.workCode,nvl(tam.wgEmployee.refPnOrganizationAct.secCode,nvl(tam.wgEmployee.refPnOrganizationAct.areaCode,nvl(tam.wgEmployee.refPnOrganizationAct.divCode,nvl(tam.wgEmployee.refPnOrganizationAct.partCode,''))))),"
					+ " tam.wgEmployee.refPnOrganizationAct.orgCode,tam.wgEmployee.wgEmployeePK.empCode,tam.workCode ";

			Session ss = this.getSession();
			Query qq = ss.createQuery(sql);
			listEmp = qq.list();
			for (int i = 0; i < listEmp.size(); i++) {
				Object[] obj = (Object[]) listEmp.get(i);
				tamVo = new FeeWpayMonthEmpWorkVO();
				tamVo.setAreaCode((String) obj[0]);
				tamVo.setAreaDesc((String) obj[1]);
				tamVo.setSecCode((String) obj[2]);
				tamVo.setSecDesc((String) obj[3]);
				tamVo.setOrgWorkCode((String) obj[4]);
				tamVo.setOrgWorkDesc((String) obj[5]);
				tamVo.setEmpCode((String) obj[6]);
				tamVo.setFullName((String) obj[7] + " " + (String) obj[8] + " "
						+ (String) obj[9]);
				tamVo.setWorkCode((String) obj[10]);
				tamVo.setWorkDesc((String) obj[11]);
				tamVo.setTotalDays((Double) obj[12]);
				tamVo.setDivCode((String) obj[13]);
				tamVo.setDivDesc((String) obj[14]);
				tamVo.setNote((String) obj[15]);
				list.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public String getStatusBeforeAddEmployee(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId) {
		String rlst = "";
		
		int newMonth = workMonth.intValue();
		newMonth = newMonth*2;
		
		
		try {
			int tmpInt;
		
			String hql = " select con.pk.userId "
					+ " from FeeWpayRwConfirmData con"
					+ " where con.pk.ouCode = '" + ouCode + "'"
					+ " and con.pk.year = " + workYear
					+ " and con.pk.period = " + newMonth
					//+ " and con.pk.userId   = '" + userId + "'"
					+ " and con.pk.flag = '1' ";

			List tmpList = this.getHibernateTemplate().find(hql);
			if (tmpList == null || tmpList.size() == 0) {
				rlst = "N";
			} else {
				rlst = "Y";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("++++++++++++++++++++++++++ ::: " + rlst);
		return rlst;
	}


	public String getStatusUpdateEmployee(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId) {
		String rlst = "";
		
		int newMonth = workMonth.intValue();
		newMonth = newMonth*2;
		
		
		try {
			
			String hql = " select con.pk.userId "
					+ " from FeeWpayRwConfirmData con"
					+ " where con.pk.ouCode = '" + ouCode + "'"
					+ " and con.pk.year = " + workYear
					+ " and con.pk.period = " + newMonth
					//+ " and con.pk.userId   = '" + userId + "'"
					+ " and con.pk.flag = '1' ";

			List tmpList = this.getHibernateTemplate().find(hql);
			if (tmpList == null || tmpList.size() == 0) {
				rlst = "N";
			} else {
				rlst = "Y";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}
	public String findAreaDescTwo(String areaCode) {
		String nameAreaDesc = "";
		List list = null;
		PnOrganization pn = new PnOrganization();
		if (areaCode != null && !areaCode.equals("")) {
			list = this.getHibernateTemplate().find(
					"from PnOrganization p where p.areaCode ='" + areaCode
							+ "'");
			if (list.size() > 0) {
				pn = (PnOrganization) list.get(0);
				nameAreaDesc = pn.getAreaDesc();
			}
		}
		return nameAreaDesc;
	}
	
	public List findListTaReportNew(String ouCode, String userId,
	Integer workYear, Integer workYearFrom, Integer workYearTo,
	String monthFrom, String monthTo, String orgCodeFrom,
	String orgCodeTo, String empCodeFrom, String empCodeTo,
	String choice) {
List list = null;
List dataList = new ArrayList();
JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
TaReportVO vo;
int checkMonthFrom = 0;
int checkMonthTo = 0;
int k = 0;
int x = 1;
String empCode = "";
String tempYear = "";
String tempYearTo = "";
int numMonthForm = monthFrom.length();
int numMonthTo = monthTo.length();
if (numMonthForm < 2) {
	monthFrom = "0" + monthFrom;
}
if (numMonthTo < 2) {
	monthTo = "0" + monthTo;
}
int numSubQuey = 0;


String sql = "";
int y = 1;// ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ë¹ï¿½ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ë¹ï¿½ï¿½ web
Map map = null;
try {

	sql = " select a.ou_code,a.work_year,a.emp_code,a.Full_Name, "
			+ "\n a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.div_short, "
			+ " NVL(a.area_code,a.div_code) area_code, NVL(a.area_desc,a.div_desc) area_desc,"
			+ " NVL(a.sec_code,NVL(a.area_code,a.div_code)) sec_code,NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)) sec_desc,"
			+ " a.orgwork_code,a.orgwork_desc, "
			+ "\n nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1, "
			+ "\n nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2, "
			+ "\n nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3, "
			+ "\n nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4, "
			+ "\n nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5, "
			+ "\n nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6, "
			+ "\n nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7, "
			+ "\n nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8, "
			+ "\n nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9, "
			+ "\n nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10, "
			+ "\n nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11, "
			+ "\n nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12, "
			+ "\n to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+ "
			+ "\n		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total "
			+ "\n from "
			+ "\n ( "
			+ "\n	 	select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month, "
			+ "\n			decode(t.work_month,1,t.total_days,0) day_month1, "
			+ "\n			decode(t.work_month,2,t.total_days,0) day_month2, "
			+ "\n			decode(t.work_month,3,t.total_days,0) day_month3, "
			+ "\n			decode(t.work_month,4,t.total_days,0) day_month4, "
			+ "\n			decode(t.work_month,5,t.total_days,0) day_month5, "
			+ "\n			decode(t.work_month,6,t.total_days,0) day_month6, "
			+ "\n			decode(t.work_month,7,t.total_days,0) day_month7, "
			+ "\n			decode(t.work_month,8,t.total_days,0) day_month8, "
			+ "\n			decode(t.work_month,9,t.total_days,0) day_month9, "
			+ "\n			decode(t.work_month,10,t.total_days,0) day_month10, "
			+ "\n			decode(t.work_month,11,t.total_days,0) day_month11, "
			+ "\n			decode(t.work_month,12,t.total_days,0) day_month12, "
			+ "\n			(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
			+ "\n			pn.code_seq_act code_seq,org.org_code,org.org_desc, "
			+ "\n			org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc "
			+ "\n	from FEE_WPAY_MONTH_EMP_WORK t,FEE_WPAY_STATUS_WORK ts,WG_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org "
			+ "\n	where t.ou_code=pn.ou_code "
			+ "\n		and t.EMP_CODE=pn.EMP_CODE "
			+ "\n		and t.ou_code=ts.ou_code "
			+ "\n		and	t.WORK_CODE=ts.WORK_CODE "
			+ "\n		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
			+ "\n		and        pn.ou_code	= org.ou_code "
			+ "\n		and        pn.code_seq_act	= org.code_seq "
			+ "\n       and     pn.emp_code in (SELECT EMP_CODE FROM FEE_WPAY_MONTH_EMP_WORK M "
			+ "\n			                      WHERE M.WORK_YEAR = t.work_year " 
			+ "\n 						          AND M.WORK_MONTH = t.work_month "
			+ "\n                                 AND M.TOTAL_DAYS <1 "
			+ "\n                               UNION "
			+ "\n                                SELECT W.EMP_CODE FROM WG_EMPLOYEE W "
			+ "\n                                WHERE W.EMP_STATUS ='B' "
			+ "\n                                 AND W.EMP_CODE  IN "
			+ "\n                                 (SELECT M.EMP_CODE FROM FEE_WPAY_MONTH_EMP_WORK M "
			+ "\n                                   WHERE M.WORK_YEAR = t.work_year "
			+ "\n                                    AND M.WORK_MONTH = t.work_month )) "
			+ "\n		 "
			+ "\n		and 	   pn.code_seq_act  in ( select   vpn.CODE_SEQ "
			+ "\n				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
			+ "\n															   where  vpn.USER_ID='"
			+ userId
			+ "'"
			+ "\n									) ";

	if ((workYearFrom != null && workYearFrom.intValue() != 0)
			&& (workYearTo != null && workYearTo.intValue() != 0)) {
		if (!monthFrom.equals("") && !monthTo.equals("")) {
			sql += "\n and trim(t.work_year||'/'||TRIM(TO_CHAR(t.work_month,'00')))  between '"
					+ workYearFrom
					+ "/"
					+ monthFrom
					+ "'"
					+ "\n and '"
					+ workYearTo + "/" + monthTo + "'";
		}
	}

	if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
		sql += "\n and  org.org_code between '" + orgCodeFrom
				+ "' and  '" + orgCodeTo + "'";
	} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
		sql += "\n and  org.org_code  >='" + orgCodeFrom + "'";
	}
	if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
		sql += "\n and t.emp_code between '" + empCodeFrom + "' and  '"
				+ empCodeTo + "'";
	} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
		sql += " and t.emp_code >='" + empCodeFrom + "'";
	}
/*	if (!workCodeFrom.equals("%") && !workCodeTo.equals("%")) {
		sql += "\n and t.WORK_CODE between '" + workCodeFrom
				+ "' and  '" + workCodeTo + "'";
	} else if (!workCodeFrom.equals("%") && workCodeTo.equals("%")) {
		sql += " and t.WORK_CODE >='" + workCodeFrom + "'";
	}*/
	sql += "\n ) a   "
			+ "\n   group by  a.ou_code,a.work_year,a.emp_code,a.full_name,a.code_seq,a.org_code,a.org_desc, "
			+ "\n	  	  		a.level_code,a.div_code,a.div_desc,a.div_short, NVL(a.area_code,a.div_code) , NVL(a.area_desc,a.div_desc),NVL(a.sec_code,NVL(a.area_code,a.div_code)),NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)),a.orgwork_code,a.orgwork_desc "
			+
			// "\n a.level_code,a.div_code,a.div_desc, a.area_code
			// ,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc
			// "+
			// "\n order by
			// nvl(a.orgwork_code,nvl(a.sec_code,nvl(a.div_code,''))),a.area_code,a.org_code,a.emp_code,a.work_code
			// ";
			"\n order by a.div_code, NVL(a.area_code,a.div_code),NVL(a.sec_code,NVL(a.area_code,a.div_code)),a.org_code,a.emp_code ";

	Object[] obj = null;
	System.out.println("\n\n sql==>" + sql);
	list = jdbcTemplate.queryForList(sql);
	System.out
			.println("--------------------------- Query Finish AAA----------------------");
	empCode = "";
	for (int i = 0; i < list.size(); i++) {
		map = new HashMap();
		map = (Map) list.get(i);
		obj = new Object[13];
		vo = new TaReportVO();
		vo.setOuCode(map.get("ou_code") != null ? map.get("ou_code")
				.toString() : "");
		vo.setWorkYear(map.get("work_year") != null ? new Integer(map
				.get("work_year").toString()) : new Integer(0));
		vo.setEmpCode(map.get("emp_code") != null ? map.get("emp_code")
				.toString() : "");
		vo.setFullName(map.get("Full_Name") != null ? map.get(
				"Full_Name").toString() : "");
		if (!empCode.equals(vo.getEmpCode())) {
			empCode = vo.getEmpCode();
			vo.setEmpCodeTemp(vo.getEmpCode());
		} else {
			vo.setFullName("");
			vo.setEmpCodeTemp("");
			empCode = vo.getEmpCode();
		}
		vo.setEmpCode(empCode);
		vo.setCodeSeq(map.get("code_seq") != null ? map.get("code_seq")
				.toString() : "");
		vo.setOrgCode(map.get("org_code") != null ? map.get("org_code")
				.toString() : "");
		vo.setOrgDesc(map.get("org_desc") != null ? map.get("org_desc")
				.toString() : "");
		vo.setLevelCode(map.get("level_code") != null ? map.get(
				"level_code").toString() : "");
		vo.setDivCode(map.get("div_code") != null ? map.get("div_code")
				.toString() : "");
		vo.setDivDesc(map.get("div_desc") != null ? map.get("div_desc")
				.toString() : "");
		vo.setDivShort(map.get("div_short") != null ? map.get(
				"div_short").toString() : "");
		vo.setAreaCode(map.get("area_code") != null ? map.get(
				"area_code").toString() : "");
		vo.setAreaDesc(map.get("area_desc") != null ? map.get(
				"area_desc").toString() : "");
		vo.setSecCode(map.get("sec_code") != null ? map.get("sec_code")
				.toString() : "");
		vo.setSecDesc(map.get("sec_desc") != null ? map.get("sec_desc")
				.toString() : "");
		vo.setOrgWorkCode(map.get("orgwork_code") != null ? map.get(
				"orgwork_code").toString() : "");
		vo.setOrgWorkDesc(map.get("orgwork_desc") != null ? map.get(
				"orgwork_desc").toString() : "");
		vo.setTotal(map.get("total") != null ? map.get("total")
				.toString() : "");

		obj[1] = map.get("month1") != null ? map.get("month1")
				.toString() : "";
		obj[2] = map.get("month2") != null ? map.get("month2")
				.toString() : "";
		obj[3] = map.get("month3") != null ? map.get("month3")
				.toString() : "";
		obj[4] = map.get("month4") != null ? map.get("month4")
				.toString() : "";
		obj[5] = map.get("month5") != null ? map.get("month5")
				.toString() : "";
		obj[6] = map.get("month6") != null ? map.get("month6")
				.toString() : "";
		obj[7] = map.get("month7") != null ? map.get("month7")
				.toString() : "";
		obj[8] = map.get("month8") != null ? map.get("month8")
				.toString() : "";
		obj[9] = map.get("month9") != null ? map.get("month9")
				.toString() : "";
		obj[10] = map.get("month10") != null ? map.get("month10")
				.toString() : "";
		obj[11] = map.get("month11") != null ? map.get("month11")
				.toString() : "";
		obj[12] = map.get("month12") != null ? map.get("month12")
				.toString() : "";

		if (monthFrom != null && !monthFrom.equals("")) {
			checkMonthFrom = Integer.parseInt(monthFrom);
		}
		if (monthTo != null && !monthTo.equals("")) {
			checkMonthTo = Integer.parseInt(monthTo);
		}
		k = 0;
		x = 1;
		y = 1;
		if (choice.equals("1")) {// case same year
			tempYear = vo.getWorkYear() == null ? "" : vo.getWorkYear()
					.toString();
			vo.setTempBudgetYear(tempYear.substring(2));
			while (checkMonthFrom <= checkMonthTo) {
				k = checkMonthFrom;
				switch (y) {
				case 1:
					vo.setMonth1((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName1(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 2:
					vo.setMonth2((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName2(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 3:
					vo.setMonth3((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName3(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 4:
					vo.setMonth4((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName4(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 5:
					vo.setMonth5((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName5(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 6:
					vo.setMonth6((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName6(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 7:
					vo.setMonth7((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName7(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 8:
					vo.setMonth8((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName8(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 9:
					vo.setMonth9((obj[k] != null ? (String) obj[k] : ""));
					vo.setMonthName9(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 10:
					vo.setMonth10((obj[k] != null ? (String) obj[k]
							: ""));
					vo.setMonthName10(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 11:
					vo.setMonth11((obj[k] != null ? (String) obj[k]
							: ""));
					vo.setMonthName11(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				case 12:
					vo.setMonth12((obj[k] != null ? (String) obj[k]
							: ""));
					vo.setMonthName12(convertMonth(k)
							+ vo.getTempBudgetYear());
					break;
				}
				checkMonthFrom++;
				y++;
			}
		} else if (choice.equals("2")) {// case difference year

			if (workYearFrom != null && workYearFrom.intValue() != 0) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½uï¿½
				tempYear = workYearFrom.toString();
				vo.setTempBudgetYear(tempYear.substring(2));
				while (checkMonthFrom <= 12) {
					k = checkMonthFrom;

					switch (y) {
					case 1:
						vo.setMonth1((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName1(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 2:
						vo.setMonth2((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName2(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 3:
						vo.setMonth3((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName3(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 4:
						vo.setMonth4((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName4(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 5:
						vo.setMonth5((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName5(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 6:
						vo.setMonth6((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName6(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 7:
						vo.setMonth7((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName7(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 8:
						vo.setMonth8((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName8(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 9:
						vo.setMonth9((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName9(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 10:
						vo.setMonth10((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName10(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 11:
						vo.setMonth11((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName11(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					case 12:
						vo.setMonth12(((obj)[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName12(convertMonth(k)
								+ vo.getTempBudgetYear());
						break;
					}
					checkMonthFrom++;
					y++;
				}
			}
			if (workYearTo != null && workYearTo.intValue() != 0) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ø´
				tempYearTo = workYearTo.toString();
				vo.setTempBudgetYear2(tempYearTo.substring(2));
				while (x <= checkMonthTo) {
					k = x;
					switch (y) {
					case 1:
						vo.setMonth1((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName1(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 2:
						vo.setMonth2((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName2(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 3:
						vo.setMonth3((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName3(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 4:
						vo.setMonth4((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName4(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 5:
						vo.setMonth5((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName5(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 6:
						vo.setMonth6((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName6(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 7:
						vo.setMonth7((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName7(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 8:
						vo.setMonth8((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName8(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 9:
						vo.setMonth9((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName9(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 10:
						vo.setMonth10((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName10(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 11:
						vo.setMonth11((obj[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName11(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					case 12:
						vo.setMonth12(((obj)[k] != null ? (String) obj[k]
								: ""));
						vo.setMonthName12(convertMonth(k)
								+ vo.getTempBudgetYear2());
						break;
					}
					x++;
					y++;
				}
			}

		}
		if (vo.getMonth1() == null) {
			vo.setMonth1("-");
		} else {
			if (vo.getMonth1().equals("0.0")) {
				vo.setMonth1("");
			}
		}
		if (vo.getMonth2() == null) {
			vo.setMonth2("-");
		} else {
			if (vo.getMonth2().equals("0.0")) {
				vo.setMonth2("");
			}
		}
		if (vo.getMonth3() == null) {
			vo.setMonth3("-");
		} else {
			if (vo.getMonth3().equals("0.0")) {
				vo.setMonth3("");
			}
		}
		if (vo.getMonth4() == null) {
			vo.setMonth4("-");
		} else {
			if (vo.getMonth4().equals("0.0")) {
				vo.setMonth4("");
			}
		}
		if (vo.getMonth5() == null) {
			vo.setMonth5("-");
		} else {
			if (vo.getMonth5().equals("0.0")) {
				vo.setMonth5("");
			}
		}
		if (vo.getMonth6() == null) {
			vo.setMonth6("-");
		} else {
			if (vo.getMonth6().equals("0.0")) {
				vo.setMonth6("");
			}
		}
		if (vo.getMonth7() == null) {
			vo.setMonth7("-");
		} else {
			if (vo.getMonth7().equals("0.0")) {
				vo.setMonth7("");
			}
		}
		if (vo.getMonth8() == null) {
			vo.setMonth8("-");
		} else {
			if (vo.getMonth8().equals("0.0")) {
				vo.setMonth8("");
			}
		}
		if (vo.getMonth9() == null) {
			vo.setMonth9("-");
		} else {
			if (vo.getMonth9().equals("0.0")) {
				vo.setMonth9("");
			}
		}
		if (vo.getMonth10() == null) {
			vo.setMonth10("-");
		} else {
			if (vo.getMonth10().equals("0.0")) {
				vo.setMonth10("");
			}
		}
		if (vo.getMonth11() == null) {
			vo.setMonth11("-");
		} else {
			if (vo.getMonth11().equals("0.0")) {
				vo.setMonth11("");
			}
		}
		if (vo.getMonth12() == null) {
			vo.setMonth12("-");
		} else {
			if (vo.getMonth12().equals("0.0")) {
				vo.setMonth12("");
			}
		}

		if (vo.getMonthName1() == null) {
			vo.setMonthName1("-");
		}
		;
		if (vo.getMonthName2() == null) {
			vo.setMonthName2("-");
		}
		;
		if (vo.getMonthName3() == null) {
			vo.setMonthName3("-");
		}
		;
		if (vo.getMonthName4() == null) {
			vo.setMonthName4("-");
		}
		;
		if (vo.getMonthName5() == null) {
			vo.setMonthName5("-");
		}
		;
		if (vo.getMonthName6() == null) {
			vo.setMonthName6("-");
		}
		;
		if (vo.getMonthName7() == null) {
			vo.setMonthName7("-");
		}
		;
		if (vo.getMonthName8() == null) {
			vo.setMonthName8("-");
		}
		;
		if (vo.getMonthName9() == null) {
			vo.setMonthName9("-");
		}
		;
		if (vo.getMonthName10() == null) {
			vo.setMonthName10("-");
		}
		;
		if (vo.getMonthName11() == null) {
			vo.setMonthName11("-");
		}
		;
		if (vo.getMonthName12() == null) {
			vo.setMonthName12("-");
		}
		;

		dataList.add(vo);
	}
} catch (Exception e) {
	e.printStackTrace();
}
System.out
		.println("--------------------------- Query Finish ----------------------");
return dataList;
}

public List findListSecTaReportNew(String ouCode, String userId,
	Integer workYear, Integer workYearFrom, Integer workYearTo,
	String monthFrom, String monthTo, String orgCodeFrom,
	String orgCodeTo, String empCodeFrom, String empCodeTo,
	String choice) {
List list = null;
List dataList = new ArrayList();
JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
TaReportVO vo;
int checkMonthFrom = 0;
int checkMonthTo = 0;
int k = 0;
int x = 1;
String empCode = "";
String tempYear = "";
String tempYearTo = "";
int numMonthForm = monthFrom.length();
int numMonthTo = monthTo.length();
if (numMonthForm < 2) {
	monthFrom = "0" + monthFrom;
}
if (numMonthTo < 2) {
	monthTo = "0" + monthTo;
}
int numSubQuey = 0;
/*Vector dataFrom = new Vector();
Vector dataTo = new Vector();
Vector dataWorkCode = new Vector();
for (int i = 0; i < dataStatusFrom.size(); i++) {
	if (!dataStatusFrom.elementAt(i).equals("")) {
		if (!dataStatusTo.elementAt(i).equals("")) {
			numSubQuey++;
			dataFrom.addElement(dataStatusFrom.elementAt(i));
			dataTo.addElement(dataStatusTo.elementAt(i));
			dataWorkCode.addElement(statusWorkCode.elementAt(i));
		} else {
			numSubQuey++;
			dataFrom.addElement(dataStatusFrom.elementAt(i));
			dataTo.addElement(dataStatusTo.elementAt(i));
			dataWorkCode.addElement(statusWorkCode.elementAt(i));
		}
	} else {
		if (!dataStatusTo.elementAt(i).equals("")) {
			numSubQuey++;
			dataFrom.addElement(dataStatusFrom.elementAt(i));
			dataTo.addElement(dataStatusTo.elementAt(i));
			dataWorkCode.addElement(statusWorkCode.elementAt(i));
		}
	}
}*/
String sql = "";
int y = 1;// ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ë¹ï¿½ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ë¹ï¿½ï¿½ web
Map map = null;
try {
	sql += " order by  nvl(vt.secCode,nvl(vt.divCode,''))";

	sql = " select distinct NVL(a.area_code,a.div_code) area_code,"
			+ " NVL(a.area_desc,a.div_desc) area_desc,"
			+ " a.div_code,a.div_desc,a.div_short  "
			+ " from "
			+ " ( "
			+ "	 select	t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
			+ "		decode(t.work_month,1,t.total_days,0) day_month1,  "
			+ "		decode(t.work_month,2,t.total_days,0) day_month2,  "
			+ "		decode(t.work_month,3,t.total_days,0) day_month3,  "
			+ "		decode(t.work_month,4,t.total_days,0) day_month4,  "
			+ "		decode(t.work_month,5,t.total_days,0) day_month5,  "
			+ "		decode(t.work_month,6,t.total_days,0) day_month6,  "
			+ "		decode(t.work_month,7,t.total_days,0) day_month7,  "
			+ "		decode(t.work_month,8,t.total_days,0) day_month8,  "
			+ "		decode(t.work_month,9,t.total_days,0) day_month9,  "
			+ "		decode(t.work_month,10,t.total_days,0) day_month10, "
			+ "		decode(t.work_month,11,t.total_days,0) day_month11, "
			+ "		decode(t.work_month,12,t.total_days,0) day_month12,  "
			+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
			+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
			+ "	org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
			+ "	from FEE_WPAY_MONTH_EMP_WORK t,FEE_WPAY_STATUS_WORK ts,WG_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
			+ "		where t.ou_code=pn.ou_code "
			+ "		and t.EMP_CODE=pn.EMP_CODE "
			+ "		and t.ou_code=ts.ou_code "
			+ "		and	t.WORK_CODE=ts.WORK_CODE "
			+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
			+ "		AND        pn.ou_code	= org.ou_code "
			+ "		AND        pn.code_seq_act	= org.code_seq "
			+ "       and     pn.emp_code in (SELECT EMP_CODE FROM FEE_WPAY_MONTH_EMP_WORK M "
			+ "			                      WHERE M.WORK_YEAR = t.work_year " 
			+ " 						          AND M.WORK_MONTH = t.work_month "
			+ "                                 AND M.TOTAL_DAYS <1 "
			+ "                               UNION "
			+ "                                SELECT W.EMP_CODE FROM WG_EMPLOYEE W "
			+ "                                WHERE W.EMP_STATUS ='B' "
			+ "                                 AND W.EMP_CODE  IN "
			+ "                                 (SELECT M.EMP_CODE FROM FEE_WPAY_MONTH_EMP_WORK M "
			+ "                                   WHERE M.WORK_YEAR = t.work_year "
			+ "                                    AND M.WORK_MONTH = t.work_month )) "
			+ "		 "
			+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ "
			+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
			+ "															   where  vpn.USER_ID='" + userId + "'"
			+ "			) ";
	/*if ((sumDonKeyFrom.intValue() != 0 || sumDonKeyTo.intValue() != 0)
			|| numSubQuey > 0) {
		sql += "\n and t.EMP_CODE in( select emp_code"
				+ "\n					  from(";
		if (numSubQuey > 0) {
			for (int i = 0; i < dataFrom.size(); i++) {
				sql += "\n 					select emp_code,sum(total_days) total"
						+ "\n  						from    ta_month_emp_work ";
				if ((workYearFrom != null && workYearFrom.intValue() != 0)
						&& (workYearTo != null && workYearTo.intValue() != 0)) {
					if (!monthFrom.equals("") && !monthTo.equals("")) {
						sql += "\n 			where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
								+ workYearFrom
								+ "/"
								+ monthFrom
								+ "' and '"
								+ workYearTo
								+ "/"
								+ monthTo + "'";
					}
				}// close if year
				sql += "\n				    and work_code ='"
						+ dataWorkCode.elementAt(i) + "'" + // size
						// ï¿½ï¿½Ò¡Ñº
						// workYearFrom
						// ï¿½ï¿½ï¿½ï¿½
						" \n 						group by emp_code ";
				if (dataTo.elementAt(i) != null
						&& !dataTo.elementAt(i).equals("")) {// for
					// check
					// number
					// of
					// day
					if (dataFrom.elementAt(i) != null
							&& !dataFrom.elementAt(i).equals("")) {
						sql += "\n  			having sum(total_days) between "
								+ dataFrom.elementAt(i) + " and "
								+ dataTo.elementAt(i);
					} else {
						sql += "\n  			having sum(total_days) between 0 and "
								+ dataTo.elementAt(i);
					}
				} else {
					sql += "\n 				having sum(total_days) >= "
							+ dataFrom.elementAt(i);
				}
				if ((i + 1) != dataFrom.size()) {
					sql += "\n 				union ";
				} else {
					if (sumDonKeyFrom.intValue() != 0
							|| sumDonKeyTo.intValue() != 0) {
						sql += "\n  			union ";
					}
				}
			}// close for
		}
		if (sumDonKeyFrom.intValue() != 0
				|| sumDonKeyTo.intValue() != 0) {// for ï¿½Ò»ï¿½ï¿½ï¿½+ï¿½Ò¡Ô¨
			sql += "\n 						select emp_code,sum(total_days) total "
					+ "\n 							from    ta_month_emp_work ";
			if ((workYearFrom != null && workYearFrom.intValue() != 0)
					&& (workYearTo != null && workYearTo.intValue() != 0)) {
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += "\n 				where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
							+ workYearFrom
							+ "/"
							+ monthFrom
							+ "' and '"
							+ workYearTo
							+ "/"
							+ monthTo
							+ "'";
				}
			}// close if year
			sql += "\n 						and    ( work_code = (select ill_code from  ta_status_control where ou_code = '"
					+ ouCode
					+ "') "
					+ "\n 							or work_code = (select bus_code from  ta_status_control where ou_code = '"
					+ ouCode
					+ "') ) "
					+ "\n 							group by emp_code "
					+ " \n							having sum(total_days) between '"
					+ sumDonKeyFrom + "' and '" + sumDonKeyTo + "'";
		}
*/
	//	sql += "\n					)" + "\n					) ";

	//}
	// sql+="\n and org.area_code IS NOT NULL ";

	if ((workYearFrom != null && workYearFrom.intValue() != 0)
			&& (workYearTo != null && workYearTo.intValue() != 0)) {
		if (!monthFrom.equals("") && !monthTo.equals("")) {
			sql += " and TRIM(t.work_year||'/'||TRIM(TO_CHAR(t.work_month,'00')))  BETWEEN '"
					+ workYearFrom
					+ "/"
					+ monthFrom
					+ "'"
					+ " AND '"
					+ workYearTo + "/" + monthTo + "'";
		}
	}
	if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
		sql += " and  org.org_code between '" + orgCodeFrom
				+ "' and  '" + orgCodeTo + "'";
	} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
		sql += " and  org.org_code  >='" + orgCodeFrom + "'";
	}
	if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
		sql += " and t.emp_code between '" + empCodeFrom + "' and  '"
				+ empCodeTo + "'";
	} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
		sql += " and t.emp_code >='" + empCodeFrom + "'";
	}
/*	if (!workCodeFrom.equals("%") && !workCodeTo.equals("%")) {
		sql += "\n and t.WORK_CODE between '" + workCodeFrom
				+ "' and  '" + workCodeTo + "'";
	} else if (!workCodeFrom.equals("%") && workCodeTo.equals("%")) {
		sql += " and t.WORK_CODE >='" + workCodeFrom + "'";
	}*/
	sql += " ) a   "
			+ "   group by  a.sec_code,a.sec_desc,a.div_code,a.div_desc,a.div_short,a.area_code,a.area_desc "
			+ " order by  NVL(a.area_code,a.div_code)";

	Object[] obj = null;
	System.out.println("\n\n sql==>" + sql);
	list = jdbcTemplate.queryForList(sql);
	empCode = "";
	for (int i = 0; i < list.size(); i++) {
		map = new HashMap();
		map = (Map) list.get(i);
		obj = new Object[13];
		vo = new TaReportVO();
		// vo.setSecCode(map.get("sec_code")!=null?map.get("sec_code").toString():"");
		// vo.setSecDesc(map.get("sec_desc")!=null?map.get("sec_desc").toString():"");
		vo.setDivCode(map.get("div_code") != null ? map.get("div_code")
				.toString() : "");
		vo.setDivDesc(map.get("div_desc") != null ? map.get("div_desc")
				.toString() : "");
		vo.setDivShort(map.get("div_short") != null ? map.get(
				"div_short").toString() : "");
		vo.setAreaCode(map.get("area_code") != null ? map.get(
				"area_code").toString() : "");
		vo.setAreaDesc(map.get("area_desc") != null ? map.get(
				"area_desc").toString() : "");

		dataList.add(vo);
	}
} catch (Exception e) {
	e.printStackTrace();
}

return dataList;
}

/**
* method ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ç¹µï¿½ï¿½ï¿½Å¢ï¿½ï¿½ï¿½ç¹µï¿½ï¿½ï¿½Ñ¡ï¿½ï¿½ ï¿½Ù¡ï¿½ï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ method doPrintReport
* 
* @param numOfMonth
* @return
*/
public String convertMonth(int k) {
String conM = "";

switch (k) {
case 1:
	conM = "Á¡ÃÒ¤Á";
	break;
case 2:
	conM = "¡ØÁÀÒ¾Ñ¹¸ì";
	break;
case 3:
	conM = "ÁÕ¹Ò¤Á";
	break;
case 4:
	conM = "àÁÉÒÂ¹";
	break;
case 5:
	conM = "¾ÄÉÀÒ¤Á";
	break;
case 6:
	conM = "ÁÔ¶Ø¹ÒÂ¹";
	break;
case 7:
	conM = "¡Ã¡®Ò¤Á";
	break;
case 8:
	conM = "ÊÔ§ËÒ¤Á";
	break;
case 9:
	conM = "¡Ñ¹ÂÒÂ¹";
	break;
case 10:
	conM = "µØÅÒ¤Á";
	break;
case 11:
	conM = "¾ÄÈ¨Ô¡ÒÂ¹";
	break;
case 12:
	conM = "¸Ñ¹ÇÒ¤Á";
	break;
case 0:
	conM = "-";
	break;
}

return conM;
}

public String findAreaDesc(String orgCode) {
String nameAreaDesc = "";
List list = null;
PnOrganization pn = new PnOrganization();
if (orgCode != null && !orgCode.equals("")) {
	list = this.getHibernateTemplate().find(
			"from PnOrganization p where p.orgCode ='" + orgCode + "'");
	if (list.size() > 0) {
		pn = (PnOrganization) list.get(0);
		nameAreaDesc = pn.getAreaDesc();
	}
}
return nameAreaDesc;
}

}