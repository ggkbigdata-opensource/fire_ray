package org.fire.platform.common.serialize;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Resource;

import org.fire.platform.modules.sys.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

@Resource
public class DictNameTransfer extends JsonSerializer<String> implements ContextualSerializer{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IDictService dictService;
	
	private String param;

    //必须要保留无参构造方法
    public DictNameTransfer() {
        
    }
    
    public DictNameTransfer(String param) {
        this.param = param;
    }

	@Override
	public void serialize(String value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		logger.info("translate dict");
		// TODO 根据param和值取字典key
		String key = param+"_"+value;
		String retValue = DictMap.getDict(key);
		if( retValue != null ){
			gen.writeString(retValue);
		}else{
			gen.writeString(value);
		}
		
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov,
			BeanProperty property) throws JsonMappingException {
		// TODO Auto-generated method stub
		if (property != null) { //为空直接跳过
            if (Objects.equals(property.getType().getRawClass(), String.class)) { //非String类直接跳过
            	DictTransferAnnotation dictTransfer = property.getAnnotation(DictTransferAnnotation.class);
                if (dictTransfer == null) {
                	dictTransfer = property.getContextAnnotation(DictTransferAnnotation.class);
                }
                if (dictTransfer != null) { //如果能得到注解，就将注解的value传入ImageURLSerialize
                    return new DictNameTransfer(dictTransfer.param());
                }
            }
            return prov.findValueSerializer(property.getType(), property);
        }
        return prov.findNullValueSerializer(property);
	}
	

}
