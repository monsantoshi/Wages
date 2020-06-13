/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.FeeRwPremiumReportByOrgVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.FeeWpayRwPremiumService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYRP005Controller extends MultiActionController {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	private FeeWpayRwPremiumService getFeeWpayRwPremiumService() {
		return (FeeWpayRwPremiumService) this.getApplicationContext().getBean(
				"feeWpayRwPremiumService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		DecimalFormat dfYear = new DecimalFormat("#####0");

		String ouCode = "";
		String userId = "";
		int year = 0;
		int period = 0;
		String flag = "";
		String section = "";

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
			period = Integer.parseInt(request.getParameter("period"));
		}

		if (request.getParameter("flag") != null) {
			flag = request.getParameter("flag");
		}

		if (request.getParameter("section") != null) {
			section = request.getParameter("section");
		}

		List empList = this.getFeeWpayRwPremiumService().rwPremiumReportByOrg(userId,
				ouCode, year, period, flag);
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment; filename=./page/report/CTWPAYRP005.xls");
		InputStream in = this.getServletContext().getResourceAsStream("/page/report/CTWPAYRP005.xls");
		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);

		WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(9);

		Alignment dataAlignLeft = Alignment.LEFT;
		Alignment dataAlignRight = Alignment.RIGHT;
		Alignment dataAlignCenter = Alignment.CENTRE;

		WritableCellFormat boldLeftFormat = new WritableCellFormat();
		boldLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		boldLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		boldLeftFormat.setAlignment(dataAlignLeft);
		boldLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		boldLeftFormat.setFont(fontBold);

		WritableCellFormat HeadFormat = new WritableCellFormat();
		HeadFormat.setAlignment(dataAlignCenter);
		HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		HeadFormat.setFont(fontBold);

		WritableCellFormat headRight = new WritableCellFormat();
		headRight.setAlignment(dataAlignRight);
		headRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		headRight.setFont(fontBold);

		WritableCellFormat headLeft = new WritableCellFormat();
		headLeft.setAlignment(dataAlignLeft);
		headLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
		headLeft.setFont(fontBold);

		WritableCellFormat normalLeftFormat = new WritableCellFormat();
		normalLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalLeftFormat.setAlignment(dataAlignLeft);
		normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftFormat.setFont(fontNormal);

		WritableCellFormat normalCenterFormat = new WritableCellFormat();
		normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalCenterFormat.setAlignment(dataAlignCenter);
		normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterFormat.setFont(fontNormal);

		WritableCellFormat normalRightFormat = new WritableCellFormat();
		normalRightFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalRightFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalRightFormat.setAlignment(dataAlignRight);
		normalRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightFormat.setFont(fontNormal);

		WritableCellFormat normalLeftLastFormat = new WritableCellFormat();
		normalLeftLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalLeftLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalLeftLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalLeftLastFormat.setAlignment(dataAlignLeft);
		normalLeftLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftLastFormat.setFont(fontNormal);

		WritableCellFormat normalCenterLastFormat = new WritableCellFormat();
		normalCenterLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalCenterLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalCenterLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalCenterLastFormat.setAlignment(dataAlignCenter);
		normalCenterLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterLastFormat.setFont(fontNormal);

		WritableCellFormat normalRightLastFormat = new WritableCellFormat();
		normalRightLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalRightLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalRightLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalRightLastFormat.setAlignment(dataAlignRight);
		normalRightLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightLastFormat.setFont(fontNormal);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(fontBold);

		// /////////////////////////////////////////////////////////////

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		// CellFormat headRight = sheet1.getWritableCell(0, 2).getCellFormat();

		sheet1.getSettings().setPassword("028313766");
		sheet1.getSettings().setProtected(true);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		sheet1.addCell(new Label(0, 0, ouDesc, HeadFormat));
		// sheet1.addCell(new Label(0,
		// 2,"ï¿½ï¿½Ð¨Ó§Ç´ "+section+" ï¿½.ï¿½. "+year+" ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½~ï¿½ :
		// "+sdfPrint.format(gd.getTime()),headRight));
		sheet1.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
		sheet1.addCell(new Label(3, 2,"   »ÃÐ¨Ó§Ç´ "+section+" ¾.È. "+year,HeadFormat));
		sheet1.addCell(new Label(10, 2,"ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
		if (empList.size() > 0) {

			int startRow = 5;
			String orgDesc = "";
			String flagDesc = "";
			int row = startRow;
			int count = 0;
			int seq = 0;
			double sumMorDay = 0.0;
			double sumAftDay = 0.0;
			double sumEvnDay = 0.0;
			double sumMorHour = 0.0;
			double sumAftHour = 0.0;
			double sumEvnHour = 0.0;
			double sumAllMorDay = 0.0;
			double sumAllAftDay = 0.0;
			double sumAllEvnDay = 0.0;
			double sumAllMorHour = 0.0;
			double sumAllAftHour = 0.0;
			double sumAllEvnHour = 0.0;
			String flagTotalHour = "";

			for (Iterator itt = empList.iterator(); itt.hasNext();) {
				FeeRwPremiumReportByOrgVO vo = (FeeRwPremiumReportByOrgVO) itt.next();
				count = count + 1;
				if (!orgDesc.equals(vo.getOrgDesc())) {
					sheet1.setRowView(row, 320);
					// -----------------Start Show sum
					// hours----bybow------------------------//
					if (!flagTotalHour.equals("")) {
						sheet1.mergeCells(0, row, 3, row);
						sheet1.addCell(new Label(0, row , "ÃÇÁ", borderNumber2));
						sheet1.addCell(new Label(4, row,
								convertFormatHour(new Double(sumMorDay)),
								borderNumber2));
						sheet1.addCell(new Label(5, row,
								convertFormatHour(new Double(sumAftDay)),
								borderNumber2));
						sheet1.addCell(new Label(6, row,
								convertFormatHour(new Double(sumEvnDay)),
								borderNumber2));

						sheet1.addCell(new Label(7, row,
								convertFormatHour(new Double(sumMorHour)),
								borderNumber2));
						sheet1.addCell(new Label(8, row,
								convertFormatHour(new Double(sumAftHour)),
								borderNumber2));
						sheet1.addCell(new Label(9, row,
								convertFormatHour(new Double(sumEvnHour)),
								borderNumber2));
						sheet1.addCell(new Label(10, row, "", borderNumber2));
						sumMorDay = 0.0;
						sumAftDay = 0.0;
						sumEvnDay = 0.0;
						sumMorHour = 0.0;
						sumAftHour = 0.0;
						sumEvnHour = 0.0;
						flagTotalHour = "";
						row++;
					}
					// -----------------End Show sum
					// hours----------------------------//
					sheet1.addCell(new Label(0, row, ChgNullToEmpty(
							vo.getOrgDesc(), ""), boldLeftFormat));
					sheet1.addCell(new Blank(1, row, boldLeftFormat));
					sheet1.addCell(new Blank(2, row, boldLeftFormat));
					sheet1.addCell(new Blank(3, row, boldLeftFormat));
					sheet1.addCell(new Blank(4, row, boldLeftFormat));
					sheet1.addCell(new Blank(5, row, boldLeftFormat));
					sheet1.addCell(new Blank(6, row, boldLeftFormat));
					sheet1.addCell(new Blank(7, row, boldLeftFormat));
					sheet1.addCell(new Blank(8, row, boldLeftFormat));
					sheet1.addCell(new Blank(9, row, boldLeftFormat));
					sheet1.addCell(new Blank(10, row, boldLeftFormat));

					orgDesc = ChgNullToEmpty(vo.getOrgDesc(), "");
					row++;
				}

				if (empList.size() == count) {
					seq = seq + 1;
					sheet1.setRowView(row, 320);
					sheet1.addCell(new Label(0, row, String.valueOf(dfInt
							.format(seq)), normalRightLastFormat));
					sheet1.addCell(new Label(1, row, vo.getEmpCode(),
							normalCenterLastFormat));
					sheet1.addCell(new Label(2, row, vo.getEmpName(),
							normalLeftLastFormat));
					sheet1.addCell(new Label(3, row, String.valueOf(dfYear
							.format(vo.getYear()))
							+ "/"
							+ String.valueOf(dfYear.format(vo.getPeriod())),
							normalCenterLastFormat));
					if (vo.getMorDay() != null) {
						sumMorDay += vo.getMorDay().doubleValue();
						sumAllMorDay += vo.getMorDay().doubleValue();
					}
					if (vo.getAftDay() != null) {
						sumAftDay += vo.getAftDay().doubleValue();
						sumAllAftDay += vo.getAftDay().doubleValue();
					}
					if (vo.getEvnDay() != null) {
						sumEvnDay += vo.getEvnDay().doubleValue();
						sumAllEvnDay += vo.getEvnDay().doubleValue();
					}

					if (vo.getMorHour() != null) {
						sumMorHour += vo.getMorHour().doubleValue();
						sumAllMorHour += vo.getMorHour().doubleValue();
					}
					if (vo.getAftHour() != null) {
						sumAftHour += vo.getAftHour().doubleValue();
						sumAllAftHour += vo.getAftHour().doubleValue();
					}
					if (vo.getEvnHour() != null) {
						sumEvnHour += vo.getEvnHour().doubleValue();
						sumAllEvnHour += vo.getEvnHour().doubleValue();
					}
					flagTotalHour = "sum";
					if (vo.getMorDay() != null) {
						sheet1.addCell(new Label(4, row, String.valueOf(dfInt
								.format(vo.getMorDay())), normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(4, row, normalRightLastFormat));
					}
					if (vo.getAftDay() != null) {
						sheet1.addCell(new Label(5, row, String.valueOf(dfInt
								.format(vo.getAftDay())), normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(5, row, normalRightLastFormat));
					}
					if (vo.getEvnDay() != null) {
						sheet1.addCell(new Label(6, row, String.valueOf(dfInt
								.format(vo.getEvnDay())), normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalRightLastFormat));
					}
					if (vo.getMorHour() != null) {
						sheet1.addCell(new Label(7, row, String.valueOf(dfInt
								.format(vo.getMorHour())),
								normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(7, row, normalRightLastFormat));
					}
					if (vo.getAftHour() != null) {
						sheet1.addCell(new Label(8, row, String.valueOf(dfInt
								.format(vo.getAftHour())),
								normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(8, row, normalRightLastFormat));
					}
					if (vo.getEvnHour() != null) {
						sheet1.addCell(new Label(9, row, String.valueOf(dfInt
								.format(vo.getEvnHour())),
								normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(9, row, normalRightLastFormat));
					}
					flagDesc = "";
					if (vo.getFlagPr() != null){
						flagDesc = "µ¡àºÔ¡àÃÕÂ¡¤×¹";
						if (vo.getFlagPr().equals("N")){ flagDesc = "";}
						if (vo.getFlagPr().equals("A")){ flagDesc = "»ÃÑº»ÃØ§";}
						if (vo.getFlagPr().equals("R")){ flagDesc = "àÃÕÂ¡¤×¹";}
						if (vo.getFlagPr().equals("B")){ flagDesc = "µ¡àºÔ¡»ÃÑº»ÃØ§";}
					}
					sheet1.addCell(new Label(10, row, flagDesc,
							normalLeftLastFormat));
					row++;
					if (!flagTotalHour.equals("")) {
						sheet1.mergeCells(0, row, 3, row);
						sheet1.addCell(new Label(0, row , "ÃÇÁ", borderNumber2));
						sheet1.addCell(new Label(4, row,
								convertFormatHour(new Double(sumMorDay)),
								borderNumber2));
						sheet1.addCell(new Label(5, row,
								convertFormatHour(new Double(sumAftDay)),
								borderNumber2));
						sheet1.addCell(new Label(6, row,
								convertFormatHour(new Double(sumEvnDay)),
								borderNumber2));

						sheet1.addCell(new Label(7, row,
								convertFormatHour(new Double(sumMorHour)),
								borderNumber2));
						sheet1.addCell(new Label(8, row,
								convertFormatHour(new Double(sumAftHour)),
								borderNumber2));
						sheet1.addCell(new Label(9, row,
								convertFormatHour(new Double(sumEvnHour)),
								borderNumber2));
						sheet1.addCell(new Label(10, row, "", borderNumber2));
						sumMorDay = 0.0;
						sumAftDay = 0.0;
						sumEvnDay = 0.0;
						sumMorHour = 0.0;
						sumAftHour = 0.0;
						sumEvnHour = 0.0;
						flagTotalHour = "";
						row++;
					}
				} else {
					seq = seq + 1;
					sheet1.setRowView(row, 320);
					sheet1.addCell(new Label(0, row, String.valueOf(dfInt
							.format(seq)), normalRightFormat));
					sheet1.addCell(new Label(1, row, vo.getEmpCode(),
							normalCenterFormat));
					sheet1.addCell(new Label(2, row, vo.getEmpName(),
							normalLeftFormat));
					sheet1.addCell(new Label(3, row, String.valueOf(dfYear
							.format(vo.getYear()))
							+ "/"
							+ String.valueOf(dfYear.format(vo.getPeriod())),
							normalCenterFormat));
					if (vo.getMorDay() != null) {
						sumMorDay += vo.getMorDay().doubleValue();
						sumAllMorDay += vo.getMorDay().doubleValue();
					}
					if (vo.getAftDay() != null) {
						sumAftDay += vo.getAftDay().doubleValue();
						sumAllAftDay += vo.getAftDay().doubleValue();
					}
					if (vo.getEvnDay() != null) {
						sumEvnDay += vo.getEvnDay().doubleValue();
						sumAllEvnDay += vo.getEvnDay().doubleValue();
					}

					if (vo.getMorHour() != null) {
						sumMorHour += vo.getMorHour().doubleValue();
						sumAllMorHour += vo.getMorHour().doubleValue();
					}
					if (vo.getAftHour() != null) {
						sumAftHour += vo.getAftHour().doubleValue();
						sumAllAftHour += vo.getAftHour().doubleValue();
					}
					if (vo.getEvnHour() != null) {
						sumEvnHour += vo.getEvnHour().doubleValue();
						sumAllEvnHour += vo.getEvnHour().doubleValue();
					}
					flagTotalHour = "sum";

					if (vo.getMorDay() != null) {
						sheet1.addCell(new Label(4, row, String.valueOf(dfInt
								.format(vo.getMorDay())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(4, row, normalRightFormat));
					}
					if (vo.getAftDay() != null) {
						sheet1.addCell(new Label(5, row, String.valueOf(dfInt
								.format(vo.getAftDay())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(5, row, normalRightFormat));
					}
					if (vo.getEvnDay() != null) {
						sheet1.addCell(new Label(6, row, String.valueOf(dfInt
								.format(vo.getEvnDay())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalRightFormat));
					}
					if (vo.getMorHour() != null) {
						sheet1.addCell(new Label(7, row, String.valueOf(dfInt
								.format(vo.getMorHour())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(7, row, normalRightFormat));
					}
					if (vo.getAftHour() != null) {
						sheet1.addCell(new Label(8, row, String.valueOf(dfInt
								.format(vo.getAftHour())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(8, row, normalRightFormat));
					}
					if (vo.getEvnHour() != null) {
						sheet1.addCell(new Label(9, row, String.valueOf(dfInt
								.format(vo.getEvnHour())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(9, row, normalRightFormat));
					}
					flagDesc = "";
					if (vo.getFlagPr() != null){
						flagDesc = "µ¡àºÔ¡àÃÕÂ¡¤×¹";
						if (vo.getFlagPr().equals("N")){ flagDesc = "";}
						if (vo.getFlagPr().equals("A")){ flagDesc = "»ÃÑº»ÃØ§";}
						if (vo.getFlagPr().equals("R")){ flagDesc = "àÃÕÂ¡¤×¹";}
						if (vo.getFlagPr().equals("B")){ flagDesc = "µ¡àºÔ¡»ÃÑº»ÃØ§";}
					}
					sheet1.addCell(new Label(10, row, flagDesc,
							normalLeftFormat));
				}
				row++;
			}
			row--;
			sheet1.mergeCells(0, row, 3, row);
			sheet1.addCell(new Label(0, row , "ÃÇÁ·Ñé§ËÁ´", borderNumber2));
			sheet1.addCell(new Label(4, row, convertFormatHour(new Double(
					sumAllMorDay)), borderNumber2));
			sheet1.addCell(new Label(5, row, convertFormatHour(new Double(
					sumAllAftDay)), borderNumber2));
			sheet1.addCell(new Label(6, row, convertFormatHour(new Double(
					sumAllEvnDay)), borderNumber2));
			sheet1.addCell(new Label(7, row, convertFormatHour(new Double(
					sumAllMorHour)), borderNumber2));
			sheet1.addCell(new Label(8, row, convertFormatHour(new Double(
					sumAllAftHour)), borderNumber2));
			sheet1.addCell(new Label(9, row, convertFormatHour(new Double(
					sumAllEvnHour)), borderNumber2));
			sheet1.addCell(new Label(10, row, "", borderNumber2));

			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		} else {
			sheet1.addCell(new Label(0, 0, ouDesc, HeadFormat));
			sheet1.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
			sheet1.addCell(new Label(3, 2,"   »ÃÐ¨Ó§Ç´ "+section+" ¾.È. "+year,HeadFormat));
			sheet1.addCell(new Label(10, 2,"ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
			sheet1.setRowView(5, 320);
			sheet1.mergeCells(0,5, 10,5);
			sheet1.addCell(new Label(0, 5, "äÁè¾º¢éÍÁÙÅ",normalCenterLastFormat));		
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}
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
}
