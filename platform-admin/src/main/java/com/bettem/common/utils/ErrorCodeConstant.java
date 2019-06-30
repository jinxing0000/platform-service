package com.bettem.common.utils;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:  错误代码常量类
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/20 17:09 <br>
 * @Author: 颜金星
 */
public class ErrorCodeConstant {
    /**
     *  请求正常返回状态
     */
    public static final int OK=0;
    /**
     *  为空错误代码
     */
    public static final int IS_EMPTY=11;
    /**
     *   会话失效
     */
    public static final int SESSION_FAILURE=2;
    /**
     *  token 无效
     */
    public static final int TOKEN_INVALID=3;
    /**
     *  验证码错误
     */
    public static final int CAPTCHA_ERROR=4;
    /**
     *  验证码失效
     */
    public static final int CAPTCHA_INVALID=6;
    /**
     *  用户不存在
     */
    public static final int USER_NONE=7;
    /**
     *  用户密码错误
     */
    public static final int USER_PASSWORD_ERROR=8;
    /**
     *  请勿重复提交错误
     */
    public static final int RESUBMIT_ERROR=9;
    /**
     *  系统错误
     */
    public static final int ERROR=500;

}
