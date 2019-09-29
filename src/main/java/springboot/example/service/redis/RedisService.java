package springboot.example.service.redis;

import org.springframework.stereotype.Service;

import springboot.example.redis.annotation.NeteaseEduCache;

@Service
public class RedisService {
	
	@NeteaseEduCache(key = "#name")
	public String getValue(String id,String name){
		System.out.println(id + ":" + name);
		return id + ":" + name;
	}
	
}
