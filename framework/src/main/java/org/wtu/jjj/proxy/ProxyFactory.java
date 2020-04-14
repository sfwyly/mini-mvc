package org.wtu.jjj.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.wtu.jjj.aspectJ.Around;
import org.wtu.jjj.aspectJ.Aspect;
import org.wtu.jjj.aspectJ.AspectMethodToReflectMethod;
import org.wtu.jjj.aspectJ.GlobalMethodInterceptor;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 代理工厂
 * @ClassName ProxyFactory
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 15:13
 * @Version 1.0
 **/
public class ProxyFactory {

    //代理实例列表
    public static List<Class<?>> proxyClassList = new ArrayList<>();
    //切面类与切入方法的对应关系
    public static List<AspectMethodToReflectMethod> aspectToReflectMethodList = new ArrayList<>();


    /**
     * 获取目标代理实例
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T getProxyInstance(Class<T> targetClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);//目标代理类
        enhancer.setCallback(new GlobalMethodInterceptor());//设置回调
        return (T) enhancer.create();
    }


    private static AspectMethodToReflectMethod getByAspectMethod(Method method){
        for (AspectMethodToReflectMethod amtrm : aspectToReflectMethodList){
            if(amtrm.getAspect_method().toString().equals(method.toString())){
                return amtrm;
            }
        }
        return null;
    }

    /**
     * 通过路径获取需要代理的方法
     * @param classList
     * @param pathList
     */
    public static void initProxyClassListByPathList(List<Class<?>> classList,Map<String,Method> pathList){
        //代理检测 检测符合需要代理的方法
        //暂时只支持类 TODO
        for(Class<?> cls : classList){
            for (Map.Entry<String,Method> path : pathList.entrySet()){
                if(cls.getPackage().getName().indexOf(path.getKey())>=0){//该类方法全需要代理
                    proxyClassList.add(cls);
                    //构造
                    AspectMethodToReflectMethod aspectMethodToReflectMethod = getByAspectMethod(path.getValue());
                    aspectMethodToReflectMethod.setReflect_cls(cls);
                    aspectMethodToReflectMethod.setMethod_list(Arrays.asList(cls.getDeclaredMethods()));
                    break;
                }
            }
        }
    }


    /**
     * 获取代理class列表
     * @param classList 所有class列表
     * @return
     */
    public static void initProxyClassList(List<Class<?>> classList){

        Map<String,Method> pathList = new HashMap<>();//类路径列表

        for (Class<?> cls : classList){
            if(!cls.isAnnotationPresent(Aspect.class)){
                continue;
            }
            //对@Aspect注解的类进行方法遍历

            Method[] methods = cls.getDeclaredMethods();

            for(Method method : methods){
                // TODO
                if(!method.isAnnotationPresent(Around.class)){//这里应该会有很多不同的通知类型
                    continue;
                }
                String value = method.getDeclaredAnnotation(Around.class).value();
                //该路径是具体的需要代理的class路径
                pathList.put(value,method);
                //对该切面方法进行存储
                ProxyFactory.aspectToReflectMethodList.add(new AspectMethodToReflectMethod(cls,method));

            }
        }

        //通过代理路径获取需要代理的class
        ProxyFactory.initProxyClassListByPathList(classList,pathList);

        return ;
    }

    /**
     * 解析路径 当前只支持包方法名
     * @param path
     * @return
     */
    public static List<?> resolvePath(String path){
        //org.wtu.jjj.*(..)

        //TODO

        //只解析包

        return null;
    }



    public Class<?> getProxyClass() {

        return null;
    }

//    public <T> T getProxy(Class<T> clazz){
//        return (T)Proxy.newProxyInstance(
//                getClass().getClassLoader(),
//                new Class[]{clazz},
//                new RemoteInvoker(clazz,encoder,decoder,selector)
//        );
//    }
}
