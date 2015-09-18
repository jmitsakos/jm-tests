package joker.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by imitsakos on 14/7/2015.
 */
@Configuration
@ComponentScan("joker.controller")
@PropertySource("classpath:/freemarker.properties")
public class FreemarkerConfig {

    @Value("${spring.view.prefix}")
    private String viewPrefix;

    @Value("${spring.view.suffix}")
    private String viewSuffix;

    @Value("${spring.freemarker.templateLoaderPath}")
    private String templateLoaderPath;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath(templateLoaderPath);
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix(viewPrefix);
        viewResolver.setSuffix(viewSuffix);
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

}
