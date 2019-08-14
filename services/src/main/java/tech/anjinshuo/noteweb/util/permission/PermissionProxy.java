package tech.anjinshuo.noteweb.util.permission;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tech.anjinshuo.noteweb.domain.rest.Response;

@Configuration
@Aspect
public class PermissionProxy {
	
	@Around("@annotation(tech.anjinshuo.noteweb.util.permission.CheckPermission)")
	public Object check(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		//获取被代理的方法名
        Method method = signature.getMethod();
		String methodName = method.getName();
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session =request.getSession();
        //拿到储存在session中的用户id
        String username = (String) session.getAttribute("username");
        
        if ("admin".equals(username)) {
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