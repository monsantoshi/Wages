package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ss.tp.dao.WgEmployeeDAO;
import com.ss.tp.dto.GwIncdecEmployeeVO;
import com.ss.tp.dto.WgEmployeeVO;

import com.ss.tp.model.WgEmployee;
import com.ss.tp.model.WgEmployeePK;

public class WgEmployeeDAOImpl extends HibernateDaoSupport implements
		WgEmployeeDAO, Serializable {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String findEmpName(String ouCode, String empCode) {
		WgEmployeePK pk = new WgEmployeePK();
		pk.setOuCode(ouCode);
		pk.setEmpCode(empCode);
		WgEmployee emp = (WgEmployee) getSession().load(WgEmployee.class, pk);
		String empName = "";
		try {
			empName = emp.getRefDbPreSuff().getPrefixName() + ""
					+ emp.getFirstName() + " " + emp.getLastName();
		} catch (Exception e) {
		}
		return empName;
	}

	public List findWgEmpBySecurity(String userId, String ouCode)
			throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" select a.wgEmployeePK.empCode from WgEmployee a ");
		hql.append(" where ");
		hql.append(" a.wgEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and a.wgEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.wgEmployeePK.empCode ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		// if( ls != null && ls.size() > 0 ){
		// for (int i=0; i<ls.size(); i++) {
		// WgEmployee element = (WgEmployee) ls.get(i);
		// WgEmployeeVO vo = new WgEmployeeVO();
		// try {
		// BeanUtils.copyProperties(vo, element);
		// BeanUtils.copyProperties(vo, element.getWgEmployeePK());
		// ls.set(i, vo);
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// throw new Exception("can't copy properties : " + e.getMessage());
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// throw new Exception("can't copy properties : " + e.getMessage());
		// }
		// }
		// }
		return ls;
	}

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception {
		StringBuffer hql = new StringBuffer(0);
		hql.append(" from WgEmployee a ");
		hql.append(" where ");
		hql.append(" a.wgEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and a.wgEmployeePK.empCode >= '" + fromEmpCode + "' ");
		hql.append(" and a.wgEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.wgEmployeePK.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		System.out.println("ls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				WgEmployee element = (WgEmployee) ls.get(i);
				WgEmployeeVO vo = new WgEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getWgEmployeePK());
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
		hql.append(" from WgEmployee a ");
		hql.append(" where ");
		hql.append(" a.wgEmployeePK.ouCode = '" + ouCode + "' ");
		// hql.append(" and a.wgEmployeePK.year = " + year );
		// hql.append(" and a.wgEmployeePK.period = " + period );
		hql.append(" and a.wgEmployeePK.empCode >= '" + fromEmpCode + "' ");
		hql.append(" and a.wgEmployeePK.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VWgPrEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and b.pk.year = " + year);
		hql.append("	 and b.pk.period = " + period);
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.wgEmployeePK.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		System.out.println("\n\n\nls : " + ls.size());
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				WgEmployee element = (WgEmployee) ls.get(i);
				WgEmployeeVO vo = new WgEmployeeVO();
				try {
					BeanUtils.copyProperties(vo, element);
					BeanUtils.copyProperties(vo, element.getWgEmployeePK());
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

	public GwIncdecEmployeeVO findByEmpCodeDetail(String userId,
			String empCode, String ouCode, Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select pnEmp.wgEmployeePK.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		// hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.orgDesc, ");
		hql.append(" prEmp.codeSeqWork ");
		hql.append(" from WgEmployee pnEmp, ");
		hql.append(" WgPrEmployee prEmp, ");
		// hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where pnEmp.wgEmployeePK.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.wgPrEmployeePK.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.wgPrEmployeePK.year = ");
		hql.append(year);
		hql.append(" and prEmp.wgPrEmployeePK.period = ");
		hql.append(period);
		hql.append(" and prEmp.wgPrEmployeePK.empCode = '");
		hql.append(empCode);
		hql.append("' ");

		hql.append(" and pnEmp.wgEmployeePK.empCode in ( ");
		hql.append("	select vpr.pk.empCode ");
		hql.append("	from VWgPrEmployeeSecurity vpr ");
		hql.append("	where vpr.pk.userId = '" + userId + "' ");
		hql.append("	and vpr.pk.year = " + year);
		hql.append("	and vpr.pk.period = " + period);
		hql.append("	and vpr.pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");

		// hql.append(" and pnEmp.wgPrEmployeePK.ouCode = pnPos.pk.ouCode ");
		// hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		// hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and pnEmp.wgEmployeePK.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and prEmp.codeSeqWork = pnOrg.pk.codeSeq ");

		System.out
				.println("HQL PnEmployee findByEmpCodeDetail ++++++++++++++++++++ ==> "
						+ hql.toString());
		List empList = this.getSession().createQuery(hql.toString()).list();

		GwIncdecEmployeeVO ret = new GwIncdecEmployeeVO();
		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			// String positionShort = (String)r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			Integer codeSeqWork = (Integer) r[6];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			// ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public GwIncdecEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		GwIncdecEmployeeVO ret = new GwIncdecEmployeeVO();
		try {
			StringBuffer hql = new StringBuffer();

			// /////// JDBC ///////////////////
			hql.append(" select distinct pnEmp.emp_Code as empCode, ");
			hql.append(" dbp.PREFIX_NAME as preName, ");
			hql.append(" pnEmp.first_Name as fName, ");
			hql.append(" pnEmp.last_Name as lName, ");
			hql.append(" pnOrg.org_Code as orgCode, ");
			hql.append(" pnOrg.div_Short || ' ' || pnOrg.area_Desc || ' ' || pnOrg.sec_Desc || ' ' || pnOrg.work_Desc  as descCode, ");
			hql.append(" prEmp.code_Seq_Work as coSeWo, ");
			hql.append(" wgDu.duty_Name as dutyName");
			hql.append(" from wg_employee pnEmp left outer join wg_duty wgDu");
			hql.append(" on pnEmp.DUTY_CODE = wgDu.DUTY_CODE , ");
			hql.append(" Wg_Pr_Employee prEmp, ");
			hql.append(" Pn_Organization pnOrg,Db_Pre_Suff dbp");
			hql.append(" where pnEmp.emp_Code = '");
			hql.append(empCode);
			hql.append("' ");
			hql.append(" and pnEmp.ou_Code = '");
			hql.append(ouCode);
			hql.append("' ");
			hql.append(" and prEmp.year = ");
			hql.append(year);
			hql.append(" and prEmp.period = ");
			hql.append(period);
			hql.append(" and pnEmp.ou_Code = prEmp.ou_Code");
			hql.append(" and pnEmp.emp_Code = prEmp.emp_Code  ");
			hql.append(" and pnEmp.ou_Code = pnOrg.ou_Code ");
			hql.append(" and prEmp.code_Seq_Work = pnOrg.code_Seq and pnEmp.PRE_NAME = dbp.PRE_SUFF_CODE");

			// /////// JDBC ///////////////////
			System.out.println(" -------------- " + hql.toString());
			List ls = this.jdbcTemplate.queryForList(hql.toString());
			ret = new GwIncdecEmployeeVO();
			ListOrderedMap row;
			if (ls != null && ls.size() > 0) {
				row = (ListOrderedMap) ls.get(0);
				String empcode = (String) row.get("empCode");
				String prefixName = (String) row.get("preName");
				String firstName = (String) row.get("fName");
				String lastName = (String) row.get("lName");

				String orgCode = (String) row.get("orgCode");
				String orgDesc = (String) row.get("descCode");
				BigDecimal codeSeqWork = (BigDecimal) row.get("coSeWo");
				String positionShort = (String) row.get("dutyName");

				ret.setEmpCode(empcode);
				ret.setName(prefixName + " " + firstName + " " + lastName);
				ret.setPositionShort(positionShort);
				ret.setOrgCode(orgCode);
				ret.setOrgDesc(orgDesc);
				ret.setCodeSeqWork(Integer.valueOf(codeSeqWork.toString()));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ret;
	}

	public GwIncdecEmployeeVO findByEmpCodeDetailInSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		GwIncdecEmployeeVO ret = new GwIncdecEmployeeVO();
		try {
			StringBuffer hql = new StringBuffer();

			// /////// JDBC ///////////////////
			hql.append(" select distinct pnEmp.emp_Code as empCode, ");
			hql.append(" dbp.PREFIX_NAME as preName, ");
			hql.append(" pnEmp.first_Name as fName, ");
			hql.append(" pnEmp.last_Name as lName, ");
			hql.append(" pnOrg.org_Code as orgCode, ");
			hql.append(" pnOrg.div_Short || ' ' || pnOrg.area_Desc || ' ' || pnOrg.sec_Desc || ' ' || pnOrg.work_Desc  as descCode, ");
			hql.append(" prEmp.code_Seq_Work as coSeWo, ");
			hql.append(" wgDu.duty_Name as dutyName");
			hql.append(" from wg_employee pnEmp left outer join wg_duty wgDu");
			hql.append(" on pnEmp.DUTY_CODE = wgDu.DUTY_CODE , ");
			hql.append(" Wg_Pr_Employee prEmp, ");
			hql.append(" Pn_Organization pnOrg,Db_Pre_Suff dbp");
			hql.append(" where pnEmp.emp_Code = '");
			hql.append(empCode);
			hql.append("' ");
			hql.append(" and pnEmp.ou_Code = '");
			hql.append(ouCode);
			hql.append("' ");
			hql.append(" and prEmp.year = ");
			hql.append(year);
			hql.append(" and prEmp.period = ");
			hql.append(period);

			hql.append("and (pnEmp.CODE_SEQ in (");
			hql.append("select vpnorganiz6_.CODE_SEQ ");
			hql.append("from V_PN_ORGANIZATION_SECURITY vpnorganiz6_");
			hql.append(" where vpnorganiz6_.USER_ID='" + userId
					+ "' and pnEmp.OU_CODE=vpnorganiz6_.OU_CODE ");
			hql.append(" and pnEmp.CODE_SEQ=vpnorganiz6_.CODE_SEQ");
			hql.append(")");
			hql.append(")");

			hql.append(" and pnEmp.ou_Code = prEmp.ou_Code");
			hql.append(" and pnEmp.emp_Code = prEmp.emp_Code  ");
			hql.append(" and pnEmp.ou_Code = pnOrg.ou_Code ");
			hql.append(" and prEmp.code_Seq_Work = pnOrg.code_Seq and pnEmp.PRE_NAME = dbp.PRE_SUFF_CODE");

			// /////// JDBC ///////////////////
			System.out.println(" -------------- " + hql.toString());
			List ls = this.jdbcTemplate.queryForList(hql.toString());
			ret = new GwIncdecEmployeeVO();
			ListOrderedMap row;
			if (ls != null && ls.size() > 0) {
				row = (ListOrderedMap) ls.get(0);
				String empcode = (String) row.get("empCode");
				String prefixName = (String) row.get("preName");
				String firstName = (String) row.get("fName");
				String lastName = (String) row.get("lName");

				String orgCode = (String) row.get("orgCode");
				String orgDesc = (String) row.get("descCode");
				BigDecimal codeSeqWork = (BigDecimal) row.get("coSeWo");
				String positionShort = (String) row.get("dutyName");

				ret.setEmpCode(empcode);
				ret.setName(prefixName + " " + firstName + " " + lastName);
				ret.setPositionShort(positionShort);
				ret.setOrgCode(orgCode);
				ret.setOrgDesc(orgDesc);
				ret.setCodeSeqWork(Integer.valueOf(codeSeqWork.toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ret;
	}

}