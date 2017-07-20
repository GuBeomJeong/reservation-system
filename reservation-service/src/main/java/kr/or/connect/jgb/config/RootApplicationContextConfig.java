package kr.or.connect.jgb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
		"kr.or.connect.jgb.dao",
		"kr.or.connect.jgb.service"	
})
@Import({DbConfig.class})
public class RootApplicationContextConfig {
	
}
