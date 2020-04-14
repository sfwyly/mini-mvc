# mini-mvc
A simple MVC framework

## Support  
1.IOC  
2.DispatcherServlet  
3.AOP 

## Use
This mvc framework have a same use method as Spring mvc!

## Recently
ProceedingJointPoint Around advice

## Examples
> Around advice
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
