package springboot.example.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.service.redis.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("test")
	public String redisTest(String id,String name){
		return redisService.getValue(id,name);
	}

}
