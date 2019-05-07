package com.jk.service.yft;

import com.jk.model.yft.ZdModel;

import java.util.HashMap;

public interface SzService {
    HashMap<String, Object> findds(Integer start, Integer pageSize,Integer zt,String cxname);

    ZdModel findOneById(Integer szId);

    void updatezd(ZdModel zd);

    HashMap<String, Object> lsfind(Integer start, Integer pageSize);

    Double findzcByZt();

    Double findsrByZt();
}