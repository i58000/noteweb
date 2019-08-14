package tech.anjinshuo.noteweb.domain.rest;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Header {
		SUCCESS		(200, "SUCCESS"),
//		SUCCESS_1	(201, "SUCCESS_1"),
		FAIL		(222, "FAIL"),
		EXCEPTION	(500, "EXCEPTION"),
		NOCONTENT	(204, "NO CONTENT"),
		UNAUTHORIZED(401, "UNAUTHORIZED");
		public int STATUS;
		public String MESSAGE;
		private Header(int s, String m) {
			this.STATUS = s;
			this.MESSAGE = m;
		}
	}

	private int status;
	private String message;
	private String timestamp;
	private Object data;
	
	// success: object
	public Response success(Object obj) {
		this.status  = Header.SUCCESS.STATUS;
		this.message = Header.SUCCESS.MESSAGE;
		this.data = obj;
		return this;
	}
	
	// success: object
	public Response success(int status, String message, Object obj) {
		this.status  = status; // Header.SUCCESS.STATUS;
		this.message = message; //Header.SUCCESS.MESSAGE;
		this.data = obj;
		return this;
	}
	
	// fail: err code
	public Response fail() {
		this.status  = Header.FAIL.STATUS;
		this.message = Header.FAIL.MESSAGE;
		return this;
	}
	public Response fail(Object obj) {
		this.fail();
		this.data = obj;
		return this;
	}
	public Response fail(String msg) {
		this.fail();
		this.message = msg;
		return this;
	}
	public Response fail(Object obj, String msg) {
		this.fail();
		this.data = obj;
		this.message = msg;
		return this;
	}
	
	// exception: err msg
	public Response exception(String errMsg) {
		this.status  = Header.EXCEPTION.STATUS;
		this.message = Header.EXCEPTION.MESSAGE;
//		this.data = errMsg;
		if (null != errMsg && !"".equals(errMsg)) {
			this.message = errMsg;
		}
		return this;
	}
	
	// no auth: err msg
	public Response unauth() {
		this.status  = Header.UNAUTHORIZED.STATUS;
		this.message = Header.UNAUTHORIZED.MESSAGE;
		return this;
	}
	
	// init
	public Response() {
		this.timestamp = Instant.now().toString();
		this.status  = Header.NOCONTENT.STATUS;
		this.message = Header.NOCONTENT.MESSAGE;
	}
	
//	public static void main(String[] args){
//        Response resp = new Response();
//        System.out.println(resp.fail());
//        System.out.println(resp.fail("sss"));
//        System.out.println(resp.fail(1));
//        System.out.println(resp.fail(1, "SSS"));
//    }
}
