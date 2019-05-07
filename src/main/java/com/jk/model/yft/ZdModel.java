package com.jk.model.yft;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ZdModel {
    private Integer szId;//   int    收支Id;
    private String room;//     String 账单房号
    private String sfren;//    String 收付款方
    private Double zdmoney;//    double 账单金额
    private String zdType;//     String 账单类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sfDate;//    date  收付款时间
    private Integer zfFangShi;//    int  支付方式 1 代表现金  2 代表支付宝 3 代表微信 4 代表转账
    private Integer dsz;//      int   带收支状态   1 .待收状态 2.待支状态
    private Integer lszt;//     int    带流水状态  1 .待收流水收入状态 2.待支出流水收入状态

    private String zfdate;//    date  支付时间
    private String beizhu;//    date  备注
    private String startdate;//账单失效起始时间
    private String entdate;//账单失效结束时间
}
