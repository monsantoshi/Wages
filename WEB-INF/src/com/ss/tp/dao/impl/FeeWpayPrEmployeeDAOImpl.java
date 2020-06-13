package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.FeeWpayPrEmployeeDAO;
import com.ss.tp.dto.ApEmpBankVO;
import com.ss.tp.dto.CTRWRP201VO;
import com.ss.tp.dto.CTWPAYRP201VO;
import com.ss.tp.dto.FeeContractVO;
import com.ss.tp.dto.FeeRwPremiumReportByOrgVO;
import com.ss.tp.dto.FeeWgEmployeeVO;
import com.ss.tp.dto.FeeWgPnEmployeeVO;
import com.ss.tp.dto.FeeWpayPrDailyNetAmtRepVO;
import com.ss.tp.dto.PrDailyNetAmtRepVO;
import com.ss.tp.dto.FeeWpayRwHealthVO;
import com.ss.tp.dto.FeeWpayRwOvertimeVO;
import com.ss.tp.dto.FeeWpayRwPremiumVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.PnEmpMoveVO;
import com.ss.tp.dto.PrDailyRandomVO;
import com.ss.tp.dto.FeeWpayPrEmployeeTextVO;
import com.ss.tp.dto.FeeWpayPrEmployeeVO;
import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.dto.VFeeWpay401VO;
import com.ss.tp.dto.VFeeWpayGlTransferVO;
import com.ss.tp.dto.VFeeWpayPrBreakPayVO;
import com.ss.tp.dto.VFeeWpayRwVinaiVO;
import com.ss.tp.dto.VFeeWpayTaxYearRepVO;
//import com.ss.tp.dto.RwDangerVO;
//import com.ss.tp.dto.RwHealthVO;
//import com.ss.tp.dto.RwOvertimeVO;
//import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.model.FeeWgEmployee;
import com.ss.tp.model.FeeWgEmployeePK;
import com.ss.tp.model.FeeWpayPrEmployee;
import com.ss.tp.model.FeeWpayPrEmployeeText;
import com.ss.tp.model.TaMonthEmpWork;
import com.ss.tp.model.TaMonthEmpWorkPK;
import com.ss.tp.dto.VPrEmployeeMonthTaxVO;

