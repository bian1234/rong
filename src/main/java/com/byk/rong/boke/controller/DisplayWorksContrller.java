package com.byk.rong.boke.controller;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.boke.service.DisplayWorksService;
import com.byk.rong.common.config.Constant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/11/5 9:12
 * @Todo:
 */
@Controller
@RequestMapping("/boke/project")
public class DisplayWorksContrller  extends BaseController {


    @Autowired
    private DisplayWorksService displayWorksService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:13
     *@Description:  新增
     *@param:
    */


    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:13
     *@Description: 删除
     *@param:
    */
    @PostMapping("delete")
    @ResponseBody
    public Map delete(String id){
        try {
            BokeDisplayWorks bokeDisplayWorks = displayWorksService.selectById(id);
            bokeDisplayWorks.setDeleteTime(new Date());
            bokeDisplayWorks.setDelFlag(Constant.DEL_FLAG_DISUSER);
            bokeDisplayWorks.setDeleteUser(getUserId());
            if (displayWorksService.updateSelective(bokeDisplayWorks) > 0){
                logger.info("用户"+getUserId()+"删除了名称为"+bokeDisplayWorks.getProjectName()+"的作品数据");
                return deleteSuccessResponse();
            }else {
                return deleteFailedResponse();
            }
        }catch (Exception e){
            return deleteFailedResponse();
        }
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:13
     *@Description: 批量删除
     *@param:
    */
    @PostMapping("deleteBatch")
    @ResponseBody
    public Map deleteBatch(@RequestParam("ids[]") String[] ids){
        for (String id: ids) {
            try {
                BokeDisplayWorks bokeDisplayWorks = displayWorksService.selectById(id);
                bokeDisplayWorks.setDeleteTime(new Date());
                bokeDisplayWorks.setDelFlag(Constant.DEL_FLAG_DISUSER);
                bokeDisplayWorks.setDeleteUser(getUserId());
                logger.info("用户"+getUserId()+"删除了名称为《 "+bokeDisplayWorks.getProjectName()+" 》的作品数据");
                displayWorksService.updateSelective(bokeDisplayWorks);
            }catch (Exception e){
                logger.info("删除作品信息时出现空指针");
            }
        }
        return deleteSuccessResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 修改
     *@Description:
     *@param:
    */

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:14
     *@Description: 列表查询
     *@param:
    */
    @GetMapping("/list")
    @ResponseBody
    public List<BokeDisplayWorks> menuList(BokeDisplayWorks bokeDisplayWork){
        bokeDisplayWork.setDelFlag(Constant.DEL_FLAG_USER);
        List<BokeDisplayWorks> bokeDisplayWorks = displayWorksService.selectByParams(bokeDisplayWork);
        return bokeDisplayWorks;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 10:03
     *@Description:  跳转作品管理界面
     *@param:
    */
    @GetMapping()
    public String list(){
        return "/boke/project/project";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:14
     *@Description: 跳转增加页面
     *@param:
    */
    @GetMapping("add")
    public String add(){
        return "/boke/project/add";
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/11/5 9:14
     *@Description:  跳转编辑界面
     *@param:
    */
}
