package tech.anjinshuo.noteweb.domain.rest;

import java.io.Serializable;

import lombok.Data;

@Data
public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String name;
	private String id;
	private String cateId;
	private String username;
	private String password;
	private Boolean isMobile;
}
