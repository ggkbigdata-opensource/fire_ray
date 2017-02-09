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
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.statis.handler.XlsLibDistrictHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.sys.domain.User;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 18:52:34
 */

@Controller
@RequestMapping("area/district")
public class DistrictController{

	@Autowired
	private IDistrictService service;
	
	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private XlsLibDistrictHandler xlsLibDistrictHandler;
	
	private static Logger log = LoggerFactory.getLogger(DistrictController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="20") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String remark
		){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(remark)) {
			map.put("remark", remark);
		}
		map.put("extraOrderColumns", " mod_date DESC ");
		log.info("query,page={},pageSize={},name={},remark={}",pageNo,pageSize,name,remark);
		PageInfo<District> page =  service.queryPageByMap(map, pageNo, pageSize);
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
			@RequestParam(required = false) String districtId
		){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(remark)) {
			map.put("remark", remark);
		}
		map.put("extraOrderColumns", " mod_date DESC ");
		log.info("queryAll,name={},remark={}",name,remark);
		List<District> list  = service.queryByMap(map);
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
	public District get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
	@RequestMapping(value = "/getId")
	@ResponseBody
	public Long getIdByName(@RequestParam(value = "name") String name){
	    log.info("getIdByName,name={}",name);
	    return 	service.queryBeanByName(name);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(District bean,HttpSession session){
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
	    	if (StringUtils.hasText(bean.getName())) {
	    		bean.setCode(ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()));
			}
	    	bean.setModUser(user.getUid());
	    	bean.setModDate(new Date());
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(District bean,HttpSession session){
	     log.info("update,bean={}",bean);
	     try{
	    	 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("修改失败，请登陆"); 
	  		      return CommonResult.fail("修改失败，请登陆");
			}
	    	if (StringUtils.hasText(bean.getName())) {
	    		bean.setCode(ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()));
			}
	    	bean.setModUser(user.getUid());
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
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
	public CommonResult delDataByIds(
			@RequestBody List<String> deleteIds,
			HttpSession session) {
		log.info("delete,deleteIds={}",deleteIds);
		int ok = 0;
		int no = 0;
//		List<String> deleteIdsList = Arrays.asList(deleteIds.split(","));
//		if(deleteIdsList==null || deleteIdsList.size()==0){
//			 return CommonResult.fail("批量删除失败");
//		}
		if(deleteIds==null || deleteIds.size()==0){
			 return CommonResult.fail("批量删除失败");
		}
		 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
   		  	  log.error("批量删除失败，请登陆"); 
 		      return CommonResult.fail("批量删除失败，请登陆");
		}
		List<Long> deleteIdsFilter = new ArrayList<Long>();
		for(String id: deleteIds) {
			// 查询是否有关联的街道数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("districtId", id);
			List<Street> lists = streetService.queryByMap(map);
			if (lists != null && lists.size() > 0) {
				no++;
				  log.error("批量删除失败，关联有街道数据，请先去掉关联街道再删除！"); 
//	 		      return CommonResult.fail("批量删除失败，关联有街道数据，请先去掉关联街道再删除！");
			}else{
				deleteIdsFilter.add(Long.parseLong(id));
//				int res =  service.delete(Long.parseLong(id));
//				if(res>0){
//					ok++;
//				}
			}
		}
		// 批量删除
		ok = service.batchDelete(deleteIdsFilter);
	    return CommonResult.success("删除成功记录："+ok+"条"+"，删除失败记录："+no+" 条，请检查街道关联数据！");
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
//		0 行政区名称(必填不重复)	1 面积	2 人口	3 说明
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2016_10_11_district")){
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		if (!title.get(0).startsWith("注意事项:")) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		// 删除第一行表头说明
    	 dataList.remove(0);
		// 删除第二行
	 	dataList.remove(0);
	 	
		//检查重复的行政区名称
		List<String> errorList = new ArrayList<>();
		Set<String> nameSet = new TreeSet<>();
		
		int countInsert = 0;
		int countUpdate = 0;
		Long begin = System.currentTimeMillis();
		for(List<String> cellValue  : dataList){
			District bean = new District();
			String name = cellValue.get(0).trim();
			
			if (StringUtils.hasText(name)) {
				if (!nameSet.add(name)) {
					errorList.add("出现重复的行政区名称[" + name + "]，请检查所有行政区名称不重复再上传Excel");
				}
			} else {
				errorList.add("行政区名称不能为空，请确保Excel内容格式正确再上传");
			}
		
			//提示错误信息
			if (errorList.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String s : errorList) {
					sb.append(s + "<br>");
				}
				return CommonResult.fail(sb.toString());
			}
			bean.setName(name);
			Long disId = service.queryBeanByName(name);
			// 新增
			if (disId == null) {
				bean.setCode(ChineseToEnglish.getPinYinHeadUpperChar(name));
				bean.setCoverArea(cellValue.get(1).trim());
				bean.setPopulation(cellValue.get(2).trim());
				bean.setRemark(cellValue.get(3).trim());
				bean.setModUser(user.getUid());
				bean.setModDate(new Date());
				service.insert(bean);
				countInsert++;
			// 修改
			}else{
				District districtSql = service.get(disId);
				if (districtSql != null) {
					districtSql.setCode(ChineseToEnglish.getPinYinHeadUpperChar(name));
					districtSql.setCoverArea(cellValue.get(1).trim());
					districtSql.setPopulation(cellValue.get(2).trim());
					districtSql.setRemark(cellValue.get(3).trim());
					districtSql.setModUser(user.getUid());
					districtSql.setModDate(new Date());
					service.update(districtSql);
					countUpdate++;
				}
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
			log.info("export ,name={}",name);
			List<District> xcbs = service.queryByMap(map);
			xlsLibDistrictHandler.createDistrict(wb, "行政区清单", xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime："+(end-begin));
			fName = "行政区清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			
		}catch (Exception e){
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出行政区清单失败,time:{}",date,e);
			XlsLibStreetHandler.createExceptionSheet(wb,e.toString());
			fName = "导出行政区清单失败,请联系管理员"+date;
		}

		return ExportExcelUtils.createDownloadExcel(fName,wb,request);
	}
	 
}
