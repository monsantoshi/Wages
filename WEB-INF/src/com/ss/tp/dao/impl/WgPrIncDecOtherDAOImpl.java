package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.WgPrIncDecOtherDAO;

import com.ss.tp.dto.RwIncDecOtherReportVO;
import com.ss.tp.dto.WgPrIncDecEmployeeVO;
import com.ss.tp.dto.WgPrIncDecOtherVO;
import com.ss.tp.model.WgPrIncDecOther;

public class WgPrIncDecOtherDAOImpl extends HibernateDaoSupport implements
		WgPrIncDecOtherDAO, Serializable {
	List rwList = new ArrayList();

	public WgPrIncDecOtherDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		WgPrIncDecOther rw = new WgPrIncDecOther();
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

	public WgPrIncDecOther loadWgPrIncDecOther(WgPrIncDecOtherVO rwVo) {
		WgPrIncDecOther rwp = new WgPrIncDecOther();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (WgPrIncDecOther) this.getHibernateTemplate().load(
					WgPrIncDecOther.class, rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		WgPrIncDecOther rw = new WgPrIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			rw = this.loadWgPrIncDecOther(rwincdecothervo);
			rw.setCodeSeq(rwincdecothervo.getCodeSeq());
			rw.setFlagPr(rwincdecothervo.getFlagPr());
			rw.setEmpCode(rwincdecothervo.getEmpCode());
			rw.setYearWork(rwincdecothervo.getYearWork());
			rw.setPeriodWork(rwincdecothervo.getPeriodWork());
			rw.setTotalAmt(rwincdecothervo.getTotalAmt());
			rw.setSeqData(rwincdecothervo.getSeqData());
			rw.setUpdBy(rwincdecothervo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		WgPrIncDecOther rw = new WgPrIncDecOther();
		try {
			BeanUtils.copyProperties(rw, rwincdecothervo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(WgPrIncDecOtherVO rwincdecothervo) {
		this.rwList.add(rwincdecothervo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateWgPrIncDecOthers() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				WgPrIncDecOtherVO rwincdecothervo = (WgPrIncDecOtherVO) this.rwList
						.get(i);
				if ((rwincdecothervo.getKeySeq() != new Long(0))
						&& rwincdecothervo.getKeySeq() != null
						&& !rwincdecothervo.getKeySeq().equals("")) {
					this.updateWgPrIncDecOther(rwincdecothervo);
				} else {
					this.insertWgPrIncDecOther(rwincdecothervo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertWgPrIncDecOthers(List rwincdecothervolist)
			throws Exception {
		WgPrIncDecOther rw = new WgPrIncDecOther();
		try {
			for (int i = 0; i < rwincdecothervolist.size(); i++) {
				WgPrIncDecOtherVO rwincdecothervo = (WgPrIncDecOtherVO) rwincdecothervolist
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
		hql.append(" rwInc.codeSeq ");
		hql.append(" from WgPrIncDecOther rwInc , WgEmployee pnEmp , PnOrganization pnOrg ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + evaOuCode + "' ");
		hql.append("	 and b.pk.year = " + evaYear);
		hql.append("	 and b.pk.period = " + evaPeriod);
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwInc.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwInc.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by pnOrg.orgCode,rwInc.empCode ");

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
			Integer yearWork = (Integer) r[6];
			Integer periodWork = (Integer) r[7];
			Double totalAmt = (Double) r[8];
			Integer seqData = (Integer) r[9];
			Integer codeSeq = (Integer) r[10];

			WgPrIncDecEmployeeVO ret = new WgPrIncDecEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setFlagPr(flagPr);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setTotalAmt(totalAmt);
			ret.setSeqData(seqData);
			ret.setCodeSeq(codeSeq);

			retList.add(ret);
		}
		return retList;
	}

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from WgPrIncDecOther p, VWgPrEmployeeSecurity e ");
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
			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return true;
	}

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from WgPrIncDecOther p, VWgPrEmployeeSecurity e ");
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
			if (i.intValue() > 0)
				return true;
			else
				return false;
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

		if (incDecCode != null && !incDecCode.equals("")) {
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
		hql.append(" rwOth.flagPr");
		hql.append(" from WgPrIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,WgIncomeDeduct prInc");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VWgPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '1' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.v.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.wgIncomeDeductPK.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.wgIncomeDeductPK.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.wgIncomeDeductPK.incDecCode ");
		hql.append(" order by nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,rwOth.empCode ");

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

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);

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

		if (incDecCode != null && !incDecCode.equals("")) {
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
		hql.append(" rwOth.flagPr");
		hql.append(" from WgPrIncDecOther rwOth , WgEmployee pnEmp , PnOrganization pnOrg ,WgIncomeDeduct prInc");
		hql.append(" where rwOth.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VWgPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and rwOth.groupCode = '2' ");
		hql.append(" and rwOth.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rwOth.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rwOth.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwOth.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rwOth.ouCode = prInc.wgIncomeDeductPK.ouCode ");
		hql.append(" and rwOth.groupCode = prInc.wgIncomeDeductPK.groupCode ");
		hql.append(" and rwOth.incDecCode = prInc.wgIncomeDeductPK.incDecCode ");
		hql.append(" order by nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,rwOth.empCode ");

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

			RwIncDecOtherReportVO ret = new RwIncDecOtherReportVO();
			ret.setIncDecCode(incDec);
			ret.setIncDecName(incDecName);
			ret.setTotalAmt(totalAmt);
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setFlagPr(flagPr);

			retList.add(ret);
		}
		return retList;
	}
}