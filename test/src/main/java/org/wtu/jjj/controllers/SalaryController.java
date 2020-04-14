package org.wtu.jjj.controllers;

import org.wtu.jjj.beans.AutoWired;
import org.wtu.jjj.service.SalaryService;
import org.wtu.jjj.web.mvc.Controller;
import org.wtu.jjj.web.mvc.RequestMapping;
import org.wtu.jjj.web.mvc.RequestParam;

/**
 * @ClassName SalaryController
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:14
 * @Version 1.0
 **/
@Controller
public class SalaryController {

    @AutoWired
    private SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
