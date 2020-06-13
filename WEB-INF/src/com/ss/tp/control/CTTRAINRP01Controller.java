package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.PnEmpMoveVO;
import com.ss.tp.service.PnEmpMoveService;

public class CTTRAINRP01Controller extends MultiActionController {
	public PnEmpMoveService getPnEmpMoveService() {
		return (PnEmpMoveService) this.getApplicationContext().getBean(
				"pnEmpMoveService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List resultList = new ArrayList();
		int startRow = 4;
		int row = startRow;
		int seq = 0;
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		DecimalFormat numformat = new DecimalFormat("0");
		// String choiceR = request.getParameter("choiceReport");
		// String choiceG = request.getParameter("choiceGroup");
		// String choiceS = request.getParameter("choiceStatus");

		int countEmp = 0;
		String orgCodeFrom = "";
		String orgCodeTo = "";
		String empFrom = "";
		String empTo = "";
		String choiceG = "";
		String choiceS = "";
		String choiceR = "";

		if (request.getParameter("orgCodeFrom") != null) {
			orgCodeFrom = request.getParameter("orgCodeFrom");
		}

		if (request.getParameter("orgCodeTo") != null) {
			orgCodeTo = request.getParameter("orgCodeTo");
		}

		if (request.getParameter("empF") != null) {
			empFrom = request.getParameter("empF");
		}

		if (request.getParameter("empT") != null) {
			empTo = request.getParameter("empT");
		}
		if (request.getParameter("choiceReport") != null) {
			choiceR = request.getParameter("choiceReport");

		}
		if (request.getParameter("choiceStatus") != null) {
			choiceS = request.getParameter("choiceStatus");
		}
		if (request.getParameter("choiceGroup") != null) {
			choiceG = request.getParameter("choiceGroup");
		}

		if (choiceR.equals("O")) {

			resultList = this.getPnEmpMoveService().findByCriteriaReport1(
					orgCodeFrom, orgCodeTo, empFrom, empTo, choiceS, choiceG);
			/*
			 * System.out.println("report1"); System.out.println(choiceR);
			 * System.out.println(choiceG); System.out.println(choiceS);
			 */
		} else if (choiceR.equals("M")) {
			resultList = this.getPnEmpMoveService().findByCriteriaReport2(
					orgCodeFrom, orgCodeTo, empFrom, empTo, choiceS, choiceG);
			/*
			 * System.out.println("report2"); System.out.println(choiceR);
			 * System.out.println(choiceG); System.out.println(choiceS);
			 */
		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTTRAINRP01.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTTRAINRP01.xls");
		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(12);

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

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy",
				new java.util.Locale("th", "TH"));
		for (int i = 0; i < resultList.size(); i++) {
			PnEmpMoveVO vo = (PnEmpMoveVO) resultList.get(i);
			seq = seq + 1;
			sheet1.addCell(new Label(2, row, String.valueOf(dfInt.format(seq))));
			sheet1.addCell(new Label(8, 1, vo.getHeader(), HeadFormat));
			sheet1.addCell(new Label(3, row, vo.getEmpCode()));
			sheet1.addCell(new Label(5, row, vo.getEmpName()));
			sheet1.addCell(new Label(7, row, vo.getMPosition()));
			sheet1.addCell(new Label(9, row, vo.getMDivdesc()));
			sheet1.addCell(new Label(10, row, vo.getMSecdesc()));
			sheet1.addCell(new Label(13, row, vo.getEduFirst()));
			sheet1.addCell(new Label(13, row + 1, vo.getSecEdu()));
			sheet1.addCell(new Label(13, row + 2, vo.getThirdEdu()));
			sheet1.addCell(new Label(13, row + 3, vo.getForthEdu()));
			sheet1.addCell(new Label(15, row + 4, vo.getFifthEdu()));
			sheet1.addCell(new Label(16, row, vo.getManReplace()));
			sheet1.addCell(new Label(17, row, vo.getMoveSeq()));
			/*
			 * sheet1.addCell(new Label
			 * (10,row,numformat.format(vo.getMoveSeq())));
			 */
			sheet1.addCell(new Label(18, row, vo.getMoveOrg()));
			sheet1.addCell(new Label(19, row, sdfPrint.format(vo
					.getDateSubmit())));
			sheet1.addCell(new Label(20, row, vo.getMoveReason()));
			sheet1.addCell(new Label(21, row, vo.getMoveNote()));

			/*
			 * tempEmpCode=vo.getEmpCode();
			 * 
			 * tempEmpCode1=tempEmpCode;
			 * 
			 * if(tempEmpCode1 != tempEmpCode){ sheet1.addCell(new Label
			 * (1,row,vo.getEmpCode())); }else{ sheet1.addCell(new Label
			 * (1,row,null)); }
			 */
			row = row + 5;
			/* tempEmpCode1=""; */
		}

		ww.write();
		ww.close();
		wb.close();
		in.close();

		return null;
	}
}