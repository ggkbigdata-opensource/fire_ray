package org.fire.platform.util;

import java.io.PrintWriter;

/**
 * 响应请求工具类
 * @author lcw
 *
 */
public class ResponseUtil {
	
	/**
	 * 打印html
	 * @param out PrintWriter对象
	 * @param title 
	 * @param content
	 */
	public static void print(PrintWriter out, String title , String content){
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">");
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(content);
		out.println("</body>");
		out.println("</html>");
	}
	
	/**
	 * 输出移动设备html(处理图片尺寸)
	 * @param out PrintWriter对象
	 * @param title 
	 * @param content
	 */
	public static void printForMobile(PrintWriter out, String title , String content){
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">");
		out.println("<title>" + title + "</title>");
		
		out.println("<style type=\"text/css\">");
		out.println("table{border-color: black; border-width: 1px;}");
		out.println("</style>");
		
		out.println("</head>");
		out.println("<body>");
		
		content = StringUtils.updateHtmlTag(content, "img", "width","100%" , "\"",true);
		content = StringUtils.updateHtmlTag(content, "img", "height","" , "\"",false);
		content = StringUtils.updateHtmlTag(content, "img", "style","" , "\"",false);
		
		out.println(content);
		out.println("</body>");
		out.println("</html>");
	}
	
	 /**
     * 生成ueditor上传图片输出结果
     * @param state (成功SUCCESS 失败FAIL)
     * @param key 标题
     * @param originalName 文件名
     * @param suffix 文件类型
     * @param ossFileUrl 文件路径
     * @param fileSize 文件大小
     * @return
     */
    public static String printWriterResult(String state, String key, String originalName, String suffix,
										   String ossFileUrl, Long fileSize){
    	StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"state\":\"" + state + "\",");
		builder.append("\"title\":\"" + key + "\",");
		/*builder.append("\"original\":\"" + originalName + "\",");*/
		builder.append("\"original\":\"\",");
		builder.append("\"type\":\"" + suffix.toLowerCase() + "\",");
		builder.append("\"url\":\"" + ossFileUrl + "\",");
		builder.append("\"size\":\"" + fileSize.toString() + "\"");
		builder.append("}");
		return builder.toString();
    }
}
