package com.hcy.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ComponentScan(
	      basePackages = "com.hcy.board",
	      excludeFilters = {
	          @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
	          @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ControllerAdvice.class)
	      }
	  )
@Import({ HibernateConfig.class })
public class AppConfig {

}
