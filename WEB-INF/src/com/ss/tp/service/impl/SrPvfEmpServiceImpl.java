package com.ss.tp.service.impl;

import java.util.List;


import com.ss.tp.dao.SrPvfEmpDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.SrPvfEmpVO;
import com.ss.tp.service.SrPvfEmpService;

public class SrPvfEmpServiceImpl implements SrPvfEmpService {

	private SrPvfEmpDAO srPvfEmpDAO;
	
	public SrPvfEmpDAO getSrPvfEmpDAO() {
		return srPvfEmpDAO;
	}

	public void setSrPvfEmpDAO(SrPvfEmpDAO srPvfEmpDAO) {
		this.srPvfEmpDAO = srPvfEmpDAO;
	}
	public List findMDate(String ouCode)
			throws Exception {
		return this.srPvfEmpDAO.findMDate(ouCode);
	}
	public List findChgRateDate(String ouCode)
			throws Exception {
		return this.srPvfEmpDAO.findChgRateDate(ouCode);
	}
	public List findChgMasterDate(String ouCode)
			throws Exception {
		return this.srPvfEmpDAO.findChgMasterDate(ouCode);
	}
	public List findEmpStatusDate(String ouCode)
			throws Exception {
		return this.srPvfEmpDAO.findEmpStatusDate(ouCode);
	}
	public List findLastDate(String ouCode)
			throws Exception {
		return this.srPvfEmpDAO.findLastDate(ouCode);
	}
	

	public List CTPFRP207(String ouCode,String ageYear, String dDate) {
		return this.srPvfEmpDAO.CTPFRP207(ouCode, ageYear, dDate);
	}

	public List CTPFRP070( String dDate) {
		// TODO Auto-generated method stub
		return this.srPvfEmpDAO.CTPFRP070( dDate);
	}

	public List CTPFRP029(String ouCode,String dDate) {
		// TODO Auto-generated method stub
		return this.srPvfEmpDAO.CTPFRP029(ouCode,dDate);
	}

	public List CTPFRP019(String dDate) {
		// TODO Auto-generated method stub
		return this.srPvfEmpDAO.CTPFRP019(dDate);
	}

	public List CTPFRP034(String dDate) {
		// TODO Auto-generated method stub
		return this.srPvfEmpDAO.CTPFRP034(dDate);
	}
	public List CTPFRP001(String ouCode,  String status) {
		// TODO Auto-generated method stub
		return this.srPvfEmpDAO.CTPFRP001(ouCode, status);
	}
	
	
	
}
