package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.FeeWpayRwPremiumDAO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwPremiumVO;
import com.ss.tp.service.FeeWpayRwPremiumService;

public class FeeWpayRwPremiumServiceImpl implements FeeWpayRwPremiumService, Serializable {

	private FeeWpayRwPremiumDAO feeWpayRwPremiumDAO;

	public FeeWpayRwPremiumDAO getFeeWpayRwPremiumDAO() {
		return feeWpayRwPremiumDAO;
	}

	public void setFeeWpayRwPremiumDAO(FeeWpayRwPremiumDAO feeWpayRwPremiumDAO) {
		this.feeWpayRwPremiumDAO = feeWpayRwPremiumDAO;
	}

	public void insertRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception {
		this.feeWpayRwPremiumDAO.insertRwPremium(rwpremiumvo);
	}

	public void updateRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception {
		this.feeWpayRwPremiumDAO.updateRwPremium(rwpremiumvo);
	}

	public void deleteRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception {
		this.feeWpayRwPremiumDAO.deleteRwPremium(rwpremiumvo);
	}

	public void insertRwPremiums(List rwpremiumvolist) throws Exception {
		this.feeWpayRwPremiumDAO.insertRwPremiums(rwpremiumvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.feeWpayRwPremiumDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.feeWpayRwPremiumDAO.countData(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom,
				evaFlagTo);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.feeWpayRwPremiumDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.feeWpayRwPremiumDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag) {
		return this.feeWpayRwPremiumDAO.rwPremiumReportByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

	
	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag,String evaAppFlag) {
		return this.feeWpayRwPremiumDAO.rwPremiumReportByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag,evaAppFlag);
	}

	public void addList(FeeWpayRwPremiumVO rpVo, boolean isSave) {
		this.feeWpayRwPremiumDAO.addList(rpVo);

		if (isSave) {
			try {
				this.feeWpayRwPremiumDAO.insertAndUpdateRwPremiums();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.feeWpayRwPremiumDAO.insertAndUpdateRwPremiums();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwPremiumDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwPremiumDAO.canDelete(ouCode, year, period, userId);
	}

	
	public List findByCriteriaListM(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.feeWpayRwPremiumDAO.findByCriteriaListM(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo);
	}
	
	public List findByCriteriaList(String userId, String evaOuCode,			
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord) {
		return this.feeWpayRwPremiumDAO.findByCriteriaList(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, count, countRecord);
	}
	public Integer countDataList(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.feeWpayRwPremiumDAO.countDataList(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo);
	}
	public void generate(String userId, String ouCode, Long year, Long period) {
		this.feeWpayRwPremiumDAO.generate(userId, ouCode, year, period);
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
		// TODO Auto-generated method stub
		return this.feeWpayRwPremiumDAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo,count,countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		// TODO Auto-generated method stub
		return this.feeWpayRwPremiumDAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}

	//@Override
	public void addListApprove(FeeWpayRwPremiumVO feewpayrwpremiumvo, boolean isSave) {
		this.feeWpayRwPremiumDAO.addListApprove(feewpayrwpremiumvo);

		if (isSave) {
			try {
				this.feeWpayRwPremiumDAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(FeeWpayRwPremiumVO feewpayrwpremiumvo)
			throws Exception {
		this.feeWpayRwPremiumDAO.updateApTableApprove(feewpayrwpremiumvo);
		
	}

}
