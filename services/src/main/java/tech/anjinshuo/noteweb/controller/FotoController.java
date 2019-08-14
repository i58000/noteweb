package tech.anjinshuo.noteweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import tech.anjinshuo.noteweb.domain.rest.Request;
import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.Cate;
import tech.anjinshuo.noteweb.service.CateService;
import tech.anjinshuo.noteweb.service.FotoService;
import tech.anjinshuo.noteweb.util.permission.CheckPermission;

@RestController
@RequestMapping("/foto")
public class FotoController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;
    @Autowired
    private FotoService fotoService;
    @Autowired
    private CateService cateService;
    
    @PostMapping("/upload")
    @CheckPermission
    public Response upload(HttpServletRequest request) {
    	MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);  
		String cateId = params.getParameter("cateId");  
		List<MultipartFile> files = params.getFiles("foto"); 

		Response resp = cateService.findById(cateId);
		if (Response.Header.EXCEPTION.STATUS == resp.getStatus()) {
			return resp;
		}

		Cate cateEntity = (Cate) resp.getData();
		return resp = fotoService.upload(cateEntity, files);
    }  
    
//    @PostMapping("/add")
//    @CheckPermission
//	public Response add(@RequestBody Request req) {
//    	Response resp = fotoService.add(req.getName(), req.getCateId());
//	    return resp;
//	}
    
    @PostMapping("/delete")
    @CheckPermission
	public Response delete(@RequestBody Request req) {
    	Response resp = fotoService.delete(req.getId());
	    return resp;
	}
    
    @PostMapping("/update")
    @CheckPermission
	public Response update(@RequestBody Request req) {
    	Response resp = fotoService.update(req.getId(), req.getName());
	    return resp;
	}
    
    @GetMapping("/findByCateId")
	public Response findByCateId(@RequestParam String cateId) {
    	Response resp = fotoService.findByCateId(cateId);
	    return resp;
	}
    
//    @GetMapping("/all")
//	public Response all() {
//    	Response resp = fotoService.findAll();
//    	if (Response.Header.SUCCESS.STATUS == resp.getStatus()) {
//        	AllCatesWithFotosDTO dto = new AllCatesWithFotosDTO();
//        	dto.addFotos(resp.getData());
//    	}
//	    return resp;
//	}
    
}
