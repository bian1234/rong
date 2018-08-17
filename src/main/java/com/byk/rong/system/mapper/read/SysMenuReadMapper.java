package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysMenuReadMapper {
    SysMenu get(String menuId);

    List<SysMenu> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    List<SysMenu> listMenuByUserId(String id);

    List<String> listUserPerms(String id);
}