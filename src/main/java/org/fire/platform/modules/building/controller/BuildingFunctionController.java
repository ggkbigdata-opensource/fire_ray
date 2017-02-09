package org.fire.platform.modules.building.controller;

import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.building.domain.BuildingFunction;
import org.fire.platform.modules.building.service.IBuildingFunctionService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-12-26 16:28:43
 */

@Controller
@RequestMapping("/buildingFunction")
public class BuildingFunctionController extends BaseController {

	@Autowired
	private IBuildingFunctionService service;
	private static Logger log = LoggerFactory.getLogger(BuildingFunctionController.class);


	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(
			HttpSession session,
			@RequestParam(required = false) String buildingId
	){
		log.info("queryAll,buildingId={}",buildingId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(buildingId)) {
			map.put("buildingId", buildingId);
		}
		List<BuildingFunction> list =  service.queryByMap(map);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			if (list != null && list.size()>0){
				data.put("total", list.size());
				data.put("rows", list);
			}
		}
		return data;
	}

	@RequestMapping(value = "/insertData")
	@ResponseBody
	public CommonResult create(HttpSession session, @RequestBody List<BuildingFunction> buildingFunctions){
		log.info("BuildingFunctionController -> create params -> session = {}, buildingFunctions = {} " , session , buildingFunctions );
		try{
			User user = (User) session.getAttribute(App.USER_SESSION_KEY);
			if (user == null) {
				log.error("新增失败，请重新登录");
				return CommonResult.fail("新增失败，请重新登录");
			}
			if(buildingFunctions == null || buildingFunctions.size() <= 0){
				return CommonResult.fail("请填写信息后再保存！");
			}
			// 删除掉以前的
			Long buildingId = buildingFunctions.get(0).getBuildingId();
			Map<String, Object> map = new HashMap<String, Object>();
			if (buildingId != null) {
				map.put("buildingId", buildingId+"");
			}
			List<BuildingFunction> list =  service.queryByMap(map);
			if (list != null && list.size() > 0){
				for (BuildingFunction bfDel : list){
					service.delete(bfDel.getId());
				}
			}
			for (BuildingFunction bf:buildingFunctions){
				BuildingFunction newBean = new BuildingFunction();
				newBean.setBuildingId(bf.getBuildingId());
				newBean.setBuildingFloor(bf.getBuildingFloor());
				newBean.setFunction(bf.getFunction());
				newBean.setFunBusinessName(bf.getFunBusinessName());
				newBean.setIsSpecificLocation(bf.getIsSpecificLocation());
				newBean.setFunBusinessName(bf.getFunBusinessName());
				newBean.setFunBuildArea(bf.getFunBuildArea());
				newBean.setUserId(user.getUid());
				newBean.setCreateDate(new Date());
				newBean.setModDate(new Date());
				service.insert(newBean);
			}
			return CommonResult.success("新增成功");
		}catch(Exception e){
			log.error("新增失败",e);
			return CommonResult.fail("新增失败");
		}

	}

}
