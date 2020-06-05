package com.wen.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 19:13
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    // 授权码模式
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    /**
     *  配置 哪些客户端能来认证
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                // 客户端 id
                .withClient("c1")
                // 客户端密钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                // 资源列表
                .resourceIds("res1")
                // 允许 客户端 授权的类型
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")
                // 允许的授权范围
                .scopes("all")
                // 跳转倒 授权页面
                .autoApprove(false)
                //  验证回调地址
                .redirectUris("http://www.baidu.com");
    }
    /**
     *  令牌服务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        // 令牌访问服务
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        // 令牌 存储策略
        services.setTokenStore(tokenStore);
        // 是否刷新令牌
        services.setSupportRefreshToken(true);

        // 设置令牌的增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        // 令牌默认有效期 2小时
        services.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }
    /**
     *  配置令牌访问端点(url)
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 密码模式 需要
                .authenticationManager(authenticationManager)
                // 授权码模式需要
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理服务需要
                .tokenServices(tokenServices())
                // 允许post提交访问端点
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     *  配置 令牌端点的安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // /oauth/token_key  公开
                .tokenKeyAccess("permitAll()")
                // /oauth/check_token 这个端点公开
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        // 设置授权码模式的授权码如何存取, 暂时采取内存方式
        return new InMemoryAuthorizationCodeServices();
    }
}
