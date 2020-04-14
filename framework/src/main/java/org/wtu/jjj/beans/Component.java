package org.wtu.jjj.beans;

import java.lang.annotation.*;

/**
 * 普通bean注册
 * @ClassName Component
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/7 10:54
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
