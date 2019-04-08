package springboot.example.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

/**
 * 基于@Aspect + @AfterThrowing 自定义全局异常
 * @author cui
 *
 */
@Aspect
@Component
public class MyExceptionAspect {

	private static final Logger logger = LoggerFactory.getLogger(MyExceptionAspect.class);

	/**
	 * 切点
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void webPointcut() {
	}

	/**
	 * 具体处理逻辑
	 * @param e
	 */
	@AfterThrowing(pointcut = "webPointcut()", throwing = "e")
	public void handlerThrowing(Exception e) {
		logger.error("发现异常！" + e.getMessage());
		logger.error(JSON.toJSONString(e.getStackTrace()));
		// 这里输入友好性信息
		writeContent("出现异常" + e.getMessage());
	}

	/**
	 * 往前端写数据
	 * @param content
	 */
	private void writeContent(String content) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/plain;charset=UTF-8");
		response.setHeader("icop-content-type", "exception");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.print(content);
		writer.flush();
		writer.close();
	}

}
