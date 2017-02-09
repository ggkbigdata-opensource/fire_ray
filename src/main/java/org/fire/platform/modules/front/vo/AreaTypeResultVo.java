package org.fire.platform.modules.front.vo;

import java.util.List;

import org.fire.platform.modules.area.vo.AreaTypeDataVo;

public class AreaTypeResultVo {
	private String title;
	List<AreaTypeDataVo> list;
	
	public AreaTypeResultVo(){
		
	}
	
	public AreaTypeResultVo(String title,List<AreaTypeDataVo> list){
		this.title = title;
		this.list = list;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<AreaTypeDataVo> getList() {
		return list;
	}
	public void setList(List<AreaTypeDataVo> list) {
		this.list = list;
	}
	
	
	

}
