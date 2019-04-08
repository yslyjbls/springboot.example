package springboot.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import springboot.example.utils.AnnotationClassUtils;

/**
 * 注解帮助类
 * 
 * @author lich
 */
public class AnnotationHelper {

	/**
	 * 获取MethodSignature
	 * 
	 * @author lich
	 * @param pjp
	 * @return MethodSignature
	 */
	public static MethodSignature getMethodSignature(ProceedingJoinPoint pjp) {
		return (MethodSignature) pjp.getSignature();
	}

	/**
	 * 获取代理类方法对象
	 * 
	 * @author lich
	 * @param pjp
	 * @return Method
	 */
	public static Method getMethod(ProceedingJoinPoint pjp) {
		return AnnotationHelper.getMethodSignature(pjp).getMethod();
	}

	/**
	 * 获取参数列表
	 * 
	 * @author lich
	 * @param pjp
	 * @return
	 */
	public static Object[] getArgs(ProceedingJoinPoint pjp) {
		return pjp.getArgs();
	}

	/**
	 * 获取参数的相关描述
	 * 
	 * @param method
	 * @param objs
	 * @return
	 */
	public static List<Param> getParms(Method method, Object[] objs) {
		Annotation[][] annos = method.getParameterAnnotations();
		Class<?>[] parameterTypes = method.getParameterTypes();
		List<Param> params = new ArrayList<Param>();
		for (int i = 0; i < annos.length; i++) {
			for (int j = 0; j < annos[i].length; j++) {
				if (AnnotationClassUtils.existAnnotation(annos[i][j])) {
					Param param = new Param(parameterTypes[i].getSimpleName(), parameterTypes[i].getName(),
							parameterTypes[i], objs[i], annos[i][j]);
					params.add(param);
				}
			}
		}
		return params;
	}

}
