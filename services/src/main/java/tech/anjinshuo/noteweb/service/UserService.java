package tech.anjinshuo.noteweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.User;
import tech.anjinshuo.noteweb.mongo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public Response login(String username, String password) {
    	Response resp = new Response();
    	// 0 success
    	// 1 no username, auto login but fail
    	// 2 wrong password
    	// 3 username no exist
    	try {
    		if ("".equals(username) || null == username || "".equals(password) || null == password) {
        		// no username, auto login but fail
    			return resp.fail(1, "try auto login");
    		} 
    		List<User> list = this.userRepository.findByUsername(username);
        	if (0 == list.size()) {
        		// username no exist
        		return resp.fail(3, "username no exist");
        	}
    		if (!list.get(0).getPassword().equals(password)){
    			// wrong password
    			return resp.fail(2, "wrong password");
    		}
    		// success
			return resp.success(0);
    	} catch(Exception e){
    		return resp.exception(e.getMessage());
    	}
    }
}
