package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PaymentDao {

        //写入操作

    public  int create(Payment payment);

    //读取操作
    public Payment getPaymentById(@Param("id") Long id);


}
