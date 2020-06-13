package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeWgEmpScoreDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeWgEmpScoreDetailVO;
import com.ss.tp.dto.PeWgEmpScoreVO;
import com.ss.tp.model.PeWgEmpScore;
import com.ss.tp.model.PeWgEmpScoreDetail;
import com.ss.tp.model.PeWgEmpScorePK;
import com.ss.tp.model.WgEmpDataStatus;

public class PeWgEmpScoreDAOImpl extends HibernateDaoSupport implements
		PeWgEmpScoreDAO, Serializable {
	List peList = new ArrayList();
	private DataSource dataSource;

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public PeWgEmpScoreVO findByKey(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		PeWgEmpScorePK pk = new PeWgEmpScorePK();
		pk.setOuCode(ouCode);
		pk.setEvaYear(evaYear);
		pk.setEvaTime(evaTime);
		pk.setEmpCode(empCode);
		PeWgEmpScore ec = (PeWgEmpScore) getSession().load(PeWgEmpScore.class,
				pk);
		PeWgEmpScoreVO ret = new PeWgEmpScoreVO();
		try {
			BeanUtils.copyProperties(ret, ec.getPk());
			BeanUtils.copyProperties(ret, ec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord) {

		String query = new String();
		query = " select a.pk.empCode,a.evaTotal from PeWgEmpScore a,WgEmpDataStatus b,PnOrganization c "
				+ " where a.pk.ouCode  = '"
				+ ouCode
				+ "' "
				+ " and   a.pk.evaYear = "
				+ evaYear
				+ " "
				+ " and   a.pk.evaTime = "
				+ evaTime
				+ " "
				+ " and   b.codeSeq in (select o.pk.codeSeq"
				+ " from VPnOrganizationSecurity o "
				+ " where o.pk.ouCode = '"
				+ ouCode + "' " + " and   o.pk.userId = '" + userId + "' ) ";
		if (!areaCode.equals("")) {
			query = query + " and c.areaCode ='" + areaCode + "' ";
		}
		if (!secCode.equals("")) {
			query = query + " and c.secCode ='" + secCode + "' ";
		}
		if (!workCode.equals("")) {
			query = query + " and c.workCode ='" + workCode + "' ";
		}
		query = query + " and a.pk.ouCode  = b.pk.ouCode"
				+ " and a.pk.empCode = b.pk.empCode"
				+ " and a.statusDate = b.statusDate"
				+ " and b.pk.ouCode  = c.pk.ouCode"
				+ " and b.codeSeq    = c.pk.codeSeq"
				+ " order by b.codeSeq,a.pk.empCode";

		Query q = this.getSession().createQuery(query);
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();
		Object arrObj[] = null;
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			arrObj = (Object[]) iter.next();
			PeWgEmpScorePK emppk = new PeWgEmpScorePK();
			emppk.setEmpCode((String) arrObj[0]);
			emppk.setEvaTime(evaTime);
			emppk.setEvaYear(evaYear);
			emppk.setOuCode(ouCode);
			PeWgEmpScore emp = (PeWgEmpScore) getSession().load(
					PeWgEmpScore.class, emppk);
			PeWgEmpScoreVO ret = new PeWgEmpScoreVO();
			try {
				BeanUtils.copyProperties(ret, emp.getPk());
				BeanUtils.copyProperties(ret, emp);
				List empList2 = new ArrayList();
				java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat(
						"dd/MM/yyyy", new java.util.Locale("US"));
				String statusDate = fmd.format(emp.getStatusDate());
				String hql = "from WgEmpDataStatus d " + "where d.pk.ouCode ='"
						+ ouCode + "' " + "and d.pk.empCode ='"
						+ emp.getPk().getEmpCode() + "' "
						+ "and to_char(d.statusDate,'DD/MM/RRRR') = '"
						+ statusDate + "' " + "order by d.pk.empCode";
				empList2 = this.getHibernateTemplate().find(hql);
				for (Iterator iter2 = empList2.iterator(); iter2.hasNext();) {
					WgEmpDataStatus emp2 = (WgEmpDataStatus) iter2.next();
					try {
						ret.setEname(emp2.getRefDbPreSuff().getPrefixName()
								+ " " + emp2.getFirstName() + " "
								+ emp2.getLastName());
						ret.setCodeSeq(new Integer(emp2.getCodeSeq() + ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// ret.setEname(emp.getEmp().getRefDbPreSuff().getPrefixName()+"
				// "+emp.getEmp().getFirstName()+"
				// "+emp.getEmp().getLastName());
				// ret.setCodeSeq(emp.getEmp().getCodeSeq());
				/*
				 * StringBuffer hql = new StringBuffer(0); hql.append(" select
				 * p.pk.codeSeq "); hql.append(" from PeWgControl p");
				 * hql.append(" where p.pk.ouCode ='"+ouCode+"' "); hql.append("
				 * and p.pk.evaYear ="+evaYear+" "); hql.append(" and
				 * p.pk.evaTime ="+evaTime+" "); hql.append(" and p.pk.codeSeq
				 * =" +emp.getEmp().getCodeSeq()+" "); hql.append(" and
				 * p.flagClose ='Y' )");
				 * 
				 * List pecontrolList = this.getHibernateTemplate().find(
				 * hql.toString() ); if (pecontrolList ==
				 * null||pecontrolList.size()==0){ ret.setConfirm("N"); } else{
				 * ret.setConfirm("Y"); }
				 */
				String hql2 = " select distinct e.pk.formCode from PeWgEmpScoreDetail e "
						+ " where e.pk.ouCode = '"
						+ ouCode
						+ "' "
						+ " and   e.pk.evaYear = "
						+ evaYear
						+ " and   e.pk.evaTime = "
						+ evaTime
						+ " and   e.pk.empCode = '" + ret.getEmpCode() + "' ";
				List form = this.getHibernateTemplate().find(hql2);
				if (form == null || form.size() == 0) {
					ret.setFormScore(new Double(0));
				} else {
					String hql3 = " select sum(e.titleScore)  from PeEvaluationFormTitle e "
							+ " where e.pk.ouCode = '"
							+ ouCode
							+ "' "
							+ " and   e.pk.formCode = '"
							+ (String) form.get(0)
							+ "' ";
					List formScore = this.getHibernateTemplate().find(hql3);

					ret.setFormScore((Double) formScore.get(0));
				}
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	public void insertPeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {

		PeWgEmpScore emp = new PeWgEmpScore();
		PeWgEmpScorePK emppk = new PeWgEmpScorePK();
		try {
			BeanUtils.copyProperties(emppk, empscorevo);
			emp.setPk(emppk);
			BeanUtils.copyProperties(emp, empscorevo);
			emp.setCreDate(new Date());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().save(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {

		PeWgEmpScore emp = new PeWgEmpScore();
		try {
			emp = this.loadPeWgEmpScore(empscorevo);
			emp.setEvaTotal(empscorevo.getEvaTotal());
			emp.setEvaEmp1(empscorevo.getEvaEmp1());
			emp.setEvaEmp2(empscorevo.getEvaEmp2());
			emp.setEvaEmp3(empscorevo.getEvaEmp3());
			emp.setUpdBy(empscorevo.getUpdBy());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().update(emp);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {
		PeWgEmpScore emp = new PeWgEmpScore();
		PeWgEmpScorePK emppk = new PeWgEmpScorePK();
		try {
			emp = this.loadPeWgEmpScore(empscorevo);
			emp.setEvaTotal(new Double(0));
			emp.setEvaEmp1("");
			emp.setEvaEmp2("");
			emp.setEvaEmp3("");
			emp.setUpdBy(empscorevo.getUpdBy());
			emp.setUpdDate(new Date());

			List empList = new ArrayList();
			empList = this
					.getSession()
					.createQuery(
							"from PeWgEmpScoreDetail e "
									+ "where e.pk.ouCode ='"
									+ empscorevo.getOuCode() + "' "
									+ "and e.pk.evaYear ='"
									+ empscorevo.getEvaYear() + "' "
									+ "and e.pk.evaTime ='"
									+ empscorevo.getEvaTime() + "' "
									+ "and e.pk.empCode ='"
									+ empscorevo.getEmpCode() + "' "
									+ "order by e.pk.empCode").list();
			for (Iterator iter = empList.iterator(); iter.hasNext();) {
				PeWgEmpScoreDetail empDtl = (PeWgEmpScoreDetail) iter.next();
				PeWgEmpScoreDetailVO ret = new PeWgEmpScoreDetailVO();
				try {
					BeanUtils.copyProperties(ret, empDtl.getPk());
					BeanUtils.copyProperties(ret, empDtl);
					this.getHibernateTemplate().delete(empDtl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId) {
		String query = new String();
		query = " select count(a.pk.empCode) from PeWgEmpScore a,WgEmpDataStatus b,PnOrganization c "
				+ " where a.pk.ouCode  = '"
				+ ouCode
				+ "' "
				+ " and   a.pk.evaYear = "
				+ evaYear
				+ " "
				+ " and   a.pk.evaTime = "
				+ evaTime
				+ " "
				+ " and   b.codeSeq in (select o.pk.codeSeq"
				+ " from VPnOrganizationSecurity o "
				+ " where o.pk.ouCode = '"
				+ ouCode + "' " + " and   o.pk.userId = '" + userId + "' ) ";
		if (!areaCode.equals("")) {
			query = query + " and c.areaCode ='" + areaCode + "' ";
		}
		if (!secCode.equals("")) {
			query = query + " and c.secCode ='" + secCode + "' ";
		}
		if (!workCode.equals("")) {
			query = query + " and c.workCode ='" + workCode + "' ";
		}
		query = query + " and a.pk.ouCode  = b.pk.ouCode"
				+ " and a.pk.empCode = b.pk.empCode"
				+ " and a.statusDate = b.statusDate"
				+ " and b.pk.ouCode  = c.pk.ouCode"
				+ " and b.codeSeq    = c.pk.codeSeq"
				+ " order by b.codeSeq,a.pk.empCode";
		List empList = this.getSession().createQuery(query).list();
		return (Integer) empList.get(0);
	}

	public List findEmpForInsertUpdate(String userId, String ouCode,
			Long evaYear, Long evaTime, String areaCode, String secCode,
			String workCode) {

		String query = new String();

		List pesLst = null;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			query = " select a.emp_Code "
					+ "from Wg_Employee a ,pn_organization o ,Pe_wg_emp_score e "
					+ "where a.ou_Code ='"
					+ ouCode
					+ "' "
					+ "and e.eva_year ="
					+ evaYear
					+ "and e.eva_time ="
					+ evaTime
					+ "and a.emp_Code in ( select b.emp_Code"
					+ " from v_wg_employee_security b "
					+ " where b.ou_Code = '"
					+ ouCode
					+ "' "
					+ " and   b.user_Id = '"
					+ userId
					+ "' ) "
					+ "and a.emp_Code not in ( select ed.emp_Code from Pe_Wg_Emp_Score_detail ed "
					+ " where ed.ou_Code = '"
					+ ouCode
					+ "' "
					+ " and   ed.eva_Year = "
					+ evaYear
					+ " and   ed.eva_Time = " + evaTime + ") ";
			if (!areaCode.equals("")) {
				query = query + " and o.area_Code ='" + areaCode + "' ";
			}
			if (!secCode.equals("")) {
				query = query + " and o.sec_Code ='" + secCode + "' ";
			}
			if (!workCode.equals("")) {
				query = query + " and o.work_Code ='" + workCode + "' ";
			}
			query = query + " and a.ou_code = e.ou_code"
					+ " and a.emp_code = e.emp_code"
					+ " and a.ou_code = o.ou_code"
					+ " and a.code_seq = o.code_seq" + " order by a.emp_Code";

			pesLst = jdbcTemplate.queryForList(query);
			Map map = null;
			for (int i = 0; pesLst != null && i < pesLst.size(); i++) {
				PeWgEmpScoreVO pesVo = new PeWgEmpScoreVO();
				map = (Map) pesLst.get(i);
				pesVo.setEmpCode(map.get("EMP_CODE") != null ? map.get(
						"EMP_CODE").toString() : "");
				pesLst.set(i, pesVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pesLst;

	}

	public void addList(PeWgEmpScoreVO PeWgEmpScoreVO) {
		this.peList.add(PeWgEmpScoreVO);
	}

	public void clearList() {
		this.peList = new ArrayList();
	}

	public void insertPeWgEmpScoreList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeWgEmpScoreVO empscorevo = (PeWgEmpScoreVO) this.peList.get(i);
				this.insertPeWgEmpScore(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void updatePeWgEmpScoreList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeWgEmpScoreVO empscorevo = (PeWgEmpScoreVO) this.peList.get(i);
				this.updatePeWgEmpScore(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void deletePeWgEmpScoreList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeWgEmpScoreVO empscorevo = (PeWgEmpScoreVO) this.peList.get(i);
				this.deletePeWgEmpScore(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public PeWgEmpScore loadPeWgEmpScore(PeWgEmpScoreVO empscorevo) {
		PeWgEmpScore emp = null;
		PeWgEmpScorePK empPk = new PeWgEmpScorePK();
		try {
			BeanUtils.copyProperties(empPk, empscorevo);
			emp = (PeWgEmpScore) this.getHibernateTemplate().load(
					PeWgEmpScore.class, empPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode) {

		String year = "";
		String period = "";
		StringBuffer sql = new StringBuffer(0);
		sql.append(" SELECT a.pk.evaYear || MAX(a.pk.evaTime) ");
		sql.append(" FROM   PeWgEmpScore a ");
		sql.append(" WHERE  a.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and a.pk.evaYear = ( select max(b.pk.evaYear) from PeWgEmpScore b ) ");
		sql.append(" group by a.pk.evaYear ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls != null && ls.size() > 0) {
			String rs = (String) ls.get(0);
			year = rs.substring(0, 4);
			period = rs.substring(4, rs.length());
		}

		DefaultYearSectionVO vo = new DefaultYearSectionVO();
		vo.setYear(year);
		vo.setPeriod(period);

		return vo;
	}
}
