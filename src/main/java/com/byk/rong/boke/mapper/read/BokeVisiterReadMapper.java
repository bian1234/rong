package com.byk.rong.boke.mapper.read;

import com.byk.rong.boke.entity.BokeVisiter;
import com.byk.rong.common.mapper.read.BaseReadMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface BokeVisiterReadMapper   extends BaseReadMapper<BokeVisiter>{

    /**
     * 天数据
     */
    int countDays(@Param("days") int days);



    /**
     *   月数据
     */
    int countMooths(@Param("mooths") int mooths);
}