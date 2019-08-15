package tech.anjinshuo.noteweb.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.anjinshuo.noteweb.domain.rest.Request;
import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.Track;
import tech.anjinshuo.noteweb.service.NoteService;
import tech.anjinshuo.noteweb.service.UserService;

@RestController
@RequestMapping("/init")
public class InitController {
	@Autowired
	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
	@Autowired
	private HttpSession session;
	@Autowired
	private NoteService noteService;
	@Autowired
	private UserService userService;
	
    @SuppressWarnings("rawtypes")
	@RequestMapping("")
	public Response init() {
    	Response resp = new Response();
    	String username = (String) session.getAttribute("username");
//    	String userId = (String) session.getAttribute("userId");

    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("username", username);
    	if (null != username) {
    		String noteId = userService.getNoteId(username);
    		List<Map> titleList = noteService.getTitleList(username, noteId);
    		
    		map.put("noteId", noteId);
    		map.put("titleList", titleList);
    	}
		resp.success(map);
	    return resp;
	}
}
