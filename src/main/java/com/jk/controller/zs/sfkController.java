package com.jk.controller.zs;


import com.jk.model.zs.ZdModel;
import com.jk.service.zs.SfkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("sfk")
public class sfkController {
    @Autowired
    private SfkService sfkService;

    @RequestMapping("finddz")
    @ResponseBody
    public HashMap<String,Object> finddz(Integer pageSize,Integer start,Integer zt,String cxname){

      return   sfkService.finddz(pageSize,start,zt,cxname);

    }

    @RequestMapping("toshow")
    public String show(){
        System.out.println("2222");
        return "show";
    }

    @RequestMapping("totk")
    public ModelAndView totk(Integer szId){
        ModelAndView mv=new ModelAndView();
        ZdModel zd=sfkService.totk(szId);

        mv.addObject("zd",zd);
        mv.setViewName("dzTk");
        return mv;
    }

    @RequestMapping("updateDz")
    @ResponseBody
    public String updateDz(ZdModel zd){
        sfkService.updateDz(zd);
        return null;
    }



    @RequestMapping("zctang")
    public ModelAndView zctang(Integer szId){
        ModelAndView mv=new ModelAndView();
        ZdModel zd=sfkService.totk(szId);

        mv.addObject("zd",zd);
        mv.setViewName("ztHx");
        return mv;
    }




}
