package tech.anjinshuo.noteweb.util.permission;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tech.anjinshuo.noteweb.domain.rest.Request;
import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.service.NoteService;
import tech.anjinshuo.noteweb.service.UserService;

@Configuration
@Aspect
public class PermissionProxy {
	
    @Autowired
    private NoteService noteService;
//    @Autowired
//    private UserService userService;
	
	
	@Around("@annotation(tech.anjinshuo.noteweb.util.permission.CheckPermission)")
	public Object check(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		//获取被代理的方法名
        Method method = signature.getMethod();
		String methodName = method.getName();
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        //拿到储存在session中的用户id
        String username = (String) session.getAttribute("username");
        //noteId
        String noteId;
        try {
        	noteId = ((Request)point.getArgs()[0]).getNoteId();
        } catch(Exception e) {
        	// 请求中没有noteId的鉴权
        	return point.proceed();
        }
        int code = noteService.checkPermission(noteId, username);
        if (0 == code) {
        	return point.proceed();
        } else {
        	Response resp = new Response();
			resp.unauth();
			return resp;
        }
        
		//按钮采用post方法，通过ajax调用返回json串，提示无权限
//		if(methodName.endsWith("POST")){
//			Response resp = new Response();
//			resp.unauth();
//			return resp;
//		}
    }
}