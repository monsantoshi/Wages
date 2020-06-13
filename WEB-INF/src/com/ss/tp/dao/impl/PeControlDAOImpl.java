package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeControlDAO;
import com.ss.tp.dto.PeControlVO;
import com.ss.tp.model.PeControl;
import com.ss.tp.model.PeControlPK;

public class PeControlDAOImpl extends HibernateDaoSupport implements
		PeControlDAO, Serializable {
	public void insertPeControl(PeControlVO pecontrolvo) throws Exception {
		PeControl pecontrol = new PeControl();
		PeControlPK pecontrolpk = new PeControlPK();
		try {
			BeanUtils.copyProperties(pecontrolpk, pecontrolvo);
			pecontrol.setPk(pecontrolpk);
			BeanUtils.copyProperties(pecontrol, pecontrolvo);
			pecontrol.setCreDate(new Date());
			pecontrol.setUpdDate(new Date());
			this.getHibernateTemplate().save(pecontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePeControl(PeControlVO pecontrolvo) throws Exception {
		PeControl pecontrol = new PeControl();
		try {
			pecontrol = this.loadPeControl(pecontrolvo);
			pecontrol.setFlagClose("Y");
			pecontrol.setUpdBy(pecontrolvo.getUpdBy());
			pecontrol.setUpdDate(new Date());
			this.getHibernateTemplate().update(pecontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public PeControl loadPeControl(PeControlVO pecontrolvo) {
		PeControl pecontrol = new PeControl();
		PeControlPK pecontrolpk = new PeControlPK();
		try {
			BeanUtils.copyProperties(pecontrolpk, pecontrolvo);
			pecontrol = (PeControl) this.getHibernateTemplate().load(
					PeControl.class, pecontrolpk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pecontrol;
	}

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq) {
		List pecontrolList = new ArrayList();
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
		hql.append(" from PeControl p");
		hql.append(" where p.pk.ouCode  ='" + ouCode + "' ");
		hql.append(" and   p.pk.evaYear =" + evaYear + " ");
		hql.append(" and   p.pk.evaTime =" + evaTime + " ");
		hql.append(" and   p.pk.userId ='" + userId + "' ");
		hql.append(" and   p.flagClose  ='Y' )");
		pecontrolList = this.getSession().createQuery(hql.toString()).list();
		if (pecontrolList == null || pecontrolList.size() == 0) {
			return null;
		} else {
			return "True";
		}
	}

	public boolean CheckDataForInsert(String ouCode, Long evaYear,
			Long evaTime, String userId) {
		List pecontrolList = new ArrayList();
		pecontrolList = this
				.getSession()
				.createQuery(
						"select distinct p.pk.ouCode " + "from PeControl p "
								+ "where p.pk.ouCode ='" + ouCode + "' "
								+ "and p.pk.evaYear =" + evaYear + " "
								+ "and p.pk.evaTime =" + evaTime + " "
								+ "and p.pk.userId  ='" + userId + "' "
				// "and p.pk.codeSeq ="+codeSeq+" "
				).list();
		if (pecontrolList == null || pecontrolList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckDataForUpdate(String ouCode, Long evaYear,
			Long evaTime, Long codeSeq) {
		List pecontrolList = new ArrayList();
		pecontrolList = this
				.getSession()
				.createQuery(
						"select distinct p.pk.ouCode " + "from PeControl p "
								+ "where p.pk.ouCode ='" + ouCode + "' "
								+ "and p.pk.evaYear =" + evaYear + " "
								+ "and p.pk.evaTime =" + evaTime + " "
								+ "and p.pk.codeSeq =" + codeSeq + " "
								+ "and p.flagClose ='N' ").list();
		if (pecontrolList == null || pecontrolList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
