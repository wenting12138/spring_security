package com.wen.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 19:29
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration
public class TokenConfig {

//    @Bean
//    public TokenStore tokenStore(){
//        // 令牌存储策略: 内存方式: 生成普通令牌
//        return new InMemoryTokenStore();
//    }

    private static final String SIGNING_KEY = "uaa123";

    @Bean
    public TokenStore tokenStore(){
        // 令牌存储策略: 内存方式: jwt令牌
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称密钥
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

}
