package com.demo.common.orika.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

/**
 * @author liuhoujie
 * @date 2020/8/13
 * @desc 用于 转换json时的自定义转换器 (单向的)
 */
public class OrikaJsonConverter<T> extends CustomConverter<T, String> {
    @Override
    public String convert(T source, Type<? extends String> destinationType, MappingContext mappingContext){
        try {
            return new ObjectMapper().writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("orika json convert error",e);
        }
    }
}

