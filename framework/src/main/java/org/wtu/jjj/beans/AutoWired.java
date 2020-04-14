package org.wtu.jjj.beans;

import java.lang.annotation.*;

/**
 * @ClassName AutoWired
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 15:35
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
