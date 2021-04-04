package com.demo;

import com.demo.flowable.config.AppDispatcherServletConfiguration;
import com.demo.flowable.config.ApplicationConfiguration;
import org.flowable.ui.modeler.conf.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author nullpoint
 * @date 2020/9/19
 */
@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        DatabaseConfiguration.class
})
@SpringBootApplication(
        exclude = {SecurityAutoConfiguration.class}
)
public class FlowableModelerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowableModelerApplication.class, args);
        System.out.println("flowable modeler ui started ...");
    }
}
