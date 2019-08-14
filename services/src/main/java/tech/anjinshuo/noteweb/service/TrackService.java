package tech.anjinshuo.noteweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tech.anjinshuo.noteweb.domain.rest.Response;
import tech.anjinshuo.noteweb.mongo.entity.Track;
import tech.anjinshuo.noteweb.mongo.entity.User;
import tech.anjinshuo.noteweb.mongo.repository.TrackRepository;
import tech.anjinshuo.noteweb.mongo.repository.UserRepository;
import tech.anjinshuo.noteweb.mongo.vo.PageViewVO;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void insert(String jsonStr) {
    	try {
        	JSONArray jsonArr = JSONArray.parseArray(jsonStr);
    		for (int i = 0; i < jsonArr.size(); i++) {
            	Track trackEntity = Track.fromBeacon((JSONObject) jsonArr.get(i));
            	trackRepository.insert(trackEntity);
    		}
//			return resp.success(0);
    	} catch(Exception e){
    		System.out.println(e.getMessage());
//    		return resp.exception(e.getMessage());
    	}
    }
    public void insert(Track entity) {
    	try {
            trackRepository.insert(entity);
    	} catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    public List<PageViewVO> getPVAndUV(){
		List<AggregationOperation> operations = new ArrayList<AggregationOperation>();
		
		operations.add(
				Aggregation.match(
						Criteria.where("action").is("login")
						)
				);
		
		operations.add(
				Aggregation.project("_id")
				.andExpression("date").plus(28800000).as("dateCN")
				.andExpression("ip").as("ip")
				);
		
		operations.add(
				Aggregation.project()
				.andExpression("dateCN").substring(0, 10).as("dateCNdate")
				.andExpression("ip").as("ip")
				);
		
		operations.add(
				Aggregation.group("dateCNdate")
				.count().as("pv")
				.addToSet("ip").as("ipSet")	
				);
		
		operations.add(
				Aggregation.project("_id")
				.andExpression("ipSet").size().as("uv")
				.andExpression("pv").as("pv")
				);
		
		operations.add(
				Aggregation.sort(Sort.Direction.ASC, "_id") // 按日期升序排序
		        );
    	
		Aggregation aggregation = Aggregation.newAggregation(operations);
		
		AggregationResults<PageViewVO> results = mongoTemplate.aggregate(
		                aggregation, "track", PageViewVO.class);
		List<PageViewVO> mappedResults = results.getMappedResults();
		for(int i = 0; i < mappedResults.size(); i++) {
			System.out.println(mappedResults.get(i));
			
		}
		return mappedResults;
    }
}
