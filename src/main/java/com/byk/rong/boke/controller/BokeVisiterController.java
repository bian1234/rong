package com.byk.rong.boke.controller;

import com.byk.rong.boke.service.BokeVisiterService;
import com.byk.rong.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/31 9:57
 * @Todo:
 */
@Controller
@RequestMapping("/boke/visiter")
public class BokeVisiterController  extends BaseController {

    @Autowired
    private BokeVisiterService bokeVisiterService;


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/31 10:46
     *@Description:  跳转到博客数据统计界面
     *@param:
    */
    @GetMapping("count")
    public String BokeCount(){
        return "/boke/count";
    }
    /**
     *@Author:      ykbian
     *@date_time:   2018/10/31 9:57
     *@Description:  统计最近七天
     *@param:
    */
    @ResponseBody
    @GetMapping("/weekDetails")
    public Map weekDetails(){
        Map map = new HashMap();
        //日期数组
        String[] dates = new String[7];
        for (int i = 6;i>=0;i--){
            //获取今天的日期
            Date today = new Date();
            //86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
            Date date = new Date(today.getTime() - 86400000L*i);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            dates[6-i] = dateStr;
        }
        //访客人数数组
        int[] visiter = bokeVisiterService.countDay(7);
        map.put("dates",dates);
        map.put("visiter",visiter);
        return map;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/31 9:58
     *@Description:  统计最近三十天
     *@param:
    */
    @ResponseBody
    @GetMapping("/moothDetails")
    public Map moothDetails(){
        Map map = new HashMap();
        //日期数组
        String[] dates = new String[31];
        for (int i = 30;i>=0;i--){
            //获取今天的日期
            Date today = new Date();
            //86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
            Date date = new Date(today.getTime() - 86400000L*i);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            dates[30-i] = dateStr;
        }
        //访客人数数组
        int[] visiter = bokeVisiterService.countDay(31);
        map.put("dates",dates);
        map.put("visiter",visiter);
        return map;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/31 9:58
     *@Description:  最近的十二个月的访客数据
     *@param:
    */
    @ResponseBody
    @GetMapping("/yearDetails")
    public Map yearDetails(){
        Map map = new HashMap();
        //生成格式化的日期数组
        String[] dates = new String[12];
        for (int i = 11;i>=0;i--){
            // 固定的获取月份数字
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -i);
            Date m = c.getTime();
            String mon = format.format(m);
            dates[11-i] = mon;
        }
        //访客人数数组
        int[] visiter = bokeVisiterService.countMooth(12);
        map.put("dates",dates);
        map.put("visiter",visiter);
        return map;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/11/2 17:01
     *@Description: 跳转到项目经验列表界面
     *@param:
    */
    @GetMapping("list")
    public String list(){
        return "/boke/project/project";
    }
}
