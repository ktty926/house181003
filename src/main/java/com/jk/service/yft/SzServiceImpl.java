package com.jk.service.yft;

import com.jk.mapper.yft.SzMapper;
import com.jk.model.yft.ZdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SzServiceImpl implements SzService {
    @Autowired
    private SzMapper mapper;

    @Override
    public HashMap<String, Object> findds(Integer start, Integer pageSize,Integer zt,String cxname) {
        Long count = mapper.finddsCount(zt,cxname);
        List<ZdModel> list=mapper.findds(start,pageSize,zt,cxname);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

    @Override
    public ZdModel findOneById(Integer szId) {
        ZdModel zd=mapper.findOneById(szId);
        return zd;
    }

    @Override
    public void updatezd(ZdModel zd) {
        mapper.updatezd(zd);
    }

    @Override
    public HashMap<String, Object> lsfind(Integer start, Integer pageSize,ZdModel zd,Integer tcse) {


        HashMap<String , Object> hash=new HashMap<>();
        hash.put("tcse",tcse);
        hash.put("start",start);
        hash.put("pageSize",pageSize);
        hash.put("mindate",zd.getMindate());
        hash.put("maxdate",zd.getMaxdate());
        hash.put("room",zd.getRoom());
        hash.put("zdType",zd.getZdType());
        hash.put("zfFangShi",zd.getZfFangShi());
        hash.put("lszt",zd.getLszt());
        Long count = mapper.lsfindCount(hash);
        List<ZdModel> list=mapper.lsfind(hash);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",count);
        return map;

    }
    //求支出
    @Override
    public Double findzcByZt() {

        return mapper.findzc();
    }
    //求收入
    @Override
    public Double findsrByZt() {
        return mapper.findsr();
    }

    @Override
    public void addls(ZdModel zd) {
        mapper.addls(zd);
    }

    @Override
    public Double getds() {

        return mapper.getds();
    }
}
