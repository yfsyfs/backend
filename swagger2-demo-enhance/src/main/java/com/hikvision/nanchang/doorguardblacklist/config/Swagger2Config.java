package com.hikvision.nanchang.doorguardblacklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hikvision.nanchang.doorguardblacklist.common.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: swagger配置类 
* @author xiexin6  
* @date 2019年3月14日  
*
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(Constants.SWAGGER2_BASEPACKAGE)).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(Constants.SWAGGER2_TITLE).description(Constants.SWAGGER2_DESCRIPTION)
				.contact("谢鑫6").version(Constants.SWAGGER2_VERSION).build();
	}

}
