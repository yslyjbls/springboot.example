package springboot.example.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//是方法参数或字段的情况下使用
@Target({ElementType.PARAMETER, ElementType.FIELD})
//在运行时触发
@Retention(RetentionPolicy.RUNTIME)
//注解包含在javadoc中
@Documented
public @interface NotNull {
	
	String value();

}
