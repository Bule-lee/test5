package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@Slf4j
public class PaymentController {

    //服务层调用test2
    @Resource
    private PaymentServiceImpl paymentService;
    //查看端口号
    @Value("${server.port}")
    private String serverPort;

    //DiscoveryClient 服务发现,spring的
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public  CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果："+result);
            if(result>0){
                return  new CommonResult(200,"插入数据库成功,端口是："+serverPort,result);
            }else {
                return  new CommonResult(444,"失败,端口是："+serverPort,null);
            }
    }
    /**
     *
     * @Param id
     */

    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*******查询结果："+paymentById);

        if (paymentById!=null){

            return new  CommonResult(200,"查询数据库成功,端口是："+serverPort,paymentById);
        }else {
            return  new CommonResult(444,"失败,端口是："+serverPort,null);
        }

    }

   // DiscoveryClient 服务发现,spring的
    @GetMapping(value = "/payment/discovery")
    public  Object discovery(){
       //遍历服务，获取服务名称
        List<String> services = discoveryClient.getServices();
        for (String s  :services){
            log.info("service=======>"+s);
        }
        //获取微服务下面的全部具体实例。
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+ //获取服务id
                    instance.getHost()+"\t"+      //获取主机名称
                    instance.getPort()+"\t"+       //获取端口号
                    instance.getUri());             //获取地址
        }
            return  this.discoveryClient;
    }


}
