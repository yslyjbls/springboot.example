package springboot.example.pojo;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class RequestParamDemo {
	
	//@NotEmpty  
	@Size(min = 10, max = 10) 
	private String name;
	
	@Max(value = 150, message = "age应<150") // 数字
    @Min(value = 1, message = "age应>1") // 数字
	@NotNull(message = "年龄不能为空")
	private Integer  age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer  getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
