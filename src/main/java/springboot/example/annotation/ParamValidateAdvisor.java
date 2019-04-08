package springboot.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import springboot.example.utils.AnnotationClassUtils;

/**
 * AOP完成自定义注解的相关校验
 * 
 * @author lich
 *
 */
@Component
@Aspect
@Order(2)
public class ParamValidateAdvisor {

	private static Logger logger = LoggerFactory.getLogger(ParamValidateAdvisor.class);

	/**
	 * 定义切点（CheckParam） 来验证是否开启自定义注解的开关
	 */
	@Pointcut("@annotation(springboot.example.annotation.CheckParam)")
	private void pointCut() {
	}

	/**
	 * 切面回环处理
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("AOP开始校验参数");
		// 获取参数
		Object[] objs = pjp.getArgs();
		// 获取方法
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();

		// 获取注解
		Annotation[][] annos = method.getParameterAnnotations();
		// 校验是否有某个注解
		boolean flag = validateParameterAnnotations(annos);
		if (!flag) {
			return pjp.proceed(objs);
		}

		// 判断自定义注解是否存在，如果存在则进行封装
		List<Param> params = AnnotationHelper.getParms(method, objs);
		if (!params.isEmpty()) {
			for (Param param : params) {
				String validRes = validateDetail(param);
				if (null != validRes && !"".equals(validRes)) {
					logger.info("客户端上报参数错误详细信息:{}", validRes);
					throw new RuntimeException(validRes);
				}
			}
		}
		return pjp.proceed(objs);
	}

	/**
	 * 具体的校验逻辑,返回对应的错误信息
	 * 
	 * @author lich
	 * @param param
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private String validateDetail(Param param) {
		StringBuilder sb = new StringBuilder();
		Object param_Val = param.getValue();// 参数值
		Annotation anno = param.getAnno(); // 注解
		if (anno instanceof NotNull) {
			append(sb, validateNotNull(param_Val, param.getAnno()));
		} else if (anno instanceof NotEmpty) {
			append(sb, validateNotEmpty(param_Val, param.getAnno()));
		}
		return sb.toString();
	}

	private String validateNotEmpty(Object param_Val, Annotation anno) {
		if (isEmpty(param_Val)) {
			NotEmpty notEmpty = (NotEmpty) anno;
			return notEmpty.value();
		}
		return null;
	}

	/**
	 * 验证Obj 是否为null以及trim()后是否为""
	 * 
	 * @author lich
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else {
			String objStr = obj.toString();
			return null == objStr || "".equals(objStr.trim()) || "[]".equals(obj.toString());
		}
	}

	private String validateNotNull(Object param_Val, Annotation anno) {
		if (null == param_Val) {
			NotNull NotNull = (NotNull) anno;
			return NotNull.value();
		}
		return null;
	}

	private void append(StringBuilder sb, String res) {
		if (null != res && !"".equals(res)) {
			sb.append(res + " ");
		}
	}

	/**
	 * 验证是否有某个自定义注解（在类、方法、参数上）
	 * 
	 * @param annos
	 * @return
	 */
	private boolean validateParameterAnnotations(Annotation[][] annos) {
		for (Annotation[] at : annos) {
			for (Annotation a : at) {
				if (AnnotationClassUtils.existAnnotation(a)) {
					return true;
				}
			}
		}
		return false;
	}

}
