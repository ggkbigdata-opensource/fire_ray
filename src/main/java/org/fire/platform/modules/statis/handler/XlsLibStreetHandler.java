package org.fire.platform.modules.statis.handler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XlsLibStreetHandler {
    static String[] title = new String[]{
    		"街道ID",
            "名称",
            "行政区",
            "说明",
            "经度",
            "纬度",
            "面积",
            "人口",
            "时间"
    };

    static String[] errTitle = new String[]{
            "id",
            "name"
    };
    
    @Autowired
    private  IDistrictService districtService;

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<Street> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (Street xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(Street xcb) {
        String[] arr = new String[9];
        arr[0] = xcb.getId() == null ? "" : xcb.getId().toString();
        arr[1] = xcb.getName() == null ? "" : xcb.getName();
        Long disId = xcb.getDistrictId();
        if (disId != null) {
			District dis = districtService.get(disId);
			if (dis != null) {
				 arr[2] = dis.getName() == null ? "" : dis.getName();
			}
		}
        arr[3] = xcb.getRemark() == null ? "" : xcb.getRemark();
        arr[4] = xcb.getLongitude() == null ? "" : xcb.getLongitude() +"";
        arr[5] = xcb.getLatitude() == null ? "" : xcb.getLatitude() +"";
        arr[6] = xcb.getCoverArea()== null ? "" : xcb.getCoverArea() +"";
        arr[7] = xcb.getPopulation() == null ? "" : xcb.getPopulation() +"";
        arr[8] = xcb.getModDate() == null ? "" : DateUtil.format(xcb.getModDate(), "yyyy/MM/dd HH:mm:ss");
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
        cell.setCellValue("导出街道清单失败,请联系管理员");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(message);
        createRow(2, sheet, errTitle);
        HSSFRow row2 = sheet.createRow(3);
        cell = row2.createCell(0);
        return sheet;
    }
}
