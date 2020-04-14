package org.wtu.jjj.proxy;

import java.lang.reflect.Method;

/**
 * 代理实例，用于存储每个方法调用是否需要代理实例
 * @ClassName ProxyInstacne
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/8 10:26
 * @Version 1.0
 **/
public class ProxyInstacne {

    private Class<?> cls;//类
    private Method method;//方法
    private Boolean isProxy = true;//是否需要代理实例

    public ProxyInstacne(Class<?> cls, Method method, Boolean isProxy) {
        this.cls = cls;
        this.method = method;
        this.isProxy = isProxy;
    }

    public ProxyInstacne(Class<?> cls, Method method) {
        this.cls = cls;
        this.method = method;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Boolean getProxy() {
        return isProxy;
    }

    public void setProxy(Boolean proxy) {
        isProxy = proxy;
    }
}
