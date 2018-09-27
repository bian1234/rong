package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.mapper.read.MenuReadMapper;
import com.byk.rong.system.mapper.write.MenuWriteMapper;
import com.byk.rong.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 14:17
 * @Todo:
 */
@Service
public class MenuServiceImpl  implements MenuService {

    @Autowired
    private MenuReadMapper menuReadMapper;
    @Autowired
    private MenuWriteMapper menuWriteMapper;
    @Override
    public int insertSelective(Menu menu) {
        return 0;
    }

    @Override
    public int updateSelective(Menu menu) {
        return 0;
    }

    @Override
    public Menu selectById(String id) {
        return null;
    }

    @Override
    public List<Menu> selectByParams(Menu menu) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
