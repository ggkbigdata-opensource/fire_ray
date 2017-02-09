package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.EquipmentEnrolmentBean;
import org.fire.platform.modules.report.bean.EquipmentTypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fire.platform.modules.report.dao.EquipmentEnrolmentMapper;
import org.fire.platform.modules.report.domain.EquipmentEnrolment;
import org.fire.platform.modules.report.service.IEquipmentEnrolmentService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class EquipmentEnrolmentServiceImpl implements IEquipmentEnrolmentService {

    @Autowired
	private EquipmentEnrolmentMapper mapper;
 
	public int insert(EquipmentEnrolment bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(EquipmentEnrolment bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public EquipmentEnrolment get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<EquipmentEnrolment> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<EquipmentEnrolment> queryPageByMap(int page,int size,Map param){
	   PageHelper.startPage(page,size);
	   List<EquipmentEnrolment> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<EquipmentEnrolment> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<EquipmentEnrolment> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<EquipmentEnrolment> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<EquipmentEnrolment> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<EquipmentEnrolment> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<EquipmentEnrolment> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

    @Override
    public int batchInsert(List<EquipmentEnrolment> enrolmentList) {
		if (enrolmentList == null || enrolmentList.isEmpty()) {
			return 0;
		}
		Map<String,Object> params = Maps.newHashMap();
		params.put("list",enrolmentList);
		return mapper.batchInsert(params);
	}

	@Override
	public PageInfo<EquipmentEnrolmentBean> getBeanList(Integer page , Integer pageSize ,Long reportId) {
		if(reportId == null){
			return null;
		}
		PageHelper.startPage(page, pageSize);
		List<EquipmentEnrolmentBean> list = mapper.getBeanList(reportId);
		return PageHelper.getPageInfo(list);
	}

    @Override
    public int deleteByReportId(Long reportId) {
		if (reportId == null) {
			return 0;
		}
		Map<String,Object> params = Maps.newHashMap();
		params.put("reportId", reportId);
		return mapper.deleteByParam(params);
    }

    @Override
    public List<EquipmentTypeBean> getBeanList2(Long reportId) {
		if(reportId == null){
			return null;
		}
		List<EquipmentTypeBean> list = mapper.getBeanList2(reportId);
		return list;
    }
}
