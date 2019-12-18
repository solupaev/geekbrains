package erth.task_tracker.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("erth.task_tracker")
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
        registry.addMapping("/**").allowedOrigins("http://127.0.0.1:8888");
    }
}
