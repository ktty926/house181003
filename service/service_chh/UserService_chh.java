package com.jk.service.service_chh;

import com.jk.bean.user_chh.Position;
import com.jk.bean.user_chh.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService_chh {
    HashMap<String, Object> findUserList(Integer page, Integer rows);

    HashMap<String, Object> saveUserToSql(UserBean user);

    List<UserBean> findUserByUserEmail(String emailCode);

    List<UserBean> findUserByphoneNumber(String phoneNumber);

    HashMap<String, Object> updateUser(UserBean user);

    UserBean findUserById(Integer id);

    List<Position> findPositionList();

    List<UserBean> findUserByAccount(String account);
}
