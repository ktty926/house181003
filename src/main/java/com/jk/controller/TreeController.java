package com.jk.controller;

import com.jk.bean.Position;
import com.jk.bean.TreeBean;
import com.jk.service.TreeConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class TreeController {

    @Autowired
    private TreeConsumerService treeConsumerService;

    @RequestMapping("delPosition")
    @ResponseBody
    public String delPosition(String positionId){
        return treeConsumerService.delPosition(positionId);
    }

    @RequestMapping("findPosition")
    @ResponseBody
    public HashMap<String, Object> findPosition(Integer page, Integer rows, HttpServletRequest request){
        return treeConsumerService.findPosition(page, rows, request);
    }

    @RequestMapping("updatePosition")
    @ResponseBody
    public HashMap<String, Object> updatePosition(Position position){
        return treeConsumerService.updatePosition(position);
    }

    @RequestMapping("addPosition")
    @ResponseBody
    public HashMap<String, Object> addPosition(Position position, HttpServletRequest request){
        return treeConsumerService.addPosition(position, request);
    }

    @RequestMapping("getTree")
    @ResponseBody
    public List<TreeBean> getTree(){
        return treeConsumerService.getTree();
    }
}
