package org.fire.platform.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.fire.platform.modules.area.vo.Latlng;

public class LatlngUtil {
	
  public static Latlng getLatlng(String latlng){
		
		String[] strs = StringUtils.split(latlng, ",");
		
		if( strs.length != 2){
			return null;
		}
		try{
			Double lng = Double.valueOf(strs[0]);
			Double lat = Double.valueOf(strs[1]);
			return new Latlng(lng,lat);
		}catch(Exception ex){
			
		}
		return null;
  }
  
  public static List<Double> getGeoPoint(String point){
		
	    List<Double> pointList = new ArrayList<Double>();
		String[] strs = StringUtils.split(point, ",");
		
		if( strs.length != 2){
			return null;
		}
		try{
			Double lat = Double.valueOf(strs[0]);
			Double lng = Double.valueOf(strs[1]);
			pointList.add(lat);
			pointList.add(lng);
		}catch(Exception ex){
			
		}
		return pointList;
}
  
  public static List<Latlng> getLaglngList(String latlngStr){
	 
		List<Latlng> latlngList = new ArrayList<Latlng>();
		String[] latlngStrs = StringUtils.split(latlngStr, ";");
		if(latlngStrs.length < 3){
			return null;
		}
		for( int i=0;i<latlngStrs.length;i++){
			Latlng latlng = getLatlng(latlngStrs[i]);
			if( latlng != null){
				latlngList.add(latlng);
			}
		}
		return latlngList;
  }
  
  public static List<List<Double>> getGeoPointList(String pointStr){
		 
	    List<List<Double>> pointList = new ArrayList<List<Double>>();
		String[] latlngStrs = StringUtils.split(pointStr, ";");
		if(latlngStrs.length < 3){
			return null;
		}
		for( int i=0;i<latlngStrs.length;i++){
			List<Double> point = getGeoPoint(latlngStrs[i]);
			pointList.add(point);
		}
		return pointList;
}

}
