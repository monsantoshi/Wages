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

import com.ss.tp.dao.VPeEmployeeScoreDAO;
import com.ss.tp.dto.VPeEmployeeScoreVO;

// import com.ss.tp.model.PnEmployee;
// import com.ss.tp.model.PnEmployeePK;
import com.ss.tp.model.VPeEmployeeScore;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeEmployeeScoreDAOImpl extends HibernateDaoSupport implements
		VPeEmployeeScoreDAO, Serializable {

	public List findVPeEmployeeScore(String userId, String ouCode,
			Integer evaYear, Integer evaYearTo, String orgFrom, String orgTo,
			String empCodeFrom, String empCodeTo) {
		System.out.println("\n\n\n***findVPeEmployeeScore***");
		VPeEmployeeScore vtm;
		VPeEmployeeScoreVO vpeEmpScoreVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();

		String sql = " from VPeEmployeeScore a "
				+ " where ("
				+
				// ============================ Security
				// ===================================
				// " a.vpeEmployeeScorePK.empCode in (" +
				// " select vpe.pk.empCode " +
				// " from VPnEmployeeSecurity vpe " +
				// " where vpe.pk.ouCode = '" + ouCode + "' " +
				// " and vpe.pk.userId = '" + userId + "'" +
				// " ) "+
				" a.codeSeq in (" + " select vpe.pk.codeSeq "
				+ " from VPnOrganizationSecurity vpe "
				+ " where vpe.pk.ouCode = '" + ouCode + "' "
				+ " and vpe.pk.userId = '" + userId + "'" + " ) "
				+ " and a.vpeEmployeeScorePK.ouCode ='" + ouCode + "' ";
		if (evaYear != null || evaYearTo != null) {
			sql += "  and a.vpeEmployeeScorePK.evaYear between " + evaYear
					+ " and " + evaYearTo;
		}
		if (!orgFrom.equals("") && !"".equals(orgTo)) {
			sql += " and a.orgCode between '" + orgFrom + "' and '" + orgTo
					+ "'";
		}
		if (!"".equals(orgFrom) && "".equals(orgTo)) {
			sql += " and a.orgCode >= '" + orgFrom + "'";
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql += " and  a.vpeEmployeeScorePK.empCode between '" + empCodeFrom
					+ "' and '" + empCodeTo + "'";
		}
		if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and a.vpeEmployeeScorePK.empCode >= '" + empCodeFrom + "'";
		}
		sql += " ) " + " order by  a.orgCode, a.vpeEmployeeScorePK.empCode ";
		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listDetail = qq.list();
			String empCode = "";
			String empCodeTemp = "";
			String year = "";
			String tempYear = "";
			Map obj = null;
			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VPeEmployeeScore) itt.next();
				vpeEmpScoreVO = new VPeEmployeeScoreVO();
				BeanUtils.copyProperties(vpeEmpScoreVO,
						vtm.getVpeEmployeeScorePK());
				BeanUtils.copyProperties(vpeEmpScoreVO, vtm);

				empCodeTemp = vpeEmpScoreVO.getEmpCode();
				if (!empCode.equals(empCodeTemp)) {
					empCode = empCodeTemp;
					vpeEmpScoreVO.setEmpCode(empCode);
				} else {
					vpeEmpScoreVO.setEmpName(" ");
					empCode = empCodeTemp;
					vpeEmpScoreVO.setEmpCode(" ");
				}
				// System.out.println("
				// empName==>>"+vpeEmpScoreVO.getEmpName());
				// tempYear =
				// vpeEmpScoreVO.getEvaYear()==null?"":vpeEmpScoreVO.getEvaYear().toString();
				// if(!year.equals(tempYear)){
				// year = tempYear;
				// vpeEmpScoreVO.setEvaYear(new Integer(year));
				// }else{
				// vpeEmpScoreVO.setEvaYear(new Integer(0));
				// }
				list.add(vpeEmpScoreVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
