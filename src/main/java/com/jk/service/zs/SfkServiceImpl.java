package com.jk.service.zs;

import com.jk.mapper.zs.SfkMapper;
import com.jk.model.zs.ZdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SfkServiceImpl implements  SfkService{
    @Autowired
    private SfkMapper sfkMapper;





    @Override
    public HashMap<String,Object> finddz(Integer pageSize, Integer start,Integer zt,String cxname) {
        Long count=sfkMapper.getCount(zt,cxname);
        List<ZdModel> find=sfkMapper.finddz(pageSize,start,zt,cxname);
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("total",count);
        hashMap.put("rows",find);
        return hashMap;
    }

    @Override
    public ZdModel totk(Integer szId) {
        ZdModel zd=sfkMapper.totk(szId);
        return zd;
    }

    @Override
    public void updateDz(ZdModel zd) {
        sfkMapper.updateDz(zd);
    }
}
