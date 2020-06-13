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
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.RwHealthVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.RwHealthService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP004Controller extends MultiActionController {

	public RwHealthService getRwHealthService() {
		return (RwHealthService) this.getApplicationContext().getBean(
				"rwHealthService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½Ô¹ï¿½ï¿½ï¿½Ø§ï¿½Ø¢ï¿½Ò¾
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer year = new Integer(request.getParameter("year"));
		Integer period = new Integer(request.getParameter("period"));
		String type = request.getParameter("type");
		String ouCode = request.getParameter("ouCode");
		String userId = request.getParameter("userId");
		String periodName = request.getParameter("periodName");
		DecimalFormat dfYear = new DecimalFormat("#####0");
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=./page/report/CTRWRP004.xls");
			InputStream in = this.getServletContext().getResourceAsStream(
					"/page/report/CTRWRP004.xls");

			Workbook wb = Workbook.getWorkbook(in);
			WritableWorkbook ww = Workbook.createWorkbook(
					response.getOutputStream(), wb);
			WritableSheet sheet = ww.getSheet(0);

			WritableFont font = new WritableFont(WritableFont.ARIAL);
			font.setPointSize(9);

			Alignment dataAlignLeft = Alignment.LEFT;
			Alignment dataAlignRight = Alignment.RIGHT;
			Alignment dataAlignCenter = Alignment.CENTRE;

			WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
			fontBold.setBoldStyle(WritableFont.BOLD);
			fontBold.setPointSize(9);

			Alignment alg = Alignment.getAlignment(1);

			WritableCellFormat boldCenterFormat = new WritableCellFormat();
			boldCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			boldCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldCenterFormat.setAlignment(dataAlignCenter);
			boldCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldCenterFormat.setFont(font);

			WritableCellFormat normalCenterFormat = new WritableCellFormat();
			normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalCenterFormat.setAlignment(dataAlignCenter);
			normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalCenterFormat.setFont(font);

			WritableCellFormat borderNumber = new WritableCellFormat();
			borderNumber.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderNumber.setAlignment(Alignment.RIGHT);
			borderNumber.setFont(font);

			WritableCellFormat borderNumber2 = new WritableCellFormat();
			borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber2.setAlignment(Alignment.RIGHT);
			borderNumber2.setFont(fontBold);

			WritableCellFormat HeadFormat = new WritableCellFormat();
			HeadFormat.setAlignment(Alignment.LEFT);
			HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			HeadFormat.setFont(fontBold);

			WritableCellFormat borderEndLine = new WritableCellFormat();
			borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
			borderEndLine.setAlignment(Alignment.LEFT);

			CellFormat title = sheet.getWritableCell(3, 0).getCellFormat();
			// CellFormat headRight = sheet.getWritableCell(0,
			// 2).getCellFormat();
			CellFormat headerRpt = sheet.getWritableCell(4, 0).getCellFormat();
			CellFormat group = sheet.getWritableCell(0, 5).getCellFormat();
			// CellFormat borderLR = sheet.getWritableCell(3,
			// 5).getCellFormat();

			WritableCellFormat borderLR = new WritableCellFormat();
			borderLR.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderLR.setAlignment(dataAlignLeft);
			borderLR.setVerticalAlignment(VerticalAlignment.CENTRE);
			borderLR.setFont(font);

			WritableCellFormat headRight = new WritableCellFormat();
			headRight.setAlignment(dataAlignRight);
			headRight.setVerticalAlignment(VerticalAlignment.CENTRE);
			headRight.setFont(fontBold);

			WritableCellFormat headLeft = new WritableCellFormat();
			headLeft.setAlignment(dataAlignLeft);
			headLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
			headLeft.setFont(fontBold);
			// CellFormat borderLRId = sheet.getWritableCell(2,
			// 5).getCellFormat();

			GregorianCalendar gd = new GregorianCalendar();
			SimpleDateFormat sdfPrint = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm", new java.util.Locale("th", "TH"));
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			/* start title */
			// sheet.mergeCells(0, 1, 3, 1);
			// sheet.addCell(new Label(0, 1,
			// "ï¿½ï¿½ï¿½ï¿½ï¿½ CTRWRP004 ï¿½ï¿½Â§Ò¹ï¿½Ô¹ï¿½ï¿½ï¿½zï¿½ï¿½Ø§ï¿½Ø¢ï¿½Ò¾",
			// HeadFormat));
			// sheet.addCell(new Label(0, 0,
			// this.getSuOrganizeService().findOrganizeName(ouCode), title));
			sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
			sheet.addCell(new Label(3, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, headRight));
			sheet.addCell(new Label(5, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));
			/* end title */

			RwHealthVO helVo = null;
			List helRpt = this.getRwHealthService().findHealthReport(ouCode,
					year, period, type, userId);
			int startRow = 4;
			String tmpCode = "";
			int checkLV = 0;
			double sumTotalHour = 0.0;
			double sumAllTotalHour = 0.0;
			String flagTotalHour = "";
			int seq = 0;
			if (helRpt.size() != 0) {
				for (int i = 0; i < helRpt.size(); i++) {
					helVo = (RwHealthVO) helRpt.get(i);
					checkLV = checkLV(helVo.getAreaCode(), helVo.getSecCode());
					if (checkLV == 3) {
						if (!tmpCode.equals(helVo.getAreaCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, helVo
									.getOrgCode() + " " + helVo.getAreaDesc(),
									group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo
									.getPreFix()
									+ " "
									+ helVo.getFirstName()
									+ " " + helVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									String.valueOf(dfYear.format(helVo
											.getYearWork()))
											+ "/"
											+ String.valueOf(dfYear
													.format(helVo
															.getPeriodWork())),
									normalCenterFormat));
							sheet.addCell(new Label(4, startRow,
									convertFormatHour(helVo.getTotalHour()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertFlagPr(helVo.getFlagPr()), borderLR));
							flagTotalHour = "sum";
							if (helVo.getTotalHour() != null) {
								sumTotalHour = sumTotalHour
										+ helVo.getTotalHour().doubleValue();
								sumAllTotalHour = sumAllTotalHour
										+ helVo.getTotalHour().doubleValue();

							}
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == (i + 1)) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;
							}
						} else {
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo
									.getPreFix()
									+ " "
									+ helVo.getFirstName()
									+ " " + helVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									String.valueOf(dfYear.format(helVo
											.getYearWork()))
											+ "/"
											+ String.valueOf(dfYear
													.format(helVo
															.getPeriodWork())),
									normalCenterFormat));
							sheet.addCell(new Label(4, startRow,
									convertFormatHour(helVo.getTotalHour()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertFlagPr(helVo.getFlagPr()), borderLR));
							flagTotalHour = "sum";
							if (helVo.getTotalHour() != null) {
								sumTotalHour = sumTotalHour
										+ helVo.getTotalHour().doubleValue();
								sumAllTotalHour = sumAllTotalHour
										+ helVo.getTotalHour().doubleValue();

							}
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;
							}
						}
						tmpCode = helVo.getAreaCode();
					} else if (checkLV == 4) {
						if (!tmpCode.equals(helVo.getSecCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;

							}
							sheet.addCell(new Label(0, startRow, helVo
									.getOrgCode()
									+ " "
									+ helVo.getAreaDesc()
									+ " " + helVo.getSecDesc(), group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo
									.getPreFix()
									+ " "
									+ helVo.getFirstName()
									+ " " + helVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									String.valueOf(dfYear.format(helVo
											.getYearWork()))
											+ "/"
											+ String.valueOf(dfYear
													.format(helVo
															.getPeriodWork())),
									normalCenterFormat));
							sheet.addCell(new Label(4, startRow,
									convertFormatHour(helVo.getTotalHour()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertFlagPr(helVo.getFlagPr()), borderLR));
							flagTotalHour = "sum";
							if (helVo.getTotalHour() != null) {
								sumTotalHour = sumTotalHour
										+ helVo.getTotalHour().doubleValue();
								sumAllTotalHour = sumAllTotalHour
										+ helVo.getTotalHour().doubleValue();
							}
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;
							}

							sheet.setRowView(startRow, 320);
						} else {
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo
									.getPreFix()
									+ " "
									+ helVo.getFirstName()
									+ " " + helVo.getLastName(), borderLR));
							sheet.addCell(new Label(3, startRow,
									String.valueOf(dfYear.format(helVo
											.getYearWork()))
											+ "/"
											+ String.valueOf(dfYear
													.format(helVo
															.getPeriodWork())),
									normalCenterFormat));
							sheet.addCell(new Label(4, startRow,
									convertFormatHour(helVo.getTotalHour()),
									borderNumber));
							sheet.addCell(new Label(5, startRow,
									convertFlagPr(helVo.getFlagPr()), borderLR));
							flagTotalHour = "sum";
							if (helVo.getTotalHour() != null) {
								sumTotalHour = sumTotalHour
										+ helVo.getTotalHour().doubleValue();
								sumAllTotalHour = sumAllTotalHour
										+ helVo.getTotalHour().doubleValue();

							}
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 3, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(4, startRow,
										convertFormatHour(new Double(
												sumTotalHour)), borderNumber2));
								sheet.addCell(new Label(5, startRow, "",
										borderNumber2));
								sumTotalHour = 0.0;
								startRow++;
							}
						}
						tmpCode = helVo.getSecCode();
					} else if (checkLV == 0) { // area code == null
						seq = seq + 1;
						sheet.addCell(new Label(0, startRow, String
								.valueOf(seq), borderNumber));
						sheet.addCell(new Label(1, startRow,
								helVo.getEmpCode(), boldCenterFormat));
						sheet.addCell(new Label(2, startRow, helVo.getPreFix()
								+ " " + helVo.getFirstName() + " "
								+ helVo.getLastName(), borderLR));
						sheet.addCell(new Label(3, startRow, String
								.valueOf(dfYear.format(helVo.getYearWork()))
								+ "/"
								+ String.valueOf(dfYear.format(helVo
										.getPeriodWork())), normalCenterFormat));
						sheet.addCell(new Label(4, startRow,
								convertFormatHour(helVo.getTotalHour()),
								borderNumber));
						sheet.addCell(new Label(5, startRow,
								convertFlagPr(helVo.getFlagPr()), borderLR));
						flagTotalHour = "sum";
						if (helVo.getTotalHour() != null) {
							sumTotalHour = sumTotalHour
									+ helVo.getTotalHour().doubleValue();
							sumAllTotalHour = sumAllTotalHour
									+ helVo.getTotalHour().doubleValue();
						}
						sheet.setRowView(startRow, 320);
						startRow++;
						if (helRpt.size() == i + 1) {
							sheet.mergeCells(0, startRow, 3, startRow);
							sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
							sheet.addCell(new Label(
									4,
									startRow,
									convertFormatHour(new Double(sumTotalHour)),
									borderNumber2));
							sheet.addCell(new Label(5, startRow, "",
									borderNumber2));
							sumTotalHour = 0.0;
							startRow++;
						}
						tmpCode = helVo.getAreaCode();
					}
				}

				sheet.mergeCells(0, startRow, 3, startRow);
				sheet.addCell(new Label(0, startRow, "ÃÇÁ·Ñé§ËÁ´", borderNumber2));
				sheet.addCell(new Label(4, startRow,
						convertFormatHour(new Double(sumAllTotalHour)),
						borderNumber2));
				sheet.addCell(new Label(5, startRow, "", borderNumber2));
				startRow++;
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
				sheet.addCell(new Label(5, startRow, "", borderEndLine));
				sheet.getSettings().setPassword("028313766");
				sheet.getSettings().setProtected(true);
			} else {
				sheet.mergeCells(0, 4, 5, 4);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border = new WritableCellFormat();
				border.setAlignment(Alignment.CENTRE);
				border.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
				sheet.addCell(new Label(3, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, headRight));
				sheet.addCell(new Label(5, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));
				sheet.addCell(new Label(0, 4, "äÁè¾º¢éÍÁÙÅ", border));
				sheet.removeRow(5);
			}

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
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ FlagPr
	 * 
	 * @param flag
	 * @return
	 */
	private String convertFlagPr(String flag) {
		if ("N".equals(flag)){
			flag = " ";
		}else if ("A".equals(flag)){
			flag = "»ÃÑº»ÃØ§";
		}else if ("R".equals(flag)){
			flag = "àÃÕÂ¡¤×¹";
		}else if ("B".equals(flag)){
			flag = "µ¡àºÔ¡»ÃÑº»ÃØ§ÃÒÂ¡ÒÃÃÑº";
		}else if ("S".equals(flag)){
			flag = "µ¡àºÔ¡»ÃÑº»ÃØ§ÃÒÂ¡ÒÃàÃÕÂ¡¤×¹";
		}else{
			flag = "";
		}
		return flag;
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½Å§ Time Format
	 * 
	 * @param hour
	 * @return
	 */
	private String convertFormatHour(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0");
		rlst = dec.format(hour != null ? hour.intValue() : 0);
		return rlst;
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
		if ((area != null && sec != null)) {
			rlst = 4;
		} else if (area != null && sec == null) {
			rlst = 3;
		}
		return rlst;
	}
}