public class FeeWpayPrEmployeeDAOImpl extends HibernateDaoSupport implements
		FeeWpayPrEmployeeDAO, Serializable {

	private Log logger = LogFactory.getLog(this.getClass());
	private JdbcTemplate jdbcTemplate;
	

	public String[] findMaxYearPeriod(String ouCode,String userId) {

		String year = "";
		String section = "";
		
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT a.key.year || MAX(a.key.period) ");
		sql.append(" FROM   FeeWpayPrEmployee a ");
		sql.append(" WHERE  a.key.ouCode = '" + ouCode + "' ");
		//.append(" and  a.creBy = '" + userId + "' ");
		sql.append(" and a.key.year = ( select max(b.key.year) from FeeWpayPrEmployee b  WHERE  b.key.ouCode = '" + ouCode + "'  ) ");
		sql.append(" group by a.key.year ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			section = rs.substring(4, rs.length());
		}

		
		
		
		String[] val = new String[3];
		val[0] = year;
		val[1] = section;
		

		return val;
	}
	
	public String[] findMinYearPeriodLine(String ouCode,String userId) {

		String year = "";
		String period = "";
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT MIN(a.pk.year)||MIN(a.pk.period) ");
		sql.append(" FROM   FeeWpayPrPeriodLine a ");
		sql.append(" WHERE  a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.pk.creBy =  '" + userId + "' ");
		sql.append(" and a.mainClose = 'N' ");
		sql.append(" and a.tranClose = 'N' ");
		sql.append(" and a.prClose = 'N' ");
		
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			period = rs.substring(4, rs.length());
		}
		String[] val = new String[2];
		val[0] = year;
		val[1] = period;
		System.out.println("VAL0" + val[0]);
		System.out.println("VAL1" + val[1]);
		return val;
	}
	
	public String[] findMinPeriodLine(String ouCode,String evayear,String userId) {

		String year = "";
		
		String period = "";
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT a.pk.year||MIN(a.pk.period) ");
		sql.append(" FROM   FeeWpayPrPeriodLine a ");
		sql.append(" WHERE  a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.pk.creBy =  '" + userId + "' ");
		sql.append(" and a.pk.year = " + evayear);
		sql.append(" and a.mainClose = 'N' ");
		sql.append(" and a.tranClose = 'N' ");
		sql.append(" and a.prClose = 'N' ");
		sql.append(" group by a.pk.year ");
		
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			period = rs.substring(4, rs.length());
		}
		String[] ral = new String[2];
		ral[0] = year;
		ral[1] = period;
		System.out.println("RAL0" + ral[0]);
		System.out.println("RAL1" + ral[1]);
		return ral;
	}
	
	public String[] findMaxYearPeriodTransClose(String ouCode,String userId) {

		String year = "";
		String section = "";
		
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT a.key.year || MAX(a.key.period) ");
		sql.append(" FROM   FeeWpayPrEmployee a ");
		sql.append(" WHERE  a.key.ouCode = '" + ouCode + "' ");
		//sql.append(" and  a.creBy = '" + userId + "' ");
		//sql.append(" and  a.tranClose = 'Y' ");
		sql.append(" and a.key.year = ( select max(b.key.year) from FeeWpayPrEmployee b  WHERE  b.key.ouCode = '" + ouCode + "'  ) ");
		sql.append(" group by a.key.year ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			section = rs.substring(4, rs.length());
		}

		
		
		
		String[] val = new String[3];
		val[0] = year;
		val[1] = section;
		

		return val;
	}
	

	public FeeWpayPrEmployeeVO findByCriteria(String ouCode, String year,
			String period, String empCode, String userId) {

		 System.out.println("findByCriteria");

		FeeWpayPrEmployeeVO vo = new FeeWpayPrEmployeeVO();
		try {
			StringBuffer hql = new StringBuffer(0);
			hql.append(" from FeeWpayPrEmployee pr ");
			hql.append(" where ");
			hql.append(" pr.key.ouCode = '" + ouCode + "' ");
			hql.append(" and pr.key.year = " + year);
			hql.append(" and pr.key.period = " + period);
			hql.append(" and pr.key.empCode = '" + empCode + "' ");
		

			List ls = this.getHibernateTemplate().find(hql.toString());
			if (ls != null && ls.size() > 0) {
				FeeWpayPrEmployee emp = (FeeWpayPrEmployee) ls.get(0);

				vo.setOuCode(emp.getKey(). getOuCode());
				vo.setYear(emp.getKey().getYear());
				vo.setPeriod(emp.getKey().getPeriod());
				vo.setEmpCode(emp.getKey().getEmpCode());
				vo.setCodeSeqWork(emp.getCodeSeqWork());
				vo.setTaxId(emp.getTaxId());
				//vo.setMarriedStatus(emp.getMarriedStatus());
				vo.setPayStatus(emp.getPayStatus());
				vo.setBankId(emp.getBankId());
				vo.setCostChild(emp.getCostChild());
			
				vo.setFlagPr(emp.getFlagPr());
				
				vo.setSeqRec(emp.getSeqRec());
			
			
				vo.setConfirmFlag(emp.getConfirmFlag());
			
				vo.setBankBranch(emp.getBankBranch());
				vo.setBankCode(emp.getBankCode());
		
				System.out.println(emp.getCostChild());

			}

			hql.setLength(0);
			hql.append(" from FeeWpayPrEmployeeText ");
			hql.append(" where ouCode = '" + ouCode + "' ");
			hql.append(" and year = " + year);
			hql.append(" and period = " + period);
			hql.append(" and empCode = '" + empCode + "' ");
			hql.append(" and creBy = '" + userId + "' ");
			hql.append(" order by keySeq ");

			ls = this.getHibernateTemplate().find(hql.toString());
			System.out.println("change value test : " + ls.size());
			for (int i = 0; i < ls.size(); i++) {
				FeeWpayPrEmployeeText prText = (FeeWpayPrEmployeeText) ls.get(i);

				if (prText.getTaxId() != null
						&& !prText.getTaxId().trim().equals(""))
					vo.setTaxId(prText.getTaxId());

				//if (prText.getMarriedStatus() != null
				//		&& !prText.getMarriedStatus().trim().equals(""))
				//	vo.setMarriedStatus(prText.getMarriedStatus());

				if (prText.getPayStatus() != null
						&& !prText.getPayStatus().trim().equals(""))
					vo.setPayStatus(prText.getPayStatus());

				if (prText.getBankId() != null
						&& !prText.getBankId().trim().equals(""))
					vo.setBankId(prText.getBankId());

				System.out.println("cost child : " + prText.getCostChild());

				if (prText.getCostChild() != null)
					vo.setCostChild(prText.getCostChild());

			
				//if (prText.getGundanFlag() != null
					//	&& !prText.getGundanFlag().trim().equals(""))
					//vo.setGundanFlag(prText.getGundanFlag());

				
			
			
				if (prText.getFlagPr() != null
						&& !prText.getFlagPr().trim().equals(""))
					vo.setFlagPr(prText.getFlagPr());

			
				if (prText.getSeqData() != null)
					vo.setSeqRec(prText.getSeqData());

				


		
				if (prText.getBankBranch() != null)
					vo.setBankBranch(prText.getBankBranch());
				
				if (prText.getBankCode() != null && !prText.getBankCode().trim().equals(""))
					vo.setBankCode(prText.getBankCode());


			
			
				if (prText.getConfirmFlag() != null
						&& !prText.getConfirmFlag().trim().equals(""))
					vo.setConfirmFlag(prText.getConfirmFlag());
				
				
			}
		} catch (Exception e) {
			System.out.println("Not found FeeWpayPrEmployee");
			e.printStackTrace();
		}

		

		return vo;
	}

	public List findByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,String bankIdFrom, String bankIdTo,
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
		
		if (bankIdFrom != null && !bankIdFrom.equals(""))
			criteria.append("and pre.bankId >= '" + bankIdFrom + "' ");

		if (bankIdTo != null && !bankIdTo.equals(""))
			criteria.append("and pre.bankId <= '" + bankIdTo + "' ");


		StringBuffer hql = new StringBuffer(0);
		hql.append(" select pne.wgEmployeePK.empCode, ");
		hql.append(" sf.prefixName, ");
		hql.append(" pne.firstName, ");
		hql.append(" pne.lastName, ");
		//hql.append(" pnp.positionShort, ");
		hql.append(" pno.orgCode, ");
		hql.append(" pno.divShort || ' ' || pno.areaDesc || ' ' || pno.secDesc || ' ' || pno.workDesc, ");
		hql.append(" pno.pk.codeSeq ");
		hql.append(" from FeeWpayPrEmployee pre, ");
		hql.append(" WgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.key.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.key.year = " + year);
		hql.append(" and pre.key.period = " + period);
		// hql.append(" and pre.payStatus = '1' ");
		hql.append(" and pne.empStatus = 'B' ");
		hql.append(" and pre.key.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append("    and pk.year = " + year);
		hql.append("	and pk.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.key.empCode = pne.wgEmployeePK.empCode ");
		hql.append(" and pre.key.ouCode = pne.wgEmployeePK.ouCode ");
		//hql.append(" and pne.pk.ouCode = pnp.pk.ouCode ");
		//hql.append(" and pne.gworkCode = pnp.pk.gworkCode ");
		//hql.append(" and pne.positionCode = pnp.pk.positionCode ");
		// hql.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
		hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pno.pk.codeSeq, pne.wgEmployeePK.empCode ");

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
		//	String positionShort = (String) r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];

			PayRollEmployeeVO vo = new PayRollEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			//vo.setPositionShort(positionShort);
			vo.setOrgCode(orgCode);
			vo.setOrgDesc(orgDesc);

			empList.set(i, vo);
		}
		return empList;
	}

	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,String bankIdFrom, String bankIdTo,
			String empCodeFrom, String empCodeTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");
		
		
		if (bankIdFrom != null && !bankIdFrom.equals(""))
			criteria.append("and pre.bankId >= '" + bankIdFrom + "' ");

		if (bankIdTo != null && !bankIdTo.equals(""))
			criteria.append("and pre.bankId <= '" + bankIdTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.key.empCode >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.key.empCode <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select count(pne.wgEmployeePK.empCode) ");
		hql.append(" from FeeWpayPrEmployee pre, ");
		hql.append(" WgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.key.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.key.year = " + year);
		hql.append(" and pre.key.period = " + period);
		// hql.append(" and pre.payStatus = '1' ");
		hql.append(" and pne.empStatus = 'B' ");
		hql.append(" and pre.key.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append("    and pk.year = " + year);
		hql.append("	and pk.period = " + period);
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.key.empCode = pne.wgEmployeePK.empCode ");
		hql.append(" and pre.key.ouCode = pne.wgEmployeePK.ouCode ");
		hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pne.wgEmployeePK.empCode ");

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
		hql.append(" select distinct pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append(" pnEm.LAST_NAME , prEm.CODE_SEQ_WORK,org.div_short||nvl(org.area_desc,org.sec_desc) orgDesc,prEm.flag_status ");
		hql.append(" from FEE_WPAY_PR_EMPLOYEE_TEXT prEm, WG_EMPLOYEE pnEm, PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
		hql.append(" where prEm.OU_CODE = '" + ouCode + "' ");
		hql.append(" and prEm.YEAR = " + year);
		hql.append(" and prEm.PERIOD = " + period);
		hql.append(" and v.USER_ID = '" + userId + "' ");
		hql.append(" and pnEm.EMP_STATUS = 'B' ");
		hql.append(criteria.toString());
		hql.append(" and prEm.OU_CODE = v.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK = v.CODE_SEQ ");
		hql.append(" and prEm.EMP_CODE=pnEm.EMP_CODE ");
		hql.append(" and prEm.OU_CODE=pnEm.OU_CODE ");
		hql.append(" and prEm.CODE_SEQ_WORK=org.CODE_SEQ ");
		hql.append(" and prEm.OU_CODE=org.OU_CODE ");
		hql.append(" and pnEm.PRE_NAME=sf.PRE_SUFF_CODE ");
		hql.append(" and prEm.CRE_BY='" + userId + "' ");
		hql.append(" ORDER BY  pnEm.emp_code, prEm.code_seq_work,decode(prEm.flag_status,'D',1,'I',2,'A',3) ");
		System.out.println(" start : " + new Date());
		System.out.println("jdbc : " + hql.toString());

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		System.out.println(" end : " + new Date());
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
				String orgDesc = (String) map.getValue(5);
				String flagStatus = (String) map.getValue(6);
				PayRollEmployeeVO vo = new PayRollEmployeeVO();
				vo.setEmpCode(empCode);
				vo.setName(prefixName + " " + firstName + " " + lastName);
				vo.setOrgDesc(orgDesc);
				vo.setFlagStatus(flagStatus);
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
	
		hql.append(" select count(*) from ( ");
		hql.append(" select distinct pnEm.EMP_CODE , sf.PREFIX_NAME, pnEm.FIRST_NAME , ");
		hql.append(" pnEm.LAST_NAME , prEm.CODE_SEQ_WORK,prEm.flag_status ");
		hql.append(" from FEE_WPAY_PR_EMPLOYEE_TEXT prEm, WG_EMPLOYEE pnEm, PN_ORGANIZATION org, DB_PRE_SUFF sf, V_PN_ORGANIZATION_SECURITY v ");
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
					+ " from FeeWpayPrPeriodLine pr " + " where pr.pk.ouCode ='"
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
	
		// //////////////////NEW//////////////////////////////////////
		StringBuffer hql = new StringBuffer(0);

		hql.append(" select div_code,div_desc, area_code,area_desc, sec_code, sec_desc,cnt_emp,emp_A,emp_I,emp_D , inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A, amt_A , cnt_R,  amt_R,cnt_B,amt_B, cnt_S, amt_S ");
		hql.append("  ,  cnt_gundan, cnt_costchild, sum_costchild ");
		hql.append(" from ");
		hql.append(" (	  /*				   for group record of count emp and A D I record				   */ ");
		hql.append(" select x.div_code,x.div_desc, x.area_code,x.area_desc,x.sec_code,x.sec_desc, sum(cnt_emp) cnt_emp, sum(emp_A) emp_A, sum(emp_I) emp_I, sum(emp_D) emp_D , '00' inc_dec_code,'00' inc_dec_name, 0 cnt_rec, 0 amt_rec, 0 cnt_N, 0 amt_N, 0 cnt_A, 0 amt_A , 0 cnt_R, 0 amt_R, 0 cnt_B, 0 amt_B, 0 cnt_S, 0 amt_S ");
		hql.append(" ,  sum(x.cnt_gundan) cnt_gundan, sum(x.cnt_costchild) cnt_costchild, sum(x.sum_costchild) sum_costchild ");
		hql.append("   from (			 /*    for count employee record		   */					 				");
		hql.append("					select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, count(a.emp_code) cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D ");
		hql.append(" ,  sum(decode(nvl(a.gundan_flag,'N'),'N',0,1)) cnt_gundan, sum(decode(nvl(a.cost_child,0),0,0,1)) cnt_costchild, sum(nvl(a.cost_child,0)) sum_costchild ");
		hql.append(" from v_fee_wpay_pr_employee_temp a, su_user_organization b, pn_organization c ");
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
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_costchild ");
		hql.append(" from fee_wpay_pr_employee_text a, su_user_organization b, pn_organization c   ");
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
		hql.append(" ,  0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from (           ");
	    hql.append(" /* for count 11 record										 */ ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '11' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N, sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A         , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B           , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild       ");
		hql.append(" from fee_wpay_rw_overtime a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.ou_code = b.ou_code		and a.code_seq = b.code_seq         and a.ou_code = c.ou_code and a.code_seq = c.code_seq   ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '11',c.sec_code,c.sec_desc ");
		hql.append(" union ");
		hql.append(" /* for count 12 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '12' inc_dec_code, count(*)cnt_rec, 0 amt_rec              , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0amt_A           , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         ,sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S      ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_premium a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'          ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.ou_code = b.ou_code     ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '12',c.sec_code,c.sec_desc     ");
		hql.append(" union           ");
		hql.append(" /* for count 13 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '13' inc_dec_code, count(*) cnt_rec, 0 amt_rec ");
		hql.append(" , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N                       , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A ");
		hql.append(" , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R                         , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B ");
		hql.append(" , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_health a, su_user_organization b,pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'            ");
		hql.append(" and a.ou_code = b.ou_code         ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code ");
		hql.append(" and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '13',c.sec_code,c.sec_desc       ");
		hql.append(" union ");
		hql.append(" /* for count income other record										 */  ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$') area_code,c.area_desc, a.inc_dec_code, count(*) cnt_rec, sum(total_amt) amt_rec             , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, sum(decode(a.flag_pr,'N',a.total_amt,0)) amt_N  , sum(decode(a.flag_pr,'A',1,0)) cnt_A, sum(decode(a.flag_pr,'A',a.total_amt,0)) amt_A                    , sum(decode(a.flag_pr,'R',1,0)) cnt_R, sum(decode(a.flag_pr,'R',a.total_amt,0)) amt_R     ,");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, sum(decode(a.flag_pr,'B',a.total_amt,0)) amt_B , sum(decode(a.flag_pr,'S',1,0)) cnt_S, sum(decode(a.flag_pr,'S',a.total_amt,0)) amt_S  ");
		hql.append(" ,  0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_inc_dec_other a, su_user_organization b, pn_organization c ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'             ");
		hql.append(" and a.ou_code = b.ou_code               ");
		hql.append(" and a.code_seq = b.code_seq and a.ou_code = c.ou_code and a.code_seq = c.code_seq        ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, a.inc_dec_code ,c.sec_code,c.sec_desc   ");
		hql.append(" union           ");
		hql.append(" /* for count 21 record										 */");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '21' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A   , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_vinai a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'           ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'     ");
		hql.append(" and a.ou_code = b.ou_code ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '21',c.sec_code,c.sec_desc     ");
		hql.append(" /* for count 22 record										 */ ");
		hql.append(" union       ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '22' inc_dec_code, count(*) cnt_rec, 0 amt_rec          ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N              , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A      , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R             , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B          , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_vinai3 a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'           ");
		hql.append(" and a.ou_code = b.ou_code       ");
		hql.append(" and a.code_seq = b.code_seq               and a.ou_code = c.ou_code               and a.code_seq = c.code_seq     ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '22',c.sec_code,c.sec_desc ");
		hql.append(" /* for count 25 record										 */ ");
		hql.append(" union       ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '24' inc_dec_code, count(*) cnt_rec, 0 amt_rec          ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N              , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A      , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R             , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B          , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_vinai2 a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'           ");
		hql.append(" and a.ou_code = b.ou_code       ");
		hql.append(" and a.code_seq = b.code_seq               and a.ou_code = c.ou_code               and a.code_seq = c.code_seq     ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '24',c.sec_code,c.sec_desc ");
		hql.append(" ) z, fee_wpay_pr_income_deduct y          ");
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

				//Long cLife = new Long(map.getValue(24).toString());
				Long cGundan = new Long(map.getValue(24).toString());
				Long cChild = new Long(map.getValue(25).toString());
				Long sumcChild = new Long(map.getValue(26).toString());

				CTWPAYRP201VO vo = new CTWPAYRP201VO();
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

				//vo.setCostLife(cLife);
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

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period,String approveFlag) {
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
	
		// //////////////////NEW//////////////////////////////////////
		StringBuffer hql = new StringBuffer(0);

		hql.append(" select div_code,div_desc, area_code,area_desc, sec_code, sec_desc,cnt_emp,emp_A,emp_I,emp_D , inc_dec_code,inc_dec_name,cnt_rec,amt_rec,cnt_N, amt_N, cnt_A, amt_A , cnt_R,  amt_R,cnt_B,amt_B, cnt_S, amt_S ");
		hql.append("  ,  cnt_gundan, cnt_costchild, sum_costchild ");
		hql.append(" from ");
		hql.append(" (	  /*				   for group record of count emp and A D I record				   */ ");
		hql.append(" select x.div_code,x.div_desc, x.area_code,x.area_desc,x.sec_code,x.sec_desc, sum(cnt_emp) cnt_emp, sum(emp_A) emp_A, sum(emp_I) emp_I, sum(emp_D) emp_D , '00' inc_dec_code,'00' inc_dec_name, 0 cnt_rec, 0 amt_rec, 0 cnt_N, 0 amt_N, 0 cnt_A, 0 amt_A , 0 cnt_R, 0 amt_R, 0 cnt_B, 0 amt_B, 0 cnt_S, 0 amt_S ");
		hql.append(" ,  sum(x.cnt_gundan) cnt_gundan, sum(x.cnt_costchild) cnt_costchild, sum(x.sum_costchild) sum_costchild ");
		hql.append("   from (			 /*    for count employee record		   */					 				");
		hql.append("					select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, count(a.emp_code) cnt_emp, 0 emp_A, 0 emp_I, 0 emp_D ");
		hql.append(" ,  sum(decode(nvl(a.gundan_flag,'N'),'N',0,1)) cnt_gundan, sum(decode(nvl(a.cost_child,0),0,0,1)) cnt_costchild, sum(nvl(a.cost_child,0)) sum_costchild ");
		hql.append(" from v_fee_wpay_pr_employee_temp a, su_user_organization b, pn_organization c ");
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
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_costchild ");
		hql.append(" from fee_wpay_pr_employee_text a, su_user_organization b, pn_organization c   ");
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
		hql.append(" ,  0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from (           ");
	    hql.append(" /* for count 11 record										 */ ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '11' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N, sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A         , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B           , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild       ");
		hql.append(" from fee_wpay_rw_overtime a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and a.ou_code = b.ou_code		and a.code_seq = b.code_seq         and a.ou_code = c.ou_code and a.code_seq = c.code_seq   ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '11',c.sec_code,c.sec_desc ");
		hql.append(" union ");
		hql.append(" /* for count 12 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '12' inc_dec_code, count(*)cnt_rec, 0 amt_rec              , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0amt_A           , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         ,sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S      ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_premium a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'          ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and b.user_id = '" + userId + "'         ");
		hql.append(" and a.ou_code = b.ou_code     ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '12',c.sec_code,c.sec_desc     ");
		hql.append(" union           ");
		hql.append(" /* for count 13 record										 */		");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '13' inc_dec_code, count(*) cnt_rec, 0 amt_rec ");
		hql.append(" , sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N                       , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A ");
		hql.append(" , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R                         , sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B ");
		hql.append(" , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_health a, su_user_organization b,pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'      ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and b.user_id = '" + userId + "'            ");
		hql.append(" and a.ou_code = b.ou_code         ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code ");
		hql.append(" and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '13',c.sec_code,c.sec_desc       ");
		hql.append(" union ");
		hql.append(" /* for count income other record										 */  ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc,nvl(c.area_code,'$') area_code,c.area_desc, a.inc_dec_code, count(*) cnt_rec, sum(total_amt) amt_rec             , ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, sum(decode(a.flag_pr,'N',a.total_amt,0)) amt_N  , sum(decode(a.flag_pr,'A',1,0)) cnt_A, sum(decode(a.flag_pr,'A',a.total_amt,0)) amt_A                    , sum(decode(a.flag_pr,'R',1,0)) cnt_R, sum(decode(a.flag_pr,'R',a.total_amt,0)) amt_R     ,");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, sum(decode(a.flag_pr,'B',a.total_amt,0)) amt_B , sum(decode(a.flag_pr,'S',1,0)) cnt_S, sum(decode(a.flag_pr,'S',a.total_amt,0)) amt_S  ");
		hql.append(" ,  0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_inc_dec_other a, su_user_organization b, pn_organization c ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and b.user_id = '" + userId + "'             ");
		hql.append(" and a.ou_code = b.ou_code               ");
		hql.append(" and a.code_seq = b.code_seq and a.ou_code = c.ou_code and a.code_seq = c.code_seq        ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, a.inc_dec_code ,c.sec_code,c.sec_desc   ");
		hql.append(" union           ");
		hql.append(" /* for count 21 record										 */");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '21' inc_dec_code, count(*) cnt_rec, 0 amt_rec              ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N         , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A   , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R         ,  ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B         , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S    ");
		hql.append(" , 0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_vinai a, su_user_organization b, pn_organization c    ");
		hql.append(" where a.ou_code = '" + ouCode + "'           ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and b.user_id = '" + userId + "'     ");
		hql.append(" and a.ou_code = b.ou_code ");
		hql.append(" and a.code_seq = b.code_seq             and a.ou_code = c.ou_code               and a.code_seq = c.code_seq      ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '21',c.sec_code,c.sec_desc     ");
		hql.append(" /* for count 22 record										 */ ");
		hql.append(" union       ");
		hql.append(" select c.div_code,c.div_desc,c.sec_code,c.sec_desc, nvl(c.area_code,'$') area_code,c.area_desc, '22' inc_dec_code, count(*) cnt_rec, 0 amt_rec          ,  ");
		hql.append(" sum(decode(a.flag_pr,'N',1,0)) cnt_N, 0 amt_N              , sum(decode(a.flag_pr,'A',1,0)) cnt_A, 0 amt_A      , sum(decode(a.flag_pr,'R',1,0)) cnt_R, 0 amt_R             , ");
		hql.append(" sum(decode(a.flag_pr,'B',1,0)) cnt_B, 0 amt_B          , sum(decode(a.flag_pr,'S',1,0)) cnt_S, 0 amt_S ");
		hql.append(" ,0 cnt_gundan, 0 cnt_costchild, 0 sum_coschild ");
		hql.append(" from fee_wpay_rw_vinai2 a, su_user_organization b, pn_organization c     ");
		hql.append(" where a.ou_code = '" + ouCode + "'         ");
		hql.append(" and a.year_pr = " + year);
		hql.append(" and a.period_pr = " + period);
		hql.append(" and a.approve_flag = '" + approveFlag + "'         ");
		hql.append(" and b.user_id = '" + userId + "'           ");
		hql.append(" and a.ou_code = b.ou_code       ");
		hql.append(" and a.code_seq = b.code_seq               and a.ou_code = c.ou_code               and a.code_seq = c.code_seq     ");
		hql.append(" group by c.div_code,c.div_desc, c.area_code,c.area_desc, '22',c.sec_code,c.sec_desc ");
		hql.append(" ) z, fee_wpay_pr_income_deduct y          ");
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

				//Long cLife = new Long(map.getValue(24).toString());
				Long cGundan = new Long(map.getValue(24).toString());
				Long cChild = new Long(map.getValue(25).toString());
				Long sumcChild = new Long(map.getValue(26).toString());

				CTWPAYRP201VO vo = new CTWPAYRP201VO();
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

				//vo.setCostLife(cLife);
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
	
	
	public FeeWpayPrEmployee loadPrEmployee(FeeWpayPrEmployeeTextVO feeWpayPrEmployeeTextVO) {
		FeeWpayPrEmployee rwp = new FeeWpayPrEmployee();
		try {
			System.out.println(">>>>>>>>>>>>>>> " + feeWpayPrEmployeeTextVO.getOuCode()
					+ " :: " + feeWpayPrEmployeeTextVO.getYear() + " :: "
					+ feeWpayPrEmployeeTextVO.getEmpCode() + " :: " + feeWpayPrEmployeeTextVO.getPeriod());
			// rwp = (RwOvertime)
			// this.getHibernateTemplate().load(RwOvertime.class,rpVo.getKeySeq());
			Criteria c = this.getSessionFactory().openSession()
					.createCriteria(FeeWpayPrEmployee.class);
			c.add(Restrictions.eq("key.ouCode", feeWpayPrEmployeeTextVO.getOuCode()));
			c.add(Restrictions.eq("key.year", Double.valueOf(feeWpayPrEmployeeTextVO.getYear())));
			c.add(Restrictions.eq("key.empCode", feeWpayPrEmployeeTextVO.getEmpCode()));
			c.add(Restrictions.eq("key.period",Double.valueOf(feeWpayPrEmployeeTextVO.getPeriod())));

			rwp = (FeeWpayPrEmployee) c.list().get(0);
			// rwp = this.getHibernateTemplate().findByCriteria(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updatePrEmpByPrEmpText(FeeWpayPrEmployeeTextVO prEmpTextVo)
			throws Exception {
		FeeWpayPrEmployee prEmp = new FeeWpayPrEmployee();

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
		hql.append("	FROM fee_wpay_rw_overtime a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwOvertimeVO vo = new FeeWpayRwOvertimeVO();
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
		hql.append("	FROM fee_wpay_rw_premium a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwPremiumVO vo = new FeeWpayRwPremiumVO();
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
		hql.append("	FROM fee_wpay_rw_health a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwHealthVO vo = new FeeWpayRwHealthVO();
			vo.setFlagPr(flagPr);
			vo.setTotalHour(totalHour);

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
		hql.append("	FROM fee_wpay_rw_overtime a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwOvertimeVO vo = new FeeWpayRwOvertimeVO();
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
		hql.append("	FROM fee_wpay_rw_premium a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwPremiumVO vo = new FeeWpayRwPremiumVO();
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
		hql.append("	FROM fee_wpay_rw_health a, su_user_organization b, pn_organization c ");
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

			FeeWpayRwHealthVO vo = new FeeWpayRwHealthVO();
			vo.setFlagPr(flagPr);
			vo.setTotalHour(totalHour);

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
			    " FROM  FEE_WPAY_PR_EMPLOYEE" +
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

	public List findByCriteriaFeeEmpMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.pk.empCode >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.pk.empCode <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select pne.pk.empCode, ");
		hql.append(" sf.prefixName, ");
		hql.append(" pne.firstName, ");
		hql.append(" pne.lastName, ");
		//hql.append(" pnp.positionShort, ");
		hql.append(" pno.orgCode, ");
		hql.append(" pno.divShort || ' ' || pno.areaDesc || ' ' || pno.secDesc || ' ' || pno.workDesc, ");
		hql.append(" pne.empStatus ");
		hql.append(" from FeeWgEmployee pre, ");
		hql.append(" FeeWgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.pk.empCode in ( ");
		hql.append(" 	select v.pk.empCode ");
		hql.append(" 	from VFeeWgPnEmployeeSecurity v ");
		hql.append(" 	where v.pk.userId = '" + userId + "' ");
		hql.append("    and v.pk.ouCode = '" + ouCode + "' ");
	    hql.append("	and v.pk.empCode = pre.pk.empCode ");
	    hql.append("	and v.pk.ouCode = pre.pk.ouCode ");
		hql.append("	and v.pk.codeSeq = pre.codeSeqAct ");
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.pk.empCode = pne.pk.empCode ");
		hql.append(" and pre.pk.ouCode = pne.pk.ouCode ");
		hql.append(" and pre.codeSeqAct = pno.pk.codeSeq ");
		hql.append(" and pre.pk.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pno.pk.codeSeq, pne.pk.empCode ");

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
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			String empStatus = (String) r[6];

			FeeWgEmployeeVO vo = new FeeWgEmployeeVO();
			vo.setEmpCode(empCode);
			vo.setName(prefixName + " " + firstName + " " + lastName);
			vo.setOrgCode(orgCode);
			vo.setOrgDesc(orgDesc);
			vo.setEmpStatus(empStatus);
			

			empList.set(i, vo);
		}
		return empList;
	}

	public Integer getCountByCriteriaFeeEmpMT001(String ouCode,  String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.pk.empCode >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.pk.empCode <= '" + empCodeTo + "' ");

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select count(pne.pk.empCode) ");
		hql.append(" from FeeWgEmployee pre, ");
		hql.append(" FeeWgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
		hql.append(" where ");
		hql.append(" pre.pk.ouCode = '" + ouCode + "' ");
		//hql.append(" and pre.pk.year = " + year);
		//hql.append(" and pre.pk.period = " + period);
		// hql.append(" and pre.payStatus = '1' ");
		//hql.append(" and pne.empStatus = 'B' ");
		hql.append(" and pre.pk.empCode in ( ");
		hql.append(" 	select v.pk.empCode ");
		hql.append(" 	from VFeeWgPnEmployeeSecurity v ");
		hql.append(" 	where v.pk.userId = '" + userId + "' ");
		hql.append("    and v.pk.ouCode =  '" + ouCode + "' ");
		hql.append("	and v.pk.empCode = pre.pk.empCode ");
		hql.append("	and v.pk.ouCode = pre.pk.ouCode ");
		hql.append("	and v.pk.codeSeq= pre.codeSeqAct ");
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.pk.empCode = pne.pk.empCode ");
		hql.append(" and pre.pk.ouCode = pne.pk.ouCode ");
		hql.append(" and pre.codeSeqAct = pno.pk.codeSeq ");
		hql.append(" and pre.pk.ouCode = pno.pk.ouCode ");
		hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pne.pk.empCode ");

		Integer rs = (Integer) this.getSession().createQuery(hql.toString())
				.uniqueResult();

		return rs;
	}

	public void insertFeeWgEmployee(FeeWgPnEmployeeVO feeWgEmpTextVo)
			throws Exception {
		//FeeWgEmployee prEmp = new FeeWgEmployee();
		FeeWgEmployee tam = null;
		FeeWgEmployeePK tamPk = null;
		
				tam = new FeeWgEmployee();
				tamPk = new FeeWgEmployeePK();
				tamPk.setOuCode(feeWgEmpTextVo.getOuCode());
				tamPk.setEmpCode(feeWgEmpTextVo.getEmpCode());
				tam.setPk(tamPk);
				tam.setFirstName(feeWgEmpTextVo.getFirstName());
				tam.setLastName(feeWgEmpTextVo.getLastName());
				tam.setCodeSeq(feeWgEmpTextVo.getCodeSeqAct());
				tam.setCodeSeqAct(feeWgEmpTextVo.getCodeSeqAct());
				tam.setEmpStatus(feeWgEmpTextVo.getEmpStatus());
				tam.setPreName(feeWgEmpTextVo.getPreName());
				tam.setMaritalStatus(feeWgEmpTextVo.getMarritedStatus());
				tam.setPayFlag(feeWgEmpTextVo.getPayStatus());
				tam.setCreBy(feeWgEmpTextVo.getCreBy());
				tam.setCreDate(new Date());
				this.getHibernateTemplate().save(tam);
			

	}
	
	public List findByCriteriaWGCONMT001(String ouCode,  String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo, int page, int recPerPage)
			throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");
		
		if (yearCodeFrom != null && !yearCodeFrom.equals(""))
			criteria.append("and pre.pk.yearCon >= '" + yearCodeFrom + "' ");

		if (yearCodeTo != null && !yearCodeTo.equals(""))
			criteria.append("and pre.pk.yearCon <= '" + yearCodeTo + "' ");


		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.pk.contractNo >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.pk.contractNo <= '" + empCodeTo + "' ");
		
	    if (dateFrom != null && !dateFrom.equals(""))
			criteria.append("and to_char(pre.eContactDate,'DD/MM/YYYY') = '" + dateFrom + "' ");
	    
	    if (yearFrom != null && !yearFrom.equals(""))
	    	criteria.append("and to_char(to_number(to_char(pre.eContactDate,'YYYY'))+543) = '" + yearFrom + "'");
	    
	    if (monthFrom != null && !monthFrom.equals(""))
	    	criteria.append("and to_number(to_char(pre.eContactDate,'MM'))>= '" + monthFrom + "' ");
	    if (monthTo != null && !monthTo.equals(""))
	    	criteria.append("and to_number(to_char(pre.eContactDate,'MM'))<= '" + monthTo + "' ");

	    StringBuffer hql = new StringBuffer(0);
		hql.append(" select pne.pk.contractNo, ");
		hql.append(" pne.pk.yearCon, ");
		//hql.append(" pne.firstName, ");
		//hql.append(" pne.lastName, ");
		//hql.append(" pnp.positionShort, ");
		hql.append(" pno.orgCode, ");
		hql.append(" pno.divShort || ' ' || pno.areaDesc || ' ' || pno.secDesc || ' ' || pno.workDesc, ");
		hql.append(" pne.inactive, ");
		hql.append(" pne.pk.codeSeq ");
		hql.append(" from FeeContract pre, ");
		hql.append(" FeeContract pne,  PnOrganization pno ");
		hql.append(" where ");
		hql.append(" pre.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.pk.codeSeq in ( ");
		hql.append(" 	select v.pk.codeSeq ");
		hql.append(" 	from VPnEmployeeSecurity v ");
		hql.append(" 	where v.pk.userId = '" + userId + "' ");
		hql.append("    and v.pk.ouCode = '" + ouCode + "' ");
	    //hql.append("	and v.pk.empCode = pre.pk.empCode ");
	    hql.append("	and v.pk.ouCode = pre.pk.ouCode ");
		hql.append("	and v.pk.codeSeq = pre.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.pk.contractNo = pne.pk.contractNo ");
		hql.append(" and pre.pk.ouCode = pne.pk.ouCode ");
		hql.append(" and pre.pk.yearCon = pne.pk.yearCon ");
		hql.append(" and pre.pk.codeSeq = pne.pk.codeSeq ");
		hql.append(" and pre.pk.codeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.pk.ouCode = pno.pk.ouCode ");
		//hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pno.pk.codeSeq, pne.pk.contractNo ");

		Query q = this.getSession().createQuery(hql.toString());

		// System.out.println("************************");
		List empList = q.setFirstResult(page * recPerPage)
				.setMaxResults(recPerPage).list();
		// System.out.println("************************");

		for (int i = 0; i < empList.size(); i++) {
			Object[] r = (Object[]) empList.get(i);
			String conCode = (String) r[0];
			String yearCon = (String) r[1];
			String orgCode = (String) r[2];
			String orgDesc = (String) r[3];
			String conStatus = (String) r[4];

			FeeContractVO vo = new FeeContractVO();
			vo.setContractNo(conCode);
			vo.setYearCon(yearCon);
			vo.setOrgCode(orgCode);
			vo.setOrgDesc(orgDesc);
			vo.setInactive(conStatus);
			

			empList.set(i, vo);
		}
		return empList;
	}

	public Integer getCountByCriteriaWGCONMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo) throws Exception {

		StringBuffer criteria = new StringBuffer(0);

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			criteria.append("and pno.orgCode >= '" + orgCodeFrom + "' ");

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pno.orgCode <= '" + orgCodeTo + "' ");
		
		if (yearCodeFrom != null && !yearCodeFrom.equals(""))
			criteria.append("and pre.pk.yearCon >= '" + yearCodeFrom + "' ");

		if (yearCodeTo != null && !orgCodeTo.equals(""))
			criteria.append("and pre.pk.yearCon <= '" + yearCodeTo + "' ");


		if (empCodeFrom != null && !empCodeFrom.equals(""))
			criteria.append("and pre.pk.contractNo >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			criteria.append("and pre.pk.contractNo <= '" + empCodeTo + "' ");
		
		  if (dateFrom != null && !dateFrom.equals(""))
			criteria.append("and to_char(pre.eContactDate,'DD/MM/YYYY') = '" + dateFrom + "' ");
		
		  if (yearFrom != null && !yearFrom.equals(""))
		    	criteria.append("and to_char(to_number(to_char(pre.eContactDate,'YYYY'))+543) = '" + yearFrom + "'");
		    
		    if (monthFrom != null && !monthFrom.equals(""))
		    	criteria.append("and to_number(to_char(pre.eContactDate,'MM'))>= '" + monthFrom + "' ");
		    if (monthTo != null && !monthTo.equals(""))
		    	criteria.append("and to_number(to_char(pre.eContactDate,'MM'))<= '" + monthTo + "' ");


		StringBuffer hql = new StringBuffer(0);
		hql.append(" select count(pne.pk.contractNo) ");
		hql.append(" from FeeContract pre, ");
		hql.append(" FeeContract pne,  PnOrganization pno ");
		hql.append(" where ");
		hql.append(" pre.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pre.pk.codeSeq in ( ");
		hql.append(" 	select v.pk.codeSeq ");
		hql.append(" 	from VPnEmployeeSecurity v ");
		hql.append(" 	where v.pk.userId = '" + userId + "' ");
		hql.append("    and v.pk.ouCode = '" + ouCode + "' ");
	    //hql.append("	and v.pk.empCode = pre.pk.empCode ");
	    hql.append("	and v.pk.ouCode = pre.pk.ouCode ");
		hql.append("	and v.pk.codeSeq = pre.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(criteria);
		hql.append(" and pre.pk.contractNo = pne.pk.contractNo ");
		hql.append(" and pre.pk.ouCode = pne.pk.ouCode ");
		hql.append(" and pre.pk.yearCon = pne.pk.yearCon ");
		hql.append(" and pre.pk.codeSeq = pne.pk.codeSeq ");
		hql.append(" and pre.pk.codeSeq = pno.pk.codeSeq ");
		hql.append(" and pre.pk.ouCode = pno.pk.ouCode ");
		//hql.append(" and pne.preName = sf.preSuffCode ");
		hql.append(" order by pno.pk.codeSeq, pne.pk.contractNo ");
		Integer rs = (Integer) this.getSession().createQuery(hql.toString()).uniqueResult();

		return rs;
	}

	/*public List findByCriteriaWGCONMT001(String ouCode,  String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom, int page, int recPerPage)
			throws Exception {
		
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		
		sql = "select pne.contract_No, pne.year_con," +
				"pno.org_code,pno.div_short ||' '||pno.area_desc||' '||pno.sec_desc||' '||pno.work_desc," +
				"pne.inactive from FEE_CONTRACT pre,FEE_CONTRACT pne,Pn_organization pno " +
				"where pre.ou_code = 'ouCode' " +
				"and pre.code_seq in (select v.code_seq from v_pn_employee_security v " +
				"where v.user_id = 'userId' " +
				"and v.ou_code = 'ouCode' " +
				"and v.code_seq = pre.code_seq " +
				"and v.ou_code = pre.ou_code) " +
				"and pre.contract_No = pne.contract_No " +
				"and pre.ou_Code = pne.ou_Code " +
				"and pre.year_Con = pne.year_Con " +
				"and pre.code_Seq = pne.code_Seq " +
				"and pre.code_Seq = pno.code_Seq " +
				"and pre.ou_Code = pno.ou_Code ";
		
		if (!orgCodeFrom.equals("")&& !orgCodeTo.equals("")){
			sql+= " and pno.org_Code  between " + orgCodeFrom + " and "
					+ orgCodeTo;
		} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
			sql += " and pno.org_Code >= " + orgCodeFrom;
		}
		if (!yearCodeFrom.equals("")&& !yearCodeTo.equals("")){
			sql+= " and pre.year_Con  between " + yearCodeFrom + " and "
					+ yearCodeTo;
		} else if (!yearCodeFrom.equals("") && yearCodeTo.equals("")) {
			sql += " and pre.year_Code >= " + yearCodeFrom;
		}
		
		if (!empCodeFrom.equals("")&& !empCodeTo.equals("")){
			sql+= " and pre.contract_no  between " + empCodeFrom + " and "
					+ empCodeTo;
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and pre.contract_no >= " + empCodeFrom;
		}
		
		if (!dateFrom.equals("")){
			sql+= " and pre.e_contact_date  = to_date('" + dateFrom + "','DD/MM/YYYY') ";
		}
		sql+=" order by pno.code_Seq, pne.contract_No ";
	
          
		
	System.out.println(sql);
	resultList = this.jdbcTemplate.queryForList(sql);
	for (int i = 0; i < resultList.size(); i++) {
		ListOrderedMap map = (ListOrderedMap) resultList.get(i);
		String conCode = (String) map.getValue(0);
		String yearCon = (String) map.getValue(1);
		String orgCode = (String) map.getValue(2);
		String orgDesc = (String) map.getValue(3);
		String conStatus = (String) map.getValue(4);
	
		FeeContractVO vo = new FeeContractVO();
		vo.setContractNo(conCode);
		vo.setYearCon(yearCon);
		vo.setOrgCode(orgCode);
		vo.setOrgDesc(orgDesc);
		vo.setInactive(conStatus);
			

		returnList.add(vo);
		}
		return returnList;
	}*/
	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCreatedWgPrEmployeeData(String ouCode, String year,String period, String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayPrEmployee ");
		hql.append(" where key.ouCode = '" + ouCode + "' ");
		hql.append(" and key.year = '" + year );
		hql.append(" and key.period = '" + period );
		//hql.append(" order by key.year desc ,key.period desc ");
		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count wg prEmp : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return false;
		}
	}
	public List wpayKtbBankReport(String userId, String evaOuCode,
			Integer evaYear, Integer evaMonth) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		
	    int evaPeriod=1;
	    evaPeriod=evaMonth.intValue()*2;
      
		String sql = "";
		sql = " SELECT  PN.EMP_CODE EMP_CODE,NVL(FEE_WPAY_PR_DESC.GET_BANK_ID('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', PN.EMP_CODE ),'0000000000') BANK_ID"
				+ " ,DB.PREFIX_NAME||' '||PN.FIRST_NAME||' '||PN.LAST_NAME NAME,"
				+ " TO_CHAR( Fee_Wpay_Pr_Desc.GET_NET_INCOME('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+"', PN.EMP_CODE)) AMOUNT "
				+ "   FROM      WG_EMPLOYEE PN,DB_PRE_SUFF DB  "
				+ "    WHERE  PN.OU_CODE    ='001'"
				+ "    AND PN.EMP_STATUS ='B'   "
				//+ "    AND PN.BANK_CODE ='001'   "
				+ " AND PN.EMP_CODE IN (SELECT EMP_CODE FROM fee_wpay_pr_employee v "
                + "                          where v.year = " 
				+evaYear 
				+ " and v.period = "
				+evaPeriod
				+ " and v.pay_status = '1' "
				+ " and v.bank_code = '001' "
				+ " and nvl(v.flag_pr,0) = '0' "
				+ ") "
				+ " AND PN.PRE_NAME = DB.PRE_SUFF_CODE   "
				+ " AND Fee_Wpay_Pr_Desc.GET_NET_INCOME('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', PN.EMP_CODE) > 0"
				+ "                              ORDER BY PN.EMP_CODE ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String bankId = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String amount = (String) map.getValue(3);
			// Double amount =(Double)map.getValue(3);

			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11); 
			// String moveNote = (String) map.getValue(12);

			ApEmpBankVO vo = new ApEmpBankVO();

			vo.setEmpCode(empCode);
			vo.setBankId(bankId);
			vo.setEmpName(empName);
			vo.setAmount(amount);

			// vo.setEduFirst(eduFirst);
			// vo.setManReplace(manReplace);
			// vo.setMoveSeq(moveSeq);
			// vo.setMoveOrg(moveOrg);
			// vo.setDateSubmit(dateSubmit);
			// vo.setMoveReason(moveReason);
			// vo.setMoveNote(moveNote);

			returnList.add(vo);
			//System.out.println("EMP_CODE : ", empCode );

		}
		return returnList;
	}
	public List findBankIdBySecurity(String userId, String ouCode, String year,
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
		hql.append(" order by a.bankId ");

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
	
	public List feeWpayPrDailyRepByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.period = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, rwPre.fullName,");
		hql.append(" nvl(rwPre.secCode,rwPre.areaCode)||' '||rwPre.areaDesc||' '||rwPre.secDesc,");
		hql.append(" rwPre.areaCode, ");
		hql.append(" rwPre.areaDesc, ");
		hql.append(" rwPre.secCode, ");
		hql.append(" rwPre.secDesc, ");
		hql.append(" rwPre.newSalary, ");
		hql.append(" rwPre.bankId, ");
		hql.append(" rwPre.incAmt, " );
		hql.append(" rwPre.taxAmt, " );
		hql.append(" rwPre.salary, ");
		hql.append(" rwPre.salary02, ");
		hql.append(" rwPre.salary03, ");
		hql.append(" rwPre.klongchev, ");
		hql.append(" rwPre.yangchev, ");
		hql.append(" rwPre.childs, ");
		hql.append(" rwPre.ot, ");
		hql.append(" rwPre.premium, ");
		hql.append(" rwPre.health, ");
		hql.append(" rwPre.netIncome, ");
		hql.append(" rwPre.tax, ");
		hql.append(" rwPre.vinai1Pct, ");
		hql.append(" rwPre.vinai1Lev, ");
		hql.append(" rwPre.vinai2, ");
		hql.append(" rwPre.refund, ");
		hql.append(" rwPre.lawLoan, ");
		hql.append(" rwPre.car, ");
		hql.append(" rwPre.loanPost, ");
		hql.append(" rwPre.motor, ");
		hql.append(" rwPre.rick, ");
		hql.append(" rwPre.study, ");
		hql.append(" rwPre.pangLoan, ");
		hql.append(" rwPre.cpt, ");
		hql.append(" rwPre.etc, ");
		hql.append(" rwPre.ssr, ");
		hql.append(" rwPre.oomSin, ");
		hql.append(" rwPre.kys, " );
		hql.append(" rwPre.kro, " );		
		hql.append(" rwPre.netDec, ");
		hql.append(" rwPre.net, ");
		hql.append(" rwPre.orgCode ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpayPrDailyNetAmt rwPre ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		//hql.append(" and rwPre.empCode ='914960' ");
		hql.append(" and rwPre.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by nvl(rwPre.secCode,rwPre.divCode)||' '||rwPre.divDesc||' '||rwPre.secDesc,rwPre.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			String areaCode = (String) r[3];
			String areaDesc = (String) r[4];
			String secCode = (String) r[5];
			String secDesc = (String) r[6];
			Double newSalary = (Double) r[7];
			String bankId = (String) r[8];
			Double incAmt = (Double) r[9];
			Double taxAmt = (Double) r[10];
			Double salary = (Double) r[11];
			Double salary02 = (Double) r[12];
			Double salary03 = (Double) r[13];
			Double klongchev = (Double) r[14];
			Double yangchev = (Double) r[15];
			Double childs = (Double) r[16];
			Double ot = (Double) r[17];
			Double premium = (Double) r[18];
			Double health = (Double) r[19];
			Double netIncome = (Double) r[20];
			Double tax = (Double) r[21];
			Double vinai1Pct = (Double) r[22];
			Double vinai1Lev = (Double) r[23];
			Double vinai2 = (Double) r[24];
			Double refund = (Double) r[25];
			Double lawLoan = (Double) r[26];
			Double car = (Double) r[27];
			Double loanPost = (Double) r[28];
			Double motor = (Double) r[29];
			Double rick = (Double) r[30];
			Double study = (Double) r[31];
			Double pangLoan = (Double) r[32];
			Double cpt = (Double) r[33];
			Double etc = (Double) r[34];
			Double ssr = (Double) r[35];
			Double oomSin = (Double) r[36];
			Double kys = (Double) r[37];
			Double kro = (Double) r[38];
			Double netDec = (Double) r[39];
			Double net = (Double) r[40];
			String orgCode = (String) r[41];
			FeeWpayPrDailyNetAmtRepVO ret = new FeeWpayPrDailyNetAmtRepVO();
			ret.setEmpCode(empCode);
			ret.setFullName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setAreaCode(areaCode);
			ret.setAreaDesc(areaDesc);
			ret.setSecCode(secCode);
			ret.setSecDesc(secDesc);
			ret.setNewSalary(newSalary);
			ret.setBankId(bankId);
			ret.setIncAmt(incAmt);
			ret.setTaxAmt(taxAmt);
			ret.setSalary(salary);
			ret.setSalary02(salary02);
			ret.setSalary03(salary03);
			ret.setKlongchev(klongchev);
			ret.setYangchev(yangchev);
			ret.setChilds(childs);
			ret.setOt(ot);
			ret.setPremium(premium);
			ret.setHealth(health);
			ret.setNetIncome(netIncome);
			ret.setTax(tax);
			ret.setVinai1Pct(vinai1Pct);
			ret.setVinai1Lev(vinai1Lev);
			ret.setVinai2(vinai2);
			ret.setRefund(refund);
			
			ret.setLawLoan(lawLoan);
			
			ret.setCar(car);
			ret.setLoanPost(loanPost);
			ret.setPangLoan(pangLoan);
			ret.setMotor(motor);
			ret.setRick(rick);
			ret.setStudy(study);
			ret.setSsr(ssr);
			ret.setOomSin(oomSin);
			ret.setCpt(cpt);
			ret.setEtc(etc);
			ret.setKys(kys);
			ret.setKro(kro);
			ret.setNetDec(netDec);
			ret.setNet(net);
			ret.setOrgCode(orgCode);
			retList.add(ret);
		}
		return retList;
	}
	
	
	public List feeWpay401Report(String evaOuCode,Long evaYear, Long evaPeriod,String flag) {
		
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearSum = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodSum = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
		hql.append(" rwPre.periodSum, ");
		hql.append(" rwPre.levelGroupSum, ");
		hql.append(" rwPre.groupCodeSum, ");
		hql.append(" rwPre.accNoSum, ");
		hql.append(" rwPre.accNameSum, ");
		hql.append(" rwPre.empAmountSum, ");
		hql.append(" rwPre.amountSum, ");
		hql.append(" rwPre.incDecCodeSum, ");
		hql.append(" rwPre.incDecSubCodeSum, ");
		hql.append(" rwPre.incDecNameSum ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpay401 rwPre ");
		hql.append(" where rwPre.ouCodeSum = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and rwPre.empAmountSum > 0 ");
		hql.append(" and rwPre.levelGroupSum = '");
		hql.append(flag);
		hql.append("' ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

		System.out.println("HQL 401Report ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String ouCodeSum = (String) r[0];
			Double yearSum = (Double) r[1];
			Double periodSum = (Double) r[2];
			String levelGroupSum = (String) r[3];
			String groupCodeSum = (String) r[4];
			String accNoSum = (String) r[5];
			String accNameSum = (String) r[6];
			Double empAmountSum = (Double) r[7];
			Double amountSum = (Double) r[8];
			String incDecCodeSum = (String) r[9];
			String incDecSubCodeSum = (String) r[10];
			String incDecNameSum = (String) r[11];
			
			VFeeWpay401VO ret = new VFeeWpay401VO();
			ret.setOuCodeSum(ouCodeSum);
			ret.setYearSum(yearSum);
			ret.setPeriodSum(periodSum);
			ret.setLevelGroupSum(levelGroupSum);
			ret.setGroupCodeSum(groupCodeSum);
			ret.setAccNameSum(accNameSum);
			ret.setAccNoSum(accNoSum);
			ret.setEmpAmountSum(empAmountSum);
			ret.setAmountSum(amountSum);
			ret.setIncDecCodeSum(incDecCodeSum);
			ret.setIncDecNameSum(incDecNameSum);
			ret.setIncDecSubCodeSum(incDecSubCodeSum);
			
			
			retList.add(ret);
		}
		return retList;
	}
	
	public List feeWpay401KTBReport(String evaOuCode,Long evaYear, Long evaPeriod,String flag) {
		
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearSum = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodSum = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
		hql.append(" rwPre.periodSum, ");
		hql.append(" rwPre.levelGroupSum, ");
		hql.append(" rwPre.groupCodeSum, ");
		hql.append(" rwPre.accNoSum, ");
		hql.append(" rwPre.accNameSum, ");
		hql.append(" rwPre.empAmountSum, ");
		hql.append(" rwPre.amountSum, ");
		hql.append(" rwPre.incDecCodeSum, ");
		hql.append(" rwPre.incDecSubCodeSum, ");
		hql.append(" rwPre.incDecNameSum ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpay401KTB rwPre ");
		hql.append(" where rwPre.ouCodeSum = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and rwPre.empAmountSum > 0 ");
		hql.append(" and rwPre.levelGroupSum = '");
		hql.append(flag);
		hql.append("' ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

		System.out.println("HQL 401Report ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String ouCodeSum = (String) r[0];
			Double yearSum = (Double) r[1];
			Double periodSum = (Double) r[2];
			String levelGroupSum = (String) r[3];
			String groupCodeSum = (String) r[4];
			String accNoSum = (String) r[5];
			String accNameSum = (String) r[6];
			Double empAmountSum = (Double) r[7];
			Double amountSum = (Double) r[8];
			String incDecCodeSum = (String) r[9];
			String incDecSubCodeSum = (String) r[10];
			String incDecNameSum = (String) r[11];
			
			VFeeWpay401VO ret = new VFeeWpay401VO();
			ret.setOuCodeSum(ouCodeSum);
			ret.setYearSum(yearSum);
			ret.setPeriodSum(periodSum);
			ret.setLevelGroupSum(levelGroupSum);
			ret.setGroupCodeSum(groupCodeSum);
			ret.setAccNameSum(accNameSum);
			ret.setAccNoSum(accNoSum);
			ret.setEmpAmountSum(empAmountSum);
			ret.setAmountSum(amountSum);
			ret.setIncDecCodeSum(incDecCodeSum);
			ret.setIncDecNameSum(incDecNameSum);
			ret.setIncDecSubCodeSum(incDecSubCodeSum);
			
			
			retList.add(ret);
		}
		return retList;
	}
	
	public List feeWpay401SCBReport(String evaOuCode,Long evaYear, Long evaPeriod,String flag) {
		
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearSum = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodSum = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
		hql.append(" rwPre.periodSum, ");
		hql.append(" rwPre.levelGroupSum, ");
		hql.append(" rwPre.groupCodeSum, ");
		hql.append(" rwPre.accNoSum, ");
		hql.append(" rwPre.accNameSum, ");
		hql.append(" rwPre.empAmountSum, ");
		hql.append(" rwPre.amountSum, ");
		hql.append(" rwPre.incDecCodeSum, ");
		hql.append(" rwPre.incDecSubCodeSum, ");
		hql.append(" rwPre.incDecNameSum ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpay401SCB rwPre ");
		hql.append(" where rwPre.ouCodeSum = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and rwPre.empAmountSum > 0 ");
		hql.append(" and rwPre.levelGroupSum = '");
		hql.append(flag);
		hql.append("' ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

		System.out.println("HQL 401Report ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String ouCodeSum = (String) r[0];
			Double yearSum = (Double) r[1];
			Double periodSum = (Double) r[2];
			String levelGroupSum = (String) r[3];
			String groupCodeSum = (String) r[4];
			String accNoSum = (String) r[5];
			String accNameSum = (String) r[6];
			Double empAmountSum = (Double) r[7];
			Double amountSum = (Double) r[8];
			String incDecCodeSum = (String) r[9];
			String incDecSubCodeSum = (String) r[10];
			String incDecNameSum = (String) r[11];
			
			VFeeWpay401VO ret = new VFeeWpay401VO();
			ret.setOuCodeSum(ouCodeSum);
			ret.setYearSum(yearSum);
			ret.setPeriodSum(periodSum);
			ret.setLevelGroupSum(levelGroupSum);
			ret.setGroupCodeSum(groupCodeSum);
			ret.setAccNameSum(accNameSum);
			ret.setAccNoSum(accNoSum);
			ret.setEmpAmountSum(empAmountSum);
			ret.setAmountSum(amountSum);
			ret.setIncDecCodeSum(incDecCodeSum);
			ret.setIncDecNameSum(incDecNameSum);
			ret.setIncDecSubCodeSum(incDecSubCodeSum);
			
			
			retList.add(ret);
		}
		return retList;
	}
	
	
	
public List feeWpayGlTransferReport(String evaYear, String evaMonth) {
		
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" where rwPre.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaMonth != null && !evaMonth.equals("")) {
			criteria.append(" and rwPre.monthPr = ");
			criteria.append(evaMonth);
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.seq, rwPre.yearPr,");
		hql.append(" rwPre.monthPr, ");
		hql.append(" rwPre.accountingDate, ");
		hql.append(" rwPre.accountCode, ");
		hql.append(" rwPre.accountName, ");
		hql.append(" rwPre.debit, ");
		hql.append(" rwPre.credit ");
		hql.append(" from VFeeWpayGlTransfer rwPre ");
		hql.append(criteria);
		
		
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by rwPre.yearPr,rwPre.monthPr,rwPre.seq,rwPre.accountCode ");

		System.out.println("HQL GlTransferReport ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double seq = (Double) r[0];
			Double yearPr = (Double) r[1];
			Double monthPr = (Double) r[2];
			Date accountingDate = (Date) r[3];
			String accountCode = (String) r[4];
			String accountName = (String) r[5];
			Double debit = (Double) r[6];
			Double credit = (Double) r[7];
			
			
			VFeeWpayGlTransferVO ret = new VFeeWpayGlTransferVO();
			ret.setSeq(seq);
			ret.setYearPr(yearPr);
			ret.setMonthPr(monthPr);
			ret.setAccountingDate(accountingDate);
			ret.setAccountCode(accountCode);
			ret.setAccountName(accountName);
			ret.setDebit(debit);
			ret.setCredit(credit);
			
			
			
			retList.add(ret);
		}
		return retList;
	}
	
	public List findByCriteriaIntBrk001(String ouCode, String year,
		String period, String userId, String bankIdFrom, String bankIdTo,
		String empCodeFrom, String empCodeTo, int page, int recPerPage)
		throws Exception {

	StringBuffer criteria = new StringBuffer(0);

	
	if (empCodeFrom != null && !empCodeFrom.equals(""))
		criteria.append("and pre.key.empCode >= '" + empCodeFrom + "' ");

	if (empCodeTo != null && !empCodeTo.equals(""))
		criteria.append("and pre.key.empCode <= '" + empCodeTo + "' ");
	
	if (bankIdFrom != null && !bankIdFrom.equals(""))
		criteria.append("and pre.bankId >= '" + bankIdFrom + "' ");

	if (bankIdTo != null && !bankIdTo.equals(""))
		criteria.append("and pre.bankId <= '" + bankIdTo + "' ");


	StringBuffer hql = new StringBuffer(0);
	hql.append(" select pne.wgEmployeePK.empCode, ");
	hql.append(" sf.prefixName, ");
	hql.append(" pne.firstName, ");
	hql.append(" pne.lastName, ");
	//hql.append(" pnp.positionShort, ");
	hql.append(" pno.orgCode, ");
	hql.append(" pno.divShort || ' ' || pno.areaDesc || ' ' || pno.secDesc || ' ' || pno.workDesc, ");
	hql.append(" pno.pk.codeSeq ");
	hql.append(" from FeeWpayPrEmployee pre, ");
	hql.append(" WgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
	hql.append(" where ");
	hql.append(" pre.key.ouCode = '" + ouCode + "' ");
	hql.append(" and pre.key.year = " + year);
	hql.append(" and pre.key.period = " + period);
	// hql.append(" and pre.payStatus = '1' ");
	hql.append(" and pne.empStatus = 'B' ");
	hql.append(" and pre.key.empCode not in ( ");
	hql.append(" 	select key.empCode ");
	hql.append(" 	from FeeWpayPrBreakPay ");
	hql.append(" 	where key.ouCode = '" + ouCode + "' ");
	hql.append("    and key.year = " + year);
	hql.append("	and key.period = " + period);
	hql.append(" ) ");
	hql.append(" and pre.key.empCode in ( ");
	hql.append(" 	select pk.empCode ");
	hql.append(" 	from VFeeWpPrEmployeeSecurity ");
	hql.append(" 	where pk.userId = '" + userId + "' ");
	hql.append("    and pk.year = " + year);
	hql.append("	and pk.period = " + period);
	hql.append(" ) ");
	hql.append(criteria);
	hql.append(" and pre.key.empCode = pne.wgEmployeePK.empCode ");
	hql.append(" and pre.key.ouCode = pne.wgEmployeePK.ouCode ");
	//hql.append(" and pne.pk.ouCode = pnp.pk.ouCode ");
	//hql.append(" and pne.gworkCode = pnp.pk.gworkCode ");
	//hql.append(" and pne.positionCode = pnp.pk.positionCode ");
	// hql.append(" and pre.codeSeqWork = pno.pk.codeSeq ");
	hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
	hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
	hql.append(" and pne.preName = sf.preSuffCode ");
	hql.append(" order by pno.pk.codeSeq, pne.wgEmployeePK.empCode ");

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
	//	String positionShort = (String) r[4];
		String orgCode = (String) r[4];
		String orgDesc = (String) r[5];

		PayRollEmployeeVO vo = new PayRollEmployeeVO();
		vo.setEmpCode(empCode);
		vo.setName(prefixName + " " + firstName + " " + lastName);
		//vo.setPositionShort(positionShort);
		vo.setOrgCode(orgCode);
		vo.setOrgDesc(orgDesc);

		empList.set(i, vo);
	}
		return empList;
	}

	public Integer getCountByCriteriaIntBrk001(String ouCode, String year,
		String period, String userId, String bankIdFrom, String bankIdTo,
		String empCodeFrom, String empCodeTo) throws Exception {

	StringBuffer criteria = new StringBuffer(0);


	if (bankIdFrom != null && !bankIdFrom.equals(""))
		criteria.append("and pre.bankId >= '" + bankIdFrom + "' ");

	if (bankIdTo != null && !bankIdTo.equals(""))
		criteria.append("and pre.bankId <= '" + bankIdTo + "' ");

	if (empCodeFrom != null && !empCodeFrom.equals(""))
		criteria.append("and pre.key.empCode >= '" + empCodeFrom + "' ");

	if (empCodeTo != null && !empCodeTo.equals(""))
		criteria.append("and pre.key.empCode <= '" + empCodeTo + "' ");

	StringBuffer hql = new StringBuffer(0);
	hql.append(" select count(pne.wgEmployeePK.empCode) ");
	hql.append(" from FeeWpayPrEmployee pre, ");
	hql.append(" WgEmployee pne,  PnOrganization pno, DbPreSuff sf ");
	hql.append(" where ");
	hql.append(" pre.key.ouCode = '" + ouCode + "' ");
	hql.append(" and pre.key.year = " + year);
	hql.append(" and pre.key.period = " + period);
	// hql.append(" and pre.payStatus = '1' ");
	hql.append(" and pne.empStatus = 'B' ");
	hql.append(" and pre.key.empCode not in ( ");
	hql.append(" 	select key.empCode ");
	hql.append(" 	from FeeWpayPrBreakPay ");
	hql.append(" 	where key.ouCode = '" + ouCode + "' ");
	hql.append("    and key.year = " + year);
	hql.append("	and key.period = " + period);
	hql.append(" ) ");
	hql.append(" and pre.key.empCode in ( ");
	hql.append(" 	select pk.empCode ");
	hql.append(" 	from VFeeWpPrEmployeeSecurity ");
	hql.append(" 	where pk.userId = '" + userId + "' ");
	hql.append("    and pk.year = " + year);
	hql.append("	and pk.period = " + period);
	hql.append(" ) ");
	hql.append(criteria);
	hql.append(" and pre.key.empCode = pne.wgEmployeePK.empCode ");
	hql.append(" and pre.key.ouCode = pne.wgEmployeePK.ouCode ");
	hql.append(" and pre.newCodeSeq = pno.pk.codeSeq ");
	hql.append(" and pre.key.ouCode = pno.pk.ouCode ");
	hql.append(" and pne.preName = sf.preSuffCode ");
	hql.append(" order by pne.wgEmployeePK.empCode ");

	Integer rs = (Integer) this.getSession().createQuery(hql.toString()).uniqueResult();
	return rs;
	}

	
	public List feeWpayPrBreakPayReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.pk.period = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();
		hql.append(" select rwV.pk.empCode, rwV.empName,");
		hql.append(" rwV.orgDesc,");
		hql.append(" rwV.bankId, ");
		hql.append(" rwV.bankCode, ");
		hql.append(" rwV.breakAmt ");
		hql.append(" from VFeeWpayPrBreakPay rwV ");
		hql.append(" where rwV.pk.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and rwV.bankCode = '001' ");
		hql.append(criteria);
		hql.append(" order by rwV.divSeq, nvl(rwV.areaSeq,0),nvl(rwV.secSeq,0),rwV.pk.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			String bankId = (String) r[3];
			String bankCode = (String) r[4];
			Double breakAmt = (Double) r[5];
			
			VFeeWpayPrBreakPayVO ret = new VFeeWpayPrBreakPayVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setBankId(bankId);
			ret.setBankCode(bankCode);
			ret.setBreakAmt(breakAmt);
			
		

			retList.add(ret);
		}
		return retList;
	}
	
	public List feeWpayPrBreakPayScbReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.pk.period = ");
			criteria.append(evaPeriod);
		}

		

		StringBuffer hql = new StringBuffer();
		hql.append(" select rwV.pk.empCode, rwV.empName,");
		hql.append(" rwV.orgDesc,");
		hql.append(" rwV.bankId, ");
		hql.append(" rwV.bankCode, ");
		hql.append(" rwV.breakAmt ");
		hql.append(" from VFeeWpayPrBreakPay rwV ");
		hql.append(" where rwV.pk.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and rwV.bankCode = '002' ");
		hql.append(criteria);
		hql.append(" order by rwV.divSeq, nvl(rwV.areaSeq,0),nvl(rwV.secSeq,0),rwV.pk.empCode ");
		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			String bankId = (String) r[3];
			String bankCode = (String) r[4];
			Double breakAmt = (Double) r[5];
			
			VFeeWpayPrBreakPayVO ret = new VFeeWpayPrBreakPayVO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setBankId(bankId);
			ret.setBankCode(bankCode);
			ret.setBreakAmt(breakAmt);
			
		

			retList.add(ret);
		}
		return retList;
	}
	
	public List feeWpayPrMonthlyRepByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.year = ");
			criteria.append(evaYear);
		}

		

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, rwPre.fullName,");
		hql.append(" nvl(rwPre.secCode,rwPre.areaCode)||' '||rwPre.areaDesc||' '||rwPre.secDesc,");
		hql.append(" rwPre.areaCode, ");
		hql.append(" rwPre.areaDesc, ");
		hql.append(" rwPre.secCode, ");
		hql.append(" rwPre.secDesc, ");
		//hql.append(" rwPre.taxId, ");
		hql.append(" substr(rwPre.empPeriod,1,6)||to_char(to_number(substr(rwPre.empPeriod,7,10))+543), ");
		hql.append(" rwPre.incomeAmt, ");
		hql.append(" rwPre.taxAmt, ");
		//hql.append(" rwPre.divShortD, ");
		//hql.append(" rwPre.diffSeq, ");
		//hql.append(" rwPre.empStatus, ");
		hql.append(" rwPre.orgCode ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpayTaxYear rwPre ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and rwPre.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by nvl(rwPre.secCode,rwPre.divCode)||' '||rwPre.divDesc||' '||rwPre.secDesc,rwPre.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			String areaCode = (String) r[3];
			String areaDesc = (String) r[4];
			String secCode = (String) r[5];
			String secDesc = (String) r[6];
			//String taxId   = (String) r[7];
			
			String empPeriod   = (String) r[7];
			Double incomeAmt   = (Double) r[8];
			Double taxAmt  = (Double) r[9];
			//String divShortD   = (String) r[10];
			//Integer  diffSeq   = (Integer) r[11];
			//String empStatus   = (String) r[12];
			String orgCode = (String) r[10];
			VFeeWpayTaxYearRepVO ret = new VFeeWpayTaxYearRepVO();
			ret.setEmpCode(empCode);
			ret.setFullName(empName);
			ret.setOrgDesc(orgDesc);
			ret.setAreaCode(areaCode);
			ret.setAreaDesc(areaDesc);
			ret.setSecCode(secCode);
			ret.setSecDesc(secDesc);
		   
			//ret.setTaxId(taxId);
			ret.setEmpPeriod(empPeriod);
			ret.setIncomeAmt(incomeAmt);
			ret.setTaxAmt(taxAmt);
			//ret.setDivShortD(divShortD);
			//ret.setDiffSeq(diffSeq);
			//ret.setEmpStatus(empStatus);
			ret.setOrgCode(orgCode);
			retList.add(ret);
		}
		return retList;
	}
	
public List feeWpay401UserReport(String evaOuCode,Long evaYear, Long evaPeriod,String flag,String userId) {
		
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearSum = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwPre.periodSum = ");
			criteria.append(evaPeriod);
		}
		
		

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
		hql.append(" rwPre.periodSum, ");
		hql.append(" rwPre.levelGroupSum, ");
		hql.append(" rwPre.groupCodeSum, ");
		hql.append(" rwPre.accNoSum, ");
		hql.append(" rwPre.accNameSum, ");
		hql.append(" rwPre.empAmountSum, ");
		hql.append(" rwPre.amountSum, ");
		hql.append(" rwPre.incDecCodeSum, ");
		hql.append(" rwPre.incDecSubCodeSum, ");
		hql.append(" rwPre.incDecNameSum ");
		//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
		//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
		hql.append(" from VFeeWpay401U rwPre ");
		hql.append(" where rwPre.ouCodeSum = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and rwPre.empAmountSum > 0 ");
		hql.append(" and rwPre.levelGroupSum = '");
		hql.append(flag);
		hql.append("' ");
		hql.append(" and rwPre.userSum = '" + userId + "' ");
		// hql.append(" and rwPre.creBy = '" + userId + "' ");
		hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

		System.out.println("HQL 401ReportUser ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String ouCodeSum = (String) r[0];
			Double yearSum = (Double) r[1];
			Double periodSum = (Double) r[2];
			String levelGroupSum = (String) r[3];
			String groupCodeSum = (String) r[4];
			String accNoSum = (String) r[5];
			String accNameSum = (String) r[6];
			Double empAmountSum = (Double) r[7];
			Double amountSum = (Double) r[8];
			String incDecCodeSum = (String) r[9];
			String incDecSubCodeSum = (String) r[10];
			String incDecNameSum = (String) r[11];
			
			VFeeWpay401VO ret = new VFeeWpay401VO();
			ret.setOuCodeSum(ouCodeSum);
			ret.setYearSum(yearSum);
			ret.setPeriodSum(periodSum);
			ret.setLevelGroupSum(levelGroupSum);
			ret.setGroupCodeSum(groupCodeSum);
			ret.setAccNameSum(accNameSum);
			ret.setAccNoSum(accNoSum);
			ret.setEmpAmountSum(empAmountSum);
			ret.setAmountSum(amountSum);
			ret.setIncDecCodeSum(incDecCodeSum);
			ret.setIncDecNameSum(incDecNameSum);
			ret.setIncDecSubCodeSum(incDecSubCodeSum);
			
			
			retList.add(ret);
		}
		return retList;
	}

public List feeWpay401UserSCBReport(String evaOuCode,Long evaYear, Long evaPeriod,String flag,String userId) {
	
	StringBuffer criteria = new StringBuffer();

	if (evaYear != null && !evaYear.equals("")) {
		criteria.append(" and rwPre.yearSum = ");
		criteria.append(evaYear);
	}

	if (evaPeriod != null && !evaPeriod.equals("")) {
		criteria.append(" and rwPre.periodSum = ");
		criteria.append(evaPeriod);
	}
	
	

	

	StringBuffer hql = new StringBuffer();

	hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
	hql.append(" rwPre.periodSum, ");
	hql.append(" rwPre.levelGroupSum, ");
	hql.append(" rwPre.groupCodeSum, ");
	hql.append(" rwPre.accNoSum, ");
	hql.append(" rwPre.accNameSum, ");
	hql.append(" rwPre.empAmountSum, ");
	hql.append(" rwPre.amountSum, ");
	hql.append(" rwPre.incDecCodeSum, ");
	hql.append(" rwPre.incDecSubCodeSum, ");
	hql.append(" rwPre.incDecNameSum ");
	//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
	//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
	hql.append(" from VFeeWpay401USCB rwPre ");
	hql.append(" where rwPre.ouCodeSum = '");
	hql.append(evaOuCode);
	hql.append("' ");
	hql.append(criteria);
	hql.append(" and rwPre.empAmountSum > 0 ");
	hql.append(" and rwPre.levelGroupSum = '");
	hql.append(flag);
	hql.append("' ");
	hql.append(" and rwPre.userSum = '" + userId + "' ");
	// hql.append(" and rwPre.creBy = '" + userId + "' ");
	hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

	System.out.println("HQL 401ReportUser ==> " + hql.toString());

	List empList = this.getSession().createQuery(hql.toString()).list();
	List retList = new ArrayList();

	for (Iterator it = empList.iterator(); it.hasNext();) {
		Object[] r = (Object[]) it.next();
		String ouCodeSum = (String) r[0];
		Double yearSum = (Double) r[1];
		Double periodSum = (Double) r[2];
		String levelGroupSum = (String) r[3];
		String groupCodeSum = (String) r[4];
		String accNoSum = (String) r[5];
		String accNameSum = (String) r[6];
		Double empAmountSum = (Double) r[7];
		Double amountSum = (Double) r[8];
		String incDecCodeSum = (String) r[9];
		String incDecSubCodeSum = (String) r[10];
		String incDecNameSum = (String) r[11];
		
		VFeeWpay401VO ret = new VFeeWpay401VO();
		ret.setOuCodeSum(ouCodeSum);
		ret.setYearSum(yearSum);
		ret.setPeriodSum(periodSum);
		ret.setLevelGroupSum(levelGroupSum);
		ret.setGroupCodeSum(groupCodeSum);
		ret.setAccNameSum(accNameSum);
		ret.setAccNoSum(accNoSum);
		ret.setEmpAmountSum(empAmountSum);
		ret.setAmountSum(amountSum);
		ret.setIncDecCodeSum(incDecCodeSum);
		ret.setIncDecNameSum(incDecNameSum);
		ret.setIncDecSubCodeSum(incDecSubCodeSum);
		
		
		retList.add(ret);
	}
	return retList;
}
public List feeWpay401UserKTBReport(String evaOuCode,Long evaYear, Long evaPeriod,String flag,String userId) {
	
	StringBuffer criteria = new StringBuffer();

	if (evaYear != null && !evaYear.equals("")) {
		criteria.append(" and rwPre.yearSum = ");
		criteria.append(evaYear);
	}

	if (evaPeriod != null && !evaPeriod.equals("")) {
		criteria.append(" and rwPre.periodSum = ");
		criteria.append(evaPeriod);
	}
	
	

	

	StringBuffer hql = new StringBuffer();

	hql.append(" select rwPre.ouCodeSum, rwPre.yearSum,");
	hql.append(" rwPre.periodSum, ");
	hql.append(" rwPre.levelGroupSum, ");
	hql.append(" rwPre.groupCodeSum, ");
	hql.append(" rwPre.accNoSum, ");
	hql.append(" rwPre.accNameSum, ");
	hql.append(" rwPre.empAmountSum, ");
	hql.append(" rwPre.amountSum, ");
	hql.append(" rwPre.incDecCodeSum, ");
	hql.append(" rwPre.incDecSubCodeSum, ");
	hql.append(" rwPre.incDecNameSum ");
	//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
	//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
	hql.append(" from VFeeWpay401UKTB rwPre ");
	hql.append(" where rwPre.ouCodeSum = '");
	hql.append(evaOuCode);
	hql.append("' ");
	hql.append(criteria);
	hql.append(" and rwPre.empAmountSum > 0 ");
	hql.append(" and rwPre.levelGroupSum = '");
	hql.append(flag);
	hql.append("' ");
	hql.append(" and rwPre.userSum = '" + userId + "' ");
	// hql.append(" and rwPre.creBy = '" + userId + "' ");
	hql.append(" order by rwPre.yearSum,rwPre.periodSum,rwPre.levelGroupSum,rwPre.groupCodeSum,rwPre.incDecCodeSum,rwPre.incDecSubCodeSum ");

	System.out.println("HQL 401ReportUser ==> " + hql.toString());

	List empList = this.getSession().createQuery(hql.toString()).list();
	List retList = new ArrayList();

	for (Iterator it = empList.iterator(); it.hasNext();) {
		Object[] r = (Object[]) it.next();
		String ouCodeSum = (String) r[0];
		Double yearSum = (Double) r[1];
		Double periodSum = (Double) r[2];
		String levelGroupSum = (String) r[3];
		String groupCodeSum = (String) r[4];
		String accNoSum = (String) r[5];
		String accNameSum = (String) r[6];
		Double empAmountSum = (Double) r[7];
		Double amountSum = (Double) r[8];
		String incDecCodeSum = (String) r[9];
		String incDecSubCodeSum = (String) r[10];
		String incDecNameSum = (String) r[11];
		
		VFeeWpay401VO ret = new VFeeWpay401VO();
		ret.setOuCodeSum(ouCodeSum);
		ret.setYearSum(yearSum);
		ret.setPeriodSum(periodSum);
		ret.setLevelGroupSum(levelGroupSum);
		ret.setGroupCodeSum(groupCodeSum);
		ret.setAccNameSum(accNameSum);
		ret.setAccNoSum(accNoSum);
		ret.setEmpAmountSum(empAmountSum);
		ret.setAmountSum(amountSum);
		ret.setIncDecCodeSum(incDecCodeSum);
		ret.setIncDecNameSum(incDecNameSum);
		ret.setIncDecSubCodeSum(incDecSubCodeSum);
		
		
		retList.add(ret);
	}
	return retList;
}


public List prDailyRepByOrg(String userId, String evaOuCode,
		Long evaYear, Long evaPeriod) {
	StringBuffer criteria = new StringBuffer();

	if (evaYear != null && !evaYear.equals("")) {
		criteria.append(" and rwPre.year = ");
		criteria.append(evaYear);
	}

	if (evaPeriod != null && !evaPeriod.equals("")) {
		criteria.append(" and rwPre.period = ");
		criteria.append(evaPeriod);
	}

	

	StringBuffer hql = new StringBuffer();

	hql.append(" select rwPre.empCode, rwPre.fullName,");
	hql.append(" nvl(rwPre.secCode,rwPre.areaCode)||' '||rwPre.divDesc||' '||' '||rwPre.areaDesc||' '||rwPre.secDesc,");
	hql.append(" rwPre.areaCode, ");
	hql.append(" rwPre.areaDesc, ");
	hql.append(" rwPre.secCode, ");
	hql.append(" rwPre.secDesc, ");
	hql.append(" rwPre.newSalary, ");
	hql.append(" rwPre.bankId, ");
	hql.append(" rwPre.incAmt, " );
	hql.append(" rwPre.taxAmt, " );
	hql.append(" rwPre.salary, ");
	hql.append(" rwPre.salary02, ");
	hql.append(" rwPre.salary03, ");
	hql.append(" rwPre.klongchev, ");
	hql.append(" rwPre.yangchev, ");
	hql.append(" rwPre.spec, ");
	hql.append(" rwPre.childs, ");
	hql.append(" rwPre.ot, ");
	hql.append(" rwPre.premium, ");
	hql.append(" rwPre.health, ");
	hql.append(" rwPre.risk, ");
	hql.append(" rwPre.feetax, ");
	hql.append(" rwPre.step, ");
	hql.append(" rwPre.netIncome, ");
	hql.append(" rwPre.tax, ");
	hql.append(" rwPre.vinai1Pct, ");
	hql.append(" rwPre.vinai2, ");
	hql.append(" rwPre.refund, ");
	hql.append(" rwPre.pvf, ");
	hql.append(" rwPre.lawLoan, ");
	hql.append(" rwPre.hor, ");
	hql.append(" rwPre.car, ");
	hql.append(" rwPre.loanPost, ");
	hql.append(" rwPre.motor, ");
	hql.append(" rwPre.rick, ");
	hql.append(" rwPre.study, ");
	hql.append(" rwPre.pangLoan, ");
	hql.append(" rwPre.homegro, ");
	hql.append(" rwPre.homektb, ");
	hql.append(" rwPre.loan, ");
	hql.append(" rwPre.cpt, ");
	hql.append(" rwPre.sahakorn, ");
	hql.append(" rwPre.oomSin, ");
	hql.append(" rwPre.etc, ");
	hql.append(" rwPre.ktb, ");
	hql.append(" rwPre.kys, ");
	hql.append(" rwPre.kro, ");
	hql.append(" rwPre.netDec, ");
	hql.append(" rwPre.net, ");
	hql.append(" rwPre.orgCode ");
	//hql.append(" rwPre.morDay,rwPre.aftDay,rwPre.evnDay,rwPre.morHour,rwPre.aftHour,rwPre.evnHour,");
	//hql.append(" rwPre.flagPr,rwPre.yearWork,rwPre.periodWork");
	hql.append(" from VPrDailyNetAmt rwPre ");
	hql.append(" where rwPre.ouCode = '");
	hql.append(evaOuCode);
	hql.append("' ");
	//hql.append(" and rwPre.empCode ='914960' ");
	hql.append(" and rwPre.empCode in ( ");
	hql.append(" 	select pk.empCode ");
	hql.append(" 	from VPrEmployeeSecurityReport ");
	hql.append(" 	where pk.userId = '" + userId + "' ");
	hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
	hql.append(" 	and pk.year = " + evaYear);
	hql.append(" 	and pk.period = " + evaPeriod);
	hql.append(" ) ");
	hql.append(criteria);
	// hql.append(" and rwPre.creBy = '" + userId + "' ");
	hql.append(" order by nvl(rwPre.secCode,rwPre.divCode)||' '||rwPre.divDesc||' '||rwPre.areaDesc||' '||rwPre.secDesc,rwPre.empCode ");

	System.out.println("HQL findByCriteria ==> " + hql.toString());

	List empList = this.getSession().createQuery(hql.toString()).list();
	List retList = new ArrayList();

	for (Iterator it = empList.iterator(); it.hasNext();) {
		Object[] r = (Object[]) it.next();
		String empCode = (String) r[0];
		String empName = (String) r[1];
		String orgDesc = (String) r[2];
		String areaCode = (String) r[3];
		String areaDesc = (String) r[4];
		String secCode = (String) r[5];
		String secDesc = (String) r[6];
		Double newSalary = (Double) r[7];
		String bankId = (String) r[8];
		Double incAmt = (Double) r[9];
		Double taxAmt = (Double) r[10];
		Double salary = (Double) r[11];
		Double salary02 = (Double) r[12];
		Double salary03 = (Double) r[13];
		Double klongchev = (Double) r[14];
		Double yangchev = (Double) r[15];
		Double spec = (Double) r[16];
		
		Double childs = (Double) r[17];
		Double ot = (Double) r[18];
		Double premium = (Double) r[19];
		Double health = (Double) r[20];
		Double risk = (Double) r[21];
		Double feetax = (Double) r[22];
		Double step = (Double) r[23];
		
		Double netIncome = (Double) r[24];
		Double tax = (Double) r[25];
		Double vinai1Pct = (Double) r[26];
		
		Double vinai2 = (Double) r[27];
		Double refund = (Double) r[28];
		
		Double pvf = (Double) r[29];		
		
		Double lawLoan = (Double) r[30];
		
		Double hor = (Double) r[31];
		
		Double car = (Double) r[32];
		Double loanPost = (Double) r[33];
		Double motor = (Double) r[34];
		Double rick = (Double) r[35];
		Double study = (Double) r[36];
		Double pangLoan = (Double) r[37];
		Double homegro = (Double) r[38];
		Double homektb = (Double) r[39];
		Double loan = (Double) r[40];
		Double cpt = (Double) r[41];
		
		
		Double sahakorn = (Double) r[42];
		
		Double oomSin = (Double) r[43];
		
		Double etc = (Double) r[44];
		Double ktb = (Double) r[45];
		Double kys = (Double) r[46];
		Double kro = (Double) r[47];
		
		Double netDec = (Double) r[48];
		Double net = (Double) r[49];
		String orgCode = (String) r[50];
		
		PrDailyNetAmtRepVO ret = new PrDailyNetAmtRepVO();
		ret.setEmpCode(empCode);
		ret.setFullName(empName);
		ret.setOrgDesc(orgDesc);
		ret.setAreaCode(areaCode);
		ret.setAreaDesc(areaDesc);
		ret.setSecCode(secCode);
		ret.setSecDesc(secDesc);
		ret.setNewSalary(newSalary);
		ret.setBankId(bankId);
		ret.setIncAmt(incAmt);
		ret.setTaxAmt(taxAmt);
		ret.setSalary(salary);
		ret.setSalary02(salary02);
		ret.setSalary03(salary03);
		ret.setKlongchev(klongchev);
		ret.setYangchev(yangchev);
		ret.setChilds(childs);
		ret.setOt(ot);
		ret.setPremium(premium);
		ret.setHealth(health);
		ret.setRisk(risk);
		ret.setNetIncome(netIncome);
		ret.setTax(tax);
		ret.setVinai1Pct(vinai1Pct);
		
		ret.setVinai2(vinai2);
		ret.setRefund(refund);
		
		ret.setLawLoan(lawLoan);
		
		ret.setCar(car);
		ret.setLoanPost(loanPost);
		ret.setPangLoan(pangLoan);
		ret.setMotor(motor);
		ret.setRick(rick);
		ret.setStudy(study);
		ret.setOomSin(oomSin);
		ret.setCpt(cpt);
		ret.setEtc(etc);
		ret.setNetDec(netDec);
		ret.setNet(net);
		ret.setOrgCode(orgCode);
		
		ret.setSpec(spec);
		ret.setFeetax(feetax);
		ret.setStep(step);
		ret.setPvf(pvf);
		ret.setHor(hor);
		ret.setCar(car);
		ret.setHomegro(homegro);
		ret.setHomektb(homektb);
		ret.setLoan(loan);
		ret.setSahakorn(sahakorn);
		ret.setKys(kys);
		ret.setKtb(ktb);
		ret.setKro(kro);
		
		
		
		retList.add(ret);
	}
	return retList;
}


	
}
