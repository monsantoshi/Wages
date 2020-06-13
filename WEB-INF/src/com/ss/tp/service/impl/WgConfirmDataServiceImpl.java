/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;

import com.ss.tp.dao.WgConfirmDataDAO;
import com.ss.tp.dto.WgConfirmDataVO;
import com.ss.tp.service.WgConfirmDataService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgConfirmDataServiceImpl implements WgConfirmDataService,
		Serializable {
	private WgConfirmDataDAO wgConfirmDataDAO;

	public WgConfirmDataDAO getWgConfirmDataDAO() {
		return wgConfirmDataDAO;
	}

	public void setWgConfirmDataDAO(WgConfirmDataDAO wgConfirmDataDAO) {
		this.wgConfirmDataDAO = wgConfirmDataDAO;
	}

	public void insertWgConfirmData(WgConfirmDataVO vo) throws Exception {
		this.wgConfirmDataDAO.insertWgConfirmData(vo);
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.wgConfirmDataDAO.isConfirmMasterData(ouCode, year, period,
				userId);
	}

	public boolean isConfirmWgData(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.wgConfirmDataDAO.isConfirmWgData(ouCode, year, period,
				userId);
	}
}
