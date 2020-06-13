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
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Label;

import jxl.write.Blank;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ss.tp.model.RwDanger;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.RwDangerService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP005Controller extends MultiActionController {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	private RwDangerService getRwDangerService() {
		return (RwDangerService) this.getApplicationContext().getBean(
				"rwDangerService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DecimalFormat df = new DecimalFormat("###,##0");
		DecimalFormat dfYear = new DecimalFormat("#####0");
		DecimalFormat numformat = new DecimalFormat("0");

		String ouCode = "";
		String userId = "";
		// String codeSeq = "";
		long yearPr = 0;
		long periodPr = 0;
		String flagPr = "";

		String year = "";
		String period = "";
		String section = "";
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		/*
		 * if (request.getParameter("codeSeq") != null) { codeSeq =
		 * request.getParameter("codeSeq"); }
		 */

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			yearPr = Long.parseLong(request.getParameter("year"));
		}

		if (request.getParameter("period") != null
				&& !"".equals(request.getParameter("period"))) {
			periodPr = Long.parseLong(request.getParameter("period"));
		}

		if (request.getParameter("flag") != null) {
			flagPr = request.getParameter("flag");
		}

		if (request.getParameter("section") != null) {
			section = request.getParameter("section");
		}

		String orgName = this.getRwDangerService().findOrgName(ouCode);
		List rwDangerList = this.getRwDangerService().findReportByCriteria(
				ouCode, userId, new Long(yearPr), new Long(periodPr), flagPr);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTRWRP005.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTRWRP005.xls");
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);

		WritableCellFormat borderAll = new WritableCellFormat();
		borderAll.setAlignment(Alignment.CENTRE);
		borderAll.setBorder(Border.ALL, BorderLineStyle.THIN);

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);

		WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(9);

		Alignment dataAlignLeft = Alignment.LEFT;
		Alignment dataAlignRight = Alignment.RIGHT;
		Alignment dataAlignCenter = Alignment.CENTRE;

		CellFormat cellOrgName = sheet1.getWritableCell(0, 0).getCellFormat();
		// CellFormat headRight = sheet1.getWritableCell(0,2).getCellFormat();
		CellFormat cellPeriod = sheet1.getWritableCell(2, 2).getCellFormat();

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

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		if (rwDangerList.size() > 0) {

			// ww.copySheet("Template", "Sheet0" , ww.getNumberOfSheets());
			// sheet1 = ww.getSheet(1);

			// ++++++++++Set Cell Format++++++++++
			CellFormat colDeptDesc = sheet1.getWritableCell(0, 5)
					.getCellFormat();
			WritableCellFormat cellDeptDesc = new WritableCellFormat(
					colDeptDesc);
			cellDeptDesc.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			CellFormat colEname = sheet1.getWritableCell(1, 5).getCellFormat();
			WritableCellFormat cellEname = new WritableCellFormat(colEname);
			cellEname.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			CellFormat colFullDay = sheet1.getWritableCell(2, 5)
					.getCellFormat();
			WritableCellFormat cellFullDay = new WritableCellFormat(colFullDay);
			cellFullDay.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			CellFormat colHalfDay = sheet1.getWritableCell(3, 5)
					.getCellFormat();
			WritableCellFormat cellHalfDay = new WritableCellFormat(colHalfDay);
			cellHalfDay.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			CellFormat colFlag = sheet1.getWritableCell(4, 5).getCellFormat();
			WritableCellFormat cellFlag = new WritableCellFormat(colFlag);
			cellFlag.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			RwDanger rw = null;
			int startRow = 5;
			int i = 0;
			int j = 0;
			int s = 1; // Sheet's name
			int m = 0;

			WritableCellFormat colEmpNo = new WritableCellFormat(colDeptDesc);
			colEmpNo.setAlignment(Alignment.CENTRE);

			WritableCellFormat cellEmpNo = new WritableCellFormat(colEmpNo);
			cellEmpNo.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			WritableCellFormat DeptDescF = new WritableCellFormat(colDeptDesc);
			WritableFont font = new WritableFont(WritableFont.ARIAL);
			font.setBoldStyle(WritableFont.BOLD);
			font.setPointSize(9);
			DeptDescF.setFont(font);

			WritableCellFormat borderNumber2 = new WritableCellFormat();
			borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber2.setAlignment(Alignment.RIGHT);
			borderNumber2.setFont(font);

			WritableCellFormat normalRightFormat = new WritableCellFormat();
			normalRightFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalRightFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalRightFormat.setAlignment(dataAlignRight);
			normalRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalRightFormat.setFont(fontNormal);

			WritableCellFormat normalCenterFormat = new WritableCellFormat();
			normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalCenterFormat.setAlignment(dataAlignCenter);
			normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalCenterFormat.setFont(fontNormal);

			WritableCellFormat normalLeftFormat = new WritableCellFormat();
			normalLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalLeftFormat.setAlignment(dataAlignLeft);
			normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalLeftFormat.setFont(fontNormal);

			WritableCellFormat boldLeftFormat = new WritableCellFormat();
			boldLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			boldLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldLeftFormat.setAlignment(dataAlignLeft);
			boldLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldLeftFormat.setFont(fontBold);

			String deptDesc = "";
			double sumFullDay = 0.0;
			double sumHalfDay = 0.0;
			double sumAllFullDay = 0.0;
			double sumAllHalfDay = 0.0;
			String flagTotalHour = "";
			int seq = 0;

			// Cell OrgName
			// sheet1.addCell(new Label(0, 0,
			// ChgNullToEmpty(orgName,""),cellOrgName));

			for (Iterator itt = rwDangerList.iterator(); itt.hasNext();) {
				rw = (RwDanger) itt.next();

				// Cell Period
				if (i > 0) {
					if (!year
							.equalsIgnoreCase(numformat.format(rw.getYearPr()))
							|| !period.equalsIgnoreCase(numformat.format(rw
									.getPeriodPr()))) {
						ww.copySheet(
								"Template",
								ChgNullToEmpty(
										numformat.format(rw.getPeriodPr()), "")
										+ "_"
										+ ChgNullToEmpty(numformat.format(rw
												.getYearPr()), ""), ww
										.getNumberOfSheets());
						i = 0;
						j++;
						sheet1.getSettings().setPassword("123");
						sheet1.getSettings().setProtected(true);
						sheet1 = ww.getSheet(j);
						sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
						sheet1.addCell(new Label(3, 2, "ประจำงวด " + section + "  พ.ศ." + ChgNullToEmpty(numformat.format(rw.getYearPr()),""),HeadFormat));
						sheet1.addCell(new Label(6, 2, "วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), headRight));
						year = numformat.format(rw.getYearPr());
						period = numformat.format(rw.getPeriodPr());
					}
				} else {
					sheet1.setName(ChgNullToEmpty(
							numformat.format(rw.getPeriodPr()), "")
							+ "_"
							+ ChgNullToEmpty(numformat.format(rw.getYearPr()),
									""));
					sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
					sheet1.addCell(new Label(3, 2, "ประจำงวด " + section + "  พ.ศ." + ChgNullToEmpty(numformat.format(rw.getYearPr()),""),HeadFormat));
					sheet1.addCell(new Label(6, 2, "วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), headRight));
					year = numformat.format(rw.getYearPr());
					period = numformat.format(rw.getPeriodPr());
				}

				// Column DeptDesc
				if (!deptDesc.equalsIgnoreCase(ChgNullToEmpty(rw.getDeptDesc(),
						""))) {
					// -----------------Start Show sum
					// hours----bybow------------------------//
					if (!flagTotalHour.equals("")) {
						sheet1.mergeCells(0, startRow + i, 3, startRow + i);
						sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
						sheet1.addCell(new Label(4, startRow + i,
								convertFormatHour(new Double(sumFullDay)),
								borderNumber2));
						sheet1.addCell(new Label(5, startRow + i,
								convertFormatHour(new Double(sumHalfDay)),
								borderNumber2));
						sheet1.addCell(new Label(6, startRow + i, "",
								borderNumber2));
						sumFullDay = 0.0;
						sumHalfDay = 0.0;
						flagTotalHour = "";
						i++;
					}
					// -----------------End Show sum
					// hours----------------------------//
					sheet1.setRowView(startRow + i, 320);
					sheet1.addCell(new Label(0, startRow + i, String
							.valueOf(ChgNullToEmpty(rw.getDeptDesc(), "")),
							boldLeftFormat));
					deptDesc = ChgNullToEmpty(rw.getDeptDesc(), "");
					sheet1.addCell(new Blank(1, startRow + i, normalLeftFormat));
					sheet1.addCell(new Blank(2, startRow + i, normalRightFormat));
					sheet1.addCell(new Blank(3, startRow + i, normalRightFormat));
					sheet1.addCell(new Blank(4, startRow + i, colFlag));
					sheet1.addCell(new Blank(5, startRow + i, normalRightFormat));
					sheet1.addCell(new Blank(6, startRow + i, colFlag));
					i++;
				}

				sheet1.setRowView(startRow + i, 320);

				if (m == (rwDangerList.size() - 1)) {
					flagTotalHour = "sum";
					seq = seq + 1;
					sheet1.addCell(new Label(0, startRow + i, String.valueOf(df
							.format(seq)), normalRightFormat));
					// Column EmpCode
					sheet1.addCell(new Label(1, startRow + i, rw.getEmpCode(),
							normalCenterFormat));

					// Column Ename
					sheet1.addCell(new Label(2, startRow + i, rw.getEname(),
							cellEname));

					sheet1.addCell(new Label(3, startRow + i,
							String.valueOf(dfYear.format(rw.getYearWork()))
									+ "/"
									+ String.valueOf(dfYear.format(rw
											.getPeriodWork())),
							normalCenterFormat));
					// Column Full Day
					if (rw.getFullDay() != null) {
						sumFullDay = sumFullDay + rw.getFullDay().doubleValue();
						sumAllFullDay = sumAllFullDay
								+ rw.getFullDay().doubleValue();

					}
					sheet1.addCell(new Label(4, startRow + i, String.valueOf(df
							.format(rw.getFullDay())), normalRightFormat));

					// Column Half Day
					if (rw.getHalfDay() != null) {
						sumHalfDay = sumHalfDay + rw.getHalfDay().doubleValue();
						sumAllHalfDay = sumAllHalfDay
								+ rw.getHalfDay().doubleValue();

					}
					sheet1.addCell(new Label(5, startRow + i, String.valueOf(df
							.format(rw.getHalfDay())), normalRightFormat));

					// Column FlagDesc
					sheet1.addCell(new Label(6, startRow + i, rw.getFlagDesc(),
							cellFlag));

					// -----------------Start Show sum
					// hours----bybow------------------------//
					if (!flagTotalHour.equals("")) {
						i++;
						sheet1.mergeCells(0, startRow + i, 3, startRow + i);
						sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
						sheet1.addCell(new Label(4, startRow + i,
								convertFormatHour(new Double(sumFullDay)),
								borderNumber2));
						sheet1.addCell(new Label(5, startRow + i,
								convertFormatHour(new Double(sumHalfDay)),
								borderNumber2));
						sheet1.addCell(new Label(6, startRow + i, "",
								borderNumber2));
						sumFullDay = 0.0;
						sumHalfDay = 0.0;
						flagTotalHour = "";
						i++;
					}
					// -----------------End Show sum
					// hours----------------------------//
				} else {
					flagTotalHour = "sum";
					seq = seq + 1;
					sheet1.addCell(new Label(0, startRow + i, String.valueOf(df
							.format(seq)), normalRightFormat));
					// Column EmpCode
					sheet1.addCell(new Label(1, startRow + i, rw.getEmpCode(),
							normalCenterFormat));

					// Column Ename
					sheet1.addCell(new Label(2, startRow + i, rw.getEname(),
							normalLeftFormat));

					sheet1.addCell(new Label(3, startRow + i,
							String.valueOf(dfYear.format(rw.getYearWork()))
									+ "/"
									+ String.valueOf(dfYear.format(rw
											.getPeriodWork())),
							normalCenterFormat));
					// Column Full Day
					if (rw.getFullDay() != null) {
						sumFullDay = sumFullDay + rw.getFullDay().doubleValue();
						sumAllFullDay = sumAllFullDay
								+ rw.getFullDay().doubleValue();

					}
					sheet1.addCell(new Label(4, startRow + i, String.valueOf(df
							.format(rw.getFullDay())), normalRightFormat));

					// Column Half Day
					if (rw.getHalfDay() != null) {
						sumHalfDay = sumHalfDay + rw.getHalfDay().doubleValue();
						sumAllHalfDay = sumAllHalfDay
								+ rw.getHalfDay().doubleValue();

					}
					sheet1.addCell(new Label(5, startRow + i, String.valueOf(df
							.format(rw.getHalfDay())), normalRightFormat));

					// Column FlagDesc
					sheet1.addCell(new Label(6, startRow + i, rw.getFlagDesc(),
							colFlag));
				}

				i++;
				m++;
			}
			i--;
			sheet1.mergeCells(0, startRow + i, 3, startRow + i);
			sheet1.addCell(new Label(0, startRow+i , "รวมทั้งหมด", borderNumber2));
			sheet1.addCell(new Label(4, startRow + i,
					convertFormatHour(new Double(sumAllFullDay)), borderNumber2));
			sheet1.addCell(new Label(5, startRow + i,
					convertFormatHour(new Double(sumAllHalfDay)), borderNumber2));
			sheet1.addCell(new Label(6, startRow + i, "", borderNumber2));

			sheet1.getSettings().setPassword("028313766");
			sheet1.getSettings().setProtected(true);

			// ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();

			wb.close();
			in.close();

			year = "";
			period = "";
			deptDesc = "";
			m = 0;

			return null;
		} else {
			ww.copySheet("Template", "ไม่มีข้อมูล" , ww.getNumberOfSheets());	
			sheet1 = ww.getSheet(1);

			sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""),
					cellOrgName));
			sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
			sheet1.addCell(new Label(3, 2, "ประจำงวด " + section + "  พ.ศ." + ChgNullToEmpty(numformat.format(yearPr),""),HeadFormat));
			sheet1.addCell(new Label(6, 2, "วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), headRight));
			sheet1.mergeCells(0,5,6,5);
			sheet1.addCell(new Label(0, 5, "ไม่มีข้อมูล",borderAll));

			sheet1.getSettings().setPassword("028313766");
			sheet1.getSettings().setProtected(true);
			ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}

	}

	/**
	 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝลง Time Format
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
