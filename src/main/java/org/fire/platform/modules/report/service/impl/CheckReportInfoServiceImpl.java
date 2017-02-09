package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.bean.CheckItemResultStatisBean;
import org.fire.platform.modules.report.bean.CheckReportInfoBean;
import org.fire.platform.modules.report.bean.ReportInfoBean;
import org.fire.platform.modules.report.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.fire.platform.modules.report.dao.CheckReportInfoMapper;
import org.fire.platform.modules.report.domain.CheckReportInfo;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class CheckReportInfoServiceImpl implements ICheckReportInfoService {

    @Autowired
	private CheckReportInfoMapper mapper;
    @Autowired
	private ICheckItemResultService checkItemResultService;
    @Autowired
	private ICheckItemResultStatisService checkItemResultStatisService;
    @Autowired
	private ICheckItemUnqualifiedService unqualifiedService;
    @Autowired
	private IEquipmentEnrolmentService enrolmentService;
 
	public int insert(CheckReportInfo bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckReportInfo bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckReportInfo get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckReportInfo> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckReportInfo> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<CheckReportInfo> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckReportInfo> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<CheckReportInfo> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckReportInfo> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckReportInfo> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<CheckReportInfo> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckReportInfo> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckReportInfoBean> queryBeanByMap(Map<String, Object> map) {
		return mapper.selectBeanByMap(map);
	}

	@Override
	public PageInfo<CheckReportInfoBean> queryBeanPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckReportInfoBean> list = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(list);
	}

	@Override
	public ReportInfoBean getReportBean(Long reportId) {
		if(reportId == null){
			return null;
		}
		ReportInfoBean infoBean = mapper.selectReportBeanById(reportId);
		if(infoBean == null ){
			return null;
		}
//		List<CheckItemResultBean> resultBeanList = checkItemResultService.queryResultBeanByReportId(reportId);
//		infoBean.setItemResultList(resultBeanList);
//		List<CheckItemResultStatisBean> statisBeans = checkItemResultStatisService.queryStatisBeanByReportId(reportId);
//		infoBean.setItemResultStatisList(statisBeans);
		return infoBean;
	}

	@Override
	public CheckReportInfoBean getReportInfoBean(Long reportId) {
		if(reportId == null){
			return null;
		}
		CheckReportInfoBean infoBean = mapper.getBeanById(reportId);
		if(infoBean == null ){
			return null;
		}
		return infoBean;
	}

    @Override
    public int deleteReport(Long id) {
		if(id == null){
			return 0;
		}
		checkItemResultService.deleteByReportId(id);
		checkItemResultStatisService.deleteByReportId(id);
		unqualifiedService.deleteByReportId(id);
		enrolmentService.deleteByReportId(id);
        return delete(id);

    }
}
