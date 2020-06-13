package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.FeeContractDetailsDAO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeContractDetailsVO;
import com.ss.tp.service.FeeContractDetailsService;

public class FeeContractDetailsServiceImpl implements FeeContractDetailsService, Serializable {

	private FeeContractDetailsDAO feeContractDetailsDAO;

	public FeeContractDetailsDAO getFeeContractDetailsDAO() {
		return feeContractDetailsDAO;
	}

	public void setFeeContractDetailsDAO(FeeContractDetailsDAO feeContractDetailsDAO) {
		this.feeContractDetailsDAO = feeContractDetailsDAO;
	}

	public void insertFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		this.feeContractDetailsDAO.insertFeeContractDetails(feeContractDetailsvo);
	}

	public void updateFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		this.feeContractDetailsDAO.updateFeeContractDetails(feeContractDetailsvo);
	}

	public void deleteFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception {
		this.feeContractDetailsDAO.deleteFeeContractDetails(feeContractDetailsvo);
	}

	public void insertFeeContractDetailss(List feeContractDetailsvolist) throws Exception {
		this.feeContractDetailsDAO.insertFeeContractDetailss(feeContractDetailsvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.feeContractDetailsDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.feeContractDetailsDAO.countData(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom,
				evaFlagTo);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.feeContractDetailsDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.feeContractDetailsDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public List feeContractDetailsReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag) {
		return this.feeContractDetailsDAO.feeContractDetailsReportByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

	public void addList(FeeContractDetailsVO rpVo, boolean isSave) {
		this.feeContractDetailsDAO.addList(rpVo);

		if (isSave) {
			try {
				this.feeContractDetailsDAO.insertAndUpdateFeeContractDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.feeContractDetailsDAO.insertAndUpdateFeeContractDetails();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeContractDetailsDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeContractDetailsDAO.canDelete(ouCode, year, period, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq, int count, int countRecord){
		return this.feeContractDetailsDAO.findByCriteriaList( userId,  evaOuCode, evaContractNo,
				 evaYear,  evaCodeSeq, count,countRecord);
	}

	public void generate(String userId, String ouCode, Long year, Long period) {
		this.feeContractDetailsDAO.generate(userId, ouCode, year, period);
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			String evaFlagFrom, String evaFlagTo, int count, int countRecord) {
		// TODO Auto-generated method stub
		return this.feeContractDetailsDAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			String evaFlagFrom, String evaFlagTo) {
		// TODO Auto-generated method stub
		return this.feeContractDetailsDAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	//@Override
	public void addListApprove(FeeContractDetailsVO feeContractDetailsvo, boolean isSave) {
		this.feeContractDetailsDAO.addListApprove(feeContractDetailsvo);

		if (isSave) {
			try {
				this.feeContractDetailsDAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(FeeContractDetailsVO feeContractDetailsvo)
			throws Exception {
		this.feeContractDetailsDAO.updateApTableApprove(feeContractDetailsvo);
		
	}

	
	public Integer countDataList(String userId, String evaOuCode,String evaContractNo,String evaYear, String evaCodeSeq){
		return this.feeContractDetailsDAO.countDataList(userId,evaOuCode,evaContractNo,evaYear,evaCodeSeq);
	}
	

}
