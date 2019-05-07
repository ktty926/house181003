package com.jk.mapper.mapper_chh;

import com.jk.bean.user_chh.Position;
import com.jk.bean.user_chh.UserBean;

import java.util.HashMap;
import java.util.List;

public interface UserMapper_chh {

    List<UserBean> findUserList(HashMap<String, Object> hashMap2);

    long getCount();

    void saveUserToSql(UserBean user);

    List<UserBean> findUserByUserEmail(String emailCode);

    List<UserBean> findUserByphoneNumber(String phoneNumber);

    void updateUserToSql(UserBean user);

    UserBean findUserById(Integer id);

    List<Position> findPositionList();

    List<UserBean> findUserByAccount(String account);
}
