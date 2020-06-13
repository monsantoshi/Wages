package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgConfirmDataDAO;
import com.ss.tp.dao.WgPrEmployeeTextDAO;
import com.ss.tp.dto.IncomeDeductVO;
import com.ss.tp.dto.WgPrEmployeeTextVO;
import com.ss.tp.model.PrEmployeeText;
import com.ss.tp.model.PrEmployeeTextForSort;
import com.ss.tp.model.WgPrEmployeeText;

public class WgPrEmployeeTextDAOImpl extends HibernateDaoSupport implements
		WgPrEmployeeTextDAO, Serializable {
	private JdbcTemplate jdbcTemplate;
	private WgConfirmDataDAO wgConfirmDataDAO;

	public WgConfirmDataDAO getWgConfirmDataDAO() {
		return wgConfirmDataDAO;
	}

	public void setWgConfirmDataDAO(WgConfirmDataDAO wgConfirmDataDAO) {
		this.wgConfirmDataDAO = wgConfirmDataDAO;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertWgPrEmployeeText(WgPrEmployeeTextVO empTxtVo)
			throws Exception {
		WgPrEmployeeText pr = new WgPrEmployeeText();
		try {
			// BeanUtils.copyProperties(pr ,empTxtVo);
			if (empTxtVo.getAdjNewsal() != null
					&& empTxtVo.getAdjNewsal().trim().length() > 0)
				pr.setAdjNewsal(Double.valueOf(empTxtVo.getAdjNewsal()));
			else
				pr.setAdjNewsal(null);

			if (empTxtVo.getAdjOldsal() != null
					&& empTxtVo.getAdjOldsal().trim().length() > 0)
				pr.setAdjOldsal(Double.valueOf(empTxtVo.getAdjOldsal()));
			else
				pr.setAdjOldsal(null);

			if (empTxtVo.getBankId() != null
					&& empTxtVo.getBankId().trim().length() > 0)
				pr.setBankId(empTxtVo.getBankId());
			else
				pr.setBankId(null);

			if (empTxtVo.getChildNoStudy() != null
					&& empTxtVo.getChildNoStudy().trim().length() > 0)
				pr.setChildNoStudy(Integer.valueOf(empTxtVo.getChildNoStudy()));
			else
				pr.setChildNoStudy(null);

			if (empTxtVo.getChildStudy() != null
					&& empTxtVo.getChildStudy().trim().length() > 0)
				pr.setChildStudy(Integer.valueOf(empTxtVo.getChildStudy()));
			else
				pr.setChildStudy(null);

			if (empTxtVo.getCodeSeqWork() != null
					&& empTxtVo.getCodeSeqWork().trim().length() > 0)
				pr.setCodeSeqWork(Integer.valueOf(empTxtVo.getCodeSeqWork()));
			else
				pr.setCodeSeqWork(null);

			if (empTxtVo.getCompensateLabour() != null
					&& empTxtVo.getCompensateLabour().trim().length() > 0)
				pr.setCompensateLabour(Double.valueOf(empTxtVo
						.getCompensateLabour()));
			else
				pr.setCompensateLabour(null);

			pr.setConfirmFlag("N");

			if (empTxtVo.getCostChild() != null
					&& empTxtVo.getCostChild().trim().length() > 0)
				pr.setCostChild(Integer.valueOf(empTxtVo.getCostChild()));
			else
				pr.setCostChild(null);

			if (empTxtVo.getCostLife() != null
					&& empTxtVo.getCostLife().trim().length() > 0)
				pr.setCostLife(Double.valueOf(empTxtVo.getCostLife()));
			else
				pr.setCostLife(null);

			if (empTxtVo.getDebtLife() != null
					&& empTxtVo.getDebtLife().trim().length() > 0)
				pr.setDebtLife(Double.valueOf(empTxtVo.getDebtLife()));
			else
				pr.setDebtLife(null);

			if (empTxtVo.getDebtLoan() != null
					&& empTxtVo.getDebtLoan().trim().length() > 0)
				pr.setDebtLoan(Double.valueOf(empTxtVo.getDebtLoan()));
			else
				pr.setDebtLoan(null);

			if (empTxtVo.getDeductAmt() != null
					&& empTxtVo.getDeductAmt().trim().length() > 0)
				pr.setDeductAmt(Double.valueOf(empTxtVo.getDeductAmt()));
			else
				pr.setDeductAmt(null);

			if (empTxtVo.getDonate() != null
					&& empTxtVo.getDonate().trim().length() > 0)
				pr.setDonate(Double.valueOf(empTxtVo.getDonate()));
			else
				pr.setDonate(null);

			if (empTxtVo.getEmpCode() != null
					&& empTxtVo.getEmpCode().trim().length() > 0)
				pr.setEmpCode(empTxtVo.getEmpCode());
			else
				pr.setEmpCode(null);

			if (empTxtVo.getFlagFather() != null
					&& empTxtVo.getFlagFather().trim().length() > 0)
				pr.setFlagFather(empTxtVo.getFlagFather());
			else
				pr.setFlagFather(null);

			if (empTxtVo.getFlagFatherSpouse() != null
					&& empTxtVo.getFlagFatherSpouse().trim().length() > 0)
				pr.setFlagFatherSpouse(empTxtVo.getFlagFatherSpouse());
			else
				pr.setFlagFatherSpouse(null);

			if (empTxtVo.getFlagMother() != null
					&& empTxtVo.getFlagMother().trim().length() > 0)
				pr.setFlagMother(empTxtVo.getFlagMother());
			else
				pr.setFlagMother(null);

			if (empTxtVo.getFlagMotherSpouse() != null
					&& empTxtVo.getFlagMotherSpouse().trim().length() > 0)
				pr.setFlagMotherSpouse(empTxtVo.getFlagMotherSpouse());
			else
				pr.setFlagMotherSpouse(null);

			if (empTxtVo.getFlagPr() != null
					&& empTxtVo.getFlagPr().trim().length() > 0)
				pr.setFlagPr(empTxtVo.getFlagPr());
			else
				pr.setFlagPr(null);

			if (empTxtVo.getFlagStatus() != null
					&& empTxtVo.getFlagStatus().trim().length() > 0)
				pr.setFlagStatus(empTxtVo.getFlagStatus());
			else
				pr.setFlagStatus(null);

			if (empTxtVo.getGundanAmt() != null
					&& empTxtVo.getGundanAmt().trim().length() > 0)
				pr.setGundanAmt(Double.valueOf(empTxtVo.getGundanAmt()));
			else
				pr.setGundanAmt(null);

			if (empTxtVo.getGundanFlag() != null
					&& empTxtVo.getGundanFlag().trim().length() > 0)
				pr.setGundanFlag(empTxtVo.getGundanFlag());
			else
				pr.setGundanFlag(null);

			if (empTxtVo.getIncomeTax() != null
					&& empTxtVo.getIncomeTax().trim().length() > 0)
				pr.setIncomeTax(Double.valueOf(empTxtVo.getIncomeTax()));
			else
				pr.setIncomeTax(null);

			// pr.setKeySeq(Long.parseLong(empTxtVo.getKeySeq()));

			if (empTxtVo.getLtf() != null
					&& empTxtVo.getLtf().trim().length() > 0)
				pr.setLtf(Double.valueOf(empTxtVo.getLtf()));
			else
				pr.setLtf(null);

			if (empTxtVo.getMarriedStatus() != null
					&& empTxtVo.getMarriedStatus().trim().length() > 0)
				pr.setMarriedStatus(empTxtVo.getMarriedStatus());
			else
				pr.setMarriedStatus(null);

			if (empTxtVo.getNewSalary() != null
					&& empTxtVo.getNewSalary().trim().length() > 0)
				pr.setNewSalary(Double.valueOf(empTxtVo.getNewSalary()));
			else
				pr.setNewSalary(null);

			if (empTxtVo.getOldSalary() != null
					&& empTxtVo.getOldSalary().trim().length() > 0)
				pr.setOldSalary(Double.valueOf(empTxtVo.getOldSalary()));
			else
				pr.setOldSalary(null);

			if (empTxtVo.getOther() != null
					&& empTxtVo.getOther().trim().length() > 0)
				pr.setOther(Double.valueOf(empTxtVo.getOther()));
			else
				pr.setOther(null);

			if (empTxtVo.getOuCode() != null
					&& empTxtVo.getOuCode().trim().length() > 0)
				pr.setOuCode(empTxtVo.getOuCode());
			else
				pr.setOuCode(null);

			if (empTxtVo.getOverage() != null
					&& empTxtVo.getOverage().trim().length() > 0)
				pr.setOverage(Double.valueOf(empTxtVo.getOverage()));
			else
				pr.setOverage(null);

			if (empTxtVo.getOverageSpouse() != null
					&& empTxtVo.getOverageSpouse().trim().length() > 0)
				pr.setOverageSpouse(Double.valueOf(empTxtVo.getOverageSpouse()));
			else
				pr.setOverageSpouse(null);

			if (empTxtVo.getPayStatus() != null
					&& empTxtVo.getPayStatus().trim().length() > 0)
				pr.setPayStatus(empTxtVo.getPayStatus());
			else
				pr.setPayStatus(null);

			if (empTxtVo.getPensionFund() != null
					&& empTxtVo.getPensionFund().trim().length() > 0)
				pr.setPensionFund(Double.valueOf(empTxtVo.getPensionFund()));
			else
				pr.setPensionFund(null);

			if (empTxtVo.getPeriod() != null
					&& empTxtVo.getPeriod().trim().length() > 0)
				pr.setPeriod(Integer.valueOf(empTxtVo.getPeriod()));
			else
				pr.setPeriod(null);

			if (empTxtVo.getRmf() != null
					&& empTxtVo.getRmf().trim().length() > 0)
				pr.setRmf(Double.valueOf(empTxtVo.getRmf()));
			else
				pr.setRmf(null);

			if (empTxtVo.getSeqData() != null
					&& empTxtVo.getSeqData().trim().length() > 0)
				pr.setSeqData(Integer.valueOf(empTxtVo.getSeqData()));
			else
				pr.setSeqData(null);

			if (empTxtVo.getTaxId() != null
					&& empTxtVo.getTaxId().trim().length() > 0)
				pr.setTaxId(empTxtVo.getTaxId());
			else
				pr.setTaxId(null);

			if (empTxtVo.getTeacherFund() != null
					&& empTxtVo.getTeacherFund().trim().length() > 0)
				pr.setTeacherFund(Double.valueOf(empTxtVo.getTeacherFund()));
			else
				pr.setTeacherFund(null);

			if (empTxtVo.getYear() != null
					&& empTxtVo.getYear().trim().length() > 0)
				pr.setYear(Integer.valueOf(empTxtVo.getYear()));
			else
				pr.setYear(null);

			if (empTxtVo.getCreBy() != null
					&& empTxtVo.getCreBy().trim().length() > 0)
				pr.setCreBy(empTxtVo.getCreBy());
			else
				pr.setCreBy(null);

			if (empTxtVo.getUpdBy() != null
					&& empTxtVo.getUpdBy().trim().length() > 0)
				pr.setUpdBy(empTxtVo.getUpdBy());
			else
				pr.setUpdBy(null);

			pr.setCreDate(new Date());
			pr.setUpdDate(new Date());
			this.getHibernateTemplate().save(pr);
			// this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePrEmployeeText(WgPrEmployeeTextVO empTxtVo)
			throws Exception {
		PrEmployeeText pr = new PrEmployeeText();

		try {
			BeanUtils.copyProperties(pr, empTxtVo);

			pr.setCreDate(new Date());
			pr.setUpdDate(new Date());

			this.getHibernateTemplate().saveOrUpdate(pr);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deletePrEmployeeText(WgPrEmployeeTextVO empTxtVo)
			throws Exception {
		PrEmployeeText pr = new PrEmployeeText();

		try {
			BeanUtils.copyProperties(pr, empTxtVo);

			this.getHibernateTemplate().delete(pr);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		/*
		 * StringBuffer sql = new StringBuffer(0); sql.append(" select count(*)
		 * "); sql.append(" from WgPrEmployeeText p, VPnOrganizationSecurity e
		 * "); sql.append(" where p.ouCode = '"+ouCode+"' "); sql.append(" and
		 * p.year = " + year); sql.append(" and p.period = " + period);
		 * sql.append(" and p.confirmFlag = 'Y' ");
		 * sql.append(" and e.pk.userId = '" + userId + "' ");
		 * //sql.append(" and p.empCode = e.pk.empCode ");
		 * sql.append(" and p.ouCode = e.pk.ouCode "); sql.append(" and
		 * p.codeSeqWork = e.pk.codeSeq "); //sql.append(" and p.year =
		 * e.pk.year "); //sql.append(" and p.period = e.pk.period ");
		 * 
		 * List ls = this.getHibernateTemplate().find( sql.toString() );
		 * System.out
		 * .println(year+ouCode+period+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		 * sql.setLength(0); sql.append(" select count(*) "); sql.append(" from
		 * WgPeriodLine "); sql.append(" where wgPeriodLinePK.year = " + year);
		 * sql.append(" and wgPeriodLinePK.ouCode = '" + ouCode + "' ");
		 * sql.append(" and wgPeriodLinePK.period = " + period ); sql.append("
		 * and mainClose = 'Y' ");
		 * 
		 * List ls2 = this.getHibernateTemplate().find( sql.toString() );
		 * 
		 * if( ls != null && ls.size() > 0 && ls2 != null && ls2.size() > 0 ){
		 * Integer i = (Integer)ls.get(0); Integer j = (Integer)ls2.get(0);
		 * 
		 * //System.out.println("i : " + i.intValue() + " j : " + j.intValue()
		 * );
		 * 
		 * if( i.intValue() > 0 || j.intValue() > 0 ) return true; else return
		 * false; }else return true;
		 */
		return this.wgConfirmDataDAO.isConfirmMasterData(ouCode, year, period,
				userId);

	}

	public List updateConfirmIncome(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List incomeList = new ArrayList();

		PrEmployeeTextForSort prEmpForSort = new PrEmployeeTextForSort();

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql1.append(" from WgPrIncDecOther wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql1.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql1.append(" and wgpr.yearPr = " + yearPr);
		hql1.append(" and wgpr.periodPr = " + periodPr);
		hql1.append(" and vwg.pk.userId = '" + userId + "' ");
		hql1.append(" and ( wgpr.confirmFlag <> 'Y' or wgpr.confirmFlag is null ) ");
		hql1.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql1.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql1.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql1.append(" and wgpr.yearPr = vwg.pk.year ");
		hql1.append(" and wgpr.periodPr = vwg.pk.period ");
		hql1.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql1.append(" and wgpr.incDecCode = wg.wgIncomeDeductPK.incDecCode ");
		hql1.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		List incomeList1 = this.getSession().createQuery(hql1.toString())
				.list();
		System.out.println("incomeList1 size : " + incomeList1.size());
		if ((incomeList1 != null) && (incomeList1.size() > 0)) {
			for (Iterator it = incomeList1.iterator(); it.hasNext();) {

				// incomeList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				incomeList.add(prEmpForSort);

			}
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql2.append(" from WgPrOvertime wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql2.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql2.append(" and wgpr.yearPr = " + yearPr);
		hql2.append(" and wgpr.periodPr = " + periodPr);
		hql2.append(" and vwg.pk.userId = '" + userId + "' ");
		hql2.append(" and ( wgpr.confirmFlag <> 'Y' or wgpr.confirmFlag is null ) ");
		hql2.append(" and wg.wgIncomeDeductPK.incDecCode = '11' ");
		hql2.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql2.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql2.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql2.append(" and wgpr.yearPr = vwg.pk.year ");
		hql2.append(" and wgpr.periodPr = vwg.pk.period ");
		hql2.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql2.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		Object incomeList2 = this.getSession().createQuery(hql2.toString())
				.uniqueResult();

		// incomeList.add(incomeList2);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList2 != null && !incomeList2.equals("")) {
			Object[] rt = (Object[]) incomeList2;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql3 = new StringBuffer();
		hql3.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql3.append(" from WgPrWorkDay wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql3.append(" where wgpr.wgPrWorkDayPK.ouCode = '" + ouCode + "' ");
		hql3.append(" and wgpr.wgPrWorkDayPK.year = " + yearPr);
		hql3.append(" and wgpr.wgPrWorkDayPK.period = " + periodPr);
		hql3.append(" and vwg.pk.userId = '" + userId + "' ");
		hql3.append(" and ( wgpr.confirmFlag <> 'Y' or wgpr.confirmFlag is null ) ");
		hql3.append(" and wg.wgIncomeDeductPK.incDecCode = '10' ");
		hql3.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql3.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql3.append(" and wgpr.wgPrWorkDayPK.ouCode = vwg.pk.ouCode ");
		hql3.append(" and wgpr.wgPrWorkDayPK.year = vwg.pk.year ");
		hql3.append(" and wgpr.wgPrWorkDayPK.period = vwg.pk.period ");
		hql3.append(" and wgpr.wgPrWorkDayPK.empCode = vwg.pk.empCode ");
		hql3.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		Object incomeList3 = this.getSession().createQuery(hql3.toString())
				.uniqueResult();
		// incomeList.add(incomeList3);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList3 != null && !incomeList3.equals("")) {
			Object[] rt = (Object[]) incomeList3;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql4 = new StringBuffer();
		hql4.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql4.append(" from WgPrIncDecOther wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql4.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql4.append(" and wgpr.yearPr = " + yearPr);
		hql4.append(" and wgpr.periodPr = " + periodPr);
		hql4.append(" and vwg.pk.userId = '" + userId + "' ");
		hql4.append(" and ( wgpr.confirmFlag <> 'Y' or wgpr.confirmFlag is null ) ");
		hql4.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql4.append(" and wg.wgIncomeDeductPK.groupCode = '2' ");
		hql4.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql4.append(" and wgpr.yearPr = vwg.pk.year ");
		hql4.append(" and wgpr.periodPr = vwg.pk.period ");
		hql4.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql4.append(" and wgpr.incDecCode = wg.wgIncomeDeductPK.incDecCode ");
		hql4.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		List deductList1 = this.getSession().createQuery(hql4.toString())
				.list();

		System.out.println("deductList1 : " + deductList1.size());

		if ((deductList1 != null) && (deductList1.size() > 0)) {
			for (Iterator it = deductList1.iterator(); it.hasNext();) {

				// incomeList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				incomeList.add(prEmpForSort);

			}
		}

		Collections.sort(incomeList);

		List retList = new ArrayList();
		int i = 0;
		Double cntOt;
		if ((incomeList != null) && (incomeList.size() > 0)) {
			for (Iterator it = incomeList.iterator(); it.hasNext();) {
				PrEmployeeTextForSort r = (PrEmployeeTextForSort) it.next();
				if (r != null) {
					String incDecName = (r.getName() != null) ? (String) r
							.getName() : "";
					// Integer cntOt = (r.getCount() != null) ? r.getCount() :
					// new Integer(0) ;
					if (r.getCount() != null) {
						cntOt = r.getCount();
					} else {
						cntOt = new Double("0");
						;
					}
					IncomeDeductVO incDecVo = new IncomeDeductVO();
					incDecVo.setIncDecName(incDecName);
					incDecVo.setCountOt(cntOt);
					retList.add(i, incDecVo);
					i++;
				}
			}
		}

		System.out.println("count completed ...");

		// update confirm flag
		// updateRwModSalIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwOvertimeIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwPremiumIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwHealthIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwDangerIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwIncDecOtherIncome(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);

		return retList;
	}

	public List deleteConfirmIncome(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List incomeList = new ArrayList();

		PrEmployeeTextForSort prEmpForSort = new PrEmployeeTextForSort();

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql1.append(" from WgPrIncDecOther wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql1.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql1.append(" and wgpr.yearPr = " + yearPr);
		hql1.append(" and wgpr.periodPr = " + periodPr);
		hql1.append(" and vwg.pk.userId = '" + userId + "' ");
		hql1.append(" and wgpr.confirmFlag = 'Y' ");
		hql1.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql1.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql1.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql1.append(" and wgpr.yearPr = vwg.pk.year ");
		hql1.append(" and wgpr.periodPr = vwg.pk.period ");
		hql1.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql1.append(" and wgpr.incDecCode = wg.wgIncomeDeductPK.incDecCode ");
		hql1.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		List incomeList1 = this.getSession().createQuery(hql1.toString())
				.list();
		System.out.println("incomeList1 size : " + incomeList1.size());
		if ((incomeList1 != null) && (incomeList1.size() > 0)) {
			for (Iterator it = incomeList1.iterator(); it.hasNext();) {

				// incomeList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				incomeList.add(prEmpForSort);

			}
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql2.append(" from WgPrOvertime wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql2.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql2.append(" and wgpr.yearPr = " + yearPr);
		hql2.append(" and wgpr.periodPr = " + periodPr);
		hql2.append(" and vwg.pk.userId = '" + userId + "' ");
		hql2.append(" and wgpr.confirmFlag = 'Y' ");
		hql2.append(" and wg.wgIncomeDeductPK.incDecCode = '11' ");
		hql2.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql2.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql2.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql2.append(" and wgpr.yearPr = vwg.pk.year ");
		hql2.append(" and wgpr.periodPr = vwg.pk.period ");
		hql2.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql2.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		Object incomeList2 = this.getSession().createQuery(hql2.toString())
				.uniqueResult();

		// incomeList.add(incomeList2);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList2 != null && !incomeList2.equals("")) {
			Object[] rt = (Object[]) incomeList2;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql3 = new StringBuffer();
		hql3.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql3.append(" from WgPrWorkDay wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql3.append(" where wgpr.wgPrWorkDayPK.ouCode = '" + ouCode + "' ");
		hql3.append(" and wgpr.wgPrWorkDayPK.year = " + yearPr);
		hql3.append(" and wgpr.wgPrWorkDayPK.period = " + periodPr);
		hql3.append(" and vwg.pk.userId = '" + userId + "' ");
		hql3.append(" and wgpr.confirmFlag = 'Y' ");
		hql3.append(" and wg.wgIncomeDeductPK.incDecCode = '10' ");
		hql3.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql3.append(" and wg.wgIncomeDeductPK.groupCode = '1' ");
		hql3.append(" and wgpr.wgPrWorkDayPK.ouCode = vwg.pk.ouCode ");
		hql3.append(" and wgpr.wgPrWorkDayPK.year = vwg.pk.year ");
		hql3.append(" and wgpr.wgPrWorkDayPK.period = vwg.pk.period ");
		hql3.append(" and wgpr.wgPrWorkDayPK.empCode = vwg.pk.empCode ");
		hql3.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		Object incomeList3 = this.getSession().createQuery(hql3.toString())
				.uniqueResult();
		// incomeList.add(incomeList3);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList3 != null && !incomeList3.equals("")) {
			Object[] rt = (Object[]) incomeList3;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		Collections.sort(incomeList);

		List retList = new ArrayList();
		int i = 0;
		Double cntOt;
		if ((incomeList != null) && (incomeList.size() > 0)) {
			for (Iterator it = incomeList.iterator(); it.hasNext();) {
				PrEmployeeTextForSort r = (PrEmployeeTextForSort) it.next();
				if (r != null) {
					String incDecName = (r.getName() != null) ? (String) r
							.getName() : "";
					// Integer cntOt = (r.getCount() != null) ? r.getCount() :
					// new Integer(0) ;
					if (r.getCount() != null) {
						cntOt = r.getCount();
					} else {
						cntOt = new Double("0");
						;
					}
					IncomeDeductVO incDecVo = new IncomeDeductVO();
					incDecVo.setIncDecName(incDecName);
					incDecVo.setCountOt(cntOt);
					retList.add(i, incDecVo);
					i++;
				}
			}
		}

		System.out.println("count completed ...");

		return retList;
	}

	public void updateWgPrOvertimeIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Wg_Pr_Overtime rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteWgPrOvertimeIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Wg_Pr_Overtime rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void updateWgPrWorkDayIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Wg_Pr_Work_Day rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year = " + year);
			sql.append(" and rw.period = " + period);
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteWgPrWorkDayIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Wg_Pr_Work_Day rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year = " + year);
			sql.append(" and rw.period = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void updateWgPrIncDecOtherIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Wg_Pr_Inc_Dec_Other rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.group_code = '1' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteWgPrIncDecOtherIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Wg_Pr_Inc_Dec_Other rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.group_code = '1' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List updateConfirmDeduct(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List deductList = new ArrayList();
		PrEmployeeTextForSort prEmpForSort = new PrEmployeeTextForSort();

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql1.append(" from WgPrIncDecOther wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql1.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql1.append(" and wgpr.yearPr = " + yearPr);
		hql1.append(" and wgpr.periodPr = " + periodPr);
		hql1.append(" and vwg.pk.userId = '" + userId + "' ");
		hql1.append(" and ( wgpr.confirmFlag <> 'Y' or wgpr.confirmFlag is null ) ");
		hql1.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql1.append(" and wg.wgIncomeDeductPK.groupCode = '2' ");
		hql1.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql1.append(" and wgpr.yearPr = vwg.pk.year ");
		hql1.append(" and wgpr.periodPr = vwg.pk.period ");
		hql1.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql1.append(" and wgpr.incDecCode = wg.wgIncomeDeductPK.incDecCode ");
		hql1.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		List deductList1 = this.getSession().createQuery(hql1.toString())
				.list();

		System.out.println("deductList1 : " + deductList1.size());

		if ((deductList1 != null) && (deductList1.size() > 0)) {
			for (Iterator it = deductList1.iterator(); it.hasNext();) {

				// incomeList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				deductList.add(prEmpForSort);

			}
		}

		Collections.sort(deductList);

		List retList = new ArrayList();
		int i = 0;
		Double cntOt;

		if ((deductList != null) && (deductList.size() > 0)) {
			for (Iterator it = deductList.iterator(); it.hasNext();) {
				PrEmployeeTextForSort r = (PrEmployeeTextForSort) it.next();
				if (r != null) {
					String incDecName = (r.getName() != null) ? (String) r
							.getName() : "";
					// Integer cntOt = (r.getCount() != null) ? r.getCount() :
					// new Integer(0) ;
					if (r.getCount() != null) {
						cntOt = r.getCount();
					} else {
						cntOt = new Double("0");
						;
					}
					IncomeDeductVO incDecVo = new IncomeDeductVO();
					incDecVo.setIncDecName(incDecName);
					incDecVo.setCountOt(cntOt);
					retList.add(i, incDecVo);
					i++;
				}
			}
		}

		System.out.println("deduct size : " + retList.size());
		System.out.println("count deduct completed ...");

		// update confirm flag
		// updateRwVinaiDeduct(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwVinai2Deduct(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);
		// updateRwIncDecOtherDeduct(ouCode, String.valueOf(yearPr),
		// String.valueOf(periodPr), userId);

		return retList;
	}

	public List deleteConfirmDeduct(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List deductList = new ArrayList();

		PrEmployeeTextForSort prEmpForSort = new PrEmployeeTextForSort();

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select wg.wgIncomeDeductPK.incDecCode, wg.incDecName , count(*) as cntOt ");
		hql1.append(" from WgPrIncDecOther wgpr, VWgPrEmployeeSecurity vwg , WgIncomeDeduct wg ");
		hql1.append(" where wgpr.ouCode = '" + ouCode + "' ");
		hql1.append(" and wgpr.yearPr = " + yearPr);
		hql1.append(" and wgpr.periodPr = " + periodPr);
		hql1.append(" and vwg.pk.userId = '" + userId + "' ");
		hql1.append(" and wgpr.confirmFlag = 'Y' ");
		hql1.append(" and wg.wgIncomeDeductPK.ouCode = '" + ouCode + "' ");
		hql1.append(" and wg.wgIncomeDeductPK.groupCode = '2' ");
		hql1.append(" and wgpr.ouCode = vwg.pk.ouCode ");
		hql1.append(" and wgpr.yearPr = vwg.pk.year ");
		hql1.append(" and wgpr.periodPr = vwg.pk.period ");
		hql1.append(" and wgpr.empCode = vwg.pk.empCode ");
		hql1.append(" and wgpr.incDecCode = wg.wgIncomeDeductPK.incDecCode ");
		hql1.append(" group by wg.wgIncomeDeductPK.incDecCode, wg.incDecName ");
		List deductList1 = this.getSession().createQuery(hql1.toString())
				.list();

		if ((deductList1 != null) && (deductList1.size() > 0)) {
			for (Iterator it = deductList1.iterator(); it.hasNext();) {

				// incomeList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				deductList.add(prEmpForSort);

			}
		}

		Collections.sort(deductList);

		List retList = new ArrayList();
		int i = 0;
		Double cntOt;

		if ((deductList != null) && (deductList.size() > 0)) {
			for (Iterator it = deductList.iterator(); it.hasNext();) {
				PrEmployeeTextForSort r = (PrEmployeeTextForSort) it.next();
				if (r != null) {
					String incDecName = (r.getName() != null) ? (String) r
							.getName() : "";
					// Integer cntOt = (r.getCount() != null) ? r.getCount() :
					// new Integer(0) ;
					if (r.getCount() != null) {
						cntOt = r.getCount();
					} else {
						cntOt = new Double("0");
						;
					}
					IncomeDeductVO incDecVo = new IncomeDeductVO();
					incDecVo.setIncDecName(incDecName);
					incDecVo.setCountOt(cntOt);
					retList.add(i, incDecVo);
					i++;
				}
			}
		}

		return retList;
	}

	public void updateWgPrIncDecOtherDeduct(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Wg_Pr_Inc_Dec_Other rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.group_code = '2' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteWgPrIncDecOtherDeduct(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Wg_Pr_Inc_Dec_Other rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.group_code = '2' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from V_Wg_Pr_Employee_Security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and year = " + year);
			sql.append("	and period = " + period);
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public Integer confirmData(String ouCode, Long yearPr, Long periodPr,
			String flagStatus, String userId) {
		try {
			Integer countData = new Integer(0);

			StringBuffer hql1 = new StringBuffer();
			hql1.append(" select count(*) ");
			hql1.append(" from WgPrEmployeeText pr ");
			hql1.append(" where pr.ouCode = '");
			hql1.append(ouCode);
			hql1.append("' ");
			hql1.append(" and pr.year = ");
			hql1.append(new Double(yearPr.doubleValue()));
			hql1.append(" and pr.period = ");
			hql1.append(new Double(periodPr.doubleValue()));
			hql1.append(" and pr.flagStatus = '");
			hql1.append(flagStatus);
			hql1.append("' ");
			hql1.append(" and ( pr.confirmFlag <> 'Y' or pr.confirmFlag is null ) ");
			hql1.append(" and ( pr.empCode in ( ");
			hql1.append("	select pk.empCode ");
			hql1.append("	from VWgPrEmployeeSecurity ");
			hql1.append("	where pk.ouCode = '" + ouCode + "' ");
			hql1.append("	and pk.year = " + yearPr);
			hql1.append("	and pk.period = " + periodPr);
			hql1.append("	and pk.userId = '" + userId + "' ");
			hql1.append(" 	) ");
			hql1.append("   or pr.codeSeqWork in ( ");
			hql1.append("	 	select pk.codeSeq ");
			hql1.append("		from VPnOrganizationSecurity ");
			hql1.append("		where pk.userId = '" + userId + "' ");
			hql1.append("		and pk.ouCode = '" + ouCode + "' ");
			hql1.append("	) ");
			hql1.append(" ) ");

			Object confirmDataObj = this.getSession()
					.createQuery(hql1.toString()).uniqueResult();

			if (confirmDataObj != null) {
				countData = (Integer) confirmDataObj;
			}

			return countData;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void updateConfirmData(String ouCode, Long yearPr, Long periodPr,
			String userId) throws Exception {
		StringBuffer hql = new StringBuffer();

		try {
			hql.append(" UPDATE Wg_Pr_Employee_Text pr ");
			hql.append(" SET pr.confirm_Flag = 'Y', ");
			hql.append(" pr.upd_date = sysdate, ");
			hql.append(" pr.upd_by = '" + userId + "' ");
			hql.append(" where pr.ou_Code = '");
			hql.append(ouCode);
			hql.append("' ");
			hql.append(" and pr.year = ");
			hql.append(yearPr);
			hql.append(" and pr.period = ");
			hql.append(periodPr);
			hql.append(" and ( pr.emp_code in ( ");
			hql.append("	select emp_code ");
			hql.append("	from v_wg_pr_employee_security ");
			hql.append("	where ou_code = '" + ouCode + "' ");
			hql.append("	and year = " + yearPr);
			hql.append("	and period = " + periodPr);
			hql.append("	and user_id = '" + userId + "' ");
			hql.append(" ) ");
			hql.append(" or pr.code_seq_work in ( ");
			hql.append(" 	select code_seq ");
			hql.append("	from v_pn_organization_security ");
			hql.append("	where ou_code = '" + ouCode + "' ");
			hql.append("	and user_id = '" + userId + "' ");
			hql.append(" ) ");
			hql.append(" ) ");

			// System.out.println(" 000000000000000 Hql updateConfirmData :
			// "+hql.toString());

			this.jdbcTemplate.execute(hql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table V_WG_PR_EMP_WORK_TEXT_REP
	 * 
	 * @param userId
	 * @param ouCode
	 * @param year
	 * @param period
	 * @return
	 */
	public List findChangeOfMonth(String ouCode, String userId, Integer year,
			Integer period) {
		List list = new ArrayList();
		List listemp = new ArrayList();
		String sql = "";
		sql = " select prm.ouCode,prm.year,prm.period,pno.divCode,pno.divDesc,"
				+ " pno.secCode,pno.secDesc,"
				+ " emp.wgEmployeePK.empCode,"
				+ " emp.refDbPreSuff.prefixName,"
				+ " emp.firstName,"
				+ " emp.lastName,"
				+ " prm.taxId,"
				+ " prm.marriedStatus,"
				+ " prm.payStatus,"
				+ " prm.bankId,"
				+ " prm.costChild,"
				+ " prm.childStudy,"
				+ " prm.childNoStudy,"
				+ " prm.costLife,"
				+ " prm.gundanFlag,"
				+ " prm.debtLife,"
				+ " prm.debtLoan,"
				+ " prm.donate,"
				+ " prm.flagFather,"
				+ " prm.flagMother,"
				+ " prm.flagFatherSpouse,"
				+ " prm.flagMotherSpouse,"
				+ " prm.ltf,"
				+ " prm.rmf,"
				+ " prm.flagStatus,"
				+ " pno.areaCode,"
				+ " pno.areaDesc  "
				+ " from VWgPrEmpTextRep prm ,PnOrganization pno,WgEmployee emp "
				+ " where prm.ouCode ='"
				+ ouCode
				+ "'"
				+ " and prm.year="
				+ year
				+ " and prm.period="
				+ period
				+ " and prm.ouCode = pno.pk.ouCode "
				+ " and prm.empCode =emp.wgEmployeePK.empCode "
				+ " and prm.codeSeqWork = pno.pk.codeSeq "
				+ " and prm.codeSeqWork in ( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '"
				+ userId
				+ "'"
				+ " ) "
				+ " order by nvl(pno.secCode,nvl(pno.areaCode,nvl(pno.divCode,''))),emp.wgEmployeePK.empCode ";
		Session ss = this.getSession();
		Query qq = ss.createQuery(sql);
		list = qq.list();
		int costChild = 0;
		int childStudy = 0;
		int childNoStudy = 0;

		WgPrEmployeeTextVO wgEmpVo = null;
		for (int i = 0; i < list.size(); i++) {
			costChild = 0;
			childStudy = 0;
			childNoStudy = 0;
			Object[] obj = (Object[]) list.get(i);
			wgEmpVo = new WgPrEmployeeTextVO();
			wgEmpVo.setOuCode(obj[0] == null ? "" : obj[0].toString());
			wgEmpVo.setYear(obj[1] == null ? "" : obj[1].toString());
			wgEmpVo.setPeriod(obj[2] == null ? "" : obj[2].toString());
			wgEmpVo.setDivCode(obj[3] == null ? "" : obj[3].toString());
			wgEmpVo.setDivDesc(obj[4] == null ? "" : obj[4].toString());
			wgEmpVo.setSecCode(obj[5] == null ? "" : obj[5].toString());
			wgEmpVo.setSecDesc(obj[6] == null ? "" : obj[6].toString());
			wgEmpVo.setEmpCode(obj[7] == null ? "" : obj[7].toString());
			wgEmpVo.setPrefix(obj[8] == null ? "" : (String) obj[8]);
			wgEmpVo.setName(obj[9] == null ? "" : (String) obj[9]);
			wgEmpVo.setLastName(obj[10] == null ? "" : (String) obj[10]);
			// wgEmpVo.setFullName(obj[8]==null?"":(String)obj[8]+obj[9]==null?"":(String)obj[9]+"
			// "+obj[10]==null?"":(String)obj[10]);
			wgEmpVo.setTaxId(obj[11] == null ? "" : obj[11].toString());
			wgEmpVo.setMarriedStatus(obj[12] == null ? "" : obj[12].toString());
			wgEmpVo.setPayStatus(obj[13] == null ? "" : obj[13].toString());
			wgEmpVo.setBankId(obj[14] == null ? "" : obj[14].toString());
			if (obj[15] != null) {
				costChild = new Double(obj[15].toString()).intValue();
			}
			wgEmpVo.setCostChild(String.valueOf(costChild).equals("0") ? ""
					: String.valueOf(costChild));
			if (obj[16] != null) {
				childStudy = new Double(obj[16].toString()).intValue();
			}
			wgEmpVo.setChildStudy(String.valueOf(childStudy).equals("0") ? ""
					: String.valueOf(childStudy));
			if (obj[17] != null) {
				childNoStudy = new Double(obj[17].toString()).intValue();
			}

			wgEmpVo.setChildNoStudy(String.valueOf(childNoStudy).equals("0") ? ""
					: String.valueOf(childNoStudy));
			wgEmpVo.setCostLife(obj[18] == null ? "" : obj[18].toString());
			wgEmpVo.setGundanFlag(obj[19] == null ? "" : obj[19].toString());
			wgEmpVo.setDebtLife(obj[20] == null ? "" : obj[20].toString());
			wgEmpVo.setDebtLoan(obj[21] == null ? "" : obj[21].toString());
			wgEmpVo.setDonate(obj[22] == null ? "" : obj[22].toString());
			wgEmpVo.setFlagFather(obj[23] == null ? "" : obj[23].toString());
			wgEmpVo.setFlagMother(obj[24] == null ? "" : obj[24].toString());
			wgEmpVo.setFlagFatherSpouse(obj[25] == null ? "" : obj[25]
					.toString());
			wgEmpVo.setFlagMotherSpouse(obj[26] == null ? "" : obj[26]
					.toString());
			wgEmpVo.setLtf(obj[27] == null ? "" : obj[27].toString());
			wgEmpVo.setRmf(obj[28] == null ? "" : obj[28].toString());
			wgEmpVo.setFlagStatus(obj[29] == null ? "" : obj[29].toString());
			wgEmpVo.setAreaCode(obj[30] == null ? "" : obj[30].toString());
			wgEmpVo.setAreaDesc(obj[31] == null ? "" : obj[31].toString());
			listemp.add(wgEmpVo);
		}
		return listemp;
	}

}
