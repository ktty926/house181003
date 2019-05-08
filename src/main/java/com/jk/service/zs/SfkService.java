package com.jk.service.zs;

import com.jk.model.zs.ZdModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public interface SfkService {



    HashMap<String,Object> finddz(Integer pageSize, Integer start, Integer zt, String cxname);

    ZdModel totk(Integer szId);

    void updateDz(ZdModel zd);

    Double showss();
}
