/*
 * Created on 4 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaViewDAO {

	public List findListEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo);

	public List findListWgEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo);

	public List findListEmpDetailNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice);

	public List findListWgEmpDetailNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice);
}
