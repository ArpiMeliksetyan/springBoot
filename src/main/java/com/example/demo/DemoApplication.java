package com.example.demo;

import com.example.demo.util.Md5Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@EnableAsync
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    @Primary
    public TaskExecutor taskExecutorFirst(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setQueueCapacity(10000);
        return taskExecutor;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Md5Encoder();
    }

    @Bean
    public TaskExecutor taskExecutorSecond(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(25000);
        return taskExecutor;
    }

    @Bean
    public TokenStore tokenStore(DataSource dataSource){
        return new JdbcTokenStore(dataSource);
    }


    //basic authroization
    // Authorization header with value like 'Basic base64Encoded(username:password)'
}

