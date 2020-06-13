package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.ss.tp.dao.PeEmployeeScoreDetailDAO;
//import com.ss.tp.dto.PeEmployeeScoreDetailVO;
//import com.ss.tp.dto.PeEmployeeScoreVO;
//import com.ss.tp.dto.PeWgEmpScoreVO;
//import com.ss.tp.model.PeEmpScorePK;
//import com.ss.tp.model.PeEmployeeScore;
//import com.ss.tp.model.PeEmployeeScoreDetail;
//import com.ss.tp.model.PeEmployeeScoreDetailPK;
//import com.ss.tp.model.PeWgEmpScore;
//import com.ss.tp.model.PeWgEmpScorePK;
import com.ss.tp.dao.PnEmployeeTextDetailDAO;

import com.ss.tp.dto.PnEmployeeTextDetailVO;
import com.ss.tp.model.PnEmployeeTextDetail;
import com.ss.tp.model.PnEmployeeTextDetailPK;

public class PnEmployeeTextDetailDAOImpl extends HibernateDaoSupport implements
		PnEmployeeTextDetailDAO, Serializable {
	List peList = new ArrayList();

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode) {
		// TODO Auto-generated method stub
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						"from PnEmployeeTextDetail e " + "where e.pk.ouCode ='"
								+ ouCode + "' " + "and e.pk.evaYear ='"
								+ evaYear + "' " + "and e.pk.evaTime ='"
								+ evaTime + "' " + "and e.pk.empCode ='"
								+ empCode + "' " + "order by e.pk.empCode")
				.list();
		List retList = new ArrayList();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			PnEmployeeTextDetail emp = (PnEmployeeTextDetail) iter.next();
			PnEmployeeTextDetailVO ret = new PnEmployeeTextDetailVO();
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

	public void insertPnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		PnEmployeeTextDetail emp = new PnEmployeeTextDetail();
		PnEmployeeTextDetailPK emppk = new PnEmployeeTextDetailPK();
		try {
			BeanUtils.copyProperties(emppk, emptextvo);
			emp.setPk(emppk);
			BeanUtils.copyProperties(emp, emptextvo);
			emp.setCreDate(new Date());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().save(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public PnEmployeeTextDetail loadPnEmployeeTextDetail(
			PnEmployeeTextDetailVO emptextvo) {
		PnEmployeeTextDetail emp = null;
		PnEmployeeTextDetailPK empPk = new PnEmployeeTextDetailPK();
		try {
			BeanUtils.copyProperties(empPk, emptextvo);
			emp = (PnEmployeeTextDetail) this.getHibernateTemplate().load(
					PnEmployeeTextDetail.class, empPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public void updatePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		PnEmployeeTextDetail emp = new PnEmployeeTextDetail();
		try {
			emp = this.loadPnEmployeeTextDetail(emptextvo);
			// emp.setEvaScore(emptextvo.getEvaScore());
			// emp.setEngName(emptextvo.getEngName());
			// emp.setEngLastname(emptextvo.getEngLastname());
			emp.setUpdBy(emptextvo.getUpdBy());
			emp.setUpdDate(new Date());
			this.getHibernateTemplate().update(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		PnEmployeeTextDetail emp = new PnEmployeeTextDetail();
		PnEmployeeTextDetailPK emppk = new PnEmployeeTextDetailPK();
		try {
			BeanUtils.copyProperties(emppk, emptextvo);
			emp.setPk(emppk);
			BeanUtils.copyProperties(emp, emptextvo);
			this.getHibernateTemplate().delete(emp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(PnEmployeeTextDetailVO emptextvo) {
		// TODO Auto-generated method stub
		this.peList.add(emptextvo);
	}

	public void clearList() {
		// TODO Auto-generated method stub
		this.peList = new ArrayList();
	}

	public void insertPnEmployeeTextDetailList() throws Exception {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PnEmployeeTextDetailVO emptextvo = (PnEmployeeTextDetailVO) this.peList
						.get(i);
				this.insertPnEmployeeTextDetail(emptextvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void updatePnEmployeeTextDetailList() throws Exception {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < this.peList.size(); i++) {
				PnEmployeeTextDetailVO emptextvo = (PnEmployeeTextDetailVO) this.peList
						.get(i);
				this.updatePnEmployeeTextDetail(emptextvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

}
