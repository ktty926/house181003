package com.jk.service.service_chh;

import com.jk.bean.user_chh.Position;
import com.jk.bean.user_chh.UserBean;
import com.jk.mapper.mapper_chh.UserMapper_chh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl_chh implements UserService_chh {
    @Autowired
    private UserMapper_chh userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
/**
 * @Author chh
 * @Description //TODO 用户分页查询
 * @Date 14:27 2019/5/7
 * @Param
 * @return
 **/
    @Override
    public HashMap<String, Object> findUserList(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
        long count = userMapper.getCount();
        hashMap2.put("startIndext",(page-1)*rows);
        hashMap2.put("endIndext",rows);
        List<UserBean> users= userMapper.findUserList(hashMap2);

        hashMap.put("total",count);
        hashMap.put("rows",users);
        return hashMap;

    }
    /**
     * @Author chh
     * @Description //TODO 用户新增的邮箱验证
     * @Date 14:43 2019/5/7
     * @Param
     * @return
     **/
    @Override
    public List<UserBean> findUserByUserEmail(String emailCode) {
        return userMapper.findUserByUserEmail(emailCode);
    }
/**
 * @Author chh
 * @Description //TODO 用户新增前 短信验证
 * @Date 15:48 2019/5/7
 * @Param 
 * @return 
 **/
    @Override
    public List<UserBean> findUserByphoneNumber(String phoneNumber) {
        return userMapper.findUserByphoneNumber(phoneNumber);
    }

    /**
 * @Author chh
 * @Description //TODO         用户新增/注册
 * @Date 14:27 2019/5/7
 * @Param
 * @return
 **/
    @Override
    public HashMap<String, Object> saveUserToSql(UserBean user) {
        HashMap<String, Object> hash = new HashMap<>();
        Integer code = (Integer) redisTemplate.opsForValue().get("code");
        String  phoneNumber= user.getPhone();
        Object phone = redisTemplate.opsForValue().get(phoneNumber);
        if(!code.toString().equals(user.getEmailCode())){
            hash.put("code",1);
            hash.put("msg","邮箱验证码不符");
            return hash;
        }
        if(!phone.toString().equals(user.getPhoneCode())){
            hash.put("code",1);
            hash.put("msg","手机验证码不符");
            return hash;
        }
            userMapper.saveUserToSql(user);
            hash.put("code",0);
            hash.put("msg","注册成功");

            return hash;
    }

    /**
 * @Author chh
 * @Description //TODO       用户修改
 * @Date 14:27 2019/5/7
 * @Param
 * @return
 **/
    @Override
    public HashMap<String, Object> updateUser(UserBean user) {
        HashMap<String, Object> hash = new HashMap<>();
            userMapper.updateUserToSql(user);
            hash.put("code",0);
            hash.put("msg","修改成功");
            return hash;
    }
    /**
     * @Author chh
     * @Description //TODO 
     * @Date 16:48 2019/5/7
     * @Param 
     * @return 
     **/
    @Override
    public UserBean findUserById(Integer id) {
        return userMapper.findUserById(id);
    }
/**
 * @Author chh
 * @Description //TODO 查询职位
 * @Date 19:13 2019/5/7
 * @Param
 * @return
 **/
    @Override
    public List<Position> findPositionList() {
        return userMapper.findPositionList();
    }
/**
 * @Author chh
 * @Description //TODO 根据账号查用户
 * @Date 19:59 2019/5/7
 * @Param
 * @return
 **/
    @Override
    public List<UserBean> findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

}
