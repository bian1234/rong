package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysRoleMenuWriteMapper {



    int save(SysRoleMenu roleMenu);

    int update(SysRoleMenu roleMenu);

    int remove(String id);

    int batchRemove(String[] ids);


    int removeByRoleId(String roleId);

    int removeByMenuId(String menuId);

    int batchSave(List<SysRoleMenu> list);
}