package springboot.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * JSR303 校验参数
 * @author lich
 *
 */
@RestController
@RequestMapping("/demo")
@Validated
public class JSR303Controller {
	
	@RequestMapping("/jsr")
	public String query(
			@Min(value = 1, message = "待办 id 不能小于 1。") @RequestParam(required = false, defaultValue = "1") int rp,
			@NotNull(message = "不能为空") String name){
		Map<String, Object> map = new HashMap<String, Object>();
		//int a = 1/0;
		return name + "的年龄：" + rp;
	}

}
