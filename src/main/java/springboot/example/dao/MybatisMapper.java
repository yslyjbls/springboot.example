package springboot.example.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MybatisMapper {

	public List<Map<String,Object>> selectAll() ;

}
