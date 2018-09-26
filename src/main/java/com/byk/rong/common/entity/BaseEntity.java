package com.byk.rong.common.entity;

import java.io.Serializable;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 16:51
 * @Todo:  基础实体类，带分页
 */

public class BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int offset;   //起始页
    // 每页条数
    private int limit;    // 每页数据

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
