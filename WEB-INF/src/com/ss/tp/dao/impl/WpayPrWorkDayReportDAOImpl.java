/*
 * Created on 26 ï¿½.ï¿½. 2549
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
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.WpayPrWorkDayReportDAO;
import com.ss.tp.dto.TaReportVO;
import com.ss.tp.model.PnOrganization;
import com.ss.tp.model.VTaMonthEmpWork;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WpayPrWorkDayReportDAOImpl extends HibernateDaoSupport implements
		WpayPrWorkDayReportDAO, Serializable {
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
	 * method ï¿½ï¿½ï¿½ï¿½Ñº Query ï¿½ï¿½ï¿½ï¿½ï¿½Å¨Ò¡ Table V_TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param orgCodeSeqTo
	 * @param userId
	 * @param orgCodeSeqFrom
	 * @param ouCode
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListTaReport(String ouCode, String userId,
			Integer workYear, String orgCodeSeqFrom, String orgCodeSeqTo,
			String empCodeFrom, String empCodeTo) {
		VTaMonthEmpWork vtm;
		TaReportVO trpVO;
		List list = new ArrayList();
		List listReport = new ArrayList();
		String sql = "from VTaMonthEmpWork as vt " + " where vt.codeSeq in( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '" + userId + "'" + " ) "
				+ " and vt.areaCode IS NOT NULL ";

		if (workYear != null && workYear.intValue() != 0) {
			sql = sql + " and vt.vtaMonthEmpWorkPK.workYear  = " + workYear;

		}
		if (!orgCodeSeqFrom.equals("") && !orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode between " + orgCodeSeqFrom + " and "
					+ orgCodeSeqTo;
		} else if (!orgCodeSeqFrom.equals("") && orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode >= " + orgCodeSeqFrom;
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql = sql + "  and vt.vtaMonthEmpWorkPK.empCode between '"
					+ empCodeFrom + "' and  '" + empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and vt.vtaMonthEmpWorkPK.empCode >='" + empCodeFrom + "'";
		}
		sql += " order by   nvl(vt.orgWorkCode,nvl(vt.secCode,nvl(vt.divCode,''))),vt.areaCode,"
				+ " vt.orgWorkCode, vt.vtaMonthEmpWorkPK.empCode,vt.vtaMonthEmpWorkPK.workCode ";
		// sql+=" order by nvl(vt.orgWorkCode,nvl(vt.secCode,vt.divCode))," +
		// " vt.vtaMonthEmpWorkPK.empCode,vt.vtaMonthEmpWorkPK.workCode ";

		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listReport = qq.list();
			Map obj = null;
			String empCode = "";
			String empCodeTemp = "";
			String totalTemp = "";
			String orgCode = "";
			String orgCodeTemp = "";
			for (Iterator itt = listReport.iterator(); itt.hasNext();) {
				vtm = (VTaMonthEmpWork) itt.next();
				trpVO = new TaReportVO();
				BeanUtils.copyProperties(trpVO, vtm.getVtaMonthEmpWorkPK());
				BeanUtils.copyProperties(trpVO, vtm);
				orgCodeTemp = trpVO.getOrgCode();
				if (!orgCode.equals(orgCodeTemp)) {
					orgCode = orgCodeTemp;
				} else {
					trpVO.setOrgDesc("");
					orgCode = orgCodeTemp;
				}
				empCodeTemp = trpVO.getEmpCode();
				if (!empCode.equals(empCodeTemp)) {

					empCode = empCodeTemp;
				} else {
					trpVO.setFullName("");
					empCode = empCodeTemp;
				}
				trpVO.setEmpCode(empCode);
				list.add(trpVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñº Query ï¿½ï¿½ï¿½ï¿½ï¿½Å¨Ò¡ Table V_TA_MONTH_EMP_WORK ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ sheet report
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param orgCodeSeqTo
	 * @param userId
	 * @param orgCodeSeqFrom
	 * @param ouCode
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListSecTaReport(String ouCode, String userId,
			Integer workYear, String orgCodeSeqFrom, String orgCodeSeqTo,
			String empCodeFrom, String empCodeTo) {

		VTaMonthEmpWork vtm;
		TaReportVO trpVO;

		List listReport = null;
		List list = new ArrayList();
		String sql = " select distinct(vt.secCode), vt.secDesc,vt.divCode,vt.divDesc,vt.areaCode,vt.areaDesc "
				+ " from VTaMonthEmpWork as vt "
				+ " where vt.codeSeq in( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '"
				+ userId
				+ "'"
				+ " )"
				+ " and vt.areaCode IS NOT NULL ";

		if (workYear != null && workYear.intValue() != 0) {
			sql = sql + " and vt.vtaMonthEmpWorkPK.workYear  = " + workYear;

		}
		if (!orgCodeSeqFrom.equals("") && !orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode between " + orgCodeSeqFrom + " and "
					+ orgCodeSeqTo;
		} else if (!orgCodeSeqFrom.equals("") && orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode >= " + orgCodeSeqFrom;
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql = sql + "  and vt.vtaMonthEmpWorkPK.empCode between '"
					+ empCodeFrom + "' and  '" + empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and vt.vtaMonthEmpWorkPK.empCode >='" + empCodeFrom + "'";
		}
		sql += " order by  nvl(vt.secCode,nvl(vt.divCode,''))";

		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			listReport = qq.list();
			String secCode = "";
			String secDesc = "";
			String divCode = "";
			String divDesc = "";
			String areaCode = "";
			String areaDesc = "";
			for (int i = 0; i < listReport.size(); i++) {
				Object[] obj = (Object[]) listReport.get(i);
				secCode = (String) obj[0];
				secDesc = (String) obj[1];
				divCode = (String) obj[2];
				divDesc = (String) obj[3];
				areaCode = (String) obj[4];
				areaDesc = (String) obj[5];
				trpVO = new TaReportVO();
				trpVO.setSecCode(secCode);
				trpVO.setSecDesc(secDesc);
				trpVO.setDivCode(divCode);
				trpVO.setDivDesc(divDesc);
				trpVO.setAreaCode(areaCode);
				trpVO.setAreaDesc(areaDesc);
				list.add(trpVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List findListTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice, Vector statusWorkCode, Vector dataStatusFrom,
			Vector dataStatusTo, Integer sumDonKeyFrom, Integer sumDonKeyTo,
			String workCodeFrom, String workCodeTo) {
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
		int numMonthForm = monthFrom.length();
		int numMonthTo = monthTo.length();
		if (numMonthForm < 2) {
			monthFrom = "0" + monthFrom;
		}
		if (numMonthTo < 2) {
			monthTo = "0" + monthTo;
		}
		int numSubQuey = 0;
		Vector dataFrom = new Vector();
		Vector dataTo = new Vector();
		Vector dataWorkCode = new Vector();
		for (int i = 0; i < dataStatusFrom.size(); i++) {
			if (!dataStatusFrom.elementAt(i).equals("")) {
				if (!dataStatusTo.elementAt(i).equals("")) {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				} else {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				}
			} else {
				if (!dataStatusTo.elementAt(i).equals("")) {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				}
			}
		}

		String sql = "";
		int y = 1;// ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ë¹ï¿½ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ë¹ï¿½ï¿½ web
		Map map = null;
		try {

			sql = " select a.ou_code,a.work_year,a.work_code,a.work_desc,a.emp_code,a.Full_Name, "
					+ "\n a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.div_short, "
					+ " NVL(a.area_code,a.div_code) area_code, NVL(a.area_desc,a.div_desc) area_desc,"
					+ " NVL(a.sec_code,NVL(a.area_code,a.div_code)) sec_code,NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)) sec_desc,"
					+ " a.orgwork_code,a.orgwork_desc, "
					+ "\n nvl(to_char(sum(a.day_month1),'fm990.0'),0) month1, "
					+ "\n nvl(to_char(sum(a.day_month2),'fm990.0'),0) month2, "
					+ "\n nvl(to_char(sum(a.day_month3),'fm990.0'),0) month3, "
					+ "\n nvl(to_char(sum(a.day_month4),'fm990.0'),0) month4, "
					+ "\n nvl(to_char(sum(a.day_month5),'fm990.0'),0) month5, "
					+ "\n nvl(to_char(sum(a.day_month6),'fm990.0'),0) month6, "
					+ "\n nvl(to_char(sum(a.day_month7),'fm990.0'),0) month7, "
					+ "\n nvl(to_char(sum(a.day_month8),'fm990.0'),0) month8, "
					+ "\n nvl(to_char(sum(a.day_month9),'fm990.0'),0) month9, "
					+ "\n nvl(to_char(sum(a.day_month10),'fm990.0'),0) month10, "
					+ "\n nvl(to_char(sum(a.day_month11),'fm990.0'),0) month11, "
					+ "\n nvl(to_char(sum(a.day_month12),'fm990.0'),0) month12, "
					+ "\n to_char(sum(a.day_month1)+sum(a.day_month2)+sum(a.day_month3)+sum(a.day_month4)+sum(a.day_month5)+sum(a.day_month6)+ "
					+ "\n		sum(a.day_month7)+sum(a.day_month8)+sum(a.day_month9)+sum(a.day_month10)+sum(a.day_month11)+sum(a.day_month12),'fm9990.0') total "
					+ "\n from "
					+ "\n ( "
					+ "\n	 	select t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month, "
					+ "\n			decode(t.work_month,1,t.total_days,0) day_month1, "
					+ "\n			decode(t.work_month,2,t.total_days,0) day_month2, "
					+ "\n			decode(t.work_month,3,t.total_days,0) day_month3, "
					+ "\n			decode(t.work_month,4,t.total_days,0) day_month4, "
					+ "\n			decode(t.work_month,5,t.total_days,0) day_month5, "
					+ "\n			decode(t.work_month,6,t.total_days,0) day_month6, "
					+ "\n			decode(t.work_month,7,t.total_days,0) day_month7, "
					+ "\n			decode(t.work_month,8,t.total_days,0) day_month8, "
					+ "\n			decode(t.work_month,9,t.total_days,0) day_month9, "
					+ "\n			decode(t.work_month,10,t.total_days,0) day_month10, "
					+ "\n			decode(t.work_month,11,t.total_days,0) day_month11, "
					+ "\n			decode(t.work_month,12,t.total_days,0) day_month12, "
					+ "\n			(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
					+ "\n			pn.code_seq_act code_seq,org.org_code,org.org_desc, "
					+ "\n			org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc "
					+ "\n	from TA_MONTH_EMP_WORK t,TA_STATUS_WORK ts,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org "
					+ "\n	where t.ou_code=pn.ou_code "
					+ "\n		and t.EMP_CODE=pn.EMP_CODE "
					+ "\n		and t.ou_code=ts.ou_code "
					+ "\n		and	t.WORK_CODE=ts.WORK_CODE "
					+ "\n		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
					+ "\n		and        pn.ou_code	= org.ou_code "
					+ "\n		and        pn.code_seq_act	= org.code_seq "
					+ "\n		 "
					+ "\n		and 	   pn.code_seq_act  in ( select   vpn.CODE_SEQ "
					+ "\n				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
					+ "\n															   where  vpn.USER_ID='"
					+ userId
					+ "'"
					+ "\n									) ";
			if ((sumDonKeyFrom.intValue() != 0 || sumDonKeyTo.intValue() != 0)
					|| numSubQuey > 0) {
				sql += "\n and t.EMP_CODE in( select emp_code"
						+ "\n					  from(";
				if (numSubQuey > 0) {
					for (int i = 0; i < dataFrom.size(); i++) {
						sql += "\n 					select emp_code,sum(total_days) total"
								+ "\n  						from    ta_month_emp_work ";
						if ((workYearFrom != null && workYearFrom.intValue() != 0)
								&& (workYearTo != null && workYearTo.intValue() != 0)) {
							if (!monthFrom.equals("") && !monthTo.equals("")) {
								sql += "\n 			where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
										+ workYearFrom
										+ "/"
										+ monthFrom
										+ "' and '"
										+ workYearTo
										+ "/"
										+ monthTo + "'";
							}
						}// close if year
						sql += "\n				    and work_code ='"
								+ dataWorkCode.elementAt(i) + "'" + // size
								// ï¿½ï¿½Ò¡Ñº
								// workYearFrom
								// ï¿½ï¿½ï¿½ï¿½
								" \n 						group by emp_code ";
						if (dataTo.elementAt(i) != null
								&& !dataTo.elementAt(i).equals("")) {// for
							// check
							// number
							// of
							// day
							if (dataFrom.elementAt(i) != null
									&& !dataFrom.elementAt(i).equals("")) {
								sql += "\n  			having sum(total_days) between "
										+ dataFrom.elementAt(i) + " and "
										+ dataTo.elementAt(i);
							} else {
								sql += "\n  			having sum(total_days) between 0 and "
										+ dataTo.elementAt(i);
							}
						} else {
							sql += "\n 				having sum(total_days) >= "
									+ dataFrom.elementAt(i);
						}
						if ((i + 1) != dataFrom.size()) {
							sql += "\n 				union ";
						} else {
							if (sumDonKeyFrom.intValue() != 0
									|| sumDonKeyTo.intValue() != 0) {
								sql += "\n  			union ";
							}
						}
					}// close for
				}
				if (sumDonKeyFrom.intValue() != 0
						|| sumDonKeyTo.intValue() != 0) {// for ï¿½Ò»ï¿½ï¿½ï¿½+ï¿½Ò¡Ô¨
					sql += "\n 						select emp_code,sum(total_days) total "
							+ "\n 							from    ta_month_emp_work ";
					if ((workYearFrom != null && workYearFrom.intValue() != 0)
							&& (workYearTo != null && workYearTo.intValue() != 0)) {
						if (!monthFrom.equals("") && !monthTo.equals("")) {
							sql += "\n 				where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
									+ workYearFrom
									+ "/"
									+ monthFrom
									+ "' and '"
									+ workYearTo
									+ "/"
									+ monthTo
									+ "'";
						}
					}// close if year
					sql += "\n 						and    ( work_code = (select ill_code from  ta_status_control where ou_code = '"
							+ ouCode
							+ "') "
							+ "\n 							or work_code = (select bus_code from  ta_status_control where ou_code = '"
							+ ouCode
							+ "') ) "
							+ "\n 							group by emp_code "
							+ " \n							having sum(total_days) between '"
							+ sumDonKeyFrom + "' and '" + sumDonKeyTo + "'";
				}

				sql += "\n					)" + "\n					) ";

			}
			// sql+="\n and org.area_code IS NOT NULL ";

			if ((workYearFrom != null && workYearFrom.intValue() != 0)
					&& (workYearTo != null && workYearTo.intValue() != 0)) {
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += "\n and trim(t.work_year||'/'||TRIM(TO_CHAR(t.work_month,'00')))  between '"
							+ workYearFrom
							+ "/"
							+ monthFrom
							+ "'"
							+ "\n and '"
							+ workYearTo + "/" + monthTo + "'";
				}
			}

			if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
				sql += "\n and  org.org_code between '" + orgCodeFrom
						+ "' and  '" + orgCodeTo + "'";
			} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
				sql += "\n and  org.org_code  >='" + orgCodeFrom + "'";
			}
			if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
				sql += "\n and t.emp_code between '" + empCodeFrom + "' and  '"
						+ empCodeTo + "'";
			} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
				sql += " and t.emp_code >='" + empCodeFrom + "'";
			}
			if (!workCodeFrom.equals("%") && !workCodeTo.equals("%")) {
				sql += "\n and t.WORK_CODE between '" + workCodeFrom
						+ "' and  '" + workCodeTo + "'";
			} else if (!workCodeFrom.equals("%") && workCodeTo.equals("%")) {
				sql += " and t.WORK_CODE >='" + workCodeFrom + "'";
			}
			sql += "\n ) a   "
					+ "\n   group by  a.ou_code,a.work_year,a.work_code,a.emp_code,a.work_desc, a.full_name,a.code_seq,a.org_code,a.org_desc, "
					+ "\n	  	  		a.level_code,a.div_code,a.div_desc,a.div_short, NVL(a.area_code,a.div_code) , NVL(a.area_desc,a.div_desc),NVL(a.sec_code,NVL(a.area_code,a.div_code)),NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)),a.orgwork_code,a.orgwork_desc "
					+
					// "\n a.level_code,a.div_code,a.div_desc, a.area_code
					// ,a.area_desc,a.sec_code,a.sec_desc,a.orgwork_code,a.orgwork_desc
					// "+
					// "\n order by
					// nvl(a.orgwork_code,nvl(a.sec_code,nvl(a.div_code,''))),a.area_code,a.org_code,a.emp_code,a.work_code
					// ";
					"\n order by a.div_code, NVL(a.area_code,a.div_code),NVL(a.sec_code,NVL(a.area_code,a.div_code)),a.org_code,a.emp_code,a.work_code ";

			Object[] obj = null;
			System.out.println("\n\n sql==>" + sql);
			list = jdbcTemplate.queryForList(sql);
			System.out
					.println("--------------------------- Query Finish AAA----------------------");
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
				vo.setDivShort(map.get("div_short") != null ? map.get(
						"div_short").toString() : "");
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

					if (workYearFrom != null && workYearFrom.intValue() != 0) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½uï¿½
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
					if (workYearTo != null && workYearTo.intValue() != 0) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ø´
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
						vo.setMonth1("");
					}
				}
				if (vo.getMonth2() == null) {
					vo.setMonth2("-");
				} else {
					if (vo.getMonth2().equals("0.0")) {
						vo.setMonth2("");
					}
				}
				if (vo.getMonth3() == null) {
					vo.setMonth3("-");
				} else {
					if (vo.getMonth3().equals("0.0")) {
						vo.setMonth3("");
					}
				}
				if (vo.getMonth4() == null) {
					vo.setMonth4("-");
				} else {
					if (vo.getMonth4().equals("0.0")) {
						vo.setMonth4("");
					}
				}
				if (vo.getMonth5() == null) {
					vo.setMonth5("-");
				} else {
					if (vo.getMonth5().equals("0.0")) {
						vo.setMonth5("");
					}
				}
				if (vo.getMonth6() == null) {
					vo.setMonth6("-");
				} else {
					if (vo.getMonth6().equals("0.0")) {
						vo.setMonth6("");
					}
				}
				if (vo.getMonth7() == null) {
					vo.setMonth7("-");
				} else {
					if (vo.getMonth7().equals("0.0")) {
						vo.setMonth7("");
					}
				}
				if (vo.getMonth8() == null) {
					vo.setMonth8("-");
				} else {
					if (vo.getMonth8().equals("0.0")) {
						vo.setMonth8("");
					}
				}
				if (vo.getMonth9() == null) {
					vo.setMonth9("-");
				} else {
					if (vo.getMonth9().equals("0.0")) {
						vo.setMonth9("");
					}
				}
				if (vo.getMonth10() == null) {
					vo.setMonth10("-");
				} else {
					if (vo.getMonth10().equals("0.0")) {
						vo.setMonth10("");
					}
				}
				if (vo.getMonth11() == null) {
					vo.setMonth11("-");
				} else {
					if (vo.getMonth11().equals("0.0")) {
						vo.setMonth11("");
					}
				}
				if (vo.getMonth12() == null) {
					vo.setMonth12("-");
				} else {
					if (vo.getMonth12().equals("0.0")) {
						vo.setMonth12("");
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
		System.out
				.println("--------------------------- Query Finish ----------------------");
		return dataList;
	}

	public List findListSecTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice, Vector statusWorkCode, Vector dataStatusFrom,
			Vector dataStatusTo, Integer sumDonKeyFrom, Integer sumDonKeyTo,
			String workCodeFrom, String workCodeTo) {
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
		int numMonthForm = monthFrom.length();
		int numMonthTo = monthTo.length();
		if (numMonthForm < 2) {
			monthFrom = "0" + monthFrom;
		}
		if (numMonthTo < 2) {
			monthTo = "0" + monthTo;
		}
		int numSubQuey = 0;
		Vector dataFrom = new Vector();
		Vector dataTo = new Vector();
		Vector dataWorkCode = new Vector();
		for (int i = 0; i < dataStatusFrom.size(); i++) {
			if (!dataStatusFrom.elementAt(i).equals("")) {
				if (!dataStatusTo.elementAt(i).equals("")) {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				} else {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				}
			} else {
				if (!dataStatusTo.elementAt(i).equals("")) {
					numSubQuey++;
					dataFrom.addElement(dataStatusFrom.elementAt(i));
					dataTo.addElement(dataStatusTo.elementAt(i));
					dataWorkCode.addElement(statusWorkCode.elementAt(i));
				}
			}
		}
		String sql = "";
		int y = 1;// ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ë¹ï¿½ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ë¹ï¿½ï¿½ web
		Map map = null;
		try {
			sql += " order by  nvl(vt.secCode,nvl(vt.divCode,''))";

			sql = " select distinct NVL(a.area_code,a.div_code) area_code,"
					+ " NVL(a.area_desc,a.div_desc) area_desc,"
					+ " a.div_code,a.div_desc,a.div_short  "
					+ " from "
					+ " ( "
					+ "	 select	t.ou_code,t.work_year,t.emp_code,t.work_code,ts.work_desc,t.work_month,  "
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
					+ "	org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
					+ "	from TA_MONTH_EMP_WORK t,TA_STATUS_WORK ts,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
					+ "		where t.ou_code=pn.ou_code "
					+ "		and t.EMP_CODE=pn.EMP_CODE "
					+ "		and t.ou_code=ts.ou_code "
					+ "		and	t.WORK_CODE=ts.WORK_CODE "
					+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
					+ "		AND        pn.ou_code	= org.ou_code "
					+ "		AND        pn.code_seq_act	= org.code_seq "
					+ "		 "
					+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ "
					+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
					+ "															   where  vpn.USER_ID='" + userId + "'"
					+ "			) ";
			if ((sumDonKeyFrom.intValue() != 0 || sumDonKeyTo.intValue() != 0)
					|| numSubQuey > 0) {
				sql += "\n and t.EMP_CODE in( select emp_code"
						+ "\n					  from(";
				if (numSubQuey > 0) {
					for (int i = 0; i < dataFrom.size(); i++) {
						sql += "\n 					select emp_code,sum(total_days) total"
								+ "\n  						from    ta_month_emp_work ";
						if ((workYearFrom != null && workYearFrom.intValue() != 0)
								&& (workYearTo != null && workYearTo.intValue() != 0)) {
							if (!monthFrom.equals("") && !monthTo.equals("")) {
								sql += "\n 			where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
										+ workYearFrom
										+ "/"
										+ monthFrom
										+ "' and '"
										+ workYearTo
										+ "/"
										+ monthTo + "'";
							}
						}// close if year
						sql += "\n				    and work_code ='"
								+ dataWorkCode.elementAt(i) + "'" + // size
								// ï¿½ï¿½Ò¡Ñº
								// workYearFrom
								// ï¿½ï¿½ï¿½ï¿½
								" \n 						group by emp_code ";
						if (dataTo.elementAt(i) != null
								&& !dataTo.elementAt(i).equals("")) {// for
							// check
							// number
							// of
							// day
							if (dataFrom.elementAt(i) != null
									&& !dataFrom.elementAt(i).equals("")) {
								sql += "\n  			having sum(total_days) between "
										+ dataFrom.elementAt(i) + " and "
										+ dataTo.elementAt(i);
							} else {
								sql += "\n  			having sum(total_days) between 0 and "
										+ dataTo.elementAt(i);
							}
						} else {
							sql += "\n 				having sum(total_days) >= "
									+ dataFrom.elementAt(i);
						}
						if ((i + 1) != dataFrom.size()) {
							sql += "\n 				union ";
						} else {
							if (sumDonKeyFrom.intValue() != 0
									|| sumDonKeyTo.intValue() != 0) {
								sql += "\n  			union ";
							}
						}
					}// close for
				}
				if (sumDonKeyFrom.intValue() != 0
						|| sumDonKeyTo.intValue() != 0) {// for ï¿½Ò»ï¿½ï¿½ï¿½+ï¿½Ò¡Ô¨
					sql += "\n 						select emp_code,sum(total_days) total "
							+ "\n 							from    ta_month_emp_work ";
					if ((workYearFrom != null && workYearFrom.intValue() != 0)
							&& (workYearTo != null && workYearTo.intValue() != 0)) {
						if (!monthFrom.equals("") && !monthTo.equals("")) {
							sql += "\n 				where trim(work_year||'/'||trim(TO_CHAR(work_month,'00')))  between '"
									+ workYearFrom
									+ "/"
									+ monthFrom
									+ "' and '"
									+ workYearTo
									+ "/"
									+ monthTo
									+ "'";
						}
					}// close if year
					sql += "\n 						and    ( work_code = (select ill_code from  ta_status_control where ou_code = '"
							+ ouCode
							+ "') "
							+ "\n 							or work_code = (select bus_code from  ta_status_control where ou_code = '"
							+ ouCode
							+ "') ) "
							+ "\n 							group by emp_code "
							+ " \n							having sum(total_days) between '"
							+ sumDonKeyFrom + "' and '" + sumDonKeyTo + "'";
				}

				sql += "\n					)" + "\n					) ";

			}
			// sql+="\n and org.area_code IS NOT NULL ";

			if ((workYearFrom != null && workYearFrom.intValue() != 0)
					&& (workYearTo != null && workYearTo.intValue() != 0)) {
				if (!monthFrom.equals("") && !monthTo.equals("")) {
					sql += " and TRIM(t.work_year||'/'||TRIM(TO_CHAR(t.work_month,'00')))  BETWEEN '"
							+ workYearFrom
							+ "/"
							+ monthFrom
							+ "'"
							+ " AND '"
							+ workYearTo + "/" + monthTo + "'";
				}
			}
			if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
				sql += " and  org.org_code between '" + orgCodeFrom
						+ "' and  '" + orgCodeTo + "'";
			} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
				sql += " and  org.org_code  >='" + orgCodeFrom + "'";
			}
			if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
				sql += " and t.emp_code between '" + empCodeFrom + "' and  '"
						+ empCodeTo + "'";
			} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
				sql += " and t.emp_code >='" + empCodeFrom + "'";
			}
			if (!workCodeFrom.equals("%") && !workCodeTo.equals("%")) {
				sql += "\n and t.WORK_CODE between '" + workCodeFrom
						+ "' and  '" + workCodeTo + "'";
			} else if (!workCodeFrom.equals("%") && workCodeTo.equals("%")) {
				sql += " and t.WORK_CODE >='" + workCodeFrom + "'";
			}
			sql += " ) a   "
					+ "   group by  a.sec_code,a.sec_desc,a.div_code,a.div_desc,a.div_short,a.area_code,a.area_desc "
					+ " order by  NVL(a.area_code,a.div_code)";

			Object[] obj = null;
			System.out.println("\n\n sql==>" + sql);
			list = jdbcTemplate.queryForList(sql);
			empCode = "";
			for (int i = 0; i < list.size(); i++) {
				map = new HashMap();
				map = (Map) list.get(i);
				obj = new Object[13];
				vo = new TaReportVO();
				// vo.setSecCode(map.get("sec_code")!=null?map.get("sec_code").toString():"");
				// vo.setSecDesc(map.get("sec_desc")!=null?map.get("sec_desc").toString():"");
				vo.setDivCode(map.get("div_code") != null ? map.get("div_code")
						.toString() : "");
				vo.setDivDesc(map.get("div_desc") != null ? map.get("div_desc")
						.toString() : "");
				vo.setDivShort(map.get("div_short") != null ? map.get(
						"div_short").toString() : "");
				vo.setAreaCode(map.get("area_code") != null ? map.get(
						"area_code").toString() : "");
				vo.setAreaDesc(map.get("area_desc") != null ? map.get(
						"area_desc").toString() : "");

				dataList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ï¿½ï¿½Í¹ï¿½ï¿½ï¿½ï¿½ç¹µï¿½ï¿½ï¿½Å¢ï¿½ï¿½ï¿½ç¹µï¿½ï¿½ï¿½Ñ¡ï¿½ï¿½ ï¿½Ù¡ï¿½ï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ method doPrintReport
	 * 
	 * @param numOfMonth
	 * @return
	 */
	public String convertMonth(int k) {
		String conM = "";

		switch (k) {
		case 1:
			conM = "Á¡ÃÒ¤Á";
			break;
		case 2:
			conM = "¡ØÁÀÒ¾Ñ¹¸ì";
			break;
		case 3:
			conM = "ÁÕ¹Ò¤Á";
			break;
		case 4:
			conM = "àÁÉÒÂ¹";
			break;
		case 5:
			conM = "¾ÄÉÀÒ¤Á";
			break;
		case 6:
			conM = "ÁÔ¶Ø¹ÒÂ¹";
			break;
		case 7:
			conM = "¡Ã¡®Ò¤Á";
			break;
		case 8:
			conM = "ÊÔ§ËÒ¤Á";
			break;
		case 9:
			conM = "¡Ñ¹ÂÒÂ¹";
			break;
		case 10:
			conM = "µØÅÒ¤Á";
			break;
		case 11:
			conM = "¾ÄÈ¨Ô¡ÒÂ¹";
			break;
		case 12:
			conM = "¸Ñ¹ÇÒ¤Á";
			break;
		case 0:
			conM = "-";
			break;
		}

		return conM;
	}

	public String findAreaDesc(String orgCode) {
		String nameAreaDesc = "";
		List list = null;
		PnOrganization pn = new PnOrganization();
		if (orgCode != null && !orgCode.equals("")) {
			list = this.getHibernateTemplate().find(
					"from PnOrganization p where p.orgCode ='" + orgCode + "'");
			if (list.size() > 0) {
				pn = (PnOrganization) list.get(0);
				nameAreaDesc = pn.getAreaDesc();
			}
		}
		return nameAreaDesc;
	}

	public String findAreaDescTwo(String areaCode) {
		String nameAreaDesc = "";
		List list = null;
		PnOrganization pn = new PnOrganization();
		if (areaCode != null && !areaCode.equals("")) {
			list = this.getHibernateTemplate().find(
					"from PnOrganization p where p.areaCode ='" + areaCode
							+ "'");
			if (list.size() > 0) {
				pn = (PnOrganization) list.get(0);
				nameAreaDesc = pn.getAreaDesc();
			}
		}
		return nameAreaDesc;
	}
}
