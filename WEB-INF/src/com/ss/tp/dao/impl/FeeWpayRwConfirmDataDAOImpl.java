package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.ss.tp.dao.RwConfirmDataDAO;
import com.ss.tp.dao.FeeWpayRwConfirmDataDAO;
import com.ss.tp.dto.FeeWpayPrEmployeeVO;
import com.ss.tp.dto.FeeWpayPrPeriodLineVO;
import com.ss.tp.dto.FeeWpayRwConfirmDataVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.VSuMasterUserVO;
//import com.ss.tp.model.RwConfirmData;
//import com.ss.tp.model.RwConfirmDataPK;
import com.ss.tp.model.FeeWpayPrEmployee;
import com.ss.tp.model.FeeWpayPrPeriodLine;
import com.ss.tp.model.FeeWpayRwConfirmData;
import com.ss.tp.model.FeeWpayRwConfirmDataPK;
import com.ss.tp.model.VSuMasterUser;

public class FeeWpayRwConfirmDataDAOImpl extends HibernateDaoSupport implements
		FeeWpayRwConfirmDataDAO, Serializable {
	public FeeWpayRwConfirmDataDAOImpl() {
		Locale.setDefault(Locale.US);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void insertRwConfirmData01(FeeWpayRwConfirmDataVO vo) throws Exception {
		FeeWpayRwConfirmData rw = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK pk = new FeeWpayRwConfirmDataPK();
		try {
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
			//this.addPrTextToPr(vo.getUserId(),vo.getOuCode(),vo.getYear().toString() ,vo.getPeriod().toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void insertRwConfirmData(FeeWpayRwConfirmDataVO vo) throws Exception {
		FeeWpayRwConfirmData rw = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK pk = new FeeWpayRwConfirmDataPK();
		try {
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
			//this.addPrTextToPr(vo.getUserId(),vo.getOuCode(),vo.getYear().toString() ,vo.getPeriod().toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '2' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm master : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
	}

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '3' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm rw : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	public boolean isConfirmVinai2(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '4' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm vinai2 : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	
	public boolean isConfirmMasterData(String ouCode, String year,
			String period) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		//hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '2' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm master : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
	}

	public boolean isConfirmRwData(String ouCode, String year, String period) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		//hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '3' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm rw : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	public boolean isConfirmVinai2(String ouCode, String year, String period) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + period);
		//hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '4' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm vinai2 : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	
	
	
	
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WPAY_RW_CAL_TRANS(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void confirmTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WPAY_PROC_CAL_BANK(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void closeTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WPAY_PROC_TAX_CAL(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public List addFeeWpayDailyToGl(String userId, String evaOuCode, String evaYear,
			String evaMonth, String accDate) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaMonth.trim());

		try {
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");

			String plsql = "" + " begin " + "    FEE_WPAY_AP_GENERATE_GL(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, evaOuCode);
			cs.setLong(2, intYear);
			cs.setLong(3, intMonth);
		
			cs.setString(4, accDate);
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());
		System.out.print("confirmMaster");

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WPAY_PROC_COLLECT_SALARY(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void insertRwConfirmDataApMt012(FeeWpayRwConfirmDataVO vo) throws Exception {
		FeeWpayRwConfirmData rw = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK pk = new FeeWpayRwConfirmDataPK();
		try {
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
			//this.addPrTextToPr(vo.getUserId(),vo.getOuCode(),vo.getYear().toString() ,vo.getPeriod().toString());
			//this.confirmMaster(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
			//this.transferTrans(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void insertRwConfirmDataPcMt001(FeeWpayRwConfirmDataVO vo) throws Exception {
		FeeWpayRwConfirmData rw = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK pk = new FeeWpayRwConfirmDataPK();
		try {
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
			this.addPrTextToPr(vo.getUserId(),vo.getOuCode(),vo.getYear().toString() ,vo.getPeriod().toString());
			this.confirmMaster(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
			this.transferTrans(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
			this.confirmTrans(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
			this.closeTrans(vo.getUserId(), vo.getOuCode(), vo.getYear().toString(), vo.getPeriod().toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findByCriteriaUserApprove(String userId,  Long evaYear,
			Long evaPeriod,	String evaUserFrom, String evaUserTo, String evaFlagFrom, int count, int countRecord) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.pk.period = ");
			criteria.append(evaPeriod);
		}

	

		if (evaUserFrom != null && !evaUserFrom.equals("")) {
			criteria.append(" and rw.pk.userId >= '");
			criteria.append(evaUserFrom);
			criteria.append("' ");
		}

		if (evaUserTo != null && !evaUserTo.equals("")) {
			criteria.append(" and rw.pk.userId <= '");
			criteria.append(evaUserTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and rw.flag3 = decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','0',0,1) ");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rw.pk.userId,su.userName,su.department,su.telNo,to_char(rw.flag3) ");
		hql.append(" from VSuMasterUser rw,SuUser su ");
		hql.append(" where rw.pk.masterUser = '" + userId + "' ");
		hql.append(criteria);
		hql.append(" and rw.pk.userId = su.userId ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" order by rw.pk.userId ");

		System.out.println("HQL findByCriteriaUserApprove ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count).setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String userIds = (String) r[0];
			String userName = (String) r[1];
			String department = (String) r[2];
			Double telNo = (Double) r[3];
			String flag3 = (String) r[4];
			//String positionShort = (String) r[4];

			VSuMasterUserVO ret = new VSuMasterUserVO();
			ret.setUserId(userIds);
			ret.setUserName(userName);
			ret.setDepartment(department);
			ret.setTelNo(telNo);
			ret.setFlag3(flag3);
			
			
			//ret.setPositionShort(positionShort);

			retList.add(ret);
		}
		return retList;
	}

	public Integer countDataUserApprove(String userId, Long evaYear,
			Long evaPeriod,String evaUserFrom, String evaUserTo, String evaFlagFrom	) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.pk.period = ");
			criteria.append(evaPeriod);
		}

	

		if (evaUserFrom != null && !evaUserFrom.equals("")) {
			criteria.append(" and rw.pk.userId >= '");
			criteria.append(evaUserFrom);
			criteria.append("' ");
		}

		if (evaUserTo != null && !evaUserTo.equals("")) {
			criteria.append(" and rw.pk.userId <= '");
			criteria.append(evaUserTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and rw.flag3 = decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','0',0,1) ");
		}

	

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rw.pk.userId) ");
		hql.append(" from VSuMasterUser rw,SuUser su ");
		hql.append(" where rw.pk.masterUser = '" + userId + "' ");
		hql.append(criteria);
		hql.append(" and rw.pk.userId = su.userId ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" order by rw.pk.userId ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public List findByCriteriaUserConfirm(String userId,  Long evaYear,
			Long evaPeriod, 
			String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3, int count, int countRecord) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.pk.period = ");
			criteria.append(evaPeriod);
		}

	

		if (evaUserFrom != null && !evaUserFrom.equals("")) {
			criteria.append(" and rw.pk.userId >= '");
			criteria.append(evaUserFrom);
			criteria.append("' ");
		}

		if (evaUserTo != null && !evaUserTo.equals("")) {
			criteria.append(" and rw.pk.userId <= '");
			criteria.append(evaUserTo);
			criteria.append("' ");
		}
		
		if (evaZipFrom != null && !evaZipFrom.equals("")) {
			criteria.append(" and rw.zipCode >= '");
			criteria.append(evaZipFrom);
			criteria.append("' ");
		}

		if (evaZipTo != null && !evaZipTo.equals("")) {
			criteria.append(" and rw.zipCode <= '");
			criteria.append(evaZipTo);
			criteria.append("' ");
		}

		if (evaFlag2 != null && !evaFlag2.equals("")) {
			criteria.append(" and rw.flag2 = '");
			criteria.append(evaFlag2);
			criteria.append("' ");
		}

		if (evaFlag3 != null && !evaFlag3.equals("")) {
			criteria.append(" and rw.flag3 = '");
			criteria.append(evaFlag3);
			criteria.append("' ");
			
		}
		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rw.pk.userId,su.userName,su.department,su.zipCode,to_char(rw.flag2),to_char(rw.flag3)  ");
		hql.append(" from VSuMasterUser rw,SuUser su ");
		hql.append(" where rw.pk.masterUser = '" + userId + "' ");
		hql.append(criteria);
		hql.append(" and rw.pk.userId = su.userId ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" order by su.zipCode,rw.pk.userId ");

		System.out.println("HQL findByCriteriaUserApprove ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count).setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String userIds = (String) r[0];
			String userName = (String) r[1];
			String department = (String) r[2];
			String zipCode = (String) r[3];
			String flag2 = (String) r[4];
			String flag3 = (String) r[5];
			//String positionShort = (String) r[4];

			VSuMasterUserVO ret = new VSuMasterUserVO();
			ret.setUserId(userIds);
			ret.setUserName(userName);
			ret.setDepartment(department);
			ret.setZipCode(zipCode);
			ret.setFlag2(flag2);
			ret.setFlag3(flag3);
			
			
			
			
			//ret.setPositionShort(positionShort);

			retList.add(ret);
		}
		return retList;
	}

	public Integer countDataUserConfirm(String userId, Long evaYear,
			Long evaPeriod,String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.pk.year = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.pk.period = ");
			criteria.append(evaPeriod);
		}

	

		if (evaUserFrom != null && !evaUserFrom.equals("")) {
			criteria.append(" and rw.pk.userId >= '");
			criteria.append(evaUserFrom);
			criteria.append("' ");
		}

		if (evaUserTo != null && !evaUserTo.equals("")) {
			criteria.append(" and rw.pk.userId <= '");
			criteria.append(evaUserTo);
			criteria.append("' ");
		}
		
		if (evaZipFrom != null && !evaZipFrom.equals("")) {
			criteria.append(" and rw.zipCode >= '");
			criteria.append(evaZipFrom);
			criteria.append("' ");
		}

		if (evaZipTo != null && !evaZipTo.equals("")) {
			criteria.append(" and rw.zipCode <= '");
			criteria.append(evaZipTo);
			criteria.append("' ");
		}

		if (evaFlag2 != null && !evaFlag2.equals("")) {
			criteria.append(" and  rw.flag2 = '");
			criteria.append(evaFlag2);
			criteria.append("' ");
		}

		if (evaFlag3 != null && !evaFlag3.equals("")) {
			criteria.append(" and rw.flag3 = '");
			criteria.append(evaFlag3);
			criteria.append("' ");
			
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rw.pk.userId) ");
		hql.append(" from VSuMasterUser rw,SuUser su ");
		hql.append(" where rw.pk.masterUser = '" + userId + "' ");
		hql.append(criteria);
		hql.append(" and rw.pk.userId = su.userId ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" order by su.zipCode,rw.pk.userId ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	
	public List findByCriteriaUserByMaster(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,to_char(l.flag1),to_char(l.flag2),to_char(l.flag3),to_char(l.flag4)");
		sql.append(" FROM VSuMasterUser l ");
		sql.append(" WHERE l.pk.masterUser = '" + userId + "' ");
		sql.append(" AND l.pk.year = '" + evaYear + "' ");
		sql.append(" AND l.pk.period = '" + evaPeriod + "' ");
		sql.append(" GROUP BY l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,l.flag1,l.flag2,l.flag3,l.flag4");
		sql.append(" ORDER BY  l.pk.userId");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double year = (Double) r[0];
			Double period = (Double) r[1];
			String userIds = (String) r[2];
			String masterUser = (String) r[3];
			String flag1 = (String) r[4];
			String flag2 = (String) r[5];
			String flag3 = (String) r[6];
			String flag4 = (String) r[7];
			
			

			VSuMasterUserVO ret = new VSuMasterUserVO();
			ret.setYear(year);
			ret.setPeriod(period);
			ret.setMasterUser(masterUser);
			ret.setFlag1(flag1);
			ret.setFlag2(flag2);
			ret.setFlag3(flag3);
			ret.setFlag4(flag4);
			ret.setUserId(userIds);

			retList.add(ret);
		}
		return retList;
	}
	
	public List findByCriteriaZipCodeByMaster(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,l.zipCode,to_char(l.flag1),to_char(l.flag2),to_char(l.flag3),to_char(l.flag4)");
		sql.append(" FROM VSuMasterUser l ");
		sql.append(" WHERE l.pk.masterUser = '" + userId + "' ");
		sql.append(" AND l.pk.year = '" + evaYear + "' ");
		sql.append(" AND l.pk.period = '" + evaPeriod + "' ");
		sql.append(" GROUP BY l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,l.zipCode,l.flag1,l.flag2,l.flag3,l.flag4");
		sql.append(" ORDER BY  l.pk.userId");

	
		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double year = (Double) r[0];
			Double period = (Double) r[1];
			String userIds = (String) r[2];
			String masterUser = (String) r[3];
			String zipCode	= (String) r[4];
			String flag1 = (String) r[5];
			String flag2 = (String) r[6];
			String flag3 = (String) r[7];
			String flag4 = (String) r[8];
			
			

			VSuMasterUserVO ret = new VSuMasterUserVO();
			ret.setYear(year);
			ret.setPeriod(period);
			ret.setMasterUser(masterUser);
			ret.setZipCode(zipCode);
			ret.setFlag1(flag1);
			ret.setFlag2(flag2);
			ret.setFlag3(flag3);
			ret.setFlag4(flag4);
			ret.setUserId(userIds);
		

			retList.add(ret);
		}
		return retList;
	}
	
	public List findByCriteriaFlagByUser(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,to_char(l.flag1),to_char(l.flag2),to_char(l.flag3),to_char(l.flag4)");
		sql.append(" FROM VSuMasterUser l ");
		sql.append(" WHERE l.pk.userId = '" + userId + "' ");
		sql.append(" AND l.pk.year = '" + evaYear + "' ");
		sql.append(" AND l.pk.period = '" + evaPeriod + "' ");
		sql.append(" GROUP BY l.pk.year,l.pk.period,l.pk.userId,l.pk.masterUser,l.flag1,l.flag2,l.flag3,l.flag4");
		sql.append(" ORDER BY  l.pk.userId");

		List yearList = this.getSession().createQuery(sql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = yearList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			Double year = (Double) r[0];
			Double period = (Double) r[1];
			String userIds = (String) r[2];
			String masterUser = (String) r[3];
			Integer flag1 = (Integer) r[4];
			Integer flag2 = (Integer) r[5];
			Integer flag3 = (Integer) r[6];
			Integer flag4 = (Integer) r[7];
			
			

			VSuMasterUserVO ret = new VSuMasterUserVO();
			ret.setYear(year);
			ret.setPeriod(period);
			ret.setMasterUser(masterUser);
			ret.setFlag1(String.valueOf(flag1));
			ret.setFlag2(String.valueOf(flag2));
			ret.setFlag3(String.valueOf(flag3));
			ret.setFlag4(String.valueOf(flag4));
			ret.setUserId(userIds);

			retList.add(ret);
		}
		return retList;
	}

	public VSuMasterUser findConfirmMaster(String ouCode, String year,
			String period, String userId) throws Exception {

		StringBuffer sql = new StringBuffer(0);
		// sql.append(" SELECT l.periodName,l.pk.period");
		sql.append(" FROM VSuMasterUser l ");
		sql.append(" WHERE l.pk.year = '" + year + "' ");
		sql.append(" AND l.pk.userId = '" + userId + "' ");
		sql.append(" AND l.pk.period	= '" + period + "' ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			return (VSuMasterUser) ls.get(0);
		} else
			return null;
	}
	public void addPrTextToPr(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    UPDATE_WPAY_PR_TEXT_TO_PR(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void updateMasterFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_PR_PERIOD_LINE pr ");
			hql.append(" SET pr.main_close = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteFlagConfirm(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Fee_Wpay_Rw_Confirm_Data rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = '" + year + "' ");
			sql.append(" and rw.period_pr = '" + period + "' ");
			sql.append(" and rw.flag = 2 ");
	        sql.append(" and rw.user_id = '" + userId + "' ");
		

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void deleteFlagApprove(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Fee_Wpay_Rw_Confirm_Data rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = '" + year + "' ");
			sql.append(" and rw.period_pr = '" + period + "' ");
			sql.append(" and rw.flag = 3 ");
	        sql.append(" and rw.user_id = '" + userId + "' ");
		

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void deletePrDailyApprove(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Fee_Wpay_Pr_Daily rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = '" + year + "' ");
			sql.append(" and rw.period_pr = '" + period + "' ");
			sql.append(" and rw.cre_by = '" + userId + "' ");
		

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List updateConfirmMaster(String userId, String ouCode,
			String yearPr, String periodPr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_PR_EMPLOYEE_TEXT pr ");
			hql.append(" SET pr.confirm_flag = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List updateConfirmDetail(String userId, String ouCode,
			String yearPr, String periodPr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void updateConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_RW_INC_DEC_OTHER pr ");
			hql.append(" SET pr.confirm_flag = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
			this.updateConfirmRwVinai(ouCode, year, period, userId);
			this.updateConfirmRwVinai2(ouCode, year, period, userId);
		    

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConfirmRwVinai(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_RW_VINAI pr ");
			hql.append(" SET pr.confirm_flag = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
		    

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConfirmRwVinai2(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_RW_VINAI2 pr ");
			hql.append(" SET pr.confirm_flag = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
		    

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTranFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();
	

	

		try {hql.append(" UPDATE FEE_WPAY_PR_PERIOD_LINE pr ");
			hql.append(" SET pr.tran_close = 'N', ");
			hql.append(" where pr.ou_code = '" + ouCode + "' ");
			hql.append(" and pr.year_pr = '" + year + "' ");
			hql.append(" and pr.period_pr = '" + period + "' ");
			hql.append(" and pr.cre_by = '" + userId + "' ");
			
		
	
			this.jdbcTemplate.execute(hql.toString());
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List updateConfirmApprove(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void convertApproveByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WP_CONVERT_APPROVE(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void convertConfirmRwDataByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WP_CONVERT_RW_DATA(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	public void convertConfirmPrTxtByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WP_CONVERT_PR_TXT(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, evaOuCode);
			cs.setLong(3, intYear);
			cs.setLong(4, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	public void insertEmpToPrBreakPay(String userId,String empId, String evaOuCode, String evaYear,String evaPeriod) throws Exception {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
					"HRPOSTV1");
			String plsql = "" + " begin " + "    FEE_WPAY_INT_PR_BREAK_PAY(?,?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, userId);
			cs.setString(2, empId);
			cs.setString(3, evaOuCode);
			cs.setLong(4, intYear);
			cs.setLong(5, intMonth);
		
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public boolean isConfirmInComeOutCome(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from SuUser su,VFeeWpayUserSFlag v ");
		hql.append(" where  v.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and v.pk.year = " + year);
		hql.append(" and v.pk.period = " + period);
		hql.append(" and v.sumFlag < 6 ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" and su.userId = v.pk.userId ");
		hql.append(" and su.inactive = 'N' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm InComeOutCome : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	public boolean isConfirmApprove(String ouCode, String year, String period,
			String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from SuUser su,VFeeWpayUserSFlag v ");
		hql.append(" where  v.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and v.pk.year = " + year);
		hql.append(" and v.pk.period = " + period);
		hql.append(" and v.sumFlag < 4 ");
		hql.append(" and su.masterUser = '" + userId + "' ");
		hql.append(" and su.userId = v.pk.userId ");
		hql.append(" and su.inactive = 'N' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm Approve: " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}
	
	
	public boolean isAddWorkDays(String ouCode, String year, String period,String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from WgEmployee ");
		hql.append(" where wgEmployeePK.ouCode = '" + ouCode + "' ");
		hql.append(" and wgEmployeePK.empCode not in (select feeWpayMonthEmpWorkPK.empCode from FeeWpayMonthEmpWork ");
		hql.append(" where feeWpayMonthEmpWorkPK.workYear = " + year );
		hql.append ("and feeWpayMonthEmpWorkPK.workMonth*2 = " + period );
					hql.append (")");
		hql.append(" and empStatus = 'B' ");
		hql.append ("and codeSeqAct in (select pk.codeSeq from SuUserOrganization ");
					hql.append("where pk.userId = '" + userId + "' ");
					hql.append (")");
		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count confirm days : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return true;
		}
		
		
	}

}