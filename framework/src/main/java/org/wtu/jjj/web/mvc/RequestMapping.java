package org.wtu.jjj.web.mvc;

import java.lang.annotation.*;

/**
 * @ClassName RequestMapping
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:11
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
