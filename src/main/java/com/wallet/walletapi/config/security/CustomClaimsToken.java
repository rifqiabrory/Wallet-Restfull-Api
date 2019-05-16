package com.wallet.walletapi.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomClaimsToken implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        User customer = (User) user;
        Map<String, Object> additionalInfo = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        additionalInfo.put("generated_time", new Date());
        additionalInfo.put("full_name", customer.getUsername());
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);
        return defaultOAuth2AccessToken;
    }
}
