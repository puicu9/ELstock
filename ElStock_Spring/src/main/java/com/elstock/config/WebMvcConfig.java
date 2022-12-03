package com.elstock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfiguer는 스프링의 MVC를 위하여 자바 기반의 구성 설정을 도와주는 인터페이스입니다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Value("${uploadPath}")
//    String uploadPath ;

/*   addResourceHandler 메소드
      Add handlers to serve static resources such as images, js, and, css files
      from specific locations under web application root, the classpath, and
      others.
  */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // basePath 형식으로 url을 호출할 때, uploadPath 경로를 토대로 파일을 읽어들이겠습니다.
//        String basePath = "/images/**" ;
//        registry.addResourceHandler(basePath).addResourceLocations(uploadPath) ;
//    }

    // 포트 번호 react 와 Spring 통합
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
    }

}
