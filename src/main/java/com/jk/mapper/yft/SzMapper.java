package com.jk.mapper.yft;

import com.jk.model.yft.ZdModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzMapper {

    Long finddsCount(@Param("zt") Integer zt, @Param("cxname") String cxname);

    List<ZdModel> findds(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("zt") Integer zt, @Param("cxname") String cxname);

    ZdModel findOneById(@Param("szId") Integer szId);

    void updatezd(ZdModel zd);

    Long lsfindCount();

    List<ZdModel> lsfind(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
    //求已确认支出
    @Select("select sum(zdmoney) from fukuang where dsz=1 and lszt=2")
    Double findzc();
    //求已确认收入
    @Select("select sum(zdmoney) from fukuang where dsz=1 and lszt=1")
    Double findsr();
}
