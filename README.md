# mini-mvc
一个简单的mvc框架

## 支持  
1.IOC  
2.DispatcherServlet  
3.AOP 

## 使用
支持与Spring MVC 同样的注解使用  

## 最近更新
ProceedingJointPoint环绕通知  

## 使用示例
> 环绕通知  
```
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
```
