package pl.cieszk.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.cieszk.todoapp.utility.RequestCounterFilter;

@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<RequestCounterFilter> requestCounterFilterFilterRegistrationBean() {
        FilterRegistrationBean<RequestCounterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestCounterFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
