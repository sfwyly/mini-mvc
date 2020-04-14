package org.wtu.jjj.aspect;

import org.wtu.jjj.aspectJ.Around;
import org.wtu.jjj.aspectJ.Aspect;
import org.wtu.jjj.aspectJ.ProceedingJoinPoint;
import org.wtu.jjj.beans.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName SalaryAspect
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/8 18:28
 * @Version 1.0
 **/
@Component
@Aspect
public class SalaryAspect {

    @Around("org.wtu.jjj.controllers")
    public Object around(ProceedingJoinPoint pjp) throws InvocationTargetException, IllegalAccessException {

        System.out.println(" Before ");
        Object object = pjp.proceed();
        System.out.println(" After ");
        return object;
    }
}
