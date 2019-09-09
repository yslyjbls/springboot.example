package springboot.example.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.example.pojo.TodoPojo;

/**
 * JSR303 校验参数
 * 
 * @author lich
 *
 */
@Controller
@RequestMapping("/demo")
@Validated
public class JSR303Controller {

	@RequestMapping("/jsr")
	@ResponseBody 
	public String query(
			@Min(value = 1, message = "年龄不能小于1") @RequestParam(required = false, defaultValue = "1") int rp,
			@NotNull(message = "不能为空") String name) {
		//Map<String, Object> map = new HashMap<String, Object>();
		// int a = 1/0;
		return name + "的年龄：" + rp + "~~~~~~~~~~~~~~~~~~~";
	}

	@RequestMapping("jsrpojo")
	public String add(@Validated TodoPojo todo) {
		return todo.getPrivateId() + "~~~~~~~~~~~~~~~~~~~~~" + todo.getCreatorId();
	}

}
