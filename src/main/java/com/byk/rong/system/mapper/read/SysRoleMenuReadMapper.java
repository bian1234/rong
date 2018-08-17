package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysRoleMenuReadMapper {

    SysRoleMenu get(String id);

    List<SysRoleMenu> list(Map<String,Object> map);

    int count(Map<String,Object> map);


    List<String> listMenuIdByRoleId(String roleId);


}