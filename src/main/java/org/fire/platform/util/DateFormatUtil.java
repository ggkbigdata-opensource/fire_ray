package org.fire.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String getYearAndMonth(Date date)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    	return sdf.format(date);
    }
    
    public static String getYear(Date date)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    	return sdf.format(date);
    }
    
    
    
    
    public static void main(String[] args) {
    	String str = getYearAndMonth(new Date());
    	System.out.print(str);
	}

}
