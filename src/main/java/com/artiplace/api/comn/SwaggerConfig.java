package com.artiplace.api.comn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
 
    private String version = "1.0";
    private String title = "Artist Place API " + version;
    
    /* swagger-ui 페이지 연결 핸들러 설정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        
        super.addResourceHandlers(registry);
    }
    
    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("[SUCCESS] 200 OK.").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("[FAIL] 500 SERVER ERROR!").responseModel(new ModelRef("Error")).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("[FAIL] 404 PAGE NOT FOUND!").build());
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.tags(new Tag("공통 API", "공통 API Controller"))
        		.consumes(getConsumeContentTypes()) 
        		.produces(getProduceContentTypes())
//        		.groupName(version)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.artiplace.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) // responseMessages 설정 적용
                .globalResponseMessage(RequestMethod.POST,responseMessages)
                .globalOperationParameters(parameters());
//        		.securitySchemes(Lists.newArrayList(apiKey()));
    }
    
//    private Predicate<String> postPaths() {
//    	return PathSelectors.any(); // 모든 경로를 api 문서로 만들경우
////      return or(regex("/admin/.*"), regex("/user/.*"));  // 일부 경로를 api 문서로 만들 경우
//     // return regex("/admin/.*");
//    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description("<h3>Artist Place API Reference for Developers</h3>") 
                .contact(new Contact("MAIVVE", "https://maivve.tistory.com", "banner4@naver.com"))
                .license("Maivve License")
             // .licenseUrl("https://www.cristoval.com/etc/webPrivacy.jsp")
                .version(version)
                .build();
    }
    
    // 수신 시 데이터 포맷
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<String>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    // 출력 시 데이터 포맷
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<String>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
    
    private List<Parameter> parameters() {
    	ParameterBuilder pb = new ParameterBuilder();
    	pb.name("accept")
    	.modelRef(new ModelRef("String"))
    	.parameterType("header")
    	.required(true)
    	.hidden(true)
    	.build();
    	
    	List<Parameter> parameters = new ArrayList<Parameter>();
    	parameters.add(pb.build());
    	return parameters;
    }
}