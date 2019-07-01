package springboot.example;

import java.nio.charset.Charset;
import java.util.List;

<<<<<<< Updated upstream
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
=======

>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import springboot.example.annotation.CustomRequestMappingHandlerMapping;
import springboot.example.configuration.Aliyun;
import springboot.example.interceptor.ApiInterceptor;
import springboot.example.interceptor.ApiInterceptor2;


/**
 * 注解配置类
 * @author lich
 *
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {
	
	/**
	 * 注册自定义拦截器
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new ApiInterceptor());
		registry.addInterceptor(new ApiInterceptor2());
	}

	/**
	 * 设置响应的字符编码
	 * @return
	 */
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		/*
        1.需要先定义一个convert转换消息的对象；
        2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        3.在convert中添加配置信息
        4.将convert添加到converters中
         */
        //1.定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
		converters.add(responseBodyConverter());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false); // 支持后缀匹配
	}
	
	/**
	 * 注册请求的版本请求方法
	 */
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
	}
	
<<<<<<< Updated upstream
	@Value("${aliyun.appKey}")
	private String appKey;
	@Value("${aliyun.appSecret}")
	private String appSecret;
	@Value("${aliyun.bucket}")
	private String bucket;
	@Value("${aliyun.endPoint}")
	private String endPoint;
	
	/**
	 * 注册自定义类，如果想要使用此类，可以在需要的类里面注入 @Autowired 注解注入
	 * 如：
	 *	@Autowired
     *	private Aliyun aliyun;
	 */
	@Bean
	public Aliyun aliyun(){
		return Aliyun.options()
				.setAppKey(appKey)
				.setAppSecret(appSecret)
				.setBucket(bucket)
				.setEndPoint(endPoint)
				.build();
	}
	
=======
>>>>>>> Stashed changes
}
