package org.fire.platform.modules.notice.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fire.platform.modules.notice.dao.NoticeAttachmentMapper;
import org.fire.platform.modules.notice.domain.NoticeAttachment;
import org.fire.platform.modules.notice.service.INoticeAttachmentService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@Service
public class NoticeAttachmentServiceImpl implements INoticeAttachmentService {

    @Autowired
	private NoticeAttachmentMapper mapper;
 
	public int insert(NoticeAttachment bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(NoticeAttachment bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public NoticeAttachment get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<NoticeAttachment> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<NoticeAttachment> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<NoticeAttachment> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<NoticeAttachment> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<NoticeAttachment> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<NoticeAttachment> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}
}
