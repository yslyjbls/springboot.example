package springboot.example.redis.annotation;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import springboot.example.redis.utils.RedisUtils;

/**
 * 缓存注解解析类
 * Created by Lich on 2019年9月9日.
 *
 */
@Component
@Aspect
public class CacheAdvisor {
	
	private static final Logger log = LoggerFactory.getLogger(CacheAdvisor.class);
	
	@Pointcut("@annotation(springboot.example.redis.annotation.NeteaseEduCache)")
	private void pointCut(){
		
	}
	
	@Around("pointCut()")
	public Object queryCache(ProceedingJoinPoint pjp) throws Throwable {
		log.info("**********方法执行前增强***************");
		long startTime = System.currentTimeMillis();
		// 获取方法上的注解key的值
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		NeteaseEduCache neteaseEduCache = method.getAnnotation(NeteaseEduCache.class);
		String keyEL = neteaseEduCache.key();
		log.info("************获取到el表达式的keyEL*********");
		log.info("keyEL表达式：" + keyEL);
		/*
		 * 获取EL表达式所需，方法调用参数---------方法的参数名及参数 1.创建解析器
		 */
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression(keyEL);
		/*
		 * 2.设置解析上下文（有哪些占位符，以及每种占位符的值）
		 */

		EvaluationContext context = new StandardEvaluationContext(); // 方法的参数名和参数值
		Object[] args = pjp.getArgs(); // 参数值
		String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method); // 参数名
		for (int i = 0; i < args.length; i++) {
			context.setVariable(parameterNames[i], args[i]);
		}
		/*
		 * 3.解析得到一个key
		 */
		String key = expression.getValue(context).toString();
		Object object = RedisUtils.get(key);
		if (null != object) {
			log.info("**********从Redis中查到了数据**********");
			log.info("Redis的KEY值:" + key);
			log.info("Redis的VALUE值:" + object.toString());
			return object;
		}
		long endTime = System.currentTimeMillis();
		log.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
		log.info("**********没有从Redis查到数据**********");
		try {
			object = pjp.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("**********方法执行后增强***************");
		log.info("**********开始从MySQL查询数据**********");
		/*
		 * 后置：将数据库查到的数据保存到Redis
		 */
		boolean isSave = RedisUtils.set(key, object);
		if (isSave) {
			log.info("**********数据成功保存到Redis缓存!!!**********");
			log.info("Redis的KEY值:" + key);
			log.info("REDIS的VALUE值:" + object.toString());
		}
		return object;
	}

} 
