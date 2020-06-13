package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ss.tp.dao.PeEvaluationFormTitleDAO;
import com.ss.tp.dto.PeEvaluationFormTitleVO;

import com.ss.tp.model.PeEvaluationFormTitle;

public class PeEvaluationFormTitleDAOImpl extends HibernateDaoSupport implements
		PeEvaluationFormTitleDAO, Serializable {
	public String findFormName(String ouCode, String formCode) {
		List formList = new ArrayList();
		formList = this
				.getSession()
				.createQuery(
						"select distinct f.formDesc from PeEvaluationFormTitle f where f.pk.ouCode ='"
								+ ouCode + "' and f.pk.formCode ='" + formCode
								+ "' ").list();
		if (formList == null || formList.size() == 0) {
			return null;
		} else {
			return (String) formList.get(0);
		}
	}

	public List findForm(String ouCode, String formType) {
		List formList = new ArrayList();
		formList = this
				.getSession()
				.createQuery(
						"select distinct f.pk.formCode from PeEvaluationFormTitle f where f.pk.ouCode ='"
								+ ouCode
								+ "' and f.formType = '"
								+ formType
								+ "' ").list();
		return formList;
	}

	public List findTitle(String ouCode, String formCode) {
		Criteria ct = this.getSession().createCriteria(
				PeEvaluationFormTitle.class);
		ct.add(Restrictions.eq("pk.ouCode", ouCode));
		ct.add(Restrictions.eq("pk.formCode", formCode));
		ct.addOrder(Order.asc("pk.titleSeq"));
		List titleList = ct.list();
		List retList = new ArrayList();
		for (Iterator iter = titleList.iterator(); iter.hasNext();) {
			PeEvaluationFormTitle form = (PeEvaluationFormTitle) iter.next();
			PeEvaluationFormTitleVO ret = new PeEvaluationFormTitleVO();
			try {
				BeanUtils.copyProperties(ret, form.getPk());
				BeanUtils.copyProperties(ret, form);
				ret.setTitleName(form.getRefPeEvaluationTitle().getTitleDesc());
				retList.add(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return retList;
	}

	public Double findFormScore(String ouCode, String formCode) {
		String query = new String();
		query = " select distinct e.pk.formScore from PeEvaluationFormTitle e "
				+ " where e.pk.ouCode = '" + ouCode + "' "
				+ " and   e.pk.formCode = '" + formCode + "' ";

		List result = this.getSession().createQuery(query).list();

		return (Double) result.get(0);

	}

	public List findFormDefault(String ouCode, String formType) {
		List formList = new ArrayList();
		formList = this
				.getSession()
				.createQuery(
						"select distinct f.pk.formCode from PeEvaluationFormTitle f where f.pk.ouCode ='"
								+ ouCode
								+ "' and f.formType = '"
								+ formType
								+ "' and f.formDefault = 'Y' ").list();
		return formList;
	}

}
