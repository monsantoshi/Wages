package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.SrPvfEmpVO;

public interface SrPvfEmpDAO {
	

	public List findMDate(String ouCode) throws Exception;
	public List findEmpStatusDate(String ouCode) throws Exception;
	public List findLastDate(String ouCode) throws Exception;
	public List findChgRateDate(String ouCode) throws Exception;
	public List findChgMasterDate(String ouCode) throws Exception;
	public List CTPFRP207(String ouCode, String ageYear, String dDate);
	public List CTPFRP070(String dDate);
	public List CTPFRP029(String ouCode,String dDate);
	public List CTPFRP019(String dDate);
	public List CTPFRP034(String dDate);
	public List CTPFRP001(String ouCode, String status);
	



	

}