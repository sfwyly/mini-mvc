package org.wtu.jjj.aspectJ;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ProceedingJoinPoint
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 15:10
 * @Version 1.0
 **/

public class ProceedingJoinPoint implements JointPoint{
    //执行对象
    private Object object;
    //要执行的方法
    private Method method;
    //执行参数
    private Object[] parameters;

    public ProceedingJoinPoint(Object object, Method method, Object[] parameters) {
        this.object = object;
        this.method = method;
        this.parameters = parameters;
    }

    public Object proceed() {
        //之前已经扫描所有的@Aspect注解类
        Object result = null;
        try {
            result = method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }



}
