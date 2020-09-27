package com.client.client.core;

import lombok.Builder;
import lombok.Data;

/**
 * @author hli
 * @date 2020/9/27 9:53
 */
@Data
@Builder
public class Customer {
    private String queen;

    private String queenTye;

    private Task task;


}
