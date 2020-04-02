package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    //写入操作

    public  int create(Payment payment);

    //读取操作
    public Payment getPaymentById(@Param("id") Long id);


}
