/*
 * Created on 7 ?.?. 2549
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

import com.ss.tp.dao.VPeWgEmployeeScoreDAO;
import com.ss.tp.dto.VPeWgEmployeeScoreVO;

import com.ss.tp.model.SuOrganize;
import com.ss.tp.model.VPeWgEmployeeScore;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeWgEmployeeScoreDAOImpl extends HibernateDaoSupport implements
		VPeWgEmployeeScoreDAO, Serializable {

	public List findVPeWgEmployeeScore(String userId, String ouCode,
			Integer evaYear, Integer evaYearTo, String orgFrom, String orgTo,
			String empCodeFrom, String empCodeTo) {
		System.out.println("\n\n\n***findVPeWgEmployeeScore***");
		VPeWgEmployeeScore vtm;
		VPeWgEmployeeScoreVO vpeWgEmpScoreVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		String sql = "";

		sql += " from VPeWgEmployeeScore a "
				+ " where ("
				+
				// ============================ Security
				// ===================================
				" a.codeSeq in (" + " select vpe.pk.codeSeq "
				+ " from VPnOrganizationSecurity vpe "
				+ " where vpe.pk.ouCode = '" + ouCode + "' "
				+ " and vpe.pk.userId = '" + userId + "'" + " ) " +
				// =========================================================================
				" and a.vpeWgEmployeeScorePK.ouCode ='" + ouCode + "' ";

		// if(evaYear!=null && evaYear.intValue()!=0 ){
		// sql.append(" and a.vpeWgEmployeeScorePK.evaYear = " + evaYear);
		// }
		if (evaYear != null || evaYearTo != null) {
			sql = sql + " and  a.vpeWgEmployeeScorePK.evaYear between "
					+ evaYear + " and " + evaYearTo;
		}

		if (!"".equals(orgFrom) && !"".equals(orgTo)) {
			sql += " and a.orgCode between '" + orgFrom + "' and '" + orgTo
					+ "'";
		}
		if (!orgFrom.equals("") && "".equals(orgTo)) {
			sql += " and a.orgCode > '" + orgFrom + "'";
		}

		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql += " and  a.vpeWgEmployeeScorePK.empCode between '"
					+ empCodeFrom + "' and '" + empCodeTo + "'";
		}
		if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and a.vpeWgEmployeeScorePK.empCode >= '" + empCodeFrom
					+ "'";
		}

		sql += " ) " + " order by a.orgCode, a.vpeWgEmployeeScorePK.empCode ";

		System.out.println(sql);
		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql.toString());
			listDetail = qq.list();
			Map obj = null;
			String empCode = "";
			String empCodeTemp = "";
			String year = "";
			String tempYear = "";
			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VPeWgEmployeeScore) itt.next();
				vpeWgEmpScoreVO = new VPeWgEmployeeScoreVO();
				BeanUtils.copyProperties(vpeWgEmpScoreVO,
						vtm.getVpeWgEmployeeScorePK());
				BeanUtils.copyProperties(vpeWgEmpScoreVO, vtm);

				empCodeTemp = vpeWgEmpScoreVO.getEmpCode();
				if (!empCode.equals(empCodeTemp)) {
					empCode = empCodeTemp;
					vpeWgEmpScoreVO.setEmpCode(empCode);
				} else {
					vpeWgEmpScoreVO.setEmpName(" ");
					empCode = empCodeTemp;
					vpeWgEmpScoreVO.setEmpCode(" ");
				}
				// tempYear =
				// vpeWgEmpScoreVO.getEvaYear()==null?"":vpeWgEmpScoreVO.getEvaYear().toString();
				// if(!year.equals(tempYear)){
				// year = tempYear;
				// vpeWgEmpScoreVO.setEvaYear(new Integer(year));
				// }else{
				// vpeWgEmpScoreVO.setEvaYear(new Integer(0));
				// }
				list.add(vpeWgEmpScoreVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
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

	public List findVPeWgEmpScoreReport(String ouCode, String userId,
			Integer evaYear, Integer evaYearTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo) {
		VPeWgEmployeeScore vtm;
		VPeWgEmployeeScoreVO vpeWgEmpScoreVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();

		String sql = " from VPeWgEmployeeScore vp ";
		// ============================ Security
		// ===================================
		sql = sql + " where vp.codeSeq in (" + " select vpe.pk.codeSeq "
				+ " from VPnOrganizationSecurity vpe "
				+ " where vpe.pk.ouCode = '" + ouCode + "' "
				+ " and vpe.pk.userId = '" + userId + "'" + " ) ";
		// =========================================================================
		sql = sql + " and vp.vpeWgEmployeeScorePK.ouCode ='" + ouCode + "' ";

		// if(evaYear!=null && evaYear.intValue()!=0 ){
		// sql=sql+" and vp.vpeWgEmployeeScorePK.evaYear = "+evaYear;
		// }
		if (evaYear != null || evaYearTo != null) {
			sql = sql + " and ((vp.vpeWgEmployeeScorePK.evaYear between "
					+ evaYear + "and " + evaYearTo
					+ ") or vp.vpeWgEmployeeScorePK.evaYear = 1)";
		}
		if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
			sql = sql + " and vp.orgCode between '" + orgCodeFrom + "' and '"
					+ orgCodeTo + "'";
		} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
			sql = sql + " and vp.orgCode >= '" + orgCodeFrom + "' ";
		}

		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql = sql + " and vp.vpeWgEmployeeScorePK.empCode between '"
					+ empCodeFrom + "' and '" + empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql = sql + " and vp.vpeWgEmployeeScorePK.empCode >= '"
					+ empCodeFrom + "' ";
		}
		sql = sql
				+ "  order by vp.divCode,nvl(vp.areaCode,vp.divCode),vp.secCode,vp.workCode,vp.vpeWgEmployeeScorePK.empCode ,vp.vpeWgEmployeeScorePK.evaYear";

		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listDetail = qq.list();

			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VPeWgEmployeeScore) itt.next();
				vpeWgEmpScoreVO = new VPeWgEmployeeScoreVO();
				BeanUtils.copyProperties(vpeWgEmpScoreVO,
						vtm.getVpeWgEmployeeScorePK());
				BeanUtils.copyProperties(vpeWgEmpScoreVO, vtm);
				// empCodeTemp = vpeWgEmpScoreVO.getEmpCode();
				// if(!empCode.equals(empCodeTemp)){
				//
				// empCode=empCodeTemp;
				// }else{
				// vpeWgEmpScoreVO.setEmpName("");
				// empCode=empCodeTemp;
				// }
				// vpeWgEmpScoreVO.setEmpCode(empCode);
				if (vpeWgEmpScoreVO.getEvaYear().equals(new Integer(1))) {
					vpeWgEmpScoreVO.setEvaYear(null);
				}
				list.add(vpeWgEmpScoreVO);
			}
			System.out.println("\n size List is ==" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
