package com.ss.tp.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ss.tp.dto.GwIncdecEmployeeVO;
import com.ss.tp.dto.WgPrOvertimeVO;
import com.ss.tp.model.WgPrOvertime;

;

public interface WgPrOvertimeDAO {
	public void insertWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception;

	public void updateWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception;

	public void deleteWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception;

	public void insertWgPrOvertimes(List rwovertimevolist) throws Exception;

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

	public GwIncdecEmployeeVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException;

	public WgPrOvertime loadWgPrOvertime(WgPrOvertimeVO rpVo);

	public void addList(WgPrOvertimeVO rwovertimevo);

	public void insertAndUpdateWgPrOvertimes() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findWgPrOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus);

}