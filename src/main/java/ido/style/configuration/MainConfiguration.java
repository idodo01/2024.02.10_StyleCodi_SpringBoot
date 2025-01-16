package ido.style.configuration;

import ido.style.converter.MultipartConverter;
import ido.style.converter.StyleMultipartConverter;
import ido.style.interceptor.CategoryInterceptor;
import ido.style.interceptor.StyleCategoryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "ido.style.aspect")
@EnableAspectJAutoProxy
public class MainConfiguration implements WebMvcConfigurer {
    @Autowired private CategoryInterceptor categoryInterceptor;
    @Autowired private StyleCategoryInterceptor styleCategoryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryInterceptor)
                .addPathPatterns("/", "/product", "/styleProduct", "/user/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MultipartConverter());
        registry.addConverter(new StyleMultipartConverter());
    }

}
