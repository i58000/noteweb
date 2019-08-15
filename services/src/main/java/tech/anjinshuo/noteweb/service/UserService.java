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
    
    public int login(String username, String password) {
    	// 0 success
    	// 1 no username
    	// 2 wrong password
    	// 3 username no exist
    	
		if ("".equals(username) || null == username || "".equals(password) || null == password) {
    		// no username, auto login but fail
			return 1;
		} 
		List<User> list = this.userRepository.findByUsername(username);
    	if (0 == list.size()) {
    		// username no exist
    		return 3;
    	}
		if (!list.get(0).getPassword().equals(password)){
			// wrong password
			return 2;
		}
		// success
		return 0;
    }
    public String getNoteId(String username) {
    	User userEntity = userRepository.findByUsername(username).get(0);
    	String noteId = userEntity.getNoteId();
		return noteId;
    }
}
