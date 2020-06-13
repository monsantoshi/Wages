package com.ss.tp.service.impl;

import java.util.List;

//import com.ss.tp.dao.PrEmployeeDAO;
import com.ss.tp.dao.FeeWpayPrEmployeeDAO;
import com.ss.tp.dao.FeeWpayConfirmDataDAO;

//import com.ss.tp.dao.PrEmployeeTextDAO;
import com.ss.tp.dao.FeeWpayPrEmployeeTextDAO;
import com.ss.tp.dto.FeeWpayPrEmployeeTextVO;
//import com.ss.tp.dto.PrEmployeeTextVO;
//import com.ss.tp.service.PrEmployeeTextService;
import com.ss.tp.service.FeeWpayPrEmployeeTextService;

public class FeeWpayPrEmployeeTextServiceImpl implements FeeWpayPrEmployeeTextService {


	private FeeWpayPrEmployeeTextDAO feeWpayPrEmployeeTextDAO;
	private FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO;
    private FeeWpayConfirmDataDAO feeWpayConfirmDataDAO;
	
	public FeeWpayPrEmployeeTextDAO getFeeWpayPrEmployeeTextDAO() {
		return feeWpayPrEmployeeTextDAO;
	}

	public void setFeeWpayPrEmployeeTextDAO(FeeWpayPrEmployeeTextDAO feeWpayPrEmployeeTextDAO) {
		this.feeWpayPrEmployeeTextDAO = feeWpayPrEmployeeTextDAO;
	}
	
	
	public FeeWpayConfirmDataDAO getFeeWpayConfirmDataDAO() {
		return feeWpayConfirmDataDAO;
	}

	public void setFeeWpayConfirmDataDAO(FeeWpayConfirmDataDAO feeWpayConfirmDataDAO) {
		this.feeWpayConfirmDataDAO = feeWpayConfirmDataDAO;
	}
	

	
	
	public void insertPrEmployeeText(FeeWpayPrEmployeeTextVO premployeetextvo)
			throws Exception {
		// delete old data before 'D'
		if (premployeetextvo.getFlagStatus().equals("D")) {
			this.feeWpayPrEmployeeTextDAO.deleteOldPrEmployeeText(premployeetextvo);
			}

			this.feeWpayPrEmployeeTextDAO.insertPrEmployeeText(premployeetextvo);
			// update new code seq & new pay status
			this.feeWpayPrEmployeeDAO.updatePrEmpByPrEmpText(premployeetextvo);

	}

	

	public void updatePrEmployeeText(FeeWpayPrEmployeeTextVO feeWpayPrEmpTextVo)
			throws Exception {
		this.feeWpayPrEmployeeTextDAO.updatePrEmployeeText(feeWpayPrEmpTextVo);
	}

	public void deletePrEmployeeText(FeeWpayPrEmployeeTextVO feeWpayPrEmpTextVo)
			throws Exception {
		this.feeWpayPrEmployeeTextDAO.deletePrEmployeeText(feeWpayPrEmpTextVo);
	}
	
