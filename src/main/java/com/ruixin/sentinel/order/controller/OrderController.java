package com.ruixin.sentinel.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ruixin.sentinel.order.api.ProductServiceAPI;
import com.ruixin.sentinel.order.exception.SentinelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * 订单controller
 * </p>
 *
 * @author ruixin
 * @date 2021/2/20
 */
@RestController
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ProductServiceAPI productServiceAPI;


    /**
     * 流控
     * <p>
     * blockHandlerClass: 将backup方法整合在该类中统一管理
     * blockHandler： backup方法
     * <p>
     * 降级
     * <p>
     * fallbackClass： ...
     * fallback：    ...
     *
     * @return
     */
    @RequestMapping("/findById")
    @SentinelResource(value = "findById", blockHandlerClass = SentinelException.class, blockHandler = "findByIdFlowException")
    public String findById(String id) {
        logger.info("请求findById....");
        return "success";
    }

    /**
     * 流控-backup方法
     * @return
     */
//    public String findByIdFlowException(String id, BlockException e){
//        logger.info("findById接口被限流...."+e);
//        return "findById接口被限流";
//    }


    /**
     * 控制下游服务Feign-流控-降级
     *
     * @return
     */
    @RequestMapping("/createOrder")
    public String createOrder() {
        logger.info("开始创建订单....");
        String product = productServiceAPI.findProductById("1");
        logger.info("product: " + product);
        return "success";
    }
}
