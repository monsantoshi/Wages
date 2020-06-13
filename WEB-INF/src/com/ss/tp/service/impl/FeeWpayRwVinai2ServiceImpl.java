package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayRwVinai2DAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinai2VO;
import com.ss.tp.service.FeeWpayRwVinai2Service;

public class FeeWpayRwVinai2ServiceImpl implements FeeWpayRwVinai2Service, Serializable {

	private FeeWpayRwVinai2DAO FeeWpayRwVinai2DAO;

	public FeeWpayRwVinai2DAO getFeeWpayRwVinai2DAO() {
		return FeeWpayRwVinai2DAO;
	}

	public void setFeeWpayRwVinai2DAO(FeeWpayRwVinai2DAO FeeWpayRwVinai2DAO) {
		this.FeeWpayRwVinai2DAO = FeeWpayRwVinai2DAO;
	}

	public void insertRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai2DAO.insertRwVinai2(rwvinai2vo);
	}

	public void updateRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai2DAO.updateRwVinai2(rwvinai2vo);
	}

	public void deleteRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.FeeWpayRwVinai2DAO.deleteRwVinai2(rwvinai2vo);
	}

	public void insertRwVinai2s(List rwvinai2volist) throws Exception {
		this.FeeWpayRwVinai2DAO.insertRwVinai2s(rwvinai2volist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.FeeWpayRwVinai2DAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.FeeWpayRwVinai2DAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.FeeWpayRwVinai2DAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.FeeWpayRwVinai2DAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwVinai2VO rpVo, boolean isSave) {
		this.FeeWpayRwVinai2DAO.addList(rpVo);

		if (isSave) {
			try {
				// this.insertAndUpdate();
				this.FeeWpayRwVinai2DAO.insertAndUpdateRwVinai2s();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.FeeWpayRwVinai2DAO.insertAndUpdateRwVinai2s();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinai2DAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinai2DAO.canDelete(ouCode, year, period, userId);
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
		// TODO Auto-generated method stub
		return this.FeeWpayRwVinai2DAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo,count,	countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		// TODO Auto-generated method stub
		return this.FeeWpayRwVinai2DAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}

	//@Override
	public void addListApprove(RwVinai2VO feewpayrwvinai2vo, boolean isSave) {
		this.FeeWpayRwVinai2DAO.addListApprove(feewpayrwvinai2vo);

		if (isSave) {
			try {
				this.FeeWpayRwVinai2DAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(RwVinai2VO feewpayrwvinai2vo)
			throws Exception {
		this.FeeWpayRwVinai2DAO.updateApTableApprove(feewpayrwvinai2vo);
		
	}
	
	public List rwVinai2Report(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return FeeWpayRwVinai2DAO.rwVinai2Report(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}
	
	
}
