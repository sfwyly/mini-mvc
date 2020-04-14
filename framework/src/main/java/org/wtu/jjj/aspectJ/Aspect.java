package org.wtu.jjj.aspectJ;

import java.lang.annotation.*;

/**
 * 切面
 * @ClassName Aspect
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 10:30
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {
}
