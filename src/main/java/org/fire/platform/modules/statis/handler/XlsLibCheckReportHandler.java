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
import org.fire.platform.modules.building.service.IBuildingService;
import org.fire.platform.modules.check.bean.CheckReportBean;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class XlsLibCheckReportHandler {
    static String[] title = new String[]{
    	"ID",
    	"行政区",
    	"街道",
    	"社区",
    	"建筑名称",
    	"场所名称",
    	"报告名称",
    	"报告编码",
    	"检测类型",
    	"报告日期",
    	"报告人电话",
    	"是否通过",
    	"不通过项",
    	"风险指数",
    	"说明",
    	"报告封面",
    	"报告文件地址"
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
	private IBuildingService buildingService;
	
	@Autowired
	private IDictService dictService;

    public   HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<CheckReportBean> xcbs) {
        HSSFSheet sheet = wb.createSheet(sheetName);  // 创建一个Excel的Sheet
        // 创建标题
        createRow(0, sheet, title);
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (CheckReport xcb : xcbs) {
                String[] arr = getFromXcb(xcb);
                createRow(rowIndex++, sheet, arr);
            }
        }
        return sheet;
    }
    
    
    private  String[] getFromXcb(CheckReport xcb) {
        String[] arr = new String[17];
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
        Long  buildingId = xcb.getBuildingId();
        if (buildingId != null) {
			Building building = buildingService.get(buildingId);
			arr[4] = building.getBaseName() == null ? "" : building.getBaseName();
		}
        
        arr[5] = xcb.getPlaceName() == null ? "" : xcb.getPlaceName();
        arr[6] = xcb.getName()== null ? "" : xcb.getName();
        arr[7] = xcb.getCode()== null ? "" : xcb.getCode();
        String reportTypeName = dictService.getByTypeAndCode(xcb.getReportType(), "report_type");
        if (StringUtils.hasText(reportTypeName)) {
       	 arr[8] = reportTypeName;
		}
        arr[9] = xcb.getPubTime() == null ? "" : DateUtil.format(xcb.getPubTime(), "yyyy/MM/dd HH:mm:ss");
       
  
        arr[10] = xcb.getReporterPhone() == null ? "" : xcb.getReporterPhone();
        //（1：通过，0：不通过）
        Integer isPass = xcb.getIsPass();
        if (isPass != null) {
			if (isPass == 1) {
				 arr[11] = "通过";
			}else if(isPass == 0){
				 arr[11] = "不通过";
			}else {
				 arr[11] = "未定义";
			}
		}
        arr[12] = xcb.getUnpassNum()== null ? "" : xcb.getUnpassNum() +"";
        String riskIndexName = dictService.getByTypeAndCode(xcb.getRiskIndex(), "report_risk_index");
        if (StringUtils.hasText(riskIndexName)) {
        	 arr[13] = riskIndexName;
		}
        arr[14] = xcb.getRemark()== null ? "" : xcb.getRemark();
        arr[15] = xcb.getReportImage() == null ? "" : xcb.getReportImage();
        arr[16] = xcb.getReportFileUrl()== null ? "" : xcb.getReportFileUrl();
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
