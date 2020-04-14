package org.wtu.jjj.starter;

import org.wtu.jjj.proxy.ProxyFactory;
import org.wtu.jjj.beans.BeanFactory;
import org.wtu.jjj.core.ClassScanner;
import org.wtu.jjj.web.handler.HandlerManager;
import org.wtu.jjj.web.server.TomcatServer;

import java.util.List;

/**
 * @ClassName MiniAplication
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/5 20:34
 * @Version 1.0
 **/
public class MiniAplication {
    public static void run(Class<?> cls,String[] args){
        System.out.println("Hello mini-spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            String packageName = cls.getPackage().getName();
            List<Class<?>> classList = ClassScanner.scanClasses(packageName);
            //代理列表初始化必须在Bean初始化 之前 执行
            ProxyFactory.initProxyClassList(classList);
            BeanFactory.initBean(classList);

            //注册所有handlerMapping
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
