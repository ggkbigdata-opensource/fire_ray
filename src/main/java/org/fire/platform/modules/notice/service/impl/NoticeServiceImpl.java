package org.fire.platform.modules.notice.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.notice.bean.NoticeAttachmentsBean;
import org.fire.platform.modules.notice.dao.NoticeAttachmentMapper;
import org.fire.platform.modules.notice.domain.NoticeAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.fire.platform.modules.notice.dao.NoticeMapper;
import org.fire.platform.modules.notice.domain.Notice;
import org.fire.platform.modules.notice.service.INoticeService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
	private NoticeMapper mapper;

	@Autowired
	private NoticeAttachmentMapper noticeAttachmentMapper;
 
	public int insert(Notice bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Notice bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Notice get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Notice> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<Notice> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Notice> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<Notice> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<Notice> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Notice> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

	public int batchDelete(List<Long> ids){
		return mapper.batchDeleteNotice(ids);
	}

	@Override
	public Long queryBeanByTitle(String title) {
		return mapper.selectByTitle(title);
	}

	public Long queryBeanByUUID(String uuid) {
		return mapper.selectByUUID(uuid);
	}

	public NoticeAttachmentsBean getBeanByUUID(String uuid){

		NoticeAttachmentsBean noticeAttachmentsBean = new NoticeAttachmentsBean();
		Long noticeId = mapper.selectByUUID(uuid);
		if (noticeId != null){
			noticeAttachmentsBean.setNotice(mapper.selectByPrimaryKey(noticeId));
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("noticeId",noticeId);
			List<NoticeAttachment> noticeAttachments = noticeAttachmentMapper.selectByMap(params);
			noticeAttachmentsBean.setNoticeAttachments(noticeAttachments);
		}
		return noticeAttachmentsBean;
	}
}
