package com.byk.rong.common.entity;

import com.byk.rong.common.config.BaseConstant;

import java.io.Serializable;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 16:51
 * @Todo:  基础实体类，带分页
 */

public class BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer offset;   //起始页
    // 每页条数
    private Integer limit;    // 每页数据

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public BaseEntity(Integer offset, Integer limit) {
        if (offset == null){
            offset = BaseConstant.PAGE_NO;
        }
        if (limit == null){
            limit = BaseConstant.PAGE_SIZE;
        }
        this.offset = offset;
        this.limit = limit;
    }

    public BaseEntity() {
    }
}
