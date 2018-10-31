package com.byk.rong.boke.service;


/**
 * @Author: ykbian
 * @Date: 2018/10/31 9:54
 * @Todo:
 */

public interface BokeVisiterService  {


    /**
     * 按月统计
     */
    int[]  countMooth(int months);


    /**
     * 按天统计
     */
    int[]  countDay(int days);
}
