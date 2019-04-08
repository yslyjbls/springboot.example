package springboot.example.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * @author lich
 *
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {

	// 请求之前
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		System.out.println("进入拦截器");
		return true;
	}

	// 请求时
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截过程处理些事情……");
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		outputStream.write("hahahahah".getBytes("utf-8"));
	}

	// 请求完成
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("拦截完成");
	}

}
