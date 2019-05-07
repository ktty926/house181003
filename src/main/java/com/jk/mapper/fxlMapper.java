package com.jk.mapper;

import com.jk.model.Tenant;
import com.jk.model.Total;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface fxlMapper {

    int count(@Param("tenName") String tenName,@Param("tenSite") String tenSite);

    List<Tenant> findTen(@Param("start")int start,@Param("rows") Integer rows,@Param("tenName") String tenName,@Param("tenSite") String tenSite);
    @Select("SELECT * FROM t_tenant t,t_teninf f,t_contract h,t_money m where f.zkName = t.tenName and t.tenSite = h.htHome and t.tenId = m.idTen and t.tenId = #{value}")
    Total findParticulars(Integer tenId);

}
