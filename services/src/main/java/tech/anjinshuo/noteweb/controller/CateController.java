package tech.anjinshuo.noteweb.controller;


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
import tech.anjinshuo.noteweb.service.CateService;
import tech.anjinshuo.noteweb.service.FotoService;
import tech.anjinshuo.noteweb.util.permission.CheckPermission;

@RestController
@RequestMapping("/cate")
public class CateController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;
    @Autowired
    private CateService cateService;
    @Autowired
    private FotoService fotoService;
    
    @PostMapping("/add")
    @CheckPermission
	public Response add(@RequestBody Request req) {
    	Response resp = cateService.add(req.getName());
	    return resp;
	}
    
    @PostMapping("/delete")
    @CheckPermission
	public Response delete(@RequestBody Request req) {
    	String cateId = req.getId();
    	Response resp = new Response();
    	// check cate exist
    	resp = cateService.findById(cateId);
    	if(Response.Header.SUCCESS.STATUS != resp.getStatus()){
    		return resp;
    	}
    	// delete fotos
    	resp = fotoService.deleteByCateId(cateId);
    	if(Response.Header.SUCCESS.STATUS != resp.getStatus()){
    		return resp;
    	}
    	// delete cate
    	resp = cateService.delete(cateId);
	    return resp;
	}
    
    @PostMapping("/update")
    @CheckPermission
	public Response update(@RequestBody Request req) {
    	Response resp = cateService.update(req.getId(), req.getName());
	    return resp;
	}
    
    @GetMapping("/all")
	public Response all() {
    	Response resp = cateService.findAll();
    	return resp;
	}
    
}
