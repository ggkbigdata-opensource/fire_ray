package org.fire.platform.modules.statis.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XlsLibPunishEventReportHandler {

	@Autowired
	private IDistrictService districtService;

    static String[] title = new String[]{
    	"街道",
    	"行政拘留",
    	"行政拘留",
		"行政罚款",
		"行政罚款",
		"行政罚款",
		"行政罚款",
		"三停",
		"三停",
		"临时查封",
		"临时查封",
    };
	int[] types = new int[]{Cell.CELL_TYPE_STRING,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC,
			Cell.CELL_TYPE_NUMERIC};

    static String[] errTitle = new String[]{
            "id",
            "baseName"
    };

    public HSSFSheet createSheet(String districtId,String monthBegin,String monthEnd,HSSFWorkbook wb, String sheetName, List<AreaTypeDataVo> punishSumDataList) {
		District district = null;
		if (StringUtils.isNotBlank(districtId)){
			district = districtService.get(Long.parseLong(districtId));
		}

        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		//从第一行开始
		int rowIndex = 0;
		HSSFRow row = sheet.createRow(rowIndex);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
		HSSFCell ce = row.createCell(0);
		// 表格的第一行第一列显示的数据  居中合并
		String titleSheet = "消防执法情况";
		if (district != null){
			String districtName = district.getName();
			titleSheet = districtName+"消防执法情况";
			if (StringUtils.isNotBlank(monthBegin) && StringUtils.isNotBlank(monthEnd)){
				titleSheet = monthBegin+"至"+monthEnd+"月份"+districtName+"消防执法情况";
			}
		}
		ce.setCellValue(titleSheet);
		ce.setCellStyle(style);
		String year = "2016";
		if(StringUtils.isNotBlank(monthBegin)){
			 year = monthBegin.split("-")[0]; // 传进来的年份
		}
		// 第二行 列头
		createRow(++rowIndex, sheet, title, style, null);
		//合并列头单元格
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 7, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 9, 10));
		String[] years = {"", year, "同期", year, "元","同期","元", year, "同期", year, "同期"};
		createRow(++rowIndex, sheet, years, style, null);
		if (punishSumDataList != null && punishSumDataList.size() > 0) {
			AreaTypeDataVo totalVo = new AreaTypeDataVo();
			totalVo.setStreetName("全区");
			for (AreaTypeDataVo areaTypeDataVo : punishSumDataList) {

				String[] arr = getFromVo(areaTypeDataVo);
				createRow(++rowIndex, sheet, arr, null,types);
				// 合计
				totalVo.setData1(totalVo.getData1()+areaTypeDataVo.getData1());
				totalVo.setData2(totalVo.getData2()+areaTypeDataVo.getData2());
				totalVo.setData3(totalVo.getData3()+areaTypeDataVo.getData3());
				totalVo.setData4(totalVo.getData4()+areaTypeDataVo.getData4());
				totalVo.setData5(totalVo.getData5()+areaTypeDataVo.getData5());
				totalVo.setData6(totalVo.getData6()+areaTypeDataVo.getData6());
				totalVo.setData7(totalVo.getData7()+areaTypeDataVo.getData7());
				totalVo.setData8(totalVo.getData8()+areaTypeDataVo.getData8());
				totalVo.setData9(totalVo.getData9()+areaTypeDataVo.getData9());
				totalVo.setData10(totalVo.getData10()+areaTypeDataVo.getData10());
			}
			String[] arrTotal = getFromVo(totalVo);
			createRow(++rowIndex, sheet, arrTotal, null,types);
        }
        return sheet;
    }

	private String[] getFromVo(AreaTypeDataVo areaTypeDataVo) {
		String[] arr = new String[11];
		arr[0] = areaTypeDataVo.getStreetName() == null ? "" : areaTypeDataVo.getStreetName();
		arr[1] = areaTypeDataVo.getData1() == null ? "" : "" + areaTypeDataVo.getData1();
		arr[2] = areaTypeDataVo.getData6() == null ? "" : "" + areaTypeDataVo.getData6();
		arr[3] = areaTypeDataVo.getData2() == null ? "" : "" + areaTypeDataVo.getData2();
		arr[4] = areaTypeDataVo.getData5() == null ? "" : "" + areaTypeDataVo.getData5();
		arr[5] = areaTypeDataVo.getData7() == null ? "" : "" + areaTypeDataVo.getData7();
		arr[6] = areaTypeDataVo.getData10() == null ? "" : "" + areaTypeDataVo.getData10();
		arr[7] = areaTypeDataVo.getData3() == null ? "" : "" + areaTypeDataVo.getData3();
		arr[8] = areaTypeDataVo.getData8() == null ? "" : "" + areaTypeDataVo.getData8();
		arr[9] = areaTypeDataVo.getData4() == null ? "" : "" + areaTypeDataVo.getData4();
		arr[10] = areaTypeDataVo.getData9() == null ? "" : "" + areaTypeDataVo.getData9();
		return arr;
	}


    private static HSSFRow createRow(int index, HSSFSheet sheet, String[] arr, CellStyle style, int[] types) {
        HSSFRow row = sheet.createRow(index);
        for (int i = 0; i < arr.length; i++) {
            HSSFCell cell = row.createCell(i);
			if (types != null ){
				cell.setCellType(types[i]);
				setCellValue(cell,types[i],arr[i]);
			}else{
				cell.setCellValue(arr[i]);
			}
            if(style != null){
            	cell.setCellStyle(style);
			}

        }
		return row;
    }

	/**
	 * 导出数据数字类型格式化
	 * @param cell
	 * @param type
	 * @param value
     */
	private static void setCellValue(HSSFCell cell,int type,Object value){
		switch (type){
			case Cell.CELL_TYPE_NUMERIC:
				String v = String.valueOf(value);
				if (value instanceof Integer){
					cell.setCellValue(Integer.parseInt(v));
				}else if(value instanceof Long){
					cell.setCellValue(Long.parseLong(v));
				}else{
					cell.setCellValue(Double.parseDouble(v));
				}
				break;
			default:
				cell.setCellValue(String.valueOf(value));
				break;
		}
	}

    /**
     * 打印错误信息到Excel中
     * @param wb
     * @param message
     * @return
     */
    public static HSSFSheet createExceptionSheet(HSSFWorkbook wb, String message) {
        HSSFSheet sheet = wb.createSheet("错误信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("导出报表失败,请联系管理员");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(message);
        createRow(2, sheet, errTitle,null, null);
        HSSFRow row2 = sheet.createRow(3);
        cell = row2.createCell(0);
        return sheet;
    }
}
