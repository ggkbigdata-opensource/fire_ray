package org.fire.platform.modules.statis.handler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class XlsLibDistrictHandler {
    static String[] title = new String[]{
    		"ID",
            "行政区名称",
            "编码",
            "说明",
            "面积",
            "人口",
            "时间"
    };

    static String[] errTitle = new String[]{
            "id",
            "name"
    };
    
    public   HSSFSheet createDistrict(HSSFWorkbook wb, String sheetName, List<District> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (District xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(District xcb) {
        String[] arr = new String[7];
        arr[0] = xcb.getId() == null ? "" : xcb.getId().toString();
        arr[1] = xcb.getName() == null ? "" : xcb.getName();
        arr[2] = xcb.getCode() == null ? "" : xcb.getCode();
        arr[3] = xcb.getRemark() == null ? "" : xcb.getRemark();
        arr[4] = xcb.getCoverArea()== null ? "" : xcb.getCoverArea() +"";
        arr[5] = xcb.getPopulation() == null ? "" : xcb.getPopulation() +"";
        arr[6] = xcb.getModDate() == null ? "" : DateUtil.format(xcb.getModDate(), "yyyy/MM/dd HH:mm:ss");
        return arr;
    }

    private static HSSFRow createRow(int index, HSSFSheet sheet, String[] arr) {
        HSSFRow row = sheet.createRow(index);
        for (int i = 0; i < arr.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(arr[i]);
        }
        return row;
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
        cell.setCellValue("导出清单失败,请联系管理员");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(message);
        createRow(2, sheet, errTitle);
        HSSFRow row2 = sheet.createRow(3);
        cell = row2.createCell(0);
        return sheet;
    }
}
