package com.Assessment.BookingAssessment.Model;

import lombok.Data;

@Data
public class ResponseMessage {
	private String message;
    private long code;
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long noError) {
		this.code = noError;
	}
    
    
}
