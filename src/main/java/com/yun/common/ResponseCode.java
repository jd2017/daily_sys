package com.yun.common;

import lombok.Data;

/**
 * 统一返回状态码
 * @author ZYJ 2018-6-22
 */
@Data
public class ResponseCode {
    public static final String NONE="100";//主题结果为空
    public static final String SUCCESS="200";//成功
    public static final String SUCCESS_ONE="201";//成功，主题结果为空
    public static final String UNEXPECT="300";//结果和预期不同
    public static final String REMOTE_QUERY_FAIL="301";//远程获取数据失败
    public static final String PARAMETER_NULL="400";//参数为空
    public static final String EXCEPTION="477";//服务器发生异常
    public static final String FAIL="500";//失败
}
