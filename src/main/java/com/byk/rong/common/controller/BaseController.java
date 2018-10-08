package com.byk.rong.common.controller;


import com.byk.rong.common.util.SaltUtil;
import com.byk.rong.common.util.ShiroUtils;
import com.byk.rong.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    @Autowired
    private UserService userService;
    public  Map<String,Object> resultMap ;

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *@Author:      ykbian
     *@date_time:   2018/8/16 11:38
     *@Description: 从请求头里面获取token信息，前提是把token信息存在请求头里面==========这个项目用不到。
     *@param:
    */
    protected String getUserId() {
       String userName = ShiroUtils.getUserName();
       /**
        *   这里先设置一个假的登录对象；
        */
//        return userService.findByUsername(userName).getId();
        return "1b78e5ecc3c511e8bf1d52540033f252";

    }

    /**
     * 参数为空返回值
     * @return
     */
    public Map emptyParamResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2001);
        resultMap.put("msg","EMPTY_PARAM");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求成功返回值
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result,String count){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        resultMap.put("count",count);
        return resultMap;
    }
    /**
     * 查询请求结果为空返回值
     * @return
     */
    public Map queryEmptyResponse(){
        resultMap = new LinkedHashMap<>();

        resultMap.put("code",4004);
        resultMap.put("msg","RESULT_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 请求失败返回值
     * @return
     */
    public Map failedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",5002);
        resultMap.put("msg","FAILED_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     * @return
     */
    public Map insertFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",5003);
        resultMap.put("msg","INSERT_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     * @return
     */
    public Map insertSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 插入请求成功返回值
     * @return
     */

    public Map insertSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @return
     */
    public Map updateSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @param result
     * @return
     */
    public Map updateSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     * @return
     */
    public Map updateFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",5004);
        resultMap.put("msg","UPDATE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * DELETE请求失败返回值
     * @return
     */
    public Map deleteFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",5005);
        resultMap.put("msg","DELETE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    public Map deleteSuccessResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code",2000);
        resultMap.put("msg","DELETE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }
}
