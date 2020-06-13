package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.PrEmployeeDAO;
import com.ss.tp.dao.PrEmployeeTextDAO;
import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.service.PrEmployeeTextService;

public class PrEmployeeTextServiceImpl implements PrEmployeeTextService {

	private PrEmployeeTextDAO prEmployeeTextDAO;
	private PrEmployeeDAO prEmployeeDao;

	public PrEmployeeTextDAO getPrEmployeeTextDAO() {
		return prEmployeeTextDAO;
	}

	public void setPrEmployeeTextDAO(PrEmployeeTextDAO prEmployeeTextDAO) {
		this.prEmployeeTextDAO = prEmployeeTextDAO;
	}

	public void insertPrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception {
		// delete old data before 'D'
		if (premployeetextvo.getFlagStatus().equals("D")) {
			this.prEmployeeTextDAO.deleteOldPrEmployeeText(premployeetextvo);
		}

		this.prEmployeeTextDAO.insertPrEmployeeText(premployeetextvo);
		// update new code seq & new pay status
		this.prEmployeeDao.updatePrEmpByPrEmpText(premployeetextvo);

	}

	public void updatePrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception {
		this.prEmployeeTextDAO.updatePrEmployeeText(premployeetextvo);
	}

	public void deletePrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception {
		this.prEmployeeTextDAO.deletePrEmployeeText(premployeetextvo);
	}
	
	public List deletePrEmployeeTextError(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
				// System.out.println("into updateconfirmIncome");

				try {
					List ls = this.prEmployeeTextDAO.deletePrEmployeeTextError(userId,
							ouCode, new Long(yearPr), new Long(periodPr));

					this.prEmployeeTextDAO.deletePrEmployeeTextError(ouCode,
							String.valueOf(yearPr), String.valueOf(periodPr), userId);

					System.out.println("pr_text_error");	
					return ls;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
	public void deletePrEmployeeTextError(String ouCode, String yearPr,
			String periodPr,String userId) throws Exception {
		this.prEmployeeTextDAO.deletePrEmployeeTextError(ouCode, yearPr, periodPr,userId);
	}
	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.prEmployeeTextDAO.isConfirmFlag(ouCode, year, period,
				userId);
	}

	public List updateConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.prEmployeeTextDAO.updateConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			System.out.println("after query");
			if (statusConfirm.equals("Y")) {
				this.prEmployeeTextDAO.updateRwModSalIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwModSal");

				this.prEmployeeTextDAO.updateRwOvertimeIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwOvertime");

				this.prEmployeeTextDAO.updateRwPremiumIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwPremium");

				this.prEmployeeTextDAO.updateRwHealthIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwHealth");

				this.prEmployeeTextDAO.updateRwDangerIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwDanger");

				this.prEmployeeTextDAO.updateRwIncDecOtherIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwIncDecOther Income");

				this.prEmployeeTextDAO.updateRwVinaiDeduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai");

				this.prEmployeeTextDAO.updateRwVinai2Deduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai2");

				this.prEmployeeTextDAO.updateRwIncDecOtherDeduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwIncDecOther Deduct");
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List deleteConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.prEmployeeTextDAO.deleteConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.prEmployeeTextDAO.deleteRwModSalIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("modsal");

			this.prEmployeeTextDAO.deleteRwOvertimeIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("overtime");

			this.prEmployeeTextDAO.deleteRwPremiumIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("premium");

			this.prEmployeeTextDAO.deleteRwHealthIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("health");

			this.prEmployeeTextDAO.deleteRwDangerIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("danger");

			this.prEmployeeTextDAO.deleteRwIncDecOtherIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("delete confirm income complete : " + ls.size());

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List updateConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		try {
			List ls = this.prEmployeeTextDAO.updateConfirmDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			// this.prEmployeeTextDAO.updateRwVinaiDeduct(ouCode,
			// String.valueOf(yearPr), String.valueOf(periodPr), userId);

			// System.out.println("updated RwVinai");

			// this.prEmployeeTextDAO.updateRwVinai2Deduct(ouCode,
			// String.valueOf(yearPr), String.valueOf(periodPr), userId);

			// System.out.println("updated RwVinai2");

			// this.prEmployeeTextDAO.updateRwIncDecOtherDeduct(ouCode,
			// String.valueOf(yearPr), String.valueOf(periodPr), userId);

			// System.out.println("updated RwIncDecOther Deduct");

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List deleteConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		try {
			List ls = this.prEmployeeTextDAO.deleteConfirmDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.prEmployeeTextDAO.deleteRwVinaiDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("vinai");

			this.prEmployeeTextDAO.deleteRwVinai2Deduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("vinai2");

			this.prEmployeeTextDAO.deleteRwIncDecOtherDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("delete confirm deduct complete : " + ls.size());

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Integer confirmData(String ouCode, long yearPr, long periodPr,
			String flagStatus, String userId) {
		return this.prEmployeeTextDAO.confirmData(ouCode, new Long(yearPr),
				new Long(periodPr), flagStatus, userId);
	}

	public void updateConfirmData(String ouCode, long yearPr, long periodPr,
			String userId) throws Exception {
		this.prEmployeeTextDAO.updateConfirmData(ouCode, new Long(yearPr),
				new Long(periodPr), userId);
	}

	
	public void deleteIncomeError(String ouCode, String yearPr,
			String periodPr,String userId) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			this.prEmployeeTextDAO.deleteIncomeError(ouCode, yearPr, periodPr,userId);
			/*List ls = this..prEmployeeTextDAO.deleteIncomeError(userId,
					ouCode, new Long(yearPr), new Long(periodPr));
*/
		/*	this.prEmployeeTextDAO.deleteRwModSalIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("modsal");

			this.prEmployeeTextDAO.deleteRwOvertimeIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("overtime");

			this.prEmployeeTextDAO.deleteRwPremiumIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("premium");

			this.prEmployeeTextDAO.deleteRwHealthIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("health");

			this.prEmployeeTextDAO.deleteRwDangerIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("danger");

			this.prEmployeeTextDAO.deleteRwIncDecOtherIncomeError(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);*/

			//System.out.println("delete income error complete : " + ls.size());

			//return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void deleteDeductError(String ouCode, String yearPr,
			String periodPr,String userId) throws Exception {
	     try{
		/*	List ls = this.prEmployeeTextDAO.deleteDeductError(userId,
					ouCode, new Long(yearPr), new Long(periodPr));*/

			this.prEmployeeTextDAO.deleteDeductError(ouCode, yearPr, periodPr,userId);
			//System.out.println("delete deduct error complete : " + ls.size());

			//return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findChangeOfMonth(String ouCode, String userId, int year,
			int period) {
		return this.prEmployeeTextDAO.findChangeOfMonth(ouCode, userId,
				new Integer(year), new Integer(period));
	}

	public List findMoveChangeOfMonth(String ouCode, String userId, int year,
			int period) {
		return this.prEmployeeTextDAO.findMoveChangeOfMonth(ouCode, userId,
				new Integer(year), new Integer(period));
	}

	public PrEmployeeDAO getPrEmployeeDao() {
		return prEmployeeDao;
	}

	public void setPrEmployeeDao(PrEmployeeDAO prEmployeeDao) {
		this.prEmployeeDao = prEmployeeDao;
	}
	
	
}
