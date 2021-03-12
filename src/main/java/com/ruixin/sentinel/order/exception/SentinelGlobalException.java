package com.ruixin.sentinel.order.exception;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  sentinel全局异常捕获
 * </p>
 *
 * @author ruixin
 * @date 2021/2/20
 */
//@Component
public class SentinelGlobalException implements UrlBlockHandler{

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {

        //BlockException 异常接口,包含Sentinel的五个异常
        // FlowException 限流异常
        // DegradeException 降级异常
        // ParamFlowException 参数限流异常
        // AuthorityException 授权异常
        // SystemBlockException 系统负载异常

        httpServletResponse.setContentType("application/json;charset=utf-8");
        String resp = "";
        if (e instanceof FlowException) {
            logger.info("流控规则被触发......");
            resp = "流控规则被触发......";
        } else if (e instanceof DegradeException) {
            logger.info("降级规则被触发...");
            resp = "流控规则被触发......";
        } else {
            logger.info("其他异常...");
            resp = "其他异常...";
        }
        httpServletResponse.getWriter().write(resp);
    }

}
