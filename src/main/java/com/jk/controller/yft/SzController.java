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
    public HashMap<String , Object> lsfind(Integer start,Integer pageSize,ZdModel zd){


        return service.lsfind(start,pageSize,zd);
    }
    @RequestMapping("tofindds")
    public String tofindds(Model model){
        Double ds=service.getds();
        model.addAttribute("ds" , ds);
        return "findds";
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
        SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
        zd.setSfString(sim.format(zd.getSfDate()));
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
        SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
        zd.setSfString(sim.format(zd.getSfDate()));
        Integer zfFangShi = zd.getZfFangShi();
        String str="";
        if(zfFangShi==1){
            str="现金";
        }
        if(zfFangShi==2){
            str="支付宝";
        }
        if(zfFangShi==3){
            str="微信";
        }
        if(zfFangShi==4){
            str="转账";
        }
        zd.setZffs(str);
        mv.setViewName("lscx");
        mv.addObject("zd",zd);
        return mv;
    }
    @RequestMapping("toaddls")
    public String toaddls(Model model){
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String format = sim.format(new Date());
        model.addAttribute("format",format);
        return "addls";
    }
    @RequestMapping("addls")
    @ResponseBody
    public String addls(ZdModel zd){
        service.addls(zd);
        return null;
    }
}
