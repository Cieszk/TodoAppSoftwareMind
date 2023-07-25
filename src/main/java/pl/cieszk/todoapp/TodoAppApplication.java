package pl.cieszk.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.cieszk.todoapp.utils.RequestCounterFilter;

@SpringBootApplication
@Validated
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

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
