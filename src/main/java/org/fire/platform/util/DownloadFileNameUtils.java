package org.fire.platform.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DownloadFileNameUtils {

	public static String getAttachmentFileName(String userAgent,
			String fileName, String defaultName, String suffix) {
		String aFileName = defaultName;
		try {
			if (userAgent == null) {
				aFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				return aFileName + "." + suffix;
			}

			userAgent = userAgent.toLowerCase();
			// ie11以前版本
			boolean isLowerThanIE11 = userAgent.indexOf("msie") != -1;
			// ie11
			boolean isIE11 = userAgent.indexOf("trident/7.0") != -1
					&& userAgent.indexOf("rv:11.0") != -1;
			if (isLowerThanIE11 || isIE11) {
				aFileName = URLEncoder.encode(fileName, "UTF8");
				aFileName = aFileName.replaceAll("\\+", "%20");
			} else {
				aFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return aFileName + "." + suffix;
	}
}
