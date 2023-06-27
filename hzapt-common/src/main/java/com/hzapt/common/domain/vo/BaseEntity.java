package com.hzapt.common.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 操作时间
     */
    public Date updateTime;
    /**
     * 是否删除 0-未删除,  1-删除
     */
    public Integer isDel;

}
