/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;

import com.ss.tp.dao.RwConfirmDataDAO;
import com.ss.tp.dto.RwConfirmDataVO;
import com.ss.tp.service.RwConfirmDataService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class RwConfirmDataServiceImpl implements RwConfirmDataService,
		Serializable {
	private RwConfirmDataDAO rwConfirmData;

	public void insertRwConfirmData(RwConfirmDataVO vo) throws Exception {
		this.rwConfirmData.insertRwConfirmData(vo);
	}

	public RwConfirmDataDAO getRwConfirmData() {
		return rwConfirmData;
	}

	public void setRwConfirmData(RwConfirmDataDAO rwConfirmData) {
		this.rwConfirmData = rwConfirmData;
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.rwConfirmData.isConfirmMasterData(ouCode, year, period,
				userId);
	}

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.rwConfirmData.isConfirmRwData(ouCode, year, period, userId);
	}
	
}
