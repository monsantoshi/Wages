package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.VFeeWgRwVinaiDAO;
import com.ss.tp.dto.VFeeWgRwVinaiVO;

public class VFeeWgRwVinaiDAOImpl extends HibernateDaoSupport implements
		VFeeWgRwVinaiDAO, Serializable {

	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.pk.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.pk.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwV.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();
		hql.append(" select rwV.pk.empCode, rwV.empName,");
		hql.append(" rwV.orgDesc,");
		hql.append(" rwV.newSalary,rwV.cutSal,rwV.absYear1,rwV.absDay1, ");
		hql.append(" rwV.absYear2,rwV.absDay2,rwV.startDateQut,rwV.endDateQut, ");
		hql.append(" rwV.totalDay,rwV.periodWork,rwV.decDay ,rwV.decSal,rwV.decSalMonth,rwV.cutSalPercent");
		hql.append(" from VFeeWgRwVinai rwV ");
		hql.append(" where rwV.pk.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");

		/*
		 * //'hql.append(" from VRwVinai rwV, PnEmployee pnEmp , PnOrganization
		 * pnOrg,VPnOrganizationSecurity v "); hql.append(" where rwV.pk.ouCode
		 * = '"); hql.append(evaOuCode); hql.append("' "); hql.append(" and
		 * pnEmp.pk.empCode in ( "); hql.append(" select pk.empCode ");
		 * hql.append(" from VPrEmployeeSecurityReport "); hql.append(" where
		 * pk.userId = '" + userId + "' "); hql.append(" and pk.ouCode = '" +
		 * evaOuCode + "' "); hql.append(" and pk.year = " + evaYear );
		 * hql.append(" and pk.period = " + evaPeriod ); hql.append(" ) ");
		 * hql.append(criteria); hql.append(" and rwV.ouCode = pnEmp.pk.ouCode
		 * "); hql.append(" and rwV.empCode = pnEmp.pk.empCode "); hql.append("
		 * and rwV.ouCode = pnOrg.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * pnOrg.pk.codeSeq "); hql.append(" and v.pk.userId = '" + userId + "'
		 * "); hql.append(" and rwV.ouCode = v.pk.ouCode "); hql.append(" and
		 * rwV.codeSeq = v.pk.codeSeq "); //hql.append(" and rwPre.creBy = '" +
		 * userId + "' "); hql.append( " order by
		 * nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		 * '||pnOrg.secDesc,rwV.empCode " );
		 */

		/*
		 * hql.append(" and v.pk.userId = '" + userId + "' "); hql.append(" and
		 * rwV.ouCode = v.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * v.pk.codeSeq ");
		 */
		hql.append(" and rwV.pk.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWgPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			System.out.println("**" + it);
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			Double newSalary = (Double) r[3];
			String cutSal = (String) r[4];
			String absYear1 = (String) r[5];
			Double absDay1 = (Double) r[6];
			String absYear2 = (String) r[7];
			Double absDay2 = (Double) r[8];
			Date startDateQut = (Date) r[9];
			Date endDateQut = (Date) r[10];
			Double totalDay = (Double) r[11];
			String periodWork = (String) r[12];
			Double decDay = (Double) r[13];
			Double decSal = (Double) r[14];
			Double decSalMonth = (Double) r[15];
			Double cutSalPercent = (Double) r[16];

			VFeeWgRwVinaiVO ret = new VFeeWgRwVinaiVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setNewSalary(newSalary);
			ret.setCutSal(cutSal);
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
			ret.setCutSalPercent(cutSalPercent);

			retList.add(ret);
		}
		return retList;
	}
	
	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag,String approveFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.pk.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.pk.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwV.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}
		
		
		if (approveFlag != null && !approveFlag.equals("")) {
			criteria.append(" and rwV.approveFlag = '");
			criteria.append(approveFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();
		hql.append(" select rwV.pk.empCode, rwV.empName,");
		hql.append(" rwV.orgDesc,");
		hql.append(" rwV.newSalary,rwV.cutSal,rwV.absYear1,rwV.absDay1, ");
		hql.append(" rwV.absYear2,rwV.absDay2,rwV.startDateQut,rwV.endDateQut, ");
		hql.append(" rwV.totalDay,rwV.periodWork,rwV.decDay ,rwV.decSal,rwV.decSalMonth,rwV.cutSalPercent");
		hql.append(" from VFeeWgRwVinai rwV ");
		hql.append(" where rwV.pk.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");

		/*
		 * //'hql.append(" from VRwVinai rwV, PnEmployee pnEmp , PnOrganization
		 * pnOrg,VPnOrganizationSecurity v "); hql.append(" where rwV.pk.ouCode
		 * = '"); hql.append(evaOuCode); hql.append("' "); hql.append(" and
		 * pnEmp.pk.empCode in ( "); hql.append(" select pk.empCode ");
		 * hql.append(" from VPrEmployeeSecurityReport "); hql.append(" where
		 * pk.userId = '" + userId + "' "); hql.append(" and pk.ouCode = '" +
		 * evaOuCode + "' "); hql.append(" and pk.year = " + evaYear );
		 * hql.append(" and pk.period = " + evaPeriod ); hql.append(" ) ");
		 * hql.append(criteria); hql.append(" and rwV.ouCode = pnEmp.pk.ouCode
		 * "); hql.append(" and rwV.empCode = pnEmp.pk.empCode "); hql.append("
		 * and rwV.ouCode = pnOrg.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * pnOrg.pk.codeSeq "); hql.append(" and v.pk.userId = '" + userId + "'
		 * "); hql.append(" and rwV.ouCode = v.pk.ouCode "); hql.append(" and
		 * rwV.codeSeq = v.pk.codeSeq "); //hql.append(" and rwPre.creBy = '" +
		 * userId + "' "); hql.append( " order by
		 * nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		 * '||pnOrg.secDesc,rwV.empCode " );
		 */

		/*
		 * hql.append(" and v.pk.userId = '" + userId + "' "); hql.append(" and
		 * rwV.ouCode = v.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * v.pk.codeSeq ");
		 */
		hql.append(" and rwV.pk.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWgPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			System.out.println("**" + it);
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			Double newSalary = (Double) r[3];
			String cutSal = (String) r[4];
			String absYear1 = (String) r[5];
			Double absDay1 = (Double) r[6];
			String absYear2 = (String) r[7];
			Double absDay2 = (Double) r[8];
			Date startDateQut = (Date) r[9];
			Date endDateQut = (Date) r[10];
			Double totalDay = (Double) r[11];
			String periodWork = (String) r[12];
			Double decDay = (Double) r[13];
			Double decSal = (Double) r[14];
			Double decSalMonth = (Double) r[15];
			Double cutSalPercent = (Double) r[16];

			VFeeWgRwVinaiVO ret = new VFeeWgRwVinaiVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setNewSalary(newSalary);
			ret.setCutSal(cutSal);
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
			ret.setCutSalPercent(cutSalPercent);

			retList.add(ret);
		}
		return retList;
	}
	
	
}
