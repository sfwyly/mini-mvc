package org.wtu.jjj.web.handler;

import org.wtu.jjj.aspectJ.AspectMethodToReflectMethod;
import org.wtu.jjj.aspectJ.JointPoint;
import org.wtu.jjj.aspectJ.ProceedingJoinPoint;
import org.wtu.jjj.beans.BeanFactory;
import org.wtu.jjj.proxy.ProxyFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MappingHandler
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:41
 * @Version 1.0
 **/
public class MappingHandler {
    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    public MappingHandler(String uri, Method method, Class<?> cls, String[] args) {
        this.uri = uri;
        this.method = method;
        this.controller = cls;
        this.args = args;
    }

    //查找是否有handler能够处理该请求
    public boolean handle(ServletRequest req, ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if(!uri.equals(requestUri)){
            return false;
        }
        Object[] parameters = new Object[args.length];
        for (int i =0;i<args.length;i++){
            parameters[i] = req.getParameter(args[i]);
        }
        //这里需要考虑是否需要代理
        Object ctl = BeanFactory.getBean(controller, BeanFactory.getClassToProxyBean().containsKey(controller));

        Class<?> aspect_cls = null;
        Method aspect_method = null;
        //获取切面方法
        for (AspectMethodToReflectMethod amtfm : ProxyFactory.aspectToReflectMethodList){
            if(amtfm.getMethod_list().contains(method)){
                aspect_cls = amtfm.getAspect_cls();
                aspect_method = amtfm.getAspect_method();
                break;
            }
        }
        Object response = null;//响应
        if(aspect_cls == null){//不需要拦截
            response = method.invoke(ctl, parameters);//反射执行方法
        }else{
            //参数

            Object[] params = new Object[aspect_method.getParameters().length];
            System.out.println("--1");
            //构造Proceed
            JointPoint process = new ProceedingJoinPoint(ctl,method,parameters);
            System.out.println("--2");
            //@Around注解必须有一个参数
            params[0] =  process;
            System.out.println("--3");
            Object aspect_bean = BeanFactory.getBean(aspect_cls,false);
            System.out.println("--4");
            if(aspect_bean==null){
                aspect_bean = aspect_cls.newInstance();
                System.out.println("--5");
            }
            System.out.println("--6");
            System.out.println(params.length);
            response = aspect_method.invoke(aspect_bean,params);//执行方法
            System.out.println("--7");
        }

        res.getWriter().println(response.toString());
        return true;
    }

}
