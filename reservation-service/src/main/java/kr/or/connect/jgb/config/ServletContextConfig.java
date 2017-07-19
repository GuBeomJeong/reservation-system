package kr.or.connect.jgb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import kr.or.connect.jgb.config.resolver.HtmlViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"kr.or.connect.jgb.controller"})
public class ServletContextConfig extends WebMvcConfigurerAdapter{
	@Bean
    public ViewResolver jspViewResolver() {
         InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(0);
        return viewResolver;
    }
	
	@Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
        return multipartResolver;
    }
	
//	@Bean
//    public ViewResolver htmlViewResolver2() {
//         InternalResourceViewResolver viewResolver = new HtmlViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/resources/html/");
//        viewResolver.setSuffix(".html");
//        viewResolver.setOrder(0);
//        return viewResolver;
//    }
    
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");  //   webapp/resources 경로를 의미
		}

}
