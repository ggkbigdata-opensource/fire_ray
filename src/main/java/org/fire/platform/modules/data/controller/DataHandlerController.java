package org.fire.platform.modules.data.controller;

import java.util.HashMap;
import java.util.Map;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.serialize.DictMap;
import org.fire.platform.modules.data.service.IDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/data")
public class DataHandlerController {
	
	private static Logger log = LoggerFactory.getLogger(DataHandlerController.class);
	
	@Autowired
	IDataHandler dataHandler;
	
	@RequestMapping(value = "/sumCheckReport")
	@ResponseBody
	public CommonResult sumCheckReport(String begin,String end){
		log.info("begin sumCheckReport begin={},end={}",begin,end);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("monthBegin", begin);
		param.put("monthEnd", end);
		dataHandler.sumCheckReportData(param);
		return CommonResult.success();
		
	   
	}
	
	@RequestMapping(value = "/sumFireEvent")
	@ResponseBody
	public CommonResult sumFireEvent(String begin,String end){
		log.info("begin sumFireEvent begin={},end={}",begin,end);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("monthBegin", begin);
		param.put("monthEnd", end);
		dataHandler.sumFireEventData(param);
		return CommonResult.success();
		
	   
	}
	
	@RequestMapping(value = "/sumPunishEvent")
	@ResponseBody
	public CommonResult sumPunishEvent(String begin,String end){
		log.info("begin sumPunishEvent begin={},end={}",begin,end);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("monthBegin", begin);
		param.put("monthEnd", end);
		dataHandler.sumPunishEventData(param);
		return CommonResult.success();
		
	   
	}
	
	@RequestMapping(value = "/sumReportAnalysis")
	@ResponseBody
	public CommonResult sumReportAnalysis(Long reportId){
		log.info("begin sumReportAnalysis reportId={}",reportId);
		String riskLevel = dataHandler.sumReportAnalysis(reportId);
		return CommonResult.success(DictMap.getDict("risk_level_"+riskLevel));
	}
	
	@RequestMapping(value = "/calcStreetSafeIndex")
	@ResponseBody
	public CommonResult calcStreetSafeIndex(Long districtId){
		dataHandler.calcStreetSafeIndex(districtId);
		
		return CommonResult.success();
	}
	
	@RequestMapping(value = "/calcBlockSafeIndex")
	@ResponseBody
	public CommonResult calcBlockSafeIndex(Long streetId){
		return null;
	}

	@RequestMapping(value = "/calcAll")
    @ResponseBody
    public CommonResult calcAll(){
	    CommonResult commonResult = null;
        commonResult = sumCheckReport(null,null);
        if(!commonResult.isSuccessful()){
            return commonResult;
        }
        commonResult = sumFireEvent(null,null);
        if(!commonResult.isSuccessful()){
            return commonResult;
        }
        commonResult = sumPunishEvent(null,null);
        calcStreetSafeIndex(1L);
        return commonResult;
    }
	
	/**
	 * 定时任务 每天凌晨一点执行所有数据分析
	 */
//	@Scheduled(cron = "0 0/1 * * * ? ")
    @Scheduled(cron = "0 0 1 * * ? ")
	public void scheduledCalc(){
    	log.info("开始定时任务 scheduledCalc ------------------");
    	long start = System.currentTimeMillis();
		calcAll();
		long end = System.currentTimeMillis() - start;
		log.info("完成定时任务 scheduledCalc 花费：{} 毫秒 ------------------",end);
	}

}
