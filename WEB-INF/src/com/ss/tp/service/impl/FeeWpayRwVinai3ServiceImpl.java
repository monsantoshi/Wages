package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayRwVinai3DAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwVinai3VO;
import com.ss.tp.service.FeeWpayRwVinai3Service;

public class FeeWpayRwVinai3ServiceImpl implements FeeWpayRwVinai3Service, Serializable {

	private FeeWpayRwVinai3DAO FeeWpayRwVinai3DAO;

	public FeeWpayRwVinai3DAO getFeeWpayRwVinai3DAO() {
		return FeeWpayRwVinai3DAO;
	}

	public void setFeeWpayRwVinai3DAO(FeeWpayRwVinai3DAO FeeWpayRwVinai3DAO) {
		this.FeeWpayRwVinai3DAO = FeeWpayRwVinai3DAO;
	}

	public void insertRwVinai3(FeeWpayRwVinai3VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai3DAO.insertRwVinai3(rwvinai2vo);
	}

	public void updateRwVinai3(FeeWpayRwVinai3VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai3DAO.updateRwVinai3(rwvinai2vo);
	}

	public void deleteRwVinai3(FeeWpayRwVinai3VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai3DAO.deleteRwVinai3(rwvinai2vo);
	}

	public void insertRwVinai3s(List rwvinai2volist) throws Exception {
		this.FeeWpayRwVinai3DAO.insertRwVinai3s(rwvinai2volist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.FeeWpayRwVinai3DAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.FeeWpayRwVinai3DAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.FeeWpayRwVinai3DAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.FeeWpayRwVinai3DAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(FeeWpayRwVinai3VO rpVo, boolean isSave) {
		this.FeeWpayRwVinai3DAO.addList(rpVo);

		if (isSave) {
			try {
				// this.insertAndUpdate();
				this.FeeWpayRwVinai3DAO.insertAndUpdateRwVinai3s();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.FeeWpayRwVinai3DAO.insertAndUpdateRwVinai3s();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinai3DAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinai3DAO.canDelete(ouCode, year, period, userId);
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
		// TODO Auto-generated method stub
		return this.FeeWpayRwVinai3DAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo,count,	countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		// TODO Auto-generated method stub
		return this.FeeWpayRwVinai3DAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}

	//@Override
	public void addListApprove(FeeWpayRwVinai3VO feewpayrwvinai2vo, boolean isSave) {
		this.FeeWpayRwVinai3DAO.addListApprove(feewpayrwvinai2vo);

		if (isSave) {
			try {
				this.FeeWpayRwVinai3DAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(FeeWpayRwVinai3VO feewpayrwvinai2vo)
			throws Exception {
		this.FeeWpayRwVinai3DAO.updateApTableApprove(feewpayrwvinai2vo);
		
	}
	
	public List rwVinai3Report(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return FeeWpayRwVinai3DAO.rwVinai3Report(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}
	
	
}
