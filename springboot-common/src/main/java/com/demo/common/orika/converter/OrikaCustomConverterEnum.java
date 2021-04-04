package com.demo.common.orika.converter;

import ma.glasnost.orika.Converter;

/**
 * @author liuhoujie
 * @date 2020/8/14
 * @desc 自定义转换器的id 枚举
 */
public enum OrikaCustomConverterEnum {
    /**
     * 自定义 json 转换器
     */
    ORIKA_JSON_CONVERTER("ORIKA_JSON_CONVERTER", new OrikaJsonConverter<>()),
    ;

    String id;
    Converter value;

    public String id() {
        return id;
    }

    public Converter value() {
        return value;
    }

    OrikaCustomConverterEnum(String id, OrikaJsonConverter value) {
        this.id = id;
        this.value = value;
    }
}
