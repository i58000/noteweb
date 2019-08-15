package tech.anjinshuo.noteweb.domain.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class RequestSync extends ArrayList<Map> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

}
//
//class ChangedNote  {
//	String _id;
//	String newTtitle;
//	ArrayList<Diff> diff;
//	ChangedNote(){
//	}
//}
//
//class Diff implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	boolean added;
//	boolean removed;
//	int count;
//	String value;
//}