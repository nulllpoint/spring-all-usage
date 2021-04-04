package com.demo.common.validate;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <p>
 * 校验对象参数工具类 <br>
 * 校验对象中 javax.validation.constraints下注解标记的参数  (JSR303/JSR-349)<br>
 * </p>
 *
 * @author liuhoujie
 * @date 2020/10/23
 */
@SuppressWarnings({"unused"})
public class ValidationUtil {

    private ValidationUtil() {
        /* 构造器私有, 不允许创建实例 */
    }

    /**
     * 初始化 validator 检查器
     */
    private static final Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            .failFast(true) // 快速校验, 遇到参数校验不通过, 则不进行后续校验, 关闭则进行所有参数校验
            .buildValidatorFactory()
            .getValidator();


    /**
     * 校验失败 , 则抛出异常信息
     *
     * @param obj 待校验的对象
     */
    public static <T> T validate(T obj) {
        Set<ConstraintViolation<T>> validateSet = validator.validate(obj);
        if (validateSet != null && validateSet.size() > 0) {
            // throw new ServiceException(ServiceError.REQUEST_PARAMETER_ERROR.getCode(), ServiceError.REQUEST_PARAMETER_ERROR.getMessage() + ":" + validateSet.iterator().next().getMessage());
            throw new RuntimeException(validateSet.iterator().next().getMessage());
        }
        return obj;
    }
}