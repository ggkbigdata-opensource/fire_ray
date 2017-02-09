package org.fire.platform.modules.statis.handler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class XlsLibBuildingHandler {
    static String[] title = new String[]{
    	"ID",
    	"行政区",
    	"街道",
    	"社区",
    	"名称",
    	"建筑编码",
    	"建筑类别",
    	"级别",
    	"工程类别",
    	"地址",
    	"经度",
    	"维度",
    	"竣工时间",
    	"消防责任人",
    	"消防责任人电话",
    	"消防联系人",
    	"消防联系电话",
    	"建筑面积",
    	"建筑高度",
    	"占地面积",
    	"地表层数",
     	"地下层数",
    	"说明"
    };

    static String[] errTitle = new String[]{
            "id",
            "baseName"
    };
    
    @Autowired
    private  IDistrictService districtService;
    
    @Autowired
	private IStreetService streetService;
	
	@Autowired
	private IBlockService blockService;
	
	@Autowired
	private IDictService dictService;

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<Building> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (Building xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(Building xcb) {
        String[] arr = new String[23];
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
        
        Long blockId = xcb.getBlockId();
        if (blockId != null) {
			Block block = blockService.get(blockId);
			if (block != null) {
				 arr[3] = block.getName() == null ? "" : block.getName();
			}
		}
        arr[4] = xcb.getBaseName() == null ? "" : xcb.getBaseName();
        arr[5] = xcb.getBaseCode()== null ? "" : xcb.getBaseCode();
        String buildingClassName = dictService.getByTypeAndCode(xcb.getBaseBuildingClass(), "building_class");
        if (StringUtils.hasText(buildingClassName)) {
        	 arr[6] = buildingClassName;
		}
        
        String levelName = dictService.getByTypeAndCode(xcb.getBaseLevel(), "building_level");
        if (StringUtils.hasText(levelName)) {
        	 arr[7] = levelName;
		}
        
        String conTypeName = dictService.getByTypeAndCode(xcb.getConType(), "building_con_type");
        if (StringUtils.hasText(conTypeName)) {
        	 arr[8] = conTypeName;
		}
        arr[9] = xcb.getBaseAddress()== null ? "" : xcb.getBaseAddress();
        
        arr[10] = xcb.getLongitude() == null ? "" : xcb.getLongitude()+"";
        arr[11] = xcb.getLatitude() == null ? "" : xcb.getLongitude() +"";
        arr[12] = xcb.getFinishTime() == null ? "" : DateUtil.format(xcb.getFinishTime(), "yyyy/MM/dd HH:mm:ss");
        
        arr[13] = xcb.getFireManager()== null ? "" : xcb.getFireManager();
        arr[14] = xcb.getFireManagerPhone()== null ? "" : xcb.getFireManagerPhone();
        arr[15] = xcb.getFireContact() == null ? "" : xcb.getFireContact();
        arr[16] = xcb.getFireContactPhone() == null ? "" : xcb.getFireContactPhone();
        arr[17] = xcb.getConBuildArea() == null ? "" : xcb.getConBuildArea()+"";
        arr[18] = xcb.getConBuildHight() == null ? "" : xcb.getConBuildHight()+"";
        arr[19] = xcb.getConCoverArea() == null ? "" : xcb.getConCoverArea()+"";
        arr[20] = xcb.getConFloors() == null ? "" : xcb.getConFloors()+"";
        arr[21] = xcb.getConUnderFloors() == null ? "" : xcb.getConUnderFloors()+"";
        arr[22] = xcb.getBaseRemark() == null ? "" : xcb.getBaseRemark();
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
