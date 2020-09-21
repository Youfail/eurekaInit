package com.client.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Component
public class ExcutorConfig {
    @Bean
    public TaskExecutor sysExecutor(){
        ThreadPoolTaskExecutor executors =
                new ThreadPoolTaskExecutor();
        executors.setCorePoolSize(0);
        executors.setMaxPoolSize(5);
        executors.setKeepAliveSeconds(2);
        executors.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executors.setQueueCapacity(10000);
        executors.setThreadNamePrefix("task-");
        return executors;
    }
}
