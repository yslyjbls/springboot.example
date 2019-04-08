package springboot.example.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import springboot.example.annotation.ApiVersion;
import springboot.example.annotation.CheckParam;
import springboot.example.annotation.NotEmpty;
import springboot.example.annotation.NotNull;


/**
 * annotation-class 工具类
 * @author lich
 *
 */
public class AnnotationClassUtils {
	
	private static List<Class<?>> classList = new ArrayList<>();

	static{
		classList.add(CheckParam.class);
		classList.add(NotNull.class);
		classList.add(NotEmpty.class);
		classList.add(ApiVersion.class);
	}
	
	/**
	 * 获取类集合
	 * @author lich
	 * @return
	 */
	public static List<Class<?>> getAnnotationClassList(){
		return classList;
	}
	
	/**
	 * 判断自定义注解是否存在
	 * @author lich
	 * @param anno
	 * @return
	 */
	public static boolean existAnnotation(Annotation anno){
		for (Class<?> c : classList) {
			if(anno.annotationType() == c){
				return true;
			}
		}
		return false;
	}
	
}
