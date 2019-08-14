package tech.anjinshuo.noteweb.mongo.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageViewVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String _id;
	private Integer pv;
	private Integer uv;
}
