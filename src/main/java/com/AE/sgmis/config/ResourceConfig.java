package com.AE.sgmis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //遍历配置文件找到所有匹配项
        StandardServletEnvironment servletEnvironment = (StandardServletEnvironment) environment;
        MutablePropertySources propertySources = servletEnvironment.getPropertySources();
        propertySources.forEach(source -> {
            if (source instanceof MapPropertySource && source.getName().contains("yml")) {
                for (String name : ((MapPropertySource) source).getPropertyNames()) {
                    if (name.matches("^file\\.(.*)+\\.path$")) {
                        String basePath = environment.getProperty(name);
                        registry.addResourceHandler("/" + basePath + "/**")
                                .addResourceLocations("file:" + basePath + "\\");
                        log.info("成功加载 {} 资源路径", name);
                    }
                }
            }
        });
    }
}
