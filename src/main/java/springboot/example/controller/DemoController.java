package springboot.example.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.annotation.CheckParam;
import springboot.example.annotation.NotEmpty;

/**
 * demo测试(自定义注解、)
 * @author lich
 *
 */
@RestController
@RequestMapping("/device")
public class DemoController {
	
	@Value("${server.port}")
	private String port;
	
	/**
	 * 自定义注解控制
	 * @param name
	 * @return
	 */
	@CheckParam
	@RequestMapping("/query")
	public String query(@NotEmpty("不能为空") String name){
		//int a = 1/0;
		return name + "你好哈哈哈：" + port;
	}
	
	/**
	 * springMVC注解控制参数
	 * @param name
	 * @return
	 */
	@RequestMapping("/paramValid4MVC")
	public String paramValid4MVC(@Valid String name,BindingResult result){
		validate(result);
		//int a = 1/0;
		return name + "你好哈哈哈：" + port;
	}

	private void validate(BindingResult result) {
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError fieldError : errorList) {
				Assert.isTrue(false,fieldError.getDefaultMessage());
			}
		}
	}
}
