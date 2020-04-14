package org.wtu.jjj.web.handler;

import org.wtu.jjj.web.mvc.Controller;
import org.wtu.jjj.web.mvc.RequestMapping;
import org.wtu.jjj.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName HandlerManager
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:43
 * @Version 1.0
 **/
public class HandlerManager {
    //handeler Mapping映射表
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static void resolveMappingHandler(List<Class<?>> classList){
        for (Class<?> cls :classList){
            if(cls.isAnnotationPresent(Controller.class)){
                parseHandlerFromController(cls);
            }
        }
    }

    private static void parseHandlerFromController(Class<?> cls){
        Method[] methods = cls.getDeclaredMethods();
        //遍历所有实现了@RequestMapping的方法
        for(Method method : methods){
            if(!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();
            for (Parameter parameter :method.getParameters()){
                if(parameter.isAnnotationPresent(RequestParam.class)){
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);
            //组装handlerMapping
            MappingHandler mappingHandler = new MappingHandler(uri,method,cls,params);
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
