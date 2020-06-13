package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.SrPvfEmpVO;

public interface SrPvfEmpService {
	public List findMDate(String evaOuCode)
			throws Exception;
	public List findChgRateDate(String evaOuCode)
			throws Exception;
	public List findChgMasterDate(String evaOuCode)
			throws Exception;
	public List findEmpStatusDate(String evaOuCode)
			throws Exception;
	public List findLastDate(String evaOuCode)
			throws Exception;
	
	public List CTPFRP207(String ouCode,String ageYear, String dDate);
	public List CTPFRP070(String dDate);
	public List CTPFRP029(String ouCode,String dDate);
	public List CTPFRP019(String dDate);
	public List CTPFRP034(String dDate);
	public List CTPFRP001(String ouCode,String status);
}