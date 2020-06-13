/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeWgEmpScoreReportDAO;
import com.ss.tp.dto.PeWgEmpScoreReportVO;
import com.ss.tp.model.PnEmployee;
import com.ss.tp.model.PnEmployeePK;
import com.ss.tp.model.SuOrganize;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PeWgEmpScoreReportDAOImpl extends HibernateDaoSupport implements
		PeWgEmpScoreReportDAO, Serializable {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

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

	public List findPeWgEmpScoreReport(String ouCode, String userId,
			Integer evaYear, Integer evaTime, String areaCode, String secCode,
			String workCode) {
		PeWgEmpScoreReportVO peWgEmpScoreVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		StringBuffer sql = new StringBuffer(0);
		String ename = "NullVal";

		sql.append(" SELECT ");
		sql.append(" 	c.pk.evaYear, c.pk.evaTime, c.pk.empCode, "
				+ " 	c.emp.refDbPreSuff.prefixName || ' ' || c.emp.firstName || ' ' || c.emp.lastName, "
				+ "	NVL(c.emp.refPnOrganization.areaCode,c.emp.refPnOrganization.divCode), "
				+ "	NVL(c.emp.refPnOrganization.areaDesc,c.emp.refPnOrganization.divDesc) , "
				+ " 	c.emp.refPnOrganization.divDesc, "
				+ "	NVL(c.emp.refPnOrganization.secCode,NVL(c.emp.refPnOrganization.areaCode,c.emp.refPnOrganization.divCode)), "
				+ " 	NVL(c.emp.refPnOrganization.secDesc,NVL(c.emp.refPnOrganization.areaDesc,c.emp.refPnOrganization.divDesc)), "
				+ "	c.emp.refPnOrganization.workCode, "
				+ " 	c.emp.refPnOrganization.workDesc, "
				+ "	c.evaEmp1, c.evaEmp2, c. evaEmp3, "
				+ " 	d.evaScore, "
				+ "	c.evaTotal, "
				+ "	d.pk.formCode, "
				+ " 	d.refPeEvaluationFormTitle.formDesc, "
				+ " 	d.refPeEvaluationFormTitle.titleCode, "
				+ " 	d.refPeEvaluationFormTitle.refPeEvaluationTitle.titleDesc ");
		sql.append(" FROM ");
		sql.append(" 	PeWgEmpScore c, PeWgEmpScoreDetail d ,WgEmpDataStatus ed,PnOrganization o");
		sql.append(" WHERE  ");
		sql.append("  c.pk.ouCode = '" + ouCode + "'");
		if (evaYear != null && evaYear.intValue() != 0) {
			sql.append(" AND c.pk.evaYear = " + evaYear);
		}

		if (evaTime != null && evaTime.intValue() != 0) {
			sql.append(" AND c.pk.evaTime = " + evaTime);
		}

		// ============================ Security
		// ===================================
		sql.append(" AND ed.codeSeq in (" + " select vpe.pk.codeSeq "
				+ " from VPnOrganizationSecurity vpe "
				+ " where vpe.pk.ouCode = '" + ouCode + "' "
				+ " and vpe.pk.userId = '" + userId + "'" + " ) ");
		// =========================================================================

		if (!areaCode.equals("")) {
			sql.append(" and o.areaCode ='" + areaCode + "' ");
		}
		if (!secCode.equals("")) {
			sql.append(" and o.secCode ='" + secCode + "' ");
		}
		if (!workCode.equals("")) {
			sql.append(" and o.workCode ='" + workCode + "' ");
		}

		sql.append(" AND c.pk.ouCode = ed.pk.ouCode "
				+ " AND c.pk.empCode = ed.pk.empCode "
				+ " AND c.statusDate = ed.statusDate "
				+ " and ed.pk.ouCode  = o.pk.ouCode"
				+ " and ed.codeSeq    = o.pk.codeSeq"
				+ " AND c.pk.ouCode = d.pk.ouCode "
				+ " AND c.pk.evaYear = d.pk.evaYear "
				+ " AND c.pk.evaTime = d.pk.evaTime "
				+ " AND c.pk.empCode = d.pk.empCode ");

		sql.append(" ORDER BY ");
		sql.append("	c.pk.evaYear, c.pk.evaTime, "
				+ "	d.pk.formCode, "
				+ "	NVL(c.emp.refPnOrganization.areaCode,c.emp.refPnOrganization.divCode),  "
				+ " 	NVL(c.emp.refPnOrganization.secCode,NVL(c.emp.refPnOrganization.areaCode,c.emp.refPnOrganization.divCode)),"
				+ " 	c.emp.refPnOrganization.workDesc, "
				+ "	c.emp.refPnOrganization.orgCode, " + "	c.pk.empCode, "
				+ "	d.refPeEvaluationFormTitle.titleCode ");

		try {
			Session ss = this.getSession();
			Query qq = ss.createQuery(sql.toString());
			listDetail = qq.list();
			Map obj = null;

			List titleCodeList = null;
			List scoreList = null;

			int m = 0;

			// for (Iterator itt=listDetail.iterator();itt.hasNext(); b++){
			for (int n = 0; n < listDetail.size(); n++) {
				// Object ob[] = (Object[]) itt.next();
				Object ob[] = (Object[]) listDetail.get(n);

				PnEmployeePK pk1 = new PnEmployeePK();
				PnEmployeePK pk2 = new PnEmployeePK();
				PnEmployeePK pk3 = new PnEmployeePK();
				PnEmployee emp = null;

				pk1.setOuCode(ouCode);
				pk2.setOuCode(ouCode);
				pk3.setOuCode(ouCode);

				peWgEmpScoreVO = new PeWgEmpScoreReportVO();
				peWgEmpScoreVO.setEvaYear((Long) ob[0]);
				peWgEmpScoreVO.setEvaTime((Long) ob[1]);
				peWgEmpScoreVO.setEmpCode((String) ob[2]);
				peWgEmpScoreVO.setEname((String) ob[3]);
				peWgEmpScoreVO.setAreaCode((String) ob[4]);
				peWgEmpScoreVO.setAreaDesc((String) ob[5]);
				peWgEmpScoreVO.setDivDesc((String) ob[6]);
				peWgEmpScoreVO.setSecCode((String) ob[7]);
				peWgEmpScoreVO.setSecDesc((String) ob[8]);
				peWgEmpScoreVO.setWorkCode((String) ob[9]);
				peWgEmpScoreVO.setWorkDesc((String) ob[10]);

				if (ob[11] != null) {
					pk1.setEmpCode((String) ob[11]);
					emp = (PnEmployee) getSession().load(PnEmployee.class, pk1);
					peWgEmpScoreVO.setEvaEmp1(emp.getRefDbPreSuff()
							.getPrefixName()
							+ " "
							+ emp.getFirstName()
							+ " "
							+ emp.getLastName());
				}

				if (ob[12] != null) {
					pk2.setEmpCode((String) ob[12]);
					emp = (PnEmployee) getSession().load(PnEmployee.class, pk2);
					peWgEmpScoreVO.setEvaEmp2(emp.getRefDbPreSuff()
							.getPrefixName()
							+ " "
							+ emp.getFirstName()
							+ " "
							+ emp.getLastName());
				}

				if (ob[13] != null) {
					pk3.setEmpCode((String) ob[13]);
					emp = (PnEmployee) getSession().load(PnEmployee.class, pk3);
					peWgEmpScoreVO.setEvaEmp3(emp.getRefDbPreSuff()
							.getPrefixName()
							+ " "
							+ emp.getFirstName()
							+ " "
							+ emp.getLastName());
				}

				// peEmpScoreVO.setEvaScore((Double) ob[14]);
				peWgEmpScoreVO.setEvaTotal((Double) ob[15]);
				peWgEmpScoreVO.setFormCode((String) ob[16]);
				peWgEmpScoreVO.setFormDesc((String) ob[17]);

				ename = ChgNullToEmpty((String) ob[3], "");

				m = n;

				titleCodeList = new ArrayList();
				scoreList = new ArrayList();

				for (int a = n; a < listDetail.size(); a++) {

					Object ob2[] = (Object[]) listDetail.get(a);

					if (ename.equalsIgnoreCase(ChgNullToEmpty((String) ob2[3],
							""))) {

						// titleCode
						titleCodeList.add(ob2[18]);

						// evaScore
						scoreList.add(ob2[14]);
						m++;
					} else {
						a = listDetail.size();
					}
				}

				if (m > 1) {
					n = m - 1;
				}

				if (titleCodeList.size() > 0) {
					peWgEmpScoreVO.setTitleCodeGroup(titleCodeList);
				}

				if (scoreList.size() > 0) {
					peWgEmpScoreVO.setScoreGroup(scoreList);
				}

				peWgEmpScoreVO.setTitleDesc((String) ob[19]);

				list.add(peWgEmpScoreVO);

				ename = "";
			}
			System.out.println("\n size List is ==" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
