/* BALL
 * Created on 31 ?.?. 2549
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

import com.ss.tp.dto.RwOvertimeVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.RwOvertimeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author BALL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP002Controller extends MultiActionController {

	/**
	 * @return Returns the taReportService.
	 */
	public RwOvertimeService getRwOvertimeService() {
		return (RwOvertimeService) this.getApplicationContext().getBean(
				"rwOvertimeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝสด๏ฟฝ๏ฟฝ๏ฟฝยงาน๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝยน๏ฟฝลง๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝัก๏ฟฝอง๏ฟฝูก๏ฟฝ๏ฟฝาง๏ฟฝ๏ฟฝะจ๏ฟฝ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = "";
		String userId = "";
		int year = 0;
		int period = 0;
		String periodName = "";
		String otType = "";
		String otStatus = "";

		// int countRow = 1;

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
			period = (int) Double.parseDouble(request.getParameter("period"));
		}
		if (request.getParameter("section") != null) {
			periodName = request.getParameter("section");
		}
		if (request.getParameter("otType") != null) {
			otType = request.getParameter("otType");
		}
		if (request.getParameter("otStatus") != null) {
			otStatus = request.getParameter("otStatus");
		}

		System.out.println("OTTYPE=" + otType);
		System.out.println("OTSTATUS=" + otStatus);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List rwOvertimeList = this.getRwOvertimeService().findRwOvertimeReport(
				ouCode, userId, year, period, otType, otStatus);
		System.out.println("list size=" + rwOvertimeList.size());

		// ---------------------------------Generate Report
		// Detail--------------------------------------------------

		WritableCellFormat headL4 = new WritableCellFormat();
		headL4.setBorder(Border.ALL, BorderLineStyle.THIN);
		WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		font2.setBoldStyle(WritableFont.BOLD);
		font2.setPointSize(9);

		Alignment data2 = Alignment.getAlignment(1);
		headL4.setAlignment(data2.LEFT);
		headL4.setFont(font2);

		RwOvertimeVO otvo = null;
		int startRow = 5;
		int i = 0;
		int j = 0;
		int s = 1; // Sheet's name
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		String otTypeTemp = "";
		otType = "";

		String refNoTemp = "";
		String refNo = "";

		String divCodeTemp = "";// LV3
		String divCode = "";
		String secCodeTemp = "";// LV4
		String secCode = "";
		List otList = rwOvertimeList;

		RwOvertimeVO vo = null;
		RwOvertimeVO vo2 = null;

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTRWRP002.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTRWRP002.xls");
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		// SheetSettings sst = new SheetSettings();
		// sst = wb.getSheet(0).getSettings();

		// ++++++++++Set Cell Format++++++++++
		CellFormat header = sheet1.getWritableCell(0, 0).getCellFormat();
		CellFormat header2 = sheet1.getWritableCell(0, 1).getCellFormat();
		CellFormat header2Center = sheet1.getWritableCell(2, 1).getCellFormat();
		CellFormat headLeft = sheet1.getWritableCell(0, 2).getCellFormat();
		CellFormat header3 = sheet1.getWritableCell(3, 2).getCellFormat();
		CellFormat frHour = sheet1.getWritableCell(0, 7).getCellFormat();
		CellFormat normal = sheet1.getWritableCell(11, 6).getCellFormat();
		CellFormat normalLeft = sheet1.getWritableCell(11, 7).getCellFormat();
		CellFormat endLeft = sheet1.getWritableCell(1, 7).getCellFormat();
		CellFormat end = sheet1.getWritableCell(5, 7).getCellFormat();
		CellFormat tbcell = sheet1.getWritableCell(6, 7).getCellFormat();
		CellFormat normalTemp = sheet1.getWritableCell(14, 7).getCellFormat();

		WritableCellFormat subHead = new WritableCellFormat();
		subHead.setBorder(Border.LEFT, BorderLineStyle.THIN);
		subHead.setAlignment(Alignment.LEFT);
		subHead.setFont(font2);
		// ------------------//
		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(font2);

		WritableCellFormat borderNumber = new WritableCellFormat();
		borderNumber.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber.setAlignment(Alignment.RIGHT);
		borderNumber.setFont(font2);
		// ------------------//
		WritableCellFormat borderEnd = new WritableCellFormat();
		borderEnd.setBorder(Border.TOP, BorderLineStyle.THIN);

		WritableCellFormat noData = new WritableCellFormat();
		noData.setBorder(Border.ALL, BorderLineStyle.THIN);
		noData.setAlignment(Alignment.CENTRE);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		if (otList.size() > 0) {
			try {
				otTypeTemp = "";
				refNoTemp = "";
				divCodeTemp = "";
				secCodeTemp = "";
				String headDesc = "";
				int countRow = 1;
				double sumTotDay15 = 0.0;
				double sumTotDay1 = 0.0;
				double sumTotDay3 = 0.0;
				double sumTotAmt15 = 0.0;
				double sumTotAmt1 = 0.0;
				double sumTotAmt3 = 0.0;
				double sumAllTotDay15 = 0.0;
				double sumAllTotDay1 = 0.0;
				double sumAllTotDay3 = 0.0;
				double sumAllTotAmt15 = 0.0;
				double sumAllTotAmt1 = 0.0;
				double sumAllTotAmt3 = 0.0;
				String flagTotalHour = "";
				String refDesc = "";
				sheet1.addCell(new Label(14, 4, "", tbcell));
				for (int k = 0; k < otList.size(); k++) {
					vo = (RwOvertimeVO) otList.get(k);
					if (k + 1 < otList.size())
						vo2 = (RwOvertimeVO) otList.get(k + 1);

					System.out.println(vo.getRefNo() + ", " + vo.getOtType());
					// if (((!refNoTemp.equalsIgnoreCase(vo.getRefNo())) ||
					// (!otTypeTemp.equalsIgnoreCase(vo.getOtType()))) &&
					// vo.getRefNo() != null && vo.getOtType() != null){
					if (!otTypeTemp.equalsIgnoreCase(vo.getOtType())
							&& vo.getOtType() != null) {
						// --------------------Start Show sum hour--
						// bybow-----------------------------//
						if (!flagTotalHour.equals("")) {
							sheet1.mergeCells(0, startRow + i, 7, startRow + i);
							sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
							sheet1.addCell(new Label(8, startRow + i,
									convertFormatHour(new Double(sumTotDay15)),
									borderNumber));
							sheet1.addCell(new Label(9, startRow + i,
									convertFormatHour(new Double(sumTotDay1)),
									borderNumber));
							sheet1.addCell(new Label(10, startRow + i,
									convertFormatHour(new Double(sumTotDay3)),
									borderNumber));
							sheet1.addCell(new Label(11, startRow + i,
									convertFormatHour(new Double(sumTotAmt15)),
									borderNumber));
							sheet1.addCell(new Label(12, startRow + i,
									convertFormatHour(new Double(sumTotAmt1)),
									borderNumber));
							sheet1.addCell(new Label(13, startRow + i,
									convertFormatHour(new Double(sumTotAmt3)),
									borderNumber));
							sheet1.addCell(new Label(14, startRow + i, "",
									borderNumber2));

							sumAllTotDay1 = sumAllTotDay1 + sumTotDay1;
							sumAllTotDay15 = sumAllTotDay15 + sumTotDay15;
							sumAllTotDay3 = sumAllTotDay3 + sumTotDay3;
							sumAllTotAmt1 = sumAllTotAmt1 + sumTotAmt1;
							sumAllTotAmt15 = sumAllTotAmt15 + sumTotAmt15;
							sumAllTotAmt3 = sumAllTotAmt3 + sumTotAmt3;

							i++;

							sheet1.mergeCells(0, startRow + i, 7, startRow + i);
							sheet1.addCell(new Label(0, startRow+i , "รวมทั้งหมด", borderNumber2));
							sheet1.addCell(new Label(
									8,
									startRow + i,
									convertFormatHour(new Double(sumAllTotDay15)),
									borderNumber));
							sheet1.addCell(new Label(
									9,
									startRow + i,
									convertFormatHour(new Double(sumAllTotDay1)),
									borderNumber));
							sheet1.addCell(new Label(
									10,
									startRow + i,
									convertFormatHour(new Double(sumAllTotDay3)),
									borderNumber));
							sheet1.addCell(new Label(
									11,
									startRow + i,
									convertFormatHour(new Double(sumAllTotAmt15)),
									borderNumber));
							sheet1.addCell(new Label(
									12,
									startRow + i,
									convertFormatHour(new Double(sumAllTotAmt1)),
									borderNumber));
							sheet1.addCell(new Label(
									13,
									startRow + i,
									convertFormatHour(new Double(sumAllTotAmt3)),
									borderNumber));
							sheet1.addCell(new Label(14, startRow + i, "",
									borderNumber2));

							sumTotDay15 = 0.0;
							sumTotDay1 = 0.0;
							sumTotDay3 = 0.0;
							sumTotAmt15 = 0.0;
							sumTotAmt1 = 0.0;
							sumTotAmt3 = 0.0;
							sumAllTotDay15 = 0.0;
							sumAllTotDay1 = 0.0;
							sumAllTotDay3 = 0.0;
							sumAllTotAmt15 = 0.0;
							sumAllTotAmt1 = 0.0;
							sumAllTotAmt3 = 0.0;
							flagTotalHour = "";
							i++;
						}
						// --------------------End Show sum
						// hour-------------------------------//
						ww.copySheet("Sheet1", "แผ่นงาน"+s, ww.getNumberOfSheets());
						s++;
						i = 0;
						j++;
						if (j > 0) {
							sheet1.getSettings().setPassword("028313766");
							sheet1.getSettings().setProtected(true);
						}
						sheet1 = ww.getSheet(j);
						String otDesc = "";
						countRow = 1;
						System.out.println("OtType ==>" + vo.getOtType() + "  "
								+ vo.getRefNo());
						if(vo.getOtType() != null){
							if(vo.getOtType().equalsIgnoreCase("1") || vo.getOtType() == "1"){
								//otDesc = "โปรแกรม CTRWRP002                                                                                                                       รายงานข้อมูลค่าล่วงเวลา";
								sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002",header2));
								sheet1.addCell(new Label(2, 1,"รายงานข้อมูลค่าล่วงเวลา",header2Center));
								
							}
							else if(vo.getOtType().equalsIgnoreCase("2") || vo.getOtType() == "2"){
								//otDesc = "โปรแกรม CTRWRP002                                                                                                           รายงานข้อมูลค่าทำงานในวันหยุดพักผ่อน";
								sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002",header2));
								sheet1.addCell(new Label(2, 1,"รายงานข้อมูลค่าทำงานในวันหยุดพักผ่อน",header2Center));
							}
						}

						headDesc = ChgNullToEmpty(vo.getDivCode(), "") + "   "
								+ ChgNullToEmpty(vo.getDivDesc(), "");
						if (vo.getSecCode() != null)
							headDesc = ChgNullToEmpty(vo.getSecCode(), "")
									+ "   "
									+ ChgNullToEmpty(vo.getDivDesc(), "")
									+ ChgNullToEmpty(vo.getSecDesc(), "");
						// refDesc = "";
						// if(vo.getRefNo() != null){
						// refDesc = "๏ฟฝำดับ๏ฟฝ๏ฟฝ๏ฟฝ ๏ฟฝ.1 "+vo.getRefNo();
						// }
						// sheet1.addCell(new Blank(5, 5, tbcell));
						sheet1.addCell(new Label(0, 0, ChgNullToEmpty(ouDesc,
								""), header));
						// sheet1.addCell(new Label(0,
						// 1,ChgNullToEmpty(otDesc,""),header2));
						sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
						sheet1.addCell(new Label(3, 2,"ประจำงวดที่ "+ periodName + " พ.ศ." + year+"                                                                                                         วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),header3));
//						sheet1.addCell(new Label(0, 3,refDesc,header));
//						refNoTemp = ChgNullToEmpty(vo.getRefNo(),"");
						otTypeTemp = ChgNullToEmpty(vo.getOtType(), "");
						secCodeTemp = "";
						divCodeTemp = "";
						flagTotalHour = "";
					}

					if (!refNoTemp.equalsIgnoreCase(vo.getRefNo())
							&& vo.getRefNo() != null) {
						if (!flagTotalHour.equals("")) {
							sheet1.mergeCells(0, startRow + i, 7, startRow + i);
							sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
							sheet1.addCell(new Label(8, startRow + i,
									convertFormatHour(new Double(sumTotDay15)),
									borderNumber));
							sheet1.addCell(new Label(9, startRow + i,
									convertFormatHour(new Double(sumTotDay1)),
									borderNumber));
							sheet1.addCell(new Label(10, startRow + i,
									convertFormatHour(new Double(sumTotDay3)),
									borderNumber));
							sheet1.addCell(new Label(11, startRow + i,
									convertFormatHour(new Double(sumTotAmt15)),
									borderNumber));
							sheet1.addCell(new Label(12, startRow + i,
									convertFormatHour(new Double(sumTotAmt1)),
									borderNumber));
							sheet1.addCell(new Label(13, startRow + i,
									convertFormatHour(new Double(sumTotAmt3)),
									borderNumber));
							sheet1.addCell(new Label(14, startRow + i, "",
									borderNumber2));

							sumAllTotDay1 = sumAllTotDay1 + sumTotDay1;
							sumAllTotDay15 = sumAllTotDay15 + sumTotDay15;
							sumAllTotDay3 = sumAllTotDay3 + sumTotDay3;
							sumAllTotAmt1 = sumAllTotAmt1 + sumTotAmt1;
							sumAllTotAmt15 = sumAllTotAmt15 + sumTotAmt15;
							sumAllTotAmt3 = sumAllTotAmt3 + sumTotAmt3;

							sumTotDay15 = 0.0;
							sumTotDay1 = 0.0;
							sumTotDay3 = 0.0;
							sumTotAmt15 = 0.0;
							sumTotAmt1 = 0.0;
							sumTotAmt3 = 0.0;
							flagTotalHour = "";
							secCodeTemp = "";
							divCodeTemp = "";
							i++;
						}
						refDesc = "";
						if (vo.getRefNo() != null
								&& !vo.getRefNo().equals(" - ")) {
							refDesc = "ลำดับที่ ล.1 "+vo.getRefNo();
							sheet1.addCell(new Label(0, startRow + i, refDesc,
									subHead));
							sheet1.addCell(new Blank(1, startRow + i, normal));
							sheet1.addCell(new Blank(2, startRow + i, normal));
							sheet1.addCell(new Blank(3, startRow + i, normal));
							sheet1.addCell(new Blank(4, startRow + i, normal));
							sheet1.addCell(new Blank(5, startRow + i, normal));
							sheet1.addCell(new Blank(6, startRow + i, normal));
							sheet1.addCell(new Blank(7, startRow + i, normal));
							sheet1.addCell(new Blank(8, startRow + i, normal));
							sheet1.addCell(new Blank(9, startRow + i, normal));
							sheet1.addCell(new Blank(10, startRow + i, normal));
							sheet1.addCell(new Blank(11, startRow + i, normal));
							sheet1.addCell(new Blank(12, startRow + i, normal));
							sheet1.addCell(new Blank(13, startRow + i, normal));
							sheet1.addCell(new Blank(14, startRow + i, endLeft));
							i++;
						}
						refNoTemp = ChgNullToEmpty(vo.getRefNo(), "");

					}
					if ((!divCodeTemp.equalsIgnoreCase(ChgNullToEmpty(
							vo.getDivCode(), "")))
							|| (!secCodeTemp.equalsIgnoreCase(ChgNullToEmpty(
									vo.getSecCode(), "")))) {
						// -----------------Start Show sum
						// hours----bybow------------------------//
						if (!flagTotalHour.equals("")) {
							sheet1.mergeCells(0, startRow + i, 7, startRow + i);
							sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
							sheet1.addCell(new Label(8, startRow + i,
									convertFormatHour(new Double(sumTotDay15)),
									borderNumber));
							sheet1.addCell(new Label(9, startRow + i,
									convertFormatHour(new Double(sumTotDay1)),
									borderNumber));
							sheet1.addCell(new Label(10, startRow + i,
									convertFormatHour(new Double(sumTotDay3)),
									borderNumber));
							sheet1.addCell(new Label(11, startRow + i,
									convertFormatHour(new Double(sumTotAmt15)),
									borderNumber));
							sheet1.addCell(new Label(12, startRow + i,
									convertFormatHour(new Double(sumTotAmt1)),
									borderNumber));
							sheet1.addCell(new Label(13, startRow + i,
									convertFormatHour(new Double(sumTotAmt3)),
									borderNumber));
							sheet1.addCell(new Label(14, startRow + i, "",
									borderNumber2));

							sumAllTotDay1 = sumAllTotDay1 + sumTotDay1;
							sumAllTotDay15 = sumAllTotDay15 + sumTotDay15;
							sumAllTotDay3 = sumAllTotDay3 + sumTotDay3;
							sumAllTotAmt1 = sumAllTotAmt1 + sumTotAmt1;
							sumAllTotAmt15 = sumAllTotAmt15 + sumTotAmt15;
							sumAllTotAmt3 = sumAllTotAmt3 + sumTotAmt3;

							sumTotDay15 = 0.0;
							sumTotDay1 = 0.0;
							sumTotDay3 = 0.0;
							sumTotAmt15 = 0.0;
							sumTotAmt1 = 0.0;
							sumTotAmt3 = 0.0;
							flagTotalHour = "";
							i++;
						}
						// -----------------End Show sum
						// hours----------------------------//
						headDesc = ChgNullToEmpty(vo.getDivCode(), "") + "   "
								+ ChgNullToEmpty(vo.getDivDesc(), "");
						if (vo.getSecCode() != null) {
							headDesc = ChgNullToEmpty(vo.getSecCode(), "")
									+ " " + ChgNullToEmpty(vo.getDivDesc(), "")
									+ " " + ChgNullToEmpty(vo.getSecDesc(), "");
						} else {
						}
						sheet1.addCell(new Label(0, startRow + i, headDesc,
								subHead));
						sheet1.addCell(new Blank(1, startRow + i, normal));
						sheet1.addCell(new Blank(2, startRow + i, normal));
						sheet1.addCell(new Blank(3, startRow + i, normal));
						sheet1.addCell(new Blank(4, startRow + i, normal));
						sheet1.addCell(new Blank(5, startRow + i, normal));
						sheet1.addCell(new Blank(6, startRow + i, normal));
						sheet1.addCell(new Blank(7, startRow + i, normal));
						sheet1.addCell(new Blank(8, startRow + i, normal));
						sheet1.addCell(new Blank(9, startRow + i, normal));
						sheet1.addCell(new Blank(10, startRow + i, normal));
						sheet1.addCell(new Blank(11, startRow + i, normal));
						sheet1.addCell(new Blank(12, startRow + i, normal));
						sheet1.addCell(new Blank(13, startRow + i, normal));
						sheet1.addCell(new Blank(14, startRow + i, endLeft));
						divCodeTemp = ChgNullToEmpty(vo.getDivCode(), "");
						secCodeTemp = ChgNullToEmpty(vo.getSecCode(), "");
						i++;

					}
					String EmpName = "";
					if (vo.getFlagWork() != null
							&& vo.getFlagWork().equals("Y")) {
						EmpName += "*";
					}
					EmpName += vo.getPrefixName() + " " + vo.getFirstName()
							+ " " + vo.getLastName();
					String flagDesc = "";
					if(vo.getFlagPr() != null){
						if(vo.getFlagPr().equals("A"))
							flagDesc="ปรับปรุง";
						else if(vo.getFlagPr().equals("R"))
							flagDesc="เรียกคืน";
						else if(vo.getFlagPr().equals("B"))
							flagDesc="ตกเบิกปรับปรุงรายการรับ";
						else if(vo.getFlagPr().equals("S"))
							flagDesc="ตกเบิกปรับปรุงรายการเรียกคืน";
						else if(vo.getFlagPr().equals("N"))
							flagDesc="";
					}
					int t15 = 0;
					int t1 = 0;
					int t3 = 0;

					int amt15 = 0;
					int amt1 = 0;
					int amt3 = 0;

					String stt15 = "";
					String stt1 = "";
					String stt3 = "";
					String sta15 = "";
					String sta1 = "";
					String sta3 = "";

					if (vo.getTotDay15() != null) {
						t15 = vo.getTotDay15().intValue();
						sumTotDay15 = sumTotDay15
								+ vo.getTotDay15().doubleValue();
						flagTotalHour = "sum";
						stt15 = String.valueOf(vo.getTotDay15().intValue());
					}
					if (vo.getTotDay1() != null) {
						t1 = vo.getTotDay1().intValue();
						sumTotDay1 = sumTotDay1 + vo.getTotDay1().doubleValue();
						flagTotalHour = "sum";
						stt1 = String.valueOf(vo.getTotDay1().intValue());
					}
					if (vo.getTotDay3() != null) {
						t3 = vo.getTotDay3().intValue();
						sumTotDay3 = sumTotDay3 + vo.getTotDay3().doubleValue();
						flagTotalHour = "sum";
						stt3 = String.valueOf(vo.getTotDay3().intValue());
					}

					// /////////////////////////////////////////
					if (vo.getAmtDay1() != null) {
						amt1 = vo.getAmtDay1().intValue();
						sumTotAmt1 = sumTotAmt1 + vo.getAmtDay1().doubleValue();
						flagTotalHour = "sum";
						sta1 = String.valueOf(vo.getAmtDay1().intValue());

					}
					if (vo.getAmtDay15() != null) {
						amt15 = vo.getAmtDay15().intValue();
						sumTotAmt15 = sumTotAmt15
								+ vo.getAmtDay15().doubleValue();
						flagTotalHour = "sum";
						sta15 = String.valueOf(vo.getAmtDay15().intValue());
					}
					if (vo.getAmtDay3() != null) {
						amt3 = vo.getAmtDay3().intValue();
						sumTotAmt3 = sumTotAmt3 + vo.getAmtDay3().doubleValue();
						flagTotalHour = "sum";
						sta3 = String.valueOf(vo.getAmtDay3().intValue());
					}

					otTypeTemp = ChgNullToEmpty(vo.getOtType(), "");
					refNoTemp = ChgNullToEmpty(vo.getRefNo(), "");
					divCodeTemp = ChgNullToEmpty(vo.getDivCode(), "");
					secCodeTemp = ChgNullToEmpty(vo.getSecCode(), "");

					if (k + 1 < otList.size()) {
						if (((!refNoTemp.equalsIgnoreCase(vo2.getRefNo())) || (!otTypeTemp
								.equalsIgnoreCase(vo2.getOtType())))
								&& vo.getRefNo() != null
								&& vo.getOtType() != null) {
							sheet1.addCell(new Label(0, startRow + i, String
									.valueOf(countRow), end));
							sheet1.addCell(new Label(1, startRow + i,
									ChgNullToEmpty(vo.getEmpCode(), ""), end));
							sheet1.addCell(new Label(2, startRow + i, EmpName,
									endLeft));
							sheet1.addCell(new Label(3, startRow + i, vo
									.getYearPeriod(), end));
							sheet1.addCell(new Label(4, startRow + i, String
									.valueOf(vo.getFiscalYear().intValue()),
									end));
							sheet1.addCell(new Label(5, startRow + i, String
									.valueOf(vo.getWorkHour().doubleValue()),
									end));
							sheet1.addCell(new Label(6, startRow + i, vo
									.getStartDateTemp(), end));
							sheet1.addCell(new Label(7, startRow + i, vo
									.getEndDateTemp(), end));
							sheet1.addCell(new Label(8, startRow + i, stt15,
									frHour));
							sheet1.addCell(new Label(9, startRow + i, stt1,
									frHour));
							sheet1.addCell(new Label(10, startRow + i, stt3,
									frHour));
							sheet1.addCell(new Label(11, startRow + i, sta15,
									frHour));
							sheet1.addCell(new Label(12, startRow + i, sta1,
									frHour));
							sheet1.addCell(new Label(13, startRow + i, sta3,
									frHour));
							sheet1.addCell(new Label(14, startRow + i,
									flagDesc, endLeft));
							countRow++;
						} else {

							sheet1.addCell(new Label(0, startRow + i, String
									.valueOf(countRow), end));
							sheet1.addCell(new Label(1, startRow + i,
									ChgNullToEmpty(vo.getEmpCode(), ""), end));
							sheet1.addCell(new Label(2, startRow + i, EmpName,
									endLeft));
							sheet1.addCell(new Label(3, startRow + i, vo
									.getYearPeriod(), end));
							sheet1.addCell(new Label(4, startRow + i, String
									.valueOf(vo.getFiscalYear().intValue()),
									end));
							sheet1.addCell(new Label(5, startRow + i, String
									.valueOf(vo.getWorkHour().doubleValue()),
									end));
							sheet1.addCell(new Label(6, startRow + i, vo
									.getStartDateTemp(), end));
							sheet1.addCell(new Label(7, startRow + i, vo
									.getEndDateTemp(), end));
							sheet1.addCell(new Label(8, startRow + i, stt15,
									frHour));
							sheet1.addCell(new Label(9, startRow + i, stt1,
									frHour));
							sheet1.addCell(new Label(10, startRow + i, stt3,
									frHour));
							sheet1.addCell(new Label(11, startRow + i, sta15,
									frHour));
							sheet1.addCell(new Label(12, startRow + i, sta1,
									frHour));
							sheet1.addCell(new Label(13, startRow + i, sta3,
									frHour));
							sheet1.addCell(new Label(14, startRow + i,
									flagDesc, endLeft));
							countRow++;
						}
					} else {

						sheet1.addCell(new Label(0, startRow + i, String
								.valueOf(countRow), end));
						sheet1.addCell(new Label(1, startRow + i,
								ChgNullToEmpty(vo.getEmpCode(), ""), end));
						sheet1.addCell(new Label(2, startRow + i, EmpName,
								endLeft));
						sheet1.addCell(new Label(3, startRow + i, vo
								.getYearPeriod(), end));
						sheet1.addCell(new Label(4, startRow + i, String
								.valueOf(vo.getFiscalYear().intValue()), end));
						sheet1.addCell(new Label(5, startRow + i, String
								.valueOf(vo.getWorkHour().doubleValue()), end));
						sheet1.addCell(new Label(6, startRow + i, vo
								.getStartDateTemp(), end));
						sheet1.addCell(new Label(7, startRow + i, vo
								.getEndDateTemp(), end));
						sheet1.addCell(new Label(8, startRow + i, stt15, frHour));
						sheet1.addCell(new Label(9, startRow + i, stt1, frHour));
						sheet1.addCell(new Label(10, startRow + i, stt3, frHour));
						sheet1.addCell(new Label(11, startRow + i, sta15,
								frHour));
						sheet1.addCell(new Label(12, startRow + i, sta1, frHour));
						sheet1.addCell(new Label(13, startRow + i, sta3, frHour));
						sheet1.addCell(new Label(14, startRow + i, flagDesc,
								endLeft));
					}
					i++;
					if (!flagTotalHour.equals("") && (k + 1 == otList.size())) {

						sheet1.mergeCells(0, startRow + i, 7, startRow + i);
						sheet1.addCell(new Label(0, startRow+i , "รวม", borderNumber2));
						sheet1.addCell(new Label(8, startRow + i,
								convertFormatHour(new Double(sumTotDay15)),
								borderNumber));
						sheet1.addCell(new Label(9, startRow + i,
								convertFormatHour(new Double(sumTotDay1)),
								borderNumber));
						sheet1.addCell(new Label(10, startRow + i,
								convertFormatHour(new Double(sumTotDay3)),
								borderNumber));
						sheet1.addCell(new Label(11, startRow + i,
								convertFormatHour(new Double(sumTotAmt15)),
								borderNumber));
						sheet1.addCell(new Label(12, startRow + i,
								convertFormatHour(new Double(sumTotAmt1)),
								borderNumber));
						sheet1.addCell(new Label(13, startRow + i,
								convertFormatHour(new Double(sumTotAmt3)),
								borderNumber));
						sheet1.addCell(new Label(14, startRow + i, "",
								borderNumber2));

						sumAllTotDay1 = sumAllTotDay1 + sumTotDay1;
						sumAllTotDay15 = sumAllTotDay15 + sumTotDay15;
						sumAllTotDay3 = sumAllTotDay3 + sumTotDay3;
						sumAllTotAmt1 = sumAllTotAmt1 + sumTotAmt1;
						sumAllTotAmt15 = sumAllTotAmt15 + sumTotAmt15;
						sumAllTotAmt3 = sumAllTotAmt3 + sumTotAmt3;

						i++;
						sheet1.mergeCells(0, startRow + i, 7, startRow + i);
						sheet1.addCell(new Label(0, startRow+i , "รวมทั้งหมด", borderNumber2));
						sheet1.addCell(new Label(8, startRow + i,
								convertFormatHour(new Double(sumAllTotDay15)),
								borderNumber));
						sheet1.addCell(new Label(9, startRow + i,
								convertFormatHour(new Double(sumAllTotDay1)),
								borderNumber));
						sheet1.addCell(new Label(10, startRow + i,
								convertFormatHour(new Double(sumAllTotDay3)),
								borderNumber));
						sheet1.addCell(new Label(11, startRow + i,
								convertFormatHour(new Double(sumAllTotAmt15)),
								borderNumber));
						sheet1.addCell(new Label(12, startRow + i,
								convertFormatHour(new Double(sumAllTotAmt1)),
								borderNumber));
						sheet1.addCell(new Label(13, startRow + i,
								convertFormatHour(new Double(sumAllTotAmt3)),
								borderNumber));
						sheet1.addCell(new Label(14, startRow + i, "",
								borderNumber2));

						sumTotDay15 = 0.0;
						sumTotDay1 = 0.0;
						sumTotDay3 = 0.0;
						sumAllTotDay15 = 0.0;
						sumAllTotDay1 = 0.0;
						sumAllTotDay3 = 0.0;
						flagTotalHour = "";
						i++;
					}
				}

				sheet1.getSettings().setPassword("028313766");
				sheet1.getSettings().setProtected(true);

				if (ww.getNumberOfSheets() > 1) {
					ww.removeSheet(0);
				}

				// sheet1 = ww.getSheet(0);
				// sheet1.getSettings().setHidden(true);
				ww.setProtected(true);
				ww.write();
				ww.close();
				// sheet1.getSettings().setHidden(false);

				wb.close();
				in.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			ww.copySheet("Sheet1", "แผ่นงาน1" , ww.getNumberOfSheets());	
			sheet1 = ww.getSheet(1);

			sheet1.addCell(new Label(0, 0, ouDesc, header));

			if(request.getParameter("otType").equals("1")){
				//sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002                                                                                                                       รายงานข้อมูลค่าล่วงเวลา", header2));
				sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002",header2));
				sheet1.addCell(new Label(2, 1,"รายงานข้อมูลค่าล่วงเวลา",header2Center));
			}
			else if(request.getParameter("otType").equals("2")){
				//sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002                                                                                                           รายงานข้อมูลค่าทำงานในวันหยุดพักผ่อน", header2));
				sheet1.addCell(new Label(0, 1,"โปรแกรม CTRWRP002",header2));
				sheet1.addCell(new Label(2, 1,"รายงานข้อมูลค่าทำงานในวันหยุดพักผ่อน",header2Center));
			}
			sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
			sheet1.addCell(new Label(3, 2, "ประจำงวด " + periodName + "   พ.ศ. " + year+"                                                                                                         วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), header3));
			
			sheet1.addCell(new Blank(5, 10, normalTemp));
			
			sheet1.mergeCells(0,startRow+i,14,startRow+i);
			sheet1.addCell(new Label(0, startRow+i,"ไม่พบข้อมูล", noData));
			sheet1.addCell(new Blank(1, startRow + i, noData));
			sheet1.addCell(new Blank(2, startRow + i, noData));
			sheet1.addCell(new Blank(3, startRow + i, noData));
			sheet1.addCell(new Blank(4, startRow + i, noData));
			sheet1.addCell(new Blank(5, startRow + i, noData));
			sheet1.addCell(new Blank(6, startRow + i, noData));
			sheet1.addCell(new Blank(7, startRow + i, noData));
			sheet1.addCell(new Blank(8, startRow + i, noData));
			sheet1.addCell(new Blank(9, startRow + i, noData));
			sheet1.addCell(new Blank(10, startRow + i, noData));
			sheet1.addCell(new Blank(11, startRow + i, noData));
			sheet1.addCell(new Blank(12, startRow + i, noData));
			sheet1.addCell(new Blank(13, startRow + i, noData));
			sheet1.addCell(new Blank(14, startRow + i, noData));
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

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
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
		if (hour != null) {
			rlst = dec.format(hour.intValue());
		} else {
			rlst = "";
		}

		return rlst;
	}
}
