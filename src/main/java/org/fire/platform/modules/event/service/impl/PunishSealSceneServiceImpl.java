package org.fire.platform.modules.event.service.impl;


import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.dao.PunishSealSceneMapper;
import org.fire.platform.modules.event.domain.PunishSealScene;
import org.fire.platform.modules.event.service.IPunishSealSceneService;
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
public class PunishSealSceneServiceImpl implements IPunishSealSceneService {

    @Autowired
    private PunishSealSceneMapper mapper;

    public int insert(PunishSealScene bean) {
        return mapper.insert(bean);
    }


    public int update(PunishSealScene bean) {
        return mapper.updateByPrimaryKey(bean);
    }

    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public PunishSealScene get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<PunishSealScene> queryAll(int page, int size) {
        return mapper.selectAll();
    }

    public PageInfo<PunishSealScene> queryPageByMap(int page, int size, Map param) {
        PageHelper.startPage(page, size);
        List<PunishSealScene> list = mapper.selectByMap(param);
        return PageHelper.getPageInfo(list);
    }

    @Override
    public List<PunishSealScene> queryAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<PunishSealScene> queryPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<PunishSealScene> list = mapper.selectAll();
        return PageHelper.getPageInfo(list);
    }

    @Override
    public List<PunishSealScene> queryByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public PageInfo<PunishSealScene> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<PunishSealScene> list = mapper.selectByMap(map);
        return PageHelper.getPageInfo(list);
    }
}
