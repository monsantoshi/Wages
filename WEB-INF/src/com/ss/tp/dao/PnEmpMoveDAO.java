package com.ss.tp.dao;

//import com.ss.tp.dto.DefaultYearSectionVO;
//import com.ss.tp.dto.PeEmployeeScoreVO;

//import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PnEmpMoveDAO {

	public List findByCriteriaReport1(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup);

	public List findByCriteriaReport2(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup);
	// public String findFormCode(String ouCode,Long evaYear,Long evaTime,String
	// empCode);
	// public DefaultYearSectionVO findMaxYearPeriod(String ouCode);
}
