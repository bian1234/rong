//package com.byk.rong.common.util;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.eis.SessionDAO;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.security.Principal;
//import java.util.Collection;
//import java.util.List;
//
///**
// *@Author:      ykbian
// *@date_time:   2018/8/17 10:45
// *@Description: 这是大神写的，貌似是用来从session中获取user信息的。
// *@param:
//*/
//public class ShiroUtils {
//
//    @Autowired
//    private static SessionDAO sessionDAO;
//
//
//    /**
//     *  从token里面获取当前登录对象
//     */
//    public static Subject getSubjct() {
//        return SecurityUtils.getSubject();
//    }
//
//    /**
//     * 当前登录对象里面获取具体的登录信息
//     */
//    public static SysUser getUser() {
//        Object object = getSubjct().getPrincipal();
//        return (SysUser)object;
//    }
//
//    /**
//     * 用户id
//     */
//    public static String getUserId() {
//        return getUser().getId();
//    }
//
//
//   /**
//    * 退出=================
//    */
//    public static void logout() {
//        getSubjct().logout();
//    }
//
//
//
//    /**
//     * =====这是什么意思？
//     */
//    public static List<Principal> getPrinciples() {
//        List<Principal> principals = null;
//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//        return principals;
//    }
//}
