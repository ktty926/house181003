package com.jk.bean.user_chh;

import lombok.Data;

/**
 * @Author chh
 * @Description //TODO 
 * @Date 11:53 2019/5/7
 * @Param 
 * @return 
 **/
@Data
public class UserBean {
    private Integer id;
    private String name;
    private String account;
    private String phone;
    private Integer position;
    private String positionName;//职位名称
    private String password;
    private String email;
    //邮箱与短信验证码
    private String emailCode;
    private String phoneCode;
}
