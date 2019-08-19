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

@Configuration
@Aspect
public class PermissionProxy {
	
    @Autowired
    private NoteService noteService;
//    @Autowired
//    private UserService userService;
	
	
	@Around("@annotation(tech.anjinshuo.noteweb.util.permission.CheckPermission) && @annotation(cp)")
	public Object check(ProceedingJoinPoint point, CheckPermission cp) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		//获取被代理的方法名
        Method method = signature.getMethod();
		String methodName = method.getName();
		String type = cp.type();
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        //拿到储存在session中的用户id
        String username = (String) session.getAttribute("username");
        
        int code = 1;
        switch(type){
        case "addNote":
        	if(username != null)
        		code = 0;
        	break;
        case "sync":
        	if(username != null)
        		code = 0;
        	break;
        default:
        	//noteId
            Object[] args = point.getArgs();
            String noteId = args.length > 0 ? ((Request) args[0]).getNoteId(): null;
            code = noteService.checkPermission(noteId, username);
        }
        
        if (0 == code) {
        	return point.proceed();
        } else {
        	Response resp = new Response();
			resp.unauth();
			return resp;
        }
    }
}