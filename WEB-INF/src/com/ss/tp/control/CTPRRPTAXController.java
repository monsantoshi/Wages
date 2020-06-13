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
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableFont.FontName;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.VPrEmployeeMonthTaxVO;
import com.ss.tp.service.PrEmployeeService;
import com.ss.tp.service.SuOrganizeService;







/**
 * @author amorn
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class CTPRRPTAXController extends MultiActionController {
	
	private PrEmployeeService getPrEmployeeService() {
		return (PrEmployeeService) this.getApplicationContext().getBean(
				"prEmployeeService");
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
		int row = 0;
		

		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			year = Integer.parseInt(request.getParameter("year"));
		}

		if (request.getParameter("period") != null) {
			period = Integer.parseInt(request.getParameter("period"));
		}

		List empList = this.getPrEmployeeService().findByCriteriaTax(ouCode, year, period);
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTPRRPTAX.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTPRRPTAX.xls");

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(12);

		// WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		// fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		// fontNormal.setPointSize(9);

		FontName fontName = WritableFont.createFont("AngsanaUPC");
		WritableFont fontNormal = new WritableFont(fontName, 16);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(16);

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
		// normalLeftFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalLeftFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalLeftFormat.setAlignment(dataAlignLeft);
		normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftFormat.setFont(fontNormal);

		WritableCellFormat normalCenterFormat = new WritableCellFormat();
		// normalCenterFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalCenterFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalCenterFormat.setAlignment(dataAlignCenter);
		normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterFormat.setFont(fontNormal);

		WritableCellFormat normalRightFormat = new WritableCellFormat();
		// normalRightFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalRightFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalRightFormat.setAlignment(dataAlignRight);
		normalRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightFormat.setFont(fontNormal);

		WritableCellFormat normalLeftLastFormat = new WritableCellFormat();
		// normalLeftLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalLeftLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalLeftLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
		normalLeftLastFormat.setAlignment(dataAlignLeft);
		normalLeftLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftLastFormat.setFont(fontNormal);

		WritableCellFormat normalCenterLastFormat = new WritableCellFormat();
		// normalCenterLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalCenterLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalCenterLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
		normalCenterLastFormat.setAlignment(dataAlignCenter);
		normalCenterLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterLastFormat.setFont(fontNormal);

		WritableCellFormat normalRightLastFormat = new WritableCellFormat();
		// normalRightLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalRightLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalRightLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
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
		row = 0;
		for (int i = 0; i < empList.size(); i++) {
			VPrEmployeeMonthTaxVO vo = (VPrEmployeeMonthTaxVO) empList.get(i);
		

			
			sheet1.addCell(new Label(0, row, vo.getEmpCode(),
					normalCenterLastFormat));
			sheet1.addCell(new Label(1, row, vo.getOldSalary(),
					normalRightLastFormat));
			sheet1.addCell(new Label(2, row, vo.getSalary(),
					normalRightLastFormat));
			sheet1.addCell(new Label(3, row, vo.getMarriedStatus().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(4, row, vo.getChildStudy(),
					normalRightLastFormat));
			sheet1.addCell(new Label(5, row, vo.getChildNoStudy(),
					normalRightLastFormat));
			sheet1.addCell(new Label(6, row,vo.getDebtLife(),
					normalRightLastFormat));
			sheet1.addCell(new Label(7, row, vo.getDebtLoan(),
					normalRightLastFormat));
			sheet1.addCell(new Label(8, row, vo.getDonate(),
					normalRightLastFormat));
			sheet1.addCell(new Label(9, row, vo.getFlagMotherSpouse().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(10, row, vo.getAaaa().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(11, row, vo.getFlagFatherSpouse().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(12, row, vo.getFlagMother().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(13, row, vo.getFlagFather().trim(),
					normalRightLastFormat));
			sheet1.addCell(new Label(14, row, vo.getLtf(),
					normalRightLastFormat));
			sheet1.addCell(new Label(15, row, vo.getRmf(),
					normalRightLastFormat));
			sheet1.addCell(new Label(16, row, vo.getHealthFather(),
					normalRightLastFormat));
			sheet1.addCell(new Label(17, row, vo.getHandicappedDec(),
					normalRightLastFormat));
			sheet1.addCell(new Label(18, row, vo.getPvfCollect(),
					normalRightLastFormat));
			sheet1.addCell(new Label(19, row, vo.getIncomeCollect(),
					normalRightLastFormat));
			sheet1.addCell(new Label(20, row, vo.getIncomeFix(),
					normalRightLastFormat));
			sheet1.addCell(new Label(21, row, vo.getIncomeVariable(),
					normalRightLastFormat));
			sheet1.addCell(new Label(22, row, vo.getTaxCollect(),
					normalRightLastFormat));
			sheet1.addCell(new Label(23, row,vo.getPvfRate(),
					normalRightLastFormat));
			sheet1.addCell(new Label(24, row, vo.getTax(),
					normalRightLastFormat));
		
			
			/*sheet1.addCell(new Label(2, row, vo.getEmpName(),
					normalLeftLastFormat));
			double d = Double.valueOf(vo.getAmount().trim()).doubleValue();
			sheet1.addCell(new Label(3, row, String.valueOf(df.format(d)),
					normalRightLastFormat));*/
		
			row++;
			
		}

		ww.write();
		ww.close();
		wb.close();
		in.close();

		return null;
	
	}
}

