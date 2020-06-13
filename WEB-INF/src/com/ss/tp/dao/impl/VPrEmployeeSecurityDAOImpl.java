package com.ss.tp.dao.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.VPrEmployeeSecurityDAO;
import com.ss.tp.dto.VPrEmployeeVO;
import com.ss.tp.model.VPrEmployeeSecurity;
import com.ss.tp.model.VPrEmployeeSecurityPK;

public class VPrEmployeeSecurityDAOImpl extends HibernateDaoSupport implements
		VPrEmployeeSecurityDAO {

	public VPrEmployeeVO findByKey(String ouCode, String userId,
			String empCode, String year, String period) {
		VPrEmployeeSecurityPK pk = new VPrEmployeeSecurityPK();
		pk.setOuCode(ouCode);
		pk.setUserId(userId);
		pk.setEmpCode(Double.valueOf(empCode));
		pk.setYear(Double.valueOf(year));
		pk.setPeriod(Double.valueOf(period));
		VPrEmployeeSecurity ec = (VPrEmployeeSecurity) getSession().load(
				VPrEmployeeSecurity.class, pk);
		VPrEmployeeVO ret = new VPrEmployeeVO();
		try {
			BeanUtils.copyProperties(ret, ec.getPk());
			BeanUtils.copyProperties(ret, ec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
