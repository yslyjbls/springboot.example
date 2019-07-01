package springboot.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Lich on 2019-04-23 14:45.
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
	
	/**
	 * 注入此方法，相当于注册到spring中的bean
	 * 设置一些swagger的一些配置
	 * @return
	 */
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())  //设置API显示信息
				.select()		//选择哪些路径和api可以生成document
				.apis(RequestHandlerSelectors.basePackage("springboot.example"))  //设置拦截的路径
				.paths(PathSelectors.any()) 
				.build();
	}

	/**
	 * 设置api显示信息
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("lich测试")  //标题
				.description("哈哈哈哈，是个示例")  //描述
				.contact(new Contact("List", "www.baidu.com", "lic718@163.com"))  //联系
				.termsOfServiceUrl("127.0.0.1:8023")  //地址
				.version("v1.0")     //版本
				.build();  
	}
	
	/**
	 * 加载资源swagger-ui.html
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
        		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
