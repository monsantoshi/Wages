package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PrIncomeDeductDAO;
import com.ss.tp.dto.PrIncomeDeductVO;
import com.ss.tp.model.PrIncomeDeduct;
import com.ss.tp.model.PrIncomeDeductPK;

public class PrIncomeDeductDAOImpl extends HibernateDaoSupport implements
		PrIncomeDeductDAO, Serializable {
	public void insertPrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		PrIncomeDeduct pr = new PrIncomeDeduct();
		PrIncomeDeductPK prpk = new PrIncomeDeductPK();
		try {
			BeanUtils.copyProperties(prpk, princomevo);
			pr.setPk(prpk);
			BeanUtils.copyProperties(pr, princomevo);
			pr.setCreDate(new Date());
			pr.setUpdDate(new Date());
			this.getHibernateTemplate().save(pr);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		PrIncomeDeduct pr = new PrIncomeDeduct();
		PrIncomeDeductPK prpk = new PrIncomeDeductPK();
		try {
			BeanUtils.copyProperties(prpk, princomevo);
			pr.setPk(prpk);
			BeanUtils.copyProperties(pr, princomevo);
			pr.setUpdDate(new Date());
			this.getHibernateTemplate().update(pr);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		PrIncomeDeduct pr = new PrIncomeDeduct();
		PrIncomeDeductPK prpk = new PrIncomeDeductPK();
		try {
			BeanUtils.copyProperties(prpk, princomevo);
			pr.setPk(prpk);
			BeanUtils.copyProperties(pr, princomevo);
			this.getHibernateTemplate().delete(pr);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void insertPrIncomeDeducts(List princomevolist) throws Exception {
		PrIncomeDeduct pr = new PrIncomeDeduct();
		PrIncomeDeductPK prpk = new PrIncomeDeductPK();
		try {
			for (int i = 0; i < princomevolist.size(); i++) {
				PrIncomeDeductVO princomevo = (PrIncomeDeductVO) princomevolist
						.get(i);

				BeanUtils.copyProperties(prpk, princomevo);
				pr.setPk(prpk);
				BeanUtils.copyProperties(pr, princomevo);
				pr.setCreDate(new Date());
				pr.setUpdDate(new Date());

				this.getHibernateTemplate().save(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");
		sql.append(" and pr.flagWeb = 'Y' ");
		if (groupCode.equals("1")) {
			sql.append(" and pr.pk.incDecCode not in ('01','02','03')  ");
		} else {
			sql.append(" and pr.pk.incDecCode not in ('59' ) ");
		}
		sql.append(" order by pr.pk.incDecCode ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PrIncomeDeductVO prIncVo = new PrIncomeDeductVO();
			try {
				PrIncomeDeduct prInc = (PrIncomeDeduct) ls.get(i);
				BeanUtils.copyProperties(prIncVo, prInc);
				BeanUtils.copyProperties(prIncVo, prInc.getPk());
				ls.set(i, prIncVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}

	public List findIncDecCodeRep(String ouCode, String groupCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");
		sql.append(" and pr.flagWeb = 'Y' ");
		if (groupCode.equals("1")) {
			sql.append(" and pr.pk.incDecCode not in ('01','02','03')  ");
		}
		sql.append(" order by pr.pk.incDecCode ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PrIncomeDeductVO prIncVo = new PrIncomeDeductVO();
			try {
				PrIncomeDeduct prInc = (PrIncomeDeduct) ls.get(i);
				BeanUtils.copyProperties(prIncVo, prInc);
				BeanUtils.copyProperties(prIncVo, prInc.getPk());
				ls.set(i, prIncVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}
	
	public List findIncDecCodeOutAmtRep(String ouCode, String groupCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");
		sql.append(" and pr.pk.incDecCode = '50' ");
		sql.append(" and pr.flagWeb = 'N' ");
		if (groupCode.equals("1")) {
			sql.append(" and pr.pk.incDecCode not in ('01','02','03')  ");
		}
		sql.append(" order by pr.pk.incDecCode ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PrIncomeDeductVO prIncVo = new PrIncomeDeductVO();
			try {
				PrIncomeDeduct prInc = (PrIncomeDeduct) ls.get(i);
				BeanUtils.copyProperties(prIncVo, prInc);
				BeanUtils.copyProperties(prIncVo, prInc.getPk());
				ls.set(i, prIncVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}


	public List findIncDecCode59(String ouCode, String groupCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");
		sql.append(" and pr.flagWeb = 'Y' ");
		if (groupCode.equals("2")) {
			sql.append(" and pr.pk.incDecCode  in ('59')  ");
		}
		sql.append(" order by pr.pk.incDecCode ");
		List ls = this.getHibernateTemplate().find(sql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			PrIncomeDeductVO prIncVo = new PrIncomeDeductVO();
			try {
				PrIncomeDeduct prInc = (PrIncomeDeduct) ls.get(i);
				BeanUtils.copyProperties(prIncVo, prInc);
				BeanUtils.copyProperties(prIncVo, prInc.getPk());
				ls.set(i, prIncVo);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return ls;
	}

	public Double findMaxIncDecCode(String ouCode, String groupCode,
			String incDecCode) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select pr.maxAmt ");
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.incDecCode = '" + incDecCode + "' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");
		Double d = (Double) this.getSession().createQuery(sql.toString())
				.uniqueResult();

		return d;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.tp.dao.PrIncomeDeductDAO#getIncDecName(java.lang.String, int,
	 * java.lang.String)
	 */
	public String getIncDecName(String ouCode, int groupCode, String incDecCode) {
		String incDecName = "";
		StringBuffer sql = new StringBuffer(0);

		sql.append(" select pr.incDecName ");
		sql.append(" from PrIncomeDeduct pr ");
		sql.append(" where pr.pk.ouCode = '");
		sql.append(ouCode);
		sql.append("' ");
		sql.append(" and pr.pk.groupCode = '");
		sql.append(groupCode);
		sql.append("' ");
		sql.append(" and pr.pk.incDecCode = '");
		sql.append(incDecCode);
		sql.append("' ");
		sql.append(" and pr.inactive = 'N' ");

		List ls = this.getHibernateTemplate().find(sql.toString());
		if (ls.size() > 0)
			incDecName = (String) ls.get(0);
		System.out.println("Name ==>" + incDecName);

		return incDecName;
	}
}