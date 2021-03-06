package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.RwIncDecOtherVO;

public interface RwIncDecOtherService {
	public void insertRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void updateRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void deleteRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception;

	public void insertRwIncDecOthers(List rwincdecothervolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode);

	public void addList(RwIncDecOtherVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean canDeleteIncome(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDeleteDeduct(String ouCode, String year, String period,
			String userId) throws Exception;

	public List incDecOtherReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag);

	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag);

	public List decOtherReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode, String evaFlag);

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag);
	
	public List decOutAmtReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode);

	public List decOutAmtReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode);

	public void generate(String userId, String ouCode, Long year, Long period);

	public void generate59(String userId, String ouCode, Long year, Long period);
}