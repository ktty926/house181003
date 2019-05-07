package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Total {
    /*租客表*/
    private Integer tenId;//租客id
    private String tenName;//租客姓名
    private String tenSite;//房源地址
    private Integer tenHomeId;//房号
    private String startDate;//合同开始时间
    private String endDate;//合同结束时间
    private Integer tenState;//状态
    /*租客信息*/
    private Integer zkId;//租客信息ID
    private String zkName;//租客姓名
    private Integer zkPhone;//租客练习电话
    private String zksfId;//租客身份证
    private String jjMan;//紧急联系人
    private Integer jjPhone;//紧急联系人电话
    /*合同信息*/
    private Integer htId;
    private String htHome;
    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")// 处理从	后端到前端的时间
    private Date htStartDate;
    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")// 处理从	后端到前端的时间
    private Date htEndDate;
    private Double htzMoney;
    private Double htyMoney;
    private String fzzffs;
    /*费用信息表*/
    private Integer moneyId;// 费用id
    private Double waterCount;//水费
    private Double electCount;//电费
    private Double gasCount;//气费
    private Integer idTen;//关联id
    /*共计xx年*/
    private Integer htYear;
    private Integer htMouth;
    private Integer htDay;
}
