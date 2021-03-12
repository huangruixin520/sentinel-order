package com.ruixin.sentinel.order.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * sentinel异常捕获
 * </p>
 *
 * @author ruixin
 * @date 2021/2/20
 */
@Slf4j
public class SentinelException {


    public static String findByIdFlowException(String id, BlockException e) {
        log.info("findById接口被限流...." + e);
        return "findById接口被限流";
    }
}
