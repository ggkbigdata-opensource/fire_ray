package org.fire.platform.modules.notice.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.modules.notice.bean.NoticeAttachmentsBean;
import  org.fire.platform.modules.notice.domain.Notice;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */
 
public interface INoticeService extends IService<Notice> {

    int batchDelete(List<Long> ids);

    Long queryBeanByTitle(String title);

    NoticeAttachmentsBean getBeanByUUID(String uuid);

    Long queryBeanByUUID(String uuid);
}
