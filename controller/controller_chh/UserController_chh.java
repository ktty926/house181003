package com.jk.controller.controller_chh;

import com.alibaba.druid.util.StringUtils;
import com.jk.bean.user_chh.Position;
import com.jk.bean.user_chh.UserBean;
import com.jk.service.service_chh.UserService_chh;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class UserController_chh {
    @Autowired
    private UserService_chh userService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Author chh
     * @Description //TODO 查询用户
     * @Date 13:05 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("findUserList")
    @ResponseBody
    public HashMap<String,Object> findUserList(Integer page, Integer rows){
        return userService.findUserList(page,rows);
    }
    /**
     * @Author chh
     * @Description //TODO 用户新增的邮箱验证
     * @Date 14:43 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("regEmail")
    @ResponseBody
    public HashMap<String, Object> regEmail(String emailCode) {
        HashMap<String, Object> hash = new HashMap<>();

        HashMap<String, Object> hashMap = new HashMap<>();
        HtmlEmail htmlEmail = new HtmlEmail();//创建一个htmlEmail实例对象

            int code = (int) ((Math.random() * 9 + 1) * 100000);
            redisTemplate.opsForValue().set("code",code);
            redisTemplate.expire("code",1, TimeUnit.MINUTES);
            if (emailCode.matches("^\\w+@(\\w+\\.)+\\w+$") && !StringUtils.isEmpty(emailCode)) {

                try {
                    htmlEmail.setHostName("smtp.qq.com");
                    htmlEmail.setCharset("utf-8");//设置发送的字符类型
                    htmlEmail.setFrom("1303474648@qq.com", "金科教育");//设置发送人的邮箱和用户名，用户名可以自行定义
                    htmlEmail.setAuthentication("1303474648@qq.com", "aipkxxbcrxbzbagj");// aipkxxbcrxbzbagj 设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
                    htmlEmail.setSubject("北京金科教育");//设置发送主题
                    htmlEmail.setMsg("您的验证码为：" + code + "，验证码将在1分钟后过期，请尽快输入验证码完成注册！");//设置发送内容
                    htmlEmail.addTo(emailCode);//设置收件人
                    htmlEmail.send();//进行发送
                    hashMap.put("email", emailCode);
                    hashMap.put("emailCode", code);
                    hashMap.put("status", "0");

                    System.out.println("邮件发送成功");
                    System.out.println(htmlEmail.getSmtpPort());
                } catch (Exception e) {
                    System.out.println("邮件发送失败");
                    hashMap.put("msg", "暂时只支持QQ邮箱");
                    e.printStackTrace();
                }
            } else {
                if (StringUtils.isEmpty(emailCode)){
                    hashMap.put("msg", "不能为空");
                }else{
                    hashMap.put("msg", "暂时只支持QQ邮箱");
                }
            }
        return hashMap;
    }
    /**
     * @Author chh
     * @Description //TODO  用户新增前 短信验证
     * @Date 15:13 2019/5/7
     * @Param 获取短信验证码
     * @return 
     **/
    @RequestMapping("duanxin")
    @ResponseBody
    public HashMap<String, Object>  phoneTest(String phoneNumber){
        HashMap<String, Object> hash = new HashMap<>();

        Date date = new Date();
        //判断是否今天三次上限
        SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
        String format = si.format(date);

        List range = redisTemplate.opsForList().range(phoneNumber + format, 0, -1);
        if(range.size() >= 3){
            hash.put("code",1);
            hash.put("msg","该手机号今天已经发送三次，请明天再试");
            return hash;
        }
        //判断是否1 分钟以内
        Object yzm = redisTemplate.opsForValue().get(phoneNumber);
        if(yzm != null){
            hash.put("code",1);
            hash.put("msg","一分钟内不能重复获取，请稍后");
            return hash;
        }

            Integer randomNumber= (int)(Math.random()*899999+100000);
       /* String number="#code#="+randomNumber+"&#company#=111";
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("mobile",phoneNumber);
        hashmap.put("tpl_id","156143");  randomNumber.toString()
        hashmap.put("tpl_value",number);
        hashmap.put("key","c14f7d6e7c6ee43940fef391c41ad7fa");
        String str = HttpClient.sendGet("http://v.juhe.cn/sms/send",hashmap);
*/
            redisTemplate.opsForValue().set(phoneNumber,randomNumber);
            redisTemplate.expire(phoneNumber,1, TimeUnit.MINUTES);

            redisTemplate.opsForList().leftPush(phoneNumber+format,randomNumber);
            redisTemplate.expire(phoneNumber+format,24,TimeUnit.HOURS);
            hash.put("yzm",randomNumber);
            hash.put("code",0);
            hash.put("msg","短信发送成功，一分钟内有效");

        return hash;
    }
    /**
     * @Author chh
     * @Description //TODO 类型查询
     * @Date 19:09 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("findPositionList")
    @ResponseBody
    public List<Position> findPositionList(){
        return userService.findPositionList();
    }


    /**
     * @Author chh
     * @Description //TODO  用户新增
     * @Date 14:22 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("saveUser")
    @ResponseBody
    public HashMap<String, Object> saveUser(UserBean user){
        HashMap<String, Object> hash = new HashMap<>();
        //先判断账号是否存在
        List<UserBean> userlist = userService.findUserByAccount(user.getAccount());
        if(userlist != null && userlist.size() > 0){
            hash.put("code",1);
            hash.put("msg","该账号已被注册");
            return hash;
        }
        //判断邮箱是否存在
        List<UserBean> use = null;
        if (!StringUtils.isEmpty(user.getEmail())){
            use = userService.findUserByUserEmail(user.getEmail());
        }

        if (use.size() > 0 && use != null){
            hash.put("status", "error");
            hash.put("msg", "该邮箱已经有人注册");
        }
        //判断手机是否存在
       List<UserBean> user1 =  userService.findUserByphoneNumber(user.getPhone());
        if(user1 != null){
            hash.put("code",1);
            hash.put("msg","该手机号已经注册");
        }
        try {
            hash = userService.saveUserToSql(user);
            return hash;
        }catch (Exception e){
            e.printStackTrace();
        }
        hash.put("code",1);
        hash.put("msg","程序错误");
        return hash;
    }
    /**
     * @Author chh
     * @Description //TODO  用户修改
     * @Date 14:22 2019/5/7
     * @Param
     * @return
     **/
    @RequestMapping("updateUser")
    @ResponseBody
    public HashMap<String, Object> updateUser(UserBean user){
        HashMap<String, Object> hash = new HashMap<>();
        try {
            hash = userService.updateUser(user);
            return hash;
        }catch (Exception e){
            e.printStackTrace();
        }
        hash.put("code",1);
        hash.put("msg","程序错误");
        return hash;
    }











}
