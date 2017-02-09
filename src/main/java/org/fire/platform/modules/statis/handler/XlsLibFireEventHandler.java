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
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class XlsLibFireEventHandler {
    static String[] title = new String[]{
    	"ID",
    	"行政区",
    	"街道",
    	"社区",
			"城市区域",

    	"建筑名称",
    	"火灾事故名称",
    	"场所名称",
    	"案号",
    	"消防手续",
    	"地理位置",
    	"工程性质",
    	"使用性质",
			"企业性质",
    	"火灾类型",
			"现场警情",
    	"案情",
    	"起火位置",
    	"起火物",
    	"起火原因分类",
    	"起火原因",
    	"发生时间",
    	"经济损失",
    	"死亡人数",
    	"受伤人数",
			"值班组",
			"微型消防站是否参与",
			"消防执法案号",
			"是否自救",
			"移交部门",
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

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<FireEvent> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (FireEvent xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(FireEvent xcb) {
        String[] arr = new String[30];
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
			if (blockId != null) {
				 arr[3] = block.getName() == null ? "" : block.getName();
			}
		}
          Long buildingId = xcb.getBuildingId();
        if (buildingId != null) {
			BuildingSubject building = buildingSubjectService.get(buildingId);
			if (building != null) {
				 arr[4] = building.getOwnerUnitName() == null ? "" : building.getOwnerUnitName();
			}
		}
        arr[5] = xcb.getPlaceName()== null ? "" : xcb.getPlaceName();
        arr[6] = xcb.getName() == null ? "" : xcb.getName();
        arr[7] = xcb.getCaseNumber() == null ? "" : xcb.getCaseNumber();
    	//消防手续 place_fire_type
        String placeFireTypeName = dictService.getByTypeAndCode(xcb.getPlaceFireType(), "place_fire_type");
        if (StringUtils.hasText(placeFireTypeName)) {
        	 arr[8] = placeFireTypeName;
		}
        
    	// 地理位置 place_position_type
		String placePositionTypeName =dictService.getByTypeAndCode(xcb.getPlacePositionType(), "place_position_type");
		 if (StringUtils.hasText(placePositionTypeName)) {
        	 arr[9] = placePositionTypeName;
		}
		
		// 工程性质 place_build_type
		String placeBuildTypeName = dictService.getByTypeAndCode(xcb.getPlaceBuildType(), "place_build_type");
		 if (StringUtils.hasText(placeBuildTypeName)) {
        	 arr[10] = placeBuildTypeName;
		}
		
	   //使用性质（酒店、厂房、学校、仓库、办公室）	 place_use_type
		String placeUseTypeName = dictService.getByTypeAndCode(xcb.getPlaceUseType(), "place_use_type");
		 if (StringUtils.hasText(placeUseTypeName)) {
        	 arr[11] = placeUseTypeName;
		}
		
		//火灾类型（冒烟警情、确认警情、原始警情） fire_type
		String fireTypeName = dictService.getByTypeAndCode(xcb.getFireType(), "fire_type");
		 if (StringUtils.hasText(fireTypeName)) {
        	 arr[12] = fireTypeName;
		}
	    arr[13] = xcb.getDescription()== null ? "" : xcb.getDescription();
	    arr[14] = xcb.getFirePosition()== null ? "" : xcb.getFirePosition();
	    arr[15] = xcb.getFireObject() == null ? "" : xcb.getFireObject();
		//起火原因分类  fire_reason_type
		String fireReasonTypeName = dictService.getByTypeAndCode(xcb.getFireReasonType(), "fire_reason_type");
		 if (StringUtils.hasText(fireReasonTypeName)) {
        	 arr[16] = fireReasonTypeName;
		}
	    arr[17] = xcb.getFireReason() == null ? "" : xcb.getFireReason();
	    arr[18] = xcb.getOccurTime() == null ? "" : DateUtil.format(xcb.getOccurTime(), "yyyy/MM/dd HH:mm:ss");
        arr[19] = xcb.getLoss()== null ? "" : xcb.getLoss()+"";
        arr[20] = xcb.getDeadNum() == null ? "" : xcb.getDeadNum()+"";
        arr[21] = xcb.getHurtNum() == null ? "" : xcb.getHurtNum()+"";
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
