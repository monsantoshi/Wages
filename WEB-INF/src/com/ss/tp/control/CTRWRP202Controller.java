/*
 * Created on 31 ï¿½.ï¿½. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.PrEmployeeTextService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP202Controller extends MultiActionController {
	public PrEmployeeTextService getPrEmployeeTextService() {
		return (PrEmployeeTextService) this.getApplicationContext().getBean(
				"prEmployeeTextService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Ê´ï¿½ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Â¹ï¿½Å§ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ñ¡ï¿½Í§ï¿½Ù¡ï¿½ï¿½Ò§ï¿½ï¿½Ð¨ï¿½
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = "";
		String userId = "";
		int year = 0;
		int period = 0;
		String periodName = "";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTRWRP202.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTRWRP202.xls");
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		if (request.getParameter("period") != null) {

			period = (new Double(request.getParameter("period")).intValue());
		}
		if (request.getParameter("periodName") != null) {
			periodName = request.getParameter("periodName");
		}
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);

		WritableSheet sheet = ww.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setPointSize(9);

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);

		DecimalFormat dfInt = new DecimalFormat("###,##0");

		Alignment alg = Alignment.getAlignment(1);
		WritableCellFormat borderGroup = new WritableCellFormat();
		borderGroup.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderGroup.setAlignment(alg.LEFT);

		WritableCellFormat borderRight = new WritableCellFormat();
		borderRight.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderRight.setAlignment(alg.LEFT);

		WritableCellFormat borderData = new WritableCellFormat();
		borderData.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData.setAlignment(alg.LEFT);
		borderData.setFont(font);

		WritableCellFormat borderDataR = new WritableCellFormat();
		borderDataR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderDataR.setAlignment(alg.CENTRE);
		borderDataR.setFont(font);

		WritableCellFormat borderData2 = new WritableCellFormat();
		borderData2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData2.setAlignment(alg.RIGHT);
		borderData2.setFont(font);

		WritableCellFormat borderNumber = new WritableCellFormat();
		borderNumber.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNumber.setAlignment(alg.RIGHT);
		borderNumber.setFont(font);

		WritableCellFormat borderMoney = new WritableCellFormat();
		borderMoney.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderMoney.setAlignment(alg.RIGHT);
		borderMoney.setFont(font);

		WritableCellFormat borderNo = new WritableCellFormat();
		borderNo.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNo.setAlignment(alg.CENTRE);
		borderNo.setFont(font);

		WritableFont fontRemark = new WritableFont(WritableFont.ARIAL);
		fontRemark.setPointSize(9);
		WritableCellFormat remark = new WritableCellFormat();
		remark.setBorder(Border.NONE, BorderLineStyle.THIN);
		remark.setAlignment(alg.LEFT);
		remark.setFont(fontRemark);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(alg.LEFT);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(alg.RIGHT);
		borderNumber2.setFont(fontBold);

		CellFormat head = sheet.getWritableCell(0 + 1, 0).getCellFormat();

		CellFormat headLeft = sheet.getWritableCell(0, 2).getCellFormat();
		CellFormat headRight = sheet.getWritableCell(7, 2).getCellFormat();
		CellFormat group = sheet.getWritableCell(2 + 1, 7).getCellFormat();
		CellFormat borderLR = sheet.getWritableCell(5 + 1, 7).getCellFormat();
		CellFormat endLeft = sheet.getWritableCell(1, 6).getCellFormat();
		CellFormat textFt = sheet.getWritableCell(4 + 1, 7).getCellFormat();
		CellFormat number = sheet.getWritableCell(1 + 1, 7).getCellFormat();

		CellFormat numberCenter = sheet.getWritableCell(7 + 1, 7)
				.getCellFormat();

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		System.out.println("ouName : " + ouName);
		System.out.println("head : " + head);

		/* start title */
		sheet.addCell(new Label(1, 0, ouName, head));
		sheet.addCell(new Label(0, 2, "ï¿½ï¿½~ï¿½ï¿½ï¿½  " + userInfo.getUserName(),
				headLeft));
		sheet.addCell(new Label(
				7,
				2,
				"ï¿½ï¿½Ð¨Ó§Ç´  "
						+ periodName
						+ " ï¿½.ï¿½."
						+ year
						+ "                                                                                                                                            ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½~ï¿½ : "
						+ sdfPrint.format(gd.getTime()), headRight));
		/* end title */

		PrEmployeeTextVO prEmpVo = null;
		List listeChageMonth = this.getPrEmployeeTextService()
				.findMoveChangeOfMonth(ouCode, userId, year, period);

		int startRow = 6;
		int checkLV = 0;
		String tempCode = "";
		String tempSec = "";
		double sumCostChild = 0.0;
		double sumChildStudy = 0.0;
		double sumChildNoStudy = 0.0;
		double sumCostLife = 0.0;
		double sumDebtLife = 0.0;
		double sumDebtLoan = 0.0;
		double sumDonate = 0.0;
		double sumLtf = 0.0;
		double sumRmf = 0.0;
		double sumHealthFather = 0.0;
		double sumHandicappedDec = 0.0;
		String flagSum = "";
		if (listeChageMonth.size() > 0) {
			for (int k = 0; k < listeChageMonth.size(); k++) {
				prEmpVo = (PrEmployeeTextVO) listeChageMonth.get(k);
				checkLV = checkLV(prEmpVo.getAreaCode(), prEmpVo.getSecCode());
				if (checkLV == 3) {
					if (!tempCode.equals(prEmpVo.getDivCode())) {
						// if(!tempCode.equals(prEmpVo.getAreaCode())){
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						sheet.setRowView(startRow, 320);

						// set border to column 1
						sheet.addCell(new Blank(0, startRow, borderLR));

						// sheet.addCell(new Label(0+1, startRow,
						// prEmpVo.getAreaCode() + " " +
						// prEmpVo.getAreaDesc(),group));
						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getDivCode() + " " + prEmpVo.getDivDesc(),
								group));
						// sheet.addCell(new Label(0+1, startRow,
						// prEmpVo.getSecCode() + " " +
						// prEmpVo.getAreaDesc()+"
						// "+prEmpVo.getSecDesc(),group));
						for (int i = 1 + 1; i < 24 + 1; i++) {
							sheet.addCell(new Blank(i, startRow, borderLR));
						}
						startRow++;
						sheet.setRowView(startRow, 320);
						sheet.addCell(new Label(0, startRow, String
								.valueOf(k + 1), numberCenter));

						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getFlagStatus(), borderNo));
						sheet.addCell(new Label(1 + 1, startRow, prEmpVo
								.getEmpCode(), borderNo));
						sheet.addCell(new Label(2 + 1, startRow, prEmpVo
								.getPrefix()
								+ " "
								+ prEmpVo.getName()
								+ " "
								+ prEmpVo.getLastName(), borderData));
						sheet.addCell(new Label(3 + 1, startRow, prEmpVo
								.getTaxId(), numberCenter));
						sheet.addCell(new Label(4 + 1, startRow, prEmpVo
								.getMarriedStatus(), borderNo));
						sheet.addCell(new Label(5 + 1, startRow, prEmpVo
								.getPayStatus(), borderNo));
						sheet.addCell(new Label(6 + 1, startRow, prEmpVo
								.getBankId(), numberCenter));
						sheet.addCell(new Label(7 + 1, startRow, prEmpVo
								.getCostChild(), borderNumber));
						sheet.addCell(new Label(8 + 1, startRow, prEmpVo
								.getChildStudy(), borderNumber));
						sheet.addCell(new Label(9 + 1, startRow, prEmpVo
								.getChildNoStudy(), borderNumber));
						sheet.addCell(new Label(10 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getCostLife()),
								number));
						sheet.addCell(new Label(11 + 1, startRow, prEmpVo
								.getGundanFlag(), borderNo));
						sheet.addCell(new Label(12 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLife()),
								number));
						sheet.addCell(new Label(13 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLoan()),
								number));
						sheet.addCell(new Label(14 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDonate()),
								number));
						sheet.addCell(new Label(15 + 1, startRow, prEmpVo
								.getFlagFather(), borderNo));
						sheet.addCell(new Label(16 + 1, startRow, prEmpVo
								.getFlagMother(), borderNo));
						sheet.addCell(new Label(17 + 1, startRow, prEmpVo
								.getFlagFatherSpouse(), borderNo));
						sheet.addCell(new Label(18 + 1, startRow, prEmpVo
								.getFlagMotherSpouse(), borderNo));
						sheet.addCell(new Label(19 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getRmf()),
								number));
						sheet.addCell(new Label(20 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getLtf()),
								number));
						sheet.addCell(new Label(21 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHealthFather()), number));
						sheet.addCell(new Label(22 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHandicappedDec()), number));
						sheet.addCell(new Label(23 + 1, startRow, prEmpVo
								.getFlagPr(), borderDataR));
						startRow++;
						// if(!prEmpVo.getCostChild().equals("")){
						// sumCostChild+=Double.parseDouble(prEmpVo.getCostChild());
						//
						// }
						// if(!prEmpVo.getChildStudy().equals("")){
						// sumChildStudy+=Double.parseDouble(prEmpVo.getChildStudy());
						//
						// }
						// if(!prEmpVo.getChildNoStudy().equals("")){
						// sumChildNoStudy+=Double.parseDouble(prEmpVo.getChildNoStudy());
						//
						// }
						// if(!prEmpVo.getCostLife().equals("")){
						// sumCostLife+=Double.parseDouble(prEmpVo.getCostLife());
						//
						// }
						// if(!prEmpVo.getDebtLife().equals("")){
						// sumDebtLife+=Double.parseDouble(prEmpVo.getDebtLife());
						//
						// }
						// if(!prEmpVo.getDebtLoan().equals("")){
						// sumDebtLoan+=Double.parseDouble(prEmpVo.getDebtLoan());
						//
						// }
						//
						// if(!prEmpVo.getDonate().equals("")){
						// sumDonate+=Double.parseDouble(prEmpVo.getDonate());
						//
						// }
						// if(!prEmpVo.getLtf().equals("")){
						// sumLtf+=Double.parseDouble(prEmpVo.getLtf());
						// }
						// if(!prEmpVo.getRmf().equals("")){
						// sumRmf+=Double.parseDouble(prEmpVo.getRmf());
						//
						// }
						flagSum = "sum";
						// if(listeChageMonth.size() ==(k+1)){
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						// }
					} else {
						sheet.setRowView(startRow, 320);
						sheet.addCell(new Label(0, startRow, String
								.valueOf(k + 1), numberCenter));

						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getFlagStatus(), borderNo));
						sheet.addCell(new Label(1 + 1, startRow, prEmpVo
								.getEmpCode(), borderNo));
						sheet.addCell(new Label(2 + 1, startRow, prEmpVo
								.getPrefix()
								+ " "
								+ prEmpVo.getName()
								+ " "
								+ prEmpVo.getLastName(), borderData));
						sheet.addCell(new Label(3 + 1, startRow, prEmpVo
								.getTaxId(), numberCenter));
						sheet.addCell(new Label(4 + 1, startRow, prEmpVo
								.getMarriedStatus(), borderNo));
						sheet.addCell(new Label(5 + 1, startRow, prEmpVo
								.getPayStatus(), borderNo));
						sheet.addCell(new Label(6 + 1, startRow, prEmpVo
								.getBankId(), numberCenter));
						sheet.addCell(new Label(7 + 1, startRow, prEmpVo
								.getCostChild(), borderNumber));
						sheet.addCell(new Label(8 + 1, startRow, prEmpVo
								.getChildStudy(), borderNumber));
						sheet.addCell(new Label(9 + 1, startRow, prEmpVo
								.getChildNoStudy(), borderNumber));
						sheet.addCell(new Label(10 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getCostLife()),
								number));
						sheet.addCell(new Label(11 + 1, startRow, prEmpVo
								.getGundanFlag(), borderNo));
						sheet.addCell(new Label(12 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLife()),
								number));
						sheet.addCell(new Label(13 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLoan()),
								number));
						sheet.addCell(new Label(14 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDonate()),
								number));
						sheet.addCell(new Label(15 + 1, startRow, prEmpVo
								.getFlagFather(), borderNo));
						sheet.addCell(new Label(16 + 1, startRow, prEmpVo
								.getFlagMother(), borderNo));
						sheet.addCell(new Label(17 + 1, startRow, prEmpVo
								.getFlagFatherSpouse(), borderNo));
						sheet.addCell(new Label(18 + 1, startRow, prEmpVo
								.getFlagMotherSpouse(), borderNo));
						sheet.addCell(new Label(19 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getRmf()),
								number));
						sheet.addCell(new Label(20 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getLtf()),
								number));
						sheet.addCell(new Label(21 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHealthFather()), number));
						sheet.addCell(new Label(22 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHandicappedDec()), number));
						sheet.addCell(new Label(23 + 1, startRow, prEmpVo
								.getFlagPr(), borderDataR));
						startRow++;
						if (!prEmpVo.getCostChild().equals("")) {
							sumCostChild += Double.parseDouble(prEmpVo
									.getCostChild());

						}
						if (!prEmpVo.getChildStudy().equals("")) {
							sumChildStudy += Double.parseDouble(prEmpVo
									.getChildStudy());

						}
						if (!prEmpVo.getChildNoStudy().equals("")) {
							sumChildNoStudy += Double.parseDouble(prEmpVo
									.getChildNoStudy());

						}
						if (!prEmpVo.getCostLife().equals("")) {
							sumCostLife += Double.parseDouble(prEmpVo
									.getCostLife());

						}
						if (!prEmpVo.getDebtLife().equals("")) {
							sumDebtLife += Double.parseDouble(prEmpVo
									.getDebtLife());

						}
						if (!prEmpVo.getDebtLoan().equals("")) {
							sumDebtLoan += Double.parseDouble(prEmpVo
									.getDebtLoan());

						}

						if (!prEmpVo.getDonate().equals("")) {
							sumDonate += Double
									.parseDouble(prEmpVo.getDonate());

						}

						if (!prEmpVo.getHealthFather().equals("")) {
							sumHealthFather += Double.parseDouble(prEmpVo
									.getHealthFather());

						}

						if (!prEmpVo.getHandicappedDec().equals("")) {
							sumHandicappedDec += Double.parseDouble(prEmpVo
									.getHandicappedDec());

						}

						if (!prEmpVo.getLtf().equals("")) {
							sumLtf += Double.parseDouble(prEmpVo.getLtf());

						}
						if (!prEmpVo.getRmf().equals("")) {
							sumRmf += Double.parseDouble(prEmpVo.getRmf());

						}
						flagSum = "sum";
						// if(listeChageMonth.size() ==(k+1)){
						//
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						// }
					}
					// tempCode=prEmpVo.getAreaCode();
					tempCode = prEmpVo.getDivCode();

				} else if (checkLV == 4) {
					if (!tempSec.equals(prEmpVo.getSecCode())) {
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						sheet.setRowView(startRow, 320);

						// set border to column 1
						sheet.addCell(new Blank(0, startRow, borderLR));

						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getSecCode()
								+ " "
								+ prEmpVo.getAreaDesc()
								+ " " + prEmpVo.getSecDesc(), group));
						for (int i = 1 + 1; i < 24 + 1; i++) {
							sheet.addCell(new Blank(i, startRow, borderLR));
						}
						startRow++;
						sheet.setRowView(startRow, 320);
						sheet.addCell(new Label(0, startRow, String
								.valueOf(k + 1), numberCenter));

						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getFlagStatus(), borderNo));
						sheet.addCell(new Label(1 + 1, startRow, prEmpVo
								.getEmpCode(), borderNo));
						sheet.addCell(new Label(2 + 1, startRow, prEmpVo
								.getPrefix()
								+ " "
								+ prEmpVo.getName()
								+ " "
								+ prEmpVo.getLastName(), borderData));
						sheet.addCell(new Label(3 + 1, startRow, prEmpVo
								.getTaxId(), numberCenter));
						sheet.addCell(new Label(4 + 1, startRow, prEmpVo
								.getMarriedStatus(), borderNo));
						sheet.addCell(new Label(5 + 1, startRow, prEmpVo
								.getPayStatus(), borderNo));
						sheet.addCell(new Label(6 + 1, startRow, prEmpVo
								.getBankId(), numberCenter));
						sheet.addCell(new Label(7 + 1, startRow, prEmpVo
								.getCostChild(), borderNumber));
						sheet.addCell(new Label(8 + 1, startRow, prEmpVo
								.getChildStudy(), borderNumber));
						sheet.addCell(new Label(9 + 1, startRow, prEmpVo
								.getChildNoStudy(), borderNumber));
						sheet.addCell(new Label(10 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getCostLife()),
								number));
						sheet.addCell(new Label(11 + 1, startRow, prEmpVo
								.getGundanFlag(), borderNo));
						sheet.addCell(new Label(12 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLife()),
								number));
						sheet.addCell(new Label(13 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLoan()),
								number));
						sheet.addCell(new Label(14 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDonate()),
								number));
						sheet.addCell(new Label(15 + 1, startRow, prEmpVo
								.getFlagFather(), borderNo));
						sheet.addCell(new Label(16 + 1, startRow, prEmpVo
								.getFlagMother(), borderNo));
						sheet.addCell(new Label(17 + 1, startRow, prEmpVo
								.getFlagFatherSpouse(), borderNo));
						sheet.addCell(new Label(18 + 1, startRow, prEmpVo
								.getFlagMotherSpouse(), borderNo));
						sheet.addCell(new Label(19 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getRmf()),
								number));
						sheet.addCell(new Label(20 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getLtf()),
								number));
						sheet.addCell(new Label(21 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHealthFather()), number));
						sheet.addCell(new Label(22 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHandicappedDec()), number));
						sheet.addCell(new Label(23 + 1, startRow, prEmpVo
								.getFlagPr(), borderDataR));
						startRow++;
						if (!prEmpVo.getCostChild().equals("")) {
							sumCostChild += Double.parseDouble(prEmpVo
									.getCostChild());

						}
						if (!prEmpVo.getChildStudy().equals("")) {
							sumChildStudy += Double.parseDouble(prEmpVo
									.getChildStudy());

						}
						if (!prEmpVo.getChildNoStudy().equals("")) {
							sumChildNoStudy += Double.parseDouble(prEmpVo
									.getChildNoStudy());

						}
						if (!prEmpVo.getCostLife().equals("")) {
							sumCostLife += Double.parseDouble(prEmpVo
									.getCostLife());

						}
						if (!prEmpVo.getDebtLife().equals("")) {
							sumDebtLife += Double.parseDouble(prEmpVo
									.getDebtLife());

						}
						if (!prEmpVo.getDebtLoan().equals("")) {
							sumDebtLoan += Double.parseDouble(prEmpVo
									.getDebtLoan());

						}

						if (!prEmpVo.getDonate().equals("")) {
							sumDonate += Double
									.parseDouble(prEmpVo.getDonate());

						}
						if (!prEmpVo.getHealthFather().equals("")) {
							sumHealthFather += Double.parseDouble(prEmpVo
									.getHealthFather());

						}

						if (!prEmpVo.getHandicappedDec().equals("")) {
							sumHandicappedDec += Double.parseDouble(prEmpVo
									.getHandicappedDec());

						}

						if (!prEmpVo.getLtf().equals("")) {
							sumLtf += Double.parseDouble(prEmpVo.getLtf());

						}
						if (!prEmpVo.getRmf().equals("")) {
							sumRmf += Double.parseDouble(prEmpVo.getRmf());

						}
						flagSum = "sum";
						// if(listeChageMonth.size() ==(k+1)){
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						// }
					} else {
						sheet.setRowView(startRow, 320);
						sheet.addCell(new Label(0, startRow, String
								.valueOf(k + 1), numberCenter));

						sheet.addCell(new Label(0 + 1, startRow, prEmpVo
								.getFlagStatus(), borderNo));
						sheet.addCell(new Label(1 + 1, startRow, prEmpVo
								.getEmpCode(), borderNo));
						sheet.addCell(new Label(2 + 1, startRow, prEmpVo
								.getPrefix()
								+ " "
								+ prEmpVo.getName()
								+ " "
								+ prEmpVo.getLastName(), borderData));
						sheet.addCell(new Label(3 + 1, startRow, prEmpVo
								.getTaxId(), numberCenter));
						sheet.addCell(new Label(4 + 1, startRow, prEmpVo
								.getMarriedStatus(), borderNo));
						sheet.addCell(new Label(5 + 1, startRow, prEmpVo
								.getPayStatus(), borderNo));
						sheet.addCell(new Label(6 + 1, startRow, prEmpVo
								.getBankId(), numberCenter));
						sheet.addCell(new Label(7 + 1, startRow, prEmpVo
								.getCostChild(), borderNumber));
						sheet.addCell(new Label(8 + 1, startRow, prEmpVo
								.getChildStudy(), borderNumber));
						sheet.addCell(new Label(9 + 1, startRow, prEmpVo
								.getChildNoStudy(), borderNumber));
						sheet.addCell(new Label(10 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getCostLife()),
								number));
						sheet.addCell(new Label(11 + 1, startRow, prEmpVo
								.getGundanFlag(), borderNo));
						sheet.addCell(new Label(12 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLife()),
								number));
						sheet.addCell(new Label(13 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDebtLoan()),
								number));
						sheet.addCell(new Label(14 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getDonate()),
								number));
						sheet.addCell(new Label(15 + 1, startRow, prEmpVo
								.getFlagFather(), borderNo));
						sheet.addCell(new Label(16 + 1, startRow, prEmpVo
								.getFlagMother(), borderNo));
						sheet.addCell(new Label(17 + 1, startRow, prEmpVo
								.getFlagFatherSpouse(), borderNo));
						sheet.addCell(new Label(18 + 1, startRow, prEmpVo
								.getFlagMotherSpouse(), borderNo));
						sheet.addCell(new Label(19 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getRmf()),
								number));
						sheet.addCell(new Label(20 + 1, startRow,
								convertFormatCurrentcy(prEmpVo.getLtf()),
								number));
						sheet.addCell(new Label(21 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHealthFather()), number));
						sheet.addCell(new Label(22 + 1, startRow,
								convertFormatCurrentcy(prEmpVo
										.getHandicappedDec()), number));
						sheet.addCell(new Label(23 + 1, startRow, prEmpVo
								.getFlagPr(), borderDataR));
						startRow++;
						if (!prEmpVo.getCostChild().equals("")) {
							sumCostChild += Double.parseDouble(prEmpVo
									.getCostChild());

						}
						if (!prEmpVo.getChildStudy().equals("")) {
							sumChildStudy += Double.parseDouble(prEmpVo
									.getChildStudy());

						}
						if (!prEmpVo.getChildNoStudy().equals("")) {
							sumChildNoStudy += Double.parseDouble(prEmpVo
									.getChildNoStudy());

						}
						if (!prEmpVo.getCostLife().equals("")) {
							sumCostLife += Double.parseDouble(prEmpVo
									.getCostLife());

						}
						if (!prEmpVo.getDebtLife().equals("")) {
							sumDebtLife += Double.parseDouble(prEmpVo
									.getDebtLife());

						}
						if (!prEmpVo.getDebtLoan().equals("")) {
							sumDebtLoan += Double.parseDouble(prEmpVo
									.getDebtLoan());

						}

						if (!prEmpVo.getDonate().equals("")) {
							sumDonate += Double
									.parseDouble(prEmpVo.getDonate());

						}

						if (!prEmpVo.getHealthFather().equals("")) {
							sumHealthFather += Double.parseDouble(prEmpVo
									.getHealthFather());

						}

						if (!prEmpVo.getHandicappedDec().equals("")) {
							sumHandicappedDec += Double.parseDouble(prEmpVo
									.getHandicappedDec());

						}

						if (!prEmpVo.getLtf().equals("")) {
							sumLtf += Double.parseDouble(prEmpVo.getLtf());

						}
						if (!prEmpVo.getRmf().equals("")) {
							sumRmf += Double.parseDouble(prEmpVo.getRmf());

						}
						flagSum = "sum";
						// if(listeChageMonth.size() ==(k+1)){
						// if(!flagSum.equals("")){
						// sheet.mergeCells(0,startRow , 6,startRow);
						// sheet.addCell(new Label(0, startRow, "ï¿½ï¿½ï¿½",
						// borderNumber2));
						// sheet.addCell(new Label(7, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostChild)),
						// borderNumber2));
						// sheet.addCell(new Label(8, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(9, startRow,
						// convertFormatCurrentcy(String.valueOf(sumChildNoStudy)),
						// borderNumber2));
						// sheet.addCell(new Label(10, startRow,
						// convertFormatCurrentcy(String.valueOf(sumCostLife)),
						// borderNumber2));
						// sheet.addCell(new Label(11, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(12, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLife)),
						// borderNumber2));
						// sheet.addCell(new Label(13, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDebtLoan)),
						// borderNumber2));
						// sheet.addCell(new Label(14, startRow,
						// convertFormatCurrentcy(String.valueOf(sumDonate)),
						// borderNumber2));
						// sheet.mergeCells(15,startRow , 18,startRow);
						// sheet.addCell(new Label(15, startRow, "",
						// borderNumber2));
						// sheet.addCell(new Label(19, startRow,
						// convertFormatCurrentcy(String.valueOf(sumLtf)),
						// borderNumber2));
						// sheet.addCell(new Label(20, startRow,
						// convertFormatCurrentcy(String.valueOf(sumRmf)),
						// borderNumber2));
						//
						// sumCostChild = 0.0;
						// sumChildStudy=0.0;
						// sumChildNoStudy=0.;
						// sumCostLife=0.0;
						// sumDebtLife=0.0;
						// sumDebtLoan=0.0;
						// sumDonate=0.0;
						// sumLtf=0.0;
						// sumRmf=0.0;
						// flagSum="";
						// startRow++;
						// }
						// }
					}
					tempSec = prEmpVo.getSecCode();

				} else {
					sheet.setRowView(startRow, 320);

					// set border to column 1
					sheet.addCell(new Blank(0, startRow, borderLR));

					// sheet.addCell(new Label(0+1, startRow,
					// prEmpVo.getDivCode() + " " +
					// prEmpVo.getDivDesc(),group));
					// startRow++;
					for (int i = 1 + 1; i < 24 + 1; i++) {
						sheet.addCell(new Blank(i, startRow, borderLR));
					}
					startRow++;
					sheet.setRowView(startRow, 320);
					sheet.addCell(new Label(0, startRow, String.valueOf(k + 1),
							numberCenter));

					sheet.addCell(new Label(0 + 1, startRow, prEmpVo
							.getFlagStatus(), borderNo));
					sheet.addCell(new Label(1 + 1, startRow, prEmpVo
							.getEmpCode(), borderNo));
					sheet.addCell(new Label(2 + 1, startRow, prEmpVo
							.getPrefix()
							+ " "
							+ prEmpVo.getName()
							+ " "
							+ prEmpVo.getLastName(), borderData));
					sheet.addCell(new Label(3 + 1, startRow,
							prEmpVo.getTaxId(), numberCenter));
					sheet.addCell(new Label(4 + 1, startRow, prEmpVo
							.getMarriedStatus(), borderNo));
					sheet.addCell(new Label(5 + 1, startRow, prEmpVo
							.getPayStatus(), borderNo));
					sheet.addCell(new Label(6 + 1, startRow, prEmpVo
							.getBankId(), numberCenter));
					sheet.addCell(new Label(7 + 1, startRow, prEmpVo
							.getCostChild(), borderNumber));
					sheet.addCell(new Label(8 + 1, startRow, prEmpVo
							.getChildStudy(), borderNumber));
					sheet.addCell(new Label(9 + 1, startRow, prEmpVo
							.getChildNoStudy(), borderNumber));
					sheet.addCell(new Label(10 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getCostLife()),
							number));
					sheet.addCell(new Label(11 + 1, startRow, prEmpVo
							.getGundanFlag(), borderNo));
					sheet.addCell(new Label(12 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getDebtLife()),
							number));
					sheet.addCell(new Label(13 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getDebtLoan()),
							number));
					sheet.addCell(new Label(14 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getDonate()), number));
					sheet.addCell(new Label(15 + 1, startRow, prEmpVo
							.getFlagFather(), borderNo));
					sheet.addCell(new Label(16 + 1, startRow, prEmpVo
							.getFlagMother(), borderNo));
					sheet.addCell(new Label(17 + 1, startRow, prEmpVo
							.getFlagFatherSpouse(), borderNo));
					sheet.addCell(new Label(18 + 1, startRow, prEmpVo
							.getFlagMotherSpouse(), borderNo));
					sheet.addCell(new Label(19 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getRmf()), number));
					sheet.addCell(new Label(20 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getLtf()), number));
					sheet.addCell(new Label(21 + 1, startRow,
							convertFormatCurrentcy(prEmpVo.getHealthFather()),
							number));
					sheet.addCell(new Label(
							22 + 1,
							startRow,
							convertFormatCurrentcy(prEmpVo.getHandicappedDec()),
							number));
					sheet.addCell(new Label(23 + 1, startRow, prEmpVo
							.getFlagPr(), borderDataR));
					startRow++;

					if (!prEmpVo.getCostChild().equals("")) {
						sumCostChild += Double.parseDouble(prEmpVo
								.getCostChild());

					}
					if (!prEmpVo.getChildStudy().equals("")) {
						sumChildStudy += Double.parseDouble(prEmpVo
								.getChildStudy());

					}
					if (!prEmpVo.getChildNoStudy().equals("")) {
						sumChildNoStudy += Double.parseDouble(prEmpVo
								.getChildNoStudy());

					}
					if (!prEmpVo.getCostLife().equals("")) {
						sumCostLife += Double
								.parseDouble(prEmpVo.getCostLife());

					}
					if (!prEmpVo.getDebtLife().equals("")) {
						sumDebtLife += Double
								.parseDouble(prEmpVo.getDebtLife());

					}
					if (!prEmpVo.getDebtLoan().equals("")) {
						sumDebtLoan += Double
								.parseDouble(prEmpVo.getDebtLoan());

					}

					if (!prEmpVo.getDonate().equals("")) {
						sumDonate += Double.parseDouble(prEmpVo.getDonate());

					}

					if (!prEmpVo.getHealthFather().equals("")) {
						sumHealthFather += Double.parseDouble(prEmpVo
								.getHealthFather());

					}

					if (!prEmpVo.getHandicappedDec().equals("")) {
						sumHandicappedDec += Double.parseDouble(prEmpVo
								.getHandicappedDec());

					}

					if (!prEmpVo.getLtf().equals("")) {
						sumLtf += Double.parseDouble(prEmpVo.getLtf());

					}
					if (!prEmpVo.getRmf().equals("")) {
						sumRmf += Double.parseDouble(prEmpVo.getRmf());

					}
					flagSum = "sum";
				}
			}
			for (int i = 0; i < 24 + 1; i++) {
				sheet.setRowView(startRow, 320);
				sheet.addCell(new Label(i, startRow, "", borderEndLine));
			}

			sheet.addCell(new Label(0, startRow+1,"ËÁÒÂàËµØ",remark));
			sheet.addCell(new Label(2, startRow+1,"- Ê¶Ò¹ÀÒ¾ÊÁÃÊ ",remark));
			sheet.addCell(new Label(3, startRow+2," 1 ¤×Í âÊ´",remark));
			sheet.addCell(new Label(3, startRow+3," 2 ¤×Í ¤ÙèÊÁÃÊäÁèÁÕà§Ô¹ä´é",remark));
			sheet.addCell(new Label(3, startRow+4," 3 ¤×Í ¤ÙèÊÁÃÊÁÕà§Ô¹ä´é",remark));
			sheet.addCell(new Label(3, startRow+5," 4 ¤×Í ËÁéÒÂ,ËÂèÒ,âÊ´ÁÕºØµÃºØ­¸ÃÃÁ",remark));
			sheet.addCell(new Label(2, startRow+6,"- »ÃÐàÀ·¡ÒÃÃÑºà§Ô¹à´×Í¹ ",remark));
			sheet.addCell(new Label(3, startRow+7," 1 ¤×Í ¸¹Ò¤ÒÃ",remark));
			sheet.addCell(new Label(3, startRow+8," 2 ¤×Í à§Ô¹Ê´",remark));
			sheet.addCell(new Label(2, startRow+9,"- Ê¶Ò¹Ð¡ÒÃ¨èÒÂà§Ô¹à´×Í¹ ",remark));
			sheet.addCell(new Label(3, startRow+10," 1 ¤×Í ÃÐ§Ñº¡ÒÃ¨èÒÂà§Ô¹à´×Í¹",remark));
			sheet.addCell(new Label(3, startRow+11," 2 ¤×Í ¨èÒÂà©¾ÒÐÃÒÂ¡ÒÃÃÑºÂ¡àÇé¹à§Ô¹à´×Í¹",remark));
			sheet.getSettings().setPassword("028313766");
			sheet.getSettings().setProtected(true);
		} else {
			Alignment noAlg = Alignment.getAlignment(1);
			WritableCellFormat border = new WritableCellFormat();
			border.setAlignment(noAlg.CENTRE);
			border.setBorder(Border.ALL, BorderLineStyle.THIN);
			sheet.mergeCells(0, startRow, 21, startRow);
			sheet.addCell(new Label(0, startRow, "äÁè¾º¢éÍÁÙÅ",border));
			WritableCellFormat border2 = new WritableCellFormat();
			sheet.removeRow(7);
			sheet.getSettings().setPassword("028313766");
			sheet.getSettings().setProtected(true);
		}
		ww.write();
		ww.close();
		wb.close();
		in.close();
		return null;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ç¨ï¿½Íº Level ï¿½Í§ Data
	 * 
	 * @param div
	 * @param sec
	 * @return
	 */
	private int checkLV(String area, String sec) {
		int rlst = 0;
		if (!area.equals("") && !sec.equals("")) {
			rlst = 4;
			// }else if (!area.equals("") && sec.equals("")){
		} else if (area.equals("") && sec.equals("")) {
			rlst = 3;
		}
		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FlagMarriedStatus
	 * 
	 * @param flag
	 * @return
	 */
	private String convertFlagMr(String flag) {
		if ("1".equals(flag)){
			flag = "âÊ´";
		}else if ("2".equals(flag)){
			flag = "¤ÙèÊÁÃÊäÁèÁÕà§Ô¹ä´é";
		}else if ("3".equals(flag)){
			flag = "¤ÙèÊÁÃÊÁÕà§Ô¹ä´é";
		}else if ("4".equals(flag)){
			flag = "ËÁéÒÂ,ËÂèÒ,âÊ´ÁÕºØµÃºØ­¸ÃÃÁ";
		}else{
			flag = "";
		}
		return flag;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FlagPayStatus
	 * 
	 * @param flag
	 * @return
	 */
	private String convertFlagPay(String flag) {
		if ("1".equals(flag)){
			flag = "¸¹Ò¤ÒÃ";
		}else if ("2".equals(flag)){
			flag = "à§Ô¹Ê´";
		}else{
			flag = "";
		}
		return flag;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FlagGundan
	 * 
	 * @param flag
	 * @return
	 */
	private String convertFlagGundan(String flag) {
		if ("Y".equalsIgnoreCase(flag)){
			flag = "ÁÕ";
		}else if ("N".equalsIgnoreCase(flag)){
			flag = "äÁèÁÕ";
		}else{
			flag = "";
		}
		return flag;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FlagFather,FlagFatherSpouse,FlagMother,FlagMotherSpouse
	 * 
	 * @param flag
	 * @return
	 */
	private String convertFlagGuardian(String flag) {
		if ("Y".equalsIgnoreCase(flag)){
			flag = "ËÑ¡ä´é";
		}else if ("N".equalsIgnoreCase(flag)){
			flag = "ËÑ¡äÁèä´é";
		}else{
			flag = "";
		}
		return flag;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FormatCurrentcy
	 * 
	 * @param Currentcy
	 * @return
	 */
	private String convertFormatCurrentcy(String data) {
		String money = "";
		DecimalFormat dfInt = new DecimalFormat("###,##0.00");
		if (!data.equals("")) {
			money = dfInt.format(Double.parseDouble(data));
		}
		return money;
	}

}
