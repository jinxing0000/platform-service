package com.bettem.modules.sys.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/20 11:30 <br>
 * @Author: 颜金星
 */
public class JwtTokenEntity implements AuthenticationToken {

    private String token;

    public JwtTokenEntity(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
