package org.fire.platform.modules.area.vo;

import java.util.ArrayList;
import java.util.List;

public class AreaCompareData {
	private String name;
	List<ValueDataVo> datas = new ArrayList<ValueDataVo>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ValueDataVo> getDatas() {
		return datas;
	}
	public void setDatas(List<ValueDataVo> datas) {
		this.datas = datas;
	}
	
	public void addData(String year,Integer data){
		ValueDataVo value = new ValueDataVo();
		value.setYear(year);
		value.setData(data);
		datas.add(value);
	}
	
	
	
	
	

}
