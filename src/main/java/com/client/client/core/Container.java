package com.client.client.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hli
 * @date 2020/9/27 9:47
 */
@Slf4j
public class Container {
    private Map<String, Customer> consumerMap = new HashMap<>();
    private final StringRedisTemplate redisTemplate;
    public static boolean run;
    private ExecutorService exec;


    public Container(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addConsumer(Customer configuration) {
        if (consumerMap.containsKey(configuration.getQueen())) {
            log.warn("Key:{} this key already exists, and it will be replaced", configuration.getQueen());
        }
        if (configuration.getTask() == null) {
            log.warn("Key:{} consumer cannot be null, this configuration will be skipped", configuration.getQueen());
        }
        consumerMap.put(configuration.getQueen(), configuration);
    }

    public void destroy() {
        run = false;
        this.exec.shutdown();
        log.info("QueueListener exiting.");
        while (!this.exec.isTerminated()) {

        }
        log.info("QueueListener exited.");
    }

    public void init() {
        run = true;
        this.exec = Executors.newCachedThreadPool(r -> {
            final AtomicInteger threadNumber = new AtomicInteger(1);
            return new Thread(r, "RedisMQListener-" + threadNumber.getAndIncrement());
        });
        consumerMap = Collections.unmodifiableMap(consumerMap);
        consumerMap.forEach((k, v) -> exec.submit(
                new Lister(redisTemplate, v.getQueen(), v.getQueenTye(), v.getTask())));
    }
}
