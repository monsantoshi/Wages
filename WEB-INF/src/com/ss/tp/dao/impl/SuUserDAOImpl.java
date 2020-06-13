package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.SuUserDAO;
import com.ss.tp.model.SuUser;

public class SuUserDAOImpl extends HibernateDaoSupport implements SuUserDAO,
		Serializable {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SuUser findUserId(String UserId) {

		SuUser emp = new SuUser();
		try {
			emp = (SuUser) getSession().load(SuUser.class, UserId);
		} catch (Exception e) {
		}
		return emp;
	}

	public SuUser checkPasswd(String UserId, String UserPasswd) {
		SuUser emp = null;

		List result = null;
		String query = "select su_util.Encrypt( '" + UserPasswd + "',"
				+ UserPasswd.length() + ") password from dual";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Integer visits;

		try {

			emp = (SuUser) getSession().load(SuUser.class, UserId);
			// System.out.println("emp"+emp.getUserId());
			result = jdbcTemplate.queryForList(query);
			Map m = (Map) result.get(0);

			if (!emp.getUserPassword().equals(m.get("password"))) {
				emp = null;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return emp;
	}

	public String updatePassword(String UserId, String UserPasswd) {

		List result = null;
		String query = "select su_util.Encrypt( '" + UserPasswd + "',"
				+ UserPasswd.length() + ") password from dual";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {

			// System.out.println("emp"+emp.getUserId());
			result = jdbcTemplate.queryForList(query);
			Map m = (Map) result.get(0);

			SuUser user = this.findUserId(UserId);
			user.setUpdBy(UserId);
			user.setUpdDate(new Date());
			if (user != null) {
				user.setUserPassword((String) m.get("password"));
				this.getHibernateTemplate().update(user);
				return "true";
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String lockUser(String UserId) {

		try {

			// System.out.println("emp"+emp.getUserId());

			SuUser user = this.findUserId(UserId);
			user.setUpdBy(UserId);
			user.setUpdDate(new Date());
			if (user != null) {
				user.setInactive("Y");
				this.getHibernateTemplate().update(user);
				return "true";
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
