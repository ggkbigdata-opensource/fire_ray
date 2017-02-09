package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fire.platform.modules.report.dao.EquipmentTypeMapper;
import org.fire.platform.modules.report.domain.EquipmentType;
import org.fire.platform.modules.report.service.IEquipmentTypeService;


/**
 * Company: Scho Techonlogy Co. Ltd
 *
 * @author ZKT
 * @date 2016-12-2 14:28:58
 */

@Service
public class EquipmentTypeServiceImpl implements IEquipmentTypeService {

    @Autowired
    private EquipmentTypeMapper mapper;

    public int insert(EquipmentType bean) {
        return mapper.insert(bean);
    }


    public int update(EquipmentType bean) {
        return mapper.updateByPrimaryKey(bean);
    }

    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public EquipmentType get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EquipmentType> queryAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<EquipmentType> queryPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<EquipmentType> list = mapper.selectAll();
        return PageHelper.getPageInfo(list);
    }

    @Override
    public List<EquipmentType> queryByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public PageInfo<EquipmentType> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<EquipmentType> list = mapper.selectByMap(map);
        return PageHelper.getPageInfo(list);
    }

    @Override
    public int batchInsert(List<EquipmentType> typeList) {
        if(typeList == null || typeList.isEmpty()){
            return 0;
        }
        return mapper.batchInsert(typeList);
    }
}
