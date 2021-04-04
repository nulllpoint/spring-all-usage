package com.demo.utils.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @description 图片验证码校验异常定义
 * @author liuhoujie
 * @date 2019年9月1日
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
