package tech.anjinshuo.noteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.Cate;
import tech.anjinshuo.noteweb.mongo.repository.CateRepository;

@Service
public class CateService {
    @Autowired
    private CateRepository cateRepository;
    
    public Response findAll() {
    	Response resp = new Response();
    	try{
    		return resp.success(cateRepository.findAll());
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
    }
    
    public Response findById(String id) {
    	Response resp = new Response();
    	try{
    		return resp.success(cateRepository.findBy_id(id));
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
    }
    
    public Response add(String name){
    	// 1 exist
    	Response resp = new Response();
    	try {
        	Cate entity = new Cate();
        	entity.setName(name);
        	Cate res = cateRepository.insert(entity);
        	return resp.success(res);
    	} catch (DuplicateKeyException e){
    		return resp.fail(1, "foto name exist, DuplicateKeyException");
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
    }
    
	public Response delete(String id){
    	Response resp = new Response();
    	// 0 success
    	// 1 not exist
    	try {
    		Cate entity = cateRepository.findBy_id(id);
    		if (null == entity) {
    			return resp.fail(1, "cate id not exist");
    		} 
        	cateRepository.delete(entity);
        	return resp.success(0);
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
	}
	
	public Response update(String id, String name){
		Response resp = new Response();
    	// 0 success
    	// 1 not exist
		// 2 no change
		// 3 save fail
    	try {
    		Cate entity = cateRepository.findBy_id(id);
    		if (null == entity) {
    			// not exist
    			return resp.fail(1, "cate id not exist");
    		}
			String oldname = entity.getName();
			if (oldname.equals(name)) {
    			// no change
				return resp.fail(2, "new name no change");
			} 
			entity.setName(name);
			cateRepository.save(entity);
			if (oldname.equals(entity.getName())){
				// save fail
				return resp.fail(3, "save fail");
			}
			return resp.success(0);
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
	}
}
