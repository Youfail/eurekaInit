package com.client.client.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author hli
 * @date 2020/9/27 10:06
 */
@Data
@Slf4j
public class Lister<T> implements Runnable{
    private final StringRedisTemplate redisTemplate;
    private final String queue;
    private final String queueType;
    private final Task task;

    public Lister(StringRedisTemplate redisTemplate, String queue, String queueType, Task task) {
        this.redisTemplate = redisTemplate;
        this.queue = queue;
        this.queueType = queueType;
        this.task = task;
    }

    @Override
    public void run() {
        log.info("启动监听队列-{}",queue);
        String value =  redisTemplate.opsForList().leftPop(queue);
        task.doTask(value);
    }
}
