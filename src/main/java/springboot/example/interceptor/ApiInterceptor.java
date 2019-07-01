package springboot.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * @author lich
 *
 */
@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 请求之前：
	 * 	进入handler方法之前执行
	 * 	用户身份认证、身份授权
	 *  应用场景：比如身份认证，如认证通过表示当前用户没有登录，需要此方法拦截不再向下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		System.out.println("进入拦截器1");
		//return false; 表示拦截，不再向下执行
		//return true;  表示放行
		return true;
	}

	/**
	 * 请求时：
	 * 	进入handler之后，返回modelAndView之前执行
	 *  应用场景：从modelAndView出发，将公用的模型数据（比如菜单导航）在这里传到视图，也可以在这里统一指定视图
	 */
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截1过程处理些事情……");
	}

	/**
	 * 请求完成:
	 * 	执行handler完成之后执行
	 *  应用场景：统一异常处理，统一日志处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("拦截1完成");
	}

}
