/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;

// import java.util.Iterator;
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
import jxl.format.UnderlineStyle;
import jxl.write.Label;

import jxl.write.Blank;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ss.tp.service.PeWgEmpScoreReportService;
import com.ss.tp.service.TaReportService;
import com.ss.tp.dto.PeWgEmpScoreReportVO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTPERP101_2Controller extends MultiActionController {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	public TaReportService getTaReportService() {

		return (TaReportService) this.getApplicationContext().getBean(
				"taReportService");
	}

	private PeWgEmpScoreReportService getPeWgEmpScoreReportService() {
		return (PeWgEmpScoreReportService) this.getApplicationContext()
				.getBean("peWgEmpScoreReportService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String codeSeq = "";
		String ouCode = "";
		String userId = "";
		int evaYear = 0;
		int evaTime = 0;
		String areaCodeTemp = "";
		String secCodeTemp = "";
		String workCodeTemp = "";

		String areaCode = "NullVal";
		String divDesc = ""; // Lv.3
		String secCode = "NullVal";
		String secDesc = ""; // Lv.4
		String workCode = "NullVal";
		String workDesc = ""; // Lv.5
		String formCode = "NullVal";
		String ename = "";
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("areaCodeTmp") != null) {
			areaCodeTemp = request.getParameter("areaCodeTmp");
		}
		if (request.getParameter("secCodeTmp") != null) {
			secCodeTemp = request.getParameter("secCodeTmp");
		}
		if (request.getParameter("workCodeTmp") != null) {
			workCodeTemp = request.getParameter("workCodeTmp");
		}
		if (request.getParameter("evaYear") != null
				&& !"".equals(request.getParameter("evaYear"))) {
			evaYear = Integer.parseInt(request.getParameter("evaYear"));
		}

		if (request.getParameter("evaTime") != null
				&& !"".equals(request.getParameter("evaTime"))) {
			evaTime = Integer.parseInt(request.getParameter("evaTime"));
		}

		String orgName = this.getPeWgEmpScoreReportService()
				.findOrgName(ouCode);
		String nameArea = this.getTaReportService().findAreaDescTwo(
				areaCodeTemp);
		List peWgEmpScoreList = this.getPeWgEmpScoreReportService()
				.findPeWgEmpScoreReport(ouCode, userId, evaYear, evaTime,
						areaCodeTemp, secCodeTemp, workCodeTemp);

		// ----------------- Generate Report Detail
		// -------------------------------
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTPERP101_2.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTPERP101_2.xls");
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

		WritableCellFormat borderLR = new WritableCellFormat();
		borderLR.setAlignment(Alignment.RIGHT);
		borderLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderLR.setBorder(Border.LEFT, BorderLineStyle.THIN);

		WritableCellFormat borderRight = new WritableCellFormat();
		borderRight.setAlignment(Alignment.CENTRE);
		borderRight.setBorder(Border.RIGHT, BorderLineStyle.THIN);

		WritableCellFormat borderAll = new WritableCellFormat();
		borderAll.setAlignment(Alignment.CENTRE);
		borderAll.setBorder(Border.ALL, BorderLineStyle.THIN);

		CellFormat cellOrgName = sheet1.getWritableCell(0, 0).getCellFormat();

		CellFormat cellTitleEnameF = sheet1.getWritableCell(0, 5)
				.getCellFormat();
		WritableCellFormat cellHeaderEname = new WritableCellFormat(
				cellTitleEnameF);
		cellHeaderEname.setBorder(Border.ALL, BorderLineStyle.THIN);

		WritableCellFormat headRight = new WritableCellFormat();
		WritableFont font3 = new WritableFont(WritableFont.ARIAL);
		font3.setBoldStyle(WritableFont.BOLD);
		font3.setPointSize(9);
		Alignment data = Alignment.getAlignment(1);
		headRight.setAlignment(data.RIGHT);
		headRight.setFont(font3);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		if (peWgEmpScoreList.size() > 0) {
			// ++++++++++Set Cell Format+++++++++
			CellFormat cellDivDescF = sheet1.getWritableCell(1, 2)
					.getCellFormat();
			CellFormat rowEvaYearF = sheet1.getWritableCell(0, 3)
					.getCellFormat();
			CellFormat colformDescF = sheet1.getWritableCell(0, 4)
					.getCellFormat();

			CellFormat colTitleCode = sheet1.getWritableCell(1, 6)
					.getCellFormat();

			CellFormat colEnameF = sheet1.getWritableCell(0, 7).getCellFormat();
			WritableCellFormat cellEname = new WritableCellFormat(colEnameF);
			cellEname.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			WritableCellFormat cellSecDesc = new WritableCellFormat(colEnameF);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL);
			font1.setBoldStyle(WritableFont.BOLD);
			font1.setPointSize(9);
			font1.setUnderlineStyle(UnderlineStyle.SINGLE);
			cellSecDesc.setFont(font1);
			// cellSecDesc.setBackground(Colour.GRAY_25);

			WritableCellFormat cellWorkDesc = new WritableCellFormat(colEnameF);
			WritableFont font2 = new WritableFont(WritableFont.ARIAL);
			font2.setBoldStyle(WritableFont.BOLD);
			font2.setPointSize(9);
			cellWorkDesc.setFont(font2);

			CellFormat colEvaScoreF = sheet1.getWritableCell(1, 7)
					.getCellFormat();
			WritableCellFormat cellEvaScore = new WritableCellFormat(
					colEvaScoreF);
			cellEvaScore.setBorder(Border.ALL, BorderLineStyle.THIN);

			PeWgEmpScoreReportVO vo = null;
			PeWgEmpScoreReportVO vo2 = null;

			int startRow = 7;
			int i = 0;
			int j = 0;
			int n = 0;
			int t = 0;
			int s = 1; // Sheet's name
			int totalRow = 0;

			// for (Iterator itt=peEmpScoreList.iterator();itt.hasNext();) {
			for (int m = 0; m < peWgEmpScoreList.size(); m++) {
				// vo = (PeEmpScoreReportVO)itt.next();
				vo = (PeWgEmpScoreReportVO) peWgEmpScoreList.get(m);
				List titleCodeList = vo.getTitleCodeGroup();
				List scoreList = vo.getScoreGroup();

				// áººï¿½ï¿½ï¿½ï¿½ï¿½Ô¹ (Separate Sheet by FormDesc)

				if (!formCode.equals(ChgNullToEmpty(vo.getFormCode(), ""))) {
					if (vo.getFormDesc() != null) {// ï¿½ï¿½ï¿½ï¿½Show Title
						// áººï¿½ï¿½ï¿½ï¿½ï¿½Ô³_AreaCode
						// ï¿½Ò¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ AreaCode
						// ï¿½ï¿½ï¿½Ô´ RecoverySheet
						ww.copySheet("Template",
								vo.getFormCode() + "_" + vo.getAreaCode(),
								ww.getNumberOfSheets());
					} else {
						ww.copySheet("Template","äÁèÃÐºØ" + s,ww.getNumberOfSheets());
						s++;
					}
					if (i > 0) {
						sheet1.getSettings().setPassword("123");
						sheet1.getSettings().setProtected(true);
					}
					i = 0;
					j++;
					sheet1 = ww.getSheet(j);
					sheet1.setRowView(startRow + i, 320);
					sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""),
							cellOrgName));
					sheet1.addCell(new Label(1, 2,"Ê¾./»¨.  "	+ ChgNullToEmpty(vo.getAreaDesc(),ChgNullToEmpty(vo.getDivDesc(), "")),cellDivDescF));
					sheet1.addCell(new Label(13, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+ sdfPrint.format(gd.getTime()), headRight));
					sheet1.addCell(new Label(0, 3, "»ÃÐ¨Ó»Õ " +  String.valueOf(vo.getEvaYear()), rowEvaYearF));
					sheet1.addCell(new Label(0, 4, String.valueOf(ChgNullToEmpty(vo.getFormDesc(), "")),colformDescF));
					formCode = ChgNullToEmpty(vo.getFormCode(), "");
					areaCode = ChgNullToEmpty(vo.getAreaCode(), "");

					sheet1.addCell(new Label(0, 5, "ª×èÍ¾¹Ñ¡§Ò¹", cellHeaderEname));

					sheet1.addCell(new Label(1, 5,  "ËÑÇ¢éÍ", cellHeaderEname));

					for (n = 0; n < 15; n++) {

						if (n < titleCodeList.size()) {
							sheet1.addCell(new Label(n + 1, 6, String
									.valueOf(titleCodeList.get(n)),
									cellHeaderEname));
						} else {
							sheet1.addCell(new Blank(n + 1, 6, cellHeaderEname));
						}
					}

					sheet1.addCell(new Label(16, 6,"ÃÇÁ", cellHeaderEname));

				} else {
					if (!areaCode.equals(ChgNullToEmpty(vo.getAreaCode(), ""))) {
						if (vo.getFormDesc() != null) {// ï¿½ï¿½ï¿½ï¿½Show Title
							// áººï¿½ï¿½ï¿½ï¿½ï¿½Ô³_AreaCode
							// ï¿½Ò¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ AreaCode
							// ï¿½ï¿½ï¿½Ô´ RecoverySheet
							ww.copySheet("Template", vo.getFormCode() + "_"
									+ vo.getAreaCode(), ww.getNumberOfSheets());
						} else {
							ww.copySheet("Template",  "äÁèÃÐºØ"  + s,
									ww.getNumberOfSheets());
							s++;
						}
						if (i > 0) {
							sheet1.getSettings().setPassword("123");
							sheet1.getSettings().setProtected(true);
						}
						i = 0;
						j++;
						sheet1 = ww.getSheet(j);
						sheet1.setRowView(startRow + i, 320);
						sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName,
								""), cellOrgName));
						sheet1.addCell(new Label(1, 2, "Ê¾./»¨.  " +  ChgNullToEmpty(vo.getAreaDesc(),ChgNullToEmpty(vo.getDivDesc(), "")),cellDivDescF));
						sheet1.addCell(new Label(13, 2,"ÇÑ¹·Õè¾ÔÁ¾ì : "+ sdfPrint.format(gd.getTime()), headRight));
						sheet1.addCell(new Label(0, 3, "»ÃÐ¨Ó»Õ " +String.valueOf(vo.getEvaYear()), rowEvaYearF));		
						sheet1.addCell(new Label(0, 4, String.valueOf(ChgNullToEmpty(vo.getFormDesc(), "")),colformDescF));
						formCode = ChgNullToEmpty(vo.getFormCode(), "");
						areaCode = ChgNullToEmpty(vo.getAreaCode(), "");

						sheet1.addCell(new Label(0, 5, "ª×èÍ¾¹Ñ¡§Ò¹", cellHeaderEname));

						sheet1.addCell(new Label(1, 5,  "ËÑÇ¢éÍ", cellHeaderEname));

						for (n = 0; n < 15; n++) {

							if (n < titleCodeList.size()) {
								sheet1.addCell(new Label(n + 1, 6, String
										.valueOf(titleCodeList.get(n)),cellHeaderEname));
							} else {
								sheet1.addCell(new Blank(n + 1, 6,cellHeaderEname));
							}
						}

						sheet1.addCell(new Label(16, 6,  "ÃÇÁ",  cellHeaderEname));

					}
				}

				// Lv.4
				if ((!secCode.equals(ChgNullToEmpty(vo.getSecCode(), "")))
						&& (!secDesc.equalsIgnoreCase(ChgNullToEmpty(
								vo.getSecDesc(), "")))) {
					if ((!ChgNullToEmpty(vo.getSecCode(), "").equals(""))
							|| (!ChgNullToEmpty(vo.getSecDesc(), "").equals(""))) {
						sheet1.setRowView(startRow + i, 320);
						sheet1.addCell(new Label(0, startRow + i, String
								.valueOf(ChgNullToEmpty(vo.getSecCode(), ""))
								+ " "
								+ String.valueOf(ChgNullToEmpty(
										vo.getSecDesc(), "")), cellSecDesc));
						secCode = ChgNullToEmpty(vo.getSecCode(), "");
						secDesc = ChgNullToEmpty(vo.getSecDesc(), "");

						for (n = 1; n < 17; n++) {
							sheet1.addCell(new Blank(n, startRow + i, borderAll));
						}
						i++;
					}
				}

				// Lv.5
				if ((!workCode.equals(ChgNullToEmpty(vo.getWorkCode(), "")))
						|| (!workDesc.equalsIgnoreCase(ChgNullToEmpty(
								vo.getWorkDesc(), "")))) {
					if ((!ChgNullToEmpty(vo.getWorkCode(), "").equals(""))
							|| (!ChgNullToEmpty(vo.getWorkDesc(), "")
									.equals(""))) {
						sheet1.setRowView(startRow + i, 320);
						sheet1.addCell(new Label(0, startRow + i, String
								.valueOf(ChgNullToEmpty(vo.getWorkCode(), ""))
								+ " "
								+ String.valueOf(ChgNullToEmpty(
										vo.getWorkDesc(), "")), cellWorkDesc));
						workCode = ChgNullToEmpty(vo.getWorkCode(), "");
						workDesc = ChgNullToEmpty(vo.getWorkDesc(), "");

						for (n = 1; n < 17; n++) {
							sheet1.addCell(new Blank(n, startRow + i, borderAll));
						}
						i++;
					}
				}

				sheet1.setRowView(startRow + i, 320);

				if ((m + 1) < peWgEmpScoreList.size()) {

					vo2 = (PeWgEmpScoreReportVO) peWgEmpScoreList.get(m + 1);

					if (!formCode.equals(ChgNullToEmpty(vo2.getFormCode(), ""))) {
						if (!ename.equalsIgnoreCase(ChgNullToEmpty(
								vo.getEname(), ""))) {
							if (!ChgNullToEmpty(vo.getEname(), "").equals("")) {
								sheet1.addCell(new Label(0, startRow + i, vo
										.getEmpCode() + " " + vo.getEname(),
										cellEname));
								ename = ChgNullToEmpty(vo.getEname(), "");

								for (n = 0; n < 16; n++) {
									if (n < scoreList.size()) {
										sheet1.addCell(new Label(n + 1,
												startRow + i, String
														.valueOf(scoreList
																.get(n)),
												cellEvaScore));
									} else {
										sheet1.addCell(new Blank(n + 1,
												startRow + i, borderAll));
									}
								}

								sheet1.addCell(new Label(16, startRow + i,
										String.valueOf(vo.getEvaTotal()),
										cellEvaScore));

								i++;
							}
						}
					} else {
						if (!areaCode.equals(ChgNullToEmpty(vo2.getAreaCode(),
								""))) {
							if (!ename.equalsIgnoreCase(ChgNullToEmpty(
									vo.getEname(), ""))) {
								if (!ChgNullToEmpty(vo.getEname(), "").equals(
										"")) {
									sheet1.addCell(new Label(0, startRow + i,
											vo.getEmpCode() + " "
													+ vo.getEname(), cellEname));
									ename = ChgNullToEmpty(vo.getEname(), "");

									for (n = 0; n < 16; n++) {
										if (n < scoreList.size()) {
											sheet1.addCell(new Label(n + 1,
													startRow + i, String
															.valueOf(scoreList
																	.get(n)),
													cellEvaScore));
										} else {
											sheet1.addCell(new Blank(n + 1,
													startRow + i, borderAll));
										}
									}

									sheet1.addCell(new Label(16, startRow + i,
											String.valueOf(vo.getEvaTotal()),
											cellEvaScore));

									i++;
								}
							}
						} else {
							if (!ename.equalsIgnoreCase(ChgNullToEmpty(
									vo.getEname(), ""))) {
								if (!ChgNullToEmpty(vo.getEname(), "").equals(
										"")) {
									sheet1.addCell(new Label(0, startRow + i,
											vo.getEmpCode() + " "
													+ vo.getEname(), colEnameF));
									ename = ChgNullToEmpty(vo.getEname(), "");

									for (n = 0; n < 16; n++) {
										if (n < scoreList.size()) {
											sheet1.addCell(new Label(n + 1,
													startRow + i, String
															.valueOf(scoreList
																	.get(n)),
													cellEvaScore));
										} else {
											sheet1.addCell(new Blank(n + 1,
													startRow + i, borderAll));
										}
									}

									sheet1.addCell(new Label(16, startRow + i,
											String.valueOf(vo.getEvaTotal()),
											cellEvaScore));

									i++;
								}
							}
						}
					}
				} else {

					if (!ename.equalsIgnoreCase(ChgNullToEmpty(vo.getEname(),
							""))) {
						if (!ChgNullToEmpty(vo.getEname(), "").equals("")) {
							sheet1.addCell(new Label(0, startRow + i, vo
									.getEmpCode() + " " + vo.getEname(),
									cellEname));
							ename = ChgNullToEmpty(vo.getEname(), "");

							for (n = 0; n < 16; n++) {
								if (n < scoreList.size()) {
									sheet1.addCell(new Label(n + 1, startRow
											+ i, String.valueOf(scoreList
											.get(n)), cellEvaScore));
								} else {
									sheet1.addCell(new Blank(n + 1, startRow
											+ i, borderAll));
								}
							}

							sheet1.addCell(new Label(16, startRow + i, String
									.valueOf(vo.getEvaTotal()), cellEvaScore));

							i++;
						}
					}
				}

			}

			sheet1.getSettings().setPassword("123");
			sheet1.getSettings().setProtected(true);

			if (ww.getNumberOfSheets() == 1) {
				ww.copySheet("Template", "äÁèÁÕ¢éÍÁÙÅ" , ww.getNumberOfSheets());
				sheet1 = ww.getSheet(1);
				sheet1.setRowView(7, 320);
				sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""),
						cellOrgName));
				sheet1.addCell(new Label(1, 2, "Ê¾./»¨.:"+nameArea,cellOrgName));
				sheet1.addCell(new Label(13, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
				sheet1.addCell(new Label(0, 3, "»ÃÐ¨Ó»Õ " + evaYear + "   ¤ÃÑé§·Õè " + evaTime, cellOrgName));
				
				sheet1.addCell(new Label(1, 5, "ËÑÇ¢éÍ", cellHeaderEname));
				
				sheet1.addCell(new Label(0, 5, "ª×èÍ¾¹Ñ¡§Ò¹", cellHeaderEname));

				for (n=1; n<16; n++)	{
					sheet1.addCell(new Blank(n, 6, cellHeaderEname));
				}
				
				sheet1.addCell(new Label(16, 6, "ÃÇÁ", cellHeaderEname));			
				
				sheet1.mergeCells(0,7, 16,7);
				sheet1.addCell(new Label(0, 7, "äÁèÁÕ¢éÍÁÙÅ", borderAll));	

				sheet1.getSettings().setPassword("123");
				sheet1.getSettings().setProtected(true);
			}
			ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();

			wb.close();
			in.close();

			formCode = "NullVal";
			areaCode = "NullVal";
			secCode = "NullVal";
			workCode = "NullVal";
			ename = "";
			return null;
		} else {
			ww.copySheet("Template", "äÁèÁÕ¢éÍÁÙÅ" , ww.getNumberOfSheets());	
			sheet1 = ww.getSheet(1);	
			sheet1.setRowView(7,320);
			sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""), cellOrgName));	
			sheet1.addCell(new Label(13, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
			sheet1.addCell(new Label(0, 3, "»ÃÐ¨Ó»Õ " + evaYear , cellOrgName));
			
			sheet1.addCell(new Label(1, 5, "ËÑÇ¢éÍ", cellHeaderEname));
			
			sheet1.addCell(new Label(0, 5, "ª×èÍ¾¹Ñ¡§Ò¹", cellHeaderEname));

			for (int n=1; n<16; n++)	{
				sheet1.addCell(new Blank(n, 6, cellHeaderEname));
			}
			
			sheet1.addCell(new Label(16, 6, "ÃÇÁ", cellHeaderEname));			
			
			sheet1.mergeCells(0,7, 16,7);
			sheet1.addCell(new Label(0, 7, "äÁèÁÕ¢éÍÁÙÅ", borderAll));	
			

			sheet1.getSettings().setPassword("123");
			sheet1.getSettings().setProtected(true);
			ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();
			wb.close();
			return null;
		}
	}
}
