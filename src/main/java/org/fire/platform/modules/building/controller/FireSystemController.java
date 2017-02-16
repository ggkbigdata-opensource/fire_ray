package org.fire.platform.modules.building.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.domain.FireSystem;
import org.fire.platform.modules.building.domain.Management;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.fire.platform.modules.building.service.IFireSystemService;
import org.fire.platform.modules.building.service.IManagementService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Controller
@RequestMapping("/fireSystem")
public class FireSystemController extends BaseController{

	@Autowired
	private IFireSystemService service;

	@Autowired
	private IManagementService managementService;

	@Autowired
	private IBuildingSubjectService buildingSubjectService;

	private static Logger log = LoggerFactory.getLogger(FireSystemController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String systemName
		){
	    log.info("query,page={},pageSize={},systemName={}",pageNo,pageSize,systemName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(systemName)) {
			map.put("systemName", systemName);
		}
	    PageInfo<FireSystem> page =  service.queryPageByMap(map, pageNo, pageSize);
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
			@RequestParam(required = false) String systemName,
			@RequestParam(required = false) String buildingId
	){
		log.info("queryAll params -> session = {}, systemName = {}, buildingId = {} " , session , systemName , buildingId );
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(systemName)) {
			map.put("systemName", systemName);
		}
		if (StringUtils.hasText(buildingId)) {
			map.put("buildingId", buildingId);
		}
		List<FireSystem> list =  service.queryByMap(map);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			data.put("total", list.size());
			data.put("rows", list);
		}
		return data;
	}

	@RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult saveChanges(HttpServletRequest req,HttpSession session) {
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
			log.error("新增失败，请重新登录");
			return CommonResult.fail("新增失败，请重新登录");
		}
		//获取编辑数据 这里获取到的是json字符串
//		String deleted = req.getParameter("deleted");
//		String inserted = req.getParameter("inserted");
		String updated = req.getParameter("updated");

//		if (deleted != null) {
//			//把json字符串转换成对象
//			List<FireSystem> listDeleted = JSON.parseArray(deleted, FireSystem.class);
//			//TODO 下面就可以根据转换后的对象进行相应的操作了
//			for (FireSystem bean:listDeleted){
//				service.delete(bean.getId());
//			}
//		}
//
//		if (inserted != null) {
//			//把json字符串转换成对象
//			List<FireSystem> listInserted = JSON.parseArray(inserted, FireSystem.class);
//			for (FireSystem bean:listInserted){
//				bean.setUserId(user.getUid());
//				bean.setCreateDate(new Date());
//				bean.setModDate(new Date());
//				service.insert(bean);
//			}
//		}

		if(updated != null){
			//把json字符串转换成对象
			List<FireSystem> listUpdated = JSON.parseArray(updated, FireSystem.class);
			for (FireSystem bs:listUpdated){
				bs.setModDate(new Date());
				bs.setUserId(user.getUid());
				service.update(bs);
			}
		}
		return CommonResult.success("操作成功");
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public FireSystem get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(FireSystem bean,HttpSession session,@RequestParam(value = "id") Long buildingId){
	     log.info("create,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		  log.error("新增失败，请重新登录"); 
				  return CommonResult.fail("新增失败，请重新登录");
			}
			 Management management = managementService.getByBuildingId(buildingId);
			 String[] fireSystemNames = {"hydrant001",
			                             "automaticFire002",
			                             "waterSpraySystem003",
			                             "gasFire004",
			                             "bubbleFire004",
			                             "automaticAlarm005",
			                             "smokeSystem006",
			                             "fireproofRolling007",
			                             "powerSupply008",
			                             "evacuationIndicating009",
			                             "buildingExtinguisher010"};
			 String[][] constituentNames={
				{"室外消火栓","室内消火栓","消火栓泵","稳压泵","气压水罐","水泵接合器","双电源切换柜","消防泵控制柜","消防水池","消防水箱"},
				{"喷淋水泵","稳压泵","气压罐","水泵接合器","报警阀","压力开关","带微动开关闸阀","水流指示器","喷头","喷淋泵控制柜"},
				{"消防水泵","稳压泵","气压罐","雨淋阀 （电动阀）","喷头","消防泵控制柜"},
				{"气体灭火控制器","气体灭火设备（成套）"},
				{"泡沫发生器（成套）","泡沫泵","泡沫混合液","泡沫罐","泡沫炮","泡沫消火栓","泡沫喷头","电动阀","灭火剂"},
				{"火灾报警控制器","消防联动控制器","终端/区域显示器","感烟探测器","感温探测器","火焰探测器","其他探测器","手报及破玻","电话插孔",
				    "扬声器","消防电梯","可燃气体控制器","可燃气体探测器","电气火灾监控器","电气火灾监控探测器"},
				{"加压送风机","加压送风阀","排烟风机","排烟阀","排烟防火阀","防火阀","补风机","风机控制箱"},
				{"防火窗（成套）","防火门（成套）","防火卷帘（成套）"},
				{"市电电源","自备发电机（功率）","双电源切换装置"},
				{"应急照明灯具","疏散指示标志"},
				{"手提式灭火器","推车式灭火器"}
			 };
			 List<FireSystem> fireSystemsInsert = new ArrayList<FireSystem>();
			 for (int i=0;i<fireSystemNames.length;i++){
				 for (int j=0;i<constituentNames.length;i++){
					 String[] constituentNames2 = constituentNames[i];
					 for (int k=0;k<constituentNames2.length;k++){
						 FireSystem fs = new FireSystem();
						 fs.setBuildingId(buildingId);
						 if (management != null){
							 fs.setManagementId(management.getId());
						 }
						 fs.setSystemName(fireSystemNames[i]);
						 fs.setSystemConstituentName(constituentNames2[k]);
						 fs.setCreateDate(new Date());
						 fs.setUserId(user.getUid());
						 fireSystemsInsert.add(fs);
					 }
				 }
			 }
			 int total = service.batchInsert(fireSystemsInsert);
		    return CommonResult.success("新增成功",total);
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult update(FireSystem bean,HttpSession session){
	     log.info("update,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		   log.error("修改失败，请重新登录"); 
				    return CommonResult.fail("修改失败，请重新登录");
			}
    	  	bean.setUserId(user.getUid());
    	  	bean.setModDate(new Date());
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("修改失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id,HttpSession session){
	     log.info("delete,id={}",id);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		   log.error("删除失败，请重新登录"); 
				    return CommonResult.fail("删除失败，请重新登录");
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
			int res =  service.delete(Long.parseLong(id));
			if(res>0){
				ok++;
			}
		}
		return CommonResult.success("删除成功记录："+ok+"条"+"！");
	}



}
