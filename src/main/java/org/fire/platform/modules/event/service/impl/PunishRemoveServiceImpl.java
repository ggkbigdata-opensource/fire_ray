package org.fire.platform.modules.event.service.impl;


import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.dao.PunishRemoveMapper;
import org.fire.platform.modules.event.domain.PunishRemove;
import org.fire.platform.modules.event.service.IPunishRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Company: Scho Techonlogy Co. Ltd
 *
 * @author ZKT
 * @date 2017-1-18 17:32:53
 */

@Service
public class PunishRemoveServiceImpl implements IPunishRemoveService {

    @Autowired
    private PunishRemoveMapper mapper;

    public int insert(PunishRemove bean) {
        return mapper.insert(bean);
    }


    public int update(PunishRemove bean) {
        return mapper.updateByPrimaryKey(bean);
    }

    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public PunishRemove get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<PunishRemove> queryAll(int page, int size) {
        return mapper.selectAll();
    }

    public PageInfo<PunishRemove> queryPageByMap(int page, int size, Map param) {
        PageHelper.startPage(page, size);
        List<PunishRemove> list = mapper.selectByMap(param);
        return PageHelper.getPageInfo(list);
    }

    @Override
    public List<PunishRemove> queryAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<PunishRemove> queryPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<PunishRemove> list = mapper.selectAll();
        return PageHelper.getPageInfo(list);
    }

    @Override
    public List<PunishRemove> queryByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public PageInfo<PunishRemove> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<PunishRemove> list = mapper.selectByMap(map);
        return PageHelper.getPageInfo(list);
    }
}
