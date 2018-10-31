package com.byk.rong.boke.service.impl;

import com.byk.rong.boke.mapper.read.BokeVisiterReadMapper;
import com.byk.rong.boke.service.BokeVisiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ykbian
 * @Date: 2018/10/31 9:55
 * @Todo:
 */
@Service
public class BokeVisiterServiceImpl  implements BokeVisiterService {

    @Autowired
    private BokeVisiterReadMapper bokeVisiterReadMapper;


    @Override
    public int[] countMooth(int months) {
        int[] ints = new int[months];
        //循环查询这些月份内的数据
        for (int i = months-1;i>=0;i--){
            int sum = bokeVisiterReadMapper.countMooths(i);
            ints[months-1-i] = sum;
        }
        return  ints;
    }

    @Override
    public int[] countDay(int days) {
        int[] ints = new int[days];
        //循环查询这些天数内的数据
        for (int i = days-1;i>=0;i--){
            int sum = bokeVisiterReadMapper.countDays(i);
            ints[ days-1-i] = sum;
        }
        return  ints;
    }
}
