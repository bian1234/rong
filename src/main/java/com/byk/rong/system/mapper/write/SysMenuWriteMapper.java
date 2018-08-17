package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysMenuWriteMapper {

    int save(SysMenu menu);

    int update(SysMenu menu);

    int remove(String menuId);

    int batchRemove(Long[] menuIds);


}