package org.fire.platform.modules.notice.controller;

import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.notice.domain.Notice;
import org.fire.platform.modules.notice.domain.NoticeAttachment;
import org.fire.platform.modules.notice.service.INoticeAttachmentService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@Controller
@RequestMapping("/noticeAttachment")
public class NoticeAttachmentController extends BaseController {

	@Autowired
	private INoticeAttachmentService service;
	private static Logger log = LoggerFactory.getLogger(NoticeAttachmentController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo,
			@RequestParam(value="rows", defaultValue="20") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String noticeId
	){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(noticeId)) {
			map.put("noticeId", noticeId);
		}
		map.put("state","1");
//		map.put("extraOrderColumns", " mod_time DESC ");
		log.info("NoticeAttachmentController -> queryPage params -> pageNo = {}, pageSize = {}, session = {}, noticeId = {} " , pageNo , pageSize , session , noticeId );
		PageInfo<NoticeAttachment> page =  service.queryPageByMap(map, pageNo, pageSize);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}


	@RequestMapping(value = "/deleteData")
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id") Long id, HttpSession session){
		log.info("delete,id={}",id);
		try{
			User user = (User)session.getAttribute(App.USER_SESSION_KEY);
			if (user == null) {
				log.error("删除失败，请登陆");
				return CommonResult.fail("删除失败，请登陆");
			}
			NoticeAttachment noticeAttachment = service.get(id);
			noticeAttachment.setState(0);
			service.update(noticeAttachment);
			return CommonResult.success("删除成功");
		}catch(Exception e){
			log.error("删除失败",e);
			return CommonResult.fail("删除失败");
		}
	}

}
