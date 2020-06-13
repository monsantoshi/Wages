package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeWgEmpScoreDetailDAO;
import com.ss.tp.dto.PeWgEmpScoreDetailVO;
import com.ss.tp.model.PeWgEmpScoreDetail;
import com.ss.tp.model.PeWgEmpScoreDetailPK;

public class PeWgEmpScoreDetailDAOImpl extends HibernateDaoSupport implements
		PeWgEmpScoreDetailDAO, Serializable {
	List peList = new ArrayList();

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						"from PeWgEmpScoreDetail e " + "where e.pk.ouCode ='"
								+ ouCode + "' " + "and e.pk.evaYear ='"
								+ evaYear + "' " + "and e.pk.evaTime ='"
								+ evaTime + "' " + "and e.pk.empCode ='"
								+ empCode + "' " + "order by e.pk.empCode")
				.list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PeWgEmpScoreDetail emp = (PeWgEmpScoreDetail) iter.next();
			PeWgEmpScoreDetailVO ret = new PeWgEmpScoreDetailVO();
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

	public void insertPeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {

		PeWgEmpScoreDetail emp = new PeWgEmpScoreDetail();
		PeWgEmpScoreDetailPK emppk = new PeWgEmpScoreDetailPK();
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

	public void updatePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {

		PeWgEmpScoreDetail emp = new PeWgEmpScoreDetail();
		try {
			emp = this.loadPeWgEmpScoreDetail(empscorevo);
			emp.setEvaScore(empscorevo.getEvaScore());
			emp.setUpdBy(empscorevo.getUpdBy());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().update(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {

		PeWgEmpScoreDetail emp = new PeWgEmpScoreDetail();
		PeWgEmpScoreDetailPK emppk = new PeWgEmpScoreDetailPK();
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

	public void addList(PeWgEmpScoreDetailVO empscorevo) {
		this.peList.add(empscorevo);
	}

	public void clearList() {
		this.peList = new ArrayList();
	}

	public void insertPeWgEmpScoreDetailList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeWgEmpScoreDetailVO empscorevo = (PeWgEmpScoreDetailVO) this.peList
						.get(i);
				this.insertPeWgEmpScoreDetail(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void updatePeWgEmpScoreDetailList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PeWgEmpScoreDetailVO empscorevo = (PeWgEmpScoreDetailVO) this.peList
						.get(i);
				this.updatePeWgEmpScoreDetail(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public PeWgEmpScoreDetail loadPeWgEmpScoreDetail(
			PeWgEmpScoreDetailVO empscorevo) {
		PeWgEmpScoreDetail emp = null;
		PeWgEmpScoreDetailPK empPk = new PeWgEmpScoreDetailPK();
		try {
			BeanUtils.copyProperties(empPk, empscorevo);
			emp = (PeWgEmpScoreDetail) this.getHibernateTemplate().load(
					PeWgEmpScoreDetail.class, empPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
}
