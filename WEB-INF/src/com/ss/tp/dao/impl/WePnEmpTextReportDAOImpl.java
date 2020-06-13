/*
 * Created on 26 �.�. 2549
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
import com.ss.tp.dao.WePnEmpTextReportDAO; // import com.ss.tp.dao.TaReportDAO;
// import com.ss.tp.dto.TaReportVO;
import com.ss.tp.dto.WePnEmpTextReportVO;
import com.ss.tp.model.PnOrganization; // import
// com.ss.tp.model.VTaMonthEmpWork;
import com.ss.tp.model.VWePnEmpText;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WePnEmpTextReportDAOImpl extends HibernateDaoSupport implements
		WePnEmpTextReportDAO, Serializable {
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
	 * @param workMonth
	 * @param orgCodeSeqTo
	 * @param userId
	 * @param orgCodeSeqFrom
	 * @param ouCode
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {
		VWePnEmpText vtm;
		WePnEmpTextReportVO trpVO;
		List list = new ArrayList();
		List listReport = new ArrayList();
		String sql = "from VWePnEmpText as vt " + " where vt.codeSeq in( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '" + userId + "'" + " ) "
				+ " and vt.areaCode IS NOT NULL ";

		if (!orgCodeSeqFrom.equals("") && !orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode between " + orgCodeSeqFrom + " and "
					+ orgCodeSeqTo;
		} else if (!orgCodeSeqFrom.equals("") && orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode >= " + orgCodeSeqFrom;
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql = sql + "  and vt.vwePnEmpTextPK.empCode between '"
					+ empCodeFrom + "' and  '" + empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and vt.vwePnEmpTextPK.empCode >='" + empCodeFrom + "'";
		}
		sql += " order by   nvl(vt.orgWorkCode,nvl(vt.secCode,nvl(vt.divCode,''))),vt.areaCode,"
				+ " vt.orgWorkCode, vt.vwePnEmpTextPK.empCode,vt.vwePnEmpTextPK.workCode ";
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
			// String totalTemp = "";
			String orgCode = "";
			String orgCodeTemp = "";
			for (Iterator itt = listReport.iterator(); itt.hasNext();) {
				vtm = (VWePnEmpText) itt.next();
				trpVO = new WePnEmpTextReportVO();
				BeanUtils.copyProperties(trpVO, vtm.getVwePnEmpTextPK());
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
	 * method ����Ѻ Query �����Ũҡ Table V_TA_MONTH_EMP_WORK ������ sheet report
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
	public List findListSecPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {

		VWePnEmpText vtm;
		WePnEmpTextReportVO trpVO;

		List listReport = null;
		List list = new ArrayList();
		String sql = " select distinct(vt.secCode), vt.secDesc,vt.divCode,vt.divDesc,vt.areaCode,vt.areaDesc "
				+ " from VWePnEmpTextPK as vt "
				+ " where vt.codeSeq in( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '"
				+ userId
				+ "'"
				+ " )"
				+ " and vt.areaCode IS NOT NULL ";

		if (!orgCodeSeqFrom.equals("") && !orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode between " + orgCodeSeqFrom + " and "
					+ orgCodeSeqTo;
		} else if (!orgCodeSeqFrom.equals("") && orgCodeSeqTo.equals("")) {
			sql += " and vt.orgCode >= " + orgCodeSeqFrom;
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql = sql + "  and vt.vwePnEmpTextPK.empCode between '"
					+ empCodeFrom + "' and  '" + empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and vt.vwePnEmpTextPK.empCode >='" + empCodeFrom + "'";
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
				trpVO = new WePnEmpTextReportVO();
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

	public List findListPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		List list = null;
		List dataList = new ArrayList();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		WePnEmpTextReportVO vo;
		/*
		 * int checkMonthFrom = 0; int checkMonthTo = 0;
		 */
		int k = 0;
		int x = 1;
		String empCode = "";
		/*
		 * String tempYear = ""; String tempYearTo = ""; int numMonthForm =
		 * monthFrom.length(); int numMonthTo = monthTo.length(); if
		 * (numMonthForm < 2) { monthFrom = "0" + monthFrom; } if (numMonthTo <
		 * 2) { monthTo = "0" + monthTo; }
		 */
		int numSubQuey = 0;

		String sql = "";
		int y = 1;// ����Ѻ��˹���͹�������˹�� web
		Map map = null;
		try {

			sql = " select a.ou_code,a.emp_code,a.Full_Name,a.preN,a.eng_name,a.eng_lastname,a.empl,a.pol,"
					+ "\n a.code_seq,a.org_code,a.org_desc,a.level_code,a.div_code,a.div_desc,a.div_short, "
					+ " NVL(a.area_code,a.div_code) area_code, NVL(a.area_desc,a.div_desc) area_desc,"
					+ " NVL(a.sec_code,NVL(a.area_code,a.div_code)) sec_code,NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)) sec_desc,"
					+ " a.orgwork_code,a.orgwork_desc,a.area_seq,a.sec_seq,a.work_seq "
					+ "\n from "
					+ "\n ( "
					+ "\n	 	select t.ou_code,t.emp_code,decode(t.prename,'1','Miss','2','Mrs.','3','Mr.') as preN,t.eng_name,t.eng_lastname,pn.level_code as empl, "
					+ "\n			(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name,pp.position_short||' '||decode(to_number(pn.level_code),'0',null,to_number(pn.level_code)) as pol, "
					+ "\n			pn.code_seq_act code_seq,org.org_code,org.org_desc, "
					+ "\n			org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc,org.area_seq,org.sec_seq,org.work_seq "
					+ "\n	from WE_PN_EMPLOYEE_TEXT t,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org,pn_position pp "
					+ "\n	where t.ou_code=pn.ou_code "
					+ "\n           and pn.ou_code=pp.ou_code "
					+ "\n           and        pn.gwork_code   = pp.gwork_code "
					+ "\n           and pn.position_code=pp.position_code "
					+ "\n		and t.EMP_CODE=pn.EMP_CODE "
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

			sql += "\n ) a   "
					+ "\n   group by  a.ou_code,a.emp_code, a.full_name,a.preN,a.eng_name,a.eng_lastname,a.empl,a.pol,a.code_seq,a.org_code,a.org_desc, "
					+ "\n	a.level_code,a.div_code,a.div_desc,a.div_short, NVL(a.area_code,a.div_code) , NVL(a.area_desc,a.div_desc),NVL(a.sec_code,NVL(a.area_code,a.div_code)),NVL(a.sec_desc,NVL(a.area_desc,a.div_desc)),a.orgwork_code,a.orgwork_desc,a.area_seq,a.sec_seq,a.work_seq  "
					+ "\n order by a.div_code,nvl(a.area_seq,0),nvl(a.sec_seq,0),nvl(a.work_seq,0),to_number(a.empl) desc,a.emp_code ";

			Object[] obj = null;
			System.out.println("\n\n sql==>" + sql);
			list = jdbcTemplate.queryForList(sql);
			System.out
					.println("--------------------------- Query Finish AAA----------------------");
			empCode = "";
			for (int i = 0; i < list.size(); i++) {
				map = new HashMap();
				map = (Map) list.get(i);
				// obj = new Object[13];
				vo = new WePnEmpTextReportVO();
				vo.setOuCode(map.get("ou_code") != null ? map.get("ou_code")
						.toString() : "");
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
				vo.setPreN(map.get("preN") != null ? map.get("preN").toString()
						: "");
				vo.setEngName(map.get("eng_name") != null ? map.get("eng_name")
						.toString() : "");
				vo.setEngLastname(map.get("eng_lastname") != null ? map.get(
						"eng_lastname").toString() : "");
				vo.setPol(map.get("pol") != null ? map.get("pol").toString()
						: "");
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

	public List findListSecPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		List list = null;
		List dataList = new ArrayList();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		WePnEmpTextReportVO vo;
		// int checkMonthFrom = 0;
		// int checkMonthTo = 0;
		// int k = 0;
		// int x = 1;
		String empCode = "";
		// String tempYear = "";
		// String tempYearTo = "";
		/*
		 * int numMonthForm = monthFrom.length(); int numMonthTo =
		 * monthTo.length(); if (numMonthForm < 2) { monthFrom = "0" +
		 * monthFrom; } if (numMonthTo < 2) { monthTo = "0" + monthTo; }
		 */
		int numSubQuey = 0;
		// Vector dataFrom = new Vector();
		// Vector dataTo = new Vector();
		// Vector dataWorkCode = new Vector();

		String sql = "";
		int y = 1;// ����Ѻ��˹���͹�������˹�� web
		Map map = null;
		try {
			sql += " order by  nvl(vt.secCode,nvl(vt.divCode,''))";

			sql = " select distinct NVL(a.area_code,a.div_code) area_code,"
					+ " NVL(a.area_desc,a.div_desc) area_desc,"
					+ " a.div_code,a.div_desc,a.div_short  "
					+ " from "
					+ " ( "
					+ "	 select	t.ou_code,t.emp_code,  "
					+ "	(pr.PREFIX_NAME || ' ' ||pn.FIRST_NAME || ' ' || pn.LAST_NAME) as Full_Name, "
					+ "	pn.code_seq_act code_seq,org.org_code,org.org_desc, "
					+ "	org.level_code,org.div_code,org.div_desc,org.div_short,org.sec_code,org.sec_desc,org.work_code orgwork_code,org.work_desc orgwork_desc,org.area_code,org.area_desc  "
					+ "	from WE_PN_EMPLOYEE_TEXT t,PN_EMPLOYEE pn, DB_PRE_SUFF pr,PN_ORGANIZATION org  "
					+ "		where t.ou_code=pn.ou_code "
					+ "		and t.EMP_CODE=pn.EMP_CODE "
					// + " and t.ou_code=ts.ou_code "
					// + " and t.WORK_CODE=ts.WORK_CODE "
					+ "		and pn.PRE_NAME=pr.PRE_SUFF_CODE "
					+ "		AND        pn.ou_code	= org.ou_code "
					+ "		AND        pn.code_seq_act	= org.code_seq "
					+ "		 "
					+ "		and 	   pn.CODE_SEQ_ACT  in ( select   vpn.CODE_SEQ "
					+ "				   				   	 		  			   	   from V_PN_ORGANIZATION_SECURITY  vpn "
					+ "															   where  vpn.USER_ID='" + userId + "'"
					+ "			) ";

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
				vo = new WePnEmpTextReportVO();
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
