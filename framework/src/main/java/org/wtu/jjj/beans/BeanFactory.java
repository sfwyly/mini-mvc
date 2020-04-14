package org.wtu.jjj.beans;

import org.wtu.jjj.proxy.ProxyFactory;
import org.wtu.jjj.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanFactory
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 15:54
 * @Version 1.0
 **/
public class BeanFactory {

    //bean实例
    private static Map<Class<?>,Object> classToBean = new ConcurrentHashMap<>();

    //代理bean
    private static Map<Class<?>,Object> classToProxyBean = new ConcurrentHashMap<>();

    public static Map<Class<?>, Object> getClassToProxyBean() {
        return classToProxyBean;
    }

    /**
     * 获取bean
     * @param cls
     * @param isProxy 是否需要代理
     * @return
     */
    public static Object getBean(Class<?> cls,boolean isProxy){
        if(isProxy){
            return classToProxyBean.get(cls);
        }
        return classToBean.get(cls);
    }



    public static void injectProceed(){

    }

    /**
     * 初始化bean
     * @param classList 类class列表
     * @throws Exception
     */
    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreate = new ArrayList<>(classList);
        while(toCreate.size()!=0){
            int remainSize = toCreate.size();
            for (int i =0;i<toCreate.size();i++){
                if(finishCreate(toCreate.get(i))){//完成初始化
                    toCreate.remove(i);
                }
            }
            if(toCreate.size()==remainSize){
                throw new Exception("cycle dependency!");
            }

        }
    }

    /**
     * 为一个类创建实例
     * @param cls 类的class
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static boolean finishCreate(Class<?> cls) throws IllegalAccessException, InstantiationException {
        //非Bean直接返回成功
        if(!cls.isAnnotationPresent(Component.class)&&!cls.isAnnotationPresent(Bean.class)&&!cls.isAnnotationPresent(Controller.class)){
            return true;
        }

        Object bean = cls.newInstance();//类实例

        for (Field field : cls.getDeclaredFields()){
            //为autowired注解的域赋值
            if(field.isAnnotationPresent(AutoWired.class)){
                Class<?> fieldType = field.getType();
                //看beanfactory中是否对该域注册
                Object reliantBean = BeanFactory.getBean(fieldType,false);
                if(reliantBean == null){//未注册返回空
                    return false;
                }
                field.setAccessible(true);
                field.set(bean,reliantBean);
            }
        }
        classToBean.put(cls,bean);
        //
        //检查切面代理类的初始化，如果需要代理 就初始化两个（1.代理类，2.未代理类）
        resolveAspectProxy(cls);
        return true;
    }

    /**
     * 扫描Aspect注解方法
     * @param cls
     */
    private static void resolveAspectProxy(Class<?> cls) throws IllegalAccessException {
        for(Class<?> c : ProxyFactory.proxyClassList){
            if(c.getName().equals(cls.getName())){//需要代理
                //将代理类加入到代理map中
                Object bean = ProxyFactory.getProxyInstance(cls);
                for (Field field : cls.getDeclaredFields()){
                    //为autowired注解的域赋值
                    if(field.isAnnotationPresent(AutoWired.class)){
                        Class<?> fieldType = field.getType();
                        // 这里的注入域也需要考虑是否需要代理类
                        //看beanfactory中是否对该域注册
                        Object reliantBean = BeanFactory.getBean(fieldType,BeanFactory.classToProxyBean.containsKey(fieldType));
                        if(reliantBean == null){//未注册返回空
                            return ;
                        }
                        field.setAccessible(true);
                        field.set(bean,reliantBean);
                    }
                }
                BeanFactory.classToProxyBean.put(cls,bean);
            }
        }
    }
}
