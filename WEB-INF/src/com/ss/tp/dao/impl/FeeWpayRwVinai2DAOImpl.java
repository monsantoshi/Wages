package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.FeeWpayRwVinai2DAO;
import com.ss.tp.dto.FeeWpayRwVinai2VO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.dto.RwVinai2VO;
import com.ss.tp.dto.VFeeWpayRwVinai2VO;
import com.ss.tp.dto.VFeeWpayRwVinaiVO;
import com.ss.tp.model.FeeWpayRwIncDecOther;
import com.ss.tp.model.FeeWpayRwVinai;
import com.ss.tp.model.FeeWpayRwVinai2;

public class FeeWpayRwVinai2DAOImpl extends HibernateDaoSupport implements
		FeeWpayRwVinai2DAO, Serializable {
	List rwList = new ArrayList();

	public FeeWpayRwVinai2DAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		FeeWpayRwVinai2 rw = new FeeWpayRwVinai2();
		try {
			BeanUtils.copyProperties(rw, rwvinai2vo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public FeeWpayRwVinai2 loadRwVinai2(RwVinai2VO rwVo) {
		FeeWpayRwVinai2 rwp = new FeeWpayRwVinai2();
		try {
			Criteria c = this.getSessionFactory().openSession().createCriteria(FeeWpayRwVinai2.class);
			c.add(Restrictions.idEq(rwVo.getKeySeq()));
	        rwp = (FeeWpayRwVinai2) c.list().get(0);
			//rwp = (FeeWpayRwPremium) this.getHibernateTemplate().load(FeeWpayRwPremium.class,
				//	rpVo.getKeySeq());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		FeeWpayRwVinai2 rw = new FeeWpayRwVinai2();
		try {
			BeanUtils.copyProperties(rw, rwvinai2vo);
			rw = this.loadRwVinai2(rwvinai2vo);
			rw.setFlagPr(rwvinai2vo.getFlagPr());
			rw.setYearWork(rwvinai2vo.getYearWork());
			rw.setPeriodWork(rwvinai2vo.getPeriodWork());
			rw.setDecDay(rwvinai2vo.getDecDay());
			rw.setRemark(rwvinai2vo.getRemark());
			rw.setUpdBy(rwvinai2vo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		FeeWpayRwVinai2 rw = new FeeWpayRwVinai2();
		try {
			BeanUtils.copyProperties(rw, rwvinai2vo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwVinai2VO rwvinai2vo) {
		this.rwList.add(rwvinai2vo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwVinai2s() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwVinai2VO rwvinai2vo = (RwVinai2VO) this.rwList.get(i);
				if ((rwvinai2vo.getKeySeq() != new Long(0))
						&& rwvinai2vo.getKeySeq() != null
						&& !rwvinai2vo.getKeySeq().equals("")) {
					this.updateRwVinai2(rwvinai2vo);
				} else {
					this.insertRwVinai2(rwvinai2vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwVinai2s(List rwvinai2volist) throws Exception {
		FeeWpayRwVinai2 rw = new FeeWpayRwVinai2();
		try {
			for (int i = 0; i < rwvinai2volist.size(); i++) {
				RwVinai2VO rwvinai2vo = (RwVinai2VO) rwvinai2volist.get(i);

				BeanUtils.copyProperties(rw, rwvinai2vo);
				rw.setCreDate(new Date());
				rw.setUpdDate(new Date());
				this.getHibernateTemplate().save(rw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, rw.codeSeq ");
		hql.append(" from FeeWpayRwVinai2 rw , WgEmployee pnEmp  , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rw.codeSeq, rw.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			//String positionShort = (String) r[4];

			PayRollEmployeeVO ret = new PayRollEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			//ret.setPositionShort(positionShort);

			retList.add(ret);
		}
		return retList;
	}

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		if (evaFlagFrom != null && !evaFlagFrom.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			criteria.append(evaFlagFrom);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (evaFlagTo != null && !evaFlagTo.equals("")) {
			criteria.append(" and decode(rw.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			criteria.append(evaFlagTo);
			criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rw.empCode) ");
		hql.append(" from FeeWpayRwVinai2 rw , WgEmployee pnEmp  , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}
	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
	//	hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from FeeWpayRwVinai2 rw, ");
		hql.append(" WgEmployee pnEmp, ");
		hql.append(" FeeWpayPrEmployee prEmp, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(year);
		hql.append(" and prEmp.key.period = ");
		hql.append(period);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		
		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		PayRollEmployeeVO ret = new PayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
		//	String positionShort = (String) r[4];
			String orgCode = (String) r[4];
			String orgDesc = (String) r[5];
			Double codeSeqWork = (Double) r[6];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
		//	ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from FeeWpayRwVinai2 rw ");
		hql.append(" where rw.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rw.yearPr = ");
		hql.append(yearPr);
		hql.append(" and rw.periodPr = ");
		hql.append(periodPr);
		hql.append(" and rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		hql.append(" 	and pk.year = " + yearPr);
		hql.append(" 	and pk.period = " + periodPr);
		hql.append(" ) ");

		// hql.append(" and rw.codeSeq in ( ");
		// hql.append(" select vpn.pk.codeSeq ");
		// hql.append(" from VPnOrganizationSecurity vpn ");
		// hql.append(" where vpn.pk.userId = '");
		// hql.append(userId);
		// hql.append("' ");
		// hql.append(" and rw.ouCode = vpn.pk.ouCode ");
		// hql.append(" and rw.codeSeq = vpn.pk.codeSeq ) ");

		System.out.println("HQL findByEmpCodeList ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		if (empList != null && empList.size() > 0) {
			for (int i = 0; i < empList.size(); i++) {
				RwVinai2VO rw = new RwVinai2VO();

				try {
					BeanUtils.copyProperties(rw, empList.get(i));
					retList.add(i, rw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return retList;
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwVinai2 p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'Y' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		sql.append(" and p.yearPr = e.pk.year ");
		sql.append(" and p.periodPr = e.pk.period ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return true;
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from FeeWpayRwVinai2 p, VFeeWpPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		sql.append(" and p.yearPr = e.pk.year ");
		sql.append(" and p.periodPr = e.pk.period ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return false;
			else
				return true;
		} else
			return true;
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

	

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(rw.keySeq) ");
		hql.append(" from FeeWpayRwVinai2 rw , WgEmployee pnEmp  , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	//@Override
	public void addListApprove(RwVinai2VO rwvinai2vo) {
		this.rwList.add(rwvinai2vo);
		
	}

	//@Override
	public void insertAndUpdateApTablesApprove() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwVinai2VO rwvinai2vo = (RwVinai2VO) this.rwList.get(i);
				if ((rwvinai2vo.getKeySeq() != new Long(0))
						&& rwvinai2vo.getKeySeq() != null
						&& !rwvinai2vo.getKeySeq().equals("")) {
					this.updateApTableApprove(rwvinai2vo);
				} else {
					this.insertRwVinai2(rwvinai2vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
		
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rw.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rw.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaCodeSeqFrom != null && !evaCodeSeqFrom.equals("")) {
			criteria.append(" and pnOrg.orgCode >= '");
			criteria.append(evaCodeSeqFrom);
			criteria.append("' ");
		}

		if (evaCodeSeqTo != null && !evaCodeSeqTo.equals("")) {
			criteria.append(" and pnOrg.orgCode <= '");
			criteria.append(evaCodeSeqTo);
			criteria.append("' ");
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rw.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rw.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		

		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, rw.codeSeq, ");
		hql.append(" rw.flagPr, ");
		hql.append(" rw.yearWork, ");
		hql.append(" rw.periodWork, ");
		hql.append(" rw.decDay, ");
		hql.append(" rw.remark, ");
		hql.append(" rw.approveFlag, ");
		hql.append(" rw.keySeq ");
		hql.append(" from FeeWpayRwVinai2 rw , WgEmployee pnEmp  , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.wgEmployeePK.ouCode ");
		hql.append(" and rw.empCode = pnEmp.wgEmployeePK.empCode ");
		hql.append(" and rw.confirmFlag = 'Y' ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rw.codeSeq, rw.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			Long codeSeq = (Long) r[4];
			String flagPr	= (String) r[5];
			Double yearWork = (Double) r[6];
			Double periodWork = (Double) r[7];
			Double decDay	= (Double) r[8];
			String remark = (String) r[9];
			String approveFlag = (String) r[10];
			Long keySeq = (Long) r[11];

			FeeWpayRwVinai2VO ret = new FeeWpayRwVinai2VO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setFlagPr(flagPr);
			ret.setYearWork(yearWork);
			ret.setPeriodWork(periodWork);
			ret.setCodeSeq(codeSeq);
			ret.setDecDay(decDay);
			ret.setRemark(remark);
			ret.setApproveFlag(approveFlag);
			ret.setKeySeq(keySeq);
			//ret.setPositionShort(positionShort);
			System.out.println("empCode" +empCode);

			retList.add(ret);
		}
		return retList;
	}

	//@Override
	public void updateApTableApprove(RwVinai2VO rwvinai2vo) throws Exception {
		FeeWpayRwVinai2 rw = new FeeWpayRwVinai2();
		try {
			BeanUtils.copyProperties(rw, rwvinai2vo);
			rw = this.loadRwVinai2(rwvinai2vo);
			rw.setApproveFlag(rwvinai2vo.getApproveFlag());
			rw.setApproveBy(rwvinai2vo.getApproveBy());
			rw.setApproveDate(new Date());
		
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public List rwVinai2Report(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwV.pk.yearPr = ");
			criteria.append(evaYear);
		}

		if (evaPeriod != null && !evaPeriod.equals("")) {
			criteria.append(" and rwV.pk.periodPr = ");
			criteria.append(evaPeriod);
		}

		if (evaFlag != null && !evaFlag.equals("")) {
			criteria.append(" and rwV.flagPr like '");
			criteria.append(evaFlag);
			criteria.append("'");
		}

		StringBuffer hql = new StringBuffer();
		hql.append(" select rwV.pk.empCode, rwV.empName,");
		hql.append(" rwV.orgDesc,");
		hql.append(" rwV.year1,");
	    hql.append(" rwV.decDay ,rwV.flagPr,rwV.remark ");
		hql.append(" from VFeeWpayRwVinai2 rwV ");
		hql.append(" where rwV.pk.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");

		/*
		 * //'hql.append(" from VRwVinai rwV, PnEmployee pnEmp , PnOrganization
		 * pnOrg,VPnOrganizationSecurity v "); hql.append(" where rwV.pk.ouCode
		 * = '"); hql.append(evaOuCode); hql.append("' "); hql.append(" and
		 * pnEmp.pk.empCode in ( "); hql.append(" select pk.empCode ");
		 * hql.append(" from VPrEmployeeSecurityReport "); hql.append(" where
		 * pk.userId = '" + userId + "' "); hql.append(" and pk.ouCode = '" +
		 * evaOuCode + "' "); hql.append(" and pk.year = " + evaYear );
		 * hql.append(" and pk.period = " + evaPeriod ); hql.append(" ) ");
		 * hql.append(criteria); hql.append(" and rwV.ouCode = pnEmp.pk.ouCode
		 * "); hql.append(" and rwV.empCode = pnEmp.pk.empCode "); hql.append("
		 * and rwV.ouCode = pnOrg.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * pnOrg.pk.codeSeq "); hql.append(" and v.pk.userId = '" + userId + "'
		 * "); hql.append(" and rwV.ouCode = v.pk.ouCode "); hql.append(" and
		 * rwV.codeSeq = v.pk.codeSeq "); //hql.append(" and rwPre.creBy = '" +
		 * userId + "' "); hql.append( " order by
		 * nvl(pnOrg.secCode,pnOrg.divCode)||' '||pnOrg.divDesc||'
		 * '||pnOrg.secDesc,rwV.empCode " );
		 */

		/*
		 * hql.append(" and v.pk.userId = '" + userId + "' "); hql.append(" and
		 * rwV.ouCode = v.pk.ouCode "); hql.append(" and rwV.codeSeq =
		 * v.pk.codeSeq ");
		 */
		hql.append(" and rwV.pk.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VFeeWpPrEmpSecReport ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + evaOuCode + "' ");
		hql.append(" 	and pk.year = " + evaYear);
		hql.append(" 	and pk.period = " + evaPeriod);
		hql.append(" ) ");
		hql.append(criteria);

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			System.out.println("**" + it);
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String empName = (String) r[1];
			String orgDesc = (String) r[2];
			String year1 = (String) r[3];
			Double decDay = (Double) r[4];
			String flagPr = (String) r[5];
			String remark = (String) r[6];
		

			VFeeWpayRwVinai2VO ret = new VFeeWpayRwVinai2VO();
			ret.setEmpCode(empCode);
			ret.setEmpName(empName);
			ret.setOrgDesc(orgDesc);
		    ret.setYear1(year1);
			ret.setDecDay(decDay);
		    ret.setFlagPr(flagPr);
		    ret.setRemark(remark);
			retList.add(ret);
		}
		return retList;
	}
	
	
	
}