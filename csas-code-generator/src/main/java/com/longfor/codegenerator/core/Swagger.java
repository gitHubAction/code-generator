package com.longfor.codegenerator.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Restful API 访问路径:
 * http://IP:port/{context-path}/swagger-ui.html
 * @author zhangshihao01
 */
@Configuration
@EnableSwagger2
public class Swagger {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uhope.web.codegenerator.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 访问：/swagger-ui.html
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("title")
                .description("description")
                .termsOfServiceUrl("http://localhost:8081/swagger-ui.html")
                .contact(new Contact("zhangshihao01", "", "zhangshihao01@longfor.com"))
                .version("3.0.0")
                .build();
    }
}
