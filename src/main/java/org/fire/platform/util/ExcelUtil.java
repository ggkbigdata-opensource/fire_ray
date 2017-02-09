package org.fire.platform.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 解析Excel 文件
 * 
 * @author chenpengye 2014年11月4日 上午10:47:48
 */
public class ExcelUtil {

	public static boolean isBlankRow(Row row) {
		if (row == null) {
			return true;
		}

		for (Cell cell : row) {
			if (cell != null
					&& cell.getCellType() != Cell.CELL_TYPE_BLANK
					&& !(cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isBlank(cell.getStringCellValue()))) {
				// 有一个单元格非空
				return false;
			}
		}
		return true;
	}
	
	public static List<List<String>> excelList(String file) throws IOException {
		InputStream fin;
		List<List<String>> rst;
		
		fin = new FileInputStream(file);
		try{
			rst = excelList(fin);
		} finally {
			IOUtils.closeQuietly(fin);
		}
		
		return rst;
	}
	
	public static List<List<String>> excelList(MultipartFile file) throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		InputStream is03 = null;
		InputStream is07 = null;
		
		try{
			is03 = file.getInputStream();
			is07 = file.getInputStream();
			
			Workbook wb = null;
			try{
				wb = new HSSFWorkbook(is03);
			}catch(Exception ex){
				wb = new XSSFWorkbook(is07);
			}finally{
				Sheet sheet = wb.getSheetAt(0);
				int rowLength = 0;
				for (Row row : sheet) {
					if (isBlankRow(row)) {
						continue;
					}
					List<String> stringList = new ArrayList<String>();
					for (Cell cell : row) {
						String str = "";
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
//							str = String.valueOf((long) cell.getNumericCellValue());
							cell.setCellType (Cell.CELL_TYPE_STRING);
							str = cell.toString();
							break;
		
						case HSSFCell.CELL_TYPE_STRING:
							str = cell.getStringCellValue();
							break;
		
						case HSSFCell.CELL_TYPE_BOOLEAN:
							str = cell.getBooleanCellValue() + "";
							break;
		
						case HSSFCell.CELL_TYPE_FORMULA:
							str = cell.getCellFormula();
							break;
						default:
							break;
						}
						Integer n = cell.getColumnIndex() - stringList.size();
						for (int i = 0; i < n; i++) {
							stringList.add("");// some cell is empty
						}
						stringList.add(str);
					}
					if(rowLength == 0){
						rowLength = stringList.size();
					}
					if(stringList.size() < rowLength){
						for (int i = 0; rowLength > stringList.size(); i++) {
							stringList.add("");
						}
					}
					list.add(stringList);
				}
			}
		}catch(Exception ex){
		}finally{
			// 关闭数据流
			IOUtils.closeQuietly(is03);
			IOUtils.closeQuietly(is07);
		}
		
		return list;
	}

	public static List<List<String>> excelList(InputStream input)
			throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowLength = 0;
		for (Row row : sheet) {
			if (isBlankRow(row)) {
				continue;
			}
			List<String> stringList = new ArrayList<String>();
			for (Cell cell : row) {
				String str = "";
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
//					str = String.valueOf((long) cell.getNumericCellValue());
					cell.setCellType (Cell.CELL_TYPE_STRING);
					str = cell.toString();
					break;

				case HSSFCell.CELL_TYPE_STRING:
					str = cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_BOOLEAN:
					str = cell.getBooleanCellValue() + "";
					break;

				case HSSFCell.CELL_TYPE_FORMULA:
					str = cell.getCellFormula();
					break;
				default:
					break;
				}
				Integer n = cell.getColumnIndex() - stringList.size();
				for (int i = 0; i < n; i++) {
					stringList.add("");// some cell is empty
				}
				stringList.add(str);
			}
			if(rowLength == 0){
				rowLength = stringList.size();
			}
			if(stringList.size() < rowLength){
				for (int i = 0; rowLength > stringList.size(); i++) {
					stringList.add("");
				}
			}
			list.add(stringList);
		}
		return list;
	}
	
	/**
	 * 微型消防站一对多导入
	 * @param input
	 * @param isManySheet
	 * @return
	 * @throws IOException
	 */
	public static List<List<List<String>>> excelList(InputStream input,Boolean isManySheet)
			throws IOException {
		List<List<List<String>>> result = new ArrayList<List<List<String>>>();
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		if (isManySheet) {
			int sheetNum = wb.getNumberOfSheets();
			
			for (int a = 0; a <sheetNum; a++) {
				HSSFSheet sheet = wb.getSheetAt(a);
				List<List<String>> list = new ArrayList<List<String>>();
				int rowLength = 0;
				for (Row row : sheet) {
					if (isBlankRow(row)) {
						continue;
					}
					List<String> stringList = new ArrayList<String>();
					for (Cell cell : row) {
						String str = "";
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
//							str = String.valueOf((long) cell.getNumericCellValue());
							cell.setCellType (Cell.CELL_TYPE_STRING);
							str = cell.toString();
							break;

						case HSSFCell.CELL_TYPE_STRING:
							str = cell.getStringCellValue();
							break;

						case HSSFCell.CELL_TYPE_BOOLEAN:
							str = cell.getBooleanCellValue() + "";
							break;

						case HSSFCell.CELL_TYPE_FORMULA:
							str = cell.getCellFormula();
							break;
						default:
							break;
						}
						Integer n = cell.getColumnIndex() - stringList.size();
						for (int i = 0; i < n; i++) {
							stringList.add("");// some cell is empty
						}
						stringList.add(str);
					}
					if(rowLength == 0){
						rowLength = stringList.size();
					}
					if(stringList.size() < rowLength){
						for (int i = 0; rowLength > stringList.size(); i++) {
							stringList.add("");
						}
					}
					list.add(stringList);
				}
				if (list != null && list.size() >0) {
					result.add(list);
				}
			}
		}
		return result;
	}

	public static void createCell(HSSFWorkbook wb, HSSFRow row,
			List<String> list, String type) {

		// 表头样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		String text = "";
		if (type.equals("title")) {
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT
					.getIndex());
		} else if (type.equals("insert")) {
			style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			text = "插入成功";
			if (list.size() == 11) {
				list.remove(10);
				list.add(text);
			} else {
				list.add(text);
			}
		} else if (type.equals("update")) {
			style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
			text = "更新成功";
			if (list.size() == 11) {
				list.remove(10);
				list.add(text);
			} else {
				list.add(text);
			}
		} else if (type.equals("error")) {
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			text = "失败";
			if (list.size() == 11) {
				list.remove(10);
				list.add(text);
			} else {
				list.add(text);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(list.get(i));
			cell.setCellStyle(style);
		}

	}

}
