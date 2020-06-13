package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.WgPrIncDecOtherVO;

public interface WgPrIncDecOtherDAO {
	public void insertWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void updateWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void deleteWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void insertWgPrIncDecOthers(List rwincdecothervolist)
			throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode);

	public void addList(WgPrIncDecOtherVO rwincdecothervo);

	public void insertAndUpdateWgPrIncDecOthers() throws Exception;

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception;

	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag);

	public List decOtherReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String incDecCode, String evaFlag);
}