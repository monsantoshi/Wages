package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.FeeWpayConfirmDataDAO;
import com.ss.tp.dto.FeeWpayConfirmDataVO;

import com.ss.tp.model.FeeWpayRwConfirmData;
import com.ss.tp.model.FeeWpayRwConfirmDataPK;

public class FeeWpayConfirmDataDAOImplLocal extends HibernateDaoSupport implements
		FeeWpayConfirmDataDAO, Serializable {
	public FeeWpayConfirmDataDAOImplLocal() {
		Locale.setDefault(Locale.US);
	}

	public void insertWgConfirmData(FeeWpayConfirmDataVO vo) throws Exception {
		FeeWpayRwConfirmData rw = new FeeWpayRwConfirmData();
		FeeWpayRwConfirmDataPK pk = new FeeWpayRwConfirmDataPK();
		
		try {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			BeanUtils.copyProperties(pk, vo);
			rw.setPk(pk);
			BeanUtils.copyProperties(rw, vo);
			rw.setCreDate(new Date());
			this.getHibernateTemplate().save(rw);
			this.addPrTextToPr(vo.getUserId(),vo.getOuCode(),vo.getYear().toString() ,vo.getPeriod().toString());
		} catch (Exception e) {
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
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
			return false;
		}
	}

	public boolean isConfirmWgData(String ouCode, String year, String period,
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
			return false;
		}
	}
	public List addPeriodInYear(String userId, String evaOuCode, String evaYear) {

		int intYear = Integer.parseInt(evaYear.trim());
		//int intMonth = Integer.parseInt(evaMonth.trim());

		try {
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");

			String plsql = "" + " begin " + "    FEE_ADD_PR_PERIOD(?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, evaOuCode);
			cs.setLong(2, intYear);
			cs.setString(3, userId);
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List addPnToPr(String userId, String evaOuCode, String evaYear,String evaMonth) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaMonth.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
			String plsql = "" + " begin " + "    BEGIN_COLECT_FEE_DATA(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, evaOuCode);
			cs.setLong(2, intYear);
			cs.setLong(3, intMonth);
			cs.setString(4, userId);
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List addPnToPrB(String userId, String evaOuCode, String evaYear,String evaMonth) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaMonth.trim());
		//intMonth = intMonth*2;

		try {
			final Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:posttest", "hrpost","HRPOST");

			String plsql = "" + " begin " + "    BEGIN_COLECT_FEE_DATA(?,?,?,?); " 
			            + " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			//cs.setString(1, userId);
			cs.setString(1, evaOuCode);
			cs.setLong(2, intYear);
			cs.setLong(3, intMonth);
			cs.setString(4, userId);
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public boolean isCreateWgPrData(String ouCode, String year,String userId) throws Exception {

		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = '1' ");
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '1' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count create wg pr : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return false;
		}
	}
	
	public boolean isInsertWgPnToPr(String ouCode, String year,String month,String userId) throws Exception {
		//int intMonth = Integer.parseInt(month.trim());
		int intMonth = Integer.parseInt(month.trim())*2;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from FeeWpayRwConfirmData ");
		hql.append(" where pk.ouCode = '" + ouCode + "' ");
		hql.append(" and pk.year = " + year);
		hql.append(" and pk.period = " + intMonth);
		hql.append(" and pk.userId = '" + userId + "' ");
		hql.append(" and pk.flag = '1' ");

		try {
			// Object obj =
			// this.getSession().createQuery(hql.toString()).uniqueResult();
			List ls = this.getHibernateTemplate().find(hql.toString());

			System.out.println("count insert wg pntopr : " + ls.size());

			if (ls != null && ls.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// if error default confrim
			return false;
		}
	}
	
	public void addPrTextToPr(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
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
	
	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
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
	
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {

		int intYear = Integer.parseInt(evaYear.trim());
		int intMonth = Integer.parseInt(evaPeriod.trim());

		try {
			//final Connection c = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@10.254.8.22:1521:post", "HRPOST",
			//		"HRPOST");
			final Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
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
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
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
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");
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
					"jdbc:oracle:thin:@localhost:1521:posttest", "hrpost",
					"HRPOST");

			String plsql = "" + " begin " + "    FEE_WPAY_AP_GENERATE_GL(?,?,?,?); "
					+ " end; ";

			CallableStatement cs = c.prepareCall(plsql);
			cs.setString(1, evaOuCode);
			cs.setLong(2, intYear);
			cs.setLong(3, intMonth);
		
			cs.setString(6, accDate);
			cs.execute();
			cs.close();
			c.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}