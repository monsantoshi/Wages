package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeEmployeeScoreDetailDAO;
import com.ss.tp.dto.PeEmployeeScoreDetailVO;
import com.ss.tp.model.PeEmployeeScoreDetail;
import com.ss.tp.model.PeEmployeeScoreDetailPK;

public class PeEmployeeScoreDetailDAOImpl extends HibernateDaoSupport implements
		PeEmployeeScoreDetailDAO, Serializable {
	List peList = new ArrayList();

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						"from PeEmployeeScoreDetail e "
								+ "where e.pk.ouCode ='" + ouCode + "' "
								+ "and e.pk.evaYear ='" + evaYear + "' "
								+ "and e.pk.evaTime ='" + evaTime + "' "
								+ "and e.pk.empCode ='" + empCode + "' "
								+ "order by e.pk.empCode").list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PeEmployeeScoreDetail emp = (PeEmployeeScoreDetail) iter.next();
			PeEmployeeScoreDetailVO ret = new PeEmployeeScoreDetailVO();
			try {
				BeanUtils.copyProperties(ret, emp.getPk());
				BeanUtils.copyProperties(ret, emp);
				ret.setTitleCode(emp.getRefPeEvaluationFormTitle()
						.getTitleCode());
				ret.setTitleDesc(emp.getRefPeEvaluationFormTitle()
						.getRefPeEvaluationTitle().getTitleDesc());
				ret.setTitleScore(emp.getRefPeEvaluationFormTitle()
						.getTitleScore());
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	public void insertPeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {

		PeEmployeeScoreDetail emp = new PeEmployeeScoreDetail();
		PeEmployeeScoreDetailPK emppk = new PeEmployeeScoreDetailPK();
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

	public void updatePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {

		PeEmployeeScoreDetail emp = new PeEmployeeScoreDetail();
		try {
			emp = this.loadPeEmployeeScoreDetail(empscorevo);
			emp.setEvaScore(empscorevo.getEvaScore());
			emp.setUpdBy(empscorevo.getUpdBy());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().update(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {

		PeEmployeeScoreDetail emp = new PeEmployeeScoreDetail();
		PeEmployeeScoreDetailPK emppk = new PeEmployeeScoreDetailPK();
		try {
			BeanUtils.copyProperties(emppk, empscorevo);
			emp.setPk(emppk);
			BeanUtils.copyProperties(emp, empscorevo);
			this.getHibernateTemplate().delete(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(PeEmployeeScoreDetailVO empscorevo) {
		this.peList.add(empscorevo);
	}

	public void clearList() {
		this.peList = new ArrayList();
	}

	public void insertPeEmployeeScoreDetailList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeEmployeeScoreDetailVO empscorevo = (PeEmployeeScoreDetailVO) this.peList
						.get(i);
				this.insertPeEmployeeScoreDetail(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void updatePeEmployeeScoreDetailList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeEmployeeScoreDetailVO empscorevo = (PeEmployeeScoreDetailVO) this.peList
						.get(i);
				this.updatePeEmployeeScoreDetail(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public PeEmployeeScoreDetail loadPeEmployeeScoreDetail(
			PeEmployeeScoreDetailVO empscorevo) {
		PeEmployeeScoreDetail emp = null;
		PeEmployeeScoreDetailPK empPk = new PeEmployeeScoreDetailPK();
		try {
			BeanUtils.copyProperties(empPk, empscorevo);
			emp = (PeEmployeeScoreDetail) this.getHibernateTemplate().load(
					PeEmployeeScoreDetail.class, empPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
}
