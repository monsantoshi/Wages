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

import com.ss.tp.dao.FeeWpayRwIncDecOtherDAO;
import com.ss.tp.dto.ApTableVO;
import com.ss.tp.dto.RwIncDecEmployeeVO;
import com.ss.tp.dto.RwIncDecOtherReportVO;
import com.ss.tp.dto.FeeWpayPrDailyOutAmtReportVO;
import com.ss.tp.dto.FeeWpayPrDailyAmtReportVO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.model.ApTable;
import com.ss.tp.model.FeeWpayRwIncDecOther;

public class FeeWpayRwIncDecOtherDAOImpl extends HibernateDaoSupport implements
		FeeWpayRwIncDecOtherDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FeeWpayRwIncDecOtherDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public FeeWpayRwIncDecOther loadRwRwIncDecOther(RwIncDecOtherVO rwVo) {
		FeeWpayRwIncDecOther rwp = new FeeWpayRwIncDecOther();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (FeeWpayRwIncDecOther) this.getHibernateTemplate().load(
					FeeWpayRwIncDecOther.class, rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			rw = this.loadRwRwIncDecOther(rwincdecothervo);
			rw.setCodeSeq(rwincdecothervo.getCodeSeq());
			rw.setFlagPr(rwincdecothervo.getFlagPr());
			rw.setEmpCode(rwincdecothervo.getEmpCode());
			rw.setYearWork(rwincdecothervo.getYearWork());
			rw.setPeriodWork(rwincdecothervo.getPeriodWork());
			rw.setTotalAmt(rwincdecothervo.getTotalAmt());
			rw.setSeqData(rwincdecothervo.getSeqData());
			rw.setStartDate(rwincdecothervo.getStartDate());
			rw.setEndDate(rwincdecothervo.getEndDate());
			rw.setUpdBy(rwincdecothervo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwIncDecOtherVO rwincdecothervo) {
		this.rwList.add(rwincdecothervo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwIncDecOthers() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwIncDecOtherVO rwincdecothervo = (RwIncDecOtherVO) this.rwList
						.get(i);
				if ((rwincdecothervo.getKeySeq() != new Long(0))
						&& rwincdecothervo.getKeySeq() != null
						&& !rwincdecothervo.getKeySeq().equals("")) {
					this.updateRwIncDecOther(rwincdecothervo);
				} else {
					this.insertRwIncDecOther(rwincdecothervo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwIncDecOthers(List rwincdecothervolist) throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			for (int i = 0; i < rwincdecothervolist.size(); i++) {
				RwIncDecOtherVO rwincdecothervo = (RwIncDecOtherVO) rwincdecothervolist
						.get(i);

				BeanUtils.copyProperties(rw, rwincdecothervo);
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
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
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

		if (incDecCode != null && !incDecCode.equals("")) {
			criteria.append(" and rwInc.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("' ");
		}

		if (groupCode != null && !groupCode.equals("")) {
			criteria.append(" and rwInc.groupCode = '");
			criteria.append(groupCode);
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
		hql.append(" rwInc.totalAmt, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq, ");
		hql.append(" rwInc.startDate, ");
		hql.append(" rwInc.endDate ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayRwIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" order by rwInc.codeSeq,rwInc.empCode ");

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
			Double totalAmt = (Double) r[8];
			Double seqData = (Double) r[9];
			Long codeSeq = (Long) r[10];
			Date startDate = (Date) r[11];
			Date endDate = (Date) r[12];

			RwIncDecEmployeeVO ret = new RwIncDecEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalAmt(totalAmt);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);
			ret.setStartDate(startDate);
			ret.setEndDate(endDate);

			retList.add(ret);
		}

		return retList;
	}

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwIncDecOther p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'Y' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.groupCode = '1' ");
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

	public boolean canDeleteIncome(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwIncDecOther p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.groupCode = '1' ");
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

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwIncDecOther p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'Y' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.groupCode = '2' ");
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

	public boolean canDeleteDeduct(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwIncDecOther p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.groupCode = '2' ");
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

	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totalAmt,");
		hql.append(" rwOth.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
		hql.append(" rwOth.flagPr, rwOth.startDate, rwOth.endDate , rwOth.yearWork||'/'||rwOth.periodWork ");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '1' ");
		hql.append(" and prInc.pk.incDecCode  not in ('01','02','03')  ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode,nvl(nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,'$'),pnOrg.divCode,rwOth.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totalAmt = (Double) r[2];
			String empCode = (String) r[3];
			String empName = (String) r[4];
			String orgDesc = (String) r[5];
			String flagPr = (String) r[6];
			Date stDate = (Date) r[7];
			Date endDate = (Date) r[8];
			String yearPeriod = (String) r[9];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);
			ret.setYearPeriod(yearPeriod);

			if (stDate != null) {
				ret.setStDate(stDate);
			} else {
				ret.setStDate(null);
			}
			if (endDate != null) {
				ret.setEndDate(endDate);
			} else {
				ret.setEndDate(null);
			}

			retList.add(ret);
		}
		return retList;
	}

	
	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		
		if (approveFlag != null && !approveFlag.equals("")) {
			criteria.append(" and rwOth.approveFlag = '");
			criteria.append(approveFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totalAmt,");
		hql.append(" rwOth.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
		hql.append(" rwOth.flagPr, rwOth.startDate, rwOth.endDate , rwOth.yearWork||'/'||rwOth.periodWork ");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '1' ");
		hql.append(" and prInc.pk.incDecCode  not in ('01','02','03')  ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode,nvl(nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,'$'),pnOrg.divCode,rwOth.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totalAmt = (Double) r[2];
			String empCode = (String) r[3];
			String empName = (String) r[4];
			String orgDesc = (String) r[5];
			String flagPr = (String) r[6];
			Date stDate = (Date) r[7];
			Date endDate = (Date) r[8];
			String yearPeriod = (String) r[9];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);
			ret.setYearPeriod(yearPeriod);

			if (stDate != null) {
				ret.setStDate(stDate);
			} else {
				ret.setStDate(null);
			}
			if (endDate != null) {
				ret.setEndDate(endDate);
			} else {
				ret.setEndDate(null);
			}

			retList.add(ret);
		}
		return retList;
	}
	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '1' ");
		hql.append(" and prInc.pk.incDecCode  not in ('01','02','03')  ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);

			retList.add(ret);
		}
		return retList;
	}
	
	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		
		if (approveFlag != null && !approveFlag.equals("")) {
			criteria.append(" and rwOth.approveFlag = '");
			criteria.append(approveFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '1' ");
		hql.append(" and prInc.pk.incDecCode  not in ('01','02','03')  ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);

			retList.add(ret);
		}
		return retList;
	}

	public List decOtherReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String incDecCode, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totalAmt,");
		hql.append(" rwOth.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
		hql.append(" rwOth.flagPr,rwOth.startDate,rwOth.endDate, rwOth.yearWork||'/'||rwOth.periodWork");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '2' ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		// hql.append(" order by rwOth.incDecCode,
		// nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		// '||pnOrg.secDesc,rwOth.empCode ");
		hql.append(" order by rwOth.incDecCode,nvl(nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,'$'),pnOrg.divCode,rwOth.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());
		
		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totalAmt = (Double) r[2];
			String empCode = (String) r[3];
			String empName = (String) r[4];
			String orgDesc = (String) r[5];
			String flagPr = (String) r[6];
			Date stDate = (Date) r[7];
			Date endDate = (Date) r[8];
			String yearPeriod = (String) r[9];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);
			ret.setStDate(stDate);
			ret.setEndDate(endDate);
			ret.setYearPeriod(yearPeriod);

			retList.add(ret);
		}
		return retList;
	}

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '2' ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);

			retList.add(ret);
		}
		return retList;
	}
	
	
	
	public List decOtherReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		
		if (approveFlag != null && !approveFlag.equals("")) {
			criteria.append(" and rwOth.approveFlag = '");
			criteria.append(approveFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totalAmt,");
		hql.append(" rwOth.empCode, pnEmp.refDbPreSuff.prefixName||' '||pnEmp.firstName||' '||pnEmp.lastName,");
		hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
		hql.append(" rwOth.flagPr,rwOth.startDate,rwOth.endDate, rwOth.yearWork||'/'||rwOth.periodWork");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '2' ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		// hql.append(" order by rwOth.incDecCode,
		// nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		// '||pnOrg.secDesc,rwOth.empCode ");
		hql.append(" order by rwOth.incDecCode,nvl(nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,'$'),pnOrg.divCode,rwOth.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());
		
		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totalAmt = (Double) r[2];
			String empCode = (String) r[3];
			String empName = (String) r[4];
			String orgDesc = (String) r[5];
			String flagPr = (String) r[6];
			Date stDate = (Date) r[7];
			Date endDate = (Date) r[8];
			String yearPeriod = (String) r[9];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);
			ret.setStDate(stDate);
			ret.setEndDate(endDate);
			ret.setYearPeriod(yearPeriod);

			retList.add(ret);
		}
		return retList;
	}

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		
		if (approveFlag != null && !approveFlag.equals("")) {
			criteria.append(" and rwOth.approveFlag = '");
			criteria.append(approveFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from FeeWpayRwIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,PrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '2' ");
		hql.append(" and prInc.flagWeb = 'Y' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);

			retList.add(ret);
		}
		return retList;
	}
	
	public List decOutAmtReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String incDecCode) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totalAmt,rwOth.outAmt,rwOth.netAmt, ");
		hql.append(" rwOth.memberNo,rwOth.empCode, rwOth.fullName,");
		hql.append(" rwOth.divDesc||' '||nvl(rwOth.secDesc,rwOth.areaDesc)  ");
		//hql.append(" rwOth.yearWork||'/'||rwOth.periodWork ");
		hql.append(" from VFeeWpayPrDailyOutAmtRep rwOth , FeeWpayPrIncomeDeduct prInc, VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		//hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		//hql.append(" and rwOth.empCode = '271884' ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		// hql.append(" order by rwOth.incDecCode,
		// nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		// '||pnOrg.secDesc,rwOth.empCode ");
		hql.append(" order by rwOth.incDecCode,rwOth.divSeq,nvl(rwOth.areaSeq,0),nvl(rwOth.secSeq,0),rwOth.memberNo,rwOth.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totalAmt = (Double) r[2];
			Double outAmt = (Double) r[3];
			Double netAmt = (Double) r[4];
			String memberNo = (String) r[5];
			String empCode = (String) r[6];
			String fullName = (String) r[7];
			String orgDesc = (String) r[8];
			
			//String yearPeriod = (String) r[9];
			

			FeeWpayPrDailyOutAmtReportVO ret = new FeeWpayPrDailyOutAmtReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setOutAmt(outAmt);
			ret.setNetAmt(netAmt);
			ret.setMemberNo(memberNo);
			ret.setEmpCode(empCode);
			ret.setFullName(fullName);
			ret.setOrgDesc(orgDesc);
			
			//ret.setYearPeriod(yearPeriod);
			
			retList.add(ret);
		}
		return retList;
	}

	public List decOutAmtReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from  VFeeWpayPrDailyOutAmtRep rwOth ,FeeWpayPrIncomeDeduct prInc, VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		//hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			FeeWpayPrDailyOutAmtReportVO ret = new FeeWpayPrDailyOutAmtReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			System.out.println(ret.getIncDecCode());
			System.out.println(ret.getIncDecName());
			
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
		sql.append(" delete Fee_Wpay_Rw_Inc_Dec_Other inc");
		sql.append(" where inc.ou_Code = '" + ouCode + "' ");
		sql.append(" and inc.year_Pr = " + year);
		sql.append(" and inc.period_Pr = " + period);
		sql.append(" and inc.group_Code = '1' ");
		sql.append(" and inc.inc_Dec_Code = '08' ");
		sql.append(" and inc.code_Seq in ( ");
		sql.append(" 	select code_Seq ");
		sql.append(" 	from Su_User_Organization ");
		sql.append(" 	where ou_Code = '" + ouCode + "' ");
		sql.append(" 	and user_Id = '" + userId + "' ");
		sql.append(" ) ");
		sql.append("and emp_code in (select a. emp_code from  fee_wpay_rw_inc_dec_other a, su_user_organization s ");
		sql.append("where a.ou_code = '" + ouCode + "' ");
		sql.append("and a.year_pr = " + yearBefore + " ");
		sql.append("and a.period_pr = " + periodBefore + " ");
		sql.append("and a.group_code = '1' ");
		sql.append("and a.inc_dec_code = '08'");
		sql.append("and s.user_id = '" + userId + "' ");
		sql.append("and a.code_seq = s.code_seq) ");

		System.out.println("****************** ::: " + sql.toString());
		this.jdbcTemplate.execute(sql.toString());

		StringBuffer hql = new StringBuffer(0);
		hql.append("insert into fee_wpay_rw_inc_dec_other(ou_code, year_pr, period_pr, emp_code, group_code, inc_dec_code, year_work, period_work");
		hql.append(", code_seq, total_amt, flag_pr, seq_data, confirm_flag ");
		hql.append(", cre_by, cre_date, upd_by, upd_date) ");
		hql.append("select ou_code, "
				+ year
				+ ", "
				+ period
				+ ", emp_code, group_code, inc_dec_code, decode(period_work,12,year_work+1,year_work) ");
		hql.append(", decode(period_work,12,1,period_work +1) ");
		hql.append(", code_seq, 3200, flag_pr, seq_data, 'N' ");
		hql.append(", '" + userId + "', sysdate, '" + userId + "', sysdate ");
		hql.append("from fee_wpay_rw_inc_dec_other ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and year_pr = " + yearBefore + " ");
		hql.append("and period_pr = " + periodBefore + " ");
		hql.append("and group_code = '1' ");
		hql.append("and inc_dec_code = '08' ");
		hql.append("and code_seq in (select code_seq  ");
		hql.append("from su_user_organization  ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + hql.toString());
		this.jdbcTemplate.execute(hql.toString());
		
		
		StringBuffer uql = new StringBuffer(0);
		uql.append("update fee_wpay_rw_inc_dec_other rw ");
		uql.append("set rw.code_seq = (select pr.code_seq_work from fee_wpay_pr_employee pr where pr.ou_code = '" + ouCode + "' ");
		uql.append(" and pr.year = " + year);
		uql.append(" and pr.period = " + period);
		uql.append(" and pr.emp_code = rw.emp_code ) ");
		uql.append(" where ou_code = '" + ouCode + "' ");
		uql.append(" and year_pr = " + yearBefore + " ");
		uql.append(" and period_pr = " + periodBefore + " ");
		uql.append(" and group_code = '1' ");
		uql.append(" and inc_dec_code = '08' ");
		uql.append(" and code_seq in (select code_seq  ");
		uql.append(" from su_user_organization  ");
		uql.append(" where ou_code = '" + ouCode + "' ");
		uql.append(" and user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + uql.toString());
		this.jdbcTemplate.execute(uql.toString());
		
		
	}

	public void generate59(String userId, String ouCode, Long year, Long period) {

		Long periodBefore = new Long(period.longValue() - 2);
		Long yearBefore = year;
		if (periodBefore.toString().equals("0")) {
			periodBefore = new Long(24);
			yearBefore = new Long(Integer.parseInt(year.toString()) - 1);
		}

		StringBuffer sql = new StringBuffer(0);
		sql.append(" delete Fee_Wpay_Rw_Inc_Dec_Other inc");
		sql.append(" where inc.ou_Code = '" + ouCode + "' ");
		sql.append(" and inc.year_Pr = " + year);
		sql.append(" and inc.period_Pr = " + period);
		sql.append(" and inc.group_Code = '2' ");
		sql.append(" and inc.inc_Dec_Code = '59' ");
		sql.append(" and inc.code_Seq in ( ");
		sql.append(" 	select code_Seq ");
		sql.append(" 	from Su_User_Organization ");
		sql.append(" 	where ou_Code = '" + ouCode + "' ");
		sql.append(" 	and user_Id = '" + userId + "' ");
		sql.append(" ) ");
		sql.append("and emp_code in (select a. emp_code from  fee_wpay_rw_inc_dec_other a, su_user_organization s ");
		sql.append("where a.ou_code = '" + ouCode + "' ");
		sql.append(" and a.year_pr = " + year);
		sql.append(" and a.period_pr = " + period);
		sql.append(" and a.group_code = '2' ");
		sql.append(" and a.inc_dec_code = '59'");
		sql.append(" and s.user_id = '" + userId + "' ");
		sql.append(" and a.code_seq = s.code_seq) ");

		System.out.println("****************** ::: " + sql.toString());
		this.jdbcTemplate.execute(sql.toString());

		StringBuffer hql = new StringBuffer(0);
		hql.append("insert into fee_wpay_rw_inc_dec_other(ou_code, year_pr, period_pr, emp_code, group_code, inc_dec_code, year_work, period_work");
		hql.append(", code_seq, total_amt, flag_pr, seq_data, confirm_flag ");
		hql.append(", cre_by, cre_date, upd_by, upd_date) ");
		hql.append("select ou_code, "
				+ year
				+ ", "
				+ period
				+ ", emp_code, group_code, inc_dec_code, decode(period_work,12,year_work+1,year_work) ");
		hql.append(", decode(period_work,12,1,period_work +1) ");
		hql.append(", code_seq, tot_amt, flag_pr, seq_data, 'N' ");
		hql.append(", '" + userId + "', sysdate, '" + userId + "', sysdate ");
		hql.append("from fee_wpay_rw_inc_dec_other ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and year_pr = " + yearBefore + " ");
		hql.append("and period_pr = " + periodBefore + " ");
		hql.append("and group_code = '2' ");
		hql.append("and inc_dec_code = '59' ");
		hql.append("and code_seq in (select code_seq  ");
		hql.append("from su_user_organization  ");
		hql.append("where ou_code = '" + ouCode + "' ");
		hql.append("and user_id = '" + userId + "') ");

		System.out.println("++++++++++++++++++++++++++ ::: " + hql.toString());
		this.jdbcTemplate.execute(hql.toString());
	}
	public void updateRwIncDecOtherApprove(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			rw = this.loadRwRwIncDecOther(rwincdecothervo);
			rw.setApproveFlag(rwincdecothervo.getApproveFlag());
			rw.setApproveBy(rwincdecothervo.getApproveBy());
			rw.setApproveDate(new Date());
			//rw.setEmpCode(aptablevo.getEmpCode());
			//rw.setCodeSeq(aptablevo.getCodeSeq());
			//rw.setNewCodeSeq(aptablevo.getNewCodeSeq());
			//rw.setNewOldDuty(aptablevo.getNewOldDuty());
			//rw.setNewDuty(aptablevo.getNewDuty());
			//rw.setNewPositionCode(aptablevo.getNewPositionCode());
			//rw.setNewLevelCode(aptablevo.getNewLevelCode());
			//rw.setNewGworkCode(aptablevo.getNewGworkCode());
			//rw.setNewOrgCode(aptablevo.getNewOrgCode());
			//rw.setSeqData(aptablevo.getSeqData());
			//rw.setTotAmt(aptablevo.getTotAmt());
			//rw.setUpdBy(aptablevo.getUpdBy());
			//rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public void addListApprove(RwIncDecOtherVO rwincdecothervo) {
		this.rwList.add(rwincdecothervo);
	}
	public void insertAndUpdateApTablesApprove() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwIncDecOtherVO rwincdecothervo = (RwIncDecOtherVO) this.rwList.get(i);
				if ((rwincdecothervo.getKeySeq() != new Long(0))
						&& rwincdecothervo.getKeySeq() != null
						&& !rwincdecothervo.getKeySeq().equals("")) {
					this.updateRwIncDecOtherApprove(rwincdecothervo);
				} else {
					this.insertRwIncDecOther(rwincdecothervo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode,int count,int countRecord) {
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

		if (incDecCode != null && !incDecCode.equals("")) {
			criteria.append(" and rwInc.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("' ");
		}

		if (groupCode != null && !groupCode.equals("")) {
			criteria.append(" and rwInc.groupCode = '");
			criteria.append(groupCode);
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
		hql.append(" rwInc.totalAmt, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq, ");
		hql.append(" rwInc.startDate, ");
		hql.append(" rwInc.endDate, ");
		hql.append(" rwInc.approveFlag ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayRwIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq,rwInc.empCode ");

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
			Double yearWork = (Double) r[6];
			Double periodWork = (Double) r[7];
			Double totalAmt = (Double) r[8];
			Double seqData = (Double) r[9];
			Long codeSeq = (Long) r[10];
			Date startDate = (Date) r[11];
			Date endDate = (Date) r[12];
			String approveFlag = (String) r[13];

			RwIncDecEmployeeVO ret = new RwIncDecEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalAmt(totalAmt);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);
			ret.setStartDate(startDate);
			ret.setEndDate(endDate);
			ret.setApproveFlag(approveFlag);

			retList.add(ret);
		}

		return retList;
	}
	
	
	public Integer countDataApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
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

		if (incDecCode != null && !incDecCode.equals("")) {
			criteria.append(" and rwInc.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("' ");
		}

		if (groupCode != null && !groupCode.equals("")) {
			criteria.append(" and rwInc.groupCode = '");
			criteria.append(groupCode);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(rwInc.keySeq) ");
		hql.append(" from FeeWpayRwIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
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
		hql.append(" order by rwInc.codeSeq,rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());
		
		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	
	public void updateApTableApprove(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		FeeWpayRwIncDecOther rw = new FeeWpayRwIncDecOther();
		try {
			rw = this.loadRwRwIncDecOther(rwincdecothervo);
			rw.setApproveFlag(rwincdecothervo.getApproveFlag());
			rw.setApproveBy(rwincdecothervo.getApproveBy());
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
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
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

		if (incDecCode != null && !incDecCode.equals("")) {
			criteria.append(" and rwInc.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("' ");
		}

		if (groupCode != null && !groupCode.equals("")) {
			criteria.append(" and rwInc.groupCode = '");
			criteria.append(groupCode);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(rwInc.keySeq) ");
		hql.append(" from FeeWpayRwIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		//hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq,rwInc.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());
		
		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	//@Override
	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			String incDecCode, String groupCode, int count, int countRecord) {
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

		if (incDecCode != null && !incDecCode.equals("")) {
			criteria.append(" and rwInc.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("' ");
		}

		if (groupCode != null && !groupCode.equals("")) {
			criteria.append(" and rwInc.groupCode = '");
			criteria.append(groupCode);
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
		hql.append(" rwInc.totalAmt, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.codeSeq, ");
		hql.append(" rwInc.startDate, ");
		hql.append(" rwInc.endDate ");
		//hql.append(" rwInc.approveFlag ");
		//hql.append(" ,nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode ");
		hql.append(" from FeeWpayRwIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		//hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwInc.codeSeq,rwInc.empCode ");

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
			String orgDesc = (String) r[6];
			Double yearWork = (Double) r[7];
			Double periodWork = (Double) r[8];
			Double totalAmt = (Double) r[9];
			Double seqData = (Double) r[10];
			Long codeSeq = (Long) r[11];
			Date startDate = (Date) r[12];
			Date endDate = (Date) r[13];
			//String approveFlag = (String) r[14];

			RwIncDecEmployeeVO ret = new RwIncDecEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOrgDesc(orgDesc);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalAmt(totalAmt);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);
			ret.setStartDate(startDate);
			ret.setEndDate(endDate);
			//ret.setApproveFlag(approveFlag);

			retList.add(ret);
		}

		return retList;
	}

	public List incOutAmtReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String incDecCode ,String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}
		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwOth.incDecCode,prInc.incDecName,rwOth.totAmt,rwOth.stDate,rwOth.endDate,rwOth.sumN,rwOth.sumR, ");
		hql.append(" rwOth.sumA,rwOth.empCode, rwOth.fullName,rwOth.flagPr, rwOth.yearWork||'/'||rwOth.periodWork, ");
		hql.append(" rwOth.divDesc||' '||nvl(rwOth.secDesc,rwOth.areaDesc)  ");
		//hql.append(" rwOth.orgCode||' '||rwOth.divDesc||' '||nvl(rwOth.secDesc,rwOth.areaDesc)||' '||rwOth.workDesc");
		hql.append(" from VFeeWpayPrDailyAmtRep rwOth , FeeWpayPrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		//hql.append(" and rwInc.confirmFlag = 'Y' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		//hql.append(" and rwOth.empCode = '271884' ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		// hql.append(" order by rwOth.incDecCode,
		// nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		// '||pnOrg.secDesc,rwOth.empCode ");
		hql.append(" order by rwOth.incDecCode,rwOth.divSeq,nvl(rwOth.areaSeq,0),nvl(rwOth.secSeq,0),rwOth.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];
			Double totAmt = (Double) r[2];
			Date stDate = (Date) r[3];
			Date endDate = (Date) r[4];
			Double sumN = (Double) r[5];
			Double sumR = (Double) r[6];
			Double sumA = (Double) r[7];
			String empCode = (String) r[8];
			String fullName = (String) r[9];
			String flagPr = (String) r[10];
			String yearPeriod = (String) r[11];
			String orgDesc = (String) r[12];
			

			FeeWpayPrDailyAmtReportVO ret = new FeeWpayPrDailyAmtReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotAmt(totAmt);
			ret.setStDate(stDate);
			ret.setEndDate(endDate);
			ret.setSumN(sumN);
			ret.setSumA(sumA);
			ret.setSumR(sumR);
			ret.setEmpCode(empCode);
			ret.setFullName(fullName);
			ret.setFlagPr(flagPr);
			ret.setYearPeriod(yearPeriod);
			ret.setOrgDesc(orgDesc);
			
			retList.add(ret);
		}
		return retList;
	}

	public List incOutAmtReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode ,String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwOth.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwOth.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (incDecCode != null && !incDecCode.equals("%")) {
			criteria.append(" and rwOth.incDecCode = '");
			criteria.append(incDecCode);
			criteria.append("'");
		}
		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwOth.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct(rwOth.incDecCode),prInc.incDecName");
		hql.append(" from  VFeeWpayPrDailyAmtRep rwOth ,FeeWpayPrIncomeDeduct prInc,VPnOrganizationSecurity v ");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwOth.ouCode = v.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.pk.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.pk.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.pk.incDecCode ");
		hql.append(" order by rwOth.incDecCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String incDec = (String) r[0];
			String incDecName = (String) r[1];

			FeeWpayPrDailyAmtReportVO ret = new FeeWpayPrDailyAmtReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
		
			retList.add(ret);
		}
		return retList;
	}



	
}