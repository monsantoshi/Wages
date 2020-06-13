package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PrEmployeeDAO;
import com.ss.tp.dto.CTRWRP201VO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.PnEmpMoveVO;
import com.ss.tp.dto.PrDailyRandomVO;
import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.dto.PrEmployeeVO;
import com.ss.tp.dto.RwDangerVO;
import com.ss.tp.dto.RwHealthVO;
import com.ss.tp.dto.RwOvertimeVO;
import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.model.PrEmployee;
import com.ss.tp.model.PrEmployeeText;
import com.ss.tp.dto.VPrEmployeeMonthTaxVO;

public class PrEmployeeDAOImpl extends HibernateDaoSupport implements
		PrEmployeeDAO, Serializable {

	private Log logger = LogFactory.getLog(this.getClass());
	private JdbcTemplate jdbcTemplate;
	

	public String[] findMaxYearPeriod(String ouCode) {

		String year = "";
		String section = "";
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT a.key.year || MAX(a.key.period) ");
		sql.append(" FROM   PrEmployee a ");
		sql.append(" WHERE  a.key.ouCode = '" + ouCode + "' ");
		sql.append(" and a.key.year = ( select max(b.key.year) from PrEmployee b ) ");
		sql.append(" group by a.key.year ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			section = rs.substring(4, rs.length());
		}

		String[] val = new String[2];
		val[0] = year;
		val[1] = section;

		return val;
	}

	public PrEmployeeVO findByCriteria(String ouCode, String year,
			String period, String empCode, String userId) {

		// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		PrEmployeeVO vo = new PrEmployeeVO();
		try {
			StringBuffer hql = new StringBuffer(0);
			hql.append(" from PrEmployee pr ");
			hql.append(" where ");
			hql.append(" pr.key.ouCode = '" + ouCode + "' ");
			hql.append(" and pr.key.year = " + year);
			hql.append(" and pr.key.period = " + period);
			hql.append(" and pr.key.empCode = '" + empCode + "' ");
			// hql.append(" and pr.key.empCode = ( ");
			// hql.append(" select pk.empCode ");
			// hql.append(" from VPrEmployeeSecurity ");
			// hql.append(" where pk.ouCode = '"+ouCode+"' ");
			// hql.append(" and pk.userId = '"+userId+"' ");
			// hql.append(" and pk.empCode = '"+empCode+"' ");
			// hql.append(" and pk.year = " + year);
			// hql.append(" and pk.period = "+ period);
			// hql.append(" ) ");

			List ls = this.getHibernateTemplate().find(hql.toString());
			if (ls != null && ls.size() > 0) {
				PrEmployee emp = (PrEmployee) ls.get(0);

				vo.setOuCode(emp.getKey().getOuCode());
				vo.setYear(emp.getKey().getYear());
				vo.setPeriod(emp.getKey().getPeriod());
				vo.setEmpCode(emp.getKey().getEmpCode());
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
				vo.setOldKlongChev(emp.getOldKlongChev());
				vo.setNewKlongChev(emp.getNewKlongChev());
				vo.setBankBranch(emp.getBankBranch());
				vo.setBankCode(emp.getBankCode());
				vo.setHealthFather(emp.getHealthFather());
				vo.setHandicappedDec(emp.getHandicappedDec());
				vo.setOldKc2000(emp.getOldKc2000());
				vo.setNewKc2000(emp.getNewKc2000());
				vo.setDebtLifeSpouse(emp.getDebtLifeSpouse());

			}

			hql.setLength(0);
			hql.append(" from PrEmployeeText ");
			hql.append(" where ouCode = '" + ouCode + "' ");
			hql.append(" and year = " + year);
			hql.append(" and period = " + period);
			hql.append(" and empCode = '" + empCode + "' ");
			hql.append(" and creBy = '" + userId + "' ");
			hql.append(" order by keySeq ");

			ls = this.getHibernateTemplate().find(hql.toString());
			System.out.println("change value test : " + ls.size());
			for (int i = 0; i < ls.size(); i++) {
				PrEmployeeText prText = (PrEmployeeText) ls.get(i);

				if (prText.getTaxId() != null
						&& !prText.getTaxId().trim().equals(""))
					vo.setTaxId(prText.getTaxId());

				if (prText.getMarriedStatus() != null
						&& !prText.getMarriedStatus().trim().equals(""))
					vo.setMarriedStatus(prText.getMarriedStatus());

				if (prText.getPayStatus() != null
						&& !prText.getPayStatus().trim().equals(""))
					vo.setPayStatus(prText.getPayStatus());

				if (prText.getBankId() != null
						&& !prText.getBankId().trim().equals(""))
					vo.setBankId(prText.getBankId());

				System.out.println("cost child : " + prText.getCostChild());

				if (prText.getCostChild() != null)
					vo.setCostChild(prText.getCostChild());

				if (prText.getChildStudy() != null)
					vo.setChildStudy(prText.getChildStudy());

				if (prText.getChildNoStudy() != null)
					vo.setChildNoStudy(prText.getChildNoStudy());

				if (prText.getCostLife() != null)
					vo.setCostLife(prText.getCostLife());

				if (prText.getGundanFlag() != null
						&& !prText.getGundanFlag().trim().equals(""))
					vo.setGundanFlag(prText.getGundanFlag());

				if (prText.getDebtLife() != null)
					vo.setDebtLife(prText.getDebtLife());
				
				if (prText.getDebtLifeSpouse() != null)
					vo.setDebtLifeSpouse(prText.getDebtLifeSpouse());

             
				if (prText.getDebtLoan() != null)
					vo.setDebtLoan(prText.getDebtLoan());

				if (prText.getDonate() != null)
					vo.setDonate(prText.getDonate());

				if (prText.getOther() != null)
					vo.setOther(prText.getOther());

				if (prText.getIncomeTax() != null)
					vo.setIncomeTax(prText.getIncomeTax());

				if (prText.getOldSalary() != null)
					vo.setOldSalary(prText.getOldSalary());

				if (prText.getNewSalary() != null)
					vo.setNewSalary(prText.getNewSalary());

				if (prText.getAdjOldsal() != null)
					vo.setAdjOldsal(prText.getAdjOldsal());

				if (prText.getAdjNewsal() != null)
					vo.setAdjNewsal(prText.getAdjNewsal());

				if (prText.getGundanAmt() != null)
					vo.setGundanAmt(prText.getGundanAmt());

				if (prText.getFlagPr() != null
						&& !prText.getFlagPr().trim().equals(""))
					vo.setFlagPr(prText.getFlagPr());

				if (prText.getDeductAmt() != null)
					vo.setDeductAmt(prText.getDeductAmt());

				if (prText.getSeqData() != null)
					vo.setSeqRec(prText.getSeqData());

				if (prText.getFlagMotherSpouse() != null
						&& !prText.getFlagMotherSpouse().trim().equals(""))
					vo.setFlagMotherSpouse(prText.getFlagMotherSpouse());

				if (prText.getFlagFatherSpouse() != null
						&& !prText.getFlagFatherSpouse().trim().equals(""))
					vo.setFlagFatherSpouse(prText.getFlagFatherSpouse());

				if (prText.getFlagMother() != null
						&& !prText.getFlagMother().trim().equals(""))
					vo.setFlagMother(prText.getFlagMother());

				if (prText.getFlagFather() != null
						&& !prText.getFlagFather().trim().equals(""))
					vo.setFlagFather(prText.getFlagFather());

				if (prText.getLtf() != null)
					vo.setLtf(prText.getLtf());

				if (prText.getRmf() != null)
					vo.setRmf(prText.getRmf());

				if (prText.getPensionFund() != null)
					vo.setPensionFund(prText.getPensionFund());

				if (prText.getTeacherFund() != null)
					vo.setTeacherFund(prText.getTeacherFund());

				if (prText.getOverageSpouse() != null)
					vo.setOverageSpouse(prText.getOverageSpouse());

				if (prText.getOverage() != null)
					vo.setOverage(prText.getOverage());

				if (prText.getOldKlongChev() != null)
					vo.setOldKlongChev(prText.getOldKlongChev());

				if (prText.getNewKlongChev() != null)
					vo.setNewKlongChev(prText.getNewKlongChev());

				if (prText.getBankBranch() != null)
					vo.setBankBranch(prText.getBankBranch());

				if (prText.getBankCode() != null
						&& !prText.getBankCode().trim().equals(""))
					vo.setBankCode(prText.getBankCode());

				if (prText.getHealthFather() != null)
					vo.setHealthFather(prText.getHealthFather());

				if (prText.getHandicappedDec() != null)
					vo.setHandicappedDec(prText.getHandicappedDec());

				if (prText.getOldKc2000() != null)
					vo.setOldKc2000(prText.getOldKc2000());
				if (prText.getNewKc2000() != null)
					vo.setNewKc2000(prText.getNewKc2000());

				if (prText.getCompensateLabour() != null)
					vo.setCompensateLabour(prText.getCompensateLabour());

				if (prText.getConfirmFlag() != null
						&& !prText.getConfirmFlag().trim().equals(""))
					vo.setConfirmFlag(prText.getConfirmFlag());
			}
		} catch (Exception e) {
			System.out.println("Not found PrEmployee");
			e.printStackTrace();
		}

		// System.out.println("xxx : " + vo.getOuCode() + " " + vo.getEmpCode()
		// + " " + vo.getYear() + " " + vo.getEmpCode());
		// System.out.println("vo.getAdjNewsal() : " + vo.getAdjNewsal());
		// System.out.println("vo.getCostLife() : " + vo.getCostLife());

		return vo;
	}

	public List findByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.key.empCode >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.key.empCode <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select pne.pk.empCode, ");
		hql.append(" sf.prefixName, ");
		hql.append(" pne.firstName, ");
		hql.append(" pne.lastName, ");
		hql.append(" pnp.positionShort, ");
		hql.append(" pno.orgCode, ");
		hql.append(" pno.divShort || ' ' || pno.areaDesc || ' ' || pno.secDesc || ' ' || pno.workDesc, ");
		hql.append(" pno.pk.codeSeq ");
		hql.append(" from PrEmployee pre, ");
		hql.append(" PnEmployee pne, PnPosition pnp, PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.key.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.key.year = " + year);
		hql.append(" and pre.key.period = " + period);
		// hql.append(" and pre.payStatus = '1' ");
		hql.append(" and pne.empStatus = 'B' ");
		hql.append(" and pre.key.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append("    and pk.year = " + year);
		hql.append("	and pk.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.key.empCode = pne.pk.empCode ");
		hql.append(" and pre.key.ouCode = pne.pk.ouCode ");
		hql.append(" and pne.pk.ouCode = pnp.pk.ouCode ");
		hql.append(" and pne.gworkCode = pnp.pk.gworkCode ");
		hql.append(" and pne.positionCode = pnp.pk.positionCode ");
		// hql.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
		hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pno.pk.codeSeq,pne.adminCode,pne.levelCode desc, pne.pk.empCode ");

		Query q = this.getSession().createQuery(hql.toString());

		// System.out.println("************************");
		List empList = q.setFirstResult(page * recPerPage)
				.setMaxResults(recPerPage).list();
		// System.out.println("************************");

		for (int i = 0; i < empList.size(); i++) {
			Object[] r = (Object[]) empList.get(i);
			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String positionShort = (String) r[4];
			String orgCode = (String) r[5];
			String orgDesc = (String) r[6];

			PayRollEmployeeVO vo = new PayRollEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			vo.setPositionShort(positionShort);
			vo.setOrgCode(orgCode);
			vo.setOrgDesc(orgDesc);

			empList.set(i, vo);
		}
		return empList;
	}

	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.key.empCode >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.key.empCode <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select count(pne.pk.empCode) ");
		hql.append(" from PrEmployee pre, ");
		hql.append(" PnEmployee pne, PnPosition pnp, PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.key.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.key.year = " + year);
		hql.append(" and pre.key.period = " + period);
		// hql.append(" and pre.payStatus = '1' ");
		hql.append(" and pne.empStatus = 'B' ");
		hql.append(" and pre.key.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append("    and pk.year = " + year);
		hql.append("	and pk.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.key.empCode = pne.pk.empCode ");
		hql.append(" and pre.key.ouCode = pne.pk.ouCode ");
		hql.append(" and pne.pk.ouCode = pnp.pk.ouCode ");
		hql.append(" and pne.gworkCode = pnp.pk.gworkCode ");
		hql.append(" and pne.positionCode = pnp.pk.positionCode ");
		// hql.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
		hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pne.pk.empCode ");

		Integer rs = (Integer) this.getSession().createQuery(hql.toString())
				.uniqueResult();

		return rs;
	}

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append(" and org.ORG_CODE >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append(" and org.ORG_CODE <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append(" and pnEm.EMP_CODE >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append(" and pnEm.EMP_CODE <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		/*
		 * hql.append(" ( "); hql.append(" select pnEm.EMP_CODE ,
		 * sf.PREFIX_NAME, pnEm.FIRST_NAME , " ); hql.append(" pnEm.LAST_NAME ,
		 * ps.POSITION_SHORT, prEm.CODE_SEQ_WORK " ); hql.append( " from
		 * PR_EMPLOYEE prEm, PN_EMPLOYEE pnEm, PN_POSITION ps, PN_ORGANIZATION
		 * org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v " ); hql.append("
		 * where prEm.OU_CODE = '"+ ouCode +"' ");
		 * hql.append(" and prEm.YEAR = " + intYear );
		 * hql.append(" and prEm.PERIOD = " + beforePeriod );
		 * hql.append(" and v.USER_ID = '" + userId + "' "); hql.append(" and
		 * prEm.OU_CODE = v.OU_CODE "); hql.append(" and prEm.CODE_SEQ_WORK =
		 * v.CODE_SEQ "); hql.append( criteria.toString() ); hql.append(" and
		 * prEm.EMP_CODE=pnEm.EMP_CODE "); hql.append(" and
		 * prEm.OU_CODE=pnEm.OU_CODE "); hql.append(" and
		 * pnEm.OU_CODE=ps.OU_CODE "); hql.append(" and
		 * pnEm.GWORK_CODE=ps.GWORK_CODE "); hql.append(" and
		 * pnEm.POSITION_CODE=ps.POSITION_CODE "); hql.append(" and
		 * prEm.CODE_SEQ_WORK=org.CODE_SEQ "); hql.append(" and
		 * prEm.OU_CODE=org.OU_CODE "); hql.append(" and
		 * pnEm.PRE_NAME=sf.PRE_SUFF_CODE "); hql.append(" and pnEm.EMP_CODE not
		 * in ( "); hql.append(" select t.emp_code "); hql.append(" from
		 * pr_employee_text t, v_pn_organization_security v " ); hql.append("
		 * where t.ou_code = '"+ ouCode +"' "); hql.append(" and t.year = " +
		 * year ); hql.append(" and t.period = " + period ); hql.append(" and
		 * t.flag_status = 'D' "); hql.append(" and v.user_id = '" + userId + "'
		 * "); hql.append(" and t.ou_code = v.ou_code "); hql.append(" and
		 * t.code_seq_work = v.code_seq "); hql.append(" ) "); hql.append(" )
		 * "); hql.append(" union "); hql.append (" select distinct
		 * pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , " ); hql.append("
		 * pnEm.LAST_NAME , ps.POSITION_SHORT, prEm.CODE_SEQ_WORK " );
		 * hql.append( " from pr_employee_text prEm, PN_EMPLOYEE pnEm,
		 * PN_POSITION ps, PN_ORGANIZATION org, DB_PRE_SUFF sf,
		 * V_PN_ORGANIZATION_SECURITY v " ); hql.append(" where prEm.OU_CODE='"+
		 * ouCode +"' "); hql.append(" and prEm.YEAR = " + year); hql.append("
		 * and prEm.PERIOD = " + period); hql.append(" and v.USER_ID = '" +
		 * userId + "' "); hql.append(" and prEm.FLAG_STATUS = 'I' ");
		 * hql.append(" and prEm.OU_CODE = v.OU_CODE "); hql.append(" and
		 * prEm.CODE_SEQ_WORK = v.CODE_SEQ "); hql.append( criteria.toString()
		 * ); hql.append(" and prEm.EMP_CODE=pnEm.EMP_CODE "); hql.append(" and
		 * prEm.OU_CODE=pnEm.OU_CODE "); hql.append(" and
		 * pnEm.OU_CODE=ps.OU_CODE "); hql.append(" and
		 * pnEm.GWORK_CODE=ps.GWORK_CODE "); hql.append(" and
		 * pnEm.POSITION_CODE=ps.POSITION_CODE "); hql.append(" and
		 * prEm.CODE_SEQ_WORK=org.CODE_SEQ "); hql.append(" and
		 * prEm.OU_CODE=org.OU_CODE "); hql.append(" and
		 * pnEm.PRE_NAME=sf.PRE_SUFF_CODE "); hql.append(" order by
		 * CODE_SEQ_WORK, EMP_CODE ");
		 */

		hql.append(" select distinct pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append(" pnEm.LAST_NAME , ps.POSITION_SHORT, prEm.CODE_SEQ_WORK, prEm.flag_status,pnEm.admin_code,pnEm.level_code ");
		hql.append(" from PR_EMPLOYEE_text prEm, PN_EMPLOYEE pnEm, PN_POSITION ps, PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
		hql.append(" where prEm.OU_CODE = '" + ouCode + "' ");
		hql.append(" and prEm.YEAR = " + year);
		hql.append(" and prEm.PERIOD = " + period);
		hql.append(" and v.USER_ID = '" + userId + "' ");
		// hql.append(" and prEm.pay_status = '1' ");
		hql.append(" and pnEm.EMP_STATUS = 'B' ");
		hql.append(criteria.toString());
		hql.append(" and prEm.OU_CODE = v.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		hql.append(" and prEm.EMP_CODE=pnEm.EMP_CODE ");
		hql.append(" and prEm.OU_CODE=pnEm.OU_CODE ");
		hql.append(" and pnEm.OU_CODE=ps.OU_CODE ");
		hql.append(" and pnEm.GWORK_CODE=ps.GWORK_CODE ");
		hql.append(" and pnEm.POSITION_CODE=ps.POSITION_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK=org.CODE_SEQ ");
		hql.append(" and prEm.OU_CODE=org.OU_CODE ");
		hql.append(" and pnEm.PRE_NAME=sf.PRE_SUFF_CODE ");
		hql.append(" and prEm.CRE_BY='" + userId + "' ");
		hql.append(" ORDER BY prEm.code_seq_work,pnEm.admin_code,pnEm.level_code desc, pnEm.emp_code, decode(prEm.flag_status,'D',1,'I',2,'A',3) ");

		// Query q = this.getSession().createQuery(hql.toString());

		System.out.println(" start : " + new Date());
		System.out.println("jdbc : " + hql.toString());

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		System.out.println(" end : " + new Date());

		// System.out.println("************************");
		// List empList = q.setFirstResult(page * recPerPage).setMaxResults(
		// recPerPage).list();
		// System.out.println("************************");

		List result = new ArrayList();
		int startRecord = page * recPerPage;

		try {
			for (int i = startRecord, count = 0; i < empList.size()
					&& count < recPerPage; i++, count++) {
				ListOrderedMap map = (ListOrderedMap) empList.get(i);
				String empCode = (String) map.getValue(0);
				String prefixName = (String) map.getValue(1);
				String firstName = (String) map.getValue(2);
				String lastName = (String) map.getValue(3);
				String positionShort = (String) map.getValue(4);
				String flagStatus = (String) map.getValue(6);
				// String orgDesc = (String) map.getValue(6);

				PayRollEmployeeVO vo = new PayRollEmployeeVO();
				vo.setEmpCode(empCode);
				vo.setName(prefixName + " " + firstName + " " + lastName);
				vo.setPositionShort(positionShort);
				vo.setFlagStatus(flagStatus);
				// vo.setOrgCode(orgCode);
				// vo.setOrgDesc(orgDesc);

				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append(" and org.ORG_CODE >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append(" and org.ORG_CODE <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append(" and pnEm.EMP_CODE >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append(" and pnEm.EMP_CODE <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		/*
		 * hql.append(" select count(*) "); hql.append(" from ( "); hql.append("
		 * ( "); hql.append(" select pnEm.EMP_CODE , sf.PREFIX_NAME,
		 * pnEm.FIRST_NAME , " ); hql.append(" pnEm.LAST_NAME ,
		 * ps.POSITION_SHORT "); hql.append( " from PR_EMPLOYEE prEm,
		 * PN_EMPLOYEE pnEm, PN_POSITION ps, PN_ORGANIZATION org, DB_PRE_SUFF
		 * sf, V_PN_ORGANIZATION_SECURITY v " ); hql.append(" where prEm.OU_CODE
		 * = '"+ ouCode +"' "); hql.append(" and prEm.YEAR = " + intYear );
		 * hql.append(" and prEm.PERIOD = " + beforePeriod ); hql.append(" and
		 * v.USER_ID = '" + userId + "' "); hql.append(" and prEm.OU_CODE =
		 * v.OU_CODE "); hql.append(" and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		 * hql.append( criteria.toString() ); hql.append(" and
		 * prEm.EMP_CODE=pnEm.EMP_CODE "); hql.append(" and
		 * prEm.OU_CODE=pnEm.OU_CODE "); hql.append(" and
		 * pnEm.OU_CODE=ps.OU_CODE "); hql.append(" and
		 * pnEm.GWORK_CODE=ps.GWORK_CODE "); hql.append(" and
		 * pnEm.POSITION_CODE=ps.POSITION_CODE "); hql.append(" and
		 * prEm.CODE_SEQ_WORK=org.CODE_SEQ "); hql.append(" and
		 * prEm.OU_CODE=org.OU_CODE "); hql.append(" and
		 * pnEm.PRE_NAME=sf.PRE_SUFF_CODE "); hql.append(" and pnEm.EMP_CODE not
		 * in ( "); hql.append(" select t.emp_code "); hql.append(" from
		 * pr_employee_text t, v_pn_organization_security v " ); hql.append("
		 * where t.ou_code = '"+ ouCode +"' "); hql.append(" and t.year = " +
		 * year ); hql.append(" and t.period = " + period ); hql.append(" and
		 * t.flag_status = 'D' "); hql.append(" and v.user_id = '" + userId + "'
		 * "); hql.append(" and t.ou_code = v.ou_code "); hql.append(" and
		 * t.code_seq_work = v.code_seq "); hql.append(" ) "); hql.append(" )
		 * "); hql.append(" union "); hql.append (" select distinct
		 * pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , " ); hql.append("
		 * pnEm.LAST_NAME , ps.POSITION_SHORT "); hql.append( " from
		 * pr_employee_text prEm, PN_EMPLOYEE pnEm, PN_POSITION ps,
		 * PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v "
		 * ); hql.append(" where prEm.OU_CODE='"+ ouCode +"' "); hql.append("
		 * and prEm.YEAR = " + year); hql.append(" and prEm.PERIOD = " +
		 * period); hql.append(" and v.USER_ID = '" + userId + "' ");
		 * hql.append(" and prEm.FLAG_STATUS = 'I' "); hql.append(" and
		 * prEm.OU_CODE = v.OU_CODE "); hql.append(" and prEm.CODE_SEQ_WORK =
		 * v.CODE_SEQ "); hql.append( criteria.toString() ); hql.append(" and
		 * prEm.EMP_CODE=pnEm.EMP_CODE "); hql.append(" and
		 * prEm.OU_CODE=pnEm.OU_CODE "); hql.append(" and
		 * pnEm.OU_CODE=ps.OU_CODE "); hql.append(" and
		 * pnEm.GWORK_CODE=ps.GWORK_CODE "); hql.append(" and
		 * pnEm.POSITION_CODE=ps.POSITION_CODE "); hql.append(" and
		 * prEm.CODE_SEQ_WORK=org.CODE_SEQ "); hql.append(" and
		 * prEm.OU_CODE=org.OU_CODE "); hql.append(" and
		 * pnEm.PRE_NAME=sf.PRE_SUFF_CODE "); hql.append(" ) x ");
		 */

		hql.append(" select count(*) from ( ");
		hql.append(" select distinct pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append(" pnEm.LAST_NAME , ps.POSITION_SHORT, prEm.CODE_SEQ_WORK, prEm.flag_status ");
		hql.append(" from PR_EMPLOYEE_text prEm, PN_EMPLOYEE pnEm, PN_POSITION ps, PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
		hql.append(" where prEm.OU_CODE = '" + ouCode + "' ");
		hql.append(" and prEm.YEAR = " + year);
		hql.append(" and prEm.PERIOD = " + period);
		hql.append(" and v.USER_ID = '" + userId + "' ");
		// hql.append(" and prEm.pay_status = '1' ");
		hql.append(" and pnEm.EMP_STATUS = 'B' ");
		hql.append(criteria.toString());
		hql.append(" and prEm.OU_CODE = v.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		hql.append(" and prEm.EMP_CODE=pnEm.EMP_CODE ");
		hql.append(" and prEm.OU_CODE=pnEm.OU_CODE ");
		hql.append(" and pnEm.OU_CODE=ps.OU_CODE ");
		hql.append(" and pnEm.GWORK_CODE=ps.GWORK_CODE ");
		hql.append(" and pnEm.POSITION_CODE=ps.POSITION_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK=org.CODE_SEQ ");
		hql.append(" and prEm.OU_CODE=org.OU_CODE ");
		hql.append(" and pnEm.PRE_NAME=sf.PRE_SUFF_CODE ");
		hql.append(" ORDER BY prEm.code_seq_work, pnEm.emp_code, decode(prEm.flag_status,'D',1,'I',2,'A',3) ");
		hql.append(" ) ");

		// Integer rs =
		// (Integer)this.getSession().createQuery(hql.toString()).uniqueResult();
		int tmp = this.jdbcTemplate.queryForInt(hql.toString());

		return new Integer(tmp);
	}

	public String[] findMaxPeriodName(String ouCode, String year, String period) {
		List periodName = null;
		String data[] = new String[3];
		String tempYear = "";
		try {
			String sql = "";
			sql = " select pr.pk.period,pr.periodName,pr.pk.year "
					+ " from PrPeriodLine pr " + " where pr.pk.ouCode ='"
					+ ouCode + "'" + " and pr.pk.year =" + year
					+ " and pr.pk.period =" + period;
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);
			periodName = qq.list();

			if (periodName.size() > 0) {
				for (Iterator it = periodName.iterator(); it.hasNext();) {
					Object[] obj = (Object[]) it.next();
					data[0] = String.valueOf(obj[0]);
					data[1] = (String) obj[1];
					tempYear = String.valueOf(obj[2]);
					if (tempYear != null && tempYear.length() > 0) {
						data[2] = tempYear.substring(0, 4);
					} else {
						data[2] = "";
					}
				}
			} else {
				data[0] = "";
				data[1] = "";
				data[2] = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
		// String sql="";
		// sql=" select div_code,div_desc,
		// area_code,area_desc,sec_code,sec_desc,cnt_emp,emp_A,emp_I,emp_D"
		// +
		// " , inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A,
		// amt_A"
		// +
		// " , cnt_R, amt_R,cnt_B,amt_B, cnt_S, amt_S " +
		// " from (" +
		// "select x.div_code,x.div_desc,
		// x.area_code,x.area_desc,x.sec_code,x.sec_desc, sum(cnt_emp) cnt_emp,
		// sum(emp_A) emp_A, sum(emp_I) emp_I, sum(emp_D) emp_D"
		// +
		// " , '00' inc_dec_code, '00' inc_dec_name, 0 cnt_rec, 0 amt_rec, 0
		// cnt_N, 0 amt_N, 0 cnt_A, 0 amt_A"
		// +
		// " , 0 cnt_R, 0 amt_R, 0 cnt_B, 0 amt_B, 0 cnt_S, 0 amt_S " +
		// " from ( " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$') area_code,c.area_desc, count(a.emp_code)
		// cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D "
		// +
		// " from pr_employee a, su_user_organization b, pn_organization c "
		// +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year = "+year+
		// " and a.period = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq_work = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq_work = c.code_seq" +
		// " group by c.div_code,c.div_desc,
		// c.area_code,c.area_desc,c.sec_code,c.sec_desc "
		// +
		// " union all " +
		// " select
		// c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$'),c.area_desc,
		// 0 cnt_emp, decode(a.flag_status,'A',1,0) emp_A,
		// decode(a.flag_status,'I',1,0) emp_I, decode(a.flag_status,'D',1,0)
		// emp_D"
		// +
		// " from pr_employee_text a, su_user_organization b, pn_organization c
		// "
		// +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year = "+year+
		// " and a.period = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq_work = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq_work = c.code_seq " +
		// " ) x group by x.div_code,x.div_desc,
		// x.area_code,x.area_desc,x.sec_code,x.sec_desc "
		// +
		// " union all " +
		// " select z.div_code,z.div_desc,
		// z.area_code,z.area_desc,z.sec_code,z.sec_desc, 0 cnt_emp, 0 emp_A, 0
		// emp_I, 0 emp_D "
		// +
		// " , z.inc_dec_code, y.inc_dec_name, z.cnt_rec, z.amt_rec, z.cnt_N,
		// z.amt_N, z.cnt_A, z.amt_A "
		// +
		// " , z.cnt_R, z.amt_R, z.cnt_B, z.amt_B, z.cnt_S, z.amt_S " +
		// " from ( " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$') area_code,c.area_desc, '01' inc_dec_code,
		// count(*) cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_mod_sal a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '01',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '11' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_overtime a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '11',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '12' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_premium a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '12',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '13' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_health a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '13',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '14' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_danger a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '14',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select
		// c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$'),c.area_desc,
		// a.inc_dec_code, count(*) cnt_rec, sum(total_amt) amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N,
		// sum(decode(a.flag_pr,'N',a.total_amt,0)) amt_N "
		// +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A,
		// sum(decode(a.flag_pr,'A',a.total_amt,0)) amt_A "
		// +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R,
		// sum(decode(a.flag_pr,'R',a.total_amt,0)) amt_R "
		// +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B,
		// sum(decode(a.flag_pr,'B',a.total_amt,0)) amt_B "
		// +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S,
		// sum(decode(a.flag_pr,'S',a.total_amt,0)) amt_S "
		// +
		// " from rw_inc_dec_other a, su_user_organization b, pn_organization c
		// "
		// +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq" +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// a.inc_dec_code ,c.sec_code,c.sec_desc"
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '21' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A " +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_vinai a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '21',c.sec_code,c.sec_desc "
		// +
		// " union " +
		// " select c.div_code,c.div_desc,c.sec_code,c.sec_desc,
		// nvl(c.area_code,'$'),c.area_desc, '22' inc_dec_code, count(*)
		// cnt_rec, 0 amt_rec "
		// +
		// " , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N " +
		// " , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A" +
		// " , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R " +
		// " , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B " +
		// " , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S " +
		// " from rw_vinai2 a, su_user_organization b, pn_organization c " +
		// " where a.ou_code = '"+ouCode+"' " +
		// " and a.year_pr = "+year+
		// " and a.period_pr = "+period +
		// " and b.user_id = '"+userId+"' " +
		// " and a.ou_code = b.ou_code " +
		// " and a.code_seq = b.code_seq " +
		// " and a.ou_code = c.ou_code " +
		// " and a.code_seq = c.code_seq " +
		// " group by c.div_code,c.div_desc, c.area_code,c.area_desc,
		// '22',c.sec_code,c.sec_desc "
		// +
		// " ) z, pr_income_deduct y " +
		// " where y.ou_code = '"+ouCode+"' " +
		// " and z.inc_dec_code = y.inc_dec_code " +
		// " order by div_code,div_desc, area_code,area_desc,sec_code,sec_desc,
		// inc_dec_code "
		// +
		// " )" +
		// " order by div_code,div_desc, area_code,area_desc,sec_code,sec_desc,
		// inc_dec_code "
		// ;

		// //////////////////NEW//////////////////////////////////////
		StringBuffer hql = new StringBuffer(0);

		// hql.append(" select div_code,div_desc,
		// area_code,area_desc,nvl(sec_code,'$') sec_code,nvl(sec_desc,'$$')
		// sec_desc,cnt_emp,emp_A,emp_I,emp_D ,
		// inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A, amt_A
		// , cnt_R, amt_R,cnt_B,amt_B, cnt_S, amt_S ");
		hql.append(" select div_code,div_desc, area_code,area_desc, sec_code, sec_desc,cnt_emp,emp_A,emp_I,emp_D , inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A, amt_A , cnt_R,  amt_R,cnt_B,amt_B, cnt_S, amt_S ");
		hql.append("  , cnt_costlife, cnt_gundan, cnt_costchild, sum_costchild ");
		hql.append(" from ");
		hql.append(" (	  /*				   for group record of count emp and A D I record				   */ ");
		hql.append(" select x.div_code,x.div_desc, x.area_code,x.area_desc,x.sec_code,x.sec_desc, sum(cnt_emp) cnt_emp, sum(emp_A) emp_A, sum(emp_I) emp_I, sum(emp_D) emp_D , '00' inc_dec_code,'00' inc_dec_name, 0 cnt_rec, 0 amt_rec, 0 cnt_N, 0 amt_N, 0 cnt_A, 0 amt_A , 0 cnt_R, 0 amt_R, 0 cnt_B, 0 amt_B, 0 cnt_S, 0 amt_S ");
		hql.append(" , sum(x.cnt_costlife) cnt_costlife, sum(x.cnt_gundan) cnt_gundan, sum(x.cnt_costchild) cnt_costchild, sum(x.sum_costchild) sum_costchild ");
		hql.append("   from (			 /*    for count employee record		   */					 				");
		hql.append("					select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, count(a.emp_code) cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D ");
		hql.append(" ,  sum(decode(nvl(a.costlife,0),0,0,1)) cnt_costlife, sum(decode(nvl(a.gundan_flag,'N'),'N',0,1)) cnt_gundan, sum(decode(nvl(a.cost_child,0),0,0,1)) cnt_costchild, sum(nvl(a.cost_child,0)) sum_costchild ");
		hql.append(" from v_pr_employee_temp a, su_user_organization b, pn_organization c ");
		hql.append(" where a.ou_code = '" + ouCode + "'               ");
		hql.append(" and a.year = " + year);
		hql.append(" and a.period = " + period);
		hql.append(" and b.user_id = '" + userId + "'           ");
		hql.append(" and a.pay_Status = '1' ");
		hql.append(" and a.ou_code = b.ou_code              and a.code_seq_work = b.code_seq        and a.ou_code = c.ou_code ");
		hql.append(" and a.code_seq_work = c.code_seq  group by c.div_code,c.div_desc, c.area_code,c.area_desc,c.sec_code,c.sec_desc    ");
		hql.append(" union all          ");
		hql.append(" /*    for count A D I record		   */");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$'),c.area_desc, 0 cnt_emp, decode(a.flag_status,'A',1,0) emp_A, decode(a.flag_status,'I',1,0) emp_I, decode(a.flag_status,'D',1,0) emp_D ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_costchild ");
		hql.append(" from pr_employee_text a, su_user_organization b, pn_organization c   ");
		hql.append(" where a.ou_code = '" + ouCode + "'        ");
		hql.append(" and a.year = " + year);
		hql.append(" and a.period = " + period);
		hql.append(" and b.user_id = '" + userId + "'     ");
		hql.append(" and a.ou_code = b.ou_code      ");
		hql.append(" and a.code_seq_work = b.code_seq        and a.ou_code = c.ou_code and a.code_seq_work = c.code_seq  ");
		hql.append(" ) x       ");
		hql.append(" group by x.div_code,x.div_desc, x.area_code,x.area_desc,x.sec_code,x.sec_desc       ");
		hql.append(" union all                ");
		hql.append(" select z.div_code,z.div_desc, z.area_code,z.area_desc,z.sec_code,z.sec_desc, 0 cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D, z.inc_dec_code, y.inc_dec_name, z.cnt_rec, z.amt_rec, z.cnt_N, z.amt_N, z.cnt_A, z.amt_A, z.cnt_R, z.amt_R, z.cnt_B, z.amt_B, z.cnt_S, z.amt_S   ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from (           ");
		hql.append(" /* for count 01 record		 								 */ ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '01' inc_dec_code, count(*) cnt_rec, 0 amt_rec, sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N ");
		hql.append(" , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A         , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0amt_R  ");
		hql.append(" , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B                  , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_mod_sal a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'       ");
		hql.append(" and a.year_pr = " + year);
		hql.append("  and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'        ");
		hql.append(" and a.ou_code = b.ou_code           and a.code_seq = b.code_seq		and a.ou_code = c.ou_code               and a.code_seq = c.code_seq   ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '01',c.sec_code,c.sec_desc   ");
		hql.append(" union           ");
		hql.append(" /* for count 11 record										 */ ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '11' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N, sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A         , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B           , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild       ");
		hql.append(" from rw_overtime a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.ou_code = b.ou_code		and a.code_seq = b.code_seq         and a.ou_code = c.ou_code and a.code_seq = c.code_seq   ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '11',c.sec_code,c.sec_desc ");
		hql.append(" union ");
		hql.append(" /* for count 12 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '12' inc_dec_code, count(*)cnt_rec, 0 amt_rec              , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0amt_A           , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         ,sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S      ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_premium a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'          ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.ou_code = b.ou_code     ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '12',c.sec_code,c.sec_desc     ");
		hql.append(" union           ");
		hql.append(" /* for count 13 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '13' inc_dec_code, count(*) cnt_rec, 0 amt_rec ");
		hql.append(" , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N                       , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A ");
		hql.append(" , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R                         , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B ");
		hql.append(" , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_health a, su_user_organization b,pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'            ");
		hql.append(" and a.ou_code = b.ou_code         ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code ");
		hql.append(" and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '13',c.sec_code,c.sec_desc       ");
		hql.append(" union ");
		hql.append(" /* for count 14 record			 							 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '14' inc_dec_code, count(*) cnt_rec ");
		hql.append(" , 0 amt_rec         , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N             , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A ");
		hql.append(" , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B    , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S     ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_danger a, su_user_organization b, pn_organization c   ");
		hql.append(" where a.ou_code = '" + ouCode + "'");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'   ");
		hql.append(" and a.ou_code = b.ou_code               and a.code_seq = b.code_seq               and a.ou_code = c.ou_code               and a.code_seq = c.code_seq    ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '14',c.sec_code,c.sec_desc  ");
		hql.append(" union ");
		hql.append(" /* for count income other record										 */  ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$'),c.area_desc, a.inc_dec_code, count(*) cnt_rec, sum(total_amt) amt_rec             , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, sum(decode(a.flag_pr,'N',a.total_amt,0)) amt_N  , sum(decode(a.flag_pr,'A',1,0)) cnt_A, sum(decode(a.flag_pr,'A',a.total_amt,0)) amt_A                    , sum(decode(a.flag_pr,'R',1,0)) cnt_R, sum(decode(a.flag_pr,'R',a.total_amt,0)) amt_R     ,");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, sum(decode(a.flag_pr,'B',a.total_amt,0)) amt_B , sum(decode(a.flag_pr,'S',1,0)) cnt_S, sum(decode(a.flag_pr,'S',a.total_amt,0)) amt_S  ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_inc_dec_other a, su_user_organization b, pn_organization c ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'             ");
		hql.append(" and a.ou_code = b.ou_code               ");
		hql.append(" and a.code_seq = b.code_seq and a.ou_code = c.ou_code and a.code_seq = c.code_seq        ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, a.inc_dec_code ,c.sec_code,c.sec_desc   ");
		hql.append(" union           ");
		hql.append(" /* for count 21 record										 */");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '21' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A   , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_vinai a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'           ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'     ");
		hql.append(" and a.ou_code = b.ou_code ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '21',c.sec_code,c.sec_desc     ");
		hql.append(" /* for count 22 record										 */ ");
		hql.append(" union       ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$'),c.area_desc, '22' inc_dec_code, count(*) cnt_rec, 0 amt_rec          ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N              , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A      , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R             , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B          , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" , 0 cnt_costlife, 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from rw_vinai2 a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'           ");
		hql.append(" and a.ou_code = b.ou_code       ");
		hql.append(" and a.code_seq = b.code_seq               and a.ou_code = c.ou_code               and a.code_seq = c.code_seq     ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '22',c.sec_code,c.sec_desc ");
		hql.append(" ) z, pr_income_deduct y          ");
		hql.append(" where y.ou_code = '" + ouCode
				+ "'         and z.inc_dec_code = y.inc_dec_code     ");
		hql.append(" order by div_code,div_desc, area_code,area_desc,sec_code,sec_desc, inc_dec_code ");
		hql.append(" )        ");
		hql.append(" order by div_code,div_desc, area_code,area_desc,NVL(sec_code,'$'),sec_desc, inc_dec_code ");

		list = this.jdbcTemplate.queryForList(hql.toString());
		// System.out.println(hql.toString());

		System.out.println("**************** LIST ***************** ::: "
				+ list.size());

		try {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap map = (ListOrderedMap) list.get(i);
				String divCode = (String) map.getValue(0);
				String divName = (String) map.getValue(1);
				String areaCode = (String) map.getValue(2);
				String areaName = (String) map.getValue(3);

				String secCode = (String) map.getValue(4);
				String secName = (String) map.getValue(5);

				Long cntEmp = new Long(map.getValue(6).toString());
				Long empA = new Long(map.getValue(7).toString());
				Long empI = new Long(map.getValue(8).toString());
				Long empD = new Long(map.getValue(9).toString());

				String incDecCode = (String) map.getValue(10);
				String incDecName = (String) map.getValue(11);

				Long cntRec = new Long(map.getValue(12).toString());
				Double amtRec = new Double(map.getValue(13).toString());
				Long cntN = new Long(map.getValue(14).toString());
				Double amtN = new Double(map.getValue(15).toString());
				Long cntA = new Long(map.getValue(16).toString());
				Double amtA = new Double(map.getValue(17).toString());
				Long cntR = new Long(map.getValue(18).toString());
				Double amtR = new Double(map.getValue(19).toString());
				Long cntB = new Long(map.getValue(20).toString());
				Double amtB = new Double(map.getValue(21).toString());
				Long cntS = new Long(map.getValue(22).toString());
				Double amtS = new Double(map.getValue(23).toString());

				Long cLife = new Long(map.getValue(24).toString());
				Long cGundan = new Long(map.getValue(25).toString());
				Long cChild = new Long(map.getValue(26).toString());
				Long sumcChild = new Long(map.getValue(27).toString());

				CTRWRP201VO vo = new CTRWRP201VO();
				vo.setDivCode(divCode);
				vo.setDivName(divName);
				vo.setAreaCode(areaCode);
				vo.setAreaName(areaName);

				vo.setSecCode(secCode);
				vo.setSecName(secName);

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

				vo.setCostLife(cLife);
				vo.setGundan(cGundan);
				vo.setCostChild(cChild);
				vo.setSumCostChild(sumcChild);

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

	public PrEmployee loadPrEmployee(PrEmployeeTextVO prEmpVo) {
		PrEmployee rwp = new PrEmployee();
		try {
			System.out.println(">>>>>>>>>>>>>>> " + prEmpVo.getOuCode()
					+ " :: " + prEmpVo.getYear() + " :: "
					+ prEmpVo.getEmpCode() + " :: " + prEmpVo.getPeriod());
			// rwp = (RwOvertime)
			// this.getHibernateTemplate().load(RwOvertime.class,rpVo.getKeySeq());
			Criteria c = this.getSessionFactory().openSession()
					.createCriteria(PrEmployee.class);
			c.add(Restrictions.eq("key.ouCode", prEmpVo.getOuCode()));
			c.add(Restrictions.eq("key.year", Double.valueOf(prEmpVo.getYear())));
			c.add(Restrictions.eq("key.empCode", prEmpVo.getEmpCode()));
			c.add(Restrictions.eq("key.period",
					Double.valueOf(prEmpVo.getPeriod())));

			rwp = (PrEmployee) c.list().get(0);
			// rwp = this.getHibernateTemplate().findByCriteria(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updatePrEmpByPrEmpText(PrEmployeeTextVO prEmpTextVo)
			throws Exception {
		PrEmployee prEmp = new PrEmployee();

		try {
			prEmp = this.loadPrEmployee(prEmpTextVo);

			prEmp.setUpdBy(prEmpTextVo.getUpdBy());
			prEmp.setUpdDate(new Date());
			if (prEmpTextVo.getFlagStatus().equals("D")) {
				prEmp.setNewCodeSeq(null);
				prEmp.setNewPayStatus("2");
			} else {
				prEmp.setNewCodeSeq(Double.valueOf(prEmpTextVo.getCodeSeqWork()));
				prEmp.setNewPayStatus("1");
			}

			this.getHibernateTemplate().update(prEmp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		if (areaCode != null && !areaCode.equals(""))
			criteria.append(" and nvl(c.area_code,'$') = '" + areaCode + "' ");

		if (secCode != null && !secCode.equals("")) {
			criteria.append(" and c.sec_code = '" + secCode + "' ");
		} else if (secCode == null) {
			criteria.append(" and c.sec_code is null  ");
		}
		/*
		 * 
		 * StringBuffer hql = new StringBuffer(0); hql.append( " select
		 * flag_work,flag_pr,sum(tot_day1_1)||'/'||sum(tot_day1_2) tot_day1, "
		 * ); hql.append( " sum(tot_day15_1)||'/'||sum(tot_day15_2)
		 * tot_day15,sum(tot_day3_1)||'/'||sum(tot_day3_2) tot_day3" );
		 * hql.append(" FROM( "); hql.append( " SELECT c.div_code, c.div_desc,
		 * c.sec_code, c.sec_desc, "); hql.append( " NVL (c.area_code, '$')
		 * area_code, c.area_desc, '11' inc_dec_code, " ); hql.append( "
		 * DECODE(ot_type,'1',sum(tot_day1)+nvl(sum(amt_day1),0),0) tot_day1_1,
		 * DECODE(ot_type,'1',sum(tot_day15)+nvl(sum(amt_day15),0),0)
		 * tot_day15_1, DECODE(ot_type,'1',sum(tot_day3)+nvl(sum(amt_day3),0),0)
		 * tot_day3_1, " ); hql.append( "
		 * DECODE(ot_type,'2',sum(tot_day1)+nvl(sum(amt_day1),0),0) tot_day1_2,
		 * DECODE(ot_type,'2',sum(tot_day15)+nvl(sum(amt_day15),0),0)
		 * tot_day15_2, DECODE(ot_type,'2',sum(tot_day3)+nvl(sum(amt_day3),0),0
		 * ) tot_day3_2, " ); hql.append(" flag_work,flag_pr,ot_type ");
		 * hql.append( " FROM rw_overtime a, su_user_organization b,
		 * pn_organization c "); hql.append(" WHERE a.ou_code = '"+ouCode+"' ");
		 * hql.append(" AND a.year_pr = "+year); hql.append(" AND a.period_pr =
		 * "+period); hql.append(" AND b.user_id = '"+userId+"' "); hql.append(
		 * criteria.toString()); hql.append(" AND a.ou_code = b.ou_code ");
		 * hql.append(" AND a.code_seq = b.code_seq "); hql.append(" AND
		 * a.ou_code = c.ou_code "); hql.append(" AND a.code_seq = c.code_seq
		 * "); hql.append( " GROUP BY c.div_code, c.div_desc, c.sec_code,
		 * c.sec_desc,NVL (c.area_code, '$'), c.area_desc,
		 * '11',flag_work,flag_pr,ot_type " ); hql.append(" ) d "); hql.append("
		 * GROUP BY flag_work,flag_pr "); hql.append( " ORDER BY
		 * DECODE(flag_pr,'N'
		 * ,1,'A',2,'R',3,'B',4,'S',5,6),DECODE(flag_work,'Y',1,2) " );
		 */

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select a.ot_type,a.flag_work,a.flag_pr,sum(a.tot_day1)||'/'||sum(amt_day1) tot_day1,  ");
		hql.append("           sum(tot_day15)||'/'||sum(amt_day15) tot_day15,sum(tot_day3)||'/'||sum(amt_day3) tot_day3");
		hql.append("	FROM rw_overtime a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("    GROUP BY ot_type,flag_work,flag_pr ");
		hql.append("    ORDER BY ot_type,DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6),DECODE(flag_work,'N',1,2) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String otType = (String) map.getValue(0);
			String flagWork = (String) map.getValue(1);
			String flagPr = (String) map.getValue(2);
			String totDay1_R = (String) map.getValue(3);
			String totDay15_R = (String) map.getValue(4);
			String totDay3_R = (String) map.getValue(5);

			RwOvertimeVO vo = new RwOvertimeVO();
			vo.setOtType(otType);
			vo.setFlagWork(flagWork);
			vo.setFlagPr(flagPr);
			vo.setTotDay1_R(totDay1_R);
			vo.setTotDay15_R(totDay15_R);
			vo.setTotDay3_R(totDay3_R);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		if (areaCode != null && !areaCode.equals(""))
			criteria.append(" and nvl(c.area_code,'$') = '" + areaCode + "' ");

		if (secCode != null && !secCode.equals("")) {
			criteria.append(" and c.sec_code = '" + secCode + "' ");
		} else if (secCode == null) {
			criteria.append(" and c.sec_code is null  ");
		}

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.mor_day) mor_day,sum(a.aft_day) aft_day,sum(a.evn_day) evn_day,  ");
		hql.append("           sum(a.mor_hour) mor_hour,sum(a.aft_hour) aft_hour,sum(a.evn_hour) evn_hour ");
		hql.append("	FROM rw_premium a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double morDay = new Double(map.getValue(1).toString());
			Double aftDay = new Double(map.getValue(2).toString());
			Double evnDay = new Double(map.getValue(3).toString());
			Double morHour = new Double(map.getValue(4).toString());
			Double aftHour = new Double(map.getValue(5).toString());
			Double evnHour = new Double(map.getValue(6).toString());

			RwPremiumVO vo = new RwPremiumVO();
			vo.setFlagPr(flagPr);
			vo.setMorDay(morDay);
			vo.setAftDay(aftDay);
			vo.setEvnDay(evnDay);
			vo.setMorHour(morHour);
			vo.setAftHour(aftHour);
			vo.setEvnHour(evnHour);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		if (areaCode != null && !areaCode.equals(""))
			criteria.append(" and nvl(c.area_code,'$') = '" + areaCode + "' ");

		if (secCode != null && !secCode.equals("")) {
			criteria.append(" and c.sec_code = '" + secCode + "' ");
		} else if (secCode == null) {
			criteria.append(" and c.sec_code is null  ");
		}

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.total_hour) total_hour ");
		hql.append("	FROM rw_health a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double totalHour = new Double(map.getValue(1).toString());

			RwHealthVO vo = new RwHealthVO();
			vo.setFlagPr(flagPr);
			vo.setTotalHour(totalHour);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		if (areaCode != null && !areaCode.equals(""))
			criteria.append(" and nvl(c.area_code,'$') = '" + areaCode + "' ");

		if (secCode != null && !secCode.equals("")) {
			criteria.append(" and c.sec_code = '" + secCode + "' ");
		} else if (secCode == null) {
			criteria.append(" and c.sec_code is null  ");
		}

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.full_day) full_day,sum(a.half_day) half_day ");
		hql.append("	FROM rw_danger a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double fullDay = new Double(map.getValue(1).toString());
			Double halfDay = new Double(map.getValue(2).toString());

			RwDangerVO vo = new RwDangerVO();
			vo.setFlagPr(flagPr);
			vo.setFullDay(fullDay);
			vo.setHalfDay(halfDay);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		/*
		 * if (areaCode != null && !areaCode.equals("")) criteria.append(" and
		 * nvl(c.area_code,'$') = '" + areaCode + "' ");
		 * 
		 * if (secCode != null && !secCode.equals("")) criteria.append(" and
		 * nvl(c.sec_code,'$') = '" + secCode + "' ");
		 */

		/*
		 * StringBuffer hql = new StringBuffer(0); hql.append( " select
		 * flag_work,flag_pr,sum(tot_day1_1)||'/'||sum(tot_day1_2) tot_day1, "
		 * ); hql.append( " sum(tot_day15_1)||'/'||sum(tot_day15_2)
		 * tot_day15,sum(tot_day3_1)||'/'||sum(tot_day3_2) tot_day3" );
		 * hql.append(" FROM( "); hql.append( " SELECT c.div_code, c.div_desc,
		 * c.sec_code, c.sec_desc, "); hql.append( " NVL (c.area_code, '$')
		 * area_code, c.area_desc, '11' inc_dec_code, " ); hql.append( "
		 * DECODE(ot_type,'1',sum(tot_day1)+nvl(sum(amt_day1),0),0) tot_day1_1,
		 * DECODE(ot_type,'1',sum(tot_day15)+nvl(sum(amt_day15),0),0)
		 * tot_day15_1, DECODE(ot_type,'1',sum(tot_day3)+nvl(sum(amt_day3),0),0)
		 * tot_day3_1, " ); hql.append( "
		 * DECODE(ot_type,'2',sum(tot_day1)+nvl(sum(amt_day1),0),0) tot_day1_2,
		 * DECODE(ot_type,'2',sum(tot_day15)+nvl(sum(amt_day15),0),0)
		 * tot_day15_2, DECODE(ot_type,'2',sum(tot_day3)+nvl(sum(amt_day3),0),0
		 * ) tot_day3_2, " ); hql.append(" flag_work,flag_pr,ot_type ");
		 * hql.append( " FROM rw_overtime a, su_user_organization b,
		 * pn_organization c "); hql.append(" WHERE a.ou_code = '"+ouCode+"' ");
		 * hql.append(" AND a.year_pr = "+year); hql.append(" AND a.period_pr =
		 * "+period); hql.append(" AND b.user_id = '"+userId+"' "); hql.append(
		 * criteria.toString()); hql.append(" AND a.ou_code = b.ou_code ");
		 * hql.append(" AND a.code_seq = b.code_seq "); hql.append(" AND
		 * a.ou_code = c.ou_code "); hql.append(" AND a.code_seq = c.code_seq
		 * "); hql.append( " GROUP BY c.div_code, c.div_desc, c.sec_code,
		 * c.sec_desc,NVL (c.area_code, '$'), c.area_desc,
		 * '11',flag_work,flag_pr,ot_type " ); hql.append(" ) d "); hql.append("
		 * GROUP BY flag_work,flag_pr "); hql.append( " ORDER BY
		 * DECODE(flag_pr,'N'
		 * ,1,'A',2,'R',3,'B',4,'S',5,6),DECODE(flag_work,'Y',1,2) " );
		 */

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select a.ot_type,a.flag_work,a.flag_pr,sum(a.tot_day1)||'/'||sum(amt_day1) tot_day1,  ");
		hql.append("           sum(tot_day15)||'/'||sum(amt_day15) tot_day15,sum(tot_day3)||'/'||sum(amt_day3) tot_day3");
		hql.append("	FROM rw_overtime a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("    GROUP BY ot_type,flag_work,flag_pr ");
		hql.append("    ORDER BY ot_type,DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6),DECODE(flag_work,'N',1,2) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String otType = (String) map.getValue(0);
			String flagWork = (String) map.getValue(1);
			String flagPr = (String) map.getValue(2);
			String totDay1_R = (String) map.getValue(3);
			String totDay15_R = (String) map.getValue(4);
			String totDay3_R = (String) map.getValue(5);

			RwOvertimeVO vo = new RwOvertimeVO();
			vo.setOtType(otType);
			vo.setFlagWork(flagWork);
			vo.setFlagPr(flagPr);
			vo.setTotDay1_R(totDay1_R);
			vo.setTotDay15_R(totDay15_R);
			vo.setTotDay3_R(totDay3_R);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		/*
		 * if (areaCode != null && !areaCode.equals("")) criteria.append(" and
		 * nvl(c.area_code,'$') = '" + areaCode + "' ");
		 * 
		 * if (secCode != null && !secCode.equals("")) criteria.append(" and
		 * c.sec_code = '" + secCode + "' ");
		 */

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.mor_day) mor_day,sum(a.aft_day) aft_day,sum(a.evn_day) evn_day,  ");
		hql.append("           sum(a.mor_hour) mor_hour,sum(a.aft_hour) aft_hour,sum(a.evn_hour) evn_hour ");
		hql.append("	FROM rw_premium a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double morDay = new Double(map.getValue(1).toString());
			Double aftDay = new Double(map.getValue(2).toString());
			Double evnDay = new Double(map.getValue(3).toString());
			Double morHour = new Double(map.getValue(4).toString());
			Double aftHour = new Double(map.getValue(5).toString());
			Double evnHour = new Double(map.getValue(6).toString());

			RwPremiumVO vo = new RwPremiumVO();
			vo.setFlagPr(flagPr);
			vo.setMorDay(morDay);
			vo.setAftDay(aftDay);
			vo.setEvnDay(evnDay);
			vo.setMorHour(morHour);
			vo.setAftHour(aftHour);
			vo.setEvnHour(evnHour);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		/*
		 * if (areaCode != null && !areaCode.equals("")) criteria.append(" and
		 * nvl(c.area_code,'$') = '" + areaCode + "' ");
		 * 
		 * if (secCode != null && !secCode.equals("")) criteria.append(" and
		 * c.sec_code = '" + secCode + "' ");
		 */

		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.total_hour) total_hour ");
		hql.append("	FROM rw_health a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double totalHour = new Double(map.getValue(1).toString());

			RwHealthVO vo = new RwHealthVO();
			vo.setFlagPr(flagPr);
			vo.setTotalHour(totalHour);

			empList.set(i, vo);
		}
		return empList;
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		StringBuffer criteria = new StringBuffer(0);

		if (divCode != null && !divCode.equals(""))
			criteria.append(" and c.div_code = '" + divCode + "' ");

		/*
		 * if (areaCode != null && !areaCode.equals("")) criteria.append(" and
		 * nvl(c.area_code,'$') = '" + areaCode + "' ");
		 * 
		 * if (secCode != null && !secCode.equals("")) criteria.append(" and
		 * c.sec_code = '" + secCode + "' ");
		 */
		StringBuffer hql = new StringBuffer(0);
		hql.append("	select flag_pr,sum(a.full_day) full_day,sum(a.half_day) half_day ");
		hql.append("	FROM rw_danger a, su_user_organization b, pn_organization c ");
		hql.append("	WHERE a.ou_code = '" + ouCode + "' ");
		hql.append("	AND a.year_pr = " + year);
		hql.append("	AND a.period_pr = " + period);
		hql.append("	AND b.user_id = '" + userId + "' ");
		hql.append(criteria.toString());
		hql.append("	AND a.ou_code = b.ou_code ");
		hql.append("	AND a.code_seq = b.code_seq ");
		hql.append("	AND a.ou_code = c.ou_code ");
		hql.append("    AND a.code_seq = c.code_seq ");
		hql.append("	GROUP BY  flag_pr ");
		hql.append("    ORDER BY DECODE(flag_pr,'N',1,'A',2,'R',3,'B',4,'S',5,6) ");

		List empList = this.jdbcTemplate.queryForList(hql.toString());
		for (int i = 0; i < empList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) empList.get(i);
			String flagPr = (String) map.getValue(0);
			Double fullDay = new Double(map.getValue(1).toString());
			Double halfDay = new Double(map.getValue(2).toString());

			RwDangerVO vo = new RwDangerVO();
			vo.setFlagPr(flagPr);
			vo.setFullDay(fullDay);
			vo.setHalfDay(halfDay);

			empList.set(i, vo);
		}
		return empList;
	}
	public List findByCriteriaTax(String evaOuCode, Integer evaYear,
			Integer evaPeriod)
			throws Exception {

		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " SELECT EMP_CODE,TO_CHAR(OLD_SALARY),TO_CHAR(SALARY),TO_CHAR(MARRIED_STATUS),TO_CHAR(CHILD_STUDY),TO_CHAR(CHILD_NO_STUDY),TO_CHAR(DEBTLIFE),TO_CHAR(DEBTLOAN),TO_CHAR(DONATE)," +
				" TO_CHAR(FLAG_MOTHER_SPOUSE),TO_CHAR(AAAA),TO_CHAR(FLAG_FATHER_SPOUSE),TO_CHAR(FLAG_MOTHER),TO_CHAR(FLAG_FATHER),TO_CHAR(LTF),TO_CHAR(RMF),TO_CHAR(HEALTH_FATHER)," +
				" TO_CHAR(HANDICAPPED_DEC),TO_CHAR(PVF_COLLECT),TO_CHAR(INCOME_COLLECT),TO_CHAR(INCOME_FIX),TO_CHAR(INCOME_VALIABLE),TO_CHAR(TAX_COLLECT),TO_CHAR(PVF_RATE)," +
				" TO_CHAR(TAX) FROM ( " +
				" SELECT  EMP_CODE  ,old_salary ,SALARY_PROMOTE SALARY, NVL(MARRIED_STATUS,0) MARRIED_STATUS,    NVL(CHILD_STUDY,0) CHILD_STUDY,NVL(CHILD_NO_STUDY,0) CHILD_NO_STUDY," +
				" NVL(DEBTLIFE,0) DEBTLIFE ,NVL(DEBTLOAN,0) DEBTLOAN ,NVL(DONATE,0) DONATE ,DECODE(NVL(FLAG_MOTHER_SPOUSE,'N'),'Y',30000,'N',0,' ','0') FLAG_MOTHER_SPOUSE,   DECODE(FLAG_MOTHER_SPOUSE,' ','spc','normal') aaaa " +
				" ,DECODE(NVL(FLAG_FATHER_SPOUSE,'N'),'Y',30000,'N',0,' ','0') FLAG_FATHER_SPOUSE , DECODE(NVL(FLAG_MOTHER,'N'),'Y',30000,'N',0,' ','0') FLAG_MOTHER  ,DECODE( NVL(FLAG_FATHER,'N'),'Y',30000,'N',0,' ','0') FLAG_FATHER," +
				" NVL(LTF,0) LTF, NVL(RMF,0) RMF, NVL(HEALTH_FATHER,0) HEALTH_FATHER  ,NVL(HANDICAPPED_DEC,0)  HANDICAPPED_DEC ," +
				" nvl(Pr_Process.ACC_PVF(ou_code,'"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "',emp_code),0) pvf_collect" +
			    " ,NVL(Get_Income_Collect (ou_code,YEAR,PERIOD-2,emp_code) ,0) INCOME_COLLECT" +
			    " ,nvl(Get_Income_Fix (ou_code,YEAR,PERIOD-2,emp_code),0) INCOME_FIX " +
			    " ,nvl(Get_Income_Collect (ou_code,YEAR,PERIOD-2,emp_code),0) - nvl(Get_Income_Fix (ou_code,YEAR,PERIOD-2,emp_code),0) INCOME_VALIABLE " +
			    " ,nvl(Get_Tax_Collect  (ou_code,YEAR,PERIOD-2,emp_code),0) TAX_COLLECT" +
			    " ,nvl(Pvf_Rate_Kung(YEAR,emp_code),0) pvf_rate" +
			    " ,nvl(Tax_Per_Month_Kung (OU_CODE,'"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "',EMP_CODE),0) TAX," +
			    " OU_CODE, YEAR  , PERIOD " +
			    " FROM  PR_EMPLOYEE" +
			    " WHERE  PAY_STATUS='1'" +
			    " AND PERIOD = " + evaPeriod +
			    " AND YEAR = "+ evaYear  +
			    " )" +
			    " where tax>0" +
			    " order by emp_code ";
		
			
	
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
	
			String empCode = (String) map.getValue(0);
			String oldSalary = (String) map.getValue(1);
			String salary = (String) map.getValue(2);
			String marriedStatus =(String) map.getValue(3);
			String childStudy  = (String) map.getValue(4);
			String childNoStudy  = (String) map.getValue(5);
			String debtLife  = (String) map.getValue(6);
			String debtLoan  = (String) map.getValue(7);
			String donate  = (String) map.getValue(8);
			String flagMotherSpouse  = (String) map.getValue(9);
			String aaaa  = (String) map.getValue(10);
			String flagFatherSpouse = (String) map.getValue(11);
			String flagMother = (String) map.getValue(12);
			String flagFather = (String) map.getValue(13);
			String ltf = (String) map.getValue(14);
			String rmf = (String) map.getValue(15);
			String healthFather = (String) map.getValue(16);
			String handicappedDec = (String) map.getValue(17);
			String pvfCollect = (String) map.getValue(18);
			String incomeCollect = (String) map.getValue(19);
			String incomeFix = (String) map.getValue(20);
			String incomeVariable = (String) map.getValue(21);
			String taxCollect = (String) map.getValue(22);
			String pvfRate = (String) map.getValue(23);
			String tax = (String) map.getValue(24);
		
			
	

			VPrEmployeeMonthTaxVO vo = new VPrEmployeeMonthTaxVO();
		
			
			vo.setEmpCode(empCode);
			vo.setOldSalary(oldSalary);
			vo.setSalary(salary);
			vo.setMarriedStatus(marriedStatus);
			vo.setChildStudy(childStudy);
			vo.setChildNoStudy(childNoStudy);
			vo.setDebtLife(debtLife);
			vo.setDebtLoan(debtLoan);
			vo.setDonate(donate);
			vo.setFlagMotherSpouse(flagMotherSpouse);
			vo.setAaaa(aaaa);
			vo.setFlagFatherSpouse(flagFatherSpouse);
			vo.setFlagMother(flagMother);
			vo.setFlagFather(flagFather);
			vo.setLtf(ltf);
			vo.setRmf(rmf);
			vo.setHealthFather(healthFather);
			vo.setHandicappedDec(handicappedDec);
			vo.setPvfCollect(pvfCollect);
			vo.setIncomeCollect(incomeCollect);
			vo.setIncomeFix(incomeFix);
			vo.setIncomeVariable(incomeVariable);
			vo.setTaxCollect(taxCollect);
			vo.setPvfRate(pvfRate);
			vo.setTax(tax);
	
			
			
			returnList.add(vo);

		}
		return returnList;
	}
	public List findPrDailyRandom(String evaOuCode, Integer evaYear,
			Integer evaPeriod)
			throws Exception {

		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " select prd.emp_code,TO_CHAR(prd.inc_dec_code) inc_dec_code,to_char(prd.tot_day) tot_day,to_char(prd.tot_hour) tot_hour,to_char(pr.old_salary) salary_2554,to_char(pr.new_salary) salary_2555," +
				" to_char(prd.year_work) year_work,to_char(prd.period_work) period_work,to_char(prd.fiscal_year) fiscal_year,to_char(prd.tot_amt) net_amt11_14,to_char(prd.sub_code) sub_code,to_char(prd.flag_pr) flag_pr " +
				" from pr_daily prd,pr_employee pr " +
				" where pr.emp_code in " +
				" (select distinct emp_code " +
				"   from ( SELECT distinct PR.EMP_CODE" +
				"        FROM PR_DAILY PR,PR_EMPLOYEE PE " +
				"        WHERE PR.YEAR_PR = " + evaYear +
				"        AND PR.PERIOD_PR = " + evaPeriod +
				"        AND PR.INC_DEC_CODE ='11' " +
				"        AND PR.OU_CODE = PE.OU_CODE " +
				"        AND PR.YEAR_PR = PE.YEAR " +
				"        AND PR.PERIOD_PR = PE.PERIOD " +
				"        AND PR.EMP_CODE = PE.EMP_CODE " +
				"        and  rownum <=150 " +
				"        union all " +
				"        SELECT distinct PR.EMP_CODE " +
				"        FROM PR_DAILY PR,PR_EMPLOYEE PE " +
				"        WHERE PR.YEAR_PR = " + evaYear +
				"        AND PR.PERIOD_PR = " + evaPeriod +
				"        AND PR.INC_DEC_CODE ='12' " +
				"        AND PR.OU_CODE = PE.OU_CODE " +
				"        AND PR.YEAR_PR = PE.YEAR " +
				"        AND PR.PERIOD_PR = PE.PERIOD " +
				"        AND PR.EMP_CODE = PE.EMP_CODE " +
				"        and  rownum <=150 " +
				"        union all " +
				"        SELECT distinct PR.EMP_CODE " +
				"        FROM PR_DAILY PR,PR_EMPLOYEE PE " +
				"        WHERE PR.YEAR_PR = " + evaYear +
				"        AND PR.PERIOD_PR = " + evaPeriod +
				"        AND PR.INC_DEC_CODE ='13' " +
				"        AND PR.OU_CODE = PE.OU_CODE " +
				"        AND PR.YEAR_PR = PE.YEAR " +
				"        AND PR.PERIOD_PR = PE.PERIOD " +
				"        AND PR.EMP_CODE = PE.EMP_CODE " +
				"        and  rownum <=150 " +
				"        union all " +
				"        SELECT distinct PR.EMP_CODE " +
				"        FROM PR_DAILY PR,PR_EMPLOYEE PE " +
				"        WHERE PR.YEAR_PR = " + evaYear +
				"        AND PR.PERIOD_PR = " + evaPeriod +
				"        AND PR.INC_DEC_CODE ='14' " +
				"        AND PR.OU_CODE = PE.OU_CODE " +
				"        AND PR.YEAR_PR = PE.YEAR " +
				"        AND PR.PERIOD_PR = PE.PERIOD " +
				"        AND PR.EMP_CODE = PE.EMP_CODE " +
				"        and  rownum <=150 " +
				"        order by dbms_random.value)) " +
				"    and  prd.inc_dec_code in ('11','12','13','14') " +
				"    and prd.year_pr = " + evaYear +
				"    and prd.period_pr = " + evaPeriod +
				"    and prd.ou_code ='001' " +
				"    and prd.ou_code = pr.ou_code " +
				"    and prd.emp_code = pr.emp_code " +
				"    and prd.year_pr = pr.year " +
				"    and prd.period_pr = pr.period " +
				"    order by prd.emp_code,prd.inc_dec_code ";
		
			
	
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
	
			String empCode = (String) map.getValue(0);
			String incDecCode = (String) map.getValue(1);
			String totDay = (String) map.getValue(2);
			String totHour = (String) map.getValue(3);
			String salary2554 = (String) map.getValue(4);
			String salary2555 = (String) map.getValue(5);
			String yearWork = (String) map.getValue(6);
			String periodWork = (String) map.getValue(7);
			String fiscalYear = (String) map.getValue(8);
			String netAmt = (String) map.getValue(9);
			String subCode = (String) map.getValue(10);
			String flagPr = (String) map.getValue(11);
		
			
	

			PrDailyRandomVO vo = new PrDailyRandomVO();
		
			
			vo.setEmpCode(empCode);
			vo.setIncDecCode(incDecCode);
			vo.setTotDay(totDay);
			vo.setTotHour(totHour);
			vo.setSalary2554(salary2554);
			vo.setSalary2555(salary2555);
			vo.setYearWork(yearWork);
			vo.setPeriodWork(periodWork);
			vo.setFiscalYear(fiscalYear);
			vo.setFiscalYear(fiscalYear);
			vo.setNetAmt(netAmt);
			vo.setSubCode(subCode);
			vo.setFlagPr(flagPr);
			
		
	
			
			
			returnList.add(vo);

		}
		return returnList;
	}
}
