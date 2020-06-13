package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.FeeWpayPrMonthlyLineDAO;
import com.ss.tp.model.FeeWpayPrMonthlyLineComparator;

public class FeeWpayPrMonthlyLineDAOImpl extends HibernateDaoSupport implements
		FeeWpayPrMonthlyLineDAO, Serializable {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List findPaySlip(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception {
		try {
			StringBuffer sql = new StringBuffer();

			// select prm.group_code , prm.inc_dec_code , pr.inc_dec_name ,
			// sum(prm.tot_amt)
			// from pr_monthly_line prm , pr_income_deduct pr
			// where prm.ou_code = '001'
			// and prm.year = 2549
			// and prm.period = 6
			// and prm.emp_code = '001274'
			// and prm.group_code = '2'
			// and pr.ou_code = '001'
			// and pr.group_code = '2'
			// and pr.inc_dec_code = prm.inc_dec_code
			// group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name

			sql.append(" select prm.group_code as groupCode , ");
			sql.append(" prm.inc_dec_code as incDecCode , ");
			sql.append(" pr.inc_dec_name as incDecName , ");
			sql.append(" sum(prm.tot_amt) as totAmt ");
			sql.append(" from fee_wpay_pr_monthly_line prm , ");
			sql.append(" fee_wpay_pr_income_deduct pr ");
			sql.append(" where prm.ou_code = '");
			sql.append(ouCode);
			sql.append("' ");
			sql.append(" and prm.year = ");
			sql.append(Double.valueOf(year));
			sql.append(" and prm.period = ");
			sql.append(Double.valueOf(period));
			sql.append(" and prm.emp_code = '");
			sql.append(empCode);
			sql.append("' ");
			sql.append(" and prm.group_code = '");
			sql.append(groupCode);
			sql.append("' ");
			sql.append(" and pr.ou_code = '");
			sql.append(ouCode);
			sql.append("' ");
			sql.append(" and pr.group_code = '");
			sql.append(groupCode);
			sql.append("' ");

			if (groupCode.equals("2"))
				sql.append(" and pr.inc_dec_code <> '24' ");

			sql.append(" and pr.inc_dec_code = prm.inc_dec_code ");
			sql.append(" group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name ");

			System.out.println("Sql findPaySlip : " + sql.toString());

			List ret = this.jdbcTemplate.queryForList(sql.toString());

			if (groupCode.equals("2")) {
				sql.setLength(0);
				sql.append(" select prm.group_code as groupCode , ");
				sql.append(" prm.inc_dec_code as incDecCode , ");
				sql.append(" pr.inc_dec_name as incDecName , ");
				sql.append(" sum(prm.tot_amt) as totAmt ");
				sql.append(" from fee_wpay_pr_monthly_line prm , ");
				sql.append(" fee_wpay_pr_income_deduct pr ");
				sql.append(" where prm.ou_code = '");
				sql.append(ouCode);
				sql.append("' ");
				sql.append(" and prm.year = ");
				sql.append(Double.valueOf(year));
				sql.append(" and prm.period = ");
				sql.append(Double.valueOf(period));
				sql.append(" and prm.emp_code = '");
				sql.append(empCode);
				sql.append("' ");
				sql.append(" and prm.group_code = '2' ");
				sql.append(" and pr.ou_code = '");
				sql.append(ouCode);
				sql.append("' ");
				sql.append(" and pr.inc_dec_code = '24' ");
				sql.append(" and prm.sub_code = 'COLLECTR' ");
				sql.append(" and pr.group_code = '2' ");
				sql.append(" and pr.inc_dec_code = prm.inc_dec_code ");
				sql.append(" group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name ");

				List tmp = this.jdbcTemplate.queryForList(sql.toString());

				ret.addAll(tmp);
			}

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {

					ListOrderedMap rt = (ListOrderedMap) ret.get(i);
					FeeWpayPrMonthlyLineComparator obj = new FeeWpayPrMonthlyLineComparator();
					obj.setIncDecCode((String) rt.getValue(1));
					obj.setIncDecName((rt.getValue(2) != null) ? (String) rt
							.getValue(2) : "");
					obj.setTotAmt(new Double(
							((BigDecimal) ((rt.getValue(3) != null) ? rt
									.getValue(3) : "0")).doubleValue()));
					ret.set(i, obj);
				}

				Collections.sort(ret);
			}

			if (ret == null) {
				return null;
			} else {
				return ret;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	
	public List findPaySlipNew(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception {
		try {
			StringBuffer sql = new StringBuffer();

			// select prm.group_code , prm.inc_dec_code , pr.inc_dec_name ,
			// sum(prm.tot_amt)
			// from pr_monthly_line prm , pr_income_deduct pr
			// where prm.ou_code = '001'
			// and prm.year = 2549
			// and prm.period = 6
			// and prm.emp_code = '001274'
			// and prm.group_code = '2'
			// and pr.ou_code = '001'
			// and pr.group_code = '2'
			// and pr.inc_dec_code = prm.inc_dec_code
			// group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name

			sql.append(" select prm.group_code as groupCode , ");
			sql.append(" prm.inc_dec_code as incDecCode , ");
			sql.append(" pr.inc_dec_name as incDecName , ");
			sql.append(" sum(prm.amount) as totAmt ");
			sql.append(" from v_fee_wpay_wages_slip prm , ");
			sql.append(" fee_wpay_pr_income_deduct pr ");
			sql.append(" where prm.ou_code = '");
			sql.append(ouCode);
			sql.append("' ");
			sql.append(" and prm.year = '");
			sql.append(year);
			sql.append("' ");
			sql.append(" and prm.period =  '");
			sql.append(period);
			sql.append("' ");
			sql.append(" and prm.emp_code = '");
			sql.append(empCode);
			sql.append("' ");
			sql.append(" and prm.group_code = '");
			sql.append(groupCode);
			sql.append("' ");
			sql.append(" and pr.ou_code = '");
			sql.append(ouCode);
			sql.append("' ");
			sql.append(" and pr.group_code = '");
			sql.append(groupCode);
			sql.append("' ");
			sql.append(" and prm.group_code = '1' ");

			//if (groupCode.equals("2"))
				//sql.append(" and pr.inc_dec_code <> '24' ");

			sql.append(" and pr.inc_dec_code = prm.inc_dec_code ");
			sql.append(" group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name ");

			System.out.println("Sql findPaySlipNew : " + sql.toString());

			List ret = this.jdbcTemplate.queryForList(sql.toString());

			if (groupCode.equals("2")) {
				sql.setLength(0);
				sql.append(" select prm.group_code as groupCode , ");
				sql.append(" prm.inc_dec_code as incDecCode , ");
				sql.append(" pr.inc_dec_name as incDecName , ");
				sql.append(" sum(prm.amount) as totAmt ");
				sql.append(" from v_fee_wpay_wages_slip prm , ");
				sql.append(" fee_wpay_pr_income_deduct pr ");
				sql.append(" where prm.ou_code = '");
				sql.append(ouCode);
				sql.append("' ");
				sql.append(" and prm.year =  '");
				sql.append(year);
				sql.append("' ");
				sql.append(" and prm.period =  '");
				sql.append(period);
				sql.append("' ");
				sql.append(" and prm.emp_code = '");
				sql.append(empCode);
				sql.append("' ");
				sql.append(" and prm.group_code = '2' ");
				sql.append(" and pr.ou_code = '");
				sql.append(ouCode);
				sql.append("' ");
				sql.append(" and pr.ou_code = prm.ou_code ");
				sql.append(" and pr.group_code = prm.group_code ");
				sql.append(" and pr.group_code = '2' ");
				sql.append(" and pr.inc_dec_code = prm.inc_dec_code ");
				sql.append(" group by prm.group_code , prm.inc_dec_code , pr.inc_dec_name ");

				List tmp = this.jdbcTemplate.queryForList(sql.toString());

				ret.addAll(tmp);
			}

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {

					ListOrderedMap rt = (ListOrderedMap) ret.get(i);
					FeeWpayPrMonthlyLineComparator obj = new FeeWpayPrMonthlyLineComparator();
					obj.setIncDecCode((String) rt.getValue(1));
					obj.setIncDecName((rt.getValue(2) != null) ? (String) rt
							.getValue(2) : "");
					obj.setTotAmt(new Double(
							((BigDecimal) ((rt.getValue(3) != null) ? rt
									.getValue(3) : "0")).doubleValue()));
					ret.set(i, obj);
				}

				Collections.sort(ret);
			}

			if (ret == null) {
				return null;
			} else {
				return ret;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List findIncomeAndTaxPrAccumulate(String ouCode, String year,
			String period, String empCode) throws Exception {
		try {
			StringBuffer sql = new StringBuffer();

			// select sum(prAcc.income_amt) , sum(prAcc.tax_amt)
			// from pr_accumulate prAcc
			// where prAcc.ou_code = '001'
			// and prAcc.year = 2549
			// and prAcc.period <= 6
			// and prAcc.emp_code = '001274'

			sql.append(" select ( prAcc.income_fix + income_valiable ) as incomeAmt , ");
			sql.append(" (	prAcc.tax_fix + pracc.TAX_VALIABLE ) as taxAmt ");
			sql.append(" from fee_wpay_pr_accumulate prAcc ");
			sql.append(" where prAcc.ou_code = '");
			sql.append(ouCode);
			sql.append("' ");
			sql.append(" and prAcc.year = ");
			sql.append(Double.valueOf(year));
			sql.append(" and prAcc.period = ");
			sql.append(Double.valueOf(period));
			sql.append(" and prAcc.emp_code = '");
			sql.append(empCode);
			sql.append("' ");

			System.out.println("Sql findIncomeAndTaxPrAccumulate : "
					+ sql.toString());

			List ret = this.jdbcTemplate.queryForList(sql.toString());

			if (ret == null) {
				return null;
			} else {
				return ret;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
