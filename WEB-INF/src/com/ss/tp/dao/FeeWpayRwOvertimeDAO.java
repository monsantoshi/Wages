package com.ss.tp.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwOvertimeVO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.model.FeeWpayRwOvertime;

public interface FeeWpayRwOvertimeDAO {
	public void insertRwOvertime(FeeWpayRwOvertimeVO rwovertimevo) throws Exception;

	public void updateRwOvertime(FeeWpayRwOvertimeVO rwovertimevo) throws Exception;

	public void deleteRwOvertime(FeeWpayRwOvertimeVO rwovertimevo) throws Exception;

	public void insertRwOvertimes(List rwovertimevolist) throws Exception;

	public List findOverTimeByCriteria(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period, int pageRecord, int totalRecord);

	public Integer findCountOverTimeByCriteria(String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String otTypeFrom, String flagPrFrom,
			String flagPrTo, String refNoFrom, String refNoTo, String ouCode,
			Long year, Long period);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException;

	public List findRwOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus);

	public List findRwOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus,String approveFlag);

	
	public FeeWpayRwOvertime loadRwOvertime(FeeWpayRwOvertimeVO rpVo);

	public void addList(FeeWpayRwOvertimeVO rwovertimevo);

	public void insertAndUpdateRwOvertimes() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;
	
	
	
	public Integer countDataApprove(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period);
	
	public void addListApprove(FeeWpayRwOvertimeVO rwovertimevo);
	
	public void insertAndUpdateApTablesApprove() throws Exception;
	
	public List findByCriteriaListApprove(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period, int count, int countRecord);
	
	public void updateApTableApprove(FeeWpayRwOvertimeVO rwovertimevo)
			throws Exception;


}