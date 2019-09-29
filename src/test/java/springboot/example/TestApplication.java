package springboot.example;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.example.redis.utils.RedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
    public void redisTest(){
		RedisUtils.set("123", "hello world!");
		System.out.println(RedisUtils.get("123"));
    }

}
