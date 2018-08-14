package com.byk.rong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *@Author:      ykbian
 *@date_time:   2018/7/27 16:49
 *@Description:
 *@param:
*/
@SpringBootApplication
@MapperScan(basePackages = {"com.byk.rong.system.mapper.read","com.byk.rong.system.mapper.write"})
public class RongApplication {

	public static void main(String[] args) {
		SpringApplication.run(RongApplication.class, args);
	}
}
