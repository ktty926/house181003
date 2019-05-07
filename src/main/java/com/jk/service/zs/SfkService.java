package com.jk.service.zs;


import com.jk.model.yft.ZdModel;

import java.util.HashMap;

public interface SfkService {



    HashMap<String,Object> finddz(Integer pageSize, Integer start, Integer zt, String cxname);

    ZdModel totk(Integer szId);

    void updateDz(ZdModel zd);
}
