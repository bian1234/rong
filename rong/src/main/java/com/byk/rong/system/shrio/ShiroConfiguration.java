//package com.byk.rong.system.shrio;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
///**
// * @Author: bianyakun
// * @Date: 2018/5/31 11:57
// * @todo: 这是权限控制的配置类  ----------->暂时理解为拦截器的作用，和springMVC的dispatcher作用一样。
// * Shiro几个核心的类，第一就是ShiroFilterFactory,第二就是SecurityManager，springboot采用@Bean注入的方式，且看以下代码
// *
// */
//@Configuration
//public class ShiroConfiguration {
//
//
//
//
//    /**
//     * @Author: bianyakun
//     * @Date: 2018/5/31 14:54
//     * @todo:   注入ShiroFilterFactoryBean
//     * @param:   * @param null
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        System.out.println("注入ShiroFilterFactoryBean");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置SecuritManager   ================>Shiro 自带的写法
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 拦截器   因为要拦截不止一个请求，所以是map
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
//        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setLoginUrl("/login");
//
//
//
//        //匿名访问静态资源=====但是这里的静态资源为什么访问不到呢
//        filterChainDefinitionMap.put("/static/assets/css/**", "anon");
//
//
//
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        // 未授权界面;------------>未登录的话去403界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//
//    }
//
//
//
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //设置realm.
//        securityManager.setRealm(myShiroRealm());
//        return securityManager;
//    }
//
//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
//     * ）
//     *
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //散列的次数，比如散列两次，相当于 md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(2);
//        return hashedCredentialsMatcher;
//    }
//
//
//    /**
//     * 身份认证realm;
//     */
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        //将设置的加密方法设置为匹配证书（字面意思），符合这个规则的就通过
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return myShiroRealm;
//    }
//
//    /**
//     * 开启shiro aop注解支持.
//     * 使用代理方式;所以需要开启代码支持;
//     *
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//}
//
