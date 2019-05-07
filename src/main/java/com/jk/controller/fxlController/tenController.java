package com.jk.controller.fxlController;

import com.jk.model.Tenant;
import com.jk.model.Total;
import com.jk.service.fxlService.tenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("demo")
public class tenController {
    @Autowired
    private tenService tenService;

    @RequestMapping("findTen")
    @ResponseBody
    public HashMap<String, Object> findTen(Integer page, Integer rows, Tenant tenant) {
        System.out.println("ssss");
        return  tenService.findTen(page,rows,tenant);
    }

    @RequestMapping("particulars")
    @ResponseBody
    public Total findParticulars(Integer tenId){
        return tenService.findParticulars(tenId);
    }
}
