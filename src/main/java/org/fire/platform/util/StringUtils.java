package org.fire.platform.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static String getString(String str) {
		if (isEmpty(str) || str.equals("null")) {
			return "";
		}
		return str;
	}

	public static boolean containsAnyWords(String s, String search) {
		boolean flag = false;
		if (s == null || search == null)
			flag = false;
		else {
			String[] ss = search.split("\\|");
			for (String s1 : ss) {
				if (s.contains(s1)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	public static List<Integer> stringArrayToListInteger(String[] array) {
		Assert.notEmpty(array);
		List<Integer> integers = new ArrayList<Integer>();
		for (String s : array) {
			if (isNumeric(s)) {
				integers.add(Integer.valueOf(s));
			}
		}
		return integers;
	}

	public static String putString(String str) {
		if (isEmpty(str) || str.trim().equals("null")) {
			return "";
		}

		try {
			str = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 用于过滤字符串并对中文进行utf-8编码的公共方法
	 * 
	 * @param str
	 * @return
	 */
	public static String getUTF8String(String str) {
		if (isEmpty(str) || "null".equals(str)) {
			return "";
		}

		try {
			str = str.replaceAll("\"", "”").replace("%", "％")
					.replace("&#40;", "(").replace("1&#41;", ")");
			str = str.replace(',', '，').replace('?', '？').replace(';', '；')
					.replace(':', '：').replace("(", "（").replace(")", "）");
			str = URLEncoder.encode(str, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static float versionNumberToFloat(String versionNumber) {
		int start = versionNumber.indexOf(".");
		String temp = versionNumber;
		if (start > 0) {
			String prefix = versionNumber.substring(0, start + 1);
			String postfix = versionNumber.substring(start,
					versionNumber.length()).replace(".", "");
			temp = prefix + postfix;
		}
		return Float.parseFloat(temp);
	}

    public static String ObjToString(Object obj){
        if(obj==null){
            return "";
        }else{
            return obj.toString();
        }
    }

    /**
     * 重复字符
     * @param str
     * @param separator
     * @return
     */
    public static String repeatStrs(String str, String separator, int times){
        StringBuilder sb;

        if(times < 0){ return null; }
        else if(times == 0){ return  ""; }
        if(times == 1){ return str; }

        sb = new StringBuilder();
        for(int i = 0; i < times; i++){
            sb.append(str).append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }
    
    public static String urlToShiroPermission(String url){
    	if(url == null){ return null; }
    	
    	url = url.replaceAll("^/+", "");
    	url = url.replaceAll("/+$", "");
    	
    	return url.replaceAll("/+", ":");
    }
    
    /** 
     * 判断数组内有无重复元素 
     * @param args 
     * @return true 有重复 | false 无重复 
     */  
    public static boolean hasRepeat(Object[] args){  
        Set<Object> tempSet = new HashSet<Object>();  
        for (int i = 0; i < args.length; i++) {  
            tempSet.add(args[i]);  
        }  
        if(args.length == tempSet.size()){  
            return false;  
        }else{  
            return true;  
        }  
    }  
    
    /** 
     * 删除Html标签 
     *  
     * @param inputString 
     * @return 
     */  
    public static String htmlRemoveTag(String inputString) {  
        if (inputString == null)  
            return null;  
        String htmlStr = inputString; // 含html标签的字符串  
        String textStr = "";  
        java.util.regex.Pattern p_script;  
        java.util.regex.Matcher m_script;  
        java.util.regex.Pattern p_style;  
        java.util.regex.Matcher m_style;  
        java.util.regex.Pattern p_html;  
        java.util.regex.Matcher m_html;  
        try {  
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>  
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";   
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>  
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";   
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
            m_script = p_script.matcher(htmlStr);  
            htmlStr = m_script.replaceAll(""); // 过滤script标签  
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
            m_style = p_style.matcher(htmlStr);  
            htmlStr = m_style.replaceAll(""); // 过滤style标签  
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
            m_html = p_html.matcher(htmlStr);  
            htmlStr = m_html.replaceAll(""); // 过滤html标签  
            textStr = htmlStr;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return textStr;// 返回文本字符串  
    }  
    
    /**
     * 判断字符串是否存在sql注入
     * @param str
     * @return
     */
    public static boolean isSqlInject(String str){
    	//过滤 ‘  
    	//ORACLE 注解 --  /**/  
    	//关键字过滤 update ,delete   
    	String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
    	            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
    	  
    	Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE); 
    	
    	try {
			str = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}  
    	
    	return sqlPattern.matcher(str).find();
    }
    
    /**
     * 
     * 正则表达式修改html标签中属性
     * @param htmlStr html文本   
     * @param searchTag  要修改的目标标签  
     * @param searchAttrib  目标标签中的属性  
     * @param startStr 替换内容开头
     * @param endStr 替换内容结尾
     * @param isAppendAttr 匹配不到是否追加该属性
     * @return
     */
    public static String updateHtmlTag(String htmlStr, String searchTag,     
            String searchAttrib,String startStr , String endStr, boolean isAppendAttr){
    	 String regxpForTag ="<\\s*" + searchTag + "\\s+([^>]*)\\s*>";      
         //String regxpForTagAttrib = searchAttrib + "\\s*=\\s*[\"|']http://([^\"|']+)[\"|']";//"=[\"|']([^[\"|']]+)[\"|']";    
         
        // String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";  
    	 String regxpForTagAttrib = searchAttrib + "=\"([^\"]+)\"";
         
         Pattern patternForTag = Pattern.compile(regxpForTag);     
         Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);     
         Matcher matcherForTag = patternForTag.matcher(htmlStr);     
         StringBuffer sb = new StringBuffer();     
         boolean result = matcherForTag.find();  
         int matchCount = 0;
         while (result) {     
             StringBuffer sbreplace = new StringBuffer("<"+searchTag +" ");   
             //System.out.println(matcherForTag.group(1));  
             Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag     
                     .group(1));     
               
             if (matcherForAttrib.find()) {  
               //  System.out.println("ll"+matcherForAttrib.group(1));  
                 /*matcherForAttrib.appendReplacement(sbreplace, searchAttrib+"=\"" +startStr     
                         + matcherForAttrib.group(1) + endStr);*/
                 matcherForAttrib.appendReplacement(sbreplace, searchAttrib+"=\"" +startStr     
                          + endStr);
                 matchCount++;
                 
                 //matcherForTag.appendReplacement(sb, sbreplace.toString());    
                 matcherForAttrib.appendTail(sbreplace);
                 matcherForTag.appendReplacement(sb, sbreplace.toString()+">");
             } else{
            	 if(isAppendAttr){
            		 StringBuilder appendStr = new StringBuilder();
                	 appendStr.append(searchAttrib+"=\"" +startStr + endStr);
                	 
                	 matcherForAttrib.appendTail(sbreplace);
                	 
                	 matcherForTag.appendReplacement(sb,  sbreplace.substring(0, sbreplace.lastIndexOf("/"))
                			 + appendStr.toString() +" />");
            	 }else{
            		//matcherForTag.appendReplacement(sb, sbreplace.toString());    
                     matcherForAttrib.appendTail(sbreplace);
                     matcherForTag.appendReplacement(sb, sbreplace.toString()+">"); 
            	 }
            	 
             }
               
             result = matcherForTag.find();     
         }
         
         if(!result){
        	 
         }
         
         System.out.println("matchCount = " + matchCount);
         matcherForTag.appendTail(sb);     
         return sb.toString();  
    }
    
    /**  
     *   
     * 基本功能：替换指定的标签  
     * <p>  
     *   
     * @param str  
     * @param beforeTag  
     *            要替换的标签  
     * @param tagAttrib  
     *            要替换的标签属性值  
     * @param startTag  
     *            新标签开始标记  
     * @param endTag  
     *            新标签结束标记  
     * @return String  
     * @如：替换img标签的src属性值为[img]属性值[/img]  
     */  
    public static String replaceHtmlTag(String str, String beforeTag,   
            String tagAttrib, String startTag, String endTag) {   
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";   
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";   
        Pattern patternForTag = Pattern.compile(regxpForTag);   
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);   
        Matcher matcherForTag = patternForTag.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result = matcherForTag.find();   
        while (result) {   
            StringBuffer sbreplace = new StringBuffer();   
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag   
                    .group(1));   
            if (matcherForAttrib.find()) {   
                matcherForAttrib.appendReplacement(sbreplace, startTag   
                        + matcherForAttrib.group(1) + endTag);   
            }   
            matcherForTag.appendReplacement(sb, sbreplace.toString());   
            result = matcherForTag.find();   
        }   
        matcherForTag.appendTail(sb);   
        return sb.toString();   
    }   
    
    public static void main(String[] args) {  
    	String htmlStr = "<p><img src=\"http://oss.aliyuncs.com/schotest/lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\" "
    			+ " title=\"lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\""
    			+ " alt=\"20085722191339_2.jpg\""
    			+ " width=\"761\""
    			+ " height=\"370\""
    			+ " style=\"width: 761px; height: 370px;\"/></p>"
    			+ "<p><img src=\"http://oss.aliyuncs.com/schotest/lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\" "
    			+ " title=\"lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\""
    			+ " alt=\"20085722191339_2.jpg\""
    			+ " width=\"761\""
    			+ " height=\"370\""
    			+ " style=\"width: 761px; height: 370px;\"/></p>";
    	
    	String result = updateHtmlTag(htmlStr, "img", "width","100%" , "\"",true);
    	result = updateHtmlTag(result, "img", "height","" , "\"",false);
    	result = updateHtmlTag(result, "img", "style","" , "\"",false);
        System.out.println("输出：  "+ result); 
        
        
        String htmlStr2 = "<p><img src=\"http://oss.aliyuncs.com/schotest/lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\" ";
        htmlStr2 += " title=\"lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\""
    			+ " alt=\"20085722191339_2.jpg\""
    			+ " /></p>"
    			+ "<p><img src=\"http://oss.aliyuncs.com/schotest/lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\" "
    			+ " title=\"lesson/org_0/2015/gSodIPMEDlvHjLDxE7Ok9A/1441867004276.jpg\""
    			+ " alt=\"20085722191339_2.jpg\""
    			+ " width=\"761\""
    			+ " height=\"370\""
    			+ " style=\"width: 761px; height: 370px;\"/></p>";
        
        result = updateHtmlTag(htmlStr2, "img", "width","100%" , "\"",true);
    	result = updateHtmlTag(result, "img", "height","" , "\"",false);
    	result = updateHtmlTag(result, "img", "style","" , "\"",false);
        System.out.println("输出：  "+ result); 
  
    }  
    
    
    /**
     * 验证字符串是否为纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){ 
    	   Pattern pattern = Pattern.compile("[0-9]*"); 
    	   Matcher isNum = pattern.matcher(str);
    	   if( !isNum.matches() ){
    	       return false; 
    	   } 
    	   return true; 
   }
}
