package com.ruixin.sentinel.order.exception;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.ruixin.sentinel.order.api.ProductServiceAPI;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  feign流控/降级自定义异常resp
 * </p>
 *
 * @author ruixin
 * @date 2021/2/20
 */
@Component
public class ProductServiceAPIFallbackFactory implements FallbackFactory<ProductServiceAPI> {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public ProductServiceAPI create(Throwable e) {

        return new ProductServiceAPI() {
            /**
             * 注：返回feign-api的返回值，这里并不是直接返回给用户
             * @param id
             * @return
             */
            @Override
            public String findProductById(String id) {
                //BlockException 异常接口,包含Sentinel的五个异常
                // FlowException 限流异常
                // DegradeException 降级异常
                // ParamFlowException 参数限流异常
                // AuthorityException 授权异常
                // SystemBlockException 系统负载异常
                String resp = "";
                if (e instanceof FlowException) {
                    logger.info("feign流控规则被触发......");
                    resp = "feign流控规则被触发......";
                } else if (e instanceof DegradeException) {
                    logger.info("feign降级规则被触发...");
                    resp = "feign降级规则被触发......";
                } else {
                    logger.info("feign其他异常...");
                    resp = "feign其他异常...";
                }
                return resp;
            }
        };
    }
}
