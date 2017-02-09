package org.fire.platform.modules.area.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.BlockBean;
import org.fire.platform.modules.area.bean.EasyuiAreaTreeNode;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.service.IBuildingService;
import org.fire.platform.modules.statis.handler.XlsLibBlockHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.ChineseToEnglish;
import org.fire.platform.util.DateUtil;
import org.fire.platform.util.ExcelUtil;
import org.fire.platform.util.ExportExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

@Controller
@RequestMapping("area/block")
public class BlockController{

	@Autowired
	private IBlockService service;
	
	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private IDictService dictService;
	
	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private XlsLibBlockHandler xlsLibBlockHandler;
	
	private static Logger log = LoggerFactory.getLogger(BlockController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String remark,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String blockType
			){
	    log.info("query,page={},pageSize={},name={},remark={},streetId={}",pageNo,pageSize,name,remark,streetId);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(remark)) {
			map.put("remark", remark);
		}
		if (StringUtils.hasText(streetId)  && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(districtId)  && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (StringUtils.hasText(blockType) && !blockType.equals("0")) {
			map.put("blockType", blockType);
		}
		map.put("extraOrderColumns", " mod_time DESC ");
	    PageInfo<BlockBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
	  	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	   		data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}
	
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(
			HttpSession session,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String remark,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String blockType
		){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(remark)) {
			map.put("remark", remark);
		}
		if (StringUtils.hasText(streetId)  && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(districtId)  && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (StringUtils.hasText(blockType) && !blockType.equals("0")) {
			map.put("blockType", blockType);
		}
		map.put("extraOrderColumns", " mod_time DESC ");
		log.info("queryAll,name={},remark={},districtId={}",name,remark,districtId);
		List<Block> list  = service.queryByMap(map);
		Map<String, Object> data = new HashMap<String, Object>();
	  	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	   		data.put("total",list.size());
			data.put("rows", list);
		}
		return data;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Block get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(Block bean,HttpSession session){
	     log.info("create,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("新增失败，请登陆"); 
	  		      return CommonResult.fail("新增失败，请登陆");
			}
	    	 Long sqlId = service.queryBeanByName(bean.getName());
	    	if (sqlId != null) {
	    		   log.error("新增失败，已存在该数据"); 
	    		   return CommonResult.fail("新增失败，已存在该数据");
			}
	    	Long streetId = bean.getStreetId();
	    	if (streetId != null) {
				Street street = streetService.get(streetId);
				String streetCode = street.getCode();
				if (StringUtils.hasText(streetCode) && StringUtils.hasText(bean.getName())) {
		    		bean.setCode(streetCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()));
				}
				Long districtId = street.getDistrictId();
				bean.setDistrictId(districtId);
			}
	    	bean.setModUser(user.getUid());
	    	bean.setModTime(new Date());
	    	bean.setState(1);
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(Block bean,HttpSession session){
	     log.info("update,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("修改失败，请登陆"); 
	  		      return CommonResult.fail("修改失败，请登陆");
			}
    	 Long streetId = bean.getStreetId();
	    	if (streetId != null) {
				Street street = streetService.get(streetId);
				String streetCode = street.getCode();
				if (StringUtils.hasText(streetCode) && StringUtils.hasText(bean.getName())) {
		    		bean.setCode(streetCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()));
				}
			}
	    	bean.setModUser(user.getUid());
	    	bean.setModTime(new Date());
	    	bean.setState(1);
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("修改失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id,HttpSession session){
	     log.info("delete,id={}",id);
	     try{
	    	 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("删除失败，请登陆"); 
	  		      return CommonResult.fail("删除失败，请登陆");
			}
		    service.delete(id);
		    return CommonResult.success("删除成功");
		 }catch(Exception e){
		    log.error("删除失败",e); 
		    return CommonResult.fail("删除失败");
		 }
	 }
	 
	@RequestMapping(value = "/deleteDataByIds", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delDataByIds(String deleteIds,HttpSession session) {
		log.info("delete,deleteIds={}",deleteIds);
		int ok = 0;
		int no = 0;
		List<String> deleteIdsList = Arrays.asList(deleteIds.split(",")); 
		if(deleteIdsList==null || deleteIdsList.size()==0){
			 return CommonResult.fail("批量删除失败");
		}
		
		 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
   		  log.error("批量删除失败，请登陆"); 
 		      return CommonResult.fail("批量删除失败，请登陆");
		}
		for(String id: deleteIdsList) {
			// 查询是否有关联的建筑数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("blockId", id);
			List<Building> lists = buildingService.queryByMap(map);
			if (lists != null && lists.size() > 0) {
				  no++;
				  log.error("批量删除失败，关联有建筑数据，请先去掉关联建筑再删除！"); 
//	 		      return CommonResult.fail("批量删除失败，关联有街道数据，请先去掉关联社区再删除！");
			}else{
				int res =  service.delete(Long.parseLong(id));
				if(res>0){
					ok++;
				}
			}
		}
		  return CommonResult.success("删除成功记录："+ok+"条"+"，删除失败记录："+no+" 条，请检查建筑关联数据！");
	}
	
	/**
	 * 导入数据
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/importStreet" , method = RequestMethod.POST)
	@ResponseBody CommonResult batchImportStreet(@RequestParam(value = "file") MultipartFile file,HttpSession session) throws IOException{
		// 获取当前登录用户
		 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		 if (user == null) {
			 log.error("导入失败，请登陆"); 
 		      return CommonResult.fail("导入失败，请登陆");
		}
		//如果是2007版本以上的文件HSSF无法解析
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!"xls".equals(extension)) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		//转换文档
		List<List<String>> dataList = ExcelUtil.excelList(file.getInputStream());
		if (dataList == null || dataList.isEmpty() || dataList.size() == 2) {
			return CommonResult.fail("Excel文件内容为空");
		}
		//检查格式
//			0行政区名称(请查看上面注意事项再导入)	1区域名称（必填不重复）	2经度	3纬度	4面积（例：3平方公里）	5人口（例：30W）	6说明
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2016_10_11_block")){
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		if (!title.get(0).startsWith("注意事项:")) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		// 删除第一行表头说明
    	 dataList.remove(0);
		// 删除第二行
	 	dataList.remove(0);
	 	
		//检查重复的名称
		List<String> errorList = new ArrayList<>();
		Set<String> nameSet = new TreeSet<>();
		int countInsert = 0;
		int countUpdate = 0;
		Long begin = System.currentTimeMillis();
		for(List<String> cellValue  : dataList){
			Long districtId = districtService.queryBeanByName(cellValue.get(0).trim());
			Long streetId = streetService.queryBeanByName(cellValue.get(1).trim());
			// 名称
			String name = cellValue.get(2).trim();
			if(districtId != null){
				if (streetId != null) {
					Street street = streetService.get(streetId);
					String streetCode = street.getCode();
					String code = "";
					if (StringUtils.hasText(streetCode) && StringUtils.hasText(name)) {
						code = streetCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(name);
					}
					if (StringUtils.hasText(name)) {
						if (!nameSet.add(name)) {
							errorList.add("出现重复的社区名称[" + name + "]，请检查所有社区名称不重复再上传Excel");
						}
					} else {
						errorList.add("社区名称不能为空，请确保Excel内容格式正确再上传");
					}
					
					//提示错误信息
					if (errorList.size() > 0) {
						StringBuilder sb = new StringBuilder();
						for (String s : errorList) {
							sb.append(s + "<br>");
						}
						return CommonResult.fail(sb.toString());
					}
					// 类型
					String blockTypeName = cellValue.get(3).trim();
					String dictCode = dictService.getDicCodeByName(blockTypeName,"block_type");
					
					Long id = service.queryBeanByName(name);
					// 新增
					if (id == null) {
						Block bean = new Block();
						bean.setDistrictId(districtId);
						bean.setStreetId(streetId);
						bean.setName(name);
						bean.setBlockType(dictCode);
						bean.setCode(code);
						// 验证输入的经度纬度是数字
						String reg ="\\d+\\.{0,1}\\d*";
						String longitude = cellValue.get(4);
						if(StringUtils.hasText(longitude)){
							if (longitude.matches(reg)) {
								bean.setLongitude(Double.parseDouble(longitude));
							}else{
								log.error("导入失败，经度只能输入数字，请重新输入："+longitude); 
								return CommonResult.fail("导入失败，经度只能输入数字，请重新输入："+longitude);
							}
						}
						String latitude = cellValue.get(5);
						if(StringUtils.hasText(latitude)){
							if (latitude.matches(reg)) {
								bean.setLatitude(Double.parseDouble(latitude));
							}else{
								log.error("导入失败，纬度只能输入数字，请重新输入："+latitude); 
								return CommonResult.fail("导入失败，纬度只能输入数字，请重新输入："+latitude);
							}
						}
						bean.setCoverArea(cellValue.get(6).trim());
						bean.setPopulation(cellValue.get(7).trim());
						bean.setRemark(cellValue.get(8).trim());
						bean.setModUser(user.getUid());
						bean.setModTime(new Date());
						service.insert(bean);
						countInsert++;
					// 修改
					}else{
						Block beanSql = service.get(id);
						if (beanSql != null) {
							beanSql.setDistrictId(districtId);
							beanSql.setStreetId(streetId);
							beanSql.setName(name);
							beanSql.setBlockType(dictCode);
							beanSql.setCode(code);
							// 验证输入的经度纬度是数字
							String reg ="\\d+\\.{0,1}\\d*";
							String longitude = cellValue.get(4);
							if(StringUtils.hasText(longitude)){
								if (longitude.matches(reg)) {
									beanSql.setLongitude(Double.parseDouble(longitude));
								}else{
									log.error("导入失败，经度只能输入数字，请重新输入："+longitude); 
									return CommonResult.fail("导入失败，经度只能输入数字，请重新输入："+longitude);
								}
							}
							String latitude = cellValue.get(5);
							if(StringUtils.hasText(latitude)){
								if (latitude.matches(reg)) {
									beanSql.setLatitude(Double.parseDouble(latitude));
								}else{
									log.error("导入失败，纬度只能输入数字，请重新输入："+latitude); 
									return CommonResult.fail("导入失败，纬度只能输入数字，请重新输入："+latitude);
								}
							}
							beanSql.setCoverArea(cellValue.get(6).trim());
							beanSql.setPopulation(cellValue.get(7).trim());
							beanSql.setRemark(cellValue.get(8).trim());
							beanSql.setModUser(user.getUid());
							beanSql.setModTime(new Date());
							service.update(beanSql);
							countUpdate++;
						}
					}
				}else {
					  log.error("导入失败，街道不存在，社区名称："+name);
		  		      return CommonResult.fail("导入失败，街道不存在，社区名称："+name);
				}

			}else {
				  log.error("导入失败，行政区不存在，名称："+name); 
	  		      return CommonResult.fail("导入失败，行政区不存在，名称："+name);
			}
		}
		
		Long end = System.currentTimeMillis();
		log.info("[IMPORT_STREET]插入数据消耗时间costTime："+(end-begin)+"，插入记录："+countInsert+" 条"+"，更新记录："+countUpdate+" 条");
		if (countInsert >0 || countUpdate >0) {
			return CommonResult.success("导入成功"+"，插入记录："+countInsert+" 条"+"，更新记录："+countUpdate+" 条");
		}else{
			return CommonResult.fail("Excel文件内容为空");
		}
	}
	
	@RequestMapping(value = "/exportLibStreet", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> exportLibCourse(HttpServletRequest request,HttpSession session) {
		HSSFWorkbook wb;
		String fName;
		try {
			wb = new HSSFWorkbook();  // 创建一个Excel文件 
			Long begin = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			// get方式，前端需要编码中文，java后台解码
			String name = request.getParameter("name");
			if (StringUtils.hasText(name)) {
				map.put("nameLike", name);
			}
			String streetId = request.getParameter("streetId");
			if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
				map.put("streetId", streetId);
			}
			String districtId = request.getParameter("districtId");
			if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
				map.put("districtId", districtId);
			}
			String blockId = request.getParameter("blockId");
			if (StringUtils.hasText(blockId)  && !blockId.equals("0")) {
				map.put("blockId", blockId);
			}
			String blockType = request.getParameter("blockType");
			if (StringUtils.hasText(blockType)  &&  !blockType.equals("0")) {
				map.put("blockType", blockType);
			}
			log.info("export ,name={},streetId={}",name,streetId);
			List<Block> xcbs = service.queryByMap(map);
			xlsLibBlockHandler.createSheet(wb, "社区清单", xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime："+(end-begin));
			fName = "社区清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			
		}catch (Exception e){
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出社区清单失败,time:{}",date,e);
			XlsLibStreetHandler.createExceptionSheet(wb,e.toString());
			fName = "导出社区清单失败,请联系管理员"+date;
		}

		return ExportExcelUtils.createDownloadExcel(fName,wb,request);
	}
	
	
	
	@RequestMapping(value = "/queryAreaTree")
	@ResponseBody
	public CommonResult queryAll(
			HttpSession session,
			@RequestParam(required = true) String flag // 1:行政区 2：街道 3：社区
		){
		// 所有行政区
		List<EasyuiAreaTreeNode> result = new ArrayList<EasyuiAreaTreeNode>();
		if (Integer.parseInt(flag) >= 1) {
			List<District> districts = districtService.queryAll();
			for (District district : districts) {
				EasyuiAreaTreeNode eard = new EasyuiAreaTreeNode();
				Long districtId = district.getId();
				eard.setId(districtId+"");
				eard.setText(district.getName());
				
				// 行政区的下一级
				List<EasyuiAreaTreeNode> childrensForDistrict = new ArrayList<EasyuiAreaTreeNode>();
				if (Integer.parseInt(flag) >= 2) {
					Map<String, Object> streetMap = new HashMap<String, Object>();
					streetMap.put("districtId", districtId);
					List<Street> streets = streetService.queryByMap(streetMap);
					for(Street street:streets){
						EasyuiAreaTreeNode childForDis = new EasyuiAreaTreeNode();
						Long streetId = street.getId();
						childForDis.setId(streetId+"");
						childForDis.setText(street.getName());
						childForDis.setParentId(districtId+"");
						childrensForDistrict.add(childForDis);
						// 街道的下一级
						List<EasyuiAreaTreeNode> childrensForStreet = new ArrayList<EasyuiAreaTreeNode>();
						if ("3".equals(flag)) {
							Map<String, Object> blockMap = new HashMap<String, Object>();
							blockMap.put("streetId", streetId);
							List<Block> blocks = service.queryByMap(blockMap);
							for (Block block : blocks) {
								EasyuiAreaTreeNode childForStreet = new EasyuiAreaTreeNode();
								Long blockId = block.getId();
								childForStreet.setId(blockId+"");
								childForStreet.setText(block.getName());
								childForStreet.setParentId(streetId+"");
								childrensForStreet.add(childForStreet);
							}
						}
						childForDis.setChildren(childrensForStreet);
					}
				}
				eard.setChildren(childrensForDistrict);
				result.add(eard);
			}
		}
		return CommonResult.success(result);
	}
		
}
