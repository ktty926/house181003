package com.jk.mapper.zs;

import com.jk.model.yft.ZdModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SfkMapper {


    Long getCount(@Param("zt") Integer zt, @Param("cxname") String cxname);


    List<ZdModel> finddz(@Param("pageSize") Integer pageSize, @Param("start") Integer start, @Param("zt") Integer zt, @Param("cxname") String cxname);

    @Select("select * from fukuang where szId=#{szId}")
    ZdModel totk(Integer szId);

    void updateDz(ZdModel zd);
}
