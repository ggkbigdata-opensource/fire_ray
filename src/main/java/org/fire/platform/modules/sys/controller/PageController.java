package org.fire.platform.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	
	// 街道页面
	@RequestMapping("/street")
	public String street() {
		return "area/street";
	}
	
	// 行政区页面
	@RequestMapping("/district")
	public String district() {
		return "area/district";
	}
	
	// 社区页面
	@RequestMapping("/block")
	public String block() {
		return "area/block";
	}
	
	// 消防站页面
	@RequestMapping("/fireStation")
	public String fireStation() {
		return "area/fireStation";
	}
	
	
	@RequestMapping("/buildingSubject")
	public String building() {
		return "building/buildingSubject";
	}
	
	@RequestMapping("/buildingFloorPanorama")
	public String buildingFloorPanorama() {
		return "building/buildingFloorPanorama";
	}
	
	
	@RequestMapping("/buildingFloor")
	public String buildingFloor() {
		return "building/buildingFloor";
	}
	
	@RequestMapping("/buildingPanorama")
	public String buildingPanorama() {
		return "building/buildingPanorama";
	}
	
	// 火灾事故
	@RequestMapping("/fireEvent")
	public String fireEvent() {
		return "event/fireEvent";
	}
	
	
	// 执法
	@RequestMapping("/punishEvent")
	public String punishEvent() {
		return "event/punishEvent";
	}
	
	// 检测报告
	@RequestMapping("/checkReport")
	public String checkReport() {
		return "check/checkReportRe";
	}

	// 检测报告
	@RequestMapping("/checkItemDef")
	public String checkItemDef() {
		return "check/checkItemDef";
	}

	// 预览检测报告
	@RequestMapping("/previewReport")
	public String previewReport() {
		return "check/previewReport";
	}

	// 用户管理
	@RequestMapping("/user")
	public String user() {
		return "sys/user";
	}
	
	// 角色管理
	@RequestMapping("/role")
	public String role() {
		return "sys/role";
	}

	// 菜单管理
	@RequestMapping("/menu")
	public String menu() {
		return "sys/menu";
	}
	
	// 火灾事故统计
	@RequestMapping("/fireEventSum")
	public String fireEventSum() {
		return "statis/fireEventSum";
	}
	
	// 执法统计
	@RequestMapping("/punishEventSum")
	public String punishEventSum() {
		return "statis/punishEventSum";
	}
	
	// 报告统计
	@RequestMapping("/checkReportSum")
	public String checkReportSum() {
		return "statis/checkReportSum";
	}
	
	// 检测排名
	@RequestMapping("/reportAnalysis")
	public String reportAnalysis() {
		return "statis/reportAnalysis";
	}
	
	
	// 报告统计
	@RequestMapping("/reportAnalysisPie")
	public String reportAnalysisPie() {
		return "statis/reportAnalysisPie";
	}
	
	//警情详情页面(两饼状图)
	@RequestMapping("/fireEventDetailPie")
	public String fireEventDetailPie() {
		return "statis/fireEventDetailPie";
	}

	// 公告页面
	@RequestMapping("/notice")
	public String notice() {
		return "notice/notice";
	}

}
