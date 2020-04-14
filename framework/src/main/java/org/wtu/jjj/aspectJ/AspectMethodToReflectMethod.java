package org.wtu.jjj.aspectJ;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AspectMethodToReflectMethod
 * @Description 从切面域到需要执行的方法映射
 * @Author 逝风无言
 * @Data 2020/4/13 11:48
 * @Version 1.0
 **/
public class AspectMethodToReflectMethod {

    //原始@Aspect注解类
    private Class<?> aspect_cls;
    //一个方法对应多个拦截方法
    private Method aspect_method;

    private Class<?> reflect_cls;
    private List<Method> method_list;

    public AspectMethodToReflectMethod(Class<?> aspect_cls, Method aspect_method) {
        this.aspect_cls = aspect_cls;
        this.aspect_method = aspect_method;
        method_list = new ArrayList<>();
    }

    public AspectMethodToReflectMethod(){
        method_list = new ArrayList<>();
    }
    public Class<?> getAspect_cls() {
        return aspect_cls;
    }

    public void setAspect_cls(Class<?> aspect_cls) {
        this.aspect_cls = aspect_cls;
    }

    public Method getAspect_method() {
        return aspect_method;
    }

    public void setAspect_method(Method aspect_method) {
        this.aspect_method = aspect_method;
    }

    public Class<?> getReflect_cls() {
        return reflect_cls;
    }

    public void setReflect_cls(Class<?> reflect_cls) {
        this.reflect_cls = reflect_cls;
    }

    public List<Method> getMethod_list() {
        return method_list;
    }

    public void setMethod_list(List<Method> method_list) {
        this.method_list = method_list;
    }
}
