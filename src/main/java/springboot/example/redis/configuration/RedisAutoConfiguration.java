package springboot.example.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * redis配置类
 * 1.创建redis模板
 * 2.序列化redis数据
 * Created by Lich on 2019年9月23日.
 *
 */
@Configuration
public class RedisAutoConfiguration {
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		
		/*
		 * 序列化
		 * 1.用jackson
		 */
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
		/*
		 * 2.自定义
		 */
		RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(); 
		
		//key采用String序列化
		template.setKeySerializer(stringRedisSerializer);
		//hash的key也用String序列化
		template.setHashKeySerializer(stringRedisSerializer);
		//value采用jackson序列化方式
		template.setValueSerializer(redisObjectSerializer);
		//hash的value也采用jackson序列化方式
		template.setHashValueSerializer(redisObjectSerializer);
		template.afterPropertiesSet();
		return template;
	}

}
