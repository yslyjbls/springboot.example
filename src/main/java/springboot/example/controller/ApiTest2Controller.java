package springboot.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.annotation.ApiVersion;

/**
 * 版本控制验证2
 * @author lich
 *
 */
@RestController
@ApiVersion(2)
@RequestMapping("/{version}/hello")
public class ApiTest2Controller {
	
	@RequestMapping("/world3")
	@ResponseBody
	@ApiVersion(3)
    public String helloWorld3(){
        System.out.println("版本是3的接口");
        return "hello,world .version is 3";
    }
	
	@RequestMapping("/world2")
	@ResponseBody
    public String helloWorld2(){
		System.out.println("版本是2的接口");
        return "hello,world .version is 2";
    }
	
	public static void sun(int n) {
		// a为某一项的值，sum为前几项的总和;
		int a = 1, sum = 0;
		for (int i = 1; i <= n; i++) {
			a *= i;
			sum += a;
			System.out.println(i + "!=" + a);
		}
		System.out.println("1!+2!+3!+...+"+n+"!=" + sum);
	}

	public static void main(String[] args) {
		sun(10);
		
	}

}
