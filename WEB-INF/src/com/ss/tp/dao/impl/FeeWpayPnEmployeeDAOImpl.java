package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dto.ApEmployeeVO;
import com.ss.tp.dto.FeeContractVO;
import com.ss.tp.dto.FeeWgPnEmployeeVO;
import com.ss.tp.dto.FeeWpayPrEmployeeVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.FeeWpayPnEmployeeDetailVO;
import com.ss.tp.dto.PnEmployeeVO;

import com.ss.tp.dto.PrEmployeeVO;

import com.ss.tp.dto.WeEmployeeVO;

//import com.ss.tp.dao.PnEmployeeDAO;
import com.ss.tp.dao.FeeWpayPnEmployeeDAO;
import com.ss.tp.model.FeeContract;
import com.ss.tp.model.FeeWgEmployee;
import com.ss.tp.model.FeeWpayPrEmployee;
import com.ss.tp.model.PnEmployee;
import com.ss.tp.model.PnEmployeePK;
import com.ss.tp.model.PrEmployee;

public class FeeWpayPnEmployeeDAOImpl extends HibernateDaoSupport implements
		FeeWpayPnEmployeeDAO, Serializable {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String findEmpName(String ouCode, String empCode) {
		PnEmployeePK pk = new PnEmployeePK();
		pk.setOuCode(ouCode);
		pk.setEmpCode(empCode);
		PnEmployee emp = (PnEmployee) getSession().load(PnEmployee.class, pk);
		String empName = "";
		try {
			empName = emp.getRefDbPreSuff().getPrefixName() + ""
					+ emp.getFirstName() + " " + emp.getLastName();
		} catch (Exception e) {
		}
		return empName;
	}

	public FeeWpayPnEmployeeDetailVO findByCriteriaNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);
		

			int intYear = Integer.parseInt(year.trim());
			int intPeriod = Integer.parseInt(period.trim());

			int beforePeriod = 0;

			if (intPeriod == 1) {
				beforePeriod = 24;
				intYear = intYear - 1;
			} else
				beforePeriod = intPeriod - 1;

			hql.append("	select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME ,  pn.LAST_NAME , pn.IDATE ,  orgPr.ORG_CODE , ");
			hql.append("    orgPr.DIV_SHORT || ' ' || orgPr.AREA_DESC || ' ' || orgPr.SEC_DESC || ' ' || orgPr.WORK_DESC as ORG_DESC , ");
			hql.append("    orgPrAct.DIV_SHORT || ' ' || orgPrAct.AREA_DESC || ' ' || orgPrAct.SEC_DESC || ' ' || orgPrAct.WORK_DESC as ORG_ACT_DESC , ");
			hql.append("    pr.NEW_CODE_SEQ                from PN_ORGANIZATION orgPrAct, DB_PRE_SUFF pre, ");
			hql.append("	FEE_WPAY_PR_EMPLOYEE pr left outer join PN_ORGANIZATION orgPr ");
			hql.append("    on pr.OU_CODE=orgPr.OU_CODE and pr.NEW_CODE_SEQ = orgPr.CODE_SEQ, ");
			hql.append("    WG_EMPLOYEE pn");
			hql.append("    where pn.OU_CODE = '" + ouCode + "' ");
			//hql.append("    where pn.OU_CODE = '" + ouCode + "' ");
			hql.append("    and pn.EMP_CODE = '" + empCode + "' ");
			hql.append("    and pr.year = " + year);
			hql.append("    and pr.period = " + period);
			hql.append("    and pr.NEW_PAY_STATUS = '2' ");
			hql.append("    and pn.emp_status = 'B' ");
			hql.append("    and ( pn.RETIRE_FLAG is null or pn.RETIRE_FLAG = '0') ");
			hql.append("    and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append("    and pn.ou_code = pr.ou_code ");
			hql.append("    and pn.emp_code = pr.emp_code ");
			hql.append("    and pn.OU_CODE = orgPrAct.OU_CODE ");
			hql.append("    and pn.CODE_SEQ = orgPrAct.CODE_SEQ ");

			System.out.println("sql : " + hql.toString());

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			FeeWpayPnEmployeeDetailVO vo = new FeeWpayPnEmployeeDetailVO();

			if (ls != null && ls.size() > 0) {
				ListOrderedMap element = (ListOrderedMap) ls.get(0);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
			

				String pDateStr = "";
				if (element.get("IDATE") != null) {
					Date pDate = new Date(
							((Timestamp) element.get("IDATE")).getTime());
					SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
							new Locale("th", "TH"));
					pDateStr = smdf.format(pDate);
				}
				String divDesc = (String) element.get("DIV_DESC");
				String secDesc = (String) element.get("SEC_DESC");
				String orgCode = (String) element.get("ORG_CODE");
				String orgDesc = (String) element.get("ORG_DESC");
				String orgActDesc = (String) element.get("ORG_ACT_DESC");

				String codeSeqAct = "";
				String codeSeq = "";
				if (element.get("NEW_CODE_SEQ") != null) {
					codeSeqAct = ((BigDecimal) element.get("NEW_CODE_SEQ"))
							.toString();
					codeSeq = ((BigDecimal) element.get("NEW_CODE_SEQ"))
							.toString();
				}

				vo.setOuCode(ouCode);
				vo.setEmpCode(empCodeStr);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setPDate(pDateStr);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setOrgActDesc(orgActDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public FeeWpayPnEmployeeDetailVO findByCriteriaInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);
		

			hql.append(" select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME , ");
			hql.append(" pn.LAST_NAME ,  ");
			hql.append(" pn.IDATE , orgPn.DIV_DESC , ");
			hql.append(" orgPn.SEC_DESC , orgPr.ORG_CODE , orgPr.DIV_SHORT || ' ' || orgPr.AREA_DESC || ' ' || orgPr.SEC_DESC || ' ' || orgPr.WORK_DESC as ORG_ACT_DESC , ");
			hql.append(" orgPn.DIV_SHORT || ' ' || orgPn.AREA_DESC || ' ' || orgPn.SEC_DESC || ' ' || orgPn.WORK_DESC as ORG_DESC , ");
			hql.append(" pr.NEW_CODE_SEQ , pr.CODE_SEQ_WORK ");
			hql.append(" from PN_ORGANIZATION orgPr, PN_ORGANIZATION orgPn, DB_PRE_SUFF pre, FEE_WPAY_PR_EMPLOYEE pr, WG_EMPLOYEE pn  ");
			hql.append(" where pn.OU_CODE='" + ouCode + "' ");
			hql.append(" and pn.EMP_CODE='" + empCode + "' ");
			hql.append(" and pr.year = " + year);
			hql.append(" and pr.period = " + period);
			hql.append(" and (pn.EMP_CODE in  ( ");
			hql.append("	select vpn.EMP_CODE ");
			hql.append("	from V_FEE_WP_PR_EMPLOYEE_SECURITY vpn ");
			hql.append("	where vpn.OU_CODE='" + ouCode + "' ");
			hql.append("	and vpn.USER_ID='" + userId + "' ");
			hql.append("  	and vpn.year = " + year);
			hql.append("	and vpn.period = " + period + " )");
			hql.append(" ) and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append(" and pn.ou_code = pr.ou_code ");
			hql.append(" and pn.emp_code = pr.emp_code ");
			hql.append(" and pr.OU_CODE=orgPr.OU_CODE ");
			hql.append(" and pr.NEW_CODE_SEQ=orgPr.CODE_SEQ ");
			hql.append(" and pn.ou_code = orgPn.ou_code ");
			hql.append(" and pn.code_seq = orgPn.code_seq ");

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx : "
					+ hql.toString());

			FeeWpayPnEmployeeDetailVO vo = new FeeWpayPnEmployeeDetailVO();

			if (ls != null && ls.size() > 0) {
				// System.out.println(ls.get(0).getClass().getName());
				ListOrderedMap element = (ListOrderedMap) ls.get(0);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
				//String account = (String) element.get("ACCOUNT");
				//String position = (String) element.get("POSITION_SHORT");
				//String levelCode = (String) element.get("LEVEL_CODE");
				//String adminDesc = (String) element.get("ADMIN_DESC");

				String pDateStr = "";
				if (element.get("IDATE") != null) {
					Date pDate = new Date(
							((Timestamp) element.get("IDATE")).getTime());
					SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
							new Locale("th", "TH"));
					pDateStr = smdf.format(pDate);
				}

				String divDesc = (String) element.get("DIV_DESC");
				String secDesc = (String) element.get("SEC_DESC");
				String orgCode = (String) element.get("ORG_CODE");
				String orgDesc = (String) element.get("ORG_DESC");
				String orgActDesc = (String) element.get("ORG_ACT_DESC");

				String codeSeqAct = ((BigDecimal) element.get("NEW_CODE_SEQ"))
						.toString();
				String codeSeq = ((BigDecimal) element.get("NEW_CODE_SEQ"))
						.toString();

				vo.setOuCode(ouCode);
				vo.setEmpCode(empCodeStr);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				//vo.setAccount(account);
				//vo.setPosition(position);
				//vo.setLevelCode(levelCode);
				//vo.setAdminDesc(adminDesc);
				vo.setPDate(pDateStr);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setOrgActDesc(orgActDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public FeeWpayPnEmployeeDetailVO findByCriteriaInSecueEmpText(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);

			int intYear = Integer.parseInt(year.trim());
			int intPeriod = Integer.parseInt(period.trim());

			int beforePeriod = 0;

			if (intPeriod == 1) {
				beforePeriod = 24;
				intYear = intYear - 1;
			} else
				beforePeriod = intPeriod - 1;

		

			hql.append("	select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME ,  pn.LAST_NAME ,  ");
			hql.append("	 orgPr.ORG_CODE , ");
			hql.append("    orgPr.DIV_SHORT || ' ' || orgPr.AREA_DESC || ' ' || orgPr.SEC_DESC || ' ' || orgPr.WORK_DESC as ORG_DESC , ");
			hql.append("	orgPrAct.DIV_SHORT || ' ' || orgPrAct.AREA_DESC || ' ' || orgPrAct.SEC_DESC || ' ' || orgPrAct.WORK_DESC as ORG_ACT_DESC , ");
			hql.append("    pr.NEW_CODE_SEQ                from PN_ORGANIZATION orgPrAct, DB_PRE_SUFF pre, ");
			hql.append("    FEE_WPAY_PR_EMPLOYEE pr , PN_ORGANIZATION orgPr , V_PN_ORGANIZATION_SECURITY v, ");
			hql.append("    WG_EMPLOYEE pn  ");
			hql.append("    where pn.OU_CODE = '" + ouCode + "' ");
			hql.append("    and pn.EMP_CODE = '" + empCode + "' ");
			//hql.append("    and pn.EMP_STATUS = 'B' ");
			hql.append("    and pr.year = " + year);
			hql.append("    and pr.period = " + period);
			hql.append("    and pr.NEW_PAY_STATUS = '1' ");
			hql.append("	and v.user_id = '" + userId + "' ");
			hql.append("    and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append("    and pn.ou_code = pr.ou_code ");
			hql.append("    and pn.emp_code = pr.emp_code ");
			hql.append("    and pr.OU_CODE=orgPr.OU_CODE ");
			hql.append("    and pr.NEW_CODE_SEQ = orgPr.CODE_SEQ ");
			hql.append("    and pn.OU_CODE = orgPrAct.OU_CODE ");
			hql.append("    and pn.CODE_SEQ = orgPrAct.CODE_SEQ ");
			hql.append(" 	and pr.OU_CODE = v.OU_CODE ");
			hql.append(" 	and pr.NEW_CODE_SEQ = v.code_seq ");

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx : "+ hql.toString());

			FeeWpayPnEmployeeDetailVO vo = new FeeWpayPnEmployeeDetailVO();

			for (int i = 0; i < ls.size(); i++) {
				// System.out.println(ls.get(0).getClass().getName());
				ListOrderedMap element = (ListOrderedMap) ls.get(i);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
				//String account = (String) element.get("ACCOUNT");


				//String pDateStr = "";
				//if (element.get("IDATE") != null) {
				//	Date pDate = new Date(
				//			((Timestamp) element.get("IDATE")).getTime());
				//	SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
				//			new Locale("th", "TH"));
				//	pDateStr = smdf.format(pDate);
				//}

				String divDesc = (String) element.get("DIV_DESC");
				String secDesc = (String) element.get("SEC_DESC");
				String orgCode = (String) element.get("ORG_CODE");
				String orgDesc = (String) element.get("ORG_DESC");
				String orgActDesc = (String) element.get("ORG_ACT_DESC");

				String codeSeqAct = ((BigDecimal) element.get("NEW_CODE_SEQ")).toString();
				String codeSeq = ((BigDecimal) element.get("NEW_CODE_SEQ")).toString();

				vo.setOuCode(ouCode);
				vo.setEmpCode(empCodeStr);
				vo.setPrefixName(prefixName);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setOrgActDesc(orgActDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public FeeWpayPnEmployeeDetailVO findPnEmployeeNotUseSecurity(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {

		try {
			StringBuffer hql = new StringBuffer(0);
			hql.append(" select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME , ");
			hql.append(" pn.LAST_NAME , pn.ACCOUNT , pos.POSITION_SHORT , ");
			hql.append(" pn.LEVEL_CODE , adm.ADMIN_DESC , pn.IDATE , orgPn.DIV_DESC , ");
			hql.append(" orgPn.SEC_DESC , orgPr.ORG_CODE , orgPr.DIV_SHORT || ' ' || orgPr.AREA_DESC || ' ' || orgPr.SEC_DESC || ' ' || orgPr.WORK_DESC as ORG_DESC , ");
			hql.append(" pr.CODE_SEQ_WORK , pr.CODE_SEQ_WORK ");
			hql.append(" from PN_POSITION pos, PN_ORGANIZATION orgPr, PN_ORGANIZATION orgPn, DB_PRE_SUFF pre, PR_EMPLOYEE pr, PN_EMPLOYEE pn left outer join PN_ADMIN adm ");
			hql.append(" on pn.OU_CODE=adm.OU_CODE ");
			hql.append(" and pn.ADMIN_CODE=adm.ADMIN_CODE ");
			hql.append(" where pn.OU_CODE='" + ouCode + "' ");
			hql.append(" and pn.EMP_CODE='" + empCode + "' ");
			hql.append(" and pr.year = " + year);
			hql.append(" and pr.period = " + period);
			hql.append(" and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
			hql.append(" and pn.OU_CODE=pos.OU_CODE ");
			hql.append(" and pn.GWORK_CODE=pos.GWORK_CODE ");
			hql.append(" and pn.POSITION_CODE=pos.POSITION_CODE ");
			hql.append(" and pn.ou_code = pr.ou_code ");
			hql.append(" and pn.emp_code = pr.emp_code ");
			hql.append(" and pr.OU_CODE=orgPr.OU_CODE ");
			hql.append(" and pr.CODE_SEQ_WORK=orgPr.CODE_SEQ ");
			hql.append(" and pn.ou_code = orgPn.ou_code ");
			hql.append(" and pn.code_seq = orgPn.code_seq ");

			List ls = this.jdbcTemplate.queryForList(hql.toString());

			// System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx : " +
			// hql.toString());

			FeeWpayPnEmployeeDetailVO vo = new FeeWpayPnEmployeeDetailVO();

			if (ls != null && ls.size() > 0) {
				// System.out.println(ls.get(0).getClass().getName());
				ListOrderedMap element = (ListOrderedMap) ls.get(0);

				String empCodeStr = (String) element.get("EMP_CODE");
				String prefixName = (String) element.get("PREFIX_NAME");
				String firstName = (String) element.get("FIRST_NAME");
				String lastName = (String) element.get("LAST_NAME");
				String account = (String) element.get("ACCOUNT");
				String position = (String) element.get("POSITION_SHORT");
				String levelCode = (String) element.get("LEVEL_CODE");
				String adminDesc = (String) element.get("ADMIN_DESC");

				String pDateStr = "";
				if (element.get("IDATE") != null) {
					Date pDate = new Date(
							((Timestamp) element.get("IDATE")).getTime());
					SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
							new Locale("th", "TH"));
					pDateStr = smdf.format(pDate);
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
				vo.setPosition(position);
				vo.setLevelCode(levelCode);
				vo.setAdminDesc(adminDesc);
				vo.setPDate(pDateStr);
				vo.setDivDesc(divDesc);
				vo.setSecDesc(secDesc);
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
				vo.setCodeSeqAct(codeSeqAct);
				vo.setCodeSeq(codeSeq);
			}

			hql.setLength(0);
			hql.append(" select t.code_seq_work as CODE_SEQ_WORK, o.div_short || ' ' || o.area_desc || ' ' || o.sec_desc || ' ' || o.work_desc as ORG_DESC, o.org_code as ORG_CODE ");
			hql.append(" from pr_employee_text t, pn_organization o ");
			hql.append(" where t.ou_code = '" + ouCode + "' ");
			hql.append(" and t.year = " + year);
			hql.append(" and t.period = " + period);
			hql.append(" and t.emp_code = '" + empCode + "' ");
			hql.append(" and t.flag_status <> 'D' ");
			hql.append(" and t.code_seq_work = o.code_seq ");
			hql.append(" and t.ou_code = o.ou_code ");
			hql.append(" order by t.key_seq ");

			ls = this.jdbcTemplate.queryForList(hql.toString());
			for (int i = 0; i < ls.size(); i++) {
				ListOrderedMap element = (ListOrderedMap) ls.get(i);
		
				BigDecimal codeSeqWork = (BigDecimal) element
						.get("CODE_SEQ_WORK");
				String orgDesc = (String) element.get("ORG_DESC");
				String orgCode = (String) element.get("ORG_CODE");

				vo.setCodeSeq(codeSeqWork.toString());
				vo.setCodeSeqAct(codeSeqWork.toString());
				vo.setOrgCode(orgCode);
				vo.setOrgDesc(orgDesc);
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List findByCriteria(String ouCode) {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						"from PnEmployee e where e.pk.ouCode ='" + ouCode
								+ "' order by e.pk.empCode").list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PnEmployee emp = (PnEmployee) iter.next();
			PnEmployeeVO ret = new PnEmployeeVO();
			try {
				BeanUtils.copyProperties(ret, emp.getPk());
				BeanUtils.copyProperties(ret, emp);
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	public List findPrEmpBySecurity(String userId, String ouCode, String year,
			String period) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from FeeWpayPrEmployee a ");
		hql.append(" where ");
		hql.append(" a.key.ouCode = '" + ouCode + "' ");
		hql.append(" and a.key.year = " + year);
		hql.append(" and a.key.period = " + period);
		// hql.append(" and a.payStatus = '1' ");
		hql.append(" and a.newPayStatus = '1' ");
		hql.append(" and a.key.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VFeeWpPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and b.pk.year = " + year);
		hql.append("	 and b.pk.period = " + period);
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.key.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				FeeWpayPrEmployee element = (FeeWpayPrEmployee) ls.get(i);
				FeeWpayPrEmployeeVO vo = new FeeWpayPrEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getKey());
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

	public List findWeEmpBySecurity(String userId, String ouCode)
			throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from PnEmployee a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.payStatus = '1' ");
		hql.append(" and a.empStatus  = 'B' ");
		hql.append(" and a.pk.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.pk.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				PnEmployee element = (PnEmployee) ls.get(i);
				PnEmployeeVO vo = new PnEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getPk());
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

	public List findPrEmpMT001(String userId, String ouCode, String year,
			String period) throws Exception {
		StringBuffer hql = new StringBuffer(0);

		hql.append("	select pn.EMP_CODE, pre.PREFIX_NAME , pn.FIRST_NAME ,  pn.LAST_NAME, ");
		hql.append("	pos.POSITION_SHORT ");
		hql.append("	from PN_POSITION pos,  PN_ORGANIZATION orgPrAct, DB_PRE_SUFF pre, ");
		hql.append("    PR_EMPLOYEE pr , PN_ORGANIZATION orgPr , ");
		hql.append("    PN_EMPLOYEE pn left outer join PN_ADMIN adm  on pn.OU_CODE=adm.OU_CODE ");
		hql.append("    and pn.ADMIN_CODE=adm.ADMIN_CODE ");
		hql.append("    where pn.OU_CODE = '" + ouCode + "' ");
		hql.append("    and pr.year = " + year);
		hql.append("    and pr.period = " + period);
		hql.append("    and pr.NEW_PAY_STATUS = '1' ");
		hql.append("    and pn.emp_status = 'B' ");
		hql.append("    and ( pn.RETIRE_FLAG is null or pn.RETIRE_FLAG = '0' ) ");
		hql.append("    and pn.PRE_NAME=pre.PRE_SUFF_CODE ");
		hql.append("    and pn.OU_CODE=pos.OU_CODE ");
		hql.append("    and pn.GWORK_CODE=pos.GWORK_CODE ");
		hql.append("    and pn.POSITION_CODE=pos.POSITION_CODE ");
		hql.append("    and pn.ou_code = pr.ou_code ");
		hql.append("    and pn.emp_code = pr.emp_code ");
		hql.append("    and pr.OU_CODE=orgPr.OU_CODE ");
		hql.append("    and pr.NEW_CODE_SEQ = orgPr.CODE_SEQ ");
		hql.append("    and pn.OU_CODE = orgPrAct.OU_CODE ");
		hql.append("    and pn.CODE_SEQ = orgPrAct.CODE_SEQ ");
		hql.append("	order by pn.EMP_CODE ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String empCode = (String) map.getValue(0);
			String prefixName = (String) map.getValue(1);
			String firstName = (String) map.getValue(2);
			String lastName = (String) map.getValue(3);
			String positionShort = (String) map.getValue(4);
			// String orgCode = (String) map.getValue(5);
			// String orgDesc = (String) map.getValue(6);

			FeeWpayPayRollEmployeeVO vo = new FeeWpayPayRollEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			vo.setPositionShort(positionShort);
			// vo.setOrgCode(orgCode);
			// vo.setOrgDesc(orgDesc);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findToPrEmpMT001(String userId, String ouCode, String year,
			String period, String fromEmpCode) throws Exception {
		StringBuffer hql = new StringBuffer(0);
	

		hql.append(" ( ");
		hql.append("	select pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append("	pnEm.LAST_NAME  ");
		// hql.append(" org.ORG_DESC ");
		hql.append("	from FEE_WPAY_PR_EMPLOYEE prEm, WG_EMPLOYEE pnEm,  PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
		hql.append("	where prEm.OU_CODE = '" + ouCode + "' ");
		hql.append("	and prEm.YEAR = " + year);
		hql.append("	and prEm.PERIOD = " + period);
		hql.append("	and prEm.EMP_CODE >= '" + fromEmpCode + "' ");
		hql.append("	and v.USER_ID = '" + userId + "' ");
		hql.append(" 	and pnEm.emp_status = 'B' ");
		hql.append(" 	and ( pnEm.RETIRE_FLAG is null or pnEm.RETIRE_FLAG = '0' ) ");
		hql.append("	and prEm.OU_CODE = v.OU_CODE ");
		hql.append("	and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		hql.append("	and prEm.EMP_CODE=pnEm.EMP_CODE ");
		hql.append("	and prEm.OU_CODE=pnEm.OU_CODE ");
		hql.append("	and prEm.CODE_SEQ_WORK=org.CODE_SEQ ");
		hql.append("	and prEm.OU_CODE=org.OU_CODE ");
		hql.append("	and pnEm.PRE_NAME=sf.PRE_SUFF_CODE ");
		hql.append("	and pnEm.EMP_CODE not in ( ");
		hql.append("			select t.emp_code ");
		hql.append("			from fee_wpay_pr_employee_text t, v_pn_organization_security v ");
		hql.append("			where t.ou_code = '" + ouCode + "' ");
		hql.append("			and t.year = " + year);
		hql.append("			and t.period = " + period);
		hql.append("			and t.flag_status = 'D' ");
		hql.append("			and v.user_id = '" + userId + "' ");
		hql.append("			and t.ou_code = v.ou_code ");
		hql.append("			and t.code_seq_work = v.code_seq ");
		hql.append("	) ");
		hql.append(" ) ");
		hql.append(" union ");
		hql.append(" select distinct pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append(" pnEm.LAST_NAME  ");
		// hql.append(" org.ORG_DESC ");
		hql.append(" from fee_wpay_pr_employee_text prEm, WG_EMPLOYEE pnEm, PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
		hql.append(" where prEm.OU_CODE='" + ouCode + "' ");
		hql.append(" and prEm.YEAR = " + year);
		hql.append(" and prEm.PERIOD = " + period);
		hql.append(" and prEm.EMP_CODE >= '" + fromEmpCode + "' ");
		hql.append(" and v.USER_ID = '" + userId + "' ");
		hql.append(" 	and pnEm.emp_status = 'B' ");
		hql.append(" 	and ( pnEm.RETIRE_FLAG is null or pnEm.RETIRE_FLAG = '0' ) ");
		hql.append(" and prEm.FLAG_STATUS = 'I' ");
		hql.append(" and prEm.OU_CODE = v.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		hql.append(" and prEm.EMP_CODE=pnEm.EMP_CODE ");
		hql.append(" and prEm.OU_CODE=pnEm.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK=org.CODE_SEQ ");
		hql.append(" and prEm.OU_CODE=org.OU_CODE ");
		hql.append(" and pnEm.PRE_NAME=sf.PRE_SUFF_CODE ");
		hql.append(" order by EMP_CODE ");

		// System.out.println( hql.toString() );

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String empCode = (String) map.getValue(0);
			String prefixName = (String) map.getValue(1);
			String firstName = (String) map.getValue(2);
			String lastName = (String) map.getValue(3);
			//String positionShort = (String) map.getValue(4);
			// String orgCode = (String) map.getValue(5);
			// String orgDesc = (String) map.getValue(6);

			FeeWpayPayRollEmployeeVO vo = new FeeWpayPayRollEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			//vo.setPositionShort(positionShort);
			// vo.setOrgCode(orgCode);
			// vo.setOrgDesc(orgDesc);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findPnEmpBySecurity(String userId, String ouCode)
			throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append("select a.pk.empCode from PnEmployee a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.pk.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
	
		return ls;
	}

	public List findEmpByNotInSecurity(String userId, String ouCode)
			throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" select a.pk.empCode ");
		hql.append(" from PnEmployee a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.empCode not in  ( ");
		hql.append("	 select b.pk.empCode from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.pk.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());

		return ls;
	}

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from PnEmployee a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.empCode >= '" + fromEmpCode + "' ");
		hql.append(" and a.pk.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.pk.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				PnEmployee element = (PnEmployee) ls.get(i);
				PnEmployeeVO vo = new PnEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getPk());
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

	public List findPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from FeeWpayPrEmployee a ");
		hql.append(" where ");
		hql.append(" a.key.ouCode = '" + ouCode + "' ");
		hql.append(" and a.key.year = " + year);
		hql.append(" and a.key.period = " + period);
		hql.append(" and a.key.empCode >= '" + fromEmpCode + "' ");
		hql.append(" and a.key.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VFeeWpPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and b.pk.year = " + year);
		hql.append("	 and b.pk.period = " + period);
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.key.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				PrEmployee element = (PrEmployee) ls.get(i);
				PrEmployeeVO vo = new PrEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getKey());
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

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String userId, String empCode,
			String ouCode, Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		System.out.println("xxx");

		hql.append(" select pnEmp.wgEmployeePK.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		//hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc , ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
		//hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where pnEmp.wgEmployeePK.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(year);
		hql.append(" and prEmp.key.period = ");
		hql.append(period);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		// hql.append(" and prEmp.payStatus = '1' ");
		hql.append(" and prEmp.newPayStatus = '1' ");
		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append("	select vpr.pk.empCode ");
		hql.append("	from VFeeWpPrEmployeeSecurity vpr ");
		hql.append("	where vpr.pk.userId = '" + userId + "' ");
		hql.append("	and vpr.pk.year = " + year);
		hql.append("	and vpr.pk.period = " + period);
		hql.append("	and vpr.pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");

		//hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		//hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		//hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = pnOrg.pk.ouCode ");
		// hql.append(" and prEmp.codeSeqWork = pnOrg.pk.codeSeq ");
		hql.append(" and prEmp.newCodeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL PnEmployee findByEmpCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		FeeWpayPayRollEmployeeVO ret = new FeeWpayPayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			//String positionShort = (String) r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			Double codeSeqWork = (Double) r[6];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public WeEmployeeVO findByWeEmpCodeDetail(String empCode, String ouCode) {
		StringBuffer hql = new StringBuffer();

		System.out.println("xxx");

		hql.append(" select pnEm.pk.empCode ,pnEm.refDbPreSuff.prefixName, pnEm.firstName , ");
		hql.append(" pnEm.lastName ,nvl(pa.adminDesc,' '), ps.positionShort,to_char(to_number(pnEm.levelCode)),org.orgCode,org.divShort || ' ' || nvl(org.secDesc,org.areaDesc) || ' ' ||org.workDesc ,pnEm.codeSeq ");
		hql.append(" from  PnEmployee pnEm, PnPosition ps, PnOrganization org,PnAdmin pa ");
		hql.append(" where pnEm.pk.ouCode='" + ouCode + "' ");
		hql.append(" and pnEm.pk.empCode = '" + empCode + "' ");
		hql.append(" 	and pnEm.empStatus = 'B' ");
		hql.append(" and pnEm.pk.ouCode=ps.pk.ouCode ");
		hql.append(" and pnEm.gworkCode=ps.pk.gworkCode ");
		hql.append(" and pnEm.positionCode=ps.pk.positionCode ");
		hql.append(" and pnEm.codeSeq=org.pk.codeSeq ");
		hql.append(" and pnEm.pk.ouCode=org.pk.ouCode ");
		hql.append(" and nvl( pnEm.adminCode,'N')  =pa.pk.adminCode  ");
		hql.append(" and pa.pk.ouCode='" + ouCode + "' ");
		hql.append(" 	and pa.inActive = 'N' ");
		hql.append(" and pnEm.pk.ouCode=pa.pk.ouCode ");

		System.out.println("HQL PnEmployee findByWeEmpCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		WeEmployeeVO ret = new WeEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String adminDesc = (String) r[4];
			String positionShort = (String) r[5];
			String levelCode = (String) r[6];
			String orgCode = (String) r[7];
			String orgDesc = (String) r[8];
			Long codeSeq = (Long) r[9];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOldDuty(adminDesc);
			ret.setOldPositionShort(positionShort + " " + levelCode);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeq(codeSeq);
		}

		return ret;
	}
	
	public ApEmployeeVO findByApEmpCodeDetail(String empCode) {
		StringBuffer hql = new StringBuffer();

		System.out.println("xxxfindByApEmpCodeDetailxxx");

		hql.append(" select pnEm.pk.empCode ,pnEm.refDbPreSuff.prefixName, pnEm.firstName , ");
		hql.append(" pnEm.lastName , ps.positionShort,to_char(to_number(pnEm.levelCode)),org.divShort || ' ' || nvl(org.secDesc,org.areaDesc) || ' ' ||org.workDesc ,pnEm.codeSeq ");
		hql.append(" from  PnEmployee pnEm, PnPosition ps, PnOrganization org,PnAdmin pa ");
		hql.append(" where pnEm.pk.ouCode='001' ");
		hql.append(" and pnEm.pk.empCode = '" + empCode + "' ");
		hql.append(" 	and pnEm.empStatus = 'B' ");
		hql.append(" and pnEm.pk.ouCode=ps.pk.ouCode ");
		hql.append(" and pnEm.gworkCode=ps.pk.gworkCode ");
		hql.append(" and pnEm.positionCode=ps.pk.positionCode ");
		hql.append(" and pnEm.codeSeq=org.pk.codeSeq ");
		hql.append(" and pnEm.pk.ouCode=org.pk.ouCode ");
		hql.append(" and nvl( pnEm.adminCode,'N')  =pa.pk.adminCode  ");
		hql.append(" and pa.pk.ouCode='001' ");
		hql.append(" 	and pa.inActive = 'N' ");
		hql.append(" and pnEm.pk.ouCode=pa.pk.ouCode ");

		System.out.println("HQL PnEmployee findByApEmpCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		ApEmployeeVO ret = new ApEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			
			String positionShort = (String) r[4];
			String levelCode = (String) r[5];
		
			String orgDesc = (String) r[6];
			Long codeSeq = (Long) r[7];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setOldPositionShort(positionShort + " " + levelCode);
			//ret.setOrgDesc(orgDesc);
			ret.setCodeSeq(codeSeq);
		}

		return ret;
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		int intYear = year.intValue();
		int intPeriod = period.intValue();

		int beforePeriod = 0;

		if (intPeriod == 1) {
			beforePeriod = 24;
			intYear = intYear - 1;
		} else
			beforePeriod = intPeriod - 1;

		hql.append(" select pnEmp.pk.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		//hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc , ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
		//hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where pnEmp.pk.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and pnEmp.pk.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(intYear);
		hql.append(" and prEmp.key.period = ");
		hql.append(beforePeriod);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		// hql.append(" and prEmp.payStatus = '1' ");
		hql.append(" and prEmp.newPayStatus = '1' ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and pnEmp.pk.ouCode = pnOrg.pk.ouCode ");
		// hql.append(" and prEmp.codeSeqWork = pnOrg.pk.codeSeq ");
		hql.append(" and prEmp.newCodeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL PnEmployee findByEmpCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		FeeWpayPayRollEmployeeVO ret = new FeeWpayPayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String positionShort = (String) r[4];
			String orgCode = (String) r[5];
			String orgDesc = (String) r[6];
			Double codeSeqWork = (Double) r[7];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurityCTRW03(
			String userId, String empCode, String ouCode, Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		int intYear = year.intValue();
		int intPeriod = period.intValue();

		hql.append(" select pnEmp.wgEmployeePK.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		//hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc , ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
		//hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where pnEmp.wgEmployeePK.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(intYear);
		hql.append(" and prEmp.key.period = ");
		hql.append(intPeriod);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		// hql.append(" and prEmp.payStatus = '1' ");
		hql.append(" and prEmp.newPayStatus = '1' ");
		//hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		//hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		//hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = pnOrg.pk.ouCode ");
		// hql.append(" and prEmp.codeSeqWork = pnOrg.pk.codeSeq ");
		hql.append(" and prEmp.newCodeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL PnEmployee findByEmpCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		FeeWpayPayRollEmployeeVO ret = new FeeWpayPayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			//String positionShort = (String) r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			Double codeSeqWork = (Double) r[6];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public WeEmployeeVO findByWeOrgCodeDetail(String orgCode, String ouCode) {
		StringBuffer hql = new StringBuffer();

		System.out.println("xxx");

		hql.append(" select org.orgCode,org.divShort || ' ' || nvl(org.secDesc,org.areaDesc) || ' ' ||org.workDesc,org.pk.codeSeq  ");
		hql.append(" from   PnOrganization org  ");
		hql.append(" where org.pk.ouCode='" + ouCode + "' ");
		hql.append(" and org.orgCode = '" + orgCode + "' ");
		hql.append(" and org.inactive = 'N' ");

		System.out.println("HQL PnEmployee findByWeOrgCodeDetail ==> "
				+ hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();

		WeEmployeeVO ret = new WeEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);

			String newOrgCode = (String) r[0];
			String newOrgDesc = (String) r[1];
			Long newCodeSeq = (Long) r[2];

			ret.setNewOrgCode(newOrgCode);
			ret.setNewOrgDesc(newOrgDesc);
			ret.setNewCodeSeq(newCodeSeq);

		}

		return ret;
	}
	public List findFeeWgContractBySecurity(String userId, String ouCode) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" select a.pk.contractNo ");
		hql.append(" from FeeContract a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq in  ( ");
		hql.append("	 select b.pk.codeSeq from VFeeWgPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		//hql.append(" and a.pk.yearCon >= " + yearFrom );
		//hql.append(" and a.pk.yearCon <= " + yearTo );
		hql.append(" order by a.pk.contractNo ");
		hql.append(" group by a.pk.contractNo ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				FeeContract element = (FeeContract) ls.get(i);
				FeeContractVO vo = new FeeContractVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getPk());
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
	
	public List findFeeWgYearBySecurity(String userId, String ouCode) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" select a.pk.yearCon ");
		hql.append(" from FeeContract a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq in  ( ");
		hql.append("	 select b.pk.codeSeq from VFeeWgPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		//hql.append(" and a.pk.yearCon >= " + yearFrom );
		//hql.append(" and a.pk.yearCon <= " + yearTo );
		hql.append(" order by a.pk.yearCon ");
		hql.append(" group by a.pk.yearCon ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				FeeContract element = (FeeContract) ls.get(i);
				FeeContractVO vo = new FeeContractVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getPk());
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

	public List findFeeWgEmpBySecurity(String userId, String ouCode)
			throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from FeeWgEmployee a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VFeeWgPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.codeSeqAct = b.pk.codeSeq ");
		hql.append(" ) ");
		//hql.append(" and a.pk.yearCon >= " + yearFrom );
		//hql.append(" and a.pk.yearCon <= " + yearTo );
		hql.append(" order by a.pk.empCode ");
		//hql.append(" group by a.pk.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				FeeWgEmployee element = (FeeWgEmployee) ls.get(i);
				FeeWgPnEmployeeVO vo = new FeeWgPnEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getPk());
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
	public List findYearConBySecurity(String userId, String ouCode)
			throws Exception {

		StringBuffer hql = new StringBuffer(0);
		hql.append("select distinct(a.pk.yearCon) from FeeContract a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq in  ( ");
		hql.append("	 select b.pk.codeSeq from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");
		hql.append(" order by a.pk.yearCon desc ");

		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
	public List findContractNoBySecurity(String userId, String ouCode)
			throws Exception {

		StringBuffer hql = new StringBuffer(0);
		hql.append("select distinct(a.pk.contractNo) from FeeContract a ");
		hql.append(" where ");
		hql.append(" a.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and a.pk.codeSeq in  ( ");
		hql.append("	 select b.pk.codeSeq from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");
		hql.append(" order by a.pk.contractNo desc ");


		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}




}