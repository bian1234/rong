package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.SysUser;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:06
 * @Todo:
 */

public interface UserService extends BaseService<SysUser>{

    public SysUser findByUsername(String username);

    public int save(SysUser sysUser);
}
