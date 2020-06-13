/*
 * Created on 8 ๏ฟฝ.๏ฟฝ. 2549
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

import com.ss.tp.dto.RwIncDecOtherReportVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.FeeWpayPrIncomeDeductService;
import com.ss.tp.service.FeeWpayRwIncDecOtherService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYAPRP011Controller extends MultiActionController {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	private FeeWpayRwIncDecOtherService getFeeWpayRwIncDecOtherService() {
		return (FeeWpayRwIncDecOtherService) this.getApplicationContext().getBean(
				"feeWpayRwIncDecOtherService");
	}

	private FeeWpayPrIncomeDeductService getFeeWpayPrIncomeDeductService() {
		return (FeeWpayPrIncomeDeductService) this.getApplicationContext().getBean(
				"feeWpayPrIncomeDeductService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");

		String ouCode = "";
		String userId = "";
		int year = 0;
		int period = 0;
		String flag = "";
		String section = "";
		String incDecCode = "";
		String incDecName = "";
		String approveFlag="";
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
		if (request.getParameter("incDecTmp") != null) {
			incDecCode = request.getParameter("incDecTmp");
			incDecName = this.getFeeWpayPrIncomeDeductService().getIncDecName(ouCode,
					2, incDecCode);
		}
		
		if (request.getParameter("approveFlag") != null) {
			approveFlag = request.getParameter("approveFlag");
		}
		// if (request.getParameter("incDecCode") != null) {
		// incDecCode = request.getParameter("incDecCode");
		// }
		//
		// if (request.getParameter("incDecName") != null) {
		// incDecName = request.getParameter("incDecName");
		// }
		//
		List empList = this.getFeeWpayRwIncDecOtherService().decOtherReport(userId,
				ouCode, year, period, incDecCode, flag,approveFlag);
		List sheetList = this.getFeeWpayRwIncDecOtherService()
				.decOtherReportCountSheet(userId, ouCode, year, period,
						incDecCode, flag,approveFlag);
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYRP011.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYRP011.xls");

		// ///////////////////// Set Format //////////////////////////
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

		WritableCellFormat HeadLeftFormat = new WritableCellFormat();
		HeadLeftFormat.setAlignment(dataAlignLeft);
		HeadLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		HeadLeftFormat.setFont(fontBold);

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

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setVerticalAlignment(VerticalAlignment.CENTRE);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(fontBold);
		// /////////////////////////////////////////////////////////////

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);

		List sheetNameList = sheetList;
		int countSheetName = sheetNameList.size();
		int countIncDecCode = sheetNameList.size();
		String tempInDecCode = "";
		String tempInDecName = "";
		WritableSheet sheet[];

		RwIncDecOtherReportVO voSheet = null;
		String nameSheet = "";
		if (countIncDecCode == 0) {// for build Sheet
			sheet = new WritableSheet[1];
		} else {
			sheet = new WritableSheet[countIncDecCode];
		}

		if (countSheetName > 0) {// for Sheet Name
			for (int i = 0; i < countSheetName - 1; i++) {
				voSheet = (RwIncDecOtherReportVO) sheetNameList.get(i + 1);
				ww.copySheet("Sheet1", voSheet.getIncDecName(),
						ww.getNumberOfSheets());

			}
		}
		if (sheetList.size() > 0) {
			voSheet = (RwIncDecOtherReportVO) sheetList.get(0);
			tempInDecCode = voSheet.getIncDecCode();
			tempInDecName = voSheet.getIncDecName();
		}
		sheet[0] = ww.getSheet(0);
		if (countSheetName == 0) {
			sheet[0].setName("ไม่พบข้อมูล");
		} else {
			sheet[0].setName(tempInDecName);
		}

		CellFormat headRight = sheet[0].getWritableCell(0 + 1, 2)
				.getCellFormat();
		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");

		sheet[0].getSettings().setPassword("028313766");
		sheet[0].getSettings().setProtected(true);

		sheet[0].addCell(new Label(0 + 1, 0, ouDesc, HeadFormat));
		sheet[0].addCell(new Label(0, 1,"โปรแกรม CTWPAYRP011                                                                                    รายงาน"+(tempInDecName.equals("")?incDecName.equals("")?"ประเภทรายหัก":incDecName:tempInDecName),HeadLeftFormat));
		sheet[0].addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), HeadLeftFormat));
		sheet[0].addCell(new Label(3, 2,"ประจำงวด "+section+" พ.ศ. "+year+"                                                   วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
		int j=0;
		if (empList.size() > 0) {

			int startRow = 4;
			String orgDesc = "";
			String flagDesc = "";
			int row = startRow;
			int count = 0;
			int countPerSheet = 1;
			double sumTotalAmt = 0.0;
			double sumAllTotalAmt = 0.0;
			String flagTotal = "";
			for (Iterator itt = empList.iterator(); itt.hasNext();) {
				RwIncDecOtherReportVO vo = (RwIncDecOtherReportVO) itt.next();
				count = count + 1;
				if (!tempInDecCode.equals(vo.getIncDecCode())) {
					if (!tempInDecCode.equals(vo.getIncDecCode())) {
						if (!flagTotal.equals("")) {
							sheet[j].mergeCells(0, row, 1 + 2, row);
							sheet[j].addCell(new Label(0, row, "รวม", borderNumber2));
							sheet[j].addCell(new Label(2 + 2, row,
									convertFormatHour(new Double(sumTotalAmt)),
									borderNumber2));
							sheet[j].addCell(new Label(3 + 2, row, "",
									borderNumber2));
							sheet[j].addCell(new Label(4 + 2, row, "",
									borderNumber2));
							sheet[j].addCell(new Label(5 + 2, row, "",
									borderNumber2));
							sumAllTotalAmt = sumAllTotalAmt + sumTotalAmt;
							sumTotalAmt = 0.0;
							flagTotal = "";
							row++;

							sheet[j].mergeCells(0, row, 1 + 2, row);
							sheet[j].addCell(new Label(0, row, "รวมทั้งหมด", borderNumber2));
							sheet[j].addCell(new Label(
									2 + 2,
									row,
									convertFormatHour(new Double(sumAllTotalAmt)),
									borderNumber2));
							sheet[j].addCell(new Label(3 + 2, row, "",
									borderNumber2));
							sheet[j].addCell(new Label(4 + 2, row, "",
									borderNumber2));
							sheet[j].addCell(new Label(5 + 2, row, "",
									borderNumber2));
							sumAllTotalAmt = 0.0;
							row++;
						}
						tempInDecCode = vo.getIncDecCode();
						tempInDecName = vo.getIncDecName();
						if (j > 0) {
							for (int y = 0; y < 6 + 2; y++) {
								sheet[j].setRowView(startRow, 320);
								sheet[j].addCell(new Label(y, row, "",
										borderEndLine));
							}
						}

						j++;
						row = startRow;
						countPerSheet = 1;
						sheet[j] = ww.getSheet(j);
						sheet[j].setRowView(row, 320);
						sheet[j].addCell(new Label(0 + 1, 0, ouDesc, HeadFormat));
						sheet[j].addCell(new Label(0,1,"โปรแกรม CTWPAYRP011                                                                         รายงาน"+tempInDecName,HeadLeftFormat));
						sheet[j].addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), HeadLeftFormat));
						sheet[j].addCell(new Label(3, 2,"ประจำงวด "+section+" พ.ศ. "+year+"                                                   วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
						//sheet[j].addCell(new Label(2,2,"ประจำงวด "+section+" พ.ศ."+year,HeadFormat));
						// sheet[j].addCell(new
						// Label(2,2,"๏ฟฝ๏ฟฝะจำงวด "+section+"
						// ๏ฟฝ.๏ฟฝ."+year,HeadFormat));
						sheet[j].getSettings().setPassword("028313766");
						sheet[j].getSettings().setProtected(true);
						orgDesc = "";
					}
				}
				if (!orgDesc.equals(vo.getOrgDesc())
						&& !"".equals(ChgNullToEmpty(vo.getOrgDesc().trim(), ""))) {
					if (!flagTotal.equals("")) {
						sheet[j].mergeCells(0, row, 1 + 2, row);
						sheet[j].addCell(new Label(0, row, "รวม", borderNumber2));
						sheet[j].addCell(new Label(2 + 2, row,
								convertFormatHour(new Double(sumTotalAmt)),
								borderNumber2));
						sheet[j].addCell(new Label(3 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(4 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(5 + 2, row, "",
								borderNumber2));
						sumAllTotalAmt = sumAllTotalAmt + sumTotalAmt;
						sumTotalAmt = 0.0;
						flagTotal = "";
						row++;
					}
					sheet[j].setRowView(row, 320);
					sheet[j].addCell(new Blank(0, row, boldLeftFormat));
					sheet[j].addCell(new Label(0 + 1, row, ChgNullToEmpty(
							vo.getOrgDesc(), ""), boldLeftFormat));
					sheet[j].addCell(new Blank(1 + 1, row, boldLeftFormat));
					sheet[j].addCell(new Blank(2 + 1, row, boldLeftFormat));
					sheet[j].addCell(new Blank(3 + 1, row, boldLeftFormat));
					sheet[j].addCell(new Blank(4 + 1, row, boldLeftFormat));
					sheet[j].addCell(new Blank(5 + 1, row, boldLeftFormat));

					sheet[j].addCell(new Blank(6 + 1, row, boldLeftFormat));
					orgDesc = ChgNullToEmpty(vo.getOrgDesc(), "");
					row++;
				}

				if (empList.size() == count) {
					sheet[j].setRowView(row, 320);
					sheet[j].addCell(new Label(0, row, String
							.valueOf(countPerSheet), normalRightFormat));
					sheet[j].addCell(new Label(0 + 1, row, vo.getEmpCode(),
							normalCenterLastFormat));
					sheet[j].addCell(new Label(1 + 1, row, vo.getEmpName(),
							normalLeftLastFormat));
					sheet[j].addCell(new Label(2 + 1, row, vo.getYearPeriod(),
							normalCenterFormat));
					sheet[j].addCell(new Label(
							2 + 2,
							row,
							vo.getTotalAmt() == null ? String.valueOf(0.0)
									: String.valueOf(df.format(vo.getTotalAmt())),
							normalRightFormat));
					flagTotal = "sum";
					if (vo.getTotalAmt() != null) {
						sumTotalAmt += vo.getTotalAmt().doubleValue();

					}
					flagDesc = "";
					if (vo.getFlagPr() != null){
						flagDesc = "ตกเบิกเรียกคืน";
						if (vo.getFlagPr().equals("N")){ flagDesc = "";}
						if (vo.getFlagPr().equals("A")){ flagDesc = "ปรับปรุง";}
						if (vo.getFlagPr().equals("R")){ flagDesc = "เรียกคืน";}
						if (vo.getFlagPr().equals("B")){ flagDesc = "ตกเบิกปรับปรุง";}
					}
					sheet[j].addCell(new Label(3 + 2, row, flagDesc,
							normalLeftLastFormat));

					if (vo.getStDate() != null) {
						sheet[j].addCell(new Label(4 + 2, row,
								convertDateFormat(vo.getStDate()),
								normalCenterFormat));
					} else {
						sheet[j].addCell(new Label(4 + 2, row, "",
								normalLeftFormat));
					}

					if (vo.getEndDate() != null) {
						sheet[j].addCell(new Label(5 + 2, row,
								convertDateFormat(vo.getEndDate()),
								normalCenterFormat));
					} else {
						sheet[j].addCell(new Label(5 + 2, row, "",
								normalLeftFormat));
					}

					row++;
					countPerSheet++;
					if (!flagTotal.equals("")) {
						sheet[j].mergeCells(0, row, 1 + 2, row);
						sheet[j].addCell(new Label(0, row, "รวม", borderNumber2));
						sheet[j].addCell(new Label(2 + 2, row,
								convertFormatHour(new Double(sumTotalAmt)),
								borderNumber2));
						sheet[j].addCell(new Label(3 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(4 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(5 + 2, row, "",
								borderNumber2));
						sumAllTotalAmt = sumAllTotalAmt + sumTotalAmt;
						sumTotalAmt = 0.0;
						flagTotal = "";

						row++;

						sheet[j].mergeCells(0, row, 1 + 2, row);
						sheet[j].addCell(new Label(0, row, "รวมทั้งหมด", borderNumber2));
						sheet[j].addCell(new Label(2 + 2, row,
								convertFormatHour(new Double(sumAllTotalAmt)),
								borderNumber2));
						sheet[j].addCell(new Label(3 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(4 + 2, row, "",
								borderNumber2));
						sheet[j].addCell(new Label(5 + 2, row, "",
								borderNumber2));

						sumAllTotalAmt = 0.0;

					}
				} else {
					sheet[j].setRowView(row, 320);
					sheet[j].addCell(new Label(0, row, String
							.valueOf(countPerSheet), normalRightFormat));
					sheet[j].addCell(new Label(0 + 1, row, vo.getEmpCode(),
							normalCenterFormat));
					sheet[j].addCell(new Label(1 + 1, row, vo.getEmpName(),
							normalLeftFormat));
					sheet[j].addCell(new Label(2 + 1, row, vo.getYearPeriod(),
							normalCenterFormat));
					sheet[j].addCell(new Label(
							2 + 2,
							row,
							vo.getTotalAmt() == null ? String.valueOf(0.0)
									: String.valueOf(df.format(vo.getTotalAmt())),
							normalRightFormat));
					flagTotal = "sum";
					if (vo.getTotalAmt() != null) {
						sumTotalAmt += vo.getTotalAmt().doubleValue();

					}
					flagDesc = "";
					if (vo.getFlagPr() != null){
						flagDesc = "ตกเบิกเรียกคืน";
						if (vo.getFlagPr().equals("N")){ flagDesc = "";}
						if (vo.getFlagPr().equals("A")){ flagDesc = "ปรับปรุง";}
						if (vo.getFlagPr().equals("R")){ flagDesc = "เรียกคืน";}
						if (vo.getFlagPr().equals("B")){ flagDesc = "ตกเบิกปรับปรุง";}
					}
					sheet[j].addCell(new Label(3 + 2, row, flagDesc,
							normalLeftFormat));

					if (vo.getStDate() != null) {
						sheet[j].addCell(new Label(4 + 2, row,
								convertDateFormat(vo.getStDate()),
								normalCenterFormat));
					} else {
						sheet[j].addCell(new Label(4 + 2, row, "",
								normalLeftFormat));
					}

					if (vo.getEndDate() != null) {
						sheet[j].addCell(new Label(5 + 2, row,
								convertDateFormat(vo.getEndDate()),
								normalCenterFormat));
					} else {
						sheet[j].addCell(new Label(5 + 2, row, "",
								normalLeftFormat));
					}
				}
				row++;
				countPerSheet++;
				if (j == 0 || (j + 1) == empList.size()) {
					for (int y = 0; y < 6 + 2; y++) {
						sheet[j].setRowView(row, 320);
						sheet[j].addCell(new Label(y, row, "", borderEndLine));
					}
				}
			}

			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		} else {
			sheet[0].setRowView(4, 320);
			sheet[0].mergeCells(0, 4, 5 + 2, 4);
			sheet[0].addCell(new Label(0 + 1, 0, ouDesc, HeadFormat));
			sheet[0].addCell(new Label(0, 1,"โปรแกรม CTWPAYRP011                                                                                    รายงาน"+(tempInDecName.equals("")?incDecName.equals("")?"ประเภทรายหัก":incDecName:tempInDecName),HeadLeftFormat));
			sheet[0].addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), HeadLeftFormat));
			sheet[0].addCell(new Label(3, 2,"ประจำงวด "+section+" พ.ศ. "+year+"                                                   วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
			sheet[0].addCell(new Label(0, 4, "ไม่พบข้อมูล",normalCenterLastFormat));	
			sheet[0].getSettings().setPassword("123");
			sheet[0].getSettings().setPassword("123");
			sheet[0].getSettings().setProtected(true);
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}
	}

	/**
	 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝลง Total Format
	 * 
	 * @param hour
	 * @return
	 */
	private String convertFormatHour(Double total) {
		String rlst = "";
		// DecimalFormat dec = new DecimalFormat("###,##0");
		DecimalFormat dec = new DecimalFormat("###,##0.00");
		rlst = dec.format(total != null ? total.doubleValue() : 0.0);
		return rlst;
	}

	public String convertDateFormat(Date date) {
		String rlst = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale(
					"th", "TH"));
			rlst = sdf.format(date);
		}
		return rlst;
	}

}
