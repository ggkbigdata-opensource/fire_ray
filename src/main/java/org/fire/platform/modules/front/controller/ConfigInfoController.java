package org.fire.platform.modules.front.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.domain.AppVersion;
import org.fire.platform.modules.sys.domain.Dict;
import org.fire.platform.modules.sys.service.IAppVersionService;
import org.fire.platform.modules.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/config")
public class ConfigInfoController {
		
	@Autowired
	private IDictService dictService;
	
	@Autowired
	private IAppVersionService appVersionService;
	
	
	private static String FILE_PATH = "D:\\TDDownload";
	

	
	@RequestMapping(value="/getDictList", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getDictList(
			@RequestParam(value = "typeCode") String typeCode,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		Map<String,Object> param = new HashMap<String,Object>();
		PageHelper.startPage(pageNum, pageSize);
		param.put("typeCode", typeCode);
		List<Dict> list = dictService.queryByMap(param);
		PageInfo<Dict> page = PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	@RequestMapping(value="/getAllDictList", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getDictListAll(){
		
		List<Dict> list = dictService.queryAll();
	    Map<String, Object> data = new HashMap<String, Object>();
   		data.put("total", list.size());
		data.put("rows", list);
		return CommonResult.success(data);
	}
	
	public CommonResult getDictList(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		
		PageHelper.startPage(pageNum, pageSize);
		List<Dict> list = dictService.queryByMap(params);
		PageInfo<Dict> page = PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	
	/**
	 * 
	 * @param version
	 * @param updateType 0 更新APP 1更新地图
	 * @return
	 */
	@RequestMapping(value="/versionUpdate", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getVersion(
			@RequestParam(value = "version") String version,
			@RequestParam(value = "updateType") Integer updateType){ 
		Map<String,Object> retMap = null;
		if( updateType == 0 ){
			retMap = getAppVersion(version);
		}else if(updateType == 1){
			retMap = getMapVersion(version);
		}
		
		return CommonResult.success(retMap);
	}
	
	@RequestMapping("/download")
	public String download(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		//response.setContentType("application/octet-stream;charset=UTF-8");  
		/**
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		**/
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"iso-8859-1") + "\"");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			
			InputStream inputStream = new FileInputStream(new File(FILE_PATH
					+ File.separator + fileName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			 // 这里主要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response
		return null;
	}
	
	
	private Map<String,Object> getAppVersion(String version){
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		param.put("appVersion", version);
		param.put("appUpdateTime", new Date());
		List<AppVersion> list = appVersionService.queryAPPVersionByMap(param);
		if( CollectionUtils.isEmpty(list)){
			retMap.put("updateType", 0);
			retMap.put("needUpdate", false);
		}else{
			AppVersion appVersion = list.get(0);
			retMap.put("updateType", 0);
			retMap.put("needUpdate", true);
			retMap.put("latestVersion",appVersion.getAppVersion() );
			retMap.put("download_url", appVersion.getAppDownUrl());
		}
		return retMap;
	}
	
	private Map<String,Object> getMapVersion(String version){
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		param.put("mapVersion", version);
		param.put("mapUpdateTime", new Date());
		List<AppVersion> list = appVersionService.queryAPPVersionByMap(param);
		if( CollectionUtils.isEmpty(list)){
			retMap.put("updateType", 1);
			retMap.put("needUpdate", false);
		}else{
			AppVersion appVersion = list.get(0);
			retMap.put("updateType", 1);
			retMap.put("needUpdate", true);
			retMap.put("latestVersion",appVersion.getMapVersion() );
			retMap.put("download_url", appVersion.getMapDownUrl());
		}
		return retMap;
	}
	
	
}
