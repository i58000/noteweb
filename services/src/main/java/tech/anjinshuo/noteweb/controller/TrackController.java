package tech.anjinshuo.noteweb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.vo.PageViewVO;
import tech.anjinshuo.noteweb.service.TrackService;
import tech.anjinshuo.noteweb.util.permission.CheckPermission;

@RestController
@RequestMapping("/track")
public class TrackController {
//	@Autowired
//	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
//	@Autowired
//	private HttpSession session;
	
    @Autowired
    private TrackService trackService;
	
	@PostMapping("")
	public void track(@RequestBody String req) {
    	// System.out.println(req);
    	trackService.insert(req);
	}
	
	@GetMapping("/v")
	@CheckPermission
	public Response v() {
		Response resp = new Response();
		List<PageViewVO> list = trackService.getPVAndUV();
		resp.success(list);
	    return resp;
	}
}
