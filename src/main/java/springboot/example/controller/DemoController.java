package springboot.example.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.annotation.CheckParam;
import springboot.example.annotation.NotEmpty;

/**
 * demo测试
 * @author lich
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@Value("${server.port}")
	private String port;
	
	@CheckParam
	@RequestMapping("/query")
	public String query(@NotEmpty("不能为空") String name){
		//int a = 1/0;
		return name + "你好哈哈哈：" + port;
	}

}
