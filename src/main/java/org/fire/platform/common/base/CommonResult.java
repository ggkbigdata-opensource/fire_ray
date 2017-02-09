package org.fire.platform.common.base;

public class CommonResult {
	private boolean successful = true;
	
	private String msg;
	
	private Object data;
	
	private long total;
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	
	public static CommonResult success(){
		CommonResult result = new CommonResult();
		result.successful = true;
		return result;
	}
	
	public static CommonResult success(Object data){
		CommonResult result = new CommonResult();
		result.successful = true;
		result.data = data;
		return result;
	}
	
	public static CommonResult success(Object data,long total){
		CommonResult result = new CommonResult();
		result.successful = true;
		result.data = data;
		result.total = total;
		return result;
	}
	
	public static CommonResult fail(){
		CommonResult result = new CommonResult();
		result.successful = false;
		return result;
	}
	
	
	public static CommonResult fail(String msg){
		CommonResult result = new CommonResult();
		result.successful = false;
		result.msg = msg;
		return result;
	}
	


	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
