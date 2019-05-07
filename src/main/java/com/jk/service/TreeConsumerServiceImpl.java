package com.jk.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.bean.Position;
import com.jk.bean.TreeBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class TreeConsumerServiceImpl implements TreeConsumerService {

    @Reference(retries = 3, timeout = 1200000)
    private TreeService treeService;

    @Override
    public HashMap<String, Object> findPosition(Integer page, Integer rows, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())){
                value = cookie.getValue();
            }
        }
        return treeService.findPosition(value, page, rows);
    }

    @Override
    public HashMap<String, Object> addPosition(Position position, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())){
                position.setUserId(cookie.getValue());
            }
        }
        if (position.getUserId() == null){
            //hashMap.put("", );
            return null;
        }
        return treeService.addPosition(position);
    }

    @Override
    public HashMap<String, Object> updatePosition(Position position) {
        return null;
    }

    @Override
    public String delPosition(String positionId) {
        return null;
    }

    @Override
    public List<TreeBean> getTree() {
        return treeService.getTree();
    }
}
