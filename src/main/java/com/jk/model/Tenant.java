package com.jk.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tenant {
    private Integer tenId;//租客id
    private String tenName;//租客姓名
    private String tenSite;//房源地址
    private Integer tenHomeId;//房号
    private String startDate;//合同开始时间
    private String endDate;//合同结束时间
    private Integer tenState;//状态
}
