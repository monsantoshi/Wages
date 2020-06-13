package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgIncomeDeductDAO;
import com.ss.tp.dto.WgPrIncDecOtherVO;
import com.ss.tp.model.WgIncomeDeduct;
import com.ss.tp.model.WgIncomeDeductPK;

public class WgIncomeDeductDAOImpl extends HibernateDaoSupport implements
		WgIncomeDeductDAO, Serializable {

	public void insertWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		WgIncomeDeduct wg = new WgIncomeDeduct();
		WgIncomeDeductPK wgpk = new WgIncomeDeductPK();
		try {
			BeanUtils.copyProperties(wgpk, wgincomevo);
			wg.setWgIncomeDeductPK(wgpk);
			BeanUtils.copyProperties(wg, wgincomevo);
			wg.setCreDate(new Date());
			wg.setUpdDate(new Date());
			this.getHibernateTemplate().save(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void updateWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		WgIncomeDeduct wg = new WgIncomeDeduct();
		WgIncomeDeductPK wgpk = new WgIncomeDeductPK();
		try {
			BeanUtils.copyProperties(wgpk, wgincomevo);
			wg.setWgIncomeDeductPK(wgpk);
			BeanUtils.copyProperties(wg, wgincomevo);
			wg.setUpdDate(new Date());
			this.getHibernateTemplate().save(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void deleteWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		WgIncomeDeduct wg = new WgIncomeDeduct();
		WgIncomeDeductPK wgpk = new WgIncomeDeductPK();
		try {
			BeanUtils.copyProperties(wgpk, wgincomevo);
			wg.setWgIncomeDeductPK(wgpk);
			BeanUtils.copyProperties(wg, wgincomevo);
			this.getHibernateTemplate().delete(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void insertWgIncomeDeducts(List wgincomevolist) throws Exception {
		WgIncomeDeduct wg = new WgIncomeDeduct();
		WgIncomeDeductPK wgpk = new WgIncomeDeductPK();
		try {
			for (int i = 0; i < wgincomevolist.size(); i++) {
				WgPrIncDecOtherVO princomevo = (WgPrIncDecOtherVO) wgincomevolist
						.get(i);

				BeanUtils.copyProperties(wgpk, princomevo);
				wg.setWgIncomeDeductPK(wgpk);
				BeanUtils.copyProperties(wg, princomevo);
				wg.setCreDate(new Date());
				wg.setUpdDate(new Date());
				this.getHibernateTemplate().save(wg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from WgIncomeDeduct wg ");
		sql.append(" where wg.wgIncomeDeductPK.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and wg.wgIncomeDeductPK.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and wg.inactive = 'N' ");
		sql.append(" order by wg.wgIncomeDeductPK.incDecCode ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			WgPrIncDecOtherVO wgIncVo = new WgPrIncDecOtherVO();
			try {
				WgIncomeDeduct wgInc = (WgIncomeDeduct) ls.get(i);
				BeanUtils.copyProperties(wgIncVo, wgInc);
				BeanUtils.copyProperties(wgIncVo, wgInc.getWgIncomeDeductPK());

				ls.set(i, wgIncVo);

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}

	public String getIncDecName(String ouCode, int groupCode, String incDecCode) {
		String incDecName = "";
		StringBuffer sql = new StringBuffer(0);

		sql.append(" select wg.incDecName ");
		sql.append(" from WgIncomeDeduct wg ");
		sql.append(" where wg.wgIncomeDeductPK.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and wg.wgIncomeDeductPK.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and wg.wgIncomeDeductPK.incDecCode = '");
		sql.append(incDecCode);
		sql.append("' ");
		sql.append(" and wg.inactive = 'N' ");

		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls.size() > 0)
			incDecName = (String) ls.get(0);
		System.out.println("Name ==>" + incDecName);

		return incDecName;
	}

	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {

		List retList = new ArrayList();
		try {
			StringBuffer criteria = new StringBuffer();

			if (evaYear != null && !evaYear.equals("")) {
				criteria.append(" and wgOth.yearPr = ");
				criteria.append(evaYear);
			}

			if (evaPeriod != null && !evaPeriod.equals("")) {
				criteria.append(" and wgOth.periodPr = ");
				criteria.append(evaPeriod);
			}

			if (incDecCode != null && !incDecCode.equals("%")) {
				criteria.append(" and wgOth.incDecCode = '");
				criteria.append(incDecCode);
				criteria.append("'");
			}

			if (evaFlag != null && !evaFlag.equals("")) {
				criteria.append(" and wgOth.flagPr like '");
				criteria.append(evaFlag);
				criteria.append("'");
			}

			StringBuffer hql = new StringBuffer();

			hql.append(" select wgOth.incDecCode,wgInc.incDecName,wgOth.totalAmt,");
			hql.append(" wgOth.empCode, wgEmp.refDbPreSuff.prefixName||' '||wgEmp.firstName||' '||wgEmp.lastName,");
			hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
			hql.append(" wgOth.flagPr");
			hql.append(" from WgPrIncDecOther wgOth , WgEmployee wgEmp , PnOrganization pnOrg ,WgIncomeDeduct wgInc");

			hql.append(" where wgOth.ouCode = '");
			hql.append(evaOuCode);
			hql.append("' ");
			hql.append(" and wgEmp.wgEmployeePK.empCode in ( ");
			hql.append(" 	select pk.empCode ");
			hql.append(" 	from VWgPrEmployeeSecurity ");
			hql.append(" 	where pk.userId = '" + userId + "' ");
			hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
			hql.append(" 	and pk.year = " + evaYear);
			hql.append(" 	and pk.period = " + evaPeriod);
			hql.append(" ) ");
			hql.append(criteria);
			hql.append(" and wgOth.groupCode = '1' ");
			hql.append(" and wgOth.ouCode = wgEmp.wgEmployeePK.ouCode ");
			hql.append(" and wgOth.empCode = wgEmp.wgEmployeePK.empCode ");
			hql.append(" and wgOth.ouCode = pnOrg.pk.ouCode ");
			hql.append(" and wgOth.codeSeq = pnOrg.pk.codeSeq ");
			hql.append(" and wgOth.ouCode = wgInc.wgIncomeDeductPK.ouCode ");
			hql.append(" and wgOth.groupCode = wgInc.wgIncomeDeductPK.groupCode ");
			hql.append(" and wgOth.incDecCode = wgInc.wgIncomeDeductPK.incDecCode ");
			// hql.append(" order by
			// wgOth.incDecCode,nvl(pnOrg.secCode,pnOrg.divCode)||'
			// '||pnOrg.divDesc||' '||pnOrg.secDesc,wgOth.empCode ");
			hql.append(" order by wgOth.incDecCode,nvl(nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,'$'),pnOrg.divCode,wgOth.empCode");
			System.out.println("HQL findByCriteria ==> " + hql.toString());

			List empList = this.getSession().createQuery(hql.toString()).list();

			for (Iterator it = empList.iterator(); it.hasNext();) {
				Object[] r = (Object[]) it.next();
				String incDec = (String) r[0];
				String incDecName = (String) r[1];
				Double totalAmt = (Double) r[2];
				String empCode = (String) r[3];
				String empName = (String) r[4];
				String orgDesc = (String) r[5];
				String flagPr = (String) r[6];

				WgPrIncDecOtherVO ret = new WgPrIncDecOtherVO();
				ret.setIncDecCode(incDec);
				ret.setIncDecName(incDecName);
				ret.setTotalAmt(totalAmt);
				ret.setEmpCode(empCode);
				ret.setEmpName(empName);
				ret.setOrgDesc(orgDesc);
				ret.setFlagPr(flagPr);

				retList.add(ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {

		List retList = new ArrayList();
		try {
			StringBuffer criteria = new StringBuffer();

			if (evaYear != null && !evaYear.equals("")) {
				criteria.append(" and wgOth.yearPr = ");
				criteria.append(evaYear);
			}

			if (evaPeriod != null && !evaPeriod.equals("")) {
				criteria.append(" and wgOth.periodPr = ");
				criteria.append(evaPeriod);
			}

			if (incDecCode != null && !incDecCode.equals("%")) {
				criteria.append(" and wgOth.incDecCode = '");
				criteria.append(incDecCode);
				criteria.append("'");
			}

			if (evaFlag != null && !evaFlag.equals("")) {
				criteria.append(" and wgOth.flagPr like '");
				criteria.append(evaFlag);
				criteria.append("'");
			}

			StringBuffer hql = new StringBuffer();

			hql.append(" select distinct(wgOth.incDecCode),wgInc.incDecName");
			hql.append(" from WgPrIncDecOther wgOth , WgEmployee wgEmp , PnOrganization pnOrg ,WgIncomeDeduct wgInc");

			hql.append(" where wgOth.ouCode = '");
			hql.append(evaOuCode);
			hql.append("' ");
			hql.append(" and wgEmp.wgEmployeePK.empCode in ( ");
			hql.append(" 	select pk.empCode ");
			hql.append(" 	from VWgPrEmployeeSecurity ");
			hql.append(" 	where pk.userId = '" + userId + "' ");
			hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
			hql.append(" 	and pk.year = " + evaYear);
			hql.append(" 	and pk.period = " + evaPeriod);
			hql.append(" ) ");
			hql.append(criteria);
			hql.append(" and wgOth.groupCode = '1' ");
			hql.append(" and wgOth.ouCode = wgEmp.wgEmployeePK.ouCode ");
			hql.append(" and wgOth.empCode = wgEmp.wgEmployeePK.empCode ");
			hql.append(" and wgOth.ouCode = pnOrg.pk.ouCode ");
			hql.append(" and wgOth.codeSeq = pnOrg.pk.codeSeq ");
			hql.append(" and wgOth.ouCode = wgInc.wgIncomeDeductPK.ouCode ");
			hql.append(" and wgOth.groupCode = wgInc.wgIncomeDeductPK.groupCode ");
			hql.append(" and wgOth.incDecCode = wgInc.wgIncomeDeductPK.incDecCode ");
			hql.append(" order by wgOth.incDecCode ");

			System.out.println("HQL findByCriteria ==> " + hql.toString());

			List empList = this.getSession().createQuery(hql.toString()).list();

			for (Iterator it = empList.iterator(); it.hasNext();) {
				Object[] r = (Object[]) it.next();
				String incDec = (String) r[0];
				String incDecName = (String) r[1];

				WgPrIncDecOtherVO ret = new WgPrIncDecOtherVO();
				ret.setIncDecCode(incDec);
				ret.setIncDecName(incDecName);
				retList.add(ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	public List decDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {

		List retList = new ArrayList();
		try {
			StringBuffer criteria = new StringBuffer();

			if (evaYear != null && !evaYear.equals("")) {
				criteria.append(" and wgOth.yearPr = ");
				criteria.append(evaYear);
			}

			if (evaPeriod != null && !evaPeriod.equals("")) {
				criteria.append(" and wgOth.periodPr = ");
				criteria.append(evaPeriod);
			}

			if (incDecCode != null && !incDecCode.equals("%")) {
				criteria.append(" and wgOth.incDecCode = '");
				criteria.append(incDecCode);
				criteria.append("'");
			}

			if (evaFlag != null && !evaFlag.equals("")) {
				criteria.append(" and wgOth.flagPr like '");
				criteria.append(evaFlag);
				criteria.append("'");
			}

			StringBuffer hql = new StringBuffer();

			hql.append(" select wgOth.incDecCode,wgInc.incDecName,wgOth.totalAmt,");
			hql.append(" wgOth.empCode, wgEmp.refDbPreSuff.prefixName||' '||wgEmp.firstName||' '||wgEmp.lastName,");
			hql.append(" nvl(pnOrg.secCode,pnOrg.areaCode)||' '||pnOrg.areaDesc||' '||pnOrg.secDesc,");
			hql.append(" wgOth.flagPr");
			hql.append(" from WgPrIncDecOther wgOth , WgEmployee wgEmp , PnOrganization pnOrg ,WgIncomeDeduct wgInc");

			hql.append(" where wgOth.ouCode = '");
			hql.append(evaOuCode);
			hql.append("' ");
			hql.append(" and wgEmp.wgEmployeePK.empCode in ( ");
			hql.append(" 	select pk.empCode ");
			hql.append(" 	from VWgPrEmployeeSecurity ");
			hql.append(" 	where pk.userId = '" + userId + "' ");
			hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
			hql.append(" 	and pk.year = " + evaYear);
			hql.append(" 	and pk.period = " + evaPeriod);
			hql.append(" ) ");
			hql.append(criteria);
			hql.append(" and wgOth.groupCode = '2' ");
			hql.append(" and wgOth.ouCode = wgEmp.wgEmployeePK.ouCode ");
			hql.append(" and wgOth.empCode = wgEmp.wgEmployeePK.empCode ");
			hql.append(" and wgOth.ouCode = pnOrg.pk.ouCode ");
			hql.append(" and wgOth.codeSeq = pnOrg.pk.codeSeq ");
			hql.append(" and wgOth.ouCode = wgInc.wgIncomeDeductPK.ouCode ");
			hql.append(" and wgOth.groupCode = wgInc.wgIncomeDeductPK.groupCode ");
			hql.append(" and wgOth.incDecCode = wgInc.wgIncomeDeductPK.incDecCode ");
			hql.append(" order by wgOth.incDecCode,nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||' '||pnOrg.secDesc,wgOth.empCode ");

			System.out.println("HQL findByCriteria ==> " + hql.toString());

			List empList = this.getSession().createQuery(hql.toString()).list();

			for (Iterator it = empList.iterator(); it.hasNext();) {
				Object[] r = (Object[]) it.next();
				String incDec = (String) r[0];
				String incDecName = (String) r[1];
				Double totalAmt = (Double) r[2];
				String empCode = (String) r[3];
				String empName = (String) r[4];
				String orgDesc = (String) r[5];
				String flagPr = (String) r[6];

				WgPrIncDecOtherVO ret = new WgPrIncDecOtherVO();
				ret.setIncDecCode(incDec);
				ret.setIncDecName(incDecName);
				ret.setTotalAmt(totalAmt);
				ret.setEmpCode(empCode);
				ret.setEmpName(empName);
				ret.setOrgDesc(orgDesc);
				ret.setFlagPr(flagPr);

				retList.add(ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	public List decDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {

		List retList = new ArrayList();
		try {
			StringBuffer criteria = new StringBuffer();

			if (evaYear != null && !evaYear.equals("")) {
				criteria.append(" and wgOth.yearPr = ");
				criteria.append(evaYear);
			}

			if (evaPeriod != null && !evaPeriod.equals("")) {
				criteria.append(" and wgOth.periodPr = ");
				criteria.append(evaPeriod);
			}

			if (incDecCode != null && !incDecCode.equals("%")) {
				criteria.append(" and wgOth.incDecCode = '");
				criteria.append(incDecCode);
				criteria.append("'");
			}

			if (evaFlag != null && !evaFlag.equals("")) {
				criteria.append(" and wgOth.flagPr like '");
				criteria.append(evaFlag);
				criteria.append("'");
			}

			StringBuffer hql = new StringBuffer();

			hql.append(" select distinct(wgOth.incDecCode),wgInc.incDecName ");

			hql.append(" from WgPrIncDecOther wgOth , WgEmployee wgEmp , PnOrganization pnOrg ,WgIncomeDeduct wgInc");

			hql.append(" where wgOth.ouCode = '");
			hql.append(evaOuCode);
			hql.append("' ");
			hql.append(" and wgEmp.wgEmployeePK.empCode in ( ");
			hql.append(" 	select pk.empCode ");
			hql.append(" 	from VWgPrEmployeeSecurity ");
			hql.append(" 	where pk.userId = '" + userId + "' ");
			hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
			hql.append(" 	and pk.year = " + evaYear);
			hql.append(" 	and pk.period = " + evaPeriod);
			hql.append(" ) ");
			hql.append(criteria);
			hql.append(" and wgOth.groupCode = '2' ");
			hql.append(" and wgOth.ouCode = wgEmp.wgEmployeePK.ouCode ");
			hql.append(" and wgOth.empCode = wgEmp.wgEmployeePK.empCode ");
			hql.append(" and wgOth.ouCode = pnOrg.pk.ouCode ");
			hql.append(" and wgOth.codeSeq = pnOrg.pk.codeSeq ");
			hql.append(" and wgOth.ouCode = wgInc.wgIncomeDeductPK.ouCode ");
			hql.append(" and wgOth.groupCode = wgInc.wgIncomeDeductPK.groupCode ");
			hql.append(" and wgOth.incDecCode = wgInc.wgIncomeDeductPK.incDecCode ");
			hql.append(" order by wgOth.incDecCode  ");

			System.out.println("HQL findByCriteria ==> " + hql.toString());

			List empList = this.getSession().createQuery(hql.toString()).list();

			for (Iterator it = empList.iterator(); it.hasNext();) {
				Object[] r = (Object[]) it.next();
				String incDec = (String) r[0];
				String incDecName = (String) r[1];

				WgPrIncDecOtherVO ret = new WgPrIncDecOtherVO();
				ret.setIncDecCode(incDec);
				ret.setIncDecName(incDecName);
				retList.add(ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}
}
