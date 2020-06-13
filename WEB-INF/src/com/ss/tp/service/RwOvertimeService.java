package com.ss.tp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwOvertimeVO;
import com.ss.tp.model.RwOvertime;

public interface RwOvertimeService {
	public void insertRwOvertime(RwOvertimeVO rwovertimevo) throws Exception;

	public void updateRwOvertime(RwOvertimeVO rwovertimevo) throws Exception;

	public void deleteRwOvertime(RwOvertimeVO rwovertimevo) throws Exception;

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

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException;

	public RwOvertime loadData(RwOvertimeVO rpVo);

	public void addList(RwOvertimeVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public List findRwOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus);

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;
}