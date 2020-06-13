/*
 * Created on 4 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaViewService {

	public List findListEmpDetail(int workYear, String ouCode, String userId,
			String codeSeq, String empCodeFrom, String empCodeTo);

	public List findListWgEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo);

	public List findListEmpDetailNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice);

	public List findListWgEmpDetailNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice);
}
