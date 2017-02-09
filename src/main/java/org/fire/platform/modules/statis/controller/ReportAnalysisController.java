package org.fire.platform.modules.statis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.ReportAnalysisBean;
import org.fire.platform.modules.statis.bean.ReportStatisBean;
import org.fire.platform.modules.statis.domain.ReportAnalysis;
import org.fire.platform.modules.statis.service.IReportAnalysisService;
import org.fire.platform.modules.statis.service.IStatisService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-10-14 19:28:53
 */

@Controller
@RequestMapping("/reportAnalysis")
public class ReportAnalysisController{

	@Autowired
	private IReportAnalysisService service;
	
	@Autowired
	private IStatisService statisService; 
	
	private static Logger log = LoggerFactory.getLogger(ReportAnalysisController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String riskLevel,
			@RequestParam(required = false) String reportAnalysisStartMonth,
			@RequestParam(required = false) String reportAnalysisEndMonth
			){
		  log.info("query,page={},pageSize={},streetId={},blockId={},districtId={},reportAnalysisStartMonth={},reportAnalysisEndMonth={}",
				  pageNo,pageSize,streetId,blockId,districtId,reportAnalysisStartMonth,reportAnalysisEndMonth);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
			map.put("blockId", blockId);
		}
		if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if(StringUtils.hasText(riskLevel) && !riskLevel.equals("0")){
			map.put("riskLevel", riskLevel);
		}
		if(StringUtils.hasText(reportAnalysisStartMonth)){
			map.put("reportAnalysisStartMonth", reportAnalysisStartMonth);
		}
		if(StringUtils.hasText(reportAnalysisEndMonth)){
			map.put("reportAnalysisEndMonth", reportAnalysisEndMonth);
		}
		PageInfo<ReportAnalysisBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
	    User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	   		data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public ReportAnalysis get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody ReportAnalysis bean){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(@RequestBody ReportAnalysis bean){
	     log.info("update,bean={}",bean);
	     try{
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id){
	     log.info("delete,id={}",id);
	     try{
		    service.delete(id);
		    return CommonResult.success("删除成功");
		 }catch(Exception e){
		    log.error("删除失败",e); 
		    return CommonResult.fail("删除失败");
		 }
	 }
	 
	 
	 @RequestMapping(value = "/genAnalysis")
	 @ResponseBody
	 public CommonResult genAnalysis(@RequestParam(value = "reportId") Long reportId){
	     log.info("genAnalysis,reportId={}",reportId);
	     try{
	    	Map<String,Object> param = new HashMap<String,Object>();
	    	param.put("reportId", reportId);
		    //TODO 获取报告项
	    	 List<ReportStatisBean> list = statisService.queryReportItemSum(param);
	    	 if( CollectionUtils.isEmpty(list)){
	    		 log.warn("根据报告Id没有获取到报告项,reportId={}",reportId);
	    		 return CommonResult.fail("根据报告Id没有获取到报告项");
	    	 }
	    	 
	    	//TODO 根据报告项生成数据
	    	
	    	 
	    	//插入数据
	    	 
	    	return null;
	    	 
		 }catch(Exception e){
		    log.error("删除失败",e); 
		    return CommonResult.fail("删除失败");
		 }
	 }
	 
}
