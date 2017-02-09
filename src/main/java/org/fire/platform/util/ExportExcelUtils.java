package org.fire.platform.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ExportExcelUtils {

	public static ResponseEntity<byte[]> downloadExam(List<String[]> list,
			String wbTitle, HttpServletRequest request) {
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet(wbTitle);
		// sheet.setColumnWidth(0,8000);//列宽
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(20);
		HSSFRow titleRow = sheet.createRow(0);

		// 创建标题行
		createCell(titleRow, list.get(0));

		for (int i = 1; i < list.size(); i++) {
			sheet.setColumnWidth(i, 10000);
			String[] arr = list.get(i);
			HSSFRow row = sheet.createRow(i);
			createCell(row, arr);
		}

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] bytes = new byte[0];
			try {
				wb.write(out);
				bytes = out.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			String userAgent = request.getHeader("User-Agent");

			String filename = DownloadFileNameUtils.getAttachmentFileName(
					userAgent, wbTitle, "", "xls");
			headers.setContentDispositionFormData("attachment", filename);
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ResponseEntity<byte[]> createDownloadExcel(String wbTitle,
			HSSFWorkbook wb, HttpServletRequest request) {
		 ResponseEntity<byte[]> rst = null;
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] bytes = new byte[0];
			try {
				wb.write(out);
				bytes = out.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			FileOutputStream fos = new FileOutputStream("D://test.xls");
//			fos.write(bytes);
//			fos.flush();
//			fos.close();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			String userAgent = request.getHeader("User-Agent");
			String filename = DownloadFileNameUtils.getAttachmentFileName(
					userAgent, wbTitle, "", "xls");
			headers.setContentDispositionFormData("attachment", filename);
			rst = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rst;
	}

	/**
	 * 创建标题
	 * @param wb
	 * @param row
	 * @param list
	 * @param titleList
	 * @param isQuizTest
	 */
	public static void createTitleCell(HSSFWorkbook wb, HSSFRow row,
			String[] cellValues, Integer[] cellWidths) {
		if (cellValues == null || cellValues.length == 0)
			return;

		int len = cellValues.length;
		for (int i = 0; i < len; i++) {
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setBottomBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setLeftBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setRightBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			
			Font font = wb.createFont();
			font.setColor(IndexedColors.WHITE.getIndex());
			style.setFont(font);
			
			HSSFSheet sheet = row.getSheet();
			
			if(cellWidths[i] != null){
				sheet.setColumnWidth(i, cellWidths[i]);
			}
			/*sheet.setColumnWidth(titleList.indexOf(CHOICE), 12000);*/

			HSSFCell cell = row.createCell(i);
			cell.setCellValue(cellValues[i]);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style);
			
		}

	}
	
	/**
	 * 
	 * @param wb
	 * @param row
	 * @param cellValues
	 * @param cellWidths
	 */
	public static void createTitleCell(HSSFWorkbook wb, HSSFRow row,
			List<String> cellValues, List<Integer> cellWidths) {
		if (cellValues == null)
			return;

		int len = cellValues.size();
		if(len == 0){
			return;
		}
		
		for (int i = 0; i < len; i++) {
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setBottomBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setLeftBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setRightBorderColor(IndexedColors.PALE_BLUE.getIndex());
			style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			
			Font font = wb.createFont();
			font.setColor(IndexedColors.WHITE.getIndex());
			style.setFont(font);
			
			HSSFSheet sheet = row.getSheet();
			
			if(cellWidths.get(i) != null){
				sheet.setColumnWidth(i, cellWidths.get(i));
			}
			/*sheet.setColumnWidth(titleList.indexOf(CHOICE), 12000);*/

			HSSFCell cell = row.createCell(i);
			cell.setCellValue(cellValues.get(i));
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style);
			
		}

	}

	/**
	 * 创建Excel行的单元格
	 * 
	 * @param row
	 * @param cellValues
	 */
	public static void createCell(HSSFRow row, String[] cellValues) {
		if (cellValues == null || cellValues.length == 0)
			return;

		int len = cellValues.length;
		for (int i = 0; i < len; i++) {
			HSSFCell cell1 = row.createCell(i);
			cell1.setCellValue(cellValues[i]);
		}
	}
	
	public static void createCell(HSSFRow row, List<String> cellValues) {
		if (cellValues == null )
			return;
		
		int len = cellValues.size();
		if(len == 0)
			return;
		
		for (int i = 0; i < len; i++) {
			HSSFCell cell1 = row.createCell(i);
			cell1.setCellValue(cellValues.get(i));
		}
	}

}
