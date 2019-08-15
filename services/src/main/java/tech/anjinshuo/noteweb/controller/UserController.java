package tech.anjinshuo.noteweb.controller;

import java.net.InetAddress;
import java.util.HashMap;
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
import tech.anjinshuo.noteweb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
	@Autowired
	private HttpSession session;
    @Autowired
    private UserService userService;
	
    @PostMapping("/login")
	public Response login(@RequestBody Request req) {
    	Response resp = new Response();
    	String username = req.getUsername();
    	String password = req.getPassword();
    	int code = userService.login(username, password);
    	// login success
    	if (0 == code) {
    		// session
    		session.setAttribute("username", username);
    		return resp.success(username);
    	}
    	else {
    		resp.fail(code);
    		
    	}
	    return resp;
	}
    
    @GetMapping("/logout")
	public Response logout() {
    	Response resp = new Response();
    	// 0 success
    	// 1 nologin
    	String username = (String) session.getAttribute("username");
    	if (null != username) {
    		session.setAttribute("username", null);
    		return resp.success(0);
    	} else {
    		return resp.fail(1, "no username in session");
    	}
	}
    
    /** 
     * @Description: 获取客户端IP地址   
     */  
    private String getClientIp() { 
           String ip = request.getHeader("x-forwarded-for");   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getHeader("Proxy-Client-IP");   
           }   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getHeader("WL-Proxy-Client-IP");   
           }   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getRemoteAddr();   
               if(ip.equals("127.0.0.1")){     
                   //根据网卡取本机配置的IP     
                   InetAddress inet=null;     
                   try {     
                       inet = InetAddress.getLocalHost();     
                   } catch (Exception e) {     
                       e.printStackTrace();     
                   }     
                   ip= inet.getHostAddress();     
               }  
           }   
           // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
           if(ip != null && ip.length() > 15){    
               if(ip.indexOf(",")>0){     
                   ip = ip.substring(0,ip.indexOf(","));     
               }     
           }     
           return ip;   
    }

}
