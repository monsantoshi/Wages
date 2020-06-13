/*
 * Created on 8 ?.?. 2549
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
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.VPeEmpScoreReportDAO;
import com.ss.tp.dto.VPeEmployeeScoreVO;
import com.ss.tp.model.SuOrganize;
import com.ss.tp.model.VPeEmployeeScore;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeEmpScoreReportDAOImpl extends HibernateDaoSupport implements
		VPeEmpScoreReportDAO, Serializable {
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

	public List findVPeEmpScoreReport(String ouCode, String userId,
			Integer evaYear, Integer evaYearTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo) {
		VPeEmployeeScore vtm;
		VPeEmployeeScoreVO vpeEmpScoreVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		try {
			String sql = " from VPeEmployeeScore vp ";
			// ============================ Security
			// ===================================
			sql = sql + " where vp.codeSeq in (" + " select vpe.pk.codeSeq "
					+ " from VPnOrganizationSecurity vpe "
					+ " where vpe.pk.ouCode = '" + ouCode + "' "
					+ " and vpe.pk.userId = '" + userId + "'" + " ) ";
			// =========================================================================
			sql = sql + " and vp.vpeEmployeeScorePK.ouCode ='" + ouCode + "' ";

			if (evaYear != null || evaYearTo != null) {
				sql = sql + " and vp.vpeEmployeeScorePK.evaYear between "
						+ evaYear + "and " + evaYearTo;
			}

			if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
				sql = sql + " and vp.orgCode between '" + orgCodeFrom
						+ "' and '" + orgCodeTo + "'";
			} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
				sql = sql + " and vp.orgCode >= '" + orgCodeFrom + "' ";
			}

			if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
				sql = sql + " and vp.vpeEmployeeScorePK.empCode between '"
						+ empCodeFrom + "' and '" + empCodeTo + "'";
			} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
				sql = sql + " and vp.vpeEmployeeScorePK.empCode >= '"
						+ empCodeFrom + "' ";
			}

			sql = sql
					+ " order by vp.orgCode, vp.orgDesc,vp.vpeEmployeeScorePK.empCode ,vp.vpeEmployeeScorePK.evaYear";

			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listDetail = qq.list();
			Map obj = null;
			String empCode = "";
			String empCodeTemp = "";
			String totalTemp = "";

			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VPeEmployeeScore) itt.next();
				vpeEmpScoreVO = new VPeEmployeeScoreVO();
				BeanUtils.copyProperties(vpeEmpScoreVO,
						vtm.getVpeEmployeeScorePK());
				BeanUtils.copyProperties(vpeEmpScoreVO, vtm);

				// empCodeTemp = vpeEmpScoreVO.getEmpCode();
				// if(!empCode.equals(empCodeTemp)){
				//
				// empCode=empCodeTemp;
				// }else{
				// vpeEmpScoreVO.setEmpName("");
				// empCode=empCodeTemp;
				// }
				// vpeEmpScoreVO.setEmpCode(empCode);

				list.add(vpeEmpScoreVO);
			}
			System.out.println("\n size List is ==" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
