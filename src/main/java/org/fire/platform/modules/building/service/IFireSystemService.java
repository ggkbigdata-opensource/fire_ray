package org.fire.platform.modules.building.service;

import org.fire.platform.common.base.IService;
import  org.fire.platform.modules.building.domain.FireSystem;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */
 
public interface IFireSystemService extends IService<FireSystem> {


    int batchInsert(List<FireSystem> fireSystems);
}
