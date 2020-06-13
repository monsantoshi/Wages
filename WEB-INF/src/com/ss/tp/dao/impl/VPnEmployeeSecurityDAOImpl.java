package com.ss.tp.dao.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.VPnEmployeeSecurityDAO;
import com.ss.tp.dto.VPnEmployeeVO;
import com.ss.tp.model.VPnEmployeeSecurity;
import com.ss.tp.model.VPnEmployeeSecurityPK;

public class VPnEmployeeSecurityDAOImpl extends HibernateDaoSupport implements
		VPnEmployeeSecurityDAO {

	public VPnEmployeeVO findByKey(String ouCode, String userId, String empCode) {
		VPnEmployeeSecurityPK pk = new VPnEmployeeSecurityPK();
		pk.setOuCode(ouCode);
		pk.setUserId(userId);
		pk.setEmpCode(Double.valueOf(empCode));
		VPnEmployeeSecurity ec = (VPnEmployeeSecurity) getSession().load(
				VPnEmployeeSecurity.class, pk);
		VPnEmployeeVO ret = new VPnEmployeeVO();
		try {
			BeanUtils.copyProperties(ret, ec.getPk());
			BeanUtils.copyProperties(ret, ec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
