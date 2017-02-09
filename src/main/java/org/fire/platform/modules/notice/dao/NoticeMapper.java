package org.fire.platform.modules.notice.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.notice.domain.Notice;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

public interface NoticeMapper extends Mapper<Notice> {


    int batchDeleteNotice(List<Long> ids);

    Long selectByTitle(String title);

    Long selectByUUID(String uuid);
}

