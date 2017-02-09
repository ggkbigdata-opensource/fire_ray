package org.fire.platform.modules.statis.handler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class XlsLibBlockHandler {
    static String[] title = new String[]{
    		"ID",
    		"行政区",
    		"街道",
    		"类型",
            "名称",
            "编码",
            "经度",
            "纬度",
            "面积",
            "人口",
            "说明",
            "时间"
    };

    static String[] errTitle = new String[]{
            "id",
            "name"
    };
    
    @Autowired
    private  IDistrictService districtService;
    
    @Autowired
    private  IStreetService streetService;
    
    @Autowired
    private IDictService dictService;

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<Block> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (Block xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(Block xcb) {
        String[] arr = new String[12];
        arr[0] = xcb.getId() == null ? "" : xcb.getId().toString();
        Long disId = xcb.getDistrictId();
        if (disId != null) {
			District dis = districtService.get(disId);
			if (dis != null) {
				 arr[1] = dis.getName() == null ? "" : dis.getName();
			}
		}
        Long streetId = xcb.getStreetId();
        if (streetId != null) {
			Street street = streetService.get(streetId);
			if (street != null) {
				 arr[2] = street.getName() == null ? "" : street.getName();
			}
		}
        String blockTypeName = dictService.getByTypeAndCode(xcb.getBlockType(), "block_type");
        if (StringUtils.hasText(blockTypeName)) {
        	 arr[3] = blockTypeName;
		}
        arr[4] = xcb.getName() == null ? "" : xcb.getName();
        arr[5] = xcb.getCode() == null ? "" : xcb.getCode();
        arr[6] = xcb.getLongitude() == null ? "" : xcb.getLongitude() +"";
        arr[7] = xcb.getLatitude() == null ? "" : xcb.getLatitude() +"";
        arr[8] = xcb.getCoverArea()== null ? "" : xcb.getCoverArea() +"";
        arr[9] = xcb.getPopulation() == null ? "" : xcb.getPopulation() +"";
        arr[10] = xcb.getRemark() == null ? "" : xcb.getRemark();
        arr[11] = xcb.getModTime() == null ? "" : DateUtil.format(xcb.getModTime(), "yyyy/MM/dd HH:mm:ss");
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
