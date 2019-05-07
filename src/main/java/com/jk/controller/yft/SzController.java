package com.jk.controller.yft;

import com.jk.model.yft.ZdModel;
import com.jk.service.yft.SzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("yftzd")
public class SzController {
    @Autowired
    private SzService service;
    @RequestMapping("findds")
    @ResponseBody
    public HashMap<String , Object> findds(Integer start,Integer pageSize,Integer zt,String cxname){


        return service.findds(start,pageSize,zt,cxname);
    }
    //收支流水查询
    @RequestMapping("lsfind")
    @ResponseBody
    public HashMap<String , Object> lsfind(Integer start,Integer pageSize){


        return service.lsfind(start,pageSize);
    }

    @RequestMapping("toqr")
    public ModelAndView toqr(Integer szId){
        ModelAndView mv = new ModelAndView();
        ZdModel zd=service.findOneById(szId);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String format = sim.format(new Date());
        zd.setZfdate(format);
        mv.setViewName("qr");
        mv.addObject("zd",zd);
        return mv;
    }
    @RequestMapping("updateds")
    @ResponseBody
    public String updatezd(ZdModel zd){
        service.updatezd(zd);
        return null;
    }
    @RequestMapping("tofindone")
    public ModelAndView tofindone(Integer szId){
        ModelAndView mv = new ModelAndView();
        ZdModel zd=service.findOneById(szId);
        mv.setViewName("findone");
        mv.addObject("zd",zd);
        return mv;
    }
    @RequestMapping("tolsfind")
    public String tolsfind(Model model){
        //支出
        Double zc=service.findzcByZt();
        //收入
        Double sr=service.findsrByZt();
        //利润
        Double lr=sr-zc;
        HashMap<String, Object> map = new HashMap<>();

        model.addAttribute("lr",lr);
        model.addAttribute("zc",zc);
        model.addAttribute("sr",sr);
        return "lsfind";
    }
    @RequestMapping("tolscx")
    public ModelAndView tolscx(Integer szId){
        ModelAndView mv = new ModelAndView();
        ZdModel zd=service.findOneById(szId);
        mv.setViewName("lscx");
        mv.addObject("zd",zd);
        return mv;
    }
}
