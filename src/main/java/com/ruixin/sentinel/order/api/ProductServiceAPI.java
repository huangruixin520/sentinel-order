package com.ruixin.sentinel.order.api;

import com.ruixin.sentinel.order.exception.ProductServiceAPIFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 商品服务远程调用
 * </p>
 *
 * @author ruixin
 * @date 2021/2/20
 */
@FeignClient(value = "sentinel-product",url = "${product.host}", fallbackFactory = ProductServiceAPIFallbackFactory.class)
public interface ProductServiceAPI {


    /**
     * 根据ID获取商品信息
     */
    @RequestMapping("/product/findProductById/{id}")
    String findProductById(@PathVariable("id") String id);

}
