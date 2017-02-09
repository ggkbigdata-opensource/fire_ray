package org.fire.platform.modules.statis.handler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.fire.platform.modules.area.bean.Equipment;
import org.fire.platform.modules.area.bean.Office;
import org.fire.platform.modules.area.bean.Vehicle;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class XlsLibFireStationHandler {
    static String[] title = new String[]{
    	"ID",
    	"行政区名称",
    	"街道名称",
    	"社区名称",
    	"消防站名称",
    	"编码",
    	"居委会主任",
    	"居委会电话",
    	"站长",
    	"站长电话",
    	"实用面积",
    	"是否应急支援服务救援站",
    	"居委会地址",
    	"消防站地址",
    	"说明"
    };
    
    static String[] infoTitle = new String[]{
    	"姓名",
    	"职务",
    	"",
    	"车辆类型",
    	"泡沫装载量",
    	"载水量",
    	"数量",
    	"",
    	"器材名称",
    	"数量"
    	
    };

    static String[] errTitle = new String[]{
            "id",
            "name"
    };
    
    @Autowired
    private  IDistrictService districtService;
    
    @Autowired
	private IStreetService streetService;
	
	@Autowired
	private IBlockService blockService;
	
	@Autowired
	private IDictService dictService;

	/**
	 * 一条记录在一个sheet
	 * @param wb
	 * @param xcbs
	 */
    public   void createSheet(HSSFWorkbook wb, List<FireStation> xcbs) {
        if (xcbs != null && xcbs.size() > 0) {
            int rowIndex = 1;
            for (FireStation xcb : xcbs) {
        	   HSSFSheet sheet = wb.createSheet(xcb.getName()+"");  // 创建一个Excel的Sheet
       		// 设置表格默认列宽度为20个字节
       		sheet.setDefaultColumnWidth((short)15);
        	// 生成一个样式
       		HSSFCellStyle style = wb.createCellStyle();
       		// 设置这些样式
       		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
       		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
       		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
       		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       		// 生成一个字体
       		HSSFFont font = wb.createFont();
       		font.setColor(HSSFColor.BLACK.index);
       		font.setFontHeightInPoints((short) 12);
       		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       		// 把字体应用到当前的样式
       		style.setFont(font);
       		// 指定当单元格内容显示不下时自动换行
       		style.setWrapText(true);
               // 创建标题
               createRow(0, sheet, title,style);
                String[] arr = getFromXcb(xcb);
                // 都放在第二行展示基本消防站信息
                createRow(rowIndex, sheet, arr,style);
                // 成员信息
                createRow(3, sheet,infoTitle,style);
                // 成员、车辆、设备数据从第四行开始写入
                int rowInfoIndex = 4;
                
                Gson gson = new Gson();
                
                String officeJson = xcb.getOffice();
            	List<Office> offices = gson.fromJson(officeJson, new TypeToken<List<Office>>() {}.getType());
            	
            	String vehicleJson = xcb.getVehicle();
            	List<Vehicle> vehicles = gson.fromJson(vehicleJson, new TypeToken<List<Vehicle>>() {}.getType());
            	
            	String equipmentJson = xcb.getEquipment();
            	List<Equipment> equipments = gson.fromJson(equipmentJson, new TypeToken<List<Equipment>>() {}.getType());
            	
            	// 循环啊!!卖萌贼溜!!
        		boolean hasNext = true;
        		int i = 0;
        		while (hasNext){
        			int nullNum = 0;
        			String[] strings = new String[10];
        			if (offices.size()>i) {
        				if (offices.get(i)!= null) {
            				strings[0] = offices.get(i).getOfficeName();
            				strings[1] = offices.get(i).getBusiness();
            			}
					}else{
        				nullNum ++;
        			}
        			
        			if (vehicles.size() > i) {
        				if (vehicles.get(i)!= null) {
            				strings[3] = vehicles.get(i).getVehicleType();
            				strings[4] = vehicles.get(i).getFoamLoadinge();
            				strings[5] = vehicles.get(i).getWaterAmount();
            				strings[6] = vehicles.get(i).getAmount();
            			}
					}else{
        				nullNum ++;
        			}
        			if (equipments.size() > i) {
        				if (equipments.get(i)!= null) {
            				strings[8] = equipments.get(i).getEquipmentName();
            				strings[9] = equipments.get(i).getEquipmentNum();
            			}
					}else{
        				nullNum ++;
        			}
        			if(nullNum == 3){
        				hasNext = false;
        			}
        			i++;
        			createRow(rowInfoIndex++, sheet, strings,style);
        		}
            }
        }
    }
    
    
    private  String[] getFromXcb(FireStation xcb) {
        String[] arr = new String[15];
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
        arr[4] = xcb.getName() == null ? "" : xcb.getName();
        arr[5] = xcb.getCode()== null ? "" : xcb.getCode();
        
        arr[6] = xcb.getCommitteesDirector()== null ? "" : xcb.getCommitteesDirector();
        arr[7] = xcb.getCommitteesPhone()== null ? "" : xcb.getCommitteesPhone();
        arr[8] = xcb.getStationMaster()== null ? "" : xcb.getStationMaster();
        arr[9] = xcb.getStationPhone()== null ? "" : xcb.getStationPhone();
        
        arr[10] = xcb.getArea() == null ? "" : xcb.getArea()+"";
        
        String emergencyServiceName = dictService.getByTypeAndCode(xcb.getEmergencyService(), "emergency_service");
        if (StringUtils.hasText(emergencyServiceName)) {
        	 arr[11] = emergencyServiceName;
		}
        
        
        arr[12] = xcb.getCommitteesAddress()== null ? "" : xcb.getCommitteesAddress();
        arr[13] = xcb.getStationAddress()== null ? "" : xcb.getStationAddress();
        
        
        arr[14] = xcb.getRemark()== null ? "" : xcb.getRemark();
        return arr;
    }
    
//    private  String[] getOfficeFromXcb(Office xcb) {
//        String[] arr = new String[2];
//        arr[0] = xcb.getOfficeName() == null ? "" : xcb.getOfficeName();
//        arr[1] = xcb.getBusiness()== null ? "" : xcb.getBusiness();
//        return arr;
//    }
//    
//    private  String[] getVehicleFromXcb(Vehicle xcb) {
//        String[] arr = new String[4];
//        arr[0] = xcb.getVehicleType()== null ? "" : xcb.getVehicleType();
//        arr[1] = xcb.getFoamLoadinge()== null ? "" : xcb.getFoamLoadinge();
//        arr[2] = xcb.getWaterAmount()== null ? "" : xcb.getWaterAmount();
//        arr[3] = xcb.getAmount()== null ? "" : xcb.getAmount();
//        return arr;
//    }

    private static HSSFRow createRow(int index, HSSFSheet sheet, String[] arr,HSSFCellStyle style) {
        HSSFRow row = sheet.createRow(index);
        for (int i = 0; i < arr.length; i++) {
            HSSFCell cell = row.createCell(i);
            // 标题设置样式
            if (index == 0) {
            	 cell.setCellStyle(style);
			}else if(index== 3){
				if (i !=2 && i!=7) {
          		  cell.setCellStyle(style);
				}
			}
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
        createRow(2, sheet, errTitle,null);
        HSSFRow row2 = sheet.createRow(3);
        cell = row2.createCell(0);
        return sheet;
    }
}
