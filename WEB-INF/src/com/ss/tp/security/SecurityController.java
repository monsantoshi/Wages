/*
 * Created on 22 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.security;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.SuUserOrgVO;
import com.ss.tp.model.SuUser;


import com.ss.tp.service.FeeWpayPrEmployeeService;
import com.ss.tp.service.FeeWpayPrWorkDayService;
import com.ss.tp.service.PeControlService;
import com.ss.tp.service.PeEmployeeScoreService;
import com.ss.tp.service.PeWgEmpScoreService;
import com.ss.tp.service.PnEmployeeService;
import com.ss.tp.service.PnEmployeeTextDetailService;
import com.ss.tp.service.PnEmployeeTextService;
import com.ss.tp.service.PnSalaryService;
import com.ss.tp.service.PnTextControlService;
import com.ss.tp.service.PrIncomeDeductService;
import com.ss.tp.service.PrPeriodLineService;
import com.ss.tp.service.RwConfirmDataService;
import com.ss.tp.service.RwDangerService;
import com.ss.tp.service.RwHealthService;
import com.ss.tp.service.RwIncDecOtherService;
import com.ss.tp.service.RwModSalService;
import com.ss.tp.service.RwOvertimeService;
import com.ss.tp.service.RwPremiumReportService;
import com.ss.tp.service.RwPremiumService;
import com.ss.tp.service.RwVinai2Service;
import com.ss.tp.service.RwVinaiService;
import com.ss.tp.service.SuUserOrganizationService;
import com.ss.tp.service.SuUserService;
import com.ss.tp.service.TaMonthEmpWorkService;
import com.ss.tp.service.TaWgMonthEmpWorkService;
import com.ss.tp.service.WePnEmpTextReportService;
import com.ss.tp.service.WePnEmployeeTextService;
import com.ss.tp.service.WePnPromoteInstService;
import com.ss.tp.service.WgConfirmDataService;
import com.ss.tp.service.WgDraftService;
import com.ss.tp.service.WgEmployeeService;
import com.ss.tp.service.WgIncomeDeductService;
import com.ss.tp.service.WgPeriodLineService;
import com.ss.tp.service.WgPrEmployeeService;
import com.ss.tp.service.WgPrIncDecOtherService;
import com.ss.tp.service.WgPrOvertimeService;
import com.ss.tp.service.SrPvfEmpService;
import com.ss.tp.service.FeeWpayPnEmployeeService;
import com.ss.tp.service.FeeWpayPrPeriodLineService;
import com.ss.tp.service.FeeWpayRwConfirmDataService;
import com.ss.tp.service.FeeWpayConfirmDataService;
import com.ss.tp.service.FeeWpayRwHealthService;
import com.ss.tp.service.FeeWpayRwIncDecOtherService;
import com.ss.tp.service.FeeWpayRwOvertimeService;
import com.ss.tp.service.FeeWpayRwPremiumReportService;
import com.ss.tp.service.FeeWpayRwPremiumService;
import com.ss.tp.service.FeeWpayRwVinai2Service;
import com.ss.tp.service.FeeWpayRwVinaiService;
import com.ss.tp.service.FeeWpayPrIncomeDeductService;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityController extends MultiActionController {

	private SuUserService getSuUserService() {
		return (SuUserService) this.getApplicationContext().getBean(
				"suUserService");
	}

	private SuUserOrganizationService getSuUserOrganizationService() {
		return (SuUserOrganizationService) this.getApplicationContext()
				.getBean("suUserOrganizationService");
	}

	private TaMonthEmpWorkService getTaMonthEmpWorkService() {
		return (TaMonthEmpWorkService) this.getApplicationContext().getBean(
				"taMonthEmpWorkService");
	}

	private TaWgMonthEmpWorkService getTaWgMonthEmpWorkService() {
		return (TaWgMonthEmpWorkService) this.getApplicationContext().getBean(
				"taWgMonthEmpWorkService");
	}

	private PnEmployeeService getPnEmployeeService() {
		return (PnEmployeeService) this.getApplicationContext().getBean(
				"pnEmployeeService");
	}

	private PrPeriodLineService getPrPeriodLineService() {
		return (PrPeriodLineService) this.getApplicationContext().getBean(
				"prPeriodLineService");
	}

	private PrIncomeDeductService getPrIncomeDeductService() {
		return (PrIncomeDeductService) this.getApplicationContext().getBean(
				"prIncomeDeductService");
	}

	private PeEmployeeScoreService getPeEmployeeScoreService() {
		return (PeEmployeeScoreService) this.getApplicationContext().getBean(
				"peEmployeeScoreService");
	}

	private PeWgEmpScoreService getPeWgEmpScoreService() {
		return (PeWgEmpScoreService) this.getApplicationContext().getBean(
				"peWgEmpScoreService");
	}

	private WgPeriodLineService getWgPeriodLineService() {
		return (WgPeriodLineService) this.getApplicationContext().getBean(
				"wgPeriodLineService");
	}

	private WgPrEmployeeService getWgPrEmployeeService() {
		return (WgPrEmployeeService) this.getApplicationContext().getBean(
				"wgPrEmployeeService");
	}

	private WgEmployeeService getWgEmployeeService() {
		return (WgEmployeeService) this.getApplicationContext().getBean(
				"wgEmployeeService");
	}

	private WgDraftService getWgDraftService() {
		return (WgDraftService) this.getApplicationContext().getBean(
				"wgDraftService");
	}

	// Payroll service////
	private RwOvertimeService getRwOvertimeService() {
		return (RwOvertimeService) this.getApplicationContext().getBean(
				"rwOvertimeService");
	}

	private RwPremiumService getRwPremiumService() {
		return (RwPremiumService) this.getApplicationContext().getBean(
				"rwPremiumService");
	}

	private RwConfirmDataService getRwConfirmDataService() {
		return (RwConfirmDataService) this.getApplicationContext().getBean(
				"rwConfirmDataService");
	}

	private RwHealthService getRwHealthService() {
		return (RwHealthService) this.getApplicationContext().getBean(
				"rwHealthService");
	}

	private RwDangerService getRwDangerService() {
		return (RwDangerService) this.getApplicationContext().getBean(
				"rwDangerService");
	}

	private RwIncDecOtherService getRwIncDecOtherService() {
		return (RwIncDecOtherService) this.getApplicationContext().getBean(
				"rwIncDecOtherService");
	}

	private RwModSalService getRwModSalService() {
		return (RwModSalService) this.getApplicationContext().getBean(
				"rwModSalService");
	}

	private RwVinaiService getRwVinaiService() {
		return (RwVinaiService) this.getApplicationContext().getBean(
				"rwVinaiService");
	}

	private RwVinai2Service getRwVinai2Service() {
		return (RwVinai2Service) this.getApplicationContext().getBean(
				"rwVinai2Service");
	}

	private WgIncomeDeductService getWgIncomeDeductService() {
		return (WgIncomeDeductService) this.getApplicationContext().getBean(
				"wgIncomeDeductService");
	}

	private WgConfirmDataService getWgConfirmDataService() {
		return (WgConfirmDataService) this.getApplicationContext().getBean(
				"wgConfirmDataService");
	}

	private PnSalaryService getPnSalaryService() {
		return (PnSalaryService) this.getApplicationContext().getBean(
				"pnSalaryService");
	}

	// Payroll service////
	private WePnPromoteInstService getWePnPromoteInstService() {
		return (WePnPromoteInstService) this.getApplicationContext().getBean(
				"wePnPromoteInstService");
	}

	

	
	private SrPvfEmpService getSrPvfEmpService() {
		return (SrPvfEmpService) this.getApplicationContext().getBean(
				"srPvfEmpService");
	}

	



	
	
	private FeeWpayPnEmployeeService getFeeWpayPnEmployeeService() {
		return (FeeWpayPnEmployeeService) this.getApplicationContext().getBean(
				"feeWpayPnEmployeeService");
	}

	private FeeWpayRwConfirmDataService getFeeWpayRwConfirmDataService() {
		return (FeeWpayRwConfirmDataService) this.getApplicationContext()
				.getBean("feeWpayRwConfirmDataService");
	}

	private FeeWpayPrPeriodLineService getFeeWpayPrPeriodLineService() {
		return (FeeWpayPrPeriodLineService) this.getApplicationContext()
				.getBean("feeWpayPrPeriodLineService");
	}

	private FeeWpayConfirmDataService getFeeWpayConfirmDataService() {
		return (FeeWpayConfirmDataService) this.getApplicationContext()
				.getBean("feeWpayConfirmDataService");
	}

	private FeeWpayRwHealthService getFeeWpayRwHealthService() {
		return (FeeWpayRwHealthService) this.getApplicationContext().getBean(
				"feeWpayRwHealthService");
	}

	private FeeWpayRwIncDecOtherService getFeeWpayRwIncDecOtherService() {
		return (FeeWpayRwIncDecOtherService) this.getApplicationContext()
				.getBean("feeWpayRwIncDecOtherService");
	}

	private FeeWpayRwVinaiService getFeeWpayRwVinaiService() {
		return (FeeWpayRwVinaiService) this.getApplicationContext().getBean(
				"feeWpayRwVinaiService");
	}

	private FeeWpayRwVinai2Service getFeeWpayRwVinai2Service() {
		return (FeeWpayRwVinai2Service) this.getApplicationContext().getBean(
				"feeWpayRwVinai2Service");
	}

	private FeeWpayPrIncomeDeductService getFeeWpayPrIncomeDeductService() {
		return (FeeWpayPrIncomeDeductService) this.getApplicationContext()
				.getBean("feeWpayPrIncomeDeductService");
	}

	private FeeWpayPrWorkDayService getFeeWpayPrWorkDayService() {
		return (FeeWpayPrWorkDayService) this.getApplicationContext().getBean(
				"feeWpayPrWorkDayService");
	}
	
	private FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean(
				"feeWpayPrEmployeeService");
	}

	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = new UserInfo();
		
	   
		SuUser suUser = new SuUser();
		
		this.bind(request, userInfo);
		// userInfo.setUserId("DEMO");
		System.out.println("############" + userInfo.getUserId() + "   "+ userInfo.getUserPassword() + "   " + userInfo.getOuCode());
       
		boolean isAddWorkDays = false;
		try {
			suUser = this.getSuUserService().checkPasswd(userInfo.getUserId(),
					userInfo.getUserPassword());
		
		
			if (suUser != null ) {

				if (userInfo.getOuCode() == null
						|| userInfo.getOuCode().equals("")) {
					List ls = this.getSuUserService().findByCriteria(suUser.getUserId());
					if (ls.size() > 0) {
						SuUserOrgVO so = (SuUserOrgVO) ls.get(0);

						userInfo.setOuCode(so.getOuCode());
						//Result result = new Result();
						
						
						
					} else {
						// ResourceBundleMessageSource ret =
						// (ResourceBundleMessageSource)
						// this.getWebApplicationContext().getBean("resultMessageSource");;
						ProcessResult processResult = new ProcessResult();
						// Result rs = new Result();
						// rs.setException(new Exception());
						// rs.setMessage("�������ö ��͡�Թ
						// �������к��� <br>
						// ��س���� User , Password
						// �����ա����");
					
						processResult.setType("ERROR");
						request.getSession().setAttribute("processResult",processResult);
					
						return new ModelAndView("login");
					}
				}
             
             	
				BeanUtils.copyProperties(userInfo, suUser);
				userInfo.setBag(new HashMap());
				request.getSession().setAttribute("UserLogin", userInfo);

				/*
				 * 
				 * List orgList = this.getSuUserOrganizationService().
				 * findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
				 * userInfo.getOuCode());
				 * request.getSession().setAttribute("OrganizationInSecurity",
				 * orgList);
				 * 
				 * List pnEmpList =
				 * this.getPnEmployeeService().findPnEmpBySecurity
				 * (userInfo.getUserId(), userInfo.getOuCode());
				 * request.getSession().setAttribute("PnEmployeeInSecurity",
				 * pnEmpList);
				 * System.out.println("############"+pnEmpList.size());
				 * 
				 * 
				 * //Service Payroll//// DefaultYearSectionVO defaltYear =
				 * this.getPrPeriodLineService
				 * ().getDefaultYearAndSection(userInfo.getOuCode(),
				 * userInfo.getUserId());
				 * request.getSession().setAttribute("DefaultYearAndSection"
				 * ,defaltYear);
				 * 
				 * List prEmpList =
				 * this.getPnEmployeeService().findPrEmpBySecurity
				 * (userInfo.getUserId(),
				 * userInfo.getOuCode(),defaltYear.getYear
				 * (),String.valueOf(defaltYear.getPeriod()));
				 * request.getSession().setAttribute("PrEmployeeInSecurity",
				 * prEmpList);
				 */

				// *********************//
				// request.getSession().setAttribute("org.osjava.taglib.breadcrumbs",
				// new BreadCrumbs());
				// BreadCrumbs crumbs =
				// (BreadCrumbs)request.getSession().getAttribute("org.osjava.taglib.breadcrumbs");
				// BreadCrumb crumb = new
				// BreadCrumb("security.htm?reqCode=doMenu", "Menu");
				// crumbs.addToTrail("Menu", crumb);
				return new ModelAndView("home");
			} else {
				// ResourceBundleMessageSource ret =
				// (ResourceBundleMessageSource)
				// this.getWebApplicationContext().getBean("resultMessageSource");;
				ProcessResult processResult = new ProcessResult();
				// Result rs = new Result();
				// rs.setException(new Exception());
				// rs.setMessage("�������ö ��͡�Թ �������к��� <br> ��س����
				// User , Password �����ա����");
				processResult.setType("ERROR");
				request.getSession().setAttribute("processResult",
						processResult);
				return new ModelAndView("login");
			}
		} catch (Exception e) {
			// ResourceBundleMessageSource ret =
			// (ResourceBundleMessageSource)
			// this.getWebApplicationContext().getBean("resultMessageSource");;
			ProcessResult processResult = new ProcessResult();
			// Result rs = new Result();
			// rs.setException(new Exception());
			// rs.setMessage("�������ö ��͡�Թ �������к��� <br> ��س����
			// User , Password �����ա����");
			processResult.setType("ERROR");
			request.getSession().setAttribute("processResult", processResult);
			return new ModelAndView("login");
		}
	}

	public ModelAndView doLogout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo uf = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (uf != null) {
			String sessionTree = "TreeItems" + uf.getUserId();
			request.getSession().removeAttribute(sessionTree);
		}
		request.getSession().removeAttribute("UserLogin");
		request.getSession().invalidate();
		return new ModelAndView("login");
	}

	public ModelAndView doMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("home");
	}

	public ModelAndView CTTAMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTTAMT001");
	}

	public ModelAndView CTTAQY002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTTAQY002");
	}

	public ModelAndView CTTAQY102(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}
		return new ModelAndView("CTTAQY102");
	}

	public ModelAndView CTTARP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTTARP001");
	}

	public ModelAndView CTPEMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}

			UserInfo uf = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			// String userId = uf.getUserId();
			String ouCode = uf.getOuCode();
			// Calendar now = Calendar.getInstance(Locale.US);
			// String year = ((now.get(Calendar.YEAR)+543)+"");
			DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
			defaltYear = this.getPeEmployeeScoreService().findMaxYearPeriod(
					ouCode);
			String year = defaltYear.getYear();
			// long evaTime = defaltYear.getPeriod();
			Long evaYear = new Long(year);
			// String check =
			// this.getPeControlService().CheckHaveData(ouCode,userId,evaYear.longValue(),evaTime,"");
			//
			// if (check == null) {
			// request.setAttribute("evaTimeDef", "1");
			// }
			// else{
			// request.setAttribute("evaTimeDef", "2");
			// }

			request.setAttribute("maxEvaYear", evaYear);
			request.setAttribute("maxEvaTime", defaltYear.getPeriod());
			// if(request.getSession().getAttribute("PnEmployeeInSecurity")==null){
			// List pnEmpList =
			// this.getPnEmployeeService().findPnEmpBySecurity(userInfo.getUserId(),
			// userInfo.getOuCode());
			// request.getSession().setAttribute("PnEmployeeInSecurity",
			// pnEmpList);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEMT001");
	}

	public ModelAndView CTPEIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo uf = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			String userId = uf.getUserId();
			String ouCode = uf.getOuCode();
			int evaYear = Integer.parseInt(request.getParameter("evaYearIns"));
			int evaTime = Integer.parseInt(request.getParameter("evaTimeIns"));
			String areaCode = request.getParameter("areaCodeIns");
			String secCode = request.getParameter("secCodeIns");
			String workCode = request.getParameter("workCodeIns");
			List list = this.getPeEmployeeScoreService()
					.findEmpForInsertUpdate(userId, ouCode, evaYear, evaTime,
							areaCode, secCode, workCode);
			request.getSession().setAttribute("empCodeList", list);
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCode(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity",
						orgList);
			}
			if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
				List pnEmpList = this.getPnEmployeeService()
						.findPnEmpBySecurity(userInfo.getUserId(),
								userInfo.getOuCode());
				request.getSession().setAttribute("PnEmployeeInSecurity",
						pnEmpList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEIN001");
	}

	public ModelAndView CTPEUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCode(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity",
						orgList);
			}
			if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
				List pnEmpList = this.getPnEmployeeService()
						.findPnEmpBySecurity(userInfo.getUserId(),
								userInfo.getOuCode());
				request.getSession().setAttribute("PnEmployeeInSecurity",
						pnEmpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEUP001");
	}

	public ModelAndView CTPEMT101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			String ouCode = userInfo.getOuCode();
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}
			DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
			defaltYear = this.getPeWgEmpScoreService()
					.findMaxYearPeriod(ouCode);
			String year = defaltYear.getYear();
			Long evaYear = new Long(year);
			request.setAttribute("maxEvaYear", evaYear);
			request.setAttribute("maxEvaTime", defaltYear.getPeriod());
			// if(request.getSession().getAttribute("WgEmployeeInSecurity")==null){
			// List wgEmpList =
			// this.getWgEmployeeService().findWgEmpBySecurity(userInfo.getUserId(),
			// userInfo.getOuCode());
			// request.getSession().setAttribute("WgEmployeeInSecurity",
			// wgEmpList);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEMT101");
	}

	public ModelAndView CTPEIN101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo uf = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			String userId = uf.getUserId();
			String ouCode = uf.getOuCode();
			int evaYear = Integer.parseInt(request.getParameter("evaYearIns"));
			int evaTime = Integer.parseInt(request.getParameter("evaTimeIns"));
			String areaCode = request.getParameter("areaCodeIns");
			String secCode = request.getParameter("secCodeIns");
			String workCode = request.getParameter("workCodeIns");
			List list = this.getPeWgEmpScoreService().findEmpForInsertUpdate(
					userId, ouCode, evaYear, evaTime, areaCode, secCode,
					workCode);
			request.getSession().setAttribute("empCodeList", list);
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCode(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity",
						orgList);
			}
			if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
				List wgEmpList = this.getWgEmployeeService()
						.findWgEmpBySecurity(userInfo.getUserId(),
								userInfo.getOuCode());
				request.getSession().setAttribute("WgEmployeeInSecurity",
						wgEmpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEIN101");
	}

	public ModelAndView CTPEUP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCode(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity",
						orgList);
			}
			if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
				List wgEmpList = this.getWgEmployeeService()
						.findWgEmpBySecurity(userInfo.getUserId(),
								userInfo.getOuCode());
				request.getSession().setAttribute("WgEmployeeInSecurity",
						wgEmpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEUP101");
	}

	public ModelAndView CTPEQY001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}

			UserInfo uf = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			// String userId = uf.getUserId();
			String ouCode = uf.getOuCode();
			// Calendar now = Calendar.getInstance(Locale.US);
			DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
			defaltYear = this.getPeEmployeeScoreService().findMaxYearPeriod(
					ouCode);
			String year = defaltYear.getYear();
			Long evaYear = new Long(year);
			request.setAttribute("maxEvaYear", evaYear);
			request.setAttribute("maxEvaTime", defaltYear.getPeriod());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEQY001");
	}

	public ModelAndView CTPEQY001_1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTPEQY001_1");
	}

	public ModelAndView CTPEQY101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCode(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity",
						orgList);
			}
			UserInfo uf = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			// String userId = uf.getUserId();
			String ouCode = uf.getOuCode();
			// Calendar now = Calendar.getInstance(Locale.US);
			DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
			defaltYear = this.getPeWgEmpScoreService()
					.findMaxYearPeriod(ouCode);
			String year = defaltYear.getYear();
			Long evaYear = new Long(year);
			request.setAttribute("maxEvaYear", evaYear);
			request.setAttribute("maxEvaTime", defaltYear.getPeriod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTPEQY101");
	}

	public ModelAndView CTPEQY101_1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTPEQY101_1");
	}

	public ModelAndView CTPEQY002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}

		return new ModelAndView("CTPEQY002");
	}

	public ModelAndView CTPEQY102(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}

		return new ModelAndView("CTPEQY102");
	}

	public ModelAndView CTPERP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTPERP001");
	}

	public ModelAndView CTPERP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}
		return new ModelAndView("CTPERP101");
	}

	public ModelAndView CTTAUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAUP001");
	}

	public ModelAndView CTTAIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		try {
			String ouCode = request.getParameter("hidOuCode");
			int month = request.getParameter("workMonth") != "" ? Integer
					.parseInt(request.getParameter("workMonth")) : 0;
			int year = request.getParameter("workYear") != "" ? Integer
					.parseInt(request.getParameter("workYear")) : 0;
			String userId = request.getParameter("userId");
			String areaCode = request.getParameter("areaCode") != null ? request
					.getParameter("areaCode") : "";
			String secCode = request.getParameter("secCode") != null ? request
					.getParameter("secCode") : "";
			String workCode = request.getParameter("workCode") != null ? request
					.getParameter("workCode") : "";
			List list = this.getTaMonthEmpWorkService().findListEmpIsNotMonth(
					ouCode, month, year, userId, areaCode, secCode, workCode);
			map.put("empCodeList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTTAIN001", map);
	}

	public ModelAndView CTTAPC001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAPC001");
	}

	public ModelAndView CTRWMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("into model : " + new Date());

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("OrganizationInSecurity")
		// ==null){
		// List orgList =
		// this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
		// userInfo.getOuCode());
		// request.getSession().setAttribute("OrganizationInSecurity", orgList);
		// }

		request.getSession().setAttribute("userOrganizationSevice",
				this.getSuUserOrganizationService());

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		// if(request.getSession().getAttribute("PrEmployeeInSecurityMT001")==null
		// ){
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// List prEmpList =
		// this.getPnEmployeeService().findPrEmpMT001(userInfo.getUserId(),
		// userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
		// request.getSession().setAttribute("PrEmployeeInSecurityMT001",
		// prEmpList);
		// }
		boolean confirm = this.getRwConfirmDataService().isConfirmMasterData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseMasterClose = this.getPrPeriodLineService()
				.isCloseMasterClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));
		// System.out.println("********************************** :::
		// "+confirm);
		if (confirm || isCloseMasterClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmMaserData",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT001");
	}

	public ModelAndView CTRWIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN001");
	}

	public ModelAndView CTRWDL001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWDL001");
	}

	public ModelAndView CTRWUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP001");
	}

	public ModelAndView CTRWMT002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // false;
		// if( confirmDanger || confirmHealth || confirmIncDecIncom
		// || confirmIncDecDeduct || confirmModSal || confirmOvertime
		// || confirmPremium || confirmVinai || confirmVinai2 )
		// confirm = true;
		request.getSession().setAttribute("isConfirm", new Boolean(confirm));
		return new ModelAndView("CTRWMT002");
	}

	public ModelAndView CTRWMT003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwOverTime") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));
		// System.out.println("********************************** :::
		// "+confirm);
		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwOverTime",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT003");
	}

	public ModelAndView CTRWUP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP003");
	}

	public ModelAndView CTRWIN003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN003");
	}

	public ModelAndView CTRWMT004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwPremiem") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwPremiumService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwPremiem",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT004");
	}

	public ModelAndView CTRWMT004N(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwPremiem",
				String.valueOf(confirm));
		// }

		// List prIncome =
		// this.getPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(),"1");
		// request.getSession().setAttribute("PrIncomeDeduct",prIncome);

		return new ModelAndView("CTRWMT004N");
	}

	public ModelAndView CTRWUP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP004");
	}

	public ModelAndView CTRWIN004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN004");
	}

	public ModelAndView CTRWMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwHealth") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwHealthService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwHealth",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT005");
	}

	public ModelAndView CTRWMT005N(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwHealth",
				String.valueOf(confirm));
		// }

		// List prIncome =
		// this.getPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(),"1");
		// request.getSession().setAttribute("PrIncomeDeduct",prIncome);

		return new ModelAndView("CTRWMT005N");
	}

	public ModelAndView CTRWUP005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP005");
	}

	public ModelAndView CTRWIN005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN005");
	}

	public ModelAndView CTRWMT006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwDanger") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwDangerService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwDanger",
				String.valueOf(confirm));
		// }

		return new ModelAndView("CTRWMT006");
	}

	public ModelAndView CTRWUP006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP006");
	}

	public ModelAndView CTRWIN006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN006");
	}

	public ModelAndView CTRWMT007(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwIncDecOther",
				String.valueOf(confirm));
		// }

		List prIncome = this.getPrIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "1");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);

		return new ModelAndView("CTRWMT007");
	}

	public ModelAndView CTRWMT008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwModSal") ==null){

		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwModSalService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwModSal",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT008");
	}

	public ModelAndView CTRWUP008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTRWUP008");
	}

	public ModelAndView CTRWIN008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTRWIN008");
	}

	public ModelAndView CTRWMT101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT101");
	}

	public ModelAndView CTRWUP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTRWUP101");
	}

	public ModelAndView CTRWIN101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTRWIN101");
	}

	public ModelAndView CTRWMT102(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai2",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTRWMT102");
	}

	public ModelAndView CTRWUP102(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWUP102");
	}

	public ModelAndView CTRWIN102(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTRWIN102");
	}

	public ModelAndView CTRWMT103(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOtherDeduct")
		// ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagDeduct(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;

		request.getSession().setAttribute("ConfirmRwIncDecOtherDeduct",
				String.valueOf(confirm));
		// }

		List prIncome = this.getPrIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "2");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);
		return new ModelAndView("CTRWMT103");
	}

	public ModelAndView CTRWMT104(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		// boolean confirmDanger =
		// this.getRwDangerService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmHealth =
		// this.getRwHealthService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmIncDecIncom =
		// this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmIncDecDeduct =
		// this.getRwIncDecOtherService().isConfirmFlagDeduct(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmModSal =
		// this.getRwModSalService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmOvertime =
		// this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmPremium =
		// this.getRwPremiumService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmVinai2 =
		// this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());
		// boolean confirmVinai =
		// this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),
		// defaltYear.getYear(), defaltYear.getPeriod(), userInfo.getUserId());

		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // false;
		// if( confirmDanger || confirmHealth || confirmIncDecIncom
		// || confirmIncDecDeduct || confirmModSal || confirmOvertime
		// || confirmPremium || confirmVinai || confirmVinai2 )
		// confirm = true;
		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		// System.out.println("104 confirm : " + confirm);

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getPrPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod());
		request.getSession().setAttribute("isCloseTranClose",
				new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTRWMT104");
	}

	public ModelAndView CTRWMT105(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		boolean canDeleteDanger = this.getRwDangerService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteHealth = this.getRwHealthService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteIncDecIncom = this.getRwIncDecOtherService()
				.canDeleteIncome(userInfo.getOuCode(), defaltYear.getYear(),
						defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteIncDecDeduct = this.getRwIncDecOtherService()
				.canDeleteDeduct(userInfo.getOuCode(), defaltYear.getYear(),
						defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteModSal = this.getRwModSalService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteOvertime = this.getRwOvertimeService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeletePremium = this.getRwPremiumService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteVinai2 = this.getRwVinai2Service().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());
		boolean canDeleteVinai = this.getRwVinaiService().canDelete(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod(), userInfo.getUserId());

		boolean confirm = false;
		if (canDeleteDanger && canDeleteHealth && canDeleteIncDecIncom
				&& canDeleteIncDecDeduct && canDeleteModSal
				&& canDeleteOvertime && canDeletePremium && canDeleteVinai
				&& canDeleteVinai2)
			confirm = true;
		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		// System.out.println("105 confirm : " + confirm);

		// System.out.println("Year : " + defaltYear.getYear());
		// System.out.println("Period : " + defaltYear.getPeriod());

		boolean canDelete = false;
		// if(request.getSession().getAttribute("canDeleteData")==null){
		canDelete = this.getPrPeriodLineService().canDeleteData(
				userInfo.getOuCode(), defaltYear.getYear(),
				defaltYear.getPeriod());
		request.getSession().setAttribute("canDeleteData",
				new Boolean(canDelete));
		// }

		// System.out.println("105 canDelete : " + canDelete);

		return new ModelAndView("CTRWMT105");
	}

	public ModelAndView CTRWMT106(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getRwConfirmDataService().isConfirmRwData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()));

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwIncDecOther",
				String.valueOf(confirm));
		// }

		List prIncome = this.getPrIncomeDeductService().findIncDecCode59(
				userInfo.getOuCode(), "2");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);

		return new ModelAndView("CTRWMT106");
	}

	public ModelAndView CTTAQY001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity31") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCodeLevel31(
							userInfo.getUserId(), userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity31",
					orgList);
		}
		return new ModelAndView("CTTAQY001");
	}

	public ModelAndView CTTAQY001_1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAQY001_1");
	}

	public ModelAndView CTRWQY001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
		}

		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getPnEmployeeService().findPrEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode(),
					defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		return new ModelAndView("CTRWQY001");
	}

	public ModelAndView CTRWQY001DT01(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}

		return new ModelAndView("CTRWQY001DT01");
	}

	public ModelAndView CTRWQY001DT02(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}

		return new ModelAndView("CTRWQY001DT02");
	}

	public ModelAndView CTRWRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP001");
	}

	public ModelAndView CTRWRP202(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP202");
	}

	public ModelAndView CTRWRP002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP002");
	}

	public ModelAndView CTPEPC001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTPEPC001");
	}

	public ModelAndView CTPEPC101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTPEPC101");
	}

	public ModelAndView CTRWRP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP003");
	}

	public ModelAndView CTRWRP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP004");
	}

	public ModelAndView CTRWRP005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP005");
	}

	public ModelAndView CTRWRP006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP006");
	}

	public ModelAndView CTRWRP008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP008");
	}

	public ModelAndView CTRWRP010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP010");
	}

	public ModelAndView CTRWRP016(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP016");
	}

	public ModelAndView CTRWRP017(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP017");
	}

	public ModelAndView CTPRBK001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTPRBK001");
	}

	public ModelAndView CTPRKTB001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTPRKTB001");
	}

	public ModelAndView CTPRRPTAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTPRRPTAX");
	}

	public ModelAndView CTWGMT004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity31") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCodeLevel31(
							userInfo.getUserId(), userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity31",
					orgList);
		}
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			/*
			 * start airsenal 02/11/2006 change call method for get the defalut
			 * year is decrease one month
			 */
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			// defaltYear =
			// this.getWgPeriodLineService().getDefaultYearAndSectionMinusOne(userInfo.getOuCode(),
			// userInfo.getUserId());
			/*
			 * end airsenal 02/11/2006 change call method for get the defalut
			 * year is decrease one month
			 */
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		}

		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmWgWorkDay",
				String.valueOf(confirm));
		return new ModelAndView("CTWGMT004");
	}

	public ModelAndView CTWGUP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		return new ModelAndView("CTWGUP004");
	}

	public ModelAndView CTWGMT007(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGMT007");
	}

	public ModelAndView CTWGMT008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGMT008");
	}

	public ModelAndView CTWGMT101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String year = null;
		if (request.getSession().getAttribute("wgDraftYear") == null) {
			year = this.getWgDraftService().findMaxYear("");
			request.getSession().setAttribute("wgDraftYear", year);
		}

		if (request.getSession().getAttribute("wgDraftAllYear") == null) {
			List years = this.getWgDraftService().findAllYear("");
			System.out.println("years size : " + years);
			request.getSession().setAttribute("wgDraftAllYear", years);
		}

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("WgDraftEmployeeInSecurity") == null) {
			// System.out.println("year : " + year);
			List prEmpList = this.getWgDraftService().findEmployee(year,
					userInfo.getOuCode(), userInfo.getUserId());
			// System.out.println("wgdraft size : " + prEmpList.size() );
			request.getSession().setAttribute("WgDraftEmployeeInSecurity",
					prEmpList);
		}

		return new ModelAndView("CTWGMT101");
	}

	public ModelAndView CTWGUP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String year = request.getParameter("yearEdit");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		boolean isConfirm = this.getWgDraftService().isConfirmFlag(
				userInfo.getOuCode(), year, userInfo.getUserId());
		request.getSession().setAttribute("isConfirm", new Boolean(isConfirm));

		return new ModelAndView("CTWGUP101");
	}

	public ModelAndView CTWGMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		defaltYear = this.getWgPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(), userInfo.getUserId());

	
		request.getSession().setAttribute("WgDefaultYearAndSection", defaltYear);

		String year = defaltYear.getYear();
		String period = defaltYear.getPeriod();
		int yearInt = Integer.parseInt(year);
		int periodInt = Integer.parseInt(period);

		if (periodInt == 1) {
			yearInt--;
			year = yearInt + "";
			period = this.getWgPeriodLineService().findMaxPeriodOfYear(userInfo.getOuCode(), yearInt + "");
		} else {
			periodInt--;
			period = periodInt + "";
		}

		if (request.getSession().getAttribute("WgPrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("WgDefaultYearAndSection");
			List prEmpList = this.getWgPrEmployeeService().findWgPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("WgPrEmployeeInSecurity",prEmpList);
		}
		if (year == null) {
			year = "";
			period = "";
		}
		Map map = new HashMap();
		map.put("yearPrevious", year);
		map.put("periodPrevious", period);
		return new ModelAndView("CTWGMT001", map);
	}

	public ModelAndView CTWGIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGIN001");
	}

	public ModelAndView CTWGUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		defaltYear = this.getWgPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession()
				.setAttribute("WgDefaultYearAndSection", defaltYear);
		String year = defaltYear.getYear();
		String period = defaltYear.getPeriod();
		int yearInt = Integer.parseInt(year);
		int periodInt = Integer.parseInt(period);

		if (periodInt == 1) {
			yearInt--;
			year = yearInt + "";
			period = this.getWgPeriodLineService().findMaxPeriodOfYear(
					userInfo.getOuCode(), yearInt + "");
		} else {
			periodInt--;
			period = periodInt + "";
		}

		Map map = new HashMap();
		map.put("yearPrevious", year);
		map.put("periodPrevious", period);

		return new ModelAndView("CTWGUP001", map);
	}

	public ModelAndView CTWGDL001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		defaltYear = this.getWgPeriodLineService().getDefaultYearAndSection(
				userInfo.getOuCode(), userInfo.getUserId());
		request.getSession()
				.setAttribute("WgDefaultYearAndSection", defaltYear);
		String year = defaltYear.getYear();
		String period = defaltYear.getPeriod();
		int yearInt = Integer.parseInt(year);
		int periodInt = Integer.parseInt(period);

		if (periodInt == 1) {
			yearInt--;
			year = yearInt + "";
			period = this.getWgPeriodLineService().findMaxPeriodOfYear(
					userInfo.getOuCode(), yearInt + "");
		} else {
			periodInt--;
			period = periodInt + "";
		}

		Map map = new HashMap();
		map.put("yearPrevious", year);
		map.put("periodPrevious", period);

		return new ModelAndView("CTWGDL001", map);
	}

	public ModelAndView CTWGRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		return new ModelAndView("CTWGRP001");
	}

	public ModelAndView CTWGRP002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}
		return new ModelAndView("CTWGRP002");
	}

	public ModelAndView CTWGRP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		return new ModelAndView("CTWGRP003");
	}

	public ModelAndView CTWGMT003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		if (request.getSession().getAttribute("WgPrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
			List prEmpList = this.getWgPrEmployeeService()
					.findWgPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("WgPrEmployeeInSecurity",
					prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwOverTime") ==null){
		// boolean confirm =
		// this.getRwConfirmDataService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmRwOverTime",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTWGMT003");
	}

	public ModelAndView CTGWMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		if (request.getSession().getAttribute("WgPrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
			List prEmpList = this.getWgPrEmployeeService()
					.findWgPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("WgPrEmployeeInSecurity",
					prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		// boolean confirm =
		// this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		// request.getSession().setAttribute("WgConfirmRwIncDecOther",String.valueOf(confirm));
		// }
		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmRwIncDecOther",
				String.valueOf(confirm));
		List prIncome = this.getPrIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "1");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);

		return new ModelAndView("CTGWMT005");
	}

	public ModelAndView CTWGUP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		// boolean confirm =
		// this.getWgPrOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		// request.getSession().setAttribute("WgConfirmRwOverTime",String.valueOf(confirm));
		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmRwOverTime",
				String.valueOf(confirm));
		return new ModelAndView("CTWGUP003");
	}

	public ModelAndView CTWGIN003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGIN003");
	}

	public ModelAndView CTWGMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		if (request.getSession().getAttribute("WgPrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
			List prEmpList = this.getWgPrEmployeeService()
					.findWgPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("WgPrEmployeeInSecurity",
					prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmRwIncDecOther",
				String.valueOf(confirm));
		// }

		List prIncome = this.getWgIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "1");
		request.getSession().setAttribute("WgPrIncomeDeduct", prIncome);

		return new ModelAndView("CTWGMT005");
	}

	public ModelAndView CTWGMT006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		if (request.getSession().getAttribute("WgPrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
			List prEmpList = this.getWgPrEmployeeService()
					.findWgPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("WgPrEmployeeInSecurity",
					prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getWgConfirmDataService().isConfirmWgData(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()), userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean tranClose = this.getWgPeriodLineService().isCloseTranClose(
				userInfo.getOuCode(), defaltYear.getYear(),
				String.valueOf(defaltYear.getPeriod()));
		if (confirm || tranClose) {
			confirm = true;
		} else {
			confirm = false;
		}
		request.getSession().setAttribute("WgConfirmRwIncDecOther",
				String.valueOf(confirm));
		// }

		List prIncome = this.getWgIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "2");
		request.getSession().setAttribute("WgPrIncomeDeduct", prIncome);

		return new ModelAndView("CTWGMT006");
	}

	public ModelAndView CTWGRP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
			System.out.println("defaultyear " + defaltYear);
			System.out.println(this.getWgPeriodLineService());
			System.out.println(userInfo);
			System.out.println(this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId()));

		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		return new ModelAndView("CTWGRP004");
	}

	public ModelAndView CTWGRP005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
			System.out.println("defaultyear " + defaltYear);
			System.out.println(this.getWgPeriodLineService());
			System.out.println(userInfo);
			System.out.println(this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId()));

		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}

		return new ModelAndView("CTWGRP005");
	}

	public ModelAndView CTWGMT002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGMT002");
	}

	public ModelAndView CTWGPC101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPC101");
	}

	public ModelAndView CTWGRP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGRP101");
	}

	public ModelAndView CTTAMT101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTTAMT101");
	}

	public ModelAndView CTTAIN101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		try {
			String ouCode = request.getParameter("hidOuCode");
			int month = request.getParameter("workMonth") != "" ? Integer
					.parseInt(request.getParameter("workMonth")) : 0;
			int year = request.getParameter("workYear") != "" ? Integer
					.parseInt(request.getParameter("workYear")) : 0;
			String userId = request.getParameter("userId");
			String areaCode = request.getParameter("areaCode") != null ? request
					.getParameter("areaCode") : "";
			String secCode = request.getParameter("secCode") != null ? request
					.getParameter("secCode") : "";
			String workCode = request.getParameter("workCode") != null ? request
					.getParameter("workCode") : "";
			List list = this.getTaWgMonthEmpWorkService()
					.findListEmpIsNotMonth(ouCode, month, year, userId,
							areaCode, secCode, workCode);
			map.put("empCodeList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTTAIN101", map);
	}

	public ModelAndView CTTAUP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAUP101");
	}

	public ModelAndView CTTAQY101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTTAQY101");
	}

	public ModelAndView CTTAQY101_1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAQY101_1");
	}

	public ModelAndView CTTAPC101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTTAPC101");
	}

	public ModelAndView CTTARP101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}
		return new ModelAndView("CTTARP101");
	}

	public ModelAndView CTRWRP201(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTRWRP201");
	}

	public ModelAndView CTWGRP006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("WgDefaultYearAndSection") == null) {
			defaltYear = this.getWgPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("WgDefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("WgDefaultYearAndSection");
		}
		return new ModelAndView("CTWGRP006");
	}

	public ModelAndView TRAINREPORT(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("TRAINREPORT");
	}

	public ModelAndView CTTTRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTTTRP001");
	}

	public ModelAndView CTPPRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTPPRP001");
	}

	public ModelAndView CTTTPM001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		if (request.getSession().getAttribute("VolumeInSecurity") == null) {
			List volumeList = this.getWePnPromoteInstService()
					.findVolumeBySecurity(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession().setAttribute("VolumeInSecurity", volumeList);
		}
		return new ModelAndView("CTTTPM001");
	}

	public ModelAndView CTRWPNT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		// defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		// if(request.getSession().getAttribute("ConfirmRwPremiem") ==null){

		// }
		return new ModelAndView("CTRWPNT001");
	}

	public ModelAndView CTRWPNTRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTRWPNTRP001");
	}

	public ModelAndView CTRWWGT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		// defaltYear = this.getPrPeriodLineService().getDefaultYearAndSection(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}
		// if(request.getSession().getAttribute("ConfirmRwPremiem") ==null){

		// }
		return new ModelAndView("CTRWWGT001");
	}

	public ModelAndView CTRWWGTRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
			List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("WgEmployeeInSecurity", wgEmpList);
		}
		return new ModelAndView("CTRWWGTRP001");
	}

	public ModelAndView CTPPPRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTPPPRP001");
	}

	public ModelAndView CTPPPRP002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null
				&& userInfo != null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}
		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null
				&& userInfo != null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);
		}
		return new ModelAndView("CTPPPRP002");
	}


	public ModelAndView CTSRRP107(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		List resultList = this.getSrPvfEmpService().findChgRateDate(
				userInfo.getOuCode());
		request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP107");
	}

	public ModelAndView CTSRRP019(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		List resultList = this.getSrPvfEmpService().findChgMasterDate(
				userInfo.getOuCode());
		request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP019");
	}

	public ModelAndView CTSRRP034(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		List resultList = this.getSrPvfEmpService().findMDate(
				userInfo.getOuCode());
		request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP034");
	}

	public ModelAndView CTSRRP207(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		List resultList = this.getSrPvfEmpService().findLastDate(
				userInfo.getOuCode());
		request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP207");
	}

	public ModelAndView CTSRRP029(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		List resultList = this.getSrPvfEmpService().findEmpStatusDate(
				userInfo.getOuCode());
		request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP029");
	}

	public ModelAndView CTSRRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		// String evaYear =
		// request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
		// String evaMonth =
		// request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
		// defaltYear = this.getApTableService().getDefaultYearAndSectionTR(
		// userInfo.getOuCode(), userInfo.getUserId());
		// request.getSession().setAttribute("DefaultYearAndSection",
		// defaltYear);

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
					userInfo.getUserId(), userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", pnEmpList);

		}

		// List resultList =
		// this.getSrPvfEmpService().findLastDate(userInfo.getOuCode());
		// request.getSession().setAttribute("ResultInSecurity", resultList);

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityTransfer(userInfo.getUserId(),userInfo.getOuCode(),defaltYear.getYear(),defaltYear.getMonth());
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTSRRP001");
	}

	public ModelAndView CTWPAYMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("into model : " + new Date());

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		request.getSession().setAttribute("userOrganizationSevice",
				this.getSuUserOrganizationService());

		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmMasterData(userInfo.getOuCode(),defaltYear.getYear(), defaltYear.getPeriod(),userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseMasterClose = this.getFeeWpayPrPeriodLineService().isCloseMasterClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		System.out.println("********************************** ::: " + confirm);
		if (confirm || isCloseMasterClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmMaserData",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYMT001");
	}

	public ModelAndView CTWPAYIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYIN001");
	}

	public ModelAndView CTWPAYDL001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYDL001");
	}

	public ModelAndView CTWPAYUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYUP001");
	}

	public ModelAndView CTWPAYMT002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmMasterData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // false;
		if (confirm)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmMaster",
				String.valueOf(confirm));
		request.getSession().setAttribute("isConfirm", new Boolean(confirm));		
		
		return new ModelAndView("CTWPAYMT002");
	}

	public ModelAndView CTWPAYMT003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwOverTime") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwOvertimeService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());
		// System.out.println("********************************** :::
		// "+confirm);
		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwOverTime",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYMT003");
	}

	public ModelAndView CTWPAYUP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYUP003");
	}

	public ModelAndView CTWPAYIN003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYIN003");
	}

	public ModelAndView CTWPAYMT004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
		
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null && userInfo != null) {
				List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCodeLevel31(userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
				
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		boolean isAddWorkDays = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isAddWorkDays = this.getFeeWpayRwConfirmDataService().isAddWorkDays(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(Integer.parseInt(defaltYear.getPeriod())+2), userInfo.getUserId());
		request.getSession().setAttribute("isAddWorkDays",new Boolean(isAddWorkDays));
			return new ModelAndView("CTWPAYMT004");
	}

	public ModelAndView CTWPAYIN004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		try {
			String ouCode = request.getParameter("hidOuCode");
			int month = request.getParameter("workMonth") != "" ? Integer
					.parseInt(request.getParameter("workMonth")) : 0;
			int year = request.getParameter("workYear") != "" ? Integer
					.parseInt(request.getParameter("workYear")) : 0;
			String userId = request.getParameter("userId");
			String areaCode = request.getParameter("areaCode") != null ? request
					.getParameter("areaCode") : "";
			String secCode = request.getParameter("secCode") != null ? request
					.getParameter("secCode") : "";
			String workCode = request.getParameter("workCode") != null ? request
					.getParameter("workCode") : "";
			List list = this.getFeeWpayPrWorkDayService()
					.findListEmpIsNotMonth(ouCode, month, year, userId,
							areaCode, secCode, workCode);
			map.put("empCodeList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTWPAYIN004", map);
	}

	public ModelAndView CTWPAYUP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYUP004");
	}

	public ModelAndView CTWPAYMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwPremiem",
				String.valueOf(confirm));
		// }

		// List prIncome =
		// this.getPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(),"1");
		// request.getSession().setAttribute("PrIncomeDeduct",prIncome);

		return new ModelAndView("CTWPAYMT005");
	}

	public ModelAndView CTWPAYMT006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwHealth",
				String.valueOf(confirm));
		// }

		// List prIncome =
		// this.getPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(),"1");
		// request.getSession().setAttribute("PrIncomeDeduct",prIncome);

		return new ModelAndView("CTWPAYMT006");
	}

	public ModelAndView CTWPAYMT008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOther") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagIncome(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwIncDecOther",
				String.valueOf(confirm));
		// }

		List prIncome = this.getFeeWpayPrIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "1");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);

		return new ModelAndView("CTWPAYMT008");
	}

	public ModelAndView CTWPAYMT009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYMT009");
	}

	public ModelAndView CTWPAYUP009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		return new ModelAndView("CTWPAYUP009");
	}

	public ModelAndView CTWPAYIN009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		//List salary = this.getPnSalaryService()
		//		.findSalary(userInfo.getOuCode());
		//request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTWPAYIN009");
	}

	public ModelAndView CTWPAYMT010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai2",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYMT010");
	}

	public ModelAndView CTWPAYUP010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYUP010");
	}

	public ModelAndView CTWPAYIN010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYIN010");
	}
	
	
	public ModelAndView CTWPAYINBRK001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		} else {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
		}

	
		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}
		
		if (request.getSession().getAttribute("BankIdInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List bankIdList = this.getFeeWpayPrEmployeeService()
					.findBankIdBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("BankIdInSecurity", bankIdList);
		}

		return new ModelAndView("CTWPAYINBRK001");
	}


	public ModelAndView CTWPAYMT011(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwIncDecOtherDeduct")
		// ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwIncDecOtherService().isConfirmFlagDeduct(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;

		request.getSession().setAttribute("ConfirmRwIncDecOtherDeduct",
				String.valueOf(confirm));
		// }

		List prIncome = this.getFeeWpayPrIncomeDeductService().findIncDecCode(
				userInfo.getOuCode(), "2");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);
		return new ModelAndView("CTWPAYMT011");
	}

	public ModelAndView CTWPAYMT012(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));
		
		/*boolean confirmMaster = false;
		confirmMaster = this.getFeeWpayRwConfirmDataService().isConfirmMasterData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),
				userInfo.getUserId()); // false;

			request.getSession().setAttribute("isConfirmMaster", new Boolean(confirmMaster));
		System.out.println("ConfirmMaster : " + confirmMaster);*/
		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTWPAYMT012");
	}

	public ModelAndView CTWPAYMT013(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmVinai2(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",
				new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTWPAYMT013");
	}
	
	
	public ModelAndView CTWPAYMT014(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService()
				.getDefaultYearAndSection(userInfo.getOuCode(),
						userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService()
				.isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService()
				.isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai3",
				String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYMT014");
	}

	public ModelAndView CTWPAYUP014(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYUP014");
	}

	public ModelAndView CTWPAYIN014(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWPAYIN014");
	}
	
	
	

	public ModelAndView CTWPAYQY001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}

		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService()
					.findPrEmpBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("PrEmployeeInSecurity", prEmpList);
		}
		
		if (request.getSession().getAttribute("BankIdInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List bankIdList = this.getFeeWpayPrEmployeeService()
					.findBankIdBySecurity(userInfo.getUserId(),
							userInfo.getOuCode(), defaltYear.getYear(),
							String.valueOf(defaltYear.getPeriod()));
			request.getSession()
					.setAttribute("BankIdInSecurity", bankIdList);
		}

		return new ModelAndView("CTWPAYQY001");
	}

	public ModelAndView CTWPAYQY001DT01(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}

		return new ModelAndView("CTWPAYQY001DT01");
	}

	public ModelAndView CTWPAYQY001DT02(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}

		return new ModelAndView("CTWPAYQY001DT02");
	}
	public ModelAndView CTWPAYRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP001");
	}
	public ModelAndView CTWPAYRP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP003");
	}
	public ModelAndView CTWPAYRP004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		
			if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
				List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity", orgList);
			}
			if (request.getSession().getAttribute("WgEmployeeInSecurity") == null) {
				List wgEmpList = this.getWgEmployeeService().findWgEmpBySecurity(userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("WgEmployeeInSecurity", wgEmpList);
			}
		}
		return new ModelAndView("CTWPAYRP004");
	}
	public ModelAndView CTWPAYRP004_1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP004_1");
	}
	public ModelAndView CTWPAYRP005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP005");
	}
	public ModelAndView CTWPAYRP006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP006");
	}
	public ModelAndView CTWPAYRP008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP008");
	}
	public ModelAndView CTWPAYRP009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP009");
	}
	public ModelAndView CTWPAYRP010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP010");
	}
	public ModelAndView CTWPAYRP011(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP011");
	}
	public ModelAndView CTWPAYRP014(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP014");
	}
	
	public ModelAndView CTWPAYRP201(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYRP201");
	}
	public ModelAndView CTWPAYAPMT011(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwIncDecOtherDeduct",String.valueOf(confirm));
		List prIncome = this.getFeeWpayPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(), "2");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);
		return new ModelAndView("CTWPAYAPMT011");
	}

	public ModelAndView CTWGEMPMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");


		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService()
					.findOrganizationByUserIdAndOuCode(userInfo.getUserId(),
							userInfo.getOuCode());
			request.getSession()
					.setAttribute("OrganizationInSecurity", orgList);
		}

		if (request.getSession().getAttribute("PnEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession()
					.getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findFeeWgEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession()
					.setAttribute("PnEmployeeInSecurity", prEmpList);
		}
		return new ModelAndView("CTWGEMPMT001");
	}

	public ModelAndView CTWGEMPIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		

		return new ModelAndView("CTWGEMPIN001");
		
		
		
		
	}

	public ModelAndView CTWGEMPUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

	
		return new ModelAndView("CTWGEMPUP001");
	}



	public ModelAndView CTWGEMPCONMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		return new ModelAndView("CTWGEMPCONMT001");
	}
	public ModelAndView CTWGCONIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

	
		return new ModelAndView("CTWGCONIN001");
		
		
		
		
	}
	public ModelAndView CTWGCONUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		
		return new ModelAndView("CTWGCONUP001");
	}
	

	public ModelAndView CTWGPAYIN001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYIN001");
	}

	public ModelAndView CTWGPAYDL001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYDL001");
	}

	public ModelAndView CTWGPAYUP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYUP001");
	}


	public ModelAndView CTWGPAYUP003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYUP003");
	}

	public ModelAndView CTWGPAYIN003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYIN003");
	}

	public ModelAndView CTWGPAYMT0041(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			if (request.getSession().getAttribute("OrganizationInSecurity31") == null
					&& userInfo != null) {
				List orgList = this.getSuUserOrganizationService()
						.findOrganizationByUserIdAndOuCodeLevel31(
								userInfo.getUserId(), userInfo.getOuCode());
				request.getSession().setAttribute("OrganizationInSecurity31",
						orgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CTWGPAYMT0041");
	}

	
	public ModelAndView CTWGPAYUP0041(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYUP0041");
	}

	
	
	public ModelAndView CTWGPAYUP009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTWGPAYUP009");
	}

	public ModelAndView CTWGPAYIN009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List salary = this.getPnSalaryService()
				.findSalary(userInfo.getOuCode());
		request.getSession().setAttribute("SalaryList", salary);
		return new ModelAndView("CTWGPAYIN009");
	}


	public ModelAndView CTWGPAYUP010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYUP010");
	}

	public ModelAndView CTWGPAYIN010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("CTWGPAYIN010");
	}

	

	
	



	
	public ModelAndView CTWPAYOR003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Calendar now = Calendar.getInstance(Locale.US);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		String evaYear = request.getParameter("hidWorkYear") == null ? String.valueOf(now.get(Calendar.YEAR) + 543) : request.getParameter("hidWorkYear");
		String evaMonth = request.getParameter("hidWorkMonth") == null ? String.valueOf(now.get(Calendar.MONTH) + 1) : request.getParameter("hidWorkMonth");
		boolean created = this.getFeeWpayConfirmDataService().isCreateWgPrData(userInfo.getOuCode(), evaYear, userInfo.getUserId()); // false;
		// if( confirmDanger || confirmHealth || confirmIncDecIncom
		// || confirmIncDecDeduct || confirmModSal || confirmOvertime
		// || confirmPremium || confirmVinai || confirmVinai2 )
		// confirm = true;
		//request.getSession().setAttribute("isConfirm", new Boolean(created));
		if (created)
			created = true;
		else
			created = false;
		request.getSession().setAttribute("CreatedPrPeriodData",String.valueOf(created));
	

		return new ModelAndView("CTWPAYOR003");
	}
	public ModelAndView CTWPAYOR004(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Calendar now = Calendar.getInstance(Locale.US);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		String evaYear = request.getParameter("hidWorkYear") == null ? String.valueOf(now.get(Calendar.YEAR) + 543) : request.getParameter("hidWorkYear");
		String evaMonth = request.getParameter("hidWorkMonth") == null ? String.valueOf(now.get(Calendar.MONTH) + 1) : request.getParameter("hidWorkMonth");
		boolean created = this.getFeeWpayConfirmDataService().isInsertWgPnToPr(userInfo.getOuCode(), evaYear,evaMonth, userInfo.getUserId()); // false;
		// if( confirmDanger || confirmHealth || confirmIncDecIncom
		// || confirmIncDecDeduct || confirmModSal || confirmOvertime
		// || confirmPremium || confirmVinai || confirmVinai2 )
		// confirm = true;
		//request.getSession().setAttribute("isConfirm", new Boolean(created));
		if (created)
			created = true;
		else
			created = false;
		request.getSession().setAttribute("CreatedWgPrEmployee",String.valueOf(created));
	
		return new ModelAndView("CTWPAYOR004");
	}


	public ModelAndView CTWPAYAPMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		
		
		if (request.getSession().getAttribute("UserInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List userList = this.getFeeWpayRwConfirmDataService().findByCriteriaUserByMaster(userInfo.getUserId(),defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("UserInSecurity", userList);
		}
	
		if (request.getSession().getAttribute("ZipCodeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List zipList = this.getFeeWpayRwConfirmDataService().findByCriteriaZipCodeByMaster(userInfo.getUserId(),defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("ZipCodeInSecurity", zipList);
		}
		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),
						userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmSuUser",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT001");
	}

	public ModelAndView CTWPAYAPMT001_N(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		

		
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
	
		if (request.getSession().getAttribute("UserInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List userList = this.getFeeWpayRwConfirmDataService().findByCriteriaUserByMaster(userInfo.getUserId(),defaltYear.getYear(),
					String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("UserInSecurity", userList);
		}
	

	
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmMasterData(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),	userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),
						String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmSuUser",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT001_N");
	}

	public ModelAndView CTWPAYAPMT003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwOvertime",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT003");
	}
	public ModelAndView CTWPAYAPMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwPremiem",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT005");
	}
	public ModelAndView CTWPAYAPMT006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwHealth",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT006");
	}
	public ModelAndView CTWPAYAPMT008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwIncDecOther",String.valueOf(confirm));
		// }
		List prIncome = this.getFeeWpayPrIncomeDeductService().findIncDecCode(userInfo.getOuCode(), "1");
		request.getSession().setAttribute("PrIncomeDeduct", prIncome);
		return new ModelAndView("CTWPAYAPMT008");
	}
	public ModelAndView CTWPAYAPMT009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinaiService().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT009");
	}
	public ModelAndView CTWPAYAPMT010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		if (request.getSession().getAttribute("OrganizationInSecurity") == null) {
			List orgList = this.getSuUserOrganizationService().findOrganizationByUserIdAndOuCode(userInfo.getUserId(),userInfo.getOuCode());
			request.getSession().setAttribute("OrganizationInSecurity", orgList);
		}

		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);
		// }else{
		// defaltYear = (DefaultYearSectionVO)
		// request.getSession().getAttribute("DefaultYearAndSection");
		// }

		if (request.getSession().getAttribute("PrEmployeeInSecurity") == null) {
			defaltYear = (DefaultYearSectionVO) request.getSession().getAttribute("DefaultYearAndSection");
			List prEmpList = this.getFeeWpayPnEmployeeService().findPrEmpBySecurity(userInfo.getUserId(),userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()));
			request.getSession().setAttribute("PrEmployeeInSecurity", prEmpList);
		}

		// if(request.getSession().getAttribute("ConfirmRwVinai2") ==null){
		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // this.getRwVinai2Service().isConfirmFlag(userInfo.getOuCode(),defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());
		boolean isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId());

		if (confirm || isCloseTranClose)
			confirm = true;
		else
			confirm = false;
		request.getSession().setAttribute("ConfirmRwVinai2",String.valueOf(confirm));
		// }
		return new ModelAndView("CTWPAYAPMT010");
	}
	public ModelAndView CTWPAYAPMT012(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		
		boolean confirmInCome = this.getFeeWpayRwConfirmDataService().isConfirmInComeOutCome(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirmInCome", new Boolean(confirmInCome));


		return new ModelAndView("CTWPAYAPMT012");
	}
	public ModelAndView CTWPAYAPMT013(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTWPAYAPMT013");
	}
	
	
	
	
	public ModelAndView CTWPAYAPRPMT003(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT003");
	}
	public ModelAndView CTWPAYAPRPMT005(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT005");
	}
	public ModelAndView CTWPAYAPRPMT006(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT006");
	}
	public ModelAndView CTWPAYAPRPMT008(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT008");
	}
	public ModelAndView CTWPAYAPRPMT009(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT009");
	}
	public ModelAndView CTWPAYAPRPMT010(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT010");
	}
	public ModelAndView CTWPAYAPRPMT011(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT011");
	}
	public ModelAndView CTWPAYAPRPMT201(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionOne(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRPMT201");
	}

	public ModelAndView CTWPAYPCMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmMasterData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }
		boolean confirmApprove = this.getFeeWpayRwConfirmDataService().isConfirmApprove(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirmApprove", new Boolean(confirmApprove));

		return new ModelAndView("CTWPAYPCMT001");
	}
	


	public ModelAndView CTWPAYGL001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionApprove(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmVinai2(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(Integer.parseInt(defaltYear.getPeriod())-2),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTWPAYGL001");
	}
	
	public ModelAndView CTWPAYGLRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionApprove(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(Integer.parseInt(defaltYear.getPeriod())-2),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);


		return new ModelAndView("CTWPAYGLRP001");
	}
	
	public ModelAndView CTWGPAYGLRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Calendar now = Calendar.getInstance(Locale.US);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		String evaYear = request.getParameter("hidWorkYear") == null ? String
				.valueOf(now.get(Calendar.YEAR) + 543) : request
				.getParameter("hidWorkYear");
		String evaMonth = request.getParameter("hidWorkMonth") == null ? String
				.valueOf(now.get(Calendar.MONTH) + 1) : request
				.getParameter("hidWorkMonth");

		// if (request.getSession().getAttribute("PnEmployeeInSecurity") ==
		// null) {
		// List pnEmpList = this.getPnEmployeeService().findPnEmpBySecurity(
		// userInfo.getUserId(), userInfo.getOuCode());
		// request.getSession().setAttribute("PnEmployeeInSecurity", pnEmpList);

		// }

		// if (request.getSession().getAttribute("VolumeInSecurity") == null) {
		// List volumeList =
		// this.getApTableService().findVolumeBySecurityApproveReport(userInfo.getUserId(),userInfo.getOuCode(),evaYear,evaMonth);
		// request.getSession().setAttribute("VolumeInSecurity", volumeList);
		// }

		return new ModelAndView("CTWGPAYGLRP001");
	}
	public ModelAndView CTWPAYKTBMT001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		// if(request.getSession().getAttribute("DefaultYearAndSection")==null){
		defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionTranClose(userInfo.getOuCode(),userInfo.getUserId());
		request.getSession().setAttribute("DefaultYearAndSection", defaltYear);

		boolean confirm = this.getFeeWpayRwConfirmDataService().isConfirmRwData(userInfo.getOuCode(), defaltYear.getYear(),String.valueOf(defaltYear.getPeriod()),userInfo.getUserId()); // false;

		request.getSession().setAttribute("isConfirm", new Boolean(confirm));

		boolean isCloseTranClose = false;
		// if(request.getSession().getAttribute("isCloseTranClose")==null){
		isCloseTranClose = this.getFeeWpayPrPeriodLineService().isCloseTranClose(userInfo.getOuCode(), defaltYear.getYear(),defaltYear.getPeriod(), userInfo.getUserId());
		request.getSession().setAttribute("isCloseTranClose",new Boolean(isCloseTranClose));
		// }

		// System.out.println("104 isCloseTranClose : " + isCloseTranClose);

		return new ModelAndView("CTWPAYKTBMT001");
	}
	
	public ModelAndView CTWGPAYKTBRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Calendar now = Calendar.getInstance(Locale.US);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSectionTranClose(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
	
		return new ModelAndView("CTWGPAYKTBRP001");
	}
	public ModelAndView CTWPAYAPRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP001");
	}
	
	public ModelAndView CTWPAYAPRP002(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService().getDefaultYearAndSection(userInfo.getOuCode(),userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP002");
	}
	public ModelAndView CTWPAYAPRP013(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP013");
	}
	public ModelAndView CTWPAYAPRP014(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP014");
	}
	public ModelAndView CTWPAYAPRP014_K(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP014_K");
	}
	public ModelAndView CTWPAYAPRP014_S(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP014_S");
	}
	
	public ModelAndView CTWPAYAPRP015(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP015");
	}
	
	public ModelAndView CTWPAYAPRP016(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP016");
	}
	
	public ModelAndView CTWPAYAPRP017(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYAPRP017");
	}

	
	public ModelAndView CTWPAYBRKRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYBRKRP001");
	}
	
	public ModelAndView CTWPAYBRSRP001(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getFeeWpayPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTWPAYBRSRP001");
	}
	
	
	public ModelAndView CTPRRP013(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DefaultYearSectionVO defaltYear = new DefaultYearSectionVO();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (request.getSession().getAttribute("DefaultYearAndSection") == null) {
			defaltYear = this.getPrPeriodLineService()
					.getDefaultYearAndSection(userInfo.getOuCode(),
							userInfo.getUserId());
			request.getSession().setAttribute("DefaultYearAndSection",
					defaltYear);
		}
		return new ModelAndView("CTPRRP013");
	}
	
}