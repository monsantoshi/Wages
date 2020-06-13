/*
 * Created on 22 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

import com.ss.tp.dto.PnEmpImpEngNameVO;

import com.ss.tp.service.PnEmpImpService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class CTPPPRP001Controller extends MultiActionController {
	public PnEmpImpService getPnEmpImpService() {
		return (PnEmpImpService) this.getApplicationContext().getBean(
				"pnEmpImpService");
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

		int workYear = 0;
		int workYearFrom = 0;
		int workYearTo = 0;
		String monthFrom = "";
		String monthTo = "";

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

		resultList = this.getPnEmpImpService().findByCriteriaReportEngNameNew(workYear,
				workYearFrom, workYearTo, monthFrom, monthTo);
		// System.out.println("report1");
		// System.out.println(monthFrom);
		// System.out.println(monthTo);
		// System.out.println(workYearFrom);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTPPPRP001.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTPPPRP001.xls");
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
			PnEmpImpEngNameVO vo = (PnEmpImpEngNameVO) resultList.get(i);
			seq = seq + 1;
			sheet1.addCell(new Label(0, row, String.valueOf(dfInt.format(seq)),
					normalRightLastFormat));
			sheet1.addCell(new Label(3, 1, vo.getMHeader(), HeadFormat));
			sheet1.addCell(new Label(1, row, vo.getEmpCode(),
					normalCenterLastFormat));
			sheet1.addCell(new Label(2, row, vo.getEmpName(),
					normalLeftLastFormat));
			sheet1.addCell(new Label(3, row, vo.getEngEmpName(),
					normalLeftLastFormat));
			sheet1.addCell(new Label(4, row, vo.getMPosition(),
					normalCenterLastFormat));
			sheet1.addCell(new Label(5, row, vo.getMDivdesc(),
					normalLeftLastFormat));
			sheet1.addCell(new Label(6, row, vo.getMSecdesc(),
					normalLeftLastFormat));
			sheet1.addCell(new Label(7, row, sdfPrint.format(vo.getIDate()),
					normalCenterLastFormat));

			/*
			 * tempEmpCode=vo.getEmpCode();
			 * 
			 * tempEmpCode1=tempEmpCode;
			 * 
			 * if(tempEmpCode1 != tempEmpCode){ sheet1.addCell(new Label
			 * (1,row,vo.getEmpCode())); }else{ sheet1.addCell(new Label
			 * (1,row,null)); }
			 */
			row++;
			/* tempEmpCode1=""; */
		}

		ww.write();
		ww.close();
		wb.close();
		in.close();

		return null;
	}
}
