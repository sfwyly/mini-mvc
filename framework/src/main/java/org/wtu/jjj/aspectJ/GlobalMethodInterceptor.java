package org.wtu.jjj.aspectJ;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.wtu.jjj.aspectJ.interceptors.AopInterceptor;

import java.lang.reflect.Method;

/**
 * 全局方法拦截器
 * @ClassName GlobalMethodInterceptor
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 15:18
 * @Version 1.0
 **/
public class GlobalMethodInterceptor implements MethodInterceptor {

    private AopInterceptor aopInterceptor;

    public GlobalMethodInterceptor(AopInterceptor aopInterceptor) {
        this.aopInterceptor = aopInterceptor;
    }
    public GlobalMethodInterceptor(){

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("---- Interceptor  Before method execute    -----");

        //执行代理方法
        Object object = methodProxy.invokeSuper(o,args);

        System.out.println("---- Interceptor  After method execute    -----");

        return object;
    }
}
