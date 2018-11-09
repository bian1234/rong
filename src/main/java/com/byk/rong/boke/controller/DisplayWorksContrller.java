package com.byk.rong.boke.controller;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.boke.service.DisplayWorksService;
import com.byk.rong.common.config.Constant;
import com.byk.rong.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
public class DisplayWorksContrller extends BaseController {


    @Autowired
    private DisplayWorksService displayWorksService;

    @Value("${byk.pagePath}")
    private String pagePath;

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 9:13
     * @Description: 新增
     * @param:
     */
    @RequiresPermissions("boke:project:add")
    @PostMapping("insert")
    @ResponseBody
    public Map insert(BokeDisplayWorks bokeDisplayWorks, MultipartFile fileObj) {
        if (fileObj.getSize() > 1048576) {
            logger.info("上传的文件大小是：" + fileObj.getSize());
            return insertFailedResponse();
        }
        //编写图片上传的业务逻辑方法
        //获取图片名称
        String filename = fileObj.getOriginalFilename();
        //获取图片扩展名
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        filename = (System.currentTimeMillis()) + "." + ext;
//        文件存进图片服务器
        try {
            fileObj.transferTo(new File(pagePath + filename));
            logger.info("图片上传到了" + pagePath + filename);
        } catch (Exception e) {
            logger.info("图片上传失败");
        }

//      项目信息存进数据库
        bokeDisplayWorks.setPageAddress(pagePath);
        bokeDisplayWorks.setPageName(filename);
        bokeDisplayWorks.setDelFlag(Constant.DEL_FLAG_USER);
        bokeDisplayWorks.setCreateTime(new Date());
        bokeDisplayWorks.setCreateUser(getUserId());
        if (displayWorksService.insertSelective(bokeDisplayWorks) > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/6 16:00
     * @Description: 图片上传
     * @param:
     */
    @PostMapping("fileInput")
    @ResponseBody
    public Map fileInput() {
        logger.info("上传图片了");
        return null;
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 9:13
     * @Description: 删除
     * @param:
     */
    @PostMapping("delete")
    @ResponseBody
    public Map delete(String id) {
        try {
            BokeDisplayWorks bokeDisplayWorks = displayWorksService.selectById(id);
            bokeDisplayWorks.setDeleteTime(new Date());
            bokeDisplayWorks.setDelFlag(Constant.DEL_FLAG_DISUSER);
            bokeDisplayWorks.setDeleteUser(getUserId());
            if (displayWorksService.updateSelective(bokeDisplayWorks) > 0) {
                logger.info("用户" + getUserId() + "删除了名称为 《 " + bokeDisplayWorks.getProjectName() + " 》 的作品数据");
                return deleteSuccessResponse();
            } else {
                return deleteFailedResponse();
            }
        } catch (Exception e) {
            return deleteFailedResponse();
        }
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 9:13
     * @Description: 批量删除
     * @param:
     */
    @PostMapping("deleteBatch")
    @ResponseBody
    public Map deleteBatch(@RequestParam("ids[]") String[] ids) {
        for (String id : ids) {
            try {
                BokeDisplayWorks bokeDisplayWorks = displayWorksService.selectById(id);
                bokeDisplayWorks.setDeleteTime(new Date());
                bokeDisplayWorks.setDelFlag(Constant.DEL_FLAG_DISUSER);
                bokeDisplayWorks.setDeleteUser(getUserId());
                logger.info("用户" + getUserId() + "删除了名称为《 " + bokeDisplayWorks.getProjectName() + " 》的作品数据");
                displayWorksService.updateSelective(bokeDisplayWorks);
            } catch (Exception e) {
                logger.info("删除作品信息时出现空指针");
            }
        }
        return deleteSuccessResponse();
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 修改
     * @Description:
     * @param:
     */
    @PostMapping("update")
    @ResponseBody
    public Map update(BokeDisplayWorks bokeDisplayWorks) {
        bokeDisplayWorks.setUpdateTime(new Date());
        bokeDisplayWorks.setUpdateUser(getUserId());
        if (displayWorksService.updateSelective(bokeDisplayWorks) > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }


    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 9:14
     * @Description: 列表查询
     * @param:
     */
    @GetMapping("/list")
    @ResponseBody
    public List<BokeDisplayWorks> menuList(BokeDisplayWorks bokeDisplayWork) {
        bokeDisplayWork.setDelFlag(Constant.DEL_FLAG_USER);
        List<BokeDisplayWorks> bokeDisplayWorks = displayWorksService.selectByParams(bokeDisplayWork);
        return bokeDisplayWorks;
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 10:03
     * @Description: 跳转作品管理界面
     * @param:
     */
    @GetMapping()
    public String list() {
        return "/boke/project/project";
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/11/5 9:14
     * @Description: 跳转增加页面
     * @param:
     */
    @GetMapping("add")
    public String add() {
        return "/boke/project/add";
    }


    /**
     *@Author: ykbian
     *@date_time: 2018/11/5 9:14
     *@Description: 跳转编辑界面
     *@param:
     */
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id")String id) {
        BokeDisplayWorks bokeDisplayWorks = displayWorksService.selectById(id);
        bokeDisplayWorks.setPageAddress(bokeDisplayWorks.getPageAddress()+"/"+bokeDisplayWorks.getPageName());
        model.addAttribute("bokeDisplayWorks",bokeDisplayWorks);
        return "/boke/project/edit";
    }
}
