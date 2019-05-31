package com.elvino.rest.model;

public class Result {
	private boolean success;
	private int responseCode;
	private String errorIndonesia;
	private String errorEnglish;
	private String messageIndonesia;
	private String messageEnglish;
	private Object object;
	
	public static Result success(Object object, int responseCode, String indonesia, String english) {
		Result r = new Result();
		r.success = true;
		r.responseCode = responseCode;
		r.messageIndonesia = indonesia;
		r.messageEnglish = english;
		r.object = object;
		return r;
	}
	
	public static Result success(Object object) {
		return success(object, 200, null, null);
	}
	
	public static Result success(String indonesia, String english) {
		return success(null, 200, indonesia, english);
	}
	
	public static Result error(Object object, int responseCode, String indonesia, String english) {
		Result r = new Result();
		r.success = false;
		r.responseCode = responseCode;
		r.messageIndonesia = indonesia;
		r.messageEnglish = english;
		r.object = object;
		return r;
	}
	
	public static Result error(String indonesia, String english) {
		return error(null, 404, indonesia, english);
	}
	
	public boolean isSuccess() { return success; }
	public void setSuccess(boolean success) { this.success = success; }
	
	public int getResponseCode() { return responseCode; }
	public void setResponseCode(int responseCode) { this.responseCode = responseCode; }
	
	public String getErrorIndonesia() { return errorIndonesia; }
	public void setErrorIndonesia(String errorIndonesia) { this.errorIndonesia = errorIndonesia; }
	
	public String getErrorEnglish() { return errorEnglish; }
	public void setErrorEnglish(String errorEnglish) { this.errorEnglish = errorEnglish; }
	
	public String getMessageIndonesia() { return messageIndonesia; }
	public void setMessageIndonesia(String messageIndonesia) { this.messageIndonesia = messageIndonesia; }
	
	public String getMessageEnglish() { return messageEnglish; }
	public void setMessageEnglish(String messageEnglish) { this.messageEnglish = messageEnglish; }
	
	public Object getObject() { return object; }
	public void setObject(Object object) { this.object = object; }
}