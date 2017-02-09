package org.fire.platform.modules.notice.controller;

import com.google.zxing.WriterException;
import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.notice.bean.NoticeAttachmentsBean;
import org.fire.platform.modules.notice.domain.Notice;
import org.fire.platform.modules.notice.domain.NoticeAttachment;
import org.fire.platform.modules.notice.service.INoticeAttachmentService;
import org.fire.platform.modules.notice.service.INoticeService;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.util.FileUpload;
import org.fire.platform.util.QRCodeWParam;
import org.fire.platform.util.ResponseUtil;
import org.fire.platform.util.UuidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	@Autowired
	private INoticeService service;
	private static Logger log = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private INoticeAttachmentService noticeAttachmentService;

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo,
			@RequestParam(value="rows", defaultValue="20") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String title
	){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(title)) {
			map.put("title", title);
		}
		map.put("enabled","1");
		map.put("extraOrderColumns", " mod_time DESC ");
		log.info("query,page={},pageSize={},name={},remark={}",pageNo,pageSize,title);
		PageInfo<Notice> page =  service.queryPageByMap(map, pageNo, pageSize);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}

	@RequestMapping(value = "/insertData")
	@ResponseBody
	public CommonResult create(Notice bean, HttpSession session){
		log.info("create,bean={}",bean);
		try{
			User user = (User) session.getAttribute(App.USER_SESSION_KEY);
			if (user == null) {
				log.error("新增失败，请登陆");
				return CommonResult.fail("新增失败，请登陆");
			}
//			Long sqlId = service.queryBeanByName(bean.getTitle());
//			if (sqlId != null) {
//				log.error("新增失败，已存在该数据");
//				return CommonResult.fail("新增失败，已存在该数据");
//			}
			bean.setPublisher(user.getUid());
			bean.setModTime(new Date());
			bean.setCreatedTime(new Date());
			bean.setEnabled(1);
			bean.setUuid(UUID.randomUUID().toString());
			service.insert(bean);
			return CommonResult.success("新增成功",bean.getId());
		}catch(Exception e){
			log.error("新增失败",e);
			return CommonResult.fail("新增失败");
		}
	}

	@RequestMapping(value = "/getData")
	@ResponseBody
	public Notice get(@RequestParam(value = "id") Long id){
		log.info("get,id={}",id);
		return 	service.get(id);
	}

	@RequestMapping(value = "/getDataByUUID")
	@ResponseBody
	public NoticeAttachmentsBean getByUUID(@RequestParam(value = "uuid") String  uuid){
		log.info("NoticeController -> getByUUID params -> uuid = {} " , uuid );
		return 	service.getBeanByUUID(uuid);
	}

	@RequestMapping(value = "/updateData")
	@ResponseBody
	public CommonResult update(Notice bean,HttpSession session){
		log.info("update,bean={}",bean);
		try{
			User user = (User)session.getAttribute(App.USER_SESSION_KEY);
			if (user == null) {
				log.error("修改失败，请登陆");
				return CommonResult.fail("修改失败，请登陆");
			}
			bean.setPublisher(user.getUid());
			bean.setModTime(new Date());
			service.update(bean);
			return CommonResult.success("修改成功",bean.getId());
		}catch(Exception e){
			log.error("新增失败",e);
			return CommonResult.fail("修改失败");
		}

	}

	@RequestMapping(value = "/deleteData")
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id") Long id,HttpSession session){
		log.info("delete,id={}",id);
		try{
			User user = (User)session.getAttribute(App.USER_SESSION_KEY);
			if (user == null) {
				log.error("删除失败，请登陆");
				return CommonResult.fail("删除失败，请登陆");
			}
			Notice notice = service.get(id);
			notice.setEnabled(0);
			service.update(notice);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("noticeId",id);
			List<NoticeAttachment> noticeAttachments = noticeAttachmentService.queryByMap(params);
			if (noticeAttachments != null && noticeAttachments.size()>0){
				for(NoticeAttachment attachment : noticeAttachments){
					attachment.setState(0);
					noticeAttachmentService.update(attachment);
				}
			}
			return CommonResult.success("删除成功");
		}catch(Exception e){
			log.error("删除失败",e);
			return CommonResult.fail("删除失败");
		}
	}

	@RequestMapping(value = "/deleteDataByIds", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delDataByIds(
			@RequestBody List<String> deleteIds,
			HttpSession session) {
		log.info("delete,deleteIds={}",deleteIds);
		int ok = 0;
		if(deleteIds==null || deleteIds.size()==0){
			return CommonResult.fail("批量删除失败");
		}
		User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
			log.error("批量删除失败，请登陆");
			return CommonResult.fail("批量删除失败，请登陆");
		}
		List<Long> deleteIdsFilter = new ArrayList<Long>();
		for (String id: deleteIds){
			deleteIdsFilter.add(Long.parseLong(id));
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("noticeId",id);
			List<NoticeAttachment> noticeAttachments = noticeAttachmentService.queryByMap(params);
			if (noticeAttachments != null && noticeAttachments.size()>0){
				for(NoticeAttachment attachment : noticeAttachments){
					attachment.setState(0);
					noticeAttachmentService.update(attachment);
				}
			}
		}
		// 批量删除
		ok = service.batchDelete(deleteIdsFilter);
		return CommonResult.success("删除成功记录："+ok+"条");
	}


	/**
	 * ue文件上传
	 * @param file
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/ueUpload", method = RequestMethod.POST)
	public @ResponseBody void ueUpload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter out = response.getWriter();
			String callback = request.getParameter("CKEditorFuncNum");

			//首先获取文件类型
			String originalName = file.getOriginalFilename();		//原文件名
			String suffix = originalName.substring(originalName.lastIndexOf(".")+1);

			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			Long fileSize = file.getSize();
			int fileWidth = sourceImg.getWidth();
			int fileHeight = sourceImg.getHeight();
			log.debug("上传图片的大小为:{} B,宽度为{},高度为{}", fileSize,fileWidth,fileHeight);
			if((fileSize/1024.0) > 2048){
				//TODO : 返回限制大小信息
				String responseText = ResponseUtil.printWriterResult("上传图片大小限制在2M以内","图片大小限制在2M","","","",0L);
				out.println(responseText);
				return ;
			}
			StringBuffer url = request.getRequestURL();
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
			String fileName = this.getFileName(suffix);
			String key = FileUpload.UE_FILE_PATH + fileName;
			// TODO 上传到服务器
			String fileSaveName = FileUpload.ueUploadFile(file, request);
			log.info("上传成功!");
			String responseText = ResponseUtil.printWriterResult("SUCCESS",key,originalName,suffix.toLowerCase(),tempContextUrl+FileUpload.UE_FILE_PATH+"/"+fileSaveName,fileSize);
			out.println(responseText);
		} catch (Exception e) {
			log.error("上传失败!", e);
		}
	}

	/**
	 * 生成上传文件名
	 * @return
	 */
	private String getFileName(String suffix){
		String fileName = System.currentTimeMillis() + "." +suffix;
		return  fileName;
	}

	/**
	 * 生成二维码
	 *
	 * @return
	 */
	@RequestMapping(value = "/createQRCode")
	public void createQRCodeNew(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "uuid", required = false) String uuid) {

		Long noticeId = service.queryBeanByUUID(uuid);
		if (noticeId == null)
			return;
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
 		try {
			String path = QRCodeWParam
					.generateQRCode(FileUpload.NOTICE_SHARE_QRCODE_FILE_PATH, tempContextUrl+"fire/view/notice/preview.jsp?uuid="+uuid,
							uuid);
			File img = new File(path);
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "gif", response.getOutputStream());
		} catch (IOException e1) {
			log.error("生成二维码失败:message:{}", e1.getMessage());
			e1.printStackTrace();
		} catch (WriterException e1) {
			log.error("生成二维码失败:message:{}", e1.getMessage());
			e1.printStackTrace();
		}

	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public CommonResult upload(@RequestParam("file") MultipartFile file,
							   @RequestParam("noticeId") String  noticeId,
							   HttpServletRequest request, HttpServletResponse response) throws IOException {
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!"xls".equals(extension) && !"xlsx".equals(extension) && !"doc".equals(extension)
				&& !"docx".equals(extension) && !"pdf".equals(extension) && !"csv".equals(extension)) {
			return CommonResult.fail("附件上传只允许word、excel、pdf格式的");
		}

		String fileSaveName = "";
		if (file == null) {
			return CommonResult.fail("请选择有效文件！");
		}

		fileSaveName = FileUpload.uploadAttachmentFile(file, request);
		Notice notice = service.get(Long.parseLong(noticeId));
		if (notice == null){
			return CommonResult.fail("上传公告附件，公告基础信息不存在");
		}
		NoticeAttachment nat  = new NoticeAttachment();
		// 不再新增字段，重命名后直接保存在uuid字段了
		nat.setUuid(fileSaveName);
		nat.setFileSize(file.getSize()+"");
		nat.setNoticeId(notice.getId());
		nat.setOriginalName(file.getOriginalFilename());
		nat.setState(1);
		noticeAttachmentService.insert(nat);
		log.info("fileSaveName:" + fileSaveName);
		return  CommonResult.success(nat.getId());
	}

}
