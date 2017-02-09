package org.fire.platform.modules.check.bean;


import java.util.List;

public class ResultSearchBean {
	
	private String code;
	private String name;
	private List<ResultSearchBean> typeBean;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResultSearchBean> getTypeBean() {
		return typeBean;
	}
	public void setTypeBean(List<ResultSearchBean> typeBean) {
		this.typeBean = typeBean;
	}
	
}
