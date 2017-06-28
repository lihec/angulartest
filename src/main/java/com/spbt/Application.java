package com.spbt;

import com.spbt.config.CommonHandlerExceptionResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 启动配置
 * @author 李贺
 * @date 2015/8/8 15:24
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                //                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/jsp/error/403.jsp");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/jsp/error/404.jsp");
                container.addErrorPages(error403Page, error404Page);
            }
        };
    }

    @Bean
    public WebMvcConfigurerAdapter intercepters() {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
            //            @Override
            //            public void addInterceptors(InterceptorRegistry registry) {
            //                SecurityInterceptor securityInterceptor = new SecurityInterceptor();
            //                securityInterceptor.setRedirectUrl("/login");
            //                securityInterceptor.setExcludes(new ArrayList<String>() {{
            //                    add("/static/**");
            //                    add("/login");
            //                    add("/login/**");
            //                    add("/logout");
            //                    add("/ex");
            //                }});
            //                registry.addInterceptor(securityInterceptor);
            //                super.addInterceptors(registry);
            //            }

            @Override
            public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
                CommonHandlerExceptionResolver handlerExceptionResolver = new CommonHandlerExceptionResolver();
                handlerExceptionResolver.setDefaultErrorView("error/error");
                handlerExceptionResolver.setDefaultStatusCode(500);
                exceptionResolvers.add(handlerExceptionResolver);
                super.configureHandlerExceptionResolvers(exceptionResolvers);
            }

            //            @Override
            //            public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //                registry.addResourceHandler("/static/**").addResourceLocations("/static/");
            //                super.addResourceHandlers(registry);
            //            }
        };
        return webMvcConfigurerAdapter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}