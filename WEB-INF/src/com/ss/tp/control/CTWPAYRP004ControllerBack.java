/*
 * Created on 22 ï¿½.ï¿½. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ss.tp.dto.FeeWpayMonthEmpWorkVO;
import com.ss.tp.dto.TaReportVO;
import com.ss.tp.service.SuOrganizeService;
import com.ss.tp.service.FeeWpayPrWorkDayService;
//import com.ss.tp.service.TaReportService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYRP004ControllerBack extends MultiActionController {

	/**
	 * @return Returns the taReportService.
	 */
	public FeeWpayPrWorkDayService getFeeWpayPrWorkDayService() {

		return (FeeWpayPrWorkDayService) this.getApplicationContext().getBean(
				"feeWpayPrWorkDayService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}



	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½Å¡ï¿½ï¿½ï¿½Ò»ï¿½Ð¨Ó¢Í§ï¿½ï¿½Ñ¡ï¿½Ò¹
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
		int workYear = 0;
		int workYearFrom = 0;
		int workYearTo = 0;
		String monthFrom = "";
		String monthTo = "";
		String empCodeFrom = "";
		String empCodeTo = "";
		String orgCodeFrom = "";
		String orgCodeTo = "";
		String choice = "";

		String workCodeFrom = "";
		String workCodeTo = "";
		int sumDonKeyFrom = 0;
		int sumDonKeyTo = 0;
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYRP004.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYRP004.xls");
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("workYear") != null
				&& !"".equals(request.getParameter("workYear"))) {
			workYear = Integer.parseInt(request.getParameter("workYear"));
		}
		if (request.getParameter("workYearFrom") != null
				&& !"".equals(request.getParameter("workYearFrom"))) {
			workYearFrom = Integer.parseInt(request
					.getParameter("workYearFrom"));
		}
		if (request.getParameter("workYearTo") != null
				&& !"".equals(request.getParameter("workYearTo"))) {
			workYearTo = Integer.parseInt(request.getParameter("workYearTo"));
		}
		if (request.getParameter("monthTempFrom") != null) {
			monthFrom = request.getParameter("monthTempFrom");
		}
		if (request.getParameter("monthTempTo") != null) {
			monthTo = request.getParameter("monthTempTo");
		}
		if (request.getParameter("orgCodeFrom") != null) {
			orgCodeFrom = request.getParameter("orgCodeFrom");
		}
		if (request.getParameter("orgCodeTo") != null) {
			orgCodeTo = request.getParameter("orgCodeTo");
		}
		if (request.getParameter("empF") != null) {
			empCodeFrom = request.getParameter("empF");
		}
		if (request.getParameter("empT") != null) {
			empCodeTo = request.getParameter("empT");
		}
		if (request.getParameter("workCodeFrom") != null) {
			workCodeFrom = request.getParameter("workCodeFrom");
		}
		if (request.getParameter("workCodeTo") != null) {
			workCodeTo = request.getParameter("workCodeTo");
		}
		if (request.getParameter("sumDonKeyForm") != null
				&& !request.getParameter("sumDonKeyForm").equals("")) {
			sumDonKeyFrom = Integer.parseInt(request
					.getParameter("sumDonKeyForm"));
		} else {
			sumDonKeyFrom = 0;
		}
		if (request.getParameter("sumDonKeyTo") != null
				&& !request.getParameter("sumDonKeyTo").equals("")) {
			sumDonKeyTo = Integer.parseInt(request.getParameter("sumDonKeyTo"));
		} else {
			if (sumDonKeyFrom == 0) {
				sumDonKeyTo = 0;
			} else {
				sumDonKeyTo = 9999;
			}

		}
		if (request.getParameter("choice") != null) {
			choice = request.getParameter("choice");
		}
		List tempFontWeb = this.getFeeWpayPrWorkDayService().findWorkMonth(ouCode);// ï¿½ï¿½ status workCode ï¿½ï¿½
		Vector dataStatusFrom = new Vector();
		Vector dataStatusTo = new Vector();
		Vector statusWorkCode = new Vector();
		if (tempFontWeb.size() > 0) {
			FeeWpayMonthEmpWorkVO tamVo = null;
			for (int i = 0; i < tempFontWeb.size(); i++) {
				tamVo = (FeeWpayMonthEmpWorkVO) tempFontWeb.get(i);
				statusWorkCode.addElement(tamVo.getWorkCode());
				dataStatusFrom.addElement(request.getParameter("numWorkFrom"
						+ tamVo.getWorkCode()));// ï¿½ï¿½ ï¿½Ó¹Ç¹ï¿½ï¿½ï¿½ï¿½Í¡Ë¹ï¿½ï¿½ web
				dataStatusTo.addElement(request.getParameter("numWorkTo"
						+ tamVo.getWorkCode()));// ï¿½ï¿½ ï¿½Ó¹Ç¹ï¿½ï¿½ï¿½ï¿½Í¡Ë¹ï¿½ï¿½ web
			}

		}
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);
		String nameAreaF = this.getFeeWpayPrWorkDayService().findAreaDesc(orgCodeFrom);
		String nameAreaT = this.getFeeWpayPrWorkDayService().findAreaDesc(orgCodeTo);
		List secList = this.getFeeWpayPrWorkDayService().findListSecTaReportNew(ouCode,
				userId, workYear, workYearFrom, workYearTo, monthFrom, monthTo,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, choice);
		List taReplist = this.getFeeWpayPrWorkDayService().findListTaReportNew(ouCode,
				userId, workYear, workYearFrom, workYearTo, monthFrom, monthTo,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, choice);
		// ---------------------------------Generate Report
		// Detail--------------------------------------------------

		WritableCellFormat border = new WritableCellFormat();
		border.setBorder(Border.ALL, BorderLineStyle.THIN);
		border.setBackground(Colour.GRAY_50);
		Alignment data = Alignment.getAlignment(1);
		border.setAlignment(data.CENTRE);

		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setBoldStyle(WritableFont.BOLD);
		font.setPointSize(9);
		border.setFont(font);

		WritableCellFormat head = new WritableCellFormat();
		head.setAlignment(data.CENTRE);
		head.setFont(font);

		WritableCellFormat headRight = new WritableCellFormat();
		headRight.setAlignment(data.RIGHT);
		headRight.setFont(font);

		WritableCellFormat nameMonth = new WritableCellFormat();
		nameMonth.setBorder(Border.ALL, BorderLineStyle.THIN);
		nameMonth.setBackground(Colour.GRAY_25);
		nameMonth.setAlignment(data.CENTRE);
		nameMonth.setFont(font);

		WritableCellFormat borderRight = new WritableCellFormat();
		borderRight.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderRight.setAlignment(data.RIGHT);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(data.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		font2.setPointSize(9);

		WritableCellFormat group2 = new WritableCellFormat();
		group2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		group2.setAlignment(data.LEFT);
		group2.setFont(font);

		WritableCellFormat group3 = new WritableCellFormat();
		group3.setBorder(Border.LEFT, BorderLineStyle.THIN);
		group3.setAlignment(data.LEFT);
		group3.setFont(font2);

		WritableCellFormat dataBorder = new WritableCellFormat();
		dataBorder.setBorder(Border.LEFT, BorderLineStyle.THIN);
		dataBorder.setAlignment(data.RIGHT);
		dataBorder.setFont(font2);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		TaReportVO tavo = null;
		int startRow = 6;
		String empTemp = "";
		String empName = "";
		String orgWork = "";
		String orgWorkTemp = "";
		List nameSecList = secList;
		List headColum = taReplist;
		int numOfSheet = secList.size();
		int numLastUnderline = numOfSheet;
		String tempSecCode = "";
		String tempSecDesc = "";
		String tempDivCode = "";
		String tempDivDesc = "";
		String tempDivShort = "";
		String tempAreaCode = "";
		String tempAreaDesc = "";
		String nameSheet = "";
		String tempYear = "";

		String tempYearFrom = String.valueOf(workYearFrom);
		String tempYearTo = String.valueOf(workYearTo);
		if (!tempYearFrom.equals("0") && !tempYearTo.equals("0")) {
			tempYearFrom = tempYearFrom.substring(2, tempYearFrom.length());
			tempYearTo = tempYearTo.substring(2, tempYearTo.length());
			tempYearFrom = monthFrom + "/" + tempYearFrom;
			tempYearTo = monthTo + "/" + tempYearTo;

		}

		int tempYear2 = 0;
		TaReportVO vo = null;

		if (secList.size() > 0) {
			vo = (TaReportVO) secList.get(0);
			// tempSecCode=(String)vo.getSecCode();
			// tempSecDesc=(String)vo.getSecDesc();
			tempDivCode = (String) vo.getDivCode();
			tempDivDesc = (String) vo.getDivDesc();
			tempDivShort = (String) vo.getDivShort();

			tempAreaCode = (String) vo.getAreaCode();
			tempAreaDesc = (String) vo.getAreaDesc();
		}
		if (headColum.size() > 0) {
			vo = new TaReportVO();
			vo = (TaReportVO) secList.get(0);
		}
		WritableSheet sheet[];

		if (numOfSheet == 0) {
			sheet = new WritableSheet[1];
		} else {
			sheet = new WritableSheet[numOfSheet];
		}

		int sheetNum = 1;
		for (int i = 0; i < numOfSheet - 1; i++) {
			tavo = (TaReportVO) nameSecList.get(i + 1);
			if (tavo.getAreaCode() == null) {
				nameSheet = "Sheet" + sheetNum;
				sheetNum++;
			} else {
				nameSheet = tavo.getAreaCode();
			}
			ww.copySheet("Sheet1", nameSheet, ww.getNumberOfSheets());

		}

		sheet[0] = ww.getSheet(0);
		if (numOfSheet == 0) {
			sheet[0].setName("Sheet");
		} else {
			if (tempAreaCode == null) {
				sheet[0].setName("Sheet");
			} else {
				sheet[0].setName(tempAreaCode);
			}

		}

		CellFormat group = sheet[0].getWritableCell(1, 7).getCellFormat();
		CellFormat borderLR = sheet[0].getWritableCell(4, 7).getCellFormat();

		// sheet[0].setRowView(startRow,320);
		// sheet[0].addCell(new Label(0, 0, ouName,head));
		// if(tempAreaCode!=null){
		// // for data report is null
		// if(tempAreaCode.equals("")){
		// if(nameAreaF!=null && nameAreaF == null){
		// sheet[0].addCell(new Label(1, 2, "Ê¾./ï¿½ï¿½. : "+nameAreaF,head));
		// sheet[0].addCell(new Label(11, 2,
		// "ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ : "+sdfPrint.format(gd.getTime()),headRight));
		// }else if(nameAreaF!=null && nameAreaF !=null ){
		// if(!nameAreaF.equals("") && !nameAreaT.equals("")){
		// sheet[0].addCell(new Label(1, 2, "Ê¾./ï¿½ï¿½. : "+nameAreaF+
		// " - "+nameAreaT,head));
		// sheet[0].addCell(new Label(11, 2,
		// "ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ : "+sdfPrint.format(gd.getTime()),headRight));
		// }else{
		// sheet[0].addCell(new Label(1, 2, "Ê¾./ï¿½ï¿½. : "+nameAreaF,head));
		// sheet[0].addCell(new Label(11, 2,
		// "ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ : "+sdfPrint.format(gd.getTime()),headRight));
		// }
		// }
		// }else{
		// sheet[0].addCell(new Label(1, 2, "Ê¾./ï¿½ï¿½. : "+tempAreaDesc,head));
		// sheet[0].addCell(new Label(11, 2,
		// "ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ : "+sdfPrint.format(gd.getTime()),headRight));
		// }
		// }else{
		// sheet[0].addCell(new Label(1, 2, "Ê¾./ï¿½ï¿½. : "+tempDivDesc,head));
		// sheet[0].addCell(new Label(11, 2,
		// "ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ : "+sdfPrint.format(gd.getTime()),headRight));
		// }
		// // sheet[0].addCell(new Label(0, 3, "ï¿½ï¿½Ð¨Ó»ï¿½ "+workYear,head));
		// sheet[0].addCell(new Label(0, 3,
		// "ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Í¹/ï¿½ï¿½: "+tempYearFrom+" ï¿½Ö§ï¿½ï¿½Í¹/ï¿½ï¿½: "+tempYearTo,head));
		// sheet[0].mergeCells(2, 4, 14, 4);
		// sheet[0].addCell(new Label(2, 4, "ï¿½Ó¹Ç¹ï¿½Ñ¹", border));
		// if(headColum.size()>0){
		// vo =new TaReportVO();
		// vo = (TaReportVO)headColum.get(0);
		// sheet[0].addCell(new Label(2, 5,vo.getMonthName1(),nameMonth));
		// sheet[0].addCell(new Label(3, 5,vo.getMonthName2(),nameMonth));
		// sheet[0].addCell(new Label(4, 5,vo.getMonthName3(),nameMonth));
		// sheet[0].addCell(new Label(5, 5,vo.getMonthName4(),nameMonth));
		// sheet[0].addCell(new Label(6, 5,vo.getMonthName5(),nameMonth));
		// sheet[0].addCell(new Label(7, 5,vo.getMonthName6(),nameMonth));
		// sheet[0].addCell(new Label(8, 5,vo.getMonthName7(),nameMonth));
		// sheet[0].addCell(new Label(9, 5,vo.getMonthName8(),nameMonth));
		// sheet[0].addCell(new Label(10, 5,vo.getMonthName9(),nameMonth));
		// sheet[0].addCell(new Label(11, 5,vo.getMonthName10(),nameMonth));
		// sheet[0].addCell(new Label(12, 5,vo.getMonthName11(),nameMonth));
		// sheet[0].addCell(new Label(13, 5,vo.getMonthName12(),nameMonth));
		// }
		// else{
		// sheet[0].addCell(new Label(2, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(3, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(4, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(5, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(6, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(7, 5, "ï¿½ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(8, 5, "ï¿½ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(9, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(10, 5, "ï¿½ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(11, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(12, 5, "ï¿½.ï¿½.",nameMonth));
		// sheet[0].addCell(new Label(13, 5, "ï¿½.ï¿½.",nameMonth));
		// }
		// System.out.println("tempAreaCode");
		// if(tempAreaCode !=null){
		// sheet[0].addCell(new Label(0, 6,tempSecCode+" "+tempSecDesc,
		// group));
		// for(int x=1;x<15;x++){
		// sheet[0].addCell(new Blank(x, startRow,borderLR));
		// }
		// startRow++;
		// }
		// System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
		int i = 0;
		int d = 0;
		int j = -1;
		tempSecCode = "";
		tempSecDesc = "";
		tempDivCode = "";
		tempDivDesc = "";
		tempDivShort = "";

		tempAreaCode = "";
		tempAreaDesc = "";
		// if(numLastUnderline!=0){
		// numLastUnderline--;
		// }
		try {
			if (taReplist.size() > 0) {
				for (Iterator itt = taReplist.iterator(); itt.hasNext();) {
					tavo = (TaReportVO) itt.next();
					if (!tempAreaCode.equals(tavo.getAreaCode())) {
						if (j >= 0) {
							for (int y = 0; y < 15; y++) {
								sheet[j].setRowView(startRow, 320);
								sheet[j].addCell(new Label(y, startRow + i, "",
										borderEndLine));
							}
						}

						tempDivCode = tavo.getDivCode();
						tempDivDesc = tavo.getDivDesc();
						tempDivShort = tavo.getDivShort();
						tempAreaCode = tavo.getAreaCode();
						tempAreaDesc = tavo.getAreaDesc();
						i = 0;
						d = 0;
						j++;
						startRow = 6;
						sheet[j] = ww.getSheet(j);
						sheet[j].setRowView(startRow, 320);
						sheet[j].addCell(new Label(0, 0, ouName, head));
						if(tempAreaCode!=null){
							sheet[j].addCell(new Label(1, 2, "Ê¾./»¨. : "+tempAreaDesc+" "+tempDivShort,head));
							sheet[j].addCell(new Label(11, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
						}else{
							sheet[j].addCell(new Label(1, 2, "Ê¾./»¨. : "+tempDivDesc,head));
							sheet[j].addCell(new Label(11, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
						}
						//						sheet[j].addCell(new Label(0, 3, "»ÃÐ¨Ó»Õ   "+workYear,head));
						sheet[j].addCell(new Label(0, 3, "µÑé§áµèà´×Í¹/»Õ: "+tempYearFrom+"           ¶Ö§à´×Í¹/»Õ: "+tempYearTo,head));
						sheet[j].mergeCells(2, 4, 14, 4);
						sheet[j].addCell(new Label(2, 4, "¨Ó¹Ç¹ÇÑ¹", border));
						sheet[j].addCell(new Label(2, 5, tavo.getMonthName1(),
								nameMonth));
						sheet[j].addCell(new Label(3, 5, tavo.getMonthName2(),
								nameMonth));
						sheet[j].addCell(new Label(4, 5, tavo.getMonthName3(),
								nameMonth));
						sheet[j].addCell(new Label(5, 5, tavo.getMonthName4(),
								nameMonth));
						sheet[j].addCell(new Label(6, 5, tavo.getMonthName5(),
								nameMonth));
						sheet[j].addCell(new Label(7, 5, tavo.getMonthName6(),
								nameMonth));
						sheet[j].addCell(new Label(8, 5, tavo.getMonthName7(),
								nameMonth));
						sheet[j].addCell(new Label(9, 5, tavo.getMonthName8(),
								nameMonth));
						sheet[j].addCell(new Label(10, 5, tavo.getMonthName9(),
								nameMonth));
						sheet[j].addCell(new Label(11, 5,
								tavo.getMonthName10(), nameMonth));
						sheet[j].addCell(new Label(12, 5,
								tavo.getMonthName11(), nameMonth));
						sheet[j].addCell(new Label(13, 5,
								tavo.getMonthName12(), nameMonth));
					}

					if (!tempSecCode.equals(tavo.getSecCode())) {
						tempSecCode = tavo.getSecCode();
						tempSecDesc = tavo.getSecDesc();
						sheet[j].setRowView(startRow, 320);
						sheet[j].addCell(new Label(0, startRow + i, tempSecCode
								+ "  " + tempSecDesc, group));
						for (int x = 1; x < 15; x++) {
							sheet[j].setRowView(startRow + i, 320);
							sheet[j].addCell(new Blank(x, startRow + i,
									borderLR));
						}
						i++;
						//startRow++;
					}

					// if(tavo.getOrgWorkCode()==null){//ï¿½ï¿½ï¿½ï¿½ï¿½null
					// ï¿½Ò¨ï¿½ï¿½ï¿½Íºï¿½Ã¡ï¿½ï¿½ï¿½ï¿½
					// tavo.setOrgWorkCode("");
					// tavo.setOrgWorkDesc("");
					// }
					//
					// if(orgWorkTemp.equals("")){//ï¿½Íºï¿½Ã¡
					// orgWorkTemp=tavo.getOrgWorkCode();
					// orgWork=tavo.getOrgWorkDesc();
					// }else
					// if(orgWorkTemp.equals(tavo.getOrgWorkCode())){//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Í¹ï¿½Íºï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
					// orgWork="";
					// }else{//ï¿½ï¿½Òµï¿½Ò§ï¿½Ñ¹
					// orgWorkTemp=tavo.getOrgWorkCode();
					// orgWork=tavo.getOrgWorkDesc();
					// }
					// if(orgWorkTemp!=null){
					// if(!orgWork.equals("")){
					// sheet[j].setRowView(startRow+i,320);
					// sheet[j].addCell(new Label(0,
					// startRow+i,orgWorkTemp+" "+orgWork,group2));
					// for(int x=1;x<15;x++){
					// sheet[j].addCell(new Blank(x, startRow+i,borderLR));
					// }
					// i++;
					// }
					//
					// }

					if (empTemp.equals("")) {
						empTemp = tavo.getEmpCode();
						empName = tavo.getFullName();
						sheet[j].addCell(new Label(0, startRow + i, empTemp
								+ " " + empName, group3));
					} else if (!empTemp.equals(tavo.getEmpCode())) {
						empTemp = tavo.getEmpCode();
						empName = tavo.getFullName();
						sheet[j].addCell(new Label(0, startRow + i, empTemp
								+ " " + empName, group3));
					} else {
						sheet[j].addCell(new Label(0, startRow + i, " ", group3));
					}

					sheet[j].setRowView(startRow + i, 320);
					sheet[j].addCell(new Label(1, startRow + i, tavo
							.getWorkCode() + " " + tavo.getWorkDesc(), group3));
					
					sheet[j].addCell(new Label(2, startRow + i, tavo
							.getMonth1(), dataBorder));
				    sheet[j].addCell(new Label(3, startRow + i, tavo
							.getMonth2(), dataBorder));
					sheet[j].addCell(new Label(4, startRow + i, tavo
							.getMonth3(), dataBorder));
					sheet[j].addCell(new Label(5, startRow + i, tavo
							.getMonth4(), dataBorder));
					sheet[j].addCell(new Label(6, startRow + i, tavo
							.getMonth5(), dataBorder));
					sheet[j].addCell(new Label(7, startRow + i, tavo
							.getMonth6(), dataBorder));
					sheet[j].addCell(new Label(8, startRow + i, tavo
							.getMonth7(), dataBorder));
					sheet[j].addCell(new Label(9, startRow + i, tavo
							.getMonth8(), dataBorder));
					sheet[j].addCell(new Label(10, startRow + i, tavo
							.getMonth9(), dataBorder));
					sheet[j].addCell(new Label(11, startRow + i, tavo
							.getMonth10(), dataBorder));
					sheet[j].addCell(new Label(12, startRow + i, tavo
							.getMonth11(), dataBorder));
					sheet[j].addCell(new Label(13, startRow + i, tavo
							.getMonth12(), dataBorder));
					sheet[j].addCell(new Label(14, startRow + i, tavo
							.getTotal(), borderLR));
					
					//startRow++;
					i++;
					//i++;

					// if(j==0 || numLastUnderline == j ){
					// for(int y=0;y<15;y++){
					// sheet[j].setRowView(startRow+i,320);
					// sheet[j].addCell(new Label(y,
					// startRow+i,"",borderEndLine));
					// }
					// }
					sheet[j].getSettings().setPassword("123");
					sheet[j].getSettings().setProtected(true);
				}
			
				for (int y = 0; y < 15; y++) {
					sheet[j].setRowView(startRow, 320);
					sheet[j].addCell(new Label(y, startRow + i, "",
							borderEndLine));
				}
			} else {
				WritableSheet sheetNoData = ww.getSheet(0);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border2 = new WritableCellFormat();
				border2.setAlignment(noAlg.CENTRE);
				border2.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheetNoData.setRowView(startRow, 320);
				sheetNoData.mergeCells(0, 6, 14, 6);
				sheetNoData.addCell(new Label(0, 6, "äÁè¾º¢éÍÁÙÅ",border2));
				sheetNoData.removeRow(7);

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
}
