package org.wtu.jjj.service;

import org.wtu.jjj.beans.Bean;

/**
 * @ClassName SalaryService
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 18:41
 * @Version 1.0
 **/
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience * 5000;
    }
}
