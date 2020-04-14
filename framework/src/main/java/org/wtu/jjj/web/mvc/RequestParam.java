package org.wtu.jjj.web.mvc;

import java.lang.annotation.*;

/**
 * @ClassName RequestParam
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:13
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    String value();
}
