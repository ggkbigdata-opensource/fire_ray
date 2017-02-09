package org.fire.platform.modules.statis.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.statis.bean.PunishEventSumBean;
import org.fire.platform.modules.statis.domain.PunishEventSum;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

public interface PunishEventSumMapper extends Mapper<PunishEventSum> {

	List<PunishEventSumBean> selectBeanByMap(Map<String, Object> map);

	List<AreaTypeDataVo> queryPunishSumStatis(Map<String, Object> params);
}

