package org.fire.platform.modules.area.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.Feature;
import org.fire.platform.modules.area.bean.Geometry;
import org.fire.platform.modules.area.bean.Properties;
import org.fire.platform.modules.area.dao.AreaMapMapper;
import org.fire.platform.modules.area.domain.AreaMap;
import org.fire.platform.modules.area.service.IAreaMapService;
import org.fire.platform.modules.area.vo.Latlng;
import org.fire.platform.modules.area.vo.MapLatlng;
import org.fire.platform.util.FileUtil;
import org.fire.platform.util.LatlngUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-30 14:55:37
 */

@Service
public class AreaMapServiceImpl implements IAreaMapService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	private static String MAP_FILE_PATH = "/home/fire/map";
//	private static String MAP_FILE_ZIP_PATH = "/home/fire/map.zip";
	private static String MAP_FILE_PATH = FileUtil.getTomcatPath() + "/resources/map";
	private static String MAP_FILE_ZIP_PATH = FileUtil.getTomcatPath() + "/resources/map.zip";


    @Autowired
	private AreaMapMapper mapper;
 
	public int insert(AreaMap bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(AreaMap bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public AreaMap get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<AreaMap> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<AreaMap> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<AreaMap> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<AreaMap> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<AreaMap> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<AreaMap> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public String generateMap() {
		logger.info("begin generateMap");
		
		//TODO 获取社区的地图
		List<MapLatlng> retLs = new ArrayList<MapLatlng>();
		
		List<AreaMap> list = mapper.selectAll();
		
		if( CollectionUtils.isEmpty(list)){
			return "no map data";
		}
		
		for( AreaMap map:list ){
			MapLatlng mapLatlng = generateOneMap(map);
			retLs.add(mapLatlng);
		}
		
		Gson gson = new Gson();
		String data = gson.toJson(retLs);
		
		//TODO 写入文件
		FileUtil.createFile(MAP_FILE_PATH, "thq.txt", data);
		
		//TODO 压缩文件
		FileUtil.zip(MAP_FILE_PATH,MAP_FILE_ZIP_PATH);
		
		
		
		return MAP_FILE_ZIP_PATH;
	}
	
	@Override
	public String generateWebMap(){
		List<AreaMap> list = mapper.selectAll();
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		List<Feature> featureList = new ArrayList<Feature>();
		
		if( CollectionUtils.isEmpty(list)){
			return "no map data";
		}
		for( AreaMap map:list ){
			Feature feature = generateOneWebMap(map);
			featureList.add(feature);
		}
		
		retMap.put("type", "FeatureCollection");
		retMap.put("features", featureList);
		
		Gson gson = new Gson();
		String data = gson.toJson(retMap);
		
		return data;
		
	}
	
	private Feature generateOneWebMap(AreaMap map){
		Feature feature = new Feature();
		feature.setType("Feature");
		feature.setId(map.getAreaCode());
		
		Properties properties = new Properties(map.getAreaName());
		feature.setProperties(properties);
		
		Geometry geometry = new Geometry();
		geometry.setType("Polygon");
		List<List<List<Double>>> coordinates = new ArrayList<List<List<Double>>>();
		List<List<Double>> pointList = LatlngUtil.getGeoPointList(map.getMapData());
		coordinates.add(pointList);
		geometry.setCoordinates(coordinates);
		
		feature.setGeometry(geometry);
		
		return feature;
	}
	
	
	private MapLatlng generateOneMap(AreaMap map){
		
		MapLatlng mapLatlng = new MapLatlng();
		
		String code = map.getAreaCode();
		String name = map.getAreaName();
		mapLatlng.setBlockCode(code);
		mapLatlng.setName(name);
		
		String centerStr = map.getAreaCenter();
		Latlng center = LatlngUtil.getLatlng(centerStr);
		if( center != null){
			mapLatlng.setCenter(center);
		}else{
			logger.warn("map data cernter is null,map={}",map);
		}
		
		String mapData = map.getMapData();
		List<Latlng> latlngList = new ArrayList<Latlng>();
		String[] latlngStrs = StringUtils.split(mapData, ";");
		if(latlngStrs.length < 3){
			logger.warn("map data not enough point");
			return null;
		}
		for( int i=0;i<latlngStrs.length;i++){
			Latlng latlng = LatlngUtil.getLatlng(latlngStrs[i]);
			if( latlng != null){
				latlngList.add(latlng);
			}
		}
		
		List<List<Latlng>> latlngListLs = new ArrayList<List<Latlng>>();
		latlngListLs.add(latlngList);
		mapLatlng.setLatlngList(latlngListLs);
		
		return mapLatlng;

	}
	
	
	
	
	
	
	
}
