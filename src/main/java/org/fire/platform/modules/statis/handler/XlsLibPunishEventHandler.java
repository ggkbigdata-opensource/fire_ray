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
import org.fire.platform.modules.building.domain.BuildingSubject;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class XlsLibPunishEventHandler {
    static String[] title = new String[]{
    	"执法ID",
    	"行政区",
    	"街道",
    	"社区",
    	"建筑名称",
    	"场所名称",
    	"执法名称",
    	"执法编码",
    	"发生时间",
    	"处罚方式",
    	"责任人",
    	"金额",
    	"处罚说明",
    	"执法人名称",
    	"原因",
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
	private IBuildingSubjectService buildingSubjectService;
	
	@Autowired
	private IDictService dictService;

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<PunishEvent> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (PunishEvent xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(PunishEvent xcb) {
        String[] arr = new String[15];
        arr[0] = xcb.getId() == null ? "" : xcb.getId().toString();
        Long districtId = xcb.getDistrictId();
        if (districtId != null) {
			District district = districtService.get(districtId);
			if (district != null) {
				 arr[1] = district.getName() == null ? "" : district.getName();
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
        Long  buildingId = xcb.getBuildingId();
        if (buildingId != null) {
			BuildingSubject building = buildingSubjectService.get(buildingId);
			arr[4] = building.getOwnerUnitName() == null ? "" : building.getOwnerUnitName();
		}
        arr[5] = xcb.getPlaceName() == null ? "" : xcb.getPlaceName();
        arr[6] = xcb.getName()== null ? "" : xcb.getName();
        arr[7] = xcb.getPunishNumber()== null ? "" : xcb.getPunishNumber();
        arr[8] = xcb.getPunishTime() == null ? "" : DateUtil.format(xcb.getPunishTime(), "yyyy/MM/dd HH:mm:ss");
        
        String punishTypeName = dictService.getByTypeAndCode(xcb.getPunishType(), "punish_type");
        if (StringUtils.hasText(punishTypeName)) {
        	 arr[9] = punishTypeName;
		}
        arr[10] = xcb.getPlaceOwner()== null ? "" : xcb.getPlaceOwner();
        arr[11] = xcb.getPunishAmount()== null ? "" : xcb.getPunishAmount()+"";
        arr[12] = xcb.getRemark() == null ? "" : xcb.getRemark();
        arr[13] = xcb.getPunishPersonName()== null ? "" : xcb.getPunishPersonName() +"";
//        arr[14] = xcb.getReason()== null ? "" : xcb.getReason();
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
