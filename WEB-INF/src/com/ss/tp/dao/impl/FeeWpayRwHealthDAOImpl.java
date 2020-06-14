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
import com.ss.tp.dao.FeeWpayRwHealthDAO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwHealthVO;
import com.ss.tp.dto.RwHealthEmployeeVO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.model.FeeWpayRwHealth;
import com.ss.tp.model.FeeWpayRwIncDecOther;

public class FeeWpayRwHealthDAOImpl extends HibernateDaoSupport implements
		FeeWpayRwHealthDAO, Serializable {
	List rwList = new ArrayList();

	public FeeWpayRwHealthDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		FeeWpayRwHealth rw = new FeeWpayRwHealth();
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

	public FeeWpayRwHealth loadRwHealth(FeeWpayRwHealthVO rwVo) {
		FeeWpayRwHealth rwp = new FeeWpayRwHealth();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (FeeWpayRwHealth) this.getHibernateTemplate().load(FeeWpayRwHealth.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		FeeWpayRwHealth rw = new FeeWpayRwHealth();
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

	public void deleteRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		FeeWpayRwHealth rw = new FeeWpayRwHealth();
		try {
			BeanUtils.copyProperties(rw, rwhealthvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(FeeWpayRwHealthVO rwhealthvo) {
		this.rwList.add(rwhealthvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwHealths() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				FeeWpayRwHealthVO rwhealthvo = (FeeWpayRwHealthVO) this.rwList.get(i);
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
		FeeWpayRwHealth rw = new FeeWpayRwHealth();
		try {
			for (int i = 0; i < rwhealthvolist.size(); i++) {
				FeeWpayRwHealthVO rwhealthvo = (FeeWpayRwHealthVO) rwhealthvolist.get(i);

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
		 * pnPos.positionShort, rw.codeSeq " ); hql.append( " from FeeWpayRwHealth rw ,
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

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, rw.codeSeq  ");
		hql.append(" from FeeWpayRwHealth rw , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

		/*
		 * hql.append(" select count(distinct rw.empCode) "); hql.append( " from
		 * FeeWpayRwHealth rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization
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
		hql.append(" from FeeWpayRwHealth rw , WgEmployee pnEmp  , PnOrganization pnOrg, VPnOrganizationSecurity v  ");
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
		hql.append(" from FeeWpayRwHealth rw, ");
		hql.append(" WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
	//	hql.append(" PnPosition pnPos, ");
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

		hql.append(" from FeeWpayRwHealth rw ");
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
				FeeWpayRwHealthVO rw = new FeeWpayRwHealthVO();

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
					+ " hel.totalHour, hel.flagPr, emp.wgEmployeePK.empCode, emp.refDbPreSuff.prefixName, emp.firstName, emp.lastName,org.orgCode,org.orgDesc, "
					+ " hel.yearWork,hel.periodWork "
					//+ " from FeeWpayRwHealth hel, PnOrganization org, VPrEmployeeSecurityReport vemp, PnEmployee emp, VPnOrganizationSecurity v "
					+ " from FeeWpayRwHealth hel, PnOrganization org,  WgEmployee emp, VPnOrganizationSecurity v "
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
			FeeWpayRwHealthVO hel = null;
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				hel = new FeeWpayRwHealthVO();
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

	
	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId,String approveFlag) {
		List rlst = new ArrayList();
		try {
			String sql = " select hel.ouCode, hel.yearPr, hel.periodPr, org.areaCode, org.secCode, org.areaDesc, org.secDesc, "
					+ " hel.totalHour, hel.flagPr, emp.wgEmployeePK.empCode, emp.refDbPreSuff.prefixName, emp.firstName, emp.lastName,org.orgCode,org.orgDesc, "
					+ " hel.yearWork,hel.periodWork "
					//+ " from FeeWpayRwHealth hel, PnOrganization org, VPrEmployeeSecurityReport vemp, PnEmployee emp, VPnOrganizationSecurity v "
					+ " from FeeWpayRwHealth hel, PnOrganization org,  WgEmployee emp, VPnOrganizationSecurity v "
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
					+ " and hel.approveFlag = '"
					+ approveFlag
					+ "'"
					+ " and hel.confirmFlag ='Y' "
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
			FeeWpayRwHealthVO hel = null;
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				hel = new FeeWpayRwHealthVO();
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
		sql.append(" from FeeWpayRwHealth p, VFeeWpPrEmployeeSecurity e ");
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
		sql.append(" from FeeWpayRwHealth p, VFeeWpPrEmployeeSecurity e ");
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
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,int count, int countRecord)

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
		hql.append(" pnEmp.salary, ");
		hql.append(" rwInc.yearWork, ");
		hql.append(" rwInc.periodWork, ");
		hql.append(" rwInc.totalHour, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count).setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String flagPr = (String) r[1];
			String empCode = (String) r[2];
			String prefixName = (String) r[3];
			String firstName = (String) r[4];
			String lastName = (String) r[5];
			Integer salary = (Integer) r[6];
			Double yearWork = (Double) r[7];
			Double periodWork = (Double) r[8];
			Double totalHour = (Double) r[9];
			Double seqData = (Double) r[10];
			Long codeSeq = (Long) r[11];

			RwHealthEmployeeVO ret = new RwHealthEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setSalary(salary);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalHour(totalHour);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);

			retList.add(ret);
		}

		return retList;
	}
	public List findByCriteriaListA(String userId, String evaOuCode,
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
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

	
	public Integer countDataApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
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
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");
		
	    System.out.println("HQL findByCriteria ==> " + hql.toString());
		
		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}
	
	public void addListApprove(FeeWpayRwHealthVO rwhealthvo) {
		this.rwList.add(rwhealthvo);
		
	}

	//@Override
	public void insertAndUpdateApTablesApprove() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				FeeWpayRwHealthVO rwhealthvo = (FeeWpayRwHealthVO) this.rwList.get(i);
				if ((rwhealthvo.getKeySeq() != new Long(0))
						&& rwhealthvo.getKeySeq() != null
						&& !rwhealthvo.getKeySeq().equals("")) {
					this.updateApTableApprove(rwhealthvo);
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

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
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
		hql.append(" rwInc.codeSeq, ");
		hql.append(" rwInc.approveFlag ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq, rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count).setMaxResults(countRecord).list();
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
			String approveFlag = (String) r[11];

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
			ret.setApproveFlag(approveFlag);

			retList.add(ret);
		}

		return retList;
	}

	//@Override
	public void updateApTableApprove(FeeWpayRwHealthVO rwhealthvo)
			throws Exception {
		FeeWpayRwHealth rw = new FeeWpayRwHealth();
		try {
			BeanUtils.copyProperties(rw, rwhealthvo);
			rw = this.loadRwHealth(rwhealthvo);
			rw.setApproveFlag(rwhealthvo.getApproveFlag());
			rw.setApproveBy(rwhealthvo.getApproveBy());
			rw.setApproveDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	

	//@Override
	public Integer countDataList(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo) {
		// TODO Auto-generated method stub
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
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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

			return (Integer) empList.get(0);
		}

	//@Override
	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		// TODO Auto-generated method stub
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
		hql.append(" from FeeWpayRwHealth rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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