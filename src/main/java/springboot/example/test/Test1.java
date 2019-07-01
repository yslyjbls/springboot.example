package springboot.example.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test1 {
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", 123);

		Map<String,Object> map1 = new HashMap<>();
		map1.put("name", "lich");
		
		map.putAll(map1);
		
		System.out.println(JSON.toJSONString(map));
	}

}