/*
 * sheet1.addCell(new Label(0,
 * row,String.valueOf(dfInt.format(seq)),normalRightFormat)); sheet1.addCell(new
 * Label (1,row,vo.getName(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (2,row,vo.getOldPosition(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (3,row,vo.getOldWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (5,row,vo.getEmpCode(),normalCenterFormat)); sheet1.addCell(new Label
 * (6,row,vo.getAccount(),normalCenterFormat)); sheet1.addCell(new Label
 * (7,row,vo.getNewPosition(),normalLeftFormat)); sheet1.addCell(new Label
 * (8,row,vo.getNewWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (10,row,vo.getNewGworkCode(),normalCenterFormat)); sheet1.addCell(new
 * Blank(0, row+1, normalRightFormat)); sheet1.addCell(new Blank(1, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (2,row+1,vo.getOldSec(),normalLeftFormat)); sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (7,row+1,vo.getNewSec(),normalLeftLineFormat)); sheet1.addCell(new Blank(10,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(0, row+2,
 * normalRightFormat)); sheet1.addCell(new Blank(1, row+2, normalCenterFormat));
 * sheet1.addCell(new Label (4,row+2,vo.getOldDiv(),normalLeftFormat));
 * sheet1.addCell(new Blank(5, row+2, normalCenterFormat)); sheet1.addCell(new
 * Blank(6, row+2, normalCenterFormat)); sheet1.addCell(new Label
 * (9,row+2,vo.getNewDiv(),normalLeftFormat)); sheet1.addCell(new Blank(10,
 * row+2, normalCenterFormat)); row=row+3;
 */
/*
 * sheet1.addCell(new Label(0,
 * row,String.valueOf(dfInt.format(seq)),normalRightFormat)); sheet1.addCell(new
 * Label (1,row,vo.getName(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (2,row,vo.getOldPosition(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (3,row,vo.getOldWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (5,row,vo.getEmpCode(),normalCenterFormat)); sheet1.addCell(new Label
 * (6,row,vo.getAccount(),normalCenterFormat)); sheet1.addCell(new Label
 * (7,row,vo.getNewPosition(),normalLeftFormat)); sheet1.addCell(new Label
 * (8,row,vo.getNewWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (10,row,vo.getNewGworkCode(),normalCenterFormat)); //sheet1.addCell(new
 * Blank(0, row+1, normalRightFormat)); //sheet1.addCell(new Blank(1, row+1,
 * normalCenterFormat)); //sheet1.addCell(new Label
 * (2,row+1,vo.getOldSec(),normalLeftFormat)); //sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); //sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); //sheet1.addCell(new Label
 * (7,row+1,vo.getNewSec(),normalLeftLineFormat)); //sheet1.addCell(new
 * Blank(10, row+1, normalCenterFormat)); sheet1.addCell(new Blank(0, row+1,
 * normalRightFormat)); sheet1.addCell(new Blank(1, row+1, normalCenterFormat));
 * sheet1.addCell(new Blank(2, row+1, normalLeftFormat)); sheet1.addCell(new
 * Label (4,row+1,vo.getOldDiv(),normalLeftFormat)); sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (9,row+1,vo.getNewDiv(),normalLeftFormat)); sheet1.addCell(new Blank(10,
 * row+1, normalCenterFormat)); row=row+2;
 */

