package org.fire.platform.common.serialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.modules.sys.domain.Dict;
import org.fire.platform.modules.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("BeanDefineConfigue")
public class DictMap implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	IDictService dictService;
	
	private static Map<String,String> dictMap = new HashMap<String,String>();
	
	public static String getDict(String key){
		return dictMap.get(key);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		List<Dict> list = dictService.queryAll();
		if( CollectionUtils.isEmpty(list)){
			return;
		}
		for( Dict dict:list){
			dictMap.put(dict.getTypeCode()+"_"+dict.getCode(), dict.getName());
		}
		
		
	}

}
