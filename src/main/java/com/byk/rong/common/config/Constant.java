package com.byk.rong.common.config;

/**
 * @Author: ykbian
 * @Date: 2018/8/9 10:22
 * @Todo:    状态信息常量
 */

public class Constant {

    /**
     *  系统默认的分页信息
     */
    public static int PAGE_NO = 0 ;
    public static int PAGE_SIZE = 99999999;

   /**
    *  默认不删除
    */
   public static String DEL_FLAG_USER = "0";
   public static String DEL_FLAG_DISUSER = "1";

   /**
    *  用户状态默认启用
    */
   public static String USER_NORMALITY = "0";
   public static String USER_DISABLE = "1";


   /**
    *  顶级菜单的父id和菜单名称
    */
    public static String TOP_MENU_PARIENT_ID = "0";
    public static String TOP_MENU_NAME = "顶级菜单";

    /**
     *  用户自行注册的创建者信息
     */
    public static String REGISTER_BY_ONESELF = "00000000000000000000000000000000";
}
