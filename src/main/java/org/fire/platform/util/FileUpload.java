package org.fire.platform.util;
import org.apache.commons.io.FileUtils;
import org.fire.platform.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;  
  

import javax.servlet.http.HttpServletRequest;  

import java.io.File;  
import java.io.IOException;  
import java.util.Date;
import java.util.UUID;

public class FileUpload {
	
	// 该目录需要自己手动去服务器创建
	public static final String FILE_PATH = "/resources/building/thumb_img";
    // 建筑主体上传的文件存放，后期需要人工进行导入
    public static final String FILE_BUILDING_PATH = "/resources/building/upload";
    // UE编辑器
    public static final String UE_FILE_PATH = "/resources/notice/ueditor/image";
    // 生成二维码存放路径
    public static final String NOTICE_SHARE_QRCODE_FILE_PATH = "/resources/notice/shareQRCode";
    // 公告附件存放路径
    public static  final String NOTICE_ATTACHMENT_FILE_PATH = "/resources/notice/attachment";

    // 生成二维码存放路径
    public static final String PUNISH_REPORT_QRCODE_FILE_PATH = "/resources/report/QRCode";

    /**
     * 文件上传
     * @param file
     * @param request
     * @return   保存的文件名称
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        // 重命名
        File tempFile = new File(FileUtil.getTomcatPath()+FILE_PATH, new Date().getTime() + "_"+String.valueOf(fileName));
        System.out.println("tempFile path :" + tempFile.getAbsolutePath());
        FileUtils.writeByteArrayToFile(tempFile,file.getBytes());
        return tempFile.getName();
    }

    /**
     * 建筑主体文件上传
     * @param file
     * @param request
     * @return   保存的文件名称
     * @throws IOException
     */
    public static String uploadBuildingFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        // 重命名
        File tempFile = new File(FileUtil.getTomcatPath()+FILE_BUILDING_PATH, new Date().getTime() + "_"+String.valueOf(fileName));
        System.out.println("tempFile path :" + tempFile.getAbsolutePath());
        FileUtils.writeByteArrayToFile(tempFile,file.getBytes());
        return tempFile.getName();
    }

    /**
     * 公告附件上传
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    public static String uploadAttachmentFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String saveName = UUID.randomUUID() + "."+fileName.substring(fileName.lastIndexOf(".") + 1);
        // 重命名
        File tempFile = new File(FileUtil.getTomcatPath()+NOTICE_ATTACHMENT_FILE_PATH, saveName);
        System.out.println("attachment tempFile path :" + tempFile.getAbsolutePath());
        FileUtils.writeByteArrayToFile(tempFile,file.getBytes());
        return saveName;
    }


    /**
     * UE图片上传
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    public static String ueUploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        // 重命名
        File tempFile = new File(FileUtil.getTomcatPath()+UE_FILE_PATH, System.currentTimeMillis() + "_"+String.valueOf(fileName));
        System.out.println("tempFile path :" + tempFile.getAbsolutePath());
        FileUtils.writeByteArrayToFile(tempFile,file.getBytes());
        return tempFile.getName();
    }

    /**
     * 获取文件
     * @param fileName
     * @return
     */
    public static File getFile(String fileName) {  
        return new File(FileUtil.getTomcatPath()+FILE_PATH, fileName);  
    }  
}
