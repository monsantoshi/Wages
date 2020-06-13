/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgPrEmployeeDAO;
import com.ss.tp.dto.CTRWRP201VO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.WgEmployeeDetailVO;
import com.ss.tp.dto.WgPayRollEmployeeVO;
import com.ss.tp.dto.WgPrEmployeeVO;
import com.ss.tp.model.WgPrEmployee;
import com.ss.tp.model.WgPrEmployeeText;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPrEmployeeDAOImpl extends HibernateDaoSupport implements
		WgPrEmployeeDAO, Serializable {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// private Log logger = LogFactory.getLog(this.getClass());

	public String[] findMaxYearPeriod(String ouCode) {
		System.out.println("findMaxYearPeriod");
		String year = "";
		String section = "";

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" SELECT a.wgPrEmployeePK.year || MAX(a.wgPrEmployeePK.period) ");
			sql.append(" FROM   WgPrEmployee a ");
			sql.append(" WHERE  a.wgPrEmployeePK.ouCode = '" + ouCode + "' ");
			sql.append(" and a.wgPrEmployeePK.year = ( select max(b.wgPrEmployeePK.year) from WgPrEmployee b ) ");
			sql.append(" group by a.wgPrEmployeePK.year ");
			List ls = this.getHibernateTemplate().find(sql.toString());
			if (ls != null && ls.size() > 0) {
				String rs = (String) ls.get(0);
				year = rs.substring(0, 4);
				section = rs.substring(4, rs.length());
			}
			System.out.println("YEAR: " + year + "  PERIOD: " + section);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (year == null) {
			year = "";
			section = "";
		}
		String[] val = new String[2];
		val[0] = year;
		val[1] = section;

		return val;
	}

	public List findWgPrEmpBySecurity(String userId, String ouCode,
			String year, String period) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from WgPrEmployee a ");
		hql.append(" where ");
		hql.append(" a.wgPrEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and a.wgPrEmployeePK.year = " + year);
		hql.append(" and a.wgPrEmployeePK.period = " + period);
		hql.append(" and a.wgPrEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and b.pk.year = " + year);
		hql.append("	 and b.pk.period = " + period);
		hql.append(" ) ");
		hql.append(" order by a.wgPrEmployeePK.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				WgPrEmployee element = (WgPrEmployee) ls.get(i);
				WgPrEmployeeVO vo = new WgPrEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getWgPrEmployeePK());
					ls.set(i, vo);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new Exception("can't copy properties : "
							+ e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw new Exception("can't copy properties : "
							+ e.getMessage());
				}
			}
		}
		return ls;
	}

	public List findWgPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {

		StringBuffer hql = new StringBuffer(0);
		hql.append(" from WgPrEmployee a ");
		hql.append(" where ");
		hql.append(" a.wgPrEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and a.wgPrEmployeePK.year = " + year);

		if (period != null && !period.trim().equals(""))
			hql.append(" and a.wgPrEmployeePK.period = " + period);

		hql.append(" and a.wgPrEmployeePK.empCode >= '" + fromEmpCode + "' ");
		hql.append(" and a.wgPrEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and b.pk.year = " + year);
		hql.append("	 and b.pk.period = a.wgPrEmployeePK.period ");
		hql.append(" ) ");
		hql.append(" order by a.wgPrEmployeePK.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				WgPrEmployee element = (WgPrEmployee) ls.get(i);
				WgPrEmployeeVO vo = new WgPrEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getWgPrEmployeePK());
					ls.set(i, vo);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new Exception("can't copy properties : "
							+ e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw new Exception("can't copy properties : "
							+ e.getMessage());
				}
			}
		}
		return ls;
	}

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append(" and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append(" and pno.orgCode <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append(" and pre.wgPrEmployeePK.empCode >= '"
					+ empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append(" and pre.wgPrEmployeePK.empCode <= '" + empCodeTo
					+ "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select DISTINCT pne.wgEmployeePK.empCode, ");
		hql.append(" sf.prefixName, ");
		hql.append(" pne.firstName, ");
		hql.append(" pne.lastName, ");
		hql.append(" pno.orgCode, ");
		hql.append(" pno.orgDesc, ");
		hql.append(" pne.dutyCode,et.flagStatus");
		hql.append(" FROM WgPrEmployee pre, ");
		hql.append(" PnOrganization pno, DbPreSuff sf , WgEmployee pne ,WgPrEmployeeText et");
		hql.append(" where ");
		hql.append(" pre.wgPrEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.wgPrEmployeePK.year = " + year);
		hql.append(" and pre.wgPrEmployeePK.period = " + period);
		hql.append(" and pre.wgPrEmployeePK.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VWgPrEmployeeSecurity ");
		hql.append(" 	where pk.ouCode = '" + ouCode + "' ");
		hql.append(" 	and pk.userId = '" + userId + "' ");
		hql.append("    and pk.year = " + year);
		hql.append("	and pk.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.wgPrEmployeePK.empCode = pne.wgEmployeePK.empCode ");
		hql.append(" and pre.wgPrEmployeePK.ouCode = pne.wgEmployeePK.ouCode ");
		hql.append(" and pre.wgPrEmployeePK.year = et.year ");
		hql.append(" and pre.wgPrEmployeePK.period = et.period ");
		hql.append(" and pre.wgPrEmployeePK.empCode = et.empCode ");
		hql.append(" and pre.wgPrEmployeePK.ouCode = et.ouCode ");
		hql.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
		hql.append(" and pre.wgPrEmployeePK.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by  pno.orgCode , pne.wgEmployeePK.empCode ,decode(et.flagStatus,'D',1,'I',2,'A',3,4)");

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(page * recPerPage)
				.setMaxResults(recPerPage).list();
		for (int i = 0; i < empList.size(); i++) {
			Object[] r = (Object[]) empList.get(i);

			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			String dutyCode = (String) r[6];
			String dutyDesc = "&nbsp;";
			String statusText = (String) r[7];
			if ((String) r[6] != null) {
				StringBuffer hqlDuty = new StringBuffer(0);
				hqlDuty.append(" select wgd.dutyName ");
				hqlDuty.append(" from WgDuty wgd ");
				hqlDuty.append(" where wgd.wgDutyPK.ouCode = '" + ouCode + "' ");
				hqlDuty.append(" and wgd.wgDutyPK.dutyCode = '" + (String) r[6]
						+ "' ");
				List dutyList = this.getHibernateTemplate().find(
						hqlDuty.toString());
				dutyDesc = (String) dutyList.get(0);
			}

			WgPayRollEmployeeVO vo = new WgPayRollEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			vo.setOrgCode(orgCode);
			vo.setOrgDesc(orgDesc);
			vo.setDutyCode(dutyCode);
			vo.setDutyDesc(dutyDesc);
			vo.setStatusText(statusText);

			empList.set(i, vo);
		}
		return empList;
	}

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);
		/*
		 * if (orgCodeFrom != null && !orgCodeFrom.equals(""))
		 * criteria.append("and pno.orgCode >= '" + orgCodeFrom+ "' ");
		 * 
		 * if (orgCodeTo != null && !orgCodeTo.equals("")) criteria.append("and
		 * pno.orgCode <= '" + orgCodeTo+ "' ");
		 * 
		 * if (empCodeFrom != null && !empCodeFrom.equals(""))
		 * criteria.append("and pre.wgPrEmployeePK.empCode >= '" + empCodeFrom +
		 * "' ");
		 * 
		 * if (empCodeTo != null && !empCodeTo.equals("")) criteria.append("and
		 * pre.wgPrEmployeePK.empCode <= '" + empCodeTo + "' ");
		 * 
		 * StringBuffer hql = new StringBuffer(0);
		 * 
		 * hql.append( " select count(DISTINCT
		 * pre.wgPrEmployeePK.empCode+et.flagStatus) "); hql.append(" from
		 * WgPrEmployee pre, "); hql.append(" WgEmployee pne, PnOrganization
		 * pno,WgPrEmployeeText et " ); hql.append(" where "); hql.append("
		 * pre.wgPrEmployeePK.ouCode = '" + ouCode + "' "); hql.append(" and
		 * pre.wgPrEmployeePK.year = " + year); hql.append(" and
		 * pre.wgPrEmployeePK.period = " + period); hql.append(" and
		 * pre.wgPrEmployeePK.empCode in ( "); hql.append(" select pk.empCode
		 * "); hql.append(" from VWgPrEmployeeSecurity "); hql.append(" where
		 * pk.ouCode = '" + ouCode + "' "); hql.append(" and pk.userId = '" +
		 * userId + "' "); hql.append(" and pk.year = " + year); hql.append("
		 * and pk.period = "+ period); hql.append(" ) "); hql.append(criteria);
		 * hql.append(" and pre.wgPrEmployeePK.empCode =
		 * pne.wgEmployeePK.empCode " ); hql.append(" and
		 * pre.wgPrEmployeePK.ouCode = pne.wgEmployeePK.ouCode " ); hql.append("
		 * and pre.wgPrEmployeePK.year = et.year "); hql.append(" and
		 * pre.wgPrEmployeePK.period = et.period "); hql.append(" and
		 * pre.wgPrEmployeePK.empCode = et.empCode "); hql.append(" and
		 * pre.wgPrEmployeePK.ouCode = et.ouCode "); hql.append(" and
		 * pre.codeSeqWork = pno.pk.codeSeq "); hql.append(" and
		 * pre.wgPrEmployeePK.ouCode = pno.pk.ouCode ");
		 */

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.org_Code >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.org_Code <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.emp_Code >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.emp_Code <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);

		hql.append(" select count(DISTINCT pre.emp_Code||' '||et.flag_Status) ");
		hql.append(" from Wg_Pr_Employee pre, ");
		hql.append(" Wg_Employee pne, Pn_Organization pno,Wg_Pr_Employee_Text et ");
		hql.append(" where ");
		hql.append(" pre.ou_Code = '" + ouCode + "' ");
		hql.append(" and pre.year = " + year);
		hql.append(" and pre.period = " + period);
		hql.append(" and pre.emp_Code in ( ");
		hql.append(" 	select v.emp_Code ");
		hql.append(" 	from V_Wg_Pr_Employee_Security v");
		hql.append(" 	where v.ou_Code = '" + ouCode + "' ");
		hql.append(" 	and v.user_Id = '" + userId + "' ");
		hql.append("    and v.year = " + year);
		hql.append("	and v.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.emp_Code = pne.emp_Code ");
		hql.append(" and pre.ou_Code = pne.ou_Code ");
		hql.append(" and pre.year = et.year ");
		hql.append(" and pre.period = et.period ");
		hql.append(" and pre.emp_Code = et.emp_Code ");
		hql.append(" and pre.ou_Code = et.ou_Code ");
		hql.append(" and pre.code_Seq_Work = pno.code_Seq ");
		hql.append(" and pre.ou_Code = pno.ou_Code ");
		int rs = this.jdbcTemplate.queryForInt(hql.toString());
		// Integer rs =
		// (Integer)this.getSession().createQuery(hql.toString()).uniqueResult();
		return new Integer(rs);
	}

	public PnEmployeeDetailVO findWgPrEmpNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);

			hql.append(" select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME , ");
			hql.append(" pn.LAST_NAME , pn.ACCOUNT , ");
			hql.append(" pn.LEVEL_CODE ,  pn.IDATE , orgPn.DIV_DESC , ");
			hql.append(" orgPn.SEC_DESC , orgPr.ORG_CODE , orgPr.AREA_DESC ||' '||orgPr.SEC_DESC as ORG_DESC , ");
			hql.append(" pr.CODE_SEQ_WORK , pr.CODE_SEQ_WORK ");
			hql.append(" from  PN_ORGANIZATION orgPn, PN_ORGANIZATION orgPr, DB_PRE_SUFF pre, WG_PR_EMPLOYEE pr, WG_EMPLOYEE pn ");
			hql.append(" where pn.OU_CODE='" + ouCode + "' ");
			hql.append(" and pn.EMP_CODE='" + empCode + "' ");
			hql.append(" and pr.year = " + year);
			hql.append(" and pr.period = " + period);
			hql.append(" and (pn.EMP_CODE not in  ( ");
			hql.append("	select vpn.EMP_CODE ");
			hql.append("	from V_WG_PR_EMPLOYEE_SECURITY vpn ");
			hql.append("	where vpn.OU_CODE='" + ouCode + "' ");
			hql.append("	and vpn.USER_ID='" + userId + "' ");
			hql.append("  	and vpn.year = " + year);
			hql.append("	and vpn.period = " + period + " )");
			hql.append(" ) and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append(" and pn.ou_code = pr.ou_code ");
			hql.append(" and pn.emp_code = pr.emp_code ");
			hql.append(" and pr.OU_CODE=orgPr.OU_CODE ");
			hql.append(" and pr.CODE_SEQ_WORK=orgPr.CODE_SEQ ");
			hql.append(" and pn.ou_code = orgPn.ou_code ");
			hql.append(" and pn.code_seq = orgPn.code_seq ");

			// System.out.println("sql : " + hql.toString());

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			PnEmployeeDetailVO vo = new PnEmployeeDetailVO();
			if (ls != null && ls.size() > 0) {
				ListOrderedMap element = (ListOrderedMap) ls.get(0);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
				String account = (String) element.get("ACCOUNT");
				// String position = (String) element.get("POSITION_SHORT");
				String levelCode = (String) element.get("LEVEL_CODE");
				// String adminDesc = (String) element.get("ADMIN_DESC");

				String iDateStr = "";
				if (element.get("IDATE") != null) {
					Date iDate = new Date(
							((Timestamp) element.get("IDATE")).getTime());
					SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
							new Locale("th", "TH"));
					iDateStr = smdf.format(iDate);
				}
				String divDesc = (String) element.get("DIV_DESC");
				String secDesc = (String) element.get("SEC_DESC");
				String orgCode = (String) element.get("ORG_CODE");
				String orgDesc = (String) element.get("ORG_DESC");

				String codeSeqAct = ((BigDecimal) element.get("CODE_SEQ_WORK"))
						.toString();
				String codeSeq = ((BigDecimal) element.get("CODE_SEQ_WORK"))
						.toString();

				vo.setOuCode(ouCode);
				vo.setEmpCode(empCodeStr);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setAccount(account);
				// vo.setPosition(position);
				vo.setLevelCode(levelCode);
				// vo.setAdminDesc(adminDesc);
				vo.setPDate(iDateStr);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public WgEmployeeDetailVO findWgPrEmpInSecue(String userId, String ouCode,
			String empCode, String year, String period) throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);

			hql.append(" select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME , ");
			hql.append(" pn.LAST_NAME , pn.ACCOUNT , ");
			hql.append(" pn.LEVEL_CODE ,  pn.IDATE , orgPn.DIV_DESC , ");
			hql.append(" orgPn.SEC_DESC , orgPr.ORG_CODE , orgPr.AREA_DESC ||' '||orgPr.SEC_DESC as ORG_DESC , ");
			hql.append(" pr.CODE_SEQ_WORK , pr.CODE_SEQ_WORK, ");
			hql.append(" pn.DUTY_CODE , wgd.DUTY_NAME ");
			hql.append(" from  PN_ORGANIZATION orgPn, PN_ORGANIZATION orgPr, DB_PRE_SUFF pre, WG_PR_EMPLOYEE pr, WG_EMPLOYEE pn, WG_DUTY wgd ");
			hql.append(" where pn.OU_CODE='" + ouCode + "' ");
			hql.append(" and pn.EMP_CODE='" + empCode + "' ");
			hql.append(" and pr.year = " + year);
			hql.append(" and pr.period = " + period);
			hql.append(" and (pn.EMP_CODE  in  ( ");
			hql.append("	select vpn.EMP_CODE ");
			hql.append("	from V_WG_PR_EMPLOYEE_SECURITY vpn ");
			hql.append("	where vpn.OU_CODE='" + ouCode + "' ");
			hql.append("	and vpn.USER_ID='" + userId + "' ");
			hql.append("  	and vpn.year = " + year);
			hql.append("	and vpn.period = " + period + " )");
			hql.append(" ) and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append(" and pn.ou_code = pr.ou_code ");
			hql.append(" and pn.emp_code = pr.emp_code ");
			hql.append(" and pr.OU_CODE=orgPr.OU_CODE ");
			hql.append(" and pr.CODE_SEQ_WORK=orgPr.CODE_SEQ ");
			hql.append(" and pn.ou_code = orgPn.ou_code ");
			hql.append(" and pn.code_seq = orgPn.code_seq ");
			hql.append(" and pn.OU_CODE=wgd.OU_CODE(+) ");
			hql.append(" and pn.DUTY_CODE=wgd.DUTY_CODE(+) ");

			// System.out.println("sql : " + hql.toString());

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			WgEmployeeDetailVO vo = new WgEmployeeDetailVO();
			if (ls != null && ls.size() > 0) {
				ListOrderedMap element = (ListOrderedMap) ls.get(0);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
				String account = (String) element.get("ACCOUNT");
				// String position = (String) element.get("POSITION_SHORT");
				String levelCode = (String) element.get("LEVEL_CODE");
				String dutyDesc = (String) element.get("DUTY_NAME");
				// String adminDesc = (String) element.get("ADMIN_DESC");

				String iDateStr = "";
				if (element.get("IDATE") != null) {
					Date iDate = new Date(
							((Timestamp) element.get("IDATE")).getTime());
					SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
							new Locale("th", "TH"));
					iDateStr = smdf.format(iDate);
				}
				String divDesc = (String) element.get("DIV_DESC");
				String secDesc = (String) element.get("SEC_DESC");
				String orgCode = (String) element.get("ORG_CODE");
				String orgDesc = (String) element.get("ORG_DESC");

				String codeSeqAct = ((BigDecimal) element.get("CODE_SEQ_WORK"))
						.toString();
				String codeSeq = ((BigDecimal) element.get("CODE_SEQ_WORK"))
						.toString();

				vo.setOuCode(ouCode);
				vo.setEmpCode(empCodeStr);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setAccount(account);
				// vo.setPosition(position);
				vo.setLevelCode(levelCode);
				// vo.setAdminDesc(adminDesc);
				vo.setPDate(iDateStr);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
				vo.setDutyDesc(dutyDesc);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public WgPrEmployeeVO findWgPrEmp(String ouCode, String year,
			String period, String yearPrev, String periodPrev, String empCode,
			String userId) {

		WgPrEmployeeVO vo = new WgPrEmployeeVO();
		try {
			StringBuffer hql = new StringBuffer(0);
			StringBuffer hql2 = new StringBuffer(0);

			hql.append(" from WgPrEmployee pr ");
			hql.append(" where ");
			hql.append(" pr.wgPrEmployeePK.ouCode = '" + ouCode + "' ");
			hql.append(" and pr.wgPrEmployeePK.year = " + yearPrev);
			hql.append(" and pr.wgPrEmployeePK.period = " + periodPrev);
			hql.append(" and pr.wgPrEmployeePK.empCode  = '" + empCode + "'");

			List ls = this.getHibernateTemplate().find(hql.toString());

			hql2.append(" from WgPrEmployeeText prt ");
			hql2.append(" where ");
			hql2.append(" prt.ouCode = '" + ouCode + "' ");
			hql2.append(" and prt.year = " + year);
			hql2.append(" and prt.period = " + period);
			hql2.append(" and prt.empCode  = '" + empCode + "'");
			hql2.append(" order by prt.keySeq  ");

			List empTextList = this.getHibernateTemplate()
					.find(hql2.toString());

			if (ls != null && ls.size() > 0) {
				WgPrEmployee emp = (WgPrEmployee) ls.get(0);

				vo.setOuCode(emp.getWgPrEmployeePK().getOuCode());
				vo.setYear(emp.getWgPrEmployeePK().getYear());
				vo.setPeriod(emp.getWgPrEmployeePK().getPeriod());
				vo.setEmpCode(emp.getWgPrEmployeePK().getEmpCode());
				vo.setCodeSeqWork(emp.getCodeSeqWork());
				vo.setTaxId(emp.getTaxId());
				vo.setMarriedStatus(emp.getMarriedStatus());
				vo.setPayStatus(emp.getPayStatus());
				vo.setBankId(emp.getBankId());
				vo.setCostChild(emp.getCostChild());
				vo.setChildStudy(emp.getChildStudy());
				vo.setChildNoStudy(emp.getChildNoStudy());
				vo.setCostLife(emp.getCostLife());
				vo.setGundanFlag(emp.getGundanFlag());
				vo.setDebtLife(emp.getDebtLife());
				vo.setDebtLoan(emp.getDebtLoan());
				vo.setDonate(emp.getDonate());
				vo.setOther(emp.getOther());
				vo.setIncomeTax(emp.getIncomeTax());
				vo.setOldSalary(emp.getOldSalary());
				vo.setNewSalary(emp.getNewSalary());
				vo.setAdjOldsal(emp.getAdjOldsal());
				vo.setAdjNewsal(emp.getAdjNewsal());
				vo.setGundanAmt(emp.getGundanAmt());
				vo.setFlagPr(emp.getFlagPr());
				vo.setDeductAmt(emp.getDeductAmt());
				vo.setSeqRec(emp.getSeqRec());
				vo.setFlagMotherSpouse(emp.getFlagMotherSpouse());
				vo.setFlagFatherSpouse(emp.getFlagFatherSpouse());
				vo.setFlagMother(emp.getFlagMother());
				vo.setFlagFather(emp.getFlagFather());
				vo.setLtf(emp.getLtf());
				vo.setRmf(emp.getRmf());
				vo.setPensionFund(emp.getPensionFund());
				vo.setTeacherFund(emp.getTeacherFund());
				vo.setOverageSpouse(emp.getOverageSpouse());
				vo.setOverage(emp.getOverage());
				vo.setCompensateLabour(emp.getCompensateLabour());
				vo.setConfirmFlag(emp.getConfirmFlag());

				for (int i = 0; i < empTextList.size(); i++) {
					WgPrEmployeeText empText = (WgPrEmployeeText) empTextList
							.get(i);
					if (empText.getTaxId() != null)
						vo.setTaxId(empText.getTaxId());
					if (empText.getMarriedStatus() != null)
						vo.setMarriedStatus(empText.getMarriedStatus());
					if (empText.getPayStatus() != null)
						vo.setPayStatus(empText.getPayStatus());
					if (empText.getBankId() != null)
						vo.setBankId(empText.getBankId());
					if (empText.getCostChild() != null)
						vo.setCostChild(empText.getCostChild());
					if (empText.getChildStudy() != null)
						vo.setChildStudy(empText.getChildStudy());
					if (empText.getChildNoStudy() != null)
						vo.setChildNoStudy(empText.getChildNoStudy());
					if (empText.getCostLife() != null)
						vo.setCostLife(empText.getCostLife());
					if (empText.getGundanFlag() != null)
						vo.setGundanFlag(empText.getGundanFlag());
					if (empText.getDebtLife() != null)
						vo.setDebtLife(empText.getDebtLife());
					if (empText.getDebtLoan() != null)
						vo.setDebtLoan(empText.getDebtLoan());
					if (empText.getDonate() != null)
						vo.setDonate(empText.getDonate());
					if (empText.getOther() != null)
						vo.setOther(empText.getOther());
					if (empText.getIncomeTax() != null)
						vo.setIncomeTax(empText.getIncomeTax());
					if (empText.getOldSalary() != null)
						vo.setOldSalary(empText.getOldSalary());
					if (empText.getNewSalary() != null)
						vo.setNewSalary(empText.getNewSalary());
					if (empText.getAdjOldsal() != null)
						vo.setAdjOldsal(empText.getAdjOldsal());
					if (empText.getAdjNewsal() != null)
						vo.setAdjNewsal(empText.getAdjNewsal());
					if (empText.getGundanAmt() != null)
						vo.setGundanAmt(empText.getGundanAmt());
					if (empText.getFlagPr() != null)
						vo.setFlagPr(empText.getFlagPr());
					if (empText.getDeductAmt() != null)
						vo.setDeductAmt(empText.getDeductAmt());

					if (empText.getFlagMotherSpouse() != null)
						vo.setFlagMotherSpouse(empText.getFlagMotherSpouse());
					if (empText.getFlagFatherSpouse() != null)
						vo.setFlagFatherSpouse(empText.getFlagFatherSpouse());
					if (empText.getFlagMother() != null)
						vo.setFlagMother(empText.getFlagMother());
					if (empText.getFlagFather() != null)
						vo.setFlagFather(empText.getFlagFather());
					if (empText.getLtf() != null)
						vo.setLtf(empText.getLtf());
					if (empText.getRmf() != null)
						vo.setRmf(empText.getRmf());
					if (empText.getPensionFund() != null)
						vo.setPensionFund(empText.getPensionFund());
					if (empText.getTeacherFund() != null)
						vo.setTeacherFund(empText.getTeacherFund());
					if (empText.getOverageSpouse() != null)
						vo.setOverageSpouse(empText.getOverageSpouse());
					if (empText.getOverage() != null)
						vo.setOverage(empText.getOverage());
					if (empText.getCompensateLabour() != null)
						vo.setCompensateLabour(empText.getCompensateLabour());
					if (empText.getConfirmFlag() != null)
						vo.setConfirmFlag(empText.getConfirmFlag());

				}
			}
		} catch (Exception e) {
			System.out.println("Not found WgPrEmployee");
			e.printStackTrace();
		}

		return vo;
	}

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period) {
		List list = new ArrayList();
		List listdetail = new ArrayList();
		System.out
				.println("***************************** BEGIN ************************************ :: "
						+ ouCode
						+ " :: "
						+ userId
						+ " :: "
						+ year
						+ " :: "
						+ period);
		String sql = "";
		sql = " select div_code,div_desc, area_code,area_desc,cnt_emp,emp_A,emp_I,emp_D"
				+ " , inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A, amt_A"
				+ " , cnt_R,  amt_R,cnt_B,amt_B, cnt_S, amt_S	"
				+ " from ("
				+ "select x.div_code,x.div_desc, x.area_code,x.area_desc, sum(cnt_emp) cnt_emp, sum(emp_A) emp_A, sum(emp_I) emp_I, sum(emp_D) emp_D"
				+ " , '00' inc_dec_code, '00' inc_dec_name, 0 cnt_rec, 0 amt_rec, 0 cnt_N, 0 amt_N, 0 cnt_A, 0 amt_A"
				+ " , 0 cnt_R, 0 amt_R, 0 cnt_B, 0 amt_B, 0 cnt_S, 0 amt_S	"
				+ "	from (	"
				+ "	select c.div_code,c.div_desc, nvl(c.area_code,'$') area_code,c.area_desc, count(a.emp_code) cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D 	"
				+ "	from wg_pr_employee a, su_user_organization b, pn_organization c					"
				+ "	where a.ou_code = '"
				+ ouCode
				+ "' 	"
				+ "	and a.year = "
				+ year
				+ "	and a.period = "
				+ period
				+ "	 and b.user_id = '"
				+ userId
				+ "' 	"
				+ "	 and a.ou_code = b.ou_code	"
				+ "	and a.code_seq_work = b.code_seq	"
				+ "	and a.ou_code = c.ou_code	"
				+ "	and a.code_seq_work = c.code_seq"
				+ "  group by c.div_code,c.div_desc, c.area_code,c.area_desc "
				+ " 	union all "
				+ "	 select c.div_code,c.div_desc,nvl(c.area_code,'$'),c.area_desc, 0 cnt_emp, decode(a.flag_status,'A',1,0) emp_A, decode(a.flag_status,'I',1,0) emp_I, decode(a.flag_status,'D',1,0) emp_D"
				+ " 	from wg_pr_employee_text a, su_user_organization b, pn_organization c ,wg_pr_employee pr "
				+ " 	where a.ou_code = '"
				+ ouCode
				+ "' "
				+ " 	and a.year = "
				+ year
				+ "  	and a.period = "
				+ period
				+ " 	and b.user_id = '"
				+ userId
				+ "' "
				+ "	and a.ou_code = pr.ou_Code "
				+ "	and a.year    = pr.year "
				+ "	and a.period  = pr.period "
				+ "	and a.emp_code = pr.emp_code "
				+ "	and pr.ou_code = b.ou_code  	"
				+ "	and pr.code_seq_work = b.code_seq "
				+ "	and b.ou_code = c.ou_code	"
				+ "	and b.code_seq = c.code_seq	"
				+ "	) x	 group by x.div_code,x.div_desc, x.area_code,x.area_desc	"
				+ "	union all	"
				+ "	select z.div_code,z.div_desc, z.area_code,z.area_desc, 0 cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D	"
				+ "	 , z.inc_dec_code, y.inc_dec_name, z.cnt_rec, z.amt_rec, z.cnt_N, z.amt_N, z.cnt_A, z.amt_A	"
				+ "	, z.cnt_R, z.amt_R, z.cnt_B, z.amt_B, z.cnt_S, z.amt_S	"
				+ "	from (	"
				+ " 	select c.div_code,c.div_desc, nvl(c.area_code,'$') area_code,c.area_desc, con.value_code inc_dec_code, count(*) cnt_rec, sum(a.work_amt) amt_rec	"
				+ "	, count(*) cnt_N, sum(a.work_amt) amt_N	 "
				+ "	, 0 cnt_A, 0 amt_A "
				+ "	, 0 cnt_R, 0 amt_R 	"
				+ "	, 0 cnt_B, 0 amt_B 	"
				+ "	 , 0 cnt_S, 0 amt_S	"
				+ "	from wg_pr_work_day a, su_user_organization b, pn_organization c,wg_control con	"
				+ "	where a.ou_code = '"
				+ ouCode
				+ "'	"
				+ " 	and a.year = "
				+ year
				+ " 	and a.period = "
				+ period
				+ " 	and b.user_id = '"
				+ userId
				+ "'	"
				+ "	and con.control_code = 'WAGE'"
				+ "	and a.ou_code = b.ou_code	"
				+ "	and a.code_seq_work = b.code_seq	"
				+ "	and a.ou_code = c.ou_code	"
				+ "	and a.code_seq_work = c.code_seq	"
				+ "	and a.ou_code = con.ou_code "
				+ "	group by c.div_code,c.div_desc, c.area_code,c.area_desc, con.value_code	"
				+ "	union	"
				+ "	select c.div_code,c.div_desc, nvl(c.area_code,'$'),c.area_desc, '11' inc_dec_code, count(*) cnt_rec, 0 amt_rec	"
				+ "	, sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N	"
				+ "	, sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A	"
				+ "	, sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R	"
				+ "	, sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B	"
				+ "	, sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S	"
				+ "	from wg_pr_overtime a, su_user_organization b, pn_organization c	"
				+ "	where a.ou_code = '"
				+ ouCode
				+ "'	"
				+ " 	and a.year_pr = "
				+ year
				+ " 	and a.period_pr = "
				+ period
				+ " 	and b.user_id = '"
				+ userId
				+ "'	"
				+ "	and a.ou_code = b.ou_code	"
				+ "	and a.code_seq = b.code_seq	"
				+ "	and a.ou_code = c.ou_code	"
				+ "	and a.code_seq = c.code_seq	"
				+ "	group by c.div_code,c.div_desc, c.area_code,c.area_desc, '11'	"
				+ "	union	"
				+ "	select c.div_code,c.div_desc,nvl(c.area_code,'$'),c.area_desc, a.inc_dec_code, count(*) cnt_rec, sum(total_amt) amt_rec	"
				+ "		, sum(decode(a.flag_pr,'N',1,0)) cnt_N, sum(decode(a.flag_pr,'N',a.total_amt,0)) amt_N	"
				+ "		, sum(decode(a.flag_pr,'A',1,0)) cnt_A, sum(decode(a.flag_pr,'A',a.total_amt,0)) amt_A	"
				+ "		, sum(decode(a.flag_pr,'R',1,0)) cnt_R, sum(decode(a.flag_pr,'R',a.total_amt,0)) amt_R	"
				+ "		, sum(decode(a.flag_pr,'B',1,0)) cnt_B, sum(decode(a.flag_pr,'B',a.total_amt,0)) amt_B	"
				+ "		, sum(decode(a.flag_pr,'S',1,0)) cnt_S, sum(decode(a.flag_pr,'S',a.total_amt,0)) amt_S	"
				+ " from wg_pr_inc_dec_other a, su_user_organization b, pn_organization c	"
				+ "	where a.ou_code = '"
				+ ouCode
				+ "'	"
				+ " 	and a.year_pr = "
				+ year
				+ " 	and a.period_pr = "
				+ period
				+ " 	and b.user_id = '"
				+ userId
				+ "'	"
				+ "	and a.ou_code = b.ou_code	"
				+ "	and a.code_seq = b.code_seq"
				+ "	and a.ou_code = c.ou_code	"
				+ "	and a.code_seq = c.code_seq	"
				+ "	group by c.div_code,c.div_desc, c.area_code,c.area_desc, a.inc_dec_code	"
				+ "	) z, wg_income_deduct y	"
				+ "	where y.ou_code = '"
				+ ouCode
				+ "'	"
				+ "	and z.inc_dec_code = y.inc_dec_code	"
				+ "	order by div_code,div_desc, area_code,area_desc, inc_dec_code "
				+ "	)"
				+ "	order by div_code,div_desc, area_code,area_desc, inc_dec_code ";

		list = this.jdbcTemplate.queryForList(sql.toString());

		System.out.println("**************** LIST ***************** ::: "
				+ list.size());

		try {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap map = (ListOrderedMap) list.get(i);
				String divCode = (String) map.getValue(0);
				String divName = (String) map.getValue(1);
				String areaCode = (String) map.getValue(2);
				String areaName = (String) map.getValue(3);

				Long cntEmp = new Long(map.getValue(4).toString());
				Long empA = new Long(map.getValue(5).toString());
				Long empI = new Long(map.getValue(6).toString());
				Long empD = new Long(map.getValue(7).toString());

				String incDecCode = (String) map.getValue(8);
				String incDecName = (String) map.getValue(9);

				Long cntRec = new Long(map.getValue(10).toString());
				Double amtRec = new Double(map.getValue(11).toString());
				Long cntN = new Long(map.getValue(12).toString());
				Double amtN = new Double(map.getValue(13).toString());
				Long cntA = new Long(map.getValue(14).toString());
				Double amtA = new Double(map.getValue(15).toString());
				Long cntR = new Long(map.getValue(16).toString());
				Double amtR = new Double(map.getValue(17).toString());
				Long cntB = new Long(map.getValue(18).toString());
				Double amtB = new Double(map.getValue(19).toString());
				Long cntS = new Long(map.getValue(20).toString());
				Double amtS = new Double(map.getValue(21).toString());

				CTRWRP201VO vo = new CTRWRP201VO();
				vo.setDivCode(divCode);
				vo.setDivName(divName);
				vo.setAreaCode(areaCode);
				vo.setAreaName(areaName);

				vo.setCntEmp(cntEmp);
				vo.setEmpA(empA);
				vo.setEmpI(empI);
				vo.setEmpD(empD);

				vo.setIncDecCode(incDecCode);
				vo.setIncDecName(incDecName);

				vo.setCntRec(cntRec);
				vo.setAmtRec(amtRec);
				vo.setCntN(cntN);
				vo.setAmtN(amtN);
				vo.setCntA(cntA);
				vo.setAmtA(amtA);
				vo.setCntR(cntR);
				vo.setAmtR(amtR);
				vo.setCntB(cntB);
				vo.setAmtB(amtB);
				vo.setCntS(cntS);
				vo.setAmtS(amtS);
				System.out.println(vo.getAmtN() + "aaaaaaaaaaa");
				listdetail.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out
				.println("**************** LIST DETAIL ***************** ::: "
						+ listdetail.size());
		return listdetail;
	}

}
