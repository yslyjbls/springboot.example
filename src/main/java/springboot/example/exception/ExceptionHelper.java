package springboot.example.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理，可自定义、可用已有的异常，可以按照自定义返回结构返回，这样可以对用户操作有较好的体验
 * 基于@RestControllerAdvice + @ExceptionHandler 的实现
 * @author lich
 *
 */
@RestControllerAdvice
@ResponseBody
public class ExceptionHelper {
	
	/**
	 * 封装返回数据
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handlerException(Exception e){
		return new String(e.getMessage());
	}

}
