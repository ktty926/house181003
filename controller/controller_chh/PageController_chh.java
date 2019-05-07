package com.jk.controller.controller_chh;

import com.jk.bean.user_chh.UserBean;
import com.jk.service.service_chh.UserService_chh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController_chh {
    @Autowired
    private UserService_chh userService;

    /**
     * @Author chh
     * @Description //TODO   跳转到用户展示
     * @Date 19:28 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("userlist")
    public String userlist(){
        return "userlist";
    }
/**
 * @Author chh
 * @Description //TODO 跳到新增页面
 * @Date 20:27 2019/5/7
 * @Param
 * @return
 **/
@RequestMapping("addUser")
public String addUser(){

    return "addUser";
}
    /**
     * @Author chh
     * @Description //TODO 用户回显
     * @Date 16:45 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("userDialog")
    public String userDialog(Integer id, Model model){
        UserBean user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "userDialog";
    }



}
