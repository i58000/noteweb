package tech.anjinshuo.noteweb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import tech.anjinshuo.noteweb.mongo.entity.Note;
import tech.anjinshuo.noteweb.mongo.entity.User;
import tech.anjinshuo.noteweb.mongo.repository.NoteRepository;
import tech.anjinshuo.noteweb.mongo.repository.UserRepository;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;
    
    
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
	public String sync(ArrayList<Map> data, String username){
		System.out.println(data);
		String noteId = null;

		for(Map<String, Object> changedNote : data) {
			System.out.println(changedNote);
			noteId = (String) changedNote.get("noteId");
			if (null != noteId) {
				User userEntity = userRepository.findByUsername(username).get(0);
				userEntity.setNoteId(noteId);
				userRepository.save(userEntity);
				continue;
			}
			String _id = (String) changedNote.get("_id");
			String newTitle = (String) changedNote.get("newTitle");
			List<Map<String, Object>> diff = (List<Map<String, Object>>) changedNote.get("diff");
			Note noteEntity = noteRepository.findBy_id(_id);
			if (null != newTitle) {
				noteEntity.setTitle(newTitle);
			}
			if (null != diff) {
				String oldContent = noteEntity.getContent();
				String newContent = applyDiff(oldContent, diff);
				noteEntity.setContent(newContent);
			}
			noteRepository.save(noteEntity);
		}
    	return noteId;
	}
	
	public int checkPermission(String noteId, String username) {
		int code;
		if(noteId == null) {
			code = username != null ? 0 : 1;
		} else {
			Note noteEntity = noteRepository.findBy_id(noteId);
			code = noteEntity.getUsername().equals(username) ? 0 : 1;
		}
		
		return code;
	}
	
	private String applyDiff(String oldStr, List<Map<String, Object>> diff) {
//		applyDiff(oldStr, diff) {
		oldStr = (null != oldStr) ? oldStr : "";
	        List<String> lines = new LinkedList<>(Arrays.asList(oldStr.split("\n")));
	        
	        int li = 0;
	        for (int di = 0; di < diff.size(); di++) {
	            Map<String, Object> d = diff.get(di);
	            if (null != d.get("added") && (boolean) d.get("added")) {
	                List<String> linesAdded =Arrays.asList(Arrays.copyOf(((String) d.get("value")).split("\n"), (int)d.get("count")));
//	                lines.splice(li, 0, ...linesAdded);
	                lines.addAll(li, linesAdded);
	                li += (int) d.get("count");
	            } else if (null != d.get("removed") && (boolean) d.get("removed")) {
//	                lines.splice(li, d.count);
	            	List linesRemoved = lines.subList(li, li + (int) d.get("count"));
	            	linesRemoved.clear();
	            } else {
	                li += (int) d.get("count");
	            }
	        }
	        String newStr = String.join("\n", lines); //lines.join("\n");
//	        return newStr;
//	    }
		return newStr;
	}
}