	public List deletePrEmployeeTextError(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
				// System.out.println("into updateconfirmIncome");

				try {
					List ls = this.feeWpayPrEmployeeTextDAO.deletePrEmployeeTextError(userId,
							ouCode, new Long(yearPr), new Long(periodPr));

					this.feeWpayPrEmployeeTextDAO.deletePrEmployeeTextError(ouCode,
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
		this.feeWpayPrEmployeeTextDAO.deletePrEmployeeTextError(ouCode, yearPr, periodPr,userId);
	}
	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayPrEmployeeTextDAO.isConfirmFlag(ouCode, year, period,
				userId);
	}

	public List updateConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.feeWpayPrEmployeeTextDAO.updateConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			System.out.println("after query");
			if (statusConfirm.equals("Y")) {
			

				this.feeWpayPrEmployeeTextDAO.updateRwOvertimeIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwOvertime");

				this.feeWpayPrEmployeeTextDAO.updateRwPremiumIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwPremium");

				this.feeWpayPrEmployeeTextDAO.updateRwHealthIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwHealth");

				this.feeWpayPrEmployeeTextDAO.updateRwIncDecOtherIncome(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwIncDecOther Income");

				this.feeWpayPrEmployeeTextDAO.updateRwVinaiDeduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai");
				
				this.feeWpayPrEmployeeTextDAO.updateRwVinai2Deduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai2");
				
				
				this.feeWpayPrEmployeeTextDAO.updateRwVinai3Deduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai3");

				this.feeWpayPrEmployeeTextDAO.updateRwIncDecOtherDeduct(ouCode,
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
			List ls = this.feeWpayPrEmployeeTextDAO.deleteConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

		

			this.feeWpayPrEmployeeTextDAO.deleteRwOvertimeIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("overtime");

			this.feeWpayPrEmployeeTextDAO.deleteRwPremiumIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("premium");

			this.feeWpayPrEmployeeTextDAO.deleteRwHealthIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("health");

		

			this.feeWpayPrEmployeeTextDAO.deleteRwIncDecOtherIncome(ouCode,
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
			List ls = this.feeWpayPrEmployeeTextDAO.updateConfirmDeduct(userId,
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
			List ls = this.feeWpayPrEmployeeTextDAO.deleteConfirmDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.feeWpayPrEmployeeTextDAO.deleteRwVinaiDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			System.out.println("vinai");

		

			this.feeWpayPrEmployeeTextDAO.deleteRwIncDecOtherDeduct(ouCode,
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
		return this.feeWpayPrEmployeeTextDAO.confirmData(ouCode, new Long(yearPr),
				new Long(periodPr), flagStatus, userId);
	}

	public void updateConfirmData(String ouCode, long yearPr, long periodPr,
			String userId) throws Exception {
		this.feeWpayPrEmployeeTextDAO.updateConfirmData(ouCode, new Long(yearPr),
				new Long(periodPr), userId);
	}

	
	public void deleteIncomeError(String ouCode, String yearPr,
			String periodPr,String userId) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			this.feeWpayPrEmployeeTextDAO.deleteIncomeError(ouCode, yearPr, periodPr,userId);
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

			this.feeWpayPrEmployeeTextDAO.deleteDeductError(ouCode, yearPr, periodPr,userId);
			//System.out.println("delete deduct error complete : " + ls.size());

			//return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findChangeOfMonth(String ouCode, String userId, int year,
			int period) {
		return this.feeWpayPrEmployeeTextDAO.findChangeOfMonth(ouCode, userId,
				new Integer(year), new Integer(period));
	}

	public List findMoveChangeOfMonth(String ouCode, String userId, int year,
			int period) {
		return this.feeWpayPrEmployeeTextDAO.findMoveChangeOfMonth(ouCode, userId,
				new Integer(year), new Integer(period));
	}

	public FeeWpayPrEmployeeDAO getFeeWpayPrEmployeeDAO() {
		return feeWpayPrEmployeeDAO;
	}

	public void setFeeWpayPrEmployeeDAO(FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO) {
		this.feeWpayPrEmployeeDAO = feeWpayPrEmployeeDAO;
	}
	
	public List updateConfirmVinai2(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.feeWpayPrEmployeeTextDAO.updateConfirmVinai2(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			System.out.println("after query");
			if (statusConfirm.equals("Y")) {
				this.feeWpayPrEmployeeTextDAO.updateRwVinai2Deduct(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai2");

				
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public List updateApproveIncome(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.feeWpayPrEmployeeTextDAO.updateApproveIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			System.out.println("after query");
			if (statusConfirm.equals("Y")) {
			

				this.feeWpayPrEmployeeTextDAO.updateRwOvertimeIncomeApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwOvertimeIncomeApprove");

				this.feeWpayPrEmployeeTextDAO.updateRwPremiumIncomeApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwPremiumIncomeApprove");

				this.feeWpayPrEmployeeTextDAO.updateRwHealthIncomeApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwHealth");

			

				this.feeWpayPrEmployeeTextDAO.updateRwIncDecOtherIncomeApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwIncDecOther Income");

				this.feeWpayPrEmployeeTextDAO.updateRwVinaiDeductApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai");
				
				this.feeWpayPrEmployeeTextDAO.updateRwVinai2DeductApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai2");

				this.feeWpayPrEmployeeTextDAO.updateRwVinai3DeductApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwVinai3");

			

				this.feeWpayPrEmployeeTextDAO.updateRwIncDecOtherDeductApprove(ouCode,
						String.valueOf(yearPr), String.valueOf(periodPr),
						userId);

				System.out.println("updated RwIncDecOther Deduct");
				
				//this.feeWpayConfirmDataDAO.confirmMaster(userId, ouCode, String.valueOf(yearPr), String.valueOf(periodPr));
				
				/*this.feeWpayConfirmDataDAO.transferTrans(userId, ouCode, String.valueOf(yearPr), String.valueOf(periodPr));*/
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public List updateApproveDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		try {
			List ls = this.feeWpayPrEmployeeTextDAO.updateApproveDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

		

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List submitIncomeTransClose(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.feeWpayPrEmployeeTextDAO.submitIncomeTransClose(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

		

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List submitDeductTransClose(String userId, String ouCode,
			long yearPr, long periodPr) throws Exception {
		// TODO Auto-generated method stub
		try {
			List ls = this.feeWpayPrEmployeeTextDAO.submitDeductTransClose(userId,
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
	
	public void updateTransferFlag(String ouCode, long yearPr, long monthPr,String userId) throws Exception {
		this.feeWpayPrEmployeeTextDAO.updateTransferFlag(ouCode, new Long(yearPr),new Long(monthPr), userId);
	}
	
	
	
}
