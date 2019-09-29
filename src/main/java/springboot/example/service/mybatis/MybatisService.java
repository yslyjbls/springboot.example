package springboot.example.service.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.example.dao.MybatisMapper;

@Service
public class MybatisService {
	
	@Autowired
	private MybatisMapper mybatisMapper;

	public List<Map<String,Object>> selectAll() {
		return mybatisMapper.selectAll();
	}

}
