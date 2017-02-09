package org.fire.platform.modules.check.controller;

import java.io.IOException;
import java.util.*;

import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.bean.ResultSearchBean;
import org.fire.platform.modules.check.domain.CheckItemResultBak;
import org.fire.platform.modules.check.service.ICheckItemResultService;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Controller
@RequestMapping("/checkItemResult_bak")
public class CheckItemResultControllerBak {

	@Autowired
	private ICheckItemResultService service;
	private static Logger log = LoggerFactory.getLogger(CheckItemResultControllerBak.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(@RequestParam(value="page", defaultValue="1") int pageNo,
										 @RequestParam(value="rows", defaultValue="10") int pageSize,
										 @RequestParam String reportId,
										 @RequestParam(value="sortCode" , required=false) String sortCode,
										 @RequestParam(value="typeCode" , required=false) String typeCode,
										 @RequestParam(value="name" , required=false) String name,
										 HttpSession session){
	    log.info("query,page={},rows={},reportId={}",pageNo,pageSize,reportId);
		Map<String,Object> params = new HashMap();
		params.put("reportId", reportId);
		if(StringUtils.hasText(sortCode) && !sortCode.equals("0")){
			params.put("itemSortCode", sortCode);
		}
		if(StringUtils.hasText(typeCode) && !typeCode.equals("0")){
			params.put("itemTypeCodeLike", typeCode);
		}
		if(StringUtils.hasText(name)){
			params.put("nameLike", name);
		}
		params.put("extraOrderColumns" , " item_type_code ");
	    PageInfo<CheckItemResultBak> page =  service.queryPageByMap(params, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}
	
	@RequestMapping(value = "/querySearchCode")
	@ResponseBody
	public CommonResult getSearchCode(long reportId){
		List<ResultSearchBean> beanList = service.getItemCodeByReportId(reportId);
		if(beanList == null || beanList.size() == 0){
			return CommonResult.fail("没有数据");
		}
		return CommonResult.success(beanList);
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public CheckItemResultBak get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody CheckItemResultBak bean){
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
	 public CommonResult update(@RequestBody CheckItemResultBak bean){
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

	@RequestMapping(value = "/importReportResult" , method = RequestMethod.POST)
	@ResponseBody CommonResult importReportResult(@RequestParam(value = "file") MultipartFile file,
												  @RequestParam Long reportId,
												  HttpSession session) {
		// 获取当前登录用户
		User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
			log.error("导入失败，请登陆");
			return CommonResult.fail("导入失败，请登陆");
		}
		try {
			List<List<String>> lists = ExcelUtil.excelList(file);
			if(lists.isEmpty()){
				return CommonResult.fail("请按模板导入");
			}
			//移除标题行
			lists.remove(0);
			lists.remove(0);
			String sortCode = "", typeCode = "", sort = "", type = "";
			List<CheckItemResultBak> resultList = new ArrayList<>();
			List<String> errorMsg = new ArrayList<>();
			int row = 2;
			for (List<String> list : lists) {
				row++;
				if(list.size() < 6) {
					errorMsg.add("第" + row + "行错误，无检测结果");
					continue;
				}
				//获取大类以及编码
				String temp = list.get(0);
				if(StringUtils.hasText(temp)){
					sort = temp;
					sortCode = temp.substring(0,temp.indexOf("."));
				}
				//获取小类以及拼接项编码
				temp = list.get(1);
				if(StringUtils.hasText(temp)){
					type = temp;
					typeCode = sortCode + "." + temp.substring(0,temp.indexOf("."));
				}
				//获取项以及编码
				String name = list.get(2);
				if(name.indexOf(".")==-1){
					errorMsg.add("第" + row + "行错误，无效行，确保一行一项，不要合并行");
					continue;
				}
				temp = typeCode + "." + name.substring(0,name.indexOf("."));
				//获取结果值
				int value = getResultValue(list.get(3));
				if(value == -2){
					errorMsg.add("第" + row + "行错误，检查结果填写不正确，请按照标题格式填写");
					continue;
				}
				//获取是否重点
				int emphasis = StringUtils.isEmpty(list.get(4))?0:list.get(4).equalsIgnoreCase("Y")?1:0;
				//获取依据
				String basis = list.get(5);
				//获取备注
				String remark = "";
				if(list.size() >= 7){
					//获取备注
					remark = list.get(6);
				}
				CheckItemResultBak result = new CheckItemResultBak();
				result.setReportId(reportId);
				result.setItemSort(sort);
				result.setItemSortCode(sortCode);
				result.setItemType(type);
				result.setItemTypeCode(temp);
				result.setName(name);
				result.setIsPass(value);
				result.setResult(remark);
				result.setLevel(emphasis + "");
				result.setCheckTime(new Date());
				result.setChecker(user.getUid() +"");
				resultList.add(result);
			}
			//提示错误
			if(errorMsg.size() > 0){
				CommonResult result = new CommonResult();
				result.setMsg("导入失败！");
				result.setData(errorMsg);
				result.setSuccessful(false);
				return result;
			}
			//批量导入 // TODO: 2016/10/17 017 时间原因，以后需要改为一次insert多条数据，而不是循环insert
			for (CheckItemResultBak result : resultList) {
				CommonResult rst = create(result);
				if(!rst.isSuccessful()){
					return rst;
				}
			}
			return CommonResult.success("导入成功！");
		} catch (IOException e) {
			log.error("导入检测报告结果失败:",e);
			return CommonResult.fail("导入失败！");
		}
	}

	private int getResultValue(String key){
		if(StringUtils.isEmpty(key)){
			return -2;
		}
		int value;
		switch (key){
			case "符合" :
				value = 2;
				break;
			case "不符合" :
				value = -1;
				break;
			case "无此项" :
				value = 0;
				break;
			case "缺陷" :
				value = 1;
				break;
			default:
				value = -2;
				break;
		}
		return value;
	}
}
