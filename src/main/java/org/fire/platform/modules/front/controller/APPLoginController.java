package org.fire.platform.modules.front.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.sys.domain.LoginLog;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.ILoginLogService;
import org.fire.platform.modules.sys.service.IUserService;
import org.fire.platform.util.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appuser")
public class APPLoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static String INVALID_FLAG = "invalid";
	
	@Resource
	private IUserService userService;
	@Resource
	private ILoginLogService loginLogService;
	
	@Resource
	private IDistrictService districtService;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult login(
			@RequestParam(value = "username", required=true) String username,
			@RequestParam(value = "password", required=true) String password,
			@RequestParam(value = "imei", required=false) String imei){
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		//检查参数,password需 要在手机端加密
		
		//根据账号和密码获取用户
		User user = userService.getUserByUserNameAndPassWord(username,password);
		if( user == null ){
			logger.warn("账号不存在或密码错误,username={},password={}",username,password);
			return CommonResult.fail("账号不存在或密码错误");
		}
		
		
		
		
		//判断用户的设备ID
		/**
		if(user.getImeiNum() != null && !user.getImeiNum().equalsIgnoreCase("invalid") && !imei.equalsIgnoreCase(user.getImeiNum())){
			return CommonResult.fail("该账号已经在其他手机登录了");
		}
		**/
		
		//需要生成一个token并返回给APP端，TODO
		String token = EncryptUtils.encryptByAES(username, password);
		//如果是第一次登录，修改手机的imei编码 TODO
		//将手机imei和token都保存起来 TODO
		Date loginDate = new Date();
		
		User updateBean = new User();
		updateBean.setUid(user.getUid());
		updateBean.setImeiNum(imei);
		updateBean.setToken(token);
		updateBean.setLastLoginTime(loginDate);
		userService.update(updateBean);
		
		//TODO 记录登录日志
		LoginLog loginlog = new LoginLog();
		loginlog.setImeiNum(imei);
		loginlog.setIpAddress("");
		loginlog.setLoginSource("");
		loginlog.setLoginTime(loginDate);
		loginlog.setLoginType(0);
		loginlog.setMobile("");
		loginlog.setToken(token);
		loginlog.setUserId(user.getUid());
		loginlog.setUsername(username);
		loginLogService.insert(loginlog);
		
		//根据社区ID查社区编码
		if( user.getDistrictId() != null ){
		 	District district = districtService.get(user.getDistrictId());
		 	if( district != null ){
		 		retMap.put("areaCode", district.getCode());
		 	}
		}
		
		retMap.put("userId", user.getUid());
		retMap.put("token",token);
		
		return CommonResult.success(retMap);
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult logout(
			@RequestParam(value = "userId", required=true) Long userId){
		
		//TODO 根据用户ID修改用户的imei为空，并修改token为空
		User updateBean = new User();
		updateBean.setUid(userId);
		updateBean.setImeiNum(INVALID_FLAG);
		updateBean.setToken(INVALID_FLAG);
		updateBean.setLastLoginTime(new Date());
		userService.update(updateBean);
		
		//TODO 记录退出登录日志
		return CommonResult.success();
	}
	


}
