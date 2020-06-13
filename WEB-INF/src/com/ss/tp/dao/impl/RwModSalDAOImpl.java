package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.RwModSalDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwModSalVO;
import com.ss.tp.model.RwModSal;

public class RwModSalDAOImpl extends HibernateDaoSupport implements
		RwModSalDAO, Serializable {
	List rwList = new ArrayList();

	public RwModSalDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		RwModSal rw = new RwModSal();
		try {

			BeanUtils.copyProperties(rw, rwmodsalvo);
			if (rwmodsalvo.getNewSal() == null) {
				rw.setNewSal(null);
			}
			if (rwmodsalvo.getOldSal() == null) {
				rw.setOldSal(null);
			}
			if (rwmodsalvo.getTotalAmt() == null) {
				rw.setTotalAmt(null);
			}
			if (rwmodsalvo.getSeqData() == null) {
				rw.setSeqData(null);
			}
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwModSal loadRwHealth(RwModSalVO rwVo) {
		RwModSal rwp = new RwModSal();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (RwModSal) this.getHibernateTemplate().load(RwModSal.class,
					rwVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		RwModSal rw = new RwModSal();
		try {
			BeanUtils.copyProperties(rw, rwmodsalvo);
			rw = this.loadRwHealth(rwmodsalvo);
			rw.setFlagPr(rwmodsalvo.getFlagPr());
			rw.setYearWork(rwmodsalvo.getYearWork());
			rw.setPeriodWork(rwmodsalvo.getPeriodWork());
			rw.setStartDateNew(rwmodsalvo.getStartDateNew());
			rw.setFiscalYear(rwmodsalvo.getFiscalYear());
			rw.setStartDateEdun(rwmodsalvo.getStartDateEdun());
			rw.setEndDateEdun(rwmodsalvo.getEndDateEdun());
			rw.setNewSal(rwmodsalvo.getNewSal());
			rw.setOldSal(rwmodsalvo.getOldSal());
			rw.setStartDateBack(rwmodsalvo.getStartDateBack());
			rw.setEndDateBack(rwmodsalvo.getEndDateBack());
			rw.setStartDateJob(rwmodsalvo.getStartDateJob());
			rw.setEndDateJob(rwmodsalvo.getEndDateJob());
			rw.setMultipleLevel(rwmodsalvo.getMultipleLevel());
			rw.setBackStep(rwmodsalvo.getBackStep());
			rw.setUpdBy(rwmodsalvo.getUpdBy());
			rw.setUpdDate(new Date());
			rw.setEduWut(rwmodsalvo.getEduWut());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		RwModSal rw = new RwModSal();
		try {
			BeanUtils.copyProperties(rw, rwmodsalvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwModSalVO rwmodsalvo) {
		this.rwList.add(rwmodsalvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwModSals() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				RwModSalVO rwmodsalvo = (RwModSalVO) this.rwList.get(i);
				if ((rwmodsalvo.getKeySeq() != new Long(0))
						&& rwmodsalvo.getKeySeq() != null
						&& !rwmodsalvo.getKeySeq().equals("")) {
					this.updateRwModSal(rwmodsalvo);
				} else {
					this.insertRwModSal(rwmodsalvo);
				}
			}

			this.clearList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void insertRwModSals(List rwmodsalvolist) throws Exception {
		RwModSal rw = new RwModSal();
		try {
			for (int i = 0; i < rwmodsalvolist.size(); i++) {
				RwModSalVO rwmodsalvo = (RwModSalVO) rwmodsalvolist.get(i);

				BeanUtils.copyProperties(rw, rwmodsalvo);
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

		hql.append(" select distinct rw.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rw.codeSeq ");
		hql.append(" from RwModSal rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
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
			String positionShort = (String) r[4];

			PayRollEmployeeVO ret = new PayRollEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);

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
		hql.append(" from RwModSal rw , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v  ");
		hql.append(" where rw.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rw.ouCode = v.pk.ouCode ");
		hql.append(" and rw.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
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
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc, ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" prEmp.newCodeSeq ");
		hql.append(" from RwModSal rw, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PrEmployee prEmp, ");
		hql.append(" PnPosition pnPos, ");
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
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
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
			String positionShort = (String) r[4];
			String orgCode = (String) r[5];
			String orgDesc = (String) r[6];
			Double codeSeqWork = (Double) r[7];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from RwModSal rw ");
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
		hql.append(" 	from VPrEmployeeSecurity ");
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
				RwModSalVO rw = new RwModSalVO();

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
		sql.append(" from RwModSal p, VPrEmployeeSecurity e ");
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

			// System.out.println("i : " + i.intValue() + " j : " + j.intValue()
			// );

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
		sql.append(" from RwModSal p, VPrEmployeeSecurity e ");
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

	/**
	 * method ����Ѻ�ʴ���§ҹ�����Ż�Ѻ��ا�� �¡���Ѻ (�Թ��͹)
	 * 
	 * @param ouCode
	 * @param userId
	 * @param year
	 * @param period
	 * @return
	 */
	public List findSalaryUpdateRpt(String ouCode, String userId, Integer year,
			Integer period, String flag) throws Exception {
		List rlst = new ArrayList();
		String hql = " select sal.ouCode, sal.yearPr, sal.periodPr, "
				+ " nvl(org.areaCode,org.divCode), org.secCode, nvl(org.areaDesc,org.divDesc), org.secDesc, "
				+ " emp.pk.empCode, emp.refDbPreSuff.prefixName, emp.firstName, emp.lastName, "
				+ " sal.startDateNew, sal.oldSal, sal.newSal, sal.startDateEdun, "
				+ " sal.startDateJob, sal.endDateJob, sal.multipleLevel, sal.backStep,sal.endDateEdun,"
				+ " sal.startDateBack,sal.endDateBack,sal.eduWut "
				+ " from RwModSal sal, PnOrganization org, VPrEmployeeSecurityReport vemp, PnEmployee emp "
				+ " where sal.ouCode = '" + ouCode + "'" + " and sal.yearPr = "
				+ year + " and sal.periodPr = " + period
				+ " and vemp.pk.userId = '" + userId + "'"
				+ " and sal.flagPr like '" + flag + "'"
				+ " and sal.ouCode = org.pk.ouCode "
				+ " and sal.codeSeq = org.pk.codeSeq "
				+ " and sal.empCode = emp.pk.empCode "
				+ " and vemp.pk.empCode = emp.pk.empCode "
				+ " and vemp.pk.year = sal.yearPr "
				+ " and vemp.pk.period = sal.periodPr "
				+ " order by nvl(org.secCode, org.divCode), sal.empCode";
		try {
			List tmpList = this.getHibernateTemplate().find(hql);
			RwModSalVO sal = null;
			for (Iterator itt = tmpList.iterator(); itt.hasNext();) {
				Object[] obj = (Object[]) itt.next();
				sal = new RwModSalVO();
				sal.setOuCode(obj[0] != null ? obj[0].toString() : null);
				sal.setYearPr(obj[1] != null ? new Double(obj[1].toString())
						: null);
				sal.setPeriodPr(obj[2] != null ? new Double(obj[2].toString())
						: null);
				sal.setAreaCode(obj[3] != null ? obj[3].toString() : null);
				sal.setSecCode(obj[4] != null ? obj[4].toString() : null);
				sal.setAreaDesc(obj[5] != null ? obj[5].toString() : null);
				sal.setSecDesc(obj[6] != null ? obj[6].toString() : null);
				sal.setEmpCode(obj[7] != null ? obj[7].toString() : null);
				sal.setPreFix(obj[8] != null ? obj[8].toString() : null);
				sal.setFirstName(obj[9] != null ? obj[9].toString() : null);
				sal.setLastName(obj[10] != null ? obj[10].toString() : null);
				sal.setStartDateNew(obj[11] != null ? (Date) obj[11] : null);
				sal.setOldSal(obj[12] != null ? new Double(obj[12].toString())
						: null);
				sal.setNewSal(obj[13] != null ? new Double(obj[13].toString())
						: null);
				sal.setStartDateEdun(obj[14] != null ? (Date) obj[14] : null);
				sal.setStartDateJob(obj[15] != null ? (Date) obj[15] : null);
				sal.setEndDateJob(obj[16] != null ? (Date) obj[16] : null);
				sal.setMultipleLevel(obj[17] != null ? obj[17].toString()
						: null);
				sal.setBackStep(obj[18] != null ? obj[18].toString() : null);

				sal.setEndDateEdun(obj[19] != null ? (Date) obj[19] : null);
				sal.setStartDateBack(obj[20] != null ? (Date) obj[20] : null);
				sal.setEndDateBack(obj[21] != null ? (Date) obj[21] : null);
				sal.setEduWut(obj[22] != null ? obj[22].toString() : null);
				rlst.add(sal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}
}