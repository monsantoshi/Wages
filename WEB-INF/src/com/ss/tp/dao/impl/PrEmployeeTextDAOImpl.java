package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.PrEmployeeTextDAO;
import com.ss.tp.dao.RwConfirmDataDAO;
import com.ss.tp.dto.IncomeDeductVO;
import com.ss.tp.dto.PrEmployeeTextErrorVO;
import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.model.PrEmployeeText;
import com.ss.tp.model.PrEmployeeTextErrorForSort;
import com.ss.tp.model.PrEmployeeTextForSort;

public class PrEmployeeTextDAOImpl extends HibernateDaoSupport implements
		PrEmployeeTextDAO, Serializable {
	private JdbcTemplate jdbcTemplate;
	private RwConfirmDataDAO rwConfirmDataDAO;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PrEmployeeText loadPrEmployeeText(PrEmployeeTextVO empTxtVo) {

		PrEmployeeText prEmpText = new PrEmployeeText();

		try {
			// Criteria c =
			// this.getSessionFactory().openSession().createCriteria(PrEmployeeText.class);
			Criteria c = this.getSession().createCriteria(PrEmployeeText.class);
			c.add(Restrictions.eq("ouCode", empTxtVo.getOuCode()));
			c.add(Restrictions.eq("year", Double.valueOf(empTxtVo.getYear())));
			c.add(Restrictions.eq("empCode", empTxtVo.getEmpCode()));
			c.add(Restrictions.eq("period",
					Double.valueOf(empTxtVo.getPeriod())));
			c.add(Restrictions.eq("flagStatus", "A"));
			c.add(Restrictions.eq("creBy", empTxtVo.getCreBy()));
			List result = c.list();
			if (result.size() > 0) {
				prEmpText = (PrEmployeeText) result.get(0);
			}
			// rwp = this.getHibernateTemplate().findByCriteria(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prEmpText;
	}

	public void insertPrEmployeeText(PrEmployeeTextVO empTxtVo)
			throws Exception {
		PrEmployeeText pr = new PrEmployeeText();

		try {
			// BeanUtils.copyProperties(pr ,empTxtVo);

			if (empTxtVo.getFlagStatus().equals("A")) {
				pr = this.loadPrEmployeeText(empTxtVo);

				if (empTxtVo.getAdjNewsal() != null
						&& empTxtVo.getAdjNewsal().trim().length() > 0)
					pr.setAdjNewsal(Double.valueOf(empTxtVo.getAdjNewsal()));

				if (empTxtVo.getAdjOldsal() != null
						&& empTxtVo.getAdjOldsal().trim().length() > 0)
					pr.setAdjOldsal(Double.valueOf(empTxtVo.getAdjOldsal()));

				if (empTxtVo.getBankBranch() != null
						&& empTxtVo.getBankBranch().trim().length() > 0)
					pr.setBankBranch(empTxtVo.getBankBranch());

				if (empTxtVo.getBankId() != null
						&& empTxtVo.getBankId().trim().length() > 0)
					pr.setBankId(empTxtVo.getBankId());

				if (empTxtVo.getChildNoStudy() != null
						&& empTxtVo.getChildNoStudy().trim().length() > 0)
					pr.setChildNoStudy(Double.valueOf(empTxtVo
							.getChildNoStudy()));

				if (empTxtVo.getChildStudy() != null
						&& empTxtVo.getChildStudy().trim().length() > 0)
					pr.setChildStudy(Double.valueOf(empTxtVo.getChildStudy()));

				if (empTxtVo.getCodeSeqWork() != null
						&& empTxtVo.getCodeSeqWork().trim().length() > 0)
					pr.setCodeSeqWork(Double.valueOf(empTxtVo.getCodeSeqWork()));

				if (empTxtVo.getCompensateLabour() != null
						&& empTxtVo.getCompensateLabour().trim().length() > 0)
					pr.setCompensateLabour(Double.valueOf(empTxtVo
							.getCompensateLabour()));

				pr.setConfirmFlag("N");

				if (empTxtVo.getCostChild() != null
						&& empTxtVo.getCostChild().trim().length() > 0)
					pr.setCostChild(Double.valueOf(empTxtVo.getCostChild()));

				if (empTxtVo.getCostLife() != null
						&& empTxtVo.getCostLife().trim().length() > 0)
					pr.setCostLife(Double.valueOf(empTxtVo.getCostLife()));

				if (empTxtVo.getDebtLifeSpouse() != null
						&& empTxtVo.getDebtLifeSpouse().trim().length() > 0)
					pr.setDebtLifeSpouse(Double.valueOf(empTxtVo.getDebtLifeSpouse()));
				
				if (empTxtVo.getDebtLife() != null
						&& empTxtVo.getDebtLife().trim().length() > 0)
					pr.setDebtLife(Double.valueOf(empTxtVo.getDebtLife()));


				if (empTxtVo.getDebtLoan() != null
						&& empTxtVo.getDebtLoan().trim().length() > 0)
					pr.setDebtLoan(Double.valueOf(empTxtVo.getDebtLoan()));

				if (empTxtVo.getDeductAmt() != null
						&& empTxtVo.getDeductAmt().trim().length() > 0)
					pr.setDeductAmt(Double.valueOf(empTxtVo.getDeductAmt()));

				if (empTxtVo.getDonate() != null
						&& empTxtVo.getDonate().trim().length() > 0)
					pr.setDonate(Double.valueOf(empTxtVo.getDonate()));

				if (empTxtVo.getEmpCode() != null
						&& empTxtVo.getEmpCode().trim().length() > 0)
					pr.setEmpCode(empTxtVo.getEmpCode());

				if (empTxtVo.getFlagFather() != null
						&& empTxtVo.getFlagFather().trim().length() > 0)
					pr.setFlagFather(empTxtVo.getFlagFather());

				if (empTxtVo.getFlagFatherSpouse() != null
						&& empTxtVo.getFlagFatherSpouse().trim().length() > 0)
					pr.setFlagFatherSpouse(empTxtVo.getFlagFatherSpouse());

				if (empTxtVo.getFlagMother() != null
						&& empTxtVo.getFlagMother().trim().length() > 0)
					pr.setFlagMother(empTxtVo.getFlagMother());

				if (empTxtVo.getFlagMotherSpouse() != null
						&& empTxtVo.getFlagMotherSpouse().trim().length() > 0)
					pr.setFlagMotherSpouse(empTxtVo.getFlagMotherSpouse());

				if (empTxtVo.getFlagPr() != null
						&& empTxtVo.getFlagPr().trim().length() > 0)
					pr.setFlagPr(empTxtVo.getFlagPr());

				if (empTxtVo.getFlagStatus() != null
						&& empTxtVo.getFlagStatus().trim().length() > 0)
					pr.setFlagStatus(empTxtVo.getFlagStatus());

				if (empTxtVo.getGundanAmt() != null
						&& empTxtVo.getGundanAmt().trim().length() > 0)
					pr.setGundanAmt(Double.valueOf(empTxtVo.getGundanAmt()));

				if (empTxtVo.getGundanFlag() != null
						&& empTxtVo.getGundanFlag().trim().length() > 0)
					pr.setGundanFlag(empTxtVo.getGundanFlag());

				if (empTxtVo.getIncomeTax() != null
						&& empTxtVo.getIncomeTax().trim().length() > 0)
					pr.setIncomeTax(Double.valueOf(empTxtVo.getIncomeTax()));

				// pr.setKeySeq(Long.parseLong(empTxtVo.getKeySeq()));

				if (empTxtVo.getLtf() != null
						&& empTxtVo.getLtf().trim().length() > 0)
					pr.setLtf(Double.valueOf(empTxtVo.getLtf()));

				if (empTxtVo.getMarriedStatus() != null
						&& empTxtVo.getMarriedStatus().trim().length() > 0)
					pr.setMarriedStatus(empTxtVo.getMarriedStatus());

				if (empTxtVo.getNewSalary() != null
						&& empTxtVo.getNewSalary().trim().length() > 0)
					pr.setNewSalary(Double.valueOf(empTxtVo.getNewSalary()));

				if (empTxtVo.getOldSalary() != null
						&& empTxtVo.getOldSalary().trim().length() > 0)
					pr.setOldSalary(Double.valueOf(empTxtVo.getOldSalary()));

				if (empTxtVo.getOther() != null
						&& empTxtVo.getOther().trim().length() > 0)
					pr.setOther(Double.valueOf(empTxtVo.getOther()));

				if (empTxtVo.getOuCode() != null
						&& empTxtVo.getOuCode().trim().length() > 0)
					pr.setOuCode(empTxtVo.getOuCode());

				if (empTxtVo.getOverage() != null
						&& empTxtVo.getOverage().trim().length() > 0)
					pr.setOverage(Double.valueOf(empTxtVo.getOverage()));

				if (empTxtVo.getOverageSpouse() != null
						&& empTxtVo.getOverageSpouse().trim().length() > 0)
					pr.setOverageSpouse(Double.valueOf(empTxtVo
							.getOverageSpouse()));

				if (empTxtVo.getPayStatus() != null
						&& empTxtVo.getPayStatus().trim().length() > 0)
					pr.setPayStatus(empTxtVo.getPayStatus());

				if (empTxtVo.getPensionFund() != null
						&& empTxtVo.getPensionFund().trim().length() > 0)
					pr.setPensionFund(Double.valueOf(empTxtVo.getPensionFund()));

				if (empTxtVo.getPeriod() != null
						&& empTxtVo.getPeriod().trim().length() > 0)
					pr.setPeriod(Double.valueOf(empTxtVo.getPeriod()));

				if (empTxtVo.getRmf() != null
						&& empTxtVo.getRmf().trim().length() > 0)
					pr.setRmf(Double.valueOf(empTxtVo.getRmf()));

				if (empTxtVo.getSeqData() != null
						&& empTxtVo.getSeqData().trim().length() > 0)
					pr.setSeqData(Double.valueOf(empTxtVo.getSeqData()));

				if (empTxtVo.getTaxId() != null
						&& empTxtVo.getTaxId().trim().length() > 0)
					pr.setTaxId(empTxtVo.getTaxId());

				if (empTxtVo.getTeacherFund() != null
						&& empTxtVo.getTeacherFund().trim().length() > 0)
					pr.setTeacherFund(Double.valueOf(empTxtVo.getTeacherFund()));

				if (empTxtVo.getOldKlongChev() != null
						&& empTxtVo.getOldKlongChev().trim().length() > 0)
					pr.setOldKlongChev(Double.valueOf(empTxtVo
							.getOldKlongChev()));

				if (empTxtVo.getNewKlongChev() != null
						&& empTxtVo.getNewKlongChev().trim().length() > 0)
					pr.setNewKlongChev(Double.valueOf(empTxtVo
							.getNewKlongChev()));

				if (empTxtVo.getBankCode() != null
						&& empTxtVo.getBankCode().trim().length() > 0)
					pr.setBankCode(empTxtVo.getBankCode());

				if (empTxtVo.getHealthFather() != null
						&& empTxtVo.getHealthFather().trim().length() > 0)
					pr.setHealthFather(Double.valueOf(empTxtVo
							.getHealthFather()));

				if (empTxtVo.getYear() != null
						&& empTxtVo.getYear().trim().length() > 0)
					pr.setYear(Double.valueOf(empTxtVo.getYear()));

				if (empTxtVo.getHandicappedDec() != null
						&& empTxtVo.getHandicappedDec().trim().length() > 0)
					pr.setHandicappedDec(Double.valueOf(empTxtVo
							.getHandicappedDec()));

				if (empTxtVo.getOldKc2000() != null
						&& empTxtVo.getOldKc2000().trim().length() > 0)
					pr.setOldKc2000(Double.valueOf(empTxtVo.getOldKc2000()));

				if (empTxtVo.getNewKc2000() != null
						&& empTxtVo.getNewKc2000().trim().length() > 0)
					pr.setNewKc2000(Double.valueOf(empTxtVo.getNewKc2000()));

			} else {

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

				if (empTxtVo.getBankBranch() != null
						&& empTxtVo.getBankBranch().trim().length() > 0)
					pr.setBankBranch(empTxtVo.getBankBranch());
				else
					pr.setBankBranch(null);

				if (empTxtVo.getBankId() != null
						&& empTxtVo.getBankId().trim().length() > 0)
					pr.setBankId(empTxtVo.getBankId());
				else
					pr.setBankId(null);

				if (empTxtVo.getChildNoStudy() != null
						&& empTxtVo.getChildNoStudy().trim().length() > 0)
					pr.setChildNoStudy(Double.valueOf(empTxtVo
							.getChildNoStudy()));
				else
					pr.setChildNoStudy(null);

				if (empTxtVo.getChildStudy() != null
						&& empTxtVo.getChildStudy().trim().length() > 0)
					pr.setChildStudy(Double.valueOf(empTxtVo.getChildStudy()));
				else
					pr.setChildStudy(null);

				if (empTxtVo.getCodeSeqWork() != null
						&& empTxtVo.getCodeSeqWork().trim().length() > 0)
					pr.setCodeSeqWork(Double.valueOf(empTxtVo.getCodeSeqWork()));
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
					pr.setCostChild(Double.valueOf(empTxtVo.getCostChild()));
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
				
				if (empTxtVo.getDebtLifeSpouse() != null
						&& empTxtVo.getDebtLifeSpouse().trim().length() > 0)
					pr.setDebtLifeSpouse(Double.valueOf(empTxtVo.getDebtLifeSpouse()));
				else
					pr.setDebtLifeSpouse(null);


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
					pr.setOverageSpouse(Double.valueOf(empTxtVo
							.getOverageSpouse()));
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
					pr.setPeriod(Double.valueOf(empTxtVo.getPeriod()));
				else
					pr.setPeriod(null);

				if (empTxtVo.getRmf() != null
						&& empTxtVo.getRmf().trim().length() > 0)
					pr.setRmf(Double.valueOf(empTxtVo.getRmf()));
				else
					pr.setRmf(null);

				if (empTxtVo.getSeqData() != null
						&& empTxtVo.getSeqData().trim().length() > 0)
					pr.setSeqData(Double.valueOf(empTxtVo.getSeqData()));
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
					pr.setYear(Double.valueOf(empTxtVo.getYear()));
				else
					pr.setYear(null);

				if (empTxtVo.getNewKlongChev() != null
						&& empTxtVo.getNewKlongChev().trim().length() > 0)
					pr.setNewKlongChev(Double.valueOf(empTxtVo
							.getNewKlongChev()));
				else
					pr.setNewKlongChev(null);

				if (empTxtVo.getBankCode() != null
						&& empTxtVo.getBankCode().trim().length() > 0)
					pr.setBankCode(empTxtVo.getBankCode());
				else
					pr.setBankCode(null);

				if (empTxtVo.getHealthFather() != null
						&& empTxtVo.getHealthFather().trim().length() > 0)
					pr.setHealthFather(Double.valueOf(empTxtVo
							.getHealthFather()));
				else
					pr.setHealthFather(null);

				if (empTxtVo.getHandicappedDec() != null
						&& empTxtVo.getHandicappedDec().trim().length() > 0)
					pr.setHandicappedDec(Double.valueOf(empTxtVo
							.getHandicappedDec()));
				else
					pr.setHandicappedDec(null);

				if (empTxtVo.getOldKc2000() != null
						&& empTxtVo.getOldKc2000().trim().length() > 0)
					pr.setOldKc2000(Double.valueOf(empTxtVo.getOldKc2000()));
				else
					pr.setOldKc2000(null);

				if (empTxtVo.getNewKc2000() != null
						&& empTxtVo.getNewKc2000().trim().length() > 0)
					pr.setNewKc2000(Double.valueOf(empTxtVo.getNewKc2000()));
				else
					pr.setNewKc2000(null);

			}

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
			this.getHibernateTemplate().saveOrUpdate(pr);
			// this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updatePrEmployeeText(PrEmployeeTextVO empTxtVo)
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

	public void deletePrEmployeeText(PrEmployeeTextVO empTxtVo)
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
	
	public List deletePrEmployeeTextError(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List incomeList = new ArrayList();

		PrEmployeeTextErrorForSort prEmpErrForSort = new PrEmployeeTextErrorForSort();

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select rw.empCode , count(*) as cntOt");
		hql1.append(" from PrEmployeeText rw, VPrEmployeeSecurity vpr  ");
		hql1.append(" where rw.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and rw.year = ");
		hql1.append(yearPr);
		hql1.append(" and rw.period = ");
		hql1.append(periodPr);
		hql1.append(" and vpr.pk.userId = '");
		hql1.append(userId);
		hql1.append("' ");
		hql1.append(" and rw.codeSeqWork <= 20000 ");
		hql1.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql1.append(" and rw.year = vpr.pk.year ");
		hql1.append(" and rw.period = vpr.pk.period ");
		hql1.append(" and rw.empCode = vpr.pk.empCode ");
		hql1.append(" and rw.creBy = vpr.pk.userId ");
		hql1.append(" group by rw.empCode  ");
		Object incomeList1 = this.getSession().createQuery(hql1.toString())
				.uniqueResult();
		// incomeList.add(incomeList1);
		if (incomeList1 != null && !incomeList1.equals("")) {
			Object[] rt = (Object[]) incomeList1;
			prEmpErrForSort.setEmpCode("000000");
			
			//prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpErrForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpErrForSort);
		}

		Collections.sort(incomeList);

		List retList = new ArrayList();
		int i = 0;
		Double cntOt;

		if ((incomeList != null) && (incomeList.size() > 0)) {
			for (Iterator it = incomeList.iterator(); it.hasNext();) {
				PrEmployeeTextErrorForSort r = (PrEmployeeTextErrorForSort) it.next();
				if (r != null) {
					String empCode = (r.getEmpCode() != null) ? (String) r
							.getEmpCode() : "";
					// Integer cntOt = (r.getCount() != null) ? r.getCount() :
					// new Integer(0) ;
					if (r.getCount() != null) {
						cntOt = r.getCount();
					} else {
						cntOt = new Double("0");
						;
					}
					PrEmployeeTextErrorVO textErrorVo = new PrEmployeeTextErrorVO();
					textErrorVo.setEmpCode(empCode);
					textErrorVo.setCountOt(cntOt);
					retList.add(i, textErrorVo);
					i++;
				}
			}
		}

		return retList;
	}
	public void deletePrEmployeeTextError(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Pr_Employee_text rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year = " + year);
			sql1.append(" and rw.period = " + period);
			sql1.append(" and rw.code_seq_work < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + year);
			sql1.append("	and period = " + period);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Pr_Employee_text rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year = " + year);
			sql2.append(" and rw.period = " + period);
			sql2.append(" and rw.code_seq_work is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + year);
			sql2.append("	and period = " + period);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}


	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		/*
		 * sql.append(" select count(*) "); sql.append(" from PrEmployeeText p,
		 * VPnOrganizationSecurity e "); sql.append(" where p.ouCode =
		 * '"+ouCode+"' "); sql.append(" and p.year = " + year); sql.append("
		 * and p.period = " + period); sql.append(" and p.confirmFlag = 'Y' ");
		 * sql.append(" and e.pk.userId = '" + userId + "' "); //sql.append("
		 * and p.empCode = e.pk.empCode "); sql.append(" and p.ouCode =
		 * e.pk.ouCode "); sql.append(" and p.codeSeqWork = e.pk.codeSeq ");
		 * //sql.append(" and p.year = e.pk.year "); //sql.append(" and p.period
		 * = e.pk.period ");
		 * 
		 * List ls = this.getHibernateTemplate().find( sql.toString() );
		 */

		return this.rwConfirmDataDAO.isConfirmMasterData(ouCode, year, period,
				userId);

		// sql.setLength(0);
		// sql.append(" select count(*) ");
		// sql.append(" from PrPeriodLine ");
		// sql.append(" where pk.year = " + year);
		// sql.append(" and pk.ouCode = '" + ouCode + "' ");
		// sql.append(" and pk.period = " + period );
		// sql.append(" and mainClose = 'Y' ");
		//
		// List ls2 = this.getHibernateTemplate().find( sql.toString() );
		//
		// if( ls != null && ls.size() > 0 && ls2 != null && ls2.size() > 0 ){
		// Integer i = (Integer)ls.get(0);
		// Integer j = (Integer)ls2.get(0);
		//
		// //System.out.println("i : " + i.intValue() + " j : " + j.intValue()
		// );
		//
		// if( i.intValue() > 0 || j.intValue() > 0 )
		// return true;
		// else
		// return false;
		// }else
		// return true;
	}

	public List updateConfirmIncome(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception {
		List incomeList = new ArrayList();

		PrEmployeeTextForSort prEmpForSort = new PrEmployeeTextForSort();

		// int count = 0;

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql1.append(" from RwModSal rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql1.append(" where rw.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and rw.yearPr = ");
		hql1.append(yearPr);
		hql1.append(" and rw.periodPr = ");
		hql1.append(periodPr);
		hql1.append(" and vpr.pk.userId = '");
		hql1.append(userId);
		hql1.append("' ");
		hql1.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql1.append(" and pr.pk.incDecCode = '02' ");
		hql1.append(" and pr.pk.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and pr.pk.groupCode = '1' ");
		hql1.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql1.append(" and rw.yearPr = vpr.pk.year ");
		hql1.append(" and rw.periodPr = vpr.pk.period ");
		hql1.append(" and rw.empCode = vpr.pk.empCode ");
		hql1.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList1 = this.getSession().createQuery(hql1.toString())
				.uniqueResult();
		if (incomeList1 != null && !incomeList1.equals("")) {
			Object[] rt = (Object[]) incomeList1;
			prEmpForSort.setInDecCode("99");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);

			// System.out.println("modsal : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql2.append(" from RwOvertime rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql2.append(" where rw.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and rw.yearPr = ");
		hql2.append(yearPr);
		hql2.append(" and rw.periodPr = ");
		hql2.append(periodPr);
		hql2.append(" and vpn.pk.userId = '");
		hql2.append(userId);
		hql2.append("' ");
		hql2.append(" and pr.pk.incDecCode = '11' ");
		hql2.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql2.append(" and pr.pk.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and pr.pk.groupCode = '1' ");
		hql2.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql2.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		hql2.append(" group by pr.pk.incDecCode,pr.incDecName ");
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

			// System.out.println("overtime : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql3 = new StringBuffer();
		hql3.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql3.append(" from RwPremium rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql3.append(" from RwPremium rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql3.append(" where rw.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and rw.yearPr = ");
		hql3.append(yearPr);
		hql3.append(" and rw.periodPr = ");
		hql3.append(periodPr);
		hql3.append(" and vpn.pk.userId = '");
		hql3.append(userId);
		hql3.append("' ");
		hql3.append(" and pr.pk.incDecCode = '12' ");
		hql3.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql3.append(" and pr.pk.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and pr.pk.groupCode = '1' ");
		// hql3.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql3.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql3.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql3.append(" and rw.yearPr = vpr.pk.year ");
		// hql3.append(" and rw.periodPr = vpr.pk.period ");
		// hql3.append(" and rw.empCode = vpr.pk.empCode ");
		hql3.append(" group by pr.pk.incDecCode,pr.incDecName ");
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

			// System.out.println("premium : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql4 = new StringBuffer();
		hql4.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql4.append(" from RwHealth rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql4.append(" from RwHealth rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql4.append(" where rw.ouCode = '");
		hql4.append(ouCode);
		hql4.append("' ");
		hql4.append(" and rw.yearPr = ");
		hql4.append(yearPr);
		hql4.append(" and rw.periodPr = ");
		hql4.append(periodPr);
		hql4.append(" and vpn.pk.userId = '");
		hql4.append(userId);
		hql4.append("' ");
		hql4.append(" and pr.pk.incDecCode = '13' ");
		hql4.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql4.append(" and pr.pk.ouCode = '");
		hql4.append(ouCode);
		hql4.append("' ");
		hql4.append(" and pr.pk.groupCode = '1' ");
		// hql4.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql4.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql4.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql4.append(" and rw.yearPr = vpr.pk.year ");
		// hql4.append(" and rw.periodPr = vpr.pk.period ");
		// hql4.append(" and rw.empCode = vpr.pk.empCode ");
		hql4.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList4 = this.getSession().createQuery(hql4.toString())
				.uniqueResult();
		// incomeList.add(incomeList4);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList4 != null && !incomeList4.equals("")) {
			Object[] rt = (Object[]) incomeList4;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);

			// System.out.println("health : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql5 = new StringBuffer();
		hql5.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql5.append(" from RwDanger rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql5.append(" from RwDanger rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql5.append(" where rw.ouCode = '");
		hql5.append(ouCode);
		hql5.append("' ");
		hql5.append(" and rw.yearPr = ");
		hql5.append(yearPr);
		hql5.append(" and rw.periodPr = ");
		hql5.append(periodPr);
		hql5.append(" and vpn.pk.userId = '");
		hql5.append(userId);
		hql5.append("' ");
		hql5.append(" and pr.pk.incDecCode = '14' ");
		hql5.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql5.append(" and pr.pk.ouCode = '");
		hql5.append(ouCode);
		hql5.append("' ");
		hql5.append(" and pr.pk.groupCode = '1' ");
		// hql5.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql5.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql5.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql5.append(" and rw.yearPr = vpr.pk.year ");
		// hql5.append(" and rw.periodPr = vpr.pk.period ");
		// hql5.append(" and rw.empCode = vpr.pk.empCode ");
		hql5.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList5 = this.getSession().createQuery(hql5.toString())
				.uniqueResult();
		// incomeList.add(incomeList5);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList5 != null && !incomeList5.equals("")) {
			Object[] rt = (Object[]) incomeList5;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);

			// System.out.println("danger : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql6 = new StringBuffer();
		hql6.append(" select rw.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql6.append(" from RwIncDecOther rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql6.append(" from RwIncDecOther rw, VPnOrganizationSecurity vpn  , PrIncomeDeduct pr ");
		hql6.append(" where rw.ouCode = '");
		hql6.append(ouCode);
		hql6.append("' ");
		hql6.append(" and rw.yearPr = ");
		hql6.append(yearPr);
		hql6.append(" and rw.periodPr = ");
		hql6.append(periodPr);
		hql6.append(" and vpn.pk.userId = '");
		hql6.append(userId);
		hql6.append("' ");
		hql6.append(" and pr.pk.ouCode = '");
		hql6.append(ouCode);
		hql6.append("' ");
		hql6.append(" and pr.pk.groupCode = '1' ");
		hql6.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql6.append(" and rw.incDecCode = pr.pk.incDecCode ");
		// hql6.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql6.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql6.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql6.append(" and rw.yearPr = vpr.pk.year ");
		// hql6.append(" and rw.periodPr = vpr.pk.period ");
		// hql6.append(" and rw.empCode = vpr.pk.empCode ");
		hql6.append(" group by rw.incDecCode , pr.incDecName ");
		List incomeList6 = this.getSession().createQuery(hql6.toString())
				.list();
		if ((incomeList6 != null) && (incomeList6.size() > 0)) {
			for (Iterator it = incomeList6.iterator(); it.hasNext();) {

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

				// System.out.println("inc_dec_other 1 : " +
				// prEmpForSort.getInDecCode() + " count : " + ++count);
			}
		}

		StringBuffer hql7 = new StringBuffer();
		hql7.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql7.append(" from RwVinai rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql7.append(" from RwVinai rw, VPnOrganizationSecurity vpn  , PrIncomeDeduct pr ");
		hql7.append(" where rw.ouCode = '");
		hql7.append(ouCode);
		hql7.append("' ");
		hql7.append(" and rw.yearPr = ");
		hql7.append(yearPr);
		hql7.append(" and rw.periodPr = ");
		hql7.append(periodPr);
		hql7.append(" and vpn.pk.userId = '");
		hql7.append(userId);
		hql7.append("' ");
		hql7.append(" and pr.pk.incDecCode = '21' ");
		hql7.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql7.append(" and pr.pk.ouCode = '");
		hql7.append(ouCode);
		hql7.append("' ");
		hql7.append(" and pr.pk.groupCode = '2' ");
		// hql7.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql7.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql7.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql7.append(" and rw.yearPr = vpr.pk.year ");
		// hql7.append(" and rw.periodPr = vpr.pk.period ");
		// hql7.append(" and rw.empCode = vpr.pk.empCode ");
		hql7.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList1 = this.getSession().createQuery(hql7.toString())
				.uniqueResult();
		// deductList.add(deductList1);
		if (deductList1 != null && !deductList1.equals("")) {
			Object[] rt = (Object[]) deductList1;
			prEmpForSort = new PrEmployeeTextForSort();
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);

			// System.out.println("vinai : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql8 = new StringBuffer();
		hql8.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		// hql8.append(" from RwVinai2 rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql8.append(" from RwVinai2 rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql8.append(" where rw.ouCode = '");
		hql8.append(ouCode);
		hql8.append("' ");
		hql8.append(" and rw.yearPr = ");
		hql8.append(yearPr);
		hql8.append(" and rw.periodPr = ");
		hql8.append(periodPr);
		hql8.append(" and vpn.pk.userId = '");
		hql8.append(userId);
		hql8.append("' ");
		hql8.append(" and pr.pk.incDecCode = '22' ");
		hql8.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql8.append(" and pr.pk.ouCode = '");
		hql8.append(ouCode);
		hql8.append("' ");
		hql8.append(" and pr.pk.groupCode = '2' ");
		// hql8.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql8.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql8.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql8.append(" and rw.yearPr = vpr.pk.year ");
		// hql8.append(" and rw.periodPr = vpr.pk.period ");
		// hql8.append(" and rw.empCode = vpr.pk.empCode ");
		hql8.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList2 = this.getSession().createQuery(hql8.toString())
				.uniqueResult();
		// deductList.add(deductList2);
		prEmpForSort = new PrEmployeeTextForSort();
		if (deductList2 != null && !deductList2.equals("")) {
			Object[] rt = (Object[]) deductList2;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);

			// System.out.println("vinai2 : " + prEmpForSort.getInDecCode() +
			// " count : " + ++count);
		}

		StringBuffer hql9 = new StringBuffer();
		hql9.append(" select rw.incDecCode ,pr.incDecName , count(*) as cntOt");
		// hql9.append(" from RwIncDecOther rw, VPrEmployeeSecurity vpr ,
		// PrIncomeDeduct pr ");
		hql9.append(" from RwIncDecOther rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql9.append(" where rw.ouCode = '");
		hql9.append(ouCode);
		hql9.append("' ");
		hql9.append(" and rw.yearPr = ");
		hql9.append(yearPr);
		hql9.append(" and rw.periodPr = ");
		hql9.append(periodPr);
		hql9.append(" and vpn.pk.userId = '");
		hql9.append(userId);
		hql9.append("' ");
		hql9.append(" and pr.pk.ouCode = '");
		hql9.append(ouCode);
		hql9.append("' ");
		hql9.append(" and pr.pk.groupCode = '2' ");
		hql9.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql9.append(" and rw.incDecCode = pr.pk.incDecCode ");
		// hql9.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql9.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql9.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		// hql9.append(" and rw.yearPr = vpr.pk.year ");
		// hql9.append(" and rw.periodPr = vpr.pk.period ");
		// hql9.append(" and rw.empCode = vpr.pk.empCode ");
		hql9.append(" group by rw.incDecCode , pr.incDecName ");
		List deductList3 = this.getSession().createQuery(hql9.toString())
				.list();
		if ((deductList3 != null) && (deductList3.size() > 0)) {
			for (Iterator it = deductList3.iterator(); it.hasNext();) {
				// deductList.add(it.next());
				prEmpForSort = new PrEmployeeTextForSort();
				Object[] rt = (Object[]) it.next();
				prEmpForSort
						.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
				prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
				prEmpForSort.setCount(new Double(
						((Integer) ((rt[2] != null) ? rt[2] : ""))
								.doubleValue()));
				incomeList.add(prEmpForSort);

				// System.out.println("inc_dec_other 2 : " +
				// prEmpForSort.getInDecCode() + " count : " + ++count);
			}
		}

		// System.out.println("Total List : " + incomeList.size() + " count : "
		// + count);

		// System.out.println("Before sort");
		// for(int i=0; i<incomeList.size(); i++){
		// PrEmployeeTextForSort r = (PrEmployeeTextForSort) incomeList.get(i);
		// System.out.println(r.getInDecCode() + " " + r.getName());
		// }

		Collections.sort(incomeList);

		// System.out.println("After sort");
		// for(int i=0; i<incomeList.size(); i++){
		// PrEmployeeTextForSort r = (PrEmployeeTextForSort) incomeList.get(i);
		// System.out.println(r.getInDecCode() + " " + r.getName());
		// }

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

					// System.out.println(r.getInDecCode() + " " + r.getName());

					i++;
				}
			}
		}

		System.out.println("count completed ... : " + retList.size());

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
		hql1.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql1.append(" from RwModSal rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql1.append(" where rw.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and rw.yearPr = ");
		hql1.append(yearPr);
		hql1.append(" and rw.periodPr = ");
		hql1.append(periodPr);
		hql1.append(" and vpr.pk.userId = '");
		hql1.append(userId);
		hql1.append("' ");
		hql1.append(" and rw.confirmFlag = 'Y' ");
		hql1.append(" and pr.pk.incDecCode = '02' ");
		hql1.append(" and pr.pk.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and pr.pk.groupCode = '1' ");
		hql1.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql1.append(" and rw.yearPr = vpr.pk.year ");
		hql1.append(" and rw.periodPr = vpr.pk.period ");
		hql1.append(" and rw.empCode = vpr.pk.empCode ");
		hql1.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList1 = this.getSession().createQuery(hql1.toString())
				.uniqueResult();
		// incomeList.add(incomeList1);
		if (incomeList1 != null && !incomeList1.equals("")) {
			Object[] rt = (Object[]) incomeList1;
			prEmpForSort.setInDecCode("99");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql2.append(" from RwOvertime rw, VPnOrganizationSecurity vpn , PrIncomeDeduct pr ");
		hql2.append(" where rw.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and rw.yearPr = ");
		hql2.append(yearPr);
		hql2.append(" and rw.periodPr = ");
		hql2.append(periodPr);
		hql2.append(" and vpn.pk.userId = '");
		hql2.append(userId);
		hql2.append("' ");
		hql2.append(" and pr.pk.incDecCode = '11' ");
		hql2.append(" and rw.confirmFlag = 'Y' ");
		hql2.append(" and pr.pk.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and pr.pk.groupCode = '1' ");
		hql2.append(" and rw.ouCode = vpn.pk.ouCode ");
		hql2.append(" and rw.codeSeq = vpn.pk.codeSeq ");
		hql2.append(" group by pr.pk.incDecCode,pr.incDecName ");
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
		hql3.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql3.append(" from RwPremium rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql3.append(" where rw.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and rw.yearPr = ");
		hql3.append(yearPr);
		hql3.append(" and rw.periodPr = ");
		hql3.append(periodPr);
		hql3.append(" and vpr.pk.userId = '");
		hql3.append(userId);
		hql3.append("' ");
		hql3.append(" and pr.pk.incDecCode = '12' ");
		hql3.append(" and rw.confirmFlag = 'Y' ");
		hql3.append(" and pr.pk.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and pr.pk.groupCode = '1' ");
		hql3.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql3.append(" and rw.yearPr = vpr.pk.year ");
		hql3.append(" and rw.periodPr = vpr.pk.period ");
		hql3.append(" and rw.empCode = vpr.pk.empCode ");
		hql3.append(" group by pr.pk.incDecCode,pr.incDecName ");
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
		hql4.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql4.append(" from RwHealth rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql4.append(" where rw.ouCode = '");
		hql4.append(ouCode);
		hql4.append("' ");
		hql4.append(" and rw.yearPr = ");
		hql4.append(yearPr);
		hql4.append(" and rw.periodPr = ");
		hql4.append(periodPr);
		hql4.append(" and vpr.pk.userId = '");
		hql4.append(userId);
		hql4.append("' ");
		hql4.append(" and pr.pk.incDecCode = '13' ");
		hql4.append(" and rw.confirmFlag = 'Y' ");
		hql4.append(" and pr.pk.ouCode = '");
		hql4.append(ouCode);
		hql4.append("' ");
		hql4.append(" and pr.pk.groupCode = '1' ");
		hql4.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql4.append(" and rw.yearPr = vpr.pk.year ");
		hql4.append(" and rw.periodPr = vpr.pk.period ");
		hql4.append(" and rw.empCode = vpr.pk.empCode ");
		hql4.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList4 = this.getSession().createQuery(hql4.toString())
				.uniqueResult();
		// incomeList.add(incomeList4);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList4 != null && !incomeList4.equals("")) {
			Object[] rt = (Object[]) incomeList4;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql5 = new StringBuffer();
		hql5.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql5.append(" from RwDanger rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql5.append(" where rw.ouCode = '");
		hql5.append(ouCode);
		hql5.append("' ");
		hql5.append(" and rw.yearPr = ");
		hql5.append(yearPr);
		hql5.append(" and rw.periodPr = ");
		hql5.append(periodPr);
		hql5.append(" and vpr.pk.userId = '");
		hql5.append(userId);
		hql5.append("' ");
		hql5.append(" and pr.pk.incDecCode = '14' ");
		hql5.append(" and rw.confirmFlag = 'Y' ");
		hql5.append(" and pr.pk.ouCode = '");
		hql5.append(ouCode);
		hql5.append("' ");
		hql5.append(" and pr.pk.groupCode = '1' ");
		hql5.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql5.append(" and rw.yearPr = vpr.pk.year ");
		hql5.append(" and rw.periodPr = vpr.pk.period ");
		hql5.append(" and rw.empCode = vpr.pk.empCode ");
		hql5.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object incomeList5 = this.getSession().createQuery(hql5.toString())
				.uniqueResult();
		// incomeList.add(incomeList5);
		prEmpForSort = new PrEmployeeTextForSort();
		if (incomeList5 != null && !incomeList5.equals("")) {
			Object[] rt = (Object[]) incomeList5;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			incomeList.add(prEmpForSort);
		}

		StringBuffer hql6 = new StringBuffer();
		hql6.append(" select rw.incDecCode ,pr.incDecName , count(*) as cntOt");
		hql6.append(" from RwIncDecOther rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql6.append(" where rw.ouCode = '");
		hql6.append(ouCode);
		hql6.append("' ");
		hql6.append(" and rw.yearPr = ");
		hql6.append(yearPr);
		hql6.append(" and rw.periodPr = ");
		hql6.append(periodPr);
		hql6.append(" and vpr.pk.userId = '");
		hql6.append(userId);
		hql6.append("' ");
		hql6.append(" and pr.pk.ouCode = '");
		hql6.append(ouCode);
		hql6.append("' ");
		hql6.append(" and pr.pk.groupCode = '1' ");
		hql6.append(" and rw.confirmFlag = 'Y' ");
		hql6.append(" and rw.incDecCode = pr.pk.incDecCode ");
		hql6.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql6.append(" and rw.yearPr = vpr.pk.year ");
		hql6.append(" and rw.periodPr = vpr.pk.period ");
		hql6.append(" and rw.empCode = vpr.pk.empCode ");
		hql6.append(" group by rw.incDecCode , pr.incDecName ");
		List incomeList6 = this.getSession().createQuery(hql6.toString())
				.list();
		if ((incomeList6 != null) && (incomeList6.size() > 0)) {
			for (Iterator it = incomeList6.iterator(); it.hasNext();) {
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

		return retList;
	}

	public void updateRwModSalIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Mod_Sal rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwModSalIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Mod_Sal rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwOvertimeIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Overtime rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.code_seq in ( ");
			sql.append("	select code_seq ");
			sql.append("	from v_pn_organization_security ");
			sql.append("	where user_id = '" + userId + "' ");
			// sql.append(" and year = " + year);
			// sql.append(" and period = " + period);
			sql.append("	and ou_code = '" + ouCode + "' ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteRwOvertimeIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Overtime rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.code_seq in ( ");
			sql.append("	select code_seq ");
			sql.append("	from v_pn_organization_security ");
			sql.append("	where user_id = '" + userId + "' ");
			sql.append("	and ou_code = rw.ou_code ");
			sql.append(" ) ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void updateRwPremiumIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_premium rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwPremiumIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_premium rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwHealthIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Health rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwHealthIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Health rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwDangerIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Danger rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwDangerIncome(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Danger rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwIncDecOtherIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Inc_Dec_Other rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.group_code = '1' ");
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwIncDecOtherIncome(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Inc_Dec_Other rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.group_code = '1' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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
		hql1.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql1.append(" from RwVinai rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql1.append(" where rw.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and rw.yearPr = ");
		hql1.append(yearPr);
		hql1.append(" and rw.periodPr = ");
		hql1.append(periodPr);
		hql1.append(" and vpr.pk.userId = '");
		hql1.append(userId);
		hql1.append("' ");
		hql1.append(" and pr.pk.incDecCode = '21' ");
		hql1.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql1.append(" and pr.pk.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and pr.pk.groupCode = '2' ");
		hql1.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql1.append(" and rw.yearPr = vpr.pk.year ");
		hql1.append(" and rw.periodPr = vpr.pk.period ");
		hql1.append(" and rw.empCode = vpr.pk.empCode ");
		hql1.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList1 = this.getSession().createQuery(hql1.toString())
				.uniqueResult();
		// deductList.add(deductList1);
		if (deductList1 != null && !deductList1.equals("")) {
			Object[] rt = (Object[]) deductList1;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			deductList.add(prEmpForSort);
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql2.append(" from RwVinai2 rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql2.append(" where rw.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and rw.yearPr = ");
		hql2.append(yearPr);
		hql2.append(" and rw.periodPr = ");
		hql2.append(periodPr);
		hql2.append(" and vpr.pk.userId = '");
		hql2.append(userId);
		hql2.append("' ");
		hql2.append(" and pr.pk.incDecCode = '22' ");
		hql2.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql2.append(" and pr.pk.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and pr.pk.groupCode = '2' ");
		hql2.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql2.append(" and rw.yearPr = vpr.pk.year ");
		hql2.append(" and rw.periodPr = vpr.pk.period ");
		hql2.append(" and rw.empCode = vpr.pk.empCode ");
		hql2.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList2 = this.getSession().createQuery(hql2.toString())
				.uniqueResult();
		// deductList.add(deductList2);
		prEmpForSort = new PrEmployeeTextForSort();
		if (deductList2 != null && !deductList2.equals("")) {
			Object[] rt = (Object[]) deductList2;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			deductList.add(prEmpForSort);
		}

		StringBuffer hql3 = new StringBuffer();
		hql3.append(" select rw.incDecCode ,pr.incDecName , count(*) as cntOt");
		hql3.append(" from RwIncDecOther rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql3.append(" where rw.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and rw.yearPr = ");
		hql3.append(yearPr);
		hql3.append(" and rw.periodPr = ");
		hql3.append(periodPr);
		hql3.append(" and vpr.pk.userId = '");
		hql3.append(userId);
		hql3.append("' ");
		hql3.append(" and pr.pk.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and pr.pk.groupCode = '2' ");
		hql3.append(" and ( rw.confirmFlag <> 'Y' or rw.confirmFlag is null ) ");
		hql3.append(" and rw.incDecCode = pr.pk.incDecCode ");
		hql3.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql3.append(" and rw.yearPr = vpr.pk.year ");
		hql3.append(" and rw.periodPr = vpr.pk.period ");
		hql3.append(" and rw.empCode = vpr.pk.empCode ");
		hql3.append(" group by rw.incDecCode , pr.incDecName ");
		List deductList3 = this.getSession().createQuery(hql3.toString())
				.list();
		if ((deductList3 != null) && (deductList3.size() > 0)) {
			for (Iterator it = deductList3.iterator(); it.hasNext();) {
				// deductList.add(it.next());
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
		hql1.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql1.append(" from RwVinai rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql1.append(" where rw.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and rw.yearPr = ");
		hql1.append(yearPr);
		hql1.append(" and rw.periodPr = ");
		hql1.append(periodPr);
		hql1.append(" and vpr.pk.userId = '");
		hql1.append(userId);
		hql1.append("' ");
		hql1.append(" and pr.pk.incDecCode = '21' ");
		hql1.append(" and rw.confirmFlag = 'Y' ");
		hql1.append(" and pr.pk.ouCode = '");
		hql1.append(ouCode);
		hql1.append("' ");
		hql1.append(" and pr.pk.groupCode = '2' ");
		hql1.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql1.append(" and rw.yearPr = vpr.pk.year ");
		hql1.append(" and rw.periodPr = vpr.pk.period ");
		hql1.append(" and rw.empCode = vpr.pk.empCode ");
		hql1.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList1 = this.getSession().createQuery(hql1.toString())
				.uniqueResult();
		// deductList.add(deductList1);
		if (deductList1 != null && !deductList1.equals("")) {
			Object[] rt = (Object[]) deductList1;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			deductList.add(prEmpForSort);
		}

		StringBuffer hql2 = new StringBuffer();
		hql2.append(" select pr.pk.incDecCode,pr.incDecName , count(*) as cntOt");
		hql2.append(" from RwVinai2 rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql2.append(" where rw.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and rw.yearPr = ");
		hql2.append(yearPr);
		hql2.append(" and rw.periodPr = ");
		hql2.append(periodPr);
		hql2.append(" and vpr.pk.userId = '");
		hql2.append(userId);
		hql2.append("' ");
		hql2.append(" and pr.pk.incDecCode = '22' ");
		hql2.append(" and rw.confirmFlag = 'Y' ");
		hql2.append(" and pr.pk.ouCode = '");
		hql2.append(ouCode);
		hql2.append("' ");
		hql2.append(" and pr.pk.groupCode = '2' ");
		hql2.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql2.append(" and rw.yearPr = vpr.pk.year ");
		hql2.append(" and rw.periodPr = vpr.pk.period ");
		hql2.append(" and rw.empCode = vpr.pk.empCode ");
		hql2.append(" group by pr.pk.incDecCode,pr.incDecName ");
		Object deductList2 = this.getSession().createQuery(hql2.toString())
				.uniqueResult();
		// deductList.add(deductList2);
		prEmpForSort = new PrEmployeeTextForSort();
		if (deductList2 != null && !deductList2.equals("")) {
			Object[] rt = (Object[]) deductList2;
			prEmpForSort.setInDecCode((rt[0] != null) ? (String) rt[0] : "");
			prEmpForSort.setName((rt[1] != null) ? (String) rt[1] : "");
			prEmpForSort.setCount(new Double(
					((Integer) ((rt[2] != null) ? rt[2] : "")).doubleValue()));
			deductList.add(prEmpForSort);
		}

		StringBuffer hql3 = new StringBuffer();
		hql3.append(" select rw.incDecCode ,pr.incDecName , count(*) as cntOt");
		hql3.append(" from RwIncDecOther rw, VPrEmployeeSecurity vpr , PrIncomeDeduct pr ");
		hql3.append(" where rw.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and rw.yearPr = ");
		hql3.append(yearPr);
		hql3.append(" and rw.periodPr = ");
		hql3.append(periodPr);
		hql3.append(" and vpr.pk.userId = '");
		hql3.append(userId);
		hql3.append("' ");
		hql3.append(" and pr.pk.ouCode = '");
		hql3.append(ouCode);
		hql3.append("' ");
		hql3.append(" and pr.pk.groupCode = '2' ");
		hql3.append(" and rw.confirmFlag = 'Y' ");
		hql3.append(" and rw.incDecCode = pr.pk.incDecCode ");
		hql3.append(" and rw.ouCode = vpr.pk.ouCode ");
		hql3.append(" and rw.yearPr = vpr.pk.year ");
		hql3.append(" and rw.periodPr = vpr.pk.period ");
		hql3.append(" and rw.empCode = vpr.pk.empCode ");
		hql3.append(" group by rw.incDecCode , pr.incDecName ");
		List deductList3 = this.getSession().createQuery(hql3.toString())
				.list();
		if ((deductList3 != null) && (deductList3.size() > 0)) {
			for (Iterator it = deductList3.iterator(); it.hasNext();) {
				// deductList.add(it.next());
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

	public void updateRwVinaiDeduct(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Vinai rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwVinaiDeduct(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Vinai rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwVinai2Deduct(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Vinai2 rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwVinai2Deduct(String ouCode, String year, String period,
			String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Vinai2 rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void updateRwIncDecOtherDeduct(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" update Rw_Inc_Dec_Other rw ");
			sql.append(" set rw.confirm_flag = 'Y', ");
			sql.append(" rw.upd_date = sysdate, ");
			sql.append(" rw.upd_by = '" + userId + "' ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.group_code = '2' ");
			sql.append(" and rw.cre_by = '" + userId + "' ");
			sql.append(" and ( rw.confirm_Flag <> 'Y' or rw.confirm_Flag is null ) ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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

	public void deleteRwIncDecOtherDeduct(String ouCode, String year,
			String period, String userId) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Rw_Inc_Dec_Other rw ");
			sql.append(" where rw.ou_code = '" + ouCode + "' ");
			sql.append(" and rw.year_pr = " + year);
			sql.append(" and rw.period_pr = " + period);
			sql.append(" and rw.confirm_flag = 'Y' ");
			sql.append(" and rw.group_code = '2' ");
			sql.append(" and rw.emp_code in ( ");
			sql.append("	select emp_code ");
			sql.append("	from v_pr_employee_security ");
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
			hql1.append(" from PrEmployeeText pr ");
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
			hql1.append("	and pr.creBy = '" + userId + "' ");
			hql1.append(" and ( pr.confirmFlag <> 'Y' or pr.confirmFlag is null ) ");
			hql1.append(" and ( pr.empCode in ( ");
			hql1.append("	select pk.empCode ");
			hql1.append("	from VPrEmployeeSecurity ");
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
			hql.append(" UPDATE Pr_Employee_Text pr ");
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
			hql.append(" and pr.cre_by = '" + userId + "' ");
			hql.append(" and ( ");
			// hql.append(" pr.emp_code in ( ");
			// hql.append(" select emp_code ");
			// hql.append(" from v_pr_employee_security ");
			// hql.append(" where ou_code = '"+ouCode+"' ");
			// hql.append(" and year = " + yearPr);
			// hql.append(" and period = " + periodPr);
			// hql.append(" and user_id = '"+userId+"' ");
			// hql.append(" ) or ");
			hql.append(" pr.code_seq_work in ( ");
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

	// XXX
	public List findChangeOfMonth(String ouCode, String userId, Integer year,
			Integer period) {
		List list = new ArrayList();
		List listemp = new ArrayList();
		String sql = "";
		sql = " select prm.ouCode,prm.year,prm.period,pno.divCode,pno.divDesc,"
				+ " nvl(pno.secCode,pno.areaCode),pno.secDesc, "
				+ " emp.pk.empCode,"
				+ " emp.refDbPreSuff.prefixName,"
				+ " emp.firstName,"
				+ " emp.lastName,"
				+ " prm.taxId,"
				+ " prm.marriedStatus,"
				+ " prm.payStatus,"
				+ " prm.bankName, "
				+ " prm.bankId||'/'||prm.bankBranch,"
				+ " prm.costChild,"
				+ " prm.childStudy,"
				+ " prm.childNoStudy,"
				+ " prm.costLife,"
				+ " prm.gundanFlag,"
				+ " prm.debtLife,"
				+ " prm.debtLifeSpouse,"
				+ " prm.debtLoan,"
				+ " prm.donate,"
				+ " prm.flagFather,"
				+ " prm.flagMother,"
				+ " prm.flagFatherSpouse,"
				+ " prm.flagMotherSpouse,"
				+ " prm.flagPr,"
				+ " prm.healthFather, "
				+ " prm.handicappedDec, "
				+ " prm.ltf,"
				+ " prm.rmf,"
				+ " prm.flagStatus,"
				+ " pno.areaCode,"
				+ " pno.areaDesc,  "
				+ " prm.keySeq "
				+ " from VPrEmpTextRep prm ,PnOrganization pno,PnEmployee emp "
				+ " where prm.ouCode ='"
				+ ouCode
				+ "'"
				+ " and prm.year="
				+ year
				+ " and prm.period="
				+ period
				+ " and prm.ouCode = pno.pk.ouCode "
				+ " and prm.empCode =emp.pk.empCode "
				+ " and prm.ouCode = emp.pk.ouCode "
				+ " and prm.codeSeqWork = pno.pk.codeSeq "
				+ " and prm.codeSeqWork in ( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '"
				+ userId
				+ "'"
				+ " ) "
				+ " order by pno.deptSeq,pno.divSeq,nvl(pno.areaSeq,0),nvl(pno.secSeq,0),nvl(pno.secCode,nvl(pno.areaCode,pno.divCode)),emp.adminCode,emp.levelCode desc,emp.pk.empCode,prm.keySeq ";
		// " order by
		// nvl(pno.secCode,nvl(pno.areaCode,pno.divCode)),emp.pk.empCode,prm.keySeq
		// "
		// ;
		Session ss = this.getSession();
		Query qq = ss.createQuery(sql);
		list = qq.list();
		int costChild = 0;
		int childStudy = 0;
		int childNoStudy = 0;

		PrEmployeeTextVO prEmpVo = null;
		for (int i = 0; i < list.size(); i++) {
			costChild = 0;
			childStudy = 0;
			childNoStudy = 0;
			Object[] obj = (Object[]) list.get(i);
			prEmpVo = new PrEmployeeTextVO();
			prEmpVo.setOuCode(obj[0] == null ? "" : obj[0].toString());
			prEmpVo.setYear(obj[1] == null ? "" : obj[1].toString());
			prEmpVo.setPeriod(obj[2] == null ? "" : obj[2].toString());
			prEmpVo.setDivCode(obj[3] == null ? "" : obj[3].toString());
			prEmpVo.setDivDesc(obj[4] == null ? "" : obj[4].toString());
			prEmpVo.setSecCode(obj[5] == null ? "" : obj[5].toString());
			prEmpVo.setSecDesc(obj[6] == null ? "" : obj[6].toString());
			prEmpVo.setEmpCode(obj[7] == null ? "" : obj[7].toString());
			prEmpVo.setPrefix(obj[8] == null ? "" : (String) obj[8]);
			prEmpVo.setName(obj[9] == null ? "" : (String) obj[9]);
			prEmpVo.setLastName(obj[10] == null ? "" : (String) obj[10]);
		
			prEmpVo.setTaxId(obj[11] == null ? "" : obj[11].toString());
			prEmpVo.setMarriedStatus(obj[12] == null ? "" : obj[12].toString());
			prEmpVo.setPayStatus(obj[13] == null ? "" : obj[13].toString());
			prEmpVo.setBankName(obj[14] == null ? "" : obj[14].toString());
			prEmpVo.setBankId(obj[15] == null ? "" : obj[15].toString());
			if (obj[16] != null) {
				costChild = new Double(obj[16].toString()).intValue();
				prEmpVo.setCostChild(String.valueOf(costChild));
			} else {
				prEmpVo.setCostChild("");
			}
			// prEmpVo.setCostChild(String.valueOf(costChild).equals("0")?"":String.valueOf(costChild));
			//
			if (obj[17] != null) {
				childStudy = new Double(obj[17].toString()).intValue();
				prEmpVo.setChildStudy(String.valueOf(childStudy));
			} else {
				prEmpVo.setChildStudy("");
			}
			// prEmpVo.setChildStudy(String.valueOf(childStudy).equals("0")?"":String.valueOf(childStudy));

			if (obj[18] != null) {
				childNoStudy = new Double(obj[18].toString()).intValue();
				prEmpVo.setChildNoStudy(String.valueOf(childNoStudy));
			} else {
				prEmpVo.setChildNoStudy("");
			}
			// prEmpVo.setChildNoStudy(String.valueOf(childNoStudy).equals("0")?"":String.valueOf(childNoStudy));

			prEmpVo.setCostLife(obj[19] == null ? "" : obj[19].toString());
			prEmpVo.setGundanFlag(obj[20] == null ? "" : obj[20].toString());
			prEmpVo.setDebtLife(obj[21] == null ? "" : obj[21].toString());
			prEmpVo.setDebtLifeSpouse(obj[22] == null ? "" : obj[22].toString());
			prEmpVo.setDebtLoan(obj[23] == null ? "" : obj[23].toString());
			prEmpVo.setDonate(obj[24] == null ? "" : obj[24].toString());
			prEmpVo.setFlagFather(obj[25] == null ? "" : obj[25].toString());
			prEmpVo.setFlagMother(obj[26] == null ? "" : obj[26].toString());
			prEmpVo.setFlagFatherSpouse(obj[27] == null ? "" : obj[27].toString());
			prEmpVo.setFlagMotherSpouse(obj[28] == null ? "" : obj[28].toString());
			prEmpVo.setFlagPr(obj[29] == null ? "" : obj[29].toString());
			prEmpVo.setHealthFather(obj[30] == null ? "" : obj[30].toString());
			prEmpVo.setHandicappedDec(obj[31] == null ? "" : obj[31].toString());
			prEmpVo.setLtf(obj[32] == null ? "" : obj[32].toString());
			prEmpVo.setRmf(obj[33] == null ? "" : obj[33].toString());
			prEmpVo.setFlagStatus(obj[34] == null ? "" : obj[34].toString());
			prEmpVo.setAreaCode(obj[35] == null ? "" : obj[35].toString());
			prEmpVo.setAreaDesc(obj[36] == null ? "" : obj[36].toString());
			prEmpVo.setKeySeq(obj[37] == null ? "" : obj[37].toString());
			listemp.add(prEmpVo);
		}
		return listemp;
	}

	public List findMoveChangeOfMonth(String ouCode, String userId,
			Integer year, Integer period) {
		List list = new ArrayList();
		List listemp = new ArrayList();
		String sql = "";
		sql = " select prm.ouCode,prm.year,prm.period,pno.divCode,pno.divDesc,"
				+ " nvl(pno.secCode,pno.areaCode),pno.secDesc, "
				+ " emp.pk.empCode,"
				+ " emp.refDbPreSuff.prefixName,"
				+ " emp.firstName,"
				+ " emp.lastName,"
				+ " prm.taxId,"
				+ " prm.marriedStatus,"
				+ " prm.payStatus,"
				+ " prm.bankId||'/'||prm.bankBranch,"
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
				+ " prm.flagPr,"
				+ " prm.healthFather, "
				+ " prm.handicappedDec, "
				+ " prm.ltf,"
				+ " prm.rmf,"
				+ " prm.flagStatus,"
				+ " pno.areaCode,"
				+ " pno.areaDesc,  "
				+ " prm.keySeq "
				+ " from VPrEmpTextMoveRep prm ,PnOrganization pno,PnEmployee emp "
				+ " where prm.ouCode ='"
				+ ouCode
				+ "'"
				+ " and prm.year="
				+ year
				+ " and prm.period="
				+ period
				+ " and prm.ouCode = pno.pk.ouCode "
				+ " and prm.empCode =emp.pk.empCode "
				+ " and prm.ouCode = emp.pk.ouCode "
				+ " and prm.codeSeqWork = pno.pk.codeSeq "
				+ " and prm.codeSeqWork in ( "
				+ " select vorg.pk.codeSeq "
				+ " from VPnOrganizationSecurity vorg "
				+ " where vorg.pk.userId = '"
				+ userId
				+ "'"
				+ " ) "
				+ " order by pno.deptSeq,pno.divSeq,nvl(pno.areaSeq,0),nvl(pno.secSeq,0),nvl(pno.secCode,nvl(pno.areaCode,pno.divCode)),emp.adminCode,emp.levelCode desc,emp.pk.empCode,prm.keySeq ";
		// " order by
		// nvl(pno.secCode,nvl(pno.areaCode,pno.divCode)),emp.pk.empCode,prm.keySeq
		// "
		// ;
		Session ss = this.getSession();
		Query qq = ss.createQuery(sql);
		list = qq.list();
		int costChild = 0;
		int childStudy = 0;
		int childNoStudy = 0;

		PrEmployeeTextVO prEmpVo = null;
		for (int i = 0; i < list.size(); i++) {
			costChild = 0;
			childStudy = 0;
			childNoStudy = 0;
			Object[] obj = (Object[]) list.get(i);
			prEmpVo = new PrEmployeeTextVO();
			prEmpVo.setOuCode(obj[0] == null ? "" : obj[0].toString());
			prEmpVo.setYear(obj[1] == null ? "" : obj[1].toString());
			prEmpVo.setPeriod(obj[2] == null ? "" : obj[2].toString());
			prEmpVo.setDivCode(obj[3] == null ? "" : obj[3].toString());
			prEmpVo.setDivDesc(obj[4] == null ? "" : obj[4].toString());
			prEmpVo.setSecCode(obj[5] == null ? "" : obj[5].toString());
			prEmpVo.setSecDesc(obj[6] == null ? "" : obj[6].toString());
			prEmpVo.setEmpCode(obj[7] == null ? "" : obj[7].toString());
			prEmpVo.setPrefix(obj[8] == null ? "" : (String) obj[8]);
			prEmpVo.setName(obj[9] == null ? "" : (String) obj[9]);
			prEmpVo.setLastName(obj[10] == null ? "" : (String) obj[10]);
			// prEmpVo.setFullName(obj[8]==null?"":(String)obj[8]+obj[9]==null?"":(String)obj[9]+"
			// "+obj[10]==null?"":(String)obj[10]);
			prEmpVo.setTaxId(obj[11] == null ? "" : obj[11].toString());
			prEmpVo.setMarriedStatus(obj[12] == null ? "" : obj[12].toString());
			prEmpVo.setPayStatus(obj[13] == null ? "" : obj[13].toString());
			prEmpVo.setBankId(obj[14] == null ? "" : obj[14].toString());
			if (obj[15] != null) {
				costChild = new Double(obj[15].toString()).intValue();
				prEmpVo.setCostChild(String.valueOf(costChild));
			} else {
				prEmpVo.setCostChild("");
			}
			// prEmpVo.setCostChild(String.valueOf(costChild).equals("0")?"":String.valueOf(costChild));
			//
			if (obj[16] != null) {
				childStudy = new Double(obj[16].toString()).intValue();
				prEmpVo.setChildStudy(String.valueOf(childStudy));
			} else {
				prEmpVo.setChildStudy("");
			}
			// prEmpVo.setChildStudy(String.valueOf(childStudy).equals("0")?"":String.valueOf(childStudy));

			if (obj[17] != null) {
				childNoStudy = new Double(obj[17].toString()).intValue();
				prEmpVo.setChildNoStudy(String.valueOf(childNoStudy));
			} else {
				prEmpVo.setChildNoStudy("");
			}
			// prEmpVo.setChildNoStudy(String.valueOf(childNoStudy).equals("0")?"":String.valueOf(childNoStudy));

			prEmpVo.setCostLife(obj[18] == null ? "" : obj[18].toString());
			prEmpVo.setGundanFlag(obj[19] == null ? "" : obj[19].toString());
			prEmpVo.setDebtLife(obj[20] == null ? "" : obj[20].toString());
			prEmpVo.setDebtLoan(obj[21] == null ? "" : obj[21].toString());
			prEmpVo.setDonate(obj[22] == null ? "" : obj[22].toString());
			prEmpVo.setFlagFather(obj[23] == null ? "" : obj[23].toString());
			prEmpVo.setFlagMother(obj[24] == null ? "" : obj[24].toString());
			prEmpVo.setFlagFatherSpouse(obj[25] == null ? "" : obj[25]
					.toString());
			prEmpVo.setFlagMotherSpouse(obj[26] == null ? "" : obj[26]
					.toString());
			prEmpVo.setFlagPr(obj[27] == null ? "" : obj[27].toString());
			prEmpVo.setHealthFather(obj[28] == null ? "" : obj[28].toString());
			prEmpVo.setHandicappedDec(obj[29] == null ? "" : obj[29].toString());
			prEmpVo.setLtf(obj[30] == null ? "" : obj[30].toString());
			prEmpVo.setRmf(obj[31] == null ? "" : obj[31].toString());
			prEmpVo.setFlagStatus(obj[32] == null ? "" : obj[32].toString());
			prEmpVo.setAreaCode(obj[33] == null ? "" : obj[33].toString());
			prEmpVo.setAreaDesc(obj[34] == null ? "" : obj[34].toString());
			prEmpVo.setKeySeq(obj[35] == null ? "" : obj[35].toString());
			listemp.add(prEmpVo);
		}
		return listemp;
	}

	public RwConfirmDataDAO getRwConfirmDataDAO() {
		return rwConfirmDataDAO;
	}

	public void setRwConfirmDataDAO(RwConfirmDataDAO rwConfirmDataDAO) {
		this.rwConfirmDataDAO = rwConfirmDataDAO;
	}

	public void deleteOldPrEmployeeText(PrEmployeeTextVO vo) throws Exception {

		try {
			StringBuffer sql = new StringBuffer(0);
			sql.append(" delete Pr_employee_text  ");
			sql.append(" where ou_code = '" + vo.getOuCode() + "' ");
			sql.append(" and year = " + vo.getYear());
			sql.append(" and period = " + vo.getPeriod());
			sql.append(" and emp_code = '" + vo.getEmpCode() + "' ");
			sql.append(" and code_seq_work = " + vo.getCodeSeqWork());
			// sql.append(" and key_seq < "+vo.getKeySeq());
			sql.append(" and cre_by = '" + vo.getCreBy() + "' ");

			System.out.println("sql : " + sql.toString());

			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void deleteIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {
		try {
			this.deleteRwDangerIncomeError(ouCode, yearPr, periodPr, userId);
			this.deleteRwOvertimeIncomeError(ouCode, yearPr, periodPr, userId);
			this.deleteRwIncDecOtherIncomeError(ouCode, yearPr, periodPr, userId);
			this.deleteRwPremiumIncomeError(ouCode, yearPr, periodPr, userId);
			this.deleteRwHealthIncomeError(ouCode, yearPr, periodPr, userId);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void deleteDeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {
		try {
			this.deleteRwIncDecOtherDeductError(ouCode, yearPr, periodPr, userId);
			this.deleteRwModSalIncomeError(ouCode, yearPr, periodPr, userId);
			this.deleteRwVinai2DeductError(ouCode, yearPr, periodPr, userId);
			this.deleteRwVinaiDeductError(ouCode, yearPr, periodPr, userId);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void deleteRwDangerIncomeError(String ouCode, String yearPr, String periodPr,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_danger rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_danger rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwHealthIncomeError(String ouCode, String yearPr, String periodPr,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_health rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_health rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwIncDecOtherDeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Inc_dec_other rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.group_code = '2' ");
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Inc_dec_other rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql1.append(" and rw.group_code = '2' ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwIncDecOtherIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Inc_dec_other rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.group_code = '1' ");
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Inc_dec_other rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql1.append(" and rw.group_code = '1' ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwModSalIncomeError(String ouCode, String yearPr, String periodPr,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_mod_sal rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_mod_sal rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwOvertimeIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Overtime rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Overtime rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwPremiumIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Premium rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Premium rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void deleteRwVinai2DeductError(String ouCode, String yearPr, String periodPr,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Vinai2 rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Vinai2 rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	public void deleteRwVinaiDeductError(String ouCode, String yearPr, String periodPr,
			String userId) throws Exception {

		try {
			StringBuffer sql1 = new StringBuffer(0);
			sql1.append(" delete Rw_Vinai rw ");
			sql1.append(" where rw.ou_code = '" + ouCode + "' ");
			sql1.append(" and rw.year_pr = " + yearPr);
			sql1.append(" and rw.period_pr = " + periodPr);
			sql1.append(" and rw.code_seq < 20000 ");
			sql1.append(" and rw.emp_code in ( ");
			sql1.append("	select emp_code ");
			sql1.append("	from v_pr_employee_security ");
			sql1.append("	where user_id = '" + userId + "' ");
			sql1.append("	and year = " + yearPr);
			sql1.append("	and period = " + periodPr);
			sql1.append("	and ou_code = rw.ou_code ");
			sql1.append(" ) ");

			System.out.println("sql1 : " + sql1.toString());

			this.jdbcTemplate.execute(sql1.toString());
			
			StringBuffer sql2 = new StringBuffer(0);
			sql2.append(" delete Rw_Vinai rw ");
			sql2.append(" where rw.ou_code = '" + ouCode + "' ");
			sql2.append(" and rw.year_pr = " + yearPr);
			sql2.append(" and rw.period_pr = " + periodPr);
			sql2.append(" and rw.code_seq is null ");
			sql2.append(" and rw.emp_code in ( ");
			sql2.append("	select emp_code ");
			sql2.append("	from v_pr_employee_security ");
			sql2.append("	where user_id = '" + userId + "' ");
			sql2.append("	and year = " + yearPr);
			sql2.append("	and period = " + periodPr);
			sql2.append("	and ou_code = rw.ou_code ");
			sql2.append(" ) ");

			System.out.println("sql2 : " + sql2.toString());

			this.jdbcTemplate.execute(sql2.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
