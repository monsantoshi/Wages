/*
 * Created on 18 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgPrWorkDayDAO;

import com.ss.tp.dto.WgPrWorkDayVO;

import com.ss.tp.model.SuOrganize;
import com.ss.tp.model.WgPrWorkDay;
import com.ss.tp.model.WgPrWorkDayPK;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPrWorkDayDAOImpl extends HibernateDaoSupport implements
		WgPrWorkDayDAO {
	List wdList = new ArrayList();

	public List addByCriteria(String ouCode, String userId, int year,
			int period, String areaCode, String secCode, String workCode,
			int pageNo, int maxRowPerPage) throws Exception {
		List ls = new ArrayList();
		try {
			/* hql */
			StringBuffer hqlEmp = new StringBuffer(0);
			StringBuffer criteria = new StringBuffer(0);

			criteria.append(" and pno.areaCode = '" + areaCode + "' ");
			if (!"".equals(secCode)) {
				criteria.append(" and pno.secCode = '" + secCode + "' ");
			}
			if (!"".equals(workCode)) {
				criteria.append(" and pno.workCode = '" + workCode + "' ");
			}
			hqlEmp.append(" select emp.wgEmployeePK.empCode,pno.orgCode, emp.refDbPreSuff.prefixName, ");
			hqlEmp.append(" emp.firstName, emp.lastName, a.wageAmt, a.workDay, a.workAmt, pre.codeSeqWork ");
			hqlEmp.append(" from  WgPrWorkDay a, WgPrEmployee pre, WgEmployee emp , PnOrganization pno ");
			hqlEmp.append(" where pre.wgPrEmployeePK.ouCode = '" + ouCode
					+ "' ");
			hqlEmp.append(" and pre.wgPrEmployeePK.year = '" + year + "' ");
			hqlEmp.append(" and pre.wgPrEmployeePK.period = '" + period + "' ");
			hqlEmp.append(criteria);
			hqlEmp.append(" and pre.wgPrEmployeePK.empCode in ( ");
			hqlEmp.append(" 	select v.pk.empCode ");
			hqlEmp.append(" 	from VWgPrEmployeeSecurity v ");
			hqlEmp.append(" 	where v.pk.ouCode = '" + ouCode + "' ");
			hqlEmp.append(" 	and v.pk.userId = '" + userId + "' ");
			hqlEmp.append("    	and v.pk.year = " + year);
			hqlEmp.append("		and v.pk.period = " + period);
			hqlEmp.append(" ) ");
			hqlEmp.append(" and a.wgPrWorkDayPK.ouCode = pre.wgPrEmployeePK.ouCode ");
			hqlEmp.append(" and a.wgPrWorkDayPK.year = pre.wgPrEmployeePK.year ");
			hqlEmp.append(" and a.wgPrWorkDayPK.period = pre.wgPrEmployeePK.period ");
			hqlEmp.append(" and a.wgPrWorkDayPK.empCode = pre.wgPrEmployeePK.empCode ");
			hqlEmp.append(" and pre.wgPrEmployeePK.empCode = emp.wgEmployeePK.empCode ");
			hqlEmp.append(" and pre.wgPrEmployeePK.ouCode = emp.wgEmployeePK.ouCode ");
			hqlEmp.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
			hqlEmp.append(" and pre.wgPrEmployeePK.ouCode = pno.pk.ouCode ");
			hqlEmp.append(" order by  pno.orgCode,emp.wgEmployeePK.empCode ");
			/*
			 * String sql = ""; sql+= " select b.wgEmployeePK.empCode,
			 * b.refPnOrganization.orgCode, b.refDbPreSuff.prefixName,
			 * b.firstName, b.lastName, a.wageAmt, a.workDay, a.workAmt,
			 * b.codeSeq " ; sql+=" from WgPrWorkDay a, WgEmployee b "; sql+="
			 * where a.wgPrWorkDayPK.ouCode = '"+ouCode+"' "; sql+=" and
			 * a.wgPrWorkDayPK.year = "+year; sql+=" and a.wgPrWorkDayPK.period
			 * = "+period; sql+=" and b.refPnOrganization.pk.ouCode = '" +
			 * ouCode + "'"; sql+=" and b.refPnOrganization.areaCode = '" +
			 * areaCode + "'"; if(!"".equals(secCode)){ sql+=" and
			 * b.refPnOrganization.secCode = '" + secCode + "'"; } if
			 * (!"".equals(workCode)){
			 * sql+=" and b.refPnOrganization.workCode = '" + workCode + "'"; }
			 * sql+=" and a.wgPrWorkDayPK.ouCode = b.wgEmployeePK.ouCode
			 * "; sql+=" and a.wgPrWorkDayPK.empCode = b.wgEmployeePK.empCode
			 * "; sql +=" order by b.refPnOrganization.orgCode,
			 * b.wgEmployeePK.empCode " ;
			 */
			Session sss = this.getSession();
			Query qry = sss.createQuery(hqlEmp.toString());
			qry.setMaxResults(maxRowPerPage);
			qry.setFirstResult(pageNo * maxRowPerPage);
			List tmp = qry.list();
			WgPrWorkDayVO wd = null;
			for (Iterator itt = tmp.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				wd = new WgPrWorkDayVO();
				wd.setEmpCode(obj[0] != null ? obj[0].toString() : null);
				wd.setPrefixName(obj[2] != null ? obj[2].toString() : null);
				wd.setFirstName(obj[3] != null ? obj[3].toString() : null);
				wd.setLastName(obj[4] != null ? obj[4].toString() : null);
				wd.setWageAmt(obj[5] != null ? new Double(obj[5].toString())
						: null);
				wd.setWorkDay(obj[6] != null ? new Double(obj[6].toString())
						: null);
				wd.setWorkAmt(obj[7] != null ? new Double(obj[7].toString())
						: null);
				ls.add(wd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ls;
	}

	// ===================Create By Kiet===================

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

	public List findWgPrWorkDayReport(String ouCode, String userId, int year,
			int period) throws Exception {
		WgPrWorkDayVO wgPrWorkDayvo;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		StringBuffer hql = new StringBuffer(0);

		hql.append(" SELECT ");
		hql.append("	w.wgPrWorkDayPK.empCode, e.refDbPreSuff.prefixName, e.firstName, e.lastName, w.wageAmt, w.workDay, w.workAmt, w.wgPrWorkDayPK.year, w.wgPrWorkDayPK.period, "
				+ "	e.refPnOrganization.areaCode, e.refPnOrganization.areaDesc, "
				+ "	e.refPnOrganization.secCode, e.refPnOrganization.secDesc ");
		hql.append(" FROM WgPrWorkDay w, WgEmployee e ");
		hql.append(" WHERE ");

		// ============================ Security ===============================
		hql.append(" w.wgPrWorkDayPK.empCode in (" + "		SELECT pk.empCode "
				+ "		FROM VWgPrEmployeeSecurity " + "		WHERE pk.ouCode = '"
				+ ouCode + "' " + "		AND pk.userId = '" + userId + "' "
				+ "		AND pk.year = " + year + "		AND pk.period = " + period
				+ " ) ");
		// =====================================================================

		hql.append(" AND e.wgEmployeePK.ouCode = w.wgPrWorkDayPK.ouCode "
				+ " AND e.wgEmployeePK.empCode = w.wgPrWorkDayPK.empCode "
				+ " AND e.codeSeq = w.codeSeqWork ");

		hql.append(" AND w.wgPrWorkDayPK.ouCode = '" + ouCode + "' ");

		if (year != 0) {
			hql.append(" AND w.wgPrWorkDayPK.year = " + year);
		}

		if (period != 0) {
			hql.append(" AND w.wgPrWorkDayPK.period = " + period);
		}

		hql.append(" ORDER BY w.wgPrWorkDayPK.empCode ");

		try {
			Session ss = this.getSession();
			Query qq = ss.createQuery(hql.toString());
			listDetail = qq.list();
			Map obj = null;

			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				Object ob[] = (Object[]) itt.next();

				wgPrWorkDayvo = new WgPrWorkDayVO();

				wgPrWorkDayvo.setEmpCode((String) ob[0]);
				wgPrWorkDayvo.setPrefixName((String) ob[1]);
				wgPrWorkDayvo.setFirstName((String) ob[2]);
				wgPrWorkDayvo.setLastName((String) ob[3]);
				wgPrWorkDayvo.setWageAmt((Double) ob[4]);
				wgPrWorkDayvo.setWorkDay((Double) ob[5]);
				wgPrWorkDayvo.setWorkAmt((Double) ob[6]);
				wgPrWorkDayvo.setYear((Integer) ob[7]);
				wgPrWorkDayvo.setPeriod((Integer) ob[8]);
				wgPrWorkDayvo.setAreaCode((String) ob[9]);
				wgPrWorkDayvo.setAreaDesc((String) ob[10]);
				wgPrWorkDayvo.setSecCode((String) ob[11]);
				wgPrWorkDayvo.setSecDesc((String) ob[12]);

				list.add(wgPrWorkDayvo);

			}
			System.out.println("\n size List is ==" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	// =======================================================
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.tp.dao.WgPrWorkDayDAO#countByCriteria(java.lang.String, int,
	 * int, java.lang.String, int, int)
	 */
	// XXX
	public int countByCriteria(String ouCode, int year, int period,
			String areaCode, String secCode, String workCode) throws Exception {
		System.out.println("ouCode " + ouCode);
		System.out.println("year " + year);
		System.out.println("areaCode " + areaCode);
		System.out.println("secCode " + secCode);
		System.out.println("workCode " + workCode);
		int rlstInt = 0;
		try {
			String sql = "";
			/* hql */
			sql += " from WgPrWorkDay a, WgEmployee b ";
			sql += " where 1=1 ";
			sql += " and a.wgPrWorkDayPK.ouCode = '" + ouCode + "' ";
			sql += " and a.wgPrWorkDayPK.year = " + year;
			sql += " and a.wgPrWorkDayPK.period = " + period;
			sql += " and b.refPnOrganization.pk.ouCode = '" + ouCode + "'";
			if (!"".equals(areaCode)) {
				sql += " and b.refPnOrganization.areaCode = '" + areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and b.refPnOrganization.secCode = '" + secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and b.refPnOrganization.workCode = '" + workCode + "'";
			}
			sql += " and a.wgPrWorkDayPK.empCode = b.wgEmployeePK.empCode ";
			sql += " order by b.refPnOrganization.orgCode, b.wgEmployeePK.empCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			rlstInt = tmpList.size();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlstInt;
	}

	public void addList(WgPrWorkDayVO wgPrWorkDayVO) {
		this.wdList.add(wgPrWorkDayVO);
	}

	public void deleteWgPrWorkDay(WgPrWorkDayVO wgPrWorkDayVO) throws Exception {
		WgPrWorkDay wd = new WgPrWorkDay();
		WgPrWorkDayPK wdpk = new WgPrWorkDayPK();
		String sql = "";
		List tmpLst = new ArrayList();
		try {
			sql = " from WgPrWorkDay a where 1=1 " + " and ou_Code='"
					+ wgPrWorkDayVO.getOuCode() + "' " + " and year="
					+ wgPrWorkDayVO.getYear() + " " + " and period="
					+ wgPrWorkDayVO.getPeriod() + " " + " and emp_Code='"
					+ wgPrWorkDayVO.getEmpCode() + "' ";
			WgPrWorkDay wdc = new WgPrWorkDay();
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				wdc = (WgPrWorkDay) itt.next();
				this.getHibernateTemplate().delete(wdc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWgPrWorkDayList() throws Exception {
		try {
			for (int i = 0; i < this.wdList.size(); i++) {
				WgPrWorkDayVO wgPrWdVO = (WgPrWorkDayVO) this.wdList.get(i);
				this.deleteWgPrWorkDay(wgPrWdVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void clearList() {
		this.wdList = new ArrayList();
	}

	public void addOrUpdateWgPrWorkDay(String ouCode, int year, int period,
			List arrEmpCode, List arrCodeSeq, List arrWorkDay, List arrWorkAmt,
			List arrWageAmt, String creby) {
		int size = arrEmpCode.size();
		Object objEmpCode = null;
		Object objCodeSeq = null;
		Object objWorkDay = null;
		Object objWorkAmt = null;
		Object objWageAmt = null;
		WgPrWorkDay wgWD = null;
		WgPrWorkDayPK wgPrWorkDayPk = null;
		for (int i = 0; i < size; i++) {
			objEmpCode = arrEmpCode.get(i);
			objCodeSeq = arrCodeSeq.get(i);
			objWorkDay = arrWorkDay.get(i);
			objWorkAmt = arrWorkAmt.get(i);
			objWageAmt = arrWageAmt.get(i);
			if (!"".equals(objEmpCode) && !"".equals(objCodeSeq)
					&& !"".equals(objWorkDay) && !"0".equals(objWorkDay)
					&& !"".equals(objWorkAmt) && !"".equals(objWageAmt)) {
				wgWD = new WgPrWorkDay();
				wgPrWorkDayPk = new WgPrWorkDayPK();
				wgPrWorkDayPk.setOuCode(ouCode);
				wgPrWorkDayPk.setYear(new Integer(year));
				wgPrWorkDayPk.setPeriod(new Integer(period));
				wgPrWorkDayPk.setEmpCode(arrEmpCode.get(i).toString());
				wgWD.setWgPrWorkDayPK(wgPrWorkDayPk);
				wgWD.setCreBy(creby);
				wgWD.setCreDate(new Date());
				wgWD.setCodeSeqWork(new Integer(arrCodeSeq.get(i).toString()));
				wgWD.setWorkDay(new Double(arrWorkDay.get(i).toString()));
				wgWD.setWorkAmt(new Double(arrWorkAmt.get(i).toString()));
				wgWD.setWageAmt(new Double(arrWageAmt.get(i).toString()));
				wgWD.setConfirmFlag("N");
				this.getHibernateTemplate().saveOrUpdate(wgWD);
				System.out.println("\nSaved....");
			}
			if ("0".equals(objWorkDay)) {
				wgWD = new WgPrWorkDay();
				wgPrWorkDayPk = new WgPrWorkDayPK();
				wgPrWorkDayPk.setOuCode(ouCode);
				wgPrWorkDayPk.setYear(new Integer(year));
				wgPrWorkDayPk.setPeriod(new Integer(period));
				wgPrWorkDayPk.setEmpCode(arrEmpCode.get(i).toString());
				wgWD.setWgPrWorkDayPK(wgPrWorkDayPk);
				this.getHibernateTemplate().delete(wgWD);
				System.out.println("\nDeleted....");
			}
		}
	}

	public Integer countConfirmFlag(String ouCode, String userId, int year,
			int period) {
		List tmp = new ArrayList();
		try {
			String sql = "";
			sql += " from WgPrWorkDay a, WgEmployee b ";
			sql += " where a.wgPrWorkDayPK.ouCode = '" + ouCode + "' ";
			sql += " and a.wgPrWorkDayPK.year = " + year;
			sql += " and a.wgPrWorkDayPK.period = " + period;
			sql += " and a.confirmFlag = 'Y' ";
			sql += " and b.codeSeq in ( " + " select vpn.pk.codeSeq "
					+ " from VPnOrganizationSecurity vpn "
					+ " where vpn.pk.ouCode = '" + ouCode + "' "
					+ " and vpn.pk.userId ='" + userId + "'";
			sql += " ) ";
			sql += " and a.wgPrWorkDayPK.ouCode = b.wgEmployeePK.ouCode ";
			sql += " and a.wgPrWorkDayPK.empCode = b.wgEmployeePK.empCode ";
			Query q = this.getSession().createQuery(sql);
			tmp = q.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Integer(tmp.size());
	}

	public List findWgEmployeeAddOrUpdatePage(String ouCode, String userId,
			int year, int period, String areaCode, String secCode,
			String workCode) {
		try {
			StringBuffer hqlEmp = new StringBuffer(0);
			StringBuffer criteria = new StringBuffer(0);

			criteria.append(" and pno.areaCode = '" + areaCode + "' ");
			if (!"".equals(secCode)) {
				criteria.append(" and pno.secCode = '" + secCode + "' ");
			}
			if (!"".equals(workCode)) {
				criteria.append(" and pno.workCode = '" + workCode + "' ");
			}
			hqlEmp.append(" select emp.wgEmployeePK.empCode, emp.refDbPreSuff.prefixName, ");
			hqlEmp.append(" emp.firstName, emp.lastName,pre.newSalary, emp.codeSeq, pre.confirmFlag ");
			hqlEmp.append(" from  WgPrEmployee pre, WgEmployee emp , PnOrganization pno ");
			hqlEmp.append(" where pre.wgPrEmployeePK.ouCode = '" + ouCode
					+ "' ");
			hqlEmp.append(" and pre.wgPrEmployeePK.year = '" + year + "' ");
			hqlEmp.append(" and pre.wgPrEmployeePK.period = '" + period + "' ");
			hqlEmp.append(criteria);
			hqlEmp.append(" and pre.wgPrEmployeePK.empCode in ( ");
			hqlEmp.append(" 	select v.pk.empCode ");
			hqlEmp.append(" 	from VWgPrEmployeeSecurity v ");
			hqlEmp.append(" 	where v.pk.ouCode = '" + ouCode + "' ");
			hqlEmp.append(" 	and v.pk.userId = '" + userId + "' ");
			hqlEmp.append("    	and v.pk.year = " + year);
			hqlEmp.append("		and v.pk.period = " + period);
			hqlEmp.append(" ) ");
			hqlEmp.append(" and pre.wgPrEmployeePK.empCode = emp.wgEmployeePK.empCode ");
			hqlEmp.append(" and pre.wgPrEmployeePK.ouCode = emp.wgEmployeePK.ouCode ");
			hqlEmp.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
			hqlEmp.append(" and pre.wgPrEmployeePK.ouCode = pno.pk.ouCode ");
			hqlEmp.append(" order by  pno.orgCode,emp.wgEmployeePK.empCode ");

			List empList = this.getHibernateTemplate().find(hqlEmp.toString());
			for (int i = 0; i < empList.size(); i++) {
				Object[] r = (Object[]) empList.get(i);
				String empCode = (String) r[0];
				String prefixName = (String) r[1];
				String firstName = (String) r[2];
				String lastName = (String) r[3];
				Double newSalary = (Double) r[4];
				Integer codeSeqWork = (Integer) r[5];
				String confirmFlag = (String) r[6];
				Double wageAmt = new Double(0);
				Double workDay = new Double(0);
				Double workAmt = new Double(0);

				StringBuffer hqlWorkDay = new StringBuffer(0);
				hqlWorkDay.append(" select a.wageAmt, a.workDay, a.workAmt ");
				hqlWorkDay.append(" from WgPrWorkDay a ");
				hqlWorkDay.append(" where a.wgPrWorkDayPK.ouCode = '" + ouCode
						+ "' ");
				hqlWorkDay.append(" and   a.wgPrWorkDayPK.year = '" + year
						+ "' ");
				hqlWorkDay.append(" and   a.wgPrWorkDayPK.period = '" + period
						+ "' ");
				hqlWorkDay.append(" and   a.wgPrWorkDayPK.empCode = '"
						+ empCode + "' ");
				List workDayList = this.getHibernateTemplate().find(
						hqlWorkDay.toString());
				if (workDayList.size() > 0) {
					Object[] r2 = (Object[]) workDayList.get(0);
					wageAmt = (Double) r2[0];
					workDay = (Double) r2[1];
					workAmt = (Double) r2[2];
				}

				WgPrWorkDayVO vo = new WgPrWorkDayVO();
				vo.setEmpCode(empCode);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setNewSalary(newSalary);
				vo.setCodeSeqWork(codeSeqWork);
				vo.setConfirmFlag(confirmFlag);
				vo.setWageAmt(wageAmt);
				vo.setWorkDay(workDay);
				vo.setWorkAmt(workAmt);
				empList.set(i, vo);
			}
			return empList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
