package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnTextControlDAO;
import com.ss.tp.dto.PnTextControlVO;

import com.ss.tp.model.PnTextControl;
import com.ss.tp.model.PnTextControlPK;

public class PnTextControlDAOImpl extends HibernateDaoSupport implements
		PnTextControlDAO, Serializable {

	public void insertPnTextControl(PnTextControlVO pntextcontrolvo)
			throws Exception {
		PnTextControl pntextcontrol = new PnTextControl();
		PnTextControlPK pntextcontrolpk = new PnTextControlPK();
		try {
			BeanUtils.copyProperties(pntextcontrolpk, pntextcontrolvo);
			pntextcontrol.setPk(pntextcontrolpk);
			BeanUtils.copyProperties(pntextcontrol, pntextcontrolvo);
			pntextcontrol.setCreDate(new Date());
			pntextcontrol.setUpdDate(new Date());
			this.getHibernateTemplate().save(pntextcontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePnTextControl(PnTextControlVO pntextcontrolvo)
			throws Exception {
		PnTextControl pntextcontrol = new PnTextControl();
		try {
			pntextcontrol = this.loadPnTextControl(pntextcontrolvo);
			pntextcontrol.setFlagClose("Y");
			pntextcontrol.setUpdBy(pntextcontrolvo.getUpdBy());
			pntextcontrol.setUpdDate(new Date());
			this.getHibernateTemplate().update(pntextcontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public PnTextControl loadPnTextControl(PnTextControlVO pntextcontrolvo) {
		PnTextControl pntextcontrol = new PnTextControl();
		PnTextControlPK pntextcontrolpk = new PnTextControlPK();
		try {
			BeanUtils.copyProperties(pntextcontrolpk, pntextcontrolvo);
			pntextcontrol = (PnTextControl) this.getHibernateTemplate().load(
					PnTextControl.class, pntextcontrolpk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pntextcontrol;
	}

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq) {
		List pntextcontrolList = new ArrayList();
		String criteria = "";
		StringBuffer hql = new StringBuffer(0);

		/*
		 * if (!codeSeq.equals("")) { criteria =
		 * " and v.orgCode ='"+codeSeq+"' "; }
		 * 
		 * hql.append(" select v.pk.codeSeq "); hql.append(" from
		 * VPnOrganizationSecurity v "); hql.append(" where v.pk.ouCode = '" +
		 * ouCode + "' "); hql.append(" and v.pk.userId = '" + userId + "' ");
		 * hql.append(criteria); hql.append(" and v.pk.codeSeq not in ");
		 * hql.append(" ( select p.pk.codeSeq "); hql.append(" from PeControl
		 * p"); hql.append (" where p.pk.ouCode ='"+ouCode+"' "); hql .append("
		 * and p.pk.evaYear ="+evaYear+ " "); hql.append(" and p.pk.evaTime
		 * ="+evaTime +" "); hql.append(" and p.flagClose ='Y' )");
		 */
		hql.append(" select p.pk.userId ");
		hql.append(" from PnTextControl p");
		hql.append(" where p.pk.ouCode  ='" + ouCode + "' ");
		hql.append(" and   p.pk.evaYear =" + evaYear + " ");
		hql.append(" and   p.pk.evaTime =" + evaTime + " ");
		hql.append(" and   p.pk.userId ='" + userId + "' ");
		hql.append(" and   p.flagClose  ='Y' )");
		pntextcontrolList = this.getSession().createQuery(hql.toString())
				.list();
		if (pntextcontrolList == null || pntextcontrolList.size() == 0) {
			return null;
		} else {
			return "True";
		}
	}

	public boolean CheckDataForInsert(String ouCode, Long evaYear,
			Long evaTime, String userId) {
		List pntextcontrolList = new ArrayList();
		pntextcontrolList = this
				.getSession()
				.createQuery(
						"select distinct p.pk.ouCode "
								+ "from PnTextControl p "
								+ "where p.pk.ouCode ='" + ouCode + "' "
								+ "and p.pk.evaYear =" + evaYear + " "
								+ "and p.pk.evaTime =" + evaTime + " "
								+ "and p.pk.userId  ='" + userId + "' "
				// "and p.pk.codeSeq ="+codeSeq+" "
				).list();
		if (pntextcontrolList == null || pntextcontrolList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckDataForUpdate(String ouCode, Long evaYear,
			Long evaTime, Long codeSeq) {
		List pntextcontrolList = new ArrayList();
		pntextcontrolList = this
				.getSession()
				.createQuery(
						"select distinct p.pk.ouCode "
								+ "from PnTextControl p "
								+ "where p.pk.ouCode ='" + ouCode + "' "
								+ "and p.pk.evaYear =" + evaYear + " "
								+ "and p.pk.evaTime =" + evaTime + " "
								+ "and p.pk.codeSeq =" + codeSeq + " "
								+ "and p.flagClose ='N' ").list();
		if (pntextcontrolList == null || pntextcontrolList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
