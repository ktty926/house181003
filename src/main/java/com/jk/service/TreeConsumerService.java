package com.jk.service;

import com.jk.bean.Position;
import com.jk.bean.TreeBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface TreeConsumerService {

    List<TreeBean> getTree();

    HashMap<String, Object> addPosition(Position position, HttpServletRequest request);

    HashMap<String, Object> updatePosition(Position position);

    String delPosition(String positionId);

    HashMap<String, Object> findPosition(Integer page, Integer rows, HttpServletRequest request);
}
