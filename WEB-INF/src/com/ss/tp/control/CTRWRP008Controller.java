/*
 * Created on 8 ï¿½.ï¿½. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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

import com.ss.tp.dto.RwModSalVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.RwModSalService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP008Controller extends MultiActionController {

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public RwModSalService getRwModSqlService() {
		return (RwModSalService) this.getApplicationContext().getBean(
				"rwModSalService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½Å»ï¿½Ñºï¿½ï¿½Ø§ï¿½ï¿½Â¡ï¿½ï¿½ï¿½Ñº (ï¿½Ô¹ï¿½ï¿½Í¹)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = request.getParameter("ouCode");
		String userId = request.getParameter("userId");
		int year = request.getParameter("year") != null ? Integer
				.parseInt(request.getParameter("year").toString()) : 0;
		int period = request.getParameter("period") != null ? Integer
				.parseInt(request.getParameter("period").toString()) : 0;
		String periodName = request.getParameter("periodName");
		String type = request.getParameter("type");
		int countRow = 1;
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=./page/report/CTRWRP008.xls");
			InputStream in = this.getServletContext().getResourceAsStream(
					"/page/report/CTRWRP008.xls");

			Alignment alg = Alignment.getAlignment(1);

			Workbook wb = Workbook.getWorkbook(in);
			WritableWorkbook ww = Workbook.createWorkbook(
					response.getOutputStream(), wb);
			WritableSheet sheet = ww.getSheet(0);
			WritableFont font = new WritableFont(WritableFont.ARIAL);
			font.setPointSize(9);

			WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
			fontBold.setBoldStyle(WritableFont.BOLD);
			fontBold.setPointSize(9);

			WritableCellFormat borderNumber = new WritableCellFormat();
			borderNumber.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNumber.setAlignment(alg.RIGHT);
			borderNumber.setFont(font);

			WritableCellFormat borderNumber2 = new WritableCellFormat();
			borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber2.setAlignment(alg.RIGHT);
			borderNumber2.setFont(fontBold);

			WritableCellFormat borderEndLine = new WritableCellFormat();
			borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
			borderEndLine.setAlignment(alg.LEFT);

			CellFormat title = sheet.getWritableCell(0, 0).getCellFormat();
			CellFormat headLeft = sheet.getWritableCell(0, 2).getCellFormat();
			CellFormat headRight = sheet.getWritableCell(12, 2).getCellFormat();
			CellFormat progNameLine = sheet.getWritableCell(0, 1)
					.getCellFormat();
			CellFormat contentCenter = sheet.getWritableCell(3, 1)
					.getCellFormat();
			CellFormat endLine = sheet.getWritableCell(3, 6).getCellFormat();
			CellFormat border = sheet.getWritableCell(1, 6).getCellFormat();
			CellFormat group = sheet.getWritableCell(5, 6).getCellFormat();
			CellFormat borderLR = sheet.getWritableCell(1, 6).getCellFormat();
			CellFormat algCenterLR = sheet.getWritableCell(6, 6)
					.getCellFormat();

			GregorianCalendar gd = new GregorianCalendar();
			SimpleDateFormat sdfPrint = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm", new java.util.Locale("th", "TH"));
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");

			/* start title */
			// P'Jowrenger
			/*
			 * String tmpStr = "A".equals(type)? "ï¿½ï¿½ï¿½ï¿½ï¿½ CTRWRP008
			 * ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½Å»ï¿½Ñºï¿½ï¿½Ø§ï¿½ï¿½Â¡ï¿½ï¿½ï¿½Ñº (ï¿½Ô¹ï¿½ï¿½Í¹)" : "ï¿½ï¿½ï¿½ï¿½ï¿½ CTRWRP008
			 * ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½Å»ï¿½Ñºï¿½ï¿½Ø§ï¿½ï¿½Â¡ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Â¡ï¿½×¹ (ï¿½Ô¹ï¿½ï¿½Í¹)" ; sheet.addCell(new
			 * Label(0, 1, tmpStr, progNameLine)); sheet.addCell(new Label(0, 0,
			 * this.getSuOrganizeService().findOrganizeName(ouCode), title));
			 * sheet.addCell(new Label(1, 2, "ï¿½ï¿½Ð¨Ó§Ç´ï¿½ï¿½ï¿½ " + periodName + " ï¿½.ï¿½. "
			 * + year+ " ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½~ï¿½ : " +sdfPrint.format(gd.getTime()),
			 * headRight));
			 */
			/* end title */

			// UltraMod
			String tmpStr = "â»Ãá¡ÃÁ CTRWRP008";
			String content = "";
			sheet.addCell(new Label(0, 0, this.getSuOrganizeService()
					.findOrganizeName(ouCode), title));
			sheet.addCell(new Label(0, 1, tmpStr, progNameLine));

			if(type.equals("A")){
                content = "ÃÒÂ§Ò¹¢éÍÁÙÅ»ÃÑº»ÃØ§ÃÒÂ¡ÒÃÃÑº (à§Ô¹à´×Í¹)";
            }else{
                content = "ÃÒÂ§Ò¹¢éÍÁÙÅ»ÃÑº»ÃØ§ÃÒÂ¡ÒÃÃÑº/àÃÕÂ¡¤×¹ (à§Ô¹à´×Í¹)";
            }
			sheet.addCell(new Label(3, 1, content, contentCenter));
			 sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
	            sheet.addCell(new Label(3, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, contentCenter));
	            sheet.addCell(new Label(12, 2,"ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));

			RwModSalVO salVo = null;
			List salRpt = this.getRwModSqlService().findSalaryUpdateRpt(ouCode,
					userId, year, period, type);
			int startRow = 5;
			String tmpCode = "";
			int checkLV = 0;
			int size = salRpt.size();
			int i = 0;
			double sumOldSal = 0.0;
			double sumNewSal = 0.0;
			double sumAllOldSal = 0.0;
			double sumAllNewSal = 0.0;
			String flagSum = "";
			if (size != 0) {
				for (int j = 0; j < salRpt.size(); j++, i++) {
					salVo = (RwModSalVO) salRpt.get(j);
					checkLV = checkLV(salVo.getAreaCode(), salVo.getSecCode());
					if (checkLV == 3) {
					        
						if (!tmpCode.equals(salVo.getAreaCode())) {
							if (!flagSum.equals("")) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertNumberFormat(new Double(
												sumOldSal)), borderNumber2));
								sheet.addCell(new Label(5, startRow,
										convertNumberFormat(new Double(
												sumNewSal)), borderNumber2));
								sheet.mergeCells(6, startRow, 14, startRow);
								sheet.addCell(new Label(6, startRow, "",
										borderNumber2));
								sumOldSal = 0.0;
								sumNewSal = 0.0;
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, salVo
									.getAreaCode()
									+ " "
									+ checkNullDesc(salVo.getAreaDesc(), null,
											"3"), group));
							for (int cell = 1; cell <= 14; cell++) {
								sheet.addCell(new Blank(cell, startRow,
										borderLR));
							}
							sheet.setRowView(startRow, 320);
							startRow++;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(countRow), algCenterLR));
							sheet.addCell(new Label(1, startRow, salVo
									.getEmpCode(), algCenterLR));
							sheet.addCell(new Label(2, startRow, salVo
									.getPreFix()
									+ " "
									+ salVo.getFirstName()
									+ " " + salVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									convertDateFormat(salVo.getStartDateNew()),
									algCenterLR));
							sheet.addCell(new Label(4, startRow,
									convertNumberFormat(salVo.getOldSal()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertNumberFormat(salVo.getNewSal()),
									borderNumber));
							sheet.addCell(new Label(
									6,
									startRow,
									convertDateFormat(salVo.getStartDateEdun()),
									algCenterLR));

							sheet.addCell(new Label(7, startRow,
									convertDateFormat(salVo.getEndDateEdun()),
									algCenterLR));
							sheet.addCell(new Label(8, startRow,
									convertEdu(salVo.getEduWut()),
									borderNumber));
							
							sheet.addCell(new Label(9, startRow,
									convertDateFormat(salVo.getStartDateBack()),
									algCenterLR));
							sheet.addCell(new Label(10, startRow,
									convertDateFormat(salVo.getEndDateBack()),
									algCenterLR));

							sheet.addCell(new Label(11, startRow,
									convertDateFormat(salVo.getStartDateJob()),
									algCenterLR));
							sheet.addCell(new Label(12, startRow,
									convertDateFormat(salVo.getEndDateJob()),
									algCenterLR));
							sheet.addCell(new Label(13, startRow,
									convertMul(salVo.getMultipleLevel()),
									borderNumber));
							sheet.addCell(new Label(14, startRow,
									convertStep(salVo.getBackStep()),
									borderNumber));
							sheet.setRowView(startRow, 320);
							startRow++;
							countRow++;
							flagSum = "sum";
							if (salVo.getOldSal() != null) {
								sumOldSal += salVo.getOldSal().doubleValue();
								sumAllOldSal += salVo.getOldSal().doubleValue();

							}
							if (salVo.getNewSal() != null) {
								sumNewSal += salVo.getNewSal().doubleValue();
								sumAllNewSal += salVo.getNewSal().doubleValue();

							}
							if (salRpt.size() == (j + 1)) {
								if (!flagSum.equals("")) {
									sheet.mergeCells(0, startRow, 3, startRow);
									sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
									sheet.addCell(new Label(4, startRow,
											convertNumberFormat(new Double(
													sumOldSal)), borderNumber2));
									sheet.addCell(new Label(5, startRow,
											convertNumberFormat(new Double(
													sumNewSal)), borderNumber2));
									sheet.mergeCells(6, startRow, 14, startRow);
									sheet.addCell(new Label(6, startRow, "",
											borderNumber2));
									sumOldSal = 0.0;
									sumNewSal = 0.0;
									startRow++;
								}
							}
						} else {
						        
							sheet.addCell(new Label(0, startRow, String
									.valueOf(countRow), algCenterLR));
							sheet.addCell(new Label(1, startRow, salVo
									.getEmpCode(), algCenterLR));
							sheet.addCell(new Label(2, startRow, salVo
									.getPreFix()
									+ " "
									+ salVo.getFirstName()
									+ " " + salVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									convertDateFormat(salVo.getStartDateNew()),
									algCenterLR));
							sheet.addCell(new Label(4, startRow,
									convertNumberFormat(salVo.getOldSal()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertNumberFormat(salVo.getNewSal()),
									borderNumber));
							sheet.addCell(new Label(
									6,
									startRow,
									convertDateFormat(salVo.getStartDateEdun()),
									algCenterLR));

							sheet.addCell(new Label(7, startRow,
									convertDateFormat(salVo.getEndDateEdun()),
									algCenterLR));
							sheet.addCell(new Label(8, startRow,
									convertEdu(salVo.getEduWut()),
									borderNumber));
							sheet.addCell(new Label(9, startRow,
									convertDateFormat(salVo.getStartDateBack()),
									algCenterLR));
							sheet.addCell(new Label(10, startRow,
									convertDateFormat(salVo.getEndDateBack()),
									algCenterLR));

							sheet.addCell(new Label(11, startRow,
									convertDateFormat(salVo.getStartDateJob()),
									algCenterLR));
							sheet.addCell(new Label(12, startRow,
									convertDateFormat(salVo.getEndDateJob()),
									algCenterLR));
							sheet.addCell(new Label(13, startRow,
									convertMul(salVo.getMultipleLevel()),
									borderNumber));
							sheet.addCell(new Label(14, startRow,
									convertStep(salVo.getBackStep()),
									borderNumber));
							sheet.setRowView(startRow, 320);
							countRow++;
							startRow++;
							flagSum = "sum";
							if (salVo.getOldSal() != null) {
								sumOldSal += salVo.getOldSal().doubleValue();
								sumAllOldSal += salVo.getOldSal().doubleValue();
							}
							if (salVo.getNewSal() != null) {
								sumNewSal += salVo.getNewSal().doubleValue();
								sumAllNewSal += salVo.getNewSal().doubleValue();

							}
							if (salRpt.size() == (j + 1)) {
								if (!flagSum.equals("")) {
									sheet.mergeCells(0, startRow, 3, startRow);
									sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
									sheet.addCell(new Label(4, startRow,
											convertNumberFormat(new Double(
													sumOldSal)), borderNumber2));
									sheet.addCell(new Label(5, startRow,
											convertNumberFormat(new Double(
													sumNewSal)), borderNumber2));
									sheet.mergeCells(6, startRow, 14, startRow);
									sheet.addCell(new Label(6, startRow, "",
											borderNumber2));
									sumOldSal = 0.0;
									sumNewSal = 0.0;
									startRow++;
								}
							}
						}
						tmpCode = salVo.getAreaCode();
					} else if (checkLV == 4) {
                                               
						if (!tmpCode.equals(salVo.getSecCode())) {
							if (!flagSum.equals("")) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertNumberFormat(new Double(
												sumOldSal)), borderNumber2));
								sheet.addCell(new Label(5, startRow,
										convertNumberFormat(new Double(
												sumNewSal)), borderNumber2));
								sheet.mergeCells(6, startRow, 14, startRow);
								sheet.addCell(new Label(6, startRow, "",
										borderNumber2));
								sumOldSal = 0.0;
								sumNewSal = 0.0;
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, salVo
									.getSecCode()
									+ " "
									+ checkNullDesc(salVo.getAreaDesc(),
											salVo.getSecDesc(), "4"), group));
							for (int cell = 1; cell <= 14; cell++) {
								sheet.addCell(new Blank(cell, startRow,
										borderLR));
							}
							sheet.setRowView(startRow, 320);
							startRow++;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(countRow), algCenterLR));
							sheet.addCell(new Label(1, startRow, salVo
									.getEmpCode(), algCenterLR));
							sheet.addCell(new Label(2, startRow, salVo
									.getPreFix()
									+ " "
									+ salVo.getFirstName()
									+ " " + salVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									convertDateFormat(salVo.getStartDateNew()),
									algCenterLR));
							sheet.addCell(new Label(4, startRow,
									convertNumberFormat(salVo.getOldSal()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertNumberFormat(salVo.getNewSal()),
									borderNumber));
							sheet.addCell(new Label(
									6,
									startRow,
									convertDateFormat(salVo.getStartDateEdun()),
									algCenterLR));

							sheet.addCell(new Label(7, startRow,
									convertDateFormat(salVo.getEndDateEdun()),
									algCenterLR));
							sheet.addCell(new Label(8, startRow,
									convertEdu(salVo.getEduWut()),
									borderNumber));
							sheet.addCell(new Label(
									9,
									startRow,
									convertDateFormat(salVo.getStartDateBack()),
									algCenterLR));
							sheet.addCell(new Label(10, startRow,
									convertDateFormat(salVo.getEndDateBack()),
									algCenterLR));

							sheet.addCell(new Label(11, startRow,
									convertDateFormat(salVo.getStartDateJob()),
									algCenterLR));
							sheet.addCell(new Label(12, startRow,
									convertDateFormat(salVo.getEndDateJob()),
									algCenterLR));
							sheet.addCell(new Label(13, startRow,
									convertMul(salVo.getMultipleLevel()),
									borderNumber));
							sheet.addCell(new Label(14, startRow,
									convertStep(salVo.getBackStep()),
									borderNumber));
							sheet.setRowView(startRow, 320);
							countRow++;
							startRow++;
							flagSum = "sum";
							if (salVo.getOldSal() != null) {
								sumOldSal += salVo.getOldSal().doubleValue();
								sumAllOldSal += salVo.getOldSal().doubleValue();

							}
							if (salVo.getNewSal() != null) {
								sumNewSal += salVo.getNewSal().doubleValue();
								sumAllNewSal += salVo.getNewSal().doubleValue();

							}
							if (salRpt.size() == (j + 1)) {
								if (!flagSum.equals("")) {
									sheet.mergeCells(0, startRow, 3, startRow);
									sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
									sheet.addCell(new Label(4, startRow,
											convertNumberFormat(new Double(
													sumOldSal)), borderNumber2));
									sheet.addCell(new Label(5, startRow,
											convertNumberFormat(new Double(
													sumNewSal)), borderNumber2));
									sheet.mergeCells(6, startRow, 14, startRow);
									sheet.addCell(new Label(6, startRow, "",
											borderNumber2));
									sumOldSal = 0.0;
									sumNewSal = 0.0;
									startRow++;
								}
							}
						} else {
						       
							sheet.addCell(new Label(0, startRow, String
									.valueOf(countRow), algCenterLR));
							sheet.addCell(new Label(1, startRow, salVo
									.getEmpCode(), algCenterLR));
							sheet.addCell(new Label(2, startRow, salVo
									.getPreFix()
									+ " "
									+ salVo.getFirstName()
									+ " " + salVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									convertDateFormat(salVo.getStartDateNew()),
									algCenterLR));
							sheet.addCell(new Label(4, startRow,
									convertNumberFormat(salVo.getOldSal()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertNumberFormat(salVo.getNewSal()),
									borderNumber));
							sheet.addCell(new Label(
									6,
									startRow,
									convertDateFormat(salVo.getStartDateEdun()),
									algCenterLR));

							sheet.addCell(new Label(7, startRow,
									convertDateFormat(salVo.getEndDateEdun()),
									algCenterLR));
							sheet.addCell(new Label(8, startRow,
									convertEdu(salVo.getEduWut()),
									borderNumber));
							sheet.addCell(new Label(
									9,
									startRow,
									convertDateFormat(salVo.getStartDateBack()),
									algCenterLR));
							sheet.addCell(new Label(10, startRow,
									convertDateFormat(salVo.getEndDateBack()),
									algCenterLR));

							sheet.addCell(new Label(11, startRow,
									convertDateFormat(salVo.getStartDateJob()),
									algCenterLR));
							sheet.addCell(new Label(12, startRow,
									convertDateFormat(salVo.getEndDateJob()),
									algCenterLR));
							sheet.addCell(new Label(13, startRow,
									convertMul(salVo.getMultipleLevel()),
									borderNumber));
							sheet.addCell(new Label(14, startRow,
									convertStep(salVo.getBackStep()),
									borderNumber));
							sheet.setRowView(startRow, 320);
							startRow++;
                                                        countRow++;
							flagSum = "sum";
							if (salVo.getOldSal() != null) {
								sumOldSal += salVo.getOldSal().doubleValue();
								sumAllOldSal += salVo.getOldSal().doubleValue();

							}
							if (salVo.getNewSal() != null) {
								sumNewSal += salVo.getNewSal().doubleValue();
								sumAllNewSal += salVo.getNewSal().doubleValue();

							}
							if (salRpt.size() == (j + 1)) {
								if (!flagSum.equals("")) {
									sheet.mergeCells(0, startRow, 3, startRow);
									sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
									sheet.addCell(new Label(4, startRow,
											convertNumberFormat(new Double(
													sumOldSal)), borderNumber2));
									sheet.addCell(new Label(5, startRow,
											convertNumberFormat(new Double(
													sumNewSal)), borderNumber2));
									sheet.mergeCells(6, startRow, 14, startRow);
									sheet.addCell(new Label(6, startRow, "",
											borderNumber2));
									sumOldSal = 0.0;
									sumNewSal = 0.0;
									startRow++;
								}
							}
						}
						tmpCode = salVo.getSecCode();
					} else {
					      
						sheet.addCell(new Label(0, startRow, String
								.valueOf(countRow), algCenterLR));
						sheet.addCell(new Label(1, startRow,
								salVo.getEmpCode(), algCenterLR));
						sheet.addCell(new Label(2, startRow, salVo.getPreFix()
								+ " " + salVo.getFirstName() + " "
								+ salVo.getLastName(), borderLR));
						sheet.addCell(new Label(3, startRow,
								convertDateFormat(salVo.getStartDateNew()),
								algCenterLR));
						sheet.addCell(new Label(4, startRow,
								convertNumberFormat(salVo.getOldSal()),
								borderNumber));
						sheet.addCell(new Label(5, startRow,
								convertNumberFormat(salVo.getNewSal()),
								borderNumber));
						sheet.addCell(new Label(6, startRow,
								convertDateFormat(salVo.getStartDateEdun()),
								algCenterLR));

						sheet.addCell(new Label(7, startRow,
								convertDateFormat(salVo.getEndDateEdun()),
								algCenterLR));
						sheet.addCell(new Label(8, startRow,
								convertEdu(salVo.getEduWut()),
								borderNumber));
						sheet.addCell(new Label(9, startRow,
								convertDateFormat(salVo.getStartDateBack()),
								algCenterLR));
						sheet.addCell(new Label(10, startRow,
								convertDateFormat(salVo.getEndDateBack()),
								algCenterLR));

						sheet.addCell(new Label(11, startRow,
								convertDateFormat(salVo.getStartDateJob()),
								algCenterLR));
						sheet.addCell(new Label(12, startRow,
								convertDateFormat(salVo.getEndDateJob()),
								algCenterLR));
						sheet.addCell(new Label(13, startRow, convertMul(salVo
								.getMultipleLevel()), borderNumber));
						sheet.addCell(new Label(14, startRow, convertStep(salVo
								.getBackStep()), borderNumber));
						sheet.setRowView(startRow, 320);
						countRow++;
						startRow++;
						flagSum = "sum";
						tmpCode = salVo.getAreaCode();
						if (salVo.getOldSal() != null) {
							sumOldSal += salVo.getOldSal().doubleValue();
							sumAllOldSal += salVo.getOldSal().doubleValue();

						}
						if (salVo.getNewSal() != null) {
							sumNewSal += salVo.getNewSal().doubleValue();
							sumAllNewSal += salVo.getNewSal().doubleValue();

						}
						if (salRpt.size() == (j + 1)) {
							if (!flagSum.equals("")) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertNumberFormat(new Double(
												sumOldSal)), borderNumber2));
								sheet.addCell(new Label(5, startRow,
										convertNumberFormat(new Double(
												sumNewSal)), borderNumber2));
								sheet.mergeCells(6, startRow, 14, startRow);
								sheet.addCell(new Label(6, startRow, "",
										borderNumber2));
								sumOldSal = 0.0;
								sumNewSal = 0.0;
								startRow++;
							}
						}
					}

				}

				for (int cell = 0; cell <= 13; cell++) {
					sheet.addCell(new Label(cell, startRow, "", borderEndLine));
				}
				sheet.mergeCells(0, startRow, 3, startRow);
				sheet.addCell(new Label(0, startRow, "ÃÇÁ·Ñé§ËÁ´", borderNumber2));
				sheet.addCell(new Label(4, startRow,
						convertNumberFormat(new Double(sumAllOldSal)),
						borderNumber2));
				sheet.addCell(new Label(5, startRow,
						convertNumberFormat(new Double(sumAllNewSal)),
						borderNumber2));
				sheet.mergeCells(6, startRow, 14, startRow);
				sheet.addCell(new Label(6, startRow, "", borderNumber2));
			} else {
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat empty = new WritableCellFormat();
				empty.setAlignment(noAlg.CENTRE);
				empty.setBorder(Border.ALL, BorderLineStyle.THIN);

				sheet.addCell(new Label(0, 0, this.getSuOrganizeService()
						.findOrganizeName(ouCode), title));
				sheet.addCell(new Label(0, 1, tmpStr, progNameLine));

				if(type.equals("A")){
	                content = "ÃÒÂ§Ò¹¢éÍÁÙÅ»ÃÑº»ÃØ§ÃÒÂ¡ÒÃÃÑº (à§Ô¹à´×Í¹)";
	            }else{
	                content = "ÃÒÂ§Ò¹¢éÍÁÙÅ»ÃÑº»ÃØ§ÃÒÂ¡ÒÃÃÑº/àÃÕÂ¡¤×¹ (à§Ô¹à´×Í¹)";
	            }
				sheet.addCell(new Label(3, 1, content, contentCenter));
				 sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
		            sheet.addCell(new Label(3, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, contentCenter));
		            sheet.addCell(new Label(12, 2,"ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));
					sheet.mergeCells(0, startRow, 14, startRow);
					
					sheet.addCell(new Label(0, startRow, "äÁè¾º¢éÍÁÙÅ", empty));
				sheet.removeRow(6);
			}
			sheet.getSettings().setPassword("123");
			sheet.getSettings().setProtected(true);
			ww.write();
			ww.close();
			wb.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (area != null && sec != null) {
			rlst = 4;
		} else if (area != null && sec == null) {
			rlst = 3;
		}
		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ Number Format
	 * 
	 * @param hour
	 * @return
	 */
	private String convertNumberFormat(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0.00");
		if (hour != null) {
			rlst = dec.format(hour.intValue());
		} else {
			rlst = "";
		}

		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ multiple_level
	 * 
	 * @param mul
	 * @return
	 */
	private String convertMul(String mul) {
		String rlst = "";
		if ("1".equals(mul)) {
			rlst = "0.5";
		} else if ("2".equals(mul)) {
			rlst = "1.0";
		}
		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ back_step
	 * 
	 * @param step
	 * @return
	 */
	private String convertStep(String step) {
		String rlst = "";
		if ("1".equals(step)) {
			rlst = "0.5";
		} else if ("2".equals(step)) {
			rlst = "1.0";
		} else if ("3".equals(step)) {
			rlst = "1.5";
		} else if ("4".equals(step)) {
			rlst = "2.0";
		}
		return rlst;
	}
	
	private String convertEdu(String edu) {
		String rlst = "";
		if ("5".equals(edu)) {
			rlst = "µèÓ¡ÇèÒ ».µÃÕ";
		} else if ("6".equals(edu)) {
			rlst = "».µÃÕ ¢Öé¹ä»";
		} 
		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ï¿½Ñ¹ï¿½ï¿½ï¿½
	 * 
	 * @param date
	 * @return
	 */
	public String convertDateFormat(Date date) {
		String rlst = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
					new Locale("th", "TH"));
			rlst = sdf.format(date);
		}
		return rlst;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñº check null ï¿½Í§ divDesc ï¿½ï¿½ï¿½ secDesc
	 * 
	 * @param divDesc
	 * @param secDesc
	 * @param lv
	 * @return
	 */
	public String checkNullDesc(String areaDesc, String secDesc, String lv) {
		String rlst = "";
		if ("3".equals(lv)) {
			rlst = areaDesc != null ? areaDesc : "";
		} else if ("4".equals(lv)) {
			rlst = areaDesc != null ? areaDesc : "";
			rlst += secDesc!=null?" "+secDesc:"";
		}
		return rlst;
	}
}
