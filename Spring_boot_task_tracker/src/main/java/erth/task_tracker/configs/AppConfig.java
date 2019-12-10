package erth.task_tracker.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("erth.task_tracker")
public class AppConfig implements WebMvcConfigurer {

}