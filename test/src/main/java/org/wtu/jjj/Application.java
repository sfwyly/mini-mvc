package org.wtu.jjj;

import org.wtu.jjj.starter.MiniAplication;

/**
 * @ClassName Application
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/4 18:52
 * @Version 1.0
 **/
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MiniAplication.run(Application.class,args);
    }
}
