//package com.yanni.sotrav;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
///**
// * @author Moritz Schulze
// */
//@Configuration
//@EnableAuthorizationServer
//public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore());
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//               .withClient("curl") //curl curl:password@localhost:8081/oauth/token\?grant_type=client_credentials
//               .authorities("ROLE_ADMIN")
//               .resourceIds("jaxenter")
//               .scopes("read", "write")
//               .authorizedGrantTypes("client_credentials")
//               .secret("password")
//               .and()
//               .withClient("web") //http://localhost:8081/oauth/authorize?client_id=web&response_type=token
//               //.redirectUris("http://github.com/techdev-solutions/")
//               .authorities("ROLE_ADMIN")
//               .resourceIds("jaxenter")
//               .scopes("read, write")
//               //.authorizedGrantTypes("implicit")
//               .authorizedGrantTypes("client_credentials")
//               .autoApprove(true)
//               .secret("password")
//               .and()
//               .withClient("my-trusted-client")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//				.scopes("read", "write", "trust")
//				.authorizedGrantTypes("implicit")
//				.accessTokenValiditySeconds(60);
//    }
//}
