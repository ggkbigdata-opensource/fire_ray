package org.fire.platform.modules.front.vo;

import java.util.Map;

public class RequestVo {
	private String apiKey;
	private Integer pageNum;
	private Integer paseSize;
	private Map<String,Object> params;
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPaseSize() {
		return paseSize;
	}
	public void setPaseSize(Integer paseSize) {
		this.paseSize = paseSize;
	}
	
	

}
