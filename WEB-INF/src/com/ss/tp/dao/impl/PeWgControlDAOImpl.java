package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PeWgControlDAO;
import com.ss.tp.dto.PeWgControlVO;
import com.ss.tp.model.PeWgControl;
import com.ss.tp.model.PeWgControlPK;

public class PeWgControlDAOImpl extends HibernateDaoSupport implements
		PeWgControlDAO, Serializable {
	public void insertPeWgControl(PeWgControlVO pecontrolvo) throws Exception {
		PeWgControl pewgcontrol = new PeWgControl();
		PeWgControlPK pewgcontrolpk = new PeWgControlPK();
		try {
			BeanUtils.copyProperties(pewgcontrolpk, pecontrolvo);
			pewgcontrol.setPk(pewgcontrolpk);
			BeanUtils.copyProperties(pewgcontrol, pecontrolvo);
			pewgcontrol.setCreDate(new Date());
			pewgcontrol.setUpdDate(new Date());
			this.getHibernateTemplate().save(pewgcontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePeWgControl(PeWgControlVO pecontrolvo) throws Exception {
		PeWgControl pecontrol = new PeWgControl();
		try {
			pecontrol = this.loadPeWgControl(pecontrolvo);
			pecontrol.setFlagClose("Y");
			pecontrol.setUpdBy(pecontrolvo.getUpdBy());
			pecontrol.setUpdDate(new Date());
			this.getHibernateTemplate().update(pecontrol);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public PeWgControl loadPeWgControl(PeWgControlVO pecontrolvo) {
		PeWgControl pecontrol = new PeWgControl();
		PeWgControlPK pecontrolpk = new PeWgControlPK();
		try {
			BeanUtils.copyProperties(pecontrolpk, pecontrolvo);
			pecontrol = (PeWgControl) this.getHibernateTemplate().load(
					PeWgControl.class, pecontrolpk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pecontrol;
	}

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq) {
		List pecontrolList = new ArrayList();
		StringBuffer hql = new StringBuffer(0);

		hql.append(" select p.pk.userId ");
		hql.append(" from PeWgControl p");
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
						"select distinct p.pk.ouCode " + "from PeWgControl p "
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
						"select distinct p.pk.ouCode " + "from PeWgControl p "
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
