package com.may.example.service.redis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.may.example.model.InternPerson;
import com.may.example.repository.InternPersonRepository;

@Service
public class RedisService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RedisService.class);
	
	private final RedisTemplate<String, Object> redisTemplate;
	private final InternPersonRepository internPersonRepository;

    @Autowired
    public RedisService(InternPersonRepository internPersonRepository,RedisTemplate<String, Object> redisTemplate) {
        this.internPersonRepository = internPersonRepository;
    	this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public void cacheInternPersonInRedis() {
    	
    	List<InternPerson> internPersons = internPersonRepository.findAll();
    	List<String> list = new ArrayList<String>();
    	
    	for(InternPerson person : internPersons) {
    		
    		LOG.info("Person ID: "+ person.getId());
    		redisTemplate.opsForHash().put("InternPerson", person.getId().toString(), person.toString());
    		list.add(person.toString());
    	}
    	
    	redisTemplate.opsForList().rightPushAll("InternPersonList", list.toArray());
    	LOG.info("Intern Person List: " + redisTemplate.opsForList().range("InternPersonList", 0, -1));
    }
    
    public Object getInternPersonFromRedis(Integer id){
    	
    	return redisTemplate.opsForHash().get("InternPerson", id.toString());
    }

}
