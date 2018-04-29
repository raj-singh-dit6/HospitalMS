package com.hospitalms.configuration;

import java.util.List;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hospitalms")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer {

	/**
	 * Configures message converter json from / to object.
	 * @return
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	/**
	 * Configures objectMapper for responses and requests.
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	/**
	 * Configures Java Mail Sender with specifications.
	 * @return
	 */
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("raj.singh.dit8@gmail.com");
		mailSender.setPassword("ajax@200");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");// Prints out everything on screen

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	/**
	 * Configures Multi-Part resolver.
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		return new StandardServletMultipartResolver();
	}

	/**
	 * Configures URL path matcher to avoid case sensitive URLs & allow *.* URLs
	 * @return
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false); // To disable case sensitive URL
		configurer.setPathMatcher(matcher);
		configurer.setUseSuffixPatternMatch(false); // To allow URL with *.*
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	/**
	 * Configures content negotiation & allow URL with *.*
	 * @return
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

	/**
	 * Configures  ModelMapper - Entity to/from DTO object
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
