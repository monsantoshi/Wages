package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnEmpSetPlaceDAO;
import com.ss.tp.dto.PnEmpSetPlaceVO;
import com.ss.tp.model.PnEmpSetPlace;
import com.ss.tp.model.PnEmpSetPlacePK;

public class PnEmpSetPlaceDAOImpl extends HibernateDaoSupport implements
		PnEmpSetPlaceDAO, Serializable {
	List peList = new ArrayList();

	public List findByCriteria(String ouCode, String moveStatus,
			String empCode, String year) {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						"from PnEmpSetPlace e " + "where e.pk.ouCode ='"
								+ ouCode + "' " + "and e.pk.moveStatus ='"
								+ moveStatus + "' " + "and e.pk.empCode ='"
								+ empCode + "' " + "and e.pk.year ='" + year
								+ "' " + "order by e.pk.empCode").list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PnEmpSetPlace emp = (PnEmpSetPlace) iter.next();
			PnEmpSetPlaceVO ret = new PnEmpSetPlaceVO();
			try {
				BeanUtils.copyProperties(ret, emp.getPk());
				BeanUtils.copyProperties(ret, emp);
				// ret.setTitleCode(emp.getRefPeEvaluationFormTitle().getTitleCode());
				// ret.setTitleDesc(emp.getRefPeEvaluationFormTitle().getRefPeEvaluationTitle().getTitleDesc());
				// ret.setTitleScore(emp.getRefPeEvaluationFormTitle().getTitleScore());
				// retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	public void insertPnEmpSetPlace(PnEmpSetPlaceVO empscorevo)
			throws Exception {

		PnEmpSetPlace emp = new PnEmpSetPlace();
		PnEmpSetPlacePK emppk = new PnEmpSetPlacePK();
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

	public void updatePnEmpSetPlace(PnEmpSetPlaceVO empscorevo)
			throws Exception {

		PnEmpSetPlace emp = new PnEmpSetPlace();
		try {
			emp = this.loadPnEmpSetPlace(empscorevo);
			// emp.setEvaScore(empscorevo.getEvaScore());
			emp.setUpdBy(empscorevo.getUpdBy());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().update(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePnEmpSetPlace(PnEmpSetPlaceVO empscorevo)
			throws Exception {

		PnEmpSetPlace emp = new PnEmpSetPlace();
		PnEmpSetPlacePK emppk = new PnEmpSetPlacePK();
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

	public void addList(PnEmpSetPlaceVO empscorevo) {
		this.peList.add(empscorevo);
	}

	public void clearList() {
		this.peList = new ArrayList();
	}

	public void insertPnEmpSetPlaceList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PnEmpSetPlaceVO empscorevo = (PnEmpSetPlaceVO) this.peList
						.get(i);
				this.insertPnEmpSetPlace(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void updatePnEmpSetPlaceList() throws Exception {
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PnEmpSetPlaceVO empscorevo = (PnEmpSetPlaceVO) this.peList
						.get(i);
				this.updatePnEmpSetPlace(empscorevo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public PnEmpSetPlace loadPnEmpSetPlace(PnEmpSetPlaceVO empscorevo) {
		PnEmpSetPlace emp = null;
		PnEmpSetPlacePK empPk = new PnEmpSetPlacePK();
		try {
			BeanUtils.copyProperties(empPk, empscorevo);
			emp = (PnEmpSetPlace) this.getHibernateTemplate().load(
					PnEmpSetPlace.class, empPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
}
