package springboot.example.controller;


<<<<<<< Updated upstream
import java.util.List;

import javax.validation.Valid;
=======
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
>>>>>>> Stashed changes

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

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
	
<<<<<<< Updated upstream
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

=======
	@RequestMapping("/device/rpc")
	public String rpc(){
		return "你好哈哈哈：" + port;
	}
	
	@RequestMapping("/log/0")
	public String log(HttpServletResponse resp){
		Map<String,Object> param = new HashMap<>();
		param.put("action", "33");
		param.put("enmsg", "cZA4pl+XD7BE1pfR1Ij9JswxmrkbDfEK6D3es3oklpl7TZUAqnLRZW/uZZBYrsjxWoPOCXJwynC7GfMK+cNQV3FETN2qMCtEfSKY4B6UE2I=");
		
		//将返回的提示信息输出到页面
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		response.setContentType("application/json;charset=utf-8");
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			byte data[] = JSONObject.toJSONString(param).getBytes();
			out.write(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
>>>>>>> Stashed changes
}
