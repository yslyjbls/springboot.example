package springboot.example.controller.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.service.mybatis.MybatisService;

@RestController
@RequestMapping("/mybatis")
public class MybatisDemoController {

	@Autowired
	private MybatisService mybatisService;
	
	@RequestMapping("test")
	public List<Map<String,Object>> redisTest(){
		return mybatisService.selectAll();
	}
}
