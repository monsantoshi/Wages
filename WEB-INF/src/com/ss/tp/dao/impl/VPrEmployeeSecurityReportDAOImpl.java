package com.ss.tp.dao.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.VPrEmployeeSecurityReportDAO;
import com.ss.tp.dto.VPrEmployeeReportVO;
import com.ss.tp.model.VPrEmployeeSecurityReport;
import com.ss.tp.model.VPrEmployeeSecurityReportPK;

public class VPrEmployeeSecurityReportDAOImpl extends HibernateDaoSupport
		implements VPrEmployeeSecurityReportDAO {

	public VPrEmployeeReportVO findByKey(String ouCode, String userId,
			String empCode, String year, String period) {
		VPrEmployeeSecurityReportPK pk = new VPrEmployeeSecurityReportPK();
		pk.setOuCode(ouCode);
		pk.setUserId(userId);
		pk.setEmpCode(Double.valueOf(empCode));
		pk.setYear(Double.valueOf(year));
		pk.setPeriod(Double.valueOf(period));
		VPrEmployeeSecurityReport ec = (VPrEmployeeSecurityReport) getSession()
				.load(VPrEmployeeSecurityReport.class, pk);
		VPrEmployeeReportVO ret = new VPrEmployeeReportVO();
		try {
			BeanUtils.copyProperties(ret, ec.getPk());
			BeanUtils.copyProperties(ret, ec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
