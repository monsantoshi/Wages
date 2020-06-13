package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnSalaryDAO;

public class PnSalaryDAOImpl extends HibernateDaoSupport implements
		PnSalaryDAO, Serializable {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PnSalaryDAOImpl() {
		Locale.setDefault(Locale.US);
		// TODO Auto-generated constructor stub
	}

	public List findSalary(String ouCode) {
		StringBuffer hql = new StringBuffer(0);

		hql.append("select salary from Pn_salary where ou_code = '" + ouCode
				+ "'");
		System.out.println("FIND SALARY ::::::: " + hql.toString());
		List result = this.jdbcTemplate.queryForList(hql.toString());
		System.out.println("******************************** ::: "
				+ result.size());
		return result;
	}

}
