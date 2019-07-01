package springboot.example.configuration;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class JSR303Config extends WebMvcConfigurerAdapter {

	@Value("${spring.messages.basename}")
	private String basename;

	@Bean
	@Primary
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setUseCodeAsDefaultMessage(false);
		resourceBundleMessageSource.setDefaultEncoding("UTF-8"); // 重复定义
		resourceBundleMessageSource.setBasenames(basename.split(","));
		return resourceBundleMessageSource;
	}

	@Bean
	@Primary
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setProviderClass(HibernateValidator.class);
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}

	@Override
	public org.springframework.validation.Validator getValidator() {
		return validator();
	}

	/**
	 * 方法级别的单个参数验证开启
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

}
