# mini-mvc
A simple MVC framework

## Support  
1.IOC  
2.DispatcherServlet  
3.AOP 

## Use
This mvc framework have a same use method as Spring mvc! **But Different implementations**

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

## Details  
Our project maintains a mapping between the Aspect methods and the proxied methods.
```
public class AspectMethodToReflectMethod {
    
    private Class<?> aspect_cls;
    private Method aspect_method;

    private Class<?> reflect_cls;
    private List<Method> method_list;
}
```
This class is initialized when the framework starts.When executing the proxied method.First,check out the corresponding aspect method.Second,find out proxied method parameters and send to ProceedingJointPoint.Third,send ProceedingJointPoint to the parameter of aspect method.Finally,perform the faceted method by reflection.
