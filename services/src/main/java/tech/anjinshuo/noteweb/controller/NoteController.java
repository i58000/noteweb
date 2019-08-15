package tech.anjinshuo.noteweb.controller;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.anjinshuo.noteweb.domain.rest.Request;
import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.service.NoteService;
import tech.anjinshuo.noteweb.util.permission.CheckPermission;

@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;
    @Autowired
    private NoteService noteService;
    
    @PostMapping("/add")
    @CheckPermission
	public Response add() {
    	Response resp = new Response();
    	String username = (String) session.getAttribute("username");
    	String noteId = noteService.add(username);
    	resp.success(noteId);
	    return resp;
	}
    
    @PostMapping("/del")
    @CheckPermission
	public Response del(@RequestBody Request req) {
    	Response resp = new Response();
    	String noteId = req.getNoteId();
    	int code = noteService.del(noteId);
    	resp.success(code);
	    return resp;
	}
    
    @PostMapping("/sync")
    @CheckPermission
	public Response sync(@SuppressWarnings("rawtypes") @RequestBody ArrayList<Map> req) {
    	Response resp = new Response();
    	int code = noteService.sync(req);

	    return resp;
	}
    
    @PostMapping("/content")
    @CheckPermission
    public Response content(@RequestBody Request req) {
    	Response resp = new Response();
    	String noteId = req.getNoteId();
    	String content = noteService.getContent(noteId);
    	resp.success(content);
	    return resp;
	}
    
}
