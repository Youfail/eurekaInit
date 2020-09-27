package com.client.client.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hli
 * @date 2020/9/27 9:59
 */
@Slf4j
public class RedisCustom implements Task {

    @Override
    public void doTask(Object t) {
        log.info("收到消息:{}",t);
    }
}
