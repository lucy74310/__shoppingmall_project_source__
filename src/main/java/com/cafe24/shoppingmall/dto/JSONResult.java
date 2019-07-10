package com.cafe24.shoppingmall.dto;

public class JSONResult {
	private String result;
	private Object data;
	private String message;
	
	public JSONResult(String result) {
		this.result = result;
	}
	public JSONResult(String result, Object data) {
		this.result = result;
		this.data = data;
	}

	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}

	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", data=" + data + ", message=" + message + "]";
	}
	
	
	
}
