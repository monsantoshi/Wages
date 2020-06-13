/*
 * Created on 4 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.*;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.TaViewDAO;
import com.ss.tp.dto.TaReportVO;

import com.ss.tp.model.VTaMonthEmpWork;
import com.ss.tp.model.VTaWgMonthEmpWork;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaViewDAOImpl extends HibernateDaoSupport implements TaViewDAO,
		Serializable {
	private DataSource dataSource;

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table V_TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param userId
	 * @param CodeSeq
	 * @param ouCode
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo) {
		VTaMonthEmpWork vtm;
		TaReportVO taViewVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		try {
			String sql = "from VTaMonthEmpWork as vt "
					+ " where vt.codeSeq in ( " + " select vorg.pk.codeSeq "
					+ " from VPnOrganizationSecurity vorg "
					+ " where vorg.pk.userId = '" + userId + "'" + " ) ";

			if (workYear != null && workYear.intValue() != 0) {
				sql = sql + " and vt.vtaMonthEmpWorkPK.workYear  = " + workYear;

			}
			if (!codeSeq.equals("")) {
				sql += " and vt.codeSeq =" + codeSeq;
			}
			if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
				sql = sql + "  and vt.vtaMonthEmpWorkPK.empCode between '"
						+ empCodeFrom + "' and  '" + empCodeTo + "'";
			} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
				sql += " and vt.vtaMonthEmpWorkPK.empCode >='" + empCodeFrom
						+ "'";
			}
			sql += " order by   vt.vtaMonthEmpWorkPK.empCode,vt.vtaMonthEmpWorkPK.workCode ";

			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listDetail = qq.list();
			Map obj = null;
			String empCode = "";
			String tempYear = "";
			int num = 0;
			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VTaMonthEmpWork) itt.next();
				tempYear = "";
				taViewVO = new TaReportVO();
				BeanUtils.copyProperties(taViewVO, vtm.getVtaMonthEmpWorkPK());
				BeanUtils.copyProperties(taViewVO, vtm);
				if (!empCode.equals(taViewVO.getEmpCode())) {
					empCode = taViewVO.getEmpCode();
					taViewVO.setEmpCodeTemp(taViewVO.getEmpCode());
				} else {
					taViewVO.setFullName("");
					taViewVO.setEmpCodeTemp("");
					empCode = taViewVO.getEmpCode();
				}
				tempYear = taViewVO.getWorkYear() == null ? "" : taViewVO
						.getWorkYear().toString();
				if (!tempYear.equals("")) {
					taViewVO.setTempBudgetYear(tempYear.substring(2));
					tempYear = taViewVO.getTempBudgetYear();
					num = 1 + Integer.parseInt(tempYear);
					taViewVO.setTempBudgetYear2(String.valueOf(num));
				} else {
					taViewVO.setTempBudgetYear("");
					taViewVO.setTempBudgetYear2("");
				}
				taViewVO.setEmpCode(empCode);
				list.add(taViewVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * method ����Ѻ Query �����Ũҡ ����һ�Шӻբͧ��ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workYear
	 * @param workYearFrom
	 * @param workYearTo
	 * @param monthFrom
	 * @param monthTo
	 * @param orgCodeFrom
	 * @param orgCodeTo
	 * @param empCodeTo
	 * @param empCodeFrom
	 * @param choice
	 * @return
	 */
	public List findListEmpDetailNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice) {
		List list = null;
		List dataList = new ArrayList();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		TaReportVO vo;
		int checkMonthFrom = 0;
		int checkMonthTo = 0;
		int k = 0;
		int x = 1;
		String empCode = "";
		String tempYear = "";
		String tempYearTo = "";
		String sql = "";
		int y = 1;// ����Ѻ��˹���͹�������˹�� web
		Map map = null;
		try {
			if (choice.equals("1")) {// same Year
				sql = " select a.ou_code,a.work_year,a.work_code,a.work_desc,a.emp_code,a.Full_Name, "
						+ " a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc, "
						+ " nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1, "
						+ " nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2, "
						+ " nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3, "
						+ " nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4, "
						+ " nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5, "
						+ " nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6, "
						+ " nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7, "
						+ " nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8, "
						+ " nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9, "
						+ " nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10, "
						+ " nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11, "
						+ " nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12, "
						+ " to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+ "
						+ "		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total "
						+ " from "
						+ " ( "
						+ "	 select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month, "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1, "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2, "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3, "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4, "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5, "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6, "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7, "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8, "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9, "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10, "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11, "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12, "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc "
						+ "	 from TA_MONTH_EMP_WORK t,TA_STATUS_WORK ts,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org "
						+ "		where t.ou_code=pn.ou_code "
						+ "		and t.EMP_CODE=pn.EMP_CODE "
						+ "		and t.ou_code=ts.ou_code "
						+ "		and	t.WORK_CODE=ts.WORK_CODE "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
						+ "		AND        pn.ou_code	= org.ou_code "
						+ "		AND        pn.code_seq	= org.code_seq "
						+ "		 "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
						+ "															   where  vpn.USER_ID= '"
						+ userId
						+ "'" + "									) " + "	 ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYear;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '" + monthFrom
							+ "' and  '" + monthTo + "'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += " ) a   "
						+ "   group by  a.ou_code,a.work_year,a.work_code,a.emp_code,a.work_desc, a.full_name,a.code_seq,a.org_code,a.org_desc, "
						+ "	  	  		a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc "
						+ " order by a.org_code,a.emp_code, a.work_code ";

			} else {// difference year

				sql = " select a.ou_code,a.work_year,a.work_code,a.work_desc,a.emp_code,a.Full_Name,  "
						+ " a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc,  "
						+ " nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1,  "
						+ " nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2,  "
						+ " nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3,  "
						+ " nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4,  "
						+ " nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5,  "
						+ " nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6,  "
						+ " nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7,  "
						+ " nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8,  "
						+ " nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9,  "
						+ " nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10,  "
						+ " nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11,  "
						+ " nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12,  "
						+ " to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+  "
						+ "		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total  "
						+ " from  "
						+ " (  "
						+ "	select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1,  "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2,  "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3,  "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4,  "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5,  "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6,  "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7,  "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8,  "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9,  "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10, "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11, "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12,  "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
						+ "	from TA_MONTH_EMP_WORK t,TA_STATUS_WORK ts,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
						+ "		where t.ou_code=pn.ou_code  "
						+ "		and t.EMP_CODE=pn.EMP_CODE  "
						+ "		and t.ou_code=ts.ou_code  "
						+ "		and	t.WORK_CODE=ts.WORK_CODE  "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE  "
						+ "		AND        pn.ou_code	= org.ou_code  "
						+ "		AND        pn.code_seq	= org.code_seq  "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ  "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn  "
						+ "															   where  vpn.USER_ID='"
						+ userId
						+ "'" + "													)  " + "	 ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYearFrom;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '" + monthFrom
							+ "' and  '12'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += "		union  "
						+ "		 "
						+ "		select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1,  "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2,  "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3,  "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4,  "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5,  "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6,  "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7,  "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8,  "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9,  "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10,  "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11,  "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12,  "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name,  "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc,  "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
						+ "	from TA_MONTH_EMP_WORK t,TA_STATUS_WORK ts,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
						+ "		where t.ou_code=pn.ou_code "
						+ "		and t.EMP_CODE=pn.EMP_CODE  "
						+ "		and t.ou_code=ts.ou_code  "
						+ "		and	t.WORK_CODE=ts.WORK_CODE  "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE  "
						+ "		AND        pn.ou_code	= org.ou_code  "
						+ "		AND        pn.code_seq	= org.code_seq  "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ  "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn  "
						+ "															   where  vpn.USER_ID='" + userId
						+ "'" + "										 )  ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYearTo;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '1' and  '" + monthTo
							+ "'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += " ) a   "
						+ " group by  a.ou_code,a.work_year,a.work_code,a.emp_code,a.work_desc, a.full_name,a.code_seq,a.org_code,a.org_desc,  "
						+ "	  	  		a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc  "
						+ "  order by a.org_code,a.emp_code, a.work_code  ";

			}
			Object[] obj = null;
			System.out.println("\n\n sql==>" + sql);
			list = jdbcTemplate.queryForList(sql);
			empCode = "";
			for (int i = 0; i < list.size(); i++) {
				map = new HashMap();
				map = (Map) list.get(i);
				obj = new Object[13];
				vo = new TaReportVO();
				vo.setOuCode(map.get("ou_code") != null ? map.get("ou_code")
						.toString() : "");
				vo.setWorkYear(map.get("work_year") != null ? new Integer(map
						.get("work_year").toString()) : new Integer(0));
				vo.setWorkCode(map.get("work_code") != null ? map.get(
						"work_code").toString() : "");
				vo.setWorkDesc(map.get("work_desc") != null ? map.get(
						"work_desc").toString() : "");
				vo.setEmpCode(map.get("emp_code") != null ? map.get("emp_code")
						.toString() : "");
				vo.setFullName(map.get("Full_Name") != null ? map.get(
						"Full_Name").toString() : "");
				if (!empCode.equals(vo.getEmpCode())) {
					empCode = vo.getEmpCode();
					vo.setEmpCodeTemp(vo.getEmpCode());
				} else {
					vo.setFullName("");
					vo.setEmpCodeTemp("");
					empCode = vo.getEmpCode();
				}
				vo.setEmpCode(empCode);
				vo.setCodeSeq(map.get("code_seq") != null ? map.get("code_seq")
						.toString() : "");
				vo.setOrgCode(map.get("org_code") != null ? map.get("org_code")
						.toString() : "");
				vo.setOrgDesc(map.get("org_desc") != null ? map.get("org_desc")
						.toString() : "");
				vo.setLevelCode(map.get("level_code") != null ? map.get(
						"level_code").toString() : "");
				vo.setDivCode(map.get("div_code") != null ? map.get("div_code")
						.toString() : "");
				vo.setDivDesc(map.get("div_desc") != null ? map.get("div_desc")
						.toString() : "");
				vo.setAreaCode(map.get("area_code") != null ? map.get(
						"area_code").toString() : "");
				vo.setAreaDesc(map.get("area_desc") != null ? map.get(
						"area_desc").toString() : "");
				vo.setSecCode(map.get("sec_code") != null ? map.get("sec_code")
						.toString() : "");
				vo.setSecDesc(map.get("sec_desc") != null ? map.get("sec_desc")
						.toString() : "");
				vo.setOrgWorkCode(map.get("orgwork_code") != null ? map.get(
						"orgwork_code").toString() : "");
				vo.setOrgWorkDesc(map.get("orgwork_desc") != null ? map.get(
						"orgwork_desc").toString() : "");
				vo.setTotal(map.get("total") != null ? map.get("total")
						.toString() : "");

				obj[1] = map.get("month1") != null ? map.get("month1")
						.toString() : "";
				obj[2] = map.get("month2") != null ? map.get("month2")
						.toString() : "";
				obj[3] = map.get("month3") != null ? map.get("month3")
						.toString() : "";
				obj[4] = map.get("month4") != null ? map.get("month4")
						.toString() : "";
				obj[5] = map.get("month5") != null ? map.get("month5")
						.toString() : "";
				obj[6] = map.get("month6") != null ? map.get("month6")
						.toString() : "";
				obj[7] = map.get("month7") != null ? map.get("month7")
						.toString() : "";
				obj[8] = map.get("month8") != null ? map.get("month8")
						.toString() : "";
				obj[9] = map.get("month9") != null ? map.get("month9")
						.toString() : "";
				obj[10] = map.get("month10") != null ? map.get("month10")
						.toString() : "";
				obj[11] = map.get("month11") != null ? map.get("month11")
						.toString() : "";
				obj[12] = map.get("month12") != null ? map.get("month12")
						.toString() : "";

				if (monthFrom != null && !monthFrom.equals("")) {
					checkMonthFrom = Integer.parseInt(monthFrom);
				}
				if (monthTo != null && !monthTo.equals("")) {
					checkMonthTo = Integer.parseInt(monthTo);
				}
				k = 0;
				x = 1;
				y = 1;
				if (choice.equals("1")) {// case same year
					tempYear = vo.getWorkYear() == null ? "" : vo.getWorkYear()
							.toString();
					vo.setTempBudgetYear(tempYear.substring(2));
					while (checkMonthFrom <= checkMonthTo) {
						k = checkMonthFrom;
						switch (y) {
						case 1:
							vo.setMonth1((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName1(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 2:
							vo.setMonth2((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName2(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 3:
							vo.setMonth3((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName3(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 4:
							vo.setMonth4((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName4(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 5:
							vo.setMonth5((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName5(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 6:
							vo.setMonth6((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName6(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 7:
							vo.setMonth7((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName7(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 8:
							vo.setMonth8((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName8(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 9:
							vo.setMonth9((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName9(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 10:
							vo.setMonth10((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName10(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 11:
							vo.setMonth11((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName11(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 12:
							vo.setMonth12((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName12(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						}
						checkMonthFrom++;
						y++;
					}
				} else if (choice.equals("2")) {// case difference year

					if (workYearFrom != null && workYearFrom.intValue() != 0) {// ������u�
						tempYear = workYearFrom.toString();
						vo.setTempBudgetYear(tempYear.substring(2));
						while (checkMonthFrom <= 12) {
							k = checkMonthFrom;

							switch (y) {
							case 1:
								vo.setMonth1((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName1(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 2:
								vo.setMonth2((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName2(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 3:
								vo.setMonth3((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName3(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 4:
								vo.setMonth4((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName4(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 5:
								vo.setMonth5((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName5(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 6:
								vo.setMonth6((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName6(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 7:
								vo.setMonth7((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName7(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 8:
								vo.setMonth8((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName8(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 9:
								vo.setMonth9((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName9(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 10:
								vo.setMonth10((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName10(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 11:
								vo.setMonth11((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName11(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 12:
								vo.setMonth12(((obj)[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName12(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							}
							checkMonthFrom++;
							y++;
						}
					}
					if (workYearTo != null && workYearTo.intValue() != 0) {// ������ش
						tempYearTo = workYearTo.toString();
						vo.setTempBudgetYear2(tempYearTo.substring(2));
						while (x <= checkMonthTo) {
							k = x;
							switch (y) {
							case 1:
								vo.setMonth1((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName1(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 2:
								vo.setMonth2((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName2(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 3:
								vo.setMonth3((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName3(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 4:
								vo.setMonth4((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName4(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 5:
								vo.setMonth5((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName5(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 6:
								vo.setMonth6((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName6(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 7:
								vo.setMonth7((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName7(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 8:
								vo.setMonth8((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName8(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 9:
								vo.setMonth9((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName9(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 10:
								vo.setMonth10((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName10(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 11:
								vo.setMonth11((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName11(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 12:
								vo.setMonth12(((obj)[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName12(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							}
							x++;
							y++;
						}
					}

				}
				if (vo.getMonth1() == null) {
					vo.setMonth1("-");
				} else {
					if (vo.getMonth1().equals("0.0")) {
						vo.setMonth1("&nbsp;");
					}
				}
				if (vo.getMonth2() == null) {
					vo.setMonth2("-");
				} else {
					if (vo.getMonth2().equals("0.0")) {
						vo.setMonth2("&nbsp;");
					}
				}
				if (vo.getMonth3() == null) {
					vo.setMonth3("-");
				} else {
					if (vo.getMonth3().equals("0.0")) {
						vo.setMonth3("&nbsp;");
					}
				}
				if (vo.getMonth4() == null) {
					vo.setMonth4("-");
				} else {
					if (vo.getMonth4().equals("0.0")) {
						vo.setMonth4("&nbsp;");
					}
				}
				if (vo.getMonth5() == null) {
					vo.setMonth5("-");
				} else {
					if (vo.getMonth5().equals("0.0")) {
						vo.setMonth5("&nbsp;");
					}
				}
				if (vo.getMonth6() == null) {
					vo.setMonth6("-");
				} else {
					if (vo.getMonth6().equals("0.0")) {
						vo.setMonth6("&nbsp;");
					}
				}
				if (vo.getMonth7() == null) {
					vo.setMonth7("-");
				} else {
					if (vo.getMonth7().equals("0.0")) {
						vo.setMonth7("&nbsp;");
					}
				}
				if (vo.getMonth8() == null) {
					vo.setMonth8("-");
				} else {
					if (vo.getMonth8().equals("0.0")) {
						vo.setMonth8("&nbsp;");
					}
				}
				if (vo.getMonth9() == null) {
					vo.setMonth9("-");
				} else {
					if (vo.getMonth9().equals("0.0")) {
						vo.setMonth9("&nbsp;");
					}
				}
				if (vo.getMonth10() == null) {
					vo.setMonth10("-");
				} else {
					if (vo.getMonth10().equals("0.0")) {
						vo.setMonth10("&nbsp;");
					}
				}
				if (vo.getMonth11() == null) {
					vo.setMonth11("-");
				} else {
					if (vo.getMonth11().equals("0.0")) {
						vo.setMonth11("&nbsp;");
					}
				}
				if (vo.getMonth12() == null) {
					vo.setMonth12("-");
				} else {
					if (vo.getMonth12().equals("0.0")) {
						vo.setMonth12("&nbsp;");
					}
				}

				if (vo.getMonthName1() == null) {
					vo.setMonthName1("-");
				}
				;
				if (vo.getMonthName2() == null) {
					vo.setMonthName2("-");
				}
				;
				if (vo.getMonthName3() == null) {
					vo.setMonthName3("-");
				}
				;
				if (vo.getMonthName4() == null) {
					vo.setMonthName4("-");
				}
				;
				if (vo.getMonthName5() == null) {
					vo.setMonthName5("-");
				}
				;
				if (vo.getMonthName6() == null) {
					vo.setMonthName6("-");
				}
				;
				if (vo.getMonthName7() == null) {
					vo.setMonthName7("-");
				}
				;
				if (vo.getMonthName8() == null) {
					vo.setMonthName8("-");
				}
				;
				if (vo.getMonthName9() == null) {
					vo.setMonthName9("-");
				}
				;
				if (vo.getMonthName10() == null) {
					vo.setMonthName10("-");
				}
				;
				if (vo.getMonthName11() == null) {
					vo.setMonthName11("-");
				}
				;
				if (vo.getMonthName12() == null) {
					vo.setMonthName12("-");
				}
				;
				dataList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table V_TA_WG_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param userId
	 * @param CodeSeq
	 * @param ouCode
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListWgEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo) {
		VTaWgMonthEmpWork vtm;
		TaReportVO taViewVO;
		List list = new ArrayList();
		List listDetail = new ArrayList();
		try {
			String sql = "from VTaWgMonthEmpWork as vt "
					+ " where vt.codeSeq in ( " + " select vorg.pk.codeSeq "
					+ " from VPnOrganizationSecurity vorg "
					+ " where vorg.pk.userId = '" + userId + "'" + " ) ";

			if (workYear != null && workYear.intValue() != 0) {
				sql = sql + " and vt.vtaWgMonthEmpWorkPK.workYear  = "
						+ workYear;

			}
			if (!codeSeq.equals("")) {
				sql += " and vt.codeSeq =" + codeSeq;
			}
			if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
				sql = sql + "  and vt.vtaWgMonthEmpWorkPK.empCode between '"
						+ empCodeFrom + "' and  '" + empCodeTo + "'";
			} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
				sql += " and vt.vtaWgMonthEmpWorkPK.empCode >='" + empCodeFrom
						+ "'";
			}
			sql += " order by   vt.vtaWgMonthEmpWorkPK.empCode,vt.vtaWgMonthEmpWorkPK.workCode ";

			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listDetail = qq.list();
			Map obj = null;
			String empCode = "";
			String tempYear = "";

			int num = 0;
			for (Iterator itt = listDetail.iterator(); itt.hasNext();) {
				vtm = (VTaWgMonthEmpWork) itt.next();
				tempYear = "";
				taViewVO = new TaReportVO();
				BeanUtils
						.copyProperties(taViewVO, vtm.getVtaWgMonthEmpWorkPK());
				BeanUtils.copyProperties(taViewVO, vtm);
				if (!empCode.equals(taViewVO.getEmpCode())) {
					empCode = taViewVO.getEmpCode();
					taViewVO.setEmpCodeTemp(taViewVO.getEmpCode());
				} else {
					taViewVO.setFullName("");
					taViewVO.setEmpCodeTemp("");
					empCode = taViewVO.getEmpCode();
				}
				tempYear = taViewVO.getWorkYear() == null ? "" : taViewVO
						.getWorkYear().toString();
				if (!tempYear.equals("")) {
					taViewVO.setTempBudgetYear(tempYear.substring(2));
					tempYear = taViewVO.getTempBudgetYear();
					num = 1 + Integer.parseInt(tempYear);
					taViewVO.setTempBudgetYear2(String.valueOf(num));
				} else {
					taViewVO.setTempBudgetYear("");
					taViewVO.setTempBudgetYear2("");
				}
				taViewVO.setEmpCode(empCode);
				list.add(taViewVO);
			}
			System.out.println("\n size List is ==>>" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * method ����Ѻ Query �����Ũҡ ����һ�Шӻբͧ�١��ҧ
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workYear
	 * @param workYearFrom
	 * @param workYearTo
	 * @param monthFrom
	 * @param monthTo
	 * @param orgCodeFrom
	 * @param orgCodeTo
	 * @param empCodeTo
	 * @param empCodeFrom
	 * @param choice
	 * @return
	 */
	public List findListWgEmpDetailNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice) {
		List list = null;
		List dataList = new ArrayList();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		TaReportVO vo;
		int checkMonthFrom = 0;
		int checkMonthTo = 0;
		int k = 0;
		int x = 1;
		String empCode = "";
		String tempYear = "";
		String tempYearTo = "";
		String sql = "";
		int y = 1;// ����Ѻ��˹���͹�������˹�� web
		Map map = null;
		try {
			if (choice.equals("1")) {// same Year
				sql = " select a.ou_code,a.work_year,a.work_code,a.work_desc,a.emp_code,a.Full_Name, "
						+ " a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc, "
						+ " nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1, "
						+ " nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2, "
						+ " nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3, "
						+ " nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4, "
						+ " nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5, "
						+ " nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6, "
						+ " nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7, "
						+ " nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8, "
						+ " nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9, "
						+ " nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10, "
						+ " nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11, "
						+ " nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12, "
						+ " to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+ "
						+ "		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total "
						+ " from "
						+ " ( "
						+ "	 select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month, "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1, "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2, "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3, "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4, "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5, "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6, "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7, "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8, "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9, "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10, "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11, "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12, "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc "
						+ "	 from  TA_WG_MONTH_EMP_WORK t,TA_WG_STATUS_WORK ts,WG_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org "
						+ "		where t.ou_code=pn.ou_code "
						+ "		and t.EMP_CODE=pn.EMP_CODE "
						+ "		and t.ou_code=ts.ou_code "
						+ "		and	t.WORK_CODE=ts.WORK_CODE "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
						+ "		AND        pn.ou_code	= org.ou_code "
						+ "		AND        pn.code_seq	= org.code_seq "
						+ "		 "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
						+ "															   where  vpn.USER_ID='"
						+ userId
						+ "'" + "										) " + "	 ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYear;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '" + monthFrom
							+ "' and  '" + monthTo + "'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += " ) a   "
						+ "   group by  a.ou_code,a.work_year,a.work_code,a.emp_code,a.work_desc, a.full_name,a.code_seq,a.org_code,a.org_desc, "
						+ "	  	  		a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc "
						+ " order by a.org_code,a.emp_code, a.work_code ";

			} else {// difference year

				sql = " select a.ou_code,a.work_year,a.work_code,a.work_desc,a.emp_code,a.Full_Name,  "
						+ " a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc,  "
						+ " nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1,  "
						+ " nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2,  "
						+ " nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3,  "
						+ " nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4,  "
						+ " nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5,  "
						+ " nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6,  "
						+ " nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7,  "
						+ " nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8,  "
						+ " nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9,  "
						+ " nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10,  "
						+ " nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11,  "
						+ " nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12,  "
						+ " to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+  "
						+ "		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total  "
						+ " from  "
						+ " (  "
						+ "	select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1,  "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2,  "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3,  "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4,  "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5,  "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6,  "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7,  "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8,  "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9,  "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10, "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11, "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12,  "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
						+ "	from  TA_WG_MONTH_EMP_WORK t,TA_WG_STATUS_WORK ts,WG_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
						+ "		where t.ou_code=pn.ou_code  "
						+ "		and t.EMP_CODE=pn.EMP_CODE  "
						+ "		and t.ou_code=ts.ou_code  "
						+ "		and	t.WORK_CODE=ts.WORK_CODE  "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE  "
						+ "		AND        pn.ou_code	= org.ou_code  "
						+ "		AND        pn.code_seq	= org.code_seq  "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ  "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn  "
						+ "															   where  vpn.USER_ID='"
						+ userId
						+ "'" + "									)  " + "	 ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYearFrom;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '" + monthFrom
							+ "' and  '12'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += "		union  "
						+ "		 "
						+ "		select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
						+ "		decode(t.work_month,1,t.total_days,0) day_month1,  "
						+ "		decode(t.work_month,2,t.total_days,0) day_month2,  "
						+ "		decode(t.work_month,3,t.total_days,0) day_month3,  "
						+ "		decode(t.work_month,4,t.total_days,0) day_month4,  "
						+ "		decode(t.work_month,5,t.total_days,0) day_month5,  "
						+ "		decode(t.work_month,6,t.total_days,0) day_month6,  "
						+ "		decode(t.work_month,7,t.total_days,0) day_month7,  "
						+ "		decode(t.work_month,8,t.total_days,0) day_month8,  "
						+ "		decode(t.work_month,9,t.total_days,0) day_month9,  "
						+ "		decode(t.work_month,10,t.total_days,0) day_month10,  "
						+ "		decode(t.work_month,11,t.total_days,0) day_month11,  "
						+ "		decode(t.work_month,12,t.total_days,0) day_month12,  "
						+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name,  "
						+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc,  "
						+ "	org.level_code,org.div_code,org.div_desc,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
						+ "	from  TA_WG_MONTH_EMP_WORK t,TA_WG_STATUS_WORK ts,WG_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
						+ "		where t.ou_code=pn.ou_code "
						+ "		and t.EMP_CODE=pn.EMP_CODE  "
						+ "		and t.ou_code=ts.ou_code  "
						+ "		and	t.WORK_CODE=ts.WORK_CODE  "
						+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE  "
						+ "		AND        pn.ou_code	= org.ou_code  "
						+ "		AND        pn.code_seq	= org.code_seq  "
						+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ  "
						+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn  "
						+ "															   where  vpn.USER_ID='" + userId
						+ "'" + "									 )  ";
				if (workYear != null && workYear.intValue() != 0) {
					sql = sql + " and t.work_year  = " + workYearTo;
				}
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and t.work_month between '1' and  '" + monthTo
							+ "'";
				}
				if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
					sql += " and  org.org_code between '" + orgCodeFrom
							+ "' and  '" + orgCodeTo + "'";
				} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
					sql += " and  org.org_code  >='" + orgCodeFrom + "'";
				}
				if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
					sql += " and t.emp_code between '" + empCodeFrom
							+ "' and  '" + empCodeTo + "'";
				} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
					sql += " and t.emp_code >='" + empCodeFrom + "'";
				}
				sql += " ) a   "
						+ " group by  a.ou_code,a.work_year,a.work_code,a.emp_code,a.work_desc, a.full_name,a.code_seq,a.org_code,a.org_desc,  "
						+ "	  	  		a.level_code,a.div_code,a.div_desc,a.area_code,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc  "
						+ "  order by a.org_code,a.emp_code, a.work_code  ";

			}
			Object[] obj = null;
			System.out.println("\n\n sql==>" + sql);
			list = jdbcTemplate.queryForList(sql);
			empCode = "";
			for (int i = 0; i < list.size(); i++) {
				map = new HashMap();
				map = (Map) list.get(i);
				obj = new Object[13];
				vo = new TaReportVO();
				vo.setOuCode(map.get("ou_code") != null ? map.get("ou_code")
						.toString() : "");
				vo.setWorkYear(map.get("work_year") != null ? new Integer(map
						.get("work_year").toString()) : new Integer(0));
				vo.setWorkCode(map.get("work_code") != null ? map.get(
						"work_code").toString() : "");
				vo.setWorkDesc(map.get("work_desc") != null ? map.get(
						"work_desc").toString() : "");
				vo.setEmpCode(map.get("emp_code") != null ? map.get("emp_code")
						.toString() : "");
				vo.setFullName(map.get("Full_Name") != null ? map.get(
						"Full_Name").toString() : "");
				if (!empCode.equals(vo.getEmpCode())) {
					empCode = vo.getEmpCode();
					vo.setEmpCodeTemp(vo.getEmpCode());
				} else {
					vo.setFullName("");
					vo.setEmpCodeTemp("");
					empCode = vo.getEmpCode();
				}
				vo.setEmpCode(empCode);
				vo.setCodeSeq(map.get("code_seq") != null ? map.get("code_seq")
						.toString() : "");
				vo.setOrgCode(map.get("org_code") != null ? map.get("org_code")
						.toString() : "");
				vo.setOrgDesc(map.get("org_desc") != null ? map.get("org_desc")
						.toString() : "");
				vo.setLevelCode(map.get("level_code") != null ? map.get(
						"level_code").toString() : "");
				vo.setDivCode(map.get("div_code") != null ? map.get("div_code")
						.toString() : "");
				vo.setDivDesc(map.get("div_desc") != null ? map.get("div_desc")
						.toString() : "");
				vo.setAreaCode(map.get("area_code") != null ? map.get(
						"area_code").toString() : "");
				vo.setAreaDesc(map.get("area_desc") != null ? map.get(
						"area_desc").toString() : "");
				vo.setSecCode(map.get("sec_code") != null ? map.get("sec_code")
						.toString() : "");
				vo.setSecDesc(map.get("sec_desc") != null ? map.get("sec_desc")
						.toString() : "");
				vo.setOrgWorkCode(map.get("orgwork_code") != null ? map.get(
						"orgwork_code").toString() : "");
				vo.setOrgWorkDesc(map.get("orgwork_desc") != null ? map.get(
						"orgwork_desc").toString() : "");
				vo.setTotal(map.get("total") != null ? map.get("total")
						.toString() : "");

				obj[1] = map.get("month1") != null ? map.get("month1")
						.toString() : "";
				obj[2] = map.get("month2") != null ? map.get("month2")
						.toString() : "";
				obj[3] = map.get("month3") != null ? map.get("month3")
						.toString() : "";
				obj[4] = map.get("month4") != null ? map.get("month4")
						.toString() : "";
				obj[5] = map.get("month5") != null ? map.get("month5")
						.toString() : "";
				obj[6] = map.get("month6") != null ? map.get("month6")
						.toString() : "";
				obj[7] = map.get("month7") != null ? map.get("month7")
						.toString() : "";
				obj[8] = map.get("month8") != null ? map.get("month8")
						.toString() : "";
				obj[9] = map.get("month9") != null ? map.get("month9")
						.toString() : "";
				obj[10] = map.get("month10") != null ? map.get("month10")
						.toString() : "";
				obj[11] = map.get("month11") != null ? map.get("month11")
						.toString() : "";
				obj[12] = map.get("month12") != null ? map.get("month12")
						.toString() : "";

				if (monthFrom != null && !monthFrom.equals("")) {
					checkMonthFrom = Integer.parseInt(monthFrom);
				}
				if (monthTo != null && !monthTo.equals("")) {
					checkMonthTo = Integer.parseInt(monthTo);
				}
				k = 0;
				x = 1;
				y = 1;
				if (choice.equals("1")) {// case same year
					tempYear = vo.getWorkYear() == null ? "" : vo.getWorkYear()
							.toString();
					vo.setTempBudgetYear(tempYear.substring(2));
					while (checkMonthFrom <= checkMonthTo) {
						k = checkMonthFrom;
						switch (y) {
						case 1:
							vo.setMonth1((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName1(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 2:
							vo.setMonth2((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName2(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 3:
							vo.setMonth3((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName3(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 4:
							vo.setMonth4((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName4(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 5:
							vo.setMonth5((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName5(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 6:
							vo.setMonth6((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName6(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 7:
							vo.setMonth7((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName7(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 8:
							vo.setMonth8((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName8(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 9:
							vo.setMonth9((obj[k] != null ? (String) obj[k] : ""));
							vo.setMonthName9(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 10:
							vo.setMonth10((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName10(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 11:
							vo.setMonth11((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName11(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						case 12:
							vo.setMonth12((obj[k] != null ? (String) obj[k]
									: ""));
							vo.setMonthName12(convertMonth(k)
									+ vo.getTempBudgetYear());
							break;
						}
						checkMonthFrom++;
						y++;
					}
				} else if (choice.equals("2")) {// case difference year

					if (workYearFrom != null && workYearFrom.intValue() != 0) {// ������u�
						tempYear = workYearFrom.toString();
						vo.setTempBudgetYear(tempYear.substring(2));
						while (checkMonthFrom <= 12) {
							k = checkMonthFrom;

							switch (y) {
							case 1:
								vo.setMonth1((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName1(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 2:
								vo.setMonth2((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName2(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 3:
								vo.setMonth3((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName3(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 4:
								vo.setMonth4((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName4(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 5:
								vo.setMonth5((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName5(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 6:
								vo.setMonth6((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName6(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 7:
								vo.setMonth7((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName7(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 8:
								vo.setMonth8((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName8(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 9:
								vo.setMonth9((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName9(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 10:
								vo.setMonth10((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName10(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 11:
								vo.setMonth11((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName11(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							case 12:
								vo.setMonth12(((obj)[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName12(convertMonth(k)
										+ vo.getTempBudgetYear());
								break;
							}
							checkMonthFrom++;
							y++;
						}
					}
					if (workYearTo != null && workYearTo.intValue() != 0) {// ������ش
						tempYearTo = workYearTo.toString();
						vo.setTempBudgetYear2(tempYearTo.substring(2));
						while (x <= checkMonthTo) {
							k = x;
							switch (y) {
							case 1:
								vo.setMonth1((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName1(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 2:
								vo.setMonth2((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName2(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 3:
								vo.setMonth3((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName3(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 4:
								vo.setMonth4((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName4(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 5:
								vo.setMonth5((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName5(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 6:
								vo.setMonth6((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName6(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 7:
								vo.setMonth7((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName7(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 8:
								vo.setMonth8((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName8(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 9:
								vo.setMonth9((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName9(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 10:
								vo.setMonth10((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName10(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 11:
								vo.setMonth11((obj[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName11(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							case 12:
								vo.setMonth12(((obj)[k] != null ? (String) obj[k]
										: ""));
								vo.setMonthName12(convertMonth(k)
										+ vo.getTempBudgetYear2());
								break;
							}
							x++;
							y++;
						}
					}

				}
				if (vo.getMonth1() == null) {
					vo.setMonth1("-");
				} else {
					if (vo.getMonth1().equals("0.0")) {
						vo.setMonth1("&nbsp;");
					}
				}
				if (vo.getMonth2() == null) {
					vo.setMonth2("-");
				} else {
					if (vo.getMonth2().equals("0.0")) {
						vo.setMonth2("&nbsp;");
					}
				}
				if (vo.getMonth3() == null) {
					vo.setMonth3("-");
				} else {
					if (vo.getMonth3().equals("0.0")) {
						vo.setMonth3("&nbsp;");
					}
				}
				if (vo.getMonth4() == null) {
					vo.setMonth4("-");
				} else {
					if (vo.getMonth4().equals("0.0")) {
						vo.setMonth4("&nbsp;");
					}
				}
				if (vo.getMonth5() == null) {
					vo.setMonth5("-");
				} else {
					if (vo.getMonth5().equals("0.0")) {
						vo.setMonth5("&nbsp;");
					}
				}
				if (vo.getMonth6() == null) {
					vo.setMonth6("-");
				} else {
					if (vo.getMonth6().equals("0.0")) {
						vo.setMonth6("&nbsp;");
					}
				}
				if (vo.getMonth7() == null) {
					vo.setMonth7("-");
				} else {
					if (vo.getMonth7().equals("0.0")) {
						vo.setMonth7("&nbsp;");
					}
				}
				if (vo.getMonth8() == null) {
					vo.setMonth8("-");
				} else {
					if (vo.getMonth8().equals("0.0")) {
						vo.setMonth8("&nbsp;");
					}
				}
				if (vo.getMonth9() == null) {
					vo.setMonth9("-");
				} else {
					if (vo.getMonth9().equals("0.0")) {
						vo.setMonth9("&nbsp;");
					}
				}
				if (vo.getMonth10() == null) {
					vo.setMonth10("-");
				} else {
					if (vo.getMonth10().equals("0.0")) {
						vo.setMonth10("&nbsp;");
					}
				}
				if (vo.getMonth11() == null) {
					vo.setMonth11("-");
				} else {
					if (vo.getMonth11().equals("0.0")) {
						vo.setMonth11("&nbsp;");
					}
				}
				if (vo.getMonth12() == null) {
					vo.setMonth12("-");
				} else {
					if (vo.getMonth12().equals("0.0")) {
						vo.setMonth12("&nbsp;");
					}
				}

				if (vo.getMonthName1() == null) {
					vo.setMonthName1("-");
				}
				;
				if (vo.getMonthName2() == null) {
					vo.setMonthName2("-");
				}
				;
				if (vo.getMonthName3() == null) {
					vo.setMonthName3("-");
				}
				;
				if (vo.getMonthName4() == null) {
					vo.setMonthName4("-");
				}
				;
				if (vo.getMonthName5() == null) {
					vo.setMonthName5("-");
				}
				;
				if (vo.getMonthName6() == null) {
					vo.setMonthName6("-");
				}
				;
				if (vo.getMonthName7() == null) {
					vo.setMonthName7("-");
				}
				;
				if (vo.getMonthName8() == null) {
					vo.setMonthName8("-");
				}
				;
				if (vo.getMonthName9() == null) {
					vo.setMonthName9("-");
				}
				;
				if (vo.getMonthName10() == null) {
					vo.setMonthName10("-");
				}
				;
				if (vo.getMonthName11() == null) {
					vo.setMonthName11("-");
				}
				;
				if (vo.getMonthName12() == null) {
					vo.setMonthName12("-");
				}
				;
				dataList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * method ���������Ѻ�ŧ��͹����繵���Ţ���繵���ѡ�� �١���¡���� method doPrintReport
	 * 
	 * @param numOfMonth
	 * @return
	 */
	public String convertMonth(int k) {
		String conM = "";

		switch (k) {
		case 1:
			conM = "�.�.";
			break;
		case 2:
			conM = "�.�.";
			break;
		case 3:
			conM = "��.�.";
			break;
		case 4:
			conM = "��.�.";
			break;
		case 5:
			conM = "�.�.";
			break;
		case 6:
			conM = "��.�.";
			break;
		case 7:
			conM = "�.�.";
			break;
		case 8:
			conM = "�.�.";
			break;
		case 9:
			conM = "�.�.";
			break;
		case 10:
			conM = "�.�.";
			break;
		case 11:
			conM = "�.�.";
			break;
		case 12:
			conM = "�.�.";
			break;
		case 0:
			conM = "-";
			break;
		}

		return conM;
	}
}
