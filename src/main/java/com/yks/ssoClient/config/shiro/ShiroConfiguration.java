package com.yks.ssoClient.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 ** Shiro 配置
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @Author 125C01063135
 * @Create 2017-10-24 9:44
 * @Desc Shiro配置文件
 **/
@Configuration
public class ShiroConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        LOGGER.info("===============Shiro启动监听配置===============");
        ShiroFilterFactoryBean bean = new MyShiroFilterFactoryBean();

        //必须配置SecurityManager
        bean.setSecurityManager(securityManager);

        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //配置退出过滤器，其中的具体退出代码shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        bean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/index");
        //未授权界面;
        bean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean(name = "formAuthenticationFilter")
    public MyFormAuthenticationFilter getFormAuthenticationFilter() {
        MyFormAuthenticationFilter filter = new MyFormAuthenticationFilter();
        filter.setUsernameParam("username");
        filter.setPasswordParam("password");
        // filter.setRememberMeParam("rememberMe");
        filter.setLoginUrl("/login");
        filter.setSuccessUrl("/index");
        return new MyFormAuthenticationFilter();
    }

    @Bean
    public MyExceptionResolver resolver() {
        return new MyExceptionResolver();
    }

    /**
     * Shiro生命周期处理器
     * LifecycleBeanPostProcessor用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调，
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调。
     * 如UserRealm就实现了Initializable，而DefaultSecurityManager实现了Destroyable。具体可以查看它们的继承关系
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        realm.setCacheManager(ehCacheManager());
        return realm;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * 安全管理器，Shiro核心,必须配置
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(77);
        return matcher;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * AOP式方法级权限检查
     * Shiro提供了相应的注解用于权限控制，如果使用这些注解就需要使用AOP的功能来进行判断，
     * 如Spring AOP；Shiro提供了Spring AOP集成用于权限注解的解析和验证。
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

}
