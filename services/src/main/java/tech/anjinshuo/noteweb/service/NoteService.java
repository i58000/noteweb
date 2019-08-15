package tech.anjinshuo.noteweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import tech.anjinshuo.noteweb.mongo.entity.Note;
import tech.anjinshuo.noteweb.mongo.repository.NoteRepository;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    
    
    @SuppressWarnings("rawtypes")
	public List<Map> getTitleList(String username, String noteId) {
    	List<Note> noteList = noteRepository.findAllByUsername(username);
    	List<Map> titleList = new ArrayList<Map>();
    	for (Note note : noteList) {
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("title", note.getTitle());
    		map.put("_id", note.get_id());
    		if(noteId.equals(note.get_id())) {
    			map.put("content", note.getContent());
    		}
    		titleList.add(map);
    	}
    	return titleList;
    }
    /*
     *  return
     *  	content
     * */
    public String getContent(String noteId) {
    	Note noteEntity = noteRepository.findBy_id(noteId);
    	String content = noteEntity.getContent();
    	return content;
    }
    
    /*
     *  return
     *  	_id
     * */
    public String add(String username) {
        	Note entity = new Note();
        	entity.setUsername(username);
        	Note res = noteRepository.insert(entity);
        	String noteId = res.get_id();
    	return noteId;
    }
    
    /*
     *  return
     * 		0 success
     * 		1 fail
     * */
    public int del(String noteId){
    	Note noteEntity = noteRepository.findBy_id(noteId);
    	noteRepository.delete(noteEntity);
    	return 0;
    }
    
    /*
     *  return
     * 		0 success
     * 		1 fail
     * */
	@SuppressWarnings("unchecked")
	public int sync(ArrayList<Map> data){
		System.out.println(data);
		for(Map<String, Object> changedNote : data) {
			System.out.println(changedNote);
			String noteId = (String) changedNote.get("_id");
			
		}
    	return 0;
	}
	
	public int checkPermission(String noteId, String username) {
		Note noteEntity = noteRepository.findBy_id(noteId);
		int code;
		if(username.equals(noteEntity.getUsername())) {
			code = 0;
		} else {
			code = 1;
		}
		return code;
	}
}
