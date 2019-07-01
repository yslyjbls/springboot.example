package springboot.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@Api(value = "api测试描述",tags="测试api分类")
@RestController
public class SwaggerController {
	
	@ApiOperation(httpMethod = "GET",response = String.class ,tags = "api方法",notes = "方法描述", value = "api方法value")
	@RequestMapping("/test/api")
	public String getA(){
		return "hello";
	}

}
