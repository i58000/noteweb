package tech.anjinshuo.noteweb.service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.Cate;
import tech.anjinshuo.noteweb.mongo.entity.Foto;
import tech.anjinshuo.noteweb.mongo.repository.FotoRepository;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public Response findAll() {
    	Response resp = new Response();
    	try{
    		return resp.success(fotoRepository.findAll());
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
    }
    
    public Response findByCateId(String cateId) {
    	Response resp = new Response();
    	try{
    		return resp.success(fotoRepository.findByCateId(cateId));
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
    }
    
//    public Response add(String name, String cateId){
//    	// 1 exist
//    	// 2 cateId not exist
//    	Response resp = new Response();
//    	try {
//    		Cate cateEntity = cateRepository.findBy_id(cateId);
//    		if (null == cateEntity) {
//    			return resp.fail(2, "cateId not exist");
//    		}
//			Foto entity = new Foto();
//        	entity.setName(name);
//        	entity.setCateId(cateId);
//        	Foto res = fotoRepository.insert(entity);
//        	return resp.success(res);
//    	} catch (DuplicateKeyException e){
//    		return resp.fail(1, "foto name exist, DuplicateKeyException");
//    	} catch (Exception e) {
//    		return resp.exception(e.getMessage());
//    	}
//    }
    
    public Response upload(Cate cateEntity, List<MultipartFile> files) {
		Response resp = new Response();
    	try {
    		if(null == cateEntity) {
    			return resp.fail(2, "cateId not exist");
    		}
    		String cateId = cateEntity.get_id();
			String url = "http://localhost:7001/fotos/admin/upload";
			List<Foto> entityList = new ArrayList<Foto>();
			MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();        
			map.add("cateId", cateId);
			for (int i = 0; i <files.size(); i++) {
				MultipartFile mpf = files.get(i);
				if (!mpf.isEmpty()) {
					String filename = mpf.getOriginalFilename();
					Foto entity = new Foto();
					entity.setCateId(cateId);
					entity.setName(filename);
					entityList.add(entity);
			        String tempFilePath = System.getProperty("java.io.tmpdir") + "/" + entity.get_id(); //mpf.getOriginalFilename();
			        System.out.println("====tempFilePath.old===="+tempFilePath);
			        File tempFile = new File(tempFilePath);
					mpf.transferTo(tempFile); //生成临时文件
			        FileSystemResource fsr = new FileSystemResource(tempFilePath);//把临时文件变成filesystemresource
					map.add("foto", fsr);
				}
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	        headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
	        HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String, Object>>(map, headers);        
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> res = restTemplate.postForEntity(url, req, String.class);
			
	        if(!"success".equals(res.getBody())) {
	        	return resp.fail(1, "upload fail");
	        }
	        try {
	        	for(int i = 0; i < entityList.size(); i++){
		        	Foto entity = entityList.get(i);
		        	fotoRepository.insert(entity);
		        }
	        } catch (Exception e) {
		        return resp.exception(e.getMessage());
	        }
	        return resp.success(entityList);
    	} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resp.exception(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resp.exception(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resp.exception(e.getMessage());
		}
    }
    
	public Response delete(String id){
    	Response resp = new Response();
    	// 0 success
    	// 1 not exist
    	// 2 delete foto file fail
    	try {
    		Foto entity = fotoRepository.findBy_id(id);
    		if (null == entity) {
    			return resp.fail(1, "foto id not exist");
    		}
    		
    		String cateId = entity.getCateId();
			MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();        
			map.add("cate", cateId);
			map.add("name", id);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			
	        HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String, Object>>(map, headers);        

			String url = "http://localhost:7001/fotos/admin/deleteFoto";
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> res = restTemplate.postForEntity(url, req, String.class);

	        if(!"success".equals(res.getBody())) {
	        	return resp.fail(2, "delete foto file fail");
	        }
	        
			fotoRepository.delete(entity);
			return resp.success(0);
    	} catch (Exception e) {
    		return resp.exception(e.getMessage());
    	}
	}
	
	public Response deleteByCateId(String cateId){
		Response resp = new Response();
    	// 0 success
    	// 1 delete foto file fail
    	try {
			MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();        
			map.add("cate", cateId);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			
	        HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String, Object>>(map, headers);        

			String url = "http://localhost:7001/fotos/admin/deleteCate";
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> res = restTemplate.postForEntity(url, req, String.class);

	        if(!"success".equals(res.getBody())) {
	        	return resp.fail(1, "delete cate fotos file fail");
	        }
	        
			fotoRepository.deleteAll(fotoRepository.findByCateId(cateId));
			
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
    		Foto entity = fotoRepository.findBy_id(id);
    		if (null == entity) {
    			// not exist
    			return resp.fail(1, "foto id not exist");
    		} 
			String oldname = entity.getName();
			if (oldname != null && oldname.equals(name)) {
				// no change
				return resp.fail(2, "new name no change");
			}
			entity.setName(name);
			fotoRepository.save(entity);
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
