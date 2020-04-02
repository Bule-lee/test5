package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //定义要调用的路径前缀常量：单机
   // private  static final String  PAYMENT_URL_PREFIX="http://localhost:8001";
    //集群: 负载均衡方式
    private  static final String  PAYMENT_URL_PREFIX="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("插入成功====>serial"+payment);
        return restTemplate.postForObject(PAYMENT_URL_PREFIX + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("传入id成功===>"+id);
        return  restTemplate.getForObject(PAYMENT_URL_PREFIX +"/payment/get/"+id,CommonResult.class);
    }


}
