package org.wtu.jjj.aspectJ;

import java.lang.annotation.*;

/**
 * 环绕切面
 * @ClassName Around
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 10:32
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Around {
    String value();
}
