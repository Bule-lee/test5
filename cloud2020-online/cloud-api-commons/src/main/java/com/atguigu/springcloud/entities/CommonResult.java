package com.atguigu.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer  code;  //判断是否200 成功 ，404 还是500？
    private  String message; // sucess 成功
    private  T data;
    //Data 可能为Null所以自定义2个参数的构造方法。
    public  CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
